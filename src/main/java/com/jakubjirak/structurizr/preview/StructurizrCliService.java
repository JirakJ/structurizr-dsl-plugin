/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.notification.NotificationGroupManager
 *  com.intellij.notification.NotificationType
 *  com.intellij.openapi.application.ApplicationManager
 *  com.intellij.openapi.components.Service
 *  com.intellij.openapi.diagnostic.Logger
 *  com.intellij.openapi.progress.ProgressIndicator
 *  com.intellij.openapi.progress.ProgressManager
 *  com.intellij.openapi.progress.Task
 *  com.intellij.openapi.progress.Task$Backgroundable
 *  com.intellij.openapi.project.Project
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.io.TextStreamsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.sequences.Sequence
 *  kotlin.sequences.SequencesKt
 *  kotlin.text.Charsets
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.preview;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.jakubjirak.structurizr.preview.ExportFormat;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u000b0\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\bJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\bH\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002J&\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002JB\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u000b0\u001eJ.\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0!2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J(\u0010#\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u0004\u0018\u00010\bJ\u0006\u0010)\u001a\u00020\u001fR\u0013\u0010\u0004\u001a\u00070\u0005\u00a2\u0006\u0002\b\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrCliService;", "", "<init>", "()V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "cliPath", "", "cliVersion", "ensureCliAvailable", "", "project", "Lcom/intellij/openapi/project/Project;", "callback", "Lkotlin/Function1;", "findCliPath", "findInPath", "executable", "getPluginCliPath", "downloadCli", "extractZip", "zipPath", "Ljava/nio/file/Path;", "destDir", "exportToFormat", "dslPath", "outputDir", "format", "Lcom/jakubjirak/structurizr/preview/ExportFormat;", "Lkotlin/Function2;", "", "buildExportCommand", "", "cli", "showNotification", "title", "content", "type", "Lcom/intellij/notification/NotificationType;", "getCliVersion", "isCliAvailable", "Companion", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrCliService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrCliService.kt\ncom/jakubjirak/structurizr/preview/StructurizrCliService\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,361:1\n1295#2,2:362\n*S KotlinDebug\n*F\n+ 1 StructurizrCliService.kt\ncom/jakubjirak/structurizr/preview/StructurizrCliService\n*L\n205#1:362,2\n*E\n"})
public final class StructurizrCliService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Logger log;
    @Nullable
    private String cliPath;
    @Nullable
    private String cliVersion;
    @NotNull
    private static final String CLI_VERSION = "2024.02.22";
    @NotNull
    private static final String CLI_DOWNLOAD_URL = "https://github.com/structurizr/cli/releases/download/v2024.02.22/structurizr-cli-2024.02.22.zip";

    public StructurizrCliService() {
        Logger logger = Logger.getInstance(StructurizrCliService.class);
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getInstance(...)");
        this.log = logger;
    }

    public final void ensureCliAvailable(@NotNull Project project, @NotNull Function1<? super String, Unit> callback) {
        String foundPath;
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        String string = this.cliPath;
        if (string != null) {
            String it = string;
            boolean bl = false;
            if (new File(it).exists()) {
                callback.invoke(it);
                return;
            }
        }
        if ((foundPath = this.findCliPath()) != null) {
            this.cliPath = foundPath;
            callback.invoke(foundPath);
            return;
        }
        this.downloadCli(project, callback);
    }

    @Nullable
    public final String findCliPath() {
        String pathCli;
        String[] stringArray = new String[7];
        stringArray[0] = "/usr/local/bin/structurizr-cli";
        stringArray[1] = "/opt/homebrew/bin/structurizr-cli";
        stringArray[2] = System.getProperty("user.home") + "/.structurizr/cli/structurizr.sh";
        stringArray[3] = System.getProperty("user.home") + "/.structurizr/structurizr-cli.jar";
        String string = System.getenv("LOCALAPPDATA");
        if (string == null) {
            string = "";
        }
        stringArray[4] = string + "\\structurizr\\cli\\structurizr.bat";
        stringArray[5] = "/usr/bin/structurizr-cli";
        stringArray[6] = "/usr/local/bin/structurizr";
        List possiblePaths = CollectionsKt.listOf((Object[])stringArray);
        for (Object element$iv : (Iterable)possiblePaths) {
            String path = (String)element$iv;
            File file = new File(path);
            if (!file.exists() || !file.canExecute()) continue;
            this.log.info("Found Structurizr CLI at: " + path);
            return path;
        }
        String string2 = this.findInPath("structurizr-cli");
        if (string2 == null) {
            string2 = this.findInPath("structurizr");
        }
        if ((pathCli = string2) != null) {
            return pathCli;
        }
        String pluginCli = this.getPluginCliPath();
        if (pluginCli != null && new File(pluginCli).exists()) {
            return pluginCli;
        }
        return null;
    }

    private final String findInPath(String executable) {
        String string = System.getenv("PATH");
        if (string == null) {
            return null;
        }
        String pathEnv = string;
        String separator = File.pathSeparator;
        String[] stringArray = new String[]{separator};
        for (String dir : StringsKt.split((CharSequence)pathEnv, (String[])stringArray, false, 0)) {
            File file = new File(dir, executable);
            if (file.exists() && file.canExecute()) {
                return file.getAbsolutePath();
            }
            File batFile = new File(dir, executable + ".bat");
            if (batFile.exists()) {
                return batFile.getAbsolutePath();
            }
            File cmdFile = new File(dir, executable + ".cmd");
            if (!cmdFile.exists()) continue;
            return cmdFile.getAbsolutePath();
        }
        return null;
    }

    private final String getPluginCliPath() {
        String userHome = System.getProperty("user.home");
        return userHome + "/.structurizr/cli/structurizr-cli.jar";
    }

    private final void downloadCli(Project project, Function1<? super String, Unit> callback) {
        StructurizrCliService outerThis = this;
        ProgressManager.getInstance().run((Task)new Task.Backgroundable(project, "Downloading Structurizr CLI", true){
            final /* synthetic */ Project $project = project;
            final /* synthetic */ StructurizrCliService this$0 = outerThis;
            final /* synthetic */ Function1<String, Unit> $callback = (Function1<String, Unit>)callback;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void run(ProgressIndicator indicator) {
                Intrinsics.checkNotNullParameter((Object)indicator, (String)"indicator");
                try {
                    indicator.setText("Downloading Structurizr CLI v2024.02.22...");
                    indicator.setFraction(0.0);
                    String[] stringArray = new String[]{".structurizr", "cli"};
                    Path cliDir = Path.of(System.getProperty("user.home"), stringArray);
                    Files.createDirectories(cliDir, new FileAttribute[0]);
                    Path zipPath = cliDir.resolve("structurizr-cli.zip");
                    URL url = new URI("https://github.com/structurizr/cli/releases/download/v2024.02.22/structurizr-cli-2024.02.22.zip").toURL();
                    indicator.setText("Downloading...");
                    Closeable closeable = url.openStream();
                    Throwable throwable = null;
                    try {
                        InputStream input = (InputStream)closeable;
                        boolean bl = false;
                        CopyOption[] copyOptionArray = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
                        long l = Files.copy(input, zipPath, copyOptionArray);
                    }
                    catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    }
                    finally {
                        CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                    }
                    indicator.setFraction(0.5);
                    indicator.setText("Extracting...");
                    Intrinsics.checkNotNull((Object)zipPath);
                    Intrinsics.checkNotNull((Object)cliDir);
                    StructurizrCliService.access$extractZip(this.this$0, zipPath, cliDir);
                    indicator.setFraction(0.9);
                    Files.deleteIfExists(zipPath);
                    Path jarPath = cliDir.resolve("structurizr-cli.jar");
                    if (!Files.exists(jarPath, new LinkOption[0])) {
                        throw new Exception("CLI JAR not found after extraction");
                    }
                    StructurizrCliService.access$setCliPath$p(this.this$0, ((Object)jarPath).toString());
                    indicator.setFraction(1.0);
                    StructurizrCliService.access$showNotification(this.this$0, this.$project, "Structurizr CLI Installed", "Structurizr CLI v2024.02.22 has been installed successfully.", NotificationType.INFORMATION);
                    this.$callback.invoke(((Object)jarPath).toString());
                }
                catch (Exception e) {
                    StructurizrCliService.access$getLog$p(this.this$0).error("Failed to download Structurizr CLI", (Throwable)e);
                    StructurizrCliService.access$showNotification(this.this$0, this.$project, "Download Failed", "Failed to download Structurizr CLI: " + e.getMessage(), NotificationType.ERROR);
                    this.$callback.invoke(null);
                }
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    private final void extractZip(Path zipPath, Path destDir) throws Exception {
        String[] stringArray = new String[]{"unzip", "-o", ((Object)zipPath).toString(), "-d", ((Object)destDir).toString()};
        ProcessBuilder processBuilder = new ProcessBuilder(stringArray);
        Process process = processBuilder.start();
        process.waitFor(60L, TimeUnit.SECONDS);
        if (process.exitValue() != 0) {
            Closeable closeable = new ZipFile(zipPath.toFile());
            Throwable throwable = null;
            try {
                Sequence $this$forEach$iv;
                ZipFile zip = (ZipFile)closeable;
                boolean bl = false;
                Enumeration<? extends ZipEntry> enumeration = zip.entries();
                Intrinsics.checkNotNullExpressionValue(enumeration, (String)"entries(...)");
                Sequence sequence = SequencesKt.asSequence((Iterator)CollectionsKt.iterator(enumeration));
                $this$forEach$iv = sequence;
                boolean $i$f$forEach = false;
                for (Object element$iv : SequencesKt.toList($this$forEach$iv)) {
                    ZipEntry entry = (ZipEntry)element$iv;
                    boolean bl2 = false;
                    Path entryDest = destDir.resolve(entry.getName());
                    if (entry.isDirectory()) {
                        Files.createDirectories(entryDest, new FileAttribute[0]);
                        continue;
                    }
                    Files.createDirectories(entryDest.getParent(), new FileAttribute[0]);
                    Closeable closeable2 = zip.getInputStream(entry);
                    Throwable throwable2 = null;
                    try {
                        InputStream input = (InputStream)closeable2;
                        boolean bl3 = false;
                        CopyOption[] copyOptionArray = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
                        Files.copy(input, entryDest, copyOptionArray);
                        Unit unit = Unit.INSTANCE;
                    }
                    catch (Throwable throwable3) {
                        throwable2 = throwable3;
                        throw throwable3;
                    }
                    finally {
                        CloseableKt.closeFinally((Closeable)closeable2, (Throwable)throwable2);
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
    }

    public final void exportToFormat(@NotNull Project project, @NotNull String dslPath, @NotNull String outputDir, @NotNull ExportFormat format, @NotNull Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        Intrinsics.checkNotNullParameter((Object)dslPath, (String)"dslPath");
        Intrinsics.checkNotNullParameter((Object)outputDir, (String)"outputDir");
        Intrinsics.checkNotNullParameter((Object)((Object)format), (String)"format");
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        String cli = this.cliPath;
        if (cli == null) {
            callback.invoke(false, "Structurizr CLI not found");
            return;
        }
        StructurizrCliService outerThis = this;
        ProgressManager.getInstance().run((Task)new Task.Backgroundable(project, "Exporting Diagram", true){
            final /* synthetic */ ExportFormat $format = format;
            final /* synthetic */ StructurizrCliService this$0 = outerThis;
            final /* synthetic */ String $cli = cli;
            final /* synthetic */ String $dslPath = dslPath;
            final /* synthetic */ String $outputDir = outputDir;
            final /* synthetic */ Function2<Boolean, String, Unit> $callback = (Function2<Boolean, String, Unit>)callback;

            public void run(ProgressIndicator indicator) {
                Intrinsics.checkNotNullParameter((Object)indicator, (String)"indicator");
                try {
                    indicator.setText("Exporting to " + this.$format.name() + "...");
                    List command = StructurizrCliService.access$buildExportCommand(this.this$0, this.$cli, this.$dslPath, this.$outputDir, this.$format);
                    StructurizrCliService.access$getLog$p(this.this$0).info("Running: " + CollectionsKt.joinToString((Iterable)command, (CharSequence)" ", null, null, (int)0, null, null));
                    ProcessBuilder processBuilder = new ProcessBuilder(command).redirectErrorStream(true);
                    Process process = processBuilder.start();
                    InputStream inputStream = process.getInputStream();
                    Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
                    InputStream inputStream2 = inputStream;
                    Charset charset = Charsets.UTF_8;
                    Reader reader = new InputStreamReader(inputStream2, charset);
                    int n = 8192;
                    String output = TextStreamsKt.readText((Reader)(reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n)));
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        this.$callback.invoke(true, output);
                    } else {
                        this.$callback.invoke(false, "Export failed with exit code " + exitCode + ": " + output);
                    }
                }
                catch (Exception e) {
                    StructurizrCliService.access$getLog$p(this.this$0).error("Export failed", (Throwable)e);
                    this.$callback.invoke(false, e.getMessage());
                }
            }
        });
    }

    private final List<String> buildExportCommand(String cli, String dslPath, String outputDir, ExportFormat format) {
        List list;
        if (StringsKt.endsWith((String)cli, (String)".jar", (boolean)false)) {
            Object[] objectArray = new String[]{"java", "-jar", cli, "export", "-workspace", dslPath, "-format", format.getCliFormat(), "-output", outputDir};
            list = CollectionsKt.listOf((Object[])objectArray);
        } else {
            Object[] objectArray = new String[]{cli, "export", "-workspace", dslPath, "-format", format.getCliFormat(), "-output", outputDir};
            list = CollectionsKt.listOf((Object[])objectArray);
        }
        return list;
    }

    private final void showNotification(Project project, String title, String content, NotificationType type) {
        ApplicationManager.getApplication().invokeLater(() -> StructurizrCliService.showNotification$lambda$4(title, content, type, project));
    }

    @Nullable
    public final String getCliVersion() {
        String string;
        String string2 = this.cliPath;
        if (string2 == null) {
            return null;
        }
        String cli = string2;
        try {
            String string3;
            List list;
            if (StringsKt.endsWith((String)cli, (String)".jar", (boolean)false)) {
                String[] commandArray = new String[]{"java", "-jar", cli, "version"};
                list = CollectionsKt.listOf((Object[])commandArray);
            } else {
                String[] commandArray = new String[]{cli, "version"};
                list = CollectionsKt.listOf((Object[])commandArray);
            }
            List command = list;
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            InputStream inputStream2 = inputStream;
            Charset charset = Charsets.UTF_8;
            Reader reader = new InputStreamReader(inputStream2, charset);
            int n = 8192;
            String output = ((Object)StringsKt.trim((CharSequence)TextStreamsKt.readText((Reader)(reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n))))).toString();
            process.waitFor(10L, TimeUnit.SECONDS);
            if (process.exitValue() == 0) {
                this.cliVersion = output;
                string3 = output;
            } else {
                string3 = null;
            }
            string = string3;
        }
        catch (Exception e) {
            this.log.warn("Failed to get CLI version", (Throwable)e);
            string = null;
        }
        return string;
    }

    public final boolean isCliAvailable() {
        return this.findCliPath() != null;
    }

    private static final void showNotification$lambda$4(String $title, String $content, NotificationType $type, Project $project) {
        NotificationGroupManager.getInstance().getNotificationGroup("Structurizr DSL").createNotification($title, $content, $type).notify($project);
    }

    public static final /* synthetic */ void access$extractZip(StructurizrCliService $this, Path zipPath, Path destDir) throws Exception {
        $this.extractZip(zipPath, destDir);
    }

    public static final /* synthetic */ void access$setCliPath$p(StructurizrCliService $this, String string) {
        $this.cliPath = string;
    }

    public static final /* synthetic */ void access$showNotification(StructurizrCliService $this, Project project, String title, String content, NotificationType type) {
        $this.showNotification(project, title, content, type);
    }

    public static final /* synthetic */ Logger access$getLog$p(StructurizrCliService $this) {
        return $this.log;
    }

    public static final /* synthetic */ List access$buildExportCommand(StructurizrCliService $this, String cli, String dslPath, String outputDir, ExportFormat format) {
        return $this.buildExportCommand(cli, dslPath, outputDir, format);
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2={"Lcom/jakubjirak/structurizr/preview/StructurizrCliService$Companion;", "", "<init>", "()V", "CLI_VERSION", "", "CLI_DOWNLOAD_URL", "getInstance", "Lcom/jakubjirak/structurizr/preview/StructurizrCliService;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final StructurizrCliService getInstance() {
            Object object = ApplicationManager.getApplication().getService(StructurizrCliService.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (StructurizrCliService)object;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
