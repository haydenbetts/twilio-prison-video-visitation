package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CcmFirPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, CcmFirPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CcmFirPolicy.class).iterator();
        while (it.hasNext()) {
            CcmFirPolicy ccmFirPolicy = (CcmFirPolicy) it.next();
            lookup.put(Integer.valueOf(ccmFirPolicy.getAssignedValue()), ccmFirPolicy);
        }
    }

    private CcmFirPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CcmFirPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
