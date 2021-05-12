package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum IceGatherPolicy {
    All(1),
    NoHost(2),
    Relay(3);
    
    private static final Map<Integer, IceGatherPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceGatherPolicy.class).iterator();
        while (it.hasNext()) {
            IceGatherPolicy iceGatherPolicy = (IceGatherPolicy) it.next();
            lookup.put(Integer.valueOf(iceGatherPolicy.getAssignedValue()), iceGatherPolicy);
        }
    }

    private IceGatherPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceGatherPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
