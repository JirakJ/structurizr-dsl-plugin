/* Copyright 2026 Jakub Jirák. SPDX-License-Identifier: Apache-2.0 */
package com.jakubjirak.structurizr.preview

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.jakubjirak.structurizr.settings.StructurizrProjectSettings
import com.jakubjirak.structurizr.settings.StructurizrSettings
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Application-level service that locates and runs an installed Structurizr CLI.
 *
 * The CLI is looked up in this order: the path configured in Settings | Tools | Structurizr DSL,
 * well-known install locations, then the system `PATH`. The plugin does not download the CLI itself.
 */
@Service
class StructurizrCliService {

    private val log = Logger.getInstance(StructurizrCliService::class.java)

    /** Returns the CLI to use (project override, then global setting, then auto-detection), or `null`. */
    fun findCliPath(project: Project? = null): String? {
        val configured = (project?.let { StructurizrProjectSettings.getInstance(it).getEffectiveCliPath() }
            ?: StructurizrSettings.getInstance().structurizrCliPath).trim()
        if (configured.isNotEmpty()) {
            if (File(configured).isFile) return configured
            log.warn("Configured Structurizr CLI path does not exist: $configured")
        }
        val home = System.getProperty("user.home")
        val wellKnown = listOf(
            "/usr/local/bin/structurizr-cli",
            "/opt/homebrew/bin/structurizr-cli",
            "$home/.structurizr/cli/structurizr.sh",
            "$home/.structurizr/cli/structurizr-cli.jar",
            "$home/.structurizr/structurizr-cli.jar",
            "${System.getenv("LOCALAPPDATA") ?: ""}\\structurizr\\cli\\structurizr.bat",
            "/usr/bin/structurizr-cli",
            "/usr/local/bin/structurizr",
        )
        return wellKnown.firstOrNull { isRunnable(File(it)) }
            ?: findInPath("structurizr-cli")
            ?: findInPath("structurizr")
    }

    /** Returns `true` when a CLI can be found. */
    fun isCliAvailable(project: Project? = null): Boolean = findCliPath(project) != null

    /**
     * Runs `export -format <format>` for [dslPath] into [outputDir] on the calling (background) thread.
     * Gives up after the timeout configured in settings, and stops early when [indicator] is cancelled.
     *
     * @return `(true, output)` on success, `(false, error)` otherwise
     */
    fun exportBlocking(
        project: Project?,
        dslPath: String,
        outputDir: String,
        format: ExportFormat,
        indicator: ProgressIndicator?,
    ): Pair<Boolean, String?> {
        val cli = findCliPath(project) ?: return false to "Structurizr CLI not found"
        val command = buildExportCommand(cli, dslPath, outputDir, format)
        log.info("Running: ${command.joinToString(" ")}")
        return try {
            runProcess(command, indicator)
        } catch (e: IOException) {
            log.warn("Structurizr CLI export failed", e)
            false to (e.message ?: "Structurizr CLI could not be started")
        }
    }

    private fun runProcess(command: List<String>, indicator: ProgressIndicator?): Pair<Boolean, String?> {
        val output = File.createTempFile("structurizr-cli", ".log")
        try {
            val process = ProcessBuilder(command).redirectErrorStream(true).redirectOutput(output).start()
            awaitOrKill(process, indicator)?.let { return false to it }
            val text = output.readText()
            return if (process.exitValue() == 0) true to text else false to "Structurizr CLI failed (exit ${process.exitValue()}): $text"
        } finally {
            output.delete()
        }
    }

    /** Waits for [process]; kills it and returns the reason when cancelled or past the configured timeout. */
    private fun awaitOrKill(process: Process, indicator: ProgressIndicator?): String? {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(timeoutSeconds().toLong())
        while (!process.waitFor(POLL_MS, TimeUnit.MILLISECONDS)) {
            val reason = when {
                indicator?.isCanceled == true -> "Export cancelled"
                System.nanoTime() > deadline -> "Structurizr CLI timed out"
                else -> null
            }
            if (reason != null) {
                process.destroyForcibly()
                return reason
            }
        }
        return null
    }

    private fun timeoutSeconds(): Int = StructurizrSettings.getInstance().cliTimeoutSeconds.coerceAtLeast(MIN_TIMEOUT_SECONDS)

    private fun isRunnable(file: File): Boolean =
        file.isFile && (file.canExecute() || file.name.endsWith(".jar") || file.name.endsWith(".bat"))

    private fun findInPath(executable: String): String? {
        val pathEnv = System.getenv("PATH") ?: return null
        return pathEnv.split(File.pathSeparator).asSequence()
            .flatMap { dir -> sequenceOf(executable, "$executable.bat", "$executable.cmd").map { File(dir, it) } }
            .firstOrNull { isRunnable(it) }
            ?.absolutePath
    }

    companion object {
        private const val POLL_MS = 200L
        private const val MIN_TIMEOUT_SECONDS = 5

        /** Command tokens for an export invocation (a `.jar` CLI is started with `java -jar`). */
        fun buildExportCommand(cli: String, dslPath: String, outputDir: String, format: ExportFormat): List<String> {
            val launcher = if (cli.endsWith(".jar")) listOf("java", "-jar", cli) else listOf(cli)
            return launcher + listOf("export", "-workspace", dslPath, "-format", format.cliFormat, "-output", outputDir)
        }

        /** Retrieves the application-level singleton instance. */
        @JvmStatic
        fun getInstance(): StructurizrCliService = service()
    }
}
