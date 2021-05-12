package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class PublicKeyFactory {
    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: org.bouncycastle.crypto.params.DSAParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: org.bouncycastle.crypto.params.DSAParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: org.bouncycastle.crypto.params.DSAParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: org.bouncycastle.crypto.params.DHValidationParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: org.bouncycastle.crypto.params.DSAParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: org.bouncycastle.crypto.params.DSAParameters} */
    /* JADX WARNING: type inference failed for: r8v0, types: [org.bouncycastle.crypto.params.ECDomainParameters] */
    /* JADX WARNING: type inference failed for: r3v9, types: [org.bouncycastle.crypto.params.DHValidationParameters] */
    /* JADX WARNING: type inference failed for: r1v29, types: [org.bouncycastle.crypto.params.ECDomainParameters] */
    /* JADX WARNING: type inference failed for: r1v30, types: [org.bouncycastle.crypto.params.ECNamedDomainParameters] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bouncycastle.crypto.params.AsymmetricKeyParameter createKey(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r10) throws java.io.IOException {
        /*
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r0 = r10.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.rsaEncryption
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 != 0) goto L_0x01bf
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id_ea_rsa
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x001f
            goto L_0x01bf
        L_0x001f:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x9.X9ObjectIdentifiers.dhpublicnumber
            boolean r1 = r1.equals(r3)
            r3 = 0
            if (r1 == 0) goto L_0x007d
            org.bouncycastle.asn1.ASN1Primitive r10 = r10.parsePublicKey()
            org.bouncycastle.asn1.x9.DHPublicKey r10 = org.bouncycastle.asn1.x9.DHPublicKey.getInstance(r10)
            java.math.BigInteger r10 = r10.getY()
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getParameters()
            org.bouncycastle.asn1.x9.DomainParameters r0 = org.bouncycastle.asn1.x9.DomainParameters.getInstance(r0)
            java.math.BigInteger r5 = r0.getP()
            java.math.BigInteger r6 = r0.getG()
            java.math.BigInteger r7 = r0.getQ()
            java.math.BigInteger r1 = r0.getJ()
            if (r1 == 0) goto L_0x0058
            java.math.BigInteger r1 = r0.getJ()
            r8 = r1
            goto L_0x0059
        L_0x0058:
            r8 = r3
        L_0x0059:
            org.bouncycastle.asn1.x9.ValidationParams r0 = r0.getValidationParams()
            if (r0 == 0) goto L_0x0070
            byte[] r1 = r0.getSeed()
            java.math.BigInteger r0 = r0.getPgenCounter()
            org.bouncycastle.crypto.params.DHValidationParameters r3 = new org.bouncycastle.crypto.params.DHValidationParameters
            int r0 = r0.intValue()
            r3.<init>(r1, r0)
        L_0x0070:
            r9 = r3
            org.bouncycastle.crypto.params.DHPublicKeyParameters r0 = new org.bouncycastle.crypto.params.DHPublicKeyParameters
            org.bouncycastle.crypto.params.DHParameters r1 = new org.bouncycastle.crypto.params.DHParameters
            r4 = r1
            r4.<init>((java.math.BigInteger) r5, (java.math.BigInteger) r6, (java.math.BigInteger) r7, (java.math.BigInteger) r8, (org.bouncycastle.crypto.params.DHValidationParameters) r9)
            r0.<init>(r10, r1)
            return r0
        L_0x007d:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.dhKeyAgreement
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x00b9
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getParameters()
            org.bouncycastle.asn1.pkcs.DHParameter r0 = org.bouncycastle.asn1.pkcs.DHParameter.getInstance(r0)
            org.bouncycastle.asn1.ASN1Primitive r10 = r10.parsePublicKey()
            org.bouncycastle.asn1.ASN1Integer r10 = (org.bouncycastle.asn1.ASN1Integer) r10
            java.math.BigInteger r1 = r0.getL()
            if (r1 != 0) goto L_0x009e
            goto L_0x00a2
        L_0x009e:
            int r2 = r1.intValue()
        L_0x00a2:
            org.bouncycastle.crypto.params.DHParameters r1 = new org.bouncycastle.crypto.params.DHParameters
            java.math.BigInteger r4 = r0.getP()
            java.math.BigInteger r0 = r0.getG()
            r1.<init>(r4, r0, r3, r2)
            org.bouncycastle.crypto.params.DHPublicKeyParameters r0 = new org.bouncycastle.crypto.params.DHPublicKeyParameters
            java.math.BigInteger r10 = r10.getValue()
            r0.<init>(r10, r1)
            return r0
        L_0x00b9:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.oiw.OIWObjectIdentifiers.elGamalAlgorithm
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ea
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getParameters()
            org.bouncycastle.asn1.oiw.ElGamalParameter r0 = org.bouncycastle.asn1.oiw.ElGamalParameter.getInstance(r0)
            org.bouncycastle.asn1.ASN1Primitive r10 = r10.parsePublicKey()
            org.bouncycastle.asn1.ASN1Integer r10 = (org.bouncycastle.asn1.ASN1Integer) r10
            org.bouncycastle.crypto.params.ElGamalPublicKeyParameters r1 = new org.bouncycastle.crypto.params.ElGamalPublicKeyParameters
            java.math.BigInteger r10 = r10.getValue()
            org.bouncycastle.crypto.params.ElGamalParameters r2 = new org.bouncycastle.crypto.params.ElGamalParameters
            java.math.BigInteger r3 = r0.getP()
            java.math.BigInteger r0 = r0.getG()
            r2.<init>(r3, r0)
            r1.<init>(r10, r2)
            return r1
        L_0x00ea:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x9.X9ObjectIdentifiers.id_dsa
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0190
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.oiw.OIWObjectIdentifiers.dsaWithSHA1
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0104
            goto L_0x0190
        L_0x0104:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r0.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x9.X9ObjectIdentifiers.id_ecPublicKey
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0188
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getParameters()
            org.bouncycastle.asn1.x9.X962Parameters r0 = org.bouncycastle.asn1.x9.X962Parameters.getInstance(r0)
            boolean r1 = r0.isNamedCurve()
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.getParameters()
            if (r1 == 0) goto L_0x014a
            r2 = r0
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r2
            org.bouncycastle.asn1.x9.X9ECParameters r0 = org.bouncycastle.crypto.ec.CustomNamedCurves.getByOID(r2)
            if (r0 != 0) goto L_0x012f
            org.bouncycastle.asn1.x9.X9ECParameters r0 = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByOID(r2)
        L_0x012f:
            org.bouncycastle.crypto.params.ECNamedDomainParameters r8 = new org.bouncycastle.crypto.params.ECNamedDomainParameters
            org.bouncycastle.math.ec.ECCurve r3 = r0.getCurve()
            org.bouncycastle.math.ec.ECPoint r4 = r0.getG()
            java.math.BigInteger r5 = r0.getN()
            java.math.BigInteger r6 = r0.getH()
            byte[] r7 = r0.getSeed()
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            goto L_0x0168
        L_0x014a:
            org.bouncycastle.asn1.x9.X9ECParameters r0 = org.bouncycastle.asn1.x9.X9ECParameters.getInstance(r0)
            org.bouncycastle.crypto.params.ECDomainParameters r8 = new org.bouncycastle.crypto.params.ECDomainParameters
            org.bouncycastle.math.ec.ECCurve r2 = r0.getCurve()
            org.bouncycastle.math.ec.ECPoint r3 = r0.getG()
            java.math.BigInteger r4 = r0.getN()
            java.math.BigInteger r5 = r0.getH()
            byte[] r6 = r0.getSeed()
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0168:
            org.bouncycastle.asn1.DEROctetString r1 = new org.bouncycastle.asn1.DEROctetString
            org.bouncycastle.asn1.DERBitString r10 = r10.getPublicKeyData()
            byte[] r10 = r10.getBytes()
            r1.<init>((byte[]) r10)
            org.bouncycastle.asn1.x9.X9ECPoint r10 = new org.bouncycastle.asn1.x9.X9ECPoint
            org.bouncycastle.math.ec.ECCurve r0 = r0.getCurve()
            r10.<init>((org.bouncycastle.math.ec.ECCurve) r0, (org.bouncycastle.asn1.ASN1OctetString) r1)
            org.bouncycastle.crypto.params.ECPublicKeyParameters r0 = new org.bouncycastle.crypto.params.ECPublicKeyParameters
            org.bouncycastle.math.ec.ECPoint r10 = r10.getPoint()
            r0.<init>(r10, r8)
            return r0
        L_0x0188:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.String r0 = "algorithm identifier in key not recognised"
            r10.<init>(r0)
            throw r10
        L_0x0190:
            org.bouncycastle.asn1.ASN1Primitive r10 = r10.parsePublicKey()
            org.bouncycastle.asn1.ASN1Integer r10 = (org.bouncycastle.asn1.ASN1Integer) r10
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getParameters()
            if (r0 == 0) goto L_0x01b5
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.toASN1Primitive()
            org.bouncycastle.asn1.x509.DSAParameter r0 = org.bouncycastle.asn1.x509.DSAParameter.getInstance(r0)
            org.bouncycastle.crypto.params.DSAParameters r3 = new org.bouncycastle.crypto.params.DSAParameters
            java.math.BigInteger r1 = r0.getP()
            java.math.BigInteger r2 = r0.getQ()
            java.math.BigInteger r0 = r0.getG()
            r3.<init>(r1, r2, r0)
        L_0x01b5:
            org.bouncycastle.crypto.params.DSAPublicKeyParameters r0 = new org.bouncycastle.crypto.params.DSAPublicKeyParameters
            java.math.BigInteger r10 = r10.getValue()
            r0.<init>(r10, r3)
            return r0
        L_0x01bf:
            org.bouncycastle.asn1.ASN1Primitive r10 = r10.parsePublicKey()
            org.bouncycastle.asn1.pkcs.RSAPublicKey r10 = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(r10)
            org.bouncycastle.crypto.params.RSAKeyParameters r0 = new org.bouncycastle.crypto.params.RSAKeyParameters
            java.math.BigInteger r1 = r10.getModulus()
            java.math.BigInteger r10 = r10.getPublicExponent()
            r0.<init>(r2, r1, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.util.PublicKeyFactory.createKey(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo):org.bouncycastle.crypto.params.AsymmetricKeyParameter");
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }
}
