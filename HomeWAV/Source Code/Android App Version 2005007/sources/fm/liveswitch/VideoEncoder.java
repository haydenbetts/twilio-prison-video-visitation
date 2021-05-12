package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class VideoEncoder extends VideoPipe {
    private static ILog __log = Log.getLogger(VideoEncoder.class);
    private int __bitrate = -1;
    private AtomicLong __framesEncoded = new AtomicLong();
    private long __lastFrameRateCount = 0;
    private long __lastFrameRateUpdateTimestamp = -1;
    private int __maxBitrate = -1;
    private int __minBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onBitrateChange = new ArrayList();
    private int __targetBitrate = -1;
    private int _frameHeight;
    private double _frameRate;
    private int _frameWidth;
    private IAction0 _onBitrateChange = null;
    private boolean _staticOutputBitrate;

    public abstract boolean getForceKeyFrame();

    public abstract void setForceKeyFrame(boolean z);

    public void addOnBitrateChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onBitrateChange == null) {
                this._onBitrateChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(VideoEncoder.this.__onBitrateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onBitrateChange.add(iAction0);
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        PayloadSpecificControlFrame payloadSpecificControlFrame;
        LrrControlFrame lrrControlFrame;
        long outputSynchronizationSource = super.getOutputSynchronizationSource();
        for (MediaControlFrame mediaControlFrame : mediaControlFrameArr) {
            if (mediaControlFrame.getPayloadType() == PayloadSpecificControlFrame.getRegisteredPayloadType() && (payloadSpecificControlFrame = (PayloadSpecificControlFrame) Global.tryCast(mediaControlFrame, PayloadSpecificControlFrame.class)) != null) {
                if (payloadSpecificControlFrame.getFeedbackMessageType() == PliControlFrame.getRegisteredFeedbackMessageType()) {
                    PliControlFrame pliControlFrame = (PliControlFrame) Global.tryCast(mediaControlFrame, PliControlFrame.class);
                    if (pliControlFrame != null && ((outputSynchronizationSource == -1 || pliControlFrame.getMediaSourceSynchronizationSource() == outputSynchronizationSource) && !getForceKeyFrame())) {
                        __log.debug(StringExtensions.format("Processing picture loss indication (PLI) for {0} (SSRC {1}).", getLabel(), LongExtensions.toString(Long.valueOf(outputSynchronizationSource))));
                        setForceKeyFrame(true);
                    }
                } else if (payloadSpecificControlFrame.getFeedbackMessageType() == FirControlFrame.getRegisteredFeedbackMessageType()) {
                    FirControlFrame firControlFrame = (FirControlFrame) Global.tryCast(mediaControlFrame, FirControlFrame.class);
                    if (firControlFrame != null && ((outputSynchronizationSource == -1 || firControlFrame.getMediaSourceSynchronizationSource() == outputSynchronizationSource) && !getForceKeyFrame())) {
                        __log.debug(StringExtensions.format("Processing full intra request (FIR) for {0} (SSRC {1}).", getLabel(), LongExtensions.toString(Long.valueOf(outputSynchronizationSource))));
                        setForceKeyFrame(true);
                    }
                } else if (payloadSpecificControlFrame.getFeedbackMessageType() == LrrControlFrame.getRegisteredFeedbackMessageType() && (lrrControlFrame = (LrrControlFrame) Global.tryCast(mediaControlFrame, LrrControlFrame.class)) != null && ((outputSynchronizationSource == -1 || lrrControlFrame.getMediaSourceSynchronizationSource() == outputSynchronizationSource) && !getForceKeyFrame())) {
                    __log.debug(StringExtensions.format("Processing layer refresh request (LRR) for {0} (SSRC {1}).", getLabel(), LongExtensions.toString(Long.valueOf(outputSynchronizationSource))));
                    setForceKeyFrame(true);
                }
            }
        }
        super.doProcessControlFrames(mediaControlFrameArr);
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromOutput(mediaTrackStats);
        mediaTrackStats.setMinBitrate(getMinBitrate());
        mediaTrackStats.setMaxBitrate(getMaxBitrate());
        mediaTrackStats.setBitrate(getBitrate());
        mediaTrackStats.setFrameWidth(getFrameWidth());
        mediaTrackStats.setFrameHeight(getFrameHeight());
        mediaTrackStats.setFrameRate(getFrameRate());
        mediaTrackStats.setFramesEncoded(getFramesEncoded());
    }

    public int getBitrate() {
        return ConstraintUtility.clampMin(this.__bitrate, getMinCodecBitrate(), getMaxCodecBitrate());
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return super.getOutputFormat() != null && !((VideoFormat) super.getOutputFormat()).getIsFixedBitrate();
    }

    public int getFrameHeight() {
        return this._frameHeight;
    }

    public double getFrameRate() {
        return this._frameRate;
    }

    public long getFramesEncoded() {
        return this.__framesEncoded.getValue();
    }

    public int getFrameWidth() {
        return this._frameWidth;
    }

    public int getMaxBitrate() {
        return ConstraintUtility.min(this.__maxBitrate, getMaxCodecBitrate());
    }

    public int getMaxCodecBitrate() {
        VideoFormat videoFormat = (VideoFormat) super.getOutputFormat();
        if (videoFormat == null) {
            return -1;
        }
        return videoFormat.getMaxBitrate();
    }

    public int getMaxOutputBitrate() {
        int maxCodecBitrate = getStaticOutputBitrate() ? getMaxCodecBitrate() : getMaxBitrate();
        return maxCodecBitrate == -1 ? getMaxInputBitrate() : maxCodecBitrate;
    }

    public int getMinBitrate() {
        return ConstraintUtility.max(this.__minBitrate, getMinCodecBitrate());
    }

    public int getMinCodecBitrate() {
        VideoFormat videoFormat = (VideoFormat) super.getOutputFormat();
        if (videoFormat == null) {
            return 0;
        }
        return videoFormat.getMinBitrate();
    }

    public int getMinOutputBitrate() {
        int minCodecBitrate = getStaticOutputBitrate() ? getMinCodecBitrate() : getMinBitrate();
        return minCodecBitrate == -1 ? getMinInputBitrate() : minCodecBitrate;
    }

    public boolean getStaticOutputBitrate() {
        return this._staticOutputBitrate;
    }

    public int getTargetBitrate() {
        return ConstraintUtility.clampMin(this.__targetBitrate, getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public int getTargetOutputBitrate() {
        return getTargetBitrate();
    }

    public boolean processFrame(VideoFrame videoFrame) {
        setFrameWidth(((VideoBuffer) videoFrame.getLastBuffer()).getWidth());
        setFrameHeight(((VideoBuffer) videoFrame.getLastBuffer()).getHeight());
        return super.processFrame(videoFrame);
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(VideoFrame videoFrame) {
        this.__framesEncoded.increment();
        long timestamp = ManagedStopwatch.getTimestamp();
        long j = this.__lastFrameRateUpdateTimestamp;
        if (j == -1) {
            this.__lastFrameRateCount = 0;
            this.__lastFrameRateUpdateTimestamp = timestamp;
        } else {
            long j2 = timestamp - j;
            if (j2 >= ((long) Constants.getTicksPerSecond())) {
                long framesEncoded = getFramesEncoded();
                setFrameRate(((double) ((framesEncoded - this.__lastFrameRateCount) * ((long) Constants.getTicksPerSecond()))) / ((double) j2));
                this.__lastFrameRateCount = framesEncoded;
                this.__lastFrameRateUpdateTimestamp = timestamp;
            }
        }
        super.raiseFrame(videoFrame);
    }

    public void removeOnBitrateChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onBitrateChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onBitrateChange.remove(iAction0);
        if (this.__onBitrateChange.size() == 0) {
            this._onBitrateChange = null;
        }
    }

    /* access modifiers changed from: protected */
    public void setBitrate(int i) {
        if (this.__bitrate != i) {
            this.__bitrate = i;
            IAction0 iAction0 = this._onBitrateChange;
            if (iAction0 != null) {
                iAction0.invoke();
            }
        }
    }

    private void setFrameHeight(int i) {
        this._frameHeight = i;
    }

    private void setFrameRate(double d) {
        this._frameRate = d;
    }

    private void setFrameWidth(int i) {
        this._frameWidth = i;
    }

    public void setMaxBitrate(int i) {
        this.__maxBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputBitrate(int i) {
        setMaxBitrate(i);
    }

    public void setMinBitrate(int i) {
        this.__minBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputBitrate(int i) {
        setMinBitrate(i);
    }

    public void setStaticOutputBitrate(boolean z) {
        this._staticOutputBitrate = z;
    }

    public void setTargetBitrate(int i) {
        this.__targetBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputBitrate(int i) {
        setTargetBitrate(i);
    }

    public VideoEncoder(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }
}
