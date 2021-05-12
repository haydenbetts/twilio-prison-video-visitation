package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum ReliableTransportState {
    New(1),
    Opening(2),
    Connected(3),
    Closing(4),
    Closed(5),
    Failed(6);
    
    private static final Map<Integer, ReliableTransportState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ReliableTransportState.class).iterator();
        while (it.hasNext()) {
            ReliableTransportState reliableTransportState = (ReliableTransportState) it.next();
            lookup.put(Integer.valueOf(reliableTransportState.getAssignedValue()), reliableTransportState);
        }
    }

    private ReliableTransportState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ReliableTransportState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
