package fm.liveswitch;

import java.util.Date;

class SctpTransmissionControlBlock {
    static final int AckNewDataPacketCountThreshold = 4;
    static final float CWNDSACKTriggerLimit = 0.9f;
    public static final float ImmediateAckThreshold = 0.4f;
    public static final int InitRetrasmitLifetime = 10000;
    public static final int MaxAckDelay = 500;
    public static final int MaxDataChunkSize = 950;
    public static final int MaxDataPacketPayloadSize = 1050;
    public static final int MaxFTSNDelay = 500;
    public static final long MaxInitiateTag = 4294967295L;
    static final long MaxNumberStreamFormats = 4294967295L;
    public static final int MaxSsn = 65535;
    public static final long MaxSuggestedCookieLifespanIncrement = 4294967295L;
    public static final long MaxTsn = 4294967295L;
    public static final int NumberOfDuplicateTsnsToSkip = 1;
    public static final int NumberOfPacketsProcessorYieldThreshold = 0;
    public static final float RtoAlpha = 0.125f;
    public static final float RtoBeta = 0.25f;
    public static final int RtoInitial = 1000;
    public static final int RtoMax = 60000;
    public static final int RtoMin = 1000;
    public static final int T1InitRetransmissionInterval = 200;
    public static final int TimeToYield = 1;
    public static final int ValidCookieLife = 12000;
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(SctpTransmissionControlBlock.class);
    private long _advertisedReceiverWindowCredit;
    private int _associationPmtu;
    private SctpAuthenticatedChunksParameters _authenticatedChunksSupport;
    private long _congestionWindow;
    private boolean _cookieAckSent;
    private SctpDynamicAddressReconfigurationSupportParameters _dynamicAddressReconfigurationSupport;
    private long _earliestAllowedRetransmissionTime;
    private long _earliestAllowedSackSendTime;
    private boolean _extraPacketInFlight;
    private SctpSackChunk _freshestReceivedSack;
    private long _greatestCumulativeTsnReceived;
    private long _greatestReceivedTsn;
    private SctpStreamCollection _inboundStreams;
    private Date _lastSentTime;
    private long _maximumStaticCongestionWindow;
    private SctpForwardTsnChunk _mostRecentOutgoingForwardTsnChunk;
    private long _myVerificationTag;
    private long _nextTsnToSend;
    private int _numberOfDuplicateForwardTsnRequests;
    private SctpStreamCollection _outboundStreams;
    private int _overallErrorCount;
    private int _overallErrorThreshold;
    private long _partialBytesAcked;
    private SctpPartialReliabilitySupportParameters _partialReliabilitySupport;
    private long _peerReceiverWindowCredit;
    private long _peerVerificationTag;
    private boolean _remoteLikelyInConnectedState;
    private long _retransmissionTimeout;
    private long _roundTripTimeVariation;
    private int _rtoPending;
    private int _sackCounter;
    private DataBuffer _secretKeyForCookie;
    private ScheduledItem _sendForwardTsnScheduledItem;
    private long _smoothedRoundTripTime;
    private long _ssThresh;
    private SctpTcbState _state;

    public long getAdvertisedReceiverWindowCredit() {
        return this._advertisedReceiverWindowCredit;
    }

    public int getAssociationPmtu() {
        return this._associationPmtu;
    }

    public SctpAuthenticatedChunksParameters getAuthenticatedChunksSupport() {
        return this._authenticatedChunksSupport;
    }

    public long getCongestionWindow() {
        return this._congestionWindow;
    }

    public boolean getCookieAckSent() {
        return this._cookieAckSent;
    }

    public SctpDynamicAddressReconfigurationSupportParameters getDynamicAddressReconfigurationSupport() {
        return this._dynamicAddressReconfigurationSupport;
    }

    public long getEarliestAllowedRetransmissionTime() {
        return this._earliestAllowedRetransmissionTime;
    }

    public long getEarliestAllowedSackSendTime() {
        return this._earliestAllowedSackSendTime;
    }

    public boolean getExtraPacketInFlight() {
        return this._extraPacketInFlight;
    }

    public SctpSackChunk getFreshestReceivedSack() {
        return this._freshestReceivedSack;
    }

    public long getGreatestCumulativeTsnReceived() {
        return this._greatestCumulativeTsnReceived;
    }

    public long getGreatestReceivedTsn() {
        return this._greatestReceivedTsn;
    }

    /* access modifiers changed from: package-private */
    public SctpStreamCollection getInboundStreams() {
        return this._inboundStreams;
    }

    public Date getLastSentTime() {
        return this._lastSentTime;
    }

    public long getMaximumStaticCongestionWindow() {
        return this._maximumStaticCongestionWindow;
    }

    public SctpForwardTsnChunk getMostRecentOutgoingForwardTsnChunk() {
        return this._mostRecentOutgoingForwardTsnChunk;
    }

    public long getMyVerificationTag() {
        return this._myVerificationTag;
    }

