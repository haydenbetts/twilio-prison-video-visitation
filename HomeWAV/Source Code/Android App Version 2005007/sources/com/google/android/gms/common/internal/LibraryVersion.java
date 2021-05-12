package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzc = new ConcurrentHashMap<>();

    public static LibraryVersion getInstance() {
        return zzb;
    }

    protected LibraryVersion() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a2 A[Catch:{ all -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a7 A[Catch:{ all -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getVersion(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10, r2)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = r9.zzc
            boolean r2 = r2.containsKey(r10)
            if (r2 == 0) goto L_0x001a
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            java.lang.Object r10 = r0.get(r10)
            java.lang.String r10 = (java.lang.String) r10
            return r10
        L_0x001a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0094 }
            r6 = 0
            r5[r6] = r10     // Catch:{ IOException -> 0x0094 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ IOException -> 0x0094 }
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch:{ IOException -> 0x0094 }
            if (r4 == 0) goto L_0x006b
            r2.load(r4)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r5 = r5.length()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r5 = r5 + 12
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r6 = r6.length()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r6.<init>(r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r6.append(r10)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r5 = " version is "
            r6.append(r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r6.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r5 = r6.toString()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.v(r1, r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            goto L_0x0084
        L_0x006b:
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r6 = r5.length()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            if (r6 == 0) goto L_0x007c
            java.lang.String r5 = r0.concat(r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            goto L_0x0081
        L_0x007c:
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r5.<init>(r0)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
        L_0x0081:
            r2.w(r1, r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
        L_0x0084:
            if (r4 == 0) goto L_0x00b6
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r4)
            goto L_0x00b6
        L_0x008a:
            r10 = move-exception
            r3 = r4
            goto L_0x00c7
        L_0x008d:
            r2 = move-exception
            r8 = r4
            r4 = r3
            r3 = r8
            goto L_0x0096
        L_0x0092:
            r10 = move-exception
            goto L_0x00c7
        L_0x0094:
            r2 = move-exception
            r4 = r3
        L_0x0096:
            com.google.android.gms.common.internal.GmsLogger r5 = zza     // Catch:{ all -> 0x0092 }
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x0092 }
            int r7 = r6.length()     // Catch:{ all -> 0x0092 }
            if (r7 == 0) goto L_0x00a7
            java.lang.String r0 = r0.concat(r6)     // Catch:{ all -> 0x0092 }
            goto L_0x00ad
        L_0x00a7:
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x0092 }
            r6.<init>(r0)     // Catch:{ all -> 0x0092 }
            r0 = r6
        L_0x00ad:
            r5.e(r1, r0, r2)     // Catch:{ all -> 0x0092 }
            if (r3 == 0) goto L_0x00b5
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
        L_0x00b5:
            r3 = r4
        L_0x00b6:
            if (r3 != 0) goto L_0x00c1
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.d(r1, r2)
            java.lang.String r3 = "UNKNOWN"
        L_0x00c1:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            r0.put(r10, r3)
            return r3
        L_0x00c7:
            if (r3 == 0) goto L_0x00cc
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
        L_0x00cc:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
