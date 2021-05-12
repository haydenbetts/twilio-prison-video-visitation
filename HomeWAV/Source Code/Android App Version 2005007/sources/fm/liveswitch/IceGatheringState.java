package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum IceGatheringState {
    New(1),
    Gathering(2),
    Complete(3),
    Closing(4),
    Closed(5),
    Failed(6);
    
    private static final Map<Integer, IceGatheringState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceGatheringState.class).iterator();
        while (it.hasNext()) {
            IceGatheringState iceGatheringState = (IceGatheringState) it.next();
            lookup.put(Integer.valueOf(iceGatheringState.getAssignedValue()), iceGatheringState);
        }
    }

    private IceGatheringState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceGatheringState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
