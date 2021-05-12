package fm.liveswitch;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.attribute.FileAttribute;

public class PathUtility {
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String getAbsolutePath(String str) {
        return new File(str).getAbsolutePath();
    }

    public static String combinePaths(String str, String str2) {
        if (str.isEmpty()) {
            return str2;
        }
        if (str2.isEmpty()) {
            return str;
        }
        return new File(new File(str), str2).getPath();
    }

    public static String combinePaths(String[] strArr) {
        if (strArr.length == 0) {
            return "";
        }
        File file = null;
        for (int i = 0; i < strArr.length; i++) {
            if (!strArr[i].isEmpty()) {
                if (file == null) {
                    file = new File(strArr[i]);
                } else {
                    file = new File(file, strArr[i]);
                }
            }
        }
        return file.getPath();
    }

    public static void delete(String str) throws IOException {
        new File(str).delete();
    }

    public static void createSymlink(String str, String str2) {
        try {
            if (Platform.getInstance().getOperatingSystem() == OperatingSystem.Android) {
                Class.forName("android.system.Os").getMethod("symlink", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
                return;
            }
            Object invoke = Class.forName("java.nio.file.FileSystems").getMethod("getDefault", new Class[0]).invoke((Object) null, new Object[0]);
            Method method = Class.forName("java.nio.file.FileSystem").getMethod("getPath", new Class[]{String.class, String[].class});
            Object invoke2 = method.invoke(invoke, new Object[]{str, new String[0]});
            Object invoke3 = method.invoke(invoke, new Object[]{str2, new String[0]});
            Class<?> cls = Class.forName("java.nio.file.Files");
            Class<?> cls2 = Class.forName("java.nio.file.Path");
            cls.getMethod("createSymbolicLink", new Class[]{cls2, cls2, FileAttribute[].class}).invoke((Object) null, new Object[]{invoke3, invoke2, new FileAttribute[0]});
        } catch (Exception e) {
            Log.error("Could not create symbolic link.", e);
        }
    }
}
