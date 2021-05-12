package lib.android.paypal.com.magnessdk.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import lib.android.paypal.com.magnessdk.b;
import lib.android.paypal.com.magnessdk.b.a;

public final class c {
    private c() {
    }

    public static String a(Context context, String str) {
        return context.getSharedPreferences(str, 0).getString(str, "");
    }

    public static String a(File file) {
        Class<c> cls = c.class;
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                byte[] bArr = new byte[((int) randomAccessFile2.length())];
                randomAccessFile2.readFully(bArr);
                String str = new String(bArr, "UTF-8");
                b.a((Class<?>) cls, (Closeable) randomAccessFile2);
                return str;
            } catch (Exception e) {
                e = e;
                randomAccessFile = randomAccessFile2;
                try {
                    a.a((Class<?>) cls, 3, (Throwable) e);
                    b.a((Class<?>) cls, (Closeable) randomAccessFile);
                    return "";
                } catch (Throwable th) {
                    th = th;
                    b.a((Class<?>) cls, (Closeable) randomAccessFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = randomAccessFile2;
                b.a((Class<?>) cls, (Closeable) randomAccessFile);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            a.a((Class<?>) cls, 3, (Throwable) e);
            b.a((Class<?>) cls, (Closeable) randomAccessFile);
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static boolean a(File file, String str) {
        Class<c> cls = c.class;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                fileOutputStream2.write(str.getBytes("UTF-8"));
                b.a((Class<?>) cls, (Closeable) fileOutputStream2);
                return true;
            } catch (Exception e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    a.a((Class<?>) cls, 3, (Throwable) e);
                    b.a((Class<?>) cls, (Closeable) fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    b.a((Class<?>) cls, (Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                b.a((Class<?>) cls, (Closeable) fileOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            a.a((Class<?>) cls, 3, (Throwable) e);
            b.a((Class<?>) cls, (Closeable) fileOutputStream);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.io.File r5) {
        /*
            java.lang.Class<lib.android.paypal.com.magnessdk.a.c> r0 = lib.android.paypal.com.magnessdk.a.c.class
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0020, all -> 0x001e }
        L_0x0012:
            java.lang.String r5 = r3.readLine()     // Catch:{ IOException -> 0x001c }
            if (r5 == 0) goto L_0x002b
            r1.append(r5)     // Catch:{ IOException -> 0x001c }
            goto L_0x0012
        L_0x001c:
            r5 = move-exception
            goto L_0x0022
        L_0x001e:
            r5 = move-exception
            goto L_0x0040
        L_0x0020:
            r5 = move-exception
            r3 = r2
        L_0x0022:
            r4 = -403(0xfffffffffffffe6d, float:NaN)
            r1.append(r4)     // Catch:{ all -> 0x003e }
            r4 = 3
            lib.android.paypal.com.magnessdk.b.a.a((java.lang.Class<?>) r0, (int) r4, (java.lang.Throwable) r5)     // Catch:{ all -> 0x003e }
        L_0x002b:
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r3)
            java.lang.String r5 = r1.toString()
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0039
            goto L_0x003d
        L_0x0039:
            java.lang.String r2 = r1.toString()
        L_0x003d:
            return r2
        L_0x003e:
            r5 = move-exception
            r2 = r3
        L_0x0040:
            lib.android.paypal.com.magnessdk.b.a((java.lang.Class<?>) r0, (java.io.Closeable) r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.a.c.b(java.io.File):java.lang.String");
    }

    public static boolean c(File file) {
        Class<c> cls = c.class;
        try {
            if (file.exists()) {
                a.a((Class<?>) cls, 0, "deleting CachedConfigDataFromDisk");
                return file.delete();
            }
        } catch (Exception e) {
            a.a((Class<?>) cls, 3, (Throwable) e);
        }
        return false;
    }
}
