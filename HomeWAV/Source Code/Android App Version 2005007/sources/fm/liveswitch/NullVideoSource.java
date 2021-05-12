package fm.liveswitch;

public class NullVideoSource extends VideoSource {
    private boolean __canChangeBitrate = false;
    private boolean __canPauseBitrate = false;
    private Size __empty = Size.getEmpty();
    private double __frameRate = -1.0d;
    private String __inputRtpStreamId = null;
    private long __inputSynchronizationSource = -1;
    private double __maxFrameRate = -1.0d;
    private Size __maxSize = null;
    private double __minFrameRate = -1.0d;
    private Size __minSize = null;
    private Size __size = null;
    private double __targetFrameRate = -1.0d;
    private Size __targetSize = null;
    private boolean _staticOutputFrameRate;
    private boolean _staticOutputSize;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public double getMaxSupportedFrameRate() {
        return -1.0d;
    }

    public Size getMaxSupportedSize() {
        return null;
    }

    public double getMinSupportedFrameRate() {
        return 0.0d;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        return PromiseBase.resolveNow();
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        return PromiseBase.resolveNow();
    }

    public void generateAndProcessRawFrame() {
        if (((VideoFormat) super.getOutputFormat()).getIsCompressed()) {
            throw new RuntimeException(new Exception("Cannot generate a raw frame. Output format is compressed."));
        } else if (getConfig() != null) {
            processFrame(VideoFrame.generateFrame(getConfig().getWidth(), getConfig().getHeight(), ((VideoFormat) super.getOutputFormat()).getName()));
        } else {
            throw new RuntimeException(new Exception("Cannot generate a raw frame. Config has not been set."));
        }
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return this.__canChangeBitrate;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return this.__canPauseBitrate;
    }

    public VideoConfig getConfig() {
        if (getSize() == null || getFrameRate() == -1.0d) {
            return null;
        }
        return new VideoConfig(getSize(), getFrameRate());
    }

    public double getFrameRate() {
        return ConstraintUtility.clampMin(ConstraintUtility.coalesce(this.__frameRate, this.__targetFrameRate), getMinSupportedFrameRate(), getMaxSupportedFrameRate());
    }

    public String getInputRtpStreamId() {
        return this.__inputRtpStreamId;
    }

    public long getInputSynchronizationSource() {
        return this.__inputSynchronizationSource;
    }

    public String getLabel() {
        return StringExtensions.format("Null Video Source ({0})", (Object) ((VideoFormat) super.getOutputFormat()).getFullName());
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

    public NullVideoSource(VideoFormat videoFormat) {
        super(videoFormat);
    }

    public NullVideoSource(VideoFormat videoFormat, VideoConfig videoConfig) {
        super(videoFormat);
        setTargetConfig(videoConfig);
    }

    public void processControlFrameResponse(MediaControlFrame mediaControlFrame) {
        super.raiseControlFrameResponse(mediaControlFrame);
    }

    public void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        super.raiseControlFrameResponses(mediaControlFrameArr);
    }

    public void processFrame(VideoFrame videoFrame) {
        raiseFrame(videoFrame);
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

    public void setInputRtpStreamId(String str) {
        this.__inputRtpStreamId = str;
    }

    public void setInputSynchronizationSource(long j) {
        this.__inputSynchronizationSource = j;
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
        if (videoConfig == null) {
            setMaxSize((Size) null);
            setTargetSize((Size) null);
            setMaxFrameRate(-1.0d);
            setTargetFrameRate(-1.0d);
            return;
        }
        setMaxSize(videoConfig.getSize());
        setTargetSize(videoConfig.getSize());
        setMaxFrameRate(videoConfig.getFrameRate());
        setTargetFrameRate(videoConfig.getFrameRate());
    }

    /* access modifiers changed from: protected */
    public void setTargetFrameRate(double d) {
        this.__targetFrameRate = d;
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
        this.__targetSize = size;
    }

    public void updateCanChangeBitrate(boolean z) {
        this.__canChangeBitrate = z;
    }

    public void updateCanPauseBitrate(boolean z) {
        this.__canPauseBitrate = z;
    }

    public void updateMaxOutputBitrate(int i) {
        setMaxOutputBitrate(i);
    }

    public void updateMaxOutputEncoding(EncodingInfo encodingInfo) {
        setMaxOutputEncoding(encodingInfo);
    }

    public void updateMaxOutputFrameRate(double d) {
        setMaxOutputFrameRate(d);
    }

    public void updateMaxOutputScale(double d) {
        setMaxOutputScale(d);
    }

    public void updateMaxOutputSize(Size size) {
        setMaxOutputSize(size);
    }

    public void updateMinOutputBitrate(int i) {
        setMinOutputBitrate(i);
    }

    public void updateMinOutputEncoding(EncodingInfo encodingInfo) {
        setMinOutputEncoding(encodingInfo);
    }

    public void updateMinOutputFrameRate(double d) {
        setMinOutputFrameRate(d);
    }

    public void updateMinOutputScale(double d) {
        setMinOutputScale(d);
    }

    public void updateMinOutputSize(Size size) {
        setMinOutputSize(size);
    }

    public void updateOutputSynchronizable(boolean z) {
        super.setOutputSynchronizable(z);
    }

    public void updateSystemDelay(long j) {
        super.setSystemDelay(j);
    }

    public void updateTargetOutputBitrate(int i) {
        setTargetOutputBitrate(i);
    }

    public void updateTargetOutputEncoding(EncodingInfo encodingInfo) {
        setTargetOutputEncoding(encodingInfo);
    }

    public void updateTargetOutputFrameRate(double d) {
        setTargetOutputFrameRate(d);
    }

    public void updateTargetOutputScale(double d) {
        setTargetOutputScale(d);
    }

    public void updateTargetOutputSize(Size size) {
        setTargetOutputSize(size);
    }

    public void updateVideoType(VideoType videoType) {
        setVideoType(videoType);
    }
}
