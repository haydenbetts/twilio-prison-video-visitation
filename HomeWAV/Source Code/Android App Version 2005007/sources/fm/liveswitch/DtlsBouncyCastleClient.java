package fm.liveswitch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import org.bouncycastle.crypto.tls.DTLSTransport;
import org.bouncycastle.crypto.tls.DefaultTlsClient;
import org.bouncycastle.crypto.tls.ExporterLabel;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsSRTPUtils;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.UseSRTPData;

class DtlsBouncyCastleClient extends DefaultTlsClient implements DtlsIClient {
    private DtlsCertificate certificate;
    private boolean closed;
    /* access modifiers changed from: private */
    public DTLSTransport connection;
    private Object connectionLock;
    /* access modifiers changed from: private */
    public List<DtlsMessage> handshakeFlight = new ArrayList();
    private IAction1<DataBuffer> onDataDecrypted;
    private IAction1<Exception> onError;
    private IAction1<DataBuffer> onKeyingMaterialAvailable;
    private IAction1<byte[]> onRemoteCertificate;
    private DtlsCipherSuite[] preferredCipherSuites;
    private DtlsBouncyCastleClientProtocol protocol;
    private byte[] receiveBuffer;
    public String remoteFingerprint;
    public String remoteFingerprintAlgorithm;
    private int selectedSrtpProtectionProfile;
    private IAction1<DataBuffer> sendCallback;
    private TlsSession session = null;
    private int[] supportedSrtpProtectionProfiles;
    private DtlsBouncyCastleUdpTransport transport;
    private DtlsProtocolVersion version;

    public int[] getSupportedSrtpProtectionProfiles() {
        return this.supportedSrtpProtectionProfiles;
    }

