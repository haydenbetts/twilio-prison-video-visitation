package fm.liveswitch;

class TurnTcpConnection extends Dynamic {
    private Object __lock = new Object();
    private CircularDataBuffer __receiveBuffer = CircularDataBuffer.create(2048);
    private TransportAddress _clientAddress;
    private Object _closeLock = new Object();
    private String _id;
    private boolean _listening = false;
    private Object _listeningLock = new Object();
    private IAction1<TurnTcpConnection> _onClose;
    private IAction2<TurnTcpConnection, CircularDataBuffer> _onReceive;
    private boolean _raisedClose = false;
    private ServerAddress _serverAddress;
    private StreamSocket _socket;

    public void close() {
        getSocket().close();
        raiseClose();
    }

    private void doReceive() {
        if (getIsClosed()) {
            raiseClose();
        } else {
            receive();
        }
    }

    public TransportAddress getClientAddress() {
        return this._clientAddress;
    }

    public String getId() {
        return this._id;
    }

    public boolean getIsClosed() {
        return getSocket().getIsClosed();
    }

    public IAction1<TurnTcpConnection> getOnClose() {
        return this._onClose;
    }

    public ServerAddress getServerAddress() {
        return this._serverAddress;
    }

    public StreamSocket getSocket() {
        return this._socket;
    }

    private void processBuffer(DataBuffer dataBuffer) {
        synchronized (this.__lock) {
            this.__receiveBuffer.appendDataBuffer(dataBuffer);
            this._onReceive.invoke(this, this.__receiveBuffer);
        }
        if (getIsClosed()) {
            raiseClose();
        }
    }

    /* access modifiers changed from: package-private */
    public void processReceiveFailure(Exception exc, boolean z) {
        if (getIsClosed()) {
            raiseClose();
        } else {
            doReceive();
        }
    }

    /* access modifiers changed from: package-private */
    public void processReceiveSuccess(DataBuffer dataBuffer) {
        try {
            processBuffer(dataBuffer);
        } catch (Exception unused) {
        }
        if (getIsClosed()) {
            raiseClose();
        } else {
            doReceive();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000e, code lost:
        r0 = getOnClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r0 == null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r0.invoke(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2.__receiveBuffer.free();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean raiseClose() {
        /*
            r2 = this;
            java.lang.Object r0 = r2._closeLock
            monitor-enter(r0)
            boolean r1 = r2._raisedClose     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x000a
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return r1
        L_0x000a:
            r1 = 1
            r2._raisedClose = r1     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            fm.liveswitch.IAction1 r0 = r2.getOnClose()
            if (r0 == 0) goto L_0x0017
            r0.invoke(r2)
        L_0x0017:
            fm.liveswitch.CircularDataBuffer r0 = r2.__receiveBuffer
            r0.free()
            return r1
        L_0x001d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TurnTcpConnection.raiseClose():boolean");
    }

    private void receive() {
        try {
            getSocket().receiveAsync(0);
        } catch (Exception e) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not receive on server TCP socket. {0}", (Object) e.getMessage()));
            }
            getSocket().close();
            raiseClose();
        }
    }

    private void setClientAddress(TransportAddress transportAddress) {
        this._clientAddress = transportAddress;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setOnClose(IAction1<TurnTcpConnection> iAction1) {
        this._onClose = iAction1;
    }

    private void setServerAddress(ServerAddress serverAddress) {
        this._serverAddress = serverAddress;
    }

    private void setSocket(StreamSocket streamSocket) {
        this._socket = streamSocket;
    }

    public void startListening() {
        synchronized (this._listeningLock) {
            if (!this._listening) {
                this._listening = true;
                doReceive();
            }
        }
    }

    public TurnTcpConnection(StreamSocket streamSocket, IAction2<TurnTcpConnection, CircularDataBuffer> iAction2) {
        setSocket(streamSocket);
        getSocket().setOnReceiveSuccess(new IActionDelegate1<DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.TurnTcpConnection.processReceiveSuccess";
            }

            public void invoke(DataBuffer dataBuffer) {
                TurnTcpConnection.this.processReceiveSuccess(dataBuffer);
            }
        });
        getSocket().setOnReceiveFailure(new IActionDelegate2<Exception, Boolean>() {
            public String getId() {
                return "fm.liveswitch.TurnTcpConnection.processReceiveFailure";
            }

            public void invoke(Exception exc, Boolean bool) {
                TurnTcpConnection.this.processReceiveFailure(exc, bool.booleanValue());
            }
        });
        this._onReceive = iAction2;
        setClientAddress(new TransportAddress(streamSocket.getRemoteIPAddress(), streamSocket.getRemotePort()));
        setServerAddress(new ServerAddress(streamSocket.getLocalIPAddress(), streamSocket.getLocalPort(), streamSocket.getPublicIPAddress()));
        setId(StringExtensions.concat(getClientAddress().toString(), "|", getServerAddress().toString()));
    }
}
