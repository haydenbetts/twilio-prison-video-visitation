package lib.android.paypal.com.magnessdk.network;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import lib.android.paypal.com.magnessdk.network.httpclient.MagnesNetworking;

class g implements MagnesNetworking {
    private final o a = a();
    private byte[] b;
    private String c = null;
    private Uri d;
    private Map<String, String> e = new HashMap();

    private o a() throws SSLException {
        return o.a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int execute(byte[] r9) {
        /*
            r8 = this;
            r0 = -1
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            android.net.Uri r3 = r8.d     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Exception -> 0x00c8, all -> 0x00c4 }
            r3 = 60000(0xea60, float:8.4078E-41)
            r2.setReadTimeout(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r2.setConnectTimeout(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            javax.net.ssl.HostnameVerifier r3 = lib.android.paypal.com.magnessdk.network.e.a()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r2.setHostnameVerifier(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.String r3 = "POST"
            r2.setRequestMethod(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            lib.android.paypal.com.magnessdk.network.o r3 = r8.a     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r2.setSSLSocketFactory(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r3 = 1
            r2.setDoOutput(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            int r3 = r9.length     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r2.setFixedLengthStreamingMode(r3)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.util.Map<java.lang.String, java.lang.String> r3 = r8.e     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
        L_0x003f:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            if (r4 == 0) goto L_0x005f
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.Object r5 = r4.getKey()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.Object r4 = r4.getValue()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r2.setRequestProperty(r5, r4)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            goto L_0x003f
        L_0x005f:
            java.io.OutputStream r3 = r2.getOutputStream()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r3.write(r9)     // Catch:{ Exception -> 0x00bc }
            r3.flush()     // Catch:{ Exception -> 0x00bc }
            int r9 = r2.getResponseCode()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r4 = "correlation-id"
            java.lang.String r4 = r2.getHeaderField(r4)     // Catch:{ Exception -> 0x00bc }
            r8.c = r4     // Catch:{ Exception -> 0x00bc }
            r4 = 200(0xc8, float:2.8E-43)
            r5 = 0
            if (r9 != r4) goto L_0x00a4
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00bc }
            java.io.InputStream r6 = r2.getInputStream()     // Catch:{ Exception -> 0x00bc }
            r4.<init>(r6)     // Catch:{ Exception -> 0x00bc }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            r6.<init>()     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
        L_0x008c:
            int r7 = r4.read(r1)     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            if (r7 == r0) goto L_0x0096
            r6.write(r1, r5, r7)     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            goto L_0x008c
        L_0x0096:
            byte[] r1 = r6.toByteArray()     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            r8.b = r1     // Catch:{ Exception -> 0x00a1, all -> 0x009e }
            r1 = r4
            goto L_0x00a8
        L_0x009e:
            r9 = move-exception
            r1 = r4
            goto L_0x00e8
        L_0x00a1:
            r9 = move-exception
            r1 = r4
            goto L_0x00cb
        L_0x00a4:
            byte[] r4 = new byte[r5]     // Catch:{ Exception -> 0x00bc }
            r8.b = r4     // Catch:{ Exception -> 0x00bc }
        L_0x00a8:
            java.lang.Class r0 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r1)
            java.lang.Class r0 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r3)
            if (r2 == 0) goto L_0x00bb
            r2.disconnect()
        L_0x00bb:
            return r9
        L_0x00bc:
            r9 = move-exception
            goto L_0x00cb
        L_0x00be:
            r9 = move-exception
            r3 = r1
            goto L_0x00e8
        L_0x00c1:
            r9 = move-exception
            r3 = r1
            goto L_0x00cb
        L_0x00c4:
            r9 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x00e8
        L_0x00c8:
            r9 = move-exception
            r2 = r1
            r3 = r2
        L_0x00cb:
            java.lang.Class r4 = r8.getClass()     // Catch:{ all -> 0x00e7 }
            r5 = 3
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r4, (int) r5, (java.lang.Throwable) r9)     // Catch:{ all -> 0x00e7 }
            java.lang.Class r9 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r9, (java.io.Closeable) r1)
            java.lang.Class r9 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r9, (java.io.Closeable) r3)
            if (r2 == 0) goto L_0x00e6
            r2.disconnect()
        L_0x00e6:
            return r0
        L_0x00e7:
            r9 = move-exception
        L_0x00e8:
            java.lang.Class r0 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r1)
            java.lang.Class r0 = r8.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r3)
            if (r2 == 0) goto L_0x00fb
            r2.disconnect()
        L_0x00fb:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.network.g.execute(byte[]):int");
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
