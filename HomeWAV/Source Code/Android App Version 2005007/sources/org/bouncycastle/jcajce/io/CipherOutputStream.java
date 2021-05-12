package org.bouncycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;

public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte = new byte[1];

    public CipherOutputStream(OutputStream outputStream, Cipher cipher2) {
        super(outputStream);
        this.cipher = cipher2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            javax.crypto.Cipher r0 = r4.cipher     // Catch:{ GeneralSecurityException -> 0x0027, Exception -> 0x000f }
            byte[] r0 = r0.doFinal()     // Catch:{ GeneralSecurityException -> 0x0027, Exception -> 0x000f }
            if (r0 == 0) goto L_0x000d
            java.io.OutputStream r1 = r4.out     // Catch:{ GeneralSecurityException -> 0x0027, Exception -> 0x000f }
            r1.write(r0)     // Catch:{ GeneralSecurityException -> 0x0027, Exception -> 0x000f }
        L_0x000d:
            r0 = 0
            goto L_0x0030
        L_0x000f:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error closing stream: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            goto L_0x002f
        L_0x0027:
            r0 = move-exception
            org.bouncycastle.crypto.io.InvalidCipherTextIOException r1 = new org.bouncycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error during cipher finalisation"
            r1.<init>(r2, r0)
        L_0x002f:
            r0 = r1
        L_0x0030:
            r4.flush()     // Catch:{ IOException -> 0x0039 }
            java.io.OutputStream r1 = r4.out     // Catch:{ IOException -> 0x0039 }
            r1.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            if (r0 != 0) goto L_0x003d
            r0 = r1
        L_0x003d:
            if (r0 != 0) goto L_0x0040
            return
        L_0x0040:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.io.CipherOutputStream.close():void");
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] update = this.cipher.update(bArr, i, i2);
        if (update != null) {
            this.out.write(update);
        }
    }
}
