package fm.liveswitch;

import fm.liveswitch.IMediaElement;
import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBranch;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaPipe;
import fm.liveswitch.MediaSink;
import fm.liveswitch.MediaSource;
import fm.liveswitch.MediaTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MediaTrack<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIOutputCollection>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIInputCollection>, TIElement extends IMediaElement, TSource extends MediaSource<TIOutput, TIInput, TIInputCollection, TSource, TFrame, TBuffer, TBufferCollection, TFormat>, TSink extends MediaSink<TIOutput, TIOutputCollection, TIInput, TSink, TFrame, TBuffer, TBufferCollection, TFormat>, TPipe extends MediaPipe<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TPipe, TFrame, TBuffer, TBufferCollection, TFormat>, TTrack extends MediaTrack<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TBranch extends MediaBranch<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends MediaTrackBase implements IMediaTrack, IMediaElement, IElement {
    private String __id = Utility.generateId();
    /* access modifiers changed from: private */
    public List<IAction0> __onDestroyed = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onStarted = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onStopped = new ArrayList();
    private String _externalId;
    private IAction0 _onDestroyed = null;
    private IAction0 _onStarted = null;
    private IAction0 _onStopped = null;
    private boolean _persistent;

    /* access modifiers changed from: protected */
    public abstract void addElement(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract TIInput[] arrayFromInputs(ArrayList<TIInput> arrayList);

    /* access modifiers changed from: protected */
    public abstract TIOutput[] arrayFromOutputs(ArrayList<TIOutput> arrayList);

    /* access modifiers changed from: protected */
    public abstract TSink[] arrayFromSinks(ArrayList<TSink> arrayList);

    /* access modifiers changed from: protected */
    public abstract TBranch branchFromTracks(TTrack[] ttrackArr);

    public abstract TIElement[] getElements();

    public abstract String getLabel();

    /* access modifiers changed from: protected */
    public abstract boolean isBranch(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isInput(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isOutput(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isPipe(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isSink(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isSource(TIElement tielement);

    /* access modifiers changed from: protected */
    public abstract boolean isStream(TIElement tielement);

    public void addOnDestroyed(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onDestroyed == null) {
                this._onDestroyed = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaTrack.this.__onDestroyed).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onDestroyed.add(iAction0);
        }
    }

    public void addOnStarted(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onStarted == null) {
                this._onStarted = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaTrack.this.__onStarted).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onStarted.add(iAction0);
        }
    }

    public void addOnStopped(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onStopped == null) {
                this._onStopped = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(MediaTrack.this.__onStopped).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onStopped.add(iAction0);
        }
    }

    private void addToBranch(TBranch tbranch, TIInput tiinput) {
        for (MediaTrack mediaTrack : tbranch.getTracks()) {
            if (mediaTrack != null && !mediaTrack.getIsTerminated() && !mediaTrack.getIsEmpty()) {
                if (mediaTrack.getIsBranched()) {
                    addToBranch((MediaBranch) mediaTrack.getLastElement(), tiinput);
                } else {
                    ((IMediaOutput) mediaTrack.getLastElement()).addOutput(tiinput);
                }
            }
        }
    }

    public Future<Object> changeSinkOutput(SinkOutput sinkOutput) {
        MediaSink activeSink = getActiveSink();
        if (activeSink != null) {
            return activeSink.changeOutput(sinkOutput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("Track has no active sink."));
        return promise;
    }

    public Future<Object> changeSourceInput(SourceInput sourceInput) {
        MediaSource source = getSource();
        if (source != null) {
            return source.changeInput(sourceInput);
        }
        Promise promise = new Promise();
        promise.reject(new Exception("Track has no source."));
        return promise;
    }

    public boolean destroy() {
        IAction0 iAction0;
        boolean z = false;
        for (IMediaElement iMediaElement : getElements()) {
            if (!iMediaElement.getPersistent() && iMediaElement.destroy()) {
                z = true;
            }
        }
        if (z && (iAction0 = this._onDestroyed) != null) {
            iAction0.invoke();
        }
        return z;
    }

    public TIElement findElement(IFunction1<TIElement, Boolean> iFunction1) {
        return findElement(iFunction1, true);
    }

    public TIElement findElement(IFunction1<TIElement, Boolean> iFunction1, boolean z) {
        ArrayList<TIElement> findElements = findElements(iFunction1, z);
        if (ArrayListExtensions.getCount(findElements) == 0) {
            return null;
        }
        return (IMediaElement) ArrayListExtensions.getItem(findElements).get(0);
    }

    public ArrayList<TIElement> findElements(IFunction1<TIElement, Boolean> iFunction1) {
        return findElements(iFunction1, true);
    }

    public ArrayList<TIElement> findElements(IFunction1<TIElement, Boolean> iFunction1, boolean z) {
        ArrayList<TIElement> arrayList = new ArrayList<>();
        for (IMediaElement iMediaElement : getElements()) {
            if (iFunction1.invoke(iMediaElement).booleanValue()) {
                arrayList.add(iMediaElement);
            }
            if (z && isBranch(iMediaElement)) {
                for (MediaTrack findElements : ((MediaBranch) iMediaElement).getTracks()) {
                    ArrayListExtensions.addRange(arrayList, findElements.findElements(iFunction1, z));
                }
            }
        }
        return arrayList;
    }

    public TSink getActiveSink() {
        MediaTrack activeTrack;
        TSink sink = getSink();
        if (sink != null && !sink.getDisabled()) {
            return sink;
        }
        IMediaElement lastElement = getLastElement();
        if (!isBranch(lastElement) || (activeTrack = ((MediaBranch) lastElement).getActiveTrack()) == null) {
            return null;
        }
        return activeTrack.getActiveSink();
    }

    public boolean getDeactivated() {
        IMediaElement firstElement = getFirstElement();
        return firstElement != null && firstElement.getDeactivated();
    }

    public boolean getDisabled() {
        IMediaElement firstElement = getFirstElement();
        return firstElement != null && firstElement.getDisabled();
    }

    public String getExternalId() {
        return this._externalId;
    }

    public TIElement getFirstElement() {
        return (IMediaElement) Utility.firstOrDefault((T[]) getElements());
    }

    public String getId() {
        return this.__id;
    }

    public TIInput getInput() {
        TIInput[] inputs = getInputs();
        if (ArrayExtensions.getLength((Object[]) inputs) == 0) {
            return null;
        }
        return inputs[0];
    }

    public TFormat getInputFormat() {
        IMediaElement firstElement = getFirstElement();
        if (firstElement == null || !isInput(firstElement)) {
            return null;
        }
        return ((IMediaInput) firstElement).getInputFormat();
    }

    public TIInput[] getInputs() {
        ArrayList arrayList = new ArrayList();
        IMediaElement firstElement = getFirstElement();
        if (isInput(firstElement)) {
            arrayList.add((IMediaInput) firstElement);
        } else if (isBranch(firstElement)) {
            for (MediaTrack mediaTrack : ((MediaBranch) firstElement).getTracks()) {
                if (mediaTrack != null) {
                    ArrayListExtensions.addRange(arrayList, (T[]) mediaTrack.getInputs());
                }
            }
        }
        return arrayFromInputs(arrayList);
    }

    public boolean getIsBranched() {
        return !getIsEmpty() && isBranch(getLastElement());
    }

    public boolean getIsEmpty() {
        return getFirstElement() == null;
    }

    public boolean getIsTerminated() {
        return !getIsEmpty() && isSink(getLastElement());
    }

    public TIElement getLastElement() {
        return (IMediaElement) Utility.lastOrDefault((T[]) getElements());
    }

    public boolean getMuted() {
        for (IMediaElement muted : getElements()) {
            if (muted.getMuted()) {
                return true;
            }
        }
        return false;
    }

    public TIOutput getOutput() {
        TIOutput[] outputs = getOutputs();
        if (ArrayExtensions.getLength((Object[]) outputs) == 0) {
            return null;
        }
        return outputs[0];
    }

    public TFormat getOutputFormat() {
        IMediaElement lastElement = getLastElement();
        if (lastElement == null || !isOutput(lastElement)) {
            return null;
        }
        return ((IMediaOutput) lastElement).getOutputFormat();
    }

    public TIOutput[] getOutputs() {
        ArrayList arrayList = new ArrayList();
        IMediaElement lastElement = getLastElement();
        if (isOutput(lastElement)) {
            arrayList.add((IMediaOutput) lastElement);
        } else if (isBranch(lastElement)) {
            for (MediaTrack mediaTrack : ((MediaBranch) lastElement).getTracks()) {
                if (mediaTrack != null) {
                    ArrayListExtensions.addRange(arrayList, (T[]) mediaTrack.getOutputs());
                }
            }
        }
        return arrayFromOutputs(arrayList);
    }

    public boolean getPaused() {
        IMediaElement lastElement = getLastElement();
        return lastElement != null && lastElement.getPaused();
    }

    public boolean getPersistent() {
        return this._persistent;
    }

    public String getPipelineJson() {
        IMediaElement firstElement = getFirstElement();
        return firstElement != null ? firstElement.getPipelineJson() : "null";
    }

    public TSink getSink() {
        TSink lastElement = getLastElement();
        if (lastElement == null || !isSink(lastElement)) {
            return null;
        }
        return (MediaSink) lastElement;
    }

    public SinkOutput getSinkOutput() {
        MediaSink activeSink = getActiveSink();
        if (activeSink != null) {
            return activeSink.getOutput();
        }
        return null;
    }

    public Future<SinkOutput[]> getSinkOutputs() {
        MediaSink activeSink = getActiveSink();
        if (activeSink != null) {
            return activeSink.getOutputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SinkOutput[0]);
        return promise;
    }

    public TSink[] getSinks() {
        ArrayList arrayList = new ArrayList();
        IMediaElement lastElement = getLastElement();
        if (isSink(lastElement)) {
            arrayList.add((MediaSink) lastElement);
        } else if (isBranch(lastElement)) {
            for (MediaTrack mediaTrack : ((MediaBranch) lastElement).getTracks()) {
                if (mediaTrack != null) {
                    ArrayListExtensions.addRange(arrayList, (T[]) mediaTrack.getSinks());
                }
            }
        }
        return arrayFromSinks(arrayList);
    }

    public TSource getSource() {
        TSource firstElement = getFirstElement();
        if (firstElement == null || !isSource(firstElement)) {
            return null;
        }
        return (MediaSource) firstElement;
    }

    public SourceInput getSourceInput() {
        MediaSource source = getSource();
        if (source != null) {
            return source.getInput();
        }
        return null;
    }

    public Future<SourceInput[]> getSourceInputs() {
        MediaSource source = getSource();
        if (source != null) {
            return source.getInputs();
        }
        Promise promise = new Promise();
        promise.resolve(new SourceInput[0]);
        return promise;
    }

    protected MediaTrack() {
    }

    public TTrack next(TBranch tbranch) {
        if (tbranch != null) {
            if (getIsBranched()) {
                throw new RuntimeException(new Exception("Cannot branch a branched track. Try merging first."));
            } else if (!getIsTerminated()) {
                if (!getIsEmpty()) {
                    for (MediaTrack mediaTrack : tbranch.getTracks()) {
                        if (mediaTrack != null && !mediaTrack.getIsEmpty()) {
                            ((IMediaOutput) getLastElement()).addOutput((IMediaInput) mediaTrack.getFirstElement());
                        }
                    }
                }
                addElement(tbranch);
            } else {
                throw new RuntimeException(new Exception("Cannot branch a terminated track."));
            }
        }
        return this;
    }

    public TTrack next(TIInput tiinput) {
        if (tiinput != null) {
            if (isStream(tiinput)) {
                throw new RuntimeException(new Exception("A stream cannot belong to a media track."));
            } else if (!getIsTerminated()) {
                if (!getIsEmpty()) {
                    if (getIsBranched()) {
                        addToBranch((MediaBranch) getLastElement(), tiinput);
                    } else {
                        ((IMediaOutput) getLastElement()).addOutput(tiinput);
                    }
                }
                addElement(tiinput);
            } else {
                throw new RuntimeException(new Exception("Cannot add to a terminated track."));
            }
        }
        return this;
    }

    public TTrack next(TTrack[] ttrackArr) {
        return next(branchFromTracks(ttrackArr));
    }

    /* access modifiers changed from: protected */
    public void raiseOnStarted() {
        IAction0 iAction0 = this._onStarted;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    /* access modifiers changed from: protected */
    public void raiseOnStopped() {
        IAction0 iAction0 = this._onStopped;
        if (iAction0 != null) {
            iAction0.invoke();
        }
    }

    public void removeOnDestroyed(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onDestroyed, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onDestroyed.remove(iAction0);
        if (this.__onDestroyed.size() == 0) {
            this._onDestroyed = null;
        }
    }

    public void removeOnStarted(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onStarted, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onStarted.remove(iAction0);
        if (this.__onStarted.size() == 0) {
            this._onStarted = null;
        }
    }

    public void removeOnStopped(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onStopped, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onStopped.remove(iAction0);
        if (this.__onStopped.size() == 0) {
            this._onStopped = null;
        }
    }

    public void setDeactivated(boolean z) {
        IMediaElement firstElement = getFirstElement();
        if (firstElement != null) {
            firstElement.setDeactivated(z);
        }
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    public void setMuted(boolean z) {
        for (IMediaElement muted : getElements()) {
            muted.setMuted(z);
        }
    }

    public void setPersistent(boolean z) {
        this._persistent = z;
    }

    public void setSinkOutput(SinkOutput sinkOutput) {
        MediaSink activeSink = getActiveSink();
        if (activeSink != null) {
            activeSink.setOutput(sinkOutput);
        }
    }

    public void setSourceInput(SourceInput sourceInput) {
        MediaSource source = getSource();
        if (source != null) {
            source.setInput(sourceInput);
        }
    }
}
