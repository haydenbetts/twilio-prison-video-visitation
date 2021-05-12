package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ua.DSTU4145Params;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Strings;

public class BCDSTU4145PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey, ECPointEncoder {
    static final long serialVersionUID = 7026240464295649314L;
    private String algorithm = "DSTU4145";
    private transient DSTU4145Params dstuParams;
    private transient ECParameterSpec ecSpec;
    private transient ECPoint q;
    private boolean withCompression;

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters) {
        this.algorithm = str;
        this.q = eCPublicKeyParameters.getQ();
        this.ecSpec = null;
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        this.algorithm = str;
        this.q = eCPublicKeyParameters.getQ();
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), parameters);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        this.algorithm = str;
        this.q = eCPublicKeyParameters.getQ();
        this.ecSpec = eCParameterSpec == null ? createSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), parameters) : EC5Util.convertSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), eCParameterSpec);
    }

    public BCDSTU4145PublicKey(ECPublicKey eCPublicKey) {
        this.algorithm = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.ecSpec = params;
        this.q = EC5Util.convertPoint(params, eCPublicKey.getW(), false);
    }

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.q = EC5Util.convertPoint(params, eCPublicKeySpec.getW(), false);
    }

    BCDSTU4145PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        populateFromPubKeyInfo(subjectPublicKeyInfo);
    }

    public BCDSTU4145PublicKey(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        this.q = bCDSTU4145PublicKey.q;
        this.ecSpec = bCDSTU4145PublicKey.ecSpec;
        this.withCompression = bCDSTU4145PublicKey.withCompression;
        this.dstuParams = bCDSTU4145PublicKey.dstuParams;
    }

    public BCDSTU4145PublicKey(org.bouncycastle.jce.spec.ECPublicKeySpec eCPublicKeySpec) {
        ECParameterSpec eCParameterSpec;
        this.q = eCPublicKeySpec.getQ();
        if (eCPublicKeySpec.getParams() != null) {
            eCParameterSpec = EC5Util.convertSpec(EC5Util.convertCurve(eCPublicKeySpec.getParams().getCurve(), eCPublicKeySpec.getParams().getSeed()), eCPublicKeySpec.getParams());
        } else {
            if (this.q.getCurve() == null) {
                this.q = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve().createPoint(this.q.getAffineXCoord().toBigInteger(), this.q.getAffineYCoord().toBigInteger());
            }
            eCParameterSpec = null;
        }
        this.ecSpec = eCParameterSpec;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new java.security.spec.ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: org.bouncycastle.jce.spec.ECNamedCurveParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.security.spec.ECParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: org.bouncycastle.jce.spec.ECParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: org.bouncycastle.jce.spec.ECNamedCurveParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: org.bouncycastle.jce.spec.ECNamedCurveParameterSpec} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateFromPubKeyInfo(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r13) {
        /*
            r12 = this;
            org.bouncycastle.asn1.DERBitString r0 = r13.getPublicKeyData()
            java.lang.String r1 = "DSTU4145"
            r12.algorithm = r1
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x014e }
            org.bouncycastle.asn1.ASN1Primitive r0 = org.bouncycastle.asn1.ASN1Primitive.fromByteArray(r0)     // Catch:{ IOException -> 0x014e }
            org.bouncycastle.asn1.ASN1OctetString r0 = (org.bouncycastle.asn1.ASN1OctetString) r0     // Catch:{ IOException -> 0x014e }
            byte[] r0 = r0.getOctets()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r13.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0029
            r12.reverseBytes(r0)
        L_0x0029:
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r13.getAlgorithm()
            org.bouncycastle.asn1.ASN1Encodable r1 = r1.getParameters()
            org.bouncycastle.asn1.ASN1Sequence r1 = (org.bouncycastle.asn1.ASN1Sequence) r1
            org.bouncycastle.asn1.ua.DSTU4145Params r1 = org.bouncycastle.asn1.ua.DSTU4145Params.getInstance(r1)
            r12.dstuParams = r1
            boolean r1 = r1.isNamedCurve()
            if (r1 == 0) goto L_0x0068
            org.bouncycastle.asn1.ua.DSTU4145Params r13 = r12.dstuParams
            org.bouncycastle.asn1.ASN1ObjectIdentifier r13 = r13.getNamedCurve()
            org.bouncycastle.crypto.params.ECDomainParameters r1 = org.bouncycastle.asn1.ua.DSTU4145NamedCurves.getByOID(r13)
            org.bouncycastle.jce.spec.ECNamedCurveParameterSpec r9 = new org.bouncycastle.jce.spec.ECNamedCurveParameterSpec
            java.lang.String r3 = r13.getId()
            org.bouncycastle.math.ec.ECCurve r4 = r1.getCurve()
            org.bouncycastle.math.ec.ECPoint r5 = r1.getG()
            java.math.BigInteger r6 = r1.getN()
            java.math.BigInteger r7 = r1.getH()
            byte[] r8 = r1.getSeed()
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            goto L_0x00cd
        L_0x0068:
            org.bouncycastle.asn1.ua.DSTU4145Params r1 = r12.dstuParams
            org.bouncycastle.asn1.ua.DSTU4145ECBinary r1 = r1.getECBinary()
            byte[] r2 = r1.getB()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r13.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r3.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0085
            r12.reverseBytes(r2)
        L_0x0085:
            org.bouncycastle.asn1.ua.DSTU4145BinaryField r3 = r1.getField()
            org.bouncycastle.math.ec.ECCurve$F2m r11 = new org.bouncycastle.math.ec.ECCurve$F2m
            int r5 = r3.getM()
            int r6 = r3.getK1()
            int r7 = r3.getK2()
            int r8 = r3.getK3()
            java.math.BigInteger r9 = r1.getA()
            java.math.BigInteger r10 = new java.math.BigInteger
            r3 = 1
            r10.<init>(r3, r2)
            r4 = r11
            r4.<init>((int) r5, (int) r6, (int) r7, (int) r8, (java.math.BigInteger) r9, (java.math.BigInteger) r10)
            byte[] r2 = r1.getG()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r13 = r13.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r13 = r13.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r13 = r13.equals(r3)
            if (r13 == 0) goto L_0x00c0
            r12.reverseBytes(r2)
        L_0x00c0:
            org.bouncycastle.jce.spec.ECParameterSpec r9 = new org.bouncycastle.jce.spec.ECParameterSpec
            org.bouncycastle.math.ec.ECPoint r13 = org.bouncycastle.asn1.ua.DSTU4145PointEncoder.decodePoint(r11, r2)
            java.math.BigInteger r1 = r1.getN()
            r9.<init>(r11, r13, r1)
        L_0x00cd:
            org.bouncycastle.math.ec.ECCurve r13 = r9.getCurve()
            byte[] r1 = r9.getSeed()
            java.security.spec.EllipticCurve r4 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertCurve(r13, r1)
            org.bouncycastle.math.ec.ECPoint r13 = org.bouncycastle.asn1.ua.DSTU4145PointEncoder.decodePoint(r13, r0)
            r12.q = r13
            org.bouncycastle.asn1.ua.DSTU4145Params r13 = r12.dstuParams
            boolean r13 = r13.isNamedCurve()
            if (r13 == 0) goto L_0x011d
            org.bouncycastle.jce.spec.ECNamedCurveSpec r13 = new org.bouncycastle.jce.spec.ECNamedCurveSpec
            org.bouncycastle.asn1.ua.DSTU4145Params r0 = r12.dstuParams
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r0.getNamedCurve()
            java.lang.String r3 = r0.getId()
            java.security.spec.ECPoint r5 = new java.security.spec.ECPoint
            org.bouncycastle.math.ec.ECPoint r0 = r9.getG()
            org.bouncycastle.math.ec.ECFieldElement r0 = r0.getAffineXCoord()
            java.math.BigInteger r0 = r0.toBigInteger()
            org.bouncycastle.math.ec.ECPoint r1 = r9.getG()
            org.bouncycastle.math.ec.ECFieldElement r1 = r1.getAffineYCoord()
            java.math.BigInteger r1 = r1.toBigInteger()
            r5.<init>(r0, r1)
            java.math.BigInteger r6 = r9.getN()
            java.math.BigInteger r7 = r9.getH()
            r2 = r13
            r2.<init>((java.lang.String) r3, (java.security.spec.EllipticCurve) r4, (java.security.spec.ECPoint) r5, (java.math.BigInteger) r6, (java.math.BigInteger) r7)
            goto L_0x014b
        L_0x011d:
            java.security.spec.ECParameterSpec r13 = new java.security.spec.ECParameterSpec
            java.security.spec.ECPoint r0 = new java.security.spec.ECPoint
            org.bouncycastle.math.ec.ECPoint r1 = r9.getG()
            org.bouncycastle.math.ec.ECFieldElement r1 = r1.getAffineXCoord()
            java.math.BigInteger r1 = r1.toBigInteger()
            org.bouncycastle.math.ec.ECPoint r2 = r9.getG()
            org.bouncycastle.math.ec.ECFieldElement r2 = r2.getAffineYCoord()
            java.math.BigInteger r2 = r2.toBigInteger()
            r0.<init>(r1, r2)
            java.math.BigInteger r1 = r9.getN()
            java.math.BigInteger r2 = r9.getH()
            int r2 = r2.intValue()
            r13.<init>(r4, r0, r1, r2)
        L_0x014b:
            r12.ecSpec = r13
            return
        L_0x014e:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "error recovering public key"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.dstu.BCDSTU4145PublicKey.populateFromPubKeyInfo(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo):void");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
    }

    private void reverseBytes(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public ECPoint engineGetQ() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec, this.withCompression) : BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) obj;
        return engineGetQ().equals(bCDSTU4145PublicKey.engineGetQ()) && engineGetSpec().equals(bCDSTU4145PublicKey.engineGetSpec());
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getEncoded() {
        ASN1Encodable aSN1Encodable = this.dstuParams;
        if (aSN1Encodable == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof ECNamedCurveSpec) {
                aSN1Encodable = new DSTU4145Params(new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName()));
            } else {
                ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                aSN1Encodable = new X962Parameters(new X9ECParameters(convertCurve, EC5Util.convertPoint(convertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf((long) this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            }
        }
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(new AlgorithmIdentifier(UAObjectIdentifiers.dstu4145be, aSN1Encodable), (ASN1Encodable) new DEROctetString(DSTU4145PointEncoder.encodePoint(this.q))));
        } catch (IOException unused) {
            return null;
        }
    }

    public String getFormat() {
        return "X.509";
    }

    public org.bouncycastle.jce.spec.ECParameterSpec getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
    }

    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    public ECPoint getQ() {
        return this.ecSpec == null ? this.q.getDetachedPoint() : this.q;
    }

    public byte[] getSbox() {
        DSTU4145Params dSTU4145Params = this.dstuParams;
        return dSTU4145Params != null ? dSTU4145Params.getDKE() : DSTU4145Params.getDefaultDKE();
    }

    public java.security.spec.ECPoint getW() {
        return new java.security.spec.ECPoint(this.q.getAffineXCoord().toBigInteger(), this.q.getAffineYCoord().toBigInteger());
    }

    public int hashCode() {
        return engineGetQ().hashCode() ^ engineGetSpec().hashCode();
    }

    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("EC Public Key");
        stringBuffer.append(lineSeparator);
        stringBuffer.append("            X: ");
        stringBuffer.append(this.q.getAffineXCoord().toBigInteger().toString(16));
        stringBuffer.append(lineSeparator);
        stringBuffer.append("            Y: ");
        stringBuffer.append(this.q.getAffineYCoord().toBigInteger().toString(16));
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }
}
