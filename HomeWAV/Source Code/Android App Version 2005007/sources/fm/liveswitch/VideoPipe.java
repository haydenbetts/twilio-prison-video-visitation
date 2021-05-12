package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class VideoPipe extends MediaPipe<IVideoOutput, IVideoOutputCollection, IVideoInput, IVideoInputCollection, VideoPipe, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> implements IVideoInput, IMediaInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IInput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoOutput, IMediaOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    private int __lastProcessHeight = -1;
    private int __lastProcessWidth = -1;
    private int __lastRaiseHeight = -1;
    private int __lastRaiseWidth = -1;
    private double __maxInputFrameRate = -1.0d;
    private double __maxInputScale = -1.0d;
    private Size __maxInputSize = null;
    private double __maxOutputFrameRate = -1.0d;
    private double __maxOutputScale = -1.0d;
    private Size __maxOutputSize = null;
    private double __minInputFrameRate = -1.0d;
    private double __minInputScale = -1.0d;
    private Size __minInputSize = null;
    private double __minOutputFrameRate = -1.0d;
    private double __minOutputScale = -1.0d;
    private Size __minOutputSize = null;
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onProcessSizeChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onRaiseSizeChange = new ArrayList();
    private double __targetOutputFrameRate = -1.0d;
    private double __targetOutputScale = -1.0d;
    private Size __targetOutputSize = null;
    private VideoType __videoType = VideoType.Unknown;
    private IAction1<Size> _onProcessSizeChange = null;
    private IAction1<Size> _onRaiseSizeChange = null;

    public void addOnProcessSizeChange(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessSizeChange == null) {
                this._onProcessSizeChange = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(VideoPipe.this.__onProcessSizeChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(size);
                        }
                    }
                };
            }
            this.__onProcessSizeChange.add(iAction1);
        }
    }

    public void addOnRaiseSizeChange(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseSizeChange == null) {
                this._onRaiseSizeChange = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(VideoPipe.this.__onRaiseSizeChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(size);
                        }
                    }
                };
            }
            this.__onRaiseSizeChange.add(iAction1);
        }
    }

    /* access modifiers changed from: protected */
    public VideoFrame createFrame(VideoBuffer videoBuffer) {
        return new VideoFrame(videoBuffer);
    }

    /* access modifiers changed from: protected */
    public IVideoInputCollection createInputCollection(IVideoOutput iVideoOutput) {
        return new IVideoInputCollection(iVideoOutput);
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

    public EncodingInfo getMaxOutputEncoding() {
        EncodingInfo maxOutputEncoding = super.getMaxOutputEncoding();
        if (maxOutputEncoding != null) {
            maxOutputEncoding.setScale(getMaxOutputScale());
            maxOutputEncoding.setFrameRate(getMaxOutputFrameRate());
            maxOutputEncoding.setSize(getMaxOutputSize());
        }
        return maxOutputEncoding;
    }

    public double getMaxOutputFrameRate() {
        double d = this.__maxOutputFrameRate;
        return d == -1.0d ? getMaxInputFrameRate() : d;
    }

    public int getMaxOutputHeight() {
        return ConstraintUtility.getHeight(getMaxOutputSize());
    }

    public double getMaxOutputScale() {
        double d = this.__maxOutputScale;
        return d == -1.0d ? getMaxInputScale() : d;
    }

    public Size getMaxOutputSize() {
        Size size = this.__maxOutputSize;
        return size == null ? getMaxInputSize() : size;
    }

    public int getMaxOutputWidth() {
        return ConstraintUtility.getWidth(getMaxOutputSize());
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

    public EncodingInfo getMinOutputEncoding() {
        EncodingInfo minOutputEncoding = super.getMinOutputEncoding();
        if (minOutputEncoding != null) {
            minOutputEncoding.setScale(getMinOutputScale());
            minOutputEncoding.setFrameRate(getMinOutputFrameRate());
            minOutputEncoding.setSize(getMinOutputSize());
        }
        return minOutputEncoding;
    }

    public double getMinOutputFrameRate() {
        double d = this.__minOutputFrameRate;
        return d == -1.0d ? getMinInputFrameRate() : d;
    }

    public int getMinOutputHeight() {
        return ConstraintUtility.getHeight(getMinOutputSize());
    }

    public double getMinOutputScale() {
        double d = this.__minOutputScale;
        return d == -1.0d ? getMinInputScale() : d;
    }

    public Size getMinOutputSize() {
        Size size = this.__minOutputSize;
        return size == null ? getMinInputSize() : size;
    }

    public int getMinOutputWidth() {
        return ConstraintUtility.getWidth(getMinOutputSize());
    }

    public boolean getOverConstrainedFrameRate() {
        return getOverConstrainedInputFrameRate() || getOverConstrainedOutputFrameRate();
    }

    public boolean getOverConstrainedHeight() {
        return getOverConstrainedInputHeight() || getOverConstrainedOutputHeight();
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

    public boolean getOverConstrainedOutput() {
        return super.getOverConstrainedOutput() || getOverConstrainedOutputScale() || getOverConstrainedOutputFrameRate() || getOverConstrainedOutputSize();
    }

    public boolean getOverConstrainedOutputFrameRate() {
        return ConstraintUtility.overConstrained(getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public boolean getOverConstrainedOutputHeight() {
        return ConstraintUtility.overConstrained(getMinOutputHeight(), getMaxOutputHeight());
    }

    public boolean getOverConstrainedOutputScale() {
        return ConstraintUtility.overConstrained(getMinOutputScale(), getMaxOutputScale());
    }

    public boolean getOverConstrainedOutputSize() {
        return ConstraintUtility.overConstrained(getMinOutputSize(), getMaxOutputSize());
    }

    public boolean getOverConstrainedOutputWidth() {
        return ConstraintUtility.overConstrained(getMinOutputWidth(), getMaxOutputWidth());
    }

    public boolean getOverConstrainedScale() {
        return getOverConstrainedInputScale() || getOverConstrainedOutputScale();
    }

    public boolean getOverConstrainedSize() {
        return getOverConstrainedInputSize() || getOverConstrainedOutputSize();
    }

    public boolean getOverConstrainedWidth() {
        return getOverConstrainedInputWidth() || getOverConstrainedOutputWidth();
    }

    public EncodingInfo getTargetOutputEncoding() {
        EncodingInfo targetOutputEncoding = super.getTargetOutputEncoding();
        if (targetOutputEncoding != null) {
            targetOutputEncoding.setScale(getTargetOutputScale());
            targetOutputEncoding.setFrameRate(getTargetOutputFrameRate());
            targetOutputEncoding.setSize(getTargetOutputSize());
        }
        return targetOutputEncoding;
    }

    public double getTargetOutputFrameRate() {
        double d = this.__targetOutputFrameRate;
        if (d == -1.0d) {
            for (IVideoOutput targetOutputFrameRate : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.max(d, targetOutputFrameRate.getTargetOutputFrameRate());
            }
        }
        return ConstraintUtility.clampMin(d, getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public int getTargetOutputHeight() {
        return ConstraintUtility.getHeight(getTargetOutputSize());
    }

    public double getTargetOutputScale() {
        double d = this.__targetOutputScale;
        if (d == -1.0d) {
            for (IVideoOutput targetOutputScale : (IVideoOutput[]) super.getInputs()) {
                d = ConstraintUtility.max(d, targetOutputScale.getTargetOutputScale());
            }
        }
        return ConstraintUtility.clampMin(d, getMinOutputScale(), getMaxOutputScale());
    }

    public Size getTargetOutputSize() {
        Size size = this.__targetOutputSize;
        if (size == null) {
            for (IVideoOutput targetOutputSize : (IVideoOutput[]) super.getInputs()) {
                size = ConstraintUtility.max(size, targetOutputSize.getTargetOutputSize());
            }
        }
        return ConstraintUtility.clampMin(size, getMinOutputSize(), getMaxOutputSize());
    }

    public int getTargetOutputWidth() {
        return ConstraintUtility.getWidth(getTargetOutputSize());
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

    /* access modifiers changed from: protected */
    public boolean outputCanProcessFrame(IVideoInput iVideoInput) {
        if (!super.outputCanProcessFrame(iVideoInput)) {
            return false;
        }
        double maxOutputScale = getMaxOutputScale();
        double minInputScale = iVideoInput.getMinInputScale();
        if (minInputScale != -1.0d && maxOutputScale != -1.0d && minInputScale > maxOutputScale) {
            return false;
        }
        double minOutputScale = getMinOutputScale();
        double maxInputScale = iVideoInput.getMaxInputScale();
        if (maxInputScale != -1.0d && minOutputScale != -1.0d && maxInputScale < minOutputScale) {
            return false;
        }
        double maxOutputFrameRate = getMaxOutputFrameRate();
        double minInputFrameRate = iVideoInput.getMinInputFrameRate();
        if (minInputFrameRate != -1.0d && maxOutputFrameRate != -1.0d && minInputFrameRate > maxOutputFrameRate) {
            return false;
        }
        double minOutputFrameRate = getMinOutputFrameRate();
        double maxInputFrameRate = iVideoInput.getMaxInputFrameRate();
        if (maxInputFrameRate != -1.0d && minOutputFrameRate != -1.0d && maxInputFrameRate < minOutputFrameRate) {
            return false;
        }
        Size maxOutputSize = getMaxOutputSize();
        Size minInputSize = iVideoInput.getMinInputSize();
        if (!(minInputSize == null || maxOutputSize == null)) {
            if (minInputSize.getWidth() != -1 && maxOutputSize.getWidth() != -1 && minInputSize.getWidth() > maxOutputSize.getWidth()) {
                return false;
            }
            if (!(minInputSize.getHeight() == -1 || maxOutputSize.getHeight() == -1 || minInputSize.getHeight() <= maxOutputSize.getHeight())) {
                return false;
            }
        }
        Size minOutputSize = getMinOutputSize();
        Size maxInputSize = iVideoInput.getMaxInputSize();
        if (maxInputSize == null || minOutputSize == null) {
            return true;
        }
        if (maxInputSize.getWidth() != -1 && minOutputSize.getWidth() != -1 && maxInputSize.getWidth() < minOutputSize.getWidth()) {
            return false;
        }
        if (maxInputSize.getHeight() == -1 || minOutputSize.getHeight() == -1 || maxInputSize.getHeight() >= minOutputSize.getHeight()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(VideoFrame videoFrame) {
        VideoBuffer videoBuffer = (VideoBuffer) videoFrame.getLastBuffer();
        if (videoBuffer != null) {
            int width = videoBuffer.getWidth();
            int height = videoBuffer.getHeight();
            if (!(width == this.__lastRaiseWidth && height == this.__lastRaiseHeight)) {
                IAction1<Size> iAction1 = this._onRaiseSizeChange;
                if (iAction1 != null) {
                    iAction1.invoke(new Size(width, height));
                }
                this.__lastRaiseWidth = width;
                this.__lastRaiseHeight = height;
            }
        }
        super.raiseFrame(videoFrame);
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

    public void removeOnRaiseSizeChange(IAction1<Size> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseSizeChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseSizeChange.remove(iAction1);
        if (this.__onRaiseSizeChange.size() == 0) {
            this._onRaiseSizeChange = null;
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
    public void setMaxOutputEncoding(EncodingInfo encodingInfo) {
        super.setMaxOutputEncoding(encodingInfo);
        if (encodingInfo == null) {
            setMaxOutputScale(-1.0d);
            setMaxOutputFrameRate(-1.0d);
            setMaxOutputSize((Size) null);
            return;
        }
        setMaxOutputScale(encodingInfo.getScale());
        setMaxOutputFrameRate(encodingInfo.getFrameRate());
        setMaxOutputSize(encodingInfo.getSize());
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputFrameRate(double d) {
        this.__maxOutputFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputScale(double d) {
        this.__maxOutputScale = d;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputSize(Size size) {
        this.__maxOutputSize = size;
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
    public void setMinOutputEncoding(EncodingInfo encodingInfo) {
        super.setMinOutputEncoding(encodingInfo);
        if (encodingInfo == null) {
            setMinOutputScale(-1.0d);
            setMinOutputFrameRate(-1.0d);
            setMinOutputSize((Size) null);
            return;
        }
        setMinOutputScale(encodingInfo.getScale());
        setMinOutputFrameRate(encodingInfo.getFrameRate());
        setMinOutputSize(encodingInfo.getSize());
    }

    /* access modifiers changed from: protected */
    public void setMinOutputFrameRate(double d) {
        this.__minOutputFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputScale(double d) {
        this.__minOutputScale = d;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputSize(Size size) {
        this.__minOutputSize = size;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputEncoding(EncodingInfo encodingInfo) {
        super.setTargetOutputEncoding(encodingInfo);
        if (encodingInfo == null) {
            setTargetOutputScale(-1.0d);
            setTargetOutputFrameRate(-1.0d);
            setTargetOutputSize((Size) null);
            return;
        }
        setTargetOutputScale(encodingInfo.getScale());
        setTargetOutputFrameRate(encodingInfo.getFrameRate());
        setTargetOutputSize(encodingInfo.getSize());
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputFrameRate(double d) {
        this.__targetOutputFrameRate = d;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputScale(double d) {
        this.__targetOutputScale = d;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputSize(Size size) {
        this.__targetOutputSize = size;
    }

    /* access modifiers changed from: protected */
    public void setVideoType(VideoType videoType) {
        this.__videoType = videoType;
    }

    public VideoPipe(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
    }

    public VideoPipe(VideoFormat videoFormat) {
        super(null, videoFormat);
    }
}
