package fm.liveswitch;

import fm.liveswitch.stun.BindingRequest;
import fm.liveswitch.stun.BindingResponse;
import fm.liveswitch.stun.Error;
import fm.liveswitch.stun.ErrorCodeAttribute;
import fm.liveswitch.stun.MappedAddressAttribute;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;
import fm.liveswitch.stun.ServerError;
import fm.liveswitch.stun.XorMappedAddressAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StunServer extends Dynamic {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(StunServer.class);
    private Object __startedLock = new Object();
    private int __streamSendTimeout = -1;
    private IFunction1<DatagramSocketCreateArgs, DatagramSocket> _createDatagramSocket;
    private IFunction1<StreamSocketCreateArgs, StreamSocket> _createStreamSocket;
    private boolean _disableTcp;
    private boolean _disableTls;
    private boolean _disableUdp;
    /* access modifiers changed from: private */
    public volatile boolean _started = false;
    private ServerAddress[] _tcpAddresses;
    private HashMap<String, TurnTcpConnection> _tcpConnections = new HashMap<>();
    private Object _tcpConnectionsLock = new Object();
    private StreamSocket[] _tcpSockets = null;
    private ServerAddress[] _tlsAddresses;
    private StreamSocket[] _tlsSockets = null;
    private ServerAddress[] _udpAddresses;
    private DatagramSocket[] _udpSockets = null;

    private String getReason(int i) {
        if (i == 400) {
            return "Bad Request";
        }
        if (i == 401) {
            return "Unauthorized";
        }
        if (i == 403) {
            return "Forbidden";
        }
        if (i == 404) {
            return "Not Found";
        }
        if (i == 420) {
            return "Unknown Attribute";
        }
        if (i == 300) {
            return "Try Alternate";
        }
        if (i == 500) {
            return "Server Error";
        }
        if (i == 508) {
            return "Insufficient Capacity";
        }
        if (i == 437) {
            return "Allocation Mismatch";
        }
        if (i == 438) {
            return "Stale Nonce";
        }
        if (i == 441) {
            return "Wrong Credentials";
        }
        if (i == 442) {
            return "Unsupported Transport Protocol";
        }
        if (i == 446) {
            return "Connection Already Exists";
        }
        if (i == 447) {
            return "Connection Timeout or Failure";
        }
        if (i == 486) {
            return "Allocation Quote Reached";
        }
        if (i == 487) {
            return "Role Conflict";
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getLabel() {
        return "STUN";
    }

    /* access modifiers changed from: protected */
    public String getPrefix(boolean z, boolean z2) {
        return z ? "[UDP] " : z2 ? "[TLS] " : "[TCP] ";
    }

    /* access modifiers changed from: private */
    public void addTcpConnection(TurnTcpConnection turnTcpConnection) {
        synchronized (this._tcpConnectionsLock) {
            HashMapExtensions.add(this._tcpConnections, turnTcpConnection.getId(), turnTcpConnection);
        }
    }

    /* access modifiers changed from: protected */
    public Message createErrorResponse(Message message, TransportAddress transportAddress, String str) {
        return createExceptionResponse(message, transportAddress, new ServerError(str));
    }

    /* access modifiers changed from: protected */
    public Message createExceptionResponse(Message message, TransportAddress transportAddress, Error error) {
        Message createMessage = Message.createMessage(message.getMethod(), MessageType.ErrorResponse, message.getTransactionId());
        int stunCode = error.getStunCode();
        if (stunCode == 0) {
            if (Log.getIsErrorEnabled()) {
                Log.error(StringExtensions.format("An exception occurred while processing request from {0}: {1}", transportAddress.toString(), error.getMessage()));
            }
            stunCode = 500;
        }
        createMessage.setErrorCode(new ErrorCodeAttribute(stunCode, getReason(stunCode)));
        return createMessage;
    }

    private <T> ArrayList<T> createServerSockets(boolean z, ServerAddress[] serverAddressArr, boolean z2, IFunction2<ServerAddress, Boolean, T> iFunction2) {
        String prefix = getPrefix(z, z2);
        ArrayList<T> arrayList = new ArrayList<>();
        if (ArrayExtensions.getLength((Object[]) serverAddressArr) == 0) {
            Log.debug(StringExtensions.format("{0}No server addresses.", (Object) prefix));
            return arrayList;
        }
        for (ServerAddress serverAddress : serverAddressArr) {
            try {
                arrayList.add(iFunction2.invoke(serverAddress, Boolean.valueOf(z2)));
            } catch (Exception e) {
                Log.error(StringExtensions.format("{0}Could not create socket on {1}.", prefix, serverAddress.toString()), e);
            }
        }
        if (ArrayListExtensions.getCount(arrayList) == 0) {
            Log.fatal(StringExtensions.format("{0}Could not create any sockets.", (Object) prefix));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public StreamSocket createTcpSocket(ServerAddress serverAddress, boolean z) {
        boolean equals = Global.equals(LocalNetwork.getAddressType(serverAddress.getIPAddress()), AddressType.IPv6);
        StreamSocket invoke = getCreateStreamSocket() != null ? getCreateStreamSocket().invoke(new StreamSocketCreateArgs(true, equals, z)) : null;
        if (invoke == null) {
            invoke = new TcpSocket(true, equals, z);
        }
        invoke.setPublicIPAddress(serverAddress.getPublicIPAddress());
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = invoke.bind(serverAddress.getIPAddress(), serverAddress.getPort(), booleanHolder);
        boolean value = booleanHolder.getValue();
        if (bind) {
            return invoke;
        }
        try {
            invoke.close();
        } catch (Exception unused) {
        }
        if (value) {
            throw new RuntimeException(new Exception("TCP socket address is in use."));
        }
        throw new RuntimeException(new Exception("TCP socket bind failed."));
    }

    /* access modifiers changed from: private */
    public DatagramSocket createUdpSocket(ServerAddress serverAddress, boolean z) {
        boolean equals = Global.equals(LocalNetwork.getAddressType(serverAddress.getIPAddress()), AddressType.IPv6);
        DatagramSocket invoke = getCreateDatagramSocket() != null ? getCreateDatagramSocket().invoke(new DatagramSocketCreateArgs(equals)) : null;
        if (invoke == null) {
            invoke = new UdpSocket(equals);
        }
        invoke.setPublicIPAddress(serverAddress.getPublicIPAddress());
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = invoke.bind(serverAddress.getIPAddress(), serverAddress.getPort(), booleanHolder);
        boolean value = booleanHolder.getValue();
        if (bind) {
            return invoke;
        }
        try {
            invoke.close();
        } catch (Exception unused) {
        }
        if (value) {
            throw new RuntimeException(new Exception("UDP socket address is in use."));
        }
        throw new RuntimeException(new Exception("UDP socket bind failed."));
    }

    /* access modifiers changed from: private */
    public void doTcpAccept(StreamSocket streamSocket) {
        if (!streamSocket.getIsClosed()) {
            tcpAccept(streamSocket);
        }
    }

    /* access modifiers changed from: private */
    public void doUdpReceive(DatagramSocket datagramSocket) {
        if (datagramSocket == null || !datagramSocket.getIsClosed()) {
            udpReceive(datagramSocket);
        }
    }

    public IFunction1<DatagramSocketCreateArgs, DatagramSocket> getCreateDatagramSocket() {
        return this._createDatagramSocket;
    }

    public IFunction1<StreamSocketCreateArgs, StreamSocket> getCreateStreamSocket() {
        return this._createStreamSocket;
    }

    private ServerAddress[] getDefaultAddresses(int i) {
        ArrayList arrayList = new ArrayList();
        for (String serverAddress : LocalNetwork.getIPAddresses(new AddressType[]{AddressType.IPv4, AddressType.IPv6}, true)) {
            arrayList.add(new ServerAddress(serverAddress, i));
        }
        return (ServerAddress[]) arrayList.toArray(new ServerAddress[0]);
    }

    public boolean getDisableTcp() {
        return this._disableTcp;
    }

    public boolean getDisableTls() {
        return this._disableTls;
    }

    public boolean getDisableUdp() {
        return this._disableUdp;
    }

    public ServerAddress getLocalTcpAddress() {
        return (ServerAddress) Utility.firstOrDefault((T[]) getLocalTcpAddresses());
    }

    public ServerAddress[] getLocalTcpAddresses() {
        StreamSocket[] streamSocketArr = this._tcpSockets;
        ServerAddress[] serverAddressArr = new ServerAddress[ArrayExtensions.getLength((Object[]) streamSocketArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) streamSocketArr); i++) {
            serverAddressArr[i] = new ServerAddress(streamSocketArr[i].getLocalIPAddress(), streamSocketArr[i].getLocalPort(), streamSocketArr[i].getPublicIPAddress());
        }
        return serverAddressArr;
    }

    public ServerAddress getLocalUdpAddress() {
        return (ServerAddress) Utility.firstOrDefault((T[]) getLocalUdpAddresses());
    }

    public ServerAddress[] getLocalUdpAddresses() {
        DatagramSocket[] datagramSocketArr = this._udpSockets;
        ServerAddress[] serverAddressArr = new ServerAddress[ArrayExtensions.getLength((Object[]) datagramSocketArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) datagramSocketArr); i++) {
            serverAddressArr[i] = new ServerAddress(datagramSocketArr[i].getLocalIPAddress(), datagramSocketArr[i].getLocalPort(), datagramSocketArr[i].getPublicIPAddress());
        }
        return serverAddressArr;
    }

    public int getStreamSendTimeout() {
        return this.__streamSendTimeout;
    }

    public ServerAddress[] getTcpAddresses() {
        return this._tcpAddresses;
    }

    public ServerAddress[] getTlsAddresses() {
        return this._tlsAddresses;
    }

    public ServerAddress[] getUdpAddresses() {
        return this._udpAddresses;
    }

    private void logSocket(boolean z, boolean z2, ServerAddress serverAddress) {
        String prefix = getPrefix(z, z2);
        if (!Log.getIsInfoEnabled()) {
            return;
        }
        if (!TransportAddress.isPrivate(serverAddress.getIPAddress())) {
            Log.info(StringExtensions.format("{0}{1} server listening on {2}.", prefix, getLabel(), serverAddress.toString()));
        } else if (StringExtensions.isNullOrEmpty(serverAddress.getPublicIPAddress())) {
            Log.warn(StringExtensions.format("{0}{1} server listening on {2}.\nThis is a private IP address and no public IP address was specified. Server may not behave as expected.", prefix, getLabel(), serverAddress.toString()));
        } else {
            Log.info(StringExtensions.format("{0}{1} server listening on {2}.\nPublic IP address is mapped to {3}. A 1:1 NAT is required.", new Object[]{prefix, getLabel(), serverAddress.toString(), serverAddress.getPublicIPAddress()}));
        }
    }

    /* access modifiers changed from: protected */
    public Message process(Message message, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress) {
        boolean z = true;
        boolean z2 = streamSocket == null;
        if (z2 || !streamSocket.getSecure()) {
            z = false;
        }
        String prefix = getPrefix(z2, z);
        String str = StringExtensions.empty;
        try {
            if (!(message instanceof BindingRequest)) {
                return null;
            }
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("{0}Processing UDP binding request from {1}{2}.", prefix, transportAddress.toString(), str));
            }
            BindingResponse processBindingRequest = processBindingRequest((BindingRequest) message, transportAddress);
            if (!Log.getIsDebugEnabled()) {
                return processBindingRequest;
            }
            Log.debug(StringExtensions.format("{0}Processed UDP binding request from {1}{2}.", prefix, transportAddress.toString(), str));
            return processBindingRequest;
        } catch (Exception e) {
            if (!Global.equals(message.getMessageType(), MessageType.Indication)) {
                return createErrorResponse(message, transportAddress, e.getMessage());
            }
            return null;
        }
    }

    private BindingResponse processBindingRequest(BindingRequest bindingRequest, TransportAddress transportAddress) {
        BindingResponse bindingResponse = new BindingResponse(bindingRequest.getTransactionId(), true);
        bindingResponse.setXorMappedAddress(new XorMappedAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort(), bindingRequest.getTransactionId()));
        bindingResponse.setMappedAddress(new MappedAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort()));
        return bindingResponse;
    }

    /* access modifiers changed from: protected */
    public boolean processBuffer(DataBuffer dataBuffer, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress, IntegerHolder integerHolder) {
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        Message readFrom = Message.readFrom(dataBuffer, 0, integerHolder2);
        int value = integerHolder2.getValue();
        if (readFrom != null) {
            integerHolder.setValue(value);
            Message process = process(readFrom, datagramSocket, streamSocket, serverAddress, transportAddress);
            if (process == null) {
                return true;
            }
            DataBuffer take = __dataBufferPool.take(process.getLength());
            process.writeTo(take, 0);
            if (datagramSocket != null) {
                datagramSocket.send(take, transportAddress.getIPAddress(), transportAddress.getPort());
            } else {
                streamSocket.sendAsync(take, this.__streamSendTimeout, (IAction0) null, (IAction2<Exception, Boolean>) null);
            }
            take.free();
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    /* access modifiers changed from: private */
    public void processTcpConnectionReceive(TurnTcpConnection turnTcpConnection, CircularDataBuffer circularDataBuffer) {
        do {
            IntegerHolder integerHolder = new IntegerHolder(0);
            boolean processBuffer = processBuffer(DataBuffer.wrap(circularDataBuffer.toArray()), (DatagramSocket) null, turnTcpConnection.getSocket(), turnTcpConnection.getServerAddress(), turnTcpConnection.getClientAddress(), integerHolder);
            int value = integerHolder.getValue();
            if (processBuffer) {
                circularDataBuffer.discard(value);
            } else {
                return;
            }
        } while (circularDataBuffer.getLength() != 0);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|4|5|(1:7)|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeTcpConnection(fm.liveswitch.TurnTcpConnection r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3._tcpConnectionsLock
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, fm.liveswitch.TurnTcpConnection> r1 = r3._tcpConnections     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = r4.getId()     // Catch:{ all -> 0x0017 }
            fm.liveswitch.HashMapExtensions.remove(r1, r2)     // Catch:{ all -> 0x0017 }
            boolean r1 = r4.getIsClosed()     // Catch:{ Exception -> 0x0015 }
            if (r1 != 0) goto L_0x0015
            r4.close()     // Catch:{ Exception -> 0x0015 }
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return
        L_0x0017:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.StunServer.removeTcpConnection(fm.liveswitch.TurnTcpConnection):void");
    }

    private void removeTcpConnections() {
        synchronized (this._tcpConnectionsLock) {
            ArrayList arrayList = new ArrayList();
            for (String add : HashMapExtensions.getKeys(this._tcpConnections)) {
                arrayList.add(add);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                removeTcpConnection(HashMapExtensions.getItem(this._tcpConnections).get((String) it.next()));
            }
        }
    }

    public void setCreateDatagramSocket(IFunction1<DatagramSocketCreateArgs, DatagramSocket> iFunction1) {
        this._createDatagramSocket = iFunction1;
    }

    public void setCreateStreamSocket(IFunction1<StreamSocketCreateArgs, StreamSocket> iFunction1) {
        this._createStreamSocket = iFunction1;
    }

    public void setDisableTcp(boolean z) {
        this._disableTcp = z;
    }

    public void setDisableTls(boolean z) {
        this._disableTls = z;
    }

    public void setDisableUdp(boolean z) {
        this._disableUdp = z;
    }

    public void setStreamSendTimeout(int i) {
        if (i >= -1) {
            this.__streamSendTimeout = i;
        }
    }

    private void setTcpAddresses(ServerAddress[] serverAddressArr) {
        this._tcpAddresses = serverAddressArr;
    }

    private void setTlsAddresses(ServerAddress[] serverAddressArr) {
        this._tlsAddresses = serverAddressArr;
    }

    private void setUdpAddresses(ServerAddress[] serverAddressArr) {
        this._udpAddresses = serverAddressArr;
    }

    public boolean start() {
        return start((ServerAddress[]) null);
    }

    public boolean start(ServerAddress[] serverAddressArr) {
        return start(serverAddressArr, (ServerAddress[]) null);
    }

    public boolean start(ServerAddress[] serverAddressArr, ServerAddress[] serverAddressArr2) {
        return start(serverAddressArr, serverAddressArr2, (ServerAddress[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        if (getDisableUdp() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        setUdpAddresses(new fm.liveswitch.ServerAddress[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r7 == null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        r7 = getDefaultAddresses(3478);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        setUdpAddresses(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004a, code lost:
        if (getDisableTcp() == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        setTcpAddresses(new fm.liveswitch.ServerAddress[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0052, code lost:
        if (r8 == null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        r8 = getDefaultAddresses(3478);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0059, code lost:
        setTcpAddresses(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
        if (getDisableTls() == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        setTlsAddresses(new fm.liveswitch.ServerAddress[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0068, code lost:
        if (r9 == null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006b, code lost:
        r9 = getDefaultAddresses(5349);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        setTlsAddresses(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
        if (getDisableUdp() == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007a, code lost:
        fm.liveswitch.Log.info("UDP is disabled.");
        r6._udpSockets = new fm.liveswitch.DatagramSocket[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0084, code lost:
        r6._udpSockets = (fm.liveswitch.DatagramSocket[]) createServerSockets(true, getUdpAddresses(), false, new fm.liveswitch.StunServer.AnonymousClass1(r6)).toArray(new fm.liveswitch.DatagramSocket[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009f, code lost:
        if (getDisableTcp() == false) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a1, code lost:
        fm.liveswitch.Log.info("TCP is disabled.");
        r6._tcpSockets = new fm.liveswitch.StreamSocket[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ab, code lost:
        r6._tcpSockets = (fm.liveswitch.StreamSocket[]) createServerSockets(false, getTcpAddresses(), false, new fm.liveswitch.StunServer.AnonymousClass2(r6)).toArray(new fm.liveswitch.StreamSocket[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c6, code lost:
        if (getDisableTls() == false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c8, code lost:
        fm.liveswitch.Log.info("TLS is disabled.");
        r6._tlsSockets = new fm.liveswitch.StreamSocket[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d2, code lost:
        r6._tlsSockets = (fm.liveswitch.StreamSocket[]) createServerSockets(false, getTlsAddresses(), true, new fm.liveswitch.StunServer.AnonymousClass3(r6)).toArray(new fm.liveswitch.StreamSocket[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e9, code lost:
        r7 = r6._udpSockets;
        r8 = r7.length;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ed, code lost:
        if (r9 >= r8) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ef, code lost:
        r0 = r7[r9];
        doUdpReceive(r0);
        logSocket(true, false, new fm.liveswitch.ServerAddress(r0.getLocalIPAddress(), r0.getLocalPort(), r0.getPublicIPAddress()));
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010b, code lost:
        r7 = r6._tcpSockets;
        r8 = r7.length;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010f, code lost:
        if (r9 >= r8) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0111, code lost:
        r0 = r7[r9];
        doTcpAccept(r0);
        logSocket(false, false, new fm.liveswitch.ServerAddress(r0.getLocalIPAddress(), r0.getLocalPort(), r0.getPublicIPAddress()));
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012d, code lost:
        r7 = r6._tlsSockets;
        r8 = r7.length;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0131, code lost:
        if (r9 >= r8) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0133, code lost:
        r0 = r7[r9];
        doTcpAccept(r0);
        logSocket(false, true, new fm.liveswitch.ServerAddress(r0.getLocalIPAddress(), r0.getLocalPort(), r0.getPublicIPAddress()));
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x014f, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start(fm.liveswitch.ServerAddress[] r7, fm.liveswitch.ServerAddress[] r8, fm.liveswitch.ServerAddress[] r9) {
        /*
            r6 = this;
            boolean r0 = r6.getDisableUdp()
            if (r0 == 0) goto L_0x0020
            boolean r0 = r6.getDisableTcp()
            if (r0 == 0) goto L_0x0020
            boolean r0 = r6.getDisableTls()
            if (r0 != 0) goto L_0x0013
            goto L_0x0020
        L_0x0013:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.Exception r8 = new java.lang.Exception
            java.lang.String r9 = "Cannot start server. UDP, TCP, and TLS are all disabled."
            r8.<init>(r9)
            r7.<init>(r8)
            throw r7
        L_0x0020:
            java.lang.Object r0 = r6.__startedLock
            monitor-enter(r0)
            boolean r1 = r6._started     // Catch:{ all -> 0x0150 }
            r2 = 0
            if (r1 == 0) goto L_0x002a
            monitor-exit(r0)     // Catch:{ all -> 0x0150 }
            return r2
        L_0x002a:
            r1 = 1
            r6._started = r1     // Catch:{ all -> 0x0150 }
            monitor-exit(r0)     // Catch:{ all -> 0x0150 }
            boolean r0 = r6.getDisableUdp()
            r3 = 3478(0xd96, float:4.874E-42)
            if (r0 == 0) goto L_0x003c
            fm.liveswitch.ServerAddress[] r7 = new fm.liveswitch.ServerAddress[r2]
            r6.setUdpAddresses(r7)
            goto L_0x0046
        L_0x003c:
            if (r7 == 0) goto L_0x003f
            goto L_0x0043
        L_0x003f:
            fm.liveswitch.ServerAddress[] r7 = r6.getDefaultAddresses(r3)
        L_0x0043:
            r6.setUdpAddresses(r7)
        L_0x0046:
            boolean r7 = r6.getDisableTcp()
            if (r7 == 0) goto L_0x0052
            fm.liveswitch.ServerAddress[] r7 = new fm.liveswitch.ServerAddress[r2]
            r6.setTcpAddresses(r7)
            goto L_0x005c
        L_0x0052:
            if (r8 == 0) goto L_0x0055
            goto L_0x0059
        L_0x0055:
            fm.liveswitch.ServerAddress[] r8 = r6.getDefaultAddresses(r3)
        L_0x0059:
            r6.setTcpAddresses(r8)
        L_0x005c:
            boolean r7 = r6.getDisableTls()
            if (r7 == 0) goto L_0x0068
            fm.liveswitch.ServerAddress[] r7 = new fm.liveswitch.ServerAddress[r2]
            r6.setTlsAddresses(r7)
            goto L_0x0074
        L_0x0068:
            if (r9 == 0) goto L_0x006b
            goto L_0x0071
        L_0x006b:
            r7 = 5349(0x14e5, float:7.496E-42)
            fm.liveswitch.ServerAddress[] r9 = r6.getDefaultAddresses(r7)
        L_0x0071:
            r6.setTlsAddresses(r9)
        L_0x0074:
            boolean r7 = r6.getDisableUdp()
            if (r7 == 0) goto L_0x0084
            java.lang.String r7 = "UDP is disabled."
            fm.liveswitch.Log.info(r7)
            fm.liveswitch.DatagramSocket[] r7 = new fm.liveswitch.DatagramSocket[r2]
            r6._udpSockets = r7
            goto L_0x009b
        L_0x0084:
            fm.liveswitch.ServerAddress[] r7 = r6.getUdpAddresses()
            fm.liveswitch.StunServer$1 r8 = new fm.liveswitch.StunServer$1
            r8.<init>()
            java.util.ArrayList r7 = r6.createServerSockets(r1, r7, r2, r8)
            fm.liveswitch.DatagramSocket[] r8 = new fm.liveswitch.DatagramSocket[r2]
            java.lang.Object[] r7 = r7.toArray(r8)
            fm.liveswitch.DatagramSocket[] r7 = (fm.liveswitch.DatagramSocket[]) r7
            r6._udpSockets = r7
        L_0x009b:
            boolean r7 = r6.getDisableTcp()
            if (r7 == 0) goto L_0x00ab
            java.lang.String r7 = "TCP is disabled."
            fm.liveswitch.Log.info(r7)
            fm.liveswitch.StreamSocket[] r7 = new fm.liveswitch.StreamSocket[r2]
            r6._tcpSockets = r7
            goto L_0x00c2
        L_0x00ab:
            fm.liveswitch.ServerAddress[] r7 = r6.getTcpAddresses()
            fm.liveswitch.StunServer$2 r8 = new fm.liveswitch.StunServer$2
            r8.<init>()
            java.util.ArrayList r7 = r6.createServerSockets(r2, r7, r2, r8)
            fm.liveswitch.StreamSocket[] r8 = new fm.liveswitch.StreamSocket[r2]
            java.lang.Object[] r7 = r7.toArray(r8)
            fm.liveswitch.StreamSocket[] r7 = (fm.liveswitch.StreamSocket[]) r7
            r6._tcpSockets = r7
        L_0x00c2:
            boolean r7 = r6.getDisableTls()
            if (r7 == 0) goto L_0x00d2
            java.lang.String r7 = "TLS is disabled."
            fm.liveswitch.Log.info(r7)
            fm.liveswitch.StreamSocket[] r7 = new fm.liveswitch.StreamSocket[r2]
            r6._tlsSockets = r7
            goto L_0x00e9
        L_0x00d2:
            fm.liveswitch.ServerAddress[] r7 = r6.getTlsAddresses()
            fm.liveswitch.StunServer$3 r8 = new fm.liveswitch.StunServer$3
            r8.<init>()
            java.util.ArrayList r7 = r6.createServerSockets(r2, r7, r1, r8)
            fm.liveswitch.StreamSocket[] r8 = new fm.liveswitch.StreamSocket[r2]
            java.lang.Object[] r7 = r7.toArray(r8)
            fm.liveswitch.StreamSocket[] r7 = (fm.liveswitch.StreamSocket[]) r7
            r6._tlsSockets = r7
        L_0x00e9:
            fm.liveswitch.DatagramSocket[] r7 = r6._udpSockets
            int r8 = r7.length
            r9 = 0
        L_0x00ed:
            if (r9 >= r8) goto L_0x010b
            r0 = r7[r9]
            r6.doUdpReceive(r0)
            fm.liveswitch.ServerAddress r3 = new fm.liveswitch.ServerAddress
            java.lang.String r4 = r0.getLocalIPAddress()
            int r5 = r0.getLocalPort()
            java.lang.String r0 = r0.getPublicIPAddress()
            r3.<init>(r4, r5, r0)
            r6.logSocket(r1, r2, r3)
            int r9 = r9 + 1
            goto L_0x00ed
        L_0x010b:
            fm.liveswitch.StreamSocket[] r7 = r6._tcpSockets
            int r8 = r7.length
            r9 = 0
        L_0x010f:
            if (r9 >= r8) goto L_0x012d
            r0 = r7[r9]
            r6.doTcpAccept(r0)
            fm.liveswitch.ServerAddress r3 = new fm.liveswitch.ServerAddress
            java.lang.String r4 = r0.getLocalIPAddress()
            int r5 = r0.getLocalPort()
            java.lang.String r0 = r0.getPublicIPAddress()
            r3.<init>(r4, r5, r0)
            r6.logSocket(r2, r2, r3)
            int r9 = r9 + 1
            goto L_0x010f
        L_0x012d:
            fm.liveswitch.StreamSocket[] r7 = r6._tlsSockets
            int r8 = r7.length
            r9 = 0
        L_0x0131:
            if (r9 >= r8) goto L_0x014f
            r0 = r7[r9]
            r6.doTcpAccept(r0)
            fm.liveswitch.ServerAddress r3 = new fm.liveswitch.ServerAddress
            java.lang.String r4 = r0.getLocalIPAddress()
            int r5 = r0.getLocalPort()
            java.lang.String r0 = r0.getPublicIPAddress()
            r3.<init>(r4, r5, r0)
            r6.logSocket(r2, r1, r3)
            int r9 = r9 + 1
            goto L_0x0131
        L_0x014f:
            return r1
        L_0x0150:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0150 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.StunServer.start(fm.liveswitch.ServerAddress[], fm.liveswitch.ServerAddress[], fm.liveswitch.ServerAddress[]):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r3 >= r1) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        r0[r3].close();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r0 = r5._tcpSockets;
        r1 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r2 >= r1) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r0[r2].close();
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        removeTcpConnections();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        r0 = r5._udpSockets;
        r1 = r0.length;
        r3 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stop() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__startedLock
            monitor-enter(r0)
            boolean r1 = r5._started     // Catch:{ all -> 0x002d }
            r2 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r2
        L_0x000a:
            r5._started = r2     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            fm.liveswitch.DatagramSocket[] r0 = r5._udpSockets
            int r1 = r0.length
            r3 = 0
        L_0x0011:
            if (r3 >= r1) goto L_0x001b
            r4 = r0[r3]
            r4.close()
            int r3 = r3 + 1
            goto L_0x0011
        L_0x001b:
            fm.liveswitch.StreamSocket[] r0 = r5._tcpSockets
            int r1 = r0.length
        L_0x001e:
            if (r2 >= r1) goto L_0x0028
            r3 = r0[r2]
            r3.close()
            int r2 = r2 + 1
            goto L_0x001e
        L_0x0028:
            r5.removeTcpConnections()
            r0 = 1
            return r0
        L_0x002d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.StunServer.stop():boolean");
    }

    private void tcpAccept(final StreamSocket streamSocket) {
        try {
            streamSocket.acceptAsync(new IAction0() {
                public void invoke() {
                    if (StunServer.this._started) {
                        StunServer.this.doTcpAccept(streamSocket);
                    }
                }
            }, new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    if (StunServer.this._started) {
                        ManagedThread.dispatch(new IAction0() {
                            public void invoke() {
                                StunServer.this.doTcpAccept(streamSocket);
                            }
                        });
                    }
                }
            }, new IAction1<StreamSocket>() {
                public void invoke(StreamSocket streamSocket) {
                    streamSocket.setPublicIPAddress(streamSocket.getPublicIPAddress());
                    try {
                        TurnTcpConnection turnTcpConnection = new TurnTcpConnection(streamSocket, new IActionDelegate2<TurnTcpConnection, CircularDataBuffer>() {
                            public String getId() {
                                return "fm.liveswitch.StunServer.processTcpConnectionReceive";
                            }

                            public void invoke(TurnTcpConnection turnTcpConnection, CircularDataBuffer circularDataBuffer) {
                                StunServer.this.processTcpConnectionReceive(turnTcpConnection, circularDataBuffer);
                            }
                        });
                        StunServer.this.addTcpConnection(turnTcpConnection);
                        turnTcpConnection.setOnClose(new IActionDelegate1<TurnTcpConnection>() {
                            public String getId() {
                                return "fm.liveswitch.StunServer.removeTcpConnection";
                            }

                            public void invoke(TurnTcpConnection turnTcpConnection) {
                                StunServer.this.removeTcpConnection(turnTcpConnection);
                            }
                        });
                        turnTcpConnection.startListening();
                    } catch (Exception e) {
                        Log.error("Could not accept TCP socket.", e);
                    }
                }
            });
        } catch (Exception e) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not accept on server TCP socket. {0}", (Object) e.getMessage()));
            }
            try {
                streamSocket.close();
            } catch (Exception unused) {
            }
        }
    }

    private void udpReceive(final DatagramSocket datagramSocket) {
        try {
            datagramSocket.receiveAsync(new IAction3<DataBuffer, String, Integer>() {
                public void invoke(DataBuffer dataBuffer, String str, Integer num) {
                    try {
                        IntegerHolder integerHolder = new IntegerHolder(0);
                        StunServer.this.processBuffer(dataBuffer, datagramSocket, (StreamSocket) null, new ServerAddress(datagramSocket.getLocalIPAddress(), datagramSocket.getLocalPort(), datagramSocket.getPublicIPAddress()), new TransportAddress(str, num.intValue()), integerHolder);
                        integerHolder.getValue();
                    } catch (Exception unused) {
                    }
                    if (StunServer.this._started) {
                        StunServer.this.doUdpReceive(datagramSocket);
                    }
                }
            }, new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    if (StunServer.this._started) {
                        StunServer.this.doUdpReceive(datagramSocket);
                    }
                }
            });
        } catch (Exception e) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not receive on server UDP socket. {0}", (Object) e.getMessage()));
            }
            datagramSocket.close();
        }
    }
}
