package fm.liveswitch;

class LocalAddress {
    private long _adapterSpeed;
    private String _ipAddress;
    private String _mask;
    private NetworkType _networkType;

    public long getAdapterSpeed() {
        return this._adapterSpeed;
    }

    public String getIPAddress() {
        return this._ipAddress;
    }

    public String getMask() {
        return this._mask;
    }

    public NetworkType getNetworkType() {
        return this._networkType;
    }

    public boolean isLocalAddress(String str) {
        return TransportAddress.checkMask(getIPAddress(), str, getMask());
    }

    public LocalAddress(String str, String str2, long j, NetworkType networkType) {
        setIPAddress(TransportAddress.sanitizeIPAddress(str));
        setMask(TransportAddress.sanitizeIPAddress(str2));
        setAdapterSpeed(j);
        setNetworkType(networkType);
    }

    public void setAdapterSpeed(long j) {
        this._adapterSpeed = j;
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setMask(String str) {
        this._mask = str;
    }

    public void setNetworkType(NetworkType networkType) {
        this._networkType = networkType;
    }

    public String toString() {
        return getIPAddress();
    }
}
