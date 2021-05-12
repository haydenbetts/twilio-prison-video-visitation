package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Stream extends StreamBase {
    private boolean __bundled = false;
    private CoreTransport __coreTransportRtcp;
    private CoreTransport __coreTransportRtp;
    private boolean __disabled = false;
    private EncryptionMode[] __encryptionModes;
    private EncryptionPolicy __encryptionPolicy = EncryptionPolicy.Required;
    private ArrayList<Candidate> __localCandidates;
    private DtlsParameters __localDtlsParameters;
    /* access modifiers changed from: private */
    public List<IAction0> __onDisabledChange = new ArrayList();
    private ArrayList<CoreTransport> __redundantCoreTransports = new ArrayList<>();
    private SdesPolicy __sdesPolicy = SdesPolicy.Negotiated;
    private CoreTransport _bundleCoreTransport;
    private BundlePolicy _bundlePolicy;
    private IceRole _iceRole;
    private int _index;
    private IceParameters _localIceParameters;
    private IAction0 _onDisabledChange = null;
    private DtlsParameters _remoteDtlsParameters;
    private IceParameters _remoteIceParameters;
    private boolean _useDtls;
    private boolean _useSdes;

    /* access modifiers changed from: package-private */
    public abstract MediaDescription createSdpMediaDescription(Message message, boolean z, boolean z2, boolean z3, BundlePolicy bundlePolicy);

    /* access modifiers changed from: protected */
    public void processCachedSettings() {
    }

    /* access modifiers changed from: package-private */
    public void addLocalCandidate(Candidate candidate) {
        get_LocalCandidates().add(candidate);
    }

    public void addOnDisabledChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onDisabledChange == null) {
                this._onDisabledChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(Stream.this.__onDisabledChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onDisabledChange.add(iAction0);
        }
    }

    /* access modifiers changed from: package-private */
    public void assignLocalParametersToCoreTransports() {
        if (!getBundled()) {
            CoreTransport[] coreTransportArr = {getCoreTransportRtp(), getCoreTransportRtcp()};
            for (int i = 0; i < 2; i++) {
                CoreTransport coreTransport = coreTransportArr[i];
                if (coreTransport != null) {
                    IceGatherer gatherer = coreTransport.getGatherer();
                    DtlsTransport dtlsTransport = coreTransport.getDtlsTransport();
                    if (gatherer != null) {
                        gatherer.setLocalParameters(getLocalIceParameters());
                    }
                    if (dtlsTransport != null) {
                        dtlsTransport.setLocalParameters(getLocalDtlsParameters());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        setCoreTransportRtcp((CoreTransport) null);
        setCoreTransportRtp((CoreTransport) null);
        setLocalIceParameters((IceParameters) null);
        setLocalDtlsParameters((DtlsParameters) null);
    }

    /* access modifiers changed from: package-private */
    public void copyLocalParameters(Stream stream) {
        setLocalIceParameters(stream.getLocalIceParameters());
        setLocalDtlsParameters(stream.getLocalDtlsParameters());
        setRemoteIceParameters(stream.getRemoteIceParameters());
        setRemoteDtlsParameters(stream.getRemoteDtlsParameters());
    }

    private void disableEncryption() {
        this.__encryptionPolicy = EncryptionPolicy.Disabled;
        this.__encryptionModes = new EncryptionMode[]{EncryptionMode.Null};
        setUseDtls(false);
        setUseSdes(false);
    }

    private void enableIceForInternalTransports(boolean z) {
        CoreTransport coreTransportRtp = getCoreTransportRtp();
        CoreTransport coreTransportRtcp = getCoreTransportRtcp();
        coreTransportRtp.getIceTransport().setUseIce(z);
        if (!(coreTransportRtp == null || coreTransportRtp.getIceTransport() == null)) {
            coreTransportRtp.getIceTransport().setUseIce(z);
        }
        if (coreTransportRtcp != null && coreTransportRtcp.getIceTransport() != null) {
            coreTransportRtcp.getIceTransport().setUseIce(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void eraseLocalCandidatesForComponent(int i) {
        for (Candidate candidate : (Candidate[]) get_LocalCandidates().toArray(new Candidate[0])) {
            if (candidate.getSdpCandidateAttribute().getComponentId() == i) {
                get_LocalCandidates().remove(candidate);
            }
        }
    }

    private ArrayList<Candidate> get_LocalCandidates() {
        return this.__localCandidates;
    }

    /* access modifiers changed from: package-private */
    public CoreTransport getBundleCoreTransport() {
        return this._bundleCoreTransport;
    }

    /* access modifiers changed from: package-private */
    public boolean getBundled() {
        return this.__bundled;
    }

    /* access modifiers changed from: package-private */
    public BundlePolicy getBundlePolicy() {
        return this._bundlePolicy;
    }

    /* access modifiers changed from: package-private */
    public CoreTransport getCoreTransportRtcp() {
        return this.__coreTransportRtcp;
    }

    /* access modifiers changed from: package-private */
    public CoreTransport getCoreTransportRtp() {
        return this.__coreTransportRtp;
    }

    public boolean getDeactivated() {
        StreamDirection direction = getDirection();
        return Global.equals(direction, StreamDirection.Unset) || Global.equals(direction, StreamDirection.Inactive);
    }

    public boolean getDisabled() {
        return this.__disabled;
    }

    public EncryptionMode getEncryptionMode() {
        return getEncryptionModes()[0];
    }

    public EncryptionMode[] getEncryptionModes() {
        return this.__encryptionModes;
    }

    public EncryptionPolicy getEncryptionPolicy() {
        return this.__encryptionPolicy;
    }

    /* access modifiers changed from: package-private */
    public IceRole getIceRole() {
        return this._iceRole;
    }

    public int getIndex() {
        return this._index;
    }

    /* access modifiers changed from: package-private */
    public Candidate[] getLocalCandidates() {
        return (Candidate[]) get_LocalCandidates().toArray(new Candidate[0]);
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getLocalCryptoKey() {
        return ((MediaDescriptionManager) getMediaDescriptionManager()).getLocalCryptoKey();
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getLocalCryptoSalt() {
        return ((MediaDescriptionManager) getMediaDescriptionManager()).getLocalCryptoSalt();
    }

    /* access modifiers changed from: package-private */
    public DtlsParameters getLocalDtlsParameters() {
        return this.__localDtlsParameters;
    }

    public IceParameters getLocalIceParameters() {
        return this._localIceParameters;
    }

    /* access modifiers changed from: package-private */
    public CoreTransport[] getRedundantCoreTransports() {
        return (CoreTransport[]) this.__redundantCoreTransports.toArray(new CoreTransport[0]);
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getRemoteCryptoKey() {
        return ((MediaDescriptionManager) getMediaDescriptionManager()).getRemoteCryptoKey();
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getRemoteCryptoSalt() {
        return ((MediaDescriptionManager) getMediaDescriptionManager()).getRemoteCryptoSalt();
    }

    /* access modifiers changed from: package-private */
    public DtlsParameters getRemoteDtlsParameters() {
        return this._remoteDtlsParameters;
    }

    public IceParameters getRemoteIceParameters() {
        return this._remoteIceParameters;
    }

    public SdesPolicy getSdesPolicy() {
        return this.__sdesPolicy;
    }

    public boolean getUseDtls() {
        return this._useDtls;
    }

    public boolean getUseSdes() {
        return this._useSdes;
    }

    /* access modifiers changed from: package-private */
    public TransportAddress obtainLikelyTransportAddress(CoreTransport coreTransport) {
        int i;
        String str;
        IceCandidate defaultLocalCandidate;
        IceGatherer gatherer = coreTransport != null ? coreTransport.getGatherer() : null;
        if (gatherer == null || (defaultLocalCandidate = gatherer.getDefaultLocalCandidate()) == null) {
            str = "0.0.0.0";
            i = 9;
        } else {
            str = defaultLocalCandidate.getIPAddress();
            i = defaultLocalCandidate.getPort();
        }
        return new TransportAddress(str, i);
    }

    /* access modifiers changed from: package-private */
    public void populateProperties(MediaDescriptionRequirements mediaDescriptionRequirements) {
        boolean z;
        mediaDescriptionRequirements.setStreamId(super.getId());
        mediaDescriptionRequirements.setStreamType(super.getType());
        CoreTransport coreTransportRtp = getCoreTransportRtp();
        if (coreTransportRtp != null) {
            mediaDescriptionRequirements.setLikelyTransportAddress(obtainLikelyTransportAddress(coreTransportRtp));
            IceTransport iceTransport = coreTransportRtp.getIceTransport();
            if (iceTransport != null && iceTransport.getUseIce()) {
                z = true;
                mediaDescriptionRequirements.setIcePolicy(coreTransportRtp.getIceTransport().getIcePolicy());
                mediaDescriptionRequirements.setUseIce(z);
                mediaDescriptionRequirements.setLocalIceParameters(getLocalIceParameters());
                mediaDescriptionRequirements.setIceCandidates(getLocalCandidates());
                mediaDescriptionRequirements.setDisabled(getDisabled());
                mediaDescriptionRequirements.setEncryptionPolicy(getEncryptionPolicy());
                mediaDescriptionRequirements.setSdesPolicy(getSdesPolicy());
                mediaDescriptionRequirements.setDtlsParameters(getLocalDtlsParameters());
                mediaDescriptionRequirements.setUseDtls(getUseDtls());
                mediaDescriptionRequirements.setUseSdes(getUseSdes());
                mediaDescriptionRequirements.setEncryptionModes(getEncryptionModes());
                mediaDescriptionRequirements.setBundlePolicy(getBundlePolicy());
                mediaDescriptionRequirements.setMediaStreamIdentifier(super.getMediaStreamIdentification());
            }
        }
        z = false;
        mediaDescriptionRequirements.setUseIce(z);
        mediaDescriptionRequirements.setLocalIceParameters(getLocalIceParameters());
        mediaDescriptionRequirements.setIceCandidates(getLocalCandidates());
        mediaDescriptionRequirements.setDisabled(getDisabled());
        mediaDescriptionRequirements.setEncryptionPolicy(getEncryptionPolicy());
        mediaDescriptionRequirements.setSdesPolicy(getSdesPolicy());
        mediaDescriptionRequirements.setDtlsParameters(getLocalDtlsParameters());
        mediaDescriptionRequirements.setUseDtls(getUseDtls());
        mediaDescriptionRequirements.setUseSdes(getUseSdes());
        mediaDescriptionRequirements.setEncryptionModes(getEncryptionModes());
        mediaDescriptionRequirements.setBundlePolicy(getBundlePolicy());
        mediaDescriptionRequirements.setMediaStreamIdentifier(super.getMediaStreamIdentification());
    }

    /* access modifiers changed from: protected */
    public void processBundledStateChanged(boolean z) {
        if (z) {
            CoreTransport coreTransportRtp = getCoreTransportRtp();
            if (coreTransportRtp != null && !Global.equals(coreTransportRtp.getId(), getBundleCoreTransport().getId())) {
                this.__redundantCoreTransports.add(getCoreTransportRtp());
                setCoreTransportRtp(getBundleCoreTransport());
            }
            CoreTransport coreTransportRtcp = getCoreTransportRtcp();
            if (coreTransportRtcp != null && !Global.equals(coreTransportRtcp.getId(), getBundleCoreTransport().getId())) {
                this.__redundantCoreTransports.add(getCoreTransportRtcp());
                setCoreTransportRtcp(getBundleCoreTransport());
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception("Transitions from the stream bundled to non-bundled state are not supported."));
    }

    /* access modifiers changed from: protected */
    public void raiseDisabledChange() {
        IAction0 iAction0 = this._onDisabledChange;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public void removeOnDisabledChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onDisabledChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onDisabledChange.remove(iAction0);
        if (this.__onDisabledChange.size() == 0) {
            this._onDisabledChange = null;
        }
    }

    private void set_LocalCandidates(ArrayList<Candidate> arrayList) {
        this.__localCandidates = arrayList;
    }

    /* access modifiers changed from: package-private */
    public void setBundleCoreTransport(CoreTransport coreTransport) {
        this._bundleCoreTransport = coreTransport;
    }

    /* access modifiers changed from: package-private */
    public void setBundled(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__bundled), Boolean.valueOf(z))) {
            this.__bundled = z;
            processBundledStateChanged(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        this._bundlePolicy = bundlePolicy;
    }

    /* access modifiers changed from: package-private */
    public void setCoreTransportRtcp(CoreTransport coreTransport) {
        if (!Global.equals(this.__coreTransportRtcp, coreTransport)) {
            this.__coreTransportRtcp = coreTransport;
            if (coreTransport != null && !getBundled()) {
                this.__coreTransportRtcp.getGatherer().setLocalParameters(getLocalIceParameters());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setCoreTransportRtp(CoreTransport coreTransport) {
        if (!Global.equals(this.__coreTransportRtp, coreTransport)) {
            this.__coreTransportRtp = coreTransport;
            if (coreTransport != null && !getBundled()) {
                this.__coreTransportRtp.getGatherer().setLocalParameters(getLocalIceParameters());
            }
        }
    }

    public void setDeactivated(boolean z) {
        throw new RuntimeException(new Exception("Streams cannot set Deactivated directly. Use SetLocal/RemoteDescription instead."));
    }

    public void setDisabled(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__disabled), Boolean.valueOf(z))) {
            this.__disabled = z;
            raiseDisabledChange();
        }
    }

    public void setEncryptionMode(EncryptionMode encryptionMode) {
        if (!Global.equals(encryptionMode, EncryptionMode.Null)) {
            setEncryptionModes(new EncryptionMode[]{encryptionMode});
            return;
        }
        disableEncryption();
    }

    public void setEncryptionModes(EncryptionMode[] encryptionModeArr) {
        if (encryptionModeArr == null || ArrayExtensions.getLength((Object[]) encryptionModeArr) == 0) {
            throw new RuntimeException(new Exception("At least one encryption mode must be specified."));
        } else if (ArrayExtensions.getLength((Object[]) encryptionModeArr) != 1 || !Global.equals(encryptionModeArr[0], EncryptionMode.Null)) {
            this.__encryptionModes = encryptionModeArr;
        } else {
            disableEncryption();
        }
    }

    public void setEncryptionPolicy(EncryptionPolicy encryptionPolicy) {
        if (!Global.equals(super.getType(), StreamType.Application)) {
            this.__encryptionPolicy = encryptionPolicy;
        } else if (Global.equals(encryptionPolicy, EncryptionPolicy.Required)) {
            this.__encryptionPolicy = encryptionPolicy;
        } else {
            throw new RuntimeException(new Exception("Data streams require encryption."));
        }
        if (Global.equals(encryptionPolicy, EncryptionPolicy.Disabled)) {
            disableEncryption();
        }
    }

    private void setIceRole(IceRole iceRole) {
        this._iceRole = iceRole;
    }

    /* access modifiers changed from: package-private */
    public void setIndex(int i) {
        this._index = i;
    }

    /* access modifiers changed from: package-private */
    public void setLocalDtlsParameters(DtlsParameters dtlsParameters) {
        this.__localDtlsParameters = dtlsParameters;
        CoreTransport coreTransportRtp = getCoreTransportRtp();
        CoreTransport coreTransportRtcp = getCoreTransportRtcp();
        if (!getBundled()) {
            if (coreTransportRtp != null) {
                coreTransportRtp.getDtlsTransport().setLocalParameters(this.__localDtlsParameters);
            }
            if (coreTransportRtcp != null) {
                coreTransportRtcp.getDtlsTransport().setLocalParameters(this.__localDtlsParameters);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocalIceParameters(IceParameters iceParameters) {
        this._localIceParameters = iceParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteDtlsParameters(DtlsParameters dtlsParameters) {
        this._remoteDtlsParameters = dtlsParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteIceParameters(IceParameters iceParameters) {
        this._remoteIceParameters = iceParameters;
    }

    public void setSdesPolicy(SdesPolicy sdesPolicy) {
        this.__sdesPolicy = sdesPolicy;
    }

    public void setUseDtls(boolean z) {
        this._useDtls = z;
    }

    public void setUseSdes(boolean z) {
        this._useSdes = z;
    }

    public Stream(StreamType streamType) {
        super(streamType);
        set_LocalCandidates(new ArrayList());
        setEncryptionMode(EncryptionMode.Aes128Strong);
        if (Global.equals(streamType, StreamType.Application)) {
            setUseSdes(false);
        } else {
            setUseSdes(true);
        }
        setUseDtls(true);
        setDisabled(false);
    }

    /* access modifiers changed from: package-private */
    public void updateProperties(boolean z, boolean z2, boolean z3) {
        MediaDescriptionManager mediaDescriptionManager = (MediaDescriptionManager) getMediaDescriptionManager();
        if (!z) {
            setBundled(mediaDescriptionManager.getBundled() && (Global.equals(getBundlePolicy(), BundlePolicy.MaxBundle) || (Global.equals(getBundlePolicy(), BundlePolicy.MaxCompatibility) && (!z2 || !z3))));
            if (!getBundled()) {
                setUseDtls(mediaDescriptionManager.getUseDtls());
                setUseSdes(mediaDescriptionManager.getUseSdes());
                setEncryptionMode(mediaDescriptionManager.getEncryptionMode());
                setRemoteDtlsParameters(mediaDescriptionManager.getRemoteDtlsParameters());
                CoreTransport coreTransportRtp = getCoreTransportRtp();
                CoreTransport coreTransportRtcp = getCoreTransportRtcp();
                if (coreTransportRtp != null) {
                    coreTransportRtp.getIceTransport().setSingleRemoteCandidateForIceBypass(mediaDescriptionManager.getPrimaryRemoteCandidateFromMLine());
                }
                if (coreTransportRtcp != null) {
                    coreTransportRtcp.getIceTransport().setSingleRemoteCandidateForIceBypass(mediaDescriptionManager.getSecondaryRemoteCandidateFromMLine());
                }
                setIceRole(mediaDescriptionManager.getIceRole());
                setLocalIceParameters(mediaDescriptionManager.getLocalIceParameters());
                setRemoteIceParameters(mediaDescriptionManager.getRemoteIceParameters());
                enableIceForInternalTransports(mediaDescriptionManager.getEnableIceForInternalTransports());
                super.setMediaStreamIdentification(mediaDescriptionManager.getMediaStreamIdentifier());
            }
        }
        if (z && z2) {
            processCachedSettings();
        }
    }
}
