package org.bytedeco.javacpp.tools;

import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.iam.InAppMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.Loader;

public class Builder {
    String[] buildCommand;
    ClassScanner classScanner;
    boolean clean;
    CommandExecutor commandExecutor;
    boolean compile;
    Collection<String> compilerOptions;
    File configDirectory;
    boolean copyLibs;
    boolean copyResources;
    boolean deleteJniFiles;
    String encoding;
    Map<String, String> environmentVariables;
    boolean generate;
    boolean header;
    String jarPrefix;
    final Logger logger;
    File outputDirectory;
    String outputName;
    Properties properties;
    File workingDirectory;

    /* access modifiers changed from: package-private */
    public void cleanOutputDirectory() throws IOException {
        File file = this.outputDirectory;
        if (file != null && file.isDirectory() && this.clean) {
            Logger logger2 = this.logger;
            logger2.info("Deleting " + this.outputDirectory);
            Files.walkFileTree(this.outputDirectory.toPath(), new SimpleFileVisitor<Path>() {
                public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
                    if (iOException == null) {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }
                    throw iOException;
                }

                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public File[] parse(String[] strArr, Class cls) throws IOException, ParserException {
        cleanOutputDirectory();
        return new Parser(this.logger, this.properties, this.encoding, (String) null).parse(this.outputDirectory, strArr, cls);
    }

    /* access modifiers changed from: package-private */
    public void includeJavaPaths(ClassProperties classProperties, boolean z) {
        File absoluteFile;
        ClassProperties classProperties2 = classProperties;
        if (!classProperties2.getProperty("platform", "").startsWith(ChannelRegistrationPayload.ANDROID_DEVICE_TYPE)) {
            String platform = Loader.getPlatform();
            final String str = classProperties2.getProperty("platform.link.prefix", "") + "jvm" + classProperties2.getProperty("platform.link.suffix", "");
            final String str2 = classProperties2.getProperty("platform.library.prefix", "") + "jvm" + classProperties2.getProperty("platform.library.suffix", "");
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            final HashSet hashSet3 = hashSet;
            final HashSet hashSet4 = hashSet2;
            AnonymousClass2 r7 = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (new File(file, "jni.h").exists()) {
                        hashSet3.add(file.getAbsolutePath());
                    }
                    if (new File(file, "jni_md.h").exists()) {
                        hashSet3.add(file.getAbsolutePath());
                    }
                    if (new File(file, str).exists()) {
                        hashSet4.add(file.getAbsolutePath());
                    }
                    if (new File(file, str2).exists()) {
                        hashSet4.add(file.getAbsolutePath());
                    }
                    return new File(file, str).isDirectory();
                }
            };
            int i = 2;
            File[] fileArr = new File[2];
            try {
                fileArr[0] = new File(System.getProperty("java.home")).getCanonicalFile();
                int i2 = 1;
                fileArr[1] = fileArr[0].getParentFile().getCanonicalFile();
                int i3 = 0;
                while (i3 < i) {
                    File file = fileArr[i3];
                    hashSet.clear();
                    hashSet2.clear();
                    ArrayList arrayList = new ArrayList(Arrays.asList(file.listFiles(r7)));
                    while (!arrayList.isEmpty()) {
                        File file2 = (File) arrayList.remove(arrayList.size() - i2);
                        String path = file2.getPath();
                        File[] listFiles = file2.listFiles(r7);
                        if (!(path == null || listFiles == null)) {
                            int length = listFiles.length;
                            int i4 = 0;
                            while (i4 < length) {
                                File file3 = listFiles[i4];
                                try {
                                    absoluteFile = file3.getCanonicalFile();
                                } catch (IOException unused) {
                                    absoluteFile = file3.getAbsoluteFile();
                                }
                                File file4 = absoluteFile;
                                if (!path.startsWith(file4.getPath())) {
                                    arrayList.add(file4);
                                }
                                i4++;
                                i2 = 1;
                            }
                        }
                    }
                    if (!hashSet.isEmpty() && !hashSet2.isEmpty()) {
                        break;
                    }
                    i3++;
                    i = 2;
                    i2 = 1;
                }
                if (hashSet.isEmpty() && new File("/System/Library/Frameworks/JavaVM.framework/Headers/").isDirectory()) {
                    hashSet.add("/System/Library/Frameworks/JavaVM.framework/Headers/");
                }
                classProperties2.addAll("platform.includepath", (Collection<String>) hashSet);
                if (platform.equals(classProperties2.getProperty("platform", platform)) && z) {
                    classProperties2.get("platform.link").add(0, "jvm");
                    classProperties2.addAll("platform.linkpath", (Collection<String>) hashSet2);
                    if (platform.startsWith("macosx")) {
                        classProperties2.addAll("platform.framework", "JavaVM");
                    }
                }
            } catch (IOException | NullPointerException e) {
                this.logger.warn("Could not include header files from java.home:" + e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int compile(String[] strArr, String str, ClassProperties classProperties, File file) throws IOException, InterruptedException {
        int i;
        String str2;
        Iterator<String> it;
        String[] strArr2 = strArr;
        String str3 = str;
        ClassProperties classProperties2 = classProperties;
        ArrayList arrayList = new ArrayList();
        includeJavaPaths(classProperties2, this.header);
        String platform = Loader.getPlatform();
        arrayList.add(classProperties2.getProperty("platform.compiler"));
        String property = classProperties2.getProperty("platform.sysroot.prefix", "");
        for (String next : classProperties2.get("platform.sysroot")) {
            if (new File(next).isDirectory()) {
                String canonicalPath = new File(next).getCanonicalPath();
                if (property.endsWith(" ")) {
                    arrayList.add(property.trim());
                    arrayList.add(canonicalPath);
                } else {
                    arrayList.add(property + canonicalPath);
                }
            }
        }
        String property2 = classProperties2.getProperty("platform.toolchain.prefix", "");
        for (String next2 : classProperties2.get("platform.toolchain")) {
            if (new File(next2).isDirectory()) {
                String canonicalPath2 = new File(next2).getCanonicalPath();
                if (property2.endsWith(" ")) {
                    arrayList.add(property2.trim());
                    arrayList.add(canonicalPath2);
                } else {
                    arrayList.add(property2 + canonicalPath2);
                }
            }
        }
        String property3 = classProperties2.getProperty("platform.includepath.prefix", "");
        for (String next3 : classProperties2.get("platform.includepath")) {
            if (new File(next3).isDirectory()) {
                String canonicalPath3 = new File(next3).getCanonicalPath();
                if (property3.endsWith(" ")) {
                    arrayList.add(property3.trim());
                    arrayList.add(canonicalPath3);
                } else {
                    arrayList.add(property3 + canonicalPath3);
                }
            }
        }
        Iterator<String> it2 = classProperties2.get("platform.includeresource").iterator();
        while (true) {
            i = 0;
            if (!it2.hasNext()) {
                break;
            }
            File[] cacheResources = Loader.cacheResources(it2.next());
            int length = cacheResources.length;
            while (i < length) {
                File file2 = cacheResources[i];
                if (file2.isDirectory()) {
                    if (property3.endsWith(" ")) {
                        arrayList.add(property3.trim());
                        arrayList.add(file2.getCanonicalPath());
                    } else {
                        arrayList.add(property3 + file2.getCanonicalPath());
                    }
                }
                i++;
            }
        }
        for (int length2 = strArr2.length - 1; length2 >= 0; length2--) {
            arrayList.add(strArr2[length2]);
        }
        List<String> list = classProperties2.get("platform.compiler.*");
        if (!list.contains("!default") && !list.contains(InAppMessage.DISPLAY_BEHAVIOR_DEFAULT)) {
            list.add(0, InAppMessage.DISPLAY_BEHAVIOR_DEFAULT);
        }
        for (String next4 : list) {
            if (!(next4 == null || next4.length() == 0)) {
                String str4 = "platform.compiler." + next4;
                String property4 = classProperties2.getProperty(str4);
                if (property4 != null && property4.length() > 0) {
                    arrayList.addAll(Arrays.asList(property4.split(" ")));
                } else if (!"!default".equals(next4) && !InAppMessage.DISPLAY_BEHAVIOR_DEFAULT.equals(next4)) {
                    this.logger.warn("Could not get the property named \"" + str4 + "\"");
                }
            }
        }
        arrayList.addAll(this.compilerOptions);
        String property5 = classProperties2.getProperty("platform.compiler.output");
        int i2 = 1;
        while (true) {
            if (i2 >= 2 && property5 == null) {
                break;
            }
            File file3 = file;
            if (property5 != null && property5.length() > 0) {
                arrayList.addAll(Arrays.asList(property5.split(" ")));
            }
            if (property5 == null || property5.length() == 0 || property5.endsWith(" ")) {
                arrayList.add(str3);
            } else {
                arrayList.add(((String) arrayList.remove(arrayList.size() - 1)) + str3);
            }
            i2++;
            property5 = classProperties2.getProperty("platform.compiler.output" + i2);
            i = 0;
        }
        String property6 = classProperties2.getProperty("platform.linkpath.prefix", "");
        String property7 = classProperties2.getProperty("platform.linkpath.prefix2");
        for (String next5 : classProperties2.get("platform.linkpath")) {
            if (new File(next5).isDirectory()) {
                String canonicalPath4 = new File(next5).getCanonicalPath();
                if (property6.endsWith(" ")) {
                    arrayList.add(property6.trim());
                    arrayList.add(canonicalPath4);
                } else {
                    arrayList.add(property6 + canonicalPath4);
                }
                if (property7 != null) {
                    if (property7.endsWith(" ")) {
                        arrayList.add(property7.trim());
                        arrayList.add(canonicalPath4);
                    } else {
                        arrayList.add(property7 + canonicalPath4);
                    }
                }
            }
        }
        for (String cacheResources2 : classProperties2.get("platform.linkresource")) {
            for (File file4 : Loader.cacheResources(cacheResources2)) {
                if (file4.isDirectory()) {
                    if (property6.endsWith(" ")) {
                        arrayList.add(property6.trim());
                        arrayList.add(file4.getCanonicalPath());
                    } else {
                        arrayList.add(property6 + file4.getCanonicalPath());
                    }
                    if (property7 != null) {
                        if (property7.endsWith(" ")) {
                            arrayList.add(property7.trim());
                            arrayList.add(file4.getCanonicalPath());
                        } else {
                            arrayList.add(property7 + file4.getCanonicalPath());
                        }
                    }
                }
            }
        }
        String property8 = classProperties2.getProperty("platform.link.prefix", "");
        String property9 = classProperties2.getProperty("platform.link.suffix", "");
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (property8.endsWith(" ")) {
            arrayList2.addAll(Arrays.asList(property8.trim().split(" ")));
            property8 = "";
        } else {
            int lastIndexOf = property8.lastIndexOf(" ");
            if (lastIndexOf != -1) {
                arrayList2.addAll(Arrays.asList(property8.substring(i, lastIndexOf).split(" ")));
                property8 = property8.substring(lastIndexOf + 1);
            }
        }
        if (property9.startsWith(" ")) {
            arrayList3.addAll(Arrays.asList(property9.trim().split(" ")));
            property9 = "";
        } else {
            int indexOf = property9.indexOf(" ");
            if (indexOf != -1) {
                String substring = property9.substring(i, indexOf);
                arrayList3.addAll(Arrays.asList(property9.substring(indexOf + 1).split(" ")));
                property9 = substring;
            }
        }
        int size = arrayList.size();
        Iterator<String> it3 = classProperties2.get("platform.link").iterator();
        while (it3.hasNext()) {
            String[] split = it3.next().split("#")[i].split("@");
            if (split.length == 3 && split[1].length() == 0) {
                str2 = split[i] + split[2];
            } else {
                str2 = split[i];
            }
            ArrayList arrayList4 = new ArrayList();
            arrayList4.addAll(arrayList2);
            StringBuilder sb = new StringBuilder();
            sb.append(property8);
            if (str2.endsWith("!")) {
                it = it3;
                str2 = str2.substring(0, str2.length() - 1);
            } else {
                it = it3;
            }
            sb.append(str2);
            sb.append(property9);
            arrayList4.add(sb.toString());
            arrayList4.addAll(arrayList3);
            arrayList.addAll(size, arrayList4);
            it3 = it;
            i = 0;
        }
        String property10 = classProperties2.getProperty("platform.frameworkpath.prefix", "");
        for (String next6 : classProperties2.get("platform.frameworkpath")) {
            if (new File(next6).isDirectory()) {
                String canonicalPath5 = new File(next6).getCanonicalPath();
                if (property10.endsWith(" ")) {
                    arrayList.add(property10.trim());
                    arrayList.add(canonicalPath5);
                } else {
                    arrayList.add(property10 + canonicalPath5);
                }
            }
        }
        String property11 = classProperties2.getProperty("platform.framework.prefix", "");
        String property12 = classProperties2.getProperty("platform.framework.suffix", "");
        for (String next7 : classProperties2.get("platform.framework")) {
            if (property11.endsWith(" ") && property12.startsWith(" ")) {
                arrayList.add(property11.trim());
                arrayList.add(next7);
                arrayList.add(property12.trim());
            } else if (property11.endsWith(" ")) {
                arrayList.add(property11.trim());
                arrayList.add(next7 + property12);
            } else if (property12.startsWith(" ")) {
                arrayList.add(property11 + next7);
                arrayList.add(property12.trim());
            } else {
                arrayList.add(property11 + next7 + property12);
            }
        }
        boolean startsWith = platform.startsWith("windows");
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            String str5 = (String) arrayList.get(i3);
            if (str5 == null) {
                str5 = "";
            }
            if (str5.trim().isEmpty() && startsWith) {
                str5 = "\"\"";
            }
            arrayList.set(i3, str5);
        }
        return this.commandExecutor.executeCommand(arrayList, file, this.environmentVariables);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01a2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01a3, code lost:
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01a5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01a6, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01c7, code lost:
        throw new java.lang.RuntimeException(r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01c1 A[ExcHandler: URISyntaxException (r0v2 'e' java.net.URISyntaxException A[CUSTOM_DECLARE]), Splitter:B:12:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File getOutputPath(java.lang.Class[] r18, java.lang.String[] r19) throws java.io.IOException {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            java.lang.String r2 = ".class"
            r17.cleanOutputDirectory()
            java.io.File r3 = r1.outputDirectory
            if (r3 == 0) goto L_0x0012
            java.io.File r3 = r3.getCanonicalFile()
            goto L_0x0013
        L_0x0012:
            r3 = 0
        L_0x0013:
            java.util.Properties r5 = r1.properties
            r6 = 1
            org.bytedeco.javacpp.ClassProperties r5 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r0, (java.util.Properties) r5, (boolean) r6)
            java.util.Properties r7 = r1.properties
            java.lang.String r8 = "platform"
            java.lang.String r7 = r7.getProperty(r8)
            java.util.Properties r8 = r1.properties
            java.lang.String r9 = "platform.extension"
            java.lang.String r8 = r8.getProperty(r9)
            java.lang.String r9 = ""
            if (r3 == 0) goto L_0x0044
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = r3.getPath()
            r10.append(r11)
            java.lang.String r11 = java.io.File.separator
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            goto L_0x0045
        L_0x0044:
            r10 = r9
        L_0x0045:
            java.lang.String r11 = "platform.library.path"
            java.lang.String r5 = r5.getProperty(r11, r9)
            r11 = 0
            if (r19 == 0) goto L_0x0052
            r19[r6] = r10
            r19[r11] = r10
        L_0x0052:
            if (r3 != 0) goto L_0x01c8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r3.<init>()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r10 = 47
            r3.append(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r12 = r0[r11]     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r12 = r12.getName()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r13 = 46
            java.lang.String r12 = r12.replace(r13, r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r3.append(r12)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r3.append(r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r3 = r3.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r12 = r0[r11]     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.net.URL r12 = org.bytedeco.javacpp.Loader.findResource(r12, r3)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r12 = r12.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r14 = r12.lastIndexOf(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r14 = r14 + r6
            java.lang.String r14 = r12.substring(r11, r14)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r15 = 1
        L_0x0088:
            int r4 = r0.length     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            if (r15 >= r4) goto L_0x00f0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r4.<init>()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r4.append(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r16 = r0[r15]     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r11 = r16.getName()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r11 = r11.replace(r13, r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r4.append(r11)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r4.append(r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r11 = r0[r15]     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.net.URL r4 = org.bytedeco.javacpp.Loader.findResource(r11, r4)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r11 = r4.lastIndexOf(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r11 = r11 + r6
            r13 = 0
            java.lang.String r4 = r4.substring(r13, r11)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r11 = r4.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r13 = r14.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            if (r11 <= r13) goto L_0x00c7
            r11 = r4
            goto L_0x00c8
        L_0x00c7:
            r11 = r14
        L_0x00c8:
            int r13 = r4.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            int r6 = r14.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            if (r13 >= r6) goto L_0x00d3
            r14 = r4
        L_0x00d3:
            boolean r4 = r11.startsWith(r14)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            if (r4 != 0) goto L_0x00e9
            int r4 = r14.lastIndexOf(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            if (r4 <= 0) goto L_0x00e9
            int r4 = r14.lastIndexOf(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r6 = 0
            java.lang.String r14 = r14.substring(r6, r4)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            goto L_0x00d3
        L_0x00e9:
            int r15 = r15 + 1
            r6 = 1
            r11 = 0
            r13 = 46
            goto L_0x0088
        L_0x00f0:
            java.net.URI r2 = new java.net.URI     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            r2.<init>(r14)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a8 }
            java.lang.String r0 = "file"
            java.lang.String r4 = r2.getScheme()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            boolean r0 = r0.equals(r4)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            java.io.File r4 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            org.bytedeco.javacpp.tools.ClassScanner r6 = r1.classScanner     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            org.bytedeco.javacpp.tools.UserClassLoader r6 = r6.getClassLoader()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            java.lang.String[] r6 = r6.getPaths()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r11 = 0
            r6 = r6[r11]     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r4.<init>(r6)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            java.io.File r4 = r4.getCanonicalFile()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            if (r0 == 0) goto L_0x011d
            java.io.File r6 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r6.<init>(r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            goto L_0x012d
        L_0x011d:
            java.io.File r6 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            int r10 = r3.lastIndexOf(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r11 = 1
            int r10 = r10 + r11
            r11 = 0
            java.lang.String r10 = r3.substring(r11, r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r6.<init>(r4, r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
        L_0x012d:
            java.net.URI r10 = new java.net.URI     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            int r11 = r12.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            int r3 = r3.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            int r11 = r11 - r3
            r3 = 1
            int r11 = r11 + r3
            r3 = 0
            java.lang.String r11 = r12.substring(r3, r11)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            r10.<init>(r11)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a5 }
            int r2 = r5.length()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            if (r2 <= 0) goto L_0x0152
            if (r0 == 0) goto L_0x0150
            java.io.File r0 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r0.<init>(r10)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            goto L_0x016a
        L_0x0150:
            r0 = r4
            goto L_0x016a
        L_0x0152:
            java.io.File r0 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2.<init>()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2.append(r7)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            if (r8 == 0) goto L_0x015f
            goto L_0x0160
        L_0x015f:
            r8 = r9
        L_0x0160:
            r2.append(r8)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r2 = r2.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r0.<init>(r6, r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
        L_0x016a:
            java.io.File r3 = new java.io.File     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r3.<init>(r0, r5)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r0.<init>()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r2 = r6.getPath()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r0.append(r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r2 = java.io.File.separator     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r0.append(r2)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r0 = r0.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            if (r19 == 0) goto L_0x01c8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2.<init>()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r4 = r4.getPath()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2.append(r4)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r4 = java.io.File.separator     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2.append(r4)     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            java.lang.String r2 = r2.toString()     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r4 = 0
            r19[r4] = r2     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            r2 = 1
            r19[r2] = r0     // Catch:{ URISyntaxException -> 0x01c1, IllegalArgumentException -> 0x01a2 }
            goto L_0x01c8
        L_0x01a2:
            r0 = move-exception
            r4 = r10
            goto L_0x01aa
        L_0x01a5:
            r0 = move-exception
            r4 = r2
            goto L_0x01aa
        L_0x01a8:
            r0 = move-exception
            r4 = 0
        L_0x01aa:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "URI: "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r0)
            throw r2
        L_0x01c1:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r0)
            throw r2
        L_0x01c8:
            boolean r0 = r3.exists()
            if (r0 != 0) goto L_0x01d1
            r3.mkdirs()
        L_0x01d1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Builder.getOutputPath(java.lang.Class[], java.lang.String[]):java.io.File");
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [java.lang.Object[]] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File[] generateAndCompile(java.lang.Class[] r31, java.lang.String r32, boolean r33, boolean r34) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]
            java.io.File r5 = r0.getOutputPath(r1, r4)
            java.util.Properties r6 = r0.properties
            r7 = 1
            org.bytedeco.javacpp.ClassProperties r6 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r1, (java.util.Properties) r6, (boolean) r7)
            java.lang.String r8 = "platform.source.suffix"
            java.lang.String r9 = ".cpp"
            java.lang.String r8 = r6.getProperty(r8, r9)
            java.lang.String r9 = "platform.library.prefix"
            java.lang.String r10 = ""
            java.lang.String r9 = r6.getProperty(r9, r10)
            java.lang.String r11 = "platform.library.suffix"
            java.lang.String r11 = r6.getProperty(r11, r10)
            org.bytedeco.javacpp.tools.Generator r15 = new org.bytedeco.javacpp.tools.Generator
            org.bytedeco.javacpp.tools.Logger r12 = r0.logger
            java.util.Properties r13 = r0.properties
            java.lang.String r14 = r0.encoding
            r15.<init>(r12, r13, r14)
            java.lang.String[] r12 = new java.lang.String[r3]
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r21 = 0
            r14 = r4[r21]
            r13.append(r14)
            java.lang.String r14 = "jnijavacpp"
            r13.append(r14)
            r13.append(r8)
            java.lang.String r13 = r13.toString()
            r12[r21] = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r3 = r4[r7]
            r13.append(r3)
            r13.append(r2)
            r13.append(r8)
            java.lang.String r3 = r13.toString()
            r12[r7] = r3
            r3 = 2
            java.lang.String[] r13 = new java.lang.String[r3]
            java.io.File r3 = r0.configDirectory
            if (r3 == 0) goto L_0x007a
            java.io.File r3 = new java.io.File
            java.io.File r7 = r0.configDirectory
            r3.<init>(r7, r14)
            java.lang.String r3 = r3.getPath()
            goto L_0x007b
        L_0x007a:
            r3 = 0
        L_0x007b:
            r13[r21] = r3
            java.io.File r3 = r0.configDirectory
            if (r3 == 0) goto L_0x008d
            java.io.File r3 = new java.io.File
            java.io.File r7 = r0.configDirectory
            r3.<init>(r7, r2)
            java.lang.String r3 = r3.getPath()
            goto L_0x008e
        L_0x008d:
            r3 = 0
        L_0x008e:
            r7 = 1
            r13[r7] = r3
            r3 = 2
            java.lang.String[] r7 = new java.lang.String[r3]
            r3 = 0
            r7[r21] = r3
            boolean r3 = r0.header
            r17 = r12
            java.lang.String r12 = ".h"
            if (r3 == 0) goto L_0x00b8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r19 = r13
            r18 = 1
            r13 = r4[r18]
            r3.append(r13)
            r3.append(r2)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            goto L_0x00bd
        L_0x00b8:
            r19 = r13
            r18 = 1
            r3 = 0
        L_0x00bd:
            r7[r18] = r3
            java.lang.String r3 = "_jnijavacpp"
            r13 = 0
            java.lang.String[] r18 = new java.lang.String[]{r3, r13}
            java.lang.String[] r3 = new java.lang.String[]{r13, r3}
            java.lang.String r13 = "java.class.path"
            java.lang.String r13 = java.lang.System.getProperty(r13)
            r20 = r3
            org.bytedeco.javacpp.tools.ClassScanner r3 = r0.classScanner
            org.bytedeco.javacpp.tools.UserClassLoader r3 = r3.getClassLoader()
            java.lang.String[] r3 = r3.getPaths()
            r22 = r7
            int r7 = r3.length
            r23 = r6
            r6 = 0
        L_0x00e2:
            if (r6 >= r7) goto L_0x0105
            r24 = r7
            r7 = r3[r6]
            r25 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r13)
            java.lang.String r13 = java.io.File.pathSeparator
            r3.append(r13)
            r3.append(r7)
            java.lang.String r13 = r3.toString()
            int r6 = r6 + 1
            r7 = r24
            r3 = r25
            goto L_0x00e2
        L_0x0105:
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]
            r7 = 0
            r6[r21] = r7
            r16 = 1
            r6[r16] = r13
            r24 = r6
            java.lang.Class[][] r6 = new java.lang.Class[r3][]
            r6[r21] = r7
            r6[r16] = r1
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r9)
            r7.append(r14)
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            r3[r21] = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r9)
            r7.append(r2)
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            r16 = r6
            r6 = 1
            r3[r6] = r7
            boolean r7 = r2.equals(r14)
            if (r7 == 0) goto L_0x01c6
            java.lang.String[] r3 = new java.lang.String[r6]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r14 = r4[r21]
            r7.append(r14)
            r7.append(r2)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r3[r21] = r7
            java.lang.String[] r7 = new java.lang.String[r6]
            java.io.File r8 = r0.configDirectory
            if (r8 == 0) goto L_0x0174
            java.io.File r8 = new java.io.File
            java.io.File r14 = r0.configDirectory
            r8.<init>(r14, r2)
            java.lang.String r8 = r8.getPath()
            goto L_0x0175
        L_0x0174:
            r8 = 0
        L_0x0175:
            r7[r21] = r8
            java.lang.String[] r8 = new java.lang.String[r6]
            boolean r6 = r0.header
            if (r6 == 0) goto L_0x0192
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r4 = r4[r21]
            r6.append(r4)
            r6.append(r2)
            r6.append(r12)
            java.lang.String r4 = r6.toString()
            goto L_0x0193
        L_0x0192:
            r4 = 0
        L_0x0193:
            r8[r21] = r4
            r4 = 0
            java.lang.String[] r18 = new java.lang.String[]{r4}
            java.lang.String[] r6 = new java.lang.String[]{r4}
            r12 = 1
            java.lang.String[] r14 = new java.lang.String[r12]
            r14[r21] = r13
            java.lang.Class[][] r13 = new java.lang.Class[r12][]
            r13[r21] = r1
            java.lang.String[] r1 = new java.lang.String[r12]
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r9)
            r12.append(r2)
            r12.append(r11)
            java.lang.String r2 = r12.toString()
            r1[r21] = r2
            r9 = r7
            r7 = r8
            r2 = r13
            r11 = r18
            r8 = r3
            r3 = r6
            r6 = r14
            goto L_0x01d6
        L_0x01c6:
            r4 = 0
            r1 = r3
            r2 = r16
            r8 = r17
            r11 = r18
            r9 = r19
            r3 = r20
            r7 = r22
            r6 = r24
        L_0x01d6:
            int r14 = r8.length
            java.lang.String[] r13 = new java.lang.String[r14]
            int r12 = r8.length
            java.lang.String[] r4 = new java.lang.String[r12]
            r22 = r5
            r16 = r12
            r5 = 0
        L_0x01e1:
            int r12 = r8.length
            if (r5 >= r12) goto L_0x02a8
            if (r5 != 0) goto L_0x01f5
            if (r33 != 0) goto L_0x01f5
            r24 = r1
            r31 = r2
            r28 = r13
            r1 = r14
            r29 = r15
            r2 = r16
            goto L_0x0299
        L_0x01f5:
            org.bytedeco.javacpp.tools.Logger r12 = r0.logger
            r17 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r24 = r1
            java.lang.String r1 = "Generating "
            r14.append(r1)
            r1 = r8[r5]
            r14.append(r1)
            java.lang.String r1 = r14.toString()
            r12.info(r1)
            r1 = r9[r5]
            if (r1 == 0) goto L_0x022e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r12 = r9[r5]
            r1.append(r12)
            java.lang.String r12 = java.io.File.separator
            r1.append(r12)
            java.lang.String r12 = "jni-config.json"
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            goto L_0x022f
        L_0x022e:
            r1 = 0
        L_0x022f:
            r13[r5] = r1
            r1 = r9[r5]
            if (r1 == 0) goto L_0x024e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r12 = r9[r5]
            r1.append(r12)
            java.lang.String r12 = java.io.File.separator
            r1.append(r12)
            java.lang.String r12 = "reflect-config.json"
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            goto L_0x024f
        L_0x024e:
            r1 = 0
        L_0x024f:
            r4[r5] = r1
            r1 = r8[r5]
            r14 = r13[r5]
            r18 = r4[r5]
            r19 = r7[r5]
            r20 = r11[r5]
            r25 = r3[r5]
            r26 = r6[r5]
            r27 = r2[r5]
            r31 = r2
            r2 = r16
            r12 = r15
            r28 = r13
            r13 = r1
            r1 = r17
            r29 = r15
            r15 = r18
            r16 = r19
            r17 = r20
            r18 = r25
            r19 = r26
            r20 = r27
            boolean r12 = r12.generate(r13, r14, r15, r16, r17, r18, r19, r20)
            if (r12 != 0) goto L_0x0299
            org.bytedeco.javacpp.tools.Logger r3 = r0.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "Nothing generated for "
            r6.append(r9)
            r5 = r8[r5]
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r3.info(r5)
            r3 = 0
            goto L_0x02b0
        L_0x0299:
            int r5 = r5 + 1
            r14 = r1
            r16 = r2
            r1 = r24
            r13 = r28
            r15 = r29
            r2 = r31
            goto L_0x01e1
        L_0x02a8:
            r24 = r1
            r28 = r13
            r1 = r14
            r2 = r16
            r3 = 1
        L_0x02b0:
            if (r3 == 0) goto L_0x0443
            boolean r3 = r0.compile
            if (r3 == 0) goto L_0x03cd
            java.util.Properties r3 = r0.properties
            java.lang.String r5 = "platform.library.static"
            java.lang.String r6 = "false"
            java.lang.String r3 = r3.getProperty(r5, r6)
            java.lang.String r3 = r3.toLowerCase()
            java.lang.String r5 = "true"
            boolean r5 = r3.equals(r5)
            java.lang.String r6 = "Compiling "
            if (r5 != 0) goto L_0x0317
            java.lang.String r5 = "t"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0317
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x02dd
            goto L_0x0317
        L_0x02dd:
            r3 = r24
            int r5 = r3.length
            r9 = 1
            int r5 = r5 - r9
            r3 = r3[r5]
            org.bytedeco.javacpp.tools.Logger r5 = r0.logger
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r6)
            java.lang.String r6 = r22.getPath()
            r9.append(r6)
            java.lang.String r6 = java.io.File.separator
            r9.append(r6)
            r9.append(r3)
            java.lang.String r6 = r9.toString()
            r5.info(r6)
            r5 = r22
            r9 = r23
            int r6 = r0.compile(r8, r3, r9, r5)
            r9 = 1
            java.io.File[] r10 = new java.io.File[r9]
            java.io.File r9 = new java.io.File
            r9.<init>(r5, r3)
            r10[r21] = r9
            goto L_0x0368
        L_0x0317:
            r5 = r22
            r9 = r23
            r3 = r24
            int r10 = r8.length
            java.io.File[] r10 = new java.io.File[r10]
            r11 = 0
            r12 = 0
        L_0x0322:
            if (r11 != 0) goto L_0x0367
            int r13 = r8.length
            if (r12 >= r13) goto L_0x0367
            if (r12 != 0) goto L_0x032c
            if (r33 != 0) goto L_0x032c
            goto L_0x0364
        L_0x032c:
            org.bytedeco.javacpp.tools.Logger r11 = r0.logger
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r6)
            java.lang.String r14 = r5.getPath()
            r13.append(r14)
            java.lang.String r14 = java.io.File.separator
            r13.append(r14)
            r14 = r3[r12]
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.info(r13)
            r11 = 1
            java.lang.String[] r13 = new java.lang.String[r11]
            r11 = r8[r12]
            r13[r21] = r11
            r11 = r3[r12]
            int r11 = r0.compile(r13, r11, r9, r5)
            java.io.File r13 = new java.io.File
            r14 = r3[r12]
            r13.<init>(r5, r14)
            r10[r12] = r13
        L_0x0364:
            int r12 = r12 + 1
            goto L_0x0322
        L_0x0367:
            r6 = r11
        L_0x0368:
            if (r6 != 0) goto L_0x03b6
            int r3 = r8.length
            r5 = 1
            int r3 = r3 - r5
        L_0x036d:
            if (r3 < 0) goto L_0x03e0
            if (r3 != 0) goto L_0x0374
            if (r34 != 0) goto L_0x0374
            goto L_0x03b3
        L_0x0374:
            boolean r5 = r0.deleteJniFiles
            if (r5 == 0) goto L_0x039b
            org.bytedeco.javacpp.tools.Logger r5 = r0.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "Deleting "
            r6.append(r9)
            r9 = r8[r3]
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r5.info(r6)
            java.io.File r5 = new java.io.File
            r6 = r8[r3]
            r5.<init>(r6)
            r5.delete()
            goto L_0x03b3
        L_0x039b:
            org.bytedeco.javacpp.tools.Logger r5 = r0.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "Keeping "
            r6.append(r9)
            r9 = r8[r3]
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r5.info(r6)
        L_0x03b3:
            int r3 = r3 + -1
            goto L_0x036d
        L_0x03b6:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Process exited with an error: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x03cd:
            int r3 = r8.length
            java.io.File[] r10 = new java.io.File[r3]
            r3 = 0
        L_0x03d1:
            int r5 = r8.length
            if (r3 >= r5) goto L_0x03e0
            java.io.File r5 = new java.io.File
            r6 = r8[r3]
            r5.<init>(r6)
            r10[r3] = r5
            int r3 = r3 + 1
            goto L_0x03d1
        L_0x03e0:
            boolean r3 = r0.header
            if (r3 == 0) goto L_0x0402
            int r3 = r7.length
            r5 = 0
        L_0x03e6:
            if (r5 >= r3) goto L_0x0402
            r6 = r7[r5]
            if (r6 == 0) goto L_0x03ff
            int r8 = r10.length
            r9 = 1
            int r8 = r8 + r9
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r10, r8)
            java.io.File[] r8 = (java.io.File[]) r8
            int r10 = r8.length
            int r10 = r10 - r9
            java.io.File r9 = new java.io.File
            r9.<init>(r6)
            r8[r10] = r9
            r10 = r8
        L_0x03ff:
            int r5 = r5 + 1
            goto L_0x03e6
        L_0x0402:
            r7 = r10
            java.io.File r3 = r0.configDirectory
            if (r3 == 0) goto L_0x0444
            r3 = 0
        L_0x0408:
            if (r3 >= r1) goto L_0x0424
            r5 = r28[r3]
            if (r5 == 0) goto L_0x0421
            int r6 = r7.length
            r8 = 1
            int r6 = r6 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r7, r6)
            java.io.File[] r6 = (java.io.File[]) r6
            int r7 = r6.length
            int r7 = r7 - r8
            java.io.File r8 = new java.io.File
            r8.<init>(r5)
            r6[r7] = r8
            r7 = r6
        L_0x0421:
            int r3 = r3 + 1
            goto L_0x0408
        L_0x0424:
            r1 = 0
        L_0x0425:
            if (r1 >= r2) goto L_0x0444
            r3 = r4[r1]
            if (r3 == 0) goto L_0x043f
            int r5 = r7.length
            r6 = 1
            int r5 = r5 + r6
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r7, r5)
            r7 = r5
            java.io.File[] r7 = (java.io.File[]) r7
            int r5 = r7.length
            int r5 = r5 - r6
            java.io.File r8 = new java.io.File
            r8.<init>(r3)
            r7[r5] = r8
            goto L_0x0440
        L_0x043f:
            r6 = 1
        L_0x0440:
            int r1 = r1 + 1
            goto L_0x0425
        L_0x0443:
            r7 = 0
        L_0x0444:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Builder.generateAndCompile(java.lang.Class[], java.lang.String, boolean, boolean):java.io.File[]");
    }

    /* access modifiers changed from: package-private */
    public void createJar(File file, String[] strArr, File... fileArr) throws IOException {
        this.logger.info("Creating " + file);
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file));
        for (File file2 : fileArr) {
            String path = file2.getPath();
            if (strArr != null) {
                int length = strArr.length;
                String[] strArr2 = new String[length];
                for (int i = 0; i < strArr.length; i++) {
                    String canonicalPath = new File(strArr[i]).getCanonicalPath();
                    if (path.startsWith(canonicalPath)) {
                        strArr2[i] = path.substring(canonicalPath.length() + 1);
                    }
                }
                for (int i2 = 0; i2 < length; i2++) {
                    if (strArr2[i2] != null && strArr2[i2].length() < path.length()) {
                        path = strArr2[i2];
                    }
                }
            }
            ZipEntry zipEntry = new ZipEntry(path.replace(File.separatorChar, '/'));
            zipEntry.setTime(file2.lastModified());
            jarOutputStream.putNextEntry(zipEntry);
            FileInputStream fileInputStream = new FileInputStream(file2);
            byte[] bArr = new byte[65536];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                jarOutputStream.write(bArr, 0, read);
            }
            fileInputStream.close();
            jarOutputStream.closeEntry();
        }
        jarOutputStream.close();
    }

    public Builder() {
        this(Logger.create(Builder.class));
    }

    public Builder(Logger logger2) {
        this.encoding = null;
        this.outputDirectory = null;
        this.outputName = null;
        this.configDirectory = null;
        this.jarPrefix = null;
        this.clean = false;
        this.generate = true;
        this.compile = true;
        this.deleteJniFiles = true;
        this.header = false;
        this.copyLibs = false;
        this.copyResources = false;
        this.properties = null;
        this.classScanner = null;
        this.buildCommand = null;
        this.workingDirectory = null;
        this.environmentVariables = null;
        this.compilerOptions = null;
        this.commandExecutor = null;
        this.logger = logger2;
        System.setProperty("org.bytedeco.javacpp.loadlibraries", "false");
        this.properties = Loader.loadProperties();
        this.classScanner = new ClassScanner(logger2, new ArrayList(), new UserClassLoader(Thread.currentThread().getContextClassLoader()));
        this.compilerOptions = new ArrayList();
        this.commandExecutor = new CommandExecutor(logger2);
    }

    public Builder classPaths(String str) {
        classPaths(str == null ? null : str.split(File.pathSeparator));
        return this;
    }

    public Builder classPaths(String... strArr) {
        this.classScanner.getClassLoader().addPaths(strArr);
        return this;
    }

    public Builder encoding(String str) {
        this.encoding = str;
        return this;
    }

    public Builder outputDirectory(String str) {
        outputDirectory(str == null ? null : new File(str));
        return this;
    }

    public Builder outputDirectory(File file) {
        this.outputDirectory = file;
        return this;
    }

    public Builder clean(boolean z) {
        this.clean = z;
        return this;
    }

    public Builder generate(boolean z) {
        this.generate = z;
        return this;
    }

    public Builder compile(boolean z) {
        this.compile = z;
        return this;
    }

    public Builder deleteJniFiles(boolean z) {
        this.deleteJniFiles = z;
        return this;
    }

    public Builder header(boolean z) {
        this.header = z;
        return this;
    }

    public Builder copyLibs(boolean z) {
        this.copyLibs = z;
        return this;
    }

    public Builder copyResources(boolean z) {
        this.copyResources = z;
        return this;
    }

    public Builder outputName(String str) {
        this.outputName = str;
        return this;
    }

    public Builder configDirectory(String str) {
        configDirectory(str == null ? null : new File(str));
        return this;
    }

    public Builder configDirectory(File file) {
        this.configDirectory = file;
        return this;
    }

    public Builder jarPrefix(String str) {
        this.jarPrefix = str;
        return this;
    }

    public Builder properties(String str) {
        if (str != null) {
            this.properties = Loader.loadProperties(str, (String) null);
        }
        return this;
    }

    public Builder properties(Properties properties2) {
        if (properties2 != null) {
            for (Map.Entry entry : properties2.entrySet()) {
                property((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return this;
    }

    public Builder propertyFile(String str) throws IOException {
        propertyFile(str == null ? null : new File(str));
        return this;
    }

    public Builder propertyFile(File file) throws IOException {
        if (file == null) {
            return this;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties2 = new Properties();
        this.properties = properties2;
        try {
            properties2.load(new InputStreamReader(fileInputStream));
        } catch (NoSuchMethodError unused) {
            this.properties.load(fileInputStream);
        }
        fileInputStream.close();
        return this;
    }

    public Builder property(String str) {
        int indexOf = str.indexOf(61);
        if (indexOf < 0) {
            indexOf = str.indexOf(58);
        }
        property(str.substring(2, indexOf), str.substring(indexOf + 1));
        return this;
    }

    public Builder property(String str, String str2) {
        if (str.length() > 0 && str2.length() > 0) {
            this.properties.put(str, str2);
        }
        return this;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public String getProperty(String str) {
        return this.properties.getProperty(str);
    }

    public Builder addProperty(String str, String... strArr) {
        StringBuilder sb;
        if (strArr != null && strArr.length > 0) {
            String property = this.properties.getProperty("platform.path.separator");
            String property2 = this.properties.getProperty(str, "");
            for (String str2 : strArr) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(property2);
                if (property2.length() == 0 || property2.endsWith(property)) {
                    sb = new StringBuilder();
                    sb.append(property2);
                } else {
                    sb = new StringBuilder();
                    sb.append(property2);
                    sb.append(property);
                }
                sb.append(str2);
                sb2.append(sb.toString());
                property2 = sb2.toString();
            }
            this.properties.setProperty(str, property2);
        }
        return this;
    }

    public Builder classesOrPackages(String... strArr) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        if (strArr == null) {
            this.classScanner.addPackage((String) null, true);
        } else {
            for (String addClassOrPackage : strArr) {
                this.classScanner.addClassOrPackage(addClassOrPackage);
            }
        }
        return this;
    }

    public Builder buildCommand(String[] strArr) {
        this.buildCommand = strArr;
        return this;
    }

    public Builder workingDirectory(String str) {
        workingDirectory(str == null ? null : new File(str));
        return this;
    }

    public Builder workingDirectory(File file) {
        this.workingDirectory = file;
        return this;
    }

    public Builder environmentVariables(Map<String, String> map) {
        this.environmentVariables = map;
        return this;
    }

    public Builder compilerOptions(String... strArr) {
        if (strArr != null) {
            this.compilerOptions.addAll(Arrays.asList(strArr));
        }
        return this;
    }

    public Builder commandExecutor(CommandExecutor commandExecutor2) {
        this.commandExecutor = commandExecutor2;
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v39, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File[] build() throws java.io.IOException, java.lang.InterruptedException, org.bytedeco.javacpp.tools.ParserException {
        /*
            r26 = this;
            r1 = r26
            java.lang.String[] r0 = r1.buildCommand
            java.lang.String r2 = "Could not load platform properties for "
            java.lang.String r3 = "platform.link"
            java.lang.String r4 = "platform.preload"
            java.lang.String r5 = ""
            r7 = 1
            if (r0 == 0) goto L_0x0232
            int r9 = r0.length
            if (r9 <= 0) goto L_0x0232
            java.util.List r0 = java.util.Arrays.asList(r0)
            java.util.Properties r9 = r1.properties
            java.lang.String r10 = "platform.buildpath"
            java.lang.String r9 = r9.getProperty(r10, r5)
            java.util.Properties r10 = r1.properties
            java.lang.String r11 = "platform.linkresource"
            java.lang.String r10 = r10.getProperty(r11, r5)
            java.util.Properties r11 = r1.properties
            java.lang.String r12 = "platform.buildresource"
            java.lang.String r11 = r11.getProperty(r12, r5)
            java.util.Properties r12 = r1.properties
            java.lang.String r13 = "platform.path.separator"
            java.lang.String r12 = r12.getProperty(r13)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            org.bytedeco.javacpp.tools.ClassScanner r14 = r1.classScanner
            java.util.Collection r14 = r14.getClasses()
            java.util.Iterator r14 = r14.iterator()
            r15 = 0
        L_0x0046:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x008c
            java.lang.Object r16 = r14.next()
            r8 = r16
            java.lang.Class r8 = (java.lang.Class) r8
            java.lang.Class r6 = org.bytedeco.javacpp.Loader.getEnclosingClass(r8)
            if (r6 == r8) goto L_0x005b
            goto L_0x0046
        L_0x005b:
            java.util.Properties r6 = r1.properties
            org.bytedeco.javacpp.ClassProperties r15 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class) r8, (java.util.Properties) r6, (boolean) r7)
            boolean r6 = r15.isLoaded()
            if (r6 != 0) goto L_0x007c
            org.bytedeco.javacpp.tools.Logger r6 = r1.logger
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r2)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.warn(r7)
            goto L_0x008a
        L_0x007c:
            java.util.List r6 = r15.get(r4)
            r13.addAll(r6)
            java.util.List r6 = r15.get(r3)
            r13.addAll(r6)
        L_0x008a:
            r7 = 1
            goto L_0x0046
        L_0x008c:
            if (r15 != 0) goto L_0x0095
            org.bytedeco.javacpp.ClassProperties r15 = new org.bytedeco.javacpp.ClassProperties
            java.util.Properties r2 = r1.properties
            r15.<init>(r2)
        L_0x0095:
            boolean r2 = r1.header
            r1.includeJavaPaths(r15, r2)
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.environmentVariables
            if (r2 != 0) goto L_0x00a5
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r1.environmentVariables = r2
        L_0x00a5:
            java.util.Set r2 = r15.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x00ad:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0113
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = r4.toUpperCase()
            r6 = 46
            r7 = 95
            java.lang.String r4 = r4.replace(r6, r7)
            java.lang.Object r3 = r3.getValue()
            java.util.List r3 = (java.util.List) r3
            java.util.Iterator r3 = r3.iterator()
            r6 = r5
        L_0x00d6:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x010d
            java.lang.Object r7 = r3.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            int r13 = r6.length()
            if (r13 <= 0) goto L_0x0105
            boolean r6 = r6.endsWith(r12)
            if (r6 != 0) goto L_0x0105
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r12)
            r6.append(r7)
            java.lang.String r7 = r6.toString()
        L_0x0105:
            r8.append(r7)
            java.lang.String r6 = r8.toString()
            goto L_0x00d6
        L_0x010d:
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.environmentVariables
            r3.put(r4, r6)
            goto L_0x00ad
        L_0x0113:
            java.lang.String r2 = java.io.File.pathSeparator
            java.lang.String r2 = r9.replace(r12, r2)
            int r3 = r2.length()
            if (r3 > 0) goto L_0x0125
            int r3 = r11.length()
            if (r3 <= 0) goto L_0x020d
        L_0x0125:
            java.lang.String[] r3 = r11.split(r12)
            int r4 = r3.length
            r5 = 0
        L_0x012b:
            if (r5 >= r4) goto L_0x01ec
            r6 = r3[r5]
            java.io.File[] r6 = org.bytedeco.javacpp.Loader.cacheResources(r6)
            int r7 = r6.length
            r8 = 0
        L_0x0135:
            if (r8 >= r7) goto L_0x01e4
            r9 = r6[r8]
            java.lang.String r11 = r9.getCanonicalPath()
            int r13 = r2.length()
            if (r13 <= 0) goto L_0x015c
            java.lang.String r13 = java.io.File.pathSeparator
            boolean r13 = r2.endsWith(r13)
            if (r13 != 0) goto L_0x015c
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r2)
            java.lang.String r2 = java.io.File.pathSeparator
            r13.append(r2)
            java.lang.String r2 = r13.toString()
        L_0x015c:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r2)
            r13.append(r11)
            java.lang.String r2 = r13.toString()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.lang.String[] r14 = r10.split(r12)
            r18 = r2
            int r2 = r14.length
            r19 = r3
            r3 = 0
        L_0x017a:
            if (r3 >= r2) goto L_0x01b1
            r20 = r14[r3]
            r21 = r2
            java.io.File[] r2 = org.bytedeco.javacpp.Loader.cacheResources(r20)
            r20 = r4
            int r4 = r2.length
            r22 = r6
            r6 = 0
        L_0x018a:
            if (r6 >= r4) goto L_0x01a8
            r23 = r2[r6]
            r24 = r2
            java.lang.String r2 = r23.getCanonicalPath()
            boolean r23 = r2.startsWith(r11)
            if (r23 == 0) goto L_0x01a3
            boolean r23 = r2.equals(r11)
            if (r23 != 0) goto L_0x01a3
            r13.add(r2)
        L_0x01a3:
            int r6 = r6 + 1
            r2 = r24
            goto L_0x018a
        L_0x01a8:
            int r3 = r3 + 1
            r4 = r20
            r2 = r21
            r6 = r22
            goto L_0x017a
        L_0x01b1:
            r20 = r4
            r22 = r6
            java.io.File[] r2 = r9.listFiles()
            if (r2 == 0) goto L_0x01d8
            int r3 = r2.length
            r4 = 0
        L_0x01bd:
            if (r4 >= r3) goto L_0x01d8
            r6 = r2[r4]
            java.lang.String r6 = r6.getAbsolutePath()
            int r9 = r13.size()
            java.lang.String[] r9 = new java.lang.String[r9]
            java.lang.Object[] r9 = r13.toArray(r9)
            java.lang.String[] r9 = (java.lang.String[]) r9
            r11 = 0
            org.bytedeco.javacpp.Loader.createLibraryLink(r6, r15, r11, r9)
            int r4 = r4 + 1
            goto L_0x01bd
        L_0x01d8:
            int r8 = r8 + 1
            r2 = r18
            r3 = r19
            r4 = r20
            r6 = r22
            goto L_0x0135
        L_0x01e4:
            r19 = r3
            r20 = r4
            int r5 = r5 + 1
            goto L_0x012b
        L_0x01ec:
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x020d
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.environmentVariables
            if (r3 != 0) goto L_0x01fd
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>()
            r1.environmentVariables = r3
        L_0x01fd:
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.environmentVariables
            java.lang.String r4 = "BUILD_PATH"
            r3.put(r4, r2)
            java.util.Map<java.lang.String, java.lang.String> r2 = r1.environmentVariables
            java.lang.String r3 = java.io.File.pathSeparator
            java.lang.String r4 = "BUILD_PATH_SEPARATOR"
            r2.put(r4, r3)
        L_0x020d:
            org.bytedeco.javacpp.tools.CommandExecutor r2 = r1.commandExecutor
            java.io.File r3 = r1.workingDirectory
            java.util.Map<java.lang.String, java.lang.String> r4 = r1.environmentVariables
            int r0 = r2.executeCommand(r0, r3, r4)
            if (r0 != 0) goto L_0x021b
            r2 = 0
            return r2
        L_0x021b:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Process exited with an error: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0232:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            org.bytedeco.javacpp.tools.ClassScanner r0 = r1.classScanner
            java.util.Collection r0 = r0.getClasses()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x026b
            java.lang.String r0 = r1.outputName
            if (r0 == 0) goto L_0x0269
            java.lang.String r8 = "jnijavacpp"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0269
            java.lang.String r0 = r1.outputName
            r8 = 0
            r9 = 1
            java.io.File[] r0 = r1.generateAndCompile(r8, r0, r9, r9)
            if (r0 == 0) goto L_0x026b
            int r8 = r0.length
            if (r8 <= 0) goto L_0x026b
            java.util.List r0 = java.util.Arrays.asList(r0)
            r6.addAll(r0)
            goto L_0x026b
        L_0x0269:
            r2 = 0
            return r2
        L_0x026b:
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            org.bytedeco.javacpp.tools.ClassScanner r0 = r1.classScanner
            java.util.Collection r0 = r0.getClasses()
            java.util.Iterator r10 = r0.iterator()
        L_0x027f:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x03ca
            java.lang.Object r0 = r10.next()
            r11 = r0
            java.lang.Class r11 = (java.lang.Class) r11
            java.lang.Class r0 = org.bytedeco.javacpp.Loader.getEnclosingClass(r11)
            if (r0 == r11) goto L_0x0293
            goto L_0x027f
        L_0x0293:
            java.util.Properties r0 = r1.properties
            r12 = 0
            org.bytedeco.javacpp.ClassProperties r13 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class) r11, (java.util.Properties) r0, (boolean) r12)
            boolean r0 = r13.isLoaded()
            if (r0 == 0) goto L_0x0335
            java.lang.Class[] r0 = r11.getInterfaces()
            java.util.List r0 = java.util.Arrays.asList(r0)
            java.lang.Class<org.bytedeco.javacpp.tools.BuildEnabled> r12 = org.bytedeco.javacpp.tools.BuildEnabled.class
            boolean r0 = r0.contains(r12)
            if (r0 == 0) goto L_0x02e3
            java.lang.Object r0 = r11.newInstance()     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            org.bytedeco.javacpp.tools.BuildEnabled r0 = (org.bytedeco.javacpp.tools.BuildEnabled) r0     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            org.bytedeco.javacpp.tools.Logger r12 = r1.logger     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            java.util.Properties r14 = r1.properties     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            java.lang.String r15 = r1.encoding     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            r0.init(r12, r14, r15)     // Catch:{ ClassCastException -> 0x02c4, InstantiationException -> 0x02c2, IllegalAccessException -> 0x02c0 }
            goto L_0x02e3
        L_0x02c0:
            r0 = move-exception
            goto L_0x02c5
        L_0x02c2:
            r0 = move-exception
            goto L_0x02c5
        L_0x02c4:
            r0 = move-exception
        L_0x02c5:
            org.bytedeco.javacpp.tools.Logger r12 = r1.logger
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Could not create an instance of "
            r14.append(r15)
            r14.append(r11)
            java.lang.String r15 = ": "
            r14.append(r15)
            r14.append(r0)
            java.lang.String r0 = r14.toString()
            r12.warn(r0)
        L_0x02e3:
            java.lang.String r0 = "global"
            java.lang.String r0 = r13.getProperty(r0)
            if (r0 == 0) goto L_0x0335
            java.lang.String r12 = r11.getName()
            boolean r12 = r12.equals(r0)
            if (r12 != 0) goto L_0x0335
            org.bytedeco.javacpp.tools.ClassScanner r12 = r1.classScanner
            java.util.Collection r12 = r12.getClasses()
            java.util.Iterator r12 = r12.iterator()
            r13 = 0
        L_0x0300:
            boolean r14 = r12.hasNext()
            if (r14 == 0) goto L_0x0316
            java.lang.Object r14 = r12.next()
            java.lang.Class r14 = (java.lang.Class) r14
            java.lang.String r14 = r14.getName()
            boolean r14 = r14.equals(r0)
            r13 = r13 | r14
            goto L_0x0300
        L_0x0316:
            boolean r0 = r1.generate
            if (r0 == 0) goto L_0x031c
            if (r13 != 0) goto L_0x027f
        L_0x031c:
            org.bytedeco.javacpp.tools.ClassScanner r0 = r1.classScanner
            org.bytedeco.javacpp.tools.UserClassLoader r0 = r0.getClassLoader()
            java.lang.String[] r0 = r0.getPaths()
            java.io.File[] r0 = r1.parse(r0, r11)
            if (r0 == 0) goto L_0x027f
            java.util.List r0 = java.util.Arrays.asList(r0)
            r6.addAll(r0)
            goto L_0x027f
        L_0x0335:
            boolean r0 = r13.isLoaded()
            if (r0 != 0) goto L_0x0342
            java.util.Properties r0 = r1.properties
            r12 = 1
            org.bytedeco.javacpp.ClassProperties r13 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class) r11, (java.util.Properties) r0, (boolean) r12)
        L_0x0342:
            boolean r0 = r13.isLoaded()
            if (r0 != 0) goto L_0x035e
            org.bytedeco.javacpp.tools.Logger r0 = r1.logger
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r2)
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            r0.warn(r11)
            goto L_0x027f
        L_0x035e:
            java.lang.String r0 = "platform.executable"
            java.util.List r0 = r13.get(r0)
            java.util.Iterator r11 = r0.iterator()
        L_0x0368:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x038f
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r14 = r8.get(r12)
            java.util.LinkedHashSet r14 = (java.util.LinkedHashSet) r14
            if (r14 != 0) goto L_0x0387
            r7.add(r12)
            java.util.LinkedHashSet r14 = new java.util.LinkedHashSet
            r14.<init>()
            r8.put(r12, r14)
        L_0x0387:
            java.util.List r12 = r13.getEffectiveClasses()
            r14.addAll(r12)
            goto L_0x0368
        L_0x038f:
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0397
            goto L_0x027f
        L_0x0397:
            java.lang.String r0 = r1.outputName
            if (r0 == 0) goto L_0x039c
            goto L_0x03a2
        L_0x039c:
            java.lang.String r0 = "platform.library"
            java.lang.String r0 = r13.getProperty(r0, r5)
        L_0x03a2:
            boolean r11 = r1.generate
            if (r11 == 0) goto L_0x027f
            int r11 = r0.length()
            if (r11 != 0) goto L_0x03ae
            goto L_0x027f
        L_0x03ae:
            java.lang.Object r11 = r9.get(r0)
            java.util.LinkedHashSet r11 = (java.util.LinkedHashSet) r11
            if (r11 != 0) goto L_0x03c1
            r7.add(r0)
            java.util.LinkedHashSet r11 = new java.util.LinkedHashSet
            r11.<init>()
            r9.put(r0, r11)
        L_0x03c1:
            java.util.List r0 = r13.getEffectiveClasses()
            r11.addAll(r0)
            goto L_0x027f
        L_0x03ca:
            java.util.Iterator r0 = r7.iterator()
            r12 = 0
        L_0x03cf:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x077d
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r7 = r8.get(r2)
            java.util.LinkedHashSet r7 = (java.util.LinkedHashSet) r7
            java.lang.Object r10 = r9.get(r2)
            java.util.LinkedHashSet r10 = (java.util.LinkedHashSet) r10
            java.lang.String r11 = "Copying "
            if (r7 == 0) goto L_0x049a
            int r10 = r7.size()
            java.lang.Class[] r10 = new java.lang.Class[r10]
            java.lang.Object[] r7 = r7.toArray(r10)
            java.lang.Class[] r7 = (java.lang.Class[]) r7
            java.util.Properties r10 = r1.properties
            r13 = 1
            org.bytedeco.javacpp.ClassProperties r10 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r7, (java.util.Properties) r10, (boolean) r13)
            java.lang.String r13 = "platform.executable.prefix"
            java.lang.String r13 = r10.getProperty(r13, r5)
            java.lang.String r14 = "platform.executable.suffix"
            java.lang.String r14 = r10.getProperty(r14, r5)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r13)
            r15.append(r2)
            r15.append(r14)
            java.lang.String r13 = r15.toString()
            java.lang.String r14 = "platform.executablepath"
            java.util.List r10 = r10.get(r14)
            java.util.Iterator r10 = r10.iterator()
        L_0x0426:
            boolean r14 = r10.hasNext()
            if (r14 == 0) goto L_0x0494
            java.lang.Object r14 = r10.next()
            java.lang.String r14 = (java.lang.String) r14
            r19 = r0
            r15 = 1
            java.lang.String[] r0 = new java.lang.String[r15]
            r15 = 0
            r0[r15] = r13
            java.nio.file.Path r0 = java.nio.file.Paths.get(r14, r0)
            r20 = r8
            java.nio.file.LinkOption[] r8 = new java.nio.file.LinkOption[r15]
            boolean r8 = java.nio.file.Files.exists(r0, r8)
            if (r8 != 0) goto L_0x0451
            r8 = 1
            java.lang.String[] r0 = new java.lang.String[r8]
            r0[r15] = r2
            java.nio.file.Path r0 = java.nio.file.Paths.get(r14, r0)
        L_0x0451:
            java.nio.file.LinkOption[] r8 = new java.nio.file.LinkOption[r15]
            boolean r8 = java.nio.file.Files.exists(r0, r8)
            if (r8 == 0) goto L_0x048f
            org.bytedeco.javacpp.tools.Logger r8 = r1.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r11)
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r8.info(r10)
            r8 = 0
            java.io.File r10 = r1.getOutputPath(r7, r8)
            java.io.File r8 = new java.io.File
            r8.<init>(r10, r13)
            java.nio.file.Path r8 = r8.toPath()
            r10 = 1
            java.nio.file.CopyOption[] r13 = new java.nio.file.CopyOption[r10]
            java.nio.file.StandardCopyOption r14 = java.nio.file.StandardCopyOption.REPLACE_EXISTING
            r15 = 0
            r13[r15] = r14
            java.nio.file.Files.copy(r0, r8, r13)
            java.io.File[] r0 = new java.io.File[r10]
            java.io.File r8 = r8.toFile()
            r0[r15] = r8
            goto L_0x04c3
        L_0x048f:
            r0 = r19
            r8 = r20
            goto L_0x0426
        L_0x0494:
            r19 = r0
            r20 = r8
            r0 = 0
            goto L_0x04c3
        L_0x049a:
            r19 = r0
            r20 = r8
            if (r10 == 0) goto L_0x0777
            int r0 = r10.size()
            java.lang.Class[] r0 = new java.lang.Class[r0]
            java.lang.Object[] r0 = r10.toArray(r0)
            r7 = r0
            java.lang.Class[] r7 = (java.lang.Class[]) r7
            if (r12 != 0) goto L_0x04b1
            r0 = 1
            goto L_0x04b2
        L_0x04b1:
            r0 = 0
        L_0x04b2:
            int r8 = r9.size()
            r10 = 1
            int r8 = r8 - r10
            if (r12 != r8) goto L_0x04bc
            r8 = 1
            goto L_0x04bd
        L_0x04bc:
            r8 = 0
        L_0x04bd:
            java.io.File[] r0 = r1.generateAndCompile(r7, r2, r0, r8)
            int r12 = r12 + 1
        L_0x04c3:
            if (r0 == 0) goto L_0x068a
            int r8 = r0.length
            if (r8 <= 0) goto L_0x068a
            int r8 = r0.length
            r10 = 0
        L_0x04ca:
            if (r10 >= r8) goto L_0x04d8
            r13 = r0[r10]
            if (r13 == 0) goto L_0x04d5
            java.io.File r8 = r13.getParentFile()
            goto L_0x04d9
        L_0x04d5:
            int r10 = r10 + 1
            goto L_0x04ca
        L_0x04d8:
            r8 = 0
        L_0x04d9:
            java.util.List r10 = java.util.Arrays.asList(r0)
            r6.addAll(r10)
            boolean r10 = r1.copyLibs
            if (r10 == 0) goto L_0x05ef
            java.util.Properties r10 = r1.properties
            r13 = 0
            org.bytedeco.javacpp.ClassProperties r10 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r7, (java.util.Properties) r10, (boolean) r13)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.List r14 = r10.get(r4)
            r13.addAll(r14)
            java.util.List r14 = r10.get(r3)
            r13.addAll(r14)
            java.util.Properties r14 = r1.properties
            r15 = 1
            org.bytedeco.javacpp.ClassProperties r14 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r7, (java.util.Properties) r14, (boolean) r15)
            java.util.Iterator r13 = r13.iterator()
        L_0x0509:
            boolean r15 = r13.hasNext()
            if (r15 == 0) goto L_0x05ef
            java.lang.Object r15 = r13.next()
            java.lang.String r15 = (java.lang.String) r15
            r21 = r3
            java.lang.String r3 = r15.trim()
            r22 = r4
            java.lang.String r4 = "#"
            boolean r3 = r3.endsWith(r4)
            if (r3 != 0) goto L_0x05e1
            java.lang.String r3 = r15.trim()
            int r3 = r3.length()
            if (r3 != 0) goto L_0x0531
            goto L_0x05e1
        L_0x0531:
            r3 = 0
            java.net.URL[] r23 = org.bytedeco.javacpp.Loader.findLibrary(r3, r10, r15)
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0557 }
            r24 = r9
            java.net.URI r9 = new java.net.URI     // Catch:{ Exception -> 0x0559 }
            r17 = 0
            r23 = r23[r17]     // Catch:{ Exception -> 0x0559 }
            java.net.URI r23 = r23.toURI()     // Catch:{ Exception -> 0x0559 }
            r25 = r10
            java.lang.String r10 = r23.toString()     // Catch:{ Exception -> 0x055b }
            java.lang.String[] r10 = r10.split(r4)     // Catch:{ Exception -> 0x055b }
            r10 = r10[r17]     // Catch:{ Exception -> 0x055b }
            r9.<init>(r10)     // Catch:{ Exception -> 0x055b }
            r3.<init>(r9)     // Catch:{ Exception -> 0x055b }
            goto L_0x057d
        L_0x0557:
            r24 = r9
        L_0x0559:
            r25 = r10
        L_0x055b:
            r3 = 0
            java.net.URL[] r9 = org.bytedeco.javacpp.Loader.findLibrary(r3, r14, r15)
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x05ca }
            java.net.URI r3 = new java.net.URI     // Catch:{ Exception -> 0x05ca }
            r17 = 0
            r9 = r9[r17]     // Catch:{ Exception -> 0x05ca }
            java.net.URI r9 = r9.toURI()     // Catch:{ Exception -> 0x05ca }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x05ca }
            java.lang.String[] r4 = r9.split(r4)     // Catch:{ Exception -> 0x05ca }
            r4 = r4[r17]     // Catch:{ Exception -> 0x05ca }
            r3.<init>(r4)     // Catch:{ Exception -> 0x05ca }
            r10.<init>(r3)     // Catch:{ Exception -> 0x05ca }
            r3 = r10
        L_0x057d:
            java.io.File r4 = new java.io.File
            java.lang.String r9 = r3.getName()
            r4.<init>(r8, r9)
            boolean r9 = r3.exists()
            if (r9 == 0) goto L_0x05e5
            boolean r9 = r6.contains(r4)
            if (r9 != 0) goto L_0x05e5
            org.bytedeco.javacpp.tools.Logger r9 = r1.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r11)
            r10.append(r3)
            java.lang.String r10 = r10.toString()
            r9.info(r10)
            java.nio.file.Path r3 = r3.toPath()
            java.nio.file.Path r9 = r4.toPath()
            r10 = 1
            java.nio.file.CopyOption[] r15 = new java.nio.file.CopyOption[r10]
            java.nio.file.StandardCopyOption r18 = java.nio.file.StandardCopyOption.REPLACE_EXISTING
            r17 = 0
            r15[r17] = r18
            java.nio.file.Files.copy(r3, r9, r15)
            r6.add(r4)
            int r3 = r0.length
            int r3 = r3 + r10
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r3)
            java.io.File[] r0 = (java.io.File[]) r0
            int r3 = r0.length
            int r3 = r3 - r10
            r0[r3] = r4
            goto L_0x05e5
        L_0x05ca:
            org.bytedeco.javacpp.tools.Logger r3 = r1.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r9 = "Could not find library "
            r4.append(r9)
            r4.append(r15)
            java.lang.String r4 = r4.toString()
            r3.warn(r4)
            goto L_0x05e5
        L_0x05e1:
            r24 = r9
            r25 = r10
        L_0x05e5:
            r3 = r21
            r4 = r22
            r9 = r24
            r10 = r25
            goto L_0x0509
        L_0x05ef:
            r21 = r3
            r22 = r4
            r24 = r9
            boolean r3 = r1.copyResources
            if (r3 == 0) goto L_0x0685
            java.util.Properties r3 = r1.properties
            r4 = 0
            org.bytedeco.javacpp.ClassProperties r3 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r7, (java.util.Properties) r3, (boolean) r4)
            java.lang.String r4 = "platform.resource"
            java.util.List r3 = r3.get(r4)
            java.util.Properties r4 = r1.properties
            r9 = 1
            org.bytedeco.javacpp.ClassProperties r4 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class[]) r7, (java.util.Properties) r4, (boolean) r9)
            java.lang.String r7 = "platform.resourcepath"
            java.util.List r4 = r4.get(r7)
            java.nio.file.Path r7 = r8.toPath()
            java.util.Iterator r3 = r3.iterator()
        L_0x061b:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x0685
            java.lang.Object r8 = r3.next()
            java.lang.String r8 = (java.lang.String) r8
            java.nio.file.Path r9 = r7.resolve(r8)
            r10 = 0
            java.nio.file.LinkOption[] r13 = new java.nio.file.LinkOption[r10]
            boolean r13 = java.nio.file.Files.exists(r9, r13)
            if (r13 != 0) goto L_0x0639
            java.nio.file.attribute.FileAttribute[] r13 = new java.nio.file.attribute.FileAttribute[r10]
            java.nio.file.Files.createDirectories(r9, r13)
        L_0x0639:
            java.util.Iterator r13 = r4.iterator()
        L_0x063d:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x061b
            java.lang.Object r14 = r13.next()
            java.lang.String r14 = (java.lang.String) r14
            r18 = r0
            r15 = 1
            java.lang.String[] r0 = new java.lang.String[r15]
            r0[r10] = r8
            java.nio.file.Path r0 = java.nio.file.Paths.get(r14, r0)
            java.nio.file.LinkOption[] r14 = new java.nio.file.LinkOption[r10]
            boolean r10 = java.nio.file.Files.exists(r0, r14)
            if (r10 == 0) goto L_0x0681
            org.bytedeco.javacpp.tools.Logger r10 = r1.logger
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r11)
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            r10.info(r14)
            java.nio.file.FileVisitOption r10 = java.nio.file.FileVisitOption.FOLLOW_LINKS
            java.util.EnumSet r10 = java.util.EnumSet.of(r10)
            r14 = 2147483647(0x7fffffff, float:NaN)
            org.bytedeco.javacpp.tools.Builder$3 r15 = new org.bytedeco.javacpp.tools.Builder$3
            r15.<init>(r9, r0)
            java.nio.file.Files.walkFileTree(r0, r10, r14, r15)
        L_0x0681:
            r0 = r18
            r10 = 0
            goto L_0x063d
        L_0x0685:
            r18 = r0
            r0 = r18
            goto L_0x0690
        L_0x068a:
            r21 = r3
            r22 = r4
            r24 = r9
        L_0x0690:
            java.io.File r3 = r1.configDirectory
            if (r3 == 0) goto L_0x076a
            java.io.File r3 = new java.io.File
            java.io.File r4 = r1.configDirectory
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r2)
            java.lang.String r2 = "/resource-config.json"
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r3.<init>(r4, r2)
            java.io.File r2 = r3.getParentFile()
            if (r2 == 0) goto L_0x06b5
            r2.mkdirs()
        L_0x06b5:
            org.bytedeco.javacpp.tools.Logger r2 = r1.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "Generating "
            r4.append(r7)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            r2.info(r4)
            java.lang.String r2 = r1.encoding
            if (r2 == 0) goto L_0x06d7
            java.io.PrintWriter r2 = new java.io.PrintWriter
            java.lang.String r4 = r1.encoding
            r2.<init>(r3, r4)
            goto L_0x06dc
        L_0x06d7:
            java.io.PrintWriter r2 = new java.io.PrintWriter
            r2.<init>(r3)
        L_0x06dc:
            java.lang.String r4 = "{"
            r2.println(r4)     // Catch:{ all -> 0x075b }
            java.lang.String r4 = "  \"resources\": ["
            r2.println(r4)     // Catch:{ all -> 0x075b }
            java.lang.String r4 = "    {\"pattern\": \"META-INF/.*\"},"
            r2.println(r4)     // Catch:{ all -> 0x075b }
            java.lang.String r4 = "    {\"pattern\": \"org/bytedeco/javacpp/properties/.*\"}"
            r2.print(r4)     // Catch:{ all -> 0x075b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x075b }
            r4.<init>()     // Catch:{ all -> 0x075b }
            java.lang.String r7 = ","
            r4.append(r7)     // Catch:{ all -> 0x075b }
            java.lang.String r7 = java.lang.System.lineSeparator()     // Catch:{ all -> 0x075b }
            r4.append(r7)     // Catch:{ all -> 0x075b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x075b }
            if (r0 == 0) goto L_0x0709
            r7 = 0
            goto L_0x070c
        L_0x0709:
            r7 = 0
            java.io.File[] r0 = new java.io.File[r7]     // Catch:{ all -> 0x075b }
        L_0x070c:
            int r8 = r0.length     // Catch:{ all -> 0x075b }
            r9 = 0
        L_0x070e:
            if (r9 >= r8) goto L_0x0747
            r10 = r0[r9]     // Catch:{ all -> 0x075b }
            if (r10 == 0) goto L_0x0744
            java.nio.file.Path r11 = r10.toPath()     // Catch:{ all -> 0x075b }
            java.io.File r13 = r1.configDirectory     // Catch:{ all -> 0x075b }
            java.nio.file.Path r13 = r13.toPath()     // Catch:{ all -> 0x075b }
            boolean r11 = r11.startsWith(r13)     // Catch:{ all -> 0x075b }
            if (r11 != 0) goto L_0x0744
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x075b }
            r11.<init>()     // Catch:{ all -> 0x075b }
            r11.append(r4)     // Catch:{ all -> 0x075b }
            java.lang.String r13 = "    {\"pattern\": \".*/"
            r11.append(r13)     // Catch:{ all -> 0x075b }
            java.lang.String r10 = r10.getName()     // Catch:{ all -> 0x075b }
            r11.append(r10)     // Catch:{ all -> 0x075b }
            java.lang.String r10 = "\"}"
            r11.append(r10)     // Catch:{ all -> 0x075b }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x075b }
            r2.print(r10)     // Catch:{ all -> 0x075b }
        L_0x0744:
            int r9 = r9 + 1
            goto L_0x070e
        L_0x0747:
            r2.println()     // Catch:{ all -> 0x075b }
            java.lang.String r0 = "  ]"
            r2.println(r0)     // Catch:{ all -> 0x075b }
            java.lang.String r0 = "}"
            r2.println(r0)     // Catch:{ all -> 0x075b }
            r2.close()
            r6.add(r3)
            goto L_0x076b
        L_0x075b:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch:{ all -> 0x075e }
        L_0x075e:
            r0 = move-exception
            r4 = r0
            r2.close()     // Catch:{ all -> 0x0764 }
            goto L_0x0769
        L_0x0764:
            r0 = move-exception
            r2 = r0
            r3.addSuppressed(r2)
        L_0x0769:
            throw r4
        L_0x076a:
            r7 = 0
        L_0x076b:
            r0 = r19
            r8 = r20
            r3 = r21
            r4 = r22
            r9 = r24
            goto L_0x03cf
        L_0x0777:
            r0 = r19
            r8 = r20
            goto L_0x03cf
        L_0x077d:
            int r0 = r6.size()
            java.io.File[] r0 = new java.io.File[r0]
            java.lang.Object[] r0 = r6.toArray(r0)
            java.io.File[] r0 = (java.io.File[]) r0
            java.lang.String r2 = r1.jarPrefix
            if (r2 == 0) goto L_0x07e5
            int r2 = r0.length
            if (r2 <= 0) goto L_0x07e5
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r1.jarPrefix
            r3.append(r4)
            java.lang.String r4 = "-"
            r3.append(r4)
            java.util.Properties r4 = r1.properties
            java.lang.String r6 = "platform"
            java.lang.String r4 = r4.getProperty(r6)
            r3.append(r4)
            java.util.Properties r4 = r1.properties
            java.lang.String r6 = "platform.extension"
            java.lang.String r4 = r4.getProperty(r6, r5)
            r3.append(r4)
            java.lang.String r4 = ".jar"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            java.io.File r3 = r2.getParentFile()
            if (r3 == 0) goto L_0x07d2
            boolean r4 = r3.exists()
            if (r4 != 0) goto L_0x07d2
            r3.mkdir()
        L_0x07d2:
            java.io.File r3 = r1.outputDirectory
            if (r3 != 0) goto L_0x07e1
            org.bytedeco.javacpp.tools.ClassScanner r3 = r1.classScanner
            org.bytedeco.javacpp.tools.UserClassLoader r3 = r3.getClassLoader()
            java.lang.String[] r6 = r3.getPaths()
            goto L_0x07e2
        L_0x07e1:
            r6 = 0
        L_0x07e2:
            r1.createJar(r2, r6, r0)
        L_0x07e5:
            java.lang.String r2 = "org.bytedeco.javacpp.loadlibraries"
            java.lang.String r3 = "true"
            java.lang.System.setProperty(r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Builder.build():java.io.File[]");
    }

    public static void printHelp() {
        String implementationVersion = Builder.class.getPackage().getImplementationVersion();
        if (implementationVersion == null) {
            implementationVersion = "unknown";
        }
        PrintStream printStream = System.out;
        printStream.println("JavaCPP version " + implementationVersion + "\nCopyright (C) 2011-2020 Samuel Audet <samuel.audet@gmail.com>\nProject site: https://github.com/bytedeco/javacpp");
        System.out.println();
        System.out.println("Usage: java -jar javacpp.jar [options] [class or package (suffixed with .* or .**)] [commands]");
        System.out.println();
        System.out.println("where options include:");
        System.out.println();
        System.out.println("    -classpath <path>      Load user classes from path");
        System.out.println("    -encoding <name>       Character encoding used for input and output files");
        System.out.println("    -d <directory>         Output all generated files to directory");
        System.out.println("    -o <name>              Output everything in a file named after given name");
        System.out.println("    -clean                 Delete the output directory before generating anything in it");
        System.out.println("    -nogenerate            Do not try to generate C++ source files, only try to parse header files");
        System.out.println("    -nocompile             Do not compile or delete the generated C++ source files");
        System.out.println("    -nodelete              Do not delete generated C++ JNI files after compilation");
        System.out.println("    -header                Generate header file with declarations of callbacks functions");
        System.out.println("    -copylibs              Copy to output directory dependent libraries (link and preload)");
        System.out.println("    -copyresources         Copy to output directory resources listed in properties");
        System.out.println("    -configdir <directory> Also create config files for GraalVM native-image in directory");
        System.out.println("    -jarprefix <prefix>    Also create a JAR file named \"<prefix>-<platform>.jar\"");
        System.out.println("    -properties <resource> Load all platform properties from resource");
        System.out.println("    -propertyfile <file>   Load all platform properties from file");
        System.out.println("    -D<property>=<value>   Set platform property to value");
        System.out.println("    -Xcompiler <option>    Pass option directly to compiler");
        System.out.println();
        System.out.println("and where optional commands include:");
        System.out.println();
        System.out.println("    -mod <file>            Output a module-info.java file for native JAR where module name is the package of the first class");
        System.out.println("    -exec [args...]        After build, call java command on the first class");
        System.out.println("    -print <property>      Print the given platform property, for example, \"platform.includepath\", and exit");
        System.out.println("                           \"platform.includepath\" has jni.h, jni_md.h, etc, and \"platform.linkpath\", the jvm library");
        System.out.println();
    }

    public static void main(String[] strArr) throws Exception {
        String[] strArr2 = strArr;
        Builder builder = new Builder();
        String str = null;
        String str2 = null;
        String[] strArr3 = null;
        int i = 0;
        boolean z = false;
        while (i < strArr2.length) {
            if ("-help".equals(strArr2[i]) || "--help".equals(strArr2[i])) {
                printHelp();
                System.exit(0);
            } else if ("-classpath".equals(strArr2[i]) || "-cp".equals(strArr2[i]) || "-lib".equals(strArr2[i])) {
                i++;
                builder.classPaths(strArr2[i]);
            } else if ("-encoding".equals(strArr2[i])) {
                i++;
                builder.encoding(strArr2[i]);
            } else if ("-d".equals(strArr2[i])) {
                i++;
                builder.outputDirectory(strArr2[i]);
            } else if ("-o".equals(strArr2[i])) {
                i++;
                builder.outputName(strArr2[i]);
            } else if ("-clean".equals(strArr2[i])) {
                builder.clean(true);
            } else if ("-nocpp".equals(strArr2[i]) || "-nogenerate".equals(strArr2[i])) {
                builder.generate(false);
            } else if ("-cpp".equals(strArr2[i]) || "-nocompile".equals(strArr2[i])) {
                builder.compile(false);
            } else if ("-nodelete".equals(strArr2[i])) {
                builder.deleteJniFiles(false);
            } else if ("-header".equals(strArr2[i])) {
                builder.header(true);
            } else if ("-copylibs".equals(strArr2[i])) {
                builder.copyLibs(true);
            } else if ("-copyresources".equals(strArr2[i])) {
                builder.copyResources(true);
            } else if ("-configdir".equals(strArr2[i])) {
                i++;
                builder.configDirectory(strArr2[i]);
            } else if ("-jarprefix".equals(strArr2[i])) {
                i++;
                builder.jarPrefix(strArr2[i]);
            } else if ("-properties".equals(strArr2[i])) {
                i++;
                builder.properties(strArr2[i]);
            } else if ("-propertyfile".equals(strArr2[i])) {
                i++;
                builder.propertyFile(strArr2[i]);
            } else if (strArr2[i].startsWith("-D")) {
                builder.property(strArr2[i]);
            } else if ("-Xcompiler".equals(strArr2[i])) {
                i++;
                builder.compilerOptions(strArr2[i]);
            } else if ("-mod".equals(strArr2[i])) {
                i++;
                str2 = strArr2[i];
            } else if ("-exec".equals(strArr2[i])) {
                strArr3 = (String[]) Arrays.copyOfRange(strArr2, i + 1, strArr2.length);
                i = strArr2.length;
            } else if ("-print".equals(strArr2[i])) {
                i++;
                str = strArr2[i];
            } else if (strArr2[i].startsWith("-")) {
                builder.logger.error("Invalid option \"" + strArr2[i] + "\"");
                printHelp();
                System.exit(1);
            } else {
                String str3 = strArr2[i];
                if (str3.endsWith(".java")) {
                    ArrayList arrayList = new ArrayList(Arrays.asList(new String[]{"javac", "-cp"}));
                    String property = System.getProperty("java.class.path");
                    for (String str4 : builder.classScanner.getClassLoader().getPaths()) {
                        property = property + File.pathSeparator + str4;
                    }
                    arrayList.add(property);
                    arrayList.add(str3);
                    int executeCommand = builder.commandExecutor.executeCommand(arrayList, builder.workingDirectory, builder.environmentVariables);
                    if (executeCommand == 0) {
                        str3 = str3.replace(File.separatorChar, '.').replace('/', '.').substring(0, str3.length() - 5);
                    } else {
                        throw new RuntimeException("Could not compile " + str3 + ": " + executeCommand);
                    }
                }
                builder.classesOrPackages(str3);
                z = true;
            }
            i++;
        }
        if (str != null) {
            Collection<Class> classes = builder.classScanner.getClasses();
            ClassProperties loadProperties = Loader.loadProperties((Class[]) classes.toArray(new Class[classes.size()]), builder.properties, true);
            builder.includeJavaPaths(loadProperties, builder.header);
            for (String println : loadProperties.get(str)) {
                System.out.println(println);
            }
            System.exit(0);
        } else if (!z) {
            printHelp();
            System.exit(2);
        }
        File[] build = builder.build();
        Collection<Class> classes2 = builder.classScanner.getClasses();
        if (str2 != null) {
            String name = classes2.iterator().next().getPackage().getName();
            String str5 = "open module " + name + "." + builder.properties.getProperty("platform").replace('-', '.') + " {\n  requires transitive " + name + ";\n}\n";
            Path path = Paths.get(str2, new String[0]);
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent, new FileAttribute[0]);
            }
            Files.write(path, str5.getBytes(), new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING});
        }
        if (build != null && build.length > 0 && !classes2.isEmpty() && strArr3 != null) {
            Class next = classes2.iterator().next();
            ArrayList arrayList2 = new ArrayList(Arrays.asList(new String[]{"java", "-cp"}));
            String property2 = System.getProperty("java.class.path");
            for (String str6 : builder.classScanner.getClassLoader().getPaths()) {
                property2 = property2 + File.pathSeparator + str6;
            }
            arrayList2.add(property2);
            arrayList2.add(next.getCanonicalName());
            arrayList2.addAll(Arrays.asList(strArr3));
            System.exit(builder.commandExecutor.executeCommand(arrayList2, builder.workingDirectory, builder.environmentVariables));
        }
    }
}
