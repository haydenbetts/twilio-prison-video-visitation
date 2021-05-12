package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum IceConnectionState {
    New(1),
    Checking(2),
    Connected(3),
    Completed(4),
    Failed(5),
    Disconnected(6),
    Closed(7);
    
    private static final Map<Integer, IceConnectionState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceConnectionState.class).iterator();
        while (it.hasNext()) {
            IceConnectionState iceConnectionState = (IceConnectionState) it.next();
            lookup.put(Integer.valueOf(iceConnectionState.getAssignedValue()), iceConnectionState);
        }
    }

    private IceConnectionState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceConnectionState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
