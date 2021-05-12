package org.bouncycastle.jcajce.provider.keystore.pkcs12;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12StoreParameter;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JDKPKCS12StoreParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;

public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private ASN1ObjectIdentifier keyAlgorithm;
    private Hashtable keyCerts = new Hashtable();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private Hashtable localIds = new Hashtable();
    protected SecureRandom random = new SecureRandom();

    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class BCPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private class CertId {
        byte[] id;

        CertId(PublicKey publicKey) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(publicKey).getKeyIdentifier();
        }

        CertId(byte[] bArr) {
            this.id = bArr;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertId)) {
                return false;
            }
            return Arrays.areEqual(this.id, ((CertId) obj).id);
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }
    }

    public static class DefPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore() {
            super((Provider) null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class DefPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super((Provider) null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            HashMap hashMap = new HashMap();
            hashMap.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            hashMap.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            hashMap.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            hashMap.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            hashMap.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(hashMap);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer num = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
    }

    private static class IgnoresCaseHashtable {
        private Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public Enumeration elements() {
            return this.orig.elements();
        }

        public Object get(String str) {
            String str2 = (String) this.keys.get(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.get(str2);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public void put(String str, Object obj) {
            String lowerCase = str == null ? null : Strings.toLowerCase(str);
            String str2 = (String) this.keys.get(lowerCase);
            if (str2 != null) {
                this.orig.remove(str2);
            }
            this.keys.put(lowerCase, str);
            this.orig.put(str, obj);
        }

        public Object remove(String str) {
            String str2 = (String) this.keys.remove(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.remove(str2);
        }
    }

    public PKCS12KeyStoreSpi(Provider provider, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        CertificateFactory instance;
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.certAlgorithm = aSN1ObjectIdentifier2;
        if (provider != null) {
            try {
                instance = CertificateFactory.getInstance("X.509", provider);
            } catch (Exception e) {
                throw new IllegalArgumentException("can't create cert factory - " + e.toString());
            }
        } else {
            instance = CertificateFactory.getInstance("X.509");
        }
        this.certFact = instance;
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, int i, char[] cArr, boolean z, byte[] bArr2) throws Exception {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        Mac createMac = this.helper.createMac(aSN1ObjectIdentifier.getId());
        createMac.init(new PKCS12Key(cArr, z), pBEParameterSpec);
        createMac.update(bArr2);
        return createMac.doFinal();
    }

    private Cipher createCipher(int i, char[] cArr, AlgorithmIdentifier algorithmIdentifier) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException {
        SecretKey secretKey;
        AlgorithmParameterSpec algorithmParameterSpec;
        PBES2Parameters instance = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        PBKDF2Params instance2 = PBKDF2Params.getInstance(instance.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier instance3 = AlgorithmIdentifier.getInstance(instance.getEncryptionScheme());
        SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(instance.getKeyDerivationFunc().getAlgorithm().getId());
        if (instance2.isDefaultPrf()) {
            secretKey = createSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, instance2.getSalt(), instance2.getIterationCount().intValue(), keySizeProvider.getKeySize(instance3)));
        } else {
            secretKey = createSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, instance2.getSalt(), instance2.getIterationCount().intValue(), keySizeProvider.getKeySize(instance3), instance2.getPrf()));
        }
        Cipher instance4 = Cipher.getInstance(instance.getEncryptionScheme().getAlgorithm().getId());
        AlgorithmIdentifier.getInstance(instance.getEncryptionScheme());
        ASN1Encodable parameters = instance.getEncryptionScheme().getParameters();
        if (parameters instanceof ASN1OctetString) {
            algorithmParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
        } else {
            GOST28147Parameters instance5 = GOST28147Parameters.getInstance(parameters);
            algorithmParameterSpec = new GOST28147ParameterSpec(instance5.getEncryptionParamSet(), instance5.getIV());
        }
        instance4.init(i, secretKey, algorithmParameterSpec);
        return instance4;
    }

    /* access modifiers changed from: private */
    public SubjectKeyIdentifier createSubjectKeyId(PublicKey publicKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())));
        } catch (Exception unused) {
            throw new RuntimeException("error creating key");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x01b3 A[Catch:{ CertificateEncodingException -> 0x025b }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01cb A[Catch:{ CertificateEncodingException -> 0x025b }, LOOP:3: B:38:0x01c5->B:40:0x01cb, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doStore(java.io.OutputStream r19, char[] r20, boolean r21) throws java.io.IOException {
        /*
            r18 = this;
            r8 = r18
            r0 = r19
            r7 = r20
            java.lang.String r1 = "No password supplied for PKCS#12 KeyStore."
            java.util.Objects.requireNonNull(r7, r1)
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r2 = r8.keys
            java.util.Enumeration r2 = r2.keys()
        L_0x0016:
            boolean r3 = r2.hasMoreElements()
            r9 = 1024(0x400, float:1.435E-42)
            r10 = 20
            if (r3 == 0) goto L_0x0122
            byte[] r3 = new byte[r10]
            java.security.SecureRandom r4 = r8.random
            r4.nextBytes(r3)
            java.lang.Object r4 = r2.nextElement()
            java.lang.String r4 = (java.lang.String) r4
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r5 = r8.keys
            java.lang.Object r5 = r5.get(r4)
            java.security.PrivateKey r5 = (java.security.PrivateKey) r5
            org.bouncycastle.asn1.pkcs.PKCS12PBEParams r6 = new org.bouncycastle.asn1.pkcs.PKCS12PBEParams
            r6.<init>(r3, r9)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r8.keyAlgorithm
            java.lang.String r3 = r3.getId()
            byte[] r3 = r8.wrapKey(r3, r5, r6, r7)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r9 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = r8.keyAlgorithm
            org.bouncycastle.asn1.ASN1Primitive r6 = r6.toASN1Primitive()
            r9.<init>(r10, r6)
            org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo r6 = new org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo
            r6.<init>(r9, r3)
            org.bouncycastle.asn1.ASN1EncodableVector r3 = new org.bouncycastle.asn1.ASN1EncodableVector
            r3.<init>()
            boolean r9 = r5 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
            if (r9 == 0) goto L_0x00c5
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r5 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r5
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = pkcs_9_at_friendlyName
            org.bouncycastle.asn1.ASN1Encodable r9 = r5.getBagAttribute(r9)
            org.bouncycastle.asn1.DERBMPString r9 = (org.bouncycastle.asn1.DERBMPString) r9
            if (r9 == 0) goto L_0x0073
            java.lang.String r9 = r9.getString()
            boolean r9 = r9.equals(r4)
            if (r9 != 0) goto L_0x007d
        L_0x0073:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = pkcs_9_at_friendlyName
            org.bouncycastle.asn1.DERBMPString r10 = new org.bouncycastle.asn1.DERBMPString
            r10.<init>((java.lang.String) r4)
            r5.setBagAttribute(r9, r10)
        L_0x007d:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = pkcs_9_at_localKeyId
            org.bouncycastle.asn1.ASN1Encodable r9 = r5.getBagAttribute(r9)
            if (r9 != 0) goto L_0x0096
            java.security.cert.Certificate r9 = r8.engineGetCertificate(r4)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = pkcs_9_at_localKeyId
            java.security.PublicKey r9 = r9.getPublicKey()
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r9 = r8.createSubjectKeyId(r9)
            r5.setBagAttribute(r10, r9)
        L_0x0096:
            java.util.Enumeration r9 = r5.getBagAttributeKeys()
            r12 = 0
        L_0x009b:
            boolean r10 = r9.hasMoreElements()
            if (r10 == 0) goto L_0x00c6
            java.lang.Object r10 = r9.nextElement()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r10
            org.bouncycastle.asn1.ASN1EncodableVector r12 = new org.bouncycastle.asn1.ASN1EncodableVector
            r12.<init>()
            r12.add(r10)
            org.bouncycastle.asn1.DERSet r13 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.ASN1Encodable r10 = r5.getBagAttribute(r10)
            r13.<init>((org.bouncycastle.asn1.ASN1Encodable) r10)
            r12.add(r13)
            org.bouncycastle.asn1.DERSequence r10 = new org.bouncycastle.asn1.DERSequence
            r10.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r12)
            r3.add(r10)
            r12 = 1
            goto L_0x009b
        L_0x00c5:
            r12 = 0
        L_0x00c6:
            if (r12 != 0) goto L_0x010d
            org.bouncycastle.asn1.ASN1EncodableVector r5 = new org.bouncycastle.asn1.ASN1EncodableVector
            r5.<init>()
            java.security.cert.Certificate r9 = r8.engineGetCertificate(r4)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = pkcs_9_at_localKeyId
            r5.add(r10)
            org.bouncycastle.asn1.DERSet r10 = new org.bouncycastle.asn1.DERSet
            java.security.PublicKey r9 = r9.getPublicKey()
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r9 = r8.createSubjectKeyId(r9)
            r10.<init>((org.bouncycastle.asn1.ASN1Encodable) r9)
            r5.add(r10)
            org.bouncycastle.asn1.DERSequence r9 = new org.bouncycastle.asn1.DERSequence
            r9.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r5)
            r3.add(r9)
            org.bouncycastle.asn1.ASN1EncodableVector r5 = new org.bouncycastle.asn1.ASN1EncodableVector
            r5.<init>()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = pkcs_9_at_friendlyName
            r5.add(r9)
            org.bouncycastle.asn1.DERSet r9 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.DERBMPString r10 = new org.bouncycastle.asn1.DERBMPString
            r10.<init>((java.lang.String) r4)
            r9.<init>((org.bouncycastle.asn1.ASN1Encodable) r10)
            r5.add(r9)
            org.bouncycastle.asn1.DERSequence r4 = new org.bouncycastle.asn1.DERSequence
            r4.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r5)
            r3.add(r4)
        L_0x010d:
            org.bouncycastle.asn1.pkcs.SafeBag r4 = new org.bouncycastle.asn1.pkcs.SafeBag
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs8ShroudedKeyBag
            org.bouncycastle.asn1.ASN1Primitive r6 = r6.toASN1Primitive()
            org.bouncycastle.asn1.DERSet r9 = new org.bouncycastle.asn1.DERSet
            r9.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r3)
            r4.<init>(r5, r6, r9)
            r1.add(r4)
            goto L_0x0016
        L_0x0122:
            org.bouncycastle.asn1.DERSequence r2 = new org.bouncycastle.asn1.DERSequence
            r2.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r1)
            java.lang.String r1 = "DER"
            byte[] r2 = r2.getEncoded(r1)
            org.bouncycastle.asn1.BEROctetString r13 = new org.bouncycastle.asn1.BEROctetString
            r13.<init>((byte[]) r2)
            byte[] r2 = new byte[r10]
            java.security.SecureRandom r3 = r8.random
            r3.nextBytes(r2)
            org.bouncycastle.asn1.ASN1EncodableVector r3 = new org.bouncycastle.asn1.ASN1EncodableVector
            r3.<init>()
            org.bouncycastle.asn1.pkcs.PKCS12PBEParams r4 = new org.bouncycastle.asn1.pkcs.PKCS12PBEParams
            r4.<init>(r2, r9)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r14 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r8.certAlgorithm
            org.bouncycastle.asn1.ASN1Primitive r4 = r4.toASN1Primitive()
            r14.<init>(r2, r4)
            java.util.Hashtable r2 = new java.util.Hashtable
            r2.<init>()
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r8.keys
            java.util.Enumeration r4 = r4.keys()
        L_0x0159:
            boolean r5 = r4.hasMoreElements()
            java.lang.String r6 = "Error encoding certificate: "
            if (r5 == 0) goto L_0x0275
            java.lang.Object r5 = r4.nextElement()     // Catch:{ CertificateEncodingException -> 0x025b }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ CertificateEncodingException -> 0x025b }
            java.security.cert.Certificate r15 = r8.engineGetCertificate(r5)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.pkcs.CertBag r9 = new org.bouncycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DEROctetString r11 = new org.bouncycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x025b }
            byte[] r12 = r15.getEncoded()     // Catch:{ CertificateEncodingException -> 0x025b }
            r11.<init>((byte[]) r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            r9.<init>(r10, r11)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1EncodableVector r10 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x025b }
            r10.<init>()     // Catch:{ CertificateEncodingException -> 0x025b }
            boolean r11 = r15 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x025b }
            if (r11 == 0) goto L_0x01f5
            r11 = r15
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r11 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r11     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1Encodable r12 = r11.getBagAttribute(r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERBMPString r12 = (org.bouncycastle.asn1.DERBMPString) r12     // Catch:{ CertificateEncodingException -> 0x025b }
            if (r12 == 0) goto L_0x019f
            java.lang.String r12 = r12.getString()     // Catch:{ CertificateEncodingException -> 0x025b }
            boolean r12 = r12.equals(r5)     // Catch:{ CertificateEncodingException -> 0x025b }
            if (r12 != 0) goto L_0x019c
            goto L_0x019f
        L_0x019c:
            r16 = r4
            goto L_0x01ab
        L_0x019f:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x025b }
            r16 = r4
            org.bouncycastle.asn1.DERBMPString r4 = new org.bouncycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.<init>((java.lang.String) r5)     // Catch:{ CertificateEncodingException -> 0x025b }
            r11.setBagAttribute(r12, r4)     // Catch:{ CertificateEncodingException -> 0x025b }
        L_0x01ab:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1Encodable r4 = r11.getBagAttribute(r4)     // Catch:{ CertificateEncodingException -> 0x025b }
            if (r4 != 0) goto L_0x01c0
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x025b }
            java.security.PublicKey r12 = r15.getPublicKey()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r12 = r8.createSubjectKeyId(r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            r11.setBagAttribute(r4, r12)     // Catch:{ CertificateEncodingException -> 0x025b }
        L_0x01c0:
            java.util.Enumeration r4 = r11.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x025b }
            r12 = 0
        L_0x01c5:
            boolean r17 = r4.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x025b }
            if (r17 == 0) goto L_0x01f8
            java.lang.Object r12 = r4.nextElement()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r12     // Catch:{ CertificateEncodingException -> 0x025b }
            r17 = r4
            org.bouncycastle.asn1.ASN1EncodableVector r4 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.<init>()     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSet r7 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1Encodable r12 = r11.getBagAttribute(r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            r7.<init>((org.bouncycastle.asn1.ASN1Encodable) r12)     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSequence r7 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x025b }
            r7.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r4)     // Catch:{ CertificateEncodingException -> 0x025b }
            r10.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            r7 = r20
            r4 = r17
            r12 = 1
            goto L_0x01c5
        L_0x01f5:
            r16 = r4
            r12 = 0
        L_0x01f8:
            if (r12 != 0) goto L_0x023b
            org.bouncycastle.asn1.ASN1EncodableVector r4 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.<init>()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSet r7 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x025b }
            java.security.PublicKey r11 = r15.getPublicKey()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r11 = r8.createSubjectKeyId(r11)     // Catch:{ CertificateEncodingException -> 0x025b }
            r7.<init>((org.bouncycastle.asn1.ASN1Encodable) r11)     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSequence r7 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x025b }
            r7.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r4)     // Catch:{ CertificateEncodingException -> 0x025b }
            r10.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1EncodableVector r4 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.<init>()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSet r7 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERBMPString r11 = new org.bouncycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x025b }
            r11.<init>((java.lang.String) r5)     // Catch:{ CertificateEncodingException -> 0x025b }
            r7.<init>((org.bouncycastle.asn1.ASN1Encodable) r11)     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.add(r7)     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSequence r5 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x025b }
            r5.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r4)     // Catch:{ CertificateEncodingException -> 0x025b }
            r10.add(r5)     // Catch:{ CertificateEncodingException -> 0x025b }
        L_0x023b:
            org.bouncycastle.asn1.pkcs.SafeBag r4 = new org.bouncycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = certBag     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.ASN1Primitive r7 = r9.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x025b }
            org.bouncycastle.asn1.DERSet r9 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x025b }
            r9.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r10)     // Catch:{ CertificateEncodingException -> 0x025b }
            r4.<init>(r5, r7, r9)     // Catch:{ CertificateEncodingException -> 0x025b }
            r3.add(r4)     // Catch:{ CertificateEncodingException -> 0x025b }
            r2.put(r15, r15)     // Catch:{ CertificateEncodingException -> 0x025b }
            r7 = r20
            r4 = r16
            r9 = 1024(0x400, float:1.435E-42)
            r10 = 20
            goto L_0x0159
        L_0x025b:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0275:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r8.certs
            java.util.Enumeration r4 = r4.keys()
        L_0x027b:
            boolean r5 = r4.hasMoreElements()
            if (r5 == 0) goto L_0x0373
            java.lang.Object r5 = r4.nextElement()     // Catch:{ CertificateEncodingException -> 0x0359 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r7 = r8.certs     // Catch:{ CertificateEncodingException -> 0x0359 }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
            java.security.cert.Certificate r7 = (java.security.cert.Certificate) r7     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r9 = r8.keys     // Catch:{ CertificateEncodingException -> 0x0359 }
            java.lang.Object r9 = r9.get(r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r9 == 0) goto L_0x0298
            goto L_0x027b
        L_0x0298:
            org.bouncycastle.asn1.pkcs.CertBag r9 = new org.bouncycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DEROctetString r11 = new org.bouncycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x0359 }
            byte[] r12 = r7.getEncoded()     // Catch:{ CertificateEncodingException -> 0x0359 }
            r11.<init>((byte[]) r12)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r9.<init>(r10, r11)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1EncodableVector r10 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x0359 }
            r10.<init>()     // Catch:{ CertificateEncodingException -> 0x0359 }
            boolean r11 = r7 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r11 == 0) goto L_0x031b
            r11 = r7
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r11 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r11     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1Encodable r12 = r11.getBagAttribute(r12)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERBMPString r12 = (org.bouncycastle.asn1.DERBMPString) r12     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r12 == 0) goto L_0x02c8
            java.lang.String r12 = r12.getString()     // Catch:{ CertificateEncodingException -> 0x0359 }
            boolean r12 = r12.equals(r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r12 != 0) goto L_0x02d2
        L_0x02c8:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERBMPString r15 = new org.bouncycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x0359 }
            r15.<init>((java.lang.String) r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r11.setBagAttribute(r12, r15)     // Catch:{ CertificateEncodingException -> 0x0359 }
        L_0x02d2:
            java.util.Enumeration r12 = r11.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x0359 }
            r15 = 0
        L_0x02d7:
            boolean r16 = r12.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r16 == 0) goto L_0x0318
            java.lang.Object r16 = r12.nextElement()     // Catch:{ CertificateEncodingException -> 0x0359 }
            r17 = r4
            r4 = r16
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r4     // Catch:{ CertificateEncodingException -> 0x0359 }
            r16 = r12
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x0359 }
            boolean r12 = r4.equals(r12)     // Catch:{ CertificateEncodingException -> 0x0359 }
            if (r12 == 0) goto L_0x02f6
            r12 = r16
            r4 = r17
            goto L_0x02d7
        L_0x02f6:
            org.bouncycastle.asn1.ASN1EncodableVector r12 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x0359 }
            r12.<init>()     // Catch:{ CertificateEncodingException -> 0x0359 }
            r12.add(r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERSet r15 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1Encodable r4 = r11.getBagAttribute(r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r15.<init>((org.bouncycastle.asn1.ASN1Encodable) r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r12.add(r15)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERSequence r4 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r12)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r10.add(r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r12 = r16
            r4 = r17
            r15 = 1
            goto L_0x02d7
        L_0x0318:
            r17 = r4
            goto L_0x031e
        L_0x031b:
            r17 = r4
            r15 = 0
        L_0x031e:
            if (r15 != 0) goto L_0x033f
            org.bouncycastle.asn1.ASN1EncodableVector r4 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4.<init>()     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4.add(r11)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERSet r11 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERBMPString r12 = new org.bouncycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x0359 }
            r12.<init>((java.lang.String) r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r11.<init>((org.bouncycastle.asn1.ASN1Encodable) r12)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4.add(r11)     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERSequence r5 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x0359 }
            r5.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r10.add(r5)     // Catch:{ CertificateEncodingException -> 0x0359 }
        L_0x033f:
            org.bouncycastle.asn1.pkcs.SafeBag r4 = new org.bouncycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = certBag     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.ASN1Primitive r9 = r9.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x0359 }
            org.bouncycastle.asn1.DERSet r11 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x0359 }
            r11.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r10)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4.<init>(r5, r9, r11)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r3.add(r4)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r2.put(r7, r7)     // Catch:{ CertificateEncodingException -> 0x0359 }
            r4 = r17
            goto L_0x027b
        L_0x0359:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0373:
            java.util.Set r4 = r18.getUsedCertificateSet()
            java.util.Hashtable r5 = r8.chainCerts
            java.util.Enumeration r5 = r5.keys()
        L_0x037d:
            boolean r7 = r5.hasMoreElements()
            if (r7 == 0) goto L_0x0427
            java.lang.Object r7 = r5.nextElement()     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r7 = (org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.CertId) r7     // Catch:{ CertificateEncodingException -> 0x040d }
            java.util.Hashtable r9 = r8.chainCerts     // Catch:{ CertificateEncodingException -> 0x040d }
            java.lang.Object r7 = r9.get(r7)     // Catch:{ CertificateEncodingException -> 0x040d }
            java.security.cert.Certificate r7 = (java.security.cert.Certificate) r7     // Catch:{ CertificateEncodingException -> 0x040d }
            boolean r9 = r4.contains(r7)     // Catch:{ CertificateEncodingException -> 0x040d }
            if (r9 != 0) goto L_0x0398
            goto L_0x037d
        L_0x0398:
            java.lang.Object r9 = r2.get(r7)     // Catch:{ CertificateEncodingException -> 0x040d }
            if (r9 == 0) goto L_0x039f
            goto L_0x037d
        L_0x039f:
            org.bouncycastle.asn1.pkcs.CertBag r9 = new org.bouncycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.DEROctetString r11 = new org.bouncycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x040d }
            byte[] r12 = r7.getEncoded()     // Catch:{ CertificateEncodingException -> 0x040d }
            r11.<init>((byte[]) r12)     // Catch:{ CertificateEncodingException -> 0x040d }
            r9.<init>(r10, r11)     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1EncodableVector r10 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x040d }
            r10.<init>()     // Catch:{ CertificateEncodingException -> 0x040d }
            boolean r11 = r7 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x040d }
            if (r11 == 0) goto L_0x03f4
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r7 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r7     // Catch:{ CertificateEncodingException -> 0x040d }
            java.util.Enumeration r11 = r7.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x040d }
        L_0x03be:
            boolean r12 = r11.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x040d }
            if (r12 == 0) goto L_0x03f4
            java.lang.Object r12 = r11.nextElement()     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r12     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r15 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x040d }
            boolean r15 = r12.equals(r15)     // Catch:{ CertificateEncodingException -> 0x040d }
            if (r15 == 0) goto L_0x03d3
            goto L_0x03be
        L_0x03d3:
            org.bouncycastle.asn1.ASN1EncodableVector r15 = new org.bouncycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x040d }
            r15.<init>()     // Catch:{ CertificateEncodingException -> 0x040d }
            r15.add(r12)     // Catch:{ CertificateEncodingException -> 0x040d }
            r16 = r2
            org.bouncycastle.asn1.DERSet r2 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1Encodable r12 = r7.getBagAttribute(r12)     // Catch:{ CertificateEncodingException -> 0x040d }
            r2.<init>((org.bouncycastle.asn1.ASN1Encodable) r12)     // Catch:{ CertificateEncodingException -> 0x040d }
            r15.add(r2)     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.DERSequence r2 = new org.bouncycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x040d }
            r2.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r15)     // Catch:{ CertificateEncodingException -> 0x040d }
            r10.add(r2)     // Catch:{ CertificateEncodingException -> 0x040d }
            r2 = r16
            goto L_0x03be
        L_0x03f4:
            r16 = r2
            org.bouncycastle.asn1.pkcs.SafeBag r2 = new org.bouncycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = certBag     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.ASN1Primitive r9 = r9.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x040d }
            org.bouncycastle.asn1.DERSet r11 = new org.bouncycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x040d }
            r11.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r10)     // Catch:{ CertificateEncodingException -> 0x040d }
            r2.<init>(r7, r9, r11)     // Catch:{ CertificateEncodingException -> 0x040d }
            r3.add(r2)     // Catch:{ CertificateEncodingException -> 0x040d }
            r2 = r16
            goto L_0x037d
        L_0x040d:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0427:
            org.bouncycastle.asn1.DERSequence r2 = new org.bouncycastle.asn1.DERSequence
            r2.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r3)
            byte[] r6 = r2.getEncoded(r1)
            r2 = 1
            r5 = 0
            r1 = r18
            r3 = r14
            r4 = r20
            byte[] r1 = r1.cryptData(r2, r3, r4, r5, r6)
            org.bouncycastle.asn1.pkcs.EncryptedData r2 = new org.bouncycastle.asn1.pkcs.EncryptedData
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = data
            org.bouncycastle.asn1.BEROctetString r4 = new org.bouncycastle.asn1.BEROctetString
            r4.<init>((byte[]) r1)
            r2.<init>(r3, r14, r4)
            r1 = 2
            org.bouncycastle.asn1.pkcs.ContentInfo[] r1 = new org.bouncycastle.asn1.pkcs.ContentInfo[r1]
            org.bouncycastle.asn1.pkcs.ContentInfo r3 = new org.bouncycastle.asn1.pkcs.ContentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = data
            r3.<init>(r4, r13)
            r4 = 0
            r1[r4] = r3
            org.bouncycastle.asn1.pkcs.ContentInfo r3 = new org.bouncycastle.asn1.pkcs.ContentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = encryptedData
            org.bouncycastle.asn1.ASN1Primitive r2 = r2.toASN1Primitive()
            r3.<init>(r4, r2)
            r2 = 1
            r1[r2] = r3
            org.bouncycastle.asn1.pkcs.AuthenticatedSafe r2 = new org.bouncycastle.asn1.pkcs.AuthenticatedSafe
            r2.<init>((org.bouncycastle.asn1.pkcs.ContentInfo[]) r1)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            if (r21 == 0) goto L_0x0474
            org.bouncycastle.asn1.DEROutputStream r3 = new org.bouncycastle.asn1.DEROutputStream
            r3.<init>(r1)
            goto L_0x0479
        L_0x0474:
            org.bouncycastle.asn1.BEROutputStream r3 = new org.bouncycastle.asn1.BEROutputStream
            r3.<init>(r1)
        L_0x0479:
            r3.writeObject(r2)
            byte[] r1 = r1.toByteArray()
            org.bouncycastle.asn1.pkcs.ContentInfo r9 = new org.bouncycastle.asn1.pkcs.ContentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = data
            org.bouncycastle.asn1.BEROctetString r3 = new org.bouncycastle.asn1.BEROctetString
            r3.<init>((byte[]) r1)
            r9.<init>(r2, r3)
            r1 = 20
            byte[] r10 = new byte[r1]
            java.security.SecureRandom r1 = r8.random
            r1.nextBytes(r10)
            org.bouncycastle.asn1.ASN1Encodable r1 = r9.getContent()
            org.bouncycastle.asn1.ASN1OctetString r1 = (org.bouncycastle.asn1.ASN1OctetString) r1
            byte[] r7 = r1.getOctets()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = id_SHA1     // Catch:{ Exception -> 0x04d8 }
            r6 = 0
            r4 = 1024(0x400, float:1.435E-42)
            r1 = r18
            r3 = r10
            r5 = r20
            byte[] r1 = r1.calculatePbeMac(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = id_SHA1     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.DERNull r4 = org.bouncycastle.asn1.DERNull.INSTANCE     // Catch:{ Exception -> 0x04d8 }
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.x509.DigestInfo r3 = new org.bouncycastle.asn1.x509.DigestInfo     // Catch:{ Exception -> 0x04d8 }
            r3.<init>(r2, r1)     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.pkcs.MacData r1 = new org.bouncycastle.asn1.pkcs.MacData     // Catch:{ Exception -> 0x04d8 }
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r3, r10, r2)     // Catch:{ Exception -> 0x04d8 }
            org.bouncycastle.asn1.pkcs.Pfx r2 = new org.bouncycastle.asn1.pkcs.Pfx
            r2.<init>(r9, r1)
            if (r21 == 0) goto L_0x04cf
            org.bouncycastle.asn1.DEROutputStream r1 = new org.bouncycastle.asn1.DEROutputStream
            r1.<init>(r0)
            goto L_0x04d4
        L_0x04cf:
            org.bouncycastle.asn1.BEROutputStream r1 = new org.bouncycastle.asn1.BEROutputStream
            r1.<init>(r0)
        L_0x04d4:
            r1.writeObject(r2)
            return
        L_0x04d8:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "error constructing MAC: "
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.doStore(java.io.OutputStream, char[], boolean):void");
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        sHA1Digest.update(bytes, 0, bytes.length);
        sHA1Digest.doFinal(bArr, 0);
        return bArr;
    }

    private Set getUsedCertificateSet() {
        HashSet hashSet = new HashSet();
        Enumeration keys2 = this.keys.keys();
        while (keys2.hasMoreElements()) {
            Certificate[] engineGetCertificateChain = engineGetCertificateChain((String) keys2.nextElement());
            for (int i = 0; i != engineGetCertificateChain.length; i++) {
                hashSet.add(engineGetCertificateChain[i]);
            }
        }
        Enumeration keys3 = this.certs.keys();
        while (keys3.hasMoreElements()) {
            hashSet.add(engineGetCertificate((String) keys3.nextElement()));
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    public byte[] cryptData(boolean z, AlgorithmIdentifier algorithmIdentifier, char[] cArr, boolean z2, byte[] bArr) throws IOException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        int i = z ? 1 : 2;
        if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            new PBEKeySpec(cArr);
            try {
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), instance.getIterations().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArr, z2);
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(i, pKCS12Key, pBEParameterSpec);
                return createCipher.doFinal(bArr);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
            try {
                return createCipher(i, cArr, algorithmIdentifier).doFinal(bArr);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: " + algorithm);
        }
    }

    public Enumeration engineAliases() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.keys();
    }

    public boolean engineContainsAlias(String str) {
        return (this.certs.get(str) == null && this.keys.get(str) == null) ? false : true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.security.cert.Certificate} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineDeleteEntry(java.lang.String r6) throws java.security.KeyStoreException {
        /*
            r5 = this;
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r0 = r5.keys
            java.lang.Object r0 = r0.remove(r6)
            java.security.Key r0 = (java.security.Key) r0
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = r5.certs
            java.lang.Object r1 = r1.remove(r6)
            java.security.cert.Certificate r1 = (java.security.cert.Certificate) r1
            if (r1 == 0) goto L_0x0020
            java.util.Hashtable r2 = r5.chainCerts
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r3 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId
            java.security.PublicKey r4 = r1.getPublicKey()
            r3.<init>((java.security.PublicKey) r4)
            r2.remove(r3)
        L_0x0020:
            if (r0 == 0) goto L_0x0045
            java.util.Hashtable r0 = r5.localIds
            java.lang.Object r6 = r0.remove(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x0035
            java.util.Hashtable r0 = r5.keyCerts
            java.lang.Object r6 = r0.remove(r6)
            r1 = r6
            java.security.cert.Certificate r1 = (java.security.cert.Certificate) r1
        L_0x0035:
            if (r1 == 0) goto L_0x0045
            java.util.Hashtable r6 = r5.chainCerts
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r0 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId
            java.security.PublicKey r1 = r1.getPublicKey()
            r0.<init>((java.security.PublicKey) r1)
            r6.remove(r0)
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineDeleteEntry(java.lang.String):void");
    }

    public Certificate engineGetCertificate(String str) {
        if (str != null) {
            Certificate certificate = (Certificate) this.certs.get(str);
            if (certificate != null) {
                return certificate;
            }
            String str2 = (String) this.localIds.get(str);
            return (Certificate) (str2 != null ? this.keyCerts.get(str2) : this.keyCerts.get(str));
        }
        throw new IllegalArgumentException("null alias passed to getCertificate.");
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.certs.elements();
        Enumeration keys2 = this.certs.keys();
        while (elements.hasMoreElements()) {
            String str = (String) keys2.nextElement();
            if (((Certificate) elements.nextElement()).equals(certificate)) {
                return str;
            }
        }
        Enumeration elements2 = this.keyCerts.elements();
        Enumeration keys3 = this.keyCerts.keys();
        while (elements2.hasMoreElements()) {
            String str2 = (String) keys3.nextElement();
            if (((Certificate) elements2.nextElement()).equals(certificate)) {
                return str2;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.Certificate[] engineGetCertificateChain(java.lang.String r9) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x00c9
            boolean r0 = r8.engineIsKeyEntry(r9)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            java.security.cert.Certificate r9 = r8.engineGetCertificate(r9)
            if (r9 == 0) goto L_0x00c8
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
        L_0x0015:
            if (r9 == 0) goto L_0x00b4
            r2 = r9
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x509.Extension.authorityKeyIdentifier
            java.lang.String r3 = r3.getId()
            byte[] r3 = r2.getExtensionValue(r3)
            if (r3 == 0) goto L_0x0065
            org.bouncycastle.asn1.ASN1InputStream r4 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x005a }
            r4.<init>((byte[]) r3)     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.asn1.ASN1Primitive r3 = r4.readObject()     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.asn1.ASN1OctetString r3 = (org.bouncycastle.asn1.ASN1OctetString) r3     // Catch:{ IOException -> 0x005a }
            byte[] r3 = r3.getOctets()     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.asn1.ASN1InputStream r4 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x005a }
            r4.<init>((byte[]) r3)     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.asn1.ASN1Primitive r3 = r4.readObject()     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.asn1.x509.AuthorityKeyIdentifier r3 = org.bouncycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(r3)     // Catch:{ IOException -> 0x005a }
            byte[] r4 = r3.getKeyIdentifier()     // Catch:{ IOException -> 0x005a }
            if (r4 == 0) goto L_0x0065
            java.util.Hashtable r4 = r8.chainCerts     // Catch:{ IOException -> 0x005a }
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r5 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId     // Catch:{ IOException -> 0x005a }
            byte[] r3 = r3.getKeyIdentifier()     // Catch:{ IOException -> 0x005a }
            r5.<init>((byte[]) r3)     // Catch:{ IOException -> 0x005a }
            java.lang.Object r3 = r4.get(r5)     // Catch:{ IOException -> 0x005a }
            java.security.cert.Certificate r3 = (java.security.cert.Certificate) r3     // Catch:{ IOException -> 0x005a }
            goto L_0x0066
        L_0x005a:
            r9 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r9 = r9.toString()
            r0.<init>(r9)
            throw r0
        L_0x0065:
            r3 = r1
        L_0x0066:
            if (r3 != 0) goto L_0x00a3
            java.security.Principal r4 = r2.getIssuerDN()
            java.security.Principal r5 = r2.getSubjectDN()
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x00a3
            java.util.Hashtable r5 = r8.chainCerts
            java.util.Enumeration r5 = r5.keys()
        L_0x007c:
            boolean r6 = r5.hasMoreElements()
            if (r6 == 0) goto L_0x00a3
            java.util.Hashtable r6 = r8.chainCerts
            java.lang.Object r7 = r5.nextElement()
            java.lang.Object r6 = r6.get(r7)
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6
            java.security.Principal r7 = r6.getSubjectDN()
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x007c
            java.security.PublicKey r7 = r6.getPublicKey()     // Catch:{ Exception -> 0x00a1 }
            r2.verify(r7)     // Catch:{ Exception -> 0x00a1 }
            r3 = r6
            goto L_0x00a3
        L_0x00a1:
            goto L_0x007c
        L_0x00a3:
            boolean r2 = r0.contains(r9)
            if (r2 == 0) goto L_0x00ac
        L_0x00a9:
            r9 = r1
            goto L_0x0015
        L_0x00ac:
            r0.addElement(r9)
            if (r3 == r9) goto L_0x00a9
            r9 = r3
            goto L_0x0015
        L_0x00b4:
            int r9 = r0.size()
            java.security.cert.Certificate[] r1 = new java.security.cert.Certificate[r9]
            r2 = 0
        L_0x00bb:
            if (r2 == r9) goto L_0x00c8
            java.lang.Object r3 = r0.elementAt(r2)
            java.security.cert.Certificate r3 = (java.security.cert.Certificate) r3
            r1[r2] = r3
            int r2 = r2 + 1
            goto L_0x00bb
        L_0x00c8:
            return r1
        L_0x00c9:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "null alias passed to getCertificateChain."
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineGetCertificateChain(java.lang.String):java.security.cert.Certificate[]");
    }

    public Date engineGetCreationDate(String str) {
        Objects.requireNonNull(str, "alias == null");
        if (this.keys.get(str) == null && this.certs.get(str) == null) {
            return null;
        }
        return new Date();
    }

    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (str != null) {
            return (Key) this.keys.get(str);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    public boolean engineIsCertificateEntry(String str) {
        return this.certs.get(str) != null && this.keys.get(str) == null;
    }

    public boolean engineIsKeyEntry(String str) {
        return this.keys.get(str) != null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: org.bouncycastle.asn1.ASN1Primitive} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v10, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v11, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: org.bouncycastle.asn1.ASN1Primitive} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v12, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v33, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v13, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX WARNING: type inference failed for: r5v7, types: [org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Encodable, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v16, types: [org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Encodable, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0483  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x04a2  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ec  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineLoad(java.io.InputStream r21, char[] r22) throws java.io.IOException {
        /*
            r20 = this;
            r8 = r20
            r0 = r21
            r9 = r22
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "No password supplied for PKCS#12 KeyStore."
            java.util.Objects.requireNonNull(r9, r1)
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream
            r1.<init>(r0)
            r0 = 10
            r1.mark(r0)
            int r0 = r1.read()
            r2 = 48
            if (r0 != r2) goto L_0x05d2
            r1.reset()
            org.bouncycastle.asn1.ASN1InputStream r0 = new org.bouncycastle.asn1.ASN1InputStream
            r0.<init>((java.io.InputStream) r1)
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.readObject()
            org.bouncycastle.asn1.ASN1Sequence r0 = (org.bouncycastle.asn1.ASN1Sequence) r0
            org.bouncycastle.asn1.pkcs.Pfx r0 = org.bouncycastle.asn1.pkcs.Pfx.getInstance(r0)
            org.bouncycastle.asn1.pkcs.ContentInfo r10 = r0.getAuthSafe()
            java.util.Vector r11 = new java.util.Vector
            r11.<init>()
            org.bouncycastle.asn1.pkcs.MacData r1 = r0.getMacData()
            r12 = 1
            r13 = 0
            if (r1 == 0) goto L_0x00cc
            org.bouncycastle.asn1.pkcs.MacData r0 = r0.getMacData()
            org.bouncycastle.asn1.x509.DigestInfo r14 = r0.getMac()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r15 = r14.getAlgorithmId()
            byte[] r16 = r0.getSalt()
            java.math.BigInteger r0 = r0.getIterationCount()
            int r0 = r0.intValue()
            org.bouncycastle.asn1.ASN1Encodable r1 = r10.getContent()
            org.bouncycastle.asn1.ASN1OctetString r1 = (org.bouncycastle.asn1.ASN1OctetString) r1
            byte[] r17 = r1.getOctets()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r15.getAlgorithm()     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            r6 = 0
            r1 = r20
            r3 = r16
            r4 = r0
            r5 = r22
            r7 = r17
            byte[] r1 = r1.calculatePbeMac(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            byte[] r14 = r14.getDigest()     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            boolean r1 = org.bouncycastle.util.Arrays.constantTimeAreEqual(r1, r14)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            if (r1 != 0) goto L_0x00cc
            int r1 = r9.length     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            java.lang.String r7 = "PKCS12 key store mac invalid - wrong password or corrupted file."
            if (r1 > 0) goto L_0x00a7
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r15.getAlgorithm()     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            r6 = 1
            r1 = r20
            r3 = r16
            r4 = r0
            r5 = r22
            r0 = r7
            r7 = r17
            byte[] r1 = r1.calculatePbeMac(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            boolean r1 = org.bouncycastle.util.Arrays.constantTimeAreEqual(r1, r14)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            if (r1 == 0) goto L_0x00a1
            r0 = 1
            goto L_0x00cd
        L_0x00a1:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            r1.<init>(r0)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            throw r1     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
        L_0x00a7:
            r0 = r7
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            r1.<init>(r0)     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
            throw r1     // Catch:{ IOException -> 0x00ca, Exception -> 0x00ae }
        L_0x00ae:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "error constructing MAC: "
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00ca:
            r0 = move-exception
            throw r0
        L_0x00cc:
            r0 = 0
        L_0x00cd:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable
            r7 = 0
            r1.<init>()
            r8.keys = r1
            java.util.Hashtable r1 = new java.util.Hashtable
            r1.<init>()
            r8.localIds = r1
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r10.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = data
            boolean r1 = r1.equals(r2)
            java.lang.String r14 = "unmarked"
            java.lang.String r15 = "attempt to add existing attribute with different value"
            if (r1 == 0) goto L_0x0483
            org.bouncycastle.asn1.ASN1InputStream r1 = new org.bouncycastle.asn1.ASN1InputStream
            org.bouncycastle.asn1.ASN1Encodable r2 = r10.getContent()
            org.bouncycastle.asn1.ASN1OctetString r2 = (org.bouncycastle.asn1.ASN1OctetString) r2
            byte[] r2 = r2.getOctets()
            r1.<init>((byte[]) r2)
            org.bouncycastle.asn1.ASN1Primitive r1 = r1.readObject()
            org.bouncycastle.asn1.pkcs.AuthenticatedSafe r1 = org.bouncycastle.asn1.pkcs.AuthenticatedSafe.getInstance(r1)
            org.bouncycastle.asn1.pkcs.ContentInfo[] r10 = r1.getContentInfo()
            r6 = 0
            r16 = 0
        L_0x010a:
            int r1 = r10.length
            if (r6 == r1) goto L_0x0485
            r1 = r10[r6]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = data
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x024b
            org.bouncycastle.asn1.ASN1InputStream r1 = new org.bouncycastle.asn1.ASN1InputStream
            r2 = r10[r6]
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getContent()
            org.bouncycastle.asn1.ASN1OctetString r2 = (org.bouncycastle.asn1.ASN1OctetString) r2
            byte[] r2 = r2.getOctets()
            r1.<init>((byte[]) r2)
            org.bouncycastle.asn1.ASN1Primitive r1 = r1.readObject()
            org.bouncycastle.asn1.ASN1Sequence r1 = (org.bouncycastle.asn1.ASN1Sequence) r1
            r2 = 0
        L_0x0133:
            int r3 = r1.size()
            if (r2 == r3) goto L_0x0246
            org.bouncycastle.asn1.ASN1Encodable r3 = r1.getObjectAt(r2)
            org.bouncycastle.asn1.pkcs.SafeBag r3 = org.bouncycastle.asn1.pkcs.SafeBag.getInstance(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs8ShroudedKeyBag
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x020c
            org.bouncycastle.asn1.ASN1Encodable r4 = r3.getBagValue()
            org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo r4 = org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo.getInstance(r4)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r4.getEncryptionAlgorithm()
            byte[] r4 = r4.getEncryptedData()
            java.security.PrivateKey r4 = r8.unwrapKey(r5, r4, r9, r0)
            r5 = r4
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r5 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r5
            org.bouncycastle.asn1.ASN1Set r17 = r3.getBagAttributes()
            if (r17 == 0) goto L_0x01e4
            org.bouncycastle.asn1.ASN1Set r3 = r3.getBagAttributes()
            java.util.Enumeration r3 = r3.getObjects()
            r17 = r7
            r18 = r17
        L_0x0176:
            boolean r19 = r3.hasMoreElements()
            if (r19 == 0) goto L_0x01e1
            java.lang.Object r19 = r3.nextElement()
            r7 = r19
            org.bouncycastle.asn1.ASN1Sequence r7 = (org.bouncycastle.asn1.ASN1Sequence) r7
            org.bouncycastle.asn1.ASN1Encodable r19 = r7.getObjectAt(r13)
            r13 = r19
            org.bouncycastle.asn1.ASN1ObjectIdentifier r13 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r13
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getObjectAt(r12)
            org.bouncycastle.asn1.ASN1Set r7 = (org.bouncycastle.asn1.ASN1Set) r7
            int r19 = r7.size()
            if (r19 <= 0) goto L_0x01ba
            r12 = 0
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getObjectAt(r12)
            org.bouncycastle.asn1.ASN1Primitive r7 = (org.bouncycastle.asn1.ASN1Primitive) r7
            org.bouncycastle.asn1.ASN1Encodable r12 = r5.getBagAttribute(r13)
            if (r12 == 0) goto L_0x01b6
            org.bouncycastle.asn1.ASN1Primitive r12 = r12.toASN1Primitive()
            boolean r12 = r12.equals(r7)
            if (r12 == 0) goto L_0x01b0
            goto L_0x01bb
        L_0x01b0:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r15)
            throw r0
        L_0x01b6:
            r5.setBagAttribute(r13, r7)
            goto L_0x01bb
        L_0x01ba:
            r7 = 0
        L_0x01bb:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_friendlyName
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x01d1
            org.bouncycastle.asn1.DERBMPString r7 = (org.bouncycastle.asn1.DERBMPString) r7
            java.lang.String r7 = r7.getString()
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r12 = r8.keys
            r12.put(r7, r4)
            r17 = r7
            goto L_0x01dd
        L_0x01d1:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = pkcs_9_at_localKeyId
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x01dd
            r18 = r7
            org.bouncycastle.asn1.ASN1OctetString r18 = (org.bouncycastle.asn1.ASN1OctetString) r18
        L_0x01dd:
            r7 = 0
            r12 = 1
            r13 = 0
            goto L_0x0176
        L_0x01e1:
            r3 = r17
            goto L_0x01e7
        L_0x01e4:
            r3 = 0
            r18 = 0
        L_0x01e7:
            if (r18 == 0) goto L_0x0204
            java.lang.String r5 = new java.lang.String
            byte[] r7 = r18.getOctets()
            byte[] r7 = org.bouncycastle.util.encoders.Hex.encode(r7)
            r5.<init>(r7)
            if (r3 != 0) goto L_0x01fe
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r3 = r8.keys
            r3.put(r5, r4)
            goto L_0x023f
        L_0x01fe:
            java.util.Hashtable r4 = r8.localIds
            r4.put(r3, r5)
            goto L_0x023f
        L_0x0204:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r3 = r8.keys
            r3.put(r14, r4)
            r16 = 1
            goto L_0x023f
        L_0x020c:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = certBag
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x021c
            r11.addElement(r3)
            goto L_0x023f
        L_0x021c:
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "extra in data "
            r5.append(r7)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = r3.getBagId()
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r3 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r3)
            r4.println(r3)
        L_0x023f:
            int r2 = r2 + 1
            r7 = 0
            r12 = 1
            r13 = 0
            goto L_0x0133
        L_0x0246:
            r17 = r0
            r13 = r6
            goto L_0x047a
        L_0x024b:
            r1 = r10[r6]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = encryptedData
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0439
            r1 = r10[r6]
            org.bouncycastle.asn1.ASN1Encodable r1 = r1.getContent()
            org.bouncycastle.asn1.pkcs.EncryptedData r1 = org.bouncycastle.asn1.pkcs.EncryptedData.getInstance(r1)
            r2 = 0
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r1.getEncryptionAlgorithm()
            org.bouncycastle.asn1.ASN1OctetString r1 = r1.getContent()
            byte[] r7 = r1.getOctets()
            r1 = r20
            r4 = r22
            r5 = r0
            r13 = r6
            r6 = r7
            byte[] r1 = r1.cryptData(r2, r3, r4, r5, r6)
            org.bouncycastle.asn1.ASN1Primitive r1 = org.bouncycastle.asn1.ASN1Primitive.fromByteArray(r1)
            org.bouncycastle.asn1.ASN1Sequence r1 = (org.bouncycastle.asn1.ASN1Sequence) r1
            r12 = 0
        L_0x0282:
            int r2 = r1.size()
            if (r12 == r2) goto L_0x0436
            org.bouncycastle.asn1.ASN1Encodable r2 = r1.getObjectAt(r12)
            org.bouncycastle.asn1.pkcs.SafeBag r2 = org.bouncycastle.asn1.pkcs.SafeBag.getInstance(r2)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r2.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = certBag
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x02a5
            r11.addElement(r2)
            r17 = r0
            r18 = r1
            goto L_0x042e
        L_0x02a5:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r2.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = pkcs8ShroudedKeyBag
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0360
            org.bouncycastle.asn1.ASN1Encodable r3 = r2.getBagValue()
            org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo r3 = org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo.getInstance(r3)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r4 = r3.getEncryptionAlgorithm()
            byte[] r3 = r3.getEncryptedData()
            java.security.PrivateKey r3 = r8.unwrapKey(r4, r3, r9, r0)
            r4 = r3
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r4 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r4
            org.bouncycastle.asn1.ASN1Set r2 = r2.getBagAttributes()
            java.util.Enumeration r2 = r2.getObjects()
            r5 = 0
            r6 = 0
        L_0x02d2:
            boolean r7 = r2.hasMoreElements()
            if (r7 == 0) goto L_0x033f
            java.lang.Object r7 = r2.nextElement()
            org.bouncycastle.asn1.ASN1Sequence r7 = (org.bouncycastle.asn1.ASN1Sequence) r7
            r17 = r0
            r0 = 0
            org.bouncycastle.asn1.ASN1Encodable r18 = r7.getObjectAt(r0)
            r0 = r18
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r0
            r18 = r1
            r1 = 1
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getObjectAt(r1)
            org.bouncycastle.asn1.ASN1Set r7 = (org.bouncycastle.asn1.ASN1Set) r7
            int r1 = r7.size()
            if (r1 <= 0) goto L_0x031a
            r1 = 0
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getObjectAt(r1)
            org.bouncycastle.asn1.ASN1Primitive r7 = (org.bouncycastle.asn1.ASN1Primitive) r7
            org.bouncycastle.asn1.ASN1Encodable r1 = r4.getBagAttribute(r0)
            if (r1 == 0) goto L_0x0316
            org.bouncycastle.asn1.ASN1Primitive r1 = r1.toASN1Primitive()
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x0310
            goto L_0x031b
        L_0x0310:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r15)
            throw r0
        L_0x0316:
            r4.setBagAttribute(r0, r7)
            goto L_0x031b
        L_0x031a:
            r7 = 0
        L_0x031b:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = pkcs_9_at_friendlyName
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x032f
            org.bouncycastle.asn1.DERBMPString r7 = (org.bouncycastle.asn1.DERBMPString) r7
            java.lang.String r6 = r7.getString()
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r0 = r8.keys
            r0.put(r6, r3)
            goto L_0x033a
        L_0x032f:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = pkcs_9_at_localKeyId
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x033a
            r5 = r7
            org.bouncycastle.asn1.ASN1OctetString r5 = (org.bouncycastle.asn1.ASN1OctetString) r5
        L_0x033a:
            r0 = r17
            r1 = r18
            goto L_0x02d2
        L_0x033f:
            r17 = r0
            r18 = r1
            java.lang.String r0 = new java.lang.String
            byte[] r1 = r5.getOctets()
            byte[] r1 = org.bouncycastle.util.encoders.Hex.encode(r1)
            r0.<init>(r1)
            if (r6 != 0) goto L_0x0359
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = r8.keys
            r1.put(r0, r3)
            goto L_0x042e
        L_0x0359:
            java.util.Hashtable r1 = r8.localIds
            r1.put(r6, r0)
            goto L_0x042e
        L_0x0360:
            r17 = r0
            r18 = r1
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r2.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = keyBag
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x040b
            org.bouncycastle.asn1.ASN1Encodable r0 = r2.getBagValue()
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r0 = org.bouncycastle.asn1.pkcs.PrivateKeyInfo.getInstance(r0)
            java.security.PrivateKey r0 = org.bouncycastle.jce.provider.BouncyCastleProvider.getPrivateKey(r0)
            r1 = r0
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r1 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r1
            org.bouncycastle.asn1.ASN1Set r2 = r2.getBagAttributes()
            java.util.Enumeration r2 = r2.getObjects()
            r3 = 0
            r4 = 0
        L_0x0389:
            boolean r5 = r2.hasMoreElements()
            if (r5 == 0) goto L_0x03f0
            java.lang.Object r5 = r2.nextElement()
            org.bouncycastle.asn1.ASN1Sequence r5 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r5)
            r6 = 0
            org.bouncycastle.asn1.ASN1Encodable r7 = r5.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r7)
            r6 = 1
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Set r5 = org.bouncycastle.asn1.ASN1Set.getInstance(r5)
            int r6 = r5.size()
            if (r6 <= 0) goto L_0x0389
            r6 = 0
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Primitive r5 = (org.bouncycastle.asn1.ASN1Primitive) r5
            org.bouncycastle.asn1.ASN1Encodable r6 = r1.getBagAttribute(r7)
            if (r6 == 0) goto L_0x03cd
            org.bouncycastle.asn1.ASN1Primitive r6 = r6.toASN1Primitive()
            boolean r6 = r6.equals(r5)
            if (r6 == 0) goto L_0x03c7
            goto L_0x03d0
        L_0x03c7:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r15)
            throw r0
        L_0x03cd:
            r1.setBagAttribute(r7, r5)
        L_0x03d0:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = pkcs_9_at_friendlyName
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x03e4
            org.bouncycastle.asn1.DERBMPString r5 = (org.bouncycastle.asn1.DERBMPString) r5
            java.lang.String r4 = r5.getString()
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r5 = r8.keys
            r5.put(r4, r0)
            goto L_0x0389
        L_0x03e4:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = pkcs_9_at_localKeyId
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0389
            r3 = r5
            org.bouncycastle.asn1.ASN1OctetString r3 = (org.bouncycastle.asn1.ASN1OctetString) r3
            goto L_0x0389
        L_0x03f0:
            java.lang.String r1 = new java.lang.String
            byte[] r2 = r3.getOctets()
            byte[] r2 = org.bouncycastle.util.encoders.Hex.encode(r2)
            r1.<init>(r2)
            if (r4 != 0) goto L_0x0405
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r2 = r8.keys
            r2.put(r1, r0)
            goto L_0x042e
        L_0x0405:
            java.util.Hashtable r0 = r8.localIds
            r0.put(r4, r1)
            goto L_0x042e
        L_0x040b:
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "extra in encryptedData "
            r1.append(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r2.getBagId()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.String r1 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r2)
            r0.println(r1)
        L_0x042e:
            int r12 = r12 + 1
            r0 = r17
            r1 = r18
            goto L_0x0282
        L_0x0436:
            r17 = r0
            goto L_0x047a
        L_0x0439:
            r17 = r0
            r13 = r6
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "extra "
            r1.append(r2)
            r3 = r10[r13]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r3.getContentType()
            java.lang.String r3 = r3.getId()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            r2 = r10[r13]
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getContent()
            java.lang.String r2 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
        L_0x047a:
            int r6 = r13 + 1
            r0 = r17
            r7 = 0
            r12 = 1
            r13 = 0
            goto L_0x010a
        L_0x0483:
            r16 = 0
        L_0x0485:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r0 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable
            r1 = 0
            r0.<init>()
            r8.certs = r0
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            r8.chainCerts = r0
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            r8.keyCerts = r0
            r12 = 0
        L_0x049c:
            int r0 = r11.size()
            if (r12 == r0) goto L_0x05d1
            java.lang.Object r0 = r11.elementAt(r12)
            org.bouncycastle.asn1.pkcs.SafeBag r0 = (org.bouncycastle.asn1.pkcs.SafeBag) r0
            org.bouncycastle.asn1.ASN1Encodable r2 = r0.getBagValue()
            org.bouncycastle.asn1.pkcs.CertBag r2 = org.bouncycastle.asn1.pkcs.CertBag.getInstance(r2)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r2.getCertId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = x509Certificate
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x05b6
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x05ab }
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getCertValue()     // Catch:{ Exception -> 0x05ab }
            org.bouncycastle.asn1.ASN1OctetString r2 = (org.bouncycastle.asn1.ASN1OctetString) r2     // Catch:{ Exception -> 0x05ab }
            byte[] r2 = r2.getOctets()     // Catch:{ Exception -> 0x05ab }
            r3.<init>(r2)     // Catch:{ Exception -> 0x05ab }
            java.security.cert.CertificateFactory r2 = r8.certFact     // Catch:{ Exception -> 0x05ab }
            java.security.cert.Certificate r2 = r2.generateCertificate(r3)     // Catch:{ Exception -> 0x05ab }
            org.bouncycastle.asn1.ASN1Set r3 = r0.getBagAttributes()
            if (r3 == 0) goto L_0x054c
            org.bouncycastle.asn1.ASN1Set r0 = r0.getBagAttributes()
            java.util.Enumeration r0 = r0.getObjects()
            r3 = r1
            r4 = r3
        L_0x04e1:
            boolean r5 = r0.hasMoreElements()
            if (r5 == 0) goto L_0x0549
            java.lang.Object r5 = r0.nextElement()
            org.bouncycastle.asn1.ASN1Sequence r5 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r5)
            r6 = 0
            org.bouncycastle.asn1.ASN1Encodable r7 = r5.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r7)
            r9 = 1
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r9)
            org.bouncycastle.asn1.ASN1Set r5 = org.bouncycastle.asn1.ASN1Set.getInstance(r5)
            int r10 = r5.size()
            if (r10 <= 0) goto L_0x04e1
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Primitive r5 = (org.bouncycastle.asn1.ASN1Primitive) r5
            boolean r10 = r2 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
            if (r10 == 0) goto L_0x052e
            r10 = r2
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r10 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r10
            org.bouncycastle.asn1.ASN1Encodable r13 = r10.getBagAttribute(r7)
            if (r13 == 0) goto L_0x052b
            org.bouncycastle.asn1.ASN1Primitive r10 = r13.toASN1Primitive()
            boolean r10 = r10.equals(r5)
            if (r10 == 0) goto L_0x0525
            goto L_0x052e
        L_0x0525:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r15)
            throw r0
        L_0x052b:
            r10.setBagAttribute(r7, r5)
        L_0x052e:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = pkcs_9_at_friendlyName
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L_0x053d
            org.bouncycastle.asn1.DERBMPString r5 = (org.bouncycastle.asn1.DERBMPString) r5
            java.lang.String r4 = r5.getString()
            goto L_0x04e1
        L_0x053d:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = pkcs_9_at_localKeyId
            boolean r7 = r7.equals(r10)
            if (r7 == 0) goto L_0x04e1
            r3 = r5
            org.bouncycastle.asn1.ASN1OctetString r3 = (org.bouncycastle.asn1.ASN1OctetString) r3
            goto L_0x04e1
        L_0x0549:
            r6 = 0
            r9 = 1
            goto L_0x0550
        L_0x054c:
            r6 = 0
            r9 = 1
            r3 = r1
            r4 = r3
        L_0x0550:
            java.util.Hashtable r0 = r8.chainCerts
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r5 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId
            java.security.PublicKey r7 = r2.getPublicKey()
            r5.<init>((java.security.PublicKey) r7)
            r0.put(r5, r2)
            if (r16 == 0) goto L_0x058c
            java.util.Hashtable r0 = r8.keyCerts
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x05a7
            java.lang.String r0 = new java.lang.String
            java.security.PublicKey r3 = r2.getPublicKey()
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r3 = r8.createSubjectKeyId(r3)
            byte[] r3 = r3.getKeyIdentifier()
            byte[] r3 = org.bouncycastle.util.encoders.Hex.encode(r3)
            r0.<init>(r3)
            java.util.Hashtable r3 = r8.keyCerts
            r3.put(r0, r2)
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r2 = r8.keys
            java.lang.Object r3 = r2.remove(r14)
            r2.put(r0, r3)
            goto L_0x05a7
        L_0x058c:
            if (r3 == 0) goto L_0x05a0
            java.lang.String r0 = new java.lang.String
            byte[] r3 = r3.getOctets()
            byte[] r3 = org.bouncycastle.util.encoders.Hex.encode(r3)
            r0.<init>(r3)
            java.util.Hashtable r3 = r8.keyCerts
            r3.put(r0, r2)
        L_0x05a0:
            if (r4 == 0) goto L_0x05a7
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r0 = r8.certs
            r0.put(r4, r2)
        L_0x05a7:
            int r12 = r12 + 1
            goto L_0x049c
        L_0x05ab:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x05b6:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Unsupported certificate type: "
            r1.append(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r2.getCertId()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x05d1:
            return
        L_0x05d2:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "stream does not represent a PKCS12 key store"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineLoad(java.io.InputStream, char[]):void");
    }

    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        if (this.keys.get(str) == null) {
            this.certs.put(str, certificate);
            this.chainCerts.put(new CertId(certificate.getPublicKey()), certificate);
            return;
        }
        throw new KeyStoreException("There is a key entry with the name " + str + ".");
    }

    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        boolean z = key instanceof PrivateKey;
        if (!z) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!z || certificateArr != null) {
            if (this.keys.get(str) != null) {
                engineDeleteEntry(str);
            }
            this.keys.put(str, key);
            if (certificateArr != null) {
                this.certs.put(str, certificateArr[0]);
                for (int i = 0; i != certificateArr.length; i++) {
                    this.chainCerts.put(new CertId(certificateArr[i].getPublicKey()), certificateArr[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    public int engineSize() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.size();
    }

    public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
        doStore(outputStream, cArr, false);
    }

    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws IOException, NoSuchAlgorithmException, CertificateException {
        PKCS12StoreParameter pKCS12StoreParameter;
        char[] cArr;
        if (loadStoreParameter != null) {
            boolean z = loadStoreParameter instanceof PKCS12StoreParameter;
            if (z || (loadStoreParameter instanceof JDKPKCS12StoreParameter)) {
                if (z) {
                    pKCS12StoreParameter = (PKCS12StoreParameter) loadStoreParameter;
                } else {
                    JDKPKCS12StoreParameter jDKPKCS12StoreParameter = (JDKPKCS12StoreParameter) loadStoreParameter;
                    pKCS12StoreParameter = new PKCS12StoreParameter(jDKPKCS12StoreParameter.getOutputStream(), loadStoreParameter.getProtectionParameter(), jDKPKCS12StoreParameter.isUseDEREncoding());
                }
                KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
                if (protectionParameter == null) {
                    cArr = null;
                } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
                    cArr = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
                } else {
                    throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
                }
                doStore(pKCS12StoreParameter.getOutputStream(), cArr, pKCS12StoreParameter.isForDEREncoding());
                return;
            }
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
        throw new IllegalArgumentException("'param' arg cannot be null");
    }

    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    /* access modifiers changed from: protected */
    public PrivateKey unwrapKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, char[] cArr, boolean z) throws IOException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        try {
            if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), instance.getIterations().intValue());
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(4, new PKCS12Key(cArr, z), pBEParameterSpec);
                return (PrivateKey) createCipher.unwrap(bArr, "", 2);
            } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                return (PrivateKey) createCipher(4, cArr, algorithmIdentifier).unwrap(bArr, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] wrapKey(String str, Key key, PKCS12PBEParams pKCS12PBEParams, char[] cArr) throws IOException {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
        try {
            SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
            Cipher createCipher = this.helper.createCipher(str);
            createCipher.init(3, createSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return createCipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }
}
