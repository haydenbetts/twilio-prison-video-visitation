package fm.liveswitch;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Global {
    public static <T> T tryCast(Object obj, Class<T> cls) {
        try {
            return cls.cast(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static IAction0 findIActionDelegate0WithId(List<IAction0> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction0 iAction0 = (IAction0) it.next();
            if (str != null && (iAction0 instanceof IActionDelegate0) && str.equals(((IActionDelegate0) iAction0).getId())) {
                return iAction0;
            }
        }
        return null;
    }

    public static <T> IAction1<T> findIActionDelegate1WithId(List<IAction1<T>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction1<T> iAction1 = (IAction1) it.next();
            if (str != null && (iAction1 instanceof IActionDelegate1) && str.equals(((IActionDelegate1) iAction1).getId())) {
                return iAction1;
            }
        }
        return null;
    }

    public static <T1, T2> IAction2<T1, T2> findIActionDelegate2WithId(List<IAction2<T1, T2>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction2<T1, T2> iAction2 = (IAction2) it.next();
            if (str != null && (iAction2 instanceof IActionDelegate2) && str.equals(((IActionDelegate2) iAction2).getId())) {
                return iAction2;
            }
        }
        return null;
    }

    public static <T1, T2, T3> IAction3<T1, T2, T3> findIActionDelegate3WithId(List<IAction3<T1, T2, T3>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction3<T1, T2, T3> iAction3 = (IAction3) it.next();
            if (str != null && (iAction3 instanceof IActionDelegate3) && str.equals(((IActionDelegate3) iAction3).getId())) {
                return iAction3;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4> IAction4<T1, T2, T3, T4> findIActionDelegate4WithId(List<IAction4<T1, T2, T3, T4>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction4<T1, T2, T3, T4> iAction4 = (IAction4) it.next();
            if (str != null && (iAction4 instanceof IActionDelegate4) && str.equals(((IActionDelegate4) iAction4).getId())) {
                return iAction4;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4, T5> IAction5<T1, T2, T3, T4, T5> findIActionDelegate5WithId(List<IAction5<T1, T2, T3, T4, T5>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction5<T1, T2, T3, T4, T5> iAction5 = (IAction5) it.next();
            if (str != null && (iAction5 instanceof IActionDelegate5) && str.equals(((IActionDelegate5) iAction5).getId())) {
                return iAction5;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4, T5, T6> IAction6<T1, T2, T3, T4, T5, T6> findIActionDelegate6WithId(List<IAction6<T1, T2, T3, T4, T5, T6>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IAction6<T1, T2, T3, T4, T5, T6> iAction6 = (IAction6) it.next();
            if (str != null && (iAction6 instanceof IActionDelegate6) && str.equals(((IActionDelegate6) iAction6).getId())) {
                return iAction6;
            }
        }
        return null;
    }

    public static <R> IFunction0<R> findIFunctionDelegate0WithId(List<IFunction0<R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction0<R> iFunction0 = (IFunction0) it.next();
            if (str != null && (iFunction0 instanceof IFunctionDelegate0) && str.equals(((IFunctionDelegate0) iFunction0).getId())) {
                return iFunction0;
            }
        }
        return null;
    }

    public static <T, R> IFunction1<T, R> findIFunctionDelegate1WithId(List<IFunction1<T, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction1<T, R> iFunction1 = (IFunction1) it.next();
            if (str != null && (iFunction1 instanceof IFunctionDelegate1) && str.equals(((IFunctionDelegate1) iFunction1).getId())) {
                return iFunction1;
            }
        }
        return null;
    }

    public static <T1, T2, R> IFunction2<T1, T2, R> findIFunctionDelegate2WithId(List<IFunction2<T1, T2, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction2<T1, T2, R> iFunction2 = (IFunction2) it.next();
            if (str != null && (iFunction2 instanceof IFunctionDelegate2) && str.equals(((IFunctionDelegate2) iFunction2).getId())) {
                return iFunction2;
            }
        }
        return null;
    }

    public static <T1, T2, T3, R> IFunction3<T1, T2, T3, R> findIFunctionDelegate3WithId(List<IFunction3<T1, T2, T3, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction3<T1, T2, T3, R> iFunction3 = (IFunction3) it.next();
            if (str != null && (iFunction3 instanceof IFunctionDelegate3) && str.equals(((IFunctionDelegate3) iFunction3).getId())) {
                return iFunction3;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4, R> IFunction4<T1, T2, T3, T4, R> findIFunctionDelegate4WithId(List<IFunction4<T1, T2, T3, T4, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction4<T1, T2, T3, T4, R> iFunction4 = (IFunction4) it.next();
            if (str != null && (iFunction4 instanceof IFunctionDelegate4) && str.equals(((IFunctionDelegate4) iFunction4).getId())) {
                return iFunction4;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4, T5, R> IFunction5<T1, T2, T3, T4, T5, R> findIFunctionDelegate5WithId(List<IFunction5<T1, T2, T3, T4, T5, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction5<T1, T2, T3, T4, T5, R> iFunction5 = (IFunction5) it.next();
            if (str != null && (iFunction5 instanceof IFunctionDelegate5) && str.equals(((IFunctionDelegate5) iFunction5).getId())) {
                return iFunction5;
            }
        }
        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, R> IFunction6<T1, T2, T3, T4, T5, T6, R> findIFunctionDelegate6WithId(List<IFunction6<T1, T2, T3, T4, T5, T6, R>> list, String str) {
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            IFunction6<T1, T2, T3, T4, T5, T6, R> iFunction6 = (IFunction6) it.next();
            if (str != null && (iFunction6 instanceof IFunctionDelegate6) && str.equals(((IFunctionDelegate6) iFunction6).getId())) {
                return iFunction6;
            }
        }
        return null;
    }

    public static <T> byte decrement(T t, byte b, IAction2<T, Byte> iAction2, boolean z) {
        byte b2 = (byte) (b - 1);
        iAction2.invoke(t, Byte.valueOf(b2));
        return z ? b2 : b;
    }

    public static <T> byte increment(T t, byte b, IAction2<T, Byte> iAction2, boolean z) {
        byte b2 = (byte) (b + 1);
        iAction2.invoke(t, Byte.valueOf(b2));
        return z ? b2 : b;
    }

    public static <T> short decrement(T t, short s, IAction2<T, Short> iAction2, boolean z) {
        short s2 = (short) (s - 1);
        iAction2.invoke(t, Short.valueOf(s2));
        return z ? s2 : s;
    }

    public static <T> short increment(T t, short s, IAction2<T, Short> iAction2, boolean z) {
        short s2 = (short) (s + 1);
        iAction2.invoke(t, Short.valueOf(s2));
        return z ? s2 : s;
    }

    public static <T> int decrement(T t, int i, IAction2<T, Integer> iAction2, boolean z) {
        int i2 = i - 1;
        iAction2.invoke(t, Integer.valueOf(i2));
        return z ? i2 : i;
    }

    public static <T> int increment(T t, int i, IAction2<T, Integer> iAction2, boolean z) {
        int i2 = i + 1;
        iAction2.invoke(t, Integer.valueOf(i2));
        return z ? i2 : i;
    }

    public static <T> long decrement(T t, long j, IAction2<T, Long> iAction2, boolean z) {
        long j2 = j - 1;
        iAction2.invoke(t, Long.valueOf(j2));
        return z ? j2 : j;
    }

    public static <T> long increment(T t, long j, IAction2<T, Long> iAction2, boolean z) {
        long j2 = 1 + j;
        iAction2.invoke(t, Long.valueOf(j2));
        return z ? j2 : j;
    }

    public static boolean loadLibrary(String str) {
        Class<Global> cls = Global.class;
        if (Platform.getInstance().getOperatingSystem() == OperatingSystem.Android) {
            try {
                System.loadLibrary(str);
                return true;
            } catch (UnsatisfiedLinkError e) {
                Log.error("Unable to load " + str + ".  Message: " + e.getMessage());
                return false;
            }
        } else {
            String mapLibraryName = System.mapLibraryName(str);
            String str2 = "lib" + mapLibraryName;
            File file = new File(System.getProperty("java.io.tmpdir"));
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2);
            if (file2.exists()) {
                file2.delete();
            }
            String libraryPath = getLibraryPath();
            if (!file2.exists()) {
                try {
                    InputStream resourceAsStream = cls.getResourceAsStream(libraryPath + "/" + mapLibraryName);
                    if (resourceAsStream == null) {
                        resourceAsStream = cls.getResourceAsStream(libraryPath + "/lib" + mapLibraryName);
                        if (resourceAsStream == null) {
                            Log.debug("Unable to load " + str);
                            return false;
                        }
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = resourceAsStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    resourceAsStream.close();
                    bufferedOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            try {
                System.load(file2.getAbsolutePath());
                return true;
            } catch (UnsatisfiedLinkError e3) {
                Log.error("Error loading library " + str + " from " + file2.getAbsolutePath() + ".  Error message: " + e3.getMessage());
                e3.printStackTrace();
                return false;
            }
        }
    }

    private static String getLibraryPath() {
        IPlatform instance = Platform.getInstance();
        OperatingSystem operatingSystem = instance.getOperatingSystem();
        Architecture architecture = instance.getArchitecture();
        if (operatingSystem == OperatingSystem.MacOS) {
            return "/libs/mac_x64";
        }
        if (operatingSystem == OperatingSystem.Windows) {
            if (architecture == Architecture.X86) {
                return "/libs/win_x86";
            }
            if (architecture == Architecture.X64) {
                return "/libs/win_x64";
            }
            throw new RuntimeException("Unsupported arch.");
        } else if (operatingSystem != OperatingSystem.Linux) {
            throw new RuntimeException("Unknown or unsupported os.");
        } else if (architecture == Architecture.X86) {
            return "/libs/linux_x86";
        } else {
            if (architecture == Architecture.X64) {
                return "/libs/linux_x64";
            }
            if (architecture == Architecture.Armv7) {
                return "/libs/linux_armv7";
            }
            if (architecture == Architecture.Armv8) {
                return ".libs/linux_armv8";
            }
            if (architecture == Architecture.Arm64) {
                return "/libs/linux_arm64";
            }
            throw new RuntimeException("Unknown or unsupported arch.");
        }
    }
}
