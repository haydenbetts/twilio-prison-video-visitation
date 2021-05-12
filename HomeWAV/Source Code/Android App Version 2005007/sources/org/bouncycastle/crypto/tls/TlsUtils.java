package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.UByte;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;

public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte[][] SSL3_CONST = genSSL3Const();
    static final byte[] SSL_CLIENT = {67, 76, 78, 84};
    static final byte[] SSL_SERVER = {83, 82, 86, 82};

    public static byte[] PRF(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i) {
        if (!tlsContext.getServerVersion().isSSL()) {
            byte[] byteArray = Strings.toByteArray(str);
            byte[] concat = concat(byteArray, bArr2);
            int prfAlgorithm = tlsContext.getSecurityParameters().getPrfAlgorithm();
            if (prfAlgorithm == 0) {
                return PRF_legacy(bArr, byteArray, concat, i);
            }
            byte[] bArr3 = new byte[i];
            hmac_hash(createPRFHash(prfAlgorithm), bArr, concat, bArr3);
            return bArr3;
        }
        throw new IllegalStateException("No PRF available for SSLv3 session");
    }

    public static byte[] PRF_legacy(byte[] bArr, String str, byte[] bArr2, int i) {
        byte[] byteArray = Strings.toByteArray(str);
        return PRF_legacy(bArr, byteArray, concat(byteArray, bArr2), i);
    }

    static byte[] PRF_legacy(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        hmac_hash(createHash(1), bArr4, bArr3, bArr6);
        hmac_hash(createHash(2), bArr5, bArr3, bArr7);
        for (int i2 = 0; i2 < i; i2++) {
            bArr6[i2] = (byte) (bArr6[i2] ^ bArr7[i2]);
        }
        return bArr6;
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) throws IOException {
        hashtable.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(vector));
    }

    static byte[] calculateKeyBlock(TlsContext tlsContext, int i) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] masterSecret = securityParameters.getMasterSecret();
        byte[] concat = concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        return isSSL(tlsContext) ? calculateKeyBlock_SSL(masterSecret, concat, i) : PRF(tlsContext, masterSecret, "key expansion", concat, i);
    }

    static byte[] calculateKeyBlock_SSL(byte[] bArr, byte[] bArr2, int i) {
        Digest createHash = createHash(1);
        Digest createHash2 = createHash(2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[(i + digestSize)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte[] bArr5 = SSL3_CONST[i3];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i2);
            i2 += digestSize;
            i3++;
        }
        return Arrays.copyOfRange(bArr4, 0, i);
    }

    static byte[] calculateMasterSecret(TlsContext tlsContext, byte[] bArr) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] sessionHash = securityParameters.extendedMasterSecret ? securityParameters.getSessionHash() : concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if (isSSL(tlsContext)) {
            return calculateMasterSecret_SSL(bArr, sessionHash);
        }
        return PRF(tlsContext, bArr, securityParameters.extendedMasterSecret ? ExporterLabel.extended_master_secret : "master secret", sessionHash, 48);
    }

    static byte[] calculateMasterSecret_SSL(byte[] bArr, byte[] bArr2) {
        Digest createHash = createHash(1);
        Digest createHash2 = createHash(2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[(digestSize * 3)];
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            byte[] bArr5 = SSL3_CONST[i2];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i);
            i += digestSize;
        }
        return bArr4;
    }

    static byte[] calculateVerifyData(TlsContext tlsContext, String str, byte[] bArr) {
        if (isSSL(tlsContext)) {
            return bArr;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        return PRF(tlsContext, securityParameters.getMasterSecret(), str, bArr, securityParameters.getVerifyDataLength());
    }

    public static void checkUint16(int i) throws IOException {
        if (!isValidUint16(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint16(long j) throws IOException {
        if (!isValidUint16(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(int i) throws IOException {
        if (!isValidUint24(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint24(long j) throws IOException {
        if (!isValidUint24(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint32(long j) throws IOException {
        if (!isValidUint32(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint48(long j) throws IOException {
        if (!isValidUint48(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint64(long j) throws IOException {
        if (!isValidUint64(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(int i) throws IOException {
        if (!isValidUint8(i)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(long j) throws IOException {
        if (!isValidUint8(j)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static void checkUint8(short s) throws IOException {
        if (!isValidUint8(s)) {
            throw new TlsFatalAlert(80);
        }
    }

    public static Digest cloneHash(short s, Digest digest) {
        switch (s) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest clonePRFHash(int i, Digest digest) {
        return i != 0 ? cloneHash(getHashAlgorithmForPRFAlgorithm(i), digest) : new CombinedHash((CombinedHash) digest);
    }

    static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return signatureAndHashAlgorithm == null ? new CombinedHash() : createHash(signatureAndHashAlgorithm.getHash());
    }

    public static Digest createHash(short s) {
        switch (s) {
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createPRFHash(int i) {
        return i != 0 ? createHash(getHashAlgorithmForPRFAlgorithm(i)) : new CombinedHash();
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static TlsSigner createTlsSigner(short s) {
        if (s == 1) {
            return new TlsRSASigner();
        }
        if (s == 2) {
            return new TlsDSSSigner();
        }
        if (s == 64) {
            return new TlsECDSASigner();
        }
        throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
    }

    public static byte[] encodeOpaque8(byte[] bArr) throws IOException {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean z, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        int i = 0;
        while (i < vector.size()) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (z || signatureAndHashAlgorithm.getSignature() != 0) {
                signatureAndHashAlgorithm.encode(outputStream);
                i++;
            } else {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
        }
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) throws IOException {
        byte[] bArr = new byte[((iArr.length * 2) + 2)];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) throws IOException {
        byte[] bArr = new byte[(sArr.length + 1)];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    private static byte[][] genSSL3Const() {
        byte[][] bArr = new byte[10][];
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            byte[] bArr2 = new byte[i2];
            Arrays.fill(bArr2, (byte) (i + 65));
            bArr[i] = bArr2;
            i = i2;
        }
        return bArr;
    }

    public static int getCipherType(int i) throws IOException {
        int encryptionAlgorithm = getEncryptionAlgorithm(i);
        switch (encryptionAlgorithm) {
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return 2;
            default:
                switch (encryptionAlgorithm) {
                    case 102:
                    case 103:
                    case 104:
                        return 2;
                    default:
                        throw new TlsFatalAlert(80);
                }
        }
    }

    static short getClientCertificateType(Certificate certificate, Certificate certificate2) throws IOException {
        if (certificate.isEmpty()) {
            return -1;
        }
        Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            if (createKey.isPrivate()) {
                throw new TlsFatalAlert(80);
            } else if (createKey instanceof RSAKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 1;
            } else if (createKey instanceof DSAPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 2;
            } else if (createKey instanceof ECPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return 64;
            } else {
                throw new TlsFatalAlert(43);
            }
        } catch (Exception e) {
            throw new TlsFatalAlert(43, e);
        }
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm(2, 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArr = {2, 3, 4, 5, 6};
        short[] sArr2 = {1, 2, 3};
        Vector vector = new Vector();
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                vector.addElement(new SignatureAndHashAlgorithm(sArr[i2], sArr2[i]));
            }
        }
        return vector;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0053, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getEncryptionAlgorithm(int r4) throws java.io.IOException {
        /*
            r0 = 1
            r1 = 0
            if (r4 == r0) goto L_0x0063
            r0 = 2
            if (r4 == r0) goto L_0x0063
            r2 = 4
            if (r4 == r2) goto L_0x0062
            r2 = 5
            if (r4 == r2) goto L_0x0062
            r2 = 12
            r3 = 13
            switch(r4) {
                case 10: goto L_0x0060;
                case 13: goto L_0x0060;
                case 16: goto L_0x0060;
                case 19: goto L_0x0060;
                case 22: goto L_0x0060;
                case 24: goto L_0x0062;
                case 168: goto L_0x005d;
                case 169: goto L_0x005a;
                case 170: goto L_0x005d;
                case 171: goto L_0x005a;
                case 172: goto L_0x005d;
                case 173: goto L_0x005a;
                case 174: goto L_0x0057;
                case 175: goto L_0x0054;
                case 176: goto L_0x0053;
                case 177: goto L_0x0053;
                case 178: goto L_0x0057;
                case 179: goto L_0x0054;
                case 180: goto L_0x0053;
                case 181: goto L_0x0053;
                case 182: goto L_0x0057;
                case 183: goto L_0x0054;
                case 184: goto L_0x0053;
                case 185: goto L_0x0053;
                case 186: goto L_0x0052;
                case 187: goto L_0x0052;
                case 188: goto L_0x0052;
                case 189: goto L_0x0052;
                case 190: goto L_0x0052;
                case 192: goto L_0x0051;
                case 193: goto L_0x0051;
                case 194: goto L_0x0051;
                case 195: goto L_0x0051;
                case 196: goto L_0x0051;
                case 49153: goto L_0x0063;
                case 49154: goto L_0x0062;
                case 49155: goto L_0x0060;
                case 49156: goto L_0x0057;
                case 49157: goto L_0x0054;
                case 49158: goto L_0x0063;
                case 49159: goto L_0x0062;
                case 49160: goto L_0x0060;
                case 49161: goto L_0x0057;
                case 49162: goto L_0x0054;
                case 49163: goto L_0x0063;
                case 49164: goto L_0x0062;
                case 49165: goto L_0x0060;
                case 49166: goto L_0x0057;
                case 49167: goto L_0x0054;
                case 49168: goto L_0x0063;
                case 49169: goto L_0x0062;
                case 49170: goto L_0x0060;
                case 49171: goto L_0x0057;
                case 49172: goto L_0x0054;
                case 49173: goto L_0x0063;
                case 49174: goto L_0x0062;
                case 49175: goto L_0x0060;
                case 49176: goto L_0x0057;
                case 49177: goto L_0x0054;
                case 49178: goto L_0x0060;
                case 49179: goto L_0x0060;
                case 49180: goto L_0x0060;
                case 49181: goto L_0x0057;
                case 49182: goto L_0x0057;
                case 49183: goto L_0x0057;
                case 49184: goto L_0x0054;
                case 49185: goto L_0x0054;
                case 49186: goto L_0x0054;
                case 49187: goto L_0x0057;
                case 49188: goto L_0x0054;
                case 49189: goto L_0x0057;
                case 49190: goto L_0x0054;
                case 49191: goto L_0x0057;
                case 49192: goto L_0x0054;
                case 49193: goto L_0x0057;
                case 49194: goto L_0x0054;
                case 49195: goto L_0x005d;
                case 49196: goto L_0x005a;
                case 49197: goto L_0x005d;
                case 49198: goto L_0x005a;
                case 49199: goto L_0x005d;
                case 49200: goto L_0x005a;
                case 49201: goto L_0x005d;
                case 49202: goto L_0x005a;
                case 49203: goto L_0x0062;
                case 49204: goto L_0x0060;
                case 49205: goto L_0x0057;
                case 49206: goto L_0x0054;
                case 49207: goto L_0x0057;
                case 49208: goto L_0x0054;
                case 49209: goto L_0x0063;
                case 49210: goto L_0x0053;
                case 49211: goto L_0x0053;
                case 49266: goto L_0x0052;
                case 49267: goto L_0x0051;
                case 49268: goto L_0x0052;
                case 49269: goto L_0x0051;
                case 49270: goto L_0x0052;
                case 49271: goto L_0x0051;
                case 49272: goto L_0x0052;
                case 49273: goto L_0x0051;
                case 49274: goto L_0x004e;
                case 49275: goto L_0x004b;
                case 49276: goto L_0x004e;
                case 49277: goto L_0x004b;
                case 49278: goto L_0x004e;
                case 49279: goto L_0x004b;
                case 49280: goto L_0x004e;
                case 49281: goto L_0x004b;
                case 49282: goto L_0x004e;
                case 49283: goto L_0x004b;
                case 49286: goto L_0x004e;
                case 49287: goto L_0x004b;
                case 49288: goto L_0x004e;
                case 49289: goto L_0x004b;
                case 49290: goto L_0x004e;
                case 49291: goto L_0x004b;
                case 49292: goto L_0x004e;
                case 49293: goto L_0x004b;
                case 49294: goto L_0x004e;
                case 49295: goto L_0x004b;
                case 49296: goto L_0x004e;
                case 49297: goto L_0x004b;
                case 49298: goto L_0x004e;
                case 49299: goto L_0x004b;
                case 49300: goto L_0x0052;
                case 49301: goto L_0x0051;
                case 49302: goto L_0x0052;
                case 49303: goto L_0x0051;
                case 49304: goto L_0x0052;
                case 49305: goto L_0x0051;
                case 49306: goto L_0x0052;
                case 49307: goto L_0x0051;
                case 49308: goto L_0x0048;
                case 49309: goto L_0x0045;
                case 49310: goto L_0x0048;
                case 49311: goto L_0x0045;
                case 49312: goto L_0x0042;
                case 49313: goto L_0x003f;
                case 49314: goto L_0x0042;
                case 49315: goto L_0x003f;
                case 49316: goto L_0x0048;
                case 49317: goto L_0x0045;
                case 49318: goto L_0x0048;
                case 49319: goto L_0x0045;
                case 49320: goto L_0x0042;
                case 49321: goto L_0x003f;
                case 49322: goto L_0x0042;
                case 49323: goto L_0x003f;
                case 49324: goto L_0x0048;
                case 49325: goto L_0x0045;
                case 49326: goto L_0x0042;
                case 49327: goto L_0x003f;
                case 52392: goto L_0x003c;
                case 52393: goto L_0x003c;
                case 52394: goto L_0x003c;
                case 52395: goto L_0x003c;
                case 52396: goto L_0x003c;
                case 52397: goto L_0x003c;
                case 52398: goto L_0x003c;
                case 65280: goto L_0x0039;
                case 65281: goto L_0x0036;
                case 65282: goto L_0x0039;
                case 65283: goto L_0x0036;
                case 65284: goto L_0x0039;
                case 65285: goto L_0x0036;
                case 65296: goto L_0x0039;
                case 65297: goto L_0x0036;
                case 65298: goto L_0x0039;
                case 65299: goto L_0x0036;
                case 65300: goto L_0x0039;
                case 65301: goto L_0x0036;
                default: goto L_0x0014;
            }
        L_0x0014:
            switch(r4) {
                case 44: goto L_0x0063;
                case 45: goto L_0x0063;
                case 46: goto L_0x0063;
                case 47: goto L_0x0057;
                case 48: goto L_0x0057;
                case 49: goto L_0x0057;
                case 50: goto L_0x0057;
                case 51: goto L_0x0057;
                default: goto L_0x0017;
            }
        L_0x0017:
            switch(r4) {
                case 53: goto L_0x0054;
                case 54: goto L_0x0054;
                case 55: goto L_0x0054;
                case 56: goto L_0x0054;
                case 57: goto L_0x0054;
                default: goto L_0x001a;
            }
        L_0x001a:
            switch(r4) {
                case 59: goto L_0x0053;
                case 60: goto L_0x0057;
                case 61: goto L_0x0054;
                case 62: goto L_0x0057;
                case 63: goto L_0x0057;
                case 64: goto L_0x0057;
                case 65: goto L_0x0035;
                case 66: goto L_0x0035;
                case 67: goto L_0x0035;
                case 68: goto L_0x0035;
                case 69: goto L_0x0035;
                default: goto L_0x001d;
            }
        L_0x001d:
            switch(r4) {
                case 103: goto L_0x0057;
                case 104: goto L_0x0054;
                case 105: goto L_0x0054;
                case 106: goto L_0x0054;
                case 107: goto L_0x0054;
                default: goto L_0x0020;
            }
        L_0x0020:
            switch(r4) {
                case 132: goto L_0x0034;
                case 133: goto L_0x0034;
                case 134: goto L_0x0034;
                case 135: goto L_0x0034;
                case 136: goto L_0x0034;
                default: goto L_0x0023;
            }
        L_0x0023:
            switch(r4) {
                case 138: goto L_0x0062;
                case 139: goto L_0x0060;
                case 140: goto L_0x0057;
                case 141: goto L_0x0054;
                case 142: goto L_0x0062;
                case 143: goto L_0x0060;
                case 144: goto L_0x0057;
                case 145: goto L_0x0054;
                case 146: goto L_0x0062;
                case 147: goto L_0x0060;
                case 148: goto L_0x0057;
                case 149: goto L_0x0054;
                case 150: goto L_0x0031;
                case 151: goto L_0x0031;
                case 152: goto L_0x0031;
                case 153: goto L_0x0031;
                case 154: goto L_0x0031;
                default: goto L_0x0026;
            }
        L_0x0026:
            switch(r4) {
                case 156: goto L_0x005d;
                case 157: goto L_0x005a;
                case 158: goto L_0x005d;
                case 159: goto L_0x005a;
                case 160: goto L_0x005d;
                case 161: goto L_0x005a;
                case 162: goto L_0x005d;
                case 163: goto L_0x005a;
                case 164: goto L_0x005d;
                case 165: goto L_0x005a;
                default: goto L_0x0029;
            }
        L_0x0029:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r0 = 80
            r4.<init>(r0)
            throw r4
        L_0x0031:
            r4 = 14
            return r4
        L_0x0034:
            return r3
        L_0x0035:
            return r2
        L_0x0036:
            r4 = 104(0x68, float:1.46E-43)
            return r4
        L_0x0039:
            r4 = 103(0x67, float:1.44E-43)
            return r4
        L_0x003c:
            r4 = 102(0x66, float:1.43E-43)
            return r4
        L_0x003f:
            r4 = 18
            return r4
        L_0x0042:
            r4 = 16
            return r4
        L_0x0045:
            r4 = 17
            return r4
        L_0x0048:
            r4 = 15
            return r4
        L_0x004b:
            r4 = 20
            return r4
        L_0x004e:
            r4 = 19
            return r4
        L_0x0051:
            return r3
        L_0x0052:
            return r2
        L_0x0053:
            return r1
        L_0x0054:
            r4 = 9
            return r4
        L_0x0057:
            r4 = 8
            return r4
        L_0x005a:
            r4 = 11
            return r4
        L_0x005d:
            r4 = 10
            return r4
        L_0x0060:
            r4 = 7
            return r4
        L_0x0062:
            return r0
        L_0x0063:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");
        } else if (i == 1) {
            return 4;
        } else {
            if (i == 2) {
                return 5;
            }
            throw new IllegalArgumentException("unknown PRFAlgorithm");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0050, code lost:
        return 5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getKeyExchangeAlgorithm(int r2) throws java.io.IOException {
        /*
            r0 = 1
            if (r2 == r0) goto L_0x0058
            r1 = 2
            if (r2 == r1) goto L_0x0058
            r1 = 4
            if (r2 == r1) goto L_0x0058
            r1 = 5
            if (r2 == r1) goto L_0x0058
            switch(r2) {
                case 10: goto L_0x0058;
                case 13: goto L_0x0056;
                case 16: goto L_0x0053;
                case 19: goto L_0x0051;
                case 22: goto L_0x0050;
                case 168: goto L_0x004d;
                case 169: goto L_0x004d;
                case 170: goto L_0x004a;
                case 171: goto L_0x004a;
                case 172: goto L_0x0047;
                case 173: goto L_0x0047;
                case 174: goto L_0x004d;
                case 175: goto L_0x004d;
                case 176: goto L_0x004d;
                case 177: goto L_0x004d;
                case 178: goto L_0x004a;
                case 179: goto L_0x004a;
                case 180: goto L_0x004a;
                case 181: goto L_0x004a;
                case 182: goto L_0x0047;
                case 183: goto L_0x0047;
                case 184: goto L_0x0047;
                case 185: goto L_0x0047;
                case 186: goto L_0x0058;
                case 187: goto L_0x0056;
                case 188: goto L_0x0053;
                case 189: goto L_0x0051;
                case 190: goto L_0x0050;
                case 192: goto L_0x0058;
                case 193: goto L_0x0056;
                case 194: goto L_0x0053;
                case 195: goto L_0x0051;
                case 196: goto L_0x0050;
                case 49153: goto L_0x0044;
                case 49154: goto L_0x0044;
                case 49155: goto L_0x0044;
                case 49156: goto L_0x0044;
                case 49157: goto L_0x0044;
                case 49158: goto L_0x0041;
                case 49159: goto L_0x0041;
                case 49160: goto L_0x0041;
                case 49161: goto L_0x0041;
                case 49162: goto L_0x0041;
                case 49163: goto L_0x003e;
                case 49164: goto L_0x003e;
                case 49165: goto L_0x003e;
                case 49166: goto L_0x003e;
                case 49167: goto L_0x003e;
                case 49168: goto L_0x003b;
                case 49169: goto L_0x003b;
                case 49170: goto L_0x003b;
                case 49171: goto L_0x003b;
                case 49172: goto L_0x003b;
                case 49173: goto L_0x0038;
                case 49174: goto L_0x0038;
                case 49175: goto L_0x0038;
                case 49176: goto L_0x0038;
                case 49177: goto L_0x0038;
                case 49178: goto L_0x0035;
                case 49179: goto L_0x0032;
                case 49180: goto L_0x002f;
                case 49181: goto L_0x0035;
                case 49182: goto L_0x0032;
                case 49183: goto L_0x002f;
                case 49184: goto L_0x0035;
                case 49185: goto L_0x0032;
                case 49186: goto L_0x002f;
                case 49187: goto L_0x0041;
                case 49188: goto L_0x0041;
                case 49189: goto L_0x0044;
                case 49190: goto L_0x0044;
                case 49191: goto L_0x003b;
                case 49192: goto L_0x003b;
                case 49193: goto L_0x003e;
                case 49194: goto L_0x003e;
                case 49195: goto L_0x0041;
                case 49196: goto L_0x0041;
                case 49197: goto L_0x0044;
                case 49198: goto L_0x0044;
                case 49199: goto L_0x003b;
                case 49200: goto L_0x003b;
                case 49201: goto L_0x003e;
                case 49202: goto L_0x003e;
                case 49203: goto L_0x002c;
                case 49204: goto L_0x002c;
                case 49205: goto L_0x002c;
                case 49206: goto L_0x002c;
                case 49207: goto L_0x002c;
                case 49208: goto L_0x002c;
                case 49209: goto L_0x002c;
                case 49210: goto L_0x002c;
                case 49211: goto L_0x002c;
                case 49266: goto L_0x0041;
                case 49267: goto L_0x0041;
                case 49268: goto L_0x0044;
                case 49269: goto L_0x0044;
                case 49270: goto L_0x003b;
                case 49271: goto L_0x003b;
                case 49272: goto L_0x003e;
                case 49273: goto L_0x003e;
                case 49274: goto L_0x0058;
                case 49275: goto L_0x0058;
                case 49276: goto L_0x0050;
                case 49277: goto L_0x0050;
                case 49278: goto L_0x0053;
                case 49279: goto L_0x0053;
                case 49280: goto L_0x0051;
                case 49281: goto L_0x0051;
                case 49282: goto L_0x0056;
                case 49283: goto L_0x0056;
                case 49286: goto L_0x0041;
                case 49287: goto L_0x0041;
                case 49288: goto L_0x0044;
                case 49289: goto L_0x0044;
                case 49290: goto L_0x003b;
                case 49291: goto L_0x003b;
                case 49292: goto L_0x003e;
                case 49293: goto L_0x003e;
                case 49294: goto L_0x004d;
                case 49295: goto L_0x004d;
                case 49296: goto L_0x004a;
                case 49297: goto L_0x004a;
                case 49298: goto L_0x0047;
                case 49299: goto L_0x0047;
                case 49300: goto L_0x004d;
                case 49301: goto L_0x004d;
                case 49302: goto L_0x004a;
                case 49303: goto L_0x004a;
                case 49304: goto L_0x0047;
                case 49305: goto L_0x0047;
                case 49306: goto L_0x002c;
                case 49307: goto L_0x002c;
                case 49308: goto L_0x0058;
                case 49309: goto L_0x0058;
                case 49310: goto L_0x0050;
                case 49311: goto L_0x0050;
                case 49312: goto L_0x0058;
                case 49313: goto L_0x0058;
                case 49314: goto L_0x0050;
                case 49315: goto L_0x0050;
                case 49316: goto L_0x004d;
                case 49317: goto L_0x004d;
                case 49318: goto L_0x004a;
                case 49319: goto L_0x004a;
                case 49320: goto L_0x004d;
                case 49321: goto L_0x004d;
                case 49322: goto L_0x004a;
                case 49323: goto L_0x004a;
                case 49324: goto L_0x0041;
                case 49325: goto L_0x0041;
                case 49326: goto L_0x0041;
                case 49327: goto L_0x0041;
                case 52392: goto L_0x003b;
                case 52393: goto L_0x0041;
                case 52394: goto L_0x0050;
                case 52395: goto L_0x004d;
                case 52396: goto L_0x002c;
                case 52397: goto L_0x004a;
                case 52398: goto L_0x0047;
                case 65280: goto L_0x0050;
                case 65281: goto L_0x0050;
                case 65282: goto L_0x003b;
                case 65283: goto L_0x003b;
                case 65284: goto L_0x0041;
                case 65285: goto L_0x0041;
                case 65296: goto L_0x004d;
                case 65297: goto L_0x004d;
                case 65298: goto L_0x004a;
                case 65299: goto L_0x004a;
                case 65300: goto L_0x002c;
                case 65301: goto L_0x002c;
                default: goto L_0x000f;
            }
        L_0x000f:
            switch(r2) {
                case 44: goto L_0x004d;
                case 45: goto L_0x004a;
                case 46: goto L_0x0047;
                case 47: goto L_0x0058;
                case 48: goto L_0x0056;
                case 49: goto L_0x0053;
                case 50: goto L_0x0051;
                case 51: goto L_0x0050;
                default: goto L_0x0012;
            }
        L_0x0012:
            switch(r2) {
                case 53: goto L_0x0058;
                case 54: goto L_0x0056;
                case 55: goto L_0x0053;
                case 56: goto L_0x0051;
                case 57: goto L_0x0050;
                default: goto L_0x0015;
            }
        L_0x0015:
            switch(r2) {
                case 59: goto L_0x0058;
                case 60: goto L_0x0058;
                case 61: goto L_0x0058;
                case 62: goto L_0x0056;
                case 63: goto L_0x0053;
                case 64: goto L_0x0051;
                case 65: goto L_0x0058;
                case 66: goto L_0x0056;
                case 67: goto L_0x0053;
                case 68: goto L_0x0051;
                case 69: goto L_0x0050;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r2) {
                case 103: goto L_0x0050;
                case 104: goto L_0x0056;
                case 105: goto L_0x0053;
                case 106: goto L_0x0051;
                case 107: goto L_0x0050;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r2) {
                case 132: goto L_0x0058;
                case 133: goto L_0x0056;
                case 134: goto L_0x0053;
                case 135: goto L_0x0051;
                case 136: goto L_0x0050;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r2) {
                case 138: goto L_0x004d;
                case 139: goto L_0x004d;
                case 140: goto L_0x004d;
                case 141: goto L_0x004d;
                case 142: goto L_0x004a;
                case 143: goto L_0x004a;
                case 144: goto L_0x004a;
                case 145: goto L_0x004a;
                case 146: goto L_0x0047;
                case 147: goto L_0x0047;
                case 148: goto L_0x0047;
                case 149: goto L_0x0047;
                case 150: goto L_0x0058;
                case 151: goto L_0x0056;
                case 152: goto L_0x0053;
                case 153: goto L_0x0051;
                case 154: goto L_0x0050;
                default: goto L_0x0021;
            }
        L_0x0021:
            switch(r2) {
                case 156: goto L_0x0058;
                case 157: goto L_0x0058;
                case 158: goto L_0x0050;
                case 159: goto L_0x0050;
                case 160: goto L_0x0053;
                case 161: goto L_0x0053;
                case 162: goto L_0x0051;
                case 163: goto L_0x0051;
                case 164: goto L_0x0056;
                case 165: goto L_0x0056;
                default: goto L_0x0024;
            }
        L_0x0024:
            org.bouncycastle.crypto.tls.TlsFatalAlert r2 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r0 = 80
            r2.<init>(r0)
            throw r2
        L_0x002c:
            r2 = 24
            return r2
        L_0x002f:
            r2 = 22
            return r2
        L_0x0032:
            r2 = 23
            return r2
        L_0x0035:
            r2 = 21
            return r2
        L_0x0038:
            r2 = 20
            return r2
        L_0x003b:
            r2 = 19
            return r2
        L_0x003e:
            r2 = 18
            return r2
        L_0x0041:
            r2 = 17
            return r2
        L_0x0044:
            r2 = 16
            return r2
        L_0x0047:
            r2 = 15
            return r2
        L_0x004a:
            r2 = 14
            return r2
        L_0x004d:
            r2 = 13
            return r2
        L_0x0050:
            return r1
        L_0x0051:
            r2 = 3
            return r2
        L_0x0053:
            r2 = 9
            return r2
        L_0x0056:
            r2 = 7
            return r2
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsUtils.getKeyExchangeAlgorithm(int):int");
    }

    public static int getMACAlgorithm(int i) throws IOException {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 5) {
                        switch (i) {
                            case 10:
                            case 13:
                            case 16:
                            case 19:
                            case 22:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA:
                            case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA:
                            case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA:
                                break;
                            case 168:
                            case 169:
                            case 170:
                            case 171:
                            case 172:
                            case 173:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384:
                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM:
                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM:
                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8:
                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8:
                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM:
                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM:
                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8:
                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8:
                            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8:
                            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8:
                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256:
                            case 65280:
                            case 65281:
                            case 65282:
                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB:
                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB:
                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB:
                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB:
                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB:
                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB:
                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB:
                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB:
                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB:
                                return 0;
                            case 174:
                            case 176:
                            case 178:
                            case 180:
                            case 182:
                            case 184:
                            case 186:
                            case 187:
                            case 188:
                            case 189:
                            case 190:
                            case 192:
                            case 193:
                            case 194:
                            case 195:
                            case 196:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256:
                                return 3;
                            case 175:
                            case 177:
                            case 179:
                            case 181:
                            case 183:
                            case 185:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384:
                                return 4;
                            default:
                                switch (i) {
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                        break;
                                    default:
                                        switch (i) {
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                break;
                                            default:
                                                switch (i) {
                                                    case 59:
                                                    case 60:
                                                    case 61:
                                                    case 62:
                                                    case 63:
                                                    case 64:
                                                        return 3;
                                                    case 65:
                                                    case 66:
                                                    case 67:
                                                    case 68:
                                                    case 69:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 103:
                                                            case 104:
                                                            case 105:
                                                            case 106:
                                                            case 107:
                                                                return 3;
                                                            default:
                                                                switch (i) {
                                                                    case 132:
                                                                    case 133:
                                                                    case 134:
                                                                    case 135:
                                                                    case 136:
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case 138:
                                                                            case 139:
                                                                            case 140:
                                                                            case 141:
                                                                            case 142:
                                                                            case 143:
                                                                            case 144:
                                                                            case 145:
                                                                            case 146:
                                                                            case 147:
                                                                            case 148:
                                                                            case 149:
                                                                            case 150:
                                                                            case 151:
                                                                            case 152:
                                                                            case 153:
                                                                            case 154:
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case 156:
                                                                                    case 157:
                                                                                    case 158:
                                                                                    case 159:
                                                                                    case 160:
                                                                                    case 161:
                                                                                    case 162:
                                                                                    case 163:
                                                                                    case 164:
                                                                                    case 165:
                                                                                        return 0;
                                                                                    default:
                                                                                        throw new TlsFatalAlert(80);
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                    }
                }
            }
            return 2;
        }
        return 1;
    }

    public static ProtocolVersion getMinimumVersion(int i) {
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                break;
            default:
                switch (i) {
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                        break;
                    default:
                        switch (i) {
                            case 156:
                            case 157:
                            case 158:
                            case 159:
                            case 160:
                            case 161:
                            case 162:
                            case 163:
                            case 164:
                            case 165:
                                break;
                            default:
                                switch (i) {
                                    case 168:
                                    case 169:
                                    case 170:
                                    case 171:
                                    case 172:
                                    case 173:
                                        break;
                                    default:
                                        switch (i) {
                                            case 186:
                                            case 187:
                                            case 188:
                                            case 189:
                                            case 190:
                                            case 191:
                                            case 192:
                                            case 193:
                                            case 194:
                                            case 195:
                                            case 196:
                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256:
                                                break;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384:
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384:
                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB:
                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB:
                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB:
                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB:
                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB:
                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB:
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8:
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256:
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case 65280:
                                                                                    case 65281:
                                                                                    case 65282:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB:
                                                                                        break;
                                                                                    default:
                                                                                        return ProtocolVersion.SSLv3;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return ProtocolVersion.TLSv12;
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, EXT_signature_algorithms);
        if (extensionData == null) {
            return null;
        }
        return readSignatureAlgorithmsExtension(extensionData);
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) throws IOException {
        if (!isTLSv12(tlsContext)) {
            return null;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return signatureAndHashAlgorithm;
        }
        throw new TlsFatalAlert(80);
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s);
    }

    public static boolean hasSigningCapability(short s) {
        return s == 1 || s == 2 || s == 64;
    }

    static void hmac_hash(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr));
        int digestSize = digest.getDigestSize();
        int length = ((bArr3.length + digestSize) - 1) / digestSize;
        int macSize = hMac.getMacSize();
        byte[] bArr4 = new byte[macSize];
        byte[] bArr5 = new byte[hMac.getMacSize()];
        byte[] bArr6 = bArr2;
        int i = 0;
        while (i < length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, macSize);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            int i2 = digestSize * i;
            System.arraycopy(bArr5, 0, bArr3, i2, Math.min(digestSize, bArr3.length - i2));
            i++;
            bArr6 = bArr4;
        }
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    public static boolean isAEADCipherSuite(int i) throws IOException {
        return 2 == getCipherType(i);
    }

    public static boolean isBlockCipherSuite(int i) throws IOException {
        return 1 == getCipherType(i);
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i) throws IOException {
        return getCipherType(i) == 0;
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isValidCipherSuiteForVersion(int i, ProtocolVersion protocolVersion) {
        return getMinimumVersion(i).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isValidUint16(int i) {
        return (65535 & i) == i;
    }

    public static boolean isValidUint16(long j) {
        return (WebSocketProtocol.PAYLOAD_SHORT_MAX & j) == j;
    }

    public static boolean isValidUint24(int i) {
        return (16777215 & i) == i;
    }

    public static boolean isValidUint24(long j) {
        return (16777215 & j) == j;
    }

    public static boolean isValidUint32(long j) {
        return (4294967295L & j) == j;
    }

    public static boolean isValidUint48(long j) {
        return (281474976710655L & j) == j;
    }

    public static boolean isValidUint64(long j) {
        return true;
    }

    public static boolean isValidUint8(int i) {
        return (i & 255) == i;
    }

    public static boolean isValidUint8(long j) {
        return (255 & j) == j;
    }

    public static boolean isValidUint8(short s) {
        return (s & 255) == s;
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean z, InputStream inputStream) throws IOException {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int i = readUint16 / 2;
        Vector vector = new Vector(i);
        int i2 = 0;
        while (i2 < i) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (z || parse.getSignature() != 0) {
                vector.addElement(parse);
                i2++;
            } else {
                throw new TlsFatalAlert(47);
            }
        }
        return vector;
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject == null) {
            throw new TlsFatalAlert(50);
        } else if (aSN1InputStream.readObject() == null) {
            return readObject;
        } else {
            throw new TlsFatalAlert(50);
        }
    }

    public static byte[] readAllOrNothing(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public static ASN1Primitive readDERObject(byte[] bArr) throws IOException {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(readASN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            return readASN1Object;
        }
        throw new TlsFatalAlert(50);
    }

    public static void readFully(byte[] bArr, InputStream inputStream) throws IOException {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static byte[] readFully(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        if (i == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] readOpaque16(InputStream inputStream) throws IOException {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) throws IOException {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream) throws IOException {
        return readFully((int) readUint8(inputStream), inputStream);
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Vector parseSupportedSignatureAlgorithms = parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parseSupportedSignatureAlgorithms;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i) {
        return (bArr[i + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 8);
    }

    public static int[] readUint16Array(int i, InputStream inputStream) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i) {
        int i2 = i + 1;
        return (bArr[i2 + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((bArr[i2] & UByte.MAX_VALUE) << 8);
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 >= 0) {
            return ((long) (read4 | (read << 24) | (read2 << 16) | (read3 << 8))) & 4294967295L;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((long) ((bArr[i3 + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 24) | ((bArr[i2] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((bArr[i3] & UByte.MAX_VALUE) << 8))) & 4294967295L;
    }

    public static long readUint48(InputStream inputStream) throws IOException {
        return ((((long) readUint24(inputStream)) & 4294967295L) << 24) | (4294967295L & ((long) readUint24(inputStream)));
    }

    public static long readUint48(byte[] bArr, int i) {
        int readUint24 = readUint24(bArr, i);
        return (((long) readUint24(bArr, i + 3)) & 4294967295L) | ((((long) readUint24) & 4294967295L) << 24);
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i) {
        return (short) (bArr[i] & UByte.MAX_VALUE);
    }

    public static short[] readUint8Array(int i, InputStream inputStream) throws IOException {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readUint8(inputStream);
        }
        return sArr;
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i) throws IOException {
        return ProtocolVersion.get(bArr[i] & UByte.MAX_VALUE, bArr[i + 1] & UByte.MAX_VALUE);
    }

    public static int readVersionRaw(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readVersionRaw(byte[] bArr, int i) throws IOException {
        return bArr[i + 1] | (bArr[i] << 8);
    }

    static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i)).getHash();
                if (HashAlgorithm.isRecognized(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    static void validateKeyUsage(Certificate certificate, int i) throws IOException {
        KeyUsage fromExtensions;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (fromExtensions = KeyUsage.fromExtensions(extensions)) != null && (fromExtensions.getBytes()[0] & UByte.MAX_VALUE & i) != i) {
            throw new TlsFatalAlert(46);
        }
    }

    private static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        } else if (signatureAndHashAlgorithm != null) {
            if (signatureAndHashAlgorithm.getSignature() != 0) {
                int i = 0;
                while (i < vector.size()) {
                    SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
                    if (signatureAndHashAlgorithm2.getHash() != signatureAndHashAlgorithm.getHash() || signatureAndHashAlgorithm2.getSignature() != signatureAndHashAlgorithm.getSignature()) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
            throw new TlsFatalAlert(47);
        } else {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
    }

    public static void writeGMTUnixTime(byte[] bArr, int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i] = (byte) (currentTimeMillis >>> 24);
        bArr[i + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i + 3] = (byte) currentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeUint16(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    public static void writeUint16(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) throws IOException {
        for (int writeUint16 : iArr) {
            writeUint16(writeUint16, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i) throws IOException {
        for (int writeUint16 : iArr) {
            writeUint16(writeUint16, bArr, i);
            i += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i);
        writeUint16Array(iArr, bArr, i + 2);
    }

    public static void writeUint24(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void writeUint24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void writeUint32(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint32(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 24));
        bArr[i + 1] = (byte) ((int) (j >>> 16));
        bArr[i + 2] = (byte) ((int) (j >>> 8));
        bArr[i + 3] = (byte) ((int) j);
    }

    public static void writeUint48(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint48(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 40));
        bArr[i + 1] = (byte) ((int) (j >>> 32));
        bArr[i + 2] = (byte) ((int) (j >>> 24));
        bArr[i + 3] = (byte) ((int) (j >>> 16));
        bArr[i + 4] = (byte) ((int) (j >>> 8));
        bArr[i + 5] = (byte) ((int) j);
    }

    public static void writeUint64(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) ((int) (j >>> 56)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) j));
    }

    public static void writeUint64(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >>> 56));
        bArr[i + 1] = (byte) ((int) (j >>> 48));
        bArr[i + 2] = (byte) ((int) (j >>> 40));
        bArr[i + 3] = (byte) ((int) (j >>> 32));
        bArr[i + 4] = (byte) ((int) (j >>> 24));
        bArr[i + 5] = (byte) ((int) (j >>> 16));
        bArr[i + 6] = (byte) ((int) (j >>> 8));
        bArr[i + 7] = (byte) ((int) j);
    }

    public static void writeUint8(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i);
    }

    public static void writeUint8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    public static void writeUint8(short s, OutputStream outputStream) throws IOException {
        outputStream.write(s);
    }

    public static void writeUint8(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) throws IOException {
        for (short writeUint8 : sArr) {
            writeUint8(writeUint8, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i) throws IOException {
        for (short writeUint8 : sArr) {
            writeUint8(writeUint8, bArr, i);
            i++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i);
        writeUint8Array(sArr, bArr, i + 1);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.getMajorVersion();
        bArr[i + 1] = (byte) protocolVersion.getMinorVersion();
    }
}
