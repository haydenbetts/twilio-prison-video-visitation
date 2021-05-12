package fm.liveswitch;

import fm.liveswitch.MediaFormat;

class MediaStreamMediaDescriptionRequirements<TFormat extends MediaFormat<TFormat>> extends MediaDescriptionRequirements {
    private TransportAddress __likelySecondaryTransportAddress;
    private StreamDirection _absoluteSenderTimeLocalDirection;
    private MediaHeaderExtensionPolicy _absoluteSenderTimePolicy;
    private String _canonicalName;
    private CcmFirPolicy _ccmFirPolicy;
    private CcmLrrPolicy _ccmLrrPolicy;
    private CcmTmmbnPolicy _ccmTmmbnPolicy;
    private CcmTmmbrPolicy _ccmTmmbrPolicy;
    private StreamDirection _direction;
    private TFormat[] _formats;
    private int _localBandwidth;
    private String _localDescriptionMediaId;
    private String _localDescriptionTrackId;
    private int _maxReceiveEncodings;
    private boolean _multiplexingSupported;
    private NackPliPolicy _nackPliPolicy;
    private NackPolicy _nackPolicy;
    private EncodingInfo[] _receiveEncodings;
    private RedFecPolicy _redFecPolicy;
    private RembPolicy _rembPolicy;
    private StreamDirection _sdesMidLocalDirection;
    private MediaHeaderExtensionPolicy _sdesMidPolicy;
    private StreamDirection _sdesRepairedRtpStreamIdLocalDirection;
    private MediaHeaderExtensionPolicy _sdesRepairedRtpStreamIdPolicy;
    private StreamDirection _sdesRtpStreamIdLocalDirection;
    private MediaHeaderExtensionPolicy _sdesRtpStreamIdPolicy;
    private EncodingInfo[] _sendEncodings;
    private int _simulcastDraftVersion;
    private SimulcastMode _simulcastMode;

