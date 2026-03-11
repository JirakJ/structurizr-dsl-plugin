/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.cli

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.util.io.HttpRequests
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.ZipInputStream

/**
 * Wrapper around the Structurizr CLI binary.
 *
 * Manages CLI installation, version detection, export, and validation.
 */
@Service(Service.Level.PROJECT)
class StructurizrCliWrapper(private val project: Project) {

    private val log = Logger.getInstance(StructurizrCliWrapper::class.java)

    companion object {
        private const val CLI_VERSION = "2024.03.03"
        private const val CLI_DOWNLOAD_URL =
            "https://github.com/structurizr/cli/releases/download/v$CLI_VERSION/structurizr-cli-$CLI_VERSION.zip"
        private const val CLI_DIR_NAME = ".structurizr-cli"

        /** Returns the service instance for the given [project]. */
        fun getInstance(project: Project): StructurizrCliWrapper =
            project.service()
    }

    /**
     * Supported CLI export formats.
     */
    enum class ExportFormat(val cliName: String, val extension: String, val displayName: String) {
        PLANTUML("plantuml", "puml", "PlantUML"),
        MERMAID("mermaid", "md", "Mermaid"),
        DOT("dot", "dot", "DOT (Graphviz)"),
        JSON("json", "json", "JSON"),
        DSL("dsl", "dsl", "DSL (Structurizr)")
    }

    /**
     * Result of a CLI export operation.
     */
    data class ExportResult(
        val success: Boolean,
        val outputFiles: List<File> = emptyList(),
        val output: String = "",
        val error: String? = null
    )

    /**
     * Result of a CLI validation operation.
     */
    data class ValidationResult(
        val valid: Boolean,
        val errors: List<String> = emptyList(),
        val warnings: List<String> = emptyList()
    )

    /** Returns the CLI installation directory. */
    fun getCliDirectory(): Path = Path.of(System.getProperty("user.home"), CLI_DIR_NAME)

    /** Returns the path to the CLI executable, or null if not found. */
    fun getCliPath(): String? {
        val cliDir = getCliDirectory()
        val unixCli = cliDir.resolve("structurizr-cli").toFile()
        if (unixCli.exists() && unixCli.canExecute()) return unixCli.absolutePath

        val windowsCli = cliDir.resolve("structurizr-cli.bat").toFile()
        if (windowsCli.exists()) return windowsCli.absolutePath

        val jarFile = cliDir.resolve("structurizr-cli.jar").toFile()
        if (jarFile.exists()) return jarFile.absolutePath

        return null
    }

    /** Returns whether the CLI is installed. */
    fun isInstalled(): Boolean = getCliPath() != null

    /** Returns the installed CLI version, or null if unavailable. */
    fun getInstalledVersion(): String? {
        val cliPath = getCliPath() ?: return null
        return try {
            val process = ProcessBuilder(cliPath, "--version")
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText()
            process.waitFor()
            Regex("structurizr-cli\\s+v?(\\d+\\.\\d+\\.\\d+)", RegexOption.IGNORE_CASE)
                .find(output)?.groupValues?.get(1)
        } catch (e: Exception) {
            log.warn("Failed to get CLI version", e)
            null
        }
    }

    /**
     * Downloads and installs the Structurizr CLI in the background.
     *
     * @param onComplete callback invoked with (success, errorMessage?)
     */
    fun downloadAndInstall(onComplete: (Boolean, String?) -> Unit) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Installing Structurizr CLI", true) {
            override fun run(indicator: ProgressIndicator) {
                indicator.isIndeterminate = false
                indicator.text = "Downloading Structurizr CLI..."
                try {
                    val cliDir = getCliDirectory()
                    Files.createDirectories(cliDir)
                    val zipFile = cliDir.resolve("structurizr-cli.zip").toFile()

                    HttpRequests.request(CLI_DOWNLOAD_URL).saveToFile(zipFile, indicator)

                    indicator.text = "Extracting Structurizr CLI..."
                    indicator.fraction = 0.7
                    extractZip(zipFile, cliDir.toFile())
                    zipFile.delete()

                    val cliScript = cliDir.resolve("structurizr-cli").toFile()
                    if (cliScript.exists()) cliScript.setExecutable(true)

                    indicator.fraction = 1.0
                    onComplete(true, null)
                } catch (e: Exception) {
                    log.error("Failed to install Structurizr CLI", e)
                    onComplete(false, e.message)
                }
            }
        })
    }

    private fun extractZip(zipFile: File, targetDir: File) {
        ZipInputStream(FileInputStream(zipFile)).use { zis ->
            var entry = zis.nextEntry
            while (entry != null) {
                val newFile = File(targetDir, entry.name)
                if (entry.isDirectory) {
                    newFile.mkdirs()
                } else {
                    newFile.parentFile?.mkdirs()
                    FileOutputStream(newFile).use { fos -> zis.copyTo(fos) }
                }
                zis.closeEntry()
                entry = zis.nextEntry
            }
        }
    }

    /**
     * Exports a DSL file using the CLI.
     *
     * @param dslFile the source DSL file
     * @param outputDir the output directory
     * @param format the export format
     * @param viewKey optional view key
     * @return the export result
     */
    fun export(dslFile: File, outputDir: File, format: ExportFormat, viewKey: String? = null): ExportResult {
        val cliPath = getCliPath()
            ?: return ExportResult(success = false, error = "Structurizr CLI is not installed")

        val command = mutableListOf<String>().apply {
            if (cliPath.endsWith(".jar")) {
                add("java")
                add("-jar")
            }
            add(cliPath)
            add("export")
            add("-workspace")
            add(dslFile.absolutePath)
            add("-format")
            add(format.cliName)
            add("-output")
            add(outputDir.absolutePath)
            if (!viewKey.isNullOrBlank()) {
                add("-view")
                add(viewKey)
            }
        }

        return try {
            val process = ProcessBuilder(command)
                .directory(outputDir)
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                val outputFiles = outputDir.listFiles()
                    ?.filter { it.extension == format.extension }
                    ?: emptyList()
                ExportResult(success = true, outputFiles = outputFiles, output = output)
            } else {
                ExportResult(success = false, error = "CLI exited with code $exitCode: $output")
            }
        } catch (e: Exception) {
            log.error("Failed to export diagram", e)
            ExportResult(success = false, error = e.message)
        }
    }

    /**
     * Validates a DSL file using the CLI.
     *
     * @param dslFile the DSL file to validate
     * @return the validation result
     */
    fun validate(dslFile: File): ValidationResult {
        val cliPath = getCliPath()
            ?: return ValidationResult(valid = false, errors = listOf("Structurizr CLI is not installed"))

        val command = mutableListOf<String>().apply {
            if (cliPath.endsWith(".jar")) {
                add("java")
                add("-jar")
            }
            add(cliPath)
            add("validate")
            add("-workspace")
            add(dslFile.absolutePath)
        }

        return try {
            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                ValidationResult(valid = true)
            } else {
                val errors = output.lines()
                    .filter { it.contains("Error", ignoreCase = true) || it.contains("Warning", ignoreCase = true) }
                    .ifEmpty { listOf(output) }
                ValidationResult(valid = false, errors = errors)
            }
        } catch (e: Exception) {
            log.error("Failed to validate DSL", e)
            ValidationResult(valid = false, errors = listOf(e.message ?: "Unknown error"))
        }
    }
}
