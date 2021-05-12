package com.urbanairship.util;

import com.urbanairship.Logger;
import java.io.Closeable;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public abstract class FileUtils {
    private static final int BUFFER_SIZE = 1024;
    private static final int NETWORK_TIMEOUT_MS = 2000;

    public static boolean deleteRecursively(File file) {
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File deleteRecursively : listFiles) {
                if (!deleteRecursively(deleteRecursively)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static class DownloadResult {
        public final boolean isSuccess;
        public final int statusCode;

        DownloadResult(boolean z, int i) {
            this.isSuccess = z;
            this.statusCode = i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.io.Closeable[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.net.URLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.net.URLConnection} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.urbanairship.util.FileUtils.DownloadResult downloadFile(java.net.URL r9, java.io.File r10) throws java.io.IOException {
        /*
            r0 = 2
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r9
            java.lang.String r3 = r10.getAbsolutePath()
            r4 = 1
            r1[r4] = r3
            java.lang.String r3 = "Downloading file from: %s to: %s"
            com.urbanairship.Logger.verbose(r3, r1)
            r1 = 0
            android.content.Context r3 = com.urbanairship.UAirship.getApplicationContext()     // Catch:{ IOException -> 0x0098, all -> 0x0094 }
            java.net.URLConnection r9 = com.urbanairship.util.ConnectionUtils.openSecureConnection(r3, r9)     // Catch:{ IOException -> 0x0098, all -> 0x0094 }
            r3 = 2000(0x7d0, float:2.803E-42)
            r9.setConnectTimeout(r3)     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            r9.setUseCaches(r4)     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            boolean r3 = r9 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            if (r3 == 0) goto L_0x0043
            r3 = r9
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            int r3 = r3.getResponseCode()     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            boolean r5 = com.urbanairship.util.UAHttpStatusUtil.inSuccessRange(r3)     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            if (r5 != 0) goto L_0x0044
            com.urbanairship.util.FileUtils$DownloadResult r5 = new com.urbanairship.util.FileUtils$DownloadResult     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            r5.<init>(r2, r3)     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            java.io.Closeable[] r10 = new java.io.Closeable[r0]
            r10[r2] = r1
            r10[r4] = r1
            endRequest(r9, r10)
            return r5
        L_0x0043:
            r3 = 0
        L_0x0044:
            java.io.InputStream r5 = r9.getInputStream()     // Catch:{ IOException -> 0x0091, all -> 0x008d }
            if (r5 == 0) goto L_0x0077
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            r6.<init>(r10)     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
        L_0x0053:
            int r7 = r5.read(r1)     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            r8 = -1
            if (r7 == r8) goto L_0x005e
            r6.write(r1, r2, r7)     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            goto L_0x0053
        L_0x005e:
            r6.close()     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            r5.close()     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            com.urbanairship.util.FileUtils$DownloadResult r1 = new com.urbanairship.util.FileUtils$DownloadResult     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            r1.<init>(r4, r3)     // Catch:{ IOException -> 0x0075, all -> 0x0073 }
            java.io.Closeable[] r10 = new java.io.Closeable[r0]
            r10[r2] = r5
            r10[r4] = r6
            endRequest(r9, r10)
            return r1
        L_0x0073:
            r10 = move-exception
            goto L_0x00a1
        L_0x0075:
            r3 = move-exception
            goto L_0x008b
        L_0x0077:
            com.urbanairship.util.FileUtils$DownloadResult r6 = new com.urbanairship.util.FileUtils$DownloadResult     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            r6.<init>(r2, r3)     // Catch:{ IOException -> 0x0089, all -> 0x0086 }
            java.io.Closeable[] r10 = new java.io.Closeable[r0]
            r10[r2] = r5
            r10[r4] = r1
            endRequest(r9, r10)
            return r6
        L_0x0086:
            r10 = move-exception
            r6 = r1
            goto L_0x00a1
        L_0x0089:
            r3 = move-exception
            r6 = r1
        L_0x008b:
            r1 = r5
            goto L_0x009b
        L_0x008d:
            r10 = move-exception
            r5 = r1
            r6 = r5
            goto L_0x00a1
        L_0x0091:
            r3 = move-exception
            r6 = r1
            goto L_0x009b
        L_0x0094:
            r10 = move-exception
            r5 = r1
            r6 = r5
            goto L_0x00a2
        L_0x0098:
            r3 = move-exception
            r9 = r1
            r6 = r9
        L_0x009b:
            r10.delete()     // Catch:{ all -> 0x009f }
            throw r3     // Catch:{ all -> 0x009f }
        L_0x009f:
            r10 = move-exception
            r5 = r1
        L_0x00a1:
            r1 = r9
        L_0x00a2:
            java.io.Closeable[] r9 = new java.io.Closeable[r0]
            r9[r2] = r5
            r9[r4] = r6
            endRequest(r1, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.util.FileUtils.downloadFile(java.net.URL, java.io.File):com.urbanairship.util.FileUtils$DownloadResult");
    }

    private static void endRequest(URLConnection uRLConnection, Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    Logger.error(e);
                }
            }
        }
        if (uRLConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            if (httpURLConnection.getErrorStream() != null) {
                try {
                    httpURLConnection.getErrorStream().close();
                } catch (Exception e2) {
                    Logger.error(e2);
                }
            }
            httpURLConnection.disconnect();
        }
    }
}
