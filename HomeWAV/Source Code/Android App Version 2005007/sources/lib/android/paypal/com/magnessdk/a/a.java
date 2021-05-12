package lib.android.paypal.com.magnessdk.a;

import android.os.Environment;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lib.android.paypal.com.magnessdk.b;

public class a {
    private static final int a = 1024;
    private boolean b = false;
    private boolean c = false;
    private File d;

    public a() {
        a();
        this.d = Environment.getExternalStorageDirectory();
    }

    private void a() {
        String externalStorageState = Environment.getExternalStorageState();
        externalStorageState.hashCode();
        if (externalStorageState.equals("mounted")) {
            this.c = true;
            this.b = true;
        } else if (!externalStorageState.equals("mounted_ro")) {
            this.c = false;
            this.b = false;
        } else {
            this.b = true;
            this.c = false;
        }
    }

    public void a(String str) {
        this.d = new File(str);
    }

    public void a(String str, byte[] bArr) throws IOException {
        if (this.b && this.c) {
            FileOutputStream fileOutputStream = null;
            try {
                if (this.d.mkdirs() || this.d.isDirectory()) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.d, str));
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream = fileOutputStream2;
                    } catch (Throwable th) {
                        fileOutputStream = fileOutputStream2;
                        th = th;
                        b.a(getClass(), (Closeable) fileOutputStream);
                        throw th;
                    }
                }
                b.a(getClass(), (Closeable) fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                b.a(getClass(), (Closeable) fileOutputStream);
                throw th;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r0]
            boolean r2 = r6.c
            r3 = 0
            if (r2 == 0) goto L_0x0049
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0040 }
            java.io.File r4 = r6.d     // Catch:{ all -> 0x0040 }
            r2.<init>(r4, r7)     // Catch:{ all -> 0x0040 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x0040 }
            r7.<init>(r2)     // Catch:{ all -> 0x0040 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x003d }
            r2.<init>()     // Catch:{ all -> 0x003d }
            r3 = 0
            int r4 = r7.read(r1, r3, r0)     // Catch:{ all -> 0x003d }
        L_0x001f:
            r5 = -1
            if (r4 == r5) goto L_0x002a
            r2.write(r1, r3, r4)     // Catch:{ all -> 0x003d }
            int r4 = r7.read(r1, r3, r0)     // Catch:{ all -> 0x003d }
            goto L_0x001f
        L_0x002a:
            byte[] r0 = r2.toByteArray()     // Catch:{ all -> 0x003d }
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "UTF-8"
            r3.<init>(r0, r1)     // Catch:{ all -> 0x003d }
            java.lang.Class r0 = r6.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r7)
            goto L_0x0049
        L_0x003d:
            r0 = move-exception
            r3 = r7
            goto L_0x0041
        L_0x0040:
            r0 = move-exception
        L_0x0041:
            java.lang.Class r7 = r6.getClass()
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r7, (java.io.Closeable) r3)
            throw r0
        L_0x0049:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.a.a.b(java.lang.String):java.lang.String");
    }

    public boolean c(String str) {
        return new File(this.d, str).delete();
    }
}
