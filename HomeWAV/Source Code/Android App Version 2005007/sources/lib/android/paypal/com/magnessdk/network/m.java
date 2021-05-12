package lib.android.paypal.com.magnessdk.network;

import android.content.Context;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.a.c;
import lib.android.paypal.com.magnessdk.b;
import lib.android.paypal.com.magnessdk.b.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    private static final String a = "CONFIG_TIME";
    private static final String b = "4.0";
    private static final int c = 1000;
    private static final String d = "CONFIG_DATA";
    private static final String e = "QW5kcm9pZE1hZ25lcw==";
    private static final int f = 5;
    private static final int g = 86400;
    private static final int h = 100;
    private BitSet i;
    private Context j;
    private JSONObject k;
    private Handler l;
    private boolean m = false;
    private boolean n = false;
    private JSONArray o;

    public m(MagnesSettings magnesSettings, Handler handler) {
        this.j = magnesSettings.getContext();
        this.l = handler;
        this.m = magnesSettings.isDisableRemoteConfig();
        this.k = i();
        o();
        try {
            a.a(getClass(), 0, this.k.toString(2));
        } catch (JSONException unused) {
        }
    }

    private boolean a(String str, String str2) {
        a.a(getClass(), 0, "entering shouldUseCachedConfiguration");
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        Class<?> cls = getClass();
        a.a(cls, 0, "Comparing Cached version is " + str + " default version is " + str2);
        int i2 = 0;
        while (i2 < split.length && i2 < split2.length && split[i2].equals(split2[i2])) {
            i2++;
        }
        return Integer.valueOf(Integer.signum((i2 >= split.length || i2 >= split2.length) ? split.length - split2.length : Integer.valueOf(split[i2]).compareTo(Integer.valueOf(split2[i2])))).intValue() >= 0;
    }

    private boolean b(JSONObject jSONObject) {
        return System.currentTimeMillis() > Long.parseLong(n()) + (jSONObject.optLong("conf_refresh_time_interval", 0) * 1000);
    }

    private JSONObject i() {
        try {
            JSONObject j2 = j();
            if (j2 == null) {
                a.a(getClass(), 0, "submit async remoteConfig request. no cached configuration found.");
                new n(this.j, this.l, this).d();
            } else if (a(j2.optString("conf_version", ""), b)) {
                boolean b2 = b(j2);
                if (!this.m && b2) {
                    Class<?> cls = getClass();
                    StringBuilder sb = new StringBuilder();
                    sb.append("submit async remoteConfig request: !isRemoteConfigDisabled: ");
                    sb.append(!this.m);
                    sb.append(" isConfigExpired: ");
                    sb.append(b2);
                    a.a(cls, 0, sb.toString());
                    new n(this.j, this.l, this).d();
                }
                Class<?> cls2 = getClass();
                a.a(cls2, 0, "Using cached config due to isRemoteConfigDisabled : " + this.m + " or isConfigExpired : " + b2);
                return j2;
            } else {
                m();
            }
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
        return k();
    }

    private JSONObject j() {
        a.a(getClass(), 0, "entering getCachedConfiguration");
        try {
            String l2 = l();
            if (!l2.isEmpty()) {
                a.a(getClass(), 0, "leaving getCachedConfiguration,cached config load successfully");
                return new JSONObject(l2);
            }
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
        a.a(getClass(), 0, "leaving getCachedConfiguration,cached config load failed");
        return null;
    }

    private JSONObject k() {
        a.a(getClass(), 0, "entering getDefaultRemoteConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("conf_version", b);
            jSONObject.put("conf_refresh_time_interval", 86400);
            jSONObject.put("endpoint_url", lib.android.paypal.com.magnessdk.a.h);
        } catch (JSONException e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
        return jSONObject;
    }

    private String l() {
        a.a(getClass(), 0, "Loading loadCachedConfigDataFromDisk");
        return c.a(new File(this.j.getFilesDir(), d));
    }

    private boolean m() {
        a.a(getClass(), 0, "entering deleteCachedConfigDataFromDisk");
        return c.c(new File(this.j.getFilesDir(), d)) && c.c(new File(this.j.getFilesDir(), a));
    }

    private String n() {
        return c.a(new File(this.j.getFilesDir(), a));
    }

    private void o() {
        JSONArray optJSONArray = this.k.optJSONArray("nc");
        if (optJSONArray != null) {
            this.o = optJSONArray;
        }
        BitSet bitSet = new BitSet(100);
        this.i = bitSet;
        bitSet.set(0, 100, true);
        int i2 = 0;
        while (optJSONArray != null && i2 < optJSONArray.length()) {
            try {
                this.i.set(optJSONArray.getInt(i2), false);
            } catch (JSONException unused) {
            }
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public String a() throws IOException {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            InputStream openStream = new URL("https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_config_android_v4.json").openStream();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(openStream, "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            b.a(getClass(), (Closeable) openStream);
                            b.a(getClass(), (Closeable) bufferedReader);
                            a.a(getClass(), 0, "leaving getRemoteConfig successfully");
                            return sb.toString();
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = openStream;
                        b.a(getClass(), (Closeable) inputStream);
                        b.a(getClass(), (Closeable) bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                inputStream = openStream;
                b.a(getClass(), (Closeable) inputStream);
                b.a(getClass(), (Closeable) bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            b.a(getClass(), (Closeable) inputStream);
            b.a(getClass(), (Closeable) bufferedReader);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        a.a(getClass(), 0, "entering saveConfigData");
        File file = new File(this.j.getFilesDir(), d);
        File file2 = new File(this.j.getFilesDir(), a);
        c.a(file, str);
        c.a(file2, String.valueOf(System.currentTimeMillis()));
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        this.k = jSONObject;
        o();
    }

    public void a(boolean z) {
        this.n = z;
    }

    public boolean a(int i2) {
        return this.i.get(i2);
    }

    public String b() {
        return this.k.optString("conf_version");
    }

    public List<String> c() throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = this.k.optJSONArray("android_apps_to_check");
        int i2 = 0;
        while (optJSONArray != null && i2 < optJSONArray.length()) {
            arrayList.add(optJSONArray.getString(i2));
            i2++;
        }
        return arrayList;
    }

    public String d() {
        return this.k.optString("m", e);
    }

    public int e() {
        return this.k.optInt("s", 5);
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return this.k.optString("endpoint_url", lib.android.paypal.com.magnessdk.a.h);
    }

    public boolean g() {
        return this.n;
    }

    public JSONArray h() {
        return this.o;
    }
}
