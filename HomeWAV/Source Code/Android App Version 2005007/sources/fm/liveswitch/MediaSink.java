package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaSink;
import fm.liveswitch.diagnostics.RateTimer;
import fm.liveswitch.diagnostics.Timers;
import fm.liveswitch.sdp.MediaDescription;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MediaSink<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIOutputCollection>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TSink extends MediaSink<TIOutput, TIOutputCollection, TIInput, TSink, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends MediaSinkBase implements IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement {
    private static ILog __log = Log.getLogger(MediaSink.class);
    private boolean __disabled = false;
    private IDispatchQueue<TFrame> __dispatchQueue;
    private boolean __inputDeactivated = false;
    private RateTimer __inputRateTimer;
    private String __inputRtpStreamId = null;
    private long __inputSynchronizationSource = -1;
    private TIOutputCollection __inputs;
    private int __maxInputBitrate = -1;
    private int __minInputBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onDisabledChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onPausedChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TFrame, Exception>> __onProcessFrameException = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessedControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaisedControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TSink>> __onStateChange = new ArrayList();
    /* access modifiers changed from: private */
    public SinkOutput __output = null;
    private boolean __paused = false;
    private ProcessFramePolicy __processPolicy;
    private volatile boolean __processingControlFrameResponses = false;
    private volatile boolean __processingFrame = false;
    private volatile boolean __raisingControlFrames = false;
    private MediaSinkState __state = MediaSinkState.Initialized;
    private Object __stateLock = new Object();
    private TFormat _inputFormat;
    private boolean _inputMuted;
    private IAction0 _onDisabledChange = null;
    private IAction0 _onPausedChange = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrameResponses = null;
    private IAction1<TFrame> _onProcessFrame = null;
    private IAction2<TFrame, Exception> _onProcessFrameException = null;
    private IAction1<MediaControlFrame[]> _onProcessedControlFrameResponses = null;
    private IAction1<TFrame> _onProcessedFrame = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrames = null;
    private IAction1<MediaControlFrame[]> _onRaisedControlFrames = null;
    private IAction1<TSink> _onStateChange = null;
    private boolean _persistent;
    private long _systemDelay;

    /* access modifiers changed from: protected */
    public abstract TIOutputCollection createOutputCollection(TIInput tiinput);

    /* access modifiers changed from: protected */
    public abstract void doDestroy();

    /* access modifiers changed from: protected */
    public void doPostProcessFrame(TFrame tframe, TBuffer tbuffer) {
    }

    /* access modifiers changed from: protected */
    public void doPreProcessFrame(TFrame tframe, TBuffer tbuffer) {
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
    }

    /* access modifiers changed from: protected */
    public abstract void doProcessFrame(TFrame tframe, TBuffer tbuffer);

    /* access modifiers changed from: protected */
    public Error doProcessSdpMediaDescription(MediaDescription mediaDescription, boolean z, boolean z2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void doProcessSinkStatsFromInput(MediaSinkStats mediaSinkStats) {
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
    }

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getAllowInputRateTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getIsMixer() {
        return false;
    }

    public abstract String getLabel();

    /* access modifiers changed from: protected */
    public void inputAdded(TIOutput tioutput) {
    }

    /* access modifiers changed from: protected */
    public void inputRemoved(TIOutput tioutput) {
    }

    public void addInput(TIOutput tioutput) {
        if (tioutput != null) {
            validateInput(tioutput);
            if (this.__inputs.add(tioutput)) {
                inputAdded(tioutput);
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null input to {0}.", (Object) getLabel())));
    }

    public void addInputs(TIOutput[] tioutputArr) {
        if (tioutputArr != null) {
            for (TIOutput addInput : tioutputArr) {
                addInput(addInput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null inputs to {0}.", (Object) getLabel())));
    }

    public void addOnDisabledChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onDisabledChange == null) {
                this._onDisabledChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaSink.this.__onDisabledChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onDisabledChange.add(iAction0);
        }
    }

    public void addOnPausedChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onPausedChange == null) {
                this._onPausedChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaSink.this.__onPausedChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onPausedChange.add(iAction0);
        }
    }

    public void addOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessControlFrameResponses == null) {
                this._onProcessControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSink.this.__onProcessControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrameResponses.add(iAction1);
        }
    }

    public void addOnProcessedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedControlFrameResponses == null) {
                this._onProcessedControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSink.this.__onProcessedControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessedControlFrameResponses.add(iAction1);
        }
    }

    public void addOnProcessedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedFrame == null) {
                this._onProcessedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaSink.this.__onProcessedFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onProcessedFrame.add(iAction1);
        }
    }

    public void addOnProcessFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessFrame == null) {
                this._onProcessFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaSink.this.__onProcessFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onProcessFrame.add(iAction1);
        }
    }

    public void addOnProcessFrameException(IAction2<TFrame, Exception> iAction2) {
        if (iAction2 != null) {
            if (this._onProcessFrameException == null) {
                this._onProcessFrameException = new IAction2<TFrame, Exception>() {
                    public void invoke(TFrame tframe, Exception exc) {
                        Iterator it = new ArrayList(MediaSink.this.__onProcessFrameException).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tframe, exc);
                        }
                    }
                };
            }
            this.__onProcessFrameException.add(iAction2);
        }
    }

    public void addOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrames == null) {
                this._onRaiseControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSink.this.__onRaiseControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrames.add(iAction1);
        }
    }

    public void addOnRaisedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedControlFrames == null) {
                this._onRaisedControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSink.this.__onRaisedControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaisedControlFrames.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<TSink> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<TSink>() {
                    public void invoke(TSink tsink) {
                        Iterator it = new ArrayList(MediaSink.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tsink);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public Future<Object> changeOutput(SinkOutput sinkOutput) {
        return doChangeOutput(new Promise(), sinkOutput);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Media sink ({0}) is being destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5.__processingFrame != false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r5.__raisingControlFrames != false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r5.__processingControlFrameResponses == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        doDestroy();
        r0 = r5.__inputRateTimer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r0 == null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r0.destroy();
        r5.__inputRateTimer = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        r0 = r5.__stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        setState(fm.liveswitch.MediaSinkState.Destroyed);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r5.__inputs.destroy();
        r0 = r5.__dispatchQueue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r0 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        r0.destroy();
        r5.__dispatchQueue = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Media sink ({0}) has been destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0072, code lost:
        monitor-enter(r5.__stateLock);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        setState(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0077, code lost:
        __log.error(fm.liveswitch.StringExtensions.format("Media sink ({0}) could not be destroyed.", (java.lang.Object) getLabel()), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0087, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x008b, code lost:
        fm.liveswitch.ManagedThread.sleep(10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean destroy() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__stateLock
            monitor-enter(r0)
            fm.liveswitch.MediaSinkState r1 = r5.getState()     // Catch:{ all -> 0x009e }
            fm.liveswitch.MediaSinkState r2 = fm.liveswitch.MediaSinkState.Destroying     // Catch:{ all -> 0x009e }
            if (r1 == r2) goto L_0x0091
            fm.liveswitch.MediaSinkState r2 = fm.liveswitch.MediaSinkState.Destroyed     // Catch:{ all -> 0x009e }
            r3 = 1
            if (r1 != r2) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return r3
        L_0x0012:
            fm.liveswitch.MediaSinkState r1 = r5.getState()     // Catch:{ all -> 0x009e }
            fm.liveswitch.MediaSinkState r2 = fm.liveswitch.MediaSinkState.Destroying     // Catch:{ all -> 0x009e }
            r5.setState(r2)     // Catch:{ all -> 0x009e }
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Media sink ({0}) is being destroyed."
            java.lang.String r4 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)
            r0.verbose(r2)
        L_0x002b:
            boolean r0 = r5.__processingFrame
            if (r0 != 0) goto L_0x008b
            boolean r0 = r5.__raisingControlFrames
            if (r0 != 0) goto L_0x008b
            boolean r0 = r5.__processingControlFrameResponses
            if (r0 == 0) goto L_0x0038
            goto L_0x008b
        L_0x0038:
            r5.doDestroy()     // Catch:{ Exception -> 0x006f }
            fm.liveswitch.diagnostics.RateTimer r0 = r5.__inputRateTimer     // Catch:{ Exception -> 0x006f }
            r2 = 0
            if (r0 == 0) goto L_0x0045
            r0.destroy()     // Catch:{ Exception -> 0x006f }
            r5.__inputRateTimer = r2     // Catch:{ Exception -> 0x006f }
        L_0x0045:
            java.lang.Object r0 = r5.__stateLock     // Catch:{ Exception -> 0x006f }
            monitor-enter(r0)     // Catch:{ Exception -> 0x006f }
            fm.liveswitch.MediaSinkState r4 = fm.liveswitch.MediaSinkState.Destroyed     // Catch:{ all -> 0x006c }
            r5.setState(r4)     // Catch:{ all -> 0x006c }
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            TIOutputCollection r0 = r5.__inputs     // Catch:{ Exception -> 0x006f }
            r0.destroy()     // Catch:{ Exception -> 0x006f }
            fm.liveswitch.IDispatchQueue<TFrame> r0 = r5.__dispatchQueue     // Catch:{ Exception -> 0x006f }
            if (r0 == 0) goto L_0x005c
            r0.destroy()     // Catch:{ Exception -> 0x006f }
            r5.__dispatchQueue = r2     // Catch:{ Exception -> 0x006f }
        L_0x005c:
            fm.liveswitch.ILog r0 = __log     // Catch:{ Exception -> 0x006f }
            java.lang.String r2 = "Media sink ({0}) has been destroyed."
            java.lang.String r4 = r5.getLabel()     // Catch:{ Exception -> 0x006f }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)     // Catch:{ Exception -> 0x006f }
            r0.verbose(r2)     // Catch:{ Exception -> 0x006f }
            return r3
        L_0x006c:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            throw r2     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            r0 = move-exception
            java.lang.Object r2 = r5.__stateLock
            monitor-enter(r2)
            r5.setState(r1)     // Catch:{ all -> 0x0088 }
            monitor-exit(r2)     // Catch:{ all -> 0x0088 }
            fm.liveswitch.ILog r1 = __log
            java.lang.String r2 = "Media sink ({0}) could not be destroyed."
            java.lang.String r3 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r1.error((java.lang.String) r2, (java.lang.Exception) r0)
            r0 = 0
            return r0
        L_0x0088:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0088 }
            throw r0
        L_0x008b:
            r0 = 10
            fm.liveswitch.ManagedThread.sleep(r0)
            goto L_0x002b
        L_0x0091:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x009e }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "A media sink cannot be destroyed while it is being destroyed on a different thread."
            r2.<init>(r3)     // Catch:{ all -> 0x009e }
            r1.<init>(r2)     // Catch:{ all -> 0x009e }
            throw r1     // Catch:{ all -> 0x009e }
        L_0x009e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaSink.destroy():boolean");
    }

    private Future<Object> doChangeOutput(final Promise<Object> promise, final SinkOutput sinkOutput) {
        if (Global.equals(sinkOutput, this.__output)) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    promise.resolve(null);
                }
            });
        } else {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    SinkOutput unused = MediaSink.this.__output = sinkOutput;
                    promise.resolve(null);
                }
            });
        }
        return promise;
    }

    public int getCcmSequenceNumber() {
        IMediaOutput input = getInput();
        if (input != null) {
            return input.getCcmSequenceNumber();
        }
        return 0;
    }

    public boolean getDeactivated() {
        return getInputDeactivated();
    }

    public boolean getDisabled() {
        return updateDisabled();
    }

    public TIOutput getInput() {
        return (IMediaOutput) this.__inputs.getValue();
    }

    public boolean getInputDeactivated() {
        return this.__inputDeactivated;
    }

    public TFormat getInputFormat() {
        return this._inputFormat;
    }

    public boolean getInputMuted() {
        return this._inputMuted;
    }

    public String getInputRtpStreamId() {
        String str = this.__inputRtpStreamId;
        if (str == null) {
            for (IMediaOutput outputRtpStreamId : getInputs()) {
                str = outputRtpStreamId.getOutputRtpStreamId();
                if (str != null) {
                    return str;
                }
            }
        }
        return str;
    }

    public TIOutput[] getInputs() {
        return (IMediaOutput[]) this.__inputs.getValues();
    }

    public long getInputSynchronizationSource() {
        long j = this.__inputSynchronizationSource;
        if (j == -1) {
            for (IMediaOutput outputSynchronizationSource : getInputs()) {
                j = outputSynchronizationSource.getOutputSynchronizationSource();
                if (j != -1) {
                    return j;
                }
            }
        }
        return j;
    }

    public int getMaxInputBitrate() {
        int i = this.__maxInputBitrate;
        if (i == -1) {
            for (IMediaOutput maxOutputBitrate : getInputs()) {
                i = ConstraintUtility.min(i, maxOutputBitrate.getMaxOutputBitrate());
            }
        }
        return i;
    }

    public EncodingInfo getMaxInputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setRtpStreamId(getInputRtpStreamId());
        encodingInfo.setSynchronizationSource(getInputSynchronizationSource());
        encodingInfo.setDeactivated(getInputDeactivated());
        encodingInfo.setBitrate(getMaxInputBitrate());
        return encodingInfo;
    }

    public int getMinInputBitrate() {
        int i = this.__minInputBitrate;
        if (i == -1) {
            for (IMediaOutput minOutputBitrate : getInputs()) {
                i = ConstraintUtility.max(i, minOutputBitrate.getMinOutputBitrate());
            }
        }
        return i;
    }

    public EncodingInfo getMinInputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setRtpStreamId(getInputRtpStreamId());
        encodingInfo.setSynchronizationSource(getInputSynchronizationSource());
        encodingInfo.setDeactivated(getInputDeactivated());
        encodingInfo.setBitrate(getMinInputBitrate());
        return encodingInfo;
    }

    public boolean getMuted() {
        return getInputMuted();
    }

    public SinkOutput getOutput() {
        return this.__output;
    }

    public Future<SinkOutput[]> getOutputs() {
        Promise promise = new Promise();
        promise.resolve(new SinkOutput[0]);
        return promise;
    }

    public boolean getOverConstrained() {
        return getOverConstrainedInput();
    }

    public boolean getOverConstrainedBitrate() {
        return getOverConstrainedInputBitrate();
    }

    public boolean getOverConstrainedInput() {
        return getOverConstrainedInputBitrate();
    }

    public boolean getOverConstrainedInputBitrate() {
        return ConstraintUtility.overConstrained(getMinInputBitrate(), getMaxInputBitrate());
    }

    public boolean getPaused() {
        return this.__paused;
    }

    public boolean getPersistent() {
        return this._persistent;
    }

    public String getPipelineJson() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonInputs(), " }"});
    }

    private String getPipelineJsonBase() {
        return StringExtensions.concat((Object[]) new String[]{getPipelineJsonId(), ", ", getPipelineJsonLabel(), ", ", getPipelineJsonTag(), ", ", getPipelineJsonDisabled(), ", ", getPipelineJsonInput()});
    }

    private String getPipelineJsonDisabled() {
        return StringExtensions.concat("\"disabled\": ", getDisabled() ? "true" : "false");
    }

    public String getPipelineJsonFromInput() {
        return StringExtensions.concat("{ ", getPipelineJsonBase(), " }");
    }

    private String getPipelineJsonId() {
        return StringExtensions.concat("\"id\": ", JsonSerializer.serializeString(super.getId()));
    }

    private String getPipelineJsonInput() {
        return StringExtensions.concat("\"inputFormat\": ", getInputFormat() == null ? "null" : JsonSerializer.serializeString(getInputFormat().toString()));
    }

    private String getPipelineJsonInputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaOutput pipelineJsonFromOutput : getInputs()) {
            arrayList.add(pipelineJsonFromOutput.getPipelineJsonFromOutput());
        }
        return StringExtensions.concat("\"inputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonLabel() {
        return StringExtensions.concat("\"label\": ", JsonSerializer.serializeString(getLabel()));
    }

    private String getPipelineJsonTag() {
        return StringExtensions.concat("\"tag\": ", JsonSerializer.serializeString(super.getTag()));
    }

    public long getPipelineSystemDelay(TFormat tformat) {
        return getSystemDelay();
    }

    public ProcessFramePolicy getProcessFramePolicy() {
        return this.__processPolicy;
    }

    public MediaSinkState getState() {
        return this.__state;
    }

    public long getSystemDelay() {
        return this._systemDelay;
    }

    public boolean hasInput(TIOutput tioutput) {
        for (TIOutput tioutput2 : getInputs()) {
            if (tioutput2 == tioutput) {
                return true;
            }
        }
        return false;
    }

    public void incrementCcmSequenceNumber() {
        IMediaOutput input = getInput();
        if (input != null) {
            input.incrementCcmSequenceNumber();
        }
    }

    private void initialize(TFormat tformat) {
        this.__processPolicy = ProcessFramePolicy.Synchronous;
        setInputFormat(tformat);
        this.__inputs = createOutputCollection(this);
    }

    private RateTimer lazyGetInputRateTimer() {
        if (this.__inputRateTimer == null && getAllowDiagnosticTimer() && getAllowInputRateTimer()) {
            MediaFormat inputFormat = getInputFormat();
            this.__inputRateTimer = Timers.getRateTimer(StringExtensions.format("{0} Input Rate ({1})", getLabel(), inputFormat != null ? inputFormat.getName() : "null"));
        }
        return this.__inputRateTimer;
    }

    public MediaSink() {
        initialize((MediaFormat) null);
    }

    public MediaSink(TFormat tformat) {
        initialize(tformat);
    }

    public void processControlFrameResponse(MediaControlFrame mediaControlFrame) {
        processControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaSinkState.Initialized)) {
            this.__processingControlFrameResponses = true;
            try {
                if (Global.equals(this.__state, MediaSinkState.Initialized)) {
                    IAction1<MediaControlFrame[]> iAction1 = this._onProcessControlFrameResponses;
                    if (iAction1 != null) {
                        iAction1.invoke(mediaControlFrameArr);
                    }
                    for (MediaControlFrame tryCast : mediaControlFrameArr) {
                        TmmbnControlFrame tmmbnControlFrame = (TmmbnControlFrame) Global.tryCast(tryCast, TmmbnControlFrame.class);
                        if (tmmbnControlFrame != null) {
                            for (TmmbnEntry tmmbnEntry : tmmbnControlFrame.getEntries()) {
                                long inputSynchronizationSource = getInputSynchronizationSource();
                                if (tmmbnEntry.getSynchronizationSource() == inputSynchronizationSource || (inputSynchronizationSource == -1 && tmmbnEntry.getSynchronizationSource() == 4294967295L)) {
                                    setPaused(tmmbnEntry.getNormalizedMaximumBitrate() == 0);
                                }
                            }
                        }
                    }
                    doProcessControlFrameResponses(mediaControlFrameArr);
                    IAction1<MediaControlFrame[]> iAction12 = this._onProcessedControlFrameResponses;
                    if (iAction12 != null) {
                        iAction12.invoke(mediaControlFrameArr);
                    }
                }
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media sink ({0}) could not process control frame responses.", (Object) getLabel()), e);
                } catch (Throwable th) {
                    this.__processingControlFrameResponses = false;
                    throw th;
                }
            } catch (Throwable th2) {
                IAction1<MediaControlFrame[]> iAction13 = this._onProcessedControlFrameResponses;
                if (iAction13 != null) {
                    iAction13.invoke(mediaControlFrameArr);
                }
                throw th2;
            }
            this.__processingControlFrameResponses = false;
        }
    }

    public boolean processFrame(TFrame tframe) {
        if (Global.equals(getProcessFramePolicy(), ProcessFramePolicy.Synchronous)) {
            return processFrameSync(tframe);
        }
        if (!Global.equals(getProcessFramePolicy(), ProcessFramePolicy.Asynchronous)) {
            return false;
        }
        for (MediaBuffer keep : tframe.getBuffers()) {
            keep.keep();
        }
        this.__dispatchQueue.enqueue(tframe);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean processFrameSync(TFrame tframe) {
        MediaBuffer buffer;
        MediaBuffer lastBuffer;
        if (getInputFormat() == null && (lastBuffer = tframe.getLastBuffer()) != null) {
            setInputFormat(lastBuffer.getFormat());
        }
        if (Global.equals(this.__state, MediaSinkState.Initialized) && !getInputDeactivated()) {
            this.__processingFrame = true;
            try {
                if (Global.equals(this.__state, MediaSinkState.Initialized) && !getInputDeactivated() && (buffer = tframe.getBuffer(getInputFormat())) != null) {
                    if (StringExtensions.isNullOrEmpty(buffer.getSourceId())) {
                        buffer.setSourceId(super.getId());
                    }
                    if (getInputMuted()) {
                        buffer.mute();
                    }
                    if (getInputSynchronizationSource() != -1 && !getIsMixer()) {
                        tframe.setSynchronizationSource(getInputSynchronizationSource());
                    }
                    if (getInputRtpStreamId() != null && !getIsMixer()) {
                        tframe.setRtpStreamId(getInputRtpStreamId());
                    }
                    setPaused(false);
                    IAction1<TFrame> iAction1 = this._onProcessFrame;
                    if (iAction1 != null) {
                        iAction1.invoke(tframe);
                    }
                    if (tframe.getDiscard()) {
                        this.__processingFrame = false;
                        return false;
                    }
                    RateTimer lazyGetInputRateTimer = lazyGetInputRateTimer();
                    if (lazyGetInputRateTimer != null) {
                        lazyGetInputRateTimer.addTick();
                    }
                    doPreProcessFrame(tframe, buffer);
                    doProcessFrame(tframe, buffer);
                    doPostProcessFrame(tframe, buffer);
                    IAction1<TFrame> iAction12 = this._onProcessedFrame;
                    if (iAction12 != null) {
                        iAction12.invoke(tframe);
                    }
                }
                this.__processingFrame = false;
                return true;
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media sink ({0}) could not process frame.", (Object) getLabel()), e);
                    IAction2<TFrame, Exception> iAction2 = this._onProcessFrameException;
                    if (iAction2 != null) {
                        iAction2.invoke(tframe, e);
                    }
                } finally {
                    this.__processingFrame = false;
                }
            } catch (Throwable th) {
                IAction1<TFrame> iAction13 = this._onProcessedFrame;
                if (iAction13 != null) {
                    iAction13.invoke(tframe);
                }
                throw th;
            }
        }
        return false;
    }

    public Error processSdpMediaDescriptionFromInput(MediaDescription mediaDescription, boolean z, boolean z2) {
        return doProcessSdpMediaDescription(mediaDescription, z, z2);
    }

    public void processSinkStatsFromInput(MediaSinkStats mediaSinkStats) {
        mediaSinkStats.setId(super.getId());
        mediaSinkStats.setTag(super.getTag());
        mediaSinkStats.setLabel(getLabel());
        mediaSinkStats.setMuted(mediaSinkStats.getMuted() || (!getDisabled() && getInputMuted()));
        SinkOutput output = getOutput();
        if (output != null) {
            mediaSinkStats.setOutputId(output.getId());
            mediaSinkStats.setOutputName(output.getName());
        }
        MediaFormat inputFormat = getInputFormat();
        if (inputFormat != null) {
            mediaSinkStats.setInputFormat(inputFormat.getInfo());
        }
        doProcessSinkStatsFromInput(mediaSinkStats);
    }

    public void processTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        mediaTrackStats.setMuted(mediaTrackStats.getMuted() || (!getDisabled() && getInputMuted()));
        doProcessTrackStatsFromInput(mediaTrackStats);
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrame(MediaControlFrame mediaControlFrame) {
        raiseControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaSinkState.Initialized)) {
            this.__raisingControlFrames = true;
            try {
                if (Global.equals(this.__state, MediaSinkState.Initialized)) {
                    for (FeedbackControlFrame feedbackControlFrame : mediaControlFrameArr) {
                        if (feedbackControlFrame instanceof FeedbackControlFrame) {
                            FeedbackControlFrame feedbackControlFrame2 = feedbackControlFrame;
                            if (feedbackControlFrame2.getMediaSourceSynchronizationSource() == 0 && getInputSynchronizationSource() != -1) {
                                feedbackControlFrame2.setMediaSourceSynchronizationSource(getInputSynchronizationSource());
                            }
                        }
                    }
                    IAction1<MediaControlFrame[]> iAction1 = this._onRaiseControlFrames;
                    if (iAction1 != null) {
                        iAction1.invoke(mediaControlFrameArr);
                    }
                    for (IMediaOutput processControlFrames : getInputs()) {
                        processControlFrames.processControlFrames(mediaControlFrameArr);
                    }
                    IAction1<MediaControlFrame[]> iAction12 = this._onRaisedControlFrames;
                    if (iAction12 != null) {
                        iAction12.invoke(mediaControlFrameArr);
                    }
                }
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media sink ({0}) could not raise control frames.", (Object) getLabel()), e);
                } catch (Throwable th) {
                    this.__raisingControlFrames = false;
                    throw th;
                }
            } catch (Throwable th2) {
                IAction1<MediaControlFrame[]> iAction13 = this._onRaisedControlFrames;
                if (iAction13 != null) {
                    iAction13.invoke(mediaControlFrameArr);
                }
                throw th2;
            }
            this.__raisingControlFrames = false;
        }
    }

    /* access modifiers changed from: protected */
    public void raiseDisabledChange() {
        IAction0 iAction0 = this._onDisabledChange;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public boolean removeInput(TIOutput tioutput) {
        if (tioutput == null) {
            throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null input from {0}.", (Object) getLabel())));
        } else if (!this.__inputs.remove(tioutput)) {
            return false;
        } else {
            inputRemoved(tioutput);
            return true;
        }
    }

    public void removeInputs() {
        removeInputs((TIOutput[]) getInputs());
    }

    public void removeInputs(TIOutput[] tioutputArr) {
        if (tioutputArr != null) {
            for (TIOutput removeInput : tioutputArr) {
                removeInput(removeInput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null inputs from {0}.", (Object) getLabel())));
    }

    public void removeOnDisabledChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onDisabledChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onDisabledChange.remove(iAction0);
        if (this.__onDisabledChange.size() == 0) {
            this._onDisabledChange = null;
        }
    }

    public void removeOnPausedChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onPausedChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onPausedChange.remove(iAction0);
        if (this.__onPausedChange.size() == 0) {
            this._onPausedChange = null;
        }
    }

    public void removeOnProcessControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessControlFrameResponses.remove(iAction1);
        if (this.__onProcessControlFrameResponses.size() == 0) {
            this._onProcessControlFrameResponses = null;
        }
    }

    public void removeOnProcessedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessedControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessedControlFrameResponses.remove(iAction1);
        if (this.__onProcessedControlFrameResponses.size() == 0) {
            this._onProcessedControlFrameResponses = null;
        }
    }

    public void removeOnProcessedFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessedFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessedFrame.remove(iAction1);
        if (this.__onProcessedFrame.size() == 0) {
            this._onProcessedFrame = null;
        }
    }

    public void removeOnProcessFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessFrame.remove(iAction1);
        if (this.__onProcessFrame.size() == 0) {
            this._onProcessFrame = null;
        }
    }

    public void removeOnProcessFrameException(IAction2<TFrame, Exception> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onProcessFrameException, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onProcessFrameException.remove(iAction2);
        if (this.__onProcessFrameException.size() == 0) {
            this._onProcessFrameException = null;
        }
    }

    public void removeOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseControlFrames.remove(iAction1);
        if (this.__onRaiseControlFrames.size() == 0) {
            this._onRaiseControlFrames = null;
        }
    }

    public void removeOnRaisedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaisedControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaisedControlFrames.remove(iAction1);
        if (this.__onRaisedControlFrames.size() == 0) {
            this._onRaisedControlFrames = null;
        }
    }

    public void removeOnStateChange(IAction1<TSink> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public void setDeactivated(boolean z) {
        setInputDeactivated(z);
    }

    public void setDisabled(boolean z) {
        __log.warn("Setting the value of MediaSink.Disabled is deprecated. Set the value of MediaSink.Deactivated instead.");
        setDeactivated(z);
    }

    public void setInput(TIOutput tioutput) {
        removeInputs();
        addInput(tioutput);
    }

    public void setInputDeactivated(boolean z) {
        this.__inputDeactivated = z;
        updateDisabled();
    }

    private void setInputFormat(TFormat tformat) {
        this._inputFormat = tformat;
    }

    public void setInputMuted(boolean z) {
        this._inputMuted = z;
    }

    public void setInputRtpStreamId(String str) {
        this.__inputRtpStreamId = str;
    }

    public void setInputs(TIOutput[] tioutputArr) {
        removeInputs();
        addInputs(tioutputArr);
    }

    public void setInputSynchronizationSource(long j) {
        this.__inputSynchronizationSource = j;
    }

    /* access modifiers changed from: protected */
    public void setMaxInputBitrate(int i) {
        this.__maxInputBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMaxInputEncoding(EncodingInfo encodingInfo) {
        if (encodingInfo == null) {
            setInputDeactivated(false);
            setMaxInputBitrate(-1);
            return;
        }
        if (getInputRtpStreamId() == null) {
            setInputRtpStreamId(encodingInfo.getRtpStreamId());
        }
        if (getInputSynchronizationSource() < 0) {
            setInputSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        setInputDeactivated(encodingInfo.getDeactivated());
        setMaxInputBitrate(encodingInfo.getBitrate());
    }

    /* access modifiers changed from: protected */
    public void setMinInputBitrate(int i) {
        this.__minInputBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMinInputEncoding(EncodingInfo encodingInfo) {
        if (encodingInfo == null) {
            setInputDeactivated(false);
            setMinInputBitrate(-1);
            return;
        }
        if (getInputRtpStreamId() == null) {
            setInputRtpStreamId(encodingInfo.getRtpStreamId());
        }
        if (getInputSynchronizationSource() < 0) {
            setInputSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        setInputDeactivated(encodingInfo.getDeactivated());
        setMinInputBitrate(encodingInfo.getBitrate());
    }

    public void setMuted(boolean z) {
        setInputMuted(z);
    }

    public void setOutput(SinkOutput sinkOutput) {
        this.__output = sinkOutput;
    }

    private void setPaused(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__paused), Boolean.valueOf(z))) {
            this.__paused = z;
            IAction0 iAction0 = this._onPausedChange;
            if (iAction0 != null) {
                iAction0.invoke();
            }
        }
    }

    public void setPersistent(boolean z) {
        this._persistent = z;
    }

    public void setProcessFramePolicy(ProcessFramePolicy processFramePolicy) {
        if (Global.equals(processFramePolicy, ProcessFramePolicy.Asynchronous) && this.__dispatchQueue == null) {
            this.__dispatchQueue = new DispatchQueue(new IAction1<TFrame>() {
                /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: 
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                    	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                    */
                public void invoke(TFrame r5) {
                    /*
                        r4 = this;
                        r0 = 0
                        fm.liveswitch.MediaSink r1 = fm.liveswitch.MediaSink.this     // Catch:{ all -> 0x0016 }
                        boolean unused = r1.processFrameSync(r5)     // Catch:{ all -> 0x0016 }
                        fm.liveswitch.MediaBuffer[] r5 = r5.getBuffers()
                        int r1 = r5.length
                    L_0x000b:
                        if (r0 >= r1) goto L_0x0015
                        r2 = r5[r0]
                        r2.free()
                        int r0 = r0 + 1
                        goto L_0x000b
                    L_0x0015:
                        return
                    L_0x0016:
                        r1 = move-exception
                        fm.liveswitch.MediaBuffer[] r5 = r5.getBuffers()
                        int r2 = r5.length
                    L_0x001c:
                        if (r0 >= r2) goto L_0x0026
                        r3 = r5[r0]
                        r3.free()
                        int r0 = r0 + 1
                        goto L_0x001c
                    L_0x0026:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaSink.AnonymousClass13.invoke(fm.liveswitch.MediaFrame):void");
                }
            });
        }
        this.__processPolicy = processFramePolicy;
    }

    private void setState(MediaSinkState mediaSinkState) {
        synchronized (this.__stateLock) {
            if (!Global.equals(this.__state, mediaSinkState)) {
                this.__state = mediaSinkState;
                IAction1<TSink> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setSystemDelay(long j) {
        this._systemDelay = j;
    }

    public String toString() {
        return StringExtensions.format("{0} ({1})", getLabel(), getInputFormat().getName());
    }

    /* access modifiers changed from: protected */
    public boolean updateDisabled() {
        boolean inputDeactivated = getInputDeactivated();
        if (!Global.equals(Boolean.valueOf(this.__disabled), Boolean.valueOf(inputDeactivated))) {
            this.__disabled = inputDeactivated;
            raiseDisabledChange();
        }
        return inputDeactivated;
    }

    private void validateInput(TIOutput tioutput) {
        if (!(tioutput instanceof Stream)) {
            if (getInputFormat() == null) {
                setInputFormat(tioutput.getOutputFormat().clone());
            }
            if (!tioutput.getOutputFormat().isCompatible(getInputFormat())) {
                throw new RuntimeException(new Exception(StringExtensions.concat((Object[]) new String[]{"Output format [", tioutput.getOutputFormat().toString(), "] of input (", tioutput.getLabel(), ") is not compatible with input format [", getInputFormat().toString(), "] of self (", getLabel(), ")."})));
            }
        }
    }
}
