package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.Certificate;
import fm.liveswitch.Fingerprint;
import java.util.Date;

public abstract class Certificate<TCertificate extends Certificate<TCertificate, TFingerprint>, TFingerprint extends Fingerprint> {
    Asn1Sequence __asn1;
    private boolean __autoRegenerate = true;
    DataBuffer __buffer;
    private Date __effectiveDate = new Date();
    private Date __expirationDate = new Date();
    private X509Extensions __extensions;
    private String __issuerName;
    private byte[] __issuerUniqueId;
    private AsymmetricKey __key;
    DataBuffer __md5Fingerprint;
    private byte[] __serialNumber;
    private Object __serializationLock = new Object();
    DataBuffer __sha1Fingerprint;
    DataBuffer __sha256Fingerprint;
    private X509AlgorithmIdentifier __signatureAlgorithm;
    private String __subjectName;
    private byte[] __subjectUniqueId;
    X509Certificate __x509;
    private String _id;

    /* access modifiers changed from: protected */
    public abstract TCertificate createCertificate();

    /* access modifiers changed from: protected */
    public abstract TFingerprint createFingerprint(String str, String str2);

    public TFingerprint calculateFingerprint(String str) {
        DataBuffer dataBuffer;
        byte[] bytes = getBytes();
        if (Global.equals(str, Fingerprint.getSha256Algorithm())) {
            if (this.__sha256Fingerprint == null) {
                synchronized (this.__serializationLock) {
                    if (this.__sha256Fingerprint == null) {
                        this.__sha256Fingerprint = HashContextBase.compute(HashType.Sha256, DataBuffer.wrap(bytes));
                    }
                }
            }
            dataBuffer = this.__sha256Fingerprint;
        } else if (Global.equals(str, Fingerprint.getSha1Algorithm())) {
            if (this.__sha1Fingerprint == null) {
                synchronized (this.__serializationLock) {
                    if (this.__sha1Fingerprint == null) {
                        this.__sha1Fingerprint = HashContextBase.compute(HashType.Sha1, DataBuffer.wrap(bytes));
                    }
                }
            }
            dataBuffer = this.__sha1Fingerprint;
        } else if (!Global.equals(str, Fingerprint.getMd5Algorithm())) {
            Log.error(StringExtensions.format("Unsupported fingerprint algorithm: {0}.", (Object) str));
            return null;
        } else if (Platform.getInstance().getUseFipsAlgorithms()) {
            Log.error("MD5 is not a permitted algorithm when FIPS is enabled");
            return null;
        } else {
            if (this.__md5Fingerprint == null) {
                synchronized (this.__serializationLock) {
                    if (this.__md5Fingerprint == null) {
                        this.__md5Fingerprint = HashContextBase.compute(HashType.Md5, DataBuffer.wrap(bytes));
                    }
                }
            }
            dataBuffer = this.__md5Fingerprint;
        }
        String hexString = dataBuffer.toHexString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < StringExtensions.getLength(hexString); i += 2) {
            if (i > 0) {
                StringBuilderExtensions.append(sb, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            }
            StringBuilderExtensions.append(sb, StringExtensions.toUpper(StringExtensions.substring(hexString, i, 2)));
        }
        return createFingerprint(str, sb.toString());
    }

    public Certificate() {
        setId(Guid.newGuid().toString().replace("-", ""));
    }

