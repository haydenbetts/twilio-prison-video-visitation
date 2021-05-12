package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class VideoSink extends MediaSink<IVideoOutput, IVideoOutputCollection, IVideoInput, VideoSink, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> implements IVideoInput, IMediaInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    private int __lastProcessHeight = -1;
    private int __lastProcessWidth = -1;
    private double __maxInputFrameRate = -1.0d;
    private double __maxInputScale = -1.0d;
    private Size __maxInputSize = null;
    private double __minInputFrameRate = -1.0d;
    private double __minInputScale = -1.0d;
    private Size __minInputSize = null;
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onProcessSizeChange = new ArrayList();
    private VideoType __videoType = VideoType.Unknown;
    private IAction1<Size> _onProcessSizeChange = null;

    public void addOnProcessSizeChange(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessSizeChange == null) {
                this._onProcessSizeChange = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(VideoSink.this.__onProcessSizeChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(size);
                        }
                    }
                };
            }
            this.__onProcessSizeChange.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public IVideoOutputCollection createOutputCollection(IVideoInput iVideoInput) {
        return new IVideoOutputCollection(iVideoInput);
    }

    /* access modifiers changed from: protected */
    public void doPreProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        int width = videoBuffer.getWidth();
        int height = videoBuffer.getHeight();
        if (!(width == this.__lastProcessWidth && height == this.__lastProcessHeight)) {
            IAction1<Size> iAction1 = this._onProcessSizeChange;
            if (iAction1 != null) {
                iAction1.invoke(new Size(width, height));
            }
            this.__lastProcessWidth = width;
            this.__lastProcessHeight = height;
        }
        super.doPreProcessFrame(videoFrame, videoBuffer);
    }

    public EncodingInfo getMaxInputEncoding() {
        EncodingInfo maxInputEncoding = super.getMaxInputEncoding();
        if (maxInputEncoding != null) {
            maxInputEncoding.setScale(getMaxInputScale());
            maxInputEncoding.setFrameRate(getMaxInputFrameRate());
            maxInputEncoding.setSize(getMaxInputSize());
        }
        return maxInputEncoding;
    }

    public double getMaxInputFrameRate() {
        double d = this.__maxInputFrameRate;
        if (d == -1.0d) {
            for (IVideoOutput maxOutputFrameRate : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.min(d, maxOutputFrameRate.getMaxOutputFrameRate());
            }
        }
        return d;
    }

    public int getMaxInputHeight() {
        return ConstraintUtility.getHeight(getMaxInputSize());
    }

    public double getMaxInputScale() {
        double d = this.__maxInputScale;
        if (d == -1.0d) {
            for (IVideoOutput maxOutputScale : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.min(d, maxOutputScale.getMaxOutputScale());
            }
        }
        return d;
    }

    public Size getMaxInputSize() {
        Size size = this.__maxInputSize;
        if (size == null) {
            for (IVideoOutput maxOutputSize : (IVideoOutput[]) super.getInputs()) {
                size = ConstraintUtility.min(size, maxOutputSize.getMaxOutputSize());
            }
        }
        return size;
    }

    public int getMaxInputWidth() {
        return ConstraintUtility.getWidth(getMaxInputSize());
    }

    public EncodingInfo getMinInputEncoding() {
        EncodingInfo minInputEncoding = super.getMinInputEncoding();
        if (minInputEncoding != null) {
            minInputEncoding.setScale(getMinInputScale());
            minInputEncoding.setFrameRate(getMinInputFrameRate());
            minInputEncoding.setSize(getMinInputSize());
        }
        return minInputEncoding;
    }

    public double getMinInputFrameRate() {
        double d = this.__minInputFrameRate;
        if (d == -1.0d) {
            for (IVideoOutput minOutputFrameRate : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.max(d, minOutputFrameRate.getMinOutputFrameRate());
            }
        }
        return d;
    }

    public int getMinInputHeight() {
        return ConstraintUtility.getHeight(getMinInputSize());
    }

    public double getMinInputScale() {
        double d = this.__minInputScale;
        if (d == -1.0d) {
            for (IVideoOutput minOutputScale : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.max(d, minOutputScale.getMinOutputScale());
            }
        }
        return d;
    }

    public Size getMinInputSize() {
        Size size = this.__minInputSize;
        if (size == null) {
            for (IVideoOutput minOutputSize : (IVideoOutput[]) super.getInputs()) {
                size = ConstraintUtility.max(size, minOutputSize.getMinOutputSize());
            }
        }
        return size;
    }

    public int getMinInputWidth() {
        return ConstraintUtility.getWidth(getMinInputSize());
    }

    public boolean getOverConstrainedFrameRate() {
        return getOverConstrainedInputFrameRate();
    }

    public boolean getOverConstrainedHeight() {
        return getOverConstrainedInputHeight();
    }

    public boolean getOverConstrainedInput() {
        return super.getOverConstrainedInput() || getOverConstrainedInputScale() || getOverConstrainedInputFrameRate() || getOverConstrainedInputSize();
    }

    public boolean getOverConstrainedInputFrameRate() {
        return ConstraintUtility.overConstrained(getMinInputFrameRate(), getMaxInputFrameRate());
    }

    public boolean getOverConstrainedInputHeight() {
        return ConstraintUtility.overConstrained(getMinInputHeight(), getMaxInputHeight());
    }

    public boolean getOverConstrainedInputScale() {
        return ConstraintUtility.overConstrained(getMinInputScale(), getMaxInputScale());
    }

    public boolean getOverConstrainedInputSize() {
        return ConstraintUtility.overConstrained(getMinInputSize(), getMaxInputSize());
    }

    public boolean getOverConstrainedInputWidth() {
        return ConstraintUtility.overConstrained(getMinInputWidth(), getMaxInputWidth());
    }

    public boolean getOverConstrainedScale() {
        return getOverConstrainedInputScale();
    }

    public boolean getOverConstrainedSize() {
        return getOverConstrainedInputSize();
    }

    public boolean getOverConstrainedWidth() {
        return getOverConstrainedInputWidth();
    }

    public VideoType getVideoType() {
        VideoType videoType = this.__videoType;
        if (Global.equals(videoType, VideoType.Unknown)) {
            for (IVideoOutput videoType2 : (IVideoOutput[]) super.getInputs()) {
                videoType = videoType2.getVideoType();
                if (!Global.equals(videoType, VideoType.Unknown)) {
                    return videoType;
                }
            }
        }
        return videoType;
    }

    public void removeOnProcessSizeChange(IAction1<Size> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessSizeChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessSizeChange.remove(iAction1);
        if (this.__onProcessSizeChange.size() == 0) {
            this._onProcessSizeChange = null;
        }
    }

    /* access modifiers changed from: protected */
    public void setMaxInputEncoding(EncodingInfo encodingInfo) {
        super.setMaxInputEncoding(encodingInfo);
        if (encodingInfo == null) {
            setMaxInputScale(-1.0d);
            setMaxInputFrameRate(-1.0d);
            setMaxInputSize((Size) null);
            return;
        }
        setMaxInputScale(encodingInfo.getScale());
        setMaxInputFrameRate(encodingInfo.getFrameRate());
        setMaxInputSize(encodingInfo.getSize());
    }

    /* access modifiers changed from: protected */
    public void setMaxInputFrameRate(double d) {
        this.__maxInputFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMaxInputScale(double d) {
        this.__maxInputScale = d;
    }

    /* access modifiers changed from: protected */
    public void setMaxInputSize(Size size) {
        this.__maxInputSize = size;
    }

    /* access modifiers changed from: protected */
    public void setMinInputEncoding(EncodingInfo encodingInfo) {
        super.setMinInputEncoding(encodingInfo);
        if (encodingInfo == null) {
            setMinInputScale(-1.0d);
            setMinInputFrameRate(-1.0d);
            setMinInputSize((Size) null);
            return;
        }
        setMinInputScale(encodingInfo.getScale());
        setMinInputFrameRate(encodingInfo.getFrameRate());
        setMinInputSize(encodingInfo.getSize());
    }

    /* access modifiers changed from: protected */
    public void setMinInputFrameRate(double d) {
        this.__minInputFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMinInputScale(double d) {
        this.__minInputScale = d;
    }

    /* access modifiers changed from: protected */
    public void setMinInputSize(Size size) {
        this.__minInputSize = size;
    }

    /* access modifiers changed from: protected */
    public void setVideoType(VideoType videoType) {
        this.__videoType = videoType;
    }

    public VideoSink() {
    }

    public VideoSink(VideoFormat videoFormat) {
        super(videoFormat);
    }
}
