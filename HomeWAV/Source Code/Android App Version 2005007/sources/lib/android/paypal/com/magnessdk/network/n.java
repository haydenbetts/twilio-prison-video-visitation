package lib.android.paypal.com.magnessdk.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import lib.android.paypal.com.magnessdk.b.a;
import org.json.JSONObject;

public final class n extends h {
    private Context b;
    private Handler c;
    private m d;

    public n(Context context, Handler handler, m mVar) {
        this.b = context;
        this.c = handler;
        this.d = mVar;
    }

    public void run() {
        a.a(getClass(), 0, "entering LoadRemoteConfigRequest.");
        Handler handler = this.c;
        if (handler != null) {
            try {
                handler.sendMessage(Message.obtain(handler, 10, "https://www.paypalobjects.com/digitalassets/c/rda-magnes/magnes_config_android_v4.json"));
                String a = this.d.a();
                if (!a.isEmpty()) {
                    JSONObject jSONObject = new JSONObject(a);
                    this.d.a(a);
                    this.d.a(jSONObject);
                    if (jSONObject.optJSONArray("nc") != null) {
                        this.d.a(true);
                    }
                    Handler handler2 = this.c;
                    handler2.sendMessage(Message.obtain(handler2, 12, a));
                    a.a(getClass(), 0, "leaving LoadRemoteConfigRequest.");
                    return;
                }
                throw new IOException("no valid remote config found!");
            } catch (Exception e) {
                a.a(getClass(), 3, (Throwable) e);
                Handler handler3 = this.c;
                handler3.sendMessage(Message.obtain(handler3, 11, e));
            }
        }
    }
}
