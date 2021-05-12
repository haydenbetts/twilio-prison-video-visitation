package fm.liveswitch;

public abstract class ViewSink<T> extends VideoSink implements IViewSink<T> {
    private ManagedStopwatch __stopwatch;
    private boolean _isRecording;

    public abstract T getView();

    public abstract boolean getViewMirror();

    public abstract LayoutScale getViewScale();

    /* access modifiers changed from: protected */
    public abstract void renderBuffer(VideoBuffer videoBuffer);

    public abstract void setViewMirror(boolean z);

    public abstract void setViewScale(LayoutScale layoutScale);

    /* access modifiers changed from: protected */
    public void doDestroy() {
        ManagedStopwatch managedStopwatch = this.__stopwatch;
        if (managedStopwatch != null) {
            managedStopwatch.stop();
            this.__stopwatch = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        if (this.__stopwatch == null) {
            ManagedStopwatch managedStopwatch = new ManagedStopwatch();
            this.__stopwatch = managedStopwatch;
            managedStopwatch.start();
        }
        renderBuffer(videoBuffer);
    }

    public boolean getIsRecording() {
        return this._isRecording;
    }

    public void setIsRecording(boolean z) {
        this._isRecording = z;
    }

    public ViewSink() {
    }

    public ViewSink(IVideoOutput iVideoOutput) {
        super((VideoFormat) iVideoOutput.getOutputFormat());
        super.addInput(iVideoOutput);
    }

    public ViewSink(VideoFormat videoFormat) {
        super(videoFormat);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewSink(IVideoOutput[] iVideoOutputArr) {
        super((iVideoOutputArr == null || ArrayExtensions.getLength((Object[]) iVideoOutputArr) == 0) ? null : (VideoFormat) iVideoOutputArr[0].getOutputFormat());
        super.addInputs((TIOutput[]) iVideoOutputArr);
    }
}
