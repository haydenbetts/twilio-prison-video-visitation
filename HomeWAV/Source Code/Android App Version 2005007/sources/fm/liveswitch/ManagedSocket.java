package fm.liveswitch;

public abstract class ManagedSocket {
    private long _adapterSpeed;
    private String _publicIPAddress;

    public abstract boolean bind(String str, int i, BooleanHolder booleanHolder);

    public abstract void close();

    public abstract boolean getIPv6();

    public abstract boolean getIsClosed();

    public abstract String getLocalIPAddress();

    public abstract int getLocalPort();

    public long getAdapterSpeed() {
        return this._adapterSpeed;
    }

    public String getPublicIPAddress() {
        return this._publicIPAddress;
    }

    protected ManagedSocket() {
    }

    public void setAdapterSpeed(long j) {
        this._adapterSpeed = j;
    }

    public void setPublicIPAddress(String str) {
        this._publicIPAddress = str;
    }
}
