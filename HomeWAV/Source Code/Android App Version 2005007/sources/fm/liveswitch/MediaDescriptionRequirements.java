package fm.liveswitch;

abstract class MediaDescriptionRequirements extends MediaDescriptionRequirementsBase {
    private EncryptionMode[] __encryptionModes;
    private EncryptionPolicy __encryptionPolicy = EncryptionPolicy.Required;
    private Candidate[] __iceCandidates;
    private TransportAddress __likelyTransportAddress;
    private SdesPolicy __sdesPolicy = SdesPolicy.Negotiated;
    private BundlePolicy _bundlePolicy;
    private boolean _disabled;
    private DtlsParameters _dtlsParameters;
    private IcePolicy _icePolicy;
    private IceParameters _localIceParameters;
    private String _streamId;
    private StreamType _streamType;
    private boolean _useDtls;
    private boolean _useIce;
    private boolean _useSdes;

    public BundlePolicy getBundlePolicy() {
        return this._bundlePolicy;
    }

    public boolean getDisabled() {
        return this._disabled;
    }

    public DtlsParameters getDtlsParameters() {
        return this._dtlsParameters;
    }

    public EncryptionMode[] getEncryptionModes() {
        return this.__encryptionModes;
    }

    public EncryptionPolicy getEncryptionPolicy() {
        return this.__encryptionPolicy;
    }

    public Candidate[] getIceCandidates() {
        if (this.__iceCandidates == null) {
            this.__iceCandidates = new Candidate[0];
        }
        return this.__iceCandidates;
    }

    public IcePolicy getIcePolicy() {
        return this._icePolicy;
    }

    public TransportAddress getLikelyTransportAddress() {
        if (this.__likelyTransportAddress == null) {
            this.__likelyTransportAddress = new TransportAddress("0.0.0.0", 9);
        }
        return this.__likelyTransportAddress;
    }

    public IceParameters getLocalIceParameters() {
        return this._localIceParameters;
    }

    public SdesPolicy getSdesPolicy() {
        return this.__sdesPolicy;
    }

    public String getStreamId() {
        return this._streamId;
    }

    public StreamType getStreamType() {
        return this._streamType;
    }

    public boolean getUseDtls() {
        return this._useDtls;
    }

    public boolean getUseIce() {
        return this._useIce;
    }

    public boolean getUseSdes() {
        return this._useSdes;
    }

    protected MediaDescriptionRequirements() {
    }

    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        this._bundlePolicy = bundlePolicy;
    }

    public void setDisabled(boolean z) {
        this._disabled = z;
    }

    public void setDtlsParameters(DtlsParameters dtlsParameters) {
        this._dtlsParameters = dtlsParameters;
    }

    public void setEncryptionModes(EncryptionMode[] encryptionModeArr) {
        if (encryptionModeArr == null || ArrayExtensions.getLength((Object[]) encryptionModeArr) == 0) {
            throw new RuntimeException(new Exception("At least one encryption mode must be specified."));
        }
        if (ArrayExtensions.getLength((Object[]) encryptionModeArr) == 1 && Global.equals(encryptionModeArr[0], EncryptionMode.Null) && Global.equals(getEncryptionPolicy(), EncryptionPolicy.Required)) {
            setEncryptionPolicy(EncryptionPolicy.Negotiated);
        }
        this.__encryptionModes = encryptionModeArr;
    }

    public void setEncryptionPolicy(EncryptionPolicy encryptionPolicy) {
        if (!Global.equals(getStreamType(), StreamType.Application)) {
            this.__encryptionPolicy = encryptionPolicy;
        } else if (Global.equals(encryptionPolicy, EncryptionPolicy.Required)) {
            this.__encryptionPolicy = encryptionPolicy;
        } else {
            throw new RuntimeException(new Exception("Data streams require encryption."));
        }
    }

    public void setIceCandidates(Candidate[] candidateArr) {
        this.__iceCandidates = candidateArr;
    }

    public void setIcePolicy(IcePolicy icePolicy) {
        this._icePolicy = icePolicy;
    }

    public void setLikelyTransportAddress(TransportAddress transportAddress) {
        this.__likelyTransportAddress = transportAddress;
    }

    public void setLocalIceParameters(IceParameters iceParameters) {
        this._localIceParameters = iceParameters;
    }

    public void setSdesPolicy(SdesPolicy sdesPolicy) {
        this.__sdesPolicy = sdesPolicy;
    }

    public void setStreamId(String str) {
        this._streamId = str;
    }

    public void setStreamType(StreamType streamType) {
        this._streamType = streamType;
    }

    public void setUseDtls(boolean z) {
        this._useDtls = z;
    }

    public void setUseIce(boolean z) {
        this._useIce = z;
    }

    public void setUseSdes(boolean z) {
        this._useSdes = z;
    }
}
