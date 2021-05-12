package fm.liveswitch.openh264;

import fm.liveswitch.Architecture;
import fm.liveswitch.Future;
import fm.liveswitch.Holder;
import fm.liveswitch.IFunction0;
import fm.liveswitch.ILog;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedConcurrentDictionary;
import fm.liveswitch.OperatingSystem;
import fm.liveswitch.Platform;
import fm.liveswitch.PromiseBase;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;

public class Utility {
    private static ManagedConcurrentDictionary<String, NativeLibraryInfo> __libraryInfo = new ManagedConcurrentDictionary<>();
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(Utility.class);

    private static void addPlatformInfo(OperatingSystem operatingSystem, Architecture architecture, String str, String str2, String str3, String[] strArr) {
        NativeLibraryInfo nativeLibraryInfo = new NativeLibraryInfo();
        nativeLibraryInfo.setDownloadURL(str);
        nativeLibraryInfo.setLibraryName(str2);
        nativeLibraryInfo.setLoadLibraryName(str3);
        nativeLibraryInfo.setOldLibraryNames(strArr);
        __libraryInfo.tryAdd(StringExtensions.format("{0}-{1}", operatingSystem.toString(), architecture.toString()), nativeLibraryInfo);
    }

    public static Future<Object> downloadOpenH264() {
        return downloadOpenH264((String) null, true);
    }

    public static Future<Object> downloadOpenH264(String str) {
        return downloadOpenH264(str, true);
    }

