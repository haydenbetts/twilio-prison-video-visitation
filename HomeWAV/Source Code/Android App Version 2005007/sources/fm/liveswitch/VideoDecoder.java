package fm.liveswitch;

public abstract class VideoDecoder extends VideoPipe {
    private boolean __delayDecodeOnPendingKeyFrame = true;
    private boolean __firstFrameDecoded = false;
    private AtomicLong __framesDecoded = new AtomicLong();
    private long __lastFrameRateCount = 0;
    private long __lastFrameRateUpdateTimestamp = -1;
    private int _frameHeight;
    private double _frameRate;
    private int _frameWidth;
    private volatile boolean _keyFrameEverReceived = false;
    private volatile boolean _keyFrameReceived = false;

    public int getMaxInputBitrate() {
        return -1;
    }

    public int getMaxOutputBitrate() {
        return -1;
    }

    public int getMinInputBitrate() {
        return -1;
    }

    public int getMinOutputBitrate() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isKeyFrame(DataBuffer dataBuffer);

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        if (!getDelayDecode()) {
            return;
        }
        if (isKeyFrame(videoBuffer.getDataBuffer())) {
            this._keyFrameReceived = true;
            if (!this._keyFrameEverReceived) {
                this._keyFrameEverReceived = true;
                return;
            }
            IVideoOutput iVideoOutput = (IVideoOutput) super.getInput();
            if (iVideoOutput != null) {
                iVideoOutput.incrementCcmSequenceNumber();
                return;
            }
            return;
        }
        sendKeyFrameRequest("Decoder needs keyframe.");
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromInput(mediaTrackStats);
        mediaTrackStats.setFrameWidth(getFrameWidth());
        mediaTrackStats.setFrameHeight(getFrameHeight());
        mediaTrackStats.setFrameRate(getFrameRate());
        mediaTrackStats.setFramesDecoded(getFramesDecoded());
    }

    /* access modifiers changed from: protected */
    public boolean getDelayDecode() {
        return getDelayDecodeOnPendingKeyFrame() && !this._keyFrameReceived;
    }

    public boolean getDelayDecodeOnPendingKeyFrame() {
        return this.__delayDecodeOnPendingKeyFrame;
    }

    public int getFrameHeight() {
        return this._frameHeight;
    }

    public double getFrameRate() {
        return this._frameRate;
    }

    public long getFramesDecoded() {
        return this.__framesDecoded.getValue();
    }

    public int getFrameWidth() {
        return this._frameWidth;
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(VideoFrame videoFrame) {
        this.__framesDecoded.increment();
        long timestamp = ManagedStopwatch.getTimestamp();
        long j = this.__lastFrameRateUpdateTimestamp;
        if (j == -1) {
            this.__lastFrameRateCount = 0;
            this.__lastFrameRateUpdateTimestamp = timestamp;
        } else {
            long j2 = timestamp - j;
            if (j2 >= ((long) Constants.getTicksPerSecond())) {
                long framesDecoded = getFramesDecoded();
                setFrameRate(((double) ((framesDecoded - this.__lastFrameRateCount) * ((long) Constants.getTicksPerSecond()))) / ((double) j2));
                this.__lastFrameRateCount = framesDecoded;
                this.__lastFrameRateUpdateTimestamp = timestamp;
            }
        }
        setFrameWidth(((VideoBuffer) videoFrame.getLastBuffer()).getWidth());
        setFrameHeight(((VideoBuffer) videoFrame.getLastBuffer()).getHeight());
        if (!this.__firstFrameDecoded) {
            this.__firstFrameDecoded = true;
            StatControlFrame statControlFrame = new StatControlFrame();
            statControlFrame.setType(StatControlFrameType.FrameDecoded);
            statControlFrame.setTimestamp(timestamp);
            super.raiseControlFrame(statControlFrame);
        }
        super.raiseFrame(videoFrame);
    }

    /* access modifiers changed from: protected */
    public void sendKeyFrameRequest(String str) {
        IVideoOutput iVideoOutput = (IVideoOutput) super.getInput();
        if (this._keyFrameEverReceived || iVideoOutput == null) {
            if (Log.getIsVerboseEnabled()) {
                Log.verbose(StringExtensions.format("Video decoder raising picture loss indication (PLI). {0}", (Object) str));
            }
            super.raiseControlFrame(new PliControlFrame());
        } else {
            if (Log.getIsVerboseEnabled()) {
                Log.verbose(StringExtensions.format("Video decoder raising full intra request (FIR). {0}", (Object) str));
            }
            super.raiseControlFrame(new FirControlFrame(iVideoOutput.getCcmSequenceNumber()));
        }
        this._keyFrameReceived = false;
    }

    public void setDelayDecodeOnPendingKeyFrame(boolean z) {
        this.__delayDecodeOnPendingKeyFrame = z;
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

    public VideoDecoder(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }
}
