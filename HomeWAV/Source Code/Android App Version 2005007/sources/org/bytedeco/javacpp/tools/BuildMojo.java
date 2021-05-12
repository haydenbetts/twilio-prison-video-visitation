package org.bytedeco.javacpp.tools;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;

public class BuildMojo extends AbstractMojo {
    String[] buildCommand = null;
    String buildPath = null;
    String[] buildPaths = null;
    String buildResource = null;
    String[] buildResources = null;
    String classOrPackageName = null;
    String[] classOrPackageNames = null;
    String classPath = null;
    String[] classPaths = null;
    boolean clean = false;
    boolean compile = true;
    String[] compilerOptions = null;
    String configDirectory = null;
    boolean copyLibs = false;
    boolean copyResources = false;
    boolean deleteJniFiles = true;
    String encoding = null;
    Map<String, String> environmentVariables = null;
    String executablePath = null;
    String[] executablePaths = null;
    boolean generate = true;
    boolean header = false;
    String includePath = null;
    String[] includePaths = null;
    String includeResource = null;
    String[] includeResources = null;
    String jarPrefix = null;
    String linkPath = null;
    String[] linkPaths = null;
    String linkResource = null;
    String[] linkResources = null;
    File outputDirectory = null;
    String outputName = null;
    PluginDescriptor plugin;
    String preloadPath = null;
    String[] preloadPaths = null;
    String preloadResource = null;
    String[] preloadResources = null;
    MavenProject project;
    String properties = null;
    File propertyFile = null;
    Properties propertyKeysAndValues = null;
    String resourcePath = null;
    String[] resourcePaths = null;
    boolean skip = false;
    String[] targetDirectories = null;
    String targetDirectory = null;
    File workingDirectory = null;

