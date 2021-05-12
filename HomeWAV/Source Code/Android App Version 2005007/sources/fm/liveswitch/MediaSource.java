package fm.liveswitch;

import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaSource;
import fm.liveswitch.diagnostics.RateTimer;
import fm.liveswitch.diagnostics.Timers;
import fm.liveswitch.sdp.MediaDescription;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MediaSource<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIInputCollection>, TSource extends MediaSource<TIOutput, TIInput, TIInputCollection, TSource, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends MediaSourceBase implements IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, IMediaElement, IElement {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(MediaSource.class);
    private boolean __disabled = true;
    private volatile boolean __externalDeactivated = false;
    /* access modifiers changed from: private */
    public SourceInput __input = null;
    private volatile boolean __internalDeactivated = false;
    private int __localMaxOutputBitrate = -1;
    private int __maxOutputBitrate = -1;
    private int __minOutputBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onDisabledChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onPausedChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onProcessedControlFrames = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaiseControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaiseFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<MediaControlFrame[]>> __onRaisedControlFrameResponses = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TFrame>> __onRaisedFrame = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TSource>> __onStateChange = new ArrayList();
    private boolean __outputDeactivated = false;
    private RateTimer __outputRateTimer;
    private String __outputRtpStreamId = null;
    private long __outputSynchronizationSource = -1;
    private TIInputCollection __outputs;
    private boolean __paused = false;
    private volatile boolean __processingControlFrames = false;
    private TIInput[] __raiseFrameOutputs;
    private volatile boolean __raisingControlFrameResponses = false;
    private volatile boolean __raisingFrame = false;
    private int __remoteMaxOutputBitrate = -1;
    private MediaSourceState __state = MediaSourceState.New;
    /* access modifiers changed from: private */
    public Object __stateLock = new Object();
    private int __targetOutputBitrate = -1;
    private IAction0 _onDisabledChange = null;
    private IAction0 _onPausedChange = null;
    private IAction1<MediaControlFrame[]> _onProcessControlFrames = null;
    private IAction1<MediaControlFrame[]> _onProcessedControlFrames = null;
    private IAction1<MediaControlFrame[]> _onRaiseControlFrameResponses = null;
    private IAction1<TFrame> _onRaiseFrame = null;
    private IAction1<MediaControlFrame[]> _onRaisedControlFrameResponses = null;
    private IAction1<TFrame> _onRaisedFrame = null;
    private IAction1<TSource> _onStateChange = null;
    private TFormat _outputFormat;
    private boolean _outputMuted;
    private boolean _outputSynchronizable;
    private boolean _persistent;
    private long _systemDelay;

    /* access modifiers changed from: protected */
    public abstract TIInputCollection createInputCollection(TIOutput tioutput);

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    /* access modifiers changed from: protected */
    public void doProcessControlFrames(MediaControlFrame[] mediaControlFrameArr) {
    }

    /* access modifiers changed from: protected */
    public Error doProcessSdpMediaDescription(MediaDescription mediaDescription, boolean z, boolean z2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void doProcessSourceStatsFromOutput(MediaSourceStats mediaSourceStats) {
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
    }

    /* access modifiers changed from: protected */
    public abstract Future<Object> doStart();

    /* access modifiers changed from: protected */
    public abstract Future<Object> doStop();

    /* access modifiers changed from: protected */
    public boolean getAllowDiagnosticTimer() {
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

    public int getCcmSequenceNumber() {
        return 0;
    }

    public abstract String getLabel();

    public void incrementCcmSequenceNumber() {
    }

    /* access modifiers changed from: protected */
    public void outputAdded(TIInput tiinput) {
    }

    /* access modifiers changed from: protected */
    public void outputRemoved(TIInput tiinput) {
    }

    public void addOnDisabledChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onDisabledChange == null) {
                this._onDisabledChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaSource.this.__onDisabledChange).iterator();
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
                        Iterator it = new ArrayList(MediaSource.this.__onPausedChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onPausedChange.add(iAction0);
        }
    }

    public void addOnProcessControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessControlFrames == null) {
                this._onProcessControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSource.this.__onProcessControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessControlFrames.add(iAction1);
        }
    }

    public void addOnProcessedControlFrames(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onProcessedControlFrames == null) {
                this._onProcessedControlFrames = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSource.this.__onProcessedControlFrames).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onProcessedControlFrames.add(iAction1);
        }
    }

    public void addOnRaiseControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaiseControlFrameResponses == null) {
                this._onRaiseControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSource.this.__onRaiseControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaiseControlFrameResponses.add(iAction1);
        }
    }

    public void addOnRaisedControlFrameResponses(IAction1<MediaControlFrame[]> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedControlFrameResponses == null) {
                this._onRaisedControlFrameResponses = new IAction1<MediaControlFrame[]>() {
                    public void invoke(MediaControlFrame[] mediaControlFrameArr) {
                        Iterator it = new ArrayList(MediaSource.this.__onRaisedControlFrameResponses).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(mediaControlFrameArr);
                        }
                    }
                };
            }
            this.__onRaisedControlFrameResponses.add(iAction1);
        }
    }

    public void addOnRaisedFrame(IAction1<TFrame> iAction1) {
        if (iAction1 != null) {
            if (this._onRaisedFrame == null) {
                this._onRaisedFrame = new IAction1<TFrame>() {
                    public void invoke(TFrame tframe) {
                        Iterator it = new ArrayList(MediaSource.this.__onRaisedFrame).iterator();
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
                        Iterator it = new ArrayList(MediaSource.this.__onRaiseFrame).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tframe);
                        }
                    }
                };
            }
            this.__onRaiseFrame.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<TSource> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<TSource>() {
                    public void invoke(TSource tsource) {
                        Iterator it = new ArrayList(MediaSource.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tsource);
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
                        return "fm.liveswitch.MediaSource<TIOutput,TIInput,TIInputCollection,TSource,TFrame,TBuffer,TBufferCollection,TFormat>.outputDisabledChanged";
                    }

                    public void invoke() {
                        MediaSource.this.outputDisabledChanged();
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

    public Future<Object> changeInput(SourceInput sourceInput) {
        return doChangeInput(new Promise(), sourceInput);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Media source ({0}) is being destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        doDestroy();
        r0 = r5.__outputRateTimer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r0 == null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        r0.destroy();
        r5.__outputRateTimer = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        r0 = r5.__stateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        setState(fm.liveswitch.MediaSourceState.Destroyed);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r5.__outputs.destroy();
        __log.verbose(fm.liveswitch.StringExtensions.format("Media source ({0}) has been destroyed.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0065, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0068, code lost:
        monitor-enter(r5.__stateLock);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        setState(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006d, code lost:
        __log.error(fm.liveswitch.StringExtensions.format("Media source ({0}) could not be destroyed.", (java.lang.Object) getLabel()), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean destroy() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__stateLock
            monitor-enter(r0)
            fm.liveswitch.MediaSourceState r1 = r5.getState()     // Catch:{ all -> 0x00b5 }
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Starting     // Catch:{ all -> 0x00b5 }
            if (r1 == r2) goto L_0x00a8
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Started     // Catch:{ all -> 0x00b5 }
            if (r1 == r2) goto L_0x009b
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Stopping     // Catch:{ all -> 0x00b5 }
            if (r1 == r2) goto L_0x008e
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroying     // Catch:{ all -> 0x00b5 }
            if (r1 == r2) goto L_0x0081
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroyed     // Catch:{ all -> 0x00b5 }
            r3 = 1
            if (r1 != r2) goto L_0x001e
            monitor-exit(r0)     // Catch:{ all -> 0x00b5 }
            return r3
        L_0x001e:
            fm.liveswitch.MediaSourceState r1 = r5.getState()     // Catch:{ all -> 0x00b5 }
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroying     // Catch:{ all -> 0x00b5 }
            r5.setState(r2)     // Catch:{ all -> 0x00b5 }
            monitor-exit(r0)     // Catch:{ all -> 0x00b5 }
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Media source ({0}) is being destroyed."
            java.lang.String r4 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)
            r0.verbose(r2)
            r5.doDestroy()     // Catch:{ Exception -> 0x0065 }
            fm.liveswitch.diagnostics.RateTimer r0 = r5.__outputRateTimer     // Catch:{ Exception -> 0x0065 }
            if (r0 == 0) goto L_0x0044
            r0.destroy()     // Catch:{ Exception -> 0x0065 }
            r0 = 0
            r5.__outputRateTimer = r0     // Catch:{ Exception -> 0x0065 }
        L_0x0044:
            java.lang.Object r0 = r5.__stateLock     // Catch:{ Exception -> 0x0065 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0065 }
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroyed     // Catch:{ all -> 0x0062 }
            r5.setState(r2)     // Catch:{ all -> 0x0062 }
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            TIInputCollection r0 = r5.__outputs     // Catch:{ Exception -> 0x0065 }
            r0.destroy()     // Catch:{ Exception -> 0x0065 }
            fm.liveswitch.ILog r0 = __log     // Catch:{ Exception -> 0x0065 }
            java.lang.String r2 = "Media source ({0}) has been destroyed."
            java.lang.String r4 = r5.getLabel()     // Catch:{ Exception -> 0x0065 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0065 }
            r0.verbose(r2)     // Catch:{ Exception -> 0x0065 }
            return r3
        L_0x0062:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            throw r2     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            r0 = move-exception
            java.lang.Object r2 = r5.__stateLock
            monitor-enter(r2)
            r5.setState(r1)     // Catch:{ all -> 0x007e }
            monitor-exit(r2)     // Catch:{ all -> 0x007e }
            fm.liveswitch.ILog r1 = __log
            java.lang.String r2 = "Media source ({0}) could not be destroyed."
            java.lang.String r3 = r5.getLabel()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r1.error((java.lang.String) r2, (java.lang.Exception) r0)
            r0 = 0
            return r0
        L_0x007e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x007e }
            throw r0
        L_0x0081:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b5 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "A media source cannot be destroyed while it is being destroyed on a different thread."
            r2.<init>(r3)     // Catch:{ all -> 0x00b5 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b5 }
            throw r1     // Catch:{ all -> 0x00b5 }
        L_0x008e:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b5 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "A media source cannot be destroyed while it is being stopped."
            r2.<init>(r3)     // Catch:{ all -> 0x00b5 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b5 }
            throw r1     // Catch:{ all -> 0x00b5 }
        L_0x009b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b5 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "A media source cannot be destroyed while it is started. (Stop the media source first.)"
            r2.<init>(r3)     // Catch:{ all -> 0x00b5 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b5 }
            throw r1     // Catch:{ all -> 0x00b5 }
        L_0x00a8:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b5 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "A media source cannot be destroyed while it is being started."
            r2.<init>(r3)     // Catch:{ all -> 0x00b5 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b5 }
            throw r1     // Catch:{ all -> 0x00b5 }
        L_0x00b5:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00b5 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaSource.destroy():boolean");
    }

    private Future<Object> doChangeInput(final Promise<Object> promise, final SourceInput sourceInput) {
        if (Global.equals(sourceInput, this.__input)) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    promise.resolve(null);
                }
            });
        } else if (Global.equals(getState(), MediaSourceState.Started)) {
            AnonymousClass12 r0 = new IFunction1<Object, Future<Object>>() {
                public Future<Object> invoke(Object obj) {
                    SourceInput unused = MediaSource.this.__input = sourceInput;
                    return MediaSource.this.start();
                }
            };
            AnonymousClass13 r6 = new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            };
            stop().then(r0, (IAction1<Exception>) r6).then(new IAction1<Object>() {
                public void invoke(Object obj) {
                    promise.resolve(null);
                }
            }, (IAction1<Exception>) new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            });
        } else {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    SourceInput unused = MediaSource.this.__input = sourceInput;
                    promise.resolve(null);
                }
            });
        }
        return promise;
    }

    public boolean getDeactivated() {
        return getOutputDeactivated();
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

    public SourceInput getInput() {
        return this.__input;
    }

    public Future<SourceInput[]> getInputs() {
        Promise promise = new Promise();
        promise.resolve(new SourceInput[0]);
        return promise;
    }

    public boolean getIsDestroyed() {
        return Global.equals(getState(), MediaSourceState.Destroyed);
    }

    public boolean getIsStarted() {
        return Global.equals(getState(), MediaSourceState.Started);
    }

    public boolean getIsStopped() {
        return Global.equals(getState(), MediaSourceState.Stopped);
    }

    public int getMaxOutputBitrate() {
        return this.__maxOutputBitrate;
    }

    public EncodingInfo getMaxOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getMaxOutputBitrate());
        return encodingInfo;
    }

    public int getMinOutputBitrate() {
        return this.__minOutputBitrate;
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
        return this.__outputRtpStreamId;
    }

    public TIInput[] getOutputs() {
        return (IMediaInput[]) this.__outputs.getValues();
    }

    public boolean getOutputSynchronizable() {
        return this._outputSynchronizable;
    }

    public long getOutputSynchronizationSource() {
        return this.__outputSynchronizationSource;
    }

    public boolean getOverConstrained() {
        return getOverConstrainedOutput();
    }

    public boolean getOverConstrainedBitrate() {
        return getOverConstrainedOutputBitrate();
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
        return StringExtensions.concat((Object[]) new String[]{"{ ", getPipelineJsonBase(), ", ", getPipelineJsonOutputs(), " }"});
    }

    private String getPipelineJsonBase() {
        return StringExtensions.concat((Object[]) new String[]{getPipelineJsonId(), ", ", getPipelineJsonLabel(), ", ", getPipelineJsonTag(), ", ", getPipelineJsonOutput()});
    }

    public String getPipelineJsonFromOutput() {
        return StringExtensions.concat("{ ", getPipelineJsonBase(), " }");
    }

    private String getPipelineJsonId() {
        return StringExtensions.concat("\"id\": ", JsonSerializer.serializeString(super.getId()));
    }

    private String getPipelineJsonLabel() {
        return StringExtensions.concat("\"label\": ", JsonSerializer.serializeString(getLabel()));
    }

    private String getPipelineJsonOutput() {
        return StringExtensions.concat("\"outputFormat\": ", getOutputFormat() == null ? "null" : JsonSerializer.serializeString(getOutputFormat().toString()));
    }

    private String getPipelineJsonOutputs() {
        ArrayList arrayList = new ArrayList();
        for (IMediaInput pipelineJsonFromInput : getOutputs()) {
            arrayList.add(pipelineJsonFromInput.getPipelineJsonFromInput());
        }
        return StringExtensions.concat("\"outputs\": [", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    private String getPipelineJsonTag() {
        return StringExtensions.concat("\"tag\": ", JsonSerializer.serializeString(super.getTag()));
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

    public MediaSourceState getState() {
        return this.__state;
    }

    public long getSynchronizationSource() {
        return getOutputSynchronizationSource();
    }

    public long getSystemDelay() {
        return this._systemDelay;
    }

    public int getTargetOutputBitrate() {
        return ConstraintUtility.clampMin(this.__targetOutputBitrate, getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public EncodingInfo getTargetOutputEncoding() {
        EncodingInfo encodingInfo = new EncodingInfo();
        encodingInfo.setSynchronizationSource(getOutputSynchronizationSource());
        encodingInfo.setDeactivated(getOutputDeactivated());
        encodingInfo.setBitrate(getTargetOutputBitrate());
        return encodingInfo;
    }

    public boolean hasOutput(TIInput tiinput) {
        for (TIInput tiinput2 : getOutputs()) {
            if (tiinput2 == tiinput) {
                return true;
            }
        }
        return false;
    }

    private RateTimer lazyGetOutputRateTimer() {
        if (this.__outputRateTimer == null && getAllowDiagnosticTimer() && getAllowOutputRateTimer()) {
            this.__outputRateTimer = Timers.getRateTimer(StringExtensions.format("{0} Output Rate ({1})", getLabel(), getOutputFormat() != null ? getOutputFormat().getName() : "null"));
        }
        return this.__outputRateTimer;
    }

    public MediaSource(TFormat tformat) {
        setOutputFormat(tformat);
        setOutputSynchronizationSource(Utility.generateSynchronizationSource());
        this.__outputs = createInputCollection(this);
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

    public void processControlFrame(MediaControlFrame mediaControlFrame) {
        processControlFrames(new MediaControlFrame[]{mediaControlFrame});
    }

    public void processControlFrames(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaSourceState.Started)) {
            this.__processingControlFrames = true;
            try {
                if (Global.equals(this.__state, MediaSourceState.Started)) {
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
                    __log.error(StringExtensions.format("Media source ({0}) could not process control frames.", (Object) getLabel()), e);
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

    public Error processSdpMediaDescriptionFromOutput(MediaDescription mediaDescription, boolean z, boolean z2) {
        return doProcessSdpMediaDescription(mediaDescription, z, z2);
    }

    public void processSourceStatsFromOutput(MediaSourceStats mediaSourceStats) {
        mediaSourceStats.setId(super.getId());
        mediaSourceStats.setTag(super.getTag());
        mediaSourceStats.setLabel(getLabel());
        mediaSourceStats.setMuted(mediaSourceStats.getMuted() || (!getDisabled() && getOutputMuted()));
        SourceInput input = getInput();
        if (input != null) {
            mediaSourceStats.setInputId(input.getId());
            mediaSourceStats.setInputName(input.getName());
        }
        MediaFormat outputFormat = getOutputFormat();
        if (outputFormat != null) {
            mediaSourceStats.setOutputFormat(outputFormat.getInfo());
        }
        doProcessSourceStatsFromOutput(mediaSourceStats);
    }

    public void processTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        mediaTrackStats.setMuted(mediaTrackStats.getMuted() || (!getDisabled() && getOutputMuted()));
        mediaTrackStats.setStopped(Global.equals(getState(), MediaSourceState.Stopped));
        doProcessTrackStatsFromOutput(mediaTrackStats);
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponse(MediaControlFrame mediaControlFrame) {
        raiseControlFrameResponses(new MediaControlFrame[]{mediaControlFrame});
    }

    /* access modifiers changed from: protected */
    public void raiseControlFrameResponses(MediaControlFrame[] mediaControlFrameArr) {
        if (Global.equals(this.__state, MediaSourceState.Started)) {
            this.__raisingControlFrameResponses = true;
            try {
                if (Global.equals(this.__state, MediaSourceState.Started)) {
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
                    __log.error(StringExtensions.format("Media source ({0}) could not raise control frame responses.", (Object) getLabel()), e);
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
        RateTimer lazyGetOutputRateTimer = lazyGetOutputRateTimer();
        if (lazyGetOutputRateTimer != null) {
            lazyGetOutputRateTimer.addTick();
        }
        if (Global.equals(this.__state, MediaSourceState.Started) && !getOutputDeactivated()) {
            this.__raisingFrame = true;
            try {
                if (Global.equals(this.__state, MediaSourceState.Started) && !getOutputDeactivated()) {
                    MediaBuffer lastBuffer2 = tframe.getLastBuffer();
                    if (lastBuffer2 != null) {
                        if (StringExtensions.isNullOrEmpty(lastBuffer2.getSourceId())) {
                            lastBuffer2.setSourceId(super.getId());
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
                    __log.error(StringExtensions.format("Media source ({0}) could not raise frame.", (Object) getLabel()), e);
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

    public void removeOnStateChange(IAction1<TSource> iAction1) {
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
                    return "fm.liveswitch.MediaSource<TIOutput,TIInput,TIInputCollection,TSource,TFrame,TBuffer,TBufferCollection,TFormat>.outputDisabledChanged";
                }

                public void invoke() {
                    MediaSource.this.outputDisabledChanged();
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
        this.__externalDeactivated = z;
        setOutputDeactivated(z);
    }

    public void setInput(SourceInput sourceInput) {
        if (!Global.equals(getState(), MediaSourceState.Started)) {
            this.__input = sourceInput;
            return;
        }
        throw new RuntimeException(new Exception("Cannot set input while the source is started. Use ChangeInput instead or stop the source first."));
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
        this._outputSynchronizable = z;
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

    /* access modifiers changed from: private */
    public void setState(MediaSourceState mediaSourceState) {
        synchronized (this.__stateLock) {
            if (!Global.equals(this.__state, mediaSourceState)) {
                this.__state = mediaSourceState;
                IAction1<TSource> iAction1 = this._onStateChange;
                if (iAction1 != null) {
                    iAction1.invoke(this);
                }
                setPaused(!Global.equals(mediaSourceState, MediaSourceState.Started));
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

    public Future<Object> start() {
        return startInternal(new Promise());
    }

    private Future<Object> startInternal(final Promise<Object> promise) {
        synchronized (this.__stateLock) {
            MediaSourceState state = getState();
            if (state == MediaSourceState.Starting) {
                promise.reject(new Exception("A media source cannot be started while it is being started on a different thread."));
                return promise;
            } else if (state == MediaSourceState.Started) {
                promise.resolve(null);
                return promise;
            } else if (state == MediaSourceState.Stopping) {
                promise.reject(new Exception("A media source cannot be started while it is being stopped."));
                return promise;
            } else if (state == MediaSourceState.Destroying) {
                promise.reject(new Exception("A media source cannot be started while it is being destroyed."));
                return promise;
            } else if (state == MediaSourceState.Destroyed) {
                promise.reject(new Exception("A media source cannot be started while it is destroyed."));
                return promise;
            } else {
                setState(MediaSourceState.Starting);
                __log.debug(StringExtensions.format("Media source ({0}) is being started.", (Object) getLabel()));
                doStart().then(new IAction1<Object>() {
                    public void invoke(Object obj) {
                        MediaSource.__log.debug(StringExtensions.format("Media source ({0}) has been started.", (Object) MediaSource.this.getLabel()));
                        synchronized (MediaSource.this.__stateLock) {
                            MediaSource.this.setState(MediaSourceState.Started);
                            promise.resolve(obj);
                        }
                    }
                }, (IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        MediaSource.__log.debug(StringExtensions.format("Media source ({0}) could not be started.", (Object) MediaSource.this.getLabel()));
                        synchronized (MediaSource.this.__stateLock) {
                            MediaSource.this.setState(MediaSourceState.Stopped);
                            promise.reject(exc);
                        }
                    }
                });
                return promise;
            }
        }
    }

    public Future<Object> stop() {
        return stopInternal(new Promise());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0060, code lost:
        __log.debug(fm.liveswitch.StringExtensions.format("Media source ({0}) is being stopped.", (java.lang.Object) getLabel()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0071, code lost:
        if (r4.__raisingFrame != false) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0075, code lost:
        if (r4.__processingControlFrames != false) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0079, code lost:
        if (r4.__raisingControlFrameResponses == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007c, code lost:
        doStop().then(new fm.liveswitch.MediaSource.AnonymousClass20(r4), (fm.liveswitch.IAction1<java.lang.Exception>) new fm.liveswitch.MediaSource.AnonymousClass21(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008d, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008e, code lost:
        fm.liveswitch.ManagedThread.sleep(10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Future<java.lang.Object> stopInternal(final fm.liveswitch.Promise<java.lang.Object> r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__stateLock
            monitor-enter(r0)
            fm.liveswitch.MediaSourceState r1 = r4.getState()     // Catch:{ all -> 0x0094 }
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.New     // Catch:{ all -> 0x0094 }
            r3 = 0
            if (r1 != r2) goto L_0x0011
            r5.resolve(r3)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x0011:
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Starting     // Catch:{ all -> 0x0094 }
            if (r1 != r2) goto L_0x0021
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x0094 }
            java.lang.String r2 = "A media source cannot be stopped while it is being started."
            r1.<init>(r2)     // Catch:{ all -> 0x0094 }
            r5.reject(r1)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x0021:
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Stopping     // Catch:{ all -> 0x0094 }
            if (r1 != r2) goto L_0x0031
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x0094 }
            java.lang.String r2 = "A media source cannot be stopped while it is being stopped on a different thread."
            r1.<init>(r2)     // Catch:{ all -> 0x0094 }
            r5.reject(r1)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x0031:
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Stopped     // Catch:{ all -> 0x0094 }
            if (r1 != r2) goto L_0x003a
            r5.resolve(r3)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x003a:
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroying     // Catch:{ all -> 0x0094 }
            if (r1 != r2) goto L_0x004a
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x0094 }
            java.lang.String r2 = "A media source cannot be stopped while it is being destroyed."
            r1.<init>(r2)     // Catch:{ all -> 0x0094 }
            r5.reject(r1)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x004a:
            fm.liveswitch.MediaSourceState r2 = fm.liveswitch.MediaSourceState.Destroyed     // Catch:{ all -> 0x0094 }
            if (r1 != r2) goto L_0x005a
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x0094 }
            java.lang.String r2 = "A media source cannot be stopped while it is destroyed."
            r1.<init>(r2)     // Catch:{ all -> 0x0094 }
            r5.reject(r1)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return r5
        L_0x005a:
            fm.liveswitch.MediaSourceState r1 = fm.liveswitch.MediaSourceState.Stopping     // Catch:{ all -> 0x0094 }
            r4.setState(r1)     // Catch:{ all -> 0x0094 }
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = "Media source ({0}) is being stopped."
            java.lang.String r2 = r4.getLabel()
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)
            r0.debug(r1)
        L_0x006f:
            boolean r0 = r4.__raisingFrame
            if (r0 != 0) goto L_0x008e
            boolean r0 = r4.__processingControlFrames
            if (r0 != 0) goto L_0x008e
            boolean r0 = r4.__raisingControlFrameResponses
            if (r0 == 0) goto L_0x007c
            goto L_0x008e
        L_0x007c:
            fm.liveswitch.Future r0 = r4.doStop()
            fm.liveswitch.MediaSource$20 r1 = new fm.liveswitch.MediaSource$20
            r1.<init>(r5)
            fm.liveswitch.MediaSource$21 r2 = new fm.liveswitch.MediaSource$21
            r2.<init>(r5)
            r0.then(r1, (fm.liveswitch.IAction1<java.lang.Exception>) r2)
            return r5
        L_0x008e:
            r0 = 10
            fm.liveswitch.ManagedThread.sleep(r0)
            goto L_0x006f
        L_0x0094:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaSource.stopInternal(fm.liveswitch.Promise):fm.liveswitch.Future");
    }

    public String toString() {
        return getLabel();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean updateDisabled() {
        /*
            r5 = this;
            boolean r0 = r5.getOutputDeactivated()
            r1 = 0
            if (r0 != 0) goto L_0x0026
            fm.liveswitch.MediaFormat r0 = r5.getOutputFormat()
            boolean r0 = r0.getIsInjected()
            if (r0 == 0) goto L_0x0012
            goto L_0x0027
        L_0x0012:
            fm.liveswitch.IMediaInput[] r0 = r5.getOutputs()
            int r2 = r0.length
            r3 = 0
        L_0x0018:
            if (r3 >= r2) goto L_0x0026
            r4 = r0[r3]
            boolean r4 = r5.outputCanProcessFrame(r4)
            if (r4 == 0) goto L_0x0023
            goto L_0x0027
        L_0x0023:
            int r3 = r3 + 1
            goto L_0x0018
        L_0x0026:
            r1 = 1
        L_0x0027:
            boolean r0 = r5.__disabled
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)
            if (r0 != 0) goto L_0x003c
            r5.__disabled = r1
            r5.raiseDisabledChange()
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaSource.updateDisabled():boolean");
    }

    private boolean updateMaxOutputBitrate(int i, long j) {
        boolean deactivated = getDeactivated();
        if (getCanPauseBitrate()) {
            this.__internalDeactivated = i == 0;
            if (this.__internalDeactivated || !this.__externalDeactivated) {
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

    private void validateOutput(TIInput tiinput) {
        if (!(tiinput instanceof Stream) && tiinput.getInputFormat() != null && !tiinput.getInputFormat().isCompatible(getOutputFormat())) {
            throw new RuntimeException(new Exception(StringExtensions.concat((Object[]) new String[]{"Input format [", tiinput.getInputFormat().toString(), "] of output (", tiinput.getLabel(), ") is not compatible with output format [", getOutputFormat().toString(), "] of self (", getLabel(), ")."})));
        }
    }
}
