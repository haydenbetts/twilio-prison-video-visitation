package fm.liveswitch;

class IceGatherOptions {
    private static int __unset = -1;
    private AddressType[] __addressTypes;
    private IceGatherPolicy __policy;
    private IcePortRange __portRange;
    private IceServer[] __servers;
    private int __stunBindingRequestLimit;
    private int __stunRequestTimeout;
    private int __tcpConnectTimeout;
    private int __turnAllocateRequestLimit;
    private IFunction1<DatagramSocketCreateArgs, DatagramSocket> _createDatagramSocket;
    private IFunction1<StreamSocketCreateArgs, StreamSocket> _createStreamSocket;
    private String[] _privateIPAddresses;
    private String[] _publicIPAddresses;
    private int _streamIndex;

    public AddressType[] getAddressTypes() {
        return this.__addressTypes;
    }

    public IFunction1<DatagramSocketCreateArgs, DatagramSocket> getCreateDatagramSocket() {
        return this._createDatagramSocket;
    }

    public IFunction1<StreamSocketCreateArgs, StreamSocket> getCreateStreamSocket() {
        return this._createStreamSocket;
    }

    public IceGatherPolicy getPolicy() {
        return this.__policy;
    }

    public IcePortRange getPortRange() {
        return this.__portRange;
    }

    public String[] getPrivateIPAddresses() {
        return this._privateIPAddresses;
    }

    public String[] getPublicIPAddresses() {
        return this._publicIPAddresses;
    }

    public IceServer getServer() {
        return (IceServer) Utility.firstOrDefault((T[]) this.__servers);
    }

    public IceServer[] getServers() {
        return this.__servers;
    }

    public int getStreamIndex() {
        return this._streamIndex;
    }

    public int getStunBindingRequestLimit() {
        return this.__stunBindingRequestLimit;
    }

    public int getStunRequestTimeout() {
        return this.__stunRequestTimeout;
    }

    public int getTcpConnectTimeout() {
        return this.__tcpConnectTimeout;
    }

    public int getTurnAllocateRequestLimit() {
        return this.__turnAllocateRequestLimit;
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy) {
        this(iceGatherPolicy, (IceServer[]) null);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr) {
        this(iceGatherPolicy, iceServerArr, (IcePortRange) null);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange) {
        this(iceGatherPolicy, iceServerArr, icePortRange, (AddressType[]) null);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange, AddressType[] addressTypeArr) {
        this(iceGatherPolicy, iceServerArr, icePortRange, addressTypeArr, __unset);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange, AddressType[] addressTypeArr, int i) {
        this(iceGatherPolicy, iceServerArr, icePortRange, addressTypeArr, i, __unset);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange, AddressType[] addressTypeArr, int i, int i2) {
        this(iceGatherPolicy, iceServerArr, icePortRange, addressTypeArr, i, i2, __unset);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange, AddressType[] addressTypeArr, int i, int i2, int i3) {
        this(iceGatherPolicy, iceServerArr, icePortRange, addressTypeArr, i, i2, i3, __unset);
    }

    public IceGatherOptions(IceGatherPolicy iceGatherPolicy, IceServer[] iceServerArr, IcePortRange icePortRange, AddressType[] addressTypeArr, int i, int i2, int i3, int i4) {
        iceServerArr = iceServerArr == null ? new IceServer[0] : iceServerArr;
        icePortRange = icePortRange == null ? new IcePortRange() : icePortRange;
        addressTypeArr = addressTypeArr == null ? new AddressType[]{AddressType.IPv4, AddressType.IPv6} : addressTypeArr;
        int i5 = __unset;
        i = i == i5 ? 3000 : i;
        i2 = i2 == i5 ? 3000 : i2;
        i4 = i4 == i5 ? 5 : i4;
        i3 = i3 == i5 ? 5 : i3;
        this.__policy = iceGatherPolicy;
        this.__servers = iceServerArr;
        this.__portRange = icePortRange;
        this.__addressTypes = addressTypeArr;
        this.__stunRequestTimeout = i;
        this.__turnAllocateRequestLimit = i3;
        this.__tcpConnectTimeout = i2;
        this.__stunBindingRequestLimit = i4;
    }

    public void setCreateDatagramSocket(IFunction1<DatagramSocketCreateArgs, DatagramSocket> iFunction1) {
        this._createDatagramSocket = iFunction1;
    }

    public void setCreateStreamSocket(IFunction1<StreamSocketCreateArgs, StreamSocket> iFunction1) {
        this._createStreamSocket = iFunction1;
    }

    public void setPrivateIPAddresses(String[] strArr) {
        this._privateIPAddresses = strArr;
    }

    public void setPublicIPAddresses(String[] strArr) {
        this._publicIPAddresses = strArr;
    }

    /* access modifiers changed from: package-private */
    public void setServers(IceServer[] iceServerArr) {
        this.__servers = iceServerArr;
    }

    public void setStreamIndex(int i) {
        this._streamIndex = i;
    }
}
