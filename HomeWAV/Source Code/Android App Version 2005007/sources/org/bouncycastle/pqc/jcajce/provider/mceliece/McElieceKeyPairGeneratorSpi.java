package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyPairGenerator;
import org.bouncycastle.pqc.crypto.mceliece.McElieceParameters;
import org.bouncycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.McElieceKeyGenParameterSpec;

public class McElieceKeyPairGeneratorSpi extends KeyPairGenerator {
    McElieceKeyPairGenerator kpg;

    public McElieceKeyPairGeneratorSpi() {
        super("McEliece");
    }

    public KeyPair generateKeyPair() {
        AsymmetricCipherKeyPair generateKeyPair = this.kpg.generateKeyPair();
        return new KeyPair(new BCMcEliecePublicKey((McEliecePublicKeyParameters) generateKeyPair.getPublic()), new BCMcEliecePrivateKey((McEliecePrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        try {
            initialize(new McElieceKeyGenParameterSpec());
        } catch (InvalidAlgorithmParameterException unused) {
        }
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        this.kpg = new McElieceKeyPairGenerator();
        super.initialize(algorithmParameterSpec);
        McElieceKeyGenParameterSpec mcElieceKeyGenParameterSpec = (McElieceKeyGenParameterSpec) algorithmParameterSpec;
        this.kpg.init(new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters(mcElieceKeyGenParameterSpec.getM(), mcElieceKeyGenParameterSpec.getT())));
    }
}
