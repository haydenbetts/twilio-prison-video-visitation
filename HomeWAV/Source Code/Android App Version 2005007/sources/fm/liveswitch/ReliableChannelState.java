package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum ReliableChannelState {
    New(1),
    Opening(2),
    Open(3),
    Closing(4),
    Closed(5),
    Failed(6);
    
    private static final Map<Integer, ReliableChannelState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ReliableChannelState.class).iterator();
        while (it.hasNext()) {
            ReliableChannelState reliableChannelState = (ReliableChannelState) it.next();
            lookup.put(Integer.valueOf(reliableChannelState.getAssignedValue()), reliableChannelState);
        }
    }

    private ReliableChannelState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ReliableChannelState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