    public static Future<Object> downloadOpenH264(final String str, final boolean z) {
        return PromiseBase.wrapPromise(new IFunction0<Future<Object>>() {
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x010f */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public fm.liveswitch.Future<java.lang.Object> invoke() {
                /*
                    r10 = this;
                    fm.liveswitch.IPlatform r0 = fm.liveswitch.Platform.getInstance()
                    fm.liveswitch.OperatingSystem r0 = r0.getOperatingSystem()
                    fm.liveswitch.IPlatform r1 = fm.liveswitch.Platform.getInstance()
                    fm.liveswitch.Architecture r1 = r1.getArchitecture()
                    fm.liveswitch.openh264.NativeLibraryInfo r2 = fm.liveswitch.openh264.Utility.getPlatformInfo(r0, r1)
                    java.lang.String r3 = fm.liveswitch.StringExtensions.empty
                    java.lang.String r4 = r1
                    java.lang.String r5 = fm.liveswitch.StringExtensions.empty
                    boolean r4 = fm.liveswitch.Global.equals(r4, r5)
                    if (r4 != 0) goto L_0x0027
                    java.lang.String r4 = r1
                    if (r4 != 0) goto L_0x0025
                    goto L_0x0027
                L_0x0025:
                    r3 = r4
                    goto L_0x0076
                L_0x0027:
                    fm.liveswitch.IPlatform r4 = fm.liveswitch.Platform.getInstance()
                    fm.liveswitch.SourceLanguage r4 = r4.getSourceLanguage()
                    fm.liveswitch.SourceLanguage r5 = fm.liveswitch.SourceLanguage.CSharp
                    boolean r4 = fm.liveswitch.Global.equals(r4, r5)
                    if (r4 == 0) goto L_0x004e
                    fm.liveswitch.OperatingSystem r4 = fm.liveswitch.OperatingSystem.Windows
                    boolean r4 = fm.liveswitch.Global.equals(r0, r4)
                    if (r4 == 0) goto L_0x004e
                    fm.liveswitch.Architecture r0 = fm.liveswitch.Architecture.X86
                    boolean r0 = fm.liveswitch.Global.equals(r1, r0)
                    if (r0 == 0) goto L_0x004a
                    java.lang.String r0 = "lib/win_x86"
                    goto L_0x004c
                L_0x004a:
                    java.lang.String r0 = "lib/win_x64"
                L_0x004c:
                    r3 = r0
                    goto L_0x0076
                L_0x004e:
                    fm.liveswitch.IPlatform r1 = fm.liveswitch.Platform.getInstance()
                    fm.liveswitch.SourceLanguage r1 = r1.getSourceLanguage()
                    fm.liveswitch.SourceLanguage r4 = fm.liveswitch.SourceLanguage.Java
                    boolean r1 = fm.liveswitch.Global.equals(r1, r4)
                    if (r1 == 0) goto L_0x0076
                    fm.liveswitch.OperatingSystem r1 = fm.liveswitch.OperatingSystem.MacOS
                    boolean r1 = fm.liveswitch.Global.equals(r0, r1)
                    if (r1 != 0) goto L_0x006e
                    fm.liveswitch.OperatingSystem r1 = fm.liveswitch.OperatingSystem.Linux
                    boolean r0 = fm.liveswitch.Global.equals(r0, r1)
                    if (r0 == 0) goto L_0x0076
                L_0x006e:
                    java.lang.String r0 = fm.liveswitch.PathUtility.getTempPath()
                    java.lang.String r3 = fm.liveswitch.PathUtility.getAbsolutePath(r0)
                L_0x0076:
                    java.lang.String r0 = r2.getLibraryName()
                    java.lang.String r0 = fm.liveswitch.PathUtility.combinePaths(r3, r0)
                    fm.liveswitch.FileStream r1 = new fm.liveswitch.FileStream
                    r1.<init>(r0)
                    boolean r1 = r1.exists()
                    if (r1 != 0) goto L_0x0126
                    boolean r1 = r2
                    if (r1 == 0) goto L_0x00cc
                    java.lang.String[] r1 = r2.getOldLibraryNames()
                    int r4 = r1.length
                    r5 = 0
                L_0x0093:
                    if (r5 >= r4) goto L_0x00cc
                    r6 = r1[r5]
                    java.lang.String r6 = fm.liveswitch.PathUtility.combinePaths(r3, r6)
                    fm.liveswitch.FileStream r7 = new fm.liveswitch.FileStream
                    r7.<init>(r6)
                    boolean r7 = r7.exists()
                    if (r7 == 0) goto L_0x00c9
                    fm.liveswitch.ILog r7 = fm.liveswitch.openh264.Utility.__log
                    java.lang.String r8 = "Removing old OpenH264 library from: {0}."
                    java.lang.String r8 = fm.liveswitch.StringExtensions.format((java.lang.String) r8, (java.lang.Object) r6)
                    r7.debug(r8)
                    fm.liveswitch.PathUtility.delete(r6)     // Catch:{ Exception -> 0x00b7 }
                    goto L_0x00c9
                L_0x00b7:
                    r7 = move-exception
                    fm.liveswitch.ILog r8 = fm.liveswitch.openh264.Utility.__log
                    java.lang.String r7 = r7.getMessage()
                    java.lang.String r9 = "Failed to remove old OpenH264 library from: {0}, Error: {1}"
                    java.lang.String r6 = fm.liveswitch.StringExtensions.format(r9, r6, r7)
                    r8.info(r6)
                L_0x00c9:
                    int r5 = r5 + 1
                    goto L_0x0093
                L_0x00cc:
                    fm.liveswitch.ILog r1 = fm.liveswitch.openh264.Utility.__log
                    java.lang.String r4 = r2.getDownloadURL()
                    java.lang.String r5 = "OpenH264 v1.7 library missing. Downloading now from: {0}."
                    java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r4)
                    r1.info(r4)
                    java.lang.String r1 = r2.getDownloadURL()
                    fm.liveswitch.Future r0 = fm.liveswitch.HttpFileTransfer.downloadFile((java.lang.String) r1, (java.lang.String) r0)
                    r0.waitForResult()
                    java.lang.String r0 = r2.getLoadLibraryName()
                    if (r0 == 0) goto L_0x011d
                    java.lang.String r0 = r2.getLoadLibraryName()
                    java.lang.String r1 = r2.getLibraryName()
                    boolean r0 = fm.liveswitch.Global.equals(r0, r1)
                    if (r0 != 0) goto L_0x011d
                    java.lang.String r0 = r2.getLibraryName()
                    java.lang.String r0 = fm.liveswitch.PathUtility.combinePaths(r3, r0)
                    java.lang.String r1 = r2.getLoadLibraryName()
                    java.lang.String r1 = fm.liveswitch.PathUtility.combinePaths(r3, r1)
                    fm.liveswitch.PathUtility.delete(r1)     // Catch:{ Exception -> 0x010f }
                L_0x010f:
                    fm.liveswitch.PathUtility.createSymlink(r0, r1)     // Catch:{ Exception -> 0x0113 }
                    goto L_0x011d
                L_0x0113:
                    r0 = move-exception
                    fm.liveswitch.ILog r1 = fm.liveswitch.openh264.Utility.__log
                    java.lang.String r2 = "Unable to create symlink for OpenH264 binary."
                    r1.error((java.lang.String) r2, (java.lang.Exception) r0)
                L_0x011d:
                    fm.liveswitch.ILog r0 = fm.liveswitch.openh264.Utility.__log
                    java.lang.String r1 = "OpenH264 library downloaded."
                    r0.info(r1)
                L_0x0126:
                    fm.liveswitch.Future r0 = fm.liveswitch.PromiseBase.resolveNow()
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.openh264.Utility.AnonymousClass1.invoke():fm.liveswitch.Future");
            }
        });
    }

    public static String getDownloadUrl() {
        NativeLibraryInfo platformInfo = getPlatformInfo();
        if (platformInfo == null) {
            return null;
        }
        return platformInfo.getDownloadURL();
    }

    public static String getLibraryName() {
        NativeLibraryInfo platformInfo = getPlatformInfo();
        if (platformInfo == null) {
            return null;
        }
        return platformInfo.getLibraryName();
    }

    public static String getLoadLibraryName() {
        NativeLibraryInfo platformInfo = getPlatformInfo();
        if (platformInfo == null) {
            return null;
        }
        return platformInfo.getLoadLibraryName() != null ? platformInfo.getLoadLibraryName() : platformInfo.getLibraryName();
    }

    private static NativeLibraryInfo getPlatformInfo() {
        OperatingSystem operatingSystem = Platform.getInstance().getOperatingSystem();
        Architecture architecture = Platform.getInstance().getArchitecture();
        NativeLibraryInfo platformInfo = getPlatformInfo(operatingSystem, architecture);
        if (platformInfo != null) {
            return platformInfo;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cisco does not distribute {0} OpenH264 binaries for {1}.", architecture, operatingSystem)));
    }

    /* access modifiers changed from: private */
    public static NativeLibraryInfo getPlatformInfo(OperatingSystem operatingSystem, Architecture architecture) {
        Holder holder = new Holder(null);
        boolean tryGetValue = __libraryInfo.tryGetValue(StringExtensions.format("{0}-{1}", operatingSystem.toString(), architecture.toString()), holder);
        NativeLibraryInfo nativeLibraryInfo = (NativeLibraryInfo) holder.getValue();
        if (!tryGetValue) {
            return null;
        }
        return nativeLibraryInfo;
    }

    public static boolean initialize() {
        if (isSupported()) {
            try {
                initialize(new Encoder(), new Decoder());
                return true;
            } catch (Exception e) {
                __log.error("Could not initialize OpenH264 encoder or decoder.", e);
            }
        }
        return false;
    }

    private static void initialize(Encoder encoder, Decoder decoder) {
        encoder.destroy();
        decoder.processBuffer((VideoBuffer) encoder.processBuffer(VideoBuffer.createBlack(160, 120, VideoFormat.getI420Name())).waitForResult()).waitForResult();
        decoder.destroy();
    }

    public static boolean isSupported() {
        return getPlatformInfo(Platform.getInstance().getOperatingSystem(), Platform.getInstance().getArchitecture()) != null;
    }

    static {
        addPlatformInfo(OperatingSystem.Windows, Architecture.X86, "http://ciscobinary.openh264.org/openh264-1.7.0-win32.dll.bz2", "openh264-1.7.0.dll", (String) null, new String[]{"openh264.dll"});
        addPlatformInfo(OperatingSystem.Windows, Architecture.X64, "http://ciscobinary.openh264.org/openh264-1.7.0-win64.dll.bz2", "openh264-1.7.0.dll", (String) null, new String[]{"openh264.dll"});
        addPlatformInfo(OperatingSystem.MacOS, Architecture.X86, "http://ciscobinary.openh264.org/libopenh264-1.7.0-osx32.4.dylib.bz2", "openh264-1.7.0.dylib", (String) null, new String[]{"openh264.dylib"});
        addPlatformInfo(OperatingSystem.MacOS, Architecture.X64, "http://ciscobinary.openh264.org/libopenh264-1.7.0-osx64.4.dylib.bz2", "openh264-1.7.0.dylib", (String) null, new String[]{"openh264.dylib"});
        addPlatformInfo(OperatingSystem.Linux, Architecture.X86, "http://ciscobinary.openh264.org/libopenh264-1.7.0-linux32.4.so.bz2", "libopenh264-1.7.0.so", "libopenh264.so.4", new String[]{"libopenh264.so"});
        addPlatformInfo(OperatingSystem.Linux, Architecture.X64, "http://ciscobinary.openh264.org/libopenh264-1.7.0-linux64.4.so.bz2", "libopenh264-1.7.0.so", "libopenh264.so.4", new String[]{"libopenh264.so"});
        addPlatformInfo(OperatingSystem.Android, Architecture.Armv7, "http://ciscobinary.openh264.org/libopenh264-1.7.0-android19.so.bz2", "libopenh264-1.7.0.so", "libopenh264.so", new String[]{"libopenh264.so"});
        addPlatformInfo(OperatingSystem.Android, Architecture.Armv8, "http://ciscobinary.openh264.org/libopenh264-1.7.0-android19.so.bz2", "libopenh264-1.7.0.so", "libopenh264.so", new String[]{"libopenh264.so"});
        addPlatformInfo(OperatingSystem.Linux, Architecture.X64, "http://ciscobinary.openh264.org/libopenh264-1.7.0-linux64.4.so.bz2", "libopenh264-1.7.0.so", "libopenh264.so", new String[]{"libopenh264.so"});
    }
}
