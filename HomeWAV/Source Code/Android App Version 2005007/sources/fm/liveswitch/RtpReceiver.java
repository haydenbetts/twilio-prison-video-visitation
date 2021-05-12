package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.diagnostics.DurationTimer;
import fm.liveswitch.diagnostics.Timers;
import java.util.ArrayList;

class RtpReceiver<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> {
    private static ILog __log = Log.getLogger(RtpReceiver.class);
    private BitrateMonitor __bitrateMonitor;
    private SrtpContext __context = null;
    private int __currentClockRate = -1;
    private int __currentPayloadType = -1;
    private DurationTimer __decryptDurationTimer;
    private FecReceiver __fecReceiver;
    private AtomicLong __firsSent = new AtomicLong();
    private RtpMovingAverage __frameDurationAverage = new RtpMovingAverage(100);
    private int __inboundPacketsLostRtp = 0;
    private RtpInterFrameDelay __interFrameDelay = null;
    private RtpJitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> __jitterBuffer;
    private RtpJitterEstimator __jitterEstimator = null;
    private long __lastCcmSendIncrementSystemTimestamp = -1;
    private int __lastCcmSendSequenceNumber = -1;
    private long __lastCcmSendSystemTimestamp = -1;
    private long __lastExpectedPacketCount = 0;
    private long __lastFinalTimestamp = -1;
    private long __lastNackRateReportSystemTimestamp = -1;
    private long __lastPliSendSystemTimestamp = -1;
    private long __lastReceiveTransit = 0;
    private long __lastReceivedPacketCount = 0;
    private RtpLastSenderReportInfo __lastSenderReportInfo = null;
    private String __logScope;
    private RtpLossController __lossController;
    private AtomicLong __lrrsSent = new AtomicLong();
    private long __maxReceivedPacketIndex = -1;
    private long __minReceivedPacketIndex = -1;
    private long __minReceivedRtpTimestamp = -1;
    private long __minReceivedSystemTimestamp = -1;
    private int __nackRateCritical = 150;
    private int __nackRateReportDelayTicks = (Constants.getTicksPerSecond() * 10);
    private int __nackRateWarning = 10;
    private AtomicLong __nacksSent = new AtomicLong();
    private int __nacksSentSinceLastReport = -1;
    private NetworkTimeProtocol __networkTimeProtocol = new NetworkTimeProtocol(0);
    private int __nextNackBufferRtpSequenceNumber = -1;
    private long __nextNackBufferSequenceNumber = -1;
    private AtomicLong __octetsReceivedRtcp = new AtomicLong();
    private AtomicLong __octetsReceivedRtp = new AtomicLong();
    private AtomicLong __packetsDiscarded = new AtomicLong();
    private AtomicLong __packetsDuplicated = new AtomicLong();
    private AtomicLong __packetsReceivedRtcp = new AtomicLong();
    private AtomicLong __packetsReceivedRtp = new AtomicLong();
    private AtomicLong __packetsRepaired = new AtomicLong();
    private AtomicLong __plisSent = new AtomicLong();
    private Object __receiveCountsLock = new Object();
    private double __receiveJitter = 0.0d;
    private int __redPayloadType;
    private long __referenceReceiveNtpTimestampTicks = -1;
    private long __referenceReceiveTimestamp = -1;
    private long __soloFecCount = 0;
    private int __ulpFecPayloadType;
    public IFunction3<RtpPacketHeader, DataBuffer, TFormat, TFrame> _createFrame;
    private RtpIExtensionParameters _extensionParameters;
    private RedFecConfig _fecConfig;
    private boolean _flushBuffer;
    private RtpIFormatParameters<TFormat> _formatParameters;
    private HexDump _hexDump;
    private long _highestTimestamp = -1;
    private String _id;
    private JitterConfig _jitterConfig;
    private boolean _legacy;
    private JitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> _legacyJitterBuffer;
    private NackBuffer<TFrame, TBuffer, TBufferCollection, TFormat> _legacyNackBuffer;
    private SrtpProtectionParameters _localProtectionParameters;
    private NackConfig _nackConfig;
    private RtpIParameters _parameters;
    private IAction1<TFrame> _receiveFrame;
    private SrtpProtectionParameters _remoteProtectionParameters;
    private String _remoteRepairedRtpStreamId;
    private String _remoteRtpStreamId;
    private long _remoteSynchronizationSource;
    private IFunction1<MediaControlFrame[], Integer> _sendControlFrames;
    private IFunction1<DataBuffer, DataBuffer> _testReceivedRtpBuffer;
    private long _timestampRolloverCounter = 0;
    private StreamType _type;

    private boolean canSendCcm(int i, int i2, long j) {
        int max = MathAssistant.max(i2, Constants.getTicksPerMillisecond() * 200);
        long j2 = this.__lastCcmSendSystemTimestamp;
        if (j2 != -1 && j - j2 < ((long) i)) {
            return false;
        }
        long j3 = this.__lastCcmSendIncrementSystemTimestamp;
        if (j3 == -1 || j - j3 >= ((long) max)) {
            this.__lastCcmSendIncrementSystemTimestamp = j;
            incrementCcmSendSequenceNumber();
        }
        this.__lastCcmSendSystemTimestamp = j;
        return true;
    }

    public boolean canSendFir(int i, int i2, long j) {
        if (!canSendCcm(i, i2, j)) {
            return false;
        }
        this.__firsSent.increment();
        return true;
    }

    public boolean canSendLrr(int i, int i2, long j) {
        if (!canSendCcm(i, i2, j)) {
            return false;
        }
        this.__lrrsSent.increment();
        return true;
    }

    public boolean canSendPli(int i, long j) {
        long j2 = this.__lastPliSendSystemTimestamp;
        if (j2 != -1 && j - j2 < ((long) i)) {
            return false;
        }
        this.__lastPliSendSystemTimestamp = j;
        this.__plisSent.increment();
        return true;
    }

