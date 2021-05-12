package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFormatCollection;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.diagnostics.DurationSample;
import fm.liveswitch.diagnostics.DurationTimer;
import fm.liveswitch.diagnostics.Timers;
import java.util.ArrayList;

class RtpSender<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>, TFormatCollection extends MediaFormatCollection<TFormat, TFormatCollection>> {
    private static ILog __log = Log.getLogger(RtpSender.class);
    private BitrateMonitor __bitrateMonitor;
    private SrtpContext __context = null;
    private int __currentMediaSequenceNumber = 0;
    private int __currentPayloadType = -1;
    private int __currentRtpSequenceNumber = (LockedRandomizer.next(64512) + 512);
    private DurationTimer __encryptDurationTimer;
    private Object __encryptRtcpLock = new Object();
    private Object __encryptRtpLock = new Object();
    private boolean __fecActivated;
    private FecProtectionParameters __fecDeltaParameters;
    private FecProtectionParameters __fecKeyFrameParameters;
    private FecProducer __fecProducer;
    private AtomicLong __firsReceived = new AtomicLong();
    private int __lastCcmReceiveSequenceNumber = -1;
    private long __lastNackRateReportSystemTimestamp = -1;
    private long __lastPliReceiveSystemTimestamp = -1;
    private long __lastRoundTripTimeReportSystemTimestamp = -1;
    private String __logScope;
    private RtpLossController __lossController;
    private AtomicLong __lrrsReceived = new AtomicLong();
    private int __nackRateCritical = 150;
    private int __nackRateReportDelayTicks = (Constants.getTicksPerSecond() * 10);
    private int __nackRateWarning = 10;
    private AtomicLong __nacksReceived = new AtomicLong();
    private int __nacksReceivedSinceLastReport = -1;
    private NetworkTimeProtocol __networkTimeProtocol = new NetworkTimeProtocol(0);
    private AtomicLong __octetsSentRtcp = new AtomicLong();
    private AtomicLong __octetsSentRtp = new AtomicLong();
    private int __outboundPacketsLostRtp = 0;
    private AtomicLong __packetsSentRtcp = new AtomicLong();
    private AtomicLong __packetsSentRtp = new AtomicLong();
    private AtomicLong __plisReceived = new AtomicLong();
    private DurationTimer __reEncryptDurationTimer;
    private int __redPayloadType;
    private int __referenceSendClockRate = -1;
    private long __referenceSendSystemTimestamp = -1;
    private long __referenceSendTimestamp = -1;
    private int __reportBlocksProcessed = 0;
    private int __roundTripTimeCritical = 750;
    private RtpRoundTripTimeFilter __roundTripTimeFilter;
    private int __roundTripTimeReportDelayTicks = (Constants.getTicksPerSecond() * 10);
    private int __roundTripTimeWarning = 300;
    private int __rtpTimestampOffset = LockedRandomizer.next(65535, Integer.MAX_VALUE);
    private long __sendBaseTimestamp = -1;
    private RtpSendBuffer<TFrame, TBuffer, TBufferCollection, TFormat> __sendBuffer;
    private volatile boolean __sentPacketsSinceLastSRControlFrame;
    private volatile boolean __systemTimestampWarned;
    private int __ulpFecPayloadType;
    private RtpIExtensionParameters _extensionParameters;
    private RedFecConfig _fecConfig;
    private RtpIFormatParameters<TFormat> _formatParameters;
    private HexDump _hexDump;
    private String _id;
    private boolean _includeRepairedRtpStreamIdSdes;
    private boolean _includeRtpStreamIdSdes;
    private SrtpProtectionParameters _localProtectionParameters;
    private String _localRepairedRtpStreamId;
    private String _localRtpStreamId;
    private long _localSynchronizationSource;
    private NackConfig _nackConfig;
    private RtpIParameters _parameters;
    private SrtpProtectionParameters _remoteProtectionParameters;
    private Transport _rtcpTransport;
    private Transport _rtpTransport;
    private IFunction1<DataBuffer, DataBuffer> _testSendingRtpBuffer;
    private StreamType _type;

    private boolean canReceiveCcm(int i) {
        int i2 = this.__lastCcmReceiveSequenceNumber;
        if ((i2 != -1 ? CcmUtility.getSequenceNumberDelta(i, i2) : 1) <= 0) {
            return false;
        }
        this.__lastCcmReceiveSequenceNumber = i;
        return true;
    }

    public boolean canReceiveFir(int i) {
        this.__firsReceived.increment();
        return canReceiveCcm(i);
    }

    public boolean canReceiveLrr(int i) {
        this.__lrrsReceived.increment();
        return canReceiveCcm(i);
    }

    public boolean canReceivePli(int i, long j) {
        this.__plisReceived.increment();
        long j2 = this.__lastPliReceiveSystemTimestamp;
        if (j2 != -1 && j - j2 < ((long) i)) {
            return false;
        }
        this.__lastPliReceiveSystemTimestamp = j;
        return true;
    }

    public void destroy() {
        SrtpContext srtpContext = this.__context;
        if (srtpContext != null) {
            srtpContext.destroy();
            this.__context = null;
        }
        DurationTimer durationTimer = this.__encryptDurationTimer;
        if (durationTimer != null) {
            durationTimer.destroy();
            this.__encryptDurationTimer = null;
        }
        DurationTimer durationTimer2 = this.__reEncryptDurationTimer;
        if (durationTimer2 != null) {
            durationTimer2.destroy();
            this.__reEncryptDurationTimer = null;
        }
    }