    public SctpStateCookie getNewCookie() {
        if (getSecretKeyForCookie() != null) {
            return new SctpStateCookie(getMyVerificationTag(), getPeerVerificationTag(), getPeerReceiverWindowCredit(), getGreatestReceivedTsn(), getOutboundStreams().getCount(), Scheduler.getCurrentTime(), getSecretKeyForCookie(), getPartialReliabilitySupport() != null && getPartialReliabilitySupport().getPartialReliabilityUsedInThisAssociation());
        }
        Log.error("SCTP: Missing secret key to sign outgoing cookie.");
        return null;
    }

    public long getNextTsnToSend() {
        return this._nextTsnToSend;
    }

    public int getNumberOfDuplicateForwardTsnRequests() {
        return this._numberOfDuplicateForwardTsnRequests;
    }

    /* access modifiers changed from: package-private */
    public SctpStreamCollection getOutboundStreams() {
        return this._outboundStreams;
    }

    public int getOverallErrorCount() {
        return this._overallErrorCount;
    }

    public int getOverallErrorThreshold() {
        return this._overallErrorThreshold;
    }

    public long getPartialBytesAcked() {
        return this._partialBytesAcked;
    }

    public SctpPartialReliabilitySupportParameters getPartialReliabilitySupport() {
        return this._partialReliabilitySupport;
    }

    public long getPeerReceiverWindowCredit() {
        return this._peerReceiverWindowCredit;
    }

    public long getPeerVerificationTag() {
        return this._peerVerificationTag;
    }

    public boolean getRemoteLikelyInConnectedState() {
        return this._remoteLikelyInConnectedState;
    }

    public long getRetransmissionTimeout() {
        return this._retransmissionTimeout;
    }

    public long getRoundTripTimeVariation() {
        return this._roundTripTimeVariation;
    }

    public int getRtoPending() {
        return this._rtoPending;
    }

    public int getSackCounter() {
        return this._sackCounter;
    }

    public DataBuffer getSecretKeyForCookie() {
        return this._secretKeyForCookie;
    }

    public ScheduledItem getSendForwardTsnScheduledItem() {
        return this._sendForwardTsnScheduledItem;
    }

    public long getSmoothedRoundTripTime() {
        return this._smoothedRoundTripTime;
    }

    public long getSSThresh() {
        return this._ssThresh;
    }

    public SctpTcbState getState() {
        return this._state;
    }

    public void importTcbParameters(SctpTransmissionControlBlock sctpTransmissionControlBlock) {
        setMyVerificationTag(sctpTransmissionControlBlock.getMyVerificationTag());
        setPeerVerificationTag(sctpTransmissionControlBlock.getPeerVerificationTag());
        setPeerReceiverWindowCredit(sctpTransmissionControlBlock.getPeerReceiverWindowCredit());
        setNextTsnToSend(sctpTransmissionControlBlock.getNextTsnToSend());
        setGreatestReceivedTsn(sctpTransmissionControlBlock.getGreatestReceivedTsn());
        setGreatestCumulativeTsnReceived(sctpTransmissionControlBlock.getGreatestReceivedTsn());
        setOutboundStreams(sctpTransmissionControlBlock.getOutboundStreams());
        setPartialReliabilitySupport(sctpTransmissionControlBlock.getPartialReliabilitySupport());
        resetAssociationState();
    }

    public void resetAssociationState() {
        setRemoteLikelyInConnectedState(false);
        setSackCounter(4);
        setEarliestAllowedSackSendTime((long) ScheduledItem.getUnset());
        setEarliestAllowedRetransmissionTime((long) ScheduledItem.getUnset());
        setExtraPacketInFlight(false);
        setSackCounter(0);
        setRetransmissionTimeout(1000);
        setCookieAckSent(false);
    }

    public SctpTransmissionControlBlock(SctpStateCookie sctpStateCookie) {
        this(sctpStateCookie.getMyVerificationTag(), sctpStateCookie.getPeerVerificationTag(), sctpStateCookie.getPeerReceiverWindowCredit(), sctpStateCookie.getLastReceivedTsnFromPeer(), sctpStateCookie.getNumberOfOutboundStreams(), (DataBuffer) null);
    }

    public SctpTransmissionControlBlock(long j, long j2, long j3, long j4, int i, DataBuffer dataBuffer) {
        this._lastSentTime = new Date();
        setEarliestAllowedSackSendTime((long) ScheduledItem.getUnset());
        setEarliestAllowedRetransmissionTime((long) ScheduledItem.getUnset());
        setMyVerificationTag(j);
        setPeerVerificationTag(j2);
        setPeerReceiverWindowCredit(j3);
        setNextTsnToSend(j);
        setGreatestReceivedTsn(j4);
        setOutboundStreams(new SctpStreamCollection(i));
        setSecretKeyForCookie(dataBuffer);
        setRemoteLikelyInConnectedState(false);
        setCookieAckSent(false);
    }

