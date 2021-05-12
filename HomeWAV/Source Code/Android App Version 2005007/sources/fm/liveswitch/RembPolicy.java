package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum RembPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, RembPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(RembPolicy.class).iterator();
        while (it.hasNext()) {
            RembPolicy rembPolicy = (RembPolicy) it.next();
            lookup.put(Integer.valueOf(rembPolicy.getAssignedValue()), rembPolicy);
        }
    }

    private RembPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static RembPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
