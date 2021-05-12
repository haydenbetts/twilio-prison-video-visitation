package fm.liveswitch;

public abstract class StreamSocket extends ManagedSocket {
    private static ILog __log = Log.getLogger(DatagramSocket.class);
    private IAction2<Exception, Boolean> _onReceiveFailure;
    private IAction1<DataBuffer> _onReceiveSuccess;

    public abstract void acceptAsync(IAction0 iAction0, IAction1<Exception> iAction1, IAction1<StreamSocket> iAction12);

    public abstract void connectAsync(String str, String str2, int i, int i2, IAction0 iAction0, IAction2<Exception, Boolean> iAction2);

    public abstract String getRemoteHostname();

    public abstract String getRemoteIPAddress();

    public abstract int getRemotePort();

    public abstract boolean getSecure();

    public abstract boolean getServer();

    public abstract void receiveAsync(int i);

    public abstract boolean send(DataBuffer dataBuffer);

    public abstract void sendAsync(DataBuffer dataBuffer, int i, IAction0 iAction0, IAction2<Exception, Boolean> iAction2);

    public IAction2<Exception, Boolean> getOnReceiveFailure() {
        return this._onReceiveFailure;
    }

    public IAction1<DataBuffer> getOnReceiveSuccess() {
        return this._onReceiveSuccess;
    }

    /* access modifiers changed from: protected */
    public void raiseAcceptFailure(IAction1<Exception> iAction1, Exception exc) {
        if (iAction1 != null) {
            try {
                iAction1.invoke(exc);
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising accept failure.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseAcceptSocket(IAction1<StreamSocket> iAction1, StreamSocket streamSocket) {
        if (iAction1 != null) {
            try {
                iAction1.invoke(streamSocket);
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising accept socket.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseAcceptSuccess(IAction0 iAction0) {
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising accept success.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseConnectFailure(IAction2<Exception, Boolean> iAction2, Exception exc, boolean z) {
        if (iAction2 != null) {
            try {
                iAction2.invoke(exc, Boolean.valueOf(z));
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising connect failure.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseConnectSuccess(IAction0 iAction0) {
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising connect success.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseReceiveFailure(IAction2<Exception, Boolean> iAction2, Exception exc, boolean z) {
        if (iAction2 != null) {
            try {
                iAction2.invoke(exc, Boolean.valueOf(z));
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising receive failure.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseReceiveSuccess(IAction1<DataBuffer> iAction1, DataBuffer dataBuffer) {
        if (iAction1 != null) {
            try {
                iAction1.invoke(dataBuffer);
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising receive success.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseSendFailure(IAction2<Exception, Boolean> iAction2, Exception exc, boolean z) {
        if (iAction2 != null) {
            try {
                iAction2.invoke(exc, Boolean.valueOf(z));
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising send failure.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseSendSuccess(IAction0 iAction0) {
        if (iAction0 != null) {
            try {
                iAction0.invoke();
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising send success.", e);
            }
        }
    }

    public void setOnReceiveFailure(IAction2<Exception, Boolean> iAction2) {
        this._onReceiveFailure = iAction2;
    }

    public void setOnReceiveSuccess(IAction1<DataBuffer> iAction1) {
        this._onReceiveSuccess = iAction1;
    }

    protected StreamSocket() {
    }
}
