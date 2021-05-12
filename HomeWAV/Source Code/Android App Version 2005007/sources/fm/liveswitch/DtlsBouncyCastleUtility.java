package fm.liveswitch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Vector;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsUtils;

class DtlsBouncyCastleUtility {
    DtlsBouncyCastleUtility() {
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(Vector vector, short s) {
        if (vector == null) {
            return null;
        }
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) it.next();
            if (signatureAndHashAlgorithm.getSignature() == s) {
                return signatureAndHashAlgorithm;
            }
        }
        return null;
    }

    public static Certificate getCertificate(DtlsCertificate dtlsCertificate) {
        try {
            byte[] bytes = dtlsCertificate.getBytes();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = bytes.length;
            TlsUtils.writeUint24(length + 3, byteArrayOutputStream);
            TlsUtils.writeUint24(length, byteArrayOutputStream);
            byteArrayOutputStream.write(bytes, 0, bytes.length);
            return Certificate.parse(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static AsymmetricKeyParameter getRsaPrivateKey(DtlsCertificate dtlsCertificate) {
        AsymmetricKey key = dtlsCertificate.getKey();
        if (key.getType() != AsymmetricKeyType.Rsa) {
            return null;
        }
        RsaKey rsaKey = (RsaKey) key;
        return new RSAPrivateCrtKeyParameters(new BigInteger(1, rsaKey.getModulus()), new BigInteger(1, rsaKey.getPublicExponent()), new BigInteger(1, rsaKey.getPrivateExponent()), new BigInteger(1, rsaKey.getPrime1()), new BigInteger(1, rsaKey.getPrime2()), new BigInteger(1, rsaKey.getExponent1()), new BigInteger(1, rsaKey.getExponent2()), new BigInteger(1, rsaKey.getCoefficient()));
    }

    public static AsymmetricKeyParameter getEcdsaPrivateKey(DtlsCertificate dtlsCertificate) {
        AsymmetricKey key = dtlsCertificate.getKey();
        if (key.getType() == AsymmetricKeyType.Ecdsa) {
            return BouncyCastleEcdsaCrypto.getPrivateKeyParameters((EcdsaKey) key);
        }
        return null;
    }

    public static int convertCipherSuite(DtlsCipherSuite dtlsCipherSuite) {
        if (dtlsCipherSuite == DtlsCipherSuite.RsaAes128Sha) {
            return 47;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheRsaAes128Sha) {
            return CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheEcdsaAes128Sha) {
            return CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.RsaAes128GcmSha256) {
            return 156;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheRsaAes128GcmSha256) {
            return CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheEcdsaAes128GcmSha256) {
            return CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.RsaAes128CbcSha256) {
            return 60;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheRsaAes128CbcSha256) {
            return CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256;
        }
        if (dtlsCipherSuite == DtlsCipherSuite.EcdheEcdsaAes128CbcSha256) {
            return CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256;
        }
        return -1;
    }
}
