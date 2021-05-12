package fm.liveswitch;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.DTLSServerProtocol;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;

class DtlsBouncyCastleServerProtocol extends DTLSServerProtocol {
    public DtlsBouncyCastleServerProtocol() {
        super(new SecureRandom());
    }

    /* access modifiers changed from: protected */
    public void processCertificateVerify(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        Log.debug("Processing DTLS 'certificate verify' message.");
        super.processCertificateVerify(serverHandshakeState, bArr, tlsHandshakeHash);
    }

    /* access modifiers changed from: protected */
    public void processClientCertificate(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'client certificate' message.");
        super.processClientCertificate(serverHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processClientHello(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'client hello' message.");
        super.processClientHello(serverHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processClientKeyExchange(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'client key exchange' message.");
        super.processClientKeyExchange(serverHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processClientSupplementalData(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        Log.debug("Processing DTLS 'client supplemental data' message.");
        super.processClientSupplementalData(serverHandshakeState, bArr);
    }

    /* access modifiers changed from: protected */
    public void processFinished(byte[] bArr, byte[] bArr2) throws IOException {
        Log.debug("Processing DTLS 'finished' message.");
        super.processFinished(bArr, bArr2);
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateRequest(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        Log.debug("Generating DTLS 'certificate request' message.");
        return super.generateCertificateRequest(serverHandshakeState, certificateRequest);
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateStatus(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        Log.debug("Generating DTLS 'certificate status' message.");
        return super.generateCertificateStatus(serverHandshakeState, certificateStatus);
    }

    /* access modifiers changed from: protected */
    public byte[] generateNewSessionTicket(DTLSServerProtocol.ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        Log.debug("Generating DTLS 'new session ticket' message.");
        return super.generateNewSessionTicket(serverHandshakeState, newSessionTicket);
    }

    /* access modifiers changed from: protected */
    public byte[] generateServerHello(DTLSServerProtocol.ServerHandshakeState serverHandshakeState) throws IOException {
        Log.debug("Generating DTLS 'server hello' message.");
        return super.generateServerHello(serverHandshakeState);
    }
}
