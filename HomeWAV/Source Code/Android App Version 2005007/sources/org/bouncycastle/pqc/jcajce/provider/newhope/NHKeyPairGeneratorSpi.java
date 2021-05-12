package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.newhope.NHKeyPairGenerator;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;

public class NHKeyPairGeneratorSpi extends KeyPairGenerator {
    NHKeyPairGenerator engine = new NHKeyPairGenerator();
    boolean initialised = false;
    SecureRandom random = new SecureRandom();

    public NHKeyPairGeneratorSpi() {
        super("NH");
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.engine.init(new KeyGenerationParameters(this.random, 1024));
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCNHPublicKey((NHPublicKeyParameters) generateKeyPair.getPublic()), new BCNHPrivateKey((NHPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.engine.init(new KeyGenerationParameters(secureRandom, 1024));
        this.initialised = true;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        this.engine.init(new KeyGenerationParameters(secureRandom, 1024));
        this.initialised = true;
    }
}
