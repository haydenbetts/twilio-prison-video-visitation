package fm.liveswitch;

class VirtualTcpSocket extends StreamSocket {
    private VirtualTcpSocket __acceptSocket;
    private boolean __iPv6;
    /* access modifiers changed from: private */
    public boolean __isClosed;
    private String __localIPAddress;
    private int __localPort;
    private String __remoteHostname;
    private String __remoteIPAddress;
    private int __remotePort;
    private boolean __secure;
    private boolean __server;
    private String _localHostname;
    private int _sendDelay;
    private VirtualAdapter _virtualAdapter;

    public StreamSocket accept() {
        return accept(-1);
    }

    public StreamSocket accept(int i) {
        if (!getServer()) {
            throw new RuntimeException(new Exception("Client sockets cannot accept."));
        } else if (this.__acceptSocket != null) {
            throw new RuntimeException(new Exception("Accepted server sockets cannot accept."));
        } else if (!getSecure() || getLocalHostname() != null) {
            verifyBound();
            VirtualPacket receive = this._virtualAdapter.receive(getLocalPort(), (String) null, 0, ProtocolType.Tcp, i, VirtualPacketType.Connect);
            if (receive == null) {
                return null;
            }
            if ((getSecure() && !Global.equals(receive.getDestinationHostname(), getLocalHostname())) || (!getSecure() && receive.getDestinationHostname() != null)) {
                return null;
            }
            VirtualPacket virtualPacket = new VirtualPacket();
            virtualPacket.setType(VirtualPacketType.ConnectAck);
            virtualPacket.setProtocolType(ProtocolType.Tcp);
            virtualPacket.setSourceIPAddress(getLocalIPAddress());
            virtualPacket.setSourcePort(getLocalPort());
            virtualPacket.setDestinationIPAddress(receive.getSourceIPAddress());
            virtualPacket.setDestinationPort(receive.getSourcePort());
            if (this._virtualAdapter.send(virtualPacket)) {
                VirtualTcpSocket virtualTcpSocket = new VirtualTcpSocket(this._virtualAdapter, getServer(), getIPv6(), getSecure());
                virtualTcpSocket.__acceptSocket = this;
                virtualTcpSocket.__localIPAddress = getLocalIPAddress();
                virtualTcpSocket.__localPort = getLocalPort();
                virtualTcpSocket.__remoteIPAddress = receive.getSourceIPAddress();
                virtualTcpSocket.__remotePort = receive.getSourcePort();
                return virtualTcpSocket;
            }
            throw new RuntimeException(new Exception("Client aborted connect."));
        } else {
            throw new RuntimeException(new Exception("Server server sockets require a local hostname."));
        }
    }

