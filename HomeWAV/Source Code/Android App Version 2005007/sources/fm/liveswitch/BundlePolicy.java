package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum BundlePolicy {
    Balanced(1),
    MaxCompatibility(2),
    MaxBundle(3),
    Disabled(4);
    
    private static final Map<Integer, BundlePolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(BundlePolicy.class).iterator();
        while (it.hasNext()) {
            BundlePolicy bundlePolicy = (BundlePolicy) it.next();
            lookup.put(Integer.valueOf(bundlePolicy.getAssignedValue()), bundlePolicy);
        }
    }

    private BundlePolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static BundlePolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
