package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class ConnectionCollection extends Collection<Connection, ConnectionCollection> {
    private HashMap<String, Connection> __lookupByExternalId = new HashMap<>();
    private HashMap<String, Connection> __lookupByInternalId = new HashMap<>();
    private Object __lookupLock = new Object();

    /* access modifiers changed from: protected */
    public void addSuccess(Connection connection) {
        super.addSuccess(connection);
        synchronized (this.__lookupLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__lookupByInternalId), connection.getId(), connection);
            String externalId = connection.getExternalId();
            if (externalId != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(this.__lookupByExternalId), externalId, connection);
            }
            connection.removeOnExternalIdChange(new IActionDelegate2<String, String>() {
                public String getId() {
                    return "fm.liveswitch.ConnectionCollection.processExternalIdChange";
                }

                public void invoke(String str, String str2) {
                    ConnectionCollection.this.processExternalIdChange(str, str2);
                }
            });
            connection.addOnExternalIdChange(new IActionDelegate2<String, String>() {
                public String getId() {
                    return "fm.liveswitch.ConnectionCollection.processExternalIdChange";
                }

                public void invoke(String str, String str2) {
                    ConnectionCollection.this.processExternalIdChange(str, str2);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public Connection[] arrayFromList(ArrayList<Connection> arrayList) {
        return (Connection[]) arrayList.toArray(new Connection[0]);
    }

    /* access modifiers changed from: protected */
    public ConnectionCollection createCollection() {
        return new ConnectionCollection();
    }

    public Connection getByExternalId(String str) {
        synchronized (this.__lookupLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__lookupByExternalId, str, holder);
            Connection connection = (Connection) holder.getValue();
            if (tryGetValue) {
                return connection;
            }
            return null;
        }
    }

    public Connection getById(String str) {
        synchronized (this.__lookupLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__lookupByInternalId, str, holder);
            Connection connection = (Connection) holder.getValue();
            if (tryGetValue) {
                return connection;
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void processExternalIdChange(String str, String str2) {
        synchronized (this.__lookupLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__lookupByInternalId, str, holder);
            Connection connection = (Connection) holder.getValue();
            if (tryGetValue) {
                String externalId = connection.getExternalId();
                if (str2 != null) {
                    HashMapExtensions.remove(this.__lookupByExternalId, str2);
                }
                HashMapExtensions.set(HashMapExtensions.getItem(this.__lookupByExternalId), externalId, connection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeSuccess(Connection connection) {
        super.removeSuccess(connection);
        synchronized (this.__lookupLock) {
            HashMapExtensions.remove(this.__lookupByInternalId, connection.getId());
            String externalId = connection.getExternalId();
            if (externalId != null) {
                HashMapExtensions.remove(this.__lookupByExternalId, externalId);
            }
            connection.removeOnExternalIdChange(new IActionDelegate2<String, String>() {
                public String getId() {
                    return "fm.liveswitch.ConnectionCollection.processExternalIdChange";
                }

                public void invoke(String str, String str2) {
                    ConnectionCollection.this.processExternalIdChange(str, str2);
                }
            });
        }
    }

    public boolean tryGetByExternalId(String str, Holder<Connection> holder) {
        boolean tryGetValue;
        synchronized (this.__lookupLock) {
            tryGetValue = HashMapExtensions.tryGetValue(this.__lookupByExternalId, str, holder);
        }
        return tryGetValue;
    }

    public boolean tryGetById(String str, Holder<Connection> holder) {
        boolean tryGetValue;
        synchronized (this.__lookupLock) {
            tryGetValue = HashMapExtensions.tryGetValue(this.__lookupByInternalId, str, holder);
        }
        return tryGetValue;
    }
}
