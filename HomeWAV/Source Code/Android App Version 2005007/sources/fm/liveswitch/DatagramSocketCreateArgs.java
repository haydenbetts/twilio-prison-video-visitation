package fm.liveswitch;

public class DatagramSocketCreateArgs {
    private boolean _iPv6;
    private int _streamIndex;

    public DatagramSocketCreateArgs(boolean z) {
        setIPv6(z);
    }

    public boolean getIPv6() {
        return this._iPv6;
    }

    public int getStreamIndex() {
        return this._streamIndex;
    }

    private void setIPv6(boolean z) {
        this._iPv6 = z;
    }

    public void setStreamIndex(int i) {
        this._streamIndex = i;
    }
}
