package lib.android.paypal.com.magnessdk.network;

import android.content.Context;
import android.os.Handler;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.b.a;
import lib.android.paypal.com.magnessdk.c;
import org.json.JSONException;
import org.json.JSONObject;

public class k extends c {
    static final String c = "RAMP_CONFIG";
    static final int d = 7200;
    private Context e;
    private Handler f;
    private MagnesSettings g;
    private JSONObject h;

    public k(MagnesSettings magnesSettings, Handler handler) {
        this.e = magnesSettings.getContext();
        this.g = magnesSettings;
        this.f = handler;
        b(a());
        try {
            a.a(getClass(), 0, d().toString(2));
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject a() {
        try {
            JSONObject a = a(c);
            if (a == null) {
                new l(this, this.g, this.f).d();
                return b();
            }
            if (a(a)) {
                a.a(getClass(), 0, "Cached config used while fetching.");
                new l(this, this.g, this.f).d();
            }
            return a;
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
            return b();
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject a(String str) {
        a.a(getClass(), 0, "entering getCachedConfig");
        try {
            String b = b(this.e, str);
            if (!b.isEmpty()) {
                a.a(getClass(), 0, "leaving getCachedConfig,cached config loadsuccessfully");
                return new JSONObject(b);
            }
            a.a(getClass(), 0, "leaving getCachedConfig,cached config loaded empty");
            return null;
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(JSONObject jSONObject) {
        return System.currentTimeMillis() > Long.parseLong(c(this.e, c)) + (jSONObject.optLong("conf_refresh_time_interval", 0) * 1000);
    }

    /* access modifiers changed from: protected */
    public JSONObject b() {
        a.a(getClass(), 0, "entering getDefaultConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(c.bl, 0);
            jSONObject.put("conf_refresh_time_interval", d);
        } catch (JSONException e2) {
            a.a(getClass(), 3, (Throwable) e2);
        }
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    /* access modifiers changed from: protected */
    public String c() {
        return "https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_android_rc_v1.json";
    }

    public JSONObject d() {
        return this.h;
    }
}
