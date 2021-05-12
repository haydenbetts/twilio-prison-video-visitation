package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class StreamBase extends Dynamic implements IStream {
    private String __id = Utility.generateId();
    private String __mediaStreamIdentification;
    /* access modifiers changed from: private */
    public List<IAction0> __onDirectionChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction0> __onStateChange = new ArrayList();
    private Object __stateLock = new Object();
    private StreamStateMachine __stateMachine;
    private long _connectedTimestamp;
    private String _connectionId;
    private String _externalId;
    private IAction0 _onDirectionChange = null;
    private IAction0 _onStateChange = null;
    private String _tag;
    private StreamType _type;

    public abstract Error changeDirection(StreamDirection streamDirection);

    public abstract StreamDirection getDirection();

    /* access modifiers changed from: package-private */
    public abstract StreamDirection getDirectionCapabilities();

    public abstract String getLabel();

    public abstract StreamDirection getLocalDirection();

    /* access modifiers changed from: package-private */
    public abstract MediaDescriptionManagerBase getMediaDescriptionManager();

    public abstract StreamDirection getRemoteDirection();

    public abstract TransportInfo getTransportInfo();

    /* access modifiers changed from: package-private */
    public abstract Error processSdpMediaDescription(Message message, MediaDescription mediaDescription, int i, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public void processStateChange() {
    }

    /* access modifiers changed from: protected */
    public void processStateLockChange() {
    }

    /* access modifiers changed from: protected */
    public void processUpdateToMediaStreamIdentification(String str) {
    }

    public abstract void setLocalDirection(StreamDirection streamDirection);

    /* access modifiers changed from: package-private */
    public abstract void setRemoteDirection(StreamDirection streamDirection);

    public void addOnDirectionChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onDirectionChange == null) {
                this._onDirectionChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(StreamBase.this.__onDirectionChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onDirectionChange.add(iAction0);
        }
    }

    public void addOnStateChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(StreamBase.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction0);
        }
    }

    /* access modifiers changed from: protected */
    public long getConnectedTimestamp() {
        return this._connectedTimestamp;
    }

    public String getConnectionId() {
        return this._connectionId;
    }

    public String getExternalId() {
        return this._externalId;
    }

    public String getId() {
        return this.__id;
    }

    public boolean getIsTerminated() {
        return Global.equals(getState(), StreamState.Closed) || Global.equals(getState(), StreamState.Failed);
    }

    public boolean getIsTerminating() {
        return Global.equals(getState(), StreamState.Closing) || Global.equals(getState(), StreamState.Failing);
    }

    public boolean getIsTerminatingOrTerminated() {
        return getIsTerminating() || getIsTerminated();
    }

    public boolean getLocalReceive() {
        StreamDirection localDirection = getLocalDirection();
        return Global.equals(localDirection, StreamDirection.SendReceive) || Global.equals(localDirection, StreamDirection.ReceiveOnly);
    }

    public boolean getLocalSend() {
        StreamDirection localDirection = getLocalDirection();
        return Global.equals(localDirection, StreamDirection.SendReceive) || Global.equals(localDirection, StreamDirection.SendOnly);
    }

    public String getMediaDescriptionId() {
        return getMediaStreamIdentification();
    }

    /* access modifiers changed from: package-private */
    public String getMediaStreamIdentification() {
        return this.__mediaStreamIdentification;
    }

    public boolean getRemoteReceive() {
        StreamDirection remoteDirection = getRemoteDirection();
        return Global.equals(remoteDirection, StreamDirection.SendReceive) || Global.equals(remoteDirection, StreamDirection.ReceiveOnly);
    }

    public boolean getRemoteSend() {
        StreamDirection remoteDirection = getRemoteDirection();
        return Global.equals(remoteDirection, StreamDirection.SendReceive) || Global.equals(remoteDirection, StreamDirection.SendOnly);
    }

    public StreamState getState() {
        return (StreamState) this.__stateMachine.getState();
    }

    /* access modifiers changed from: package-private */
    public Object getStateLock() {
        return this.__stateLock;
    }

    public String getTag() {
        return this._tag;
    }

    public StreamType getType() {
        return this._type;
    }

    private void logInvalidStateTransition(StreamState streamState) {
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Stream {0} state for connection {1} is currently {2}. Cannot transition to {3} state.", new Object[]{getId(), getConnectionId(), StringExtensions.toLower(getState().toString()), StringExtensions.toLower(streamState.toString())}));
        }
    }

    /* access modifiers changed from: package-private */
    public void raiseDirectionChange() {
        IAction0 iAction0 = this._onDirectionChange;
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising direction change to the application code for stream {0}.", (Object) getId()), e);
            }
        }
    }

    private void raiseStateChange() {
        IAction0 iAction0 = this._onStateChange;
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising state change to the application code for stream {0}.", (Object) getId()), e);
            }
        }
    }

    public void removeOnDirectionChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onDirectionChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onDirectionChange.remove(iAction0);
        if (this.__onDirectionChange.size() == 0) {
            this._onDirectionChange = null;
        }
    }

    public void removeOnStateChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onStateChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onStateChange.remove(iAction0);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    private void setConnectedTimestamp(long j) {
        this._connectedTimestamp = j;
    }

    /* access modifiers changed from: package-private */
    public void setConnectionId(String str) {
        this._connectionId = str;
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    public void setLocalReceive(boolean z) {
        StreamDirection localDirection = getLocalDirection();
        if (localDirection == StreamDirection.SendReceive) {
            if (!z) {
                setLocalDirection(StreamDirection.SendOnly);
            }
        } else if (localDirection == StreamDirection.Inactive || localDirection == StreamDirection.Unset) {
            if (z) {
                setLocalDirection(StreamDirection.ReceiveOnly);
            } else {
                setLocalDirection(StreamDirection.SendOnly);
            }
        } else if (localDirection == StreamDirection.SendOnly) {
            if (z) {
                setLocalDirection(StreamDirection.SendReceive);
            }
        } else if (Global.equals(localDirection, StreamDirection.ReceiveOnly) && !z) {
            setLocalDirection(StreamDirection.Inactive);
        }
    }

    public void setLocalSend(boolean z) {
        StreamDirection localDirection = getLocalDirection();
        if (localDirection == StreamDirection.SendReceive) {
            if (!z) {
                setLocalDirection(StreamDirection.ReceiveOnly);
            }
        } else if (localDirection == StreamDirection.Inactive || localDirection == StreamDirection.Unset) {
            if (z) {
                setLocalDirection(StreamDirection.SendOnly);
            } else {
                setLocalDirection(StreamDirection.ReceiveOnly);
            }
        } else if (localDirection == StreamDirection.ReceiveOnly) {
            if (z) {
                setLocalDirection(StreamDirection.SendReceive);
            }
        } else if (Global.equals(localDirection, StreamDirection.SendOnly) && !z) {
            setLocalDirection(StreamDirection.Inactive);
        }
    }

    /* access modifiers changed from: package-private */
    public void setMediaStreamIdentification(String str) {
        if (!Global.equals(this.__mediaStreamIdentification, str)) {
            String str2 = this.__mediaStreamIdentification;
            this.__mediaStreamIdentification = str;
            processUpdateToMediaStreamIdentification(str2);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setState(fm.liveswitch.StreamState r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.getStateLock()
            monitor-enter(r0)
            fm.liveswitch.StreamStateMachine r1 = r9.__stateMachine     // Catch:{ all -> 0x00d2 }
            boolean r1 = r1.transition(r10)     // Catch:{ all -> 0x00d2 }
            r2 = 0
            if (r1 != 0) goto L_0x003d
            fm.liveswitch.StreamState r1 = r9.getState()     // Catch:{ all -> 0x00d2 }
            boolean r1 = fm.liveswitch.Global.equals(r10, r1)     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x003b
            fm.liveswitch.StreamState r1 = fm.liveswitch.StreamState.Closing     // Catch:{ all -> 0x00d2 }
            boolean r1 = fm.liveswitch.Global.equals(r10, r1)     // Catch:{ all -> 0x00d2 }
            if (r1 == 0) goto L_0x0038
            fm.liveswitch.StreamState r1 = r9.getState()     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamState r3 = fm.liveswitch.StreamState.Closed     // Catch:{ all -> 0x00d2 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x003b
            fm.liveswitch.StreamState r1 = r9.getState()     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamState r3 = fm.liveswitch.StreamState.Failed     // Catch:{ all -> 0x00d2 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00d2 }
            if (r1 != 0) goto L_0x003b
        L_0x0038:
            r9.logInvalidStateTransition(r10)     // Catch:{ all -> 0x00d2 }
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return r2
        L_0x003d:
            fm.liveswitch.StreamState r10 = r9.getState()     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamState r1 = fm.liveswitch.StreamState.Connected     // Catch:{ all -> 0x00d2 }
            boolean r10 = fm.liveswitch.Global.equals(r10, r1)     // Catch:{ all -> 0x00d2 }
            if (r10 == 0) goto L_0x0050
            long r3 = fm.liveswitch.ManagedStopwatch.getTimestamp()     // Catch:{ all -> 0x00d2 }
            r9.setConnectedTimestamp(r3)     // Catch:{ all -> 0x00d2 }
        L_0x0050:
            boolean r10 = fm.liveswitch.Log.getIsInfoEnabled()     // Catch:{ all -> 0x00d2 }
            r1 = 1
            if (r10 == 0) goto L_0x00ca
            fm.liveswitch.StreamState r10 = r9.getState()     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamState r3 = fm.liveswitch.StreamState.Connected     // Catch:{ all -> 0x00d2 }
            boolean r10 = fm.liveswitch.Global.equals(r10, r3)     // Catch:{ all -> 0x00d2 }
            r3 = 3
            r4 = 2
            r5 = 4
            if (r10 == 0) goto L_0x0097
            java.lang.String r10 = "{0} stream {1} for connection {2} took {3}ms to connect (connectivity checks and secure key exchange)."
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamType r7 = r9.getType()     // Catch:{ all -> 0x00d2 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00d2 }
            r6[r2] = r7     // Catch:{ all -> 0x00d2 }
            java.lang.String r7 = r9.getId()     // Catch:{ all -> 0x00d2 }
            r6[r1] = r7     // Catch:{ all -> 0x00d2 }
            java.lang.String r7 = r9.getConnectionId()     // Catch:{ all -> 0x00d2 }
            r6[r4] = r7     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamStateMachine r7 = r9.__stateMachine     // Catch:{ all -> 0x00d2 }
            long r7 = r7.getLastStateMillis()     // Catch:{ all -> 0x00d2 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00d2 }
            java.lang.String r7 = fm.liveswitch.LongExtensions.toString(r7)     // Catch:{ all -> 0x00d2 }
            r6[r3] = r7     // Catch:{ all -> 0x00d2 }
            java.lang.String r10 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object[]) r6)     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.Log.info(r10)     // Catch:{ all -> 0x00d2 }
        L_0x0097:
            java.lang.String r10 = "Setting {0} stream {1} state for connection {2} to {3}."
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamType r6 = r9.getType()     // Catch:{ all -> 0x00d2 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00d2 }
            java.lang.String r6 = fm.liveswitch.StringExtensions.toLower(r6)     // Catch:{ all -> 0x00d2 }
            r5[r2] = r6     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r9.getId()     // Catch:{ all -> 0x00d2 }
            r5[r1] = r2     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r9.getConnectionId()     // Catch:{ all -> 0x00d2 }
            r5[r4] = r2     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.StreamState r2 = r9.getState()     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.toLower(r2)     // Catch:{ all -> 0x00d2 }
            r5[r3] = r2     // Catch:{ all -> 0x00d2 }
            java.lang.String r10 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00d2 }
            fm.liveswitch.Log.info(r10)     // Catch:{ all -> 0x00d2 }
        L_0x00ca:
            r9.raiseStateChange()     // Catch:{ all -> 0x00d2 }
            r9.processStateChange()     // Catch:{ all -> 0x00d2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            return r1
        L_0x00d2:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d2 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.StreamBase.setState(fm.liveswitch.StreamState):boolean");
    }

    /* access modifiers changed from: package-private */
    public void setStateLock(Object obj) {
        if (!Global.equals(this.__stateLock, obj)) {
            this.__stateLock = obj;
            processStateLockChange();
        }
    }

    public void setTag(String str) {
        this._tag = str;
    }

    private void setType(StreamType streamType) {
        this._type = streamType;
    }

    public StreamBase(StreamType streamType) {
        setType(streamType);
        setConnectedTimestamp(-1);
        this.__stateMachine = new StreamStateMachine();
    }

    public String toString() {
        return getLabel();
    }
}
