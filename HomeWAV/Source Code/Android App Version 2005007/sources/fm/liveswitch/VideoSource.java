package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class VideoSource extends MediaSource<IVideoOutput, IVideoInput, IVideoInputCollection, VideoSource, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat> implements IVideoOutput, IMediaOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IOutput<IVideoOutput, IVideoInput, VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat>, IVideoElement, IMediaElement, IElement {
    private long __baseSystemTimestamp = -1;
    private long __baseTimestamp = -1;
    private int __lastRaiseHeight = -1;
    private int __lastRaiseWidth = -1;
    private double __maxOutputFrameRate = -1.0d;
    private double __maxOutputScale = -1.0d;
    private Size __maxOutputSize = null;
    private double __minOutputFrameRate = -1.0d;
    private double __minOutputScale = -1.0d;
    private Size __minOutputSize = null;
    /* access modifiers changed from: private */
    public List<IAction1<Size>> __onRaiseSizeChange = new ArrayList();
    private double __targetOutputFrameRate = -1.0d;
    private double __targetOutputScale = -1.0d;
    private Size __targetOutputSize = null;
    private VideoType __videoType = VideoType.Unknown;
    private long _frameCount;
    private IAction1<Size> _onRaiseSizeChange = null;

    public void addOnRaiseSizeChange(IAction1<Size> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseSizeChange == null) {
                this._onRaiseSizeChange = new IAction1<Size>() {
                    public void invoke(Size size) {
                        Iterator it = new ArrayList(VideoSource.this.__onRaiseSizeChange).iterator();
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
    public IVideoInputCollection createInputCollection(IVideoOutput iVideoOutput) {
        return new IVideoInputCollection(iVideoOutput);
    }

    public int getAverageFrameRate() {
        if (getFrameCount() == 0 || this.__baseSystemTimestamp == -1) {
            return 0;
        }
        long timestamp = ManagedStopwatch.getTimestamp() - this.__baseSystemTimestamp;
        if (timestamp < ((long) Constants.getTicksPerSecond())) {
            return -1;
        }
        return (int) ((getFrameCount() * ((long) Constants.getTicksPerSecond())) / timestamp);
    }

    public long getFrameCount() {
        return this._frameCount;
    }

    public double getFrameRateDistance(double d, double d2) {
        if (d2 > 0.0d) {
            return MathAssistant.abs(d2 - d);
        }
        return -1.0d;
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
        return this.__maxOutputFrameRate;
    }

    public int getMaxOutputHeight() {
        return ConstraintUtility.getHeight(getMaxOutputSize());
    }

    public double getMaxOutputScale() {
        return this.__maxOutputScale;
    }

    public Size getMaxOutputSize() {
        return this.__maxOutputSize;
    }

    public int getMaxOutputWidth() {
        return ConstraintUtility.getWidth(getMaxOutputSize());
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
        return this.__minOutputFrameRate;
    }

    public int getMinOutputHeight() {
        return ConstraintUtility.getHeight(getMinOutputSize());
    }

    public double getMinOutputScale() {
        return this.__minOutputScale;
    }

    public Size getMinOutputSize() {
        return this.__minOutputSize;
    }

    public int getMinOutputWidth() {
        return ConstraintUtility.getWidth(getMinOutputSize());
    }

    public boolean getOverConstrainedFrameRate() {
        return getOverConstrainedOutputFrameRate();
    }

    public boolean getOverConstrainedHeight() {
        return getOverConstrainedOutputHeight();
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
        return getOverConstrainedOutputScale();
    }

    public boolean getOverConstrainedSize() {
        return getOverConstrainedOutputSize();
    }

    public boolean getOverConstrainedWidth() {
        return getOverConstrainedOutputWidth();
    }

    public int getSizeDistance(int i, int i2, int i3, int i4) {
        if (i3 > 0 && i4 > 0) {
            return MathAssistant.abs(i3 - i) + MathAssistant.abs(i4 - i2);
        }
        if (i3 <= 0) {
            return MathAssistant.abs(i4 - i2);
        }
        if (i4 <= 0) {
            return MathAssistant.abs(i3 - i);
        }
        return -1;
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
        return ConstraintUtility.clampMin(this.__targetOutputFrameRate, getMinOutputFrameRate(), getMaxOutputFrameRate());
    }

    public int getTargetOutputHeight() {
        return ConstraintUtility.getHeight(getTargetOutputSize());
    }

    public double getTargetOutputScale() {
        return ConstraintUtility.clampMin(this.__targetOutputScale, getMinOutputScale(), getMaxOutputScale());
    }

    public Size getTargetOutputSize() {
        return ConstraintUtility.clampMin(this.__targetOutputSize, getMinOutputSize(), getMaxOutputSize());
    }

    public int getTargetOutputWidth() {
        return ConstraintUtility.getWidth(getTargetOutputSize());
    }

    public VideoType getVideoType() {
        return this.__videoType;
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
        setFrameCount(getFrameCount() + 1);
        if (videoFrame.getTimestamp() != -1 && videoFrame.getSystemTimestamp() == -1) {
            if (this.__baseSystemTimestamp == -1) {
                this.__baseSystemTimestamp = ManagedStopwatch.getTimestamp();
                this.__baseTimestamp = videoFrame.getTimestamp();
            }
            videoFrame.setSystemTimestamp(MediaFrame.calculateSystemTimestamp(this.__baseSystemTimestamp, videoFrame.getTimestamp(), ((VideoFormat) super.getOutputFormat()).getClockRate(), this.__baseTimestamp));
        }
        if (videoFrame.getSystemTimestamp() != -1 && videoFrame.getTimestamp() == -1) {
            if (this.__baseSystemTimestamp == -1) {
                this.__baseSystemTimestamp = videoFrame.getSystemTimestamp();
            }
            videoFrame.setTimestamp(MediaFrame.calculateTimestamp(this.__baseSystemTimestamp, videoFrame.getSystemTimestamp(), ((VideoFormat) super.getOutputFormat()).getClockRate()));
        }
        if (videoFrame.getTimestamp() == -1 && videoFrame.getSystemTimestamp() == -1) {
            long timestamp = ManagedStopwatch.getTimestamp();
            if (this.__baseSystemTimestamp == -1) {
                this.__baseSystemTimestamp = timestamp;
            }
            videoFrame.setSystemTimestamp(timestamp);
            videoFrame.setTimestamp(MediaFrame.calculateTimestamp(this.__baseSystemTimestamp, timestamp, ((VideoFormat) super.getOutputFormat()).getClockRate()));
        }
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

    private void setFrameCount(long j) {
        this._frameCount = j;
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

    public VideoSource(VideoFormat videoFormat) {
        super(videoFormat);
        super.setOutputSynchronizable(true);
    }
}
