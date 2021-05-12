package fm.liveswitch;

public class NullAudioSink extends AudioSink {
    private long __frameCount = 0;
    private Object __frameCountLock = new Object();
    private String __outputRtpStreamId = null;
    private long __outputSynchronizationSource = -1;
    private Promise<Object> __processedFramesPromise = null;
    private long __processedFramesPromiseResolveCount = 0;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        synchronized (this.__frameCountLock) {
            this.__frameCount++;
            doProcessFrameWrapper(this.__processedFramesPromise);
        }
    }

    private void doProcessFrameWrapper(final Promise<Object> promise) {
        if (promise != null && this.__frameCount >= this.__processedFramesPromiseResolveCount) {
            this.__processedFramesPromise = null;
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    promise.resolve(null);
                }
            });
        }
    }

    public long getFrameCount() {
        return this.__frameCount;
    }

    public String getLabel() {
        return StringExtensions.format("Null Audio Sink ({0})", (Object) ((AudioFormat) super.getInputFormat()).getFullName());
    }

    public String getOutputRtpStreamId() {
        String str = this.__outputRtpStreamId;
        if (str != null) {
            return str;
        }
        return super.getInputRtpStreamId();
    }

    public long getOutputSynchronizationSource() {
        long j = this.__outputSynchronizationSource;
        if (j != -1) {
            return j;
        }
        return super.getInputSynchronizationSource();
    }

    public NullAudioSink() {
    }

    public NullAudioSink(AudioFormat audioFormat) {
        super(audioFormat);
    }

    public void processControlFrame(MediaControlFrame mediaControlFrame) {
        super.raiseControlFrame(mediaControlFrame);
    }

    public void processControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        super.raiseControlFrames(mediaControlFrameArr);
    }

    public Future<Object> processedFrames(long j) {
        Promise<Object> promise = new Promise<>();
        try {
            synchronized (this.__frameCountLock) {
                Promise<Object> promise2 = this.__processedFramesPromise;
                if (promise2 != null) {
                    this.__processedFramesPromise = null;
                    promise2.reject(new Exception("Another call to ProcessedFrames has superceded this one."));
                }
                if (this.__frameCount >= j) {
                    promise.resolve(null);
                    return promise;
                }
                this.__processedFramesPromiseResolveCount = j;
                this.__processedFramesPromise = promise;
                return promise;
            }
        } catch (Exception e) {
            promise.reject(e);
            return promise;
        }
    }

    public void setOutputRtpStreamId(String str) {
        this.__outputRtpStreamId = str;
    }

    public void setOutputSynchronizationSource(long j) {
        this.__outputSynchronizationSource = j;
    }

    public void updateMaxInputBitrate(int i) {
        setMaxInputBitrate(i);
    }

    public void updateMaxInputEncoding(EncodingInfo encodingInfo) {
        setMaxInputEncoding(encodingInfo);
    }

    public void updateMinInputBitrate(int i) {
        setMinInputBitrate(i);
    }

    public void updateMinInputEncoding(EncodingInfo encodingInfo) {
        setMinInputEncoding(encodingInfo);
    }

    public void updateSystemDelay(long j) {
        super.setSystemDelay(j);
    }
}