    public int getSelectedSrtpProtectionProfile() {
        return this.selectedSrtpProtectionProfile;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public String getRemoteFingerprintAlgorithm() {
        return this.remoteFingerprintAlgorithm;
    }

    public String getRemoteFingerprint() {
        return this.remoteFingerprint;
    }

    public IAction1<byte[]> getOnRemoteCertificate() {
        return this.onRemoteCertificate;
    }

    public DtlsCertificate getCertificate() {
        return this.certificate;
    }

    public DtlsCipherSuite[] getPreferredCipherSuites() {
        return this.preferredCipherSuites;
    }

    public DtlsProtocolVersion getVersion() {
        return this.version;
    }

    public IAction1<DataBuffer> getOnDataDecrypted() {
        return this.onDataDecrypted;
    }

    public void setOnDataDecrypted(IAction1<DataBuffer> iAction1) {
        this.onDataDecrypted = iAction1;
    }

    public void setOnKeyingMaterialAvailable(IAction1<DataBuffer> iAction1) {
        this.onKeyingMaterialAvailable = iAction1;
    }

    public IAction1<Exception> getOnError() {
        return this.onError;
    }

    public void setOnError(IAction1<Exception> iAction1) {
        this.onError = iAction1;
    }

    public DtlsBouncyCastleClient(DtlsCertificate dtlsCertificate, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsFingerprint dtlsFingerprint, int[] iArr, IAction1<byte[]> iAction1, final IAction1<DataBuffer> iAction12) {
        this.certificate = dtlsCertificate;
        this.preferredCipherSuites = dtlsCipherSuiteArr;
        this.version = dtlsProtocolVersion;
        this.remoteFingerprintAlgorithm = dtlsFingerprint.getAlgorithm();
        this.remoteFingerprint = dtlsFingerprint.getValue();
        this.supportedSrtpProtectionProfiles = iArr;
        this.connectionLock = new Object();
        this.onRemoteCertificate = iAction1;
        this.sendCallback = iAction12;
        this.transport = new DtlsBouncyCastleUdpTransport(new IAction1<byte[]>() {
            public void invoke(byte[] bArr) {
                if (DtlsBouncyCastleClient.this.getClosed()) {
                    return;
                }
                if (DtlsBouncyCastleClient.this.connection == null) {
                    Log.debug(String.format(Locale.getDefault(), "Sending DTLS packet (%d bytes).", new Object[]{Integer.valueOf(bArr.length)}));
                    DtlsMessage[] parseMultiple = DtlsMessage.parseMultiple(DataBuffer.wrap(bArr));
                    if (parseMultiple != null) {
                        for (DtlsMessage add : parseMultiple) {
                            DtlsBouncyCastleClient.this.handshakeFlight.add(add);
                        }
                    }
                    if (DtlsBouncyCastleClient.this.handshakeFlight.size() >= 1) {
                        DtlsMessage dtlsMessage = (DtlsMessage) DtlsBouncyCastleClient.this.handshakeFlight.get(DtlsBouncyCastleClient.this.handshakeFlight.size() - 1);
                        if (dtlsMessage.getContentType() == DtlsContentType.getHandshake() && dtlsMessage.getHandshakeType() == DtlsHandshakeType.getClientHello()) {
                            DtlsBouncyCastleClient.this.sendHandshakeFlight(iAction12);
                        } else if (DtlsBouncyCastleClient.this.handshakeFlight.size() >= 2 && ((DtlsMessage) DtlsBouncyCastleClient.this.handshakeFlight.get(DtlsBouncyCastleClient.this.handshakeFlight.size() - 2)).getContentType() == DtlsContentType.getChangeCipherSpec() && dtlsMessage.getContentType() == DtlsContentType.getHandshake()) {
                            DtlsBouncyCastleClient.this.sendHandshakeFlight(iAction12);
                        }
                    }
                } else {
                    iAction12.invoke(DataBuffer.wrap(bArr));
                }
            }
        });
    }

    public Error open() {
        try {
            DtlsBouncyCastleClientProtocol dtlsBouncyCastleClientProtocol = new DtlsBouncyCastleClientProtocol();
            this.protocol = dtlsBouncyCastleClientProtocol;
            this.connection = dtlsBouncyCastleClientProtocol.connect(this, this.transport);
            synchronized (this.connectionLock) {
                this.receiveBuffer = new byte[this.connection.getReceiveLimit()];
                processReceived();
            }
            if (this.closed) {
                return new Error(ErrorCode.DtlsKeyExchangeFailed, new Exception("Could not connect to DTLS server."));
            }
            return null;
        } catch (Exception e) {
            return new Error(ErrorCode.DtlsKeyExchangeFailed, e);
        }
    }

    /* access modifiers changed from: private */
    public void sendHandshakeFlight(IAction1<DataBuffer> iAction1) {
        DataBuffer raw = this.handshakeFlight.get(0).getRaw();
        for (int i = 1; i < this.handshakeFlight.size(); i++) {
            raw.append(this.handshakeFlight.get(i).getRaw());
        }
        this.handshakeFlight.clear();
        iAction1.invoke(raw);
    }

    public void send(DataBuffer dataBuffer) {
        DTLSTransport dTLSTransport = this.connection;
        if (dTLSTransport != null) {
            try {
                dTLSTransport.send(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
            } catch (Exception e) {
                IAction1<Exception> iAction1 = this.onError;
                if (iAction1 != null) {
                    iAction1.invoke(new Exception(String.format("DTLS client could not process outgoing message.", new Object[]{e.getMessage()})));
                }
            }
        }
    }

    public void receive(DataBuffer dataBuffer) {
        if (this.connection == null) {
            Log.debug(String.format(Locale.getDefault(), "Received DTLS packet (%d bytes).", new Object[]{Integer.valueOf(dataBuffer.getLength())}));
        }
        synchronized (this.connectionLock) {
            DtlsBouncyCastleUdpTransport dtlsBouncyCastleUdpTransport = this.transport;
            if (dtlsBouncyCastleUdpTransport != null) {
                dtlsBouncyCastleUdpTransport.push(dataBuffer);
            }
            processReceived();
        }
    }

    private void processReceived() {
        if (this.connection != null && this.receiveBuffer != null) {
            int i = 0;
            while (i != -1) {
                try {
                    DTLSTransport dTLSTransport = this.connection;
                    byte[] bArr = this.receiveBuffer;
                    i = dTLSTransport.receive(bArr, 0, bArr.length, 0);
                    if (i > 0) {
                        this.onDataDecrypted.invoke(DataBuffer.wrap(this.receiveBuffer, 0, i));
                    }
                } catch (Exception e) {
                    IAction1<Exception> iAction1 = this.onError;
                    if (iAction1 != null) {
                        iAction1.invoke(new Exception(String.format("DTLS client could not process incoming message.", new Object[]{e.getMessage()})));
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void close() {
        DTLSTransport dTLSTransport = this.connection;
        if (dTLSTransport != null) {
            try {
                dTLSTransport.close();
            } catch (Exception unused) {
            }
            this.connection = null;
        }
        DtlsBouncyCastleClientProtocol dtlsBouncyCastleClientProtocol = this.protocol;
        if (dtlsBouncyCastleClientProtocol != null) {
            try {
                dtlsBouncyCastleClientProtocol.cancel();
            } catch (Exception unused2) {
            }
            this.protocol = null;
        }
        DtlsBouncyCastleUdpTransport dtlsBouncyCastleUdpTransport = this.transport;
        if (dtlsBouncyCastleUdpTransport != null) {
            try {
                dtlsBouncyCastleUdpTransport.close();
            } catch (Exception unused3) {
            }
            this.transport = null;
        }
        this.closed = true;
    }

    public byte[] getKeyingMaterial() {
        return this.context.exportKeyingMaterial(ExporterLabel.dtls_srtp, (byte[]) null, 60);
    }

    public TlsSession getSessionToResume() {
        return this.session;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyAlertRaised(byte r6, byte r7, java.lang.String r8, java.lang.Exception r9) {
        /*
            r5 = this;
            boolean r0 = r5.closed
            if (r0 != 0) goto L_0x0067
            r0 = 1
            r1 = 0
            if (r9 != 0) goto L_0x0009
            goto L_0x0016
        L_0x0009:
            java.lang.Object[] r2 = new java.lang.Object[r0]
            java.lang.String r3 = r9.getMessage()
            r2[r1] = r3
            java.lang.String r3 = ", Inner exception: %s"
            java.lang.String.format(r3, r2)
        L_0x0016:
            java.util.Locale r2 = java.util.Locale.getDefault()
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = java.lang.String.valueOf(r6)
            r3[r1] = r4
            java.lang.String r4 = java.lang.String.valueOf(r7)
            r3[r0] = r4
            r4 = 2
            r3[r4] = r8
            java.lang.String r8 = "DTLS client raised alert. (Level: %s, Description: %s, Message: '%s')"
            java.lang.String r8 = java.lang.String.format(r2, r8, r3)
            if (r6 != r0) goto L_0x004c
            if (r7 != 0) goto L_0x0042
            java.lang.String r8 = "Local DTLS client closed connection."
            if (r9 != 0) goto L_0x003e
            fm.liveswitch.Log.debug(r8)
            goto L_0x0058
        L_0x003e:
            fm.liveswitch.Log.debug(r8, r9)
            goto L_0x0058
        L_0x0042:
            if (r9 != 0) goto L_0x0048
            fm.liveswitch.Log.warn(r8)
            goto L_0x0058
        L_0x0048:
            fm.liveswitch.Log.warn(r8, r9)
            goto L_0x0058
        L_0x004c:
            if (r6 != r4) goto L_0x004f
            goto L_0x0059
        L_0x004f:
            if (r9 != 0) goto L_0x0055
            fm.liveswitch.Log.debug(r8)
            goto L_0x0058
        L_0x0055:
            fm.liveswitch.Log.debug(r8, r9)
        L_0x0058:
            r0 = 0
        L_0x0059:
            if (r0 == 0) goto L_0x0067
            fm.liveswitch.IAction1<java.lang.Exception> r6 = r5.onError
            if (r6 == 0) goto L_0x0067
            java.lang.Exception r7 = new java.lang.Exception
            r7.<init>(r8)
            r6.invoke(r7)
        L_0x0067:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DtlsBouncyCastleClient.notifyAlertRaised(byte, byte, java.lang.String, java.lang.Exception):void");
    }

    public void notifyAlertReceived(byte b, byte b2) {
        IAction1<Exception> iAction1;
        if (!this.closed) {
            boolean z = false;
            String format = String.format(Locale.getDefault(), "DTLS client received alert. (Level: %s, Description: %s)", new Object[]{String.valueOf(b), String.valueOf(b2)});
            if (b == 1) {
                if (b2 == 0) {
                    format = "Remote DTLS server closed connection.";
                    z = true;
                } else {
                    Log.warn(format);
                }
            } else if (b == 2) {
                Log.error(format);
            } else {
                Log.debug(format);
            }
            if (z && (iAction1 = this.onError) != null) {
                iAction1.invoke(new Exception(format));
            }
        }
    }

    public ProtocolVersion getClientVersion() {
        if (this.version == DtlsProtocolVersion.Dtls12) {
            return ProtocolVersion.DTLSv12;
        }
        return ProtocolVersion.DTLSv10;
    }

    public ProtocolVersion getMinimumVersion() {
        if (this.version == DtlsProtocolVersion.Dtls12) {
            return ProtocolVersion.DTLSv12;
        }
        return ProtocolVersion.DTLSv10;
    }

    public int[] getCipherSuites() {
        int length = getPreferredCipherSuites().length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = DtlsBouncyCastleUtility.convertCipherSuite(getPreferredCipherSuites()[i]);
        }
        return iArr;
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        int[] iArr = this.supportedSrtpProtectionProfiles;
        if (iArr != null) {
            TlsSRTPUtils.addUseSRTPExtension(ensureExtensionsInitialised, new UseSRTPData(iArr, (byte[]) null));
        }
        if (TlsECCUtils.containsECCCipherSuites(getCipherSuites())) {
            AsymmetricKey key = getCertificate().getKey();
            if (key.getType() == AsymmetricKeyType.Ecdsa) {
                EcdsaKey ecdsaKey = (EcdsaKey) key;
                if (ecdsaKey.getNamedCurve() == EcdsaNamedCurve.P256) {
                    this.namedCurves = new int[]{23};
                } else if (ecdsaKey.getNamedCurve() == EcdsaNamedCurve.P384) {
                    this.namedCurves = new int[]{24};
                } else if (ecdsaKey.getNamedCurve() == EcdsaNamedCurve.P521) {
                    this.namedCurves = new int[]{25};
                }
                this.clientECPointFormats = new short[]{0};
                TlsECCUtils.addSupportedEllipticCurvesExtension(ensureExtensionsInitialised, this.namedCurves);
                TlsECCUtils.addSupportedPointFormatsExtension(ensureExtensionsInitialised, this.clientECPointFormats);
            }
        }
        return ensureExtensionsInitialised;
    }

    public void processServerExtensions(Hashtable hashtable) throws IOException {
        UseSRTPData useSRTPExtension;
        super.processServerExtensions(hashtable);
        if (hashtable != null && this.supportedSrtpProtectionProfiles != null && (useSRTPExtension = TlsSRTPUtils.getUseSRTPExtension(hashtable)) != null && useSRTPExtension.getProtectionProfiles() != null && useSRTPExtension.getProtectionProfiles().length > 0) {
            this.selectedSrtpProtectionProfile = useSRTPExtension.getProtectionProfiles()[0];
            if (!validateSelectedSrtpProtectionProfile()) {
                throw new TlsFatalAlert(47);
            }
        }
    }

    private boolean validateSelectedSrtpProtectionProfile() {
        for (int i : this.supportedSrtpProtectionProfiles) {
            if (this.selectedSrtpProtectionProfile == i) {
                return true;
            }
        }
        return false;
    }

    public TlsAuthentication getAuthentication() {
        return new DtlsBouncyCastleClientAuthentication(this.context, this.certificate, this.remoteFingerprintAlgorithm, this.remoteFingerprint, this.onRemoteCertificate);
    }

    public void notifyHandshakeComplete() throws IOException {
        super.notifyHandshakeComplete();
        TlsSession resumableSession = this.context.getResumableSession();
        if (resumableSession != null) {
            this.session = resumableSession;
        }
        IAction1<DataBuffer> iAction1 = this.onKeyingMaterialAvailable;
        if (iAction1 != null) {
            iAction1.invoke(DataBuffer.wrap(getKeyingMaterial()));
        }
    }
}
