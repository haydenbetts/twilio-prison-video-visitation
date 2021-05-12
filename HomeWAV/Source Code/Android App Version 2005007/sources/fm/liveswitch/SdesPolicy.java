package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SdesPolicy {
    Negotiated(2),
    Disabled(3);
    
    private static final Map<Integer, SdesPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SdesPolicy.class).iterator();
        while (it.hasNext()) {
            SdesPolicy sdesPolicy = (SdesPolicy) it.next();
            lookup.put(Integer.valueOf(sdesPolicy.getAssignedValue()), sdesPolicy);
        }
    }

    private SdesPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SdesPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
