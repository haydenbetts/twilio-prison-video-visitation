package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum RedFecPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, RedFecPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(RedFecPolicy.class).iterator();
        while (it.hasNext()) {
            RedFecPolicy redFecPolicy = (RedFecPolicy) it.next();
            lookup.put(Integer.valueOf(redFecPolicy.getAssignedValue()), redFecPolicy);
        }
    }

    private RedFecPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static RedFecPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
