package fm.liveswitch;

public class StreamSocketCreateArgs {
    private boolean _iPv6;
    private boolean _secure;
    private boolean _server;
    private int _streamIndex;

    public boolean getIPv6() {
        return this._iPv6;
    }

    public boolean getSecure() {
        return this._secure;
    }

    public boolean getServer() {
        return this._server;
    }

    public int getStreamIndex() {
        return this._streamIndex;
    }

    private void setIPv6(boolean z) {
        this._iPv6 = z;
    }

    private void setSecure(boolean z) {
        this._secure = z;
    }

    private void setServer(boolean z) {
        this._server = z;
    }

    public void setStreamIndex(int i) {
        this._streamIndex = i;
    }

    public StreamSocketCreateArgs(boolean z, boolean z2, boolean z3) {
        setServer(z);
        setIPv6(z2);
        setSecure(z3);
    }
}
