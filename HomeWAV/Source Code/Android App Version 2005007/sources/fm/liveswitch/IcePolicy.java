package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum IcePolicy {
    Required(1),
    Disabled(2),
    Negotiated(3);
    
    private static final Map<Integer, IcePolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IcePolicy.class).iterator();
        while (it.hasNext()) {
            IcePolicy icePolicy = (IcePolicy) it.next();
            lookup.put(Integer.valueOf(icePolicy.getAssignedValue()), icePolicy);
        }
    }

    private IcePolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IcePolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
