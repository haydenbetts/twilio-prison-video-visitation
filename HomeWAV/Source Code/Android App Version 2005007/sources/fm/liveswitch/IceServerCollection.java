package fm.liveswitch;

import java.util.ArrayList;

public class IceServerCollection extends Collection<IceServer, IceServerCollection> {
    /* access modifiers changed from: protected */
    public IceServer[] arrayFromList(ArrayList<IceServer> arrayList) {
        return (IceServer[]) arrayList.toArray(new IceServer[0]);
    }

    /* access modifiers changed from: protected */
    public IceServerCollection createCollection() {
        return new IceServerCollection();
    }
}
