package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ReliableChannel {
    public static int _unset = -1;
    private AtomicLong __bytesReceived = new AtomicLong();
    private AtomicLong __bytesSent = new AtomicLong();
    private Object __lock;
    private AtomicLong __messagesReceived = new AtomicLong();
    private AtomicLong __messagesSent = new AtomicLong();
    /* access modifiers changed from: private */
    public List<IAction1<Exception>> __onError = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<DataBuffer>> __onReceiveBinary = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<String>> __onReceiveString = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ReliableChannel>> __onStateChange = new ArrayList();
    private ArrayList<SctpMessage> __outgoingBuffer = new ArrayList<>();
    private ReliableChannelState __state = ReliableChannelState.New;
    private Error _error;
    private SctpTransport _innerTransport;
    private int _innerTransportStreamId;
    private String _label;
    private IAction1<Exception> _onError = null;
    private IAction1<DataBuffer> _onReceiveBinary = null;
    private IAction1<String> _onReceiveString = null;
    private IAction1<ReliableChannel> _onStateChange = null;
    private boolean _ordered;
    private String _subProtocol;

    public void addOnError(IAction1<Exception> iAction1) {
        if (iAction1 != null) {
            if (this._onError == null) {
                this._onError = new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        Iterator it = new ArrayList(ReliableChannel.this.__onError).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(exc);
                        }
                    }
                };
            }
            this.__onError.add(iAction1);
        }
    }

    public void addOnReceiveBinary(IAction1<DataBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveBinary == null) {
                this._onReceiveBinary = new IAction1<DataBuffer>() {
                    public void invoke(DataBuffer dataBuffer) {
                        Iterator it = new ArrayList(ReliableChannel.this.__onReceiveBinary).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dataBuffer);
                        }
                    }
                };
            }
            this.__onReceiveBinary.add(iAction1);
        }
    }

    public void addOnReceiveString(IAction1<String> iAction1) {
        if (iAction1 != null) {
            if (this._onReceiveString == null) {
                this._onReceiveString = new IAction1<String>() {
                    public void invoke(String str) {
                        Iterator it = new ArrayList(ReliableChannel.this.__onReceiveString).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(str);
                        }
                    }
                };
            }
            this.__onReceiveString.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<ReliableChannel> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<ReliableChannel>() {
                    public void invoke(ReliableChannel reliableChannel) {
                        Iterator it = new ArrayList(ReliableChannel.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(reliableChannel);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public void close() {
        synchronized (this.__lock) {
            setState(ReliableChannelState.Closing);
            setState(ReliableChannelState.Closed);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3 = getInnerTransport();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007c, code lost:
        if (r3 == null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007e, code lost:
        r3 = r3.sendData(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (r3 == null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0084, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0086, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0087, code lost:
        r3 = fm.liveswitch.StringExtensions.format("Reliable Data: could not send data: {0}", (java.lang.Object) r3.getMessage());
        fm.liveswitch.Log.error(fm.liveswitch.StringExtensions.format(r3, new java.lang.Object[0]));
        setError(new fm.liveswitch.Error(fm.liveswitch.ErrorCode.ReliableDataChannelSendError, new java.lang.Exception(r3)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ad, code lost:
        return getError();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error dispatch(fm.liveswitch.DataBuffer r3, long r4, fm.liveswitch.IAction0 r6, fm.liveswitch.IAction1<java.lang.Exception> r7) {
        /*
            r2 = this;
            fm.liveswitch.SctpMessage r0 = new fm.liveswitch.SctpMessage
            int r1 = r2.getInnerTransportStreamId()
            r0.<init>(r3, r1)
            boolean r3 = r2.getOrdered()
            r3 = r3 ^ 1
            r0.setUnordered(r3)
            r0.setOnSuccess(r6)
            r0.setOnFailure(r7)
            r0.setPayloadType(r4)
            java.lang.Object r3 = r2.__lock
            monitor-enter(r3)
            fm.liveswitch.ReliableChannelState r4 = r2.getState()     // Catch:{ all -> 0x00ae }
            fm.liveswitch.ReliableChannelState r5 = fm.liveswitch.ReliableChannelState.New     // Catch:{ all -> 0x00ae }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x00ae }
            r5 = 0
            r6 = 0
            if (r4 == 0) goto L_0x0036
            java.util.ArrayList<fm.liveswitch.SctpMessage> r4 = r2.__outgoingBuffer     // Catch:{ all -> 0x00ae }
            r4.add(r0)     // Catch:{ all -> 0x00ae }
            r0.setUnordered(r6)     // Catch:{ all -> 0x00ae }
            monitor-exit(r3)     // Catch:{ all -> 0x00ae }
            return r5
        L_0x0036:
            fm.liveswitch.ReliableChannelState r4 = r2.getState()     // Catch:{ all -> 0x00ae }
            fm.liveswitch.ReliableChannelState r7 = fm.liveswitch.ReliableChannelState.Opening     // Catch:{ all -> 0x00ae }
            boolean r4 = fm.liveswitch.Global.equals(r4, r7)     // Catch:{ all -> 0x00ae }
            if (r4 == 0) goto L_0x0046
            r0.setUnordered(r6)     // Catch:{ all -> 0x00ae }
            goto L_0x0077
        L_0x0046:
            fm.liveswitch.ReliableChannelState r4 = r2.getState()     // Catch:{ all -> 0x00ae }
            fm.liveswitch.ReliableChannelState r7 = fm.liveswitch.ReliableChannelState.Open     // Catch:{ all -> 0x00ae }
            boolean r4 = fm.liveswitch.Global.equals(r4, r7)     // Catch:{ all -> 0x00ae }
            if (r4 != 0) goto L_0x0077
            java.lang.String r4 = "Reliable Data: Attempting to send data on a channel in state {0}."
            fm.liveswitch.ReliableChannelState r5 = r2.getState()     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x00ae }
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00ae }
            fm.liveswitch.Log.error(r5)     // Catch:{ all -> 0x00ae }
            fm.liveswitch.Error r5 = new fm.liveswitch.Error     // Catch:{ all -> 0x00ae }
            fm.liveswitch.ErrorCode r6 = fm.liveswitch.ErrorCode.ReliableDataChannelSendError     // Catch:{ all -> 0x00ae }
            java.lang.Exception r7 = new java.lang.Exception     // Catch:{ all -> 0x00ae }
            r7.<init>(r4)     // Catch:{ all -> 0x00ae }
            r5.<init>((fm.liveswitch.ErrorCode) r6, (java.lang.Exception) r7)     // Catch:{ all -> 0x00ae }
            monitor-exit(r3)     // Catch:{ all -> 0x00ae }
            return r5
        L_0x0077:
            monitor-exit(r3)     // Catch:{ all -> 0x00ae }
            fm.liveswitch.SctpTransport r3 = r2.getInnerTransport()     // Catch:{ Exception -> 0x0086 }
            if (r3 == 0) goto L_0x0085
            fm.liveswitch.Error r3 = r3.sendData(r0)     // Catch:{ Exception -> 0x0086 }
            if (r3 == 0) goto L_0x0085
            return r3
        L_0x0085:
            return r5
        L_0x0086:
            r3 = move-exception
            java.lang.String r4 = "Reliable Data: could not send data: {0}"
            java.lang.String r3 = r3.getMessage()
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r3)
            java.lang.Object[] r4 = new java.lang.Object[r6]
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r4)
            fm.liveswitch.Log.error(r4)
            fm.liveswitch.Error r4 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r5 = fm.liveswitch.ErrorCode.ReliableDataChannelSendError
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r3)
            r4.<init>((fm.liveswitch.ErrorCode) r5, (java.lang.Exception) r6)
            r2.setError(r4)
            fm.liveswitch.Error r3 = r2.getError()
            return r3
        L_0x00ae:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00ae }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReliableChannel.dispatch(fm.liveswitch.DataBuffer, long, fm.liveswitch.IAction0, fm.liveswitch.IAction1):fm.liveswitch.Error");
    }

    public long getBytesReceived() {
        return this.__bytesReceived.getValue();
    }

    public long getBytesSent() {
        return this.__bytesSent.getValue();
    }

    public Error getError() {
        return this._error;
    }

    public SctpTransport getInnerTransport() {
        return this._innerTransport;
    }

    public int getInnerTransportStreamId() {
        return this._innerTransportStreamId;
    }

    public String getLabel() {
        return this._label;
    }

    static long getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier reliableSctpPayloadProtocolIdentifier) {
        if (Global.equals(reliableSctpPayloadProtocolIdentifier, ReliableSctpPayloadProtocolIdentifier.WebRtcBinary)) {
            return 53;
        }
        if (Global.equals(reliableSctpPayloadProtocolIdentifier, ReliableSctpPayloadProtocolIdentifier.WebRtcDcep)) {
            return 50;
        }
        if (Global.equals(reliableSctpPayloadProtocolIdentifier, ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyBinary)) {
            return 57;
        }
        if (Global.equals(reliableSctpPayloadProtocolIdentifier, ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyString)) {
            return 56;
        }
        if (Global.equals(reliableSctpPayloadProtocolIdentifier, ReliableSctpPayloadProtocolIdentifier.WebRtcString)) {
            return 51;
        }
        throw new RuntimeException(new Exception("Reliable Data: Unknown SCTP WebRTC ppi"));
    }

    public long getMessagesReceived() {
        return this.__messagesReceived.getValue();
    }

    public long getMessagesSent() {
        return this.__messagesSent.getValue();
    }

    public boolean getOrdered() {
        return this._ordered;
    }

    static ReliableSctpPayloadProtocolIdentifier getPpiFromLong(long j) {
        if (j == 53) {
            return ReliableSctpPayloadProtocolIdentifier.WebRtcBinary;
        }
        if (j == 50) {
            return ReliableSctpPayloadProtocolIdentifier.WebRtcDcep;
        }
        if (j == 57) {
            return ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyBinary;
        }
        if (j == 56) {
            return ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyString;
        }
        if (j == 51) {
            return ReliableSctpPayloadProtocolIdentifier.WebRtcString;
        }
        throw new RuntimeException(new Exception("Reliable Data: Unknown SCTP WebRTC ppi"));
    }

    public ReliableChannelState getState() {
        return this.__state;
    }

    public String getSubProtocol() {
        return this._subProtocol;
    }

    /* access modifiers changed from: package-private */
    public ReliableChannelType getType() {
        if (getOrdered()) {
            return ReliableChannelType.Reliable;
        }
        return ReliableChannelType.ReliableUnordered;
    }

    public void open() {
        Log.debug(StringExtensions.format("Reliable Data: requesting to open channel {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(getInnerTransportStreamId()))));
        synchronized (this.__lock) {
            if (!Global.equals(getState(), ReliableChannelState.New)) {
                Log.error("Reliable Data: Attempting to open a channel that is not new.");
            } else {
                setState(ReliableChannelState.Opening);
                SctpTransport innerTransport = getInnerTransport();
                ReliableRtcDcepDataChannelOpen reliableRtcDcepDataChannelOpen = new ReliableRtcDcepDataChannelOpen(getOrdered() ? ReliableChannelType.Reliable : ReliableChannelType.ReliableUnordered, getLabel(), getSubProtocol());
                if (innerTransport == null || innerTransport.getIsClosed()) {
                    setError(new Error(ErrorCode.ReliableDataChannelOpenError, new Exception("Reliable Data: Inner Sctp transport is either not initialized or is closed")));
                    setState(ReliableChannelState.Failed);
                } else {
                    SctpMessage sctpMessage = new SctpMessage(DataBuffer.wrap(reliableRtcDcepDataChannelOpen.getBytes()), getInnerTransportStreamId());
                    sctpMessage.setUnordered(false);
                    sctpMessage.setPayloadType(getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier.WebRtcDcep));
                    try {
                        Error sendData = innerTransport.sendData(sctpMessage);
                        Iterator<SctpMessage> it = this.__outgoingBuffer.iterator();
                        while (it.hasNext()) {
                            SctpMessage next = it.next();
                            next.setStreamId(getInnerTransportStreamId());
                            sendData = innerTransport.sendData(next);
                        }
                        this.__outgoingBuffer.clear();
                        if (sendData != null) {
                            setError(sendData);
                            setState(ReliableChannelState.Failed);
                        }
                    } catch (Exception e) {
                        setError(new Error(ErrorCode.ReliableDataChannelOpenError, e));
                        setState(ReliableChannelState.Failed);
                    }
                }
            }
        }
    }

    private void processChannelOpenAck(ReliableRtcDcepDataChannelAck reliableRtcDcepDataChannelAck) {
        synchronized (this.__lock) {
            if (!Global.equals(getState(), ReliableChannelState.Opening)) {
                Log.error(StringExtensions.format("Reliable Data: Received channel open acknowledgement on channel {0}, but this channel is not in the channel requested state.", (Object) IntegerExtensions.toString(Integer.valueOf(getInnerTransportStreamId()))));
            } else {
                SctpTransport innerTransport = getInnerTransport();
                if (innerTransport != null) {
                    if (!innerTransport.getIsClosed()) {
                        this.__outgoingBuffer.clear();
                        setState(ReliableChannelState.Open);
                        Log.debug(StringExtensions.format("Reliable Data: remote party confirmed opening channel {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(getInnerTransportStreamId()))));
                    }
                }
                Log.error(StringExtensions.format("Reliable Data: Inner Sctp transport is either not initialized or closed. Cannot process Channel Open Ack message on channel {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(getInnerTransportStreamId()))));
                setState(ReliableChannelState.Failed);
            }
        }
    }

    private void raiseError(Exception exc) {
        IAction1<Exception> iAction1 = this._onError;
        if (iAction1 != null) {
            iAction1.invoke(exc);
        }
    }

    private void raiseReceiveBinary(DataBuffer dataBuffer) {
        IAction1<DataBuffer> iAction1 = this._onReceiveBinary;
        if (iAction1 != null) {
            iAction1.invoke(dataBuffer);
        }
    }

    private void raiseReceiveString(String str) {
        IAction1<String> iAction1 = this._onReceiveString;
        if (iAction1 != null) {
            iAction1.invoke(str);
        }
    }

    private void raiseStateChange() {
        IAction1<ReliableChannel> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    public void receiveSctpMessage(SctpMessage sctpMessage) {
        DataBuffer dataBuffer;
        ReliableSctpPayloadProtocolIdentifier ppiFromLong = getPpiFromLong(sctpMessage.getPayloadType());
        try {
            if (!Global.equals(getState(), ReliableChannelState.Open)) {
                if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcDcep)) {
                    if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcDcep)) {
                        ReliableRtcDcepMessage parseBytes = ReliableRtcDcepMessage.parseBytes(sctpMessage.getPayload());
                        if (parseBytes == null) {
                            throw new RuntimeException(new Exception("Reliable Data: received an invalid webrtc message"));
                        } else if (parseBytes.getMessageType() == 3) {
                            try {
                                respondToOpenRequest((ReliableRtcDcepDataChannelOpen) parseBytes);
                                return;
                            } catch (Exception e) {
                                Log.error(StringExtensions.format("Reliable Data: could not process incoming channel open request: {0}", (Object) e.getMessage()));
                                return;
                            }
                        } else if (parseBytes.getMessageType() == 2) {
                            try {
                                processChannelOpenAck((ReliableRtcDcepDataChannelAck) parseBytes);
                                return;
                            } catch (Exception e2) {
                                Log.error(StringExtensions.format("Reliable Data: could not process incoming channel open acknowledgement: {0}", (Object) e2.getMessage()));
                                return;
                            }
                        } else {
                            throw new RuntimeException(new Exception("Reliable Data: received an invalid webrtc message."));
                        }
                    } else {
                        return;
                    }
                }
            }
            String str = null;
            if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyString)) {
                String str2 = StringExtensions.empty;
                this.__bytesReceived.add(1);
                str = str2;
                dataBuffer = null;
            } else if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcString)) {
                String readUtf8String = sctpMessage.getPayload().readUtf8String(0);
                this.__bytesReceived.add((long) sctpMessage.getPayload().getLength());
                dataBuffer = null;
                str = readUtf8String;
            } else if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyBinary)) {
                dataBuffer = DataBuffer.wrap(new byte[0]);
                this.__bytesReceived.add(1);
            } else if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcBinary)) {
                DataBuffer payload = sctpMessage.getPayload();
                this.__bytesReceived.add((long) sctpMessage.getPayload().getLength());
                dataBuffer = payload;
            } else if (Global.equals(ppiFromLong, ReliableSctpPayloadProtocolIdentifier.WebRtcDcep)) {
                throw new RuntimeException(new Exception("Reliable Data: received a webrtc dcep message on an open channel. This scenario is not supported."));
            } else {
                throw new RuntimeException(new Exception("Reliable Data: received a reliable data message of an unknown type."));
            }
            this.__messagesReceived.increment();
            if (str != null) {
                raiseReceiveString(str);
            } else if (dataBuffer != null) {
                raiseReceiveBinary(dataBuffer);
            }
        } catch (Exception e3) {
            Log.error("Reliable Data: Could not process new data.", e3);
        }
    }

    public ReliableChannel(String str, boolean z, String str2, Object obj) {
        setLabel(str);
        setOrdered(z);
        setSubProtocol(str2);
        setInnerTransportStreamId(_unset);
        this.__lock = obj;
    }

    public void removeOnError(IAction1<Exception> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onError, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onError.remove(iAction1);
        if (this.__onError.size() == 0) {
            this._onError = null;
        }
    }

    public void removeOnReceiveBinary(IAction1<DataBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveBinary, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveBinary.remove(iAction1);
        if (this.__onReceiveBinary.size() == 0) {
            this._onReceiveBinary = null;
        }
    }

    public void removeOnReceiveString(IAction1<String> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceiveString, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceiveString.remove(iAction1);
        if (this.__onReceiveString.size() == 0) {
            this._onReceiveString = null;
        }
    }

    public void removeOnStateChange(IAction1<ReliableChannel> iAction1) {
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
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ad A[Catch:{ Exception -> 0x0101 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0136 A[Catch:{ Exception -> 0x0101 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean respondToOpenRequest(fm.liveswitch.ReliableRtcDcepDataChannelOpen r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.__lock
            monitor-enter(r0)
            fm.liveswitch.ReliableChannelState r1 = r7.getState()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r2 = fm.liveswitch.ReliableChannelState.New     // Catch:{ all -> 0x0151 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0151 }
            r2 = 0
            if (r1 != 0) goto L_0x002c
            java.lang.String r8 = "Reliable Data: Received channel open request on channel {0}, which is not in the New state. Check channel ownership convention."
            int r1 = r7.getInnerTransportStreamId()     // Catch:{ all -> 0x0151 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0151 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x0151 }
            java.lang.String r8 = fm.liveswitch.StringExtensions.format((java.lang.String) r8, (java.lang.Object) r1)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.Log.error(r8)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r2
        L_0x002c:
            fm.liveswitch.ReliableChannelType r1 = r8.getChannelType()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.Reliable     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            r4 = 1
            if (r3 != 0) goto L_0x007c
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.PartialReliableREXMIT     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            if (r3 != 0) goto L_0x007c
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.PartialReliableTimed     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            if (r3 == 0) goto L_0x004a
            goto L_0x007c
        L_0x004a:
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.ReliableUnordered     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            if (r3 != 0) goto L_0x0078
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.PartialReliableREXMITUnordered     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            if (r3 != 0) goto L_0x0078
            fm.liveswitch.ReliableChannelType r3 = fm.liveswitch.ReliableChannelType.PartialReliableTimedUnordered     // Catch:{ all -> 0x0151 }
            boolean r3 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x0151 }
            if (r3 == 0) goto L_0x0063
            goto L_0x0078
        L_0x0063:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException     // Catch:{ all -> 0x0151 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x0151 }
            java.lang.String r3 = "Reliable Data: received open request for a channel of an unknown type {0}"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0151 }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r1)     // Catch:{ all -> 0x0151 }
            r2.<init>(r1)     // Catch:{ all -> 0x0151 }
            r8.<init>(r2)     // Catch:{ all -> 0x0151 }
            throw r8     // Catch:{ all -> 0x0151 }
        L_0x0078:
            r7.setOrdered(r2)     // Catch:{ all -> 0x0151 }
            goto L_0x007f
        L_0x007c:
            r7.setOrdered(r4)     // Catch:{ all -> 0x0151 }
        L_0x007f:
            java.lang.String r1 = r8.getSubProtocol()     // Catch:{ all -> 0x0151 }
            r7.setSubProtocol(r1)     // Catch:{ all -> 0x0151 }
            java.lang.String r8 = r8.getLabel()     // Catch:{ all -> 0x0151 }
            r7.setLabel(r8)     // Catch:{ all -> 0x0151 }
            java.lang.String r8 = "Reliable Data: opening channel {0} on request."
            int r1 = r7.getInnerTransportStreamId()     // Catch:{ all -> 0x0151 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0151 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x0151 }
            java.lang.String r8 = fm.liveswitch.StringExtensions.format((java.lang.String) r8, (java.lang.Object) r1)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.Log.debug(r8)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.SctpTransport r8 = r7.getInnerTransport()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableRtcDcepDataChannelAck r1 = new fm.liveswitch.ReliableRtcDcepDataChannelAck     // Catch:{ all -> 0x0151 }
            r1.<init>()     // Catch:{ all -> 0x0151 }
            if (r8 == 0) goto L_0x0136
            fm.liveswitch.SctpMessage r3 = new fm.liveswitch.SctpMessage     // Catch:{ all -> 0x0151 }
            byte[] r1 = r1.getBytes()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.DataBuffer r1 = fm.liveswitch.DataBuffer.wrap(r1)     // Catch:{ all -> 0x0151 }
            int r5 = r7.getInnerTransportStreamId()     // Catch:{ all -> 0x0151 }
            r3.<init>(r1, r5)     // Catch:{ all -> 0x0151 }
            r3.setUnordered(r2)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableSctpPayloadProtocolIdentifier r1 = fm.liveswitch.ReliableSctpPayloadProtocolIdentifier.WebRtcDcep     // Catch:{ all -> 0x0151 }
            long r5 = getLongFromSctpPPI(r1)     // Catch:{ all -> 0x0151 }
            r3.setPayloadType(r5)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.Error r1 = r8.sendData(r3)     // Catch:{ Exception -> 0x0124 }
            if (r1 == 0) goto L_0x00d8
            r7.setError(r1)     // Catch:{ Exception -> 0x0124 }
            fm.liveswitch.ReliableChannelState r1 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ Exception -> 0x0124 }
            r7.setState(r1)     // Catch:{ Exception -> 0x0124 }
        L_0x00d8:
            java.util.ArrayList<fm.liveswitch.SctpMessage> r1 = r7.__outgoingBuffer     // Catch:{ all -> 0x0151 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0151 }
        L_0x00de:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0151 }
            if (r3 == 0) goto L_0x0113
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.SctpMessage r3 = (fm.liveswitch.SctpMessage) r3     // Catch:{ all -> 0x0151 }
            int r5 = r7.getInnerTransportStreamId()     // Catch:{ Exception -> 0x0101 }
            r3.setStreamId(r5)     // Catch:{ Exception -> 0x0101 }
            fm.liveswitch.Error r3 = r8.sendData(r3)     // Catch:{ Exception -> 0x0101 }
            if (r3 == 0) goto L_0x00de
            r7.setError(r3)     // Catch:{ Exception -> 0x0101 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ Exception -> 0x0101 }
            r7.setState(r8)     // Catch:{ Exception -> 0x0101 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r2
        L_0x0101:
            r8 = move-exception
            fm.liveswitch.Error r1 = new fm.liveswitch.Error     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.ReliableDataChannelOpenError     // Catch:{ all -> 0x0151 }
            r1.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r8)     // Catch:{ all -> 0x0151 }
            r7.setError(r1)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r2
        L_0x0113:
            java.util.ArrayList<fm.liveswitch.SctpMessage> r8 = r7.__outgoingBuffer     // Catch:{ all -> 0x0151 }
            r8.clear()     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Opening     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Open     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r4
        L_0x0124:
            r8 = move-exception
            fm.liveswitch.Error r1 = new fm.liveswitch.Error     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.ReliableDataChannelOpenError     // Catch:{ all -> 0x0151 }
            r1.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r8)     // Catch:{ all -> 0x0151 }
            r7.setError(r1)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r2
        L_0x0136:
            java.lang.String r8 = "Reliable Data: Inner Sctp transport is not initialized."
            fm.liveswitch.Log.error(r8)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.Error r1 = new fm.liveswitch.Error     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.ReliableDataChannelOpenError     // Catch:{ all -> 0x0151 }
            java.lang.Exception r4 = new java.lang.Exception     // Catch:{ all -> 0x0151 }
            r4.<init>(r8)     // Catch:{ all -> 0x0151 }
            r1.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r4)     // Catch:{ all -> 0x0151 }
            r7.setError(r1)     // Catch:{ all -> 0x0151 }
            fm.liveswitch.ReliableChannelState r8 = fm.liveswitch.ReliableChannelState.Failed     // Catch:{ all -> 0x0151 }
            r7.setState(r8)     // Catch:{ all -> 0x0151 }
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            return r2
        L_0x0151:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0151 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReliableChannel.respondToOpenRequest(fm.liveswitch.ReliableRtcDcepDataChannelOpen):boolean");
    }

    public void sendBytes(DataBuffer dataBuffer) {
        sendBytes(dataBuffer, (IAction0) null, (IAction1<Exception>) null);
    }

    public void sendBytes(DataBuffer dataBuffer, IAction0 iAction0, IAction1<Exception> iAction1) {
        long j;
        if (dataBuffer == null || dataBuffer.getLength() == 0) {
            j = getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyBinary);
            dataBuffer = DataBuffer.wrap(new byte[1]);
        } else {
            j = getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier.WebRtcBinary);
        }
        Error dispatch = dispatch(dataBuffer, j, iAction0, iAction1);
        if (dispatch == null) {
            this.__bytesSent.add((long) dataBuffer.getLength());
            this.__messagesSent.increment();
            return;
        }
        if (iAction1 != null) {
            iAction1.invoke(dispatch.getException());
        }
        setError(dispatch);
        setState(ReliableChannelState.Failed);
    }

    public void sendString(String str) {
        sendString(str, (IAction0) null, (IAction1<Exception>) null);
    }

    public void sendString(String str, IAction0 iAction0, IAction1<Exception> iAction1) {
        DataBuffer dataBuffer;
        long j;
        if (str == null || StringExtensions.getLength(str) == 0) {
            dataBuffer = DataBuffer.wrap(new byte[1]);
            j = getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier.WebRtcEmptyString);
        } else {
            dataBuffer = DataBuffer.wrap(Utf8.encode(str));
            j = getLongFromSctpPPI(ReliableSctpPayloadProtocolIdentifier.WebRtcString);
        }
        Error dispatch = dispatch(dataBuffer, j, iAction0, iAction1);
        if (dispatch == null) {
            this.__bytesSent.add((long) dataBuffer.getLength());
            this.__messagesSent.increment();
            return;
        }
        if (iAction1 != null) {
            iAction1.invoke(dispatch.getException());
        }
        setError(dispatch);
        setState(ReliableChannelState.Failed);
    }

    private void setError(Error error) {
        this._error = error;
    }

    public void setInnerTransport(SctpTransport sctpTransport) {
        this._innerTransport = sctpTransport;
    }

    /* access modifiers changed from: package-private */
    public void setInnerTransportStreamId(int i) {
        this._innerTransportStreamId = i;
    }

    private void setLabel(String str) {
        this._label = str;
    }

    /* access modifiers changed from: package-private */
    public void setOrdered(boolean z) {
        this._ordered = z;
    }

    private void setState(ReliableChannelState reliableChannelState) {
        synchronized (this.__lock) {
            if (!Global.equals(reliableChannelState, getState())) {
                this.__state = reliableChannelState;
                raiseStateChange();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSubProtocol(String str) {
        this._subProtocol = str;
    }
}
