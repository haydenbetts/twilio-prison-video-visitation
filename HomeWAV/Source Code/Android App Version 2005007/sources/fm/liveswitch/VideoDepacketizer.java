package fm.liveswitch;

import fm.liveswitch.VideoFragment;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class VideoDepacketizer<TFragment extends VideoFragment> extends VideoPipe {
    private static IDataBufferPool __dataBufferPool;
    private static ILog __log;
    private boolean __delayDepacketizeOnPendingKeyFrame = true;
    private AtomicLong __framesCorrupted = new AtomicLong();
    private AtomicLong __framesDropped = new AtomicLong();
    private AtomicLong __framesReceived = new AtomicLong();
    private volatile boolean __sentKeyFrameRequestForCurrentFrame = false;
    private volatile boolean _keyFrameEverReceived = false;
    private volatile boolean _keyFrameReceived = false;
    private long _lastCorruptTimestamp = -1;
    private TFragment _lastFragment = null;
    private long _lastSequenceNumber = -1;
    private long _lastTimestamp = -1;
    private ArrayList<TFragment> _pendingFragments = new ArrayList<>();

    /* access modifiers changed from: protected */
    public abstract TFragment doCreateFragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer);

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isGapAllowed(TFragment tfragment, TFragment tfragment2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isKeyFrame(DataBuffer dataBuffer);

    private void clearPendingFragments() {
        Iterator<TFragment> it = this._pendingFragments.iterator();
        while (it.hasNext()) {
            VideoFragment videoFragment = (VideoFragment) it.next();
            videoFragment.free();
            videoFragment.destroy();
        }
        this._pendingFragments.clear();
    }

    private static VideoFormat cloneFormat(VideoFormat videoFormat, boolean z) {
        VideoFormat clone = videoFormat.clone();
        clone.setIsPacketized(z);
        return clone;
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        clearPendingFragments();
        TFragment tfragment = this._lastFragment;
        if (tfragment != null) {
            tfragment.free();
            this._lastFragment = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        int i = 0;
        this.__sentKeyFrameRequestForCurrentFrame = false;
        long timestamp = videoFrame.getTimestamp();
        long sequenceNumber = videoBuffer.getSequenceNumber();
        if (this._lastSequenceNumber == -1) {
            this._lastSequenceNumber = sequenceNumber - 1;
        }
        if (sequenceNumber > this._lastSequenceNumber) {
            TFragment doCreateFragment = doCreateFragment(videoBuffer.getRtpHeader(), videoBuffer.getDataBuffer());
            doCreateFragment.keep();
            if (sequenceNumber - this._lastSequenceNumber > 1) {
                if (!isGapAllowed(doCreateFragment, this._lastFragment)) {
                    this._keyFrameReceived = false;
                }
                if (ArrayListExtensions.getCount(this._pendingFragments) > 0) {
                    clearPendingFragments();
                    long j = this._lastCorruptTimestamp;
                    long j2 = this._lastTimestamp;
                    if (j != j2) {
                        this._lastCorruptTimestamp = j2;
                        this.__framesCorrupted.increment();
                    }
                } else if (doCreateFragment.getFirst()) {
                    long j3 = this._lastCorruptTimestamp;
                    long j4 = this._lastTimestamp;
                    if (j3 != j4) {
                        this._lastCorruptTimestamp = j4;
                        this.__framesCorrupted.increment();
                    }
                }
            }
            this._lastSequenceNumber = sequenceNumber;
            this._lastTimestamp = timestamp;
            doCreateFragment.keep();
            TFragment tfragment = this._lastFragment;
            if (tfragment != null) {
                tfragment.free();
            }
            this._lastFragment = doCreateFragment;
            if (ArrayListExtensions.getCount(this._pendingFragments) != 0 || doCreateFragment.getFirst()) {
                if (ArrayListExtensions.getCount(this._pendingFragments) != 0 && doCreateFragment.getFirst()) {
                    clearPendingFragments();
                    this.__framesDropped.increment();
                }
                this._pendingFragments.add(doCreateFragment);
                VideoFragment videoFragment = (VideoFragment) ArrayListExtensions.getItem(this._pendingFragments).get(ArrayListExtensions.getCount(this._pendingFragments) - 1);
                if (((VideoFragment) ArrayListExtensions.getItem(this._pendingFragments).get(0)).getFirst() && videoFragment.getLast()) {
                    Iterator<TFragment> it = this._pendingFragments.iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        i2 += ((VideoFragment) it.next()).getBuffer().getLength();
                    }
                    DataBuffer take = __dataBufferPool.take(i2);
                    Iterator<TFragment> it2 = this._pendingFragments.iterator();
                    while (it2.hasNext()) {
                        IntegerHolder integerHolder = new IntegerHolder(i);
                        take.write(((VideoFragment) it2.next()).getBuffer(), i, integerHolder);
                        i = integerHolder.getValue();
                    }
                    clearPendingFragments();
                    if (getDelayDepacketize()) {
                        if (isKeyFrame(take)) {
                            this._keyFrameReceived = true;
                            if (!this._keyFrameEverReceived) {
                                this._keyFrameEverReceived = true;
                            } else {
                                IVideoOutput iVideoOutput = (IVideoOutput) super.getInput();
                                if (iVideoOutput != null) {
                                    iVideoOutput.incrementCcmSequenceNumber();
                                }
                            }
                        } else {
                            sendKeyFrameRequest("Video depacketizer needs keyframe.");
                        }
                    }
                    this.__framesReceived.increment();
                    if (getDelayDepacketize()) {
                        this.__framesDropped.increment();
                    } else {
                        videoFrame.addBuffer(new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), take, (VideoFormat) super.getOutputFormat()));
                        raiseFrame(videoFrame);
                    }
                    take.free();
                } else if (((VideoFragment) ArrayListExtensions.getItem(this._pendingFragments).get(ArrayListExtensions.getCount(this._pendingFragments) - 1)).getLast()) {
                    Iterator<TFragment> it3 = this._pendingFragments.iterator();
                    while (it3.hasNext()) {
                        VideoFragment videoFragment2 = (VideoFragment) it3.next();
                        videoFragment2.free();
                        videoFragment2.destroy();
                    }
                    this._pendingFragments.clear();
                }
            } else if (this._lastCorruptTimestamp != videoFrame.getTimestamp()) {
                this._lastCorruptTimestamp = videoFrame.getTimestamp();
                this.__framesCorrupted.increment();
            }
        } else {
            throw new RuntimeException(new Exception("Video depacketizer cannot process unordered packets."));
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromInput(mediaTrackStats);
        mediaTrackStats.setFramesReceived(getFramesReceived());
        mediaTrackStats.setFramesDropped(getFramesDropped());
        mediaTrackStats.setFramesCorrupted(getFramesCorrupted());
    }

    /* access modifiers changed from: protected */
    public boolean getDelayDepacketize() {
        return getDelayDepacketizeOnPendingKeyFrame() && !this._keyFrameReceived;
    }

    public boolean getDelayDepacketizeOnPendingKeyFrame() {
        return this.__delayDepacketizeOnPendingKeyFrame;
    }

    public long getFramesCorrupted() {
        return this.__framesCorrupted.getValue();
    }

    public long getFramesDropped() {
        return this.__framesDropped.getValue();
    }

    public long getFramesReceived() {
        return this.__framesReceived.getValue();
    }

    private static VideoFormat prepareInputFormat(VideoFormat videoFormat) {
        if (videoFormat == null) {
            return null;
        }
        return cloneFormat(videoFormat, true);
    }

    private static VideoFormat prepareOutputFormat(VideoFormat videoFormat) {
        if (videoFormat == null) {
            return null;
        }
        return cloneFormat(videoFormat, false);
    }

    /* access modifiers changed from: protected */
    public void sendKeyFrameRequest(String str) {
        if (!this.__sentKeyFrameRequestForCurrentFrame) {
            this.__sentKeyFrameRequestForCurrentFrame = true;
            IVideoOutput iVideoOutput = (IVideoOutput) super.getInput();
            if (this._keyFrameEverReceived || iVideoOutput == null) {
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("Video decoder raising picture loss indication (PLI). {0}", (Object) str));
                }
                super.raiseControlFrame(new PliControlFrame());
            } else {
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(StringExtensions.format("Video decoder raising full intra request (FIR). {0}", (Object) str));
                }
                super.raiseControlFrame(new FirControlFrame(iVideoOutput.getCcmSequenceNumber()));
            }
            this._keyFrameReceived = false;
        }
    }

    public void setDelayDepacketizeOnPendingKeyFrame(boolean z) {
        this.__delayDepacketizeOnPendingKeyFrame = z;
    }

    static {
        Class<VideoDepacketizer> cls = VideoDepacketizer.class;
        __dataBufferPool = DataBufferPool.getTracer((Class) cls);
        __log = Log.getLogger((Class) cls);
    }

    public VideoDepacketizer(VideoFormat videoFormat) {
        super(prepareInputFormat(videoFormat), prepareOutputFormat(videoFormat));
    }

    public VideoDepacketizer(IVideoOutput iVideoOutput) {
        super(prepareInputFormat((VideoFormat) iVideoOutput.getOutputFormat()), prepareOutputFormat((VideoFormat) iVideoOutput.getOutputFormat()));
        super.addInput(iVideoOutput);
    }

    public VideoDepacketizer(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(prepareInputFormat(videoFormat), prepareOutputFormat(videoFormat2));
    }
}
