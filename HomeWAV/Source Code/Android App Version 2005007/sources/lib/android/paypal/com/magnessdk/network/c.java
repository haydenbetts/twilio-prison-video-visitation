package lib.android.paypal.com.magnessdk.network;

import android.content.Context;
import java.io.File;
import lib.android.paypal.com.magnessdk.b.a;
import org.json.JSONObject;

public abstract class c {
    static final int a = 1000;
    static final String b = "conf_refresh_time_interval";
    private static final String c = "_TIME";
    private static final String d = "_DATA";

    /* access modifiers changed from: protected */
    public abstract JSONObject a();

    /* access modifiers changed from: protected */
    public abstract JSONObject a(String str);

    /* access modifiers changed from: protected */
    public void a(Context context, String str, String str2) {
        a.a(getClass(), 0, "entering saveConfigData");
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, str2 + d);
        File filesDir2 = context.getFilesDir();
        File file2 = new File(filesDir2, str2 + c);
        lib.android.paypal.com.magnessdk.a.c.a(file, str);
        lib.android.paypal.com.magnessdk.a.c.a(file2, String.valueOf(System.currentTimeMillis()));
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, String str) {
        a.a(getClass(), 0, "entering deleteCachedConfigDataFromDisk");
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, str + d);
        File filesDir2 = context.getFilesDir();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(c);
        return lib.android.paypal.com.magnessdk.a.c.c(file) && lib.android.paypal.com.magnessdk.a.c.c(new File(filesDir2, sb.toString()));
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public String b(Context context, String str) {
        a.a(getClass(), 0, "Loading loadCachedConfigData");
        File filesDir = context.getFilesDir();
        return lib.android.paypal.com.magnessdk.a.c.a(new File(filesDir, str + d));
    }

    /* access modifiers changed from: protected */
    public abstract JSONObject b();

    /* access modifiers changed from: protected */
    public abstract void b(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public abstract String c();

    /* access modifiers changed from: protected */
    public String c(Context context, String str) {
        a.a(getClass(), 0, "Loading loadCachedConfigTime");
        File filesDir = context.getFilesDir();
        return lib.android.paypal.com.magnessdk.a.c.a(new File(filesDir, str + c));
    }

    /* access modifiers changed from: package-private */
    public abstract JSONObject d();
}