    private int doSendFrame(TFrame tframe) {
        if (this.__context == null) {
            __log.error(this.__logScope, "Cannot send frame. No context set. Did you SetParameters and Start?");
            return MediaTransport.getSendFrameErrorNoEncryptionContext();
        }
        if (getIncludeRtpStreamIdSdes() && tframe.getRtpStreamId() == null) {
            setIncludeRtpStreamIdSdes(false);
        }
        if (getIncludeRepairedRtpStreamIdSdes() && tframe.getRepairedRtpStreamId() == null) {
            setIncludeRepairedRtpStreamIdSdes(false);
        }
        if (getNackEnabled()) {
            this.__sendBuffer.write(tframe);
        }
        MediaBuffer buffer = tframe.getBuffer(true, true);
        if (buffer == null) {
            MediaBuffer buffer2 = tframe.getBuffer(true);
            if (buffer2 == null) {
                __log.error(this.__logScope, "Frame has no packetized buffers to send.");
                return MediaTransport.getSendFrameErrorNoPacketizedBuffers();
            }
            buffer = encryptBuffer(buffer2);
            if (buffer == null) {
                return MediaTransport.getSendFrameErrorEncryptionFailed();
            }
            tframe.addBuffer(buffer);
        }
        for (DataBuffer dataBuffer : buffer.getDataBuffers()) {
            sendEncryptedRtpBuffer(dataBuffer);
            dataBuffer.free();
        }
        return MediaTransport.getSendFrameSuccess();
    }

