package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.DTLSTransport;
import org.bouncycastle.crypto.tls.DefaultTlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.DefaultTlsServer;
import org.bouncycastle.crypto.tls.DefaultTlsSignerCredentials;
import org.bouncycastle.crypto.tls.ExporterLabel;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsSRTPUtils;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.tls.UseSRTPData;

class DtlsBouncyCastleServer extends DefaultTlsServer implements DtlsIServer {
    private DtlsCertificate certificate;
    private int[] clientSrtpProtectionProfiles;
    private boolean closed;
    /* access modifiers changed from: private */
    public DTLSTransport connection;
    private Object connectionLock;
    /* access modifiers changed from: private */
    public List<DtlsMessage> handshakeFlight = new ArrayList();
    private DtlsProtocolVersion maxVersion;
    private DtlsProtocolVersion minVersion;
    private IAction1<DataBuffer> onDataDecrypted;
    private IAction1<Exception> onError;
    private IAction1<DataBuffer> onKeyingMaterialAvailable;
    private IAction1<byte[]> onRemoteCertificate;
    private DtlsCipherSuite[] preferredCipherSuites;
    private DtlsBouncyCastleServerProtocol protocol;
    private byte[] receiveBuffer;
    public String remoteFingerprint;
    public String remoteFingerprintAlgorithm;
    private int selectedSrtpProtectionProfile;
    private IAction1<DataBuffer> sendCallback;
    private int[] supportedSrtpProtectionProfiles;
    private DtlsBouncyCastleUdpTransport transport;

    public boolean getClosed() {
        return this.closed;
    }

    public int[] getSupportedSrtpProtectionProfiles() {
        return this.supportedSrtpProtectionProfiles;
    }

    public int[] getClientSrtpProtectionProfiles() {
        return this.clientSrtpProtectionProfiles;
    }

    public void setClientSrtpProtectionProfiles(int[] iArr) {
        this.clientSrtpProtectionProfiles = iArr;
    }

