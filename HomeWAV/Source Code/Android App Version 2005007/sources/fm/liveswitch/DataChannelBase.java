package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DataChannelBase<TDataChannel> extends Dynamic implements IDataChannel<TDataChannel> {
    private AtomicLong __bytesReceived = new AtomicLong();
    private AtomicLong __bytesSent = new AtomicLong();
    private String __id = Utility.generateId();
    private ILog __log = Log.getLogger(DataChannel.class);
    private AtomicLong __messagesReceived = new AtomicLong();
    private AtomicLong __messagesSent = new AtomicLong();
    /* access modifiers changed from: private */
    public List<IAction1<Integer>> __onDataReceived = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<Integer>> __onDataSent = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<TDataChannel>> __onStateChange = new ArrayList();
    private Object __stateLock = new Object();
    private DataChannelStateMachine __stateMachine;
    private String _connectionId;
    private IFunction1<String, Object> _getRemoteConnectionInfo;
    private String _label;
    private IAction1<Integer> _onDataReceived = null;
    private IAction1<Integer> _onDataSent = null;
    private IAction1<DataChannelReceiveArgs> _onReceive;
    private IAction1<TDataChannel> _onStateChange = null;
    private boolean _ordered;
    private String _streamId;
    private String _subprotocol;

    /* access modifiers changed from: protected */
    public abstract TDataChannel getInstance();

    /* access modifiers changed from: protected */
    public void processStateChange() {
    }

    /* access modifiers changed from: protected */
    public void processStateLockChange() {
    }

    /* access modifiers changed from: package-private */
    public abstract void promisedSendDataBytes(DataBuffer dataBuffer, int i, Promise<Object> promise);

    /* access modifiers changed from: package-private */
    public abstract void promisedSendDataString(String str, int i, Promise<Object> promise);

    public abstract Future<Object> sendDataBytes(DataBuffer dataBuffer);

    public abstract Future<Object> sendDataString(String str);

    /* access modifiers changed from: package-private */
    public void addOnDataReceived(IAction1<Integer> iAction1) {
        if (iAction1 != null) {
            if (this._onDataReceived == null) {
                this._onDataReceived = new IAction1<Integer>() {
                    public void invoke(Integer num) {
                        Iterator it = new ArrayList(DataChannelBase.this.__onDataReceived).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(num);
                        }
                    }
                };
            }
            this.__onDataReceived.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void addOnDataSent(IAction1<Integer> iAction1) {
        if (iAction1 != null) {
            if (this._onDataSent == null) {
                this._onDataSent = new IAction1<Integer>() {
                    public void invoke(Integer num) {
                        Iterator it = new ArrayList(DataChannelBase.this.__onDataSent).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(num);
                        }
                    }
                };
            }
            this.__onDataSent.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<TDataChannel> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<TDataChannel>() {
                    public void invoke(TDataChannel tdatachannel) {
                        Iterator it = new ArrayList(DataChannelBase.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tdatachannel);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public DataChannelBase(String str, boolean z, String str2) {
        setLabel(str);
        setOrdered(z);
        setSubprotocol(str2);
        this.__stateMachine = new DataChannelStateMachine();
    }

    public long getBytesReceived() {
        return this.__bytesReceived.getValue();
    }

    public long getBytesSent() {
        return this.__bytesSent.getValue();
    }

    public String getConnectionId() {
        return this._connectionId;
    }

    /* access modifiers changed from: package-private */
    public IFunction1<String, Object> getGetRemoteConnectionInfo() {
        return this._getRemoteConnectionInfo;
    }

    public String getId() {
        return this.__id;
    }

    public DataChannelInfo getInfo() {
        DataChannelInfo dataChannelInfo = new DataChannelInfo();
        dataChannelInfo.setId(getId());
        dataChannelInfo.setLabel(getLabel());
        dataChannelInfo.setOrdered(getOrdered());
        dataChannelInfo.setSubprotocol(getSubprotocol());
        return dataChannelInfo;
    }

    public boolean getIsTerminated() {
        return Global.equals(getState(), DataChannelState.Closed) || Global.equals(getState(), DataChannelState.Failed);
    }

    public boolean getIsTerminating() {
        return Global.equals(getState(), DataChannelState.Closing);
    }

    public boolean getIsTerminatingOrTerminated() {
        return getIsTerminating() || getIsTerminated();
    }

    public String getLabel() {
        return this._label;
    }

    public long getMessagesReceived() {
        return this.__messagesReceived.getValue();
    }

    public long getMessagesSent() {
        return this.__messagesSent.getValue();
    }

    public IAction1<DataChannelReceiveArgs> getOnReceive() {
        return this._onReceive;
    }

    public boolean getOrdered() {
        return this._ordered;
    }

    public DataChannelState getState() {
        return (DataChannelState) this.__stateMachine.getState();
    }

    /* access modifiers changed from: package-private */
    public Object getStateLock() {
        return this.__stateLock;
    }

    public String getStreamId() {
        return this._streamId;
    }

    public String getSubprotocol() {
        return this._subprotocol;
    }

    private void logInvalidStateTransition(DataChannelState dataChannelState) {
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Data channel {0} ({1}) state for stream {2} for connection {3} is currently {4}. Cannot transition to {5} state.", new Object[]{getId(), getLabel(), getStreamId(), getConnectionId(), StringExtensions.toLower(getState().toString()), StringExtensions.toLower(dataChannelState.toString())}));
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareAndSendMessage(DataBuffer dataBuffer, Promise<Object> promise) {
        promisedSendDataBytes(new DataMessage(dataBuffer).getBytes(), dataBuffer.getLength(), promise);
    }

    /* access modifiers changed from: package-private */
    public void prepareAndSendMessage(String str, Promise<Object> promise) {
        promisedSendDataBytes(new DataMessage(str).getBytes(), ArrayExtensions.getLength(Utf8.encode(str)), promise);
    }

    /* access modifiers changed from: protected */
    public void raiseDataBytes(DataBuffer dataBuffer) {
        DataMessage parseBytes = DataMessage.parseBytes(dataBuffer);
        if (parseBytes != null) {
            if (parseBytes.getDataBytes() != null) {
                registerDataReceived(parseBytes.getDataBytes().getLength());
            } else {
                registerDataReceived(ArrayExtensions.getLength(Utf8.encode(parseBytes.getDataString())));
            }
            DataChannelReceiveArgs dataChannelReceiveArgs = new DataChannelReceiveArgs();
            dataChannelReceiveArgs.setDataString(parseBytes.getDataString());
            dataChannelReceiveArgs.setDataBytes(parseBytes.getDataBytes());
            dataChannelReceiveArgs.setDataMessage(parseBytes);
            IFunction1<String, Object> getRemoteConnectionInfo = getGetRemoteConnectionInfo();
            String remoteConnectionId = parseBytes.getRemoteConnectionId();
            if (!(getRemoteConnectionInfo == null || remoteConnectionId == null)) {
                dataChannelReceiveArgs.setRemoteConnectionInfo(getRemoteConnectionInfo.invoke(remoteConnectionId));
            }
            IAction1<DataChannelReceiveArgs> onReceive = getOnReceive();
            if (onReceive != null) {
                onReceive.invoke(dataChannelReceiveArgs);
                return;
            }
            return;
        }
        Log.error("DataChannelBase.RaiseDataBytes: Could not parse received bytes as data message.");
    }

    /* access modifiers changed from: protected */
    public void raiseDataString(String str) {
        registerDataReceived(ArrayExtensions.getLength(Utf8.encode(str)));
        this.__log.error(StringExtensions.format("Discarding unexpected string on data channel with label '{0}' for connection with ID '{1}': {2}.", getLabel(), getConnectionId(), str));
    }

    private void raiseStateChange() {
        IAction1<TDataChannel> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            try {
                iAction1.invoke(getInstance());
            } catch (Exception e) {
                Log.error(StringExtensions.format("Exception occurred while raising state change to the application code for data stream {0}.", (Object) getId()), e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerDataReceived(int i) {
        IAction1<Integer> iAction1 = this._onDataReceived;
        if (iAction1 != null) {
            iAction1.invoke(Integer.valueOf(i));
        }
        this.__messagesReceived.increment();
        this.__bytesReceived.add((long) i);
    }

    /* access modifiers changed from: protected */
    public void registerDataSent(int i) {
        IAction1<Integer> iAction1 = this._onDataSent;
        if (iAction1 != null) {
            iAction1.invoke(Integer.valueOf(i));
        }
        this.__messagesSent.increment();
        this.__bytesSent.add((long) i);
    }

    /* access modifiers changed from: package-private */
    public void removeOnDataReceived(IAction1<Integer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDataReceived, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDataReceived.remove(iAction1);
        if (this.__onDataReceived.size() == 0) {
            this._onDataReceived = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnDataSent(IAction1<Integer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDataSent, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDataSent.remove(iAction1);
        if (this.__onDataSent.size() == 0) {
            this._onDataSent = null;
        }
    }

    public void removeOnStateChange(IAction1<TDataChannel> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setConnectionId(String str) {
        this._connectionId = str;
    }

    /* access modifiers changed from: package-private */
    public void setGetRemoteConnectionInfo(IFunction1<String, Object> iFunction1) {
        this._getRemoteConnectionInfo = iFunction1;
    }

    private void setLabel(String str) {
        this._label = str;
    }

    public void setOnReceive(IAction1<DataChannelReceiveArgs> iAction1) {
        this._onReceive = iAction1;
    }

    private void setOrdered(boolean z) {
        this._ordered = z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setState(fm.liveswitch.DataChannelState r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.getStateLock()
            monitor-enter(r0)
            fm.liveswitch.DataChannelStateMachine r1 = r10.__stateMachine     // Catch:{ all -> 0x00c0 }
            boolean r1 = r1.transition(r11)     // Catch:{ all -> 0x00c0 }
            r2 = 0
            if (r1 != 0) goto L_0x003d
            fm.liveswitch.DataChannelState r1 = r10.getState()     // Catch:{ all -> 0x00c0 }
            boolean r1 = fm.liveswitch.Global.equals(r11, r1)     // Catch:{ all -> 0x00c0 }
            if (r1 != 0) goto L_0x003b
            fm.liveswitch.DataChannelState r1 = fm.liveswitch.DataChannelState.Closing     // Catch:{ all -> 0x00c0 }
            boolean r1 = fm.liveswitch.Global.equals(r11, r1)     // Catch:{ all -> 0x00c0 }
            if (r1 == 0) goto L_0x0038
            fm.liveswitch.DataChannelState r1 = r10.getState()     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.DataChannelState r3 = fm.liveswitch.DataChannelState.Closed     // Catch:{ all -> 0x00c0 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00c0 }
            if (r1 != 0) goto L_0x003b
            fm.liveswitch.DataChannelState r1 = r10.getState()     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.DataChannelState r3 = fm.liveswitch.DataChannelState.Failed     // Catch:{ all -> 0x00c0 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00c0 }
            if (r1 != 0) goto L_0x003b
        L_0x0038:
            r10.logInvalidStateTransition(r11)     // Catch:{ all -> 0x00c0 }
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            return r2
        L_0x003d:
            boolean r11 = fm.liveswitch.Log.getIsInfoEnabled()     // Catch:{ all -> 0x00c0 }
            r1 = 1
            if (r11 == 0) goto L_0x00b8
            fm.liveswitch.DataChannelState r11 = r10.getState()     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.DataChannelState r3 = fm.liveswitch.DataChannelState.Connected     // Catch:{ all -> 0x00c0 }
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)     // Catch:{ all -> 0x00c0 }
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 5
            if (r11 == 0) goto L_0x0087
            java.lang.String r11 = "Data channel {0} ({1} for stream {2} for connection {3} took {4}ms to connect."
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.getId()     // Catch:{ all -> 0x00c0 }
            r7[r2] = r8     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.getLabel()     // Catch:{ all -> 0x00c0 }
            r7[r1] = r8     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.getStreamId()     // Catch:{ all -> 0x00c0 }
            r7[r5] = r8     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.getConnectionId()     // Catch:{ all -> 0x00c0 }
            r7[r4] = r8     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.DataChannelStateMachine r8 = r10.__stateMachine     // Catch:{ all -> 0x00c0 }
            long r8 = r8.getLastStateMillis()     // Catch:{ all -> 0x00c0 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = fm.liveswitch.LongExtensions.toString(r8)     // Catch:{ all -> 0x00c0 }
            r7[r3] = r8     // Catch:{ all -> 0x00c0 }
            java.lang.String r11 = fm.liveswitch.StringExtensions.format((java.lang.String) r11, (java.lang.Object[]) r7)     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.Log.info(r11)     // Catch:{ all -> 0x00c0 }
        L_0x0087:
            java.lang.String r11 = "Setting data channel {0} ({1}) state for stream {2} for connection {3} to {4}."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = r10.getId()     // Catch:{ all -> 0x00c0 }
            r6[r2] = r7     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = r10.getLabel()     // Catch:{ all -> 0x00c0 }
            r6[r1] = r2     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = r10.getStreamId()     // Catch:{ all -> 0x00c0 }
            r6[r5] = r2     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = r10.getConnectionId()     // Catch:{ all -> 0x00c0 }
            r6[r4] = r2     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.DataChannelState r2 = r10.getState()     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = fm.liveswitch.StringExtensions.toLower(r2)     // Catch:{ all -> 0x00c0 }
            r6[r3] = r2     // Catch:{ all -> 0x00c0 }
            java.lang.String r11 = fm.liveswitch.StringExtensions.format((java.lang.String) r11, (java.lang.Object[]) r6)     // Catch:{ all -> 0x00c0 }
            fm.liveswitch.Log.info(r11)     // Catch:{ all -> 0x00c0 }
        L_0x00b8:
            r10.raiseStateChange()     // Catch:{ all -> 0x00c0 }
            r10.processStateChange()     // Catch:{ all -> 0x00c0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            return r1
        L_0x00c0:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c0 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DataChannelBase.setState(fm.liveswitch.DataChannelState):boolean");
    }

    /* access modifiers changed from: package-private */
    public void setStateLock(Object obj) {
        if (!Global.equals(this.__stateLock, obj)) {
            this.__stateLock = obj;
            processStateLockChange();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStreamId(String str) {
        this._streamId = str;
    }

    private void setSubprotocol(String str) {
        this._subprotocol = str;
    }
}
