package fm.liveswitch;

class IceSocketsServerPair {
    private IceServer _server;
    private ManagedSocket[] _sockets;

    public IceServer getServer() {
        return this._server;
    }

    public ManagedSocket[] getSockets() {
        return this._sockets;
    }

    public IceSocketsServerPair(ManagedSocket[] managedSocketArr, IceServer iceServer) {
        setSockets(managedSocketArr);
        setServer(iceServer);
    }

    private void setServer(IceServer iceServer) {
        this._server = iceServer;
    }

    private void setSockets(ManagedSocket[] managedSocketArr) {
        this._sockets = managedSocketArr;
    }
}
