package fm.liveswitch;

public class FrameRatePipe extends VideoPipe {
    private double __frameRate = -1.0d;
    private double __maxFrameRate = -1.0d;
    private double __minFrameRate = -1.0d;
    private double __targetFrameRate = -1.0d;
    private boolean _staticOutputFrameRate;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void frameRateChanged(double d, double d2) {
    }

    /* access modifiers changed from: protected */
    public void frameRateChanging(double d, double d2) {
    }

    public String getLabel() {
        return "Frame-Rate Pipe";
    }

    public double getMaxSupportedFrameRate() {
        return -1.0d;
    }

    public double getMinSupportedFrameRate() {
        return 0.0d;
    }

    /* access modifiers changed from: protected */
    public void doPreProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        super.doPreProcessFrame(videoFrame, videoBuffer);
        double frameRate = getFrameRate();
        double targetOutputFrameRate = getTargetOutputFrameRate();
        if (targetOutputFrameRate > 0.0d && targetOutputFrameRate != frameRate) {
            if (frameRate > 0.0d) {
                Log.debug(StringExtensions.format("Changing {0} frame-rate from {1}fps to {2}fps.", getLabel(), DoubleExtensions.toString(Double.valueOf(frameRate)), DoubleExtensions.toString(Double.valueOf(targetOutputFrameRate))));
            }
            frameRateChanging(frameRate, targetOutputFrameRate);
            setFrameRate(targetOutputFrameRate);
            frameRateChanged(frameRate, targetOutputFrameRate);
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        raiseFrame(videoFrame);
    }

    public FrameRatePipe(VideoFormat videoFormat) {
        super(videoFormat.clone(), videoFormat.clone());
    }

    public double getFrameRate() {
        return ConstraintUtility.clampMin(this.__frameRate, getMinSupportedFrameRate(), getMaxSupportedFrameRate());
    }

    public double getMaxFrameRate() {
        return ConstraintUtility.min(this.__maxFrameRate, getMaxSupportedFrameRate());
    }

    public double getMaxOutputFrameRate() {
        double maxSupportedFrameRate = getStaticOutputFrameRate() ? getMaxSupportedFrameRate() : getMaxFrameRate();
        return maxSupportedFrameRate == -1.0d ? getMaxInputFrameRate() : maxSupportedFrameRate;
    }

    public double getMinFrameRate() {
        return ConstraintUtility.max(this.__minFrameRate, getMinSupportedFrameRate());
    }

    public double getMinOutputFrameRate() {
        double minSupportedFrameRate = getStaticOutputFrameRate() ? getMinSupportedFrameRate() : getMinFrameRate();
        return minSupportedFrameRate == -1.0d ? getMinInputFrameRate() : minSupportedFrameRate;
    }

    public boolean getStaticOutputFrameRate() {
        return this._staticOutputFrameRate;
    }

    public double getTargetFrameRate() {
        return ConstraintUtility.clampMin(this.__targetFrameRate, getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public double getTargetOutputFrameRate() {
        return getTargetFrameRate();
    }

    /* access modifiers changed from: protected */
    public void setFrameRate(double d) {
        this.__frameRate = d;
    }

    public void setMaxFrameRate(double d) {
        this.__maxFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputFrameRate(double d) {
        setMaxFrameRate(d);
    }

    public void setMinFrameRate(double d) {
        this.__minFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputFrameRate(double d) {
        setMinFrameRate(d);
    }

    public void setStaticOutputFrameRate(boolean z) {
        this._staticOutputFrameRate = z;
    }

    public void setTargetFrameRate(double d) {
        this.__targetFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputFrameRate(double d) {
        setTargetFrameRate(d);
    }
}