    public int getSelectedSrtpProtectionProfile() {
        return this.selectedSrtpProtectionProfile;
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

    public DtlsCertificate getCertificate() {
        return this.certificate;
    }

    public DtlsCipherSuite[] getPreferredCipherSuites() {
        return this.preferredCipherSuites;
    }

    public DtlsProtocolVersion getMinVersion() {
        return this.minVersion;
    }

    public DtlsProtocolVersion getMaxVersion() {
        return this.maxVersion;
    }

    public DtlsBouncyCastleServer(DtlsCertificate dtlsCertificate, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsProtocolVersion dtlsProtocolVersion2, DtlsFingerprint dtlsFingerprint, int[] iArr, IAction1<byte[]> iAction1, final IAction1<DataBuffer> iAction12) {
        this.certificate = dtlsCertificate;
        this.preferredCipherSuites = dtlsCipherSuiteArr;
        this.minVersion = dtlsProtocolVersion;
        this.maxVersion = dtlsProtocolVersion2;
        this.remoteFingerprintAlgorithm = dtlsFingerprint.getAlgorithm();
        this.remoteFingerprint = dtlsFingerprint.getValue();
        this.supportedSrtpProtectionProfiles = iArr;
        this.selectedSrtpProtectionProfile = -1;
        this.connectionLock = new Object();
        this.onRemoteCertificate = iAction1;
        this.sendCallback = iAction12;
        this.transport = new DtlsBouncyCastleUdpTransport(new IAction1<byte[]>() {
            public void invoke(byte[] bArr) {
                if (DtlsBouncyCastleServer.this.getClosed()) {
                    return;
                }
                if (DtlsBouncyCastleServer.this.connection == null) {
                    Log.debug(String.format(Locale.getDefault(), "Sending DTLS packet (%d bytes).", new Object[]{Integer.valueOf(bArr.length)}));
                    DtlsMessage[] parseMultiple = DtlsMessage.parseMultiple(DataBuffer.wrap(bArr));
                    if (parseMultiple != null) {
                        for (DtlsMessage add : parseMultiple) {
                            DtlsBouncyCastleServer.this.handshakeFlight.add(add);
                        }
                    }
                    if (DtlsBouncyCastleServer.this.handshakeFlight.size() >= 1) {
                        DtlsMessage dtlsMessage = (DtlsMessage) DtlsBouncyCastleServer.this.handshakeFlight.get(DtlsBouncyCastleServer.this.handshakeFlight.size() - 1);
                        if (dtlsMessage.getContentType() == DtlsContentType.getHandshake() && (dtlsMessage.getHandshakeType() == DtlsHandshakeType.getServerHelloDone() || dtlsMessage.getHandshakeType() == DtlsHandshakeType.getHelloRequest() || dtlsMessage.getHandshakeType() == DtlsHandshakeType.getHelloVerifyRequest())) {
                            DtlsBouncyCastleServer.this.sendHandshakeFlight(iAction12);
                        } else if (DtlsBouncyCastleServer.this.handshakeFlight.size() >= 2 && ((DtlsMessage) DtlsBouncyCastleServer.this.handshakeFlight.get(DtlsBouncyCastleServer.this.handshakeFlight.size() - 2)).getContentType() == DtlsContentType.getChangeCipherSpec() && dtlsMessage.getContentType() == DtlsContentType.getHandshake()) {
                            DtlsBouncyCastleServer.this.sendHandshakeFlight(iAction12);
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
            DtlsBouncyCastleServerProtocol dtlsBouncyCastleServerProtocol = new DtlsBouncyCastleServerProtocol();
            this.protocol = dtlsBouncyCastleServerProtocol;
            this.connection = dtlsBouncyCastleServerProtocol.accept(this, this.transport);
            synchronized (this.connectionLock) {
                this.receiveBuffer = new byte[this.connection.getReceiveLimit()];
                processReceived();
            }
            if (this.closed) {
                return new Error(ErrorCode.DtlsKeyExchangeFailed, new Exception("Could not accept DTLS client connection."));
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
                        iAction1.invoke(new Exception(String.format("DTLS server could not process incoming message.", new Object[]{e.getMessage()})));
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void send(DataBuffer dataBuffer) {
        DTLSTransport dTLSTransport = this.connection;
        if (dTLSTransport != null) {
            try {
                dTLSTransport.send(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
            } catch (Exception e) {
                IAction1<Exception> iAction1 = this.onError;
                if (iAction1 != null) {
                    iAction1.invoke(new Exception(String.format("DTLS server could not process outgoing message.", new Object[]{e.getMessage()})));
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
        DtlsBouncyCastleServerProtocol dtlsBouncyCastleServerProtocol = this.protocol;
        if (dtlsBouncyCastleServerProtocol != null) {
            try {
                dtlsBouncyCastleServerProtocol.cancel();
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

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyAlertRaised(byte r7, byte r8, java.lang.String r9, java.lang.Exception r10) {
        /*
            r6 = this;
            boolean r0 = r6.closed
            if (r0 != 0) goto L_0x006d
            r0 = 1
            r1 = 0
            if (r10 != 0) goto L_0x000b
            java.lang.String r2 = ""
            goto L_0x0019
        L_0x000b:
            java.lang.Object[] r2 = new java.lang.Object[r0]
            java.lang.String r3 = r10.getMessage()
            r2[r1] = r3
            java.lang.String r3 = ", Inner exception: %s"
            java.lang.String r2 = java.lang.String.format(r3, r2)
        L_0x0019:
            java.util.Locale r3 = java.util.Locale.getDefault()
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = java.lang.String.valueOf(r7)
            r4[r1] = r5
            java.lang.String r5 = java.lang.String.valueOf(r8)
            r4[r0] = r5
            r5 = 2
            r4[r5] = r9
            r9 = 3
            r4[r9] = r2
            java.lang.String r9 = "DTLS server raised alert. (Level: %s, Description: %s, Message: '%s'%s)"
            java.lang.String r9 = java.lang.String.format(r3, r9, r4)
            if (r7 != r0) goto L_0x0052
            if (r8 != 0) goto L_0x0048
            java.lang.String r9 = "Local DTLS server closed connection."
            if (r10 != 0) goto L_0x0044
            fm.liveswitch.Log.debug(r9)
            goto L_0x005e
        L_0x0044:
            fm.liveswitch.Log.debug(r9, r10)
            goto L_0x005e
        L_0x0048:
            if (r10 != 0) goto L_0x004e
            fm.liveswitch.Log.warn(r9)
            goto L_0x005e
        L_0x004e:
            fm.liveswitch.Log.warn(r9, r10)
            goto L_0x005e
        L_0x0052:
            if (r7 != r5) goto L_0x0055
            goto L_0x005f
        L_0x0055:
            if (r10 != 0) goto L_0x005b
            fm.liveswitch.Log.debug(r9)
            goto L_0x005e
        L_0x005b:
            fm.liveswitch.Log.debug(r9, r10)
        L_0x005e:
            r0 = 0
        L_0x005f:
            if (r0 == 0) goto L_0x006d
            fm.liveswitch.IAction1<java.lang.Exception> r7 = r6.onError
            if (r7 == 0) goto L_0x006d
            java.lang.Exception r8 = new java.lang.Exception
            r8.<init>(r9)
            r7.invoke(r8)
        L_0x006d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DtlsBouncyCastleServer.notifyAlertRaised(byte, byte, java.lang.String, java.lang.Exception):void");
    }

    public void notifyAlertReceived(byte b, byte b2) {
        IAction1<Exception> iAction1;
        if (!this.closed) {
            boolean z = false;
            String format = String.format(Locale.getDefault(), "DTLS server received alert. (Level: %s, Description: %s)", new Object[]{String.valueOf(b), String.valueOf(b2)});
            if (b == 1) {
                if (b2 == 0) {
                    format = "Remote DTLS client closed connection.";
                    Log.debug(format);
                } else {
                    Log.warn(format);
                }
            } else if (b == 2) {
                z = true;
            } else {
                Log.debug(format);
            }
            if (z && (iAction1 = this.onError) != null) {
                iAction1.invoke(new Exception(format));
            }
        }
    }

    /* access modifiers changed from: protected */
    public int[] getCipherSuites() {
        int length = getPreferredCipherSuites().length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = DtlsBouncyCastleUtility.convertCipherSuite(getPreferredCipherSuites()[i]);
        }
        return iArr;
    }

    public CertificateRequest getCertificateRequest() {
        return new CertificateRequest(new short[]{1, 64}, TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.serverVersion) ? TlsUtils.getDefaultSupportedSignatureAlgorithms() : null, (Vector) null);
    }

    public void notifyClientCertificate(Certificate certificate2) throws IOException {
        String str;
        IAction1<byte[]> iAction1;
        if (certificate2 != null) {
            org.bouncycastle.asn1.x509.Certificate[] certificateList = certificate2.getCertificateList();
            if (certificateList == null || certificateList.length == 0) {
                throw new TlsFatalAlert(42);
            }
            org.bouncycastle.asn1.x509.Certificate certificate3 = certificateList[0];
            if (this.remoteFingerprintAlgorithm.toLowerCase().equals("sha2") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha256") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha-256")) {
                str = HashContextBase.compute(HashType.Sha256, DataBuffer.wrap(certificate3.getEncoded())).toHexString();
            } else if (this.remoteFingerprintAlgorithm.toLowerCase().equals("sha") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha1") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha-1")) {
                str = HashContextBase.compute(HashType.Sha1, DataBuffer.wrap(certificate3.getEncoded())).toHexString();
            } else {
                throw new TlsFatalAlert(49);
            }
            if (str.toLowerCase().equals(this.remoteFingerprint.replace(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, "").toLowerCase())) {
                byte[] bArr = null;
                try {
                    bArr = certificate3.getEncoded();
                } catch (Exception e) {
                    Log.error("Could not parse remote DTLS certificate.", e);
                }
                if (bArr != null && (iAction1 = this.onRemoteCertificate) != null) {
                    iAction1.invoke(bArr);
                    return;
                }
                return;
            }
            throw new TlsFatalAlert(49);
        }
        throw new TlsFatalAlert(42);
    }

    public void processClientExtensions(Hashtable hashtable) throws IOException {
        UseSRTPData useSRTPExtension;
        IAction1<Exception> iAction1;
        super.processClientExtensions(hashtable);
        if (hashtable != null && this.supportedSrtpProtectionProfiles != null && (useSRTPExtension = TlsSRTPUtils.getUseSRTPExtension(hashtable)) != null && useSRTPExtension.getProtectionProfiles() != null && useSRTPExtension.getProtectionProfiles().length > 0) {
            this.clientSrtpProtectionProfiles = useSRTPExtension.getProtectionProfiles();
            if (!selectSrtpProtectionProfile() && (iAction1 = this.onError) != null) {
                iAction1.invoke(new Exception("DTLS server could not select an SRTP protection profile."));
            }
        }
    }

    private boolean selectSrtpProtectionProfile() {
        for (int i : this.supportedSrtpProtectionProfiles) {
            for (int i2 : this.clientSrtpProtectionProfiles) {
                if (i2 == i) {
                    this.selectedSrtpProtectionProfile = i2;
                    return true;
                }
            }
        }
        return false;
    }

    public Hashtable getServerExtensions() throws IOException {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getServerExtensions());
        int i = this.selectedSrtpProtectionProfile;
        if (i >= 0 && this.supportedSrtpProtectionProfiles != null) {
            TlsSRTPUtils.addUseSRTPExtension(ensureExtensionsInitialised, new UseSRTPData(new int[]{i}, new byte[0]));
        }
        return ensureExtensionsInitialised;
    }

    /* access modifiers changed from: protected */
    public ProtocolVersion getMaximumVersion() {
        if (this.maxVersion == DtlsProtocolVersion.Dtls10) {
            return ProtocolVersion.DTLSv10;
        }
        return ProtocolVersion.DTLSv12;
    }

    /* access modifiers changed from: protected */
    public ProtocolVersion getMinimumVersion() {
        if (this.minVersion == DtlsProtocolVersion.Dtls12) {
            return ProtocolVersion.DTLSv12;
        }
        return ProtocolVersion.DTLSv10;
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getECDSASignerCredentials() throws IOException {
        AsymmetricKeyParameter ecdsaPrivateKey = DtlsBouncyCastleUtility.getEcdsaPrivateKey(getCertificate());
        if (ecdsaPrivateKey == null) {
            return null;
        }
        if (this.supportedSignatureAlgorithms == null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), ecdsaPrivateKey);
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = DtlsBouncyCastleUtility.getSignatureAndHashAlgorithm(this.supportedSignatureAlgorithms, 3);
        if (signatureAndHashAlgorithm != null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), ecdsaPrivateKey, signatureAndHashAlgorithm);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getRSASignerCredentials() {
        AsymmetricKeyParameter rsaPrivateKey = DtlsBouncyCastleUtility.getRsaPrivateKey(getCertificate());
        if (rsaPrivateKey == null) {
            return null;
        }
        if (this.supportedSignatureAlgorithms == null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), rsaPrivateKey);
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = DtlsBouncyCastleUtility.getSignatureAndHashAlgorithm(this.supportedSignatureAlgorithms, 1);
        if (signatureAndHashAlgorithm != null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), rsaPrivateKey, signatureAndHashAlgorithm);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public TlsEncryptionCredentials getRSAEncryptionCredentials() {
        AsymmetricKeyParameter rsaPrivateKey = DtlsBouncyCastleUtility.getRsaPrivateKey(getCertificate());
        if (rsaPrivateKey != null) {
            return new DefaultTlsEncryptionCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), rsaPrivateKey);
        }
        return null;
    }

    public void notifyHandshakeComplete() throws IOException {
        super.notifyHandshakeComplete();
        IAction1<DataBuffer> iAction1 = this.onKeyingMaterialAvailable;
        if (iAction1 != null) {
            iAction1.invoke(DataBuffer.wrap(getKeyingMaterial()));
        }
    }
}
