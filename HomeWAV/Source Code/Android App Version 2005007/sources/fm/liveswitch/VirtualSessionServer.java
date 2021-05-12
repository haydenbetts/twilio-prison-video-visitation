package fm.liveswitch;

import java.util.HashMap;

class VirtualSessionServer {
    private HashMap<String, VirtualClient> _clients = new HashMap<>();
    private Object _clientsLock = new Object();

    public void add(VirtualClient virtualClient) {
        synchronized (this._clientsLock) {
            HashMapExtensions.add(this._clients, virtualClient.getName(), virtualClient);
            for (VirtualClient next : HashMapExtensions.getValues(this._clients)) {
                if (!Global.equals(next, virtualClient)) {
                    next.sessionServerNotify(VirtualSessionServerEventType.AddedClient, virtualClient.getName());
                }
            }
        }
    }

    private VirtualClient getClient(String str) {
        VirtualClient virtualClient;
        synchronized (this._clientsLock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this._clients, str, holder);
            virtualClient = (VirtualClient) holder.getValue();
        }
        return virtualClient;
    }

    public void remove(VirtualClient virtualClient) {
        synchronized (this._clientsLock) {
            HashMapExtensions.remove(this._clients, virtualClient.getName());
            for (VirtualClient sessionServerNotify : HashMapExtensions.getValues(this._clients)) {
                sessionServerNotify.sessionServerNotify(VirtualSessionServerEventType.RemovedClient, virtualClient.getName());
            }
        }
    }

    public void sendCandidate(Candidate candidate, String str) {
        synchronized (this._clientsLock) {
            for (VirtualClient next : HashMapExtensions.getValues(this._clients)) {
                if (!Global.equals(next.getName(), str)) {
                    next.setCandidate(candidate, str);
                }
            }
        }
    }
}
