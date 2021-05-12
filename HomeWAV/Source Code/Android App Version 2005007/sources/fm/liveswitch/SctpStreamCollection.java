package fm.liveswitch;

import java.util.HashMap;

class SctpStreamCollection {
    private Object __lock = new Object();
    private HashMap<String, SctpStream> __streams;
    private int _count;

    public int getCount() {
        return this._count;
    }

    public SctpStream getStream(int i) {
        SctpStream sctpStream;
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(i));
        synchronized (this.__lock) {
            if (!this.__streams.containsKey(integerExtensions)) {
                HashMapExtensions.set(HashMapExtensions.getItem(this.__streams), integerExtensions, new SctpStream(i));
            }
            sctpStream = HashMapExtensions.getItem(this.__streams).get(integerExtensions);
        }
        return sctpStream;
    }

    public SctpStreamCollection(int i) {
        if (i > 0) {
            setCount(i);
            this.__streams = new HashMap<>();
            return;
        }
        throw new RuntimeException(new Exception("SCTP: number of streams must be positive."));
    }

    private void setCount(int i) {
        this._count = i;
    }
}
