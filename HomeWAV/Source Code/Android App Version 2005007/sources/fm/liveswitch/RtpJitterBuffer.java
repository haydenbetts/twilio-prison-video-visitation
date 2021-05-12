package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.vp8.Fragment;
import fm.liveswitch.vp8.Utility;
import java.util.ArrayList;
import java.util.Iterator;

class RtpJitterBuffer<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> {
    private static ILog __log = Log.getLogger(RtpJitterBuffer.class);
    private long __averageFrameDurationRtp = -1;
    private int __clockRate;
    private long __duplicateCount = 0;
    private long __foundCount = 0;
    private ArrayList<TFrame> __heldFrames = new ArrayList<>();
    private boolean __isAudio = false;
    private RtpPacketHeader __lastPoppedHeader = null;
    private DataBuffer __lastPoppedPayload = null;
    private long __lastPoppedSequenceNumber = -1;
    private long __lastPoppedTimestamp = -1;
    private long __lateCount = 0;
    private long __lostCount = 0;
    private ArrayList<RtpMissingFrame> __missingFrames = new ArrayList<>();
    private IAction1<TFrame> __pop;
    private long __remoteTimestampJump = -1;
    private long __remoteTimestampJumpMillis = ((long) (Constants.getMillisecondsPerSecond() * 5));
    private boolean __streamTypeInitialized = false;
    private long __timestampOffset = 0;
    private long _averageFrameDurationTicks;
    private long _jitterEstimateTicks;
    private long _maxExpiryTicks;
    private long _minExpiryTicks;
    private boolean _nackEnabled;
    private long _roundTripTimeTicks;
    private String _rtpStreamId;
    private long _synchronizationSource;

    public void destroy() {
        DataBuffer dataBuffer = this.__lastPoppedPayload;
        if (dataBuffer != null) {
            dataBuffer.free();
            this.__lastPoppedPayload = null;
        }
    }

