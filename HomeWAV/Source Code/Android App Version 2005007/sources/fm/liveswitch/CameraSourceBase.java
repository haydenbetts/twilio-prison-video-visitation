package fm.liveswitch;

public abstract class CameraSourceBase extends VideoSource {
    private Size __empty = Size.getEmpty();
    private double __frameRate = -1.0d;
    private double __maxFrameRate = -1.0d;
    private Size __maxSize = null;
    private double __minFrameRate = -1.0d;
    private Size __minSize = null;
    private Size __size = null;
    private double __targetFrameRate = -1.0d;
    private Size __targetSize = null;
    private boolean _staticOutputFrameRate;
    private boolean _staticOutputSize;

    public double getMaxSupportedFrameRate() {
        return -1.0d;
    }

    public Size getMaxSupportedSize() {
        return null;
    }

    public double getMinSupportedFrameRate() {
        return 0.0d;
    }

    public CameraSourceBase(VideoFormat videoFormat, VideoConfig videoConfig) {
        super(videoFormat);
        setTargetConfig(videoConfig);
        setVideoType(VideoType.Camera);
    }

    public VideoConfig getConfig() {
        return new VideoConfig(getSize(), getFrameRate());
    }

    public double getFrameRate() {
        return ConstraintUtility.clampMin(ConstraintUtility.coalesce(this.__frameRate, this.__targetFrameRate), getMinSupportedFrameRate(), getMaxSupportedFrameRate());
    }

    public double getMaxFrameRate() {
        return ConstraintUtility.min(this.__maxFrameRate, getMaxSupportedFrameRate());
    }

    public double getMaxOutputFrameRate() {
        if (getStaticOutputFrameRate()) {
            return getMaxSupportedFrameRate();
        }
        return getMaxFrameRate();
    }

    public Size getMaxOutputSize() {
        if (getStaticOutputSize()) {
            return getMaxSupportedSize();
        }
        return getMaxSize();
    }

    public Size getMaxSize() {
        return ConstraintUtility.min(this.__maxSize, getMaxSupportedSize());
    }

    public double getMinFrameRate() {
        return ConstraintUtility.max(this.__minFrameRate, getMinSupportedFrameRate());
    }

    public double getMinOutputFrameRate() {
        if (getStaticOutputFrameRate()) {
            return getMinSupportedFrameRate();
        }
        return getMinFrameRate();
    }

    public Size getMinOutputSize() {
        if (getStaticOutputSize()) {
            return getMinSupportedSize();
        }
        return getMinSize();
    }

    public Size getMinSize() {
        return ConstraintUtility.max(this.__minSize, getMinSupportedSize());
    }

    public Size getMinSupportedSize() {
        return this.__empty;
    }

    public Size getSize() {
        Size size = this.__size;
        if (size == null) {
            size = this.__targetSize;
        }
        return ConstraintUtility.clampMin(size, getMinSupportedSize(), getMaxSupportedSize());
    }

    public boolean getStaticOutputFrameRate() {
        return this._staticOutputFrameRate;
    }

    public boolean getStaticOutputSize() {
        return this._staticOutputSize;
    }

    public VideoConfig getTargetConfig() {
        return new VideoConfig(getTargetSize(), getTargetFrameRate());
    }

    public double getTargetFrameRate() {
        return ConstraintUtility.clampMin(this.__targetFrameRate, getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public double getTargetOutputFrameRate() {
        return getTargetFrameRate();
    }

    public Size getTargetOutputSize() {
        return getTargetSize();
    }

    public Size getTargetSize() {
        return ConstraintUtility.clampMin(this.__targetSize, getMinOutputSize(), getMaxOutputSize());
    }

    /* access modifiers changed from: protected */
    public void setConfig(VideoConfig videoConfig) {
        if (videoConfig == null) {
            setSize((Size) null);
            setFrameRate(-1.0d);
            return;
        }
        setSize(videoConfig.getSize());
        setFrameRate(videoConfig.getFrameRate());
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

    /* access modifiers changed from: protected */
    public void setMaxOutputSize(Size size) {
        setMaxSize(size);
    }

    public void setMaxSize(Size size) {
        this.__maxSize = size;
    }

    public void setMinFrameRate(double d) {
        this.__minFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputFrameRate(double d) {
        setMinFrameRate(d);
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

    public void setStaticOutputFrameRate(boolean z) {
        this._staticOutputFrameRate = z;
    }

    public void setStaticOutputSize(boolean z) {
        this._staticOutputSize = z;
    }

    public void setTargetConfig(VideoConfig videoConfig) {
        if (videoConfig != null) {
            setTargetSize(videoConfig.getSize());
            setTargetFrameRate(videoConfig.getFrameRate());
            return;
        }
        throw new RuntimeException(new Exception("Target configuration cannot be null."));
    }

    /* access modifiers changed from: protected */
    public void setTargetFrameRate(double d) {
        if (d > 0.0d) {
            this.__targetFrameRate = d;
            return;
        }
        throw new RuntimeException(new Exception("Target frame-rate must be a positive number."));
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputFrameRate(double d) {
        setTargetFrameRate(d);
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputSize(Size size) {
        setTargetSize(size);
    }

    public void setTargetSize(Size size) {
        if (size != null) {
            this.__targetSize = size;
            return;
        }
        throw new RuntimeException(new Exception("Target size cannot be null."));
    }
}