    private int doSendFrameWithFec(TFrame tframe) {
        if (this.__fecProducer == null) {
            this.__fecProducer = new FecProducer(new FecContext());
            FecProtectionParameters keyFrameParameters = getFecConfig().getKeyFrameParameters();
            this.__fecKeyFrameParameters = keyFrameParameters;
            if (keyFrameParameters == null) {
                FecProtectionParameters fecProtectionParameters = new FecProtectionParameters();
                fecProtectionParameters.setMaskType(getFecConfig().getBursty() ? FecMaskType.Bursty : FecMaskType.Random);
                this.__fecKeyFrameParameters = fecProtectionParameters;
            }
            FecProtectionParameters deltaParameters = getFecConfig().getDeltaParameters();
            this.__fecDeltaParameters = deltaParameters;
            if (deltaParameters == null) {
                FecProtectionParameters fecProtectionParameters2 = new FecProtectionParameters();
                fecProtectionParameters2.setMaskType(getFecConfig().getBursty() ? FecMaskType.Bursty : FecMaskType.Random);
                this.__fecDeltaParameters = fecProtectionParameters2;
            }
            this.__redPayloadType = getFormatParameters().getNegotiatedPayloadType(getFormatParameters().getRedFormat());
            this.__ulpFecPayloadType = getFormatParameters().getNegotiatedPayloadType(getFormatParameters().getUlpFecFormat());
        }
        if (Global.equals(getType(), StreamType.Video)) {
            VideoFrame videoFrame = (VideoFrame) Global.tryCast(tframe, VideoFrame.class);
            if (videoFrame == null) {
                this.__fecProducer.setFecParameters(this.__fecDeltaParameters, 0);
            } else if (((VideoBuffer) videoFrame.getLastBuffer()).getIsKeyFrame()) {
                this.__fecProducer.setFecParameters(this.__fecKeyFrameParameters, 0);
            } else {
                this.__fecProducer.setFecParameters(this.__fecDeltaParameters, 0);
            }
        } else {
            this.__fecProducer.setFecParameters(this.__fecDeltaParameters, 0);
        }
        MediaBuffer lastBuffer = tframe.getLastBuffer();
        Holder holder = new Holder(null);
        byte[][] rtpPackets = toRtpPackets(lastBuffer, holder);
        int[] iArr = (int[]) holder.getValue();
        if (rtpPackets == null) {
            return MediaTransport.getSendFrameErrorFecFailure();
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) rtpPackets); i++) {
            byte[] bArr = rtpPackets[i];
            int i2 = iArr[i];
            int length = ArrayExtensions.getLength(bArr) - i2;
            FecRedPacket buildRedPacket = this.__fecProducer.buildRedPacket(bArr, length, i2, this.__redPayloadType);
            DataBuffer wrap = DataBuffer.wrap(buildRedPacket.getData(), 0, buildRedPacket.getLength());
            RtpPacketHeader readFrom = RtpPacketHeader.readFrom(wrap);
            MediaBuffer clone = lastBuffer.clone();
            clone.setRtpHeader(readFrom);
            clone.setDataBuffer(wrap.subset(readFrom.calculateHeaderLength()));
            clone.setFormat(getFormatParameters().getRedFormat());
            MediaFrame clone2 = tframe.clone();
            clone2.removeBuffers();
            clone2.addBuffer(clone);
            doSendFrame(clone2);
            if (!this.__fecProducer.addRtpPacketAndGenerateFec(bArr, length, i2)) {
                return MediaTransport.getSendFrameErrorFecFailure();
            }
            while (this.__fecProducer.getFecAvailable()) {
                LongHolder longHolder = new LongHolder(0);
                int nextRtpSequenceNumber = getNextRtpSequenceNumber(longHolder);
                longHolder.getValue();
                FecRedPacket fecPacket = this.__fecProducer.getFecPacket(this.__redPayloadType, this.__ulpFecPayloadType, nextRtpSequenceNumber, i2);
                DataBuffer wrap2 = DataBuffer.wrap(fecPacket.getData(), 0, fecPacket.getLength());
                RtpPacketHeader readFrom2 = RtpPacketHeader.readFrom(wrap2);
                MediaBuffer clone3 = lastBuffer.clone();
                clone3.setRtpHeader(readFrom2);
                clone3.setDataBuffer(wrap2.subset(readFrom2.calculateHeaderLength()));
                clone3.setFormat(getFormatParameters().getRedFormat());
                MediaFrame clone4 = tframe.clone();
                clone4.removeBuffers();
                clone4.addBuffer(clone3);
                doSendFrame(clone4);
            }
        }
        return MediaTransport.getSendFrameSuccess();
    }

    private TBuffer encryptBuffer(TBuffer tbuffer) {
        DataBuffer encryptRtp;
        SrtpContext srtpContext = this.__context;
        if (srtpContext == null) {
            __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Missing encryption context.", new Object[0]));
            return null;
        }
        DataBuffer[] dataBuffers = tbuffer.getDataBuffers();
        RtpPacketHeader[] rtpHeaders = tbuffer.getRtpHeaders();
        DataBuffer[] dataBufferArr = new DataBuffer[ArrayExtensions.getLength((Object[]) dataBuffers)];
        int i = 0;
        while (i < ArrayExtensions.getLength((Object[]) dataBuffers)) {
            if (rtpHeaders[i] == null) {
                __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Buffer is missing an RTP header.", new Object[0]));
                return null;
            }
            HexDump hexDump = getHexDump();
            if (hexDump != null) {
                hexDump.writeRtp(true, ManagedStopwatch.getTimestamp(), rtpHeaders[i], dataBuffers[i]);
            }
            try {
                synchronized (this.__encryptRtpLock) {
                    DurationSample beginSample = this.__encryptDurationTimer.beginSample();
                    try {
                        encryptRtp = srtpContext.encryptRtp(rtpHeaders[i], dataBuffers[i]);
                    } finally {
                        this.__encryptDurationTimer.endSample(beginSample);
                    }
                }
                if (encryptRtp == null) {
                    __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Encryption failed.", new Object[0]));
                    return null;
                }
                dataBufferArr[i] = encryptRtp;
                i++;
            } catch (Exception e) {
                __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Encryption failed.", new Object[0]), e);
                return null;
            }
        }
        TBuffer clone = tbuffer.clone();
        clone.getFormat().setIsEncrypted(true);
        clone.setDataBuffers(dataBufferArr);
        return clone;
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
        codecStats.setCodecType(CodecType.Encode);
        if (Global.equals(getType(), StreamType.Audio) && (audioFormat = (AudioFormat) Global.tryCast(negotiatedFormat, AudioFormat.class)) != null) {
            codecStats.setChannelCount(audioFormat.getChannelCount());
        }
        return codecStats;
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

    public long getFirsReceived() {
        return this.__firsReceived.getValue();
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

    public boolean getIncludeRepairedRtpStreamIdSdes() {
        return this._includeRepairedRtpStreamIdSdes;
    }

    public boolean getIncludeRtpStreamIdSdes() {
        return this._includeRtpStreamIdSdes;
    }

    public double getInitialLossBasedBandwidthEstimate() {
        return this.__lossController.getInitialBandwidthEstimate();
    }

    public SrtpProtectionParameters getLocalProtectionParameters() {
        return this._localProtectionParameters;
    }

    public String getLocalRepairedRtpStreamId() {
        return this._localRepairedRtpStreamId;
    }

    public String getLocalRtpStreamId() {
        return this._localRtpStreamId;
    }

    public long getLocalSynchronizationSource() {
        return this._localSynchronizationSource;
    }

    public double getLossBasedBandwidthEstimate() {
        return this.__lossController.getBandwidthEstimate();
    }

    public long getLrrsReceived() {
        return this.__lrrsReceived.getValue();
    }

    public NackConfig getNackConfig() {
        return this._nackConfig;
    }

    public boolean getNackEnabled() {
        NackConfig nackConfig = getNackConfig();
        return nackConfig != null && !nackConfig.getDisableBuffering();
    }

    public long getNacksReceived() {
        return this.__nacksReceived.getValue();
    }

    private int getNextRtpSequenceNumber(LongHolder longHolder) {
        return incrementRtpSequenceNumber(longHolder);
    }

    public long getOctetsSentRtcp() {
        return this.__octetsSentRtcp.getValue();
    }

    public long getOctetsSentRtp() {
        return this.__octetsSentRtp.getValue();
    }

    public long getPacketsSentRtcp() {
        return this.__packetsSentRtcp.getValue();
    }

    public long getPacketsSentRtp() {
        return this.__packetsSentRtp.getValue();
    }

    public RtpIParameters getParameters() {
        return this._parameters;
    }

    public long getPlisReceived() {
        return this.__plisReceived.getValue();
    }

    public SrtpProtectionParameters getRemoteProtectionParameters() {
        return this._remoteProtectionParameters;
    }

    public int getRoundTripTime() {
        return this.__roundTripTimeFilter.getRoundTripTime();
    }

    public Transport getRtcpTransport() {
        return this._rtcpTransport;
    }

    private long getRtpTimestamp(long j) {
        if (this.__sendBaseTimestamp == -1) {
            this.__sendBaseTimestamp = j;
        }
        return ((j - this.__sendBaseTimestamp) + ((long) this.__rtpTimestampOffset)) % 4294967296L;
    }

    public Transport getRtpTransport() {
        return this._rtpTransport;
    }

    static SdesChunk getSdesChunk(long j, String str, String str2, String str3, String str4) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SdesItem(SdesItemType.getCanonicalName(), str));
        if (str2 != null) {
            arrayList.add(new SdesItem(SdesItemType.getMid(), str2));
        }
        if (str3 != null) {
            arrayList.add(new SdesItem(SdesItemType.getRtpStreamId(), str3));
        }
        if (str4 != null) {
            arrayList.add(new SdesItem(SdesItemType.getRepairedRtpStreamId(), str4));
        }
        return new SdesChunk(j, (SdesItem[]) arrayList.toArray(new SdesItem[0]));
    }

    public SdesChunk getSdesChunk(String str) {
        String str2 = null;
        String localRtpStreamId = getIncludeRtpStreamIdSdes() ? getLocalRtpStreamId() : null;
        if (getIncludeRepairedRtpStreamIdSdes()) {
            str2 = getLocalRepairedRtpStreamId();
        }
        return getSdesChunk(getLocalSynchronizationSource(), getParameters().getCanonicalName(), str, localRtpStreamId, str2);
    }

    public SRControlFrame getSRControlFrame(ReportBlock[] reportBlockArr, long j) {
        if (this.__referenceSendSystemTimestamp == -1 || !this.__sentPacketsSinceLastSRControlFrame) {
            return null;
        }
        this.__sentPacketsSinceLastSRControlFrame = false;
        SRControlFrame sRControlFrame = new SRControlFrame(reportBlockArr);
        sRControlFrame.setSynchronizationSource(getLocalSynchronizationSource());
        if (sRControlFrame.getRtpTimestamp() == 0) {
            sRControlFrame.setRtpTimestamp(getRtpTimestamp(this.__referenceSendTimestamp + (((j - this.__referenceSendSystemTimestamp) * ((long) this.__referenceSendClockRate)) / ((long) Constants.getTicksPerSecond()))));
        }
        if (sRControlFrame.getNtpTimestamp() == 0) {
            sRControlFrame.setNtpTimestamp(this.__networkTimeProtocol.ticksToNtp(j));
        }
        if (sRControlFrame.getPacketCount() == 0) {
            sRControlFrame.setPacketCount(this.__packetsSentRtp.getValue());
        }
        if (sRControlFrame.getOctetCount() == 0) {
            sRControlFrame.setOctetCount(this.__octetsSentRtp.getValue());
        }
        return sRControlFrame;
    }

    public MediaSenderStats getStats() {
        MediaSenderStats mediaSenderStats = new MediaSenderStats();
        mediaSenderStats.setId(getId());
        mediaSenderStats.setTimestamp(DateExtensions.getUtcNow());
        mediaSenderStats.setSynchronizationSource(getLocalSynchronizationSource());
        mediaSenderStats.setRtpStreamId(getLocalRtpStreamId());
        mediaSenderStats.setRepairedRtpStreamId(getLocalRepairedRtpStreamId());
        mediaSenderStats.setCodec(getCodecStats());
        mediaSenderStats.setNackCount(this.__nacksReceived.getValue());
        mediaSenderStats.setPliCount(this.__plisReceived.getValue());
        mediaSenderStats.setFirCount(this.__firsReceived.getValue());
        mediaSenderStats.setLrrCount(this.__lrrsReceived.getValue());
        mediaSenderStats.setBytesSent(this.__octetsSentRtp.getValue());
        mediaSenderStats.setPacketsSent(this.__packetsSentRtp.getValue());
        mediaSenderStats.setRoundTripTime(getRoundTripTime());
        return mediaSenderStats;
    }

    public IFunction1<DataBuffer, DataBuffer> getTestSendingRtpBuffer() {
        return this._testSendingRtpBuffer;
    }

    public StreamType getType() {
        return this._type;
    }

    private int incrementRtpSequenceNumber(LongHolder longHolder) {
        int i = this.__currentMediaSequenceNumber;
        this.__currentMediaSequenceNumber = i + 1;
        longHolder.setValue((long) i);
        int i2 = this.__currentRtpSequenceNumber;
        int i3 = i2 + 1;
        this.__currentRtpSequenceNumber = i3;
        if (i3 == 65536) {
            this.__currentRtpSequenceNumber = 0;
        }
        return i2;
    }

    private int prepareBufferForSend(TBuffer tbuffer, TFrame tframe) {
        if (tbuffer.getSequenceNumbers() == null) {
            tbuffer.setSequenceNumbers(new long[ArrayExtensions.getLength((Object[]) tbuffer.getDataBuffers())]);
            for (int i = 0; i < ArrayExtensions.getLength(tbuffer.getSequenceNumbers()); i++) {
                tbuffer.getSequenceNumbers()[i] = -1;
            }
        }
        if (!tbuffer.getIsPacketized()) {
            tbuffer.setRtpHeaders(new RtpPacketHeader[ArrayExtensions.getLength((Object[]) tbuffer.getDataBuffers())]);
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) tbuffer.getDataBuffers()); i2++) {
                RtpPacket wrap = RtpPacket.wrap(tbuffer.getDataBuffers()[i2]);
                tbuffer.getRtpHeaders()[i2] = RtpPacketHeader.readFrom(wrap.getHeader());
                tbuffer.getDataBuffers()[i2] = wrap.getPayload();
            }
        }
        if (ArrayExtensions.getLength((Object[]) tbuffer.getDataBuffers()) != ArrayExtensions.getLength((Object[]) tbuffer.getRtpHeaders())) {
            __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Buffer is missing RTP headers.", new Object[0]));
            return MediaTransport.getSendFrameErrorMissingRtpHeaders();
        }
        for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) tbuffer.getDataBuffers()); i3++) {
            if (tbuffer.getRtpHeaders()[i3] == null) {
                tbuffer.getRtpHeaders()[i3] = new RtpPacketHeader();
            }
            RtpPacketHeader rtpPacketHeader = tbuffer.getRtpHeaders()[i3];
            RtpHeaderExtensionForm rtpHeaderExtensionForm = RtpHeaderExtensionForm.OneByte;
            ArrayList arrayList = new ArrayList();
            if ((Global.equals(getExtensionParameters().getSdesMidDirection(), StreamDirection.SendOnly) || Global.equals(getExtensionParameters().getSdesMidDirection(), StreamDirection.SendReceive)) && tframe.getMid() != null) {
                RtpHeaderSdesMid rtpHeaderSdesMid = new RtpHeaderSdesMid(rtpHeaderExtensionForm, tframe.getMid());
                rtpHeaderSdesMid.setId(getExtensionParameters().getRtpHeaderExtensionRegistry().registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType.SdesMid));
                arrayList.add(rtpHeaderSdesMid);
            }
            if (tframe.getRtpStreamId() != null && (Global.equals(getExtensionParameters().getSdesRtpStreamIdDirection(), StreamDirection.SendOnly) || Global.equals(getExtensionParameters().getSdesRtpStreamIdDirection(), StreamDirection.SendReceive))) {
                RtpHeaderSdesRtpStreamId rtpHeaderSdesRtpStreamId = new RtpHeaderSdesRtpStreamId(rtpHeaderExtensionForm, tframe.getRtpStreamId());
                rtpHeaderSdesRtpStreamId.setId(getExtensionParameters().getRtpHeaderExtensionRegistry().registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType.SdesRtpStreamId));
                arrayList.add(rtpHeaderSdesRtpStreamId);
            }
            if (tframe.getRepairedRtpStreamId() != null && (Global.equals(getExtensionParameters().getSdesRepairedRtpStreamIdDirection(), StreamDirection.SendOnly) || Global.equals(getExtensionParameters().getSdesRepairedRtpStreamIdDirection(), StreamDirection.SendReceive))) {
                RtpHeaderSdesRepairedRtpStreamId rtpHeaderSdesRepairedRtpStreamId = new RtpHeaderSdesRepairedRtpStreamId(rtpHeaderExtensionForm, tframe.getRepairedRtpStreamId());
                rtpHeaderSdesRepairedRtpStreamId.setId(getExtensionParameters().getRtpHeaderExtensionRegistry().registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType.SdesRepairedRtpStreamId));
                arrayList.add(rtpHeaderSdesRepairedRtpStreamId);
            }
            if (Global.equals(getExtensionParameters().getAbsoluteSenderTimeDirection(), StreamDirection.SendOnly) || Global.equals(getExtensionParameters().getAbsoluteSenderTimeDirection(), StreamDirection.SendReceive)) {
                RtpHeaderAbsSendTime rtpHeaderAbsSendTime = new RtpHeaderAbsSendTime(rtpHeaderExtensionForm, this.__networkTimeProtocol.ticksToNtp(ManagedStopwatch.getTimestamp()));
                rtpHeaderAbsSendTime.setId(getExtensionParameters().getRtpHeaderExtensionRegistry().registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType.AbsSendTime));
                arrayList.add(rtpHeaderAbsSendTime);
            }
            if (ArrayListExtensions.getCount(arrayList) > 0) {
                rtpPacketHeader.setHeaderExtension(new RtpHeaderExtension(rtpHeaderExtensionForm, (RtpHeaderExtensionElement[]) arrayList.toArray(new RtpHeaderExtensionElement[0])));
            } else {
                rtpPacketHeader.setHeaderExtension((IRtpHeaderExtension) null);
            }
            if (tbuffer.getSequenceNumbers()[i3] == -1) {
                LongHolder longHolder = new LongHolder(0);
                int nextRtpSequenceNumber = getNextRtpSequenceNumber(longHolder);
                long value = longHolder.getValue();
                rtpPacketHeader.setSequenceNumber(nextRtpSequenceNumber);
                tbuffer.getSequenceNumbers()[i3] = value;
            } else if (rtpPacketHeader.getSequenceNumber() < 0) {
                StringBuilder sb = new StringBuilder();
                StringBuilderExtensions.append(sb, StringExtensions.format("Invalid sequence number ({0}).", (Object) LongExtensions.toString(Long.valueOf(tframe.getSequenceNumber()))));
                for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) tframe.getBuffers()); i4++) {
                    if (i4 == 0) {
                        StringBuilderExtensions.append(sb, tframe.getBuffers()[i4].getFormat().getName());
                    } else {
                        StringBuilderExtensions.append(sb, StringExtensions.format(" -> {0}", (Object) tframe.getBuffers()[i4].getFormat().getName()));
                    }
                }
                __log.error(this.__logScope, sb.toString());
                rtpPacketHeader.setSequenceNumber((int) (tbuffer.getSequenceNumbers()[i3] % 65536));
            }
            if (tframe.getRtpTimestamp() == -1) {
                if (tframe.getTimestamp() == -1) {
                    __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Timestamp not set.", new Object[0]));
                    return MediaTransport.getSendFrameErrorNoTimestamp();
                }
                tframe.setRtpTimestamp(getRtpTimestamp(tframe.getTimestamp()));
            }
            rtpPacketHeader.setTimestamp(tframe.getRtpTimestamp());
            tframe.setSynchronizationSource(getLocalSynchronizationSource());
            rtpPacketHeader.setSynchronizationSource(getLocalSynchronizationSource());
            rtpPacketHeader.setContributingSources(tframe.getContributingSources());
            int negotiatedPayloadType = getFormatParameters().getNegotiatedPayloadType(tbuffer.getFormat());
            if (negotiatedPayloadType == -1) {
                __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame. Buffer is using a non-negotiated format.", new Object[0]));
                return MediaTransport.getSendFrameErrorUnknownPayloadType();
            }
            rtpPacketHeader.setPayloadType(negotiatedPayloadType);
            if (!tbuffer.getFormat().getIsInjected()) {
                this.__currentPayloadType = negotiatedPayloadType;
            }
        }
        if (!tbuffer.getFormat().getIsInjected()) {
            updateSynchronization(tframe, tbuffer.getFormat());
        }
        return MediaTransport.getSendFrameSuccess();
    }

    public void processGenericNackControlFrame(GenericNackControlFrame genericNackControlFrame) {
        for (GenericNack genericNack : genericNackControlFrame.getGenericNacks()) {
            if (resendRtpPacket(genericNack.getPacketId())) {
                genericNack.setLostPacketIdPlusHandled(0, true);
            }
            int packetId = genericNack.getPacketId();
            for (int i = 1; i <= genericNack.getLostPacketIdPlusLength(); i++) {
                if (genericNack.getLostPacketIdPlus(i) && resendRtpPacket((packetId + i) % 65536)) {
                    genericNack.setLostPacketIdPlusHandled(i, true);
                }
            }
        }
        updateNackReceiverStatistics();
    }

    public void processReportBlock(ReportBlock reportBlock, long j) {
        this.__reportBlocksProcessed++;
        if (reportBlock.getLastSenderReportTimestamp() > 0) {
            long compactNtpToTicks = this.__networkTimeProtocol.compactNtpToTicks(reportBlock.getLastSenderReportTimestamp());
            long delaySinceLastSenderReport = (reportBlock.getDelaySinceLastSenderReport() * ((long) Constants.getTicksPerSecond())) / 65536;
            NetworkTimeProtocol networkTimeProtocol = this.__networkTimeProtocol;
            long compactNtpToTicks2 = networkTimeProtocol.compactNtpToTicks(networkTimeProtocol.ticksToCompactNtp(j));
            if (compactNtpToTicks2 < compactNtpToTicks) {
                compactNtpToTicks2 += (long) (Constants.getTicksPerSecond() * 65536);
            }
            this.__roundTripTimeFilter.update((int) MathAssistant.max(0, ((compactNtpToTicks2 - compactNtpToTicks) - delaySinceLastSenderReport) / ((long) Constants.getTicksPerMillisecond())));
            long timestamp = ManagedStopwatch.getTimestamp();
            if (this.__lastRoundTripTimeReportSystemTimestamp == -1) {
                this.__lastRoundTripTimeReportSystemTimestamp = timestamp;
            }
            if (timestamp - this.__lastRoundTripTimeReportSystemTimestamp > ((long) this.__roundTripTimeReportDelayTicks)) {
                if (this.__roundTripTimeFilter.getRoundTripTime() > this.__roundTripTimeCritical) {
                    __log.warn(this.__logScope, StringExtensions.format("Critical network conditions detected! Round trip time is estimated to be {0}ms, exceeding critical threshold of {1}ms.", IntegerExtensions.toString(Integer.valueOf(this.__roundTripTimeFilter.getRoundTripTime())), IntegerExtensions.toString(Integer.valueOf(this.__roundTripTimeCritical))));
                } else if (this.__roundTripTimeFilter.getRoundTripTime() > this.__roundTripTimeWarning) {
                    __log.warn(this.__logScope, StringExtensions.format("Poor network conditions detected! Round trip time is estimated to be {0}ms, exceeding warning threshold {1}ms.", IntegerExtensions.toString(Integer.valueOf(this.__roundTripTimeFilter.getRoundTripTime())), IntegerExtensions.toString(Integer.valueOf(this.__roundTripTimeWarning))));
                } else {
                    __log.debug(this.__logScope, StringExtensions.format("Round trip time is estimated to be {0}ms.", (Object) IntegerExtensions.toString(Integer.valueOf(this.__roundTripTimeFilter.getRoundTripTime()))));
                }
                this.__lastRoundTripTimeReportSystemTimestamp = timestamp;
            }
        }
        if (getFecEnabled() && this.__reportBlocksProcessed > getFecConfig().getMinimumReportsBeforeFec()) {
            double percentLost = reportBlock.getPercentLost() * 100.0d;
            if (this.__fecActivated) {
                if (percentLost < ((double) getFecConfig().getActivationThreshold())) {
                    this.__fecActivated = false;
                    if (__log.getIsDebugEnabled()) {
                        __log.debug(this.__logScope, StringExtensions.format("FEC deactivated. {0}% loss is < activation threshold of {1}%.", Utility.formatDoubleAsPercent(reportBlock.getPercentLost(), 0), IntegerExtensions.toString(Integer.valueOf(getFecConfig().getActivationThreshold()))));
                    }
                }
            } else if (percentLost >= ((double) getFecConfig().getActivationThreshold())) {
                this.__fecActivated = true;
                if (__log.getIsDebugEnabled()) {
                    __log.debug(this.__logScope, StringExtensions.format("FEC activated. {0}% loss is >= activation threshold of {1}%.", Utility.formatDoubleAsPercent(reportBlock.getPercentLost(), 0), IntegerExtensions.toString(Integer.valueOf(getFecConfig().getActivationThreshold()))));
                }
            }
        }
        this.__outboundPacketsLostRtp = reportBlock.getCumulativeNumberOfPacketsLost();
        this.__lossController.update(reportBlock.getPercentLost());
    }

    private boolean resendRtpPacket(int i) {
        RtpPacketPair read;
        DataBuffer encryptRtp;
        if (getNackEnabled() && (read = this.__sendBuffer.read(i)) != null) {
            SrtpContext srtpContext = this.__context;
            if (srtpContext == null) {
                __log.warn(this.__logScope, StringExtensions.format("Discarding outbound frame retransmission. Missing encryption context.", new Object[0]));
                return false;
            }
            HexDump hexDump = getHexDump();
            if (hexDump != null) {
                hexDump.writeRtp(true, ManagedStopwatch.getTimestamp(), read.getHeader(), read.getPayload());
            }
            try {
                synchronized (this.__encryptRtpLock) {
                    DurationSample beginSample = this.__reEncryptDurationTimer.beginSample();
                    try {
                        encryptRtp = srtpContext.encryptRtp(read.getHeader(), read.getPayload());
                    } finally {
                        this.__reEncryptDurationTimer.endSample(beginSample);
                    }
                }
                if (encryptRtp == null) {
                    __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame retransmission. Encryption failed.", new Object[0]));
                    return false;
                }
                try {
                    return sendEncryptedRtpBuffer(encryptRtp);
                } finally {
                    encryptRtp.free();
                }
            } catch (Exception e) {
                __log.error(this.__logScope, StringExtensions.format("Discarding outbound frame retransmission. Encryption failed.", new Object[0]), e);
            }
        }
        return false;
    }

    public void resetBandwidthEstimate(double d) {
        this.__lossController.hardReset(d);
    }

    public void resetCurrentBitrate() {
        this.__bitrateMonitor.reset();
        this.__lossController.setCurrentBitrate(0.0d);
    }

    public RtpSender(StreamType streamType, long j, String str, String str2, int i, NackConfig nackConfig, RedFecConfig redFecConfig, RtpIParameters rtpIParameters, RtpIFormatParameters<TFormat> rtpIFormatParameters, RtpIExtensionParameters rtpIExtensionParameters, SrtpProtectionParameters srtpProtectionParameters, SrtpProtectionParameters srtpProtectionParameters2, HexDump hexDump, Transport transport, Transport transport2) {
        long j2 = j;
        String str3 = str;
        String str4 = str2;
        boolean z = false;
        setRtpTransport(transport);
        setRtcpTransport(transport2);
        setType(streamType);
        setLocalSynchronizationSource(j);
        setLocalRtpStreamId(str3);
        setLocalRepairedRtpStreamId(str4);
        setNackConfig(nackConfig);
        setFecConfig(redFecConfig);
        setParameters(rtpIParameters);
        setFormatParameters(rtpIFormatParameters);
        setExtensionParameters(rtpIExtensionParameters);
        setLocalProtectionParameters(srtpProtectionParameters);
        setRemoteProtectionParameters(srtpProtectionParameters2);
        setHexDump(hexDump);
        this.__bitrateMonitor = new BitrateMonitor(new SystemClock());
        this.__lossController = new RtpLossController(new SystemClock(), (double) i);
        setId(Utility.generateId());
        setIncludeRtpStreamIdSdes(str3 != null);
        setIncludeRepairedRtpStreamIdSdes(str4 != null ? true : z);
        if (getNackEnabled()) {
            this.__sendBuffer = new RtpSendBuffer<>(getType().toString(), getNackConfig().getSendBufferLength());
        }
        this.__logScope = StringExtensions.format("{0}-{1}", StringExtensions.toLower(getType().toString()), LongExtensions.toString(Long.valueOf(j)));
        this.__roundTripTimeFilter = new RtpRoundTripTimeFilter(this.__logScope);
        SrtpContext srtpContext = new SrtpContext(srtpProtectionParameters.getProtectionProfileString(), srtpProtectionParameters.getKey(), srtpProtectionParameters.getSalt(), srtpProtectionParameters2.getKey(), srtpProtectionParameters2.getSalt());
        srtpContext.setLocalSynchronizationSource(j);
        this.__context = srtpContext;
        this.__encryptDurationTimer = Timers.getDurationTimer(StringExtensions.format("RTP Encrypt ({0} {1})", getType().toString(), LongExtensions.toString(Long.valueOf(j))));
        this.__reEncryptDurationTimer = Timers.getDurationTimer(StringExtensions.format("RTP Re-Encrypt ({0} {1})", getType().toString(), LongExtensions.toString(Long.valueOf(j))));
    }

    public int sendControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        DataBuffer encryptRtcp;
        RtcpPacket[] rtcpPacketArr = new RtcpPacket[ArrayExtensions.getLength((Object[]) mediaControlFrameArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaControlFrameArr); i++) {
            rtcpPacketArr[i] = new RtcpPacket(mediaControlFrameArr[i].getDataBuffer());
        }
        SrtpContext srtpContext = this.__context;
        if (srtpContext == null) {
            __log.error(this.__logScope, StringExtensions.format("Discarding outbound control frame(s). Missing encryption context.", new Object[0]));
            return MediaTransport.getSendFrameErrorNoEncryptionContext();
        }
        HexDump hexDump = getHexDump();
        if (hexDump != null) {
            hexDump.writeRtcp(true, ManagedStopwatch.getTimestamp(), rtcpPacketArr);
        }
        try {
            synchronized (this.__encryptRtcpLock) {
                encryptRtcp = srtpContext.encryptRtcp(rtcpPacketArr);
            }
            if (encryptRtcp == null) {
                __log.error(this.__logScope, StringExtensions.format("Discarding outbound control frame(s). Encryption failed.", new Object[0]));
                return MediaTransport.getSendFrameErrorEncryptionFailed();
            }
            updateRtcpSenderStatistics(encryptRtcp.getLength());
            getRtcpTransport().send(encryptRtcp);
            return MediaTransport.getSendFrameSuccess();
        } catch (Exception e) {
            __log.error(this.__logScope, StringExtensions.format("Discarding outbound control frame(s). Encryption failed.", new Object[0]), e);
            return MediaTransport.getSendFrameErrorEncryptionFailed();
        }
    }

    private boolean sendEncryptedRtpBuffer(DataBuffer dataBuffer) {
        IFunction1<DataBuffer, DataBuffer> testSendingRtpBuffer = getTestSendingRtpBuffer();
        if (testSendingRtpBuffer != null && (dataBuffer = testSendingRtpBuffer.invoke(dataBuffer)) == null) {
            return false;
        }
        updateRtpSenderStatistics(dataBuffer.getLength());
        this.__sentPacketsSinceLastSRControlFrame = true;
        getRtpTransport().send(dataBuffer);
        return true;
    }

    public int sendFrame(TFrame tframe, TBuffer tbuffer) {
        try {
            int prepareBufferForSend = prepareBufferForSend(tbuffer, tframe);
            if (prepareBufferForSend != MediaTransport.getSendFrameSuccess()) {
                return prepareBufferForSend;
            }
            if (this.__fecActivated) {
                int doSendFrameWithFec = doSendFrameWithFec(tframe);
                this.__bitrateMonitor.update(getOctetsSentRtp() * 8);
                this.__lossController.setCurrentBitrate(this.__bitrateMonitor.getBitrate());
                return doSendFrameWithFec;
            }
            int doSendFrame = doSendFrame(tframe);
            this.__bitrateMonitor.update(getOctetsSentRtp() * 8);
            this.__lossController.setCurrentBitrate(this.__bitrateMonitor.getBitrate());
            return doSendFrame;
        } finally {
            this.__bitrateMonitor.update(getOctetsSentRtp() * 8);
            this.__lossController.setCurrentBitrate(this.__bitrateMonitor.getBitrate());
        }
    }

    private void setExtensionParameters(RtpIExtensionParameters rtpIExtensionParameters) {
        this._extensionParameters = rtpIExtensionParameters;
    }

    private void setFecConfig(RedFecConfig redFecConfig) {
        this._fecConfig = redFecConfig;
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

    public void setIncludeRepairedRtpStreamIdSdes(boolean z) {
        this._includeRepairedRtpStreamIdSdes = z;
    }

    public void setIncludeRtpStreamIdSdes(boolean z) {
        this._includeRtpStreamIdSdes = z;
    }

    private void setLocalProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._localProtectionParameters = srtpProtectionParameters;
    }

    private void setLocalRepairedRtpStreamId(String str) {
        this._localRepairedRtpStreamId = str;
    }

    private void setLocalRtpStreamId(String str) {
        this._localRtpStreamId = str;
    }

    private void setLocalSynchronizationSource(long j) {
        this._localSynchronizationSource = j;
    }

    private void setNackConfig(NackConfig nackConfig) {
        this._nackConfig = nackConfig;
    }

    private void setParameters(RtpIParameters rtpIParameters) {
        this._parameters = rtpIParameters;
    }

    private void setRemoteProtectionParameters(SrtpProtectionParameters srtpProtectionParameters) {
        this._remoteProtectionParameters = srtpProtectionParameters;
    }

    /* access modifiers changed from: package-private */
    public void setRtcpTransport(Transport transport) {
        this._rtcpTransport = transport;
    }

    /* access modifiers changed from: package-private */
    public void setRtpTransport(Transport transport) {
        this._rtpTransport = transport;
    }

    public void setTestSendingRtpBuffer(IFunction1<DataBuffer, DataBuffer> iFunction1) {
        this._testSendingRtpBuffer = iFunction1;
    }

    private void setType(StreamType streamType) {
        this._type = streamType;
    }

    private byte[][] toRtpPackets(TBuffer tbuffer, Holder<int[]> holder) {
        if (!tbuffer.getIsPacketized()) {
            holder.setValue(null);
            return null;
        }
        RtpPacketHeader[] rtpHeaders = tbuffer.getRtpHeaders();
        DataBuffer[] dataBuffers = tbuffer.getDataBuffers();
        if (ArrayExtensions.getLength((Object[]) rtpHeaders) != ArrayExtensions.getLength((Object[]) dataBuffers)) {
            holder.setValue(null);
            return null;
        }
        byte[][] bArr = new byte[ArrayExtensions.getLength((Object[]) rtpHeaders)][];
        holder.setValue(new int[ArrayExtensions.getLength((Object[]) rtpHeaders)]);
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) bArr); i++) {
            RtpPacketHeader rtpPacketHeader = rtpHeaders[i];
            DataBuffer dataBuffer = dataBuffers[i];
            int calculateHeaderLength = rtpPacketHeader.calculateHeaderLength();
            DataBuffer allocate = DataBuffer.allocate(dataBuffer.getLength() + calculateHeaderLength);
            rtpPacketHeader.writeTo(allocate, 0);
            allocate.write(dataBuffer, calculateHeaderLength);
            bArr[i] = allocate.getData();
            holder.getValue()[i] = calculateHeaderLength;
        }
        return bArr;
    }

    private void updateNackReceiverStatistics() {
        this.__nacksReceived.increment();
        this.__nacksReceivedSinceLastReport++;
        long timestamp = ManagedStopwatch.getTimestamp();
        if (this.__lastNackRateReportSystemTimestamp == -1) {
            this.__lastNackRateReportSystemTimestamp = timestamp;
        }
        long j = timestamp - this.__lastNackRateReportSystemTimestamp;
        if (j > ((long) this.__nackRateReportDelayTicks)) {
            long ticksPerMillisecond = j / ((long) Constants.getTicksPerMillisecond());
            long ticksPerSecond = ((long) (this.__nacksReceivedSinceLastReport * Constants.getTicksPerSecond())) / j;
            if (ticksPerSecond > ((long) this.__nackRateCritical)) {
                __log.warn(this.__logScope, StringExtensions.format("Critical network condition detected! Received {0} generic NACKs per second in the last {1}ms, exceeding critical threshold of {2}.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond)), IntegerExtensions.toString(Integer.valueOf(this.__nackRateCritical))));
            } else if (ticksPerSecond > ((long) this.__nackRateWarning)) {
                __log.warn(this.__logScope, StringExtensions.format("Poor network condition detected! Received {0} generic NACKs per second in the last {1}ms, exceeding warning threshold of {2}.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond)), IntegerExtensions.toString(Integer.valueOf(this.__nackRateWarning))));
            } else {
                __log.debug(this.__logScope, StringExtensions.format("Received {0} generic NACKs per second in the last {1}ms.", LongExtensions.toString(Long.valueOf(ticksPerSecond)), LongExtensions.toString(Long.valueOf(ticksPerMillisecond))));
            }
            this.__nacksReceivedSinceLastReport = 0;
            this.__lastNackRateReportSystemTimestamp = timestamp;
        }
    }

    private void updateRtcpSenderStatistics(int i) {
        this.__packetsSentRtcp.increment();
        this.__octetsSentRtcp.add((long) i);
    }

    private void updateRtpSenderStatistics(int i) {
        this.__packetsSentRtp.increment();
        this.__octetsSentRtp.add((long) i);
    }

    private void updateSynchronization(TFrame tframe, TFormat tformat) {
        if (this.__referenceSendTimestamp != -1) {
            return;
        }
        if (tframe.getSystemTimestamp() != -1) {
            this.__referenceSendClockRate = tformat.getClockRate();
            this.__referenceSendTimestamp = tframe.getTimestamp();
            this.__referenceSendSystemTimestamp = tframe.getSystemTimestamp();
        } else if (!this.__systemTimestampWarned) {
            this.__systemTimestampWarned = true;
            __log.warn(this.__logScope, "Source is sending frames with no system timestamp. Remote peer may not be able to properly synchronize streams.");
        }
    }
}
