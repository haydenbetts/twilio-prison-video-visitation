package permissions.dispatcher;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.legacy.app.FragmentCompat;

public final class PermissionUtils {
    private static final SimpleArrayMap<String, Integer> MIN_SDK_PERMISSIONS;

    static {
        SimpleArrayMap<String, Integer> simpleArrayMap = new SimpleArrayMap<>(8);
        MIN_SDK_PERMISSIONS = simpleArrayMap;
        simpleArrayMap.put("com.android.voicemail.permission.ADD_VOICEMAIL", 14);
        simpleArrayMap.put("android.permission.BODY_SENSORS", 20);
        simpleArrayMap.put("android.permission.READ_CALL_LOG", 16);
        simpleArrayMap.put("android.permission.READ_EXTERNAL_STORAGE", 16);
        simpleArrayMap.put("android.permission.USE_SIP", 9);
        simpleArrayMap.put("android.permission.WRITE_CALL_LOG", 16);
        simpleArrayMap.put("android.permission.SYSTEM_ALERT_WINDOW", 23);
        simpleArrayMap.put("android.permission.WRITE_SETTINGS", 23);
    }

    private PermissionUtils() {
    }

    public static boolean verifyPermissions(int... iArr) {
        if (iArr.length == 0) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean permissionExists(String str) {
        Integer num = MIN_SDK_PERMISSIONS.get(str);
        return num == null || Build.VERSION.SDK_INT >= num.intValue();
    }

    public static boolean hasSelfPermissions(Context context, String... strArr) {
        for (String str : strArr) {
            if (permissionExists(str) && !hasSelfPermission(context, str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasSelfPermission(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23 && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(context, str);
        }
        try {
            if (PermissionChecker.checkSelfPermission(context, str) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private static boolean hasSelfPermissionForXiaomi(Context context, String str) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return true;
        }
        if (AppOpsManagerCompat.noteOp(context, permissionToOp, Process.myUid(), context.getPackageName()) == 0 && PermissionChecker.checkSelfPermission(context, str) == 0) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... strArr) {
        for (String shouldShowRequestPermissionRationale : strArr) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, shouldShowRequestPermissionRationale)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String... strArr) {
        for (String shouldShowRequestPermissionRationale : strArr) {
            if (fragment.shouldShowRequestPermissionRationale(shouldShowRequestPermissionRationale)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean shouldShowRequestPermissionRationale(android.app.Fragment fragment, String... strArr) {
        for (String shouldShowRequestPermissionRationale : strArr) {
            if (FragmentCompat.shouldShowRequestPermissionRationale(fragment, shouldShowRequestPermissionRationale)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static void requestPermissions(android.app.Fragment fragment, String[] strArr, int i) {
        FragmentCompat.requestPermissions(fragment, strArr, i);
    }
}
