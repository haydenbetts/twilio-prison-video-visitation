package lib.android.paypal.com.magnessdk.network;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import lib.android.paypal.com.magnessdk.MagnesSDK;

public final class j extends Handler {
    private static j a;
    private WeakReference<MagnesSDK> b;

    private j(Looper looper, MagnesSDK magnesSDK) {
        super(looper);
        this.b = new WeakReference<>(magnesSDK);
    }

    public static synchronized j a(Looper looper, MagnesSDK magnesSDK) {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j(looper, magnesSDK);
            }
            jVar = a;
        }
        return jVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x009d, code lost:
        r3.append(" started.");
        r6 = r3.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r6) {
        /*
            r5 = this;
            java.lang.ref.WeakReference<lib.android.paypal.com.magnessdk.MagnesSDK> r0 = r5.b
            java.lang.Object r0 = r0.get()
            lib.android.paypal.com.magnessdk.MagnesSDK r0 = (lib.android.paypal.com.magnessdk.MagnesSDK) r0
            if (r0 == 0) goto L_0x0155
            int r0 = r6.what
            r1 = 0
            if (r0 == 0) goto L_0x013b
            r2 = 1
            r3 = 3
            if (r0 == r2) goto L_0x0120
            r2 = 2
            if (r0 == r2) goto L_0x0114
            switch(r0) {
                case 10: goto L_0x0108;
                case 11: goto L_0x00fc;
                case 12: goto L_0x00f0;
                default: goto L_0x0019;
            }
        L_0x0019:
            switch(r0) {
                case 20: goto L_0x00e4;
                case 21: goto L_0x00d8;
                case 22: goto L_0x00cc;
                default: goto L_0x001c;
            }
        L_0x001c:
            switch(r0) {
                case 40: goto L_0x00bf;
                case 41: goto L_0x00b3;
                case 42: goto L_0x00a6;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.String r2 = " started."
            java.lang.String r4 = "POST request to "
            switch(r0) {
                case 50: goto L_0x0084;
                case 51: goto L_0x0077;
                case 52: goto L_0x006a;
                case 53: goto L_0x0058;
                case 54: goto L_0x0040;
                case 55: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x0155
        L_0x0028:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.Object r6 = r6.obj
            r2.append(r6)
            java.lang.String r6 = " successfully."
            r2.append(r6)
            goto L_0x014e
        L_0x0040:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.Object r6 = r6.obj
            r1.append(r6)
            java.lang.String r6 = "error."
            r1.append(r6)
            goto L_0x0133
        L_0x0058:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            java.lang.Object r6 = r6.obj
            r3.append(r6)
            goto L_0x009d
        L_0x006a:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "GET request succeeded with: "
            goto L_0x0146
        L_0x0077:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "GET request error with HTTP code: "
            goto L_0x012b
        L_0x0084:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "GET request to "
            r3.append(r4)
            java.lang.Object r6 = r6.obj
            lib.android.paypal.com.magnessdk.network.k r6 = (lib.android.paypal.com.magnessdk.network.k) r6
            java.lang.String r6 = r6.c()
            r3.append(r6)
        L_0x009d:
            r3.append(r2)
            java.lang.String r6 = r3.toString()
            goto L_0x0152
        L_0x00a6:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "s request successed. "
            goto L_0x0146
        L_0x00b3:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "s request error. "
            goto L_0x012b
        L_0x00bf:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "s request started. "
            goto L_0x0146
        L_0x00cc:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "beacon successed. "
            goto L_0x0146
        L_0x00d8:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "beacon error. "
            goto L_0x012b
        L_0x00e4:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "beacon started. "
            goto L_0x0146
        L_0x00f0:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "remote config fetched successfully. "
            goto L_0x0146
        L_0x00fc:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "remote config error. "
            goto L_0x012b
        L_0x0108:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "remote config started. fetching "
            goto L_0x0146
        L_0x0114:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "device info payload successed. "
            goto L_0x0146
        L_0x0120:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "device info payload error. "
        L_0x012b:
            r1.append(r2)
            java.lang.Object r6 = r6.obj
            r1.append(r6)
        L_0x0133:
            java.lang.String r6 = r1.toString()
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r0, (int) r3, (java.lang.String) r6)
            goto L_0x0155
        L_0x013b:
            java.lang.Class r0 = r5.getClass()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "device info payload started. "
        L_0x0146:
            r2.append(r3)
            java.lang.Object r6 = r6.obj
            r2.append(r6)
        L_0x014e:
            java.lang.String r6 = r2.toString()
        L_0x0152:
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r0, (int) r1, (java.lang.String) r6)
        L_0x0155:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.network.j.handleMessage(android.os.Message):void");
    }
}
