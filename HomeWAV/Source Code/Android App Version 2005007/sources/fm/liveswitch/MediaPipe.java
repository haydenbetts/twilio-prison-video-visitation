package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaPipe;
import fm.liveswitch.diagnostics.DurationSample;
import fm.liveswitch.diagnostics.DurationTimer;
import fm.liveswitch.diagnostics.RateTimer;
import fm.liveswitch.diagnostics.Timers;
import fm.liveswitch.sdp.MediaDescription;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MediaPipe<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIOutputCollection>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIInputCollection>, TPipe extends MediaPipe<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TPipe, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends Dynamic implements IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement {
    private static ILog __log = Log.getLogger(MediaSource.class);
    private long __baseRtpTimestamp = -1;
    private long __baseTimestamp = -1;
    private boolean __disabled = true;
    private IDispatchQueue<TFrame> __dispatchQueue;
    private DurationTimer __durationTimer;
    private volatile boolean __externalDeactivated = false;
    private String __id = Utility.generateId();
    private boolean __inputDeactivated = false;
    private RateTimer __inputRateTimer;
    private String __inputRtpStreamId = null;
    private long __inputSynchronizationSource = -1;
    private TIOutputCollection __inputs;
    private volatile boolean __internalDeactivated = false;
    private int __localMaxOutputBitrate = -1;
    private int __maxInputBitrate = -1;
    private int __maxOutputBitrate = -1;
    private int __minInputBitrate = -1;
    private int __minOutputBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onDisabledChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onPausedChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction2<TFrame, Exception>> __onProcessFrameException = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessedControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessedControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onProcessedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaiseFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaisedControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaisedControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaisedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TPipe>> __onStateChange = new ArrayList();
    private boolean __outputDeactivated = false;
    private RateTimer __outputRateTimer;
    private String __outputRtpStreamId = null;
    private boolean __outputSynchronizable = false;
    private long __outputSynchronizationSource = -1;
    private TIInputCollection __outputs;
    private boolean __paused = false;
    private LinkedList<Pair<TFrame, Promise<TBuffer>>> __processBufferPromises = null;
    private Object __processBufferPromisesLock = new Object();
    private ProcessFramePolicy __processPolicy;
    private volatile boolean __processingControlFrameResponses = false;
    private volatile boolean __processingControlFrames = false;
    private volatile boolean __processingFrame = false;
    private TIInput[] __raiseFrameOutputs;
    private volatile boolean __raisingControlFrameResponses = false;
    private volatile boolean __raisingControlFrames = false;
    private volatile boolean __raisingFrame = false;
    private int __remoteMaxOutputBitrate = -1;
    private MediaPipeState __state = MediaPipeState.Initialized;
    private Object __stateLock = new Object();
    private int __targetOutputBitrate = -1;
    private String _externalId;
    private TFormat _inputFormat;
    private boolean _inputMuted;
    private IAction0 _onDisabledChange = null;
    private IAction0 _onPausedChange = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrames = null;
    private IAction1<TFrame> _onProcessFrame = null;
    private IAction2<TFrame, Exception> _onProcessFrameException = null;
    private IAction1<MediaControlFrame[]> _onProcessedControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onProcessedControlFrames = null;
    private IAction1<TFrame> _onProcessedFrame = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrames = null;
    private IAction1<TFrame> _onRaiseFrame = null;
    private IAction1<MediaControlFrame[]> _onRaisedControlFrameResponses = null;
    private IAction1<MediaControlFrame[]> _onRaisedControlFrames = null;
    private IAction1<TFrame> _onRaisedFrame = null;
    private IAction1<TPipe> _onStateChange = null;
    private TFormat _outputFormat;
    private boolean _outputMuted;
    private boolean _persistent;
    private long _systemDelay;
    private String _tag;

    /* access modifiers changed from: protected */
    public abstract TFrame createFrame(TBuffer tbuffer);

    /* access modifiers changed from: protected */
    public abstract TIInputCollection createInputCollection(TIOutput tioutput);

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
    public abstract void doProcessFrame(TFrame tframe, TBuffer tbuffer);

    /* access modifiers changed from: protected */
    public Error doProcessSdpMediaDescription(MediaDescription mediaDescription, boolean z, boolean z2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void doProcessSinkStatsFromInput(MediaSinkStats mediaSinkStats) {
    }

    /* access modifiers changed from: protected */
    public void doProcessSourceStatsFromOutput(MediaSourceStats mediaSourceStats) {
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
    }

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getAllowDurationTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getAllowInputRateTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getAllowOutputRateTimer() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean getCanPauseBitrate() {
        return false;
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

    /* access modifiers changed from: protected */
    public void outputAdded(TIInput tiinput) {
    }

    /* access modifiers changed from: protected */
    public void outputRemoved(TIInput tiinput) {
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
                        Iterator it = new ArrayList(MediaPipe.this.__onDisabledChange).iterator();
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
                        Iterator it = new ArrayList(MediaPipe.this.__onPausedChange).iterator();
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
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrameResponses.add(iAction1);
        }
    }

    public void addOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessControlFrames == null) {
                this._onProcessControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrames.add(iAction1);
        }
    }

    public void addOnProcessedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedControlFrameResponses == null) {
                this._onProcessedControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessedControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessedControlFrameResponses.add(iAction1);
        }
    }

    public void addOnProcessedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedControlFrames == null) {
                this._onProcessedControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessedControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessedControlFrames.add(iAction1);
        }
    }

    public void addOnProcessedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedFrame == null) {
                this._onProcessedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessedFrame).iterator();
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
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessFrame).iterator();
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
                        Iterator it = new ArrayList(MediaPipe.this.__onProcessFrameException).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(tframe, exc);
                        }
                    }
                };
            }
            this.__onProcessFrameException.add(iAction2);
        }
    }

    public void addOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrameResponses == null) {
                this._onRaiseControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaiseControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrameResponses.add(iAction1);
        }
    }

    public void addOnRaiseControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrames == null) {
                this._onRaiseControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaiseControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrames.add(iAction1);
        }
    }

    public void addOnRaisedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedControlFrameResponses == null) {
                this._onRaisedControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaisedControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaisedControlFrameResponses.add(iAction1);
        }
    }

    public void addOnRaisedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedControlFrames == null) {
                this._onRaisedControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaisedControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaisedControlFrames.add(iAction1);
        }
    }

    public void addOnRaisedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedFrame == null) {
                this._onRaisedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaisedFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onRaisedFrame.add(iAction1);
        }
    }

    public void addOnRaiseFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseFrame == null) {
                this._onRaiseFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaPipe.this.__onRaiseFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onRaiseFrame.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<TPipe> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<TPipe>() {
                    public void invoke(TPipe tpipe) {
                        Iterator it = new ArrayList(MediaPipe.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tpipe);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public void addOutput(TIInput tiinput) {
        if (tiinput != null) {
            validateOutput(tiinput);
            if (this.__outputs.add(tiinput)) {
                outputAdded(tiinput);
                tiinput.addOnDisabledChange(new IActionDelegate0() {
                    public String getId() {
                        return "fm.liveswitch.MediaPipe<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TPipe,TFrame,TBuffer,TBufferCollection,TFormat>.outputDisabledChanged";
                    }

                    public void invoke() {
                        MediaPipe.this.outputDisabledChanged();
                    }
                });
                updateDisabled();
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null output to {0}.", (Object) getLabel())));
    }

    public void addOutputs(TIInput[] tiinputArr) {
        if (tiinputArr != null) {
            for (TIInput addOutput : tiinputArr) {
                addOutput(addOutput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot add null outputs to {0}.", (Object) getLabel())));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Media pipe ({0}) is being destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5.__processingFrame != false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r5.__processingControlFrames != false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r5.__processingControlFrameResponses != false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        if (r5.__raisingFrame != false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (r5.__raisingControlFrames != false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        if (r5.__raisingControlFrameResponses == false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        doDestroy();
        r0 = r5.__outputRateTimer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004a, code lost:
        if (r0 == null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r0.destroy();
        r5.__outputRateTimer = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        r0 = r5.__durationTimer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0053, code lost:
        if (r0 == null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0055, code lost:
        r0.destroy();
        r5.__durationTimer = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005a, code lost:
        r0 = r5.__inputRateTimer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        if (r0 == null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005e, code lost:
        r0.destroy();
        r5.__inputRateTimer = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0063, code lost:
        r0 = r5.__stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0065, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        setState(fm.liveswitch.MediaPipeState.Destroyed);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006b, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r5.__inputs.destroy();
        r5.__outputs.destroy();
        r0 = r5.__dispatchQueue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0078, code lost:
        if (r0 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007a, code lost:
        r0.destroy();
        r5.__dispatchQueue = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007f, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Media pipe ({0}) has been destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0092, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0095, code lost:
        monitor-enter(r5.__stateLock);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        setState(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009a, code lost:
        __log.error(fm.liveswitch.StringExtensions.format("Media pipe ({0}) could not be destroyed.", (java.lang.Object) getLabel()), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00aa, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ae, code lost:
        fm.liveswitch.ManagedThread.sleep(10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean destroy() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__stateLock
            monitor-enter(r0)
            fm.liveswitch.MediaPipeState r1 = r5.getState()     // Catch:{ all -> 0x00c2 }
            fm.liveswitch.MediaPipeState r2 = fm.liveswitch.MediaPipeState.Destroying     // Catch:{ all -> 0x00c2 }
            if (r1 == r2) goto L_0x00b5
            fm.liveswitch.MediaPipeState r2 = fm.liveswitch.MediaPipeState.Destroyed     // Catch:{ all -> 0x00c2 }
            r3 = 1
            if (r1 != r2) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            return r3
        L_0x0012:
            fm.liveswitch.MediaPipeState r1 = r5.getState()     // Catch:{ all -> 0x00c2 }
            fm.liveswitch.MediaPipeState r2 = fm.liveswitch.MediaPipeState.Destroying     // Catch:{ all -> 0x00c2 }
            r5.setState(r2)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Media pipe ({0}) is being destroyed."
            java.lang.String r4 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)
            r0.verbose(r2)
        L_0x002b:
            boolean r0 = r5.__processingFrame
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.__processingControlFrames
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.__processingControlFrameResponses
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.__raisingFrame
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.__raisingControlFrames
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.__raisingControlFrameResponses
            if (r0 == 0) goto L_0x0044
            goto L_0x00ae
        L_0x0044:
            r5.doDestroy()     // Catch:{ Exception -> 0x0092 }
            fm.liveswitch.diagnostics.RateTimer r0 = r5.__outputRateTimer     // Catch:{ Exception -> 0x0092 }
            r2 = 0
            if (r0 == 0) goto L_0x0051
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            r5.__outputRateTimer = r2     // Catch:{ Exception -> 0x0092 }
        L_0x0051:
            fm.liveswitch.diagnostics.DurationTimer r0 = r5.__durationTimer     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x005a
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            r5.__durationTimer = r2     // Catch:{ Exception -> 0x0092 }
        L_0x005a:
            fm.liveswitch.diagnostics.RateTimer r0 = r5.__inputRateTimer     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x0063
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            r5.__inputRateTimer = r2     // Catch:{ Exception -> 0x0092 }
        L_0x0063:
            java.lang.Object r0 = r5.__stateLock     // Catch:{ Exception -> 0x0092 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0092 }
            fm.liveswitch.MediaPipeState r4 = fm.liveswitch.MediaPipeState.Destroyed     // Catch:{ all -> 0x008f }
            r5.setState(r4)     // Catch:{ all -> 0x008f }
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            TIOutputCollection r0 = r5.__inputs     // Catch:{ Exception -> 0x0092 }
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            TIInputCollection r0 = r5.__outputs     // Catch:{ Exception -> 0x0092 }
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            fm.liveswitch.IDispatchQueue<TFrame> r0 = r5.__dispatchQueue     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x007f
            r0.destroy()     // Catch:{ Exception -> 0x0092 }
            r5.__dispatchQueue = r2     // Catch:{ Exception -> 0x0092 }
        L_0x007f:
            fm.liveswitch.ILog r0 = __log     // Catch:{ Exception -> 0x0092 }
            java.lang.String r2 = "Media pipe ({0}) has been destroyed."
            java.lang.String r4 = r5.getLabel()     // Catch:{ Exception -> 0x0092 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0092 }
            r0.verbose(r2)     // Catch:{ Exception -> 0x0092 }
            return r3
        L_0x008f:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            throw r2     // Catch:{ Exception -> 0x0092 }
        L_0x0092:
            r0 = move-exception
            java.lang.Object r2 = r5.__stateLock
            monitor-enter(r2)
            r5.setState(r1)     // Catch:{ all -> 0x00ab }
            monitor-exit(r2)     // Catch:{ all -> 0x00ab }
            fm.liveswitch.ILog r1 = __log
            java.lang.String r2 = "Media pipe ({0}) could not be destroyed."
            java.lang.String r3 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r1.error((java.lang.String) r2, (java.lang.Exception) r0)
            r0 = 0
            return r0
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00ab }
            throw r0
        L_0x00ae:
            r0 = 10
            fm.liveswitch.ManagedThread.sleep(r0)
            goto L_0x002b
        L_0x00b5:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00c2 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "A media pipe cannot be destroyed while it is being destroyed on a different thread."
            r2.<init>(r3)     // Catch:{ all -> 0x00c2 }
            r1.<init>(r2)     // Catch:{ all -> 0x00c2 }
            throw r1     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaPipe.destroy():boolean");
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        raiseControlFrameResponses(mediaControlFrameArr);
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        raiseControlFrames(mediaControlFrameArr);
    }

    public int getCcmSequenceNumber() {
        IMediaOutput input = getInput();
        if (input != null) {
            return input.getCcmSequenceNumber();
        }
        return 0;
    }

    public boolean getDeactivated() {
        return getInputDeactivated() && getOutputDeactivated();
    }

    public boolean getDeactivatedByApplication() {
        return this.__externalDeactivated;
    }

    public boolean getDeactivatedByServer() {
        return this.__internalDeactivated;
    }

    public boolean getDisabled() {
        return updateDisabled();
    }

    public String getExternalId() {
        return this._externalId;
    }

    public String getId() {
        return this.__id;
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

    public int getMaxOutputBitrate() {
        int i = this.__maxOutputBitrate;
        return i == -1 ? getMaxInputBitrate() : i;
    }

    public EncodingInfo getMaxOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getMaxOutputBitrate());
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

    public int getMinOutputBitrate() {
        int i = this.__minOutputBitrate;
        return i == -1 ? getMinInputBitrate() : i;
    }

    public EncodingInfo getMinOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getMinOutputBitrate());
        return encodingInfo;
    }

    public boolean getMuted() {
        return getOutputMuted();
    }

    public TIInput getOutput() {
        return (IMediaInput) this.__outputs.getValue();
    }

    public boolean getOutputDeactivated() {
        return this.__outputDeactivated;
    }

    public TFormat getOutputFormat() {
        return this._outputFormat;
    }

    public boolean getOutputMuted() {
        return this._outputMuted;
    }

    public String getOutputRtpStreamId() {
        String str = this.__outputRtpStreamId;
        return str == null ? getInputRtpStreamId() : str;
    }

    public TIInput[] getOutputs() {
        return (IMediaInput[]) this.__outputs.getValues();
    }

    public boolean getOutputSynchronizable() {
        if (this.__outputSynchronizable) {
            return true;
        }
        for (IMediaOutput outputSynchronizable : getInputs()) {
            if (outputSynchronizable.getOutputSynchronizable()) {
                return true;
            }
        }
        return false;
    }

    public long getOutputSynchronizationSource() {
        long j = this.__outputSynchronizationSource;
        return j == -1 ? getInputSynchronizationSource() : j;
    }

    public boolean getOverConstrained() {
        return getOverConstrainedInput() || getOverConstrainedOutput();
    }

    public boolean getOverConstrainedBitrate() {
        return getOverConstrainedInputBitrate() || getOverConstrainedOutputBitrate();
    }

    public boolean getOverConstrainedInput() {
        return getOverConstrainedInputBitrate();
    }

    public boolean getOverConstrainedInputBitrate() {
        return ConstraintUtility.overConstrained(getMinInputBitrate(), getMaxInputBitrate());
    }

    public boolean getOverConstrainedOutput() {
        return getOverConstrainedOutputBitrate();
    }

    public boolean getOverConstrainedOutputBitrate() {
        return ConstraintUtility.overConstrained(getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public boolean getPaused() {
        return this.__paused;
    }

    public boolean getPersistent() {
        return this._persistent;
    }

    public String getPipelineJson() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonInputs(), ", ", getPipelineJsonOutputs(), " }"});
    }

    /* access modifiers changed from: protected */
    public String getPipelineJsonBase() {
        return StringExtensions.concat((Object[]) new String[]{getPipelineJsonId(), ", ", getPipelineJsonLabel(), ", ", getPipelineJsonTag(), ", ", getPipelineJsonDisabled(), ", ", getPipelineJsonInput(), ", ", getPipelineJsonOutput()});
    }

    private String getPipelineJsonDisabled() {
        return StringExtensions.concat("\"disabled\": ", getDisabled() ? "true" : "false");
    }

    public String getPipelineJsonFromInput() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonOutputs(), " }"});
    }

    public String getPipelineJsonFromOutput() {
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonInputs(), " }"});
    }

    private String getPipelineJsonId() {
        return StringExtensions.concat("\"id\": ", JsonSerializer.serializeString(getId()));
    }

    private String getPipelineJsonInput() {
        return StringExtensions.concat("\"inputFormat\": ", getInputFormat() == null ? "null" : JsonSerializer.serializeString(getInputFormat().toString()));
    }

    /* access modifiers changed from: protected */
    public String getPipelineJsonInputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaOutput pipelineJsonFromOutput : getInputs()) {
            arrayList.add(pipelineJsonFromOutput.getPipelineJsonFromOutput());
        }
        return StringExtensions.concat("\"inputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonLabel() {
        return StringExtensions.concat("\"label\": ", JsonSerializer.serializeString(getLabel()));
    }

    private String getPipelineJsonOutput() {
        return StringExtensions.concat("\"outputFormat\": ", getOutputFormat() == null ? "null" : JsonSerializer.serializeString(getOutputFormat().toString()));
    }

    /* access modifiers changed from: protected */
    public String getPipelineJsonOutputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaInput pipelineJsonFromInput : getOutputs()) {
            arrayList.add(pipelineJsonFromInput.getPipelineJsonFromInput());
        }
        return StringExtensions.concat("\"outputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonTag() {
        return StringExtensions.concat("\"tag\": ", JsonSerializer.serializeString(getTag()));
    }

    public long getPipelineSystemDelay(TFormat tformat) {
        for (IMediaInput iMediaInput : getOutputs()) {
            MediaFormat inputFormat = iMediaInput.getInputFormat();
            if (inputFormat != null && inputFormat.isCompatible(tformat)) {
                return getSystemDelay() + iMediaInput.getPipelineSystemDelay(tformat);
            }
        }
        return getSystemDelay();
    }

    public ProcessFramePolicy getProcessFramePolicy() {
        return this.__processPolicy;
    }

    private TIInput[] getRaiseFrameOutputs() {
        TIInput[] outputs = getOutputs();
        if (ArrayExtensions.getLength((Object[]) outputs) == 0) {
            return null;
        }
        TIInput[] tiinputArr = this.__raiseFrameOutputs;
        if (tiinputArr == null || ArrayExtensions.getLength((Object[]) tiinputArr) != ArrayExtensions.getLength((Object[]) outputs)) {
            TIInput[] tiinputArr2 = (IMediaInput[]) this.__outputs.toArray();
            this.__raiseFrameOutputs = tiinputArr2;
            if (ArrayExtensions.getLength((Object[]) tiinputArr2) == 0) {
                return null;
            }
        } else {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) outputs); i++) {
                this.__raiseFrameOutputs[i] = outputs[i];
            }
        }
        return this.__raiseFrameOutputs;
    }

    public MediaPipeState getState() {
        return this.__state;
    }

    public long getSynchronizationSource() {
        return getOutputSynchronizationSource();
    }

    public long getSystemDelay() {
        return this._systemDelay;
    }

    public String getTag() {
        return this._tag;
    }

    public int getTargetOutputBitrate() {
        int i = this.__targetOutputBitrate;
        if (i == -1) {
            for (IMediaOutput targetOutputBitrate : getInputs()) {
                i = ConstraintUtility.max(i, targetOutputBitrate.getTargetOutputBitrate());
            }
        }
        return ConstraintUtility.clampMin(i, getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public EncodingInfo getTargetOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getTargetOutputBitrate());
        return encodingInfo;
    }

    public boolean hasInput(TIOutput tioutput) {
        for (TIOutput tioutput2 : getInputs()) {
            if (tioutput2 == tioutput) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOutput(TIInput tiinput) {
        for (TIInput tiinput2 : getOutputs()) {
            if (tiinput2 == tiinput) {
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

    private void initialize(TFormat tformat, TFormat tformat2) {
        this.__processPolicy = ProcessFramePolicy.Synchronous;
        setInputFormat(tformat);
        setOutputFormat(tformat2);
        this.__outputs = createInputCollection(this);
        this.__inputs = createOutputCollection(this);
    }

    private DurationTimer lazyGetDurationTimer() {
        if (this.__durationTimer == null && getAllowDiagnosticTimer() && getAllowDurationTimer()) {
            MediaFormat inputFormat = getInputFormat();
            MediaFormat outputFormat = getOutputFormat();
            String label = getLabel();
            String str = "null";
            String name = inputFormat != null ? inputFormat.getName() : str;
            if (outputFormat != null) {
                str = outputFormat.getName();
            }
            this.__durationTimer = Timers.getDurationTimer(StringExtensions.format("{0} Duration ({1} -> {2})", label, name, str));
        }
        return this.__durationTimer;
    }

    private RateTimer lazyGetInputRateTimer() {
        if (this.__inputRateTimer == null && getAllowDiagnosticTimer() && getAllowInputRateTimer()) {
            MediaFormat inputFormat = getInputFormat();
            this.__inputRateTimer = Timers.getRateTimer(StringExtensions.format("{0} Input Rate ({1})", getLabel(), inputFormat != null ? inputFormat.getName() : "null"));
        }
        return this.__inputRateTimer;
    }

    private RateTimer lazyGetOutputRateTimer() {
        if (this.__outputRateTimer == null && getAllowDiagnosticTimer() && getAllowOutputRateTimer()) {
            MediaFormat outputFormat = getOutputFormat();
            this.__outputRateTimer = Timers.getRateTimer(StringExtensions.format("{0} Output Rate ({1})", getLabel(), outputFormat != null ? outputFormat.getName() : "null"));
        }
        return this.__outputRateTimer;
    }

    public MediaPipe(TFormat tformat, TFormat tformat2) {
        initialize(tformat, tformat2);
    }

    public MediaPipe(TFormat tformat) {
        initialize((MediaFormat) null, tformat);
    }

    /* access modifiers changed from: protected */
    public boolean outputCanProcessFrame(TIInput tiinput) {
        if (tiinput.getDisabled()) {
            return false;
        }
        MediaFormat outputFormat = getOutputFormat();
        MediaFormat inputFormat = tiinput.getInputFormat();
        if (inputFormat == null || !inputFormat.isCompatible(outputFormat)) {
            return false;
        }
        int maxOutputBitrate = getMaxOutputBitrate();
        int minInputBitrate = tiinput.getMinInputBitrate();
        if (minInputBitrate != -1 && maxOutputBitrate != -1 && minInputBitrate > maxOutputBitrate) {
            return false;
        }
        int minOutputBitrate = getMinOutputBitrate();
        int maxInputBitrate = tiinput.getMaxInputBitrate();
        if (maxInputBitrate == -1 || minOutputBitrate == -1 || maxInputBitrate >= minOutputBitrate) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void outputDisabledChanged() {
        updateDisabled();
    }

    public Future<TBuffer> processBuffer(TBuffer tbuffer) {
        Promise promise = new Promise();
        if (tbuffer != null) {
            try {
                if (tbuffer.getFormat() != null) {
                    if (this.__processBufferPromises == null) {
                        if (getInputFormat() == null) {
                            setInputFormat(tbuffer.getFormat());
                        }
                        this.__processBufferPromises = new LinkedList<>();
                        addOnRaiseFrame(new IAction1<TFrame>() {
                            public void invoke(TFrame tframe) {
                                MediaPipe.this.processBufferPromise(tframe, tframe.getLastBuffer().keep(), (Exception) null);
                            }
                        });
                    }
                    if (tbuffer.getFormat().isCompatible(getInputFormat())) {
                        MediaFrame createFrame = createFrame(tbuffer);
                        synchronized (this.__processBufferPromisesLock) {
                            this.__processBufferPromises.addLast(new Pair(createFrame, promise));
                        }
                        try {
                            processFrame(createFrame);
                        } catch (Exception e) {
                            processBufferPromise(createFrame, (MediaBuffer) null, e);
                        }
                        return promise;
                    }
                    throw new RuntimeException(new Exception(StringExtensions.format("Buffer input format '{0}' is not compatible with input format '{1}'.", tbuffer.getFormat().getFullName(), getInputFormat().getFullName())));
                }
                throw new RuntimeException(new Exception("Cannot process buffer with null format."));
            } catch (Exception e2) {
                promise.reject(e2);
            }
        } else {
            throw new RuntimeException(new Exception("Cannot process null buffer."));
        }
    }

    /* access modifiers changed from: private */
    public void processBufferPromise(TFrame tframe, TBuffer tbuffer, Exception exc) {
        ArrayList arrayList;
        Promise promise;
        synchronized (this.__processBufferPromisesLock) {
            LinkedListNode<Pair<TFrame, Promise<TBuffer>>> first = this.__processBufferPromises.getFirst();
            arrayList = null;
            promise = null;
            while (first != null) {
                TFrame tframe2 = (MediaFrame) first.getValue().getItem1();
                Promise promise2 = (Promise) first.getValue().getItem2();
                LinkedListNode<Pair<TFrame, Promise<TBuffer>>> next = first.getNext();
                if (tframe2 == tframe) {
                    this.__processBufferPromises.removeNode(first);
                    promise = promise2;
                } else if (tbuffer != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(promise2);
                    this.__processBufferPromises.removeNode(first);
                }
                first = next;
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Promise) it.next()).reject(new Exception("Buffer was discarded."));
            }
        }
        if (promise == null) {
            return;
        }
        if (tbuffer != null) {
            promise.resolve(tbuffer);
        } else {
            promise.reject(exc);
        }
    }

    public void processControlFrame(MediaControlFrame mediaControlFrame) {
        processControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrameResponse(MediaControlFrame mediaControlFrame) {
        processControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaPipeState.Initialized)) {
            this.__processingControlFrameResponses = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized)) {
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
                                    int normalizedMaximumBitrate = tmmbnEntry.getNormalizedMaximumBitrate();
                                    if (normalizedMaximumBitrate == 0) {
                                        setPaused(true);
                                    } else {
                                        updateMaxOutputBitrate(normalizedMaximumBitrate, 0);
                                        setPaused(false);
                                    }
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
                    __log.error(StringExtensions.format("Media pipe ({0}) could not process control frame responses.", (Object) getLabel()), e);
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

    public void processControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaPipeState.Initialized)) {
            this.__processingControlFrames = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized)) {
                    IAction1<MediaControlFrame[]> iAction1 = this._onProcessControlFrames;
                    if (iAction1 != null) {
                        iAction1.invoke(mediaControlFrameArr);
                    }
                    for (MediaControlFrame tryCast : mediaControlFrameArr) {
                        TmmbrControlFrame tmmbrControlFrame = (TmmbrControlFrame) Global.tryCast(tryCast, TmmbrControlFrame.class);
                        if (tmmbrControlFrame != null) {
                            for (TmmbrEntry tmmbrEntry : tmmbrControlFrame.getEntries()) {
                                if (tmmbrEntry.getSynchronizationSource() == getOutputSynchronizationSource()) {
                                    updateMaxOutputBitrate(tmmbrEntry.getNormalizedMaximumBitrate(), tmmbrControlFrame.getPacketSenderSynchronizationSource());
                                }
                            }
                        }
                    }
                    doProcessControlFrames(mediaControlFrameArr);
                    IAction1<MediaControlFrame[]> iAction12 = this._onProcessedControlFrames;
                    if (iAction12 != null) {
                        iAction12.invoke(mediaControlFrameArr);
                    }
                }
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media pipe ({0}) could not process control frames.", (Object) getLabel()), e);
                } catch (Throwable th) {
                    this.__processingControlFrames = false;
                    throw th;
                }
            } catch (Throwable th2) {
                IAction1<MediaControlFrame[]> iAction13 = this._onProcessedControlFrames;
                if (iAction13 != null) {
                    iAction13.invoke(mediaControlFrameArr);
                }
                throw th2;
            }
            this.__processingControlFrames = false;
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
        if (Global.equals(this.__state, MediaPipeState.Initialized) && !getInputDeactivated()) {
            this.__processingFrame = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized) && !getInputDeactivated() && (buffer = tframe.getBuffer(getInputFormat())) != null) {
                    if (StringExtensions.isNullOrEmpty(buffer.getSourceId())) {
                        buffer.setSourceId(getId());
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
                    DurationTimer lazyGetDurationTimer = lazyGetDurationTimer();
                    if (lazyGetDurationTimer != null) {
                        tframe.setDurationSample(lazyGetDurationTimer.beginSample());
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
                    __log.error(StringExtensions.format("Media pipe ({0}) could not process frame.", (Object) getLabel()), e);
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
        Error doProcessSdpMediaDescription = doProcessSdpMediaDescription(mediaDescription, z, z2);
        for (IMediaInput iMediaInput : getOutputs()) {
            if (doProcessSdpMediaDescription != null) {
                return doProcessSdpMediaDescription;
            }
            doProcessSdpMediaDescription = iMediaInput.processSdpMediaDescriptionFromInput(mediaDescription, z, z2);
        }
        return null;
    }

    public Error processSdpMediaDescriptionFromOutput(MediaDescription mediaDescription, boolean z, boolean z2) {
        Error doProcessSdpMediaDescription = doProcessSdpMediaDescription(mediaDescription, z, z2);
        for (IMediaOutput iMediaOutput : getInputs()) {
            if (doProcessSdpMediaDescription != null) {
                return doProcessSdpMediaDescription;
            }
            doProcessSdpMediaDescription = iMediaOutput.processSdpMediaDescriptionFromOutput(mediaDescription, z, z2);
        }
        return null;
    }

    public void processSinkStatsFromInput(MediaSinkStats mediaSinkStats) {
        doProcessSinkStatsFromInput(mediaSinkStats);
        for (IMediaInput processSinkStatsFromInput : getOutputs()) {
            processSinkStatsFromInput.processSinkStatsFromInput(mediaSinkStats);
        }
    }

    public void processSourceStatsFromOutput(MediaSourceStats mediaSourceStats) {
        doProcessSourceStatsFromOutput(mediaSourceStats);
        for (IMediaOutput processSourceStatsFromOutput : getInputs()) {
            processSourceStatsFromOutput.processSourceStatsFromOutput(mediaSourceStats);
        }
    }

    public void processTrackStatsFromInput(MediaTrackStats mediaTrackStats) {
        mediaTrackStats.setMuted(mediaTrackStats.getMuted() || (!getDisabled() && (getInputMuted() || getOutputMuted())));
        doProcessTrackStatsFromInput(mediaTrackStats);
        for (IMediaInput processTrackStatsFromInput : getOutputs()) {
            processTrackStatsFromInput.processTrackStatsFromInput(mediaTrackStats);
        }
    }

    public void processTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        mediaTrackStats.setMuted(mediaTrackStats.getMuted() || (!getDisabled() && (getInputMuted() || getOutputMuted())));
        doProcessTrackStatsFromOutput(mediaTrackStats);
        for (IMediaOutput processTrackStatsFromOutput : getInputs()) {
            processTrackStatsFromOutput.processTrackStatsFromOutput(mediaTrackStats);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrame(MediaControlFrame mediaControlFrame) {
        raiseControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponse(MediaControlFrame mediaControlFrame) {
        raiseControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaPipeState.Initialized)) {
            this.__raisingControlFrameResponses = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized)) {
                    for (FeedbackControlFrame feedbackControlFrame : mediaControlFrameArr) {
                        if (feedbackControlFrame instanceof FeedbackControlFrame) {
                            FeedbackControlFrame feedbackControlFrame2 = feedbackControlFrame;
                            if (feedbackControlFrame2.getMediaSourceSynchronizationSource() == 0 && getOutputSynchronizationSource() != -1) {
                                feedbackControlFrame2.setMediaSourceSynchronizationSource(getOutputSynchronizationSource());
                            }
                        }
                    }
                    IAction1<MediaControlFrame[]> iAction1 = this._onRaiseControlFrameResponses;
                    if (iAction1 != null) {
                        iAction1.invoke(mediaControlFrameArr);
                    }
                    for (IMediaInput processControlFrameResponses : getOutputs()) {
                        processControlFrameResponses.processControlFrameResponses(mediaControlFrameArr);
                    }
                    IAction1<MediaControlFrame[]> iAction12 = this._onRaisedControlFrameResponses;
                    if (iAction12 != null) {
                        iAction12.invoke(mediaControlFrameArr);
                    }
                }
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media pipe ({0}) could not raise control frame responses.", (Object) getLabel()), e);
                } catch (Throwable th) {
                    this.__raisingControlFrameResponses = false;
                    throw th;
                }
            } catch (Throwable th2) {
                IAction1<MediaControlFrame[]> iAction13 = this._onRaisedControlFrameResponses;
                if (iAction13 != null) {
                    iAction13.invoke(mediaControlFrameArr);
                }
                throw th2;
            }
            this.__raisingControlFrameResponses = false;
        }
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaPipeState.Initialized)) {
            this.__raisingControlFrames = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized)) {
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
                    __log.error(StringExtensions.format("Media pipe ({0}) could not raise control frames.", (Object) getLabel()), e);
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

    /* access modifiers changed from: protected */
    public void raiseFrame(TFrame tframe) {
        MediaBuffer lastBuffer;
        if (getOutputFormat() == null && (lastBuffer = tframe.getLastBuffer()) != null) {
            setOutputFormat(lastBuffer.getFormat());
        }
        DurationTimer lazyGetDurationTimer = lazyGetDurationTimer();
        if (!(lazyGetDurationTimer == null || tframe.getDurationSample() == null)) {
            lazyGetDurationTimer.endSample(tframe.getDurationSample());
            tframe.setDurationSample((DurationSample) null);
        }
        RateTimer lazyGetOutputRateTimer = lazyGetOutputRateTimer();
        if (lazyGetOutputRateTimer != null) {
            lazyGetOutputRateTimer.addTick();
        }
        if (Global.equals(this.__state, MediaPipeState.Initialized) && !getOutputDeactivated()) {
            this.__raisingFrame = true;
            try {
                if (Global.equals(this.__state, MediaPipeState.Initialized) && !getOutputDeactivated()) {
                    MediaBuffer lastBuffer2 = tframe.getLastBuffer();
                    if (lastBuffer2 != null) {
                        if (StringExtensions.isNullOrEmpty(lastBuffer2.getSourceId())) {
                            lastBuffer2.setSourceId(getId());
                        }
                        if (getOutputMuted()) {
                            lastBuffer2.mute();
                        }
                    }
                    if (getOutputSynchronizationSource() != -1) {
                        tframe.setSynchronizationSource(getOutputSynchronizationSource());
                    }
                    if (getOutputRtpStreamId() != null) {
                        tframe.setRtpStreamId(getOutputRtpStreamId());
                    }
                    int clockRate = getInputFormat().getClockRate();
                    int clockRate2 = getOutputFormat().getClockRate();
                    if (clockRate != clockRate2) {
                        if (this.__baseTimestamp == -1) {
                            this.__baseTimestamp = tframe.getTimestamp();
                        }
                        if (this.__baseRtpTimestamp == -1) {
                            this.__baseRtpTimestamp = tframe.getRtpTimestamp();
                        }
                        tframe.updateTimestamp(this.__baseTimestamp, this.__baseRtpTimestamp, clockRate, clockRate2);
                    }
                    IAction1<TFrame> iAction1 = this._onRaiseFrame;
                    if (iAction1 != null) {
                        iAction1.invoke(tframe);
                    }
                    if (!tframe.getDiscard()) {
                        IMediaInput[] raiseFrameOutputs = getRaiseFrameOutputs();
                        if (raiseFrameOutputs != null) {
                            int length = ArrayExtensions.getLength((Object[]) raiseFrameOutputs);
                            for (int i = 0; i < ArrayExtensions.getLength((Object[]) raiseFrameOutputs); i++) {
                                IMediaInput iMediaInput = raiseFrameOutputs[i];
                                if (iMediaInput.getDisabled() || (!getOutputFormat().getIsInjected() && !getOutputFormat().isCompatible(iMediaInput.getInputFormat()))) {
                                    raiseFrameOutputs[i] = null;
                                    length--;
                                }
                            }
                            if (length > 1) {
                                for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) raiseFrameOutputs); i2++) {
                                    IMediaInput iMediaInput2 = raiseFrameOutputs[i2];
                                    if (iMediaInput2 != null) {
                                        iMediaInput2.processFrame(tframe.clone());
                                    }
                                }
                            } else if (length == 1) {
                                for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) raiseFrameOutputs); i3++) {
                                    IMediaInput iMediaInput3 = raiseFrameOutputs[i3];
                                    if (iMediaInput3 != null) {
                                        iMediaInput3.processFrame(tframe);
                                    }
                                }
                            } else if (Log.getIsVerboseEnabled()) {
                                if (ArrayExtensions.getLength((Object[]) raiseFrameOutputs) > 0) {
                                    __log.verbose("Discarding frame. All outputs disabled.");
                                } else {
                                    __log.verbose("Discarding frame. No outputs.");
                                }
                            }
                        }
                        IAction1<TFrame> iAction12 = this._onRaisedFrame;
                        if (iAction12 != null) {
                            iAction12.invoke(tframe);
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    __log.error(StringExtensions.format("Media pipe ({0}) could not raise frame.", (Object) getLabel()), e);
                } catch (Throwable th) {
                    this.__raisingFrame = false;
                    throw th;
                }
            } catch (Throwable th2) {
                IAction1<TFrame> iAction13 = this._onRaisedFrame;
                if (iAction13 != null) {
                    iAction13.invoke(tframe);
                }
                throw th2;
            }
            this.__raisingFrame = false;
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

    public void removeOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessControlFrames.remove(iAction1);
        if (this.__onProcessControlFrames.size() == 0) {
            this._onProcessControlFrames = null;
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

    public void removeOnProcessedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onProcessedControlFrames, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onProcessedControlFrames.remove(iAction1);
        if (this.__onProcessedControlFrames.size() == 0) {
            this._onProcessedControlFrames = null;
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

    public void removeOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseControlFrameResponses.remove(iAction1);
        if (this.__onRaiseControlFrameResponses.size() == 0) {
            this._onRaiseControlFrameResponses = null;
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

    public void removeOnRaisedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaisedControlFrameResponses, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaisedControlFrameResponses.remove(iAction1);
        if (this.__onRaisedControlFrameResponses.size() == 0) {
            this._onRaisedControlFrameResponses = null;
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

    public void removeOnRaisedFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaisedFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaisedFrame.remove(iAction1);
        if (this.__onRaisedFrame.size() == 0) {
            this._onRaisedFrame = null;
        }
    }

    public void removeOnRaiseFrame(IAction1<TFrame> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onRaiseFrame, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onRaiseFrame.remove(iAction1);
        if (this.__onRaiseFrame.size() == 0) {
            this._onRaiseFrame = null;
        }
    }

    public void removeOnStateChange(IAction1<TPipe> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public boolean removeOutput(TIInput tiinput) {
        if (tiinput == null) {
            throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null output from {0}.", (Object) getLabel())));
        } else if (!this.__outputs.remove(tiinput)) {
            return false;
        } else {
            outputRemoved(tiinput);
            tiinput.removeOnDisabledChange(new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.MediaPipe<TIOutput,TIOutputCollection,TIInput,TIInputCollection,TPipe,TFrame,TBuffer,TBufferCollection,TFormat>.outputDisabledChanged";
                }

                public void invoke() {
                    MediaPipe.this.outputDisabledChanged();
                }
            });
            updateDisabled();
            return true;
        }
    }

    public void removeOutputs() {
        removeOutputs((TIInput[]) getOutputs());
    }

    public void removeOutputs(TIInput[] tiinputArr) {
        if (tiinputArr != null) {
            for (TIInput removeOutput : tiinputArr) {
                removeOutput(removeOutput);
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Cannot remove null outputs from {0}.", (Object) getLabel())));
    }

    public void setDeactivated(boolean z) {
        boolean deactivated = getDeactivated();
        this.__externalDeactivated = z;
        setInputDeactivated(z);
        setOutputDeactivated(z);
        if (deactivated && !z) {
            raiseControlFrame(TmmbrControlFrame.normalized(getMaxOutputBitrate(), getOutputSynchronizationSource(), getOutputSynchronizationSource()));
        }
    }

    public void setExternalId(String str) {
        this._externalId = str;
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
    public void setMaxOutputBitrate(int i) {
        this.__maxOutputBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputEncoding(EncodingInfo encodingInfo) {
        if (encodingInfo == null) {
            setOutputDeactivated(false);
            setMaxOutputBitrate(-1);
            return;
        }
        if (getOutputSynchronizationSource() < 0) {
            setOutputSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        setOutputDeactivated(encodingInfo.getDeactivated());
        setMaxOutputBitrate(encodingInfo.getBitrate());
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

    /* access modifiers changed from: protected */
    public void setMinOutputBitrate(int i) {
        this.__minOutputBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputEncoding(EncodingInfo encodingInfo) {
        if (encodingInfo == null) {
            setOutputDeactivated(false);
            setMinOutputBitrate(-1);
            return;
        }
        if (getOutputSynchronizationSource() < 0) {
            setOutputSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        setOutputDeactivated(encodingInfo.getDeactivated());
        setMinOutputBitrate(encodingInfo.getBitrate());
    }

    public void setMuted(boolean z) {
        setOutputMuted(z);
    }

    public void setOutput(TIInput tiinput) {
        removeOutputs();
        addOutput(tiinput);
    }

    public void setOutputDeactivated(boolean z) {
        this.__outputDeactivated = z;
        updateDisabled();
    }

    private void setOutputFormat(TFormat tformat) {
        this._outputFormat = tformat;
    }

    public void setOutputMuted(boolean z) {
        this._outputMuted = z;
    }

    public void setOutputRtpStreamId(String str) {
        this.__outputRtpStreamId = str;
    }

    public void setOutputs(TIInput[] tiinputArr) {
        removeOutputs();
        addOutputs(tiinputArr);
    }

    /* access modifiers changed from: protected */
    public void setOutputSynchronizable(boolean z) {
        this.__outputSynchronizable = true;
    }

    public void setOutputSynchronizationSource(long j) {
        this.__outputSynchronizationSource = j;
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
                        fm.liveswitch.MediaPipe r1 = fm.liveswitch.MediaPipe.this     // Catch:{ all -> 0x0016 }
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
                    throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaPipe.AnonymousClass20.invoke(fm.liveswitch.MediaFrame):void");
                }
            });
        }
        this.__processPolicy = processFramePolicy;
    }

    private void setState(MediaPipeState mediaPipeState) {
        synchronized (this.__stateLock) {
            if (!Global.equals(this.__state, mediaPipeState)) {
                this.__state = mediaPipeState;
                IAction1<TPipe> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
            }
        }
    }

    public void setSynchronizationSource(long j) {
        setOutputSynchronizationSource(j);
    }

    /* access modifiers changed from: protected */
    public void setSystemDelay(long j) {
        this._systemDelay = j;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputBitrate(int i) {
        this.__targetOutputBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputEncoding(EncodingInfo encodingInfo) {
        if (encodingInfo == null) {
            setOutputDeactivated(false);
            setTargetOutputBitrate(-1);
            return;
        }
        if (getOutputSynchronizationSource() < 0) {
            setOutputSynchronizationSource(encodingInfo.getSynchronizationSource());
        }
        setOutputDeactivated(encodingInfo.getDeactivated());
        setTargetOutputBitrate(encodingInfo.getBitrate());
    }

    public String toString() {
        return getLabel();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateDisabled() {
        /*
            r5 = this;
            boolean r0 = r5.getInputDeactivated()
            r1 = 0
            if (r0 != 0) goto L_0x002c
            boolean r0 = r5.getOutputDeactivated()
            if (r0 != 0) goto L_0x002c
            fm.liveswitch.MediaFormat r0 = r5.getOutputFormat()
            boolean r0 = r0.getIsInjected()
            if (r0 == 0) goto L_0x0018
            goto L_0x002d
        L_0x0018:
            fm.liveswitch.IMediaInput[] r0 = r5.getOutputs()
            int r2 = r0.length
            r3 = 0
        L_0x001e:
            if (r3 >= r2) goto L_0x002c
            r4 = r0[r3]
            boolean r4 = r5.outputCanProcessFrame(r4)
            if (r4 == 0) goto L_0x0029
            goto L_0x002d
        L_0x0029:
            int r3 = r3 + 1
            goto L_0x001e
        L_0x002c:
            r1 = 1
        L_0x002d:
            boolean r0 = r5.__disabled
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)
            if (r0 != 0) goto L_0x0042
            r5.__disabled = r1
            r5.raiseDisabledChange()
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaPipe.updateDisabled():boolean");
    }

    private boolean updateMaxOutputBitrate(int i, long j) {
        boolean deactivated = getDeactivated();
        if (getCanPauseBitrate()) {
            this.__internalDeactivated = i == 0;
            if (this.__internalDeactivated || !this.__externalDeactivated) {
                this.__inputDeactivated = this.__internalDeactivated;
                this.__outputDeactivated = this.__internalDeactivated;
            }
        }
        boolean updateDisabled = updateDisabled();
        if (i != 0 && getCanChangeBitrate()) {
            if (i != -1) {
                i = ConstraintUtility.max(i, getMinOutputBitrate());
            }
            int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i2 == 0 || j == getOutputSynchronizationSource()) {
                this.__localMaxOutputBitrate = i;
            } else {
                this.__remoteMaxOutputBitrate = i;
            }
            setMaxOutputBitrate(ConstraintUtility.min(this.__localMaxOutputBitrate, this.__remoteMaxOutputBitrate));
            if (i2 != 0 && !deactivated && Global.equals(Boolean.valueOf(deactivated), Boolean.valueOf(getDeactivated())) && !updateDisabled) {
                raiseControlFrameResponse(TmmbnControlFrame.normalized(getMaxOutputBitrate(), getOutputSynchronizationSource()));
            }
        }
        return updateDisabled;
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

    private void validateOutput(TIInput tiinput) {
        if (!(tiinput instanceof Stream) && tiinput.getInputFormat() != null && !tiinput.getInputFormat().isCompatible(getOutputFormat())) {
            throw new RuntimeException(new Exception(StringExtensions.concat((Object[]) new String[]{"Input format [", tiinput.getInputFormat().toString(), "] of output (", tiinput.getLabel(), ") is not compatible with output format [", getOutputFormat().toString(), "] of self (", getLabel(), ")."})));
        }
    }
}
