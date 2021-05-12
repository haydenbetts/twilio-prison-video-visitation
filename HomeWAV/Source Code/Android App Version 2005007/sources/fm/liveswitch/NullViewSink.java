package fm.liveswitch;

public class NullViewSink<T> extends ViewSink<T> {
    private long __frameCount = 0;
    private Object __frameCountLock = new Object();
    private Promise<Object> __renderedFramesPromise = null;
    private long __renderedFramesPromiseResolveCount = 0;
    private T __view = null;
    private boolean _viewMirror;
    private LayoutScale _viewScale;

    public String getLabel() {
        return "Null View Sink";
    }

    public long getFrameCount() {
        return this.__frameCount;
    }

    public T getView() {
        return this.__view;
    }

    public boolean getViewMirror() {
        return this._viewMirror;
    }

    public LayoutScale getViewScale() {
        return this._viewScale;
    }

    public NullViewSink() {
    }

    public NullViewSink(VideoFormat videoFormat) {
        super(videoFormat);
    }

    /* access modifiers changed from: protected */
    public void renderBuffer(VideoBuffer videoBuffer) {
        synchronized (this.__frameCountLock) {
            this.__frameCount++;
            renderBufferWrapper(this.__renderedFramesPromise);
        }
    }

    private void renderBufferWrapper(final Promise<Object> promise) {
        if (promise != null && this.__frameCount >= this.__renderedFramesPromiseResolveCount) {
            this.__renderedFramesPromise = null;
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    promise.resolve(null);
                }
            });
        }
    }

    public Future<Object> renderedFrames(long j) {
        Promise<Object> promise = new Promise<>();
        try {
            synchronized (this.__frameCountLock) {
                Promise<Object> promise2 = this.__renderedFramesPromise;
                if (promise2 != null) {
                    this.__renderedFramesPromise = null;
                    promise2.reject(new Exception("Another call to RenderedFrames has superceded this one."));
                }
                if (this.__frameCount >= j) {
                    promise.resolve(null);
                    return promise;
                }
                this.__renderedFramesPromiseResolveCount = j;
                this.__renderedFramesPromise = promise;
                return promise;
            }
        } catch (Exception e) {
            promise.reject(e);
            return promise;
        }
    }

    public void setViewMirror(boolean z) {
        this._viewMirror = z;
    }

    public void setViewScale(LayoutScale layoutScale) {
        this._viewScale = layoutScale;
    }

    public void updateMaxInputBitrate(int i) {
        setMaxInputBitrate(i);
    }

    public void updateMinInputBitrate(int i) {
        setMinInputBitrate(i);
    }
}
