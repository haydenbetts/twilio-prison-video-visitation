package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum IceTransportState {
    New(1),
    Checking(2),
    Connected(3),
    Disconnected(4),
    Closed(5),
    Failed(6);
    
    private static final Map<Integer, IceTransportState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceTransportState.class).iterator();
        while (it.hasNext()) {
            IceTransportState iceTransportState = (IceTransportState) it.next();
            lookup.put(Integer.valueOf(iceTransportState.getAssignedValue()), iceTransportState);
        }
    }

    private IceTransportState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceTransportState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
