package fm.liveswitch;

class VirtualUdpSocket extends DatagramSocket {
    private boolean __iPv6;
    /* access modifiers changed from: private */
    public boolean __isClosed;
    private String __localIPAddress;
    private int __localPort;
    private int _maxQueuedPackets;
    private int _sendDelay;
    private VirtualAdapter _virtualAdapter;

    public int getReceiveBufferSize() {
        return -1;
    }

    public int getSendBufferSize() {
        return -1;
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
        Holder holder = new Holder(this.__localIPAddress);
        IntegerHolder integerHolder = new IntegerHolder(this.__localPort);
        boolean bind = this._virtualAdapter.bind(ProtocolType.Udp, str, i, booleanHolder, holder, integerHolder);
        this.__localIPAddress = (String) holder.getValue();
        this.__localPort = integerHolder.getValue();
        return bind;
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
        this._virtualAdapter.unbind(ProtocolType.Udp, this.__localPort);
        this.__isClosed = true;
    }

    public boolean getIPv6() {
        return this.__iPv6;
    }

    public boolean getIsClosed() {
        return this.__isClosed;
    }

    public String getLocalIPAddress() {
        return this.__localIPAddress;
    }

    public int getLocalPort() {
        return this.__localPort;
    }

    public int getMaxQueuedPackets() {
        return this._maxQueuedPackets;
    }

    public int getSendDelay() {
        return this._sendDelay;
    }

    public VirtualPacket receive() {
        while (!getIsClosed()) {
            VirtualPacket receive = receive(1);
            if (receive != null) {
                return receive;
            }
        }
        return null;
    }

    public VirtualPacket receive(int i) {
        verifyBound();
        return this._virtualAdapter.receive(getLocalPort(), (String) null, 0, ProtocolType.Udp, i, VirtualPacketType.Data);
    }

    public void receiveAsync(final IAction3<DataBuffer, String, Integer> iAction3, final IAction1<Exception> iAction1) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    VirtualPacket receive = VirtualUdpSocket.this.receive();
                    if (receive != null) {
                        VirtualUdpSocket.this.raiseReceiveSuccess(iAction3, receive.getPayload(), receive.getSourceIPAddress(), receive.getSourcePort());
                    } else if (VirtualUdpSocket.this.__isClosed) {
                        VirtualUdpSocket.this.raiseReceiveFailure(iAction1, new Exception("Socket closed."));
                    } else {
                        VirtualUdpSocket.this.raiseReceiveFailure(iAction1, new Exception("Receive timed out."));
                    }
                } catch (Exception e) {
                    VirtualUdpSocket.this.raiseReceiveFailure(iAction1, e);
                }
            }
        });
    }

    public Error send(DataBuffer dataBuffer, String str, int i) {
        verifyBound();
        VirtualPacket virtualPacket = new VirtualPacket(getSendDelay());
        virtualPacket.setProtocolType(ProtocolType.Udp);
        virtualPacket.setSourceIPAddress(getLocalIPAddress());
        virtualPacket.setSourcePort(getLocalPort());
        virtualPacket.setDestinationIPAddress(str);
        virtualPacket.setDestinationPort(i);
        virtualPacket.setPayload(dataBuffer.copy());
        this._virtualAdapter.send(virtualPacket);
        return null;
    }

    public void setMaxQueuedPackets(int i) {
        this._maxQueuedPackets = i;
    }

    public void setSendDelay(int i) {
        this._sendDelay = i;
    }

    public String toString() {
        return StringExtensions.format("{0}:{1}", getLocalIPAddress(), IntegerExtensions.toString(Integer.valueOf(getLocalPort())));
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

    public VirtualUdpSocket(VirtualAdapter virtualAdapter) {
        this(virtualAdapter, false);
    }

    public VirtualUdpSocket(VirtualAdapter virtualAdapter, boolean z) {
        this._virtualAdapter = virtualAdapter;
        this.__iPv6 = z;
    }
}
