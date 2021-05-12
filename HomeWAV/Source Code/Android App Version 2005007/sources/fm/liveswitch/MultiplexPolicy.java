package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MultiplexPolicy {
    Negotiated(1),
    Required(2);
    
    private static final Map<Integer, MultiplexPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MultiplexPolicy.class).iterator();
        while (it.hasNext()) {
            MultiplexPolicy multiplexPolicy = (MultiplexPolicy) it.next();
            lookup.put(Integer.valueOf(multiplexPolicy.getAssignedValue()), multiplexPolicy);
        }
    }

    private MultiplexPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MultiplexPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
