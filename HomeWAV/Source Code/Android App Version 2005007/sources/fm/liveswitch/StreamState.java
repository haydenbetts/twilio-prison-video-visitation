package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum StreamState {
    New(1),
    Initializing(2),
    Connecting(3),
    Connected(4),
    Failing(5),
    Failed(6),
    Closing(7),
    Closed(8);
    
    private static final Map<Integer, StreamState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(StreamState.class).iterator();
        while (it.hasNext()) {
            StreamState streamState = (StreamState) it.next();
            lookup.put(Integer.valueOf(streamState.getAssignedValue()), streamState);
        }
    }

    private StreamState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static StreamState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
