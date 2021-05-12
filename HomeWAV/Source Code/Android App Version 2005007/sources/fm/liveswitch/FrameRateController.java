package fm.liveswitch;

public class FrameRateController extends FrameRatePipe {
    private SmoothingContext __frameDurationContext;
    private long __lastTimestamp = -1;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Frame-Rate Controller";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        double frameRate = getFrameRate();
        if (frameRate == -1.0d) {
            this.__lastTimestamp = -1;
            raiseFrame(videoFrame);
        } else if (frameRate != 0.0d) {
            double d = 1.0E7d / frameRate;
            if (this.__frameDurationContext == null) {
                this.__frameDurationContext = new SmoothingContext(SmoothingContext.getDefaultSmoothingFactor(), d);
            }
            long timestamp = ManagedStopwatch.getTimestamp();
            long j = this.__lastTimestamp;
            if (j == -1) {
                this.__lastTimestamp = timestamp;
                raiseFrame(videoFrame);
                return;
            }
            double d2 = (double) (timestamp - j);
            if (this.__frameDurationContext.testValue(d2) >= d) {
                this.__frameDurationContext.processValue(d2);
                this.__lastTimestamp = timestamp;
                raiseFrame(videoFrame);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void frameRateChanged(double d, double d2) {
        super.frameRateChanged(d, d2);
        this.__frameDurationContext = null;
    }

    public FrameRateController(VideoFormat videoFormat) {
        super(videoFormat);
    }
}
