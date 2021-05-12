package fm.liveswitch;

class IceTransportOptions {
    private int _deadStreamTimeout;
    private int _keepAliveInterval;
    private IceKeepAlivePolicy _keepAlivePolicy;

    public int getDeadStreamTimeout() {
        return this._deadStreamTimeout;
    }

    public int getKeepAliveInterval() {
        return this._keepAliveInterval;
    }

    public IceKeepAlivePolicy getKeepAlivePolicy() {
        return this._keepAlivePolicy;
    }

    public IceTransportOptions(IceKeepAlivePolicy iceKeepAlivePolicy, int i, int i2) {
        setKeepAlivePolicy(iceKeepAlivePolicy);
        setDeadStreamTimeout(i);
        setKeepAliveInterval(i2);
    }

    private void setDeadStreamTimeout(int i) {
        this._deadStreamTimeout = i;
    }

    private void setKeepAliveInterval(int i) {
        this._keepAliveInterval = i;
    }

    private void setKeepAlivePolicy(IceKeepAlivePolicy iceKeepAlivePolicy) {
        this._keepAlivePolicy = iceKeepAlivePolicy;
    }
}
