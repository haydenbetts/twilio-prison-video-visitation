package org.bouncycastle.util.io.pem;

import com.microsoft.appcenter.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.bouncycastle.util.encoders.Base64;

public class PemReader extends BufferedReader {
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";

    public PemReader(Reader reader) {
        super(reader);
    }

    private PemObject loadObject(String str) throws IOException {
        String readLine;
        String str2 = END + str;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        while (true) {
            readLine = readLine();
            if (readLine == null) {
                break;
            } else if (readLine.indexOf(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR) >= 0) {
                int indexOf = readLine.indexOf(58);
                arrayList.add(new PemHeader(readLine.substring(0, indexOf), readLine.substring(indexOf + 1).trim()));
            } else if (readLine.indexOf(str2) != -1) {
                break;
            } else {
                stringBuffer.append(readLine.trim());
            }
        }
        if (readLine != null) {
            return new PemObject(str, arrayList, Base64.decode(stringBuffer.toString()));
        }
        throw new IOException(str2 + " not found");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public org.bouncycastle.util.io.pem.PemObject readPemObject() throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            java.lang.String r0 = r3.readLine()
            if (r0 == 0) goto L_0x000f
            java.lang.String r1 = "-----BEGIN "
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x000f
            goto L_0x0000
        L_0x000f:
            if (r0 == 0) goto L_0x0029
            r1 = 11
            java.lang.String r0 = r0.substring(r1)
            r1 = 45
            int r1 = r0.indexOf(r1)
            r2 = 0
            java.lang.String r0 = r0.substring(r2, r1)
            if (r1 <= 0) goto L_0x0029
            org.bouncycastle.util.io.pem.PemObject r0 = r3.loadObject(r0)
            return r0
        L_0x0029:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.util.io.pem.PemReader.readPemObject():org.bouncycastle.util.io.pem.PemObject");
    }
}
