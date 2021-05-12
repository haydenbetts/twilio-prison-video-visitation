package lib.android.paypal.com.magnessdk.network;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lib.android.paypal.com.magnessdk.Environment;
import lib.android.paypal.com.magnessdk.MagnesSDK;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.b.a;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;
import org.json.JSONObject;

public final class b extends h {
    private static final String b = "app_id";
    private static final String c = "app_version";
    private static final String d = "os_type";
    private static final String e = "os_version";
    private static final String f = "device_model";
    private static final String g = "comp_version";
    private static final String h = "Dyson/%S (%S %S)";
    private HashMap<String, String> i;
    private Map<String, String> j;
    private Handler k;
    private JSONObject l;
    private MagnesNetworkingFactoryImpl m;
    private MagnesSettings n;
    private String o = "****MAGNES DEBUGGING MESSAGE****";

    public b(JSONObject jSONObject, MagnesSettings magnesSettings, Handler handler) {
        this.m = magnesSettings.getMagnesNetworkingFactoryImpl() == null ? new MagnesNetworkingFactoryImpl() : magnesSettings.getMagnesNetworkingFactoryImpl();
        this.i = new HashMap<>();
        this.j = new HashMap();
        this.k = handler;
        this.n = magnesSettings;
        this.l = jSONObject;
        this.i.put("appGuid", jSONObject.optString("app_guid"));
        this.i.put("libraryVersion", a(jSONObject));
        this.i.put("additionalData", jSONObject.toString());
    }

    private String a(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry next : hashMap.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode((String) next.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode((String) next.getValue(), "UTF-8"));
        }
        Class<?> cls = getClass();
        a.a(cls, 0, "encoded device info payload : " + sb.toString());
        return sb.toString();
    }

    private String a(JSONObject jSONObject) {
        return String.format(Locale.US, h, new Object[]{jSONObject.optString(g), jSONObject.optString(d), Build.VERSION.RELEASE});
    }

    public void a() {
        this.j.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV");
        this.j.put("X-PAYPAL-REQUEST-DATA-FORMAT", "NV");
        this.j.put("X-PAYPAL-SERVICE-VERSION", "1.0.0");
        this.j.put(DefaultHttpClient.CONTENT_TYPE_KEY, "application/x-www-form-urlencoded");
        JSONObject jSONObject = this.l;
        if (jSONObject != null) {
            this.j.put("os-type", jSONObject.optString(d, "Android"));
            this.j.put("os-version", this.l.optString(e, Build.VERSION.RELEASE));
            this.j.put("device-model", this.l.optString(f, Build.MODEL));
            this.j.put("app-id", this.l.optString(b, "Unknown"));
            this.j.put("app-version", this.l.optString(c, "Unknown"));
            this.j.put("comp-version", this.l.optString(g, lib.android.paypal.com.magnessdk.a.f));
        }
    }

    public void b() {
        if (this.n.isEnableNetworkOnCallerThread()) {
            c();
        } else {
            d();
        }
    }

    public void c() {
        Handler handler;
        Message obtain;
        a();
        try {
            MagnesNetworking createHttpClient = this.m.createHttpClient(DefaultHttpClient.METHOD_POST);
            Handler handler2 = this.k;
            String str = lib.android.paypal.com.magnessdk.a.l;
            if (handler2 != null) {
                if (this.n.getEnvironment() == Environment.LIVE) {
                    str = MagnesSDK.getInstance().a.f();
                    handler = this.k;
                    obtain = Message.obtain(handler, 0, str);
                } else {
                    handler = this.k;
                    obtain = Message.obtain(handler, 0, str);
                }
                handler.sendMessage(obtain);
            } else {
                str = lib.android.paypal.com.magnessdk.a.h;
            }
            createHttpClient.setUri(Uri.parse(str));
            createHttpClient.setHeader(this.j);
            int execute = createHttpClient.execute(a(this.i).getBytes("UTF-8"));
            String str2 = this.o;
            Log.d(str2, "DeviceInfoRequest returned PayPal-Debug-Id: " + createHttpClient.getPayPalDebugId());
            if (execute == 200) {
                String str3 = new String(createHttpClient.getResponseContent(), "UTF-8");
                Handler handler3 = this.k;
                if (handler3 != null) {
                    handler3.sendMessage(Message.obtain(handler3, 2, str3));
                }
                Class<?> cls = getClass();
                a.a(cls, 0, "DeviceInfoRequest returned HTTP" + execute + " ,responseString: " + str3);
                return;
            }
            Handler handler4 = this.k;
            if (handler4 != null) {
                handler4.sendMessage(Message.obtain(handler4, 1, Integer.valueOf(execute)));
            }
            Class<?> cls2 = getClass();
            a.a(cls2, 3, "DeviceInfoRequest returned HTTP" + execute);
        } catch (Exception e2) {
            a.a(getClass(), 3, (Throwable) e2);
            Handler handler5 = this.k;
            if (handler5 != null) {
                handler5.sendMessage(Message.obtain(handler5, 1, e2));
            }
        }
    }

    public void run() {
        if (this.k != null) {
            c();
        }
    }
}
