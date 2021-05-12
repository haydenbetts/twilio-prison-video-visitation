package lib.android.paypal.com.magnessdk;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.MessageCenterDataManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import lib.android.paypal.com.magnessdk.b.a;
import lib.android.paypal.com.magnessdk.network.k;
import lib.android.paypal.com.magnessdk.network.m;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class e extends c {
    private static final int bo = 255;
    private static final int bp = 256;
    private static final int bq = 16;
    private static final int br = 32;
    private static final int bs = 32;
    private static final String bt = "invalid_input";
    private static final String bu = "SG1hY1NIQTI1Ng==";
    private static final String bv = "RiskManagerCT";
    private static final String bw = "00:00:00:00:00:00";
    private int bA = -1;
    private int bB = -1;
    private int bC = -1;
    private String bD;
    private String bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String bJ;
    private String bK;
    private String bL;
    private String bM;
    private String bN;
    private String bO;
    private String bP;
    private String bQ;
    private String bR;
    private String bS;
    private String bT;
    private String bU;
    private String bV;
    private String bW;
    private String bX;
    private String bY;
    private String bZ;
    private int bx = -1;
    private int by = -1;
    private int bz = -1;
    private PowerManager cA;
    private PackageManager cB;
    private Location cC;
    private JSONObject cD;
    private JSONObject cE;
    private JSONObject cF;
    private JSONObject cG;
    private JSONObject cH;
    private Handler cI;
    private m cJ;
    private String ca;
    private String cb;
    private List<String> cc;
    private List<String> cd;
    private List<String> ce;
    private long cf = -1;
    private long cg = -1;
    private boolean ch;
    private boolean ci;
    private boolean cj;
    private boolean ck;

    /* renamed from: cl  reason: collision with root package name */
    private boolean f7cl;
    private boolean cm;
    private boolean cn;
    private boolean co;
    private boolean cp;
    private Map<String, String> cq;
    private NetworkInfo cr;
    private WifiInfo cs;
    private GsmCellLocation ct;
    private CdmaCellLocation cu;
    private TelephonyManager cv;
    private WifiManager cw;
    private ConnectivityManager cx;
    private BatteryManager cy;
    private LocationManager cz;

    e() {
    }

    private Location a(LocationManager locationManager) {
        Location location = null;
        if (locationManager == null) {
            return null;
        }
        try {
            List<String> providers = locationManager.getProviders(true);
            for (int size = providers.size() - 1; size >= 0; size--) {
                location = locationManager.getLastKnownLocation(providers.get(size));
                if (location != null) {
                    break;
                }
            }
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
        }
        return location;
    }

    private String a(String str) throws Exception {
        if (str == null || str.isEmpty()) {
            str = "invalid input in dc method";
        }
        MessageDigest instance = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toString((b & UByte.MAX_VALUE) + UByte.MIN_VALUE, 16).substring(1));
        }
        return sb.toString().substring(0, 32);
    }

    private String a(String str, String str2, long j, String str3) throws Exception {
        String str4;
        String str5;
        StringBuilder sb;
        if (!b.a((Object) str) || !b.a((Object) str2) || !b.a((Object) Long.valueOf(j))) {
            if (b.a((Object) str)) {
                str = "";
            }
            if (b.a((Object) str2)) {
                str2 = "";
            }
            if (b.a((Object) Long.valueOf(j))) {
                sb = new StringBuilder();
                sb.append(str);
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(j);
            }
            sb.append(str2);
            str4 = sb.toString();
        } else {
            str4 = bt;
        }
        String a = b.a(bu);
        if (b.a((Object) Long.valueOf(j))) {
            str5 = b.a(str3);
        } else {
            str5 = b.a(str3) + j;
        }
        Mac instance = Mac.getInstance(a);
        instance.init(new SecretKeySpec(str5.getBytes(), a));
        byte[] doFinal = instance.doFinal(str4.getBytes());
        StringBuilder sb2 = new StringBuilder();
        for (byte b : doFinal) {
            sb2.append(Integer.toString((b & UByte.MAX_VALUE) + UByte.MIN_VALUE, 16).substring(1));
        }
        return sb2.toString().substring(0, 32);
    }

    private ArrayList<String> a(WifiManager wifiManager) {
        int i;
        if (wifiManager == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (!(scanResults == null || scanResults.size() == 0)) {
            int i2 = Integer.MIN_VALUE;
            String bssid = wifiManager.getConnectionInfo().getBSSID();
            if (bssid != null && !bssid.equals(bw)) {
                int i3 = -1;
                for (int i4 = 0; i4 < scanResults.size(); i4++) {
                    if (!bssid.equals(scanResults.get(i4).BSSID) && i2 < (i = scanResults.get(i4).level)) {
                        i3 = i4;
                        i2 = i;
                    }
                }
                arrayList.add(bssid);
                if (i3 != -1) {
                    arrayList.add(scanResults.get(i3).BSSID);
                }
                return arrayList;
            }
        }
        return null;
    }

    private List<String> a(boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses != null && inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        String hostAddress = nextElement.getHostAddress();
                        if (!(nextElement instanceof Inet6Address) || z) {
                            arrayList.add(hostAddress);
                        }
                    }
                }
            }
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private JSONObject a(Context context, BatteryManager batteryManager, PowerManager powerManager) {
        int i;
        int i2;
        int i3;
        int i4;
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        JSONObject jSONObject = new JSONObject();
        double d = 12345.0d;
        int i5 = 12345;
        if (registerReceiver != null) {
            double intExtra = (double) registerReceiver.getIntExtra("level", 12345);
            int intExtra2 = registerReceiver.getIntExtra("scale", 12345);
            int intExtra3 = registerReceiver.getIntExtra("temperature", 12345);
            i2 = registerReceiver.getIntExtra("voltage", 12345);
            i = registerReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, 12345);
            i3 = registerReceiver.getIntExtra("plugged", 12345);
            d = (intExtra == 12345.0d || intExtra2 == 12345) ? intExtra : intExtra / ((double) intExtra2);
            i5 = intExtra3;
        } else {
            i3 = 12345;
            i2 = 12345;
            i = 12345;
        }
        int i6 = -401;
        if (Build.VERSION.SDK_INT >= 21) {
            i6 = batteryManager.getIntProperty(2);
            i4 = powerManager.isPowerSaveMode();
        } else {
            i4 = -401;
        }
        try {
            jSONObject.put("current", a((Object) Integer.valueOf(i6)));
            jSONObject.put("powerSaveMode", a((Object) Integer.valueOf(i4)));
            jSONObject.put("temp", a((Object) Integer.valueOf(i5)));
            jSONObject.put("voltage", a((Object) Integer.valueOf(i2)));
            jSONObject.put("state", a((Object) Integer.valueOf(i)));
            jSONObject.put("method", a((Object) Integer.valueOf(i3)));
            jSONObject.put("level", new DecimalFormat(".##").format(a((Object) Double.valueOf(d))));
        } catch (JSONException e) {
            a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    private JSONObject a(Location location) {
        if (location != null) {
            try {
                return new JSONObject("{\"lat\":" + location.getLatitude() + ",\"lng\":" + location.getLongitude() + ",\"acc\":" + location.getAccuracy() + ",\"timestamp\":" + location.getTime() + "}");
            } catch (Exception e) {
                a.a(getClass(), 3, (Throwable) e);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.telephony.gsm.GsmCellLocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.telephony.cdma.CdmaCellLocation} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.telephony.TelephonyManager r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r5.getPhoneType()
            if (r0 == 0) goto L_0x0065
            r1 = 1
            r2 = 0
            r3 = 3
            if (r0 == r1) goto L_0x0044
            r1 = 2
            if (r0 == r1) goto L_0x002c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unknown ("
            r0.append(r1)
            int r5 = r5.getPhoneType()
            r0.append(r5)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            goto L_0x0067
        L_0x002c:
            java.lang.String r0 = "cdma"
            r4.bR = r0
            boolean r0 = r4.ck     // Catch:{ Exception -> 0x005c }
            if (r0 == 0) goto L_0x0041
            android.telephony.CellLocation r5 = r5.getCellLocation()     // Catch:{ Exception -> 0x005c }
            java.lang.Class<android.telephony.cdma.CdmaCellLocation> r0 = android.telephony.cdma.CdmaCellLocation.class
            java.lang.Object r5 = lib.android.paypal.com.magnessdk.b.a((java.lang.Object) r5, r0)     // Catch:{ Exception -> 0x005c }
            r2 = r5
            android.telephony.cdma.CdmaCellLocation r2 = (android.telephony.cdma.CdmaCellLocation) r2     // Catch:{ Exception -> 0x005c }
        L_0x0041:
            r4.cu = r2     // Catch:{ Exception -> 0x005c }
            goto L_0x0069
        L_0x0044:
            java.lang.String r0 = "gsm"
            r4.bR = r0
            boolean r0 = r4.ck     // Catch:{ Exception -> 0x005c }
            if (r0 == 0) goto L_0x0059
            android.telephony.CellLocation r5 = r5.getCellLocation()     // Catch:{ Exception -> 0x005c }
            java.lang.Class<android.telephony.gsm.GsmCellLocation> r0 = android.telephony.gsm.GsmCellLocation.class
            java.lang.Object r5 = lib.android.paypal.com.magnessdk.b.a((java.lang.Object) r5, r0)     // Catch:{ Exception -> 0x005c }
            r2 = r5
            android.telephony.gsm.GsmCellLocation r2 = (android.telephony.gsm.GsmCellLocation) r2     // Catch:{ Exception -> 0x005c }
        L_0x0059:
            r4.ct = r2     // Catch:{ Exception -> 0x005c }
            goto L_0x0069
        L_0x005c:
            r5 = move-exception
            java.lang.Class r0 = r4.getClass()
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r0, (int) r3, (java.lang.Throwable) r5)
            goto L_0x0069
        L_0x0065:
            java.lang.String r5 = "none"
        L_0x0067:
            r4.bR = r5
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.e.a(android.telephony.TelephonyManager):void");
    }

    private long b(String str) {
        StatFs statFs = new StatFs(str);
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    private String b() {
        List<String> a = a(false);
        if (a == null) {
            return null;
        }
        return a.get(0);
    }

    private String b(TelephonyManager telephonyManager) {
        try {
            return telephonyManager.getSimOperatorName();
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
            return null;
        }
    }

    private JSONObject b(Context context) {
        boolean z;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        JSONObject jSONObject = new JSONObject();
        boolean z2 = false;
        if (sensorManager != null) {
            try {
                if (sensorManager.getDefaultSensor(1) != null) {
                    z = true;
                    jSONObject.put("ac", z);
                    if (!(sensorManager == null || sensorManager.getDefaultSensor(4) == null)) {
                        z2 = true;
                    }
                    jSONObject.put("gy", z2);
                    return jSONObject;
                }
            } catch (JSONException e) {
                a.a(getClass(), 3, (Throwable) e);
            }
        }
        z = false;
        jSONObject.put("ac", z);
        z2 = true;
        jSONObject.put("gy", z2);
        return jSONObject;
    }

    private void b(JSONObject jSONObject) {
        Map<String, String> map = this.cq;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                try {
                    jSONObject.put((String) next.getKey(), next.getValue());
                } catch (Exception e) {
                    a.a(getClass(), 3, (Throwable) e);
                }
            }
        }
    }

    private String c() {
        String property;
        String property2 = System.getProperty("http.proxyHost");
        if (property2 == null || (property = System.getProperty("http.proxyPort")) == null) {
            return null;
        }
        return "host=" + property2 + ",port=" + property;
    }

    private JSONObject c(Context context) {
        int i;
        JSONObject jSONObject = new JSONObject();
        try {
            i = Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            a.a(getClass(), 3, (Throwable) e);
            i = c.d;
        }
        try {
            jSONObject.put("brightness", a((Object) Integer.valueOf(i)));
        } catch (JSONException e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
        return jSONObject;
    }

    private String d() {
        try {
            Iterator<T> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    if (networkInterface.getInterfaceAddresses().size() != 0) {
                        String name = networkInterface.getName();
                        if (name.startsWith("ppp") || name.startsWith("tun") || name.startsWith("tap")) {
                            return name;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
            return null;
        }
    }

    private String d(Context context) {
        int i = context.getSharedPreferences(bv, 0).getInt(bv, 0);
        return i + "";
    }

    private String e() throws IOException {
        lib.android.paypal.com.magnessdk.a.a aVar = new lib.android.paypal.com.magnessdk.a.a();
        aVar.a(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.ebay.lid/");
        try {
            if (this.cn || this.cm) {
                return aVar.b("fb.bin");
            }
            return null;
        } catch (FileNotFoundException unused) {
            if (!this.cn) {
                return null;
            }
            Boolean bool = Boolean.TRUE;
            String a = b.a(true);
            aVar.a("fb.bin", a.getBytes("UTF-8"));
            return a;
        } catch (IOException e) {
            a.a(getClass(), 3, (Throwable) e);
            return null;
        }
    }

    private void e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(bv, 0);
        int i = sharedPreferences.getInt(bv, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i2 = 1;
        if (i > 0 && i < Integer.MAX_VALUE) {
            i2 = 1 + i;
        }
        edit.putInt(bv, i2);
        edit.apply();
    }

    private String f() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ck ? 1 : 0);
        sb.append(this.f7cl ? 1 : 0);
        sb.append(this.co ? 1 : 0);
        sb.append(this.cp ? 1 : 0);
        sb.append(this.cm ? 1 : 0);
        sb.append(this.cn ? 1 : 0);
        return sb.toString();
    }

    private JSONObject f(Context context) {
        long j;
        JSONObject jSONObject = new JSONObject();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long j2 = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long j3 = 12345;
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            j3 = memoryInfo.availMem;
            j = memoryInfo.totalMem;
        } else {
            j = 12345;
        }
        try {
            jSONObject.put("free", a((Object) Long.valueOf(j3)));
            jSONObject.put("total", a((Object) Long.valueOf(j)));
            jSONObject.put("max_runtime", a((Object) Long.valueOf(maxMemory)));
            jSONObject.put("total_runtime", a((Object) Long.valueOf(j2)));
            jSONObject.put("free_runtime", a((Object) Long.valueOf(freeMemory)));
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    private JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        boolean z = Environment.isExternalStorageRemovable() && "mounted".equals(Environment.getExternalStorageState());
        long b = b(Environment.getDataDirectory().getPath());
        long a = Build.VERSION.SDK_INT >= 21 ? super.a(600) : -401;
        try {
            jSONObject.put("mounted", z);
            jSONObject.put("free_ud", a((Object) Long.valueOf(b)));
            jSONObject.put("free_sd", a((Object) Long.valueOf(a)));
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = this.bx;
            Integer num = null;
            jSONObject.put("base_station_id", i == -1 ? null : Integer.valueOf(i));
            jSONObject.put("bssid", this.bD);
            jSONObject.put("bssid_array", this.ce == null ? null : new JSONArray(this.ce));
            int i2 = this.by;
            jSONObject.put("cell_id", i2 == -1 ? null : Integer.valueOf(i2));
            jSONObject.put("conn_type", this.bL);
            jSONObject.put("conf_version", this.bW);
            jSONObject.put("device_id", this.bM);
            jSONObject.put("dc_id", this.bK);
            long j = this.cg;
            jSONObject.put("device_uptime", j == -1 ? null : Long.valueOf(j));
            jSONObject.put("ip_addrs", this.bN);
            jSONObject.put("ip_addresses", this.cc == null ? null : new JSONArray(this.cc));
            jSONObject.put("known_apps", this.cd == null ? null : new JSONArray(this.cd));
            jSONObject.put("locale_country", this.bP);
            jSONObject.put("locale_lang", this.bQ);
            jSONObject.put("location", a(this.cC));
            int i3 = this.bC;
            jSONObject.put("location_area_code", i3 == -1 ? null : Integer.valueOf(i3));
            jSONObject.put("phone_type", this.bR);
            jSONObject.put("risk_comp_session_id", this.bS);
            jSONObject.put("roaming", this.ch);
            jSONObject.put("sim_operator_name", this.bZ);
            jSONObject.put("sim_serial_number", this.bT);
            jSONObject.put("ssid", this.bU);
            int i4 = this.bB;
            jSONObject.put("cdma_network_id", i4 == -1 ? null : Integer.valueOf(i4));
            int i5 = this.bA;
            jSONObject.put("cdma_system_id", i5 == -1 ? null : Integer.valueOf(i5));
            jSONObject.put("subscriber_id", this.bV);
            long j2 = this.cf;
            jSONObject.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, j2 == -1 ? null : Long.valueOf(j2));
            jSONObject.put("tz_name", this.bO);
            jSONObject.put("ds", this.ci);
            int i6 = this.bz;
            if (i6 != -1) {
                num = Integer.valueOf(i6);
            }
            jSONObject.put("tz", num);
            jSONObject.put("network_operator", this.bE);
            jSONObject.put("pairing_id", this.bF);
            jSONObject.put("serial_number", this.bG);
            jSONObject.put("VPN_setting", this.bI);
            jSONObject.put("proxy_setting", this.bH);
            jSONObject.put("c", this.bJ);
            jSONObject.put("mg_id", this.bX);
            jSONObject.put("linker_id", this.bY);
            jSONObject.put("pl", this.ca);
            jSONObject.put("battery", this.cD);
            jSONObject.put("memory", this.cE);
            jSONObject.put("disk", this.cF);
            jSONObject.put(MainActivity.SCREEN_KEY, this.cG);
            jSONObject.put("sr", this.cH);
            jSONObject.put("t", be);
            b(jSONObject);
            return jSONObject;
        } catch (Exception e) {
            a.a(getClass(), 3, (Throwable) e);
            return jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MagnesSettings magnesSettings, k kVar, m mVar) {
        return a(magnesSettings, kVar, mVar, this.cb, (String) null, (HashMap<String, String>) null, this.cI);
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(MagnesSettings magnesSettings, k kVar, m mVar, String str, String str2, HashMap<String, String> hashMap, Handler handler) {
        TelephonyManager telephonyManager;
        a.a(getClass(), 0, "collecting RiskBlobDynamicData");
        this.cJ = mVar;
        Context context = magnesSettings.getContext();
        this.cv = (TelephonyManager) context.getSystemService(ShippingInfoWidget.PHONE_FIELD);
        this.cw = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.cz = (LocationManager) context.getSystemService("location");
        this.cx = (ConnectivityManager) context.getSystemService("connectivity");
        if (Build.VERSION.SDK_INT >= 21) {
            this.cy = (BatteryManager) context.getSystemService("batterymanager");
            this.cA = (PowerManager) context.getSystemService("power");
        }
        this.cB = context.getPackageManager();
        boolean z = true;
        this.ck = a(context, "android.permission.ACCESS_COARSE_LOCATION") || a(context, "android.permission.ACCESS_FINE_LOCATION");
        this.cm = a(context, "android.permission.READ_EXTERNAL_STORAGE");
        this.cn = a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        this.f7cl = a(context, "android.permission.READ_PHONE_STATE");
        this.cp = a(context, "android.permission.ACCESS_NETWORK_STATE");
        this.co = a(context, "android.permission.ACCESS_WIFI_STATE");
        this.cq = hashMap;
        this.cf = System.currentTimeMillis();
        this.bW = mVar.b();
        this.bF = str2;
        this.cb = str;
        this.cI = handler;
        if (str2 == null) {
            this.bF = b.a(false);
        }
        a(this.cv);
        WifiManager wifiManager = this.cw;
        NetworkInfo networkInfo = null;
        if (wifiManager != null) {
            this.cs = this.co ? wifiManager.getConnectionInfo() : null;
        }
        ConnectivityManager connectivityManager = this.cx;
        if (connectivityManager != null) {
            if (this.cp) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            this.cr = networkInfo;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (!a(context, "android.permission.READ_PRIVILEGED_PHONE_STATE") && ((telephonyManager = this.cv) == null || !telephonyManager.hasCarrierPrivileges())) {
                z = false;
            }
            this.cj = z;
        }
        a(82, magnesSettings);
        a(81, magnesSettings);
        a(16, magnesSettings);
        a(21, magnesSettings);
        a(75, magnesSettings);
        a(23, magnesSettings);
        a(27, magnesSettings);
        a(28, magnesSettings);
        a(25, magnesSettings);
        a(56, magnesSettings);
        a(72, magnesSettings);
        a(42, magnesSettings);
        a(43, magnesSettings);
        a(45, magnesSettings);
        a(53, magnesSettings);
        a(80, magnesSettings);
        a(71, magnesSettings);
        a(4, magnesSettings);
        a(57, magnesSettings);
        a(58, magnesSettings);
        a(6, magnesSettings);
        a(30, magnesSettings);
        a(29, magnesSettings);
        a(13, magnesSettings);
        a(68, magnesSettings);
        a(49, magnesSettings);
        a(84, magnesSettings);
        a(5, magnesSettings);
        a(48, magnesSettings);
        a(11, magnesSettings);
        a(85, magnesSettings);
        a(46, magnesSettings);
        a(79, magnesSettings);
        a(87, magnesSettings);
        a(98, magnesSettings);
        be = false;
        if (a(kVar, bf, c.bl)) {
            a(89, magnesSettings);
            a(92, magnesSettings);
            a(93, magnesSettings);
            a(91, magnesSettings);
        }
        a.a(getClass(), 0, "finishing RiskBlobDynamicData");
        return a();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: android.location.Location} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.util.List<java.lang.String>] */
    /* JADX WARNING: type inference failed for: r3v12, types: [java.util.List<java.lang.String>] */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x03fd, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x03ff, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0400, code lost:
        lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x03ff A[ExcHandler: AssertionError (e java.lang.AssertionError), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r8, lib.android.paypal.com.magnessdk.MagnesSettings r9) {
        /*
            r7 = this;
            r0 = 3
            android.content.Context r9 = r9.getContext()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r1 = 4
            r2 = -1
            if (r8 == r1) goto L_0x03e9
            r1 = 5
            r3 = 0
            if (r8 == r1) goto L_0x03d5
            r1 = 6
            if (r8 == r1) goto L_0x03c1
            r1 = 42
            r4 = 1
            if (r8 == r1) goto L_0x03b2
            r1 = 43
            if (r8 == r1) goto L_0x039e
            r1 = 45
            if (r8 == r1) goto L_0x0389
            r1 = 46
            r5 = 29
            if (r8 == r1) goto L_0x0367
            r1 = 48
            if (r8 == r1) goto L_0x0352
            r1 = 49
            if (r8 == r1) goto L_0x0330
            r1 = 71
            if (r8 == r1) goto L_0x0320
            r1 = 72
            if (r8 == r1) goto L_0x0310
            r1 = 84
            if (r8 == r1) goto L_0x02fa
            r1 = 85
            if (r8 == r1) goto L_0x02e5
            r1 = 26
            switch(r8) {
                case 11: goto L_0x02d0;
                case 13: goto L_0x028b;
                case 16: goto L_0x027b;
                case 21: goto L_0x026b;
                case 23: goto L_0x0219;
                case 25: goto L_0x0209;
                case 27: goto L_0x01f5;
                case 28: goto L_0x01e1;
                case 29: goto L_0x01cb;
                case 30: goto L_0x01b6;
                case 53: goto L_0x0193;
                case 68: goto L_0x016c;
                case 75: goto L_0x015c;
                case 87: goto L_0x0154;
                case 89: goto L_0x0140;
                case 98: goto L_0x0130;
                default: goto L_0x0040;
            }     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0040:
            switch(r8) {
                case 56: goto L_0x011b;
                case 57: goto L_0x0106;
                case 58: goto L_0x00f1;
                default: goto L_0x0043;
            }     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0043:
            switch(r8) {
                case 79: goto L_0x00d8;
                case 80: goto L_0x00bb;
                case 81: goto L_0x00a0;
                case 82: goto L_0x007b;
                default: goto L_0x0046;
            }     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0046:
            switch(r8) {
                case 91: goto L_0x006b;
                case 92: goto L_0x005b;
                case 93: goto L_0x004b;
                default: goto L_0x0049;
            }     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0049:
            goto L_0x0407
        L_0x004b:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            org.json.JSONObject r8 = r7.g()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cF = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x005b:
            lib.android.paypal.com.magnessdk.network.m r1 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r1.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            org.json.JSONObject r8 = r7.f(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cE = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x006b:
            lib.android.paypal.com.magnessdk.network.m r1 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r1.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            org.json.JSONObject r8 = r7.c(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cG = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x007b:
            lib.android.paypal.com.magnessdk.network.m r1 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r1.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            lib.android.paypal.com.magnessdk.MagnesSDK r8 = lib.android.paypal.com.magnessdk.MagnesSDK.getInstance()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            lib.android.paypal.com.magnessdk.MagnesSettings r8 = r8.b     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            int r8 = r8.getMagnesSource()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            lib.android.paypal.com.magnessdk.MagnesSource r1 = lib.android.paypal.com.magnessdk.MagnesSource.PAYPAL     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            int r1 = r1.getVersion()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != r1) goto L_0x0407
            r7.e(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r7.d(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bJ = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x00a0:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r8.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r9 = r7.cb     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r8.append(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            long r1 = r7.cf     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r8.append(r1)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.toString()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r7.a((java.lang.String) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bK = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x00bb:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.TimeZone r8 = java.util.TimeZone.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.Date r9 = new java.util.Date     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r9.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            long r1 = r9.getTime()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            int r8 = r8.getOffset(r1)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bz = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x00d8:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.TimeZone r8 = java.util.TimeZone.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.Date r9 = new java.util.Date     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r9.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r8.inDaylightTime(r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.ci = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x00f1:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.cdma.CdmaCellLocation r8 = r7.cu     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x00fe
            goto L_0x0102
        L_0x00fe:
            int r2 = r8.getSystemId()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0102:
            r7.bA = r2     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0106:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.cdma.CdmaCellLocation r8 = r7.cu     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x0113
            goto L_0x0117
        L_0x0113:
            int r2 = r8.getNetworkId()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0117:
            r7.bB = r2     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x011b:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x0128
            goto L_0x012c
        L_0x0128:
            java.lang.String r3 = r8.getNetworkOperator()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x012c:
            r7.bE = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0130:
            lib.android.paypal.com.magnessdk.network.m r1 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r1.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            org.json.JSONObject r8 = r7.b((android.content.Context) r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cH = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0140:
            lib.android.paypal.com.magnessdk.network.m r1 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r1.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.os.BatteryManager r8 = r7.cy     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            android.os.PowerManager r1 = r7.cA     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            org.json.JSONObject r8 = r7.a((android.content.Context) r9, (android.os.BatteryManager) r8, (android.os.PowerManager) r1)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cD = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0154:
            java.lang.String r8 = r7.f()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.ca = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x015c:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.List r8 = r7.a((boolean) r4)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cc = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x016c:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.f7cl     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r1) goto L_0x0190
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r5) goto L_0x0188
            boolean r8 = r7.cj     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
        L_0x0188:
            java.lang.String r8 = android.os.Build.getSerial()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x018c:
            r7.bG = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0190:
            java.lang.String r8 = android.os.Build.SERIAL     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x018c
        L_0x0193:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.TimeZone r8 = java.util.TimeZone.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.TimeZone r9 = java.util.TimeZone.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.Date r1 = new java.util.Date     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r1.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r9 = r9.inDaylightTime(r1)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.Locale r1 = java.util.Locale.ENGLISH     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getDisplayName(r9, r4, r1)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bO = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x01b6:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.gsm.GsmCellLocation r8 = r7.ct     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x01c3
            goto L_0x01c7
        L_0x01c3:
            int r2 = r8.getLac()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x01c7:
            r7.bC = r2     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x01cb:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.ck     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x01dd
            android.location.LocationManager r8 = r7.cz     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            android.location.Location r3 = r7.a((android.location.LocationManager) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x01dd:
            r7.cC = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x01e1:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.Locale r8 = java.util.Locale.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getLanguage()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bQ = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x01f5:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.Locale r8 = java.util.Locale.getDefault()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getCountry()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bP = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0209:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.lang.String r8 = r7.e()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bY = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0219:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r8.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r9 == 0) goto L_0x025f
            java.util.List r9 = r9.c()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
        L_0x0232:
            boolean r1 = r9.hasNext()     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            if (r1 == 0) goto L_0x025f
            java.lang.Object r1 = r9.next()     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            android.content.pm.PackageManager r2 = r7.cB     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            android.content.Intent r4 = new android.content.Intent     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            r4.<init>()     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            android.content.ComponentName r5 = android.content.ComponentName.unflattenFromString(r1)     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            android.content.Intent r4 = r4.setComponent(r5)     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            boolean r2 = lib.android.paypal.com.magnessdk.b.a((android.content.pm.PackageManager) r2, (android.content.Intent) r4)     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            if (r2 == 0) goto L_0x0232
            r8.add(r1)     // Catch:{ Exception -> 0x0257, AssertionError -> 0x03ff }
            goto L_0x0232
        L_0x0257:
            r9 = move-exception
            java.lang.Class r1 = r7.getClass()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r1, (int) r0, (java.lang.Throwable) r9)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x025f:
            int r9 = r8.size()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r9 != 0) goto L_0x0266
            goto L_0x0267
        L_0x0266:
            r3 = r8
        L_0x0267:
            r7.cd = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x026b:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.lang.String r8 = r7.b()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bN = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x027b:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            long r8 = android.os.SystemClock.uptimeMillis()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.cg = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x028b:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.f7cl     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r1) goto L_0x02c9
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r5) goto L_0x02a7
            boolean r8 = r7.cj     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
        L_0x02a7:
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            int r8 = r8.getPhoneType()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != r4) goto L_0x02b9
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getImei()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x02b5:
            r7.bM = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x02b9:
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            int r8 = r8.getPhoneType()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r9 = 2
            if (r8 != r9) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getMeid()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x02b5
        L_0x02c9:
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getDeviceId()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x02b5
        L_0x02d0:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.net.NetworkInfo r8 = r7.cr     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x02dd
            goto L_0x02e1
        L_0x02dd:
            java.lang.String r3 = r8.getTypeName()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x02e1:
            r7.bL = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x02e5:
            java.lang.String r2 = r7.cb     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r3 = r7.bF     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            long r4 = r7.cf     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            lib.android.paypal.com.magnessdk.network.m r8 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r6 = r8.d()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r1 = r7
            java.lang.String r8 = r1.a(r2, r3, r4, r6)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bX = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x02fa:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.ck     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x030c
            android.net.wifi.WifiManager r8 = r7.cw     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.util.ArrayList r3 = r7.a((android.net.wifi.WifiManager) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x030c:
            r7.ce = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0310:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.lang.String r8 = r7.c()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bH = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0320:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.lang.String r8 = r7.d()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bI = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0330:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.f7cl     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r5) goto L_0x0348
            boolean r8 = r7.cj     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
        L_0x0348:
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getSubscriberId()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bV = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0352:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.net.wifi.WifiInfo r8 = r7.cs     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x035f
            goto L_0x0363
        L_0x035f:
            java.lang.String r3 = r8.getSSID()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x0363:
            r7.bU = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0367:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            boolean r8 = r7.f7cl     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 < r5) goto L_0x037f
            boolean r8 = r7.cj     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
        L_0x037f:
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            java.lang.String r8 = r8.getSimSerialNumber()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bT = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x0389:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.TelephonyManager r8 = r7.cv     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x0396
            goto L_0x039a
        L_0x0396:
            java.lang.String r3 = r7.b((android.telephony.TelephonyManager) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x039a:
            r7.bZ = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x039e:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.ServiceState r8 = new android.telephony.ServiceState     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r8.<init>()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r8.getRoaming()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.ch = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x03b2:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            java.lang.String r8 = lib.android.paypal.com.magnessdk.b.a((boolean) r4)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            r7.bS = r8     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x03c1:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.gsm.GsmCellLocation r8 = r7.ct     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x03ce
            goto L_0x03d2
        L_0x03ce:
            int r2 = r8.getCid()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x03d2:
            r7.by = r2     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x03d5:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.net.wifi.WifiInfo r8 = r7.cs     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x03e2
            goto L_0x03e6
        L_0x03e2:
            java.lang.String r3 = r8.getBSSID()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x03e6:
            r7.bD = r3     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x03e9:
            lib.android.paypal.com.magnessdk.network.m r9 = r7.cJ     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            boolean r8 = r9.a((int) r8)     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 == 0) goto L_0x0407
            android.telephony.cdma.CdmaCellLocation r8 = r7.cu     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            if (r8 != 0) goto L_0x03f6
            goto L_0x03fa
        L_0x03f6:
            int r2 = r8.getBaseStationId()     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
        L_0x03fa:
            r7.bx = r2     // Catch:{ AssertionError -> 0x03ff, Exception -> 0x03fd }
            goto L_0x0407
        L_0x03fd:
            r8 = move-exception
            goto L_0x0400
        L_0x03ff:
            r8 = move-exception
        L_0x0400:
            java.lang.Class r9 = r7.getClass()
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r9, (int) r0, (java.lang.Throwable) r8)
        L_0x0407:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.e.a(int, lib.android.paypal.com.magnessdk.MagnesSettings):void");
    }
}