    /* access modifiers changed from: package-private */
    public String[] merge(String[] strArr, String str) {
        if (strArr != null && str != null) {
            strArr = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
            strArr[strArr.length - 1] = str;
        } else if (str != null) {
            strArr = new String[]{str};
        }
        return strArr != null ? strArr : new String[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x07ac A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x07f4 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x083c A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0897 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x08ed A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }, LOOP:12: B:154:0x08e7->B:156:0x08ed, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0916 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x059a A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x059b A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x05c2 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x060c A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0650 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0694 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x06d8 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x071c A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0764 A[Catch:{ IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException -> 0x092f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() throws org.apache.maven.plugin.MojoExecutionException {
        /*
            r24 = this;
            r1 = r24
            java.lang.String r2 = "platform.executablepath"
            java.lang.String r3 = "platform.resourcepath"
            java.lang.String r4 = "platform.preloadresource"
            java.lang.String r5 = "platform.preloadpath"
            java.lang.String r6 = "platform.linkresource"
            java.lang.String r7 = "platform.linkpath"
            java.lang.String r8 = "platform.includeresource"
            java.lang.String r9 = "platform.includepath"
            java.lang.String r10 = "platform.buildresource"
            java.lang.String r11 = "platform.buildpath"
            java.lang.String r12 = "platform"
            java.lang.String r13 = "platform.artifacts"
            java.lang.String r14 = "\""
            org.apache.maven.plugin.logging.Log r15 = r24.getLog()
            boolean r16 = r15.isDebugEnabled()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r16 == 0) goto L_0x0445
            r16 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r17 = r2
            java.lang.String r2 = "classPath: "
            r13.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r1.classPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r13.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "classPaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.classPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "buildPath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.buildPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "buildPaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.buildPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "buildResource: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.buildResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "buildResources: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.buildResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "includePath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.includePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "includePaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.includePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "includeResource: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.includeResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "includeResources: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.includeResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "linkPath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.linkPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "linkPaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.linkPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "linkResource: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.linkResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "linkResources: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.linkResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "preloadPath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.preloadPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "preloadPaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.preloadPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "preloadResource: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.preloadResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "preloadResources: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.preloadResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "resourcePath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.resourcePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "resourcePaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.resourcePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "executablePath: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.executablePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "executablePaths: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.executablePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "encoding: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.encoding     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "outputDirectory: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r13 = r1.outputDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "outputName: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.outputName     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "clean: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.clean     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "generate: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.generate     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "compile: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.compile     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "deleteJniFiles: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.deleteJniFiles     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "header: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.header     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "copyLibs: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.copyLibs     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "copyResources: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.copyResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "configDirectory: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.configDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "jarPrefix: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.jarPrefix     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "properties: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.properties     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "propertyFile: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r13 = r1.propertyFile     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "propertyKeysAndValues: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Properties r13 = r1.propertyKeysAndValues     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "classOrPackageName: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r1.classOrPackageName     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "classOrPackageNames: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.classOrPackageNames     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "buildCommand: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.buildCommand     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "targetDirectory: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.buildCommand     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "workingDirectory: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r13 = r1.workingDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "environmentVariables: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Map<java.lang.String, java.lang.String> r13 = r1.environmentVariables     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "compilerOptions: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r13 = r1.compilerOptions     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = java.util.Arrays.deepToString(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "skip: "
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r13 = r1.skip     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0449
        L_0x0445:
            r17 = r2
            r16 = r13
        L_0x0449:
            java.lang.String r2 = r1.targetDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r2 == 0) goto L_0x0452
            org.apache.maven.project.MavenProject r13 = r1.project     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.addCompileSourceRoot(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x0452:
            java.lang.String[] r2 = r1.targetDirectories     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r2 == 0) goto L_0x046e
            int r13 = r2.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r18 = r3
            r3 = 0
        L_0x045a:
            if (r3 >= r13) goto L_0x0470
            r19 = r13
            r13 = r2[r3]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r20 = r2
            org.apache.maven.project.MavenProject r2 = r1.project     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.addCompileSourceRoot(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r3 = r3 + 1
            r13 = r19
            r2 = r20
            goto L_0x045a
        L_0x046e:
            r18 = r3
        L_0x0470:
            boolean r2 = r1.skip     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r2 == 0) goto L_0x047a
            java.lang.String r2 = "Skipping execution of JavaCPP Builder"
            r15.info(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            return
        L_0x047a:
            java.lang.String[] r2 = r1.classPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.classPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r2 = r1.merge(r2, r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r1.classPaths = r2     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r2 = r1.classOrPackageNames     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.classOrPackageName     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r2 = r1.merge(r2, r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r1.classOrPackageNames = r2     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.BuildMojo$1 r2 = new org.bytedeco.javacpp.tools.BuildMojo$1     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>(r15)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r3 = new org.bytedeco.javacpp.tools.Builder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r2 = r1.classPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r3.classPaths((java.lang.String[]) r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.encoding     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.encoding(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r3 = r1.outputDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.outputDirectory((java.io.File) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.outputName     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.outputName(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.clean     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.clean(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.generate     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.generate(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.compile     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.compile(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.deleteJniFiles     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.deleteJniFiles(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.header     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.header(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.copyLibs     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.copyLibs(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r1.copyResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.copyResources(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.configDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.configDirectory((java.lang.String) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.jarPrefix     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.jarPrefix(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r3 = r1.properties     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.properties((java.lang.String) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r3 = r1.propertyFile     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.propertyFile((java.io.File) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Properties r3 = r1.propertyKeysAndValues     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.properties((java.util.Properties) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r3 = r1.classOrPackageNames     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.classesOrPackages(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r3 = r1.buildCommand     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.buildCommand(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r3 = r1.workingDirectory     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.workingDirectory((java.io.File) r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.environmentVariables     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.environmentVariables(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r3 = r1.compilerOptions     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.bytedeco.javacpp.tools.Builder r2 = r2.compilerOptions(r3)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Properties r3 = r2.properties     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = "platform.extension"
            java.lang.String r13 = r3.getProperty(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r19 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r20 = r4
            java.lang.String r4 = "Detected platform \""
            r2.append(r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r4 = org.bytedeco.javacpp.Loader.getPlatform()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r14)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.info(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r4 = "Building platform \""
            r2.append(r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.Object r4 = r3.get(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r14)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r4 = ""
            if (r13 == 0) goto L_0x0574
            int r21 = r13.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r21 <= 0) goto L_0x0574
            r21 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r22 = r6
            java.lang.String r6 = " with extension \""
            r5.append(r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r14)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0579
        L_0x0574:
            r21 = r5
            r22 = r6
            r5 = r4
        L_0x0579:
            r2.append(r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.info(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = "platform.host"
            java.lang.String r5 = org.bytedeco.javacpp.Loader.getPlatform()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r3.setProperty(r2, r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.Object r5 = r3.get(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.append(r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r13 == 0) goto L_0x059b
            goto L_0x059c
        L_0x059b:
            r13 = r4
        L_0x059c:
            r2.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r5 = "platform.module"
            r6 = 45
            r12 = 46
            java.lang.String r2 = r2.replace(r6, r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r3.setProperty(r5, r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = "platform.path.separator"
            java.lang.String r2 = r3.getProperty(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.buildPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.buildPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12 = 0
        L_0x05c0:
            if (r12 >= r6) goto L_0x0600
            r13 = r5[r12]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r14 = r3.getProperty(r11, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r23 = r14.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r23 == 0) goto L_0x05ea
            boolean r23 = r14.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r23 == 0) goto L_0x05d5
            goto L_0x05ea
        L_0x05d5:
            r23 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r14)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x05e5:
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x05f8
        L_0x05ea:
            r23 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r14)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r5.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x05e5
        L_0x05f8:
            r3.setProperty(r11, r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r12 = r12 + 1
            r5 = r23
            goto L_0x05c0
        L_0x0600:
            java.lang.String[] r5 = r1.buildResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.buildResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11 = 0
        L_0x060a:
            if (r11 >= r6) goto L_0x0644
            r12 = r5[r11]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r13 = r3.getProperty(r10, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r14 = r13.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r14 == 0) goto L_0x0632
            boolean r14 = r13.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r14 == 0) goto L_0x061f
            goto L_0x0632
        L_0x061f:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.append(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x062d:
            java.lang.String r12 = r14.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x063e
        L_0x0632:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.append(r13)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r14.append(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x062d
        L_0x063e:
            r3.setProperty(r10, r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r11 + 1
            goto L_0x060a
        L_0x0644:
            java.lang.String[] r5 = r1.includePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.includePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10 = 0
        L_0x064e:
            if (r10 >= r6) goto L_0x0688
            r11 = r5[r10]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r12 = r3.getProperty(r9, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r13 = r12.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r13 == 0) goto L_0x0676
            boolean r13 = r12.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r13 == 0) goto L_0x0663
            goto L_0x0676
        L_0x0663:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r11)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x0671:
            java.lang.String r11 = r13.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0682
        L_0x0676:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r12)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13.append(r11)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0671
        L_0x0682:
            r3.setProperty(r9, r11)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r10 = r10 + 1
            goto L_0x064e
        L_0x0688:
            java.lang.String[] r5 = r1.includeResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.includeResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9 = 0
        L_0x0692:
            if (r9 >= r6) goto L_0x06cc
            r10 = r5[r9]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r11 = r3.getProperty(r8, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r12 = r11.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r12 == 0) goto L_0x06ba
            boolean r12 = r11.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r12 == 0) goto L_0x06a7
            goto L_0x06ba
        L_0x06a7:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.append(r11)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x06b5:
            java.lang.String r10 = r12.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x06c6
        L_0x06ba:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.append(r11)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r12.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x06b5
        L_0x06c6:
            r3.setProperty(r8, r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r9 = r9 + 1
            goto L_0x0692
        L_0x06cc:
            java.lang.String[] r5 = r1.linkPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.linkPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r8 = 0
        L_0x06d6:
            if (r8 >= r6) goto L_0x0710
            r9 = r5[r8]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r10 = r3.getProperty(r7, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r10.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x06fe
            boolean r11 = r10.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x06eb
            goto L_0x06fe
        L_0x06eb:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r9)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x06f9:
            java.lang.String r9 = r11.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x070a
        L_0x06fe:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r9)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x06f9
        L_0x070a:
            r3.setProperty(r7, r9)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r8 = r8 + 1
            goto L_0x06d6
        L_0x0710:
            java.lang.String[] r5 = r1.linkResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.linkResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r7 = 0
        L_0x071a:
            if (r7 >= r6) goto L_0x0758
            r8 = r5[r7]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9 = r22
            java.lang.String r10 = r3.getProperty(r9, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r10.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x0744
            boolean r11 = r10.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x0731
            goto L_0x0744
        L_0x0731:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x073f:
            java.lang.String r8 = r11.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0750
        L_0x0744:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x073f
        L_0x0750:
            r3.setProperty(r9, r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r7 = r7 + 1
            r22 = r9
            goto L_0x071a
        L_0x0758:
            java.lang.String[] r5 = r1.preloadPaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.preloadPath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r7 = 0
        L_0x0762:
            if (r7 >= r6) goto L_0x07a0
            r8 = r5[r7]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9 = r21
            java.lang.String r10 = r3.getProperty(r9, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r10.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x078c
            boolean r11 = r10.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x0779
            goto L_0x078c
        L_0x0779:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x0787:
            java.lang.String r8 = r11.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0798
        L_0x078c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0787
        L_0x0798:
            r3.setProperty(r9, r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r7 = r7 + 1
            r21 = r9
            goto L_0x0762
        L_0x07a0:
            java.lang.String[] r5 = r1.preloadResources     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.preloadResource     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r7 = 0
        L_0x07aa:
            if (r7 >= r6) goto L_0x07e8
            r8 = r5[r7]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9 = r20
            java.lang.String r10 = r3.getProperty(r9, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r10.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x07d4
            boolean r11 = r10.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x07c1
            goto L_0x07d4
        L_0x07c1:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x07cf:
            java.lang.String r8 = r11.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x07e0
        L_0x07d4:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x07cf
        L_0x07e0:
            r3.setProperty(r9, r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r7 = r7 + 1
            r20 = r9
            goto L_0x07aa
        L_0x07e8:
            java.lang.String[] r5 = r1.resourcePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.resourcePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r7 = 0
        L_0x07f2:
            if (r7 >= r6) goto L_0x0830
            r8 = r5[r7]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9 = r18
            java.lang.String r10 = r3.getProperty(r9, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r11 = r10.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x081c
            boolean r11 = r10.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r11 == 0) goto L_0x0809
            goto L_0x081c
        L_0x0809:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x0817:
            java.lang.String r8 = r11.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0828
        L_0x081c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r10)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r11.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0817
        L_0x0828:
            r3.setProperty(r9, r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r7 = r7 + 1
            r18 = r9
            goto L_0x07f2
        L_0x0830:
            java.lang.String[] r5 = r1.executablePaths     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r1.executablePath     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String[] r5 = r1.merge(r5, r6)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r6 = r5.length     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r13 = 0
        L_0x083a:
            if (r13 >= r6) goto L_0x0878
            r7 = r5[r13]     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r8 = r17
            java.lang.String r9 = r3.getProperty(r8, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r10 = r9.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r10 == 0) goto L_0x0864
            boolean r10 = r9.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r10 == 0) goto L_0x0851
            goto L_0x0864
        L_0x0851:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.append(r9)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.append(r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x085f:
            java.lang.String r7 = r10.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0870
        L_0x0864:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.append(r9)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r10.append(r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x085f
        L_0x0870:
            r3.setProperty(r8, r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r13 = r13 + 1
            r17 = r8
            goto L_0x083a
        L_0x0878:
            org.apache.maven.project.MavenProject r5 = r1.project     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.apache.maven.model.Build r5 = r5.getBuild()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r5 = r5.getOutputDirectory()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r6 = r16
            r3.setProperty(r6, r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.apache.maven.plugin.descriptor.PluginDescriptor r5 = r1.plugin     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.List r5 = r5.getArtifacts()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x0891:
            boolean r7 = r5.hasNext()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r7 == 0) goto L_0x08d9
            java.lang.Object r7 = r5.next()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            org.apache.maven.artifact.Artifact r7 = (org.apache.maven.artifact.Artifact) r7     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.io.File r7 = r7.getFile()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r7 = r7.getCanonicalPath()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r8 = r3.getProperty(r6, r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            int r9 = r8.length()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r9 == 0) goto L_0x08c9
            boolean r9 = r8.endsWith(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r9 == 0) goto L_0x08b6
            goto L_0x08c9
        L_0x08b6:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.append(r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x08c4:
            java.lang.String r7 = r9.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x08d5
        L_0x08c9:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.append(r8)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r9.append(r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x08c4
        L_0x08d5:
            r3.setProperty(r6, r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x0891
        L_0x08d9:
            org.apache.maven.project.MavenProject r2 = r1.project     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Properties r2 = r2.getProperties()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Set r4 = r3.stringPropertyNames()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x08e7:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r5 == 0) goto L_0x090c
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r6.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r7 = "javacpp."
            r6.append(r7)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r6.append(r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r5 = r3.getProperty(r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r2.setProperty(r6, r5)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            goto L_0x08e7
        L_0x090c:
            java.io.File[] r2 = r19.build()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            boolean r3 = r15.isDebugEnabled()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            if (r3 == 0) goto L_0x092e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r3.<init>()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r4 = "outputFiles: "
            r3.append(r4)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = java.util.Arrays.deepToString(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r3.append(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
            r15.debug(r2)     // Catch:{ IOException -> 0x0937, ClassNotFoundException -> 0x0935, NoClassDefFoundError -> 0x0933, InterruptedException -> 0x0931, ParserException -> 0x092f }
        L_0x092e:
            return
        L_0x092f:
            r0 = move-exception
            goto L_0x0938
        L_0x0931:
            r0 = move-exception
            goto L_0x0938
        L_0x0933:
            r0 = move-exception
            goto L_0x0938
        L_0x0935:
            r0 = move-exception
            goto L_0x0938
        L_0x0937:
            r0 = move-exception
        L_0x0938:
            r2 = r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to execute JavaCPP Builder: "
            r3.append(r4)
            java.lang.String r4 = r2.getMessage()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r15.error(r3)
            org.apache.maven.plugin.MojoExecutionException r3 = new org.apache.maven.plugin.MojoExecutionException
            java.lang.String r4 = "Failed to execute JavaCPP Builder"
            r3.<init>(r4, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.BuildMojo.execute():void");
    }
}
