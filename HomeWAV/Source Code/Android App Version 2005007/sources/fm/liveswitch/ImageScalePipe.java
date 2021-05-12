package fm.liveswitch;

public class ImageScalePipe extends VideoPipe {
    private double __maxScale = -1.0d;
    private double __minScale = -1.0d;
    private double __scale = -1.0d;
    private double __targetScale = -1.0d;
    private boolean _staticOutputScale;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Image Scale Pipe";
    }

    public double getMaxSupportedScale() {
        return -1.0d;
    }

    public double getMinSupportedScale() {
        return 0.0d;
    }

    /* access modifiers changed from: protected */
    public void scaleChanged(double d, double d2) {
    }

    /* access modifiers changed from: protected */
    public void scaleChanging(double d, double d2) {
    }

    /* access modifiers changed from: protected */
    public void doPreProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        super.doPreProcessFrame(videoFrame, videoBuffer);
        double scale = getScale();
        double targetOutputScale = getTargetOutputScale();
        if (targetOutputScale > 0.0d && targetOutputScale != scale) {
            if (scale > 0.0d) {
                Log.debug(StringExtensions.format("Changing {0} scale from {1} to {2}.", getLabel(), DoubleExtensions.toString(Double.valueOf(scale)), DoubleExtensions.toString(Double.valueOf(targetOutputScale))));
            }
            scaleChanging(scale, targetOutputScale);
            setScale(targetOutputScale);
            scaleChanged(scale, targetOutputScale);
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        raiseFrame(videoFrame);
    }

    public double getMaxOutputScale() {
        double maxSupportedScale = getStaticOutputScale() ? getMaxSupportedScale() : getMaxScale();
        return maxSupportedScale == -1.0d ? getMaxInputScale() : maxSupportedScale;
    }

    public double getMaxScale() {
        return ConstraintUtility.min(this.__maxScale, getMaxSupportedScale());
    }

    public double getMinOutputScale() {
        double minSupportedScale = getStaticOutputScale() ? getMinSupportedScale() : getMinScale();
        return minSupportedScale == -1.0d ? getMinInputScale() : minSupportedScale;
    }

    public double getMinScale() {
        return ConstraintUtility.max(this.__minScale, getMinSupportedScale());
    }

    public double getScale() {
        return ConstraintUtility.clampMin(this.__scale, getMinSupportedScale(), getMaxSupportedScale());
    }

    public boolean getStaticOutputScale() {
        return this._staticOutputScale;
    }

    public double getTargetOutputScale() {
        return getTargetScale();
    }

    public double getTargetScale() {
        return ConstraintUtility.clampMin(this.__targetScale, getMinOutputScale(), getMaxOutputScale());
    }

    public ImageScalePipe(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }

    public ImageScalePipe(VideoFormat videoFormat) {
        super(videoFormat);
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputScale(double d) {
        setMaxScale(d);
    }

    public void setMaxScale(double d) {
        this.__maxScale = d;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputScale(double d) {
        setMinScale(d);
    }

    public void setMinScale(double d) {
        this.__minScale = d;
    }

    /* access modifiers changed from: protected */
    public void setScale(double d) {
        this.__scale = d;
    }

    public void setStaticOutputScale(boolean z) {
        this._staticOutputScale = z;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputScale(double d) {
        setTargetScale(d);
    }

    public void setTargetScale(double d) {
        this.__targetScale = d;
    }
}