    public TCertificate clone() {
        TCertificate createCertificate = createCertificate();
        createCertificate.setId(getId());
        createCertificate.setIssuerName(getIssuerName());
        createCertificate.setSubjectName(getSubjectName());
        createCertificate.setEffectiveDate(getEffectiveDate());
        createCertificate.setExpirationDate(getExpirationDate());
        createCertificate.setIssuerUniqueId(getIssuerUniqueId());
        createCertificate.setSubjectUniqueId(getSubjectUniqueId());
        createCertificate.setExtensions(getExtensions());
        createCertificate.setSerialNumber(getSerialNumber());
        createCertificate.setSignatureAlgorithm(getSignatureAlgorithm());
        createCertificate.setKey(getKey());
        synchronized (this.__serializationLock) {
            createCertificate.__buffer = this.__buffer;
            createCertificate.__asn1 = this.__asn1;
            createCertificate.__x509 = this.__x509;
            createCertificate.__md5Fingerprint = this.__md5Fingerprint;
            createCertificate.__sha1Fingerprint = this.__sha1Fingerprint;
            createCertificate.__sha256Fingerprint = this.__sha256Fingerprint;
        }
        return createCertificate;
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC fromAsn1(Asn1Sequence asn1Sequence, TC tc) {
        TC fromX509;
        X509Certificate fromAsn1 = X509Certificate.fromAsn1(asn1Sequence);
        if (fromAsn1 == null || (fromX509 = fromX509(fromAsn1, tc)) == null) {
            return null;
        }
        fromX509.__asn1 = asn1Sequence;
        return fromX509;
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC fromX509(X509Certificate x509Certificate, TC tc) {
        X509SubjectPublicKeyInfo subjectPublicKeyInfo;
        AsymmetricKey asymmetricKey;
        if (x509Certificate == null) {
            return null;
        }
        byte[] signature = x509Certificate.getSignature();
        X509TbsCertificate tbsCertificate = x509Certificate.getTbsCertificate();
        if (tbsCertificate == null || !X509AlgorithmIdentifier.areEqual(x509Certificate.getSignatureAlgorithm(), tbsCertificate.getSignatureAlgorithm()) || (subjectPublicKeyInfo = tbsCertificate.getSubjectPublicKeyInfo()) == null || subjectPublicKeyInfo.getSubjectPublicKey() == null) {
            return null;
        }
        if (signature != null) {
            asymmetricKey = AsymmetricKey.fromSubjectPublicKeyInfo(subjectPublicKeyInfo);
            if (tbsCertificate.getSourceAsn() == null) {
                boolean verify = asymmetricKey.verify(tbsCertificate.toAsn1().getBytes(), signature, x509Certificate.getSignatureAlgorithm());
            } else {
                DataBuffer sourceData = tbsCertificate.getSourceAsn().getSourceData();
                DataBuffer buffer = tbsCertificate.toAsn1().getBuffer();
                if (!sourceData.sequenceEquals(buffer)) {
                    Log.warn(StringExtensions.format("Certificate ASN.1 input does not match certificate ASN.1 output.\nINPUT: {0}\nOUTPUT: {1}", sourceData.toHexString(), buffer.toHexString()));
                }
                asymmetricKey.verify(sourceData.toArray(), signature, x509Certificate.getSignatureAlgorithm());
            }
        } else {
            asymmetricKey = null;
        }
        if (tbsCertificate.getValidity() == null) {
            return null;
        }
        tc.setSignatureAlgorithm(x509Certificate.getSignatureAlgorithm());
        tc.setSerialNumber(tbsCertificate.getSerialNumber());
        tc.setEffectiveDate(tbsCertificate.getValidity().getNotBefore().getValue());
        tc.setExpirationDate(tbsCertificate.getValidity().getNotAfter().getValue());
        tc.setIssuerName(getCommonName(tbsCertificate.getIssuer()));
        tc.setSubjectName(getCommonName(tbsCertificate.getSubject()));
        tc.setIssuerUniqueId(tbsCertificate.getIssuerUniqueId());
        tc.setSubjectUniqueId(tbsCertificate.getSubjectUniqueId());
        tc.setExtensions(tbsCertificate.getExtensions());
        tc.setKey(asymmetricKey);
        tc.__x509 = x509Certificate;
        return tc;
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificate(String str, String str2, Date date, AsymmetricKey asymmetricKey, TC tc) {
        Date utcNow = DateExtensions.getUtcNow();
        if (asymmetricKey == null) {
            throw new RuntimeException(new Exception("Key cannot be null."));
        } else if (!asymmetricKey.hasPublic()) {
            throw new RuntimeException(new Exception("Key is missing public details."));
        } else if (asymmetricKey.hasPrivate()) {
            if (str == null) {
                str = "LiveSwitch";
            }
            tc.setIssuerName(str);
            if (str2 == null) {
                str2 = "LiveSwitch";
            }
            tc.setSubjectName(str2);
            tc.setEffectiveDate(DateExtensions.addSeconds(utcNow, -864000.0d));
            tc.setExpirationDate(date);
            tc.setSerialNumber(Binary.toBytes32((long) LockedRandomizer.next(), false));
            tc.setSignatureAlgorithm(asymmetricKey.getPreferredSignatureAlgorithm());
            tc.setKey(asymmetricKey);
            return tc;
        } else {
            throw new RuntimeException(new Exception("Key is missing private details."));
        }
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificate(String str, String str2, AsymmetricKey asymmetricKey, TC tc) {
        return generateCertificate(str, str2, DateExtensions.addSeconds(DateExtensions.getUtcNow(), 864000.0d), asymmetricKey, tc);
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificate(AsymmetricKey asymmetricKey, TC tc) {
        return generateCertificate("LiveSwitch", asymmetricKey, tc);
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificate(String str, AsymmetricKey asymmetricKey, TC tc) {
        return generateCertificate(str, str, asymmetricKey, tc);
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificateFromOldCertificate(TC tc, TC tc2) {
        return generateCertificate(tc.getIssuerName(), tc.getSubjectName(), AsymmetricKey.createKey(tc.getKey().getType()), tc2);
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC generateCertificateFromOldCertificate(TC tc, Date date, TC tc2) {
        return generateCertificate(tc.getIssuerName(), tc.getSubjectName(), date, AsymmetricKey.createKey(tc.getKey().getType()), tc2);
    }

    public boolean getAutoRegenerate() {
        return this.__autoRegenerate;
    }

    public DataBuffer getBuffer() {
        Asn1Sequence asn1;
        if (this.__buffer == null) {
            synchronized (this.__serializationLock) {
                if (this.__buffer == null && (asn1 = toAsn1()) != null) {
                    this.__buffer = asn1.getBuffer();
                }
            }
        }
        return this.__buffer;
    }

    public byte[] getBytes() {
        return getBuffer().toArray();
    }

    private static String getCommonName(X501Name x501Name) {
        X501Attribute attribute;
        if (x501Name == null || (attribute = x501Name.getAttribute(X501AttributeType.getCommonName())) == null) {
            return null;
        }
        return attribute.attributeValueAsString();
    }

    public Date getEffectiveDate() {
        return this.__effectiveDate;
    }

    public Date getExpirationDate() {
        return this.__expirationDate;
    }

    /* access modifiers changed from: package-private */
    public X509Extensions getExtensions() {
        return this.__extensions;
    }

    public String getId() {
        return this._id;
    }

    public boolean getIsExpired() {
        return DateExtensions.getTicks(DateExtensions.getUtcNow()) > DateExtensions.getTicks(getExpirationDate());
    }

    public boolean getIsExpiring() {
        return DateExtensions.getTicks(getExpirationDate()) - DateExtensions.getTicks(DateExtensions.getUtcNow()) < Constants.getTicksPerDay();
    }

    public boolean getIsSealed() {
        return this.__x509 != null;
    }

    public String getIssuerName() {
        return this.__issuerName;
    }

    /* access modifiers changed from: package-private */
    public byte[] getIssuerUniqueId() {
        return this.__issuerUniqueId;
    }

    public AsymmetricKey getKey() {
        return this.__key;
    }

    /* access modifiers changed from: package-private */
    public byte[] getSerialNumber() {
        return this.__serialNumber;
    }

    /* access modifiers changed from: package-private */
    public X509AlgorithmIdentifier getSignatureAlgorithm() {
        return this.__signatureAlgorithm;
    }

    public String getSubjectName() {
        return this.__subjectName;
    }

    /* access modifiers changed from: package-private */
    public byte[] getSubjectUniqueId() {
        return this.__subjectUniqueId;
    }

    static <TC extends Certificate<TC, TF>, TF extends Fingerprint> TC parseBytes(byte[] bArr, TC tc) {
        TC fromAsn1;
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(Asn1Any.parseBytes(bArr), Asn1Sequence.class);
        if (asn1Sequence == null || (fromAsn1 = fromAsn1(asn1Sequence, tc)) == null) {
            return null;
        }
        fromAsn1.__buffer = DataBuffer.wrap(bArr);
        return fromAsn1;
    }

    public void regenerate() {
        regenerate(DateExtensions.addSeconds(DateExtensions.getUtcNow(), 864000.0d));
    }

    public void regenerate(Date date) {
        synchronized (this.__serializationLock) {
            this.__buffer = null;
            this.__asn1 = null;
            this.__x509 = null;
            this.__md5Fingerprint = null;
            this.__sha1Fingerprint = null;
            this.__sha256Fingerprint = null;
            setExpirationDate(date);
            setSerialNumber(Binary.toBytes32((long) LockedRandomizer.next(), false));
            AsymmetricKey key = getKey();
            if (key != null) {
                setKey(key.regenerate());
            }
        }
    }

    public void setAutoRegenerate(boolean z) {
        this.__autoRegenerate = z;
    }

    /* access modifiers changed from: package-private */
    public void setEffectiveDate(Date date) {
        if (!getIsSealed()) {
            this.__effectiveDate = date;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set effective date. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setExpirationDate(Date date) {
        if (!getIsSealed()) {
            this.__expirationDate = date;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set expiration date. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setExtensions(X509Extensions x509Extensions) {
        if (!getIsSealed()) {
            this.__extensions = x509Extensions;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set extensions. Certificate is sealed."));
    }

    public void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: package-private */
    public void setIssuerName(String str) {
        if (!getIsSealed()) {
            this.__issuerName = str;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set issuer name. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setIssuerUniqueId(byte[] bArr) {
        if (!getIsSealed()) {
            this.__issuerUniqueId = bArr;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set issuer unique ID. Certificate is sealed."));
    }

    public void setKey(AsymmetricKey asymmetricKey) {
        if (!getIsSealed()) {
            this.__key = asymmetricKey;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set key. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setSerialNumber(byte[] bArr) {
        if (!getIsSealed()) {
            this.__serialNumber = bArr;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set serial number. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setSignatureAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        if (!getIsSealed()) {
            this.__signatureAlgorithm = x509AlgorithmIdentifier;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set signature algorithm. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setSubjectName(String str) {
        if (!getIsSealed()) {
            this.__subjectName = str;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set subject name. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public void setSubjectUniqueId(byte[] bArr) {
        if (!getIsSealed()) {
            this.__subjectUniqueId = bArr;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set subject unique ID. Certificate is sealed."));
    }

    /* access modifiers changed from: package-private */
    public Asn1Sequence toAsn1() {
        X509Certificate x509;
        if (this.__asn1 == null) {
            synchronized (this.__serializationLock) {
                if (this.__asn1 == null && (x509 = toX509()) != null) {
                    this.__asn1 = x509.toAsn1();
                }
            }
        }
        return this.__asn1;
    }

    /* access modifiers changed from: package-private */
    public X509Certificate toX509() {
        if (this.__x509 == null) {
            synchronized (this.__serializationLock) {
                if (this.__x509 == null) {
                    AsymmetricKey key = getKey();
                    if (key == null || !key.hasPrivate()) {
                        throw new RuntimeException(new Exception("Key is missing private information."));
                    }
                    X509TbsCertificate x509TbsCertificate = new X509TbsCertificate();
                    x509TbsCertificate.setVersion(X509TbsCertificate.getVersion3());
                    x509TbsCertificate.setSerialNumber(getSerialNumber());
                    x509TbsCertificate.setSignatureAlgorithm(getSignatureAlgorithm());
                    x509TbsCertificate.setIssuer(new X501Name(new X501RelativeDistinguishedName[]{new X501RelativeDistinguishedName(new X501Attribute[]{new X501Attribute(X501AttributeType.getCommonName(), new X501DirectoryString(getIssuerName()).toAsn1Printable().getBytes())})}));
                    X509Validity x509Validity = new X509Validity();
                    x509Validity.setNotBefore(new X509Time(getEffectiveDate(), X509TimeType.Utc));
                    x509Validity.setNotAfter(new X509Time(getExpirationDate(), X509TimeType.Utc));
                    x509TbsCertificate.setValidity(x509Validity);
                    x509TbsCertificate.setSubject(new X501Name(new X501RelativeDistinguishedName[]{new X501RelativeDistinguishedName(new X501Attribute[]{new X501Attribute(X501AttributeType.getCommonName(), new X501DirectoryString(getSubjectName()).toAsn1Printable().getBytes())})}));
                    x509TbsCertificate.setSubjectPublicKeyInfo(key.toSubjectPublicKeyInfo());
                    x509TbsCertificate.setIssuerUniqueId(getIssuerUniqueId());
                    x509TbsCertificate.setSubjectUniqueId(getSubjectUniqueId());
                    x509TbsCertificate.setExtensions(getExtensions());
                    X509Certificate x509Certificate = new X509Certificate();
                    x509Certificate.setTbsCertificate(x509TbsCertificate);
                    x509Certificate.setSignatureAlgorithm(getSignatureAlgorithm());
                    x509Certificate.setSignature(key.sign(x509TbsCertificate.toAsn1().getBytes(), getSignatureAlgorithm()));
                    this.__x509 = x509Certificate;
                }
            }
        }
        return this.__x509;
    }
}
