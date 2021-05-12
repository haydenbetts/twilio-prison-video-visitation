package fm.liveswitch;

abstract class VirtualDevice {
    private int _ipAddress1;
    private int _ipAddress2;
    private int _ipAddress3;
    private int _ipAddress4;
    private VirtualNetwork _network;
    private Object _stateLock = new Object();
    private boolean _useDhcp;

    /* access modifiers changed from: protected */
    public abstract boolean doProcessSend(VirtualPacket virtualPacket);

    /* access modifiers changed from: protected */
    public abstract boolean doRaiseSend(VirtualPacket virtualPacket);

    public String getIPAddress() {
        return StringExtensions.format("{0}.{1}.{2}.{3}", new Object[]{IntegerExtensions.toString(Integer.valueOf(getIPAddress1())), IntegerExtensions.toString(Integer.valueOf(getIPAddress2())), IntegerExtensions.toString(Integer.valueOf(getIPAddress3())), IntegerExtensions.toString(Integer.valueOf(getIPAddress4()))});
    }

    public int getIPAddress1() {
        return this._ipAddress1;
    }

    public int getIPAddress2() {
        return this._ipAddress2;
    }

    public int getIPAddress3() {
        return this._ipAddress3;
    }

    public int getIPAddress4() {
        return this._ipAddress4;
    }

    public VirtualNetwork getNetwork() {
        return this._network;
    }

    public boolean getUseDhcp() {
        return this._useDhcp;
    }

    /* access modifiers changed from: package-private */
    public boolean processSend(VirtualPacket virtualPacket) {
        return doProcessSend(virtualPacket);
    }

    /* access modifiers changed from: package-private */
    public boolean raiseSend(VirtualPacket virtualPacket) {
        return doRaiseSend(virtualPacket);
    }

    /* access modifiers changed from: package-private */
    public void setAddressAndNetwork(int i, int i2, int i3, int i4, VirtualNetwork virtualNetwork) {
        synchronized (this._stateLock) {
            setIPAddress1(i);
            setIPAddress2(i2);
            setIPAddress3(i3);
            setIPAddress4(i4);
            setNetwork(virtualNetwork);
        }
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress1(int i) {
        this._ipAddress1 = i;
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress2(int i) {
        this._ipAddress2 = i;
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress3(int i) {
        this._ipAddress3 = i;
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress4(int i) {
        this._ipAddress4 = i;
    }

    /* access modifiers changed from: package-private */
    public void setNetwork(VirtualNetwork virtualNetwork) {
        this._network = virtualNetwork;
    }

    private void setUseDhcp(boolean z) {
        this._useDhcp = z;
    }

    public String toString() {
        return getIPAddress();
    }

    public VirtualDevice() {
        setUseDhcp(true);
    }

    public VirtualDevice(int i, int i2, int i3, int i4) {
        setUseDhcp(false);
        setIPAddress1(i);
        setIPAddress2(i2);
        setIPAddress3(i3);
        setIPAddress4(i4);
    }
}
