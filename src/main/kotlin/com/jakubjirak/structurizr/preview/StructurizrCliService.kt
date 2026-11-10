/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import java.io.File
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.concurrent.TimeUnit
import java.util.zip.ZipFile

/**
 * Application-level service that manages the Structurizr CLI binary.
 *
 * Handles discovery, download, extraction, and invocation of the CLI tool.
 */
@Service
class StructurizrCliService {

    private val log = Logger.getInstance(StructurizrCliService::class.java)
    private var cliPath: String? = null
    private var cliVersion: String? = null

    /**
     * Ensures the CLI is available, downloading it if necessary.
     * Invokes [callback] with the resolved path or `null` on failure.
     */
    fun ensureCliAvailable(project: Project, callback: (String?) -> Unit) {
        cliPath?.let { path ->
            if (File(path).exists()) {
                callback(path)
                return
            }
        }
        findCliPath()?.let { found ->
            cliPath = found
            callback(found)
            return
        }
        downloadCli(project, callback)
    }

    /** Searches well-known locations and the system PATH for the CLI binary. */
    fun findCliPath(): String? {
        val possiblePaths = listOf(
            "/usr/local/bin/structurizr-cli",
            "/opt/homebrew/bin/structurizr-cli",
            "${System.getProperty("user.home")}/.structurizr/cli/structurizr.sh",
            "${System.getProperty("user.home")}/.structurizr/structurizr-cli.jar",
            "${System.getenv("LOCALAPPDATA") ?: ""}\\structurizr\\cli\\structurizr.bat",
            "/usr/bin/structurizr-cli",
            "/usr/local/bin/structurizr",
        )
        for (path in possiblePaths) {
            val file = File(path)
            if (file.exists() && file.canExecute()) {
                log.info("Found Structurizr CLI at: $path")
                return path
            }
        }

        val pathCli = findInPath("structurizr-cli") ?: findInPath("structurizr")
        if (pathCli != null) return pathCli

        val pluginCli = pluginCliPath
        if (pluginCli != null && File(pluginCli).exists()) return pluginCli

        return null
    }

    private fun findInPath(executable: String): String? {
        val pathEnv = System.getenv("PATH") ?: return null
        for (dir in pathEnv.split(File.pathSeparator)) {
            val file = File(dir, executable)
            if (file.exists() && file.canExecute()) return file.absolutePath
            val batFile = File(dir, "$executable.bat")
            if (batFile.exists()) return batFile.absolutePath
            val cmdFile = File(dir, "$executable.cmd")
            if (cmdFile.exists()) return cmdFile.absolutePath
        }
        return null
    }

    private val pluginCliPath: String?
        get() = "${System.getProperty("user.home")}/.structurizr/cli/structurizr-cli.jar"

