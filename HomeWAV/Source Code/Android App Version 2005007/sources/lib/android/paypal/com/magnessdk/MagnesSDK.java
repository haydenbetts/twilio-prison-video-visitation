package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.network.a;
import lib.android.paypal.com.magnessdk.network.b;
import lib.android.paypal.com.magnessdk.network.d;
import lib.android.paypal.com.magnessdk.network.j;
import lib.android.paypal.com.magnessdk.network.k;
import lib.android.paypal.com.magnessdk.network.m;
import org.json.JSONException;
import org.json.JSONObject;

public final class MagnesSDK {
    private static final int c = 32;
    private static MagnesSDK d;
    public m a;
    MagnesSettings b;
    private JSONObject e;
    private Handler f;
    private HandlerThread g;
    private d h;
    private k i;

    private MagnesSDK() {
    }

    private void a() {
        if (this.g == null) {
            HandlerThread handlerThread = new HandlerThread("MagnesHandlerThread");
            this.g = handlerThread;
            handlerThread.start();
            this.f = j.a(this.g.getLooper(), this);
        }
    }

    private void a(Context context, JSONObject jSONObject) {
        new b(jSONObject, this.b, this.f).b();
        if (b()) {
            new a(jSONObject, this.b, this.f).b();
        }
    }

    private boolean b() {
        return !this.b.isDisableBeacon() && this.b.getEnvironment() == Environment.LIVE;
    }

    public static synchronized MagnesSDK getInstance() {
        MagnesSDK magnesSDK;
        synchronized (MagnesSDK.class) {
            if (d == null) {
                d = new MagnesSDK();
            }
            magnesSDK = d;
        }
        return magnesSDK;
    }

    public MagnesResult collect(Context context) {
        try {
            return collect(context, (String) null, (HashMap<String, String>) null);
        } catch (InvalidInputException unused) {
            return null;
        }
    }

    public MagnesResult collect(Context context, String str, HashMap<String, String> hashMap) throws InvalidInputException {
        Class<?> cls = getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("COLLECT method called with paypalClientMetaDataId : ");
        sb.append(str);
        sb.append(" , Is pass in additionalData null? : ");
        sb.append(Boolean.toString(hashMap == null));
        lib.android.paypal.com.magnessdk.b.a.a(cls, 0, sb.toString());
        if (str == null || str.length() <= 32) {
            if (this.b == null) {
                lib.android.paypal.com.magnessdk.b.a.a(getClass(), 2, "No MagnesSettings specified, using platform default.");
                MagnesSettings build = new MagnesSettings.Builder(context).build();
                this.b = build;
                setUp(build);
            }
            if (this.a.g()) {
                lib.android.paypal.com.magnessdk.b.a.a(getClass(), 0, "nc presents, collecting coreData.");
                d dVar = new d();
                this.h = dVar;
                this.e = dVar.a(this.b, this.i, this.a);
                this.a.a(false);
            }
            JSONObject a2 = this.h.a(new e().a(this.b, this.i, this.a, this.h.b(), str, hashMap, this.f));
            String str2 = null;
            try {
                Class<?> cls2 = getClass();
                lib.android.paypal.com.magnessdk.b.a.a(cls2, 0, "Device Info JSONObject : " + a2.toString(2));
                str2 = a2.getString("pairing_id");
            } catch (JSONException e2) {
                lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e2);
            }
            return new MagnesResult().setDeviceInfo(a2).setPaypalClientMetaDataId(str2);
        }
        throw new InvalidInputException(d.M);
    }

    public MagnesResult collectAndSubmit(Context context) {
        try {
            return collectAndSubmit(context, (String) null, (HashMap<String, String>) null);
        } catch (InvalidInputException unused) {
            return null;
        }
    }

    public MagnesResult collectAndSubmit(Context context, String str, HashMap<String, String> hashMap) throws InvalidInputException {
        Class<?> cls = getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("SUBMIT method called with paypalClientMetaDataId : ");
        sb.append(str);
        sb.append(" , Is pass in additionalData null? : ");
        sb.append(Boolean.toString(hashMap == null));
        lib.android.paypal.com.magnessdk.b.a.a(cls, 0, sb.toString());
        if (str == null || str.length() <= 32) {
            MagnesResult collect = collect(context, str, hashMap);
            a(context, collect.getDeviceInfo());
            return collect;
        }
        throw new InvalidInputException(d.M);
    }

    public MagnesSettings setUp(MagnesSettings magnesSettings) {
        this.b = magnesSettings;
        a();
        this.a = new m(magnesSettings, this.f);
        this.i = new k(magnesSettings, this.f);
        if (this.h == null) {
            d dVar = new d();
            this.h = dVar;
            this.e = dVar.a(magnesSettings, this.i, this.a);
        }
        return magnesSettings;
    }
}
