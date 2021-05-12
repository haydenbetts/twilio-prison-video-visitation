package fm.liveswitch;

public class IdentityVideoPipe extends VideoPipe {
    private boolean __canChangeBitrate = false;
    private boolean __canPauseBitrate = false;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        raiseFrame(videoFrame);
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return this.__canChangeBitrate;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return this.__canPauseBitrate;
    }

    public String getLabel() {
        return StringExtensions.format("Identity Video Pipe ({0})", (Object) ((VideoFormat) super.getOutputFormat()).getFullName());
    }

    public IdentityVideoPipe(VideoFormat videoFormat) {
        super(videoFormat.clone(), videoFormat.clone());
    }

    public void updateCanChangeBitrate(boolean z) {
        this.__canChangeBitrate = z;
    }

    public void updateCanPauseBitrate(boolean z) {
        this.__canPauseBitrate = z;
    }

    public void updateMaxInputBitrate(int i) {
        setMaxInputBitrate(i);
    }

    public void updateMaxInputEncoding(EncodingInfo encodingInfo) {
        setMaxInputEncoding(encodingInfo);
    }

    public void updateMaxInputFrameRate(double d) {
        setMaxInputFrameRate(d);
    }

    public void updateMaxInputScale(double d) {
        setMaxInputScale(d);
    }

    public void updateMaxInputSize(Size size) {
        setMaxInputSize(size);
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

    public void updateMinInputBitrate(int i) {
        setMinInputBitrate(i);
    }

    public void updateMinInputEncoding(EncodingInfo encodingInfo) {
        setMinInputEncoding(encodingInfo);
    }

    public void updateMinInputFrameRate(double d) {
        setMinInputFrameRate(d);
    }

    public void updateMinInputScale(double d) {
        setMinInputScale(d);
    }

    public void updateMinInputSize(Size size) {
        setMinInputSize(size);
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
