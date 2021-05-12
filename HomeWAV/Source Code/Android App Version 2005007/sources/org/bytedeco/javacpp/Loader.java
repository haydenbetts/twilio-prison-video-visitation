package org.bytedeco.javacpp;

import com.microsoft.appcenter.Constants;
import com.urbanairship.channel.ChannelRegistrationPayload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.Raw;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class Loader {
    private static final String PLATFORM = Detector.getPlatform();
    static File cacheDir = null;
    private static final ThreadLocal<Deque<Class<?>>> classStack = new ThreadLocal<Deque<Class<?>>>() {
        /* access modifiers changed from: protected */
        public Deque<Class<?>> initialValue() {
            return new ArrayDeque();
        }
    };
    static Map<String, URL[]> foundLibraries = new HashMap();
    static Map<String, String> loadedLibraries = new HashMap();
    private static final Logger logger = Logger.create(Loader.class);
    static WeakHashMap<Class<? extends Pointer>, HashMap<String, Integer>> memberOffsets = new WeakHashMap<>();
    static boolean pathsFirst;
    private static java.util.Properties platformProperties = null;
    static File tempDir = null;

    @Name({"JavaCPP_addressof"})
    public static native Pointer addressof(String str);

    @Cast({"JavaVM*"})
    @Name({"JavaCPP_getJavaVM"})
    public static native Pointer getJavaVM();

    @Raw(withEnv = true)
    @Name({"JavaCPP_loadGlobal"})
    public static native void loadGlobal(String str);

    @Name({"JavaCPP_totalChips"})
    public static native int totalChips();

    @Name({"JavaCPP_totalCores"})
    public static native int totalCores();

    @Name({"JavaCPP_totalProcessors"})
    public static native int totalProcessors();

    static {
        boolean z = false;
        pathsFirst = false;
        String lowerCase = System.getProperty("org.bytedeco.javacpp.pathsFirst", System.getProperty("org.bytedeco.javacpp.pathsfirst", "false").toLowerCase()).toLowerCase();
        if (lowerCase.equals("true") || lowerCase.equals("t") || lowerCase.equals("")) {
            z = true;
        }
        pathsFirst = z;
        try {
            load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load Loader: " + th);
        }
    }

    public static class Detector {
        public static String getPlatform() {
            String lowerCase = System.getProperty("java.vm.name", "").toLowerCase();
            String lowerCase2 = System.getProperty("os.name", "").toLowerCase();
            String lowerCase3 = System.getProperty("os.arch", "").toLowerCase();
            String lowerCase4 = System.getProperty("sun.arch.abi", "").toLowerCase();
            String lowerCase5 = System.getProperty("sun.boot.library.path", "").toLowerCase();
            String str = "arm";
            if (lowerCase.startsWith("dalvik") && lowerCase2.startsWith("linux")) {
                lowerCase2 = ChannelRegistrationPayload.ANDROID_DEVICE_TYPE;
            } else if (lowerCase.startsWith("robovm") && lowerCase2.startsWith("darwin")) {
                lowerCase2 = "ios";
                lowerCase3 = str;
            } else if (lowerCase2.startsWith("mac os x") || lowerCase2.startsWith("darwin")) {
                lowerCase2 = "macosx";
            } else {
                int indexOf = lowerCase2.indexOf(32);
                if (indexOf > 0) {
                    lowerCase2 = lowerCase2.substring(0, indexOf);
                }
            }
            if (lowerCase3.equals("i386") || lowerCase3.equals("i486") || lowerCase3.equals("i586") || lowerCase3.equals("i686")) {
                str = "x86";
            } else if (lowerCase3.equals("amd64") || lowerCase3.equals("x86-64") || lowerCase3.equals("x64")) {
                str = "x86_64";
            } else if (lowerCase3.startsWith("aarch64") || lowerCase3.startsWith("armv8") || lowerCase3.startsWith("arm64")) {
                str = "arm64";
            } else if (lowerCase3.startsWith(str) && (lowerCase4.equals("gnueabihf") || lowerCase5.contains("openjdk-armhf"))) {
                str = "armhf";
            } else if (!lowerCase3.startsWith(str)) {
                str = lowerCase3;
            }
            return System.getProperty("org.bytedeco.javacpp.platform", lowerCase2 + "-" + str);
        }
    }

    public static String getPlatform() {
        return PLATFORM;
    }

    public static java.util.Properties loadProperties() {
        String platform = getPlatform();
        java.util.Properties properties = platformProperties;
        if (properties != null && platform.equals(properties.getProperty("platform"))) {
            return platformProperties;
        }
        java.util.Properties loadProperties = loadProperties(platform, (String) null);
        platformProperties = loadProperties;
        return loadProperties;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(1:2)|3|4|5|7|8|(2:10|11)|48|(4:51|(2:55|(2:60|75))|68|49)|69|61|(1:(5:14|48|(1:49)|69|61))) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:15|16|17|18|20|21|(2:23|24)|(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r7 = r0.getResourceAsStream("properties/" + r8 + ".properties");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.load(new java.io.InputStreamReader(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a4, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.load(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a9, code lost:
        if (r7 != null) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00af, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r8 = logger;
        r7 = "Unable to close resource : " + r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c5, code lost:
        r8.error(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c9, code lost:
        if (r7 != null) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00cf, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        logger.error("Unable to close resource : " + r7.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e8, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e9, code lost:
        if (r7 != null) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ef, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r8 = logger;
        r7 = "Unable to close resource : " + r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0106, code lost:
        if (r3 != null) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x010c, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010d, code lost:
        r8 = logger;
        r0 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0165, code lost:
        if (r3 != null) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x016b, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016c, code lost:
        logger.error("Unable to close resource : " + r8.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0184, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x005d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0085 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x00a6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0060 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0108 A[SYNTHETIC, Splitter:B:44:0x0108] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0128  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties loadProperties(java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.Class<org.bytedeco.javacpp.Loader> r0 = org.bytedeco.javacpp.Loader.class
            java.lang.String r1 = "Unable to close resource : "
            if (r8 != 0) goto L_0x0008
            java.lang.String r8 = "generic"
        L_0x0008:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            java.lang.String r3 = "platform"
            r2.put(r3, r7)
            java.lang.String r3 = java.io.File.pathSeparator
            java.lang.String r4 = "platform.path.separator"
            r2.put(r4, r3)
            java.lang.String r3 = "/"
            java.lang.String r3 = java.lang.System.mapLibraryName(r3)
            r4 = 47
            int r4 = r3.indexOf(r4)
            r5 = 0
            java.lang.String r5 = r3.substring(r5, r4)
            java.lang.String r6 = "platform.library.prefix"
            r2.put(r6, r5)
            int r4 = r4 + 1
            java.lang.String r3 = r3.substring(r4)
            java.lang.String r4 = "platform.library.suffix"
            r2.put(r4, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "properties/"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r7 = ".properties"
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            java.io.InputStream r3 = r0.getResourceAsStream(r3)
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ NoSuchMethodError -> 0x0060 }
            r5.<init>(r3)     // Catch:{ NoSuchMethodError -> 0x0060 }
            r2.load(r5)     // Catch:{ NoSuchMethodError -> 0x0060 }
            goto L_0x0063
        L_0x005d:
            r7 = move-exception
            goto L_0x0165
        L_0x0060:
            r2.load(r3)     // Catch:{ Exception -> 0x0085 }
        L_0x0063:
            if (r3 == 0) goto L_0x0116
            r3.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x0116
        L_0x006a:
            r7 = move-exception
            org.bytedeco.javacpp.tools.Logger r8 = logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x0072:
            r0.append(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.error(r7)
            goto L_0x0116
        L_0x0085:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r5.<init>()     // Catch:{ all -> 0x005d }
            r5.append(r4)     // Catch:{ all -> 0x005d }
            r5.append(r8)     // Catch:{ all -> 0x005d }
            r5.append(r7)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x005d }
            java.io.InputStream r7 = r0.getResourceAsStream(r7)     // Catch:{ all -> 0x005d }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ NoSuchMethodError -> 0x00a6 }
            r8.<init>(r7)     // Catch:{ NoSuchMethodError -> 0x00a6 }
            r2.load(r8)     // Catch:{ NoSuchMethodError -> 0x00a6 }
            goto L_0x00a9
        L_0x00a4:
            r8 = move-exception
            goto L_0x00c9
        L_0x00a6:
            r2.load(r7)     // Catch:{ Exception -> 0x00e9, all -> 0x00a4 }
        L_0x00a9:
            if (r7 == 0) goto L_0x0106
            r7.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x0106
        L_0x00af:
            r7 = move-exception
            org.bytedeco.javacpp.tools.Logger r8 = logger     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r0.<init>()     // Catch:{ all -> 0x005d }
            r0.append(r1)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x005d }
            r0.append(r7)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x005d }
        L_0x00c5:
            r8.error(r7)     // Catch:{ all -> 0x005d }
            goto L_0x0106
        L_0x00c9:
            if (r7 == 0) goto L_0x00e8
            r7.close()     // Catch:{ IOException -> 0x00cf }
            goto L_0x00e8
        L_0x00cf:
            r7 = move-exception
            org.bytedeco.javacpp.tools.Logger r0 = logger     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r2.<init>()     // Catch:{ all -> 0x005d }
            r2.append(r1)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x005d }
            r2.append(r7)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x005d }
            r0.error(r7)     // Catch:{ all -> 0x005d }
        L_0x00e8:
            throw r8     // Catch:{ all -> 0x005d }
        L_0x00e9:
            if (r7 == 0) goto L_0x0106
            r7.close()     // Catch:{ IOException -> 0x00ef }
            goto L_0x0106
        L_0x00ef:
            r7 = move-exception
            org.bytedeco.javacpp.tools.Logger r8 = logger     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r0.<init>()     // Catch:{ all -> 0x005d }
            r0.append(r1)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x005d }
            r0.append(r7)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x005d }
            goto L_0x00c5
        L_0x0106:
            if (r3 == 0) goto L_0x0116
            r3.close()     // Catch:{ IOException -> 0x010c }
            goto L_0x0116
        L_0x010c:
            r7 = move-exception
            org.bytedeco.javacpp.tools.Logger r8 = logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x0072
        L_0x0116:
            java.util.Properties r7 = java.lang.System.getProperties()
            java.util.Set r7 = r7.entrySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x0122:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0164
            java.lang.Object r8 = r7.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            java.lang.Object r0 = r8.getKey()
            boolean r0 = r0 instanceof java.lang.String
            if (r0 == 0) goto L_0x0122
            java.lang.Object r0 = r8.getValue()
            boolean r0 = r0 instanceof java.lang.String
            if (r0 == 0) goto L_0x0122
            java.lang.Object r0 = r8.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r8 = r8.getValue()
            java.lang.String r8 = (java.lang.String) r8
            if (r0 == 0) goto L_0x0122
            if (r8 == 0) goto L_0x0122
            java.lang.String r1 = "org.bytedeco.javacpp.platform."
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x0122
            java.lang.String r1 = "platform."
            int r1 = r0.indexOf(r1)
            java.lang.String r0 = r0.substring(r1)
            r2.put(r0, r8)
            goto L_0x0122
        L_0x0164:
            return r2
        L_0x0165:
            if (r3 == 0) goto L_0x0184
            r3.close()     // Catch:{ IOException -> 0x016b }
            goto L_0x0184
        L_0x016b:
            r8 = move-exception
            org.bytedeco.javacpp.tools.Logger r0 = logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r8 = r8.getMessage()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            r0.error(r8)
        L_0x0184:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.loadProperties(java.lang.String, java.lang.String):java.util.Properties");
    }

    public static boolean checkVersion(String str, String str2) {
        return checkVersion(str, str2, "-", true, getCallerClass(2));
    }

    public static boolean checkVersion(String str, String str2, String str3, boolean z, Class cls) {
        try {
            String version = getVersion();
            String version2 = getVersion(str, str2, cls);
            if (version2 == null) {
                if (z) {
                    if (isLoadLibraries()) {
                        Logger logger2 = logger;
                        logger2.warn("Version of " + str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str2 + " could not be found.");
                    }
                }
                return false;
            }
            String[] split = version.split(str3);
            String[] split2 = version2.split(str3);
            int length = split2.length;
            int i = 1;
            if (split2[split2.length - 1].equals("SNAPSHOT")) {
                i = 2;
            }
            boolean equals = split2[length - i].equals(split[0]);
            if (!equals && z && isLoadLibraries()) {
                Logger logger3 = logger;
                logger3.warn("Versions of org.bytedeco:javacpp:" + version + " and " + str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str2 + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + version2 + " do not match.");
            }
            return equals;
        } catch (Exception e) {
            if (z && isLoadLibraries()) {
                Logger logger4 = logger;
                logger4.warn("Unable to load properties : " + e.getMessage());
            }
            return false;
        }
    }

    public static String getVersion() throws IOException {
        return getVersion("org.bytedeco", "javacpp");
    }

    public static String getVersion(String str, String str2) throws IOException {
        return getVersion(str, str2, getCallerClass(2));
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getVersion(java.lang.String r4, java.lang.String r5, java.lang.Class r6) throws java.io.IOException {
        /*
            java.lang.String r0 = "Unable to close resource : "
            java.util.Properties r1 = new java.util.Properties
            r1.<init>()
            java.lang.ClassLoader r6 = r6.getClassLoader()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "META-INF/maven/"
            r2.append(r3)
            r2.append(r4)
            java.lang.String r4 = "/"
            r2.append(r4)
            r2.append(r5)
            java.lang.String r4 = "/pom.properties"
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            java.io.InputStream r4 = r6.getResourceAsStream(r4)
            if (r4 != 0) goto L_0x0031
            r4 = 0
            return r4
        L_0x0031:
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ NoSuchMethodError -> 0x003c }
            r5.<init>(r4)     // Catch:{ NoSuchMethodError -> 0x003c }
            r1.load(r5)     // Catch:{ NoSuchMethodError -> 0x003c }
            goto L_0x003f
        L_0x003a:
            r5 = move-exception
            goto L_0x0065
        L_0x003c:
            r1.load(r4)     // Catch:{ all -> 0x003a }
        L_0x003f:
            java.lang.String r5 = "version"
            java.lang.String r5 = r1.getProperty(r5)     // Catch:{ all -> 0x003a }
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0064
        L_0x004b:
            r4 = move-exception
            org.bytedeco.javacpp.tools.Logger r6 = logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r4 = r4.getMessage()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r6.error(r4)
        L_0x0064:
            return r5
        L_0x0065:
            if (r4 == 0) goto L_0x0084
            r4.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0084
        L_0x006b:
            r4 = move-exception
            org.bytedeco.javacpp.tools.Logger r6 = logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r4 = r4.getMessage()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r6.error(r4)
        L_0x0084:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.getVersion(java.lang.String, java.lang.String, java.lang.Class):java.lang.String");
    }

    public static Class getEnclosingClass(Class<?> cls) {
        while (cls.getEnclosingClass() != null && !cls.isAnnotationPresent(Properties.class)) {
            if (cls.isAnnotationPresent(Platform.class)) {
                Platform platform = (Platform) cls.getAnnotation(Platform.class);
                if (platform.pragma().length > 0 || platform.define().length > 0 || platform.exclude().length > 0 || platform.include().length > 0 || platform.cinclude().length > 0 || platform.includepath().length > 0 || platform.includeresource().length > 0 || platform.compiler().length > 0 || platform.linkpath().length > 0 || platform.linkresource().length > 0 || platform.link().length > 0 || platform.frameworkpath().length > 0 || platform.framework().length > 0 || platform.preloadresource().length > 0 || platform.preloadpath().length > 0 || platform.preload().length > 0 || platform.resourcepath().length > 0 || platform.resource().length > 0 || platform.library().length() > 0) {
                    break;
                }
            }
            cls = cls.getEnclosingClass();
        }
        return cls;
    }

    public static ClassProperties loadProperties(Class[] clsArr, java.util.Properties properties, boolean z) {
        ClassProperties classProperties = new ClassProperties(properties);
        if (clsArr != null) {
            for (Class load : clsArr) {
                classProperties.load(load, z);
            }
        }
        return classProperties;
    }

    public static ClassProperties loadProperties(Class cls, java.util.Properties properties, boolean z) {
        ClassProperties classProperties = new ClassProperties(properties);
        if (cls != null) {
            classProperties.load(cls, z);
        }
        return classProperties;
    }

    public static Class getCallerClass(int i) {
        Class<Loader>[] clsArr;
        Class<Loader> cls = Loader.class;
        try {
            clsArr = new SecurityManager() {
                public Class[] getClassContext() {
                    return super.getClassContext();
                }
            }.getClassContext();
        } catch (NoSuchMethodError | SecurityException e) {
            Logger logger2 = logger;
            logger2.warn("Could not create an instance of SecurityManager: " + e.getMessage());
            clsArr = null;
        }
        int i2 = 0;
        if (clsArr != null) {
            while (i2 < clsArr.length) {
                if (clsArr[i2] == cls) {
                    return clsArr[i + i2];
                }
                i2++;
            }
        } else {
            try {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                while (i2 < stackTrace.length) {
                    if (Class.forName(stackTrace[i2].getClassName()) == cls) {
                        return Class.forName(stackTrace[i + i2].getClassName());
                    }
                    i2++;
                }
            } catch (ClassNotFoundException e2) {
                Logger logger3 = logger;
                logger3.error("No definition for the class found : " + e2.getMessage());
            }
        }
        return null;
    }

    public static File cacheResource(String str) throws IOException {
        return cacheResource(getCallerClass(2), str);
    }

    public static File cacheResource(Class cls, String str) throws IOException {
        URL findResource = findResource(cls, str);
        if (findResource != null) {
            return cacheResource(findResource);
        }
        return null;
    }

    public static File[] cacheResources(String str) throws IOException {
        return cacheResources(getCallerClass(2), str);
    }

    public static File[] cacheResources(Class cls, String str) throws IOException {
        URL[] findResources = findResources(cls, str);
        File[] fileArr = new File[findResources.length];
        for (int i = 0; i < findResources.length; i++) {
            fileArr[i] = cacheResource(findResources[i]);
        }
        return fileArr;
    }

    public static File cacheResource(URL url) throws IOException {
        return cacheResource(url, (String) null);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:199:0x03b1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0110 */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02d6  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x03de A[SYNTHETIC, Splitter:B:214:0x03de] */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x03e3 A[Catch:{ all -> 0x0422, all -> 0x042b }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01b5  */
    public static java.io.File cacheResource(java.net.URL r19, java.lang.String r20) throws java.io.IOException {
        /*
            r0 = r20
            java.lang.String r1 = r19.toString()
            java.lang.String r2 = "#"
            java.lang.String[] r1 = r1.split(r2)
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0021 }
            r4 = r1[r2]     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0021 }
            r3.<init>(r4)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0021 }
            java.io.File r4 = new java.io.File     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0023 }
            java.net.URI r5 = new java.net.URI     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0023 }
            r6 = r1[r2]     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0023 }
            r5.<init>(r6)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0023 }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0023 }
            goto L_0x002c
        L_0x0021:
            r3 = r19
        L_0x0023:
            java.io.File r4 = new java.io.File
            java.lang.String r5 = r3.getPath()
            r4.<init>(r5)
        L_0x002c:
            java.lang.String r5 = r4.getName()
            java.io.File r6 = getCacheDir()
            java.io.File r7 = r6.getCanonicalFile()
            java.lang.String r8 = "org.bytedeco.javacpp.cachedir.nosubdir"
            java.lang.String r9 = "false"
            java.lang.String r8 = java.lang.System.getProperty(r8, r9)
            java.lang.String r8 = r8.toLowerCase()
            java.lang.String r9 = "true"
            boolean r9 = r8.equals(r9)
            r10 = 1
            if (r9 != 0) goto L_0x0060
            java.lang.String r9 = "t"
            boolean r9 = r8.equals(r9)
            if (r9 != 0) goto L_0x0060
            java.lang.String r9 = ""
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x005e
            goto L_0x0060
        L_0x005e:
            r8 = 0
            goto L_0x0061
        L_0x0060:
            r8 = 1
        L_0x0061:
            java.net.URLConnection r9 = r3.openConnection()
            boolean r11 = r9 instanceof java.net.JarURLConnection
            r12 = 0
            if (r11 == 0) goto L_0x00b4
            java.net.JarURLConnection r9 = (java.net.JarURLConnection) r9
            java.util.jar.JarFile r11 = r9.getJarFile()
            java.util.jar.JarEntry r9 = r9.getJarEntry()
            java.io.File r13 = new java.io.File
            java.lang.String r11 = r11.getName()
            r13.<init>(r11)
            java.io.File r11 = new java.io.File
            java.lang.String r14 = r9.getName()
            r11.<init>(r14)
            long r14 = r9.getSize()
            long r16 = r9.getTime()
            if (r8 != 0) goto L_0x00eb
            java.lang.String r8 = r13.getName()
            java.lang.String r9 = r11.getParent()
            if (r9 == 0) goto L_0x00ae
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r8)
            java.lang.String r8 = java.io.File.separator
            r11.append(r8)
            r11.append(r9)
            java.lang.String r8 = r11.toString()
        L_0x00ae:
            java.io.File r9 = new java.io.File
            r9.<init>(r7, r8)
            goto L_0x00ea
        L_0x00b4:
            boolean r11 = r9 instanceof java.net.HttpURLConnection
            if (r11 == 0) goto L_0x00ef
            int r11 = r9.getContentLength()
            long r14 = (long) r11
            long r16 = r9.getLastModified()
            if (r8 != 0) goto L_0x00eb
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = r3.getHost()
            r8.append(r9)
            java.lang.String r9 = r3.getPath()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.io.File r9 = new java.io.File
            r11 = 47
            int r11 = r8.lastIndexOf(r11)
            int r11 = r11 + r10
            java.lang.String r8 = r8.substring(r2, r11)
            r9.<init>(r7, r8)
        L_0x00ea:
            r7 = r9
        L_0x00eb:
            r8 = r16
            goto L_0x0185
        L_0x00ef:
            java.lang.String r11 = r3.getProtocol()
            java.lang.String r13 = "jrt"
            boolean r11 = r11.equals(r13)
            if (r11 == 0) goto L_0x0152
            java.lang.String r9 = r3.getPath()
            java.net.URI r11 = new java.net.URI     // Catch:{ URISyntaxException -> 0x013e }
            java.lang.String r15 = "jrt"
            r11.<init>(r15, r9, r12)     // Catch:{ URISyntaxException -> 0x013e }
            java.nio.file.Path r11 = java.nio.file.Paths.get(r11)     // Catch:{ URISyntaxException -> 0x013e }
            long r15 = java.nio.file.Files.size(r11)     // Catch:{ NoSuchFileException -> 0x0110 }
            r13 = r15
            goto L_0x0130
        L_0x0110:
            java.net.URI r11 = new java.net.URI     // Catch:{ URISyntaxException -> 0x013e }
            java.lang.String r15 = "jrt"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x013e }
            r13.<init>()     // Catch:{ URISyntaxException -> 0x013e }
            java.lang.String r14 = "/modules"
            r13.append(r14)     // Catch:{ URISyntaxException -> 0x013e }
            r13.append(r9)     // Catch:{ URISyntaxException -> 0x013e }
            java.lang.String r9 = r13.toString()     // Catch:{ URISyntaxException -> 0x013e }
            r11.<init>(r15, r9, r12)     // Catch:{ URISyntaxException -> 0x013e }
            java.nio.file.Path r11 = java.nio.file.Paths.get(r11)     // Catch:{ URISyntaxException -> 0x013e }
            long r13 = java.nio.file.Files.size(r11)     // Catch:{ URISyntaxException -> 0x013e }
        L_0x0130:
            java.nio.file.LinkOption[] r9 = new java.nio.file.LinkOption[r2]     // Catch:{ URISyntaxException -> 0x013e }
            java.nio.file.attribute.FileTime r9 = java.nio.file.Files.getLastModifiedTime(r11, r9)     // Catch:{ URISyntaxException -> 0x013e }
            long r15 = r9.toMillis()     // Catch:{ URISyntaxException -> 0x013e }
            r16 = r15
            r14 = r13
            goto L_0x0142
        L_0x013e:
            r14 = 0
            r16 = 0
        L_0x0142:
            if (r8 != 0) goto L_0x00eb
            java.io.File r8 = new java.io.File
            java.io.File r9 = r4.getParentFile()
            java.lang.String r9 = r9.getName()
            r8.<init>(r7, r9)
            goto L_0x0182
        L_0x0152:
            boolean r11 = r4.exists()
            if (r11 == 0) goto L_0x0164
            long r13 = r4.length()
            long r15 = r4.lastModified()
        L_0x0160:
            r16 = r15
            r14 = r13
            goto L_0x0173
        L_0x0164:
            if (r9 == 0) goto L_0x016f
            long r13 = r9.getContentLengthLong()
            long r15 = r9.getLastModified()
            goto L_0x0160
        L_0x016f:
            r14 = 0
            r16 = 0
        L_0x0173:
            if (r8 != 0) goto L_0x00eb
            java.io.File r8 = new java.io.File
            java.io.File r9 = r4.getParentFile()
            java.lang.String r9 = r9.getName()
            r8.<init>(r7, r9)
        L_0x0182:
            r7 = r8
            goto L_0x00eb
        L_0x0185:
            int r11 = r1.length
            if (r11 <= r10) goto L_0x01a0
            r11 = r1[r10]
            if (r11 == 0) goto L_0x01a0
            r11 = r1[r10]
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x01a0
            r1 = r1[r10]
            boolean r5 = r1.equals(r5)
            r18 = r5
            r5 = r1
            r1 = r18
            goto L_0x01a1
        L_0x01a0:
            r1 = 0
        L_0x01a1:
            java.io.File r11 = new java.io.File
            r11.<init>(r7, r5)
            java.io.File r5 = new java.io.File
            java.lang.String r13 = ".lock"
            r5.<init>(r6, r13)
            if (r0 == 0) goto L_0x02d6
            int r13 = r20.length()
            if (r13 <= 0) goto L_0x02d6
            java.lang.Runtime r13 = java.lang.Runtime.getRuntime()
            monitor-enter(r13)
            java.nio.file.Path r1 = r11.toPath()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.nio.file.Path r0 = java.nio.file.Paths.get(r0, r3)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.nio.file.Path r0 = r0.normalize()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            boolean r3 = r11.exists()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r3 == 0) goto L_0x01de
            boolean r3 = java.nio.file.Files.isSymbolicLink(r1)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r3 == 0) goto L_0x01de
            java.nio.file.Path r3 = java.nio.file.Files.readSymbolicLink(r1)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            boolean r3 = r3.equals(r0)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r3 != 0) goto L_0x027e
        L_0x01de:
            boolean r3 = r0.isAbsolute()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r3 == 0) goto L_0x027e
            boolean r3 = r0.equals(r1)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r3 != 0) goto L_0x027e
            org.bytedeco.javacpp.tools.Logger r3 = logger     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            boolean r4 = r3.isDebugEnabled()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            if (r4 == 0) goto L_0x020b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            r4.<init>()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.lang.String r7 = "Locking "
            r4.append(r7)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            r4.append(r6)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.lang.String r6 = " to create symbolic link"
            r4.append(r6)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            r3.debug(r4)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
        L_0x020b:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.nio.channels.FileChannel r4 = r4.getChannel()     // Catch:{ IOException -> 0x0291, RuntimeException -> 0x028f, all -> 0x028c }
            java.nio.channels.FileLock r5 = r4.lock()     // Catch:{ IOException -> 0x027b, RuntimeException -> 0x0279, all -> 0x0277 }
            boolean r6 = r11.exists()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 == 0) goto L_0x022e
            boolean r6 = java.nio.file.Files.isSymbolicLink(r1)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 == 0) goto L_0x022e
            java.nio.file.Path r6 = java.nio.file.Files.readSymbolicLink(r1)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            boolean r6 = r6.equals(r0)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 != 0) goto L_0x0271
        L_0x022e:
            boolean r6 = r0.isAbsolute()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 == 0) goto L_0x0271
            boolean r6 = r0.equals(r1)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 != 0) goto L_0x0271
            boolean r6 = r3.isDebugEnabled()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            if (r6 == 0) goto L_0x025c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            r6.<init>()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            java.lang.String r7 = "Creating symbolic link "
            r6.append(r7)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            r6.append(r1)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            java.lang.String r7 = " to "
            r6.append(r7)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            r6.append(r0)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            r3.debug(r6)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
        L_0x025c:
            java.io.File r3 = r11.getParentFile()     // Catch:{ FileAlreadyExistsException -> 0x0269 }
            r3.mkdirs()     // Catch:{ FileAlreadyExistsException -> 0x0269 }
            java.nio.file.attribute.FileAttribute[] r3 = new java.nio.file.attribute.FileAttribute[r2]     // Catch:{ FileAlreadyExistsException -> 0x0269 }
            java.nio.file.Files.createSymbolicLink(r1, r0, r3)     // Catch:{ FileAlreadyExistsException -> 0x0269 }
            goto L_0x0271
        L_0x0269:
            r11.delete()     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            java.nio.file.attribute.FileAttribute[] r2 = new java.nio.file.attribute.FileAttribute[r2]     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
            java.nio.file.Files.createSymbolicLink(r1, r0, r2)     // Catch:{ IOException -> 0x0275, RuntimeException -> 0x0273 }
        L_0x0271:
            r12 = r5
            goto L_0x027f
        L_0x0273:
            r0 = move-exception
            goto L_0x0294
        L_0x0275:
            r0 = move-exception
            goto L_0x0294
        L_0x0277:
            r0 = move-exception
            goto L_0x02c6
        L_0x0279:
            r0 = move-exception
            goto L_0x027c
        L_0x027b:
            r0 = move-exception
        L_0x027c:
            r5 = r12
            goto L_0x0294
        L_0x027e:
            r4 = r12
        L_0x027f:
            if (r12 == 0) goto L_0x0284
            r12.release()     // Catch:{ all -> 0x02cc }
        L_0x0284:
            if (r4 == 0) goto L_0x0289
            r4.close()     // Catch:{ all -> 0x02cc }
        L_0x0289:
            monitor-exit(r13)     // Catch:{ all -> 0x02cc }
            goto L_0x04e2
        L_0x028c:
            r0 = move-exception
            r4 = r12
            goto L_0x02c6
        L_0x028f:
            r0 = move-exception
            goto L_0x0292
        L_0x0291:
            r0 = move-exception
        L_0x0292:
            r4 = r12
            r5 = r4
        L_0x0294:
            org.bytedeco.javacpp.tools.Logger r1 = logger     // Catch:{ all -> 0x02c4 }
            boolean r2 = r1.isDebugEnabled()     // Catch:{ all -> 0x02c4 }
            if (r2 == 0) goto L_0x02b8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x02c4 }
            r2.<init>()     // Catch:{ all -> 0x02c4 }
            java.lang.String r3 = "Failed to create symbolic link "
            r2.append(r3)     // Catch:{ all -> 0x02c4 }
            r2.append(r11)     // Catch:{ all -> 0x02c4 }
            java.lang.String r3 = ": "
            r2.append(r3)     // Catch:{ all -> 0x02c4 }
            r2.append(r0)     // Catch:{ all -> 0x02c4 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x02c4 }
            r1.debug(r0)     // Catch:{ all -> 0x02c4 }
        L_0x02b8:
            if (r5 == 0) goto L_0x02bd
            r5.release()     // Catch:{ all -> 0x02cc }
        L_0x02bd:
            if (r4 == 0) goto L_0x02c2
            r4.close()     // Catch:{ all -> 0x02cc }
        L_0x02c2:
            monitor-exit(r13)     // Catch:{ all -> 0x02cc }
            return r12
        L_0x02c4:
            r0 = move-exception
            r12 = r5
        L_0x02c6:
            if (r12 == 0) goto L_0x02ce
            r12.release()     // Catch:{ all -> 0x02cc }
            goto L_0x02ce
        L_0x02cc:
            r0 = move-exception
            goto L_0x02d4
        L_0x02ce:
            if (r4 == 0) goto L_0x02d3
            r4.close()     // Catch:{ all -> 0x02cc }
        L_0x02d3:
            throw r0     // Catch:{ all -> 0x02cc }
        L_0x02d4:
            monitor-exit(r13)     // Catch:{ all -> 0x02cc }
            throw r0
        L_0x02d6:
            boolean r0 = r4.exists()
            if (r0 == 0) goto L_0x0435
            if (r1 == 0) goto L_0x0435
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()
            monitor-enter(r1)
            java.nio.file.Path r0 = r11.toPath()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.nio.file.Path r4 = r4.toPath()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.nio.file.Path r4 = r4.normalize()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            boolean r13 = r11.exists()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            if (r13 == 0) goto L_0x0311
            boolean r13 = java.nio.file.Files.isSymbolicLink(r0)     // Catch:{ IOException -> 0x030d, RuntimeException -> 0x030b, all -> 0x0306 }
            if (r13 == 0) goto L_0x0311
            java.nio.file.Path r13 = java.nio.file.Files.readSymbolicLink(r0)     // Catch:{ IOException -> 0x030d, RuntimeException -> 0x030b, all -> 0x0306 }
            boolean r13 = r13.equals(r4)     // Catch:{ IOException -> 0x030d, RuntimeException -> 0x030b, all -> 0x0306 }
            if (r13 != 0) goto L_0x03d9
            goto L_0x0311
        L_0x0306:
            r0 = move-exception
            r16 = r12
            goto L_0x0425
        L_0x030b:
            r0 = move-exception
            goto L_0x030e
        L_0x030d:
            r0 = move-exception
        L_0x030e:
            r10 = r12
            goto L_0x03f2
        L_0x0311:
            boolean r13 = r4.isAbsolute()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            if (r13 == 0) goto L_0x03d9
            boolean r13 = r4.equals(r0)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            if (r13 != 0) goto L_0x03d9
            org.bytedeco.javacpp.tools.Logger r13 = logger     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            boolean r16 = r13.isDebugEnabled()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            if (r16 == 0) goto L_0x033e
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            r10.<init>()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.lang.String r12 = "Locking "
            r10.append(r12)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            r10.append(r6)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.lang.String r12 = " to create symbolic link"
            r10.append(r12)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            r13.debug(r10)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
        L_0x033e:
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            r10.<init>(r5)     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.nio.channels.FileChannel r10 = r10.getChannel()     // Catch:{ IOException -> 0x03ef, RuntimeException -> 0x03ed, all -> 0x03e8 }
            java.nio.channels.FileLock r12 = r10.lock()     // Catch:{ IOException -> 0x03d5, RuntimeException -> 0x03d3, all -> 0x03cc }
            boolean r17 = r11.exists()     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            if (r17 == 0) goto L_0x0368
            boolean r17 = java.nio.file.Files.isSymbolicLink(r0)     // Catch:{ IOException -> 0x0365, RuntimeException -> 0x0362 }
            if (r17 == 0) goto L_0x0368
            java.nio.file.Path r2 = java.nio.file.Files.readSymbolicLink(r0)     // Catch:{ IOException -> 0x0365, RuntimeException -> 0x0362 }
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x0365, RuntimeException -> 0x0362 }
            if (r2 != 0) goto L_0x03bb
            goto L_0x0368
        L_0x0362:
            r0 = move-exception
            goto L_0x03f2
        L_0x0365:
            r0 = move-exception
            goto L_0x03f2
        L_0x0368:
            boolean r2 = r4.isAbsolute()     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            if (r2 == 0) goto L_0x03bb
            boolean r2 = r4.equals(r0)     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            if (r2 != 0) goto L_0x03bb
            boolean r2 = r13.isDebugEnabled()     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            if (r2 == 0) goto L_0x0399
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            r2.<init>()     // Catch:{ IOException -> 0x03c8, RuntimeException -> 0x03c6, all -> 0x03c0 }
            r20 = r10
            java.lang.String r10 = "Creating symbolic link "
            r2.append(r10)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            r2.append(r0)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            java.lang.String r10 = " to "
            r2.append(r10)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            r2.append(r4)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            r13.debug(r2)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            goto L_0x039b
        L_0x0399:
            r20 = r10
        L_0x039b:
            java.io.File r2 = r11.getParentFile()     // Catch:{ FileAlreadyExistsException -> 0x03b1 }
            r2.mkdirs()     // Catch:{ FileAlreadyExistsException -> 0x03b1 }
            r2 = 0
            java.nio.file.attribute.FileAttribute[] r10 = new java.nio.file.attribute.FileAttribute[r2]     // Catch:{ FileAlreadyExistsException -> 0x03b1 }
            java.nio.file.Files.createSymbolicLink(r0, r4, r10)     // Catch:{ FileAlreadyExistsException -> 0x03b1 }
            goto L_0x03bd
        L_0x03a9:
            r0 = move-exception
            goto L_0x03c3
        L_0x03ab:
            r0 = move-exception
            goto L_0x03ae
        L_0x03ad:
            r0 = move-exception
        L_0x03ae:
            r10 = r20
            goto L_0x03f2
        L_0x03b1:
            r11.delete()     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            r2 = 0
            java.nio.file.attribute.FileAttribute[] r2 = new java.nio.file.attribute.FileAttribute[r2]     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            java.nio.file.Files.createSymbolicLink(r0, r4, r2)     // Catch:{ IOException -> 0x03ad, RuntimeException -> 0x03ab, all -> 0x03a9 }
            goto L_0x03bd
        L_0x03bb:
            r20 = r10
        L_0x03bd:
            r16 = r20
            goto L_0x03dc
        L_0x03c0:
            r0 = move-exception
            r20 = r10
        L_0x03c3:
            r16 = r20
            goto L_0x0425
        L_0x03c6:
            r0 = move-exception
            goto L_0x03c9
        L_0x03c8:
            r0 = move-exception
        L_0x03c9:
            r20 = r10
            goto L_0x03f2
        L_0x03cc:
            r0 = move-exception
            r20 = r10
            r16 = r20
            r12 = 0
            goto L_0x0425
        L_0x03d3:
            r0 = move-exception
            goto L_0x03d6
        L_0x03d5:
            r0 = move-exception
        L_0x03d6:
            r20 = r10
            goto L_0x03f1
        L_0x03d9:
            r12 = 0
            r16 = 0
        L_0x03dc:
            if (r12 == 0) goto L_0x03e1
            r12.release()     // Catch:{ all -> 0x042b }
        L_0x03e1:
            if (r16 == 0) goto L_0x03e6
            r16.close()     // Catch:{ all -> 0x042b }
        L_0x03e6:
            monitor-exit(r1)     // Catch:{ all -> 0x042b }
            return r11
        L_0x03e8:
            r0 = move-exception
            r12 = 0
            r16 = 0
            goto L_0x0425
        L_0x03ed:
            r0 = move-exception
            goto L_0x03f0
        L_0x03ef:
            r0 = move-exception
        L_0x03f0:
            r10 = 0
        L_0x03f1:
            r12 = 0
        L_0x03f2:
            org.bytedeco.javacpp.tools.Logger r2 = logger     // Catch:{ all -> 0x0422 }
            boolean r4 = r2.isDebugEnabled()     // Catch:{ all -> 0x0422 }
            if (r4 == 0) goto L_0x0416
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0422 }
            r4.<init>()     // Catch:{ all -> 0x0422 }
            java.lang.String r13 = "Could not create symbolic link "
            r4.append(r13)     // Catch:{ all -> 0x0422 }
            r4.append(r11)     // Catch:{ all -> 0x0422 }
            java.lang.String r13 = ": "
            r4.append(r13)     // Catch:{ all -> 0x0422 }
            r4.append(r0)     // Catch:{ all -> 0x0422 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0422 }
            r2.debug(r0)     // Catch:{ all -> 0x0422 }
        L_0x0416:
            if (r12 == 0) goto L_0x041b
            r12.release()     // Catch:{ all -> 0x042b }
        L_0x041b:
            if (r10 == 0) goto L_0x0420
            r10.close()     // Catch:{ all -> 0x042b }
        L_0x0420:
            monitor-exit(r1)     // Catch:{ all -> 0x042b }
            goto L_0x0437
        L_0x0422:
            r0 = move-exception
            r16 = r10
        L_0x0425:
            if (r12 == 0) goto L_0x042d
            r12.release()     // Catch:{ all -> 0x042b }
            goto L_0x042d
        L_0x042b:
            r0 = move-exception
            goto L_0x0433
        L_0x042d:
            if (r16 == 0) goto L_0x0432
            r16.close()     // Catch:{ all -> 0x042b }
        L_0x0432:
            throw r0     // Catch:{ all -> 0x042b }
        L_0x0433:
            monitor-exit(r1)     // Catch:{ all -> 0x042b }
            throw r0
        L_0x0435:
            r10 = 0
            r12 = 0
        L_0x0437:
            boolean r0 = r11.exists()
            if (r0 == 0) goto L_0x045b
            long r0 = r11.length()
            int r2 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r2 != 0) goto L_0x045b
            long r0 = r11.lastModified()
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x045b
            java.io.File r0 = r11.getCanonicalFile()
            java.io.File r0 = r0.getParentFile()
            boolean r0 = r7.equals(r0)
            if (r0 != 0) goto L_0x04e2
        L_0x045b:
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()
            monitor-enter(r1)
            org.bytedeco.javacpp.tools.Logger r0 = logger     // Catch:{ all -> 0x04e3 }
            boolean r2 = r0.isDebugEnabled()     // Catch:{ all -> 0x04e3 }
            if (r2 == 0) goto L_0x0481
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x04e3 }
            r2.<init>()     // Catch:{ all -> 0x04e3 }
            java.lang.String r4 = "Locking "
            r2.append(r4)     // Catch:{ all -> 0x04e3 }
            r2.append(r6)     // Catch:{ all -> 0x04e3 }
            java.lang.String r4 = " before extracting"
            r2.append(r4)     // Catch:{ all -> 0x04e3 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x04e3 }
            r0.debug(r2)     // Catch:{ all -> 0x04e3 }
        L_0x0481:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x04e3 }
            r2.<init>(r5)     // Catch:{ all -> 0x04e3 }
            java.nio.channels.FileChannel r10 = r2.getChannel()     // Catch:{ all -> 0x04e3 }
            java.nio.channels.FileLock r12 = r10.lock()     // Catch:{ all -> 0x04e3 }
            boolean r2 = r11.exists()     // Catch:{ all -> 0x04e3 }
            if (r2 == 0) goto L_0x04b2
            long r4 = r11.length()     // Catch:{ all -> 0x04e3 }
            int r2 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r2 != 0) goto L_0x04b2
            long r4 = r11.lastModified()     // Catch:{ all -> 0x04e3 }
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x04b2
            java.io.File r2 = r11.getCanonicalFile()     // Catch:{ all -> 0x04e3 }
            java.io.File r2 = r2.getParentFile()     // Catch:{ all -> 0x04e3 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x04e3 }
            if (r2 != 0) goto L_0x04d7
        L_0x04b2:
            boolean r2 = r0.isDebugEnabled()     // Catch:{ all -> 0x04e3 }
            if (r2 == 0) goto L_0x04cc
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x04e3 }
            r2.<init>()     // Catch:{ all -> 0x04e3 }
            java.lang.String r4 = "Extracting "
            r2.append(r4)     // Catch:{ all -> 0x04e3 }
            r2.append(r3)     // Catch:{ all -> 0x04e3 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x04e3 }
            r0.debug(r2)     // Catch:{ all -> 0x04e3 }
        L_0x04cc:
            r11.delete()     // Catch:{ all -> 0x04e3 }
            r2 = 0
            r4 = 1
            extractResource((java.net.URL) r3, (java.io.File) r11, (java.lang.String) r2, (java.lang.String) r2, (boolean) r4)     // Catch:{ all -> 0x04e3 }
            r11.setLastModified(r8)     // Catch:{ all -> 0x04e3 }
        L_0x04d7:
            if (r12 == 0) goto L_0x04dc
            r12.release()     // Catch:{ all -> 0x04ea }
        L_0x04dc:
            if (r10 == 0) goto L_0x04e1
            r10.close()     // Catch:{ all -> 0x04ea }
        L_0x04e1:
            monitor-exit(r1)     // Catch:{ all -> 0x04ea }
        L_0x04e2:
            return r11
        L_0x04e3:
            r0 = move-exception
            if (r12 == 0) goto L_0x04ec
            r12.release()     // Catch:{ all -> 0x04ea }
            goto L_0x04ec
        L_0x04ea:
            r0 = move-exception
            goto L_0x04f2
        L_0x04ec:
            if (r10 == 0) goto L_0x04f1
            r10.close()     // Catch:{ all -> 0x04ea }
        L_0x04f1:
            throw r0     // Catch:{ all -> 0x04ea }
        L_0x04f2:
            monitor-exit(r1)     // Catch:{ all -> 0x04ea }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.cacheResource(java.net.URL, java.lang.String):java.io.File");
    }

    public static File extractResource(String str, File file, String str2, String str3) throws IOException {
        return extractResource(getCallerClass(2), str, file, str2, str3);
    }

    public static File extractResource(Class cls, String str, File file, String str2, String str3) throws IOException {
        URL findResource = findResource(cls, str);
        if (findResource != null) {
            return extractResource(findResource, file, str2, str3);
        }
        return null;
    }

    public static File[] extractResources(String str, File file, String str2, String str3) throws IOException {
        return extractResources(getCallerClass(2), str, file, str2, str3);
    }

    public static File[] extractResources(Class cls, String str, File file, String str2, String str3) throws IOException {
        URL[] findResources = findResources(cls, str);
        File[] fileArr = new File[findResources.length];
        for (int i = 0; i < findResources.length; i++) {
            fileArr[i] = extractResource(findResources[i], file, str2, str3);
        }
        return fileArr;
    }

    public static File extractResource(URL url, File file, String str, String str2) throws IOException {
        return extractResource(url, file, str, str2, false);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:45|46|47|48) */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0168, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0169, code lost:
        r6 = r1;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x016d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x016f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0170, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0180, code lost:
        r3.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0117 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:41:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0180  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x0141=Splitter:B:58:0x0141, B:47:0x0117=Splitter:B:47:0x0117, B:61:0x0147=Splitter:B:61:0x0147} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x0141=Splitter:B:58:0x0141, B:47:0x0117=Splitter:B:47:0x0117} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File extractResource(java.net.URL r16, java.io.File r17, java.lang.String r18, java.lang.String r19, boolean r20) throws java.io.IOException {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = 0
            if (r16 == 0) goto L_0x000e
            java.net.URLConnection r4 = r16.openConnection()
            goto L_0x000f
        L_0x000e:
            r4 = r3
        L_0x000f:
            boolean r5 = r4 instanceof java.net.JarURLConnection
            r6 = 0
            if (r5 == 0) goto L_0x00d5
            r5 = r4
            java.net.JarURLConnection r5 = (java.net.JarURLConnection) r5
            java.util.jar.JarFile r7 = r5.getJarFile()
            java.util.jar.JarEntry r5 = r5.getJarEntry()
            r7.getName()
            java.lang.String r8 = r5.getName()
            java.lang.String r9 = "/"
            boolean r10 = r8.endsWith(r9)
            if (r10 != 0) goto L_0x003d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            r10.append(r9)
            java.lang.String r8 = r10.toString()
        L_0x003d:
            boolean r5 = r5.isDirectory()
            if (r5 != 0) goto L_0x0049
            java.util.jar.JarEntry r5 = r7.getJarEntry(r8)
            if (r5 == 0) goto L_0x00d5
        L_0x0049:
            java.util.Enumeration r3 = r7.entries()
        L_0x004d:
            boolean r4 = r3.hasMoreElements()
            if (r4 == 0) goto L_0x00d4
            java.lang.Object r4 = r3.nextElement()
            java.util.jar.JarEntry r4 = (java.util.jar.JarEntry) r4
            java.lang.String r5 = r4.getName()
            long r9 = r4.getSize()
            long r11 = r4.getTime()
            boolean r7 = r5.startsWith(r8)
            if (r7 == 0) goto L_0x004d
            java.io.File r7 = new java.io.File
            int r13 = r8.length()
            java.lang.String r13 = r5.substring(r13)
            r7.<init>(r0, r13)
            boolean r4 = r4.isDirectory()
            if (r4 == 0) goto L_0x0082
            r7.mkdirs()
            goto L_0x00cf
        L_0x0082:
            if (r20 == 0) goto L_0x00a4
            boolean r4 = r7.exists()
            if (r4 == 0) goto L_0x00a4
            long r13 = r7.length()
            int r4 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x00a4
            long r9 = r7.lastModified()
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r4 != 0) goto L_0x00a4
            java.io.File r4 = r7.getCanonicalFile()
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00cf
        L_0x00a4:
            r7.delete()
            java.lang.String r4 = r16.toString()
            java.net.URL r9 = new java.net.URL
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "!/"
            int r13 = r4.indexOf(r13)
            int r13 = r13 + 2
            java.lang.String r4 = r4.substring(r6, r13)
            r10.append(r4)
            r10.append(r5)
            java.lang.String r4 = r10.toString()
            r9.<init>(r4)
            java.io.File r7 = extractResource((java.net.URL) r9, (java.io.File) r7, (java.lang.String) r1, (java.lang.String) r2)
        L_0x00cf:
            r7.setLastModified(r11)
            goto L_0x004d
        L_0x00d4:
            return r0
        L_0x00d5:
            if (r4 == 0) goto L_0x00dc
            java.io.InputStream r4 = r4.getInputStream()
            goto L_0x00dd
        L_0x00dc:
            r4 = r3
        L_0x00dd:
            if (r4 != 0) goto L_0x00e0
            return r3
        L_0x00e0:
            if (r1 != 0) goto L_0x0141
            if (r2 != 0) goto L_0x0141
            if (r0 != 0) goto L_0x00f1
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            java.lang.String r1 = "java.io.tmpdir"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            r0.<init>(r1)     // Catch:{ IOException -> 0x016f, all -> 0x016d }
        L_0x00f1:
            boolean r1 = r0.isDirectory()     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            if (r1 == 0) goto L_0x012a
            java.io.File r1 = new java.io.File     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            java.io.File r2 = new java.io.File     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            java.net.URI r5 = new java.net.URI     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            java.lang.String r7 = r16.toString()     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            java.lang.String r8 = "#"
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            r7 = r7[r6]     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            r5.<init>(r7)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            r2.<init>(r5)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            java.lang.String r2 = r2.getName()     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            r1.<init>(r0, r2)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x0117 }
            goto L_0x0131
        L_0x0117:
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            java.lang.String r5 = r16.getPath()     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            r2.<init>(r5)     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            java.lang.String r2 = r2.getName()     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            goto L_0x0131
        L_0x012a:
            java.io.File r1 = r0.getParentFile()     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            r15 = r1
            r1 = r0
            r0 = r15
        L_0x0131:
            if (r0 == 0) goto L_0x0136
            r0.mkdirs()     // Catch:{ IOException -> 0x013d, all -> 0x016d }
        L_0x0136:
            boolean r0 = r1.exists()     // Catch:{ IOException -> 0x013d, all -> 0x016d }
            r2 = r1
            r1 = r0
            goto L_0x0147
        L_0x013d:
            r0 = move-exception
            r5 = r3
            r3 = r1
            goto L_0x0171
        L_0x0141:
            java.io.File r1 = java.io.File.createTempFile(r1, r2, r0)     // Catch:{ IOException -> 0x016f, all -> 0x016d }
            r2 = r1
            r1 = 0
        L_0x0147:
            r2.delete()     // Catch:{ IOException -> 0x0168, all -> 0x016d }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0168, all -> 0x016d }
            r5.<init>(r2)     // Catch:{ IOException -> 0x0168, all -> 0x016d }
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0165 }
        L_0x0153:
            int r3 = r4.read(r0)     // Catch:{ IOException -> 0x0165 }
            r7 = -1
            if (r3 == r7) goto L_0x015e
            r5.write(r0, r6, r3)     // Catch:{ IOException -> 0x0165 }
            goto L_0x0153
        L_0x015e:
            r4.close()
            r5.close()
            return r2
        L_0x0165:
            r0 = move-exception
            r6 = r1
            goto L_0x016b
        L_0x0168:
            r0 = move-exception
            r6 = r1
            r5 = r3
        L_0x016b:
            r3 = r2
            goto L_0x0171
        L_0x016d:
            r0 = move-exception
            goto L_0x017b
        L_0x016f:
            r0 = move-exception
            r5 = r3
        L_0x0171:
            if (r3 == 0) goto L_0x0178
            if (r6 != 0) goto L_0x0178
            r3.delete()     // Catch:{ all -> 0x0179 }
        L_0x0178:
            throw r0     // Catch:{ all -> 0x0179 }
        L_0x0179:
            r0 = move-exception
            r3 = r5
        L_0x017b:
            r4.close()
            if (r3 == 0) goto L_0x0183
            r3.close()
        L_0x0183:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.extractResource(java.net.URL, java.io.File, java.lang.String, java.lang.String, boolean):java.io.File");
    }

    public static URL findResource(Class cls, String str) throws IOException {
        URL[] findResources = findResources(cls, str, 1);
        if (findResources.length > 0) {
            return findResources[0];
        }
        return null;
    }

    public static URL[] findResources(Class cls, String str) throws IOException {
        return findResources(cls, str, -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.URL[] findResources(java.lang.Class r8, java.lang.String r9, int r10) throws java.io.IOException {
        /*
            r0 = 0
            if (r10 != 0) goto L_0x0006
            java.net.URL[] r8 = new java.net.URL[r0]
            return r8
        L_0x0006:
            java.lang.String r1 = "//"
            boolean r2 = r9.contains(r1)
            java.lang.String r3 = "/"
            if (r2 == 0) goto L_0x0015
            java.lang.String r9 = r9.replace(r1, r3)
            goto L_0x0006
        L_0x0015:
            java.net.URL r1 = r8.getResource(r9)
            r2 = 1
            if (r1 == 0) goto L_0x0023
            if (r10 != r2) goto L_0x0023
            java.net.URL[] r8 = new java.net.URL[r2]
            r8[r0] = r1
            return r8
        L_0x0023:
            boolean r3 = r9.startsWith(r3)
            java.lang.String r4 = ""
            r5 = 47
            if (r3 != 0) goto L_0x0043
            java.lang.String r3 = r8.getName()
            r6 = 46
            java.lang.String r3 = r3.replace(r6, r5)
            int r6 = r3.lastIndexOf(r5)
            if (r6 < 0) goto L_0x0047
            int r6 = r6 + r2
            java.lang.String r2 = r3.substring(r0, r6)
            goto L_0x0048
        L_0x0043:
            java.lang.String r9 = r9.substring(r2)
        L_0x0047:
            r2 = r4
        L_0x0048:
            java.lang.ClassLoader r8 = r8.getClassLoader()
            if (r8 != 0) goto L_0x0052
            java.lang.ClassLoader r8 = java.lang.ClassLoader.getSystemClassLoader()
        L_0x0052:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            java.util.Enumeration r3 = r8.getResources(r3)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            if (r1 == 0) goto L_0x006f
            r6.add(r1)
        L_0x006f:
            if (r1 != 0) goto L_0x00a5
            boolean r7 = r3.hasMoreElements()
            if (r7 != 0) goto L_0x00a5
            int r7 = r2.length()
            if (r7 <= 0) goto L_0x00a5
            int r3 = r2.length()
            int r3 = r3 + -2
            int r3 = r2.lastIndexOf(r5, r3)
            if (r3 < 0) goto L_0x0090
            int r3 = r3 + 1
            java.lang.String r2 = r2.substring(r0, r3)
            goto L_0x0091
        L_0x0090:
            r2 = r4
        L_0x0091:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            java.util.Enumeration r3 = r8.getResources(r3)
            goto L_0x006f
        L_0x00a5:
            boolean r8 = r3.hasMoreElements()
            if (r8 == 0) goto L_0x00c3
            if (r10 < 0) goto L_0x00b3
            int r8 = r6.size()
            if (r8 >= r10) goto L_0x00c3
        L_0x00b3:
            java.lang.Object r8 = r3.nextElement()
            java.net.URL r8 = (java.net.URL) r8
            boolean r9 = r6.contains(r8)
            if (r9 != 0) goto L_0x00a5
            r6.add(r8)
            goto L_0x00a5
        L_0x00c3:
            int r8 = r6.size()
            java.net.URL[] r8 = new java.net.URL[r8]
            java.lang.Object[] r8 = r6.toArray(r8)
            java.net.URL[] r8 = (java.net.URL[]) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.findResources(java.lang.Class, java.lang.String, int):java.net.URL[]");
    }

    public static File getCacheDir() throws IOException {
        if (cacheDir == null) {
            int i = 0;
            String[] strArr = {System.getProperty("org.bytedeco.javacpp.cachedir"), System.getProperty("org.bytedeco.javacpp.cacheDir"), System.getProperty("user.home") + "/.javacpp/cache/", System.getProperty("java.io.tmpdir") + "/.javacpp-" + System.getProperty("user.name") + "/cache/"};
            while (true) {
                if (i >= 4) {
                    break;
                }
                String str = strArr[i];
                if (str != null) {
                    File file = new File(str);
                    try {
                        if ((file.exists() || file.mkdirs()) && file.canRead() && file.canWrite() && file.canExecute()) {
                            cacheDir = file;
                            break;
                        }
                    } catch (SecurityException e) {
                        Logger logger2 = logger;
                        logger2.warn("Could not access " + file + ": " + e.getMessage());
                    }
                }
                i++;
            }
        }
        File file2 = cacheDir;
        if (file2 != null) {
            return file2;
        }
        throw new IOException("Could not create the cache: Set the \"org.bytedeco.javacpp.cachedir\" system property.");
    }

    public static File getTempDir() {
        if (tempDir == null) {
            File file = new File(System.getProperty("java.io.tmpdir"));
            int i = 0;
            while (true) {
                if (i >= 1000) {
                    break;
                }
                File file2 = new File(file, "javacpp" + System.nanoTime());
                if (file2.mkdir()) {
                    tempDir = file2;
                    file2.deleteOnExit();
                    break;
                }
                i++;
            }
        }
        return tempDir;
    }

    public static synchronized Map<String, String> getLoadedLibraries() {
        HashMap hashMap;
        synchronized (Loader.class) {
            hashMap = new HashMap(loadedLibraries);
        }
        return hashMap;
    }

    public static boolean isLoadLibraries() {
        String lowerCase = System.getProperty("org.bytedeco.javacpp.loadLibraries", System.getProperty("org.bytedeco.javacpp.loadlibraries", "true").toLowerCase()).toLowerCase();
        return lowerCase.equals("true") || lowerCase.equals("t") || lowerCase.equals("");
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkPlatform(java.lang.Class<?> r11, java.util.Properties r12) {
        /*
            java.lang.Class r0 = getEnclosingClass(r11)
        L_0x0004:
            java.lang.Class<org.bytedeco.javacpp.annotation.Properties> r1 = org.bytedeco.javacpp.annotation.Properties.class
            boolean r1 = r11.isAnnotationPresent(r1)
            if (r1 != 0) goto L_0x002e
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r1 = org.bytedeco.javacpp.annotation.Platform.class
            boolean r1 = r11.isAnnotationPresent(r1)
            if (r1 != 0) goto L_0x002e
            java.lang.Class r1 = r11.getSuperclass()
            if (r1 == 0) goto L_0x002e
            if (r0 == 0) goto L_0x0029
            java.lang.Class r1 = r11.getSuperclass()
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r1 != r2) goto L_0x0029
            r11 = 0
            r10 = r0
            r0 = r11
            r11 = r10
            goto L_0x0004
        L_0x0029:
            java.lang.Class r11 = r11.getSuperclass()
            goto L_0x0004
        L_0x002e:
            java.lang.Class<org.bytedeco.javacpp.annotation.Properties> r0 = org.bytedeco.javacpp.annotation.Properties.class
            java.lang.annotation.Annotation r0 = r11.getAnnotation(r0)
            org.bytedeco.javacpp.annotation.Properties r0 = (org.bytedeco.javacpp.annotation.Properties) r0
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r1 = org.bytedeco.javacpp.annotation.Platform.class
            java.lang.annotation.Annotation r1 = r11.getAnnotation(r1)
            org.bytedeco.javacpp.annotation.Platform r1 = (org.bytedeco.javacpp.annotation.Platform) r1
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x0046
            if (r1 != 0) goto L_0x0046
            r4 = 1
            goto L_0x0047
        L_0x0046:
            r4 = 0
        L_0x0047:
            if (r0 == 0) goto L_0x00b3
            java.lang.Class[] r5 = r0.inherit()
            java.lang.String[] r6 = r0.names()
            java.util.ArrayDeque r7 = new java.util.ArrayDeque
            java.util.List r8 = java.util.Arrays.asList(r5)
            r7.<init>(r8)
        L_0x005a:
            int r8 = r7.size()
            if (r8 <= 0) goto L_0x0085
            if (r6 == 0) goto L_0x0065
            int r8 = r6.length
            if (r8 != 0) goto L_0x0085
        L_0x0065:
            java.lang.Object r8 = r7.removeFirst()
            java.lang.Class r8 = (java.lang.Class) r8
            java.lang.Class<org.bytedeco.javacpp.annotation.Properties> r9 = org.bytedeco.javacpp.annotation.Properties.class
            java.lang.annotation.Annotation r8 = r8.getAnnotation(r9)
            org.bytedeco.javacpp.annotation.Properties r8 = (org.bytedeco.javacpp.annotation.Properties) r8
            if (r8 == 0) goto L_0x005a
            java.lang.String[] r6 = r8.names()
            java.lang.Class[] r8 = r8.inherit()
            java.util.List r8 = java.util.Arrays.asList(r8)
            r7.addAll(r8)
            goto L_0x005a
        L_0x0085:
            org.bytedeco.javacpp.annotation.Platform[] r0 = r0.value()
            if (r0 == 0) goto L_0x009e
            int r7 = r0.length
            if (r7 <= 0) goto L_0x009e
            int r5 = r0.length
            r7 = 0
        L_0x0090:
            if (r7 >= r5) goto L_0x00b3
            r8 = r0[r7]
            boolean r8 = checkPlatform(r8, r12, r6)
            if (r8 == 0) goto L_0x009b
            goto L_0x00b4
        L_0x009b:
            int r7 = r7 + 1
            goto L_0x0090
        L_0x009e:
            if (r5 == 0) goto L_0x00b3
            int r0 = r5.length
            if (r0 <= 0) goto L_0x00b3
            int r0 = r5.length
            r6 = 0
        L_0x00a5:
            if (r6 >= r0) goto L_0x00b3
            r7 = r5[r6]
            boolean r7 = checkPlatform(r7, r12)
            if (r7 == 0) goto L_0x00b0
            goto L_0x00b4
        L_0x00b0:
            int r6 = r6 + 1
            goto L_0x00a5
        L_0x00b3:
            r3 = r4
        L_0x00b4:
            if (r1 == 0) goto L_0x00c4
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r0 = org.bytedeco.javacpp.annotation.Platform.class
            java.lang.annotation.Annotation r11 = r11.getAnnotation(r0)
            org.bytedeco.javacpp.annotation.Platform r11 = (org.bytedeco.javacpp.annotation.Platform) r11
            java.lang.String[] r0 = new java.lang.String[r2]
            boolean r3 = checkPlatform(r11, r12, r0)
        L_0x00c4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.checkPlatform(java.lang.Class, java.util.Properties):boolean");
    }

    public static boolean checkPlatform(Platform platform, java.util.Properties properties, String... strArr) {
        if (platform == null) {
            return true;
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        String property = properties.getProperty("platform");
        String property2 = properties.getProperty("platform.extension");
        String[][] strArr2 = new String[2][];
        if (platform.value().length > 0) {
            strArr = platform.value();
        }
        strArr2[0] = strArr;
        strArr2[1] = platform.not();
        boolean[] zArr = {false, false};
        for (int i = 0; i < 2; i++) {
            String[] strArr3 = strArr2[i];
            int length = strArr3.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (property.startsWith(strArr3[i2])) {
                    zArr[i] = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if ((strArr2[0].length != 0 && !zArr[0]) || (strArr2[1].length != 0 && zArr[1])) {
            return false;
        }
        boolean z = platform.extension().length == 0 || (isLoadLibraries() && property2 == null);
        for (String str : platform.extension()) {
            if (property2 != null && property2.length() > 0 && property2.endsWith(str)) {
                return true;
            }
        }
        return z;
    }

    public static String[] load(Class... clsArr) {
        return load(clsArr, true);
    }

    public static String[] load(Class[] clsArr, boolean z) {
        String[] strArr = new String[clsArr.length];
        java.util.Properties loadProperties = loadProperties();
        for (int i = 0; i < clsArr.length; i++) {
            Class cls = clsArr[i];
            if (getEnclosingClass(cls) == cls && loadProperties(cls, loadProperties, false).isLoaded()) {
                if (loadProperties(cls, loadProperties, true).isLoaded()) {
                    if (z) {
                        try {
                            Logger logger2 = logger;
                            logger2.info("Loading " + cls);
                        } catch (NoClassDefFoundError | UnsatisfiedLinkError e) {
                            if (z) {
                                Logger logger3 = logger;
                                logger3.warn("Could not load " + cls + ": " + e);
                            }
                        }
                    }
                    strArr[i] = load(cls);
                } else if (z) {
                    Logger logger4 = logger;
                    logger4.warn("Could not load platform properties for " + cls);
                }
            }
        }
        return strArr;
    }

    public static String load() {
        return load(getCallerClass(2), loadProperties(), pathsFirst);
    }

    public static String load(boolean z) {
        return load(getCallerClass(2), loadProperties(), z);
    }

    public static String load(Class cls) {
        return load(cls, loadProperties(), pathsFirst);
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0356  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String load(java.lang.Class r23, java.util.Properties r24, boolean r25) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            boolean r3 = isLoadLibraries()
            if (r3 == 0) goto L_0x03a8
            if (r0 != 0) goto L_0x0010
            goto L_0x03a8
        L_0x0010:
            boolean r3 = checkPlatform(r23, r24)
            java.lang.String r5 = "platform"
            if (r3 == 0) goto L_0x0385
            java.lang.Class r3 = getEnclosingClass(r23)
            r6 = 1
            org.bytedeco.javacpp.ClassProperties r1 = loadProperties((java.lang.Class) r3, (java.util.Properties) r1, (boolean) r6)
            java.lang.String r7 = "global"
            java.util.List r7 = r1.get(r7)
            boolean r8 = r7.isEmpty()
            if (r8 == 0) goto L_0x0056
            java.util.List r8 = r1.getInheritedClasses()
            if (r8 == 0) goto L_0x004f
            java.util.List r8 = r1.getInheritedClasses()
            java.util.Iterator r8 = r8.iterator()
        L_0x003b:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x004f
            java.lang.Object r9 = r8.next()
            java.lang.Class r9 = (java.lang.Class) r9
            java.lang.String r9 = r9.getName()
            r7.add(r9)
            goto L_0x003b
        L_0x004f:
            java.lang.String r8 = r3.getName()
            r7.add(r8)
        L_0x0056:
            java.lang.String r8 = r23.getName()
            boolean r8 = r7.contains(r8)
            if (r8 != 0) goto L_0x0067
            java.lang.String r0 = r23.getName()
            r7.add(r0)
        L_0x0067:
            java.util.Iterator r0 = r7.iterator()
        L_0x006b:
            boolean r7 = r0.hasNext()
            java.lang.String r8 = ": "
            if (r7 == 0) goto L_0x00cd
            java.lang.Object r7 = r0.next()
            java.lang.String r7 = (java.lang.String) r7
            org.bytedeco.javacpp.tools.Logger r9 = logger     // Catch:{ ClassNotFoundException -> 0x009d }
            boolean r10 = r9.isDebugEnabled()     // Catch:{ ClassNotFoundException -> 0x009d }
            if (r10 == 0) goto L_0x0095
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x009d }
            r10.<init>()     // Catch:{ ClassNotFoundException -> 0x009d }
            java.lang.String r11 = "Loading class "
            r10.append(r11)     // Catch:{ ClassNotFoundException -> 0x009d }
            r10.append(r7)     // Catch:{ ClassNotFoundException -> 0x009d }
            java.lang.String r10 = r10.toString()     // Catch:{ ClassNotFoundException -> 0x009d }
            r9.debug(r10)     // Catch:{ ClassNotFoundException -> 0x009d }
        L_0x0095:
            java.lang.ClassLoader r9 = r3.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x009d }
            java.lang.Class.forName(r7, r6, r9)     // Catch:{ ClassNotFoundException -> 0x009d }
            goto L_0x006b
        L_0x009d:
            r0 = move-exception
            org.bytedeco.javacpp.tools.Logger r1 = logger
            boolean r2 = r1.isDebugEnabled()
            if (r2 == 0) goto L_0x00c0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to load class "
            r2.append(r3)
            r2.append(r7)
            r2.append(r8)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.debug(r2)
        L_0x00c0:
            java.lang.NoClassDefFoundError r1 = new java.lang.NoClassDefFoundError
            java.lang.String r2 = r0.toString()
            r1.<init>(r2)
            r1.initCause(r0)
            throw r1
        L_0x00cd:
            java.io.File r0 = getCacheDir()     // Catch:{ IOException -> 0x00d7 }
            java.lang.String r0 = r0.getCanonicalPath()     // Catch:{ IOException -> 0x00d7 }
            r7 = r0
            goto L_0x00d8
        L_0x00d7:
            r7 = 0
        L_0x00d8:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.lang.String r0 = "platform.preload"
            java.util.List r0 = r1.get(r0)
            r9.addAll(r0)
            java.lang.String r0 = "platform.link"
            java.util.List r0 = r1.get(r0)
            r9.addAll(r0)
            java.util.Iterator r12 = r9.iterator()
            r13 = 0
        L_0x00fe:
            boolean r0 = r12.hasNext()
            r14 = 0
            if (r0 == 0) goto L_0x016c
            java.lang.Object r0 = r12.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r15 = "!"
            boolean r15 = r0.endsWith(r15)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r15 == 0) goto L_0x011e
            int r15 = r0.length()     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            int r15 = r15 - r6
            java.lang.String r0 = r0.substring(r14, r15)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            r15 = 1
            goto L_0x011f
        L_0x011e:
            r15 = 0
        L_0x011f:
            java.util.Map<java.lang.String, java.net.URL[]> r4 = foundLibraries     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.lang.Object r4 = r4.get(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.net.URL[] r4 = (java.net.URL[]) r4     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r4 != 0) goto L_0x0133
            java.util.Map<java.lang.String, java.net.URL[]> r4 = foundLibraries     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.net.URL[] r6 = findLibrary(r3, r1, r0, r2)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            r4.put(r0, r6)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            r4 = r6
        L_0x0133:
            int r6 = r10.size()     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.lang.Object[] r6 = r10.toArray(r6)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            java.lang.String r4 = loadLibrary(r3, r4, r0, r6)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r4 == 0) goto L_0x0158
            java.io.File r6 = new java.io.File     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            r6.<init>(r4)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            boolean r6 = r6.exists()     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r6 == 0) goto L_0x0158
            r10.add(r4)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r15 == 0) goto L_0x0158
            r11.add(r4)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
        L_0x0158:
            if (r7 == 0) goto L_0x016a
            if (r4 == 0) goto L_0x016a
            boolean r6 = r4.startsWith(r7)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            if (r6 == 0) goto L_0x016a
            java.lang.String[] r6 = new java.lang.String[r14]     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            createLibraryLink(r4, r1, r0, r6)     // Catch:{ UnsatisfiedLinkError -> 0x0168 }
            goto L_0x016a
        L_0x0168:
            r0 = move-exception
            r13 = r0
        L_0x016a:
            r6 = 1
            goto L_0x00fe
        L_0x016c:
            java.lang.String r0 = "platform.executable"
            java.util.List r4 = r1.get(r0)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r0 = r4.size()
            if (r0 <= 0) goto L_0x02c9
            java.lang.String r0 = r1.getProperty(r5)
            java.lang.String r5 = "platform.extension"
            java.util.List r5 = r1.get(r5)
            java.lang.String[] r15 = new java.lang.String[r14]
            java.lang.Object[] r5 = r5.toArray(r15)
            java.lang.String[] r5 = (java.lang.String[]) r5
            java.lang.String r15 = "platform.executable.prefix"
            java.lang.String r12 = ""
            java.lang.String r15 = r1.getProperty(r15, r12)
            java.lang.String r14 = "platform.executable.suffix"
            java.lang.String r14 = r1.getProperty(r14, r12)
            r16 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r15)
            r17 = r11
            r11 = 0
            java.lang.Object r18 = r4.get(r11)
            r11 = r18
            java.lang.String r11 = (java.lang.String) r11
            r13.append(r11)
            r13.append(r14)
            java.lang.String r11 = r13.toString()
            java.lang.String r13 = "platform.library.path"
            java.lang.String r13 = r1.getProperty(r13, r12)
            int r18 = r13.length()     // Catch:{ IOException -> 0x02a9 }
            if (r18 <= 0) goto L_0x0206
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException -> 0x02a9 }
        L_0x01cc:
            boolean r18 = r9.hasNext()     // Catch:{ IOException -> 0x02a9 }
            if (r18 == 0) goto L_0x0206
            java.lang.Object r18 = r9.next()     // Catch:{ IOException -> 0x02a9 }
            r19 = r9
            r9 = r18
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ IOException -> 0x02a9 }
            r18 = r11
            r11 = 1
            java.net.URL[] r9 = findLibrary(r3, r1, r9, r11)     // Catch:{ IOException -> 0x02a5 }
            int r11 = r9.length     // Catch:{ IOException -> 0x02a5 }
            r20 = r12
            r12 = 0
        L_0x01e7:
            if (r12 >= r11) goto L_0x01ff
            r21 = r9[r12]     // Catch:{ IOException -> 0x02a5 }
            r22 = r9
            java.io.File r9 = cacheResource((java.net.URL) r21)     // Catch:{ IOException -> 0x02a5 }
            if (r9 == 0) goto L_0x01f8
            r11 = 1
            r9.setExecutable(r11)     // Catch:{ IOException -> 0x02a5 }
            goto L_0x01ff
        L_0x01f8:
            r21 = r11
            int r12 = r12 + 1
            r9 = r22
            goto L_0x01e7
        L_0x01ff:
            r11 = r18
            r9 = r19
            r12 = r20
            goto L_0x01cc
        L_0x0206:
            r18 = r11
            r20 = r12
            java.util.Iterator r9 = r4.iterator()     // Catch:{ IOException -> 0x02a5 }
            r11 = r18
        L_0x0210:
            boolean r12 = r9.hasNext()     // Catch:{ IOException -> 0x02a3 }
            if (r12 == 0) goto L_0x02cd
            java.lang.Object r12 = r9.next()     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ IOException -> 0x02a3 }
            r19 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x02a3 }
            r9.<init>()     // Catch:{ IOException -> 0x02a3 }
            r9.append(r15)     // Catch:{ IOException -> 0x02a3 }
            r9.append(r12)     // Catch:{ IOException -> 0x02a3 }
            r9.append(r14)     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r11 = r9.toString()     // Catch:{ IOException -> 0x02a3 }
            int r9 = r5.length     // Catch:{ IOException -> 0x02a3 }
            r12 = 1
            int r9 = r9 - r12
        L_0x0233:
            r12 = -1
            if (r9 < r12) goto L_0x029f
            if (r9 < 0) goto L_0x023b
            r18 = r5[r9]     // Catch:{ IOException -> 0x02a3 }
            goto L_0x023d
        L_0x023b:
            r18 = r20
        L_0x023d:
            int r21 = r13.length()     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r12 = "/"
            if (r21 <= 0) goto L_0x0259
            r21 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x02a3 }
            r5.<init>()     // Catch:{ IOException -> 0x02a3 }
            r5.append(r12)     // Catch:{ IOException -> 0x02a3 }
            r5.append(r13)     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x02a3 }
            r22 = r0
            goto L_0x0273
        L_0x0259:
            r21 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x02a3 }
            r5.<init>()     // Catch:{ IOException -> 0x02a3 }
            r5.append(r0)     // Catch:{ IOException -> 0x02a3 }
            r22 = r0
            if (r18 != 0) goto L_0x026a
            r0 = r20
            goto L_0x026c
        L_0x026a:
            r0 = r18
        L_0x026c:
            r5.append(r0)     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x02a3 }
        L_0x0273:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x02a3 }
            r0.<init>()     // Catch:{ IOException -> 0x02a3 }
            r0.append(r5)     // Catch:{ IOException -> 0x02a3 }
            r0.append(r12)     // Catch:{ IOException -> 0x02a3 }
            r0.append(r11)     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x02a3 }
            java.io.File r0 = cacheResource((java.lang.Class) r3, (java.lang.String) r0)     // Catch:{ IOException -> 0x02a3 }
            if (r0 == 0) goto L_0x0297
            r5 = 1
            r0.setExecutable(r5)     // Catch:{ IOException -> 0x02a3 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x02a3 }
            r6.add(r0)     // Catch:{ IOException -> 0x02a3 }
            goto L_0x0298
        L_0x0297:
            r5 = 1
        L_0x0298:
            int r9 = r9 + -1
            r5 = r21
            r0 = r22
            goto L_0x0233
        L_0x029f:
            r9 = r19
            goto L_0x0210
        L_0x02a3:
            r0 = move-exception
            goto L_0x02ac
        L_0x02a5:
            r0 = move-exception
            r11 = r18
            goto L_0x02ac
        L_0x02a9:
            r0 = move-exception
            r18 = r11
        L_0x02ac:
            org.bytedeco.javacpp.tools.Logger r5 = logger
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "Could not extract executable "
            r9.append(r12)
            r9.append(r11)
            r9.append(r8)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r5.error(r0)
            goto L_0x02cd
        L_0x02c9:
            r17 = r11
            r16 = r13
        L_0x02cd:
            int r0 = r4.size()
            if (r0 <= 0) goto L_0x02e4
            int r0 = r6.size()
            if (r0 <= 0) goto L_0x02e2
            r1 = 0
            java.lang.Object r0 = r6.get(r1)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x02e3
        L_0x02e2:
            r4 = 0
        L_0x02e3:
            return r4
        L_0x02e4:
            r12 = -1
        L_0x02e5:
            java.lang.String r0 = "platform.library"
            java.lang.String r0 = r1.getProperty(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            if (r12 < 0) goto L_0x0304
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4.append(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.String r5 = "#"
            r4.append(r5)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4.append(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4.append(r12)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.String r0 = r4.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
        L_0x0304:
            java.util.Map<java.lang.String, java.net.URL[]> r4 = foundLibraries     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.Object r4 = r4.get(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.net.URL[] r4 = (java.net.URL[]) r4     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            if (r4 != 0) goto L_0x0318
            java.util.Map<java.lang.String, java.net.URL[]> r4 = foundLibraries     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.net.URL[] r5 = findLibrary(r3, r1, r0, r2)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4.put(r0, r5)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            r4 = r5
        L_0x0318:
            int r5 = r10.size()     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.Object[] r5 = r10.toArray(r5)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            java.lang.String r4 = loadLibrary(r3, r4, r0, r5)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            if (r7 == 0) goto L_0x0339
            if (r4 == 0) goto L_0x0339
            boolean r5 = r4.startsWith(r7)     // Catch:{ UnsatisfiedLinkError -> 0x0351 }
            if (r5 == 0) goto L_0x0339
            r5 = 0
            java.lang.String[] r6 = new java.lang.String[r5]     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            createLibraryLink(r4, r1, r0, r6)     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            goto L_0x033a
        L_0x0339:
            r5 = 0
        L_0x033a:
            java.util.Iterator r0 = r17.iterator()     // Catch:{ UnsatisfiedLinkError -> 0x034f }
        L_0x033e:
            boolean r6 = r0.hasNext()     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            if (r6 == 0) goto L_0x034e
            java.lang.Object r6 = r0.next()     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            loadGlobal(r6)     // Catch:{ UnsatisfiedLinkError -> 0x034f }
            goto L_0x033e
        L_0x034e:
            return r4
        L_0x034f:
            r0 = move-exception
            goto L_0x0353
        L_0x0351:
            r0 = move-exception
            r5 = 0
        L_0x0353:
            r4 = r0
        L_0x0354:
            if (r4 == 0) goto L_0x0377
            boolean r6 = r4 instanceof java.lang.UnsatisfiedLinkError
            if (r6 == 0) goto L_0x036a
            java.lang.String r6 = r4.getMessage()
            java.lang.String r8 = "already loaded in another classloader"
            boolean r6 = r6.contains(r8)
            if (r6 == 0) goto L_0x036a
            int r12 = r12 + 1
            goto L_0x02e5
        L_0x036a:
            java.lang.Throwable r6 = r4.getCause()
            if (r6 == r4) goto L_0x0375
            java.lang.Throwable r4 = r4.getCause()
            goto L_0x0354
        L_0x0375:
            r4 = 0
            goto L_0x0354
        L_0x0377:
            if (r16 == 0) goto L_0x0384
            java.lang.Throwable r1 = r0.getCause()
            if (r1 != 0) goto L_0x0384
            r13 = r16
            r0.initCause(r13)
        L_0x0384:
            throw r0
        L_0x0385:
            java.lang.UnsatisfiedLinkError r2 = new java.lang.UnsatisfiedLinkError
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Platform \""
            r3.append(r4)
            java.lang.String r1 = r1.getProperty(r5)
            r3.append(r1)
            java.lang.String r1 = "\" not supported by "
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x03a8:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.load(java.lang.Class, java.util.Properties, boolean):java.lang.String");
    }

    public static URL[] findLibrary(Class cls, ClassProperties classProperties, String str) {
        return findLibrary(cls, classProperties, str, pathsFirst);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.URL[] findLibrary(java.lang.Class r23, org.bytedeco.javacpp.ClassProperties r24, java.lang.String r25, boolean r26) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            java.lang.String r3 = ":"
            boolean r4 = r2.startsWith(r3)
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0016
            java.lang.String r2 = r2.substring(r6)
        L_0x0014:
            r3 = 1
            goto L_0x0027
        L_0x0016:
            boolean r4 = r2.contains(r3)
            if (r4 == 0) goto L_0x0026
            int r3 = r2.indexOf(r3)
            int r3 = r3 + r6
            java.lang.String r2 = r2.substring(r3)
            goto L_0x0014
        L_0x0026:
            r3 = 0
        L_0x0027:
            java.lang.String r4 = "!"
            boolean r4 = r2.endsWith(r4)
            if (r4 == 0) goto L_0x0038
            int r4 = r2.length()
            int r4 = r4 - r6
            java.lang.String r2 = r2.substring(r5, r4)
        L_0x0038:
            java.lang.String r4 = r2.trim()
            java.lang.String r7 = "#"
            boolean r4 = r4.endsWith(r7)
            if (r4 == 0) goto L_0x0053
            java.lang.String r4 = r2.trim()
            java.lang.String r8 = "##"
            boolean r4 = r4.endsWith(r8)
            if (r4 != 0) goto L_0x0053
            java.net.URL[] r0 = new java.net.URL[r5]
            return r0
        L_0x0053:
            java.lang.String[] r2 = r2.split(r7)
            int r4 = r2.length
            if (r4 <= r6) goto L_0x0064
            r4 = r2[r6]
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x0064
            r4 = 1
            goto L_0x0065
        L_0x0064:
            r4 = 0
        L_0x0065:
            r8 = r2[r5]
            java.lang.String r9 = "@"
            java.lang.String[] r8 = r8.split(r9)
            if (r4 == 0) goto L_0x0072
            r2 = r2[r6]
            goto L_0x0074
        L_0x0072:
            r2 = r2[r5]
        L_0x0074:
            java.lang.String[] r2 = r2.split(r9)
            r9 = r8[r5]
            r10 = r2[r5]
            int r11 = r8.length
            java.lang.String r12 = ""
            if (r11 <= r6) goto L_0x0086
            int r11 = r8.length
            int r11 = r11 - r6
            r8 = r8[r11]
            goto L_0x0087
        L_0x0086:
            r8 = r12
        L_0x0087:
            int r11 = r2.length
            if (r11 <= r6) goto L_0x008f
            int r11 = r2.length
            int r11 = r11 - r6
            r2 = r2[r11]
            goto L_0x0090
        L_0x008f:
            r2 = r12
        L_0x0090:
            java.lang.String r11 = "platform"
            java.lang.String r11 = r1.getProperty(r11)
            java.lang.String r13 = "platform.extension"
            java.util.List r13 = r1.get(r13)
            java.lang.String[] r14 = new java.lang.String[r5]
            java.lang.Object[] r13 = r13.toArray(r14)
            java.lang.String[] r13 = (java.lang.String[]) r13
            java.lang.String r14 = "platform.library.prefix"
            java.lang.String r14 = r1.getProperty(r14, r12)
            java.lang.String r15 = "platform.library.suffix"
            java.lang.String r6 = r1.getProperty(r15, r12)
            r5 = 3
            r25 = r7
            java.lang.String[] r7 = new java.lang.String[r5]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r9)
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r17 = 0
            r7[r17] = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r9)
            r5.append(r8)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r16 = 1
            r7[r16] = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r9)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r19 = 2
            r7[r19] = r5
            r20 = r7
            r5 = 3
            java.lang.String[] r7 = new java.lang.String[r5]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r10)
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r17 = 0
            r7[r17] = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r10)
            r5.append(r2)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r16 = 1
            r7[r16] = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            r5.append(r10)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r7[r19] = r5
            java.util.List r5 = r1.get(r15)
            r6 = 0
            java.lang.String[] r15 = new java.lang.String[r6]
            java.lang.Object[] r5 = r5.toArray(r15)
            java.lang.String[] r5 = (java.lang.String[]) r5
            int r6 = r5.length
            r15 = 1
            if (r6 <= r15) goto L_0x0219
            int r6 = r5.length
            r7 = 3
            int r6 = r6 * 3
            java.lang.String[] r6 = new java.lang.String[r6]
            int r15 = r5.length
            int r15 = r15 * 3
            java.lang.String[] r7 = new java.lang.String[r15]
            r18 = r11
            r15 = 0
        L_0x0167:
            int r11 = r5.length
            if (r15 >= r11) goto L_0x0212
            int r11 = r15 * 3
            r19 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r14)
            r13.append(r9)
            r0 = r5[r15]
            r13.append(r0)
            r13.append(r8)
            java.lang.String r0 = r13.toString()
            r6[r11] = r0
            int r0 = r11 + 1
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r14)
            r13.append(r9)
            r13.append(r8)
            r21 = r8
            r8 = r5[r15]
            r13.append(r8)
            java.lang.String r8 = r13.toString()
            r6[r0] = r8
            int r8 = r11 + 2
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r14)
            r13.append(r9)
            r22 = r4
            r4 = r5[r15]
            r13.append(r4)
            java.lang.String r4 = r13.toString()
            r6[r8] = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r10)
            r13 = r5[r15]
            r4.append(r13)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r7[r11] = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r10)
            r4.append(r2)
            r11 = r5[r15]
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r7[r0] = r4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r14)
            r0.append(r10)
            r4 = r5[r15]
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r7[r8] = r0
            int r15 = r15 + 1
            r0 = r23
            r13 = r19
            r8 = r21
            r4 = r22
            goto L_0x0167
        L_0x0212:
            r22 = r4
            r19 = r13
            r0 = r7
            r7 = r6
            goto L_0x0222
        L_0x0219:
            r22 = r4
            r18 = r11
            r19 = r13
            r0 = r7
            r7 = r20
        L_0x0222:
            if (r3 == 0) goto L_0x022f
            r2 = 1
            java.lang.String[] r7 = new java.lang.String[r2]
            r0 = 0
            r7[r0] = r9
            java.lang.String[] r3 = new java.lang.String[r2]
            r3[r0] = r10
            r0 = r3
        L_0x022f:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r3 = "platform.linkpath"
            java.util.List r3 = r1.get(r3)
            r2.addAll(r3)
            java.lang.String r3 = "platform.preloadpath"
            java.util.List r3 = r1.get(r3)
            r2.addAll(r3)
            java.lang.String r3 = "platform.preloadresource"
            java.util.List r3 = r1.get(r3)
            java.lang.String r4 = "platform.library.path"
            java.lang.String r1 = r1.getProperty(r4, r12)
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x025f
            if (r26 == 0) goto L_0x025f
            r4 = 0
            r3.add(r4, r1)
            goto L_0x0260
        L_0x025f:
            r4 = 0
        L_0x0260:
            r5 = 0
            r3.add(r5)
            java.lang.String r5 = "java.library.path"
            java.lang.String r5 = java.lang.System.getProperty(r5, r12)
            int r6 = r5.length()
            if (r6 <= 0) goto L_0x0287
            if (r26 != 0) goto L_0x027a
            boolean r6 = isLoadLibraries()
            if (r6 == 0) goto L_0x027a
            if (r22 == 0) goto L_0x0287
        L_0x027a:
            java.lang.String r6 = java.io.File.pathSeparator
            java.lang.String[] r5 = r5.split(r6)
            java.util.List r5 = java.util.Arrays.asList(r5)
            r2.addAll(r5)
        L_0x0287:
            java.util.ArrayList r5 = new java.util.ArrayList
            int r6 = r7.length
            int r8 = r2.size()
            r9 = 1
            int r8 = r8 + r9
            int r6 = r6 * r8
            r5.<init>(r6)
            r6 = 0
        L_0x0296:
            java.lang.String r8 = "ref"
            r10 = r23
            if (r10 == 0) goto L_0x03c0
            int r11 = r7.length
            if (r6 >= r11) goto L_0x03c0
            r13 = r19
            int r11 = r13.length
            int r11 = r11 - r9
        L_0x02a3:
            r9 = -1
            if (r11 < r9) goto L_0x03b0
            if (r11 < 0) goto L_0x02ab
            r9 = r13[r11]
            goto L_0x02ac
        L_0x02ab:
            r9 = r12
        L_0x02ac:
            java.util.Iterator r14 = r3.iterator()
        L_0x02b0:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x03a3
            java.lang.Object r15 = r14.next()
            java.lang.String r15 = (java.lang.String) r15
            java.lang.String r4 = "/"
            if (r15 == 0) goto L_0x02d8
            boolean r19 = r15.endsWith(r4)
            if (r19 != 0) goto L_0x02d8
            r19 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r15)
            r3.append(r4)
            java.lang.String r15 = r3.toString()
            goto L_0x02da
        L_0x02d8:
            r19 = r3
        L_0x02da:
            int r3 = r1.length()
            if (r3 <= 0) goto L_0x02fa
            boolean r3 = r1.equals(r15)
            if (r3 == 0) goto L_0x02fa
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            r24 = r1
            r1 = r18
            goto L_0x0328
        L_0x02fa:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r24 = r1
            if (r15 != 0) goto L_0x0305
            r1 = r12
            goto L_0x0314
        L_0x0305:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
        L_0x0314:
            r3.append(r1)
            r1 = r18
            r3.append(r1)
            if (r9 != 0) goto L_0x0320
            r15 = r12
            goto L_0x0321
        L_0x0320:
            r15 = r9
        L_0x0321:
            r3.append(r15)
            java.lang.String r3 = r3.toString()
        L_0x0328:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.<init>()     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.append(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.append(r4)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r3 = r7[r6]     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.append(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            java.lang.String r3 = r15.toString()     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            java.net.URL r3 = findResource(r10, r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            if (r3 == 0) goto L_0x038b
            if (r22 == 0) goto L_0x037d
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.<init>()     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.append(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r3 = r25
            r15.append(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r18 = r1
            r1 = r0[r6]     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15.append(r1)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            java.lang.String r1 = r15.toString()     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r4.<init>(r1)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            boolean r1 = r1.contains(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            if (r1 != 0) goto L_0x037a
            java.lang.Class<java.net.URL> r1 = java.net.URL.class
            java.lang.reflect.Field r1 = r1.getDeclaredField(r8)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15 = 1
            r1.setAccessible(r15)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r15 = r0[r6]     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            r1.set(r4, r15)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
        L_0x037a:
            r1 = r3
            r3 = r4
            goto L_0x0381
        L_0x037d:
            r18 = r1
            r1 = r25
        L_0x0381:
            boolean r4 = r5.contains(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            if (r4 != 0) goto L_0x038f
            r5.add(r3)     // Catch:{ IOException -> 0x039c, NoSuchFieldException -> 0x039a, IllegalAccessException -> 0x0398 }
            goto L_0x038f
        L_0x038b:
            r18 = r1
            r1 = r25
        L_0x038f:
            r25 = r1
            r3 = r19
            r4 = 0
            r1 = r24
            goto L_0x02b0
        L_0x0398:
            r0 = move-exception
            goto L_0x039d
        L_0x039a:
            r0 = move-exception
            goto L_0x039d
        L_0x039c:
            r0 = move-exception
        L_0x039d:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x03a3:
            r24 = r1
            r19 = r3
            r1 = r25
            int r11 = r11 + -1
            r4 = 0
            r1 = r24
            goto L_0x02a3
        L_0x03b0:
            r24 = r1
            r19 = r3
            r1 = r25
            int r6 = r6 + 1
            r4 = 0
            r9 = 1
            r1 = r24
            r19 = r13
            goto L_0x0296
        L_0x03c0:
            r1 = r25
            if (r26 == 0) goto L_0x03c6
            r3 = 0
            goto L_0x03ca
        L_0x03c6:
            int r3 = r5.size()
        L_0x03ca:
            r4 = 0
        L_0x03cb:
            int r6 = r2.size()
            if (r6 <= 0) goto L_0x0451
            int r6 = r7.length
            if (r4 >= r6) goto L_0x0451
            java.util.Iterator r6 = r2.iterator()
        L_0x03d8:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x044c
            java.lang.Object r9 = r6.next()
            java.lang.String r9 = (java.lang.String) r9
            java.io.File r10 = new java.io.File
            r11 = r7[r4]
            r10.<init>(r9, r11)
            boolean r9 = r10.exists()
            if (r9 == 0) goto L_0x044a
            java.net.URI r9 = r10.toURI()     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            java.net.URL r9 = r9.toURL()     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            if (r22 == 0) goto L_0x0431
            java.net.URL r10 = new java.net.URL     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r11.<init>()     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r11.append(r9)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r11.append(r1)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r9 = r0[r4]     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r11.append(r9)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            java.lang.String r9 = r11.toString()     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r10.<init>(r9)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            java.lang.String r9 = r10.toString()     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            boolean r9 = r9.contains(r1)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            if (r9 != 0) goto L_0x042e
            java.lang.Class<java.net.URL> r9 = java.net.URL.class
            java.lang.reflect.Field r9 = r9.getDeclaredField(r8)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r11 = 1
            r9.setAccessible(r11)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r12 = r0[r4]     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r9.set(r10, r12)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            goto L_0x042f
        L_0x042e:
            r11 = 1
        L_0x042f:
            r9 = r10
            goto L_0x0432
        L_0x0431:
            r11 = 1
        L_0x0432:
            boolean r10 = r5.contains(r9)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            if (r10 != 0) goto L_0x03d8
            int r10 = r3 + 1
            r5.add(r3, r9)     // Catch:{ IOException -> 0x0443, NoSuchFieldException -> 0x0441, IllegalAccessException -> 0x043f }
            r3 = r10
            goto L_0x03d8
        L_0x043f:
            r0 = move-exception
            goto L_0x0444
        L_0x0441:
            r0 = move-exception
            goto L_0x0444
        L_0x0443:
            r0 = move-exception
        L_0x0444:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x044a:
            r11 = 1
            goto L_0x03d8
        L_0x044c:
            r11 = 1
            int r4 = r4 + 1
            goto L_0x03cb
        L_0x0451:
            int r0 = r5.size()
            java.net.URL[] r0 = new java.net.URL[r0]
            java.lang.Object[] r0 = r5.toArray(r0)
            java.net.URL[] r0 = (java.net.URL[]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.findLibrary(java.lang.Class, org.bytedeco.javacpp.ClassProperties, java.lang.String, boolean):java.net.URL[]");
    }

    public static String loadLibrary(String str, String... strArr) {
        return loadLibrary((Class<?>) getCallerClass(2), str, strArr);
    }

    public static String loadLibrary(Class<?> cls, String str, String... strArr) {
        try {
            return loadLibrary(findResources(cls, str), str, strArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadLibrary(URL[] urlArr, String str, String... strArr) {
        return loadLibrary((Class<?>) null, urlArr, str, strArr);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:40|41|(4:43|44|47|48)|49|(4:58|59|(5:61|(1:116)(11:65|66|67|68|69|70|(3:72|73|(3:75|76|77))|83|84|85|(5:89|(6:91|92|93|94|95|96)(1:101)|102|271|118))|117|269|118)|268)|119) */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0185, code lost:
        r19 = r14;
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0189, code lost:
        r9.delete();
        java.nio.file.Files.createSymbolicLink(r7, r2, new java.nio.file.attribute.FileAttribute[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0193, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0197, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x019b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x019f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01a2, code lost:
        r18 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01a4, code lost:
        r17 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01a6, code lost:
        r19 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01a8, code lost:
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01aa, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:?, code lost:
        r7 = logger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01b1, code lost:
        if (r7.isDebugEnabled() == false) goto L_0x01e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01b3, code lost:
        r7.debug("Failed to create symbolic link " + r9 + " to " + r4 + ": " + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01d8, code lost:
        r18 = r2;
        r16 = r7;
        r17 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01de, code lost:
        r19 = r14;
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01e2, code lost:
        r8 = r8 + 1;
        r4 = r24;
        r7 = r16;
        r12 = r17;
        r2 = r18;
        r14 = r19;
        r15 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01f2, code lost:
        r17 = r12;
        r19 = r14;
        r20 = r15;
        r15 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x026b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:?, code lost:
        r4 = r0.getTargetException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0273, code lost:
        if ((r4 instanceof java.lang.UnsatisfiedLinkError) == false) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0275, code lost:
        logger.warn("Unable to load the library " + r11 + " within the ClassLoader scope of " + r21.getName() + " because: " + r4.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x02a6, code lost:
        throw ((java.lang.UnsatisfiedLinkError) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x02a7, code lost:
        logger.warn("Unable to load the library " + r11 + " within the ClassLoader scope of " + r21.getName());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02e1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02e2, code lost:
        r14 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:?, code lost:
        loadedLibraries.remove(r11);
        r4 = logger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x02ee, code lost:
        if (r4.isDebugEnabled() != false) goto L_0x02f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02f0, code lost:
        r4.debug("Failed to load " + r2 + ": " + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x031c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0326, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0327, code lost:
        r19 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0329, code lost:
        r1 = r0;
        r7 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0448, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x044e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0451, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x047e, code lost:
        r1.debug("Failed to extract for " + r3 + ": " + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x04b6, code lost:
        r2.debug("Failed to load for " + r3 + ": " + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:?, code lost:
        classStack.get().pop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x04de, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0087, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        r1 = r0;
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008f, code lost:
        r1 = r0;
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r7 = cacheResource(r7, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0097, code lost:
        if (r7 == null) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r7 = new java.io.File(new java.net.URI(r9.toString().split("#")[0]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b3, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r7 = new java.io.File(r9.getPath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00be, code lost:
        r15 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r7 = logger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c6, code lost:
        if (r7.isDebugEnabled() != false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r7.debug("Failed to access " + r9 + ": " + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e4, code lost:
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r2 = r15.getParentFile();
        r7 = r4.length;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ef, code lost:
        if (r8 < r7) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f1, code lost:
        r4 = new java.io.File(r4[r8]);
        r9 = r4.getParentFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00fc, code lost:
        if (r9 == null) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0104, code lost:
        r16 = r7;
        r9 = new java.io.File(r2, r4.getName());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r7 = r9.toPath().normalize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011b, code lost:
        r18 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r2 = r4.toPath().normalize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0125, code lost:
        if (r9.exists() != false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x012b, code lost:
        if (java.nio.file.Files.isSymbolicLink(r7) != false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x012d, code lost:
        r17 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0137, code lost:
        if (java.nio.file.Files.readSymbolicLink(r7).equals(r2) == false) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x013a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x013e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0141, code lost:
        r17 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0143, code lost:
        r2 = r0;
        r19 = r14;
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x014a, code lost:
        r17 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0158, code lost:
        r12 = logger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x015e, code lost:
        if (r12.isDebugEnabled() != false) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0160, code lost:
        r19 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r14 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0167, code lost:
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r14.append("Creating symbolic link ");
        r14.append(r7);
        r14.append(" to ");
        r14.append(r2);
        r12.debug(r14.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0181, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0183, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0093 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00b5 */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01b3 A[Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02cc A[Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b, UnsatisfiedLinkError -> 0x02e1, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02d1 A[Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b, UnsatisfiedLinkError -> 0x02e1, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x031c A[ExcHandler: URISyntaxException (e java.net.URISyntaxException), PHI: r14 
      PHI: (r14v4 java.lang.UnsatisfiedLinkError) = (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError) binds: [B:30:0x0076, B:40:0x0093, B:58:0x00e9, B:66:0x010f, B:69:0x011d, B:84:0x014c, B:50:0x00c0] A[DONT_GENERATE, DONT_INLINE], Splitter:B:40:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0326 A[ExcHandler: UnsatisfiedLinkError (e java.lang.UnsatisfiedLinkError), PHI: r14 
      PHI: (r14v2 java.lang.UnsatisfiedLinkError) = (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError) binds: [B:30:0x0076, B:40:0x0093, B:58:0x00e9, B:66:0x010f, B:69:0x011d, B:84:0x014c, B:50:0x00c0] A[DONT_GENERATE, DONT_INLINE], Splitter:B:30:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x03d1 A[Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0400 A[Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x042a A[Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, IOException | URISyntaxException -> 0x0448 }] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0448 A[ExcHandler: IOException | URISyntaxException (e java.lang.Throwable), PHI: r19 
      PHI: (r19v2 java.lang.UnsatisfiedLinkError) = (r19v0 java.lang.UnsatisfiedLinkError), (r19v0 java.lang.UnsatisfiedLinkError), (r19v7 java.lang.UnsatisfiedLinkError), (r19v7 java.lang.UnsatisfiedLinkError), (r19v7 java.lang.UnsatisfiedLinkError), (r19v11 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError) binds: [B:182:0x033f, B:206:0x03c9, B:126:0x020b, B:130:0x0215, B:144:0x026d, B:112:0x01ab, B:92:0x0162, B:93:?, B:95:0x016b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:92:0x0162] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x044e A[ExcHandler: UnsatisfiedLinkError (e java.lang.UnsatisfiedLinkError), PHI: r19 
      PHI: (r19v1 java.lang.UnsatisfiedLinkError) = (r19v0 java.lang.UnsatisfiedLinkError), (r19v0 java.lang.UnsatisfiedLinkError), (r19v7 java.lang.UnsatisfiedLinkError), (r19v11 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError), (r19v18 java.lang.UnsatisfiedLinkError) binds: [B:182:0x033f, B:206:0x03c9, B:126:0x020b, B:112:0x01ab, B:92:0x0162, B:93:?, B:95:0x016b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:92:0x0162] */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x047e A[Catch:{ all -> 0x0451, all -> 0x04df }] */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x04b6 A[Catch:{ all -> 0x0451, all -> 0x04df }] */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x01e2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0087 A[ExcHandler: URISyntaxException (e java.net.URISyntaxException), PHI: r14 
      PHI: (r14v18 java.lang.UnsatisfiedLinkError) = (r14v7 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError) binds: [B:163:0x02e3, B:72:0x0127, B:76:0x012f, B:53:0x00c8, B:54:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:53:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089 A[ExcHandler: IOException (e java.io.IOException), PHI: r14 
      PHI: (r14v17 java.lang.UnsatisfiedLinkError) = (r14v7 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError) binds: [B:163:0x02e3, B:43:0x009a, B:53:0x00c8, B:54:?, B:44:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:43:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008e A[ExcHandler: UnsatisfiedLinkError (r0v31 'e' java.lang.UnsatisfiedLinkError A[CUSTOM_DECLARE]), PHI: r14 
      PHI: (r14v15 java.lang.UnsatisfiedLinkError) = (r14v7 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v9 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError), (r14v1 java.lang.UnsatisfiedLinkError) binds: [B:163:0x02e3, B:72:0x0127, B:76:0x012f, B:43:0x009a, B:53:0x00c8, B:54:?, B:44:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:43:0x009a] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:157:0x02d4=Splitter:B:157:0x02d4, B:219:0x042d=Splitter:B:219:0x042d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String loadLibrary(java.lang.Class<?> r21, java.net.URL[] r22, java.lang.String r23, java.lang.String... r24) {
        /*
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            java.lang.Class<org.bytedeco.javacpp.Loader> r5 = org.bytedeco.javacpp.Loader.class
            monitor-enter(r5)
            boolean r6 = isLoadLibraries()     // Catch:{ all -> 0x04df }
            r7 = 0
            if (r6 != 0) goto L_0x0014
            monitor-exit(r5)
            return r7
        L_0x0014:
            java.lang.String r6 = ":"
            boolean r6 = r3.startsWith(r6)     // Catch:{ all -> 0x04df }
            r8 = 0
            r9 = 1
            if (r6 == 0) goto L_0x0023
            java.lang.String r3 = r3.substring(r9)     // Catch:{ all -> 0x04df }
            goto L_0x0035
        L_0x0023:
            java.lang.String r6 = ":"
            boolean r6 = r3.contains(r6)     // Catch:{ all -> 0x04df }
            if (r6 == 0) goto L_0x0035
            java.lang.String r6 = ":"
            int r6 = r3.indexOf(r6)     // Catch:{ all -> 0x04df }
            java.lang.String r3 = r3.substring(r8, r6)     // Catch:{ all -> 0x04df }
        L_0x0035:
            java.lang.String r6 = "!"
            boolean r6 = r3.endsWith(r6)     // Catch:{ all -> 0x04df }
            if (r6 == 0) goto L_0x0048
            int r6 = r3.length()     // Catch:{ all -> 0x04df }
            int r6 = r6 - r9
            java.lang.String r3 = r3.substring(r8, r6)     // Catch:{ all -> 0x04df }
            r6 = 1
            goto L_0x0049
        L_0x0048:
            r6 = 0
        L_0x0049:
            java.lang.String r10 = "#"
            java.lang.String[] r10 = r3.split(r10)     // Catch:{ all -> 0x04df }
            r11 = r10[r8]     // Catch:{ all -> 0x04df }
            int r12 = r10.length     // Catch:{ all -> 0x04df }
            if (r12 <= r9) goto L_0x005e
            r12 = r10[r9]     // Catch:{ all -> 0x04df }
            int r12 = r12.length()     // Catch:{ all -> 0x04df }
            if (r12 <= 0) goto L_0x005e
            r11 = r10[r9]     // Catch:{ all -> 0x04df }
        L_0x005e:
            java.util.Map<java.lang.String, java.lang.String> r10 = loadedLibraries     // Catch:{ all -> 0x04df }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x04df }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x04df }
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r12 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r12 = r12.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r12 = (java.util.Deque) r12     // Catch:{ all -> 0x04df }
            r12.push(r1)     // Catch:{ all -> 0x04df }
            int r12 = r2.length     // Catch:{ UnsatisfiedLinkError -> 0x049b, IOException -> 0x0457, URISyntaxException -> 0x0455 }
            r14 = r7
            r13 = 0
        L_0x0074:
            if (r13 >= r12) goto L_0x032e
            r7 = r2[r13]     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            java.net.URI r9 = r7.toURI()     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            java.io.File r15 = new java.io.File     // Catch:{ Exception -> 0x0093 }
            r15.<init>(r9)     // Catch:{ Exception -> 0x0093 }
            r17 = r12
            r19 = r14
            goto L_0x01fa
        L_0x0087:
            r0 = move-exception
            goto L_0x008a
        L_0x0089:
            r0 = move-exception
        L_0x008a:
            r1 = r0
            r7 = r14
            goto L_0x045a
        L_0x008e:
            r0 = move-exception
            r1 = r0
            r7 = r14
            goto L_0x049e
        L_0x0093:
            java.io.File r7 = cacheResource((java.net.URL) r7, (java.lang.String) r10)     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            if (r7 == 0) goto L_0x009a
            goto L_0x00be
        L_0x009a:
            java.io.File r7 = new java.io.File     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            java.net.URI r15 = new java.net.URI     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            java.lang.String r8 = r9.toString()     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            java.lang.String r2 = "#"
            java.lang.String[] r2 = r8.split(r2)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            r8 = 0
            r2 = r2[r8]     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            r15.<init>(r2)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            r7.<init>(r15)     // Catch:{ IllegalArgumentException | URISyntaxException -> 0x00b5, Exception -> 0x00b2, UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089 }
            goto L_0x00be
        L_0x00b2:
            r0 = move-exception
            r2 = r0
            goto L_0x00c0
        L_0x00b5:
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r2 = r9.getPath()     // Catch:{ Exception -> 0x00b2 }
            r7.<init>(r2)     // Catch:{ Exception -> 0x00b2 }
        L_0x00be:
            r15 = r7
            goto L_0x00e5
        L_0x00c0:
            org.bytedeco.javacpp.tools.Logger r7 = logger     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            boolean r8 = r7.isDebugEnabled()     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            if (r8 == 0) goto L_0x00e4
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r8.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r15 = "Failed to access "
            r8.append(r15)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r9 = ": "
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r8.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r2 = r8.toString()     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r7.debug(r2)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
        L_0x00e4:
            r15 = 0
        L_0x00e5:
            if (r15 == 0) goto L_0x01f2
            if (r4 == 0) goto L_0x01f2
            java.io.File r2 = r15.getParentFile()     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            int r7 = r4.length     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            r8 = 0
        L_0x00ef:
            if (r8 >= r7) goto L_0x01f2
            r9 = r4[r8]     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            java.io.File r4 = new java.io.File     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            r4.<init>(r9)     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            java.io.File r9 = r4.getParentFile()     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            if (r9 == 0) goto L_0x01d8
            boolean r9 = r9.equals(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            if (r9 != 0) goto L_0x01d8
            java.io.File r9 = new java.io.File     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            r16 = r7
            java.lang.String r7 = r4.getName()     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            r9.<init>(r2, r7)     // Catch:{ UnsatisfiedLinkError -> 0x0326, IOException -> 0x031e, URISyntaxException -> 0x031c }
            java.nio.file.Path r7 = r9.toPath()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            java.nio.file.Path r7 = r7.normalize()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            java.nio.file.Path r17 = r4.toPath()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            r18 = r2
            java.nio.file.Path r2 = r17.normalize()     // Catch:{ IOException -> 0x019d, RuntimeException -> 0x019b, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            boolean r17 = r9.exists()     // Catch:{ IOException -> 0x019d, RuntimeException -> 0x019b, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            if (r17 == 0) goto L_0x014a
            boolean r17 = java.nio.file.Files.isSymbolicLink(r7)     // Catch:{ IOException -> 0x0140, RuntimeException -> 0x013e, UnsatisfiedLinkError -> 0x008e, URISyntaxException -> 0x0087 }
            if (r17 == 0) goto L_0x014a
            r17 = r12
            java.nio.file.Path r12 = java.nio.file.Files.readSymbolicLink(r7)     // Catch:{ IOException -> 0x013c, RuntimeException -> 0x013a, UnsatisfiedLinkError -> 0x008e, URISyntaxException -> 0x0087 }
            boolean r12 = r12.equals(r2)     // Catch:{ IOException -> 0x013c, RuntimeException -> 0x013a, UnsatisfiedLinkError -> 0x008e, URISyntaxException -> 0x0087 }
            if (r12 != 0) goto L_0x01de
            goto L_0x014c
        L_0x013a:
            r0 = move-exception
            goto L_0x0143
        L_0x013c:
            r0 = move-exception
            goto L_0x0143
        L_0x013e:
            r0 = move-exception
            goto L_0x0141
        L_0x0140:
            r0 = move-exception
        L_0x0141:
            r17 = r12
        L_0x0143:
            r2 = r0
            r19 = r14
            r20 = r15
            goto L_0x01ab
        L_0x014a:
            r17 = r12
        L_0x014c:
            boolean r12 = r2.isAbsolute()     // Catch:{ IOException -> 0x0199, RuntimeException -> 0x0197, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            if (r12 == 0) goto L_0x01de
            boolean r12 = r2.equals(r7)     // Catch:{ IOException -> 0x0199, RuntimeException -> 0x0197, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            if (r12 != 0) goto L_0x01de
            org.bytedeco.javacpp.tools.Logger r12 = logger     // Catch:{ IOException -> 0x0199, RuntimeException -> 0x0197, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            boolean r19 = r12.isDebugEnabled()     // Catch:{ IOException -> 0x0199, RuntimeException -> 0x0197, UnsatisfiedLinkError -> 0x0326, URISyntaxException -> 0x031c }
            if (r19 == 0) goto L_0x0185
            r19 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0183, RuntimeException -> 0x0181, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r14.<init>()     // Catch:{ IOException -> 0x0183, RuntimeException -> 0x0181, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r20 = r15
            java.lang.String r15 = "Creating symbolic link "
            r14.append(r15)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r14.append(r7)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            java.lang.String r15 = " to "
            r14.append(r15)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r14.append(r2)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            java.lang.String r14 = r14.toString()     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r12.debug(r14)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            goto L_0x0189
        L_0x0181:
            r0 = move-exception
            goto L_0x01a8
        L_0x0183:
            r0 = move-exception
            goto L_0x01a8
        L_0x0185:
            r19 = r14
            r20 = r15
        L_0x0189:
            r9.delete()     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            r12 = 0
            java.nio.file.attribute.FileAttribute[] r14 = new java.nio.file.attribute.FileAttribute[r12]     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            java.nio.file.Files.createSymbolicLink(r7, r2, r14)     // Catch:{ IOException -> 0x0195, RuntimeException -> 0x0193, UnsatisfiedLinkError -> 0x044e, URISyntaxException -> 0x0448 }
            goto L_0x01e2
        L_0x0193:
            r0 = move-exception
            goto L_0x01aa
        L_0x0195:
            r0 = move-exception
            goto L_0x01aa
        L_0x0197:
            r0 = move-exception
            goto L_0x01a6
        L_0x0199:
            r0 = move-exception
            goto L_0x01a6
        L_0x019b:
            r0 = move-exception
            goto L_0x01a4
        L_0x019d:
            r0 = move-exception
            goto L_0x01a4
        L_0x019f:
            r0 = move-exception
            goto L_0x01a2
        L_0x01a1:
            r0 = move-exception
        L_0x01a2:
            r18 = r2
        L_0x01a4:
            r17 = r12
        L_0x01a6:
            r19 = r14
        L_0x01a8:
            r20 = r15
        L_0x01aa:
            r2 = r0
        L_0x01ab:
            org.bytedeco.javacpp.tools.Logger r7 = logger     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            boolean r12 = r7.isDebugEnabled()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r12 == 0) goto L_0x01e2
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r12.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r14 = "Failed to create symbolic link "
            r12.append(r14)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r12.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = " to "
            r12.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r12.append(r4)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r4 = ": "
            r12.append(r4)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r12.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r2 = r12.toString()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.debug(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            goto L_0x01e2
        L_0x01d8:
            r18 = r2
            r16 = r7
            r17 = r12
        L_0x01de:
            r19 = r14
            r20 = r15
        L_0x01e2:
            int r8 = r8 + 1
            r4 = r24
            r7 = r16
            r12 = r17
            r2 = r18
            r14 = r19
            r15 = r20
            goto L_0x00ef
        L_0x01f2:
            r17 = r12
            r19 = r14
            r20 = r15
            r15 = r20
        L_0x01fa:
            if (r10 == 0) goto L_0x0209
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r1 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r1 = (java.util.Deque) r1     // Catch:{ all -> 0x04df }
            r1.pop()     // Catch:{ all -> 0x04df }
            monitor-exit(r5)
            return r10
        L_0x0209:
            if (r15 == 0) goto L_0x030d
            boolean r2 = r15.exists()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r2 == 0) goto L_0x030d
            java.lang.String r2 = r15.getAbsolutePath()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            org.bytedeco.javacpp.tools.Logger r4 = logger     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            boolean r7 = r4.isDebugEnabled()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r7 == 0) goto L_0x0231
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r8 = "Loading "
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r7 = r7.toString()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.debug(r7)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x0231:
            java.util.Map<java.lang.String, java.lang.String> r4 = loadedLibraries     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.put(r11, r2)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r1 == 0) goto L_0x02c9
            java.lang.ClassLoader r4 = r5.getClassLoader()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.ClassLoader r7 = r21.getClassLoader()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r4 == r7) goto L_0x02c9
            java.lang.Class<java.lang.Runtime> r4 = java.lang.Runtime.class
            java.lang.String r7 = "load0"
            r8 = 2
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            java.lang.Class<java.lang.Class> r8 = java.lang.Class.class
            r12 = 0
            r9[r12] = r8     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r12 = 1
            r9[r12] = r8     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r7, r9)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r4.setAccessible(r12)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            java.lang.Runtime r7 = java.lang.Runtime.getRuntime()     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r9 = 0
            r8[r9] = r1     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r8[r12] = r2     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r4.invoke(r7, r8)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x02a7, InvocationTargetException -> 0x026b }
            r4 = 1
            goto L_0x02ca
        L_0x026b:
            r0 = move-exception
            r4 = r0
            java.lang.Throwable r4 = r4.getTargetException()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            boolean r7 = r4 instanceof java.lang.UnsatisfiedLinkError     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r7 != 0) goto L_0x02a4
            org.bytedeco.javacpp.tools.Logger r7 = logger     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r8.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = "Unable to load the library "
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r8.append(r11)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = " within the ClassLoader scope of "
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = r21.getName()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = " because: "
            r8.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r8.append(r4)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r4 = r8.toString()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.warn(r4)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            goto L_0x02c9
        L_0x02a4:
            java.lang.UnsatisfiedLinkError r4 = (java.lang.UnsatisfiedLinkError) r4     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            throw r4     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x02a7:
            org.bytedeco.javacpp.tools.Logger r4 = logger     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r8 = "Unable to load the library "
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r11)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r8 = " within the ClassLoader scope of "
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r8 = r21.getName()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r7 = r7.toString()     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.warn(r7)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x02c9:
            r4 = 0
        L_0x02ca:
            if (r4 != 0) goto L_0x02cf
            java.lang.System.load(r2)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x02cf:
            if (r6 == 0) goto L_0x02d4
            loadGlobal(r2)     // Catch:{ UnsatisfiedLinkError -> 0x02e1, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x02d4:
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r1 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r1 = (java.util.Deque) r1     // Catch:{ all -> 0x04df }
            r1.pop()     // Catch:{ all -> 0x04df }
            monitor-exit(r5)
            return r2
        L_0x02e1:
            r0 = move-exception
            r14 = r0
            java.util.Map<java.lang.String, java.lang.String> r4 = loadedLibraries     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r4.remove(r11)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            org.bytedeco.javacpp.tools.Logger r4 = logger     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            boolean r7 = r4.isDebugEnabled()     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            if (r7 == 0) goto L_0x030f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r7.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r8 = "Failed to load "
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r7.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r2 = ": "
            r7.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r7.append(r14)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            java.lang.String r2 = r7.toString()     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            r4.debug(r2)     // Catch:{ UnsatisfiedLinkError -> 0x008e, IOException -> 0x0089, URISyntaxException -> 0x0087 }
            goto L_0x030f
        L_0x030d:
            r14 = r19
        L_0x030f:
            int r13 = r13 + 1
            r2 = r22
            r4 = r24
            r12 = r17
            r7 = 0
            r8 = 0
            r9 = 1
            goto L_0x0074
        L_0x031c:
            r0 = move-exception
            goto L_0x031f
        L_0x031e:
            r0 = move-exception
        L_0x031f:
            r19 = r14
        L_0x0321:
            r1 = r0
            r7 = r19
            goto L_0x045a
        L_0x0326:
            r0 = move-exception
            r19 = r14
        L_0x0329:
            r1 = r0
            r7 = r19
            goto L_0x049e
        L_0x032e:
            r19 = r14
            if (r10 == 0) goto L_0x033f
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r1 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r1 = (java.util.Deque) r1     // Catch:{ all -> 0x04df }
            r1.pop()     // Catch:{ all -> 0x04df }
            monitor-exit(r5)
            return r10
        L_0x033f:
            java.lang.String r2 = r3.trim()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r4 = "#"
            boolean r2 = r2.endsWith(r4)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r2 != 0) goto L_0x043a
            java.lang.String r2 = "#"
            java.lang.String[] r2 = r3.split(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4 = 0
            r2 = r2[r4]     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r6 = "@"
            java.lang.String[] r2 = r2.split(r6)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r2 = r2[r4]     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r6 = "!"
            boolean r6 = r2.endsWith(r6)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r6 == 0) goto L_0x036e
            int r6 = r2.length()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7 = 1
            int r6 = r6 - r7
            java.lang.String r2 = r2.substring(r4, r6)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x036e:
            org.bytedeco.javacpp.tools.Logger r4 = logger     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            boolean r6 = r4.isDebugEnabled()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r6 == 0) goto L_0x038a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r7 = "Loading library "
            r6.append(r7)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r6 = r6.toString()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.debug(r6)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x038a:
            java.util.Map<java.lang.String, java.lang.String> r4 = loadedLibraries     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.put(r11, r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r1 == 0) goto L_0x0427
            java.lang.ClassLoader r4 = r5.getClassLoader()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.ClassLoader r6 = r21.getClassLoader()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r4 == r6) goto L_0x0427
            java.lang.Class<java.lang.Runtime> r4 = java.lang.Runtime.class
            java.lang.String r6 = "loadLibrary0"
            r7 = 2
            java.lang.Class[] r8 = new java.lang.Class[r7]     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r9 = 0
            r8[r9] = r7     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r9 = 1
            r8[r9] = r7     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r6, r8)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            r4.setAccessible(r9)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            java.lang.Runtime r6 = java.lang.Runtime.getRuntime()     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0403, InvocationTargetException -> 0x03c6 }
            r8 = 0
            r7[r8] = r1     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0404, InvocationTargetException -> 0x03c4 }
            r7[r9] = r2     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0404, InvocationTargetException -> 0x03c4 }
            r4.invoke(r6, r7)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException -> 0x0404, InvocationTargetException -> 0x03c4 }
            r8 = 1
            goto L_0x0428
        L_0x03c4:
            r0 = move-exception
            goto L_0x03c8
        L_0x03c6:
            r0 = move-exception
            r8 = 0
        L_0x03c8:
            r4 = r0
            java.lang.Throwable r4 = r4.getTargetException()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            boolean r6 = r4 instanceof java.lang.UnsatisfiedLinkError     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            if (r6 != 0) goto L_0x0400
            org.bytedeco.javacpp.tools.Logger r6 = logger     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = "Unable to load the library "
            r7.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r9 = " within the ClassLoader scope of "
            r7.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = r21.getName()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = " because: "
            r7.append(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = r4.getMessage()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r7.append(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = r7.toString()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.warn(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            goto L_0x0428
        L_0x0400:
            java.lang.UnsatisfiedLinkError r4 = (java.lang.UnsatisfiedLinkError) r4     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            throw r4     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x0403:
            r8 = 0
        L_0x0404:
            org.bytedeco.javacpp.tools.Logger r4 = logger     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r7 = "Unable to load the library "
            r6.append(r7)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r7 = " within the ClassLoader scope of "
            r6.append(r7)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = r21.getName()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r6.append(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            java.lang.String r1 = r6.toString()     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            r4.warn(r1)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
            goto L_0x0428
        L_0x0427:
            r8 = 0
        L_0x0428:
            if (r8 != 0) goto L_0x042d
            java.lang.System.loadLibrary(r2)     // Catch:{ UnsatisfiedLinkError -> 0x044e, IOException -> 0x044b, URISyntaxException -> 0x0448 }
        L_0x042d:
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r1 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r1 = (java.util.Deque) r1     // Catch:{ all -> 0x04df }
            r1.pop()     // Catch:{ all -> 0x04df }
            monitor-exit(r5)
            return r2
        L_0x043a:
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r1 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r1 = (java.util.Deque) r1     // Catch:{ all -> 0x04df }
            r1.pop()     // Catch:{ all -> 0x04df }
            monitor-exit(r5)
            r1 = 0
            return r1
        L_0x0448:
            r0 = move-exception
            goto L_0x0321
        L_0x044b:
            r0 = move-exception
            goto L_0x0321
        L_0x044e:
            r0 = move-exception
            goto L_0x0329
        L_0x0451:
            r0 = move-exception
            r1 = r0
            goto L_0x04d3
        L_0x0455:
            r0 = move-exception
            goto L_0x0458
        L_0x0457:
            r0 = move-exception
        L_0x0458:
            r1 = r7
            r1 = r0
        L_0x045a:
            java.util.Map<java.lang.String, java.lang.String> r2 = loadedLibraries     // Catch:{ all -> 0x0451 }
            r2.remove(r11)     // Catch:{ all -> 0x0451 }
            if (r7 == 0) goto L_0x046a
            java.lang.Throwable r2 = r1.getCause()     // Catch:{ all -> 0x0451 }
            if (r2 != 0) goto L_0x046a
            r1.initCause(r7)     // Catch:{ all -> 0x0451 }
        L_0x046a:
            java.lang.UnsatisfiedLinkError r2 = new java.lang.UnsatisfiedLinkError     // Catch:{ all -> 0x0451 }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0451 }
            r2.<init>(r4)     // Catch:{ all -> 0x0451 }
            r2.initCause(r1)     // Catch:{ all -> 0x0451 }
            org.bytedeco.javacpp.tools.Logger r1 = logger     // Catch:{ all -> 0x0451 }
            boolean r4 = r1.isDebugEnabled()     // Catch:{ all -> 0x0451 }
            if (r4 == 0) goto L_0x049a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0451 }
            r4.<init>()     // Catch:{ all -> 0x0451 }
            java.lang.String r6 = "Failed to extract for "
            r4.append(r6)     // Catch:{ all -> 0x0451 }
            r4.append(r3)     // Catch:{ all -> 0x0451 }
            java.lang.String r3 = ": "
            r4.append(r3)     // Catch:{ all -> 0x0451 }
            r4.append(r2)     // Catch:{ all -> 0x0451 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0451 }
            r1.debug(r3)     // Catch:{ all -> 0x0451 }
        L_0x049a:
            throw r2     // Catch:{ all -> 0x0451 }
        L_0x049b:
            r0 = move-exception
            r1 = r7
            r1 = r0
        L_0x049e:
            java.util.Map<java.lang.String, java.lang.String> r2 = loadedLibraries     // Catch:{ all -> 0x0451 }
            r2.remove(r11)     // Catch:{ all -> 0x0451 }
            if (r7 == 0) goto L_0x04ae
            java.lang.Throwable r2 = r1.getCause()     // Catch:{ all -> 0x0451 }
            if (r2 != 0) goto L_0x04ae
            r1.initCause(r7)     // Catch:{ all -> 0x0451 }
        L_0x04ae:
            org.bytedeco.javacpp.tools.Logger r2 = logger     // Catch:{ all -> 0x0451 }
            boolean r4 = r2.isDebugEnabled()     // Catch:{ all -> 0x0451 }
            if (r4 == 0) goto L_0x04d2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0451 }
            r4.<init>()     // Catch:{ all -> 0x0451 }
            java.lang.String r6 = "Failed to load for "
            r4.append(r6)     // Catch:{ all -> 0x0451 }
            r4.append(r3)     // Catch:{ all -> 0x0451 }
            java.lang.String r3 = ": "
            r4.append(r3)     // Catch:{ all -> 0x0451 }
            r4.append(r1)     // Catch:{ all -> 0x0451 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0451 }
            r2.debug(r3)     // Catch:{ all -> 0x0451 }
        L_0x04d2:
            throw r1     // Catch:{ all -> 0x0451 }
        L_0x04d3:
            java.lang.ThreadLocal<java.util.Deque<java.lang.Class<?>>> r2 = classStack     // Catch:{ all -> 0x04df }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x04df }
            java.util.Deque r2 = (java.util.Deque) r2     // Catch:{ all -> 0x04df }
            r2.pop()     // Catch:{ all -> 0x04df }
            throw r1     // Catch:{ all -> 0x04df }
        L_0x04df:
            r0 = move-exception
            r1 = r0
            monitor-exit(r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.loadLibrary(java.lang.Class, java.net.URL[], java.lang.String, java.lang.String[]):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012c, code lost:
        if (java.nio.file.Files.readSymbolicLink(r0).equals(r2) != false) goto L_0x0160;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String createLibraryLink(java.lang.String r17, org.bytedeco.javacpp.ClassProperties r18, java.lang.String r19, java.lang.String... r20) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            java.lang.String r4 = ":"
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0019
            boolean r7 = r2.startsWith(r4)
            if (r7 == 0) goto L_0x0019
            java.lang.String r2 = r2.substring(r5)
            goto L_0x0029
        L_0x0019:
            if (r2 == 0) goto L_0x0029
            boolean r7 = r2.contains(r4)
            if (r7 == 0) goto L_0x0029
            int r4 = r2.indexOf(r4)
            java.lang.String r2 = r2.substring(r6, r4)
        L_0x0029:
            if (r2 == 0) goto L_0x003c
            java.lang.String r4 = "!"
            boolean r4 = r2.endsWith(r4)
            if (r4 == 0) goto L_0x003c
            int r4 = r2.length()
            int r4 = r4 - r5
            java.lang.String r2 = r2.substring(r6, r4)
        L_0x003c:
            java.io.File r4 = new java.io.File
            r4.<init>(r0)
            java.lang.String r7 = r4.getParent()
            java.lang.String r4 = r4.getName()
            java.lang.String r8 = ""
            if (r2 == 0) goto L_0x0054
            java.lang.String r9 = "#"
            java.lang.String[] r2 = r2.split(r9)
            goto L_0x0058
        L_0x0054:
            java.lang.String[] r2 = new java.lang.String[]{r8}
        L_0x0058:
            int r9 = r2.length
            if (r9 <= r5) goto L_0x0066
            r9 = r2[r5]
            int r9 = r9.length()
            if (r9 <= 0) goto L_0x0066
            r2 = r2[r5]
            goto L_0x0068
        L_0x0066:
            r2 = r2[r6]
        L_0x0068:
            java.lang.String r9 = "@"
            java.lang.String[] r2 = r2.split(r9)
            r9 = r2[r6]
            int r10 = r2.length
            if (r10 <= r5) goto L_0x0077
            int r8 = r2.length
            int r8 = r8 - r5
            r8 = r2[r8]
        L_0x0077:
            boolean r2 = r4.contains(r9)
            if (r2 != 0) goto L_0x007e
            return r0
        L_0x007e:
            java.lang.String r2 = "platform.library.suffix"
            java.util.List r9 = r1.get(r2)
            java.util.Iterator r9 = r9.iterator()
        L_0x0088:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00e1
            java.lang.Object r10 = r9.next()
            java.lang.String r10 = (java.lang.String) r10
            int r12 = r4.lastIndexOf(r10)
            int r13 = r8.length()
            java.lang.String r14 = "."
            if (r13 == 0) goto L_0x00a5
            int r13 = r4.lastIndexOf(r8)
            goto L_0x00a9
        L_0x00a5:
            int r13 = r4.indexOf(r14)
        L_0x00a9:
            int r14 = r4.lastIndexOf(r14)
            if (r13 >= r12) goto L_0x00c5
            if (r12 >= r14) goto L_0x00c5
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = r4.substring(r6, r12)
            r8.append(r9)
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            goto L_0x00e2
        L_0x00c5:
            if (r12 <= 0) goto L_0x0088
            if (r13 <= 0) goto L_0x0088
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            if (r12 >= r13) goto L_0x00d1
            goto L_0x00d2
        L_0x00d1:
            r12 = r13
        L_0x00d2:
            java.lang.String r9 = r4.substring(r6, r12)
            r8.append(r9)
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            goto L_0x00e2
        L_0x00e1:
            r8 = 0
        L_0x00e2:
            if (r8 != 0) goto L_0x00ff
            java.util.List r1 = r1.get(r2)
            java.util.Iterator r1 = r1.iterator()
        L_0x00ec:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00ff
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = r4.endsWith(r2)
            if (r2 == 0) goto L_0x00ec
            r8 = r4
        L_0x00ff:
            if (r8 == 0) goto L_0x021e
            int r1 = r8.length()
            if (r1 <= 0) goto L_0x021e
            java.io.File r1 = new java.io.File
            r1.<init>(r7, r8)
            java.nio.file.Path r0 = r1.toPath()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.lang.String[] r2 = new java.lang.String[r6]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r2 = java.nio.file.Paths.get(r4, r2)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r9 = r1.exists()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.lang.String r10 = "Creating symbolic link "
            if (r9 == 0) goto L_0x012e
            boolean r9 = java.nio.file.Files.isSymbolicLink(r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r9 == 0) goto L_0x012e
            java.nio.file.Path r9 = java.nio.file.Files.readSymbolicLink(r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r9 = r9.equals(r2)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r9 != 0) goto L_0x0160
        L_0x012e:
            boolean r9 = r2.isAbsolute()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r9 != 0) goto L_0x0160
            java.nio.file.Path r9 = r0.getFileName()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r9 = r2.equals(r9)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r9 != 0) goto L_0x0160
            org.bytedeco.javacpp.tools.Logger r9 = logger     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r12 = r9.isDebugEnabled()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r12 == 0) goto L_0x0158
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r12.<init>()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r12.append(r10)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r12.append(r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.lang.String r12 = r12.toString()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r9.debug(r12)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
        L_0x0158:
            r1.delete()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.attribute.FileAttribute[] r9 = new java.nio.file.attribute.FileAttribute[r6]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Files.createSymbolicLink(r0, r2, r9)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
        L_0x0160:
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            int r2 = r3.length     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r9 = 0
        L_0x0166:
            if (r9 >= r2) goto L_0x01f2
            r12 = r3[r9]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r12 != 0) goto L_0x0170
        L_0x016c:
            r18 = r0
            goto L_0x01eb
        L_0x0170:
            r13 = 2
            java.lang.String[] r14 = new java.lang.String[r13]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r14[r6] = r8     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r14[r5] = r4     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r15 = 0
        L_0x0178:
            if (r15 >= r13) goto L_0x016c
            r5 = r14[r15]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.io.File r13 = new java.io.File     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r13.<init>(r12, r5)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r5 = r13.toPath()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.lang.String[] r11 = new java.lang.String[r6]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r11 = java.nio.file.Paths.get(r12, r11)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r18 = r0
            java.lang.String[] r0 = new java.lang.String[r6]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r0 = java.nio.file.Paths.get(r7, r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r0 = r11.relativize(r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Path r0 = r0.resolve(r4)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r11 = r13.exists()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r11 == 0) goto L_0x01b1
            boolean r11 = java.nio.file.Files.isSymbolicLink(r5)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r11 == 0) goto L_0x01b1
            java.nio.file.Path r11 = java.nio.file.Files.readSymbolicLink(r5)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r11 = r11.equals(r0)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r11 != 0) goto L_0x01e4
        L_0x01b1:
            boolean r11 = r0.isAbsolute()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r11 != 0) goto L_0x01e4
            java.nio.file.Path r11 = r5.getFileName()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r11 = r0.equals(r11)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r11 != 0) goto L_0x01e4
            org.bytedeco.javacpp.tools.Logger r11 = logger     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            boolean r16 = r11.isDebugEnabled()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            if (r16 == 0) goto L_0x01db
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r6.<init>()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r6.append(r10)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r6.append(r5)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r11.debug(r6)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
        L_0x01db:
            r13.delete()     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            r6 = 0
            java.nio.file.attribute.FileAttribute[] r11 = new java.nio.file.attribute.FileAttribute[r6]     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
            java.nio.file.Files.createSymbolicLink(r5, r0, r11)     // Catch:{ IOException -> 0x01f7, RuntimeException -> 0x01f5 }
        L_0x01e4:
            int r15 = r15 + 1
            r0 = r18
            r5 = 1
            r13 = 2
            goto L_0x0178
        L_0x01eb:
            int r9 = r9 + 1
            r0 = r18
            r5 = 1
            goto L_0x0166
        L_0x01f2:
            r18 = r0
            goto L_0x021e
        L_0x01f5:
            r0 = move-exception
            goto L_0x01f8
        L_0x01f7:
            r0 = move-exception
        L_0x01f8:
            org.bytedeco.javacpp.tools.Logger r2 = logger
            boolean r3 = r2.isDebugEnabled()
            if (r3 == 0) goto L_0x021c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to create symbolic link "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = ": "
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.debug(r0)
        L_0x021c:
            r1 = 0
            return r1
        L_0x021e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Loader.createLibraryLink(java.lang.String, org.bytedeco.javacpp.ClassProperties, java.lang.String, java.lang.String[]):java.lang.String");
    }

    static Class putMemberOffset(String str, String str2, int i) throws ClassNotFoundException {
        try {
            Class<Loader> cls = (Class) classStack.get().peek();
            String replace = str.replace('/', '.');
            if (cls == null) {
                cls = Loader.class;
            }
            Class<?> cls2 = Class.forName(replace, false, cls.getClassLoader());
            if (str2 != null) {
                putMemberOffset((Class<? extends Pointer>) cls2.asSubclass(Pointer.class), str2, i);
            }
            return cls2;
        } catch (ClassNotFoundException e) {
            Logger logger2 = logger;
            logger2.warn("Loader.putMemberOffset(): " + e);
            return null;
        }
    }

    static synchronized void putMemberOffset(Class<? extends Pointer> cls, String str, int i) {
        synchronized (Loader.class) {
            HashMap hashMap = memberOffsets.get(cls);
            if (hashMap == null) {
                WeakHashMap<Class<? extends Pointer>, HashMap<String, Integer>> weakHashMap = memberOffsets;
                HashMap hashMap2 = new HashMap();
                weakHashMap.put(cls, hashMap2);
                hashMap = hashMap2;
            }
            hashMap.put(str, Integer.valueOf(i));
        }
    }

    public static int offsetof(Class<? extends Pointer> cls, String str) {
        HashMap hashMap = memberOffsets.get(cls);
        Class<? extends U> cls2 = cls;
        while (hashMap == null && cls2.getSuperclass() != null) {
            Class<? extends U> asSubclass = cls2.getSuperclass().asSubclass(Pointer.class);
            hashMap = memberOffsets.get(asSubclass);
            cls2 = asSubclass;
        }
        return ((Integer) hashMap.get(str)).intValue();
    }

    public static int sizeof(Class<? extends Pointer> cls) {
        return offsetof(cls, "sizeof");
    }
}
