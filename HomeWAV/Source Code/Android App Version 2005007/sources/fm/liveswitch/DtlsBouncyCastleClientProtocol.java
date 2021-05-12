package fm.liveswitch;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.DTLSClientProtocol;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.TlsClient;

class DtlsBouncyCastleClientProtocol extends DTLSClientProtocol {
    public DtlsBouncyCastleClientProtocol() {
        super(new SecureRandom());
    }

    /* access modifiers changed from: protected */
    public void processCertificateRequest(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'certificate request' message.");
        super.processCertificateRequest(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processCertificateStatus(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'certificate status' message.");
        super.processCertificateStatus(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processFinished(byte[] bArr, byte[] bArr2) throws IOException {
        Log.debug("Processing DTLS 'finished' message.");
        super.processFinished(bArr, bArr2);
    }

    /* access modifiers changed from: protected */
    public byte[] processHelloVerifyRequest(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'hello verify request' message.");
        return super.processHelloVerifyRequest(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processNewSessionTicket(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'new session ticket' message.");
        super.processNewSessionTicket(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public Certificate processServerCertificate(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'server certificate' message.");
        return super.processServerCertificate(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processServerHello(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'server hello' message.");
        super.processServerHello(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processServerKeyExchange(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'server key exchange' message.");
        super.processServerKeyExchange(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processServerSupplementalData(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'server supplemental data' message.");
        super.processServerSupplementalData(clientHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateVerify(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        Log.debug("Generating DTLS 'certificate verify' message.");
        return super.generateCertificateVerify(clientHandshakeState, digitallySigned);
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientHello(DTLSClientProtocol.ClientHandshakeState clientHandshakeState, TlsClient tlsClient) throws IOException {
        Log.debug("Generating DTLS 'client hello' message.");
        return super.generateClientHello(clientHandshakeState, tlsClient);
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientKeyExchange(DTLSClientProtocol.ClientHandshakeState clientHandshakeState) throws IOException {
        Log.debug("Generating DTLS 'client key exchange' message.");
        return super.generateClientKeyExchange(clientHandshakeState);
    }
}
