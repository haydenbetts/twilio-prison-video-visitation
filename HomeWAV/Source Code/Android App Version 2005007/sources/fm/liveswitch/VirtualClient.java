package fm.liveswitch;

class VirtualClient {
    private VirtualAdapter[] _adapters;
    private Connection _connection;
    private String _name;

    public void send(String str) {
    }

    public void sessionServerNotify(VirtualSessionServerEventType virtualSessionServerEventType, String str) {
    }

    public VirtualAdapter[] getAdapters() {
        return this._adapters;
    }

    public Connection getConnection() {
        return this._connection;
    }

    public String getName() {
        return this._name;
    }

    private void setAdapters(VirtualAdapter[] virtualAdapterArr) {
        this._adapters = virtualAdapterArr;
    }

    public void setCandidate(Candidate candidate, String str) {
        if (getConnection() != null) {
            getConnection().addRemoteCandidate(candidate);
        }
    }

    private void setConnection(Connection connection) {
        this._connection = connection;
    }

    private void setName(String str) {
        this._name = str;
    }

    public VirtualClient(VirtualSessionServer virtualSessionServer, String str, VirtualAdapter[] virtualAdapterArr, Connection connection) {
        setName(str);
        setAdapters(virtualAdapterArr);
        setConnection(connection);
    }
}
