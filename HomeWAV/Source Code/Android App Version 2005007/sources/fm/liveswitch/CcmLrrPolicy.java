package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CcmLrrPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, CcmLrrPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CcmLrrPolicy.class).iterator();
        while (it.hasNext()) {
            CcmLrrPolicy ccmLrrPolicy = (CcmLrrPolicy) it.next();
            lookup.put(Integer.valueOf(ccmLrrPolicy.getAssignedValue()), ccmLrrPolicy);
        }
    }

    private CcmLrrPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CcmLrrPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
