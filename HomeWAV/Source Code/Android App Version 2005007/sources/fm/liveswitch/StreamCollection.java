package fm.liveswitch;

import java.util.ArrayList;

public class StreamCollection extends Collection<Stream, StreamCollection> {
    /* access modifiers changed from: protected */
    public Stream[] arrayFromList(ArrayList<Stream> arrayList) {
        return (Stream[]) arrayList.toArray(new Stream[0]);
    }

    /* access modifiers changed from: protected */
    public StreamCollection createCollection() {
        return new StreamCollection();
    }

    public <T extends Stream> T getByType(StreamType streamType) {
        for (T t : (Stream[]) super.getValues()) {
            if (Global.equals(t.getType(), streamType)) {
                return t;
            }
        }
        return null;
    }

    public <T extends Stream> ArrayList<T> getManyByType(StreamType streamType) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (Stream stream : (Stream[]) super.getValues()) {
            if (Global.equals(stream.getType(), streamType)) {
                arrayList.add(stream);
            }
        }
        return arrayList;
    }
}
