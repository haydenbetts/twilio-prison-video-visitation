package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SctpTransportState {
    New(1),
    Connecting(2),
    Connected(3),
    Closing(4),
    Closed(5),
    Failed(6),
    Failing(7);
    
    private static final Map<Integer, SctpTransportState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SctpTransportState.class).iterator();
        while (it.hasNext()) {
            SctpTransportState sctpTransportState = (SctpTransportState) it.next();
            lookup.put(Integer.valueOf(sctpTransportState.getAssignedValue()), sctpTransportState);
        }
    }

    private SctpTransportState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SctpTransportState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
