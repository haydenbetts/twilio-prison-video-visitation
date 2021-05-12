package fm.liveswitch;

public abstract class ScreenSourceBase extends VideoSource {
    private Size __empty = Size.getEmpty();
    private double __frameRate = -1.0d;
    private double __maxFrameRate = -1.0d;
    private Size __maxSize = null;
    private double __minFrameRate = -1.0d;
    private Size __minSize = null;
    private Point __origin;
    private Size __size = null;
    private double __targetFrameRate = -1.0d;
    private Point __targetOrigin = null;
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

    public ScreenConfig getConfig() {
        return new ScreenConfig(getRegion(), getFrameRate());
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

    public Point getOrigin() {
        Point point = this.__origin;
        return point != null ? point : this.__targetOrigin;
    }

    public Rectangle getRegion() {
        return new Rectangle(getOrigin(), getSize());
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

    public ScreenConfig getTargetConfig() {
        return new ScreenConfig(getTargetRegion(), getTargetFrameRate());
    }

    public double getTargetFrameRate() {
        return ConstraintUtility.clampMin(this.__targetFrameRate, getMinSupportedFrameRate(), getMaxSupportedFrameRate());
    }

    public Point getTargetOrigin() {
        return this.__targetOrigin;
    }

    public double getTargetOutputFrameRate() {
        return getTargetFrameRate();
    }

    public Size getTargetOutputSize() {
        return getTargetSize();
    }

    public Rectangle getTargetRegion() {
        return new Rectangle(getTargetOrigin(), getTargetSize());
    }

    public Size getTargetSize() {
        return ConstraintUtility.clampMin(this.__targetSize, getMinOutputSize(), getMaxOutputSize());
    }

    public ScreenSourceBase(VideoFormat videoFormat, ScreenConfig screenConfig) {
        super(videoFormat);
        setTargetConfig(screenConfig);
        setVideoType(VideoType.Screen);
    }

    /* access modifiers changed from: protected */
    public void setConfig(ScreenConfig screenConfig) {
        if (screenConfig == null) {
            setRegion((Rectangle) null);
            setFrameRate(-1.0d);
            return;
        }
        setRegion(screenConfig.getRegion());
        setFrameRate(screenConfig.getFrameRate());
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
    public void setOrigin(Point point) {
        this.__origin = point;
    }

    /* access modifiers changed from: protected */
    public void setRegion(Rectangle rectangle) {
        if (rectangle == null) {
            setOrigin((Point) null);
            setSize((Size) null);
            return;
        }
        setOrigin(rectangle.getOrigin());
        setSize(rectangle.getSize());
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

    public void setTargetConfig(ScreenConfig screenConfig) {
        if (screenConfig != null) {
            setTargetRegion(screenConfig.getRegion());
            setTargetFrameRate(screenConfig.getFrameRate());
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

    public void setTargetOrigin(Point point) {
        if (point != null) {
            this.__targetOrigin = point;
            return;
        }
        throw new RuntimeException(new Exception("Target origin cannot be null."));
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputFrameRate(double d) {
        setTargetFrameRate(d);
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputSize(Size size) {
        setTargetSize(size);
    }

    public void setTargetRegion(Rectangle rectangle) {
        if (rectangle != null) {
            setTargetOrigin(rectangle.getOrigin());
            setTargetSize(rectangle.getSize());
            return;
        }
        throw new RuntimeException(new Exception("Target region cannot be null."));
    }

    public void setTargetSize(Size size) {
        if (size != null) {
            this.__targetSize = size;
            return;
        }
        throw new RuntimeException(new Exception("Target size cannot be null."));
    }
}