    /* access modifiers changed from: package-private */
    public StreamDirection getAbsoluteSenderTimeLocalDirection() {
        return this._absoluteSenderTimeLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getAbsoluteSenderTimePolicy() {
        return this._absoluteSenderTimePolicy;
    }

    public String getCanonicalName() {
        return this._canonicalName;
    }

    public CcmFirPolicy getCcmFirPolicy() {
        return this._ccmFirPolicy;
    }

    public CcmLrrPolicy getCcmLrrPolicy() {
        return this._ccmLrrPolicy;
    }

    public CcmTmmbnPolicy getCcmTmmbnPolicy() {
        return this._ccmTmmbnPolicy;
    }

    public CcmTmmbrPolicy getCcmTmmbrPolicy() {
        return this._ccmTmmbrPolicy;
    }

    public StreamDirection getDirection() {
        return this._direction;
    }

    public TFormat[] getFormats() {
        return this._formats;
    }

    public TransportAddress getLikelySecondaryTransportAddress() {
        if (this.__likelySecondaryTransportAddress == null) {
            this.__likelySecondaryTransportAddress = new TransportAddress("0.0.0.0", 9);
        }
        return this.__likelySecondaryTransportAddress;
    }

    public int getLocalBandwidth() {
        return this._localBandwidth;
    }

    public String getLocalDescriptionMediaId() {
        return this._localDescriptionMediaId;
    }

    public String getLocalDescriptionTrackId() {
        return this._localDescriptionTrackId;
    }

    public int getMaxReceiveEncodings() {
        return this._maxReceiveEncodings;
    }

    public boolean getMultiplexingSupported() {
        return this._multiplexingSupported;
    }

    public NackPliPolicy getNackPliPolicy() {
        return this._nackPliPolicy;
    }

    public NackPolicy getNackPolicy() {
        return this._nackPolicy;
    }

    public EncodingInfo[] getReceiveEncodings() {
        return this._receiveEncodings;
    }

    public RedFecPolicy getRedFecPolicy() {
        return this._redFecPolicy;
    }

    public RembPolicy getRembPolicy() {
        return this._rembPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesMidLocalDirection() {
        return this._sdesMidLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesMidPolicy() {
        return this._sdesMidPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRepairedRtpStreamIdLocalDirection() {
        return this._sdesRepairedRtpStreamIdLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesRepairedRtpStreamIdPolicy() {
        return this._sdesRepairedRtpStreamIdPolicy;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getSdesRtpStreamIdLocalDirection() {
        return this._sdesRtpStreamIdLocalDirection;
    }

    /* access modifiers changed from: package-private */
    public MediaHeaderExtensionPolicy getSdesRtpStreamIdPolicy() {
        return this._sdesRtpStreamIdPolicy;
    }

    public EncodingInfo[] getSendEncodings() {
        return this._sendEncodings;
    }

    public int getSimulcastDraftVersion() {
        return this._simulcastDraftVersion;
    }

    public SimulcastMode getSimulcastMode() {
        return this._simulcastMode;
    }

    /* access modifiers changed from: package-private */
    public void setAbsoluteSenderTimeLocalDirection(StreamDirection streamDirection) {
        this._absoluteSenderTimeLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setAbsoluteSenderTimePolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._absoluteSenderTimePolicy = mediaHeaderExtensionPolicy;
    }

    public void setCanonicalName(String str) {
        this._canonicalName = str;
    }

    public void setCcmFirPolicy(CcmFirPolicy ccmFirPolicy) {
        this._ccmFirPolicy = ccmFirPolicy;
    }

    public void setCcmLrrPolicy(CcmLrrPolicy ccmLrrPolicy) {
        this._ccmLrrPolicy = ccmLrrPolicy;
    }

    public void setCcmTmmbnPolicy(CcmTmmbnPolicy ccmTmmbnPolicy) {
        this._ccmTmmbnPolicy = ccmTmmbnPolicy;
    }

    public void setCcmTmmbrPolicy(CcmTmmbrPolicy ccmTmmbrPolicy) {
        this._ccmTmmbrPolicy = ccmTmmbrPolicy;
    }

    public void setDirection(StreamDirection streamDirection) {
        this._direction = streamDirection;
    }

    public void setFormats(TFormat[] tformatArr) {
        this._formats = tformatArr;
    }

    public void setLikelySecondaryTransportAddress(TransportAddress transportAddress) {
        this.__likelySecondaryTransportAddress = transportAddress;
    }

    public void setLocalBandwidth(int i) {
        this._localBandwidth = i;
    }

    public void setLocalDescriptionMediaId(String str) {
        this._localDescriptionMediaId = str;
    }

    public void setLocalDescriptionTrackId(String str) {
        this._localDescriptionTrackId = str;
    }

    public void setMaxReceiveEncodings(int i) {
        this._maxReceiveEncodings = i;
    }

    public void setMultiplexingSupported(boolean z) {
        this._multiplexingSupported = z;
    }

    public void setNackPliPolicy(NackPliPolicy nackPliPolicy) {
        this._nackPliPolicy = nackPliPolicy;
    }

    public void setNackPolicy(NackPolicy nackPolicy) {
        this._nackPolicy = nackPolicy;
    }

    public void setReceiveEncodings(EncodingInfo[] encodingInfoArr) {
        this._receiveEncodings = encodingInfoArr;
    }

    public void setRedFecPolicy(RedFecPolicy redFecPolicy) {
        this._redFecPolicy = redFecPolicy;
    }

    public void setRembPolicy(RembPolicy rembPolicy) {
        this._rembPolicy = rembPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSdesMidLocalDirection(StreamDirection streamDirection) {
        this._sdesMidLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesMidPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesMidPolicy = mediaHeaderExtensionPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRepairedRtpStreamIdLocalDirection(StreamDirection streamDirection) {
        this._sdesRepairedRtpStreamIdLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRepairedRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesRepairedRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRtpStreamIdLocalDirection(StreamDirection streamDirection) {
        this._sdesRtpStreamIdLocalDirection = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setSdesRtpStreamIdPolicy(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy) {
        this._sdesRtpStreamIdPolicy = mediaHeaderExtensionPolicy;
    }

    public void setSendEncodings(EncodingInfo[] encodingInfoArr) {
        this._sendEncodings = encodingInfoArr;
    }

    public void setSimulcastDraftVersion(int i) {
        this._simulcastDraftVersion = i;
    }

    public void setSimulcastMode(SimulcastMode simulcastMode) {
        this._simulcastMode = simulcastMode;
    }
}
