package lib.android.paypal.com.magnessdk.network;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.util.HashMap;
import java.util.Map;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;
import org.json.JSONObject;

public final class a extends h {
    private static final String b = "pairing_id";
    private static final String c = "ip_addrs";
    private static final int d = -1;
    private static final long e = 1000;
    private JSONObject f;
    private int g;
    private Map<String, String> h = new HashMap();
    private Handler i;
    private MagnesNetworkingFactoryImpl j;
    private MagnesSettings k;

    public a(JSONObject jSONObject, MagnesSettings magnesSettings, Handler handler) {
        this.g = magnesSettings.getMagnesSource();
        this.f = jSONObject;
        this.i = handler;
        this.k = magnesSettings;
        this.j = magnesSettings.getMagnesNetworkingFactoryImpl() == null ? new MagnesNetworkingFactoryImpl() : magnesSettings.getMagnesNetworkingFactoryImpl();
    }

    public void a() {
        this.h.put("User-Agent", String.format("%s/%s/%s/%s/Android", new Object[]{this.f.optString("app_id"), this.f.optString("app_version"), this.f.optString("app_version"), this.f.optString("app_guid")}));
        this.h.put("Accept-Language", "en-us");
    }

    public void b() {
        if (this.k.isEnableNetworkOnCallerThread()) {
            c();
        } else {
            d();
        }
    }

    public void c() {
        try {
            a();
            StringBuilder sb = new StringBuilder(lib.android.paypal.com.magnessdk.a.i);
            sb.append("?p=");
            sb.append(this.f.optString(b));
            sb.append("&i=");
            sb.append(this.f.optString(c));
            sb.append("&t=");
            sb.append(String.valueOf(System.currentTimeMillis() / 1000));
            if (this.g == -1) {
                sb.append("&s=");
                sb.append(this.f.optString("app_id"));
            } else {
                sb.append("&a=");
                sb.append(this.g);
            }
            Handler handler = this.i;
            if (handler != null) {
                handler.sendMessage(Message.obtain(handler, 20, sb));
            }
            MagnesNetworking createHttpClient = this.j.createHttpClient(DefaultHttpClient.METHOD_GET);
            createHttpClient.setHeader(this.h);
            createHttpClient.setUri(Uri.parse(sb.toString()));
            Class<?> cls = getClass();
            lib.android.paypal.com.magnessdk.b.a.a(cls, 0, "Sending BeaconRequest : " + sb.toString());
            int execute = createHttpClient.execute((byte[]) null);
            if (execute == 200) {
                String str = new String(createHttpClient.getResponseContent(), "UTF-8");
                Class<?> cls2 = getClass();
                lib.android.paypal.com.magnessdk.b.a.a(cls2, 0, "BeaconRequest returned HTTP" + execute + " ,responseString: " + str);
                Handler handler2 = this.i;
                if (handler2 != null) {
                    handler2.sendMessage(Message.obtain(handler2, 22, str));
                    return;
                }
                return;
            }
            Handler handler3 = this.i;
            if (handler3 != null) {
                handler3.sendMessage(Message.obtain(handler3, 21, "Beacon return non-200 status code : " + execute));
            }
            Class<?> cls3 = getClass();
            lib.android.paypal.com.magnessdk.b.a.a(cls3, 3, "BeaconRequest returned HTTP" + execute);
        } catch (Exception e2) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e2);
            Handler handler4 = this.i;
            if (handler4 != null) {
                handler4.sendMessage(Message.obtain(handler4, 21, "Beacon return non-200 status code : " + e2));
            }
        }
    }

    public void run() {
        if (this.i != null) {
            c();
        }
    }
}
