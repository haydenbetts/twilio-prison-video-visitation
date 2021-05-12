package fm.liveswitch;

public abstract class VideoPacketizer extends VideoPipe {
    private AtomicLong __framesSent = new AtomicLong();

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return true;
    }

    private static VideoFormat cloneFormat(VideoFormat videoFormat, boolean z) {
        VideoFormat clone = videoFormat.clone();
        clone.setIsPacketized(z);
        return clone;
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromOutput(mediaTrackStats);
        mediaTrackStats.setFramesSent(getFramesSent());
    }

    public long getFramesSent() {
        return this.__framesSent.getValue();
    }

    private static VideoFormat prepareInputFormat(VideoFormat videoFormat) {
        if (videoFormat == null) {
            return null;
        }
        return cloneFormat(videoFormat, false);
    }

    private static VideoFormat prepareOutputFormat(VideoFormat videoFormat) {
        if (videoFormat == null) {
            return null;
        }
        return cloneFormat(videoFormat, true);
    }

    public boolean processFrame(VideoFrame videoFrame) {
        this.__framesSent.increment();
        return super.processFrame(videoFrame);
    }

    public VideoPacketizer(VideoFormat videoFormat) {
        super(prepareInputFormat(videoFormat), prepareOutputFormat(videoFormat));
    }

    public VideoPacketizer(IVideoOutput iVideoOutput) {
        super(prepareInputFormat((VideoFormat) iVideoOutput.getOutputFormat()), prepareOutputFormat((VideoFormat) iVideoOutput.getOutputFormat()));
        super.addInput(iVideoOutput);
    }

    public VideoPacketizer(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(prepareInputFormat(videoFormat), prepareOutputFormat(videoFormat2));
    }
}