    public void destroy() {
        RtpJitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> rtpJitterBuffer = this.__jitterBuffer;
        if (rtpJitterBuffer != null) {
            rtpJitterBuffer.destroy();
            this.__jitterBuffer = null;
        }
        SrtpContext srtpContext = this.__context;
        if (srtpContext != null) {
            srtpContext.destroy();
            this.__context = null;
        }
        DurationTimer durationTimer = this.__decryptDurationTimer;
        if (durationTimer != null) {
            durationTimer.destroy();
            this.__decryptDurationTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void fecReceiverCallback(FecRawPacket fecRawPacket, boolean z) {
        MediaFrame generateFrame;
        if (this.__context != null) {
            DataBuffer wrap = DataBuffer.wrap(fecRawPacket.getData(), 0, fecRawPacket.getLength());
            RtpPacketHeader readFrom = RtpPacketHeader.readFrom(wrap);
            MediaFormat negotiatedFormat = getFormatParameters().getNegotiatedFormat(readFrom.getPayloadType());
            if (negotiatedFormat != null && (generateFrame = generateFrame(readFrom, wrap.subset(readFrom.calculateHeaderLength()), negotiatedFormat, this.__context)) != null) {
                if (z) {
                    this.__packetsRepaired.increment();
                }
                generateFrame.setSequenceNumber(generateFrame.getSequenceNumber() - this.__soloFecCount);
                generateFrame.setRtpSequenceNumber((int) (generateFrame.getSequenceNumber() % 65536));
                generateFrame.getBuffer().setSequenceNumber(generateFrame.getSequenceNumber());
                generateFrame.getBuffer().getRtpHeader().setSequenceNumber(generateFrame.getRtpSequenceNumber());
                if (getLegacy()) {
                    processLegacyJitter(generateFrame);
                } else {
                    processFinal(generateFrame);
                }
            }
        }
    }

    private void finishInitialization(long j) {
        setRemoteSynchronizationSource(j);
        this.__logScope = StringExtensions.format("{0}-{1}", StringExtensions.toLower(getType().toString()), LongExtensions.toString(Long.valueOf(j)));
        this.__jitterEstimator = new RtpJitterEstimator(this.__logScope);
        this.__context.setRemoteSynchronizationSource(j);
        this.__decryptDurationTimer = Timers.getDurationTimer(StringExtensions.format("RTP Decrypt ({0} {1})", getType().toString(), LongExtensions.toString(Long.valueOf(j))));
    }

    private TFrame generateFrame(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer, TFormat tformat, SrtpContext srtpContext) {
        RtpHeaderExtension parseRawHeaderExtension;
        IFunction3<RtpPacketHeader, DataBuffer, TFormat, TFrame> iFunction3 = this._createFrame;
        if (iFunction3 == null) {
            return null;
        }
        MediaFormat clone = tformat.clone();
        clone.setIsEncrypted(false);
        TFrame tframe = (MediaFrame) iFunction3.invoke(rtpPacketHeader, dataBuffer, clone);
        tframe.setRtpTimestamp(rtpPacketHeader.getTimestamp());
        tframe.setTimestamp(getMediaFrameTimestamp(rtpPacketHeader.getTimestamp()));
        tframe.setRtpSequenceNumber(rtpPacketHeader.getSequenceNumber());
        tframe.setSequenceNumber(srtpContext.getRtpDecryptionPacketIndex(rtpPacketHeader.getSequenceNumber()));
        tframe.getBuffer().setSequenceNumber(tframe.getSequenceNumber());
        tframe.setSynchronizationSource(rtpPacketHeader.getSynchronizationSource());
        tframe.setContributingSources(rtpPacketHeader.getContributingSources());
        RtpRawHeaderExtension rtpRawHeaderExtension = (RtpRawHeaderExtension) Global.tryCast(rtpPacketHeader.getHeaderExtension(), RtpRawHeaderExtension.class);
        RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry = getExtensionParameters().getRtpHeaderExtensionRegistry();
        if (rtpRawHeaderExtension == null || rtpHeaderExtensionRegistry == null || rtpHeaderExtensionRegistry.getNumberOfNegotiatedExtensions() <= 0 || (parseRawHeaderExtension = RtpHeaderExtension.parseRawHeaderExtension(rtpRawHeaderExtension, rtpHeaderExtensionRegistry)) == null) {
            return tframe;
        }
        RtpHeaderSdesMid sdesMid = parseRawHeaderExtension.getSdesMid();
        if (sdesMid != null) {
            tframe.setMid(sdesMid.getText());
        }
        RtpHeaderSdesRtpStreamId sdesRtpStreamId = parseRawHeaderExtension.getSdesRtpStreamId();
        if (sdesRtpStreamId != null) {
            tframe.setRtpStreamId(sdesRtpStreamId.getText());
            if (getRemoteRtpStreamId() == null) {
                setRemoteRtpStreamId(tframe.getRtpStreamId());
                getParameters().updateRemoteSynchronizationSource(getRemoteSynchronizationSource(), getRemoteRtpStreamId(), (String) null);
            }
        }
        RtpHeaderSdesRepairedRtpStreamId sdesRepairedRtpStreamId = parseRawHeaderExtension.getSdesRepairedRtpStreamId();
        if (sdesRepairedRtpStreamId != null) {
            tframe.setRepairedRtpStreamId(sdesRepairedRtpStreamId.getText());
            if (getRemoteRepairedRtpStreamId() == null) {
                setRemoteRepairedRtpStreamId(tframe.getRepairedRtpStreamId());
                getParameters().updateRemoteSynchronizationSource(getRemoteSynchronizationSource(), (String) null, getRemoteRepairedRtpStreamId());
            }
        }
        return tframe;
    }

    public int getCcmSendSequenceNumber() {
        return this.__lastCcmSendSequenceNumber;
    }

    private CodecStats getCodecStats() {
        MediaFormat negotiatedFormat;
        AudioFormat audioFormat;
        int i = this.__currentPayloadType;
        if (i == -1 || (negotiatedFormat = getFormatParameters().getNegotiatedFormat(i)) == null) {
            return null;
        }
        CodecStats codecStats = new CodecStats();
        codecStats.setId(getId());
        codecStats.setTimestamp(DateExtensions.getUtcNow());
        codecStats.setName(negotiatedFormat.getName());
        codecStats.setClockRate(negotiatedFormat.getClockRate());
        codecStats.setParameters(negotiatedFormat.getParameters());
        codecStats.setPayloadType(i);
        codecStats.setCodecType(CodecType.Decode);
        if (Global.equals(getType(), StreamType.Audio) && (audioFormat = (AudioFormat) Global.tryCast(negotiatedFormat, AudioFormat.class)) != null) {
            codecStats.setChannelCount(audioFormat.getChannelCount());
        }
        return codecStats;
    }

    public int getCurrentClockRate() {
        return this.__currentClockRate;
    }

    public int getCurrentJitter() {
        if (this.__currentClockRate == -1) {
            return 0;
        }
        return (int) MathAssistant.ceil((this.__receiveJitter * ((double) Constants.getMillisecondsPerSecond())) / ((double) this.__currentClockRate));
    }

    public int getCurrentPayloadType() {
        return this.__currentPayloadType;
    }

    public RtpIExtensionParameters getExtensionParameters() {
        return this._extensionParameters;
    }

    public RedFecConfig getFecConfig() {
        return this._fecConfig;
    }

    public boolean getFecEnabled() {
        RedFecConfig fecConfig = getFecConfig();
        return fecConfig != null && !fecConfig.getDisabled();
    }

    public long getFirsSent() {
        return this.__firsSent.getValue();
    }

    /* access modifiers changed from: package-private */
    public boolean getFlushBuffer() {
        return this._flushBuffer;
    }

    public RtpIFormatParameters<TFormat> getFormatParameters() {
        return this._formatParameters;
    }

    public HexDump getHexDump() {
        return this._hexDump;
    }

    public String getId() {
        return this._id;
    }

    public double getInitialLossBasedBandwidthEstimate() {
        return this.__lossController.getInitialBandwidthEstimate();
    }

    public JitterConfig getJitterConfig() {
        return this._jitterConfig;
    }

    public boolean getJitterEnabled() {
        JitterConfig jitterConfig = getJitterConfig();
        return jitterConfig != null && !jitterConfig.getDisableBuffering();
    }

    public boolean getLegacy() {
        return this._legacy;
    }

    public JitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> getLegacyJitterBuffer() {
        return this._legacyJitterBuffer;
    }

    public NackBuffer<TFrame, TBuffer, TBufferCollection, TFormat> getLegacyNackBuffer() {
        return this._legacyNackBuffer;
    }

    public SrtpProtectionParameters getLocalProtectionParameters() {
        return this._localProtectionParameters;
    }

    public double getLossBasedBandwidthEstimate() {
        return this.__lossController.getBandwidthEstimate();
    }

    public long getLrrsSent() {
        return this.__lrrsSent.getValue();
    }

    private long getMediaFrameTimestamp(long j) {
        long j2;
        long j3;
        long j4 = this._highestTimestamp;
        if (j4 == -1) {
            this._highestTimestamp = j;
            return j;
        }
        if (j4 < 2147483648L) {
            if (j - j4 > 2147483648L) {
                j2 = (this._timestampRolloverCounter - 1) % 4294967296L;
                Long.signum(j2);
                return (j2 * 4294967296L) + j;
            }
            j3 = this._timestampRolloverCounter;
            this._highestTimestamp = MathAssistant.max(j4, j);
        } else if (j4 - 2147483648L > j) {
            j2 = (this._timestampRolloverCounter + 1) % 4294967296L;
            this._highestTimestamp = j;
            this._timestampRolloverCounter = j2;
            Long.signum(j2);
            return (j2 * 4294967296L) + j;
        } else {
            j3 = this._timestampRolloverCounter;
            this._highestTimestamp = MathAssistant.max(j4, j);
        }
        j2 = j3;
        Long.signum(j2);
        return (j2 * 4294967296L) + j;
    }

    public NackConfig getNackConfig() {
        return this._nackConfig;
    }

    public boolean getNackEnabled() {
        NackConfig nackConfig = getNackConfig();
        return nackConfig != null && !nackConfig.getDisableBuffering();
    }

    public long getNacksSent() {
        return this.__nacksSent.getValue();
    }

    public long getOctetsReceivedRtcp() {
        return this.__octetsReceivedRtcp.getValue();
    }

    public long getOctetsReceivedRtp() {
        return this.__octetsReceivedRtp.getValue();
    }

    public long getPacketsDiscarded() {
        if (getLegacy()) {
            return this.__packetsDiscarded.getValue();
        }
        RtpJitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> rtpJitterBuffer = this.__jitterBuffer;
        if (rtpJitterBuffer == null) {
            return 0;
        }
        return rtpJitterBuffer.getLateCount();
    }

    public long getPacketsDuplicated() {
        if (getLegacy()) {
            return this.__packetsDuplicated.getValue();
        }
        RtpJitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> rtpJitterBuffer = this.__jitterBuffer;
        if (rtpJitterBuffer == null) {
            return 0;
        }
        return rtpJitterBuffer.getDuplicateCount();
    }

    public long getPacketsReceivedRtcp() {
        return this.__packetsReceivedRtcp.getValue();
    }

    public long getPacketsReceivedRtp() {
        return this.__packetsReceivedRtp.getValue();
    }

    public long getPacketsRepaired() {
        return this.__packetsRepaired.getValue();
    }

    public RtpIParameters getParameters() {
        return this._parameters;
    }

    public long getPlisSent() {
        return this.__plisSent.getValue();
    }

    public IAction1<TFrame> getReceiveFrame() {
        return this._receiveFrame;
    }

    public SrtpProtectionParameters getRemoteProtectionParameters() {
        return this._remoteProtectionParameters;
    }

    public String getRemoteRepairedRtpStreamId() {
        return this._remoteRepairedRtpStreamId;
    }

    public String getRemoteRtpStreamId() {
        return this._remoteRtpStreamId;
    }

    public long getRemoteSynchronizationSource() {
        return this._remoteSynchronizationSource;
    }

    public ReportBlock getReportBlock(long j) {
        long j2;
        long j3;
        long value;
        long j4;
        long j5;
        long j6;
        if (this.__maxReceivedPacketIndex == -1) {
            return null;
        }
        ReportBlock reportBlock = new ReportBlock();
        reportBlock.setSynchronizationSource(getRemoteSynchronizationSource());
        RtpLastSenderReportInfo rtpLastSenderReportInfo = this.__lastSenderReportInfo;
        synchronized (this.__receiveCountsLock) {
            j2 = this.__maxReceivedPacketIndex;
            j3 = (j2 - this.__minReceivedPacketIndex) + 1;
            value = this.__packetsReceivedRtp.getValue();
            j4 = j3 - this.__lastExpectedPacketCount;
            this.__lastExpectedPacketCount = j3;
            j5 = value - this.__lastReceivedPacketCount;
            this.__lastReceivedPacketCount = value;
            j6 = (long) this.__receiveJitter;
        }
        int i = (int) (j4 - j5);
        if (j4 != 0 && i > 0) {
            reportBlock.setFractionLost((short) ((int) (((long) (i << 8)) / j4)));
        }
        reportBlock.setCumulativeNumberOfPacketsLost((int) (j3 - value));
        this.__inboundPacketsLostRtp = reportBlock.getCumulativeNumberOfPacketsLost();
        reportBlock.setExtendedHighestSequenceNumberReceived(j2);
        reportBlock.setInterarrivalJitter(j6);
        if (rtpLastSenderReportInfo == null) {
            reportBlock.setLastSenderReportTimestamp(0);
            reportBlock.setDelaySinceLastSenderReport(0);
        } else {
            reportBlock.setLastSenderReportTimestamp(Binary.fromBytes32(Binary.toBytes64(rtpLastSenderReportInfo.getNtpTimestamp(), false), 2, false));
            reportBlock.setDelaySinceLastSenderReport((MathAssistant.max(0, j - rtpLastSenderReportInfo.getTransportSystemTimestamp()) * 65536) / ((long) Constants.getTicksPerSecond()));
        }
        this.__lossController.update(reportBlock.getPercentLost());
        return reportBlock;
    }

    public IFunction1<MediaControlFrame[], Integer> getSendControlFrames() {
        return this._sendControlFrames;
    }

    public MediaReceiverStats getStats() {
        CodecStats codecStats = getCodecStats();
        MediaReceiverStats mediaReceiverStats = new MediaReceiverStats();
        mediaReceiverStats.setId(getId());
        mediaReceiverStats.setTimestamp(DateExtensions.getUtcNow());
        mediaReceiverStats.setSynchronizationSource(getRemoteSynchronizationSource());
        mediaReceiverStats.setRtpStreamId(getRemoteRtpStreamId());
        mediaReceiverStats.setRepairedRtpStreamId(getRemoteRepairedRtpStreamId());
        mediaReceiverStats.setCodec(codecStats);
        mediaReceiverStats.setNackCount(this.__nacksSent.getValue());
        mediaReceiverStats.setPliCount(this.__plisSent.getValue());
        mediaReceiverStats.setFirCount(this.__firsSent.getValue());
        mediaReceiverStats.setLrrCount(this.__lrrsSent.getValue());
        mediaReceiverStats.setBytesReceived(this.__octetsReceivedRtp.getValue());
        mediaReceiverStats.setPacketsReceived(this.__packetsReceivedRtp.getValue());
        mediaReceiverStats.setPacketsLost((long) this.__inboundPacketsLostRtp);
        mediaReceiverStats.setPacketsDiscarded(getPacketsDiscarded());
        mediaReceiverStats.setPacketsDuplicated(getPacketsDuplicated());
        mediaReceiverStats.setPacketsRepaired(getPacketsRepaired());
        mediaReceiverStats.setJitter(getCurrentJitter());
        return mediaReceiverStats;
    }

    public IFunction1<DataBuffer, DataBuffer> getTestReceivedRtpBuffer() {
        return this._testReceivedRtpBuffer;
    }

    public StreamType getType() {
        return this._type;
    }

    public boolean hasUnreportedLoss() {
        long j;
        long j2;
        if (this.__maxReceivedPacketIndex == -1) {
            return false;
        }
        synchronized (this.__receiveCountsLock) {
            long value = this.__packetsReceivedRtp.getValue();
            j = ((this.__maxReceivedPacketIndex - this.__minReceivedPacketIndex) + 1) - this.__lastExpectedPacketCount;
            j2 = value - this.__lastReceivedPacketCount;
        }
        if (j2 < j) {
            return true;
        }
        return false;
    }

    private void incrementCcmSendSequenceNumber() {
        this.__lastCcmSendSequenceNumber = (this.__lastCcmSendSequenceNumber + 1) % 256;
    }

    /* access modifiers changed from: private */
    public void legacyNackBufferReadFrameCallback(TFrame tframe) {
        this.__nextNackBufferSequenceNumber = tframe.getBuffer().getSequenceNumber() + 1;
        this.__nextNackBufferRtpSequenceNumber = (tframe.getBuffer().getRtpSequenceNumber() + 1) % 65536;
        processFec(tframe.clone());
    }

    /* access modifiers changed from: private */
    public void legacyNackBufferReadNackCallback(GenericNack genericNack) {
        GenericNackControlFrame genericNackControlFrame = new GenericNackControlFrame(genericNack);
        genericNackControlFrame.setMediaSourceSynchronizationSource(getRemoteSynchronizationSource());
        IFunction1<MediaControlFrame[], Integer> sendControlFrames = getSendControlFrames();
        if (sendControlFrames != null) {
            if (sendControlFrames.invoke(new MediaControlFrame[]{genericNackControlFrame}).intValue() == MediaTransport.getSendFrameSuccess()) {
                updateNackSenderStatistics();
            }
        }
    }

    /* access modifiers changed from: private */
    public void processFec(TFrame tframe) {
        if (getFecEnabled()) {
            if (this.__fecReceiver == null) {
                this.__fecReceiver = new FecReceiver(new IActionDelegate2<FecRawPacket, Boolean>() {
                    public String getId() {
                        return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.fecReceiverCallback";
                    }

                    public void invoke(FecRawPacket fecRawPacket, Boolean bool) {
                        RtpReceiver.this.fecReceiverCallback(fecRawPacket, bool.booleanValue());
                    }
                });
                this.__redPayloadType = getFormatParameters().getNegotiatedPayloadType(getFormatParameters().getRedFormat());
                this.__ulpFecPayloadType = getFormatParameters().getNegotiatedPayloadType(getFormatParameters().getUlpFecFormat());
            }
            if (tframe.getBuffer().getRtpHeader().getPayloadType() == this.__redPayloadType) {
                RtpPacketHeader rtpHeader = tframe.getBuffer().getRtpHeader();
                int calculateHeaderLength = rtpHeader.calculateHeaderLength();
                DataBuffer dataBuffer = tframe.getBuffer().getDataBuffer();
                DataBuffer allocate = DataBuffer.allocate(dataBuffer.getLength() + calculateHeaderLength);
                rtpHeader.writeTo(allocate, 0);
                allocate.write(dataBuffer, calculateHeaderLength);
                if (!this.__fecReceiver.addReceivedRedPacket(calculateHeaderLength, rtpHeader.getSequenceNumber(), allocate.getData(), allocate.getLength(), this.__ulpFecPayloadType)) {
                    __log.warn("Could not process RED packet.");
                    return;
                }
                if (this.__fecReceiver.getLastPacketReceivedSoloFec()) {
                    this.__soloFecCount++;
                }
                if (!this.__fecReceiver.processReceivedFec()) {
                    __log.warn("Could not process FEC packet.");
                }
            } else if (getLegacy()) {
                processLegacyJitter(tframe);
            } else {
                processFinal(tframe);
            }
        } else if (getLegacy()) {
            processLegacyJitter(tframe);
        } else {
            processFinal(tframe);
        }
    }

    /* access modifiers changed from: private */
    public void processFinal(TFrame tframe) {
        IAction1 receiveFrame = getReceiveFrame();
        if (receiveFrame != null) {
            receiveFrame.invoke(tframe);
        }
        if (Global.equals(getType(), StreamType.Video)) {
            long timestamp = tframe.getTimestamp();
            long j = this.__lastFinalTimestamp;
            if (timestamp != j) {
                if (j != -1) {
                    this.__frameDurationAverage.add(SoundUtility.calculateDurationFromTimestampDelta((int) (tframe.getTimestamp() - this.__lastFinalTimestamp), this.__currentClockRate));
                }
                this.__lastFinalTimestamp = tframe.getTimestamp();
            }
        }
    }

    private void processFrame(TFrame tframe, int i) {
        if (this.__referenceReceiveNtpTimestampTicks != -1) {
            tframe.setSystemTimestamp(this.__referenceReceiveNtpTimestampTicks + (((tframe.getTimestamp() - this.__referenceReceiveTimestamp) * ((long) Constants.getTicksPerSecond())) / ((long) this.__currentClockRate)));
        }
        RtpPacketHeader rtpHeader = tframe.getBuffer().getRtpHeader();
        updateRtpReceiverStatistics(rtpHeader, tframe.getBuffer().getSequenceNumber(), this.__currentClockRate, rtpHeader.calculateHeaderLength() + tframe.getBuffer().getDataBuffer().getLength(), tframe.getNetworkSystemTimestamp());
        if (getLegacy()) {
            processLegacyNack(tframe, i);
        } else {
            processJitter(tframe, i);
        }
    }

    private void processJitter(TFrame tframe, int i) {
        IFunction1<MediaControlFrame[], Integer> sendControlFrames;
        int i2;
        int average;
        if (this.__interFrameDelay == null) {
            this.__interFrameDelay = new RtpInterFrameDelay(this.__currentClockRate);
        }
        if (this.__jitterBuffer == null) {
            RtpJitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> rtpJitterBuffer = new RtpJitterBuffer<>(this.__currentClockRate, new IActionDelegate1<TFrame>() {
                public String getId() {
                    return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processFec";
                }

                public void invoke(TFrame tframe) {
                    RtpReceiver.this.processFec(tframe);
                }
            });
            rtpJitterBuffer.setNackEnabled(getNackEnabled());
            rtpJitterBuffer.setRtpStreamId(getRemoteRtpStreamId());
            rtpJitterBuffer.setSynchronizationSource(getRemoteSynchronizationSource());
            this.__jitterBuffer = rtpJitterBuffer;
            StreamType type = getType();
            if (type == StreamType.Audio) {
                this.__jitterBuffer.setAverageFrameDurationMillis(20);
            } else if (type == StreamType.Video) {
                if (!getNackEnabled()) {
                    this.__jitterBuffer.setAverageFrameDurationMillis(200);
                } else {
                    this.__jitterBuffer.setAverageFrameDurationMillis(400);
                }
                this.__jitterBuffer.setMaxExpiryMillis(800);
            }
        }
        StreamType type2 = getType();
        if (type2 == StreamType.Audio) {
            this.__jitterEstimator.updateEstimate((long) this.__interFrameDelay.calculateDelayMillis(tframe.getTimestamp(), tframe.getTransportSystemTimestamp()), tframe.getBuffer().getDataBuffer().getLength());
            this.__jitterBuffer.setJitterEstimateMillis(this.__jitterEstimator.getJitterEstimate());
        } else if (type2 == StreamType.Video) {
            if (tframe.getBuffer().getRtpHeader().getMarker()) {
                this.__jitterEstimator.updateEstimate((long) this.__interFrameDelay.calculateDelayMillis(tframe.getTimestamp(), tframe.getTransportSystemTimestamp()), 500);
                this.__jitterBuffer.setJitterEstimateMillis(this.__jitterEstimator.getJitterEstimate());
            }
            if (!getNackEnabled() && (average = this.__frameDurationAverage.getAverage()) > 0) {
                this.__jitterBuffer.setAverageFrameDurationMillis(average);
            }
        }
        this.__jitterBuffer.setRoundTripTimeMillis(i);
        long[] push = this.__jitterBuffer.push(tframe, tframe.getTransportSystemTimestamp());
        if (push != null && ArrayExtensions.getLength(push) > 0 && (sendControlFrames = getSendControlFrames()) != null) {
            GenericNack genericNack = null;
            long j = 0;
            ArrayList arrayList = new ArrayList();
            int length = push.length;
            int i3 = 0;
            while (i3 < length) {
                long j2 = push[i3];
                if (genericNack == null) {
                    genericNack = new GenericNack();
                    arrayList.add(genericNack);
                    genericNack.setPacketId((int) (j2 % 65536));
                    i2 = i3;
                } else {
                    i2 = i3;
                    long j3 = j2 - j;
                    if (j3 > 16) {
                        genericNack = new GenericNack();
                        arrayList.add(genericNack);
                        genericNack.setPacketId((int) (j2 % 65536));
                    } else {
                        genericNack.setLostPacketIdPlus((int) j3, true);
                        i3 = i2 + 1;
                    }
                }
                j = j2;
                i3 = i2 + 1;
            }
            GenericNackControlFrame genericNackControlFrame = new GenericNackControlFrame((GenericNack[]) arrayList.toArray(new GenericNack[0]));
            genericNackControlFrame.setMediaSourceSynchronizationSource(getRemoteSynchronizationSource());
            if (sendControlFrames.invoke(new MediaControlFrame[]{genericNackControlFrame}).intValue() == MediaTransport.getSendFrameSuccess()) {
                updateNackSenderStatistics();
            }
        }
    }

    private void processLegacyJitter(TFrame tframe) {
        if (!getJitterEnabled()) {
            processFinal(tframe);
            return;
        }
        if (getLegacyJitterBuffer() == null) {
            JitterBuffer jitterBuffer = new JitterBuffer(this.__currentClockRate, new IActionDelegate1<TFrame>() {
                public String getId() {
                    return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processFinal";
                }

                public void invoke(TFrame tframe) {
                    RtpReceiver.this.processFinal(tframe);
                }
            });
            jitterBuffer.setLength(getJitterConfig().getBufferLength());
            setLegacyJitterBuffer(jitterBuffer);
        }
        if (!getLegacyJitterBuffer().push(tframe)) {
            this.__packetsDiscarded.increment();
            if (__log.getIsDebugEnabled()) {
                __log.debug(this.__logScope, "Jitter buffer is discarding late packet for stream.");
            }
        }
    }

    private void processLegacyNack(TFrame tframe, int i) {
        if (getLegacyNackBuffer() != null) {
            getLegacyNackBuffer().setRetransmissionTimeout(i);
        }
        if (!getNackEnabled()) {
            processFec(tframe);
            return;
        }
        if (this.__nextNackBufferSequenceNumber == -1) {
            this.__nextNackBufferSequenceNumber = tframe.getBuffer().getSequenceNumber();
            this.__nextNackBufferRtpSequenceNumber = tframe.getBuffer().getRtpSequenceNumber();
        }
        if (tframe.getBuffer().getSequenceNumber() - this.__nextNackBufferSequenceNumber > ((long) getLegacyNackBuffer().getLength()) || getFlushBuffer()) {
            setFlushBuffer(false);
            long j = this.__nextNackBufferSequenceNumber;
            int i2 = this.__nextNackBufferRtpSequenceNumber;
            while (i2 < tframe.getBuffer().getRtpSequenceNumber()) {
                getLegacyNackBuffer().read(j, i2, -1, tframe.getTransportSystemTimestamp(), new IActionDelegate1<TFrame>() {
                    public String getId() {
                        return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.processFec";
                    }

                    public void invoke(TFrame tframe) {
                        RtpReceiver.this.processFec(tframe);
                    }
                }, (IAction1<GenericNack>) null);
                i2++;
                j++;
            }
            this.__nextNackBufferSequenceNumber = tframe.getBuffer().getSequenceNumber();
        }
        int write = getLegacyNackBuffer().write(tframe);
        if (write < 0) {
            if (write == -1) {
                this.__packetsDuplicated.increment();
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(this.__logScope, "NACK buffer is discarding duplicate packet for stream.");
                }
            } else {
                this.__packetsDiscarded.increment();
                if (__log.getIsDebugEnabled()) {
                    __log.debug(this.__logScope, "NACK buffer is discarding late packet for stream.");
                }
            }
        }
        do {
        } while (getLegacyNackBuffer().read(this.__nextNackBufferSequenceNumber, this.__nextNackBufferRtpSequenceNumber, tframe.getTimestamp(), tframe.getTransportSystemTimestamp(), new IActionDelegate1<TFrame>() {
            public String getId() {
                return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.legacyNackBufferReadFrameCallback";
            }

            public void invoke(TFrame tframe) {
                RtpReceiver.this.legacyNackBufferReadFrameCallback(tframe);
            }
        }, new IActionDelegate1<GenericNack>() {
            public String getId() {
                return "fm.liveswitch.RtpReceiver<TFrame,TBuffer,TBufferCollection,TFormat,TFormatCollection>.legacyNackBufferReadNackCallback";
            }

            public void invoke(GenericNack genericNack) {
                RtpReceiver.this.legacyNackBufferReadNackCallback(genericNack);
            }
        }));
    }

    public void processSRControlFrame(SRControlFrame sRControlFrame, long j) {
        RtpLastSenderReportInfo rtpLastSenderReportInfo = new RtpLastSenderReportInfo();
        rtpLastSenderReportInfo.setNtpTimestamp(sRControlFrame.getNtpTimestamp());
        rtpLastSenderReportInfo.setTransportSystemTimestamp(j);
        this.__lastSenderReportInfo = rtpLastSenderReportInfo;
        if (this.__referenceReceiveNtpTimestampTicks == -1) {
            this.__referenceReceiveTimestamp = sRControlFrame.getRtpTimestamp();
            this.__referenceReceiveNtpTimestampTicks = this.__networkTimeProtocol.ntpToTicks(sRControlFrame.getNtpTimestamp());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
        if (r13.getBuffer() == null) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean receiveRtp(fm.liveswitch.RtpInboundRtp r13, int r14) {
        /*
            r12 = this;
            long r0 = r12.getRemoteSynchronizationSource()
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0015
            fm.liveswitch.RtpPacketHeader r0 = r13.getHeader()
            long r0 = r0.getSynchronizationSource()
            r12.finishInitialization(r0)
        L_0x0015:
            r0 = 8
            fm.liveswitch.IFunction1 r2 = r12.getTestReceivedRtpBuffer()     // Catch:{ all -> 0x013e }
            r3 = 0
            if (r2 == 0) goto L_0x0048
            fm.liveswitch.DataBuffer r4 = r13.getBuffer()     // Catch:{ all -> 0x013e }
            java.lang.Object r2 = r2.invoke(r4)     // Catch:{ all -> 0x013e }
            fm.liveswitch.DataBuffer r2 = (fm.liveswitch.DataBuffer) r2     // Catch:{ all -> 0x013e }
            r13.setBuffer(r2)     // Catch:{ all -> 0x013e }
            fm.liveswitch.DataBuffer r2 = r13.getBuffer()     // Catch:{ all -> 0x013e }
            if (r2 != 0) goto L_0x0048
        L_0x0031:
            fm.liveswitch.BitrateMonitor r13 = r12.__bitrateMonitor
            long r4 = r12.getOctetsReceivedRtp()
            long r4 = r4 * r0
            r13.update(r4)
            fm.liveswitch.RtpLossController r13 = r12.__lossController
            fm.liveswitch.BitrateMonitor r14 = r12.__bitrateMonitor
            double r0 = r14.getBitrate()
            r13.setCurrentBitrate(r0)
            return r3
        L_0x0048:
            fm.liveswitch.SrtpContext r2 = r12.__context     // Catch:{ all -> 0x013e }
            if (r2 != 0) goto L_0x005c
            fm.liveswitch.ILog r13 = __log     // Catch:{ all -> 0x013e }
            java.lang.String r14 = r12.__logScope     // Catch:{ all -> 0x013e }
            java.lang.String r2 = "Discarding inbound frame. Missing decryption context."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x013e }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ all -> 0x013e }
            r13.warn((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x013e }
            goto L_0x0031
        L_0x005c:
            fm.liveswitch.diagnostics.DurationTimer r4 = r12.__decryptDurationTimer     // Catch:{ all -> 0x013e }
            fm.liveswitch.diagnostics.DurationSample r4 = r4.beginSample()     // Catch:{ all -> 0x013e }
            fm.liveswitch.DataBuffer r5 = r13.getBuffer()     // Catch:{ all -> 0x0137 }
            fm.liveswitch.RtpPacketHeader r6 = r13.getHeader()     // Catch:{ all -> 0x0137 }
            fm.liveswitch.RtpPacketPair r5 = r2.decryptRtp(r5, r6)     // Catch:{ all -> 0x0137 }
            fm.liveswitch.diagnostics.DurationTimer r6 = r12.__decryptDurationTimer     // Catch:{ all -> 0x013e }
            r6.endSample(r4)     // Catch:{ all -> 0x013e }
            if (r5 != 0) goto L_0x0085
            fm.liveswitch.ILog r13 = __log     // Catch:{ all -> 0x013e }
            java.lang.String r14 = r12.__logScope     // Catch:{ all -> 0x013e }
            java.lang.String r2 = "Discarding inbound frame. Decryption failed."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x013e }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ all -> 0x013e }
            r13.warn((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x013e }
            goto L_0x0031
        L_0x0085:
            fm.liveswitch.RtpPacketHeader r10 = r5.getHeader()     // Catch:{ all -> 0x013e }
            if (r10 != 0) goto L_0x009b
            fm.liveswitch.ILog r13 = __log     // Catch:{ all -> 0x013e }
            java.lang.String r14 = r12.__logScope     // Catch:{ all -> 0x013e }
            java.lang.String r2 = "Discarding inbound frame. Header is empty."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x013e }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ all -> 0x013e }
            r13.warn((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x013e }
            goto L_0x0031
        L_0x009b:
            fm.liveswitch.DataBuffer r11 = r5.getPayload()     // Catch:{ all -> 0x013e }
            if (r11 != 0) goto L_0x00b1
            fm.liveswitch.ILog r13 = __log     // Catch:{ all -> 0x013e }
            java.lang.String r14 = r12.__logScope     // Catch:{ all -> 0x013e }
            java.lang.String r2 = "Discarding inbound frame. Payload is empty."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x013e }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ all -> 0x013e }
            r13.warn((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x013e }
            goto L_0x011b
        L_0x00b1:
            fm.liveswitch.HexDump r4 = r12.getHexDump()     // Catch:{ all -> 0x0132 }
            if (r4 == 0) goto L_0x00c1
            r5 = 0
            long r6 = fm.liveswitch.ManagedStopwatch.getTimestamp()     // Catch:{ all -> 0x0132 }
            r8 = r10
            r9 = r11
            r4.writeRtp(r5, r6, r8, r9)     // Catch:{ all -> 0x0132 }
        L_0x00c1:
            fm.liveswitch.RtpIFormatParameters r4 = r12.getFormatParameters()     // Catch:{ all -> 0x0132 }
            int r5 = r10.getPayloadType()     // Catch:{ all -> 0x0132 }
            fm.liveswitch.MediaFormat r4 = r4.getNegotiatedFormat(r5)     // Catch:{ all -> 0x0132 }
            if (r4 != 0) goto L_0x00ed
            fm.liveswitch.ILog r13 = __log     // Catch:{ all -> 0x0132 }
            java.lang.String r14 = r12.__logScope     // Catch:{ all -> 0x0132 }
            java.lang.String r2 = "Discarding inbound frame. Unrecognized payload type {0}."
            int r4 = r10.getPayloadType()     // Catch:{ all -> 0x0132 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0132 }
            java.lang.String r4 = fm.liveswitch.IntegerExtensions.toString(r4)     // Catch:{ all -> 0x0132 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0132 }
            r13.warn((java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0132 }
        L_0x00e8:
            r11.free()     // Catch:{ all -> 0x013e }
            goto L_0x0031
        L_0x00ed:
            fm.liveswitch.MediaFrame r2 = r12.generateFrame(r10, r11, r4, r2)     // Catch:{ all -> 0x0132 }
            if (r2 != 0) goto L_0x00f4
            goto L_0x00e8
        L_0x00f4:
            boolean r3 = r4.getIsInjected()     // Catch:{ all -> 0x0132 }
            if (r3 != 0) goto L_0x0106
            int r3 = r10.getPayloadType()     // Catch:{ all -> 0x0132 }
            r12.__currentPayloadType = r3     // Catch:{ all -> 0x0132 }
            int r3 = r4.getClockRate()     // Catch:{ all -> 0x0132 }
            r12.__currentClockRate = r3     // Catch:{ all -> 0x0132 }
        L_0x0106:
            long r3 = r13.getNetworkSystemTimestamp()     // Catch:{ all -> 0x0132 }
            r2.setNetworkSystemTimestamp(r3)     // Catch:{ all -> 0x0132 }
            long r3 = r13.getTransportSystemTimestamp()     // Catch:{ all -> 0x0132 }
            r2.setTransportSystemTimestamp(r3)     // Catch:{ all -> 0x0132 }
            r12.processFrame(r2, r14)     // Catch:{ all -> 0x0132 }
            r3 = 1
            r11.free()     // Catch:{ all -> 0x013e }
        L_0x011b:
            fm.liveswitch.BitrateMonitor r13 = r12.__bitrateMonitor
            long r4 = r12.getOctetsReceivedRtp()
            long r4 = r4 * r0
            r13.update(r4)
            fm.liveswitch.RtpLossController r13 = r12.__lossController
            fm.liveswitch.BitrateMonitor r14 = r12.__bitrateMonitor
            double r0 = r14.getBitrate()
            r13.setCurrentBitrate(r0)
            return r3
        L_0x0132:
            r13 = move-exception
            r11.free()     // Catch:{ all -> 0x013e }
            throw r13     // Catch:{ all -> 0x013e }
        L_0x0137:
            r13 = move-exception
            fm.liveswitch.diagnostics.DurationTimer r14 = r12.__decryptDurationTimer     // Catch:{ all -> 0x013e }
            r14.endSample(r4)     // Catch:{ all -> 0x013e }
            throw r13     // Catch:{ all -> 0x013e }
        L_0x013e:
            r13 = move-exception
            fm.liveswitch.BitrateMonitor r14 = r12.__bitrateMonitor
            long r2 = r12.getOctetsReceivedRtp()
            long r2 = r2 * r0
            r14.update(r2)
            fm.liveswitch.RtpLossController r14 = r12.__lossController
            fm.liveswitch.BitrateMonitor r0 = r12.__bitrateMonitor
            double r0 = r0.getBitrate()
            r14.setCurrentBitrate(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpReceiver.receiveRtp(fm.liveswitch.RtpInboundRtp, int):boolean");
    }

    public void resetBandwidthEstimate(double d) {
        this.__lossController.hardReset(d);
    }

    public void resetCurrentBitrate() {
        this.__bitrateMonitor.reset();
        this.__lossController.setCurrentBitrate(0.0d);
    }

    public RtpReceiver(StreamType streamType, long j, String str, String str2, int i, NackConfig nackConfig, RedFecConfig redFecConfig, JitterConfig jitterConfig, boolean z, RtpIParameters rtpIParameters, RtpIFormatParameters<TFormat> rtpIFormatParameters, RtpIExtensionParameters rtpIExtensionParameters, SrtpProtectionParameters srtpProtectionParameters, SrtpProtectionParameters srtpProtectionParameters2, HexDump hexDump, IFunction1<Integer, TFrame[]> iFunction1) {
        long j2 = j;
        setType(streamType);
        setNackConfig(nackConfig);
        setFecConfig(redFecConfig);
        setJitterConfig(jitterConfig);
        setLegacy(z);
        setRemoteSynchronizationSource(-1);
        String str3 = str;
        setRemoteRtpStreamId(str);
        setRemoteRepairedRtpStreamId(str2);
        setParameters(rtpIParameters);
        setFormatParameters(rtpIFormatParameters);
        setExtensionParameters(rtpIExtensionParameters);
        setLocalProtectionParameters(srtpProtectionParameters);
        setRemoteProtectionParameters(srtpProtectionParameters2);
        setHexDump(hexDump);
        this.__bitrateMonitor = new BitrateMonitor(new SystemClock());
        this.__lossController = new RtpLossController(new SystemClock(), (double) i);
        setId(Utility.generateId());
        if (getLegacy() && getNackEnabled()) {
            setLegacyNackBuffer(new NackBuffer(getType().toString(), getNackConfig().getReceiveBufferLength(), iFunction1));
        }
        this.__context = new SrtpContext(srtpProtectionParameters.getProtectionProfileString(), srtpProtectionParameters.getKey(), srtpProtectionParameters.getSalt(), srtpProtectionParameters2.getKey(), srtpProtectionParameters2.getSalt());
        if (j2 != -1) {
            finishInitialization(j);
        }
    }

    private void setExtensionParameters(RtpIExtensionParameters rtpIExtensionParameters) {
        this._extensionParameters = rtpIExtensionParameters;
    }

    private void setFecConfig(RedFecConfig redFecConfig) {
        this._fecConfig = redFecConfig;
    }

    /* access modifiers changed from: package-private */
    public void setFlushBuffer(boolean z) {
        this._flushBuffer = z;
    }

    private void setFormatParameters(RtpIFormatParameters<TFormat> rtpIFormatParameters) {
        this._formatParameters = rtpIFormatParameters;
    }

    private void setHexDump(HexDump hexDump) {
        this._hexDump = hexDump;
    }

    private void setId(String str) {
        this._id = str;
    }

    private void setJitterConfig(JitterConfig jitterConfig) {
        this._jitterConfig = jitterConfig;
    }

    private void setLegacy(boolean z) {
        this._legacy = z;
    }

    private void setLegacyJitterBuffer(JitterBuffer<TFrame, TBuffer, TBufferCollection, TFormat> jitterBuffer) {
        this._legacyJitterBuffer = jitterBuffer;
    }

    private void setLegacyNackBuffer(NackBuffer<TFrame, TBuffer, TBufferCollection, TFormat> nackBuffer) {
        this._legacyNackBuffer = nackBuffer;
    }

    private void setLocalProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._localProtectionParameters = srtpProtectionParameters;
    }

    private void setNackConfig(NackConfig nackConfig) {
        this._nackConfig = nackConfig;
    }

    private void setParameters(RtpIParameters rtpIParameters) {
        this._parameters = rtpIParameters;
    }

    public void setReceiveFrame(IAction1<TFrame> iAction1) {
        this._receiveFrame = iAction1;
    }

    private void setRemoteProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._remoteProtectionParameters = srtpProtectionParameters;
    }

    private void setRemoteRepairedRtpStreamId(String str) {
        this._remoteRepairedRtpStreamId = str;
    }

    private void setRemoteRtpStreamId(String str) {
        this._remoteRtpStreamId = str;
    }

    private void setRemoteSynchronizationSource(long j) {
        this._remoteSynchronizationSource = j;
    }

    public void setSendControlFrames(IFunction1<MediaControlFrame[], Integer> iFunction1) {
        this._sendControlFrames = iFunction1;
    }

    public void setTestReceivedRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        this._testReceivedRtpBuffer = iFunction1;
    }

    private void setType(StreamType streamType) {
        this._type = streamType;
    }

    private void updateNackSenderStatistics() {
        this.__nacksSent.increment();
        this.__nacksSentSinceLastReport++;
        long timestamp = ManagedStopwatch.getTimestamp();
        if (this.__lastNackRateReportSystemTimestamp == -1) {
            this.__lastNackRateReportSystemTimestamp = timestamp;
        }
        long j = timestamp - this.__lastNackRateReportSystemTimestamp;
        if (j > ((long) this.__nackRateReportDelayTicks)) {
            long ticksPerMillisecond = j / ((long) Constants.getTicksPerMillisecond());
            long ticksPerSecond = ((long) (this.__nacksSentSinceLastReport * Constants.getTicksPerSecond())) / j;
            if (ticksPerSecond > ((long) this.__nackRateCritical)) {
                __log.warn(this.__logScope, StringExtensions.format("Critical network condition detected! Sent {0} generic NACKs per second in the last {1}ms, exceeding critical threshold of {2}.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond)), IntegerExtensions.toString(Integer.valueOf(this.__nackRateCritical))));
            } else if (ticksPerSecond > ((long) this.__nackRateWarning)) {
                __log.warn(this.__logScope, StringExtensions.format("Poor network condition detected! Sent {0} generic NACKs per second in the last {1}ms, exceeding warning threshold of {2}.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond)), IntegerExtensions.toString(Integer.valueOf(this.__nackRateWarning))));
            } else {
                __log.debug(this.__logScope, StringExtensions.format("Sent {0} generic NACKs per second in the last {1}ms.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond))));
            }
            this.__nacksSentSinceLastReport = 0;
            this.__lastNackRateReportSystemTimestamp = timestamp;
        }
    }

    public void updateRtcpReceiverStatistics(int i) {
        this.__octetsReceivedRtcp.add((long) i);
        this.__packetsReceivedRtcp.increment();
    }

    private void updateRtpReceiverStatistics(RtpPacketHeader rtpPacketHeader, long j, int i, int i2, long j2) {
        synchronized (this.__receiveCountsLock) {
            if (this.__minReceivedSystemTimestamp == -1) {
                this.__minReceivedRtpTimestamp = rtpPacketHeader.getTimestamp();
                this.__minReceivedSystemTimestamp = j2;
                this.__minReceivedPacketIndex = j;
                this.__maxReceivedPacketIndex = j;
            }
            this.__minReceivedRtpTimestamp = MathAssistant.min(rtpPacketHeader.getTimestamp(), this.__minReceivedRtpTimestamp);
            this.__minReceivedSystemTimestamp = MathAssistant.min(j2, this.__minReceivedSystemTimestamp);
            this.__minReceivedPacketIndex = MathAssistant.min(j, this.__minReceivedPacketIndex);
            this.__maxReceivedPacketIndex = MathAssistant.max(j, this.__maxReceivedPacketIndex);
            this.__octetsReceivedRtp.add((long) i2);
            this.__packetsReceivedRtp.increment();
            long ticksPerSecond = ((((j2 - this.__minReceivedSystemTimestamp) * ((long) i)) / ((long) Constants.getTicksPerSecond())) + this.__minReceivedRtpTimestamp) - rtpPacketHeader.getTimestamp();
            long j3 = ticksPerSecond - this.__lastReceiveTransit;
            this.__lastReceiveTransit = ticksPerSecond;
            if (j3 < 0) {
                j3 = -j3;
            }
            double d = this.__receiveJitter;
            this.__receiveJitter = d + ((((double) j3) - d) / 16.0d);
        }
    }
}
