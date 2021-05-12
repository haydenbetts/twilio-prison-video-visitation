package fm.liveswitch;

public class ImageSizePipe extends VideoPipe {
    private Size __empty = Size.getEmpty();
    private Size __maxSize = null;
    private Size __minSize = null;
    private Size __size;
    private Size __targetSize = null;
    private boolean _staticOutputSize;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Image Size Pipe";
    }

    public Size getMaxSupportedSize() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void sizeChanged(Size size, Size size2) {
    }

    /* access modifiers changed from: protected */
    public void sizeChanging(Size size, Size size2) {
    }

    /* access modifiers changed from: protected */
    public void doPreProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        super.doPreProcessFrame(videoFrame, videoBuffer);
        Size size = getSize();
        Size targetOutputSize = getTargetOutputSize();
        if (targetOutputSize != null && !targetOutputSize.isEquivalent(size)) {
            if (size != null) {
                Log.debug(StringExtensions.format("Changing {0} size from {1} to {2}.", getLabel(), size.toString(), targetOutputSize.toString()));
            }
            sizeChanging(size, targetOutputSize);
            setSize(targetOutputSize);
            sizeChanged(size, targetOutputSize);
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        raiseFrame(videoFrame);
    }

    public Size getMaxOutputSize() {
        Size maxSupportedSize = getStaticOutputSize() ? getMaxSupportedSize() : getMaxSize();
        return maxSupportedSize == null ? getMaxInputSize() : maxSupportedSize;
    }

    public Size getMaxSize() {
        return ConstraintUtility.min(this.__maxSize, getMaxSupportedSize());
    }

    public Size getMinOutputSize() {
        Size minSupportedSize = getStaticOutputSize() ? getMinSupportedSize() : getMinSize();
        return minSupportedSize == null ? getMinInputSize() : minSupportedSize;
    }

    public Size getMinSize() {
        return ConstraintUtility.max(this.__minSize, getMinSupportedSize());
    }

    public Size getMinSupportedSize() {
        return this.__empty;
    }

    public Size getSize() {
        return ConstraintUtility.clampMin(this.__size, getMinSupportedSize(), getMaxSupportedSize());
    }

    public boolean getStaticOutputSize() {
        return this._staticOutputSize;
    }

    public Size getTargetOutputSize() {
        return getTargetSize();
    }

    public Size getTargetSize() {
        return ConstraintUtility.clampMin(this.__targetSize, getMinOutputSize(), getMaxOutputSize());
    }

    public ImageSizePipe(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }

    public ImageSizePipe(VideoFormat videoFormat) {
        super(videoFormat);
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputSize(Size size) {
        setMaxSize(size);
    }

    public void setMaxSize(Size size) {
        this.__maxSize = size;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputSize(Size size) {
        setMinSize(size);
    }

    public void setMinSize(Size size) {
        this.__minSize = size;
    }

    /* access modifiers changed from: protected */
    public void setSize(Size size) {
        this.__size = size;
    }

    public void setStaticOutputSize(boolean z) {
        this._staticOutputSize = z;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputSize(Size size) {
        setTargetSize(size);
    }

    public void setTargetSize(Size size) {
        this.__targetSize = size;
    }
}