    public SctpTransmissionControlBlock(int i, int i2, long j) {
        this._lastSentTime = new Date();
        setMyVerificationTag(0);
        setCookieAckSent(false);
        setState(SctpTcbState.ClosedNeverOpened);
        setOutboundStreams(new SctpStreamCollection(i));
        setInboundStreams(new SctpStreamCollection(i2));
        setAdvertisedReceiverWindowCredit(j);
        setSecretKeyForCookie(__dataBufferPool.take(10));
        new Randomizer().nextBytes(getSecretKeyForCookie().getData());
        resetAssociationState();
    }

    public void setAdvertisedReceiverWindowCredit(long j) {
        this._advertisedReceiverWindowCredit = j;
    }

    public void setAssociationPmtu(int i) {
        this._associationPmtu = i;
    }

    public void setAuthenticatedChunksSupport(SctpAuthenticatedChunksParameters sctpAuthenticatedChunksParameters) {
        this._authenticatedChunksSupport = sctpAuthenticatedChunksParameters;
    }

    public void setCongestionWindow(long j) {
        this._congestionWindow = j;
    }

    public void setCookieAckSent(boolean z) {
        this._cookieAckSent = z;
    }

    public void setDynamicAddressReconfigurationSupport(SctpDynamicAddressReconfigurationSupportParameters sctpDynamicAddressReconfigurationSupportParameters) {
        this._dynamicAddressReconfigurationSupport = sctpDynamicAddressReconfigurationSupportParameters;
    }

    public void setEarliestAllowedRetransmissionTime(long j) {
        this._earliestAllowedRetransmissionTime = j;
    }

    public void setEarliestAllowedSackSendTime(long j) {
        this._earliestAllowedSackSendTime = j;
    }

    public void setExtraPacketInFlight(boolean z) {
        this._extraPacketInFlight = z;
    }

    public void setFreshestReceivedSack(SctpSackChunk sctpSackChunk) {
        this._freshestReceivedSack = sctpSackChunk;
    }

    public void setGreatestCumulativeTsnReceived(long j) {
        this._greatestCumulativeTsnReceived = j;
    }

    public void setGreatestReceivedTsn(long j) {
        this._greatestReceivedTsn = j;
    }

    /* access modifiers changed from: package-private */
    public void setInboundStreams(SctpStreamCollection sctpStreamCollection) {
        this._inboundStreams = sctpStreamCollection;
    }

    public void setLastSentTime(Date date) {
        this._lastSentTime = date;
    }

    public void setMaximumStaticCongestionWindow(long j) {
        this._maximumStaticCongestionWindow = j;
    }

    public void setMostRecentOutgoingForwardTsnChunk(SctpForwardTsnChunk sctpForwardTsnChunk) {
        this._mostRecentOutgoingForwardTsnChunk = sctpForwardTsnChunk;
    }

    public void setMyVerificationTag(long j) {
        this._myVerificationTag = j;
    }

    public void setNextTsnToSend(long j) {
        this._nextTsnToSend = j;
    }

    public void setNumberOfDuplicateForwardTsnRequests(int i) {
        this._numberOfDuplicateForwardTsnRequests = i;
    }

    /* access modifiers changed from: package-private */
    public void setOutboundStreams(SctpStreamCollection sctpStreamCollection) {
        this._outboundStreams = sctpStreamCollection;
    }

    public void setOverallErrorCount(int i) {
        this._overallErrorCount = i;
    }

    public void setOverallErrorThreshold(int i) {
        this._overallErrorThreshold = i;
    }

    public void setPartialBytesAcked(long j) {
        this._partialBytesAcked = j;
    }

    public void setPartialReliabilitySupport(SctpPartialReliabilitySupportParameters sctpPartialReliabilitySupportParameters) {
        this._partialReliabilitySupport = sctpPartialReliabilitySupportParameters;
    }

    public void setPeerReceiverWindowCredit(long j) {
        this._peerReceiverWindowCredit = j;
    }

    public void setPeerVerificationTag(long j) {
        this._peerVerificationTag = j;
    }

    public void setRemoteLikelyInConnectedState(boolean z) {
        this._remoteLikelyInConnectedState = z;
    }

    public void setRetransmissionTimeout(long j) {
        this._retransmissionTimeout = j;
    }

    public void setRoundTripTimeVariation(long j) {
        this._roundTripTimeVariation = j;
    }

    public void setRtoPending(int i) {
        this._rtoPending = i;
    }

    public void setSackCounter(int i) {
        this._sackCounter = i;
    }

    private void setSecretKeyForCookie(DataBuffer dataBuffer) {
        this._secretKeyForCookie = dataBuffer;
    }

    public void setSendForwardTsnScheduledItem(ScheduledItem scheduledItem) {
        this._sendForwardTsnScheduledItem = scheduledItem;
    }

    public void setSmoothedRoundTripTime(long j) {
        this._smoothedRoundTripTime = j;
    }

    public void setSSThresh(long j) {
        this._ssThresh = j;
    }

    public void setState(SctpTcbState sctpTcbState) {
        this._state = sctpTcbState;
    }
}
