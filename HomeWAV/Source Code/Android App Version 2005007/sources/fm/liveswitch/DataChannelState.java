package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum DataChannelState {
    New(1),
    Connecting(2),
    Connected(3),
    Closing(4),
    Closed(5),
    Failed(6);
    
    private static final Map<Integer, DataChannelState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DataChannelState.class).iterator();
        while (it.hasNext()) {
            DataChannelState dataChannelState = (DataChannelState) it.next();
            lookup.put(Integer.valueOf(dataChannelState.getAssignedValue()), dataChannelState);
        }
    }

    private DataChannelState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DataChannelState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
