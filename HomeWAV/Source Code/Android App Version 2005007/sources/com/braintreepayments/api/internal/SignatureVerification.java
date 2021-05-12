package com.braintreepayments.api.internal;

public class SignatureVerification {
    static boolean sEnableSignatureVerification = true;

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0074 A[SYNTHETIC, Splitter:B:40:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x007a A[SYNTHETIC, Splitter:B:46:0x007a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isSignatureValid(android.content.Context r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, int r12) {
        /*
            boolean r0 = sEnableSignatureVerification
            r1 = 1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.content.pm.PackageManager r8 = r8.getPackageManager()
            r0 = 64
            r2 = 0
            android.content.pm.PackageInfo r8 = r8.getPackageInfo(r9, r0)     // Catch:{ NameNotFoundException -> 0x007f }
            android.content.pm.Signature[] r8 = r8.signatures     // Catch:{ NameNotFoundException -> 0x007f }
            r9 = 0
            int r0 = r8.length
            if (r0 == 0) goto L_0x0019
            r0 = 1
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            int r3 = r8.length
            r4 = 0
        L_0x001c:
            if (r4 >= r3) goto L_0x007e
            r5 = r8[r4]
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ CertificateException -> 0x0078, all -> 0x0071 }
            byte[] r5 = r5.toByteArray()     // Catch:{ CertificateException -> 0x0078, all -> 0x0071 }
            r6.<init>(r5)     // Catch:{ CertificateException -> 0x0078, all -> 0x0071 }
            java.lang.String r9 = "X509"
            java.security.cert.CertificateFactory r9 = java.security.cert.CertificateFactory.getInstance(r9)     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            java.security.cert.Certificate r9 = r9.generateCertificate(r6)     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            java.security.cert.X509Certificate r9 = (java.security.cert.X509Certificate) r9     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            javax.security.auth.x500.X500Principal r5 = r9.getSubjectX500Principal()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            java.lang.String r5 = r5.getName()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            javax.security.auth.x500.X500Principal r7 = r9.getIssuerX500Principal()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            java.lang.String r7 = r7.getName()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            java.security.PublicKey r9 = r9.getPublicKey()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            int r9 = r9.hashCode()     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            boolean r5 = r10.equals(r5)     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            if (r5 == 0) goto L_0x005d
            boolean r5 = r11.equals(r7)     // Catch:{ CertificateException -> 0x006f, all -> 0x006c }
            if (r5 == 0) goto L_0x005d
            if (r12 != r9) goto L_0x005d
            r9 = 1
            goto L_0x005e
        L_0x005d:
            r9 = 0
        L_0x005e:
            r0 = r0 & r9
            if (r0 != 0) goto L_0x0065
            r6.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0064:
            return r2
        L_0x0065:
            r6.close()     // Catch:{ IOException -> 0x0068 }
        L_0x0068:
            int r4 = r4 + 1
            r9 = r6
            goto L_0x001c
        L_0x006c:
            r8 = move-exception
            r9 = r6
            goto L_0x0072
        L_0x006f:
            r9 = r6
            goto L_0x0078
        L_0x0071:
            r8 = move-exception
        L_0x0072:
            if (r9 == 0) goto L_0x0077
            r9.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0077:
            throw r8
        L_0x0078:
            if (r9 == 0) goto L_0x007d
            r9.close()     // Catch:{ IOException -> 0x007d }
        L_0x007d:
            return r2
        L_0x007e:
            return r0
        L_0x007f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.internal.SignatureVerification.isSignatureValid(android.content.Context, java.lang.String, java.lang.String, java.lang.String, int):boolean");
    }
}
