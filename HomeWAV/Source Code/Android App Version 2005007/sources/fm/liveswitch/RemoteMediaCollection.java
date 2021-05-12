package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoteMediaCollection extends Collection<RemoteMedia, RemoteMediaCollection> {
    private HashMap<String, RemoteMedia> __lookup = new HashMap<>();
    private Object __lookupLock = new Object();

    /* access modifiers changed from: protected */
    public void addSuccess(RemoteMedia remoteMedia) {
        super.addSuccess(remoteMedia);
        synchronized (this.__lookupLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__lookup), remoteMedia.getId(), remoteMedia);
        }
    }

    /* access modifiers changed from: protected */
    public RemoteMedia[] arrayFromList(ArrayList<RemoteMedia> arrayList) {
        return (RemoteMedia[]) arrayList.toArray(new RemoteMedia[0]);
    }

    /* access modifiers changed from: protected */
    public RemoteMediaCollection createCollection() {
        return new RemoteMediaCollection();
    }

    public RemoteMedia getById(String str) {
        synchronized (this.__lookupLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__lookup, str, holder);
            RemoteMedia remoteMedia = (RemoteMedia) holder.getValue();
            if (tryGetValue) {
                return remoteMedia;
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void removeSuccess(RemoteMedia remoteMedia) {
        super.removeSuccess(remoteMedia);
        synchronized (this.__lookupLock) {
            HashMapExtensions.remove(this.__lookup, remoteMedia.getId());
        }
    }
}
