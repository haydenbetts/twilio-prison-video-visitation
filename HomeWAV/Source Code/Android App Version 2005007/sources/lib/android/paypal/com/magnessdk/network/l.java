package lib.android.paypal.com.magnessdk.network;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.microsoft.appcenter.http.DefaultHttpClient;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;

public final class l extends h {
    private Handler b;
    private MagnesNetworkingFactoryImpl c;
    private MagnesSettings d;
    private k e;

    public l(k kVar, MagnesSettings magnesSettings, Handler handler) {
        this.b = handler;
        this.e = kVar;
        this.d = magnesSettings;
        this.c = magnesSettings.getMagnesNetworkingFactoryImpl() == null ? new MagnesNetworkingFactoryImpl() : magnesSettings.getMagnesNetworkingFactoryImpl();
    }

    public void a() {
    }

    public void b() {
        if (this.d.isEnableNetworkOnCallerThread()) {
            c();
        } else {
            d();
        }
    }

    public void c() {
        Handler handler;
        Message obtain;
        try {
            MagnesNetworking createHttpClient = this.c.createHttpClient(DefaultHttpClient.METHOD_GET);
            createHttpClient.setUri(Uri.parse(this.e.c()));
            Handler handler2 = this.b;
            if (handler2 != null) {
                handler2.sendMessage(Message.obtain(handler2, 50, this.e));
            }
            int execute = createHttpClient.execute((byte[]) null);
            String str = new String(createHttpClient.getResponseContent(), "UTF-8");
            if (execute == 200) {
                this.e.a(this.d.getContext(), str, "RAMP_CONFIG");
                handler = this.b;
                if (handler != null) {
                    obtain = Message.obtain(handler, 52, str);
                } else {
                    return;
                }
            } else {
                handler = this.b;
                if (handler != null) {
                    obtain = Message.obtain(handler, 51, execute + " : " + str);
                } else {
                    return;
                }
            }
            handler.sendMessage(obtain);
        } catch (Exception e2) {
            Handler handler3 = this.b;
            if (handler3 != null) {
                handler3.sendMessage(Message.obtain(handler3, 51, e2));
            }
        }
    }

    public void run() {
        if (this.b != null) {
            c();
        }
    }
}
