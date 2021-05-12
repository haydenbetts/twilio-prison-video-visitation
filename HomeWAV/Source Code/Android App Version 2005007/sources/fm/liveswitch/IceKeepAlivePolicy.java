package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum IceKeepAlivePolicy {
    All(1),
    ActiveOnly(2);
    
    private static final Map<Integer, IceKeepAlivePolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceKeepAlivePolicy.class).iterator();
        while (it.hasNext()) {
            IceKeepAlivePolicy iceKeepAlivePolicy = (IceKeepAlivePolicy) it.next();
            lookup.put(Integer.valueOf(iceKeepAlivePolicy.getAssignedValue()), iceKeepAlivePolicy);
        }
    }

    private IceKeepAlivePolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceKeepAlivePolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
