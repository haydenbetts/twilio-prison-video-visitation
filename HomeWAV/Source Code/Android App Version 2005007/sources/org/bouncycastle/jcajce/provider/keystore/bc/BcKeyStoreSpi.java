package org.bouncycastle.jcajce.provider.keystore.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.io.DigestInputStream;
import org.bouncycastle.crypto.io.DigestOutputStream;
import org.bouncycastle.crypto.io.MacInputStream;
import org.bouncycastle.crypto.io.MacOutputStream;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.util.io.TeeOutputStream;

public class BcKeyStoreSpi extends KeyStoreSpi implements BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    private static final String KEY_CIPHER = "PBEWithSHAAnd3-KeyTripleDES-CBC";
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    private static final int KEY_SALT_SIZE = 20;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final String STORE_CIPHER = "PBEWithSHAAndTwofish-CBC";
    private static final int STORE_SALT_SIZE = 20;
    private static final int STORE_VERSION = 2;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    protected SecureRandom random = new SecureRandom();
    protected Hashtable table = new Hashtable();
    protected int version;

    public static class BouncyCastleStore extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        public void engineLoad(InputStream inputStream, char[] cArr) throws IOException {
            this.table.clear();
            if (inputStream != null) {
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int readInt = dataInputStream.readInt();
                if (readInt == 2 || readInt == 0 || readInt == 1) {
                    int readInt2 = dataInputStream.readInt();
                    byte[] bArr = new byte[readInt2];
                    if (readInt2 == 20) {
                        dataInputStream.readFully(bArr);
                        int readInt3 = dataInputStream.readInt();
                        if (readInt3 < 0 || readInt3 > 4096) {
                            throw new IOException("Key store corrupted.");
                        }
                        CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, makePBECipher(readInt == 0 ? "OldPBEWithSHAAndTwofish-CBC" : BcKeyStoreSpi.STORE_CIPHER, 2, cArr, bArr, readInt3));
                        SHA1Digest sHA1Digest = new SHA1Digest();
                        loadStore(new DigestInputStream(cipherInputStream, sHA1Digest));
                        byte[] bArr2 = new byte[sHA1Digest.getDigestSize()];
                        sHA1Digest.doFinal(bArr2, 0);
                        byte[] bArr3 = new byte[sHA1Digest.getDigestSize()];
                        Streams.readFully(cipherInputStream, bArr3);
                        if (!Arrays.constantTimeAreEqual(bArr2, bArr3)) {
                            this.table.clear();
                            throw new IOException("KeyStore integrity check failed.");
                        }
                        return;
                    }
                    throw new IOException("Key store corrupted.");
                }
                throw new IOException("Wrong version of key store.");
            }
        }

        public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] bArr = new byte[20];
            int nextInt = (this.random.nextInt() & 1023) + 1024;
            this.random.nextBytes(bArr);
            dataOutputStream.writeInt(this.version);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, makePBECipher(BcKeyStoreSpi.STORE_CIPHER, 1, cArr, bArr, nextInt));
            DigestOutputStream digestOutputStream = new DigestOutputStream(new SHA1Digest());
            saveStore(new TeeOutputStream(cipherOutputStream, digestOutputStream));
            cipherOutputStream.write(digestOutputStream.getDigest());
            cipherOutputStream.close();
        }
    }

    public static class Std extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    private class StoreEntry {
        String alias;
        Certificate[] certChain;
        Date date;
        Object obj;
        int type;

        StoreEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws Exception {
            this.date = new Date();
            this.type = 4;
            this.alias = str;
            this.certChain = certificateArr;
            byte[] bArr = new byte[20];
            BcKeyStoreSpi.this.random.setSeed(System.currentTimeMillis());
            BcKeyStoreSpi.this.random.nextBytes(bArr);
            int nextInt = (BcKeyStoreSpi.this.random.nextInt() & 1023) + 1024;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, cArr, bArr, nextInt)));
            BcKeyStoreSpi.this.encodeKey(key, dataOutputStream2);
            dataOutputStream2.close();
            this.obj = byteArrayOutputStream.toByteArray();
        }

        StoreEntry(String str, Certificate certificate) {
            this.date = new Date();
            this.type = 1;
            this.alias = str;
            this.obj = certificate;
            this.certChain = null;
        }

        StoreEntry(String str, Date date2, int i, Object obj2) {
            this.date = new Date();
            this.alias = str;
            this.date = date2;
            this.type = i;
            this.obj = obj2;
        }

        StoreEntry(String str, Date date2, int i, Object obj2, Certificate[] certificateArr) {
            this.date = new Date();
            this.alias = str;
            this.date = date2;
            this.type = i;
            this.obj = obj2;
            this.certChain = certificateArr;
        }

        StoreEntry(String str, byte[] bArr, Certificate[] certificateArr) {
            this.date = new Date();
            this.type = 3;
            this.alias = str;
            this.obj = bArr;
            this.certChain = certificateArr;
        }

        /* access modifiers changed from: package-private */
        public String getAlias() {
            return this.alias;
        }

        /* access modifiers changed from: package-private */
        public Certificate[] getCertificateChain() {
            return this.certChain;
        }

        /* access modifiers changed from: package-private */
        public Date getDate() {
            return this.date;
        }

        /* access modifiers changed from: package-private */
        public Object getObject() {
            return this.obj;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:15|16|17|18|19|(2:23|24)(2:25|26)) */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r2 = new java.io.DataInputStream(new java.io.ByteArrayInputStream((byte[]) r10.obj));
            r1 = new byte[r2.readInt()];
            r2.readFully(r1);
            r9 = r2.readInt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            r2 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.access$100(r10.this$0, new java.io.DataInputStream(new javax.crypto.CipherInputStream(r2, r10.this$0.makePBECipher("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, r11, r1, r9))));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
            r7 = r1;
            r8 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r2 = new java.io.DataInputStream(new java.io.ByteArrayInputStream((byte[]) r10.obj));
            r1 = new byte[r2.readInt()];
            r2.readFully(r1);
            r9 = r2.readInt();
            r2 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.access$100(r10.this$0, new java.io.DataInputStream(new javax.crypto.CipherInputStream(r2, r10.this$0.makePBECipher("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, r11, r1, r9))));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c1, code lost:
            if (r2 != null) goto L_0x00c3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c3, code lost:
            r1 = new java.io.ByteArrayOutputStream();
            r9 = new java.io.DataOutputStream(r1);
            r9.writeInt(r7.length);
            r9.write(r7);
            r9.writeInt(r8);
            r11 = new java.io.DataOutputStream(new javax.crypto.CipherOutputStream(r9, r10.this$0.makePBECipher(org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.KEY_CIPHER, 1, r11, r7, r8)));
            org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.access$000(r10.this$0, r2, r11);
            r11.close();
            r10.obj = r1.toByteArray();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00f9, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ff, code lost:
            throw new java.security.UnrecoverableKeyException("no match");
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0087 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getObject(char[] r11) throws java.security.NoSuchAlgorithmException, java.security.UnrecoverableKeyException {
            /*
                r10 = this;
                java.lang.String r0 = "no match"
                if (r11 == 0) goto L_0x0007
                int r1 = r11.length
                if (r1 != 0) goto L_0x000e
            L_0x0007:
                java.lang.Object r1 = r10.obj
                boolean r2 = r1 instanceof java.security.Key
                if (r2 == 0) goto L_0x000e
                return r1
            L_0x000e:
                int r1 = r10.type
                r2 = 4
                if (r1 != r2) goto L_0x0106
                java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
                java.lang.Object r2 = r10.obj
                byte[] r2 = (byte[]) r2
                byte[] r2 = (byte[]) r2
                r1.<init>(r2)
                java.io.DataInputStream r2 = new java.io.DataInputStream
                r2.<init>(r1)
                int r1 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                byte[] r7 = new byte[r1]     // Catch:{ Exception -> 0x0100 }
                r2.readFully(r7)     // Catch:{ Exception -> 0x0100 }
                int r8 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r3 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                java.lang.String r4 = "PBEWithSHAAnd3-KeyTripleDES-CBC"
                r5 = 2
                r6 = r11
                javax.crypto.Cipher r1 = r3.makePBECipher(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0100 }
                javax.crypto.CipherInputStream r3 = new javax.crypto.CipherInputStream     // Catch:{ Exception -> 0x0100 }
                r3.<init>(r2, r1)     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r1 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x004b }
                java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x004b }
                r2.<init>(r3)     // Catch:{ Exception -> 0x004b }
                java.security.Key r11 = r1.decodeKey(r2)     // Catch:{ Exception -> 0x004b }
                return r11
            L_0x004b:
                java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0100 }
                java.lang.Object r2 = r10.obj     // Catch:{ Exception -> 0x0100 }
                byte[] r2 = (byte[]) r2     // Catch:{ Exception -> 0x0100 }
                byte[] r2 = (byte[]) r2     // Catch:{ Exception -> 0x0100 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0100 }
                java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0100 }
                r2.<init>(r1)     // Catch:{ Exception -> 0x0100 }
                int r1 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0100 }
                r2.readFully(r1)     // Catch:{ Exception -> 0x0100 }
                int r9 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r3 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                java.lang.String r4 = "BrokenPBEWithSHAAnd3-KeyTripleDES-CBC"
                r5 = 2
                r6 = r11
                r7 = r1
                r8 = r9
                javax.crypto.Cipher r3 = r3.makePBECipher(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0100 }
                javax.crypto.CipherInputStream r4 = new javax.crypto.CipherInputStream     // Catch:{ Exception -> 0x0100 }
                r4.<init>(r2, r3)     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r2 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0087 }
                java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0087 }
                r3.<init>(r4)     // Catch:{ Exception -> 0x0087 }
                java.security.Key r2 = r2.decodeKey(r3)     // Catch:{ Exception -> 0x0087 }
            L_0x0084:
                r7 = r1
                r8 = r9
                goto L_0x00c1
            L_0x0087:
                java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0100 }
                java.lang.Object r2 = r10.obj     // Catch:{ Exception -> 0x0100 }
                byte[] r2 = (byte[]) r2     // Catch:{ Exception -> 0x0100 }
                byte[] r2 = (byte[]) r2     // Catch:{ Exception -> 0x0100 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0100 }
                java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0100 }
                r2.<init>(r1)     // Catch:{ Exception -> 0x0100 }
                int r1 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0100 }
                r2.readFully(r1)     // Catch:{ Exception -> 0x0100 }
                int r9 = r2.readInt()     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r3 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                java.lang.String r4 = "OldPBEWithSHAAnd3-KeyTripleDES-CBC"
                r5 = 2
                r6 = r11
                r7 = r1
                r8 = r9
                javax.crypto.Cipher r3 = r3.makePBECipher(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0100 }
                javax.crypto.CipherInputStream r4 = new javax.crypto.CipherInputStream     // Catch:{ Exception -> 0x0100 }
                r4.<init>(r2, r3)     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r2 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0100 }
                r3.<init>(r4)     // Catch:{ Exception -> 0x0100 }
                java.security.Key r2 = r2.decodeKey(r3)     // Catch:{ Exception -> 0x0100 }
                goto L_0x0084
            L_0x00c1:
                if (r2 == 0) goto L_0x00fa
                java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0100 }
                r1.<init>()     // Catch:{ Exception -> 0x0100 }
                java.io.DataOutputStream r9 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0100 }
                r9.<init>(r1)     // Catch:{ Exception -> 0x0100 }
                int r3 = r7.length     // Catch:{ Exception -> 0x0100 }
                r9.writeInt(r3)     // Catch:{ Exception -> 0x0100 }
                r9.write(r7)     // Catch:{ Exception -> 0x0100 }
                r9.writeInt(r8)     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r3 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                java.lang.String r4 = "PBEWithSHAAnd3-KeyTripleDES-CBC"
                r5 = 1
                r6 = r11
                javax.crypto.Cipher r11 = r3.makePBECipher(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0100 }
                javax.crypto.CipherOutputStream r3 = new javax.crypto.CipherOutputStream     // Catch:{ Exception -> 0x0100 }
                r3.<init>(r9, r11)     // Catch:{ Exception -> 0x0100 }
                java.io.DataOutputStream r11 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0100 }
                r11.<init>(r3)     // Catch:{ Exception -> 0x0100 }
                org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi r3 = org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.this     // Catch:{ Exception -> 0x0100 }
                r3.encodeKey(r2, r11)     // Catch:{ Exception -> 0x0100 }
                r11.close()     // Catch:{ Exception -> 0x0100 }
                byte[] r11 = r1.toByteArray()     // Catch:{ Exception -> 0x0100 }
                r10.obj = r11     // Catch:{ Exception -> 0x0100 }
                return r2
            L_0x00fa:
                java.security.UnrecoverableKeyException r11 = new java.security.UnrecoverableKeyException     // Catch:{ Exception -> 0x0100 }
                r11.<init>(r0)     // Catch:{ Exception -> 0x0100 }
                throw r11     // Catch:{ Exception -> 0x0100 }
            L_0x0100:
                java.security.UnrecoverableKeyException r11 = new java.security.UnrecoverableKeyException
                r11.<init>(r0)
                throw r11
            L_0x0106:
                java.lang.RuntimeException r11 = new java.lang.RuntimeException
                java.lang.String r0 = "forget something!"
                r11.<init>(r0)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi.StoreEntry.getObject(char[]):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public int getType() {
            return this.type;
        }
    }

    public static class Version1 extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }

    public BcKeyStoreSpi(int i) {
        this.version = i;
    }

    private Certificate decodeCertificate(DataInputStream dataInputStream) throws IOException {
        String readUTF = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        try {
            return this.helper.createCertificateFactory(readUTF).generateCertificate(new ByteArrayInputStream(bArr));
        } catch (NoSuchProviderException e) {
            throw new IOException(e.toString());
        } catch (CertificateException e2) {
            throw new IOException(e2.toString());
        }
    }

    /* access modifiers changed from: private */
    public Key decodeKey(DataInputStream dataInputStream) throws IOException {
        KeySpec keySpec;
        int read = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        String readUTF2 = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        if (readUTF.equals("PKCS#8") || readUTF.equals("PKCS8")) {
            keySpec = new PKCS8EncodedKeySpec(bArr);
        } else if (readUTF.equals("X.509") || readUTF.equals("X509")) {
            keySpec = new X509EncodedKeySpec(bArr);
        } else if (readUTF.equals("RAW")) {
            return new SecretKeySpec(bArr, readUTF2);
        } else {
            throw new IOException("Key format " + readUTF + " not recognised!");
        }
        if (read == 0) {
            return this.helper.createKeyFactory(readUTF2).generatePrivate(keySpec);
        }
        if (read == 1) {
            return this.helper.createKeyFactory(readUTF2).generatePublic(keySpec);
        }
        if (read == 2) {
            try {
                return this.helper.createSecretKeyFactory(readUTF2).generateSecret(keySpec);
            } catch (Exception e) {
                throw new IOException("Exception creating key: " + e.toString());
            }
        } else {
            throw new IOException("Key type " + read + " not recognised!");
        }
    }

    private void encodeCertificate(Certificate certificate, DataOutputStream dataOutputStream) throws IOException {
        try {
            byte[] encoded = certificate.getEncoded();
            dataOutputStream.writeUTF(certificate.getType());
            dataOutputStream.writeInt(encoded.length);
            dataOutputStream.write(encoded);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void encodeKey(Key key, DataOutputStream dataOutputStream) throws IOException {
        byte[] encoded = key.getEncoded();
        dataOutputStream.write(key instanceof PrivateKey ? 0 : key instanceof PublicKey ? 1 : 2);
        dataOutputStream.writeUTF(key.getFormat());
        dataOutputStream.writeUTF(key.getAlgorithm());
        dataOutputStream.writeInt(encoded.length);
        dataOutputStream.write(encoded);
    }

    static Provider getBouncyCastleProvider() {
        return Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null ? Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) : new BouncyCastleProvider();
    }

    public Enumeration engineAliases() {
        return this.table.keys();
    }

    public boolean engineContainsAlias(String str) {
        return this.table.get(str) != null;
    }

    public void engineDeleteEntry(String str) throws KeyStoreException {
        if (this.table.get(str) != null) {
            this.table.remove(str);
        }
    }

    public Certificate engineGetCertificate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry == null) {
            return null;
        }
        if (storeEntry.getType() == 1) {
            return (Certificate) storeEntry.getObject();
        }
        Certificate[] certificateChain = storeEntry.getCertificateChain();
        if (certificateChain != null) {
            return certificateChain[0];
        }
        return null;
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.table.elements();
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            if (!(storeEntry.getObject() instanceof Certificate)) {
                Certificate[] certificateChain = storeEntry.getCertificateChain();
                if (certificateChain != null && certificateChain[0].equals(certificate)) {
                    return storeEntry.getAlias();
                }
            } else if (((Certificate) storeEntry.getObject()).equals(certificate)) {
                return storeEntry.getAlias();
            }
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry != null) {
            return storeEntry.getCertificateChain();
        }
        return null;
    }

    public Date engineGetCreationDate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry != null) {
            return storeEntry.getDate();
        }
        return null;
    }

    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry == null || storeEntry.getType() == 1) {
            return null;
        }
        return (Key) storeEntry.getObject(cArr);
    }

    public boolean engineIsCertificateEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        return storeEntry != null && storeEntry.getType() == 1;
    }

    public boolean engineIsKeyEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        return (storeEntry == null || storeEntry.getType() == 1) ? false : true;
    }

    public void engineLoad(InputStream inputStream, char[] cArr) throws IOException {
        this.table.clear();
        if (inputStream != null) {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int readInt = dataInputStream.readInt();
            if (readInt == 2 || readInt == 0 || readInt == 1) {
                int readInt2 = dataInputStream.readInt();
                if (readInt2 > 0) {
                    byte[] bArr = new byte[readInt2];
                    dataInputStream.readFully(bArr);
                    int readInt3 = dataInputStream.readInt();
                    HMac hMac = new HMac(new SHA1Digest());
                    if (cArr == null || cArr.length == 0) {
                        loadStore(dataInputStream);
                        dataInputStream.readFully(new byte[hMac.getMacSize()]);
                        return;
                    }
                    byte[] PKCS12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
                    PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
                    pKCS12ParametersGenerator.init(PKCS12PasswordToBytes, bArr, readInt3);
                    CipherParameters generateDerivedMacParameters = pKCS12ParametersGenerator.generateDerivedMacParameters(readInt != 2 ? hMac.getMacSize() : hMac.getMacSize() * 8);
                    Arrays.fill(PKCS12PasswordToBytes, (byte) 0);
                    hMac.init(generateDerivedMacParameters);
                    loadStore(new MacInputStream(dataInputStream, hMac));
                    byte[] bArr2 = new byte[hMac.getMacSize()];
                    hMac.doFinal(bArr2, 0);
                    byte[] bArr3 = new byte[hMac.getMacSize()];
                    dataInputStream.readFully(bArr3);
                    if (!Arrays.constantTimeAreEqual(bArr2, bArr3)) {
                        this.table.clear();
                        throw new IOException("KeyStore integrity check failed.");
                    }
                    return;
                }
                throw new IOException("Invalid salt detected");
            }
            throw new IOException("Wrong version of key store.");
        }
    }

    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry == null || storeEntry.getType() == 1) {
            this.table.put(str, new StoreEntry(str, certificate));
            return;
        }
        throw new KeyStoreException("key store already has a key entry with alias " + str);
    }

    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        if (!(key instanceof PrivateKey) || certificateArr != null) {
            try {
                this.table.put(str, new StoreEntry(str, key, cArr, certificateArr));
            } catch (Exception e) {
                throw new KeyStoreException(e.toString());
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        this.table.put(str, new StoreEntry(str, bArr, certificateArr));
    }

    public int engineSize() {
        return this.table.size();
    }

    public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bArr = new byte[20];
        int nextInt = (this.random.nextInt() & 1023) + 1024;
        this.random.nextBytes(bArr);
        dataOutputStream.writeInt(this.version);
        dataOutputStream.writeInt(20);
        dataOutputStream.write(bArr);
        dataOutputStream.writeInt(nextInt);
        HMac hMac = new HMac(new SHA1Digest());
        MacOutputStream macOutputStream = new MacOutputStream(hMac);
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
        byte[] PKCS12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        pKCS12ParametersGenerator.init(PKCS12PasswordToBytes, bArr, nextInt);
        hMac.init(pKCS12ParametersGenerator.generateDerivedMacParameters(this.version < 2 ? hMac.getMacSize() : hMac.getMacSize() * 8));
        for (int i = 0; i != PKCS12PasswordToBytes.length; i++) {
            PKCS12PasswordToBytes[i] = 0;
        }
        saveStore(new TeeOutputStream(dataOutputStream, macOutputStream));
        byte[] bArr2 = new byte[hMac.getMacSize()];
        hMac.doFinal(bArr2, 0);
        dataOutputStream.write(bArr2);
        dataOutputStream.close();
    }

    /* access modifiers changed from: protected */
    public void loadStore(InputStream inputStream) throws IOException {
        StoreEntry storeEntry;
        Hashtable hashtable;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int read = dataInputStream.read(); read > 0; read = dataInputStream.read()) {
            String readUTF = dataInputStream.readUTF();
            Date date = new Date(dataInputStream.readLong());
            int readInt = dataInputStream.readInt();
            Certificate[] certificateArr = null;
            if (readInt != 0) {
                certificateArr = new Certificate[readInt];
                for (int i = 0; i != readInt; i++) {
                    certificateArr[i] = decodeCertificate(dataInputStream);
                }
            }
            Certificate[] certificateArr2 = certificateArr;
            if (read == 1) {
                Certificate decodeCertificate = decodeCertificate(dataInputStream);
                hashtable = this.table;
                storeEntry = new StoreEntry(readUTF, date, 1, (Object) decodeCertificate);
            } else if (read == 2) {
                Key decodeKey = decodeKey(dataInputStream);
                hashtable = this.table;
                storeEntry = new StoreEntry(readUTF, date, 2, decodeKey, certificateArr2);
            } else if (read == 3 || read == 4) {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr);
                this.table.put(readUTF, new StoreEntry(readUTF, date, read, bArr, certificateArr2));
            } else {
                throw new RuntimeException("Unknown object type in store.");
            }
            hashtable.put(readUTF, storeEntry);
        }
    }

    /* access modifiers changed from: protected */
    public Cipher makePBECipher(String str, int i, char[] cArr, byte[] bArr, int i2) throws IOException {
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
            SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i2);
            Cipher createCipher = this.helper.createCipher(str);
            createCipher.init(i, createSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return createCipher;
        } catch (Exception e) {
            throw new IOException("Error initialising store of key store: " + e);
        }
    }

    /* access modifiers changed from: protected */
    public void saveStore(OutputStream outputStream) throws IOException {
        Enumeration elements = this.table.elements();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        while (true) {
            if (elements.hasMoreElements()) {
                StoreEntry storeEntry = (StoreEntry) elements.nextElement();
                dataOutputStream.write(storeEntry.getType());
                dataOutputStream.writeUTF(storeEntry.getAlias());
                dataOutputStream.writeLong(storeEntry.getDate().getTime());
                Certificate[] certificateChain = storeEntry.getCertificateChain();
                if (certificateChain == null) {
                    dataOutputStream.writeInt(0);
                } else {
                    dataOutputStream.writeInt(certificateChain.length);
                    for (int i = 0; i != certificateChain.length; i++) {
                        encodeCertificate(certificateChain[i], dataOutputStream);
                    }
                }
                int type = storeEntry.getType();
                if (type == 1) {
                    encodeCertificate((Certificate) storeEntry.getObject(), dataOutputStream);
                } else if (type == 2) {
                    encodeKey((Key) storeEntry.getObject(), dataOutputStream);
                } else if (type == 3 || type == 4) {
                    byte[] bArr = (byte[]) storeEntry.getObject();
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                } else {
                    throw new RuntimeException("Unknown object type in store.");
                }
            } else {
                dataOutputStream.write(0);
                return;
            }
        }
    }

    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }
}
