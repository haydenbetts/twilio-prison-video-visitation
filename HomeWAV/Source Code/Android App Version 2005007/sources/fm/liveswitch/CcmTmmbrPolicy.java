package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CcmTmmbrPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, CcmTmmbrPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CcmTmmbrPolicy.class).iterator();
        while (it.hasNext()) {
            CcmTmmbrPolicy ccmTmmbrPolicy = (CcmTmmbrPolicy) it.next();
            lookup.put(Integer.valueOf(ccmTmmbrPolicy.getAssignedValue()), ccmTmmbrPolicy);
        }
    }

    private CcmTmmbrPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CcmTmmbrPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
