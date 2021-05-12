package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DtlsTransport extends Transport {
    private static DtlsCipherSuite[] __ecdsaCipherSuites = {DtlsCipherSuite.EcdheEcdsaAes128Sha, DtlsCipherSuite.EcdheEcdsaAes128GcmSha256, DtlsCipherSuite.EcdheEcdsaAes128CbcSha256};
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(DtlsTransport.class);
    private static DtlsCipherSuite[] __rsaCipherSuites = {DtlsCipherSuite.RsaAes128Sha, DtlsCipherSuite.EcdheRsaAes128Sha, DtlsCipherSuite.RsaAes128GcmSha256, DtlsCipherSuite.EcdheRsaAes128GcmSha256, DtlsCipherSuite.RsaAes128CbcSha256, DtlsCipherSuite.EcdheRsaAes128CbcSha256};
    private DtlsCipherSuite[] __cipherSuites;
    private DtlsProtocolVersion __clientVersion;
    private String __connectionId;
    private DtlsIClient __dtlsClient = null;
    private ManagedThread __dtlsHandshakeThread = null;
    private DtlsIServer __dtlsServer = null;
    private DataBuffer __keyingMaterial = null;
    private long __lastStateChangeSystemTimestamp;
    private DtlsCertificate[] __localCertificates;
    private DtlsParameters __localParameters;
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<DataBuffer>> __onReceive = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<DtlsTransport>> __onStateChange = new ArrayList();
    private DtlsRole __preferredRole;
    /* access modifiers changed from: private */
    public ArrayList<DtlsCertificate> __remoteCertificates = new ArrayList<>();
    private DtlsParameters __remoteParameters;
    private int __selectedSrtpProtectionProfile;
    private ArrayList<DataBuffer> __sendQueue = new ArrayList<>();
    private DtlsProtocolVersion __serverMaxVersion;
    private DtlsProtocolVersion __serverMinVersion;
    private DtlsTransportState __state;
    private boolean _closingShouldNotTriggerGlobalNonGracefulShutdown;
    private boolean _connectionShouldStayAliveWhenClosed;
    private EncryptionMode[] _encryptionModes;
    private Error _error;
    private String _id;
    private Transport _innerTransport;
    private DataBuffer _lastDtlsChangeCipherSpec;
    private DataBuffer _lastDtlsFinishedFlight;
    private boolean _nextMessageDtlsFinished;
    private IAction1<DataBuffer> _onReceive = null;
    private IAction1<DtlsTransport> _onStateChange = null;
    private boolean _stopOnConnected;

    public void addOnReceive(IAction1<DataBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onReceive == null) {
                this._onReceive = new IAction1<DataBuffer>() {
                    public void invoke(DataBuffer dataBuffer) {
                        Iterator it = new ArrayList(DtlsTransport.this.__onReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dataBuffer);
                        }
                    }
                };
            }
            this.__onReceive.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<DtlsTransport> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<DtlsTransport>() {
                    public void invoke(DtlsTransport dtlsTransport) {
                        Iterator it = new ArrayList(DtlsTransport.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dtlsTransport);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public void disableInnerTransport() {
        setInnerTransport((Transport) null);
    }

    /* access modifiers changed from: private */
    public void doDtls(ManagedThread managedThread) {
        Error error;
        try {
            __log.debug(getId(), "Starting DTLS key exchange.");
            if (this.__dtlsServer != null) {
                __log.debug(getId(), "Accepting DTLS client connection.");
                this.__dtlsServer.setOnDataDecrypted(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.DtlsTransport.processDataDecrypted";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        DtlsTransport.this.processDataDecrypted(dataBuffer);
                    }
                });
                error = this.__dtlsServer.open();
                if (error == null) {
                    __log.debug(getId(), "Accepted DTLS client connection.");
                    this.__selectedSrtpProtectionProfile = this.__dtlsServer.getSelectedSrtpProtectionProfile();
                } else {
                    synchronized (this.__lock) {
                        setError(error);
                        setState(DtlsTransportState.Failed);
                    }
                }
            } else {
                __log.debug(getId(), "Connecting to DTLS server.");
                this.__dtlsClient.setOnDataDecrypted(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.DtlsTransport.processDataDecrypted";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        DtlsTransport.this.processDataDecrypted(dataBuffer);
                    }
                });
                error = this.__dtlsClient.open();
                if (error == null) {
                    __log.debug(getId(), "Connected to DTLS server.");
                    this.__selectedSrtpProtectionProfile = this.__dtlsClient.getSelectedSrtpProtectionProfile();
                } else {
                    synchronized (this.__lock) {
                        setError(error);
                        setState(DtlsTransportState.Failed);
                    }
                }
            }
            if (error == null) {
                __log.debug(getId(), "Completed DTLS key exchange.");
                processDtlsFinished();
            }
        } catch (Exception e) {
            synchronized (this.__lock) {
                setError(new Error(ErrorCode.DtlsKeyExchangeFailed, e));
                setState(DtlsTransportState.Failed);
            }
        }
    }

    public DtlsTransport(Object obj, String str, Transport transport, DtlsCertificate[] dtlsCertificateArr, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsProtocolVersion dtlsProtocolVersion2, DtlsProtocolVersion dtlsProtocolVersion3, DtlsRole dtlsRole) {
        this.__lock = obj;
        this.__connectionId = str;
        setId(Utility.generateId());
        if (transport == null) {
            throw new RuntimeException(new Exception("Inner transport cannot be null."));
        } else if (dtlsCertificateArr != null) {
            this.__state = DtlsTransportState.New;
            setInnerTransport(transport);
            this.__localCertificates = dtlsCertificateArr;
            this.__clientVersion = dtlsProtocolVersion3;
            this.__serverMinVersion = dtlsProtocolVersion;
            this.__serverMaxVersion = dtlsProtocolVersion2;
            this.__cipherSuites = dtlsCipherSuiteArr;
            this.__preferredRole = dtlsRole;
        } else {
            throw new RuntimeException(new Exception("Local certificates cannot be null."));
        }
    }

    private DtlsCipherSuite[] filterCipherSuites(DtlsCipherSuite[] dtlsCipherSuiteArr) {
        AsymmetricKey key = get_LocalCertificate().getKey();
        boolean equals = Global.equals(key.getType(), AsymmetricKeyType.Rsa);
        boolean equals2 = Global.equals(key.getType(), AsymmetricKeyType.Ecdsa);
        ArrayList arrayList = new ArrayList();
        for (DtlsCipherSuite dtlsCipherSuite : dtlsCipherSuiteArr) {
            if (equals && isCompatible(dtlsCipherSuite, AsymmetricKeyType.Rsa)) {
                arrayList.add(dtlsCipherSuite);
            }
            if (equals2 && isCompatible(dtlsCipherSuite, AsymmetricKeyType.Ecdsa)) {
                arrayList.add(dtlsCipherSuite);
            }
        }
        return (DtlsCipherSuite[]) arrayList.toArray(new DtlsCipherSuite[0]);
    }

    private DtlsCertificate get_LocalCertificate() {
        return (DtlsCertificate) Utility.firstOrDefault((T[]) this.__localCertificates);
    }

    public boolean getClosingShouldNotTriggerGlobalNonGracefulShutdown() {
        return this._closingShouldNotTriggerGlobalNonGracefulShutdown;
    }

    public boolean getConnectionShouldStayAliveWhenClosed() {
        return this._connectionShouldStayAliveWhenClosed;
    }

    public EncryptionMode[] getEncryptionModes() {
        return this._encryptionModes;
    }

    public Error getError() {
        return this._error;
    }

    public String getId() {
        return this._id;
    }

    public Transport getInnerTransport() {
        return this._innerTransport;
    }

    public boolean getIsClosed() {
        return Global.equals(getState(), DtlsTransportState.Closed);
    }

    public DtlsCertificate[] getLocalCertificates() {
        return this.__localCertificates;
    }

    public DataBuffer getLocalKey() {
        DataBuffer dataBuffer = this.__keyingMaterial;
        if (dataBuffer == null) {
            return null;
        }
        if (this.__dtlsServer != null) {
            return dataBuffer.subset(16, 16);
        }
        return dataBuffer.subset(0, 16);
    }

    public DtlsParameters getLocalParameters() {
        if (this.__localParameters == null) {
            DtlsFingerprint[] dtlsFingerprintArr = new DtlsFingerprint[ArrayExtensions.getLength((Object[]) this.__localCertificates)];
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) this.__localCertificates); i++) {
                dtlsFingerprintArr[i] = (DtlsFingerprint) this.__localCertificates[i].calculateFingerprint(Fingerprint.getSha256Algorithm());
            }
            this.__localParameters = new DtlsParameters(DtlsRole.Auto, dtlsFingerprintArr, this.__preferredRole);
        }
        return this.__localParameters;
    }

    public DataBuffer getLocalSalt() {
        DataBuffer dataBuffer = this.__keyingMaterial;
        if (dataBuffer == null) {
            return null;
        }
        if (this.__dtlsServer != null) {
            return dataBuffer.subset(46, 14);
        }
        return dataBuffer.subset(32, 14);
    }

    /* access modifiers changed from: package-private */
    public DtlsCertificate getRemoteCertificate() {
        return (DtlsCertificate) Utility.firstOrDefault(this.__remoteCertificates);
    }

    /* access modifiers changed from: package-private */
    public DtlsCertificate[] getRemoteCertificates() {
        return (DtlsCertificate[]) this.__remoteCertificates.toArray(new DtlsCertificate[0]);
    }

    public DataBuffer getRemoteKey() {
        DataBuffer dataBuffer = this.__keyingMaterial;
        if (dataBuffer == null) {
            return null;
        }
        if (this.__dtlsServer != null) {
            return dataBuffer.subset(0, 16);
        }
        return dataBuffer.subset(16, 16);
    }

    public DtlsParameters getRemoteParameters() {
        return this.__remoteParameters;
    }

    public DataBuffer getRemoteSalt() {
        DataBuffer dataBuffer = this.__keyingMaterial;
        if (dataBuffer == null) {
            return null;
        }
        if (this.__dtlsServer != null) {
            return dataBuffer.subset(32, 14);
        }
        return dataBuffer.subset(46, 14);
    }

    public int getSelectedSrtpProtectionProfile() {
        return this.__selectedSrtpProtectionProfile;
    }

    private static int getSrtpProtectionProfile(EncryptionMode encryptionMode) {
        String str = StringExtensions.empty;
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Strong)) {
            str = SrtpProtectionProfile.getAes128CmHmacSha180ProtectionProfileString();
        }
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Weak)) {
            str = SrtpProtectionProfile.getAes128CmHmacSha132ProtectionProfileString();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullStrong)) {
            str = SrtpProtectionProfile.getNullHmacSha180ProtectionProfileString();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullWeak)) {
            str = SrtpProtectionProfile.getNullHmacSha132ProtectionProfileString();
        }
        return SrtpProtectionProfile.protectionProfileCodeFromString(str);
    }

    public DtlsTransportState getState() {
        return this.__state;
    }

    public boolean getStopOnConnected() {
        return this._stopOnConnected;
    }

    private static boolean isCompatible(DtlsCipherSuite dtlsCipherSuite, AsymmetricKeyType asymmetricKeyType) {
        if (Global.equals(asymmetricKeyType, AsymmetricKeyType.Rsa)) {
            for (DtlsCipherSuite equals : __rsaCipherSuites) {
                if (Global.equals(equals, dtlsCipherSuite)) {
                    return true;
                }
            }
        } else if (Global.equals(asymmetricKeyType, AsymmetricKeyType.Ecdsa)) {
            for (DtlsCipherSuite equals2 : __ecdsaCipherSuites) {
                if (Global.equals(equals2, dtlsCipherSuite)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDtls(DataBuffer dataBuffer) {
        int read8;
        if (dataBuffer.getLength() != 0 && (read8 = dataBuffer.read8(0)) >= 20 && read8 <= 63) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void processCoreDtlsError(Exception exc) {
        synchronized (this.__lock) {
            setError(new Error(ErrorCode.DtlsInternalError, exc));
            setState(DtlsTransportState.Failed);
        }
    }

    /* access modifiers changed from: private */
    public void processDataDecrypted(DataBuffer dataBuffer) {
        raiseReceive(dataBuffer);
    }

    private void processDtlsFinished() {
        synchronized (this.__lock) {
            if (!Global.equals(getState(), DtlsTransportState.Closed)) {
                setState(DtlsTransportState.Connected);
                Iterator<DataBuffer> it = this.__sendQueue.iterator();
                while (it.hasNext()) {
                    send(it.next());
                }
                this.__sendQueue.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public void processKeyingMaterial(DataBuffer dataBuffer) {
        this.__keyingMaterial = dataBuffer;
    }

    /* access modifiers changed from: package-private */
    public void processReceive(DataBuffer dataBuffer) {
        if (!isDtls(dataBuffer)) {
            return;
        }
        if (this.__dtlsServer != null) {
            for (DtlsMessage dtlsMessage : DtlsMessage.parseMultiple(dataBuffer)) {
                DataBuffer dataBuffer2 = this._lastDtlsFinishedFlight;
                if (dtlsMessage.getContentType() == DtlsContentType.getChangeCipherSpec() && dataBuffer2 != null) {
                    getInnerTransport().send(dataBuffer2);
                }
            }
            this.__dtlsServer.receive(dataBuffer);
            return;
        }
        DtlsIClient dtlsIClient = this.__dtlsClient;
        if (dtlsIClient != null) {
            dtlsIClient.receive(dataBuffer);
            return;
        }
        synchronized (this.__lock) {
            setError(new Error(ErrorCode.DtlsNotReady, new Exception("Neither DTLS server nor client have been prepared.")));
            __log.debug(getId(), getError().getDescription());
        }
    }

    private void processRemoteCertificate(DtlsCertificate dtlsCertificate) {
        synchronized (this.__lock) {
            this.__remoteCertificates.add(dtlsCertificate);
        }
    }

    private void raiseReceive(DataBuffer dataBuffer) {
        IAction1<DataBuffer> iAction1 = this._onReceive;
        if (iAction1 != null) {
            iAction1.invoke(dataBuffer);
        }
    }

    public void removeOnReceive(IAction1<DataBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceive.remove(iAction1);
        if (this.__onReceive.size() == 0) {
            this._onReceive = null;
        }
    }

    public void removeOnStateChange(IAction1<DtlsTransport> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public void send(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            return;
        }
        if (Global.equals(getState(), DtlsTransportState.Connected)) {
            sendToEncryptor(dataBuffer);
        } else if (Global.equals(getState(), DtlsTransportState.Connecting) || Global.equals(getState(), DtlsTransportState.New)) {
            synchronized (this.__lock) {
                if (!Global.equals(getState(), DtlsTransportState.Connecting)) {
                    if (!Global.equals(getState(), DtlsTransportState.New)) {
                        if (Global.equals(getState(), DtlsTransportState.Connected)) {
                            sendToEncryptor(dataBuffer);
                        }
                    }
                }
                this.__sendQueue.add(dataBuffer);
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendEncryptedData(DataBuffer dataBuffer) {
        Transport innerTransport;
        if (this.__dtlsServer != null) {
            DtlsMessage[] parseMultiple = DtlsMessage.parseMultiple(dataBuffer);
            for (DtlsMessage dtlsMessage : parseMultiple) {
                DataBuffer dataBuffer2 = null;
                if (this._nextMessageDtlsFinished) {
                    DataBuffer raw = dtlsMessage.getContentType() == DtlsContentType.getHandshake() ? dtlsMessage.getRaw() : null;
                    if (raw != null) {
                        DataBuffer allocate = DataBuffer.allocate(this._lastDtlsChangeCipherSpec.getLength() + raw.getLength());
                        BitAssistant.copy(this._lastDtlsChangeCipherSpec.getData(), this._lastDtlsChangeCipherSpec.getIndex(), allocate.getData(), allocate.getIndex(), this._lastDtlsChangeCipherSpec.getLength());
                        BitAssistant.copy(raw.getData(), raw.getIndex(), allocate.getData(), allocate.getIndex() + this._lastDtlsChangeCipherSpec.getLength(), raw.getLength());
                        this._lastDtlsFinishedFlight = allocate;
                    }
                    this._nextMessageDtlsFinished = false;
                }
                if (dtlsMessage.getContentType() == DtlsContentType.getChangeCipherSpec()) {
                    dataBuffer2 = dtlsMessage.getRaw();
                }
                if (dataBuffer2 != null) {
                    this._nextMessageDtlsFinished = true;
                    if (ArrayExtensions.getLength((Object[]) parseMultiple) > 2) {
                        __log.warn(getId(), "Outgoing DTLS flight with a Change Cipher Spec message contains more than two messages.");
                    }
                    this._lastDtlsChangeCipherSpec = dataBuffer2;
                } else {
                    this._nextMessageDtlsFinished = false;
                }
            }
        }
        if (!Global.equals(getState(), DtlsTransportState.Closed) && !Global.equals(getState(), DtlsTransportState.Failed) && (innerTransport = getInnerTransport()) != null && !getInnerTransport().getIsClosed()) {
            innerTransport.send(dataBuffer);
        }
    }

    private boolean sendToEncryptor(DataBuffer dataBuffer) {
        DtlsIServer dtlsIServer = this.__dtlsServer;
        if (dtlsIServer != null) {
            dtlsIServer.send(dataBuffer);
            return true;
        }
        DtlsIClient dtlsIClient = this.__dtlsClient;
        if (dtlsIClient == null) {
            return false;
        }
        dtlsIClient.send(dataBuffer);
        return true;
    }

    public void setClosingShouldNotTriggerGlobalNonGracefulShutdown(boolean z) {
        this._closingShouldNotTriggerGlobalNonGracefulShutdown = z;
    }

    public void setConnectionShouldStayAliveWhenClosed(boolean z) {
        this._connectionShouldStayAliveWhenClosed = z;
    }

    /* access modifiers changed from: package-private */
    public void setEncryptionModes(EncryptionMode[] encryptionModeArr) {
        this._encryptionModes = encryptionModeArr;
    }

    private void setError(Error error) {
        this._error = error;
    }

    private void setId(String str) {
        this._id = str;
    }

    private void setInnerTransport(Transport transport) {
        this._innerTransport = transport;
    }

    /* access modifiers changed from: package-private */
    public void setLocalParameters(DtlsParameters dtlsParameters) {
        this.__localParameters = dtlsParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteParameters(DtlsParameters dtlsParameters) {
        if (getLocalParameters() != null) {
            this.__remoteParameters = dtlsParameters;
            if (!Global.equals(getLocalParameters().getRole(), getRemoteParameters().getRole()) || Global.equals(getRemoteParameters().getRole(), DtlsRole.Auto)) {
                getRemoteParameters().setRole(getLocalParameters().negotiate(getRemoteParameters().getRole()));
                return;
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Cannot set remote DTLS parameters. Remote DTLS role matches local DTLS role: {0}", (Object) getLocalParameters().getRole().toString())));
        }
        throw new RuntimeException(new Exception("Cannot set remote DTLS parameters. Local DTLS parameters are not set."));
    }

    public void setState(DtlsTransportState dtlsTransportState) {
        synchronized (this.__lock) {
            if (!Global.equals(dtlsTransportState, this.__state) && !Global.equals(this.__state, DtlsTransportState.Closed) && !Global.equals(this.__state, DtlsTransportState.Failed)) {
                if (__log.getIsDebugEnabled()) {
                    if (Global.equals(this.__state, DtlsTransportState.Connecting) && Global.equals(dtlsTransportState, DtlsTransportState.Connected)) {
                        __log.debug(getId(), StringExtensions.format("DTLS transport took {0}ms to secure connection {1}.", IntegerExtensions.toString(Integer.valueOf((int) ((ManagedStopwatch.getTimestamp() - this.__lastStateChangeSystemTimestamp) / ((long) Constants.getTicksPerMillisecond())))), this.__connectionId));
                    }
                    if (!Global.equals(dtlsTransportState, DtlsTransportState.Failed) || getError() == null) {
                        __log.debug(getId(), StringExtensions.format("Setting DTLS transport state to {0} for connection {1}.", StringExtensions.toLower(dtlsTransportState.toString()), this.__connectionId));
                    } else {
                        __log.debug(getId(), StringExtensions.format("Setting DTLS transport state to {0} for connection {1}. ({2})", StringExtensions.toLower(dtlsTransportState.toString()), this.__connectionId, getError().getCode().toString()), getError().getException());
                    }
                }
                this.__state = dtlsTransportState;
                this.__lastStateChangeSystemTimestamp = ManagedStopwatch.getTimestamp();
                if (Global.equals(dtlsTransportState, DtlsTransportState.Failed) || Global.equals(dtlsTransportState, DtlsTransportState.Closed)) {
                    this.__sendQueue.clear();
                    DtlsIServer dtlsIServer = this.__dtlsServer;
                    if (dtlsIServer != null) {
                        dtlsIServer.close();
                    } else {
                        DtlsIClient dtlsIClient = this.__dtlsClient;
                        if (dtlsIClient != null) {
                            dtlsIClient.close();
                        }
                    }
                    setInnerTransport((Transport) null);
                    this.__remoteParameters = null;
                }
                IAction1<DtlsTransport> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
            }
        }
    }

    public void setStopOnConnected(boolean z) {
        this._stopOnConnected = z;
    }

    public boolean start() {
        synchronized (this.__lock) {
            if (!Global.equals(this.__state, DtlsTransportState.New) && !Global.equals(this.__state, DtlsTransportState.Closed) && !Global.equals(this.__state, DtlsTransportState.Failed)) {
                return false;
            }
            if (getLocalParameters() == null) {
                throw new RuntimeException(new Exception("Cannot start DTLS transport. Local DTLS parameters are not set."));
            } else if (getRemoteParameters() != null) {
                setState(DtlsTransportState.Connecting);
                int[] iArr = null;
                EncryptionMode[] encryptionModes = getEncryptionModes();
                if (encryptionModes != null) {
                    iArr = new int[ArrayExtensions.getLength((Object[]) encryptionModes)];
                    for (int i = 0; i < ArrayExtensions.getLength((Object[]) encryptionModes); i++) {
                        iArr[i] = getSrtpProtectionProfile(encryptionModes[i]);
                    }
                }
                if (Global.equals(getLocalParameters().getRole(), DtlsRole.Server)) {
                    int[] iArr2 = iArr;
                    DtlsIServer server = DtlsFactory.server(getId(), get_LocalCertificate(), filterCipherSuites(this.__cipherSuites), this.__serverMinVersion, this.__serverMaxVersion, getRemoteParameters().getFingerprint(), iArr2, new IAction1<byte[]>() {
                        public void invoke(byte[] bArr) {
                            try {
                                DtlsTransport.this.__remoteCertificates.add(DtlsCertificate.parseBytes(bArr));
                            } catch (Exception e) {
                                DtlsTransport.__log.error(DtlsTransport.this.getId(), "Could not parse remote DTLS certificate.", e);
                            }
                        }
                    }, new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.sendEncryptedData";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            DtlsTransport.this.sendEncryptedData(dataBuffer);
                        }
                    });
                    this.__dtlsServer = server;
                    server.setOnKeyingMaterialAvailable(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.processKeyingMaterial";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            DtlsTransport.this.processKeyingMaterial(dataBuffer);
                        }
                    });
                    this.__dtlsServer.setOnError(new IActionDelegate1<Exception>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.processCoreDtlsError";
                        }

                        public void invoke(Exception exc) {
                            DtlsTransport.this.processCoreDtlsError(exc);
                        }
                    });
                } else if (Global.equals(getLocalParameters().getRole(), DtlsRole.Client)) {
                    int[] iArr3 = iArr;
                    DtlsIClient client = DtlsFactory.client(getId(), get_LocalCertificate(), this.__cipherSuites, this.__clientVersion, getRemoteParameters().getFingerprint(), iArr3, new IAction1<byte[]>() {
                        public void invoke(byte[] bArr) {
                            try {
                                DtlsTransport.this.__remoteCertificates.add(DtlsCertificate.parseBytes(bArr));
                            } catch (Exception e) {
                                DtlsTransport.__log.error(DtlsTransport.this.getId(), "Could not parse remote DTLS certificate.", e);
                            }
                        }
                    }, new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.sendEncryptedData";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            DtlsTransport.this.sendEncryptedData(dataBuffer);
                        }
                    });
                    this.__dtlsClient = client;
                    client.setOnKeyingMaterialAvailable(new IActionDelegate1<DataBuffer>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.processKeyingMaterial";
                        }

                        public void invoke(DataBuffer dataBuffer) {
                            DtlsTransport.this.processKeyingMaterial(dataBuffer);
                        }
                    });
                    this.__dtlsClient.setOnError(new IActionDelegate1<Exception>() {
                        public String getId() {
                            return "fm.liveswitch.DtlsTransport.processCoreDtlsError";
                        }

                        public void invoke(Exception exc) {
                            DtlsTransport.this.processCoreDtlsError(exc);
                        }
                    });
                } else {
                    throw new RuntimeException(new Exception("Local DTLS role has not been set prior to DTLS handshake."));
                }
                ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                    public String getId() {
                        return "fm.liveswitch.DtlsTransport.doDtls";
                    }

                    public void invoke(ManagedThread managedThread) {
                        DtlsTransport.this.doDtls(managedThread);
                    }
                });
                this.__dtlsHandshakeThread = managedThread;
                managedThread.start();
                return true;
            } else {
                throw new RuntimeException(new Exception("Cannot start DTLS transport. Remote DTLS parameters are not set."));
            }
        }
    }

    public boolean stop() {
        synchronized (this.__lock) {
            if (!Global.equals(getState(), DtlsTransportState.Closed)) {
                if (!Global.equals(getState(), DtlsTransportState.Failed)) {
                    setState(DtlsTransportState.Closed);
                    return true;
                }
            }
            return false;
        }
    }

    public void updateInfo(TransportInfo transportInfo) {
        DtlsParameters remoteParameters;
        DtlsFingerprint fingerprint;
        DtlsParameters localParameters;
        DtlsFingerprint fingerprint2;
        DtlsCertificate dtlsCertificate = get_LocalCertificate();
        if (!(dtlsCertificate == null || (localParameters = getLocalParameters()) == null || (fingerprint2 = localParameters.getFingerprint()) == null)) {
            CertificateInfo certificateInfo = new CertificateInfo();
            certificateInfo.setId(dtlsCertificate.getId());
            certificateInfo.setFingerprint(fingerprint2.getValue());
            certificateInfo.setFingerprintAlgorithm(fingerprint2.getAlgorithm());
            transportInfo.setLocalCertificate(certificateInfo);
        }
        DtlsCertificate remoteCertificate = getRemoteCertificate();
        if (remoteCertificate != null && (remoteParameters = getRemoteParameters()) != null && (fingerprint = remoteParameters.getFingerprint()) != null) {
            CertificateInfo certificateInfo2 = new CertificateInfo();
            certificateInfo2.setId(remoteCertificate.getId());
            certificateInfo2.setFingerprint(fingerprint.getValue());
            certificateInfo2.setFingerprintAlgorithm(fingerprint.getAlgorithm());
            transportInfo.setRemoteCertificate(certificateInfo2);
        }
    }

    public void updateStats(TransportStats transportStats) {
        DtlsParameters remoteParameters;
        DtlsFingerprint fingerprint;
        DtlsParameters localParameters;
        DtlsFingerprint fingerprint2;
        DtlsCertificate dtlsCertificate = get_LocalCertificate();
        if (!(dtlsCertificate == null || (localParameters = getLocalParameters()) == null || (fingerprint2 = localParameters.getFingerprint()) == null)) {
            CertificateStats certificateStats = new CertificateStats();
            certificateStats.setId(dtlsCertificate.getId());
            certificateStats.setTimestamp(DateExtensions.getUtcNow());
            certificateStats.setFingerprint(fingerprint2.getValue());
            certificateStats.setFingerprintAlgorithm(fingerprint2.getAlgorithm());
            certificateStats.setCertificateBase64(Base64.encode(dtlsCertificate.getBytes()));
            transportStats.setLocalCertificate(certificateStats);
        }
        DtlsCertificate remoteCertificate = getRemoteCertificate();
        if (remoteCertificate != null && (remoteParameters = getRemoteParameters()) != null && (fingerprint = remoteParameters.getFingerprint()) != null) {
            CertificateStats certificateStats2 = new CertificateStats();
            certificateStats2.setId(remoteCertificate.getId());
            certificateStats2.setTimestamp(DateExtensions.getUtcNow());
            certificateStats2.setFingerprint(fingerprint.getValue());
            certificateStats2.setFingerprintAlgorithm(fingerprint.getAlgorithm());
            certificateStats2.setCertificateBase64(Base64.encode(remoteCertificate.getBytes()));
            transportStats.setRemoteCertificate(certificateStats2);
        }
    }
}
