package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagedConnectionCollection extends Collection<ManagedConnection, ManagedConnectionCollection> {
    private HashMap<String, ManagedConnection> __lookup = new HashMap<>();
    private Object __lookupLock = new Object();
    private HashMap<String, ManagedConnection> __remoteLookup = new HashMap<>();

    /* access modifiers changed from: protected */
    public void addSuccess(ManagedConnection managedConnection) {
        super.addSuccess(managedConnection);
        synchronized (this.__lookupLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__lookup), managedConnection.getId(), managedConnection);
            if (managedConnection.getRemoteConnectionId() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(this.__remoteLookup), managedConnection.getRemoteConnectionId(), managedConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ManagedConnection[] arrayFromList(ArrayList<ManagedConnection> arrayList) {
        return (ManagedConnection[]) arrayList.toArray(new ManagedConnection[0]);
    }

    /* access modifiers changed from: protected */
    public ManagedConnectionCollection createCollection() {
        return new ManagedConnectionCollection();
    }

    public ManagedConnection getById(String str) {
        if (str != null) {
            synchronized (this.__lookupLock) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this.__lookup, str, holder);
                ManagedConnection managedConnection = (ManagedConnection) holder.getValue();
                if (tryGetValue) {
                    return managedConnection;
                }
            }
        }
        return null;
    }

    public ManagedConnection getByRemoteId(String str) {
        if (str != null) {
            synchronized (this.__lookupLock) {
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this.__remoteLookup, str, holder);
                ManagedConnection managedConnection = (ManagedConnection) holder.getValue();
                if (tryGetValue) {
                    return managedConnection;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void removeSuccess(ManagedConnection managedConnection) {
        super.removeSuccess(managedConnection);
        synchronized (this.__lookupLock) {
            HashMapExtensions.remove(this.__lookup, managedConnection.getId());
            if (managedConnection.getRemoteConnectionId() != null) {
                HashMapExtensions.remove(this.__remoteLookup, managedConnection.getRemoteConnectionId());
            }
        }
    }

    public boolean tryGetById(String str, Holder<ManagedConnection> holder) {
        boolean tryGetValue;
        if (str == null) {
            holder.setValue(null);
            return false;
        }
        synchronized (this.__lookupLock) {
            tryGetValue = HashMapExtensions.tryGetValue(this.__lookup, str, holder);
        }
        return tryGetValue;
    }

    public boolean tryGetByRemoteId(String str, Holder<ManagedConnection> holder) {
        boolean tryGetValue;
        if (str == null) {
            holder.setValue(null);
            return false;
        }
        synchronized (this.__lookupLock) {
            tryGetValue = HashMapExtensions.tryGetValue(this.__remoteLookup, str, holder);
        }
        return tryGetValue;
    }
}
