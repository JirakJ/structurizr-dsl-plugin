/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.intellij.openapi.components.Service
 *  com.intellij.openapi.components.Service$Level
 *  com.intellij.openapi.diagnostic.Logger
 *  com.intellij.openapi.progress.ProgressIndicator
 *  com.intellij.openapi.progress.ProgressManager
 *  com.intellij.openapi.progress.Task
 *  com.intellij.openapi.progress.Task$Backgroundable
 *  com.intellij.openapi.project.Project
 *  com.intellij.util.io.HttpRequests
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.ByteStreamsKt
 *  kotlin.io.CloseableKt
 *  kotlin.io.FilesKt
 *  kotlin.io.TextStreamsKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlin.text.Regex
 *  kotlin.text.RegexOption
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.jakubjirak.structurizr.cli;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.util.io.HttpRequests;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(value={Service.Level.PROJECT})
@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 !2\u00020\u0001:\u0004!\"#$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u0004\u0018\u00010\fJ\"\u0010\u0010\u001a\u00020\u00112\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00110\u0013J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J*\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00070\u0007\u00a2\u0006\u0002\b\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2={"Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper;", "", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "getCliDirectory", "Ljava/nio/file/Path;", "getCliPath", "", "isInstalled", "", "getInstalledVersion", "downloadAndInstall", "", "onComplete", "Lkotlin/Function2;", "extractZip", "zipFile", "Ljava/io/File;", "targetDir", "export", "Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ExportResult;", "dslFile", "outputDir", "format", "Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ExportFormat;", "viewKey", "validate", "Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ValidationResult;", "Companion", "ExportFormat", "ExportResult", "ValidationResult", "structurizr-dsl-plugin"})
@SourceDebugExtension(value={"SMAP\nStructurizrCliWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StructurizrCliWrapper.kt\ncom/jakubjirak/structurizr/cli/StructurizrCliWrapper\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,319:1\n3792#2:320\n4307#2,2:321\n766#3:323\n857#3,2:324\n1#4:326\n*S KotlinDebug\n*F\n+ 1 StructurizrCliWrapper.kt\ncom/jakubjirak/structurizr/cli/StructurizrCliWrapper\n*L\n215#1:320\n215#1:321,2\n273#1:323\n273#1:324,2\n*E\n"})
public final class StructurizrCliWrapper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Project project;
    @NotNull
    private final Logger log;
    @NotNull
    private static final String CLI_VERSION = "2024.03.03";
    @NotNull
    private static final String CLI_DOWNLOAD_URL = "https://github.com/structurizr/cli/releases/download/v2024.03.03/structurizr-cli-2024.03.03.zip";
    @NotNull
    private static final String CLI_DIR_NAME = ".structurizr-cli";

    public StructurizrCliWrapper(@NotNull Project project) {
        Intrinsics.checkNotNullParameter((Object)project, (String)"project");
        this.project = project;
        Logger logger = Logger.getInstance(StructurizrCliWrapper.class);
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getInstance(...)");
        this.log = logger;
    }

    @NotNull
    public final Path getCliDirectory() {
        String userHome = System.getProperty("user.home");
        String[] stringArray = new String[]{CLI_DIR_NAME};
        Path path = Path.of(userHome, stringArray);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"of(...)");
        return path;
    }

    @Nullable
    public final String getCliPath() {
        Path cliDir = this.getCliDirectory();
        File unixCli = cliDir.resolve("structurizr-cli").toFile();
        if (unixCli.exists() && unixCli.canExecute()) {
            return unixCli.getAbsolutePath();
        }
        File windowsCli = cliDir.resolve("structurizr-cli.bat").toFile();
        if (windowsCli.exists()) {
            return windowsCli.getAbsolutePath();
        }
        File jarFile = cliDir.resolve("structurizr-cli.jar").toFile();
        if (jarFile.exists()) {
            return jarFile.getAbsolutePath();
        }
        return null;
    }

    public final boolean isInstalled() {
        return this.getCliPath() != null;
    }

    @Nullable
    public final String getInstalledVersion() {
        String string;
        String string2 = this.getCliPath();
        if (string2 == null) {
            return null;
        }
        String cliPath = string2;
        try {
            String[] stringArray = new String[]{cliPath, "--version"};
            Process process = new ProcessBuilder(stringArray).redirectErrorStream(true).start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            InputStream inputStream2 = inputStream;
            Charset charset = Charsets.UTF_8;
            Reader reader = new InputStreamReader(inputStream2, charset);
            int n = 8192;
            String output = TextStreamsKt.readText((Reader)(reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n)));
            process.waitFor();
            Regex versionRegex = new Regex("structurizr-cli\\s+v?(\\d+\\.\\d+\\.\\d+)", RegexOption.IGNORE_CASE);
            Object object = versionRegex.find((CharSequence)output, 0);
            string = object != null ? (String)((MatchResult)object).getGroupValues().get(1) : null;
        }
        catch (Exception e) {
            this.log.warn("Failed to get CLI version", (Throwable)e);
            string = null;
        }
        return string;
    }

    public final void downloadAndInstall(@NotNull Function2<? super Boolean, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, (String)"onComplete");
        Project project = this.project;
        ProgressManager.getInstance().run((Task)new Task.Backgroundable(project, "Installing Structurizr CLI", true){
            final /* synthetic */ StructurizrCliWrapper this$0 = StructurizrCliWrapper.this;
            final /* synthetic */ Function2<? super Boolean, ? super String, Unit> $onComplete = onComplete;

            public void run(ProgressIndicator indicator) {
                Intrinsics.checkNotNullParameter((Object)indicator, (String)"indicator");
                indicator.setIndeterminate(false);
                indicator.setText("Downloading Structurizr CLI...");
                try {
                    Path cliDir = this.this$0.getCliDirectory();
                    Files.createDirectories(cliDir, new FileAttribute[0]);
                    File zipFile = cliDir.resolve("structurizr-cli.zip").toFile();
                    HttpRequests.request((String)"https://github.com/structurizr/cli/releases/download/v2024.03.03/structurizr-cli-2024.03.03.zip").saveToFile(zipFile, indicator);
                    indicator.setText("Extracting Structurizr CLI...");
                    indicator.setFraction(0.7);
                    Intrinsics.checkNotNull((Object)zipFile);
                    File file = cliDir.toFile();
                    Intrinsics.checkNotNullExpressionValue((Object)file, (String)"toFile(...)");
                    StructurizrCliWrapper.access$extractZip(this.this$0, zipFile, file);
                    zipFile.delete();
                    File cliScript = cliDir.resolve("structurizr-cli").toFile();
                    if (cliScript.exists()) {
                        cliScript.setExecutable(true);
                    }
                    indicator.setFraction(1.0);
                    this.$onComplete.invoke((Boolean)true, null);
                }
                catch (Exception e) {
                    StructurizrCliWrapper.access$getLog$p(this.this$0).error("Failed to install Structurizr CLI", (Throwable)e);
                    this.$onComplete.invoke((Boolean)false, (String)e.getMessage());
                }
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void extractZip(File zipFile, File targetDir) throws Exception {
        Closeable closeable = new ZipInputStream(new FileInputStream(zipFile));
        Throwable throwable = null;
        try {
            ZipInputStream zis = (ZipInputStream)closeable;
            boolean bl = false;
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                File newFile = new File(targetDir, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    File file = newFile.getParentFile();
                    if (file != null) {
                        file.mkdirs();
                    }
                    Closeable closeable2 = new FileOutputStream(newFile);
                    Throwable throwable2 = null;
                    try {
                        FileOutputStream fos = (FileOutputStream)closeable2;
                        boolean bl2 = false;
                        Long l = ByteStreamsKt.copyTo((InputStream)zis, (OutputStream)fos, 8192);
                    }
                    catch (Throwable throwable3) {
                        throwable2 = throwable3;
                        throw throwable3;
                    }
                    finally {
                        CloseableKt.closeFinally((Closeable)closeable2, (Throwable)throwable2);
                    }
                }
                zis.closeEntry();
                entry = zis.getNextEntry();
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


    @NotNull
    public final ExportResult export(@NotNull File dslFile, @NotNull File outputDir, @NotNull ExportFormat format, @Nullable String viewKey) {
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        Intrinsics.checkNotNullParameter((Object)outputDir, (String)"outputDir");
        Intrinsics.checkNotNullParameter((Object)((Object)format), (String)"format");
        String string = this.getCliPath();
        if (string == null) {
            return new ExportResult(false, null, null, "Structurizr CLI is not installed", 6, null);
        }
        String cliPath = string;
        List command = new ArrayList();
        if (StringsKt.endsWith((String)cliPath, (String)".jar", (boolean)false)) {
            command.add("java");
            command.add("-jar");
        }
        command.add(cliPath);
        command.add("export");
        command.add("-workspace");
        String string2 = dslFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAbsolutePath(...)");
        command.add(string2);
        command.add("-format");
        command.add(format.getCliName());
        command.add("-output");
        String string3 = outputDir.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"getAbsolutePath(...)");
        command.add(string3);
        Object object = viewKey;
        if (!(object == null || StringsKt.isBlank((CharSequence)object))) {
            command.add("-view");
            command.add(viewKey);
        }
        try {
            ExportResult exportResult;
            Process process = new ProcessBuilder(command).directory(outputDir).redirectErrorStream(true).start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            InputStream inputStream2 = inputStream;
            Charset charset = Charsets.UTF_8;
            Reader reader = new InputStreamReader(inputStream2, charset);
            int n = 8192;
            String output = TextStreamsKt.readText((Reader)(reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n)));
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                List list;
                File[] fileArray = outputDir.listFiles();
                if (fileArray != null) {
                    File[] $this$filter$iv = fileArray;
                    boolean $i$f$filter = false;
                    File[] fileArray2 = $this$filter$iv;
                    Collection destination$iv$iv = new ArrayList();
                    boolean $i$f$filterTo = false;
                    int n2 = fileArray2.length;
                    for (int i = 0; i < n2; ++i) {
                        File element$iv$iv;
                        File it = element$iv$iv = fileArray2[i];
                        boolean bl = false;
                        Intrinsics.checkNotNull((Object)it);
                        if (!Intrinsics.areEqual((Object)FilesKt.getExtension((File)it), (Object)format.getExtension())) continue;
                        destination$iv$iv.add(element$iv$iv);
                    }
                    list = (List)destination$iv$iv;
                } else {
                    list = CollectionsKt.emptyList();
                }
                List outputFiles = list;
                exportResult = new ExportResult(true, outputFiles, output, null, 8, null);
            } else {
                exportResult = new ExportResult(false, null, null, "CLI exited with code " + exitCode + ": " + output, 6, null);
            }
            object = exportResult;
        }
        catch (Exception e) {
            this.log.error("Failed to export diagram", (Throwable)e);
            object = new ExportResult(false, null, null, e.getMessage(), 6, null);
        }
        return (ExportResult)object;
    }

    public static /* synthetic */ ExportResult export$default(StructurizrCliWrapper structurizrCliWrapper, File file, File file2, ExportFormat exportFormat, String string, int n, Object object) {
        if ((n & 8) != 0) {
            string = null;
        }
        return structurizrCliWrapper.export(file, file2, exportFormat, string);
    }


    @NotNull
    public final ValidationResult validate(@NotNull File dslFile) {
        ValidationResult validationResult;
        Intrinsics.checkNotNullParameter((Object)dslFile, (String)"dslFile");
        String string = this.getCliPath();
        if (string == null) {
            return new ValidationResult(false, CollectionsKt.listOf((Object)"Structurizr CLI is not installed"), null, 4, null);
        }
        String cliPath = string;
        List command = new ArrayList();
        if (StringsKt.endsWith((String)cliPath, (String)".jar", (boolean)false)) {
            command.add("java");
            command.add("-jar");
        }
        command.add(cliPath);
        command.add("validate");
        command.add("-workspace");
        String string2 = dslFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAbsolutePath(...)");
        command.add(string2);
        try {
            ValidationResult validationResult2;
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            InputStream inputStream2 = inputStream;
            Charset charset = Charsets.UTF_8;
            Object object = new InputStreamReader(inputStream2, charset);
            int n = 8192;
            String output = TextStreamsKt.readText((Reader)(object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, n)));
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                validationResult2 = new ValidationResult(true, null, null, 6, null);
            } else {
                Object object2;
                List lines = StringsKt.lines((CharSequence)output);
                boolean $i$f$filter = false;
                Iterable $this$filter$iv = lines;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filter$iv) {
                    String it = (String)element$iv$iv;
                    boolean bl = false;
                    if (!(StringsKt.contains((CharSequence)it, (CharSequence)"Error", (boolean)true) || StringsKt.contains((CharSequence)it, (CharSequence)"Warning", (boolean)true))) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                List errors = (List)destination$iv$iv;
                boolean bl = false;
                if (errors.isEmpty()) {
                    boolean bl2 = bl;
                    boolean bl3 = false;
                    List list = CollectionsKt.listOf((Object)output);
                    bl = bl2;
                    object2 = list;
                } else {
                    object2 = errors;
                }
                DefaultConstructorMarker defaultConstructorMarker = null;
                int n2 = 4;
                List list = null;
                List list2 = (List)object2;
                boolean bl4 = bl;
                validationResult2 = new ValidationResult(bl4, list2, list, n2, defaultConstructorMarker);
            }
            validationResult = validationResult2;
        }
        catch (Exception e) {
            this.log.error("Failed to validate DSL", (Throwable)e);
            String string3 = e.getMessage();
            if (string3 == null) {
                string3 = "Unknown error";
            }
            validationResult = new ValidationResult(false, CollectionsKt.listOf((Object)string3), null, 4, null);
        }
        return validationResult;
    }

    public static final /* synthetic */ void access$extractZip(StructurizrCliWrapper $this, File zipFile, File targetDir) throws Exception {
        $this.extractZip(zipFile, targetDir);
    }

    public static final /* synthetic */ Logger access$getLog$p(StructurizrCliWrapper $this) {
        return $this.log;
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2={"Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$Companion;", "", "<init>", "()V", "CLI_VERSION", "", "CLI_DOWNLOAD_URL", "CLI_DIR_NAME", "getInstance", "Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper;", "project", "Lcom/intellij/openapi/project/Project;", "structurizr-dsl-plugin"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final StructurizrCliWrapper getInstance(@NotNull Project project) {
            Intrinsics.checkNotNullParameter((Object)project, (String)"project");
            Object object = project.getService(StructurizrCliWrapper.class);
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getService(...)");
            return (StructurizrCliWrapper)object;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0011"}, d2={"Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ExportFormat;", "", "cliName", "", "extension", "displayName", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCliName", "()Ljava/lang/String;", "getExtension", "getDisplayName", "PLANTUML", "MERMAID", "DOT", "JSON", "DSL", "structurizr-dsl-plugin"})
    public enum ExportFormat {
        PLANTUML("plantuml", "puml", "PlantUML"),
        MERMAID("mermaid", "md", "Mermaid"),
        DOT("dot", "dot", "DOT (Graphviz)"),
        JSON("json", "json", "JSON"),
        DSL("dsl", "dsl", "DSL (Structurizr)");

        @NotNull
        private final String cliName;
        @NotNull
        private final String extension;
        @NotNull
        private final String displayName;

        ExportFormat(String cliName, String extension, String displayName) {
            this.cliName = cliName;
            this.extension = extension;
            this.displayName = displayName;
        }

        @NotNull
        public final String getCliName() {
            return this.cliName;
        }

        @NotNull
        public final String getExtension() {
            return this.extension;
        }

        @NotNull
        public final String getDisplayName() {
            return this.displayName;
        }

        @NotNull
        public static EnumEntries<ExportFormat> getEntries() {
            return EnumEntriesKt.enumEntries((Enum[])values());
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003J9\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u00a8\u0006\u001d"}, d2={"Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ExportResult;", "", "success", "", "outputFiles", "", "Ljava/io/File;", "output", "", "error", "<init>", "(ZLjava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getSuccess", "()Z", "getOutputFiles", "()Ljava/util/List;", "getOutput", "()Ljava/lang/String;", "getError", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class ExportResult {
        private final boolean success;
        @NotNull
        private final List<File> outputFiles;
        @NotNull
        private final String output;
        @Nullable
        private final String error;

        public ExportResult(boolean success, @NotNull List<? extends File> outputFiles, @NotNull String output, @Nullable String error) {
            Intrinsics.checkNotNullParameter(outputFiles, (String)"outputFiles");
            Intrinsics.checkNotNullParameter((Object)output, (String)"output");
            this.success = success;
            this.outputFiles = (List<File>)outputFiles;
            this.output = output;
            this.error = error;
        }

        public /* synthetic */ ExportResult(boolean bl, List list, String string, String string2, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(bl,
                (n & 2) != 0 ? CollectionsKt.emptyList() : list,
                (n & 4) != 0 ? "" : string,
                (n & 8) != 0 ? null : string2);
        }

        public final boolean getSuccess() {
            return this.success;
        }

        @NotNull
        public final List<File> getOutputFiles() {
            return this.outputFiles;
        }

        @NotNull
        public final String getOutput() {
            return this.output;
        }

        @Nullable
        public final String getError() {
            return this.error;
        }

        public final boolean component1() {
            return this.success;
        }

        @NotNull
        public final List<File> component2() {
            return this.outputFiles;
        }

        @NotNull
        public final String component3() {
            return this.output;
        }

        @Nullable
        public final String component4() {
            return this.error;
        }

        @NotNull
        public final ExportResult copy(boolean success, @NotNull List<? extends File> outputFiles, @NotNull String output, @Nullable String error) {
            Intrinsics.checkNotNullParameter(outputFiles, (String)"outputFiles");
            Intrinsics.checkNotNullParameter((Object)output, (String)"output");
            return new ExportResult(success, outputFiles, output, error);
        }

        public static /* synthetic */ ExportResult copy$default(ExportResult exportResult, boolean bl, List list, String string, String string2, int n, Object object) {
            if ((n & 1) != 0) {
                bl = exportResult.success;
            }
            if ((n & 2) != 0) {
                list = exportResult.outputFiles;
            }
            if ((n & 4) != 0) {
                string = exportResult.output;
            }
            if ((n & 8) != 0) {
                string2 = exportResult.error;
            }
            return exportResult.copy(bl, list, string, string2);
        }

        @NotNull
        public String toString() {
            return "ExportResult(success=" + this.success + ", outputFiles=" + this.outputFiles + ", output=" + this.output + ", error=" + this.error + ")";
        }

        public int hashCode() {
            int result = Boolean.hashCode(this.success);
            result = result * 31 + ((Object)this.outputFiles).hashCode();
            result = result * 31 + this.output.hashCode();
            result = result * 31 + (this.error == null ? 0 : this.error.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExportResult)) {
                return false;
            }
            ExportResult exportResult = (ExportResult)other;
            if (this.success != exportResult.success) {
                return false;
            }
            if (!Intrinsics.areEqual(this.outputFiles, exportResult.outputFiles)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.output, (Object)exportResult.output)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.error, (Object)exportResult.error);
        }
    }

    @Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u00a8\u0006\u0018"}, d2={"Lcom/jakubjirak/structurizr/cli/StructurizrCliWrapper$ValidationResult;", "", "valid", "", "errors", "", "", "warnings", "<init>", "(ZLjava/util/List;Ljava/util/List;)V", "getValid", "()Z", "getErrors", "()Ljava/util/List;", "getWarnings", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "structurizr-dsl-plugin"})
    public static final class ValidationResult {
        private final boolean valid;
        @NotNull
        private final List<String> errors;
        @NotNull
        private final List<String> warnings;

        public ValidationResult(boolean valid, @NotNull List<String> errors, @NotNull List<String> warnings) {
            Intrinsics.checkNotNullParameter(errors, (String)"errors");
            Intrinsics.checkNotNullParameter(warnings, (String)"warnings");
            this.valid = valid;
            this.errors = errors;
            this.warnings = warnings;
        }

        public /* synthetic */ ValidationResult(boolean bl, List list, List list2, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(bl,
                (n & 2) != 0 ? CollectionsKt.emptyList() : list,
                (n & 4) != 0 ? CollectionsKt.emptyList() : list2);
        }

        public final boolean getValid() {
            return this.valid;
        }

        @NotNull
        public final List<String> getErrors() {
            return this.errors;
        }

        @NotNull
        public final List<String> getWarnings() {
            return this.warnings;
        }

        public final boolean component1() {
            return this.valid;
        }

        @NotNull
        public final List<String> component2() {
            return this.errors;
        }

        @NotNull
        public final List<String> component3() {
            return this.warnings;
        }

        @NotNull
        public final ValidationResult copy(boolean valid, @NotNull List<String> errors, @NotNull List<String> warnings) {
            Intrinsics.checkNotNullParameter(errors, (String)"errors");
            Intrinsics.checkNotNullParameter(warnings, (String)"warnings");
            return new ValidationResult(valid, errors, warnings);
        }

        public static /* synthetic */ ValidationResult copy$default(ValidationResult validationResult, boolean bl, List list, List list2, int n, Object object) {
            if ((n & 1) != 0) {
                bl = validationResult.valid;
            }
            if ((n & 2) != 0) {
                list = validationResult.errors;
            }
            if ((n & 4) != 0) {
                list2 = validationResult.warnings;
            }
            return validationResult.copy(bl, list, list2);
        }

        @NotNull
        public String toString() {
            return "ValidationResult(valid=" + this.valid + ", errors=" + this.errors + ", warnings=" + this.warnings + ")";
        }

        public int hashCode() {
            int result = Boolean.hashCode(this.valid);
            result = result * 31 + ((Object)this.errors).hashCode();
            result = result * 31 + ((Object)this.warnings).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ValidationResult)) {
                return false;
            }
            ValidationResult validationResult = (ValidationResult)other;
            if (this.valid != validationResult.valid) {
                return false;
            }
            if (!Intrinsics.areEqual(this.errors, validationResult.errors)) {
                return false;
            }
            return Intrinsics.areEqual(this.warnings, validationResult.warnings);
        }
    }
}
