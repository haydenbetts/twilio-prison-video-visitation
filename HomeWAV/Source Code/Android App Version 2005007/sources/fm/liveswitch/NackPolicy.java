package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum NackPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, NackPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(NackPolicy.class).iterator();
        while (it.hasNext()) {
            NackPolicy nackPolicy = (NackPolicy) it.next();
            lookup.put(Integer.valueOf(nackPolicy.getAssignedValue()), nackPolicy);
        }
    }

    private NackPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static NackPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