    public int getAverageFrameDurationMillis() {
        return (int) (getAverageFrameDurationTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getAverageFrameDurationTicks() {
        return this._averageFrameDurationTicks;
    }

    public long getDuplicateCount() {
        return this.__duplicateCount;
    }

    public int getExpiryMillis() {
        return (int) (getExpiryTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getExpiryTicks() {
        long j;
        long j2;
        if (getNackEnabled()) {
            j2 = getJitterEstimateTicks() + getAverageFrameDurationTicks();
            j = getRoundTripTimeTicks();
        } else {
            j2 = getJitterEstimateTicks();
            j = getAverageFrameDurationTicks();
        }
        long j3 = j2 + j;
        long minExpiryTicks = getMinExpiryTicks();
        if (minExpiryTicks != -1) {
            j3 = MathAssistant.max(j3, minExpiryTicks);
        }
        long maxExpiryTicks = getMaxExpiryTicks();
        return maxExpiryTicks != -1 ? MathAssistant.min(j3, maxExpiryTicks) : j3;
    }

    public long getFoundCount() {
        return this.__foundCount;
    }

    private TFrame getHead() {
        if (ArrayListExtensions.getCount(this.__heldFrames) == 0) {
            return null;
        }
        return (MediaFrame) ArrayListExtensions.getItem(this.__heldFrames).get(0);
    }

    public int getHeldCount() {
        return ArrayListExtensions.getCount(this.__heldFrames);
    }

    public long[] getHeldSequenceNumbers() {
        ArrayList arrayList = new ArrayList();
        Iterator<TFrame> it = this.__heldFrames.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((MediaFrame) it.next()).getSequenceNumber()));
        }
        return Utility.toLongArray(arrayList);
    }

    public boolean getHolding() {
        return ArrayListExtensions.getCount(this.__heldFrames) != 0;
    }

    public int getJitterEstimateMillis() {
        return (int) (getJitterEstimateTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getJitterEstimateTicks() {
        return this._jitterEstimateTicks;
    }

    public long getLateCount() {
        return this.__lateCount;
    }

    private String getLogScope() {
        String rtpStreamId = getRtpStreamId();
        long synchronizationSource = getSynchronizationSource();
        if (rtpStreamId == null && synchronizationSource == -1) {
            return StringExtensions.empty;
        }
        if (rtpStreamId == null) {
            return StringExtensions.format("SSRC:{0}", (Object) LongExtensions.toString(Long.valueOf(synchronizationSource)));
        }
        if (synchronizationSource == -1) {
            return StringExtensions.format("RID:{0}", (Object) rtpStreamId);
        }
        return StringExtensions.format("RID:{0}|SSRC:{1}", rtpStreamId, LongExtensions.toString(Long.valueOf(synchronizationSource)));
    }

    public long getLostCount() {
        return this.__lostCount;
    }

    public int getMaxExpiryMillis() {
        return (int) (getMaxExpiryTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getMaxExpiryTicks() {
        return this._maxExpiryTicks;
    }

    public int getMinExpiryMillis() {
        return (int) (getMinExpiryTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getMinExpiryTicks() {
        return this._minExpiryTicks;
    }

    public int getMissingCount() {
        return ArrayListExtensions.getCount(this.__missingFrames);
    }

    public RtpMissingFrame[] getMissingFrames() {
        return (RtpMissingFrame[]) this.__missingFrames.toArray(new RtpMissingFrame[0]);
    }

    public long[] getMissingSequenceNumbers() {
        ArrayList arrayList = new ArrayList();
        Iterator<RtpMissingFrame> it = this.__missingFrames.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().getSequenceNumber()));
        }
        return Utility.toLongArray(arrayList);
    }

    public boolean getNackEnabled() {
        return this._nackEnabled;
    }

    public int getRoundTripTimeMillis() {
        return (int) (getRoundTripTimeTicks() / ((long) Constants.getTicksPerMillisecond()));
    }

    public long getRoundTripTimeTicks() {
        return this._roundTripTimeTicks;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    private TFrame getTail() {
        if (ArrayListExtensions.getCount(this.__heldFrames) == 0) {
            return null;
        }
        return (MediaFrame) ArrayListExtensions.getItem(this.__heldFrames).get(ArrayListExtensions.getCount(this.__heldFrames) - 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x001a, code lost:
        r5 = fm.liveswitch.dtmf.Packet.readFrom(r5.getBuffer().getDataBuffer());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getTimestamp(TFrame r5) {
        /*
            r4 = this;
            long r0 = r5.getTimestamp()
            fm.liveswitch.MediaBuffer r2 = r5.getBuffer()
            fm.liveswitch.MediaFormat r2 = r2.getFormat()
            java.lang.String r2 = r2.getName()
            java.lang.String r3 = fm.liveswitch.AudioFormat.getDtmfName()
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)
            if (r2 == 0) goto L_0x002e
            fm.liveswitch.MediaBuffer r5 = r5.getBuffer()
            fm.liveswitch.DataBuffer r5 = r5.getDataBuffer()
            fm.liveswitch.dtmf.Packet r5 = fm.liveswitch.dtmf.Packet.readFrom(r5)
            if (r5 == 0) goto L_0x002e
            int r5 = r5.getDuration()
            long r2 = (long) r5
            long r0 = r0 + r2
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpJitterBuffer.getTimestamp(fm.liveswitch.MediaFrame):long");
    }

    private void missing(long j, long j2) {
        while (j < j2) {
            this.__missingFrames.add(new RtpMissingFrame(j));
            j++;
        }
    }

    private void pop(TFrame tframe) {
        this.__lastPoppedSequenceNumber = tframe.getSequenceNumber();
        this.__lastPoppedTimestamp = getTimestamp(tframe);
        this.__lastPoppedHeader = tframe.getBuffer().getRtpHeader();
        DataBuffer keep = tframe.getBuffer().getDataBuffer().keep();
        DataBuffer dataBuffer = this.__lastPoppedPayload;
        if (dataBuffer != null) {
            dataBuffer.free();
        }
        this.__lastPoppedPayload = keep;
        this.__pop.invoke(tframe);
        if (!this.__streamTypeInitialized) {
            AudioFrame audioFrame = (AudioFrame) Global.tryCast(tframe, AudioFrame.class);
            if (audioFrame != null) {
                this.__isAudio = true;
                if (audioFrame.getDuration() > 0) {
                    setAverageFrameDurationMillis(audioFrame.getDuration());
                }
            }
            this.__streamTypeInitialized = true;
        }
    }

    public long[] push(TFrame tframe, long j) {
        long j2;
        TFrame tframe2 = tframe;
        if (this.__lastPoppedSequenceNumber == -1) {
            pop(tframe);
            return null;
        }
        long sequenceNumber = tframe.getSequenceNumber() - this.__lastPoppedSequenceNumber;
        if (sequenceNumber <= 0) {
            this.__lateCount++;
        } else {
            if (this.__isAudio) {
                if (!tframe.getBuffer().getFormat().getIsInjected()) {
                    if (this.__averageFrameDurationRtp == -1) {
                        this.__averageFrameDurationRtp = ((long) (getAverageFrameDurationMillis() * this.__clockRate)) / ((long) Constants.getMillisecondsPerSecond());
                    }
                    long timestamp = getTimestamp(tframe);
                    long j3 = (this.__lastPoppedTimestamp + (this.__averageFrameDurationRtp * sequenceNumber)) - this.__timestampOffset;
                    if (tframe.getBuffer().getRtpHeader().getMarker()) {
                        __log.debug(getLogScope(), "Timestamp reset detected on the remote audio stream. Marker bit set.");
                        this.__timestampOffset += j3 - timestamp;
                    } else {
                        if (this.__remoteTimestampJump == -1) {
                            this.__remoteTimestampJump = (this.__remoteTimestampJumpMillis * ((long) this.__clockRate)) / ((long) Constants.getMillisecondsPerSecond());
                        }
                        long j4 = timestamp - j3;
                        if (MathAssistant.abs(j4) >= this.__remoteTimestampJump) {
                            __log.debug(getLogScope(), StringExtensions.format("Timestamp reset detected on the remote audio stream. Marker bit was not set, but the timestamp was off by {0}ms.", (Object) LongExtensions.toString(Long.valueOf((j4 * ((long) Constants.getMillisecondsPerSecond())) / ((long) this.__clockRate)))));
                            this.__timestampOffset += j3 - timestamp;
                        }
                    }
                }
                if (this.__timestampOffset != 0) {
                    tframe2.setTimestamp(tframe.getTimestamp() + this.__timestampOffset);
                    tframe2.setRtpTimestamp(tframe.getTimestamp() % 4294967296L);
                    tframe.getBuffer().getRtpHeader().setTimestamp(tframe.getRtpTimestamp());
                }
                j2 = 1;
            } else {
                j2 = 1;
            }
            if (sequenceNumber == j2) {
                tryFound(tframe.getSequenceNumber());
                pop(tframe);
                do {
                } while (tryPopHead());
            } else if (tryHold(tframe)) {
                tframe.getBuffer().keep();
            }
        }
        tryPopGap();
        return tryGetNackSequenceNumbers(j);
    }

    public RtpJitterBuffer(int i, IAction1<TFrame> iAction1) {
        this.__clockRate = i;
        this.__pop = iAction1;
        setMinExpiryTicks(-1);
        setMaxExpiryTicks(-1);
    }

    public void setAverageFrameDurationMillis(int i) {
        setAverageFrameDurationTicks((long) (i * Constants.getTicksPerMillisecond()));
    }

    public void setAverageFrameDurationTicks(long j) {
        this._averageFrameDurationTicks = j;
    }

    public void setJitterEstimateMillis(int i) {
        setJitterEstimateTicks((long) (i * Constants.getTicksPerMillisecond()));
    }

    public void setJitterEstimateTicks(long j) {
        this._jitterEstimateTicks = j;
    }

    public void setMaxExpiryMillis(int i) {
        setMaxExpiryTicks((long) (i * Constants.getTicksPerMillisecond()));
    }

    public void setMaxExpiryTicks(long j) {
        this._maxExpiryTicks = j;
    }

    public void setMinExpiryMillis(int i) {
        setMinExpiryTicks((long) (i * Constants.getTicksPerMillisecond()));
    }

    public void setMinExpiryTicks(long j) {
        this._minExpiryTicks = j;
    }

    public void setNackEnabled(boolean z) {
        this._nackEnabled = z;
    }

    public void setRoundTripTimeMillis(int i) {
        setRoundTripTimeTicks((long) (i * Constants.getTicksPerMillisecond()));
    }

    public void setRoundTripTimeTicks(long j) {
        this._roundTripTimeTicks = j;
    }

    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    private boolean shouldPopGap(TFrame tframe, TFrame tframe2) {
        int sequenceNumber;
        long timestamp = getTimestamp(tframe2);
        if (((timestamp - this.__lastPoppedTimestamp) * ((long) Constants.getTicksPerSecond())) / ((long) this.__clockRate) > getExpiryTicks()) {
            return true;
        }
        long timestamp2 = getTimestamp(tframe);
        if (timestamp2 != timestamp && (sequenceNumber = (int) (tframe.getSequenceNumber() - this.__lastPoppedSequenceNumber)) >= 2 && Global.equals(tframe.getBuffer().getFormat().getName(), VideoFormat.getVp8Name())) {
            Fragment fragment = new Fragment(tframe.getBuffer().getRtpHeader(), tframe.getBuffer().getDataBuffer());
            Fragment fragment2 = new Fragment(this.__lastPoppedHeader, this.__lastPoppedPayload);
            if (Utility.isGapAllowed(fragment, fragment2)) {
                if (__log.getIsDebugEnabled()) {
                    int i = sequenceNumber - 1;
                    String[] strArr = new String[i];
                    for (int i2 = 0; i2 < i; i2++) {
                        strArr[i2] = LongExtensions.toString(Long.valueOf(this.__lastPoppedSequenceNumber + ((long) i2) + 1));
                    }
                    if (fragment2.getLast()) {
                        __log.debug(getLogScope(), StringExtensions.format("Ignoring {0} missing VP8 packet(s) with timestamp {1} and sequence number(s) {2} -- the first packet(s) in a frame with temporal layer index {3}.", new Object[]{IntegerExtensions.toString(Integer.valueOf(i)), LongExtensions.toString(Long.valueOf(timestamp2)), StringExtensions.join(", ", strArr), IntegerExtensions.toString(Integer.valueOf(fragment.getTemporalLayerIndex()))}));
                    } else if (fragment.getFirst()) {
                        __log.debug(getLogScope(), StringExtensions.format("Ignoring {0} missing VP8 packet(s) with timestamp {1} and sequence number(s) {2} -- the last packet(s) in a frame with temporal layer index {3}.", new Object[]{IntegerExtensions.toString(Integer.valueOf(i)), LongExtensions.toString(Long.valueOf(this.__lastPoppedTimestamp)), StringExtensions.join(", ", strArr), IntegerExtensions.toString(Integer.valueOf(fragment2.getTemporalLayerIndex()))}));
                    } else {
                        __log.debug(getLogScope(), StringExtensions.format("Ignoring {0} missing VP8 packet(s) with timestamp {1} and sequence number(s) {2} -- the middle packet(s) in a frame with temporal layer index {3}.", new Object[]{IntegerExtensions.toString(Integer.valueOf(i)), LongExtensions.toString(Long.valueOf(this.__lastPoppedTimestamp)), StringExtensions.join(", ", strArr), IntegerExtensions.toString(Integer.valueOf(fragment2.getTemporalLayerIndex()))}));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean tryFound(long j) {
        if (ArrayListExtensions.getCount(this.__heldFrames) != 0) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__missingFrames); i++) {
                if (((RtpMissingFrame) ArrayListExtensions.getItem(this.__missingFrames).get(i)).getSequenceNumber() == j) {
                    ArrayListExtensions.removeAt(this.__missingFrames, i);
                    this.__foundCount++;
                    return true;
                }
            }
        }
        return false;
    }

    private long[] tryGetNackSequenceNumbers(long j) {
        if (!getHolding() || !getNackEnabled()) {
            return null;
        }
        ArrayList arrayList = null;
        for (int i = 0; i < ArrayListExtensions.getCount(this.__missingFrames); i++) {
            RtpMissingFrame rtpMissingFrame = (RtpMissingFrame) ArrayListExtensions.getItem(this.__missingFrames).get(i);
            if (rtpMissingFrame.nackable(j, getRoundTripTimeTicks(), getExpiryTicks())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(Long.valueOf(rtpMissingFrame.getSequenceNumber()));
                rtpMissingFrame.nacked(j);
            }
        }
        if (arrayList == null) {
            return null;
        }
        return Utility.toLongArray(arrayList);
    }

    private boolean tryHold(TFrame tframe) {
        long j = this.__lastPoppedSequenceNumber;
        int i = 0;
        while (i < ArrayListExtensions.getCount(this.__heldFrames)) {
            j = ((MediaFrame) ArrayListExtensions.getItem(this.__heldFrames).get(i)).getSequenceNumber();
            if (j == tframe.getSequenceNumber()) {
                this.__duplicateCount++;
                return false;
            } else if (j > tframe.getSequenceNumber()) {
                ArrayListExtensions.insert(this.__heldFrames, i, tframe);
                tryFound(tframe.getSequenceNumber());
                return true;
            } else {
                i++;
            }
        }
        this.__heldFrames.add(tframe);
        if (tframe.getSequenceNumber() - j > 1) {
            missing(j + 1, tframe.getSequenceNumber());
        }
        return true;
    }

    private boolean tryLost(long j, long j2) {
        for (int i = 0; i < ArrayListExtensions.getCount(this.__missingFrames); i++) {
            if (((RtpMissingFrame) ArrayListExtensions.getItem(this.__missingFrames).get(i)).getSequenceNumber() == j) {
                while (j < j2) {
                    ArrayListExtensions.removeAt(this.__missingFrames, i);
                    this.__lostCount++;
                    j++;
                }
                return true;
            }
        }
        return false;
    }

    private boolean tryPopGap() {
        MediaFrame head = getHead();
        MediaFrame tail = getTail();
        if (head == null || tail == null) {
            return false;
        }
        while (head != null && tail != null) {
            if (shouldPopGap(head, tail)) {
                tryLost(this.__lastPoppedSequenceNumber + 1, head.getSequenceNumber());
                tryUnhold(head.getSequenceNumber());
                pop(head);
                head.getBuffer().free();
                do {
                } while (tryPopHead());
                head = getHead();
                tail = getTail();
            } else {
                head = null;
                tail = null;
            }
        }
        return true;
    }

    private boolean tryPopHead() {
        MediaFrame head = getHead();
        if (head == null || head.getSequenceNumber() - this.__lastPoppedSequenceNumber != 1) {
            return false;
        }
        tryUnhold(head.getSequenceNumber());
        pop(head);
        head.getBuffer().free();
        return true;
    }

    private boolean tryUnhold(long j) {
        for (int i = 0; i < ArrayListExtensions.getCount(this.__heldFrames); i++) {
            if (((MediaFrame) ArrayListExtensions.getItem(this.__heldFrames).get(i)).getSequenceNumber() == j) {
                ArrayListExtensions.removeAt(this.__heldFrames, i);
                return true;
            }
        }
        return false;
    }
}