    private fun downloadCli(project: Project, callback: (String?) -> Unit) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Downloading Structurizr CLI", true) {
            override fun run(indicator: ProgressIndicator) {
                try {
                    indicator.text = "Downloading Structurizr CLI v$CLI_VERSION..."
                    indicator.fraction = 0.0

                    val cliDir = Path.of(System.getProperty("user.home"), ".structurizr", "cli")
                    Files.createDirectories(cliDir)
                    val zipPath = cliDir.resolve("structurizr-cli.zip")
                    val url = URI(CLI_DOWNLOAD_URL).toURL()

                    indicator.text = "Downloading..."
                    url.openStream().use { input ->
                        Files.copy(input, zipPath, StandardCopyOption.REPLACE_EXISTING)
                    }

                    indicator.fraction = 0.5
                    indicator.text = "Extracting..."
                    extractZip(zipPath, cliDir)

                    indicator.fraction = 0.9
                    Files.deleteIfExists(zipPath)

                    val jarPath = cliDir.resolve("structurizr-cli.jar")
                    if (!Files.exists(jarPath)) {
                        throw Exception("CLI JAR not found after extraction")
                    }

                    cliPath = jarPath.toString()
                    indicator.fraction = 1.0
                    showNotification(
                        project,
                        "Structurizr CLI Installed",
                        "Structurizr CLI v$CLI_VERSION has been installed successfully.",
                        NotificationType.INFORMATION,
                    )
                    callback(jarPath.toString())
                } catch (e: Exception) {
                    log.error("Failed to download Structurizr CLI", e)
                    showNotification(
                        project,
                        "Download Failed",
                        "Failed to download Structurizr CLI: ${e.message}",
                        NotificationType.ERROR,
                    )
                    callback(null)
                }
            }
        })
    }

    private fun extractZip(zipPath: Path, destDir: Path) {
        val unzipResult = ProcessBuilder("unzip", "-o", zipPath.toString(), "-d", destDir.toString())
            .start()
            .also { it.waitFor(60L, TimeUnit.SECONDS) }

        if (unzipResult.exitValue() != 0) {
            ZipFile(zipPath.toFile()).use { zip ->
                zip.entries().asSequence().forEach { entry ->
                    val entryDest = destDir.resolve(entry.name)
                    if (entry.isDirectory) {
                        Files.createDirectories(entryDest)
                    } else {
                        Files.createDirectories(entryDest.parent)
                        zip.getInputStream(entry).use { input ->
                            Files.copy(input, entryDest, StandardCopyOption.REPLACE_EXISTING)
                        }
                    }
                }
            }
        }
    }

    /**
     * Exports a DSL workspace to the given [format].
     *
     * Runs asynchronously with a progress indicator; invokes [callback] with
     * `(success, outputOrError)` when done.
     */
    fun exportToFormat(
        project: Project,
        dslPath: String,
        outputDir: String,
        format: ExportFormat,
        callback: (Boolean, String?) -> Unit,
    ) {
        val cli = cliPath ?: run {
            callback(false, "Structurizr CLI not found")
            return
        }

        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Exporting Diagram", true) {
            override fun run(indicator: ProgressIndicator) {
                try {
                    indicator.text = "Exporting to ${format.name}..."
                    val command = buildExportCommand(cli, dslPath, outputDir, format)
                    log.info("Running: ${command.joinToString(" ")}")

                    val process = ProcessBuilder(command).redirectErrorStream(true).start()
                    val output = process.inputStream.bufferedReader().readText()
                    val exitCode = process.waitFor()

                    if (exitCode == 0) {
                        callback(true, output)
                    } else {
                        callback(false, "Export failed with exit code $exitCode: $output")
                    }
                } catch (e: Exception) {
                    log.error("Export failed", e)
                    callback(false, e.message)
                }
            }
        })
    }

    /**
     * Returns the list of CLI command tokens for an export invocation.
     */
    private fun buildExportCommand(cli: String, dslPath: String, outputDir: String, format: ExportFormat): List<String> =
        if (cli.endsWith(".jar")) {
            listOf("java", "-jar", cli, "export", "-workspace", dslPath, "-format", format.cliFormat, "-output", outputDir)
        } else {
            listOf(cli, "export", "-workspace", dslPath, "-format", format.cliFormat, "-output", outputDir)
        }

    private fun showNotification(project: Project, title: String, content: String, type: NotificationType) {
        ApplicationManager.getApplication().invokeLater {
            NotificationGroupManager.getInstance()
                .getNotificationGroup("Structurizr DSL")
                .createNotification(title, content, type)
                .notify(project)
        }
    }

    /** Returns the detected CLI version string, or `null` if unavailable. */
    fun getCliVersion(): String? {
        val cli = cliPath ?: return null
        return try {
            val command = if (cli.endsWith(".jar")) listOf("java", "-jar", cli, "version") else listOf(cli, "version")
            val process = ProcessBuilder(command).redirectErrorStream(true).start()
            val output = process.inputStream.bufferedReader().readText().trim()
            process.waitFor(10L, TimeUnit.SECONDS)
            if (process.exitValue() == 0) {
                cliVersion = output
                output
            } else {
                null
            }
        } catch (e: Exception) {
            log.warn("Failed to get CLI version", e)
            null
        }
    }

    /** Returns `true` when a CLI binary can be found on the system. */
    fun isCliAvailable(): Boolean = findCliPath() != null

    companion object {
        private const val CLI_VERSION = "2024.02.22"
        private const val CLI_DOWNLOAD_URL =
            "https://github.com/structurizr/cli/releases/download/v2024.02.22/structurizr-cli-2024.02.22.zip"

        /** Retrieves the application-level singleton instance. */
        @JvmStatic
        fun getInstance(): StructurizrCliService =
            service()
    }
}