    public void acceptAsync(final IAction0 iAction0, final IAction1<Exception> iAction1, final IAction1<StreamSocket> iAction12) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    StreamSocket accept = VirtualTcpSocket.this.accept(-1);
                    VirtualTcpSocket.this.raiseAcceptSuccess(iAction0);
                    if (accept != null) {
                        VirtualTcpSocket.this.raiseAcceptSocket(iAction12, accept);
                    }
                } catch (Exception e) {
                    VirtualTcpSocket.this.raiseAcceptFailure(iAction1, e);
                }
            }
        });
    }

    public boolean bind() {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = bind(booleanHolder);
        booleanHolder.getValue();
        return bind;
    }

    public boolean bind(BooleanHolder booleanHolder) {
        return bind((String) null, booleanHolder);
    }

    public boolean bind(String str) {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = bind(str, booleanHolder);
        booleanHolder.getValue();
        return bind;
    }

    public boolean bind(String str, BooleanHolder booleanHolder) {
        return bind(str, 0, booleanHolder);
    }

    public boolean bind(String str, int i) {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = bind(str, i, booleanHolder);
        booleanHolder.getValue();
        return bind;
    }

    public boolean bind(String str, int i, BooleanHolder booleanHolder) {
        if (this.__acceptSocket == null) {
            Holder holder = new Holder(this.__localIPAddress);
            IntegerHolder integerHolder = new IntegerHolder(this.__localPort);
            boolean bind = this._virtualAdapter.bind(ProtocolType.Tcp, str, i, booleanHolder, holder, integerHolder);
            this.__localIPAddress = (String) holder.getValue();
            this.__localPort = integerHolder.getValue();
            return bind;
        }
        throw new RuntimeException(new Exception("Accepted server sockets cannot bind."));
    }

    public boolean bind(int i) {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = bind(i, booleanHolder);
        booleanHolder.getValue();
        return bind;
    }

    public boolean bind(int i, BooleanHolder booleanHolder) {
        return bind((String) null, i, booleanHolder);
    }

    public void close() {
        this._virtualAdapter.unbind(ProtocolType.Tcp, this.__localPort);
        this.__isClosed = true;
    }

    public boolean connect(String str, String str2, int i, int i2) {
        if (getServer()) {
            throw new RuntimeException(new Exception("Server sockets cannot connect."));
        } else if (this.__acceptSocket == null) {
            verifyBound();
            VirtualPacket virtualPacket = new VirtualPacket();
            virtualPacket.setType(VirtualPacketType.Connect);
            virtualPacket.setProtocolType(ProtocolType.Tcp);
            virtualPacket.setSourceIPAddress(getLocalIPAddress());
            virtualPacket.setSourcePort(getLocalPort());
            virtualPacket.setDestinationHostname(getSecure() ? str : null);
            virtualPacket.setDestinationIPAddress(str2);
            virtualPacket.setDestinationPort(i);
            if (!this._virtualAdapter.send(virtualPacket)) {
                return false;
            }
            if (this._virtualAdapter.receive(getLocalPort(), str2, i, ProtocolType.Tcp, i2, VirtualPacketType.ConnectAck) == null) {
                return false;
            }
            this.__remoteHostname = str;
            this.__remoteIPAddress = str2;
            this.__remotePort = i;
            return true;
        } else {
            throw new RuntimeException(new Exception("Accepted server sockets cannot connect."));
        }
    }

    public void connectAsync(String str, String str2, int i, int i2, IAction0 iAction0, IAction2<Exception, Boolean> iAction2) {
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        final IAction0 iAction02 = iAction0;
        final IAction2<Exception, Boolean> iAction22 = iAction2;
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    VirtualTcpSocket.this.connect(str3, str4, i3, i4);
                    VirtualTcpSocket.this.raiseConnectSuccess(iAction02);
                } catch (Exception e) {
                    VirtualTcpSocket.this.raiseConnectFailure(iAction22, e, false);
                }
            }
        });
    }

    public boolean getIPv6() {
        return this.__iPv6;
    }

    public boolean getIsClosed() {
        return this.__isClosed;
    }

    public String getLocalHostname() {
        return this._localHostname;
    }

    public String getLocalIPAddress() {
        return this.__localIPAddress;
    }

    public int getLocalPort() {
        return this.__localPort;
    }

    public String getRemoteHostname() {
        return this.__remoteHostname;
    }

    public String getRemoteIPAddress() {
        return this.__remoteIPAddress;
    }

    public int getRemotePort() {
        return this.__remotePort;
    }

    public boolean getSecure() {
        return this.__secure;
    }

    public int getSendDelay() {
        return this._sendDelay;
    }

    public boolean getServer() {
        return this.__server;
    }

    public VirtualPacket receive() {
        return receive(-1);
    }

    public VirtualPacket receive(int i) {
        verifyBound();
        verifyConnected();
        VirtualPacket receive = this._virtualAdapter.receive(getLocalPort(), getRemoteIPAddress(), getRemotePort(), ProtocolType.Tcp, i, VirtualPacketType.Data);
        if (receive != null) {
            VirtualPacket virtualPacket = new VirtualPacket();
            virtualPacket.setType(VirtualPacketType.DataAck);
            virtualPacket.setProtocolType(ProtocolType.Tcp);
            virtualPacket.setSourceIPAddress(getLocalIPAddress());
            virtualPacket.setSourcePort(getLocalPort());
            virtualPacket.setDestinationIPAddress(getRemoteIPAddress());
            virtualPacket.setDestinationPort(getRemotePort());
            this._virtualAdapter.send(virtualPacket);
        }
        return receive;
    }

    public void receiveAsync(final int i) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    VirtualPacket receive = VirtualTcpSocket.this.receive(i);
                    if (receive != null) {
                        VirtualTcpSocket virtualTcpSocket = VirtualTcpSocket.this;
                        virtualTcpSocket.raiseReceiveSuccess(virtualTcpSocket.getOnReceiveSuccess(), receive.getPayload());
                    } else if (VirtualTcpSocket.this.__isClosed) {
                        VirtualTcpSocket virtualTcpSocket2 = VirtualTcpSocket.this;
                        virtualTcpSocket2.raiseReceiveFailure(virtualTcpSocket2.getOnReceiveFailure(), new Exception("Socket closed."), false);
                    } else {
                        VirtualTcpSocket virtualTcpSocket3 = VirtualTcpSocket.this;
                        virtualTcpSocket3.raiseReceiveFailure(virtualTcpSocket3.getOnReceiveFailure(), new Exception("Receive timed out."), true);
                    }
                } catch (Exception e) {
                    VirtualTcpSocket virtualTcpSocket4 = VirtualTcpSocket.this;
                    virtualTcpSocket4.raiseReceiveFailure(virtualTcpSocket4.getOnReceiveFailure(), e, false);
                }
            }
        });
    }

    public boolean send(DataBuffer dataBuffer) {
        return send(dataBuffer, -1);
    }

    public boolean send(DataBuffer dataBuffer, int i) {
        verifyBound();
        verifyConnected();
        VirtualPacket virtualPacket = new VirtualPacket();
        virtualPacket.setType(VirtualPacketType.Data);
        virtualPacket.setProtocolType(ProtocolType.Tcp);
        virtualPacket.setSourceIPAddress(getLocalIPAddress());
        virtualPacket.setSourcePort(getLocalPort());
        virtualPacket.setDestinationIPAddress(getRemoteIPAddress());
        virtualPacket.setDestinationPort(getRemotePort());
        virtualPacket.setPayload(dataBuffer.copy());
        if (!this._virtualAdapter.send(virtualPacket)) {
            return false;
        }
        if (this._virtualAdapter.receive(getLocalPort(), getRemoteIPAddress(), getRemotePort(), ProtocolType.Tcp, i, VirtualPacketType.DataAck) == null) {
            return false;
        }
        return true;
    }

    public void sendAsync(final DataBuffer dataBuffer, int i, final IAction0 iAction0, final IAction2<Exception, Boolean> iAction2) {
        dataBuffer.keep();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    VirtualTcpSocket.this.send(dataBuffer);
                    VirtualTcpSocket.this.raiseSendSuccess(iAction0);
                } catch (Exception e) {
                    VirtualTcpSocket.this.raiseSendFailure(iAction2, e, false);
                } catch (Throwable th) {
                    dataBuffer.free();
                    throw th;
                }
                dataBuffer.free();
            }
        });
    }

    public void setLocalHostname(String str) {
        this._localHostname = str;
    }

    public void setSendDelay(int i) {
        this._sendDelay = i;
    }

    public String toString() {
        if (StringExtensions.isNullOrEmpty(getRemoteIPAddress())) {
            return StringExtensions.format("{0}:{1}", getLocalIPAddress(), IntegerExtensions.toString(Integer.valueOf(getLocalPort())));
        }
        return StringExtensions.format("{0}:{1} -> {2}:{3}", new Object[]{getLocalIPAddress(), IntegerExtensions.toString(Integer.valueOf(getLocalPort())), getRemoteIPAddress(), IntegerExtensions.toString(Integer.valueOf(getRemotePort()))});
    }

    private void verifyBound() {
        if (StringExtensions.isNullOrEmpty(getLocalIPAddress())) {
            BooleanHolder booleanHolder = new BooleanHolder(false);
            boolean bind = bind(booleanHolder);
            booleanHolder.getValue();
            if (!bind) {
                throw new RuntimeException(new Exception("No ports available."));
            }
        }
    }

    private void verifyConnected() {
        if (getServer() && this.__acceptSocket == null) {
            throw new RuntimeException(new Exception("Accepting server socket cannot be connected."));
        } else if (this.__remoteIPAddress == null) {
            throw new RuntimeException(new Exception("Socket is not connected."));
        }
    }

    public VirtualTcpSocket(VirtualAdapter virtualAdapter, boolean z, boolean z2, boolean z3) {
        this._virtualAdapter = virtualAdapter;
        this.__server = z;
        this.__iPv6 = z2;
        this.__secure = z3;
    }
}
