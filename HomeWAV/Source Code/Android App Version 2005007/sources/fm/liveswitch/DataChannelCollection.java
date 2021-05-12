package fm.liveswitch;

import java.util.ArrayList;

public class DataChannelCollection extends Collection<DataChannel, DataChannelCollection> {
    /* access modifiers changed from: protected */
    public DataChannel[] arrayFromList(ArrayList<DataChannel> arrayList) {
        return (DataChannel[]) arrayList.toArray(new DataChannel[0]);
    }

    /* access modifiers changed from: protected */
    public DataChannelCollection createCollection() {
        return new DataChannelCollection();
    }
}
