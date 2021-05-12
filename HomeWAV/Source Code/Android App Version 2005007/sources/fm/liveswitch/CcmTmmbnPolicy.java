package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CcmTmmbnPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, CcmTmmbnPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CcmTmmbnPolicy.class).iterator();
        while (it.hasNext()) {
            CcmTmmbnPolicy ccmTmmbnPolicy = (CcmTmmbnPolicy) it.next();
            lookup.put(Integer.valueOf(ccmTmmbnPolicy.getAssignedValue()), ccmTmmbnPolicy);
        }
    }

    private CcmTmmbnPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CcmTmmbnPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
