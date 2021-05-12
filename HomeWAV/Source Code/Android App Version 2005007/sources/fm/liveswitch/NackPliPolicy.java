package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum NackPliPolicy {
    Disabled(1),
    Negotiated(2);
    
    private static final Map<Integer, NackPliPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(NackPliPolicy.class).iterator();
        while (it.hasNext()) {
            NackPliPolicy nackPliPolicy = (NackPliPolicy) it.next();
            lookup.put(Integer.valueOf(nackPliPolicy.getAssignedValue()), nackPliPolicy);
        }
    }

    private NackPliPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static NackPliPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
