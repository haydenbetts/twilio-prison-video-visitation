package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.agreement.DHStandardGroups;
import org.bouncycastle.crypto.params.DHParameters;

public abstract class DefaultTlsServer extends AbstractTlsServer {
    public DefaultTlsServer() {
    }

    public DefaultTlsServer(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHEKeyExchange(int i) {
        return new TlsDHEKeyExchange(i, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHKeyExchange(int i) {
        return new TlsDHKeyExchange(i, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHEKeyExchange(int i) {
        return new TlsECDHEKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHKeyExchange(int i) {
        return new TlsECDHKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }

    /* access modifiers changed from: protected */
    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, 159, 158, 107, 103, 57, 51, 157, 156, 61, 60, 53, 47};
    }

    public TlsCredentials getCredentials() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return getRSAEncryptionCredentials();
        }
        if (keyExchangeAlgorithm == 3) {
            return getDSASignerCredentials();
        }
        if (keyExchangeAlgorithm != 5) {
            if (keyExchangeAlgorithm == 17) {
                return getECDSASignerCredentials();
            }
            if (keyExchangeAlgorithm != 19) {
                if (keyExchangeAlgorithm == 20) {
                    return null;
                }
                throw new TlsFatalAlert(80);
            }
        }
        return getRSASignerCredentials();
    }

    /* access modifiers changed from: protected */
    public DHParameters getDHParameters() {
        return DHStandardGroups.rfc5114_2048_256;
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getECDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        if (keyExchangeAlgorithm == 1) {
            return createRSAKeyExchange();
        }
        if (keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5) {
            return createDHEKeyExchange(keyExchangeAlgorithm);
        }
        if (keyExchangeAlgorithm == 7 || keyExchangeAlgorithm == 9) {
            return createDHKeyExchange(keyExchangeAlgorithm);
        }
        switch (keyExchangeAlgorithm) {
            case 16:
            case 18:
            case 20:
                return createECDHKeyExchange(keyExchangeAlgorithm);
            case 17:
            case 19:
                return createECDHEKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public TlsEncryptionCredentials getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }
}
