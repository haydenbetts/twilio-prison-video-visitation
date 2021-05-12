package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum TrickleIcePolicy {
    NotSupported(1),
    FullTrickle(2),
    HalfTrickle(3);
    
    private static final Map<Integer, TrickleIcePolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(TrickleIcePolicy.class).iterator();
        while (it.hasNext()) {
            TrickleIcePolicy trickleIcePolicy = (TrickleIcePolicy) it.next();
            lookup.put(Integer.valueOf(trickleIcePolicy.getAssignedValue()), trickleIcePolicy);
        }
    }

    private TrickleIcePolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static TrickleIcePolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
