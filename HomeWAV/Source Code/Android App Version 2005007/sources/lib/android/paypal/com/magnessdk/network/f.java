package lib.android.paypal.com.magnessdk.network;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;

class f implements MagnesNetworking {
    private final o a = a();
    private byte[] b;
    private String c = null;
    private Uri d;
    private Map<String, String> e = new HashMap();

    private o a() throws SSLException {
        return o.a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int execute(byte[] r9) {
        /*
            r8 = this;
            r9 = -1
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            android.net.Uri r2 = r8.d     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ Exception -> 0x00ab, all -> 0x00a7 }
            r2 = 60000(0xea60, float:8.4078E-41)
            r1.setReadTimeout(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r1.setConnectTimeout(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            javax.net.ssl.HostnameVerifier r2 = lib.android.paypal.com.magnessdk.network.e.a()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r1.setHostnameVerifier(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.util.Map<java.lang.String, java.lang.String> r2 = r8.e     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.util.Set r2 = r2.entrySet()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
        L_0x0032:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            if (r3 == 0) goto L_0x0052
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.Object r4 = r3.getKey()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r1.setRequestProperty(r4, r3)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            goto L_0x0032
        L_0x0052:
            int r2 = r1.getResponseCode()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.lang.String r3 = "correlation-id"
            java.lang.String r3 = r1.getHeaderField(r3)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r8.c = r3     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 0
            if (r2 != r3) goto L_0x0088
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ Exception -> 0x0086 }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0086 }
            r6.<init>()     // Catch:{ Exception -> 0x0086 }
        L_0x0075:
            int r7 = r3.read(r5)     // Catch:{ Exception -> 0x0086 }
            if (r7 == r9) goto L_0x007f
            r6.write(r5, r4, r7)     // Catch:{ Exception -> 0x0086 }
            goto L_0x0075
        L_0x007f:
            byte[] r4 = r6.toByteArray()     // Catch:{ Exception -> 0x0086 }
            r8.b = r4     // Catch:{ Exception -> 0x0086 }
            goto L_0x008d
        L_0x0086:
            r2 = move-exception
            goto L_0x00ae
        L_0x0088:
            byte[] r3 = new byte[r4]     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r8.b = r3     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
            r3 = r0
        L_0x008d:
            java.lang.Class r9 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r9, (java.io.Closeable) r3)
            java.lang.Class r9 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r9, (java.io.Closeable) r0)
            if (r1 == 0) goto L_0x00a0
            r1.disconnect()
        L_0x00a0:
            return r2
        L_0x00a1:
            r9 = move-exception
            r3 = r0
            goto L_0x00cb
        L_0x00a4:
            r2 = move-exception
            r3 = r0
            goto L_0x00ae
        L_0x00a7:
            r9 = move-exception
            r1 = r0
            r3 = r1
            goto L_0x00cb
        L_0x00ab:
            r2 = move-exception
            r1 = r0
            r3 = r1
        L_0x00ae:
            java.lang.Class r4 = r8.getClass()     // Catch:{ all -> 0x00ca }
            r5 = 3
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r4, (int) r5, (java.lang.Throwable) r2)     // Catch:{ all -> 0x00ca }
            java.lang.Class r2 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r2, (java.io.Closeable) r3)
            java.lang.Class r2 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r2, (java.io.Closeable) r0)
            if (r1 == 0) goto L_0x00c9
            r1.disconnect()
        L_0x00c9:
            return r9
        L_0x00ca:
            r9 = move-exception
        L_0x00cb:
            java.lang.Class r2 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r2, (java.io.Closeable) r3)
            java.lang.Class r2 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r2, (java.io.Closeable) r0)
            if (r1 == 0) goto L_0x00de
            r1.disconnect()
        L_0x00de:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.network.f.execute(byte[]):int");
    }

    public String getPayPalDebugId() {
        return this.c;
    }

    public byte[] getResponseContent() {
        return this.b;
    }

    public void setHeader(Map<String, String> map) {
        this.e = map;
    }

    public void setUri(Uri uri) {
        this.d = uri;
    }
}
