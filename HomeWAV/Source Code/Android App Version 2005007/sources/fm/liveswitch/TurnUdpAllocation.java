package fm.liveswitch;

class TurnUdpAllocation extends TurnAllocation {
    private IAction3<TurnUdpAllocation, TransportAddress, DataBuffer> _onReceive;
    private boolean _receiving = false;
    private Object _receivingLock = new Object();
    private DatagramSocket _socket;
    private StreamSocket _tcpServerSocket;
    private DatagramSocket _udpServerSocket;

    public void close() {
        try {
            super.close();
            getSocket().close();
            if (getTcpServerSocket() != null) {
                getTcpServerSocket().close();
            }
        } catch (Exception e) {
            Log.warn("UDP allocation was unable to close all associated sockets.", e);
        }
    }

    private void doReceive() {
        if (!getIsClosed() && !super.getIsExpired()) {
            receive();
        }
    }

    public boolean getIsClosed() {
        return getSocket().getIsClosed();
    }

    public String getLocalIPAddress() {
        return getSocket().getLocalIPAddress();
    }

    public int getLocalPort() {
        return getSocket().getLocalPort();
    }

    public String getPublicIPAddress() {
        return getSocket().getPublicIPAddress();
    }

    public DatagramSocket getSocket() {
        return this._socket;
    }

    public StreamSocket getTcpServerSocket() {
        return this._tcpServerSocket;
    }

    public DatagramSocket getUdpServerSocket() {
        return this._udpServerSocket;
    }

    private void receive() {
        try {
            getSocket().receiveAsync(new IActionDelegate3<DataBuffer, String, Integer>() {
                public String getId() {
                    return "fm.liveswitch.TurnUdpAllocation.receiveSuccess";
                }

                public void invoke(DataBuffer dataBuffer, String str, Integer num) {
                    TurnUdpAllocation.this.receiveSuccess(dataBuffer, str, num.intValue());
                }
            }, new IActionDelegate1<Exception>() {
                public String getId() {
                    return "fm.liveswitch.TurnUdpAllocation.receiveFailure";
                }

                public void invoke(Exception exc) {
                    TurnUdpAllocation.this.receiveFailure(exc);
                }
            });
        } catch (Exception e) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not receive on allocation socket. {0}", (Object) e.getMessage()));
            }
            getSocket().close();
        }
    }

    /* access modifiers changed from: private */
    public void receiveFailure(Exception exc) {
        doReceive();
    }

    /* access modifiers changed from: private */
    public void receiveSuccess(DataBuffer dataBuffer, String str, int i) {
        try {
            this._onReceive.invoke(this, new TransportAddress(str, i), dataBuffer);
        } catch (Exception unused) {
        }
        doReceive();
    }

    public boolean sendData(DataBuffer dataBuffer, TransportAddress transportAddress, Holder<Exception> holder) {
        try {
            getSocket().send(dataBuffer, transportAddress.getIPAddress(), transportAddress.getPort());
            holder.setValue(null);
            return true;
        } catch (Exception e) {
            holder.setValue(e);
            return false;
        }
    }

    private void setSocket(DatagramSocket datagramSocket) {
        this._socket = datagramSocket;
    }

    private void setTcpServerSocket(StreamSocket streamSocket) {
        this._tcpServerSocket = streamSocket;
    }

    private void setUdpServerSocket(DatagramSocket datagramSocket) {
        this._udpServerSocket = datagramSocket;
    }

    public void startReceiving() {
        synchronized (this._receivingLock) {
            if (!this._receiving) {
                this._receiving = true;
                doReceive();
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TurnUdpAllocation(DatagramSocket datagramSocket, StreamSocket streamSocket, DataBuffer dataBuffer, DataBuffer dataBuffer2, DatagramSocket datagramSocket2, TransportAddress transportAddress, String str, String str2, long j, IAction3<TurnUdpAllocation, TransportAddress, DataBuffer> iAction3, IAction1<TransportAddress> iAction1) {
        super(dataBuffer, dataBuffer2, transportAddress, str, str2, j, iAction1);
        StreamSocket streamSocket2 = streamSocket;
        DatagramSocket datagramSocket3 = datagramSocket2;
        IAction3<TurnUdpAllocation, TransportAddress, DataBuffer> iAction32 = iAction3;
        if (datagramSocket == null && streamSocket2 == null) {
            throw new RuntimeException(new Exception("UDP and TCP server sockets cannot both be null."));
        } else if (datagramSocket3 == null) {
            throw new RuntimeException(new Exception("Socket cannot be null."));
        } else if (iAction32 != null) {
            setUdpServerSocket(datagramSocket);
            setTcpServerSocket(streamSocket);
            setSocket(datagramSocket3);
            this._onReceive = iAction32;
        } else {
            throw new RuntimeException(new Exception("Receive callback cannot be null."));
        }
    }
}
