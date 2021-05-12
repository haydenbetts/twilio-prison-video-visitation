package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.google.firebase.messaging.Constants;
import com.urbanairship.analytics.data.EventsStorage;
import com.urbanairship.json.matchers.VersionMatcher;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;
import lib.android.paypal.com.magnessdk.a.c;
import lib.android.paypal.com.magnessdk.network.k;
import lib.android.paypal.com.magnessdk.network.m;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class d extends c {
    private String bA;
    private String bB;
    private int bC;
    private boolean bD;
    private boolean bE;
    private boolean bF;
    private long bG = -1;
    private long bH = -1;
    private long bI = -1;
    private JSONObject bJ;
    private JSONObject bK;
    private JSONObject bL;
    private JSONObject bM;
    private JSONObject bN;
    private JSONArray bO;
    private JSONObject bP;
    private m bQ;
    private String bo;
    private String bp;
    private String bq;
    private String br;
    private String bs;
    private String bt;
    private String bu;
    private String bv;
    private String bw;
    private String bx;
    private String by;
    private String bz;

    static final class a {
        private a() {
        }

        static boolean a() {
            return b() || c() || d() || e() || f() || g() || h() || i();
        }

        private static boolean b() {
            return Build.MANUFACTURER.equals("unknown") || Build.MANUFACTURER.equals("Genymotion") || Build.MANUFACTURER.contains("AndyOS");
        }

        private static boolean c() {
            return Build.BRAND.equals("generic") || Build.BRAND.equals("generic_x86") || Build.BRAND.equals("Android") || Build.BRAND.equals("AndyOS");
        }

        private static boolean d() {
            return Build.DEVICE.equals("AndyOSX") || Build.DEVICE.equals("Droid4X") || Build.DEVICE.equals("generic") || Build.DEVICE.equals("generic_x86") || Build.DEVICE.equals("vbox86p");
        }

        private static boolean e() {
            return Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.HARDWARE.equals("andy");
        }

        private static boolean f() {
            return Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk") || Build.MODEL.equals("Android SDK built for x86");
        }

        private static boolean g() {
            return Build.FINGERPRINT.startsWith("generic");
        }

        private static boolean h() {
            return Build.PRODUCT.matches(".*_?sdk_?.*") || Build.PRODUCT.equals("vbox86p") || Build.PRODUCT.equals("Genymotion") || Build.PRODUCT.equals("Driod4X") || Build.PRODUCT.equals("AndyOSX");
        }

        private static boolean i() {
            return new File(Environment.getExternalStorageDirectory().toString() + File.separatorChar + "windows" + File.separatorChar + "BstSharedFolder").exists();
        }
    }

    static final class b {
        private b() {
        }

        private static String a(String str) throws IOException {
            Properties properties = new Properties();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(lib.android.paypal.com.magnessdk.a.b.h.getBytes("UTF-8"));
            try {
                properties.load(byteArrayInputStream);
            } catch (Exception e) {
                lib.android.paypal.com.magnessdk.b.a.a((Class<?>) b.class, 3, (Throwable) e);
            } catch (Throwable th) {
                byteArrayInputStream.close();
                throw th;
            }
            byteArrayInputStream.close();
            return properties.getProperty(str);
        }

        static boolean a() {
            return c() || b() || d();
        }

        private static boolean b() {
            try {
                return new File(a("suFileName")).exists();
            } catch (Exception e) {
                lib.android.paypal.com.magnessdk.b.a.a((Class<?>) b.class, 3, (Throwable) e);
                return false;
            }
        }

        private static boolean c() {
            return Build.TAGS != null && Build.TAGS.contains("test-keys");
        }

        private static boolean d() {
            try {
                return new File(a("superUserApk")).exists();
            } catch (Exception e) {
                lib.android.paypal.com.magnessdk.b.a.a((Class<?>) b.class, 3, (Throwable) e);
                return false;
            }
        }
    }

    private long a(String str) {
        StatFs statFs = new StatFs(str);
        return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
    }

    private int b(int i) {
        int i2;
        File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
            public boolean accept(File file) {
                return Pattern.matches("cpu[0-9]+", file.getName());
            }
        });
        int i3 = c.d;
        if (i == 3) {
            i3 = listFiles.length;
        } else {
            int i4 = 0;
            if (i == 2) {
                int length = listFiles.length;
                i2 = Integer.MAX_VALUE;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    File file = listFiles[i4];
                    String b2 = c.b(new File(file.getPath() + "/cpufreq/cpuinfo_min_freq"));
                    if (b2 == null || b2.equals("-403")) {
                        break;
                    }
                    int parseInt = Integer.parseInt(b2);
                    if (parseInt < i2) {
                        i2 = parseInt;
                    }
                    i4++;
                }
            } else if (i == 1) {
                int length2 = listFiles.length;
                i2 = 0;
                while (true) {
                    if (i4 >= length2) {
                        break;
                    }
                    File file2 = listFiles[i4];
                    File file3 = new File(file2.getPath() + "/cpufreq/cpuinfo_max_freq");
                    String b3 = c.b(file3);
                    if (b3 == null || b3.equals("-403")) {
                        break;
                    }
                    int parseInt2 = Integer.parseInt(c.b(file3));
                    if (parseInt2 > i2) {
                        i2 = parseInt2;
                    }
                    i4++;
                }
            } else {
                i3 = 12345;
            }
            i3 = i2;
        }
        if (i3 == 0 || i3 == Integer.MAX_VALUE) {
            return 12345;
        }
        return i3;
    }

    private String b(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), lib.android.paypal.com.magnessdk.a.b.f);
    }

    private String b(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(lib.android.paypal.com.magnessdk.a.b.c, 0);
        String string = sharedPreferences.getString(lib.android.paypal.com.magnessdk.a.b.c, "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (str == null || str.equals(string)) {
            if (!string.equals("")) {
                return string;
            }
            Boolean bool = Boolean.TRUE;
            str = b.a(true);
        }
        edit.putString(lib.android.paypal.com.magnessdk.a.b.c, str);
        edit.apply();
        return str;
    }

    private long c() throws IllegalArgumentException {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
    }

    private String c(Context context) {
        Uri uri;
        Cursor query;
        try {
            uri = Uri.parse("content://com.google.android.gsf.gservices");
        } catch (Exception unused) {
            uri = null;
        }
        if (uri == null || !a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") || (query = context.getContentResolver().query(uri, (String[]) null, (String) null, new String[]{lib.android.paypal.com.magnessdk.a.b.f}, (String) null)) == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                if (query.getColumnCount() >= 2) {
                    String hexString = Long.toHexString(Long.parseLong(query.getString(1)));
                    query.close();
                    return hexString;
                }
            }
            return null;
        } catch (NumberFormatException e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
            return null;
        } finally {
            query.close();
        }
    }

    private String d(Context context) {
        WifiInfo connectionInfo = a(context, "android.permission.ACCESS_WIFI_STATE") ? ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo() : null;
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getMacAddress();
    }

    private JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        long a2 = a(Environment.getDataDirectory().getPath());
        try {
            jSONObject.put("total_sd", a((Object) Long.valueOf(Build.VERSION.SDK_INT >= 21 ? a(601) : -401)));
            jSONObject.put("total_ud", a((Object) Long.valueOf(a2)));
        } catch (JSONException e) {
            lib.android.paypal.com.magnessdk.b.a.a((Class<?>) d.class, 3, (Throwable) e);
        }
        return jSONObject;
    }

    private String e(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            int b2 = b(3);
            int b3 = b(2);
            int b4 = b(1);
            jSONObject.put("minFreq", a((Object) Integer.valueOf(b3)));
            jSONObject.put("maxFreq", a((Object) Integer.valueOf(b4)));
            jSONObject.put("cores", a((Object) Integer.valueOf(b2)));
        } catch (JSONException e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    private long f(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    private JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(VersionMatcher.ALTERNATE_VERSION_KEY, a((Object) c.b(new File("proc/version"))));
            jSONObject.put("board", a((Object) Build.BOARD));
            jSONObject.put("bootloader", a((Object) Build.BOOTLOADER));
            jSONObject.put("cpu_abi1", a((Object) Build.CPU_ABI));
            jSONObject.put(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, a((Object) Build.DISPLAY));
            jSONObject.put("radio", a((Object) Build.getRadioVersion()));
            jSONObject.put("fingerprint", a((Object) Build.FINGERPRINT));
            jSONObject.put("hardware", a((Object) Build.HARDWARE));
            jSONObject.put("manufacturer", a((Object) Build.MANUFACTURER));
            jSONObject.put("product", a((Object) Build.PRODUCT));
            jSONObject.put(EventsStorage.Events.COLUMN_NAME_TIME, a((Object) Long.valueOf(Build.TIME)));
        } catch (JSONException e) {
            lib.android.paypal.com.magnessdk.b.a.a((Class<?>) d.class, 3, (Throwable) e);
        }
        return jSONObject;
    }

    private long g(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
    }

    private JSONObject h(Context context) {
        float f;
        float f2;
        float f3;
        int i;
        float f4;
        int i2;
        int i3;
        JSONObject jSONObject = new JSONObject();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int i4 = 12345;
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            if (Build.VERSION.SDK_INT >= 17) {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                i3 = point.x;
                i2 = point.y;
            } else {
                i3 = -401;
                i2 = -401;
            }
            f4 = displayMetrics.density;
            i = displayMetrics.densityDpi;
            f3 = displayMetrics.scaledDensity;
            f2 = displayMetrics.xdpi;
            float f5 = displayMetrics.ydpi;
            i4 = i3;
            f = f5;
        } else {
            f = 12345.0f;
            i2 = 12345;
            f4 = 12345.0f;
            i = 12345;
            f3 = 12345.0f;
            f2 = 12345.0f;
        }
        try {
            jSONObject.put("width", a((Object) Integer.valueOf(i4)));
            jSONObject.put("height", a((Object) Integer.valueOf(i2)));
            jSONObject.put("density", a((Object) Float.valueOf(f4)));
            jSONObject.put("densityDpi", a((Object) Integer.valueOf(i)));
            jSONObject.put("scale", a((Object) Float.valueOf(f3)));
            jSONObject.put("xdpi", a((Object) Float.valueOf(f2)));
            jSONObject.put("ydpi", a((Object) Float.valueOf(f)));
        } catch (Exception e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    private JSONObject i(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dua", a((Object) Build.VERSION.SDK_INT >= 17 ? WebSettings.getDefaultUserAgent(context) : System.getProperty("http.agent")));
        } catch (Exception e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_guid", this.bo);
            jSONObject.put("app_id", this.bp);
            jSONObject.put(lib.android.paypal.com.magnessdk.a.b.f, this.bu);
            jSONObject.put("app_version", this.bq);
            long j = this.bH;
            Long l = null;
            jSONObject.put("app_first_install_time", j == -1 ? null : Long.valueOf(j));
            long j2 = this.bI;
            jSONObject.put("app_last_update_time", j2 == -1 ? null : Long.valueOf(j2));
            jSONObject.put("conf_url", this.bA);
            jSONObject.put("comp_version", this.bB);
            jSONObject.put("device_model", this.br);
            jSONObject.put("device_name", this.bs);
            jSONObject.put("gsf_id", this.bv);
            jSONObject.put("is_emulator", this.bE);
            jSONObject.put("is_rooted", this.bF);
            jSONObject.put("os_type", "Android");
            jSONObject.put("os_version", this.bt);
            jSONObject.put("payload_type", this.bx);
            jSONObject.put("sms_enabled", this.bD);
            jSONObject.put("mac_addrs", this.bw);
            jSONObject.put("magnes_guid", this.bJ);
            int i = this.bC;
            jSONObject.put("magnes_source", i == 0 ? null : Integer.valueOf(i));
            jSONObject.put("notif_token", this.bz);
            jSONObject.put("source_app_version", this.by);
            long j3 = this.bG;
            if (j3 != -1) {
                l = Long.valueOf(j3);
            }
            jSONObject.put("total_storage_space", l);
            jSONObject.put("nc", this.bO);
            jSONObject.put(MainActivity.SCREEN_KEY, this.bK);
            jSONObject.put("cpu", this.bL);
            jSONObject.put("disk", this.bM);
            jSONObject.put("system", this.bN);
            jSONObject.put("user_agent", this.bP);
            jSONObject.put("t", be);
            return jSONObject;
        } catch (JSONException e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
            return jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MagnesSettings magnesSettings, k kVar, m mVar) {
        this.bQ = mVar;
        lib.android.paypal.com.magnessdk.b.a.a(getClass(), 0, "collecting RiskBlobCoreData");
        a(1, magnesSettings);
        a(2, magnesSettings);
        a(3, magnesSettings);
        a(65, magnesSettings);
        a(66, magnesSettings);
        a(69, magnesSettings);
        a(8, magnesSettings);
        a(9, magnesSettings);
        a(14, magnesSettings);
        a(15, magnesSettings);
        a(70, magnesSettings);
        a(59, magnesSettings);
        a(60, magnesSettings);
        a(32, magnesSettings);
        a(86, magnesSettings);
        a(62, magnesSettings);
        a(34, magnesSettings);
        a(37, magnesSettings);
        a(38, magnesSettings);
        a(63, magnesSettings);
        a(47, magnesSettings);
        a(52, magnesSettings);
        a(88, magnesSettings);
        be = false;
        if (a(kVar, bf, c.bl)) {
            a(91, magnesSettings);
            a(90, magnesSettings);
            a(93, magnesSettings);
            a(94, magnesSettings);
            a(95, magnesSettings);
        }
        return a();
    }

    /* access modifiers changed from: package-private */
    public void a(int i, MagnesSettings magnesSettings) {
        try {
            Context context = magnesSettings.getContext();
            if (i == 1) {
                this.bo = b(context, magnesSettings.getAppGuid());
            } else if (i == 2) {
                this.bp = context.getPackageName();
            } else if (i != 3) {
                if (i != 32) {
                    if (i != 34) {
                        if (i != 47) {
                            if (i != 52) {
                                if (i == 86) {
                                    JSONObject a2 = a(context);
                                    this.bJ = a2;
                                    c.bf = a2.optString("id");
                                } else if (i != 88) {
                                    if (i == 8) {
                                        this.bB = a.f;
                                    } else if (i != 9) {
                                        if (i != 14) {
                                            if (i != 15) {
                                                if (i != 37) {
                                                    if (i != 38) {
                                                        if (i != 59) {
                                                            if (i != 60) {
                                                                if (i != 62) {
                                                                    if (i != 63) {
                                                                        if (i != 65) {
                                                                            if (i != 66) {
                                                                                if (i != 69) {
                                                                                    if (i != 70) {
                                                                                        if (i != 90) {
                                                                                            if (i != 91) {
                                                                                                switch (i) {
                                                                                                    case 93:
                                                                                                        if (this.bQ.a(i)) {
                                                                                                            this.bM = d();
                                                                                                            return;
                                                                                                        }
                                                                                                        return;
                                                                                                    case 94:
                                                                                                        if (this.bQ.a(i)) {
                                                                                                            this.bN = f();
                                                                                                            return;
                                                                                                        }
                                                                                                        return;
                                                                                                    case 95:
                                                                                                        if (this.bQ.a(i)) {
                                                                                                            this.bP = i(context);
                                                                                                            return;
                                                                                                        }
                                                                                                        return;
                                                                                                    default:
                                                                                                        return;
                                                                                                }
                                                                                            } else if (this.bQ.a(i)) {
                                                                                                this.bK = h(context);
                                                                                            }
                                                                                        } else if (this.bQ.a(i)) {
                                                                                            this.bL = e();
                                                                                        }
                                                                                    } else if (this.bQ.a(i)) {
                                                                                        this.bv = c(context);
                                                                                    }
                                                                                } else if (this.bQ.a(i)) {
                                                                                    this.bu = b(context);
                                                                                }
                                                                            } else if (this.bQ.a(i)) {
                                                                                this.bI = f(context);
                                                                            }
                                                                        } else if (this.bQ.a(i)) {
                                                                            this.bH = g(context);
                                                                        }
                                                                    } else if (this.bQ.a(i)) {
                                                                        this.by = e(context);
                                                                    }
                                                                } else if (this.bQ.a(i)) {
                                                                    this.bC = magnesSettings.getMagnesSource();
                                                                }
                                                            } else if (this.bQ.a(i)) {
                                                                this.bF = b.a();
                                                            }
                                                        } else if (this.bQ.a(i)) {
                                                            this.bE = a.a();
                                                        }
                                                    } else if (this.bQ.a(i)) {
                                                        this.bx = lib.android.paypal.com.magnessdk.a.b.g;
                                                    }
                                                } else if (this.bQ.a(i)) {
                                                    this.bt = Build.VERSION.RELEASE;
                                                }
                                            } else if (this.bQ.a(i)) {
                                                this.bs = Build.DEVICE;
                                            }
                                        } else if (this.bQ.a(i)) {
                                            this.br = Build.MODEL;
                                        }
                                    } else if (this.bQ.a(i)) {
                                        this.bA = "https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_config_android_v4.json";
                                    }
                                } else if (this.bQ.g()) {
                                    this.bO = this.bQ.h();
                                }
                            } else if (this.bQ.a(i)) {
                                this.bG = c();
                            }
                        } else if (this.bQ.a(i)) {
                            this.bD = context.getPackageManager().hasSystemFeature("android.hardware.telephony");
                        }
                    } else if (this.bQ.a(i)) {
                        this.bz = magnesSettings.getNotificationToken();
                    }
                } else if (this.bQ.a(i)) {
                    this.bw = d(context);
                }
            } else if (this.bQ.a(i)) {
                this.bq = e(context);
            }
        } catch (Exception e) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.bo;
    }
}
