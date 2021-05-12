package fm.liveswitch;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;

class BouncyCastleEcdsaCrypto {
    BouncyCastleEcdsaCrypto() {
    }

    private static String getCurveName(EcdsaNamedCurve ecdsaNamedCurve) {
        if (ecdsaNamedCurve == EcdsaNamedCurve.P256) {
            return "P-256";
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P384) {
            return "P-384";
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P521) {
            return "P-521";
        }
        throw new RuntimeException("Unsupported named curve.");
    }

    private static String getAlgorithm(EcdsaNamedCurve ecdsaNamedCurve) {
        if (ecdsaNamedCurve == EcdsaNamedCurve.P256) {
            return "SHA256WithECDSA";
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P384) {
            return "SHA384WithECDSA";
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P521) {
            return "SHA512WithECDSA";
        }
        throw new RuntimeException("Unsupported named curve.");
    }

    public static EcdsaKey createKey(EcdsaNamedCurve ecdsaNamedCurve) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        ECDomainParameters domainParameters = getDomainParameters(ecdsaNamedCurve);
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, new SecureRandom()));
        AsymmetricCipherKeyPair generateKeyPair = eCKeyPairGenerator.generateKeyPair();
        byte[] encoded = domainParameters.getCurve().fromBigInteger(((ECPrivateKeyParameters) generateKeyPair.getPrivate()).getD()).getEncoded();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) generateKeyPair.getPublic();
        byte[] encoded2 = domainParameters.getCurve().fromBigInteger(eCPublicKeyParameters.getQ().getAffineXCoord().toBigInteger()).getEncoded();
        byte[] encoded3 = domainParameters.getCurve().fromBigInteger(eCPublicKeyParameters.getQ().getAffineYCoord().toBigInteger()).getEncoded();
        byte[] bArr = new byte[(encoded2.length + encoded3.length)];
        BitAssistant.copy(encoded2, 0, bArr, 0, encoded2.length);
        BitAssistant.copy(encoded3, 0, bArr, encoded2.length, encoded3.length);
        EcdsaKey ecdsaKey = new EcdsaKey();
        ecdsaKey.setPrivateKey(encoded);
        ecdsaKey.setNamedCurve(ecdsaNamedCurve);
        ecdsaKey.setPublicKey(bArr);
        return ecdsaKey;
    }

    public static byte[] sign(byte[] bArr, EcdsaKey ecdsaKey) {
        try {
            AsymmetricKeyParameter privateKeyParameters = getPrivateKeyParameters(ecdsaKey);
            DSADigestSigner dSADigestSigner = new DSADigestSigner(new ECDSASigner(), getDigest(ecdsaKey.getNamedCurve()));
            dSADigestSigner.init(true, privateKeyParameters);
            dSADigestSigner.update(bArr, 0, bArr.length);
            return dSADigestSigner.generateSignature();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, EcdsaKey ecdsaKey) {
        AsymmetricKeyParameter publicKeyParameters = getPublicKeyParameters(ecdsaKey);
        DSADigestSigner dSADigestSigner = new DSADigestSigner(new ECDSASigner(), getDigest(ecdsaKey.getNamedCurve()));
        dSADigestSigner.init(false, publicKeyParameters);
        dSADigestSigner.update(bArr, 0, bArr.length);
        return dSADigestSigner.verifySignature(bArr2);
    }

    static Digest getDigest(EcdsaNamedCurve ecdsaNamedCurve) {
        if (ecdsaNamedCurve == EcdsaNamedCurve.P256) {
            return new SHA256Digest();
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P384) {
            return new SHA384Digest();
        }
        if (ecdsaNamedCurve == EcdsaNamedCurve.P521) {
            return new SHA512Digest();
        }
        throw new RuntimeException("Unsupported named curve.");
    }

    static ECDomainParameters getDomainParameters(EcdsaNamedCurve ecdsaNamedCurve) {
        X9ECParameters x9ECParameters;
        if (ecdsaNamedCurve == EcdsaNamedCurve.P256) {
            x9ECParameters = NISTNamedCurves.getByName("P-256");
        } else if (ecdsaNamedCurve == EcdsaNamedCurve.P384) {
            x9ECParameters = NISTNamedCurves.getByName("P-384");
        } else if (ecdsaNamedCurve == EcdsaNamedCurve.P521) {
            x9ECParameters = NISTNamedCurves.getByName("P-521");
        } else {
            throw new RuntimeException("Unsupported named curve.");
        }
        return new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH());
    }

    static AsymmetricKeyParameter getPrivateKeyParameters(EcdsaKey ecdsaKey) {
        return new ECPrivateKeyParameters(new BigInteger(1, ecdsaKey.getPrivateKey()), getDomainParameters(ecdsaKey.getNamedCurve()));
    }

    static AsymmetricKeyParameter getPublicKeyParameters(EcdsaKey ecdsaKey) {
        BigInteger bigInteger = new BigInteger(1, BitAssistant.subArray(ecdsaKey.getPublicKey(), 0, ecdsaKey.getPublicKey().length / 2));
        BigInteger bigInteger2 = new BigInteger(1, BitAssistant.subArray(ecdsaKey.getPublicKey(), ecdsaKey.getPublicKey().length / 2));
        ECDomainParameters domainParameters = getDomainParameters(ecdsaKey.getNamedCurve());
        return new ECPublicKeyParameters(domainParameters.getCurve().createPoint(bigInteger, bigInteger2), domainParameters);
    }
}
