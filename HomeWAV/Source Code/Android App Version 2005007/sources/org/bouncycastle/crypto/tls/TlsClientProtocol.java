package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.util.Arrays;

public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication = null;
    protected CertificateRequest certificateRequest = null;
    protected CertificateStatus certificateStatus = null;
    protected TlsKeyExchange keyExchange = null;
    protected byte[] selectedSessionID = null;
    protected TlsClient tlsClient = null;
    TlsClientContextImpl tlsClientContext = null;

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient2) throws IOException {
        SessionParameters exportSessionParameters;
        if (tlsClient2 == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        } else if (this.tlsClient == null) {
            this.tlsClient = tlsClient2;
            this.securityParameters = new SecurityParameters();
            this.securityParameters.entity = 1;
            this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
            this.securityParameters.clientRandom = createRandomBlock(tlsClient2.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
            this.tlsClient.init(this.tlsClientContext);
            this.recordStream.init(this.tlsClientContext);
            TlsSession sessionToResume = tlsClient2.getSessionToResume();
            if (!(sessionToResume == null || !sessionToResume.isResumable() || (exportSessionParameters = sessionToResume.exportSessionParameters()) == null)) {
                this.tlsSession = sessionToResume;
                this.sessionParameters = exportSessionParameters;
            }
            sendClientHelloMessage();
            this.connection_state = 1;
            blockForHandshake();
        } else {
            throw new IllegalStateException("'connect' can only be called once");
        }
    }

    /* access modifiers changed from: protected */
    public TlsContext getContext() {
        return this.tlsClientContext;
    }

    /* access modifiers changed from: package-private */
    public AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    /* access modifiers changed from: protected */
    public TlsPeer getPeer() {
        return this.tlsClient;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
        r9.keyExchange.skipServerCredentials();
        r9.authentication = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r9.keyExchange.skipServerKeyExchange();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006c, code lost:
        assertEmpty(r0);
        r9.connection_state = 8;
        r9.recordStream.getHandshakeHash().sealHashAlgorithms();
        r10 = r9.tlsClient.getClientSupplementalData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0082, code lost:
        if (r10 == null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0084, code lost:
        sendSupplementalDataMessage(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0087, code lost:
        r9.connection_state = 9;
        r10 = r9.certificateRequest;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        if (r10 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
        r9.keyExchange.skipClientCredentials();
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0096, code lost:
        r10 = r9.authentication.getClientCredentials(r10);
        r0 = r9.keyExchange;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009e, code lost:
        if (r10 != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a0, code lost:
        r0.skipClientCredentials();
        r0 = org.bouncycastle.crypto.tls.Certificate.EMPTY_CHAIN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a6, code lost:
        r0.processClientCredentials(r10);
        r0 = r10.getCertificate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ad, code lost:
        sendCertificateMessage(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b0, code lost:
        r9.connection_state = 10;
        sendClientKeyExchangeMessage();
        r9.connection_state = 11;
        r0 = r9.recordStream.prepareToFinish();
        r9.securityParameters.sessionHash = getCurrentPRFHash(getContext(), r0, (byte[]) null);
        establishMasterSecret(getContext(), r9.keyExchange);
        r9.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e9, code lost:
        if (r10 == null) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ed, code lost:
        if ((r10 instanceof org.bouncycastle.crypto.tls.TlsSignerCredentials) == false) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ef, code lost:
        r10 = (org.bouncycastle.crypto.tls.TlsSignerCredentials) r10;
        r11 = org.bouncycastle.crypto.tls.TlsUtils.getSignatureAndHashAlgorithm(getContext(), r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f9, code lost:
        if (r11 != null) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fb, code lost:
        r0 = r9.securityParameters.getSessionHash();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0102, code lost:
        r0 = r0.getFinalHash(r11.getHash());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010a, code lost:
        sendCertificateVerifyMessage(new org.bouncycastle.crypto.tls.DigitallySigned(r11, r10.generateCertificateSignature(r0)));
        r9.connection_state = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011a, code lost:
        sendChangeCipherSpecMessage();
        sendFinishedMessage();
        r9.connection_state = 13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleHandshakeMessage(short r10, byte[] r11) throws java.io.IOException {
        /*
            r9 = this;
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r11)
            boolean r11 = r9.resumedSession
            r1 = 15
            r2 = 20
            r3 = 16
            r4 = 13
            r5 = 2
            r6 = 10
            if (r11 == 0) goto L_0x0030
            if (r10 != r2) goto L_0x002a
            short r10 = r9.connection_state
            if (r10 != r5) goto L_0x002a
            r9.processFinishedMessage(r0)
            r9.connection_state = r1
            r9.sendFinishedMessage()
            r9.connection_state = r4
            r9.connection_state = r3
            r9.completeHandshake()
            return
        L_0x002a:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0030:
            if (r10 == 0) goto L_0x0298
            r11 = 0
            if (r10 == r5) goto L_0x0245
            r7 = 14
            r8 = 4
            if (r10 == r8) goto L_0x0228
            if (r10 == r2) goto L_0x0205
            r1 = 22
            r2 = 5
            if (r10 == r1) goto L_0x01e4
            r1 = 23
            if (r10 == r1) goto L_0x01d1
            r1 = 40
            r3 = 6
            r7 = 3
            switch(r10) {
                case 11: goto L_0x0191;
                case 12: goto L_0x016a;
                case 13: goto L_0x0124;
                case 14: goto L_0x0052;
                default: goto L_0x004c;
            }
        L_0x004c:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0052:
            short r10 = r9.connection_state
            switch(r10) {
                case 2: goto L_0x005d;
                case 3: goto L_0x0060;
                case 4: goto L_0x0067;
                case 5: goto L_0x0067;
                case 6: goto L_0x006c;
                case 7: goto L_0x006c;
                default: goto L_0x0057;
            }
        L_0x0057:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r1)
            throw r10
        L_0x005d:
            r9.handleSupplementalData(r11)
        L_0x0060:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.skipServerCredentials()
            r9.authentication = r11
        L_0x0067:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.skipServerKeyExchange()
        L_0x006c:
            assertEmpty(r0)
            r10 = 8
            r9.connection_state = r10
            org.bouncycastle.crypto.tls.RecordStream r10 = r9.recordStream
            org.bouncycastle.crypto.tls.TlsHandshakeHash r10 = r10.getHandshakeHash()
            r10.sealHashAlgorithms()
            org.bouncycastle.crypto.tls.TlsClient r10 = r9.tlsClient
            java.util.Vector r10 = r10.getClientSupplementalData()
            if (r10 == 0) goto L_0x0087
            r9.sendSupplementalDataMessage(r10)
        L_0x0087:
            r10 = 9
            r9.connection_state = r10
            org.bouncycastle.crypto.tls.CertificateRequest r10 = r9.certificateRequest
            if (r10 != 0) goto L_0x0096
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.skipClientCredentials()
            r10 = r11
            goto L_0x00b0
        L_0x0096:
            org.bouncycastle.crypto.tls.TlsAuthentication r0 = r9.authentication
            org.bouncycastle.crypto.tls.TlsCredentials r10 = r0.getClientCredentials(r10)
            org.bouncycastle.crypto.tls.TlsKeyExchange r0 = r9.keyExchange
            if (r10 != 0) goto L_0x00a6
            r0.skipClientCredentials()
            org.bouncycastle.crypto.tls.Certificate r0 = org.bouncycastle.crypto.tls.Certificate.EMPTY_CHAIN
            goto L_0x00ad
        L_0x00a6:
            r0.processClientCredentials(r10)
            org.bouncycastle.crypto.tls.Certificate r0 = r10.getCertificate()
        L_0x00ad:
            r9.sendCertificateMessage(r0)
        L_0x00b0:
            r9.connection_state = r6
            r9.sendClientKeyExchangeMessage()
            r0 = 11
            r9.connection_state = r0
            org.bouncycastle.crypto.tls.RecordStream r0 = r9.recordStream
            org.bouncycastle.crypto.tls.TlsHandshakeHash r0 = r0.prepareToFinish()
            org.bouncycastle.crypto.tls.SecurityParameters r1 = r9.securityParameters
            org.bouncycastle.crypto.tls.TlsContext r2 = r9.getContext()
            byte[] r11 = getCurrentPRFHash(r2, r0, r11)
            r1.sessionHash = r11
            org.bouncycastle.crypto.tls.TlsContext r11 = r9.getContext()
            org.bouncycastle.crypto.tls.TlsKeyExchange r1 = r9.keyExchange
            establishMasterSecret(r11, r1)
            org.bouncycastle.crypto.tls.RecordStream r11 = r9.recordStream
            org.bouncycastle.crypto.tls.TlsPeer r1 = r9.getPeer()
            org.bouncycastle.crypto.tls.TlsCompression r1 = r1.getCompression()
            org.bouncycastle.crypto.tls.TlsPeer r2 = r9.getPeer()
            org.bouncycastle.crypto.tls.TlsCipher r2 = r2.getCipher()
            r11.setPendingConnectionState(r1, r2)
            if (r10 == 0) goto L_0x011a
            boolean r11 = r10 instanceof org.bouncycastle.crypto.tls.TlsSignerCredentials
            if (r11 == 0) goto L_0x011a
            org.bouncycastle.crypto.tls.TlsSignerCredentials r10 = (org.bouncycastle.crypto.tls.TlsSignerCredentials) r10
            org.bouncycastle.crypto.tls.TlsContext r11 = r9.getContext()
            org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm r11 = org.bouncycastle.crypto.tls.TlsUtils.getSignatureAndHashAlgorithm(r11, r10)
            if (r11 != 0) goto L_0x0102
            org.bouncycastle.crypto.tls.SecurityParameters r0 = r9.securityParameters
            byte[] r0 = r0.getSessionHash()
            goto L_0x010a
        L_0x0102:
            short r1 = r11.getHash()
            byte[] r0 = r0.getFinalHash(r1)
        L_0x010a:
            byte[] r10 = r10.generateCertificateSignature(r0)
            org.bouncycastle.crypto.tls.DigitallySigned r0 = new org.bouncycastle.crypto.tls.DigitallySigned
            r0.<init>(r11, r10)
            r9.sendCertificateVerifyMessage(r0)
            r10 = 12
            r9.connection_state = r10
        L_0x011a:
            r9.sendChangeCipherSpecMessage()
            r9.sendFinishedMessage()
            r9.connection_state = r4
            goto L_0x02a2
        L_0x0124:
            short r10 = r9.connection_state
            if (r10 == r8) goto L_0x0133
            if (r10 == r2) goto L_0x0133
            if (r10 != r3) goto L_0x012d
            goto L_0x0138
        L_0x012d:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0133:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.skipServerKeyExchange()
        L_0x0138:
            org.bouncycastle.crypto.tls.TlsAuthentication r10 = r9.authentication
            if (r10 == 0) goto L_0x0164
            org.bouncycastle.crypto.tls.TlsContext r10 = r9.getContext()
            org.bouncycastle.crypto.tls.CertificateRequest r10 = org.bouncycastle.crypto.tls.CertificateRequest.parse(r10, r0)
            r9.certificateRequest = r10
            assertEmpty(r0)
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            org.bouncycastle.crypto.tls.CertificateRequest r11 = r9.certificateRequest
            r10.validateCertificateRequest(r11)
            org.bouncycastle.crypto.tls.RecordStream r10 = r9.recordStream
            org.bouncycastle.crypto.tls.TlsHandshakeHash r10 = r10.getHandshakeHash()
            org.bouncycastle.crypto.tls.CertificateRequest r11 = r9.certificateRequest
            java.util.Vector r11 = r11.getSupportedSignatureAlgorithms()
            org.bouncycastle.crypto.tls.TlsUtils.trackHashAlgorithms(r10, r11)
            r10 = 7
            r9.connection_state = r10
            goto L_0x02a2
        L_0x0164:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r1)
            throw r10
        L_0x016a:
            short r10 = r9.connection_state
            if (r10 == r5) goto L_0x017b
            if (r10 == r7) goto L_0x017e
            if (r10 == r8) goto L_0x0185
            if (r10 != r2) goto L_0x0175
            goto L_0x0185
        L_0x0175:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x017b:
            r9.handleSupplementalData(r11)
        L_0x017e:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.skipServerCredentials()
            r9.authentication = r11
        L_0x0185:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            r10.processServerKeyExchange(r0)
            assertEmpty(r0)
            r9.connection_state = r3
            goto L_0x02a2
        L_0x0191:
            short r10 = r9.connection_state
            if (r10 == r5) goto L_0x019e
            if (r10 != r7) goto L_0x0198
            goto L_0x01a1
        L_0x0198:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x019e:
            r9.handleSupplementalData(r11)
        L_0x01a1:
            org.bouncycastle.crypto.tls.Certificate r10 = org.bouncycastle.crypto.tls.Certificate.parse(r0)
            r9.peerCertificate = r10
            assertEmpty(r0)
            org.bouncycastle.crypto.tls.Certificate r10 = r9.peerCertificate
            if (r10 == 0) goto L_0x01b6
            org.bouncycastle.crypto.tls.Certificate r10 = r9.peerCertificate
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x01b9
        L_0x01b6:
            r10 = 0
            r9.allowCertificateStatus = r10
        L_0x01b9:
            org.bouncycastle.crypto.tls.TlsKeyExchange r10 = r9.keyExchange
            org.bouncycastle.crypto.tls.Certificate r11 = r9.peerCertificate
            r10.processServerCertificate(r11)
            org.bouncycastle.crypto.tls.TlsClient r10 = r9.tlsClient
            org.bouncycastle.crypto.tls.TlsAuthentication r10 = r10.getAuthentication()
            r9.authentication = r10
            org.bouncycastle.crypto.tls.Certificate r11 = r9.peerCertificate
            r10.notifyServerCertificate(r11)
            r9.connection_state = r8
            goto L_0x02a2
        L_0x01d1:
            short r10 = r9.connection_state
            if (r10 != r5) goto L_0x01de
            java.util.Vector r10 = readSupplementalDataMessage(r0)
            r9.handleSupplementalData(r10)
            goto L_0x02a2
        L_0x01de:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x01e4:
            short r10 = r9.connection_state
            if (r10 != r8) goto L_0x01ff
            boolean r10 = r9.allowCertificateStatus
            if (r10 == 0) goto L_0x01f9
            org.bouncycastle.crypto.tls.CertificateStatus r10 = org.bouncycastle.crypto.tls.CertificateStatus.parse(r0)
            r9.certificateStatus = r10
            assertEmpty(r0)
            r9.connection_state = r2
            goto L_0x02a2
        L_0x01f9:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x01ff:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0205:
            short r10 = r9.connection_state
            if (r10 == r4) goto L_0x0212
            if (r10 != r7) goto L_0x020c
            goto L_0x0216
        L_0x020c:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0212:
            boolean r10 = r9.expectSessionTicket
            if (r10 != 0) goto L_0x0222
        L_0x0216:
            r9.processFinishedMessage(r0)
            r9.connection_state = r1
            r9.connection_state = r3
            r9.completeHandshake()
            goto L_0x02a2
        L_0x0222:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0228:
            short r10 = r9.connection_state
            if (r10 != r4) goto L_0x023f
            boolean r10 = r9.expectSessionTicket
            if (r10 == 0) goto L_0x0239
            r9.invalidateSession()
            r9.receiveNewSessionTicketMessage(r0)
            r9.connection_state = r7
            goto L_0x02a2
        L_0x0239:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x023f:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0245:
            short r10 = r9.connection_state
            r1 = 1
            if (r10 != r1) goto L_0x0292
            r9.receiveServerHelloMessage(r0)
            r9.connection_state = r5
            org.bouncycastle.crypto.tls.RecordStream r10 = r9.recordStream
            r10.notifyHelloComplete()
            r9.applyMaxFragmentLengthExtension()
            boolean r10 = r9.resumedSession
            if (r10 == 0) goto L_0x0282
            org.bouncycastle.crypto.tls.SecurityParameters r10 = r9.securityParameters
            org.bouncycastle.crypto.tls.SessionParameters r11 = r9.sessionParameters
            byte[] r11 = r11.getMasterSecret()
            byte[] r11 = org.bouncycastle.util.Arrays.clone((byte[]) r11)
            r10.masterSecret = r11
            org.bouncycastle.crypto.tls.RecordStream r10 = r9.recordStream
            org.bouncycastle.crypto.tls.TlsPeer r11 = r9.getPeer()
            org.bouncycastle.crypto.tls.TlsCompression r11 = r11.getCompression()
            org.bouncycastle.crypto.tls.TlsPeer r0 = r9.getPeer()
            org.bouncycastle.crypto.tls.TlsCipher r0 = r0.getCipher()
            r10.setPendingConnectionState(r11, r0)
            r9.sendChangeCipherSpecMessage()
            goto L_0x02a2
        L_0x0282:
            r9.invalidateSession()
            byte[] r10 = r9.selectedSessionID
            int r0 = r10.length
            if (r0 <= 0) goto L_0x02a2
            org.bouncycastle.crypto.tls.TlsSessionImpl r0 = new org.bouncycastle.crypto.tls.TlsSessionImpl
            r0.<init>(r10, r11)
            r9.tlsSession = r0
            goto L_0x02a2
        L_0x0292:
            org.bouncycastle.crypto.tls.TlsFatalAlert r10 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r10.<init>(r6)
            throw r10
        L_0x0298:
            assertEmpty(r0)
            short r10 = r9.connection_state
            if (r10 != r3) goto L_0x02a2
            r9.refuseRenegotiation()
        L_0x02a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsClientProtocol.handleHandshakeMessage(short, byte[]):void");
    }

    /* access modifiers changed from: protected */
    public void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = 3;
        TlsKeyExchange keyExchange2 = this.tlsClient.getKeyExchange();
        this.keyExchange = keyExchange2;
        keyExchange2.init(getContext());
    }

    /* access modifiers changed from: protected */
    public void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(parse);
    }

    /* access modifiers changed from: protected */
    public void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (readVersion.isDTLS()) {
            throw new TlsFatalAlert(47);
        } else if (!readVersion.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert(47);
        } else if (readVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            this.recordStream.setWriteVersion(readVersion);
            getContextAdmin().setServerVersion(readVersion);
            this.tlsClient.notifyServerVersion(readVersion);
            this.securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream) byteArrayInputStream);
            byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
            this.selectedSessionID = readOpaque8;
            if (readOpaque8.length <= 32) {
                this.tlsClient.notifySessionID(readOpaque8);
                boolean z = false;
                this.resumedSession = this.selectedSessionID.length > 0 && this.tlsSession != null && Arrays.areEqual(this.selectedSessionID, this.tlsSession.getSessionID());
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (!Arrays.contains(this.offeredCipherSuites, readUint16) || readUint16 == 0 || CipherSuite.isSCSV(readUint16) || !TlsUtils.isValidCipherSuiteForVersion(readUint16, getContext().getServerVersion())) {
                    throw new TlsFatalAlert(47);
                }
                this.tlsClient.notifySelectedCipherSuite(readUint16);
                short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
                if (Arrays.contains(this.offeredCompressionMethods, readUint8)) {
                    this.tlsClient.notifySelectedCompressionMethod(readUint8);
                    this.serverExtensions = readExtensions(byteArrayInputStream);
                    if (this.serverExtensions != null) {
                        Enumeration keys = this.serverExtensions.keys();
                        while (keys.hasMoreElements()) {
                            Integer num = (Integer) keys.nextElement();
                            if (!num.equals(EXT_RenegotiationInfo)) {
                                if (TlsUtils.getExtensionData(this.clientExtensions, num) != null) {
                                    boolean z2 = this.resumedSession;
                                } else {
                                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                                }
                            }
                        }
                    }
                    byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo);
                    if (extensionData != null) {
                        this.secure_renegotiation = true;
                        if (!Arrays.constantTimeAreEqual(extensionData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert(40);
                        }
                    }
                    this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
                    Hashtable hashtable = this.clientExtensions;
                    Hashtable hashtable2 = this.serverExtensions;
                    if (this.resumedSession) {
                        if (readUint16 == this.sessionParameters.getCipherSuite() && readUint8 == this.sessionParameters.getCompressionAlgorithm()) {
                            hashtable = null;
                            hashtable2 = this.sessionParameters.readServerExtensions();
                        } else {
                            throw new TlsFatalAlert(47);
                        }
                    }
                    this.securityParameters.cipherSuite = readUint16;
                    this.securityParameters.compressionAlgorithm = readUint8;
                    if (hashtable2 != null) {
                        boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable2);
                        if (!hasEncryptThenMACExtension || TlsUtils.isBlockCipherSuite(readUint16)) {
                            this.securityParameters.encryptThenMAC = hasEncryptThenMACExtension;
                            this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(hashtable2);
                            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(hashtable, hashtable2, 47);
                            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable2);
                            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request, 47);
                            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsProtocol.EXT_SessionTicket, 47)) {
                                z = true;
                            }
                            this.expectSessionTicket = z;
                        } else {
                            throw new TlsFatalAlert(47);
                        }
                    }
                    if (hashtable != null) {
                        this.tlsClient.processServerExtensions(hashtable2);
                    }
                    this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
                    this.securityParameters.verifyDataLength = 12;
                    return;
                }
                throw new TlsFatalAlert(47);
            }
            throw new TlsFatalAlert(47);
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 15);
        digitallySigned.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendClientHelloMessage() throws IOException {
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion clientVersion = this.tlsClient.getClientVersion();
        if (!clientVersion.isDTLS()) {
            getContextAdmin().setClientVersion(clientVersion);
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            if (this.tlsSession != null && ((bArr = this.tlsSession.getSessionID()) == null || bArr.length > 32)) {
                bArr = TlsUtils.EMPTY_BYTES;
            }
            boolean isFallback = this.tlsClient.isFallback();
            this.offeredCipherSuites = this.tlsClient.getCipherSuites();
            this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
            if (bArr.length > 0 && this.sessionParameters != null && (!Arrays.contains(this.offeredCipherSuites, this.sessionParameters.getCipherSuite()) || !Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
                bArr = TlsUtils.EMPTY_BYTES;
            }
            this.clientExtensions = this.tlsClient.getClientExtensions();
            TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 1);
            TlsUtils.writeVersion(clientVersion, handshakeMessage);
            handshakeMessage.write(this.securityParameters.getClientRandom());
            TlsUtils.writeOpaque8(bArr, handshakeMessage);
            boolean z = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo) == null;
            boolean z2 = !Arrays.contains(this.offeredCipherSuites, 255);
            if (z && z2) {
                this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
            }
            if (isFallback && !Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
                this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
            }
            TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, handshakeMessage);
            TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, handshakeMessage);
            if (this.clientExtensions != null) {
                writeExtensions(handshakeMessage, this.clientExtensions);
            }
            handshakeMessage.writeToRecordStream();
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void sendClientKeyExchangeMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }
}
