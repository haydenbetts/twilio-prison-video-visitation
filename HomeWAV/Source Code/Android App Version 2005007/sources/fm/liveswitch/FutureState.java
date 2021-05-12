package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum FutureState {
    Pending(1),
    Resolved(2),
    Rejected(3);
    
    private static final Map<Integer, FutureState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(FutureState.class).iterator();
        while (it.hasNext()) {
            FutureState futureState = (FutureState) it.next();
            lookup.put(Integer.valueOf(futureState.getAssignedValue()), futureState);
        }
    }

    private FutureState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static FutureState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
