package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum FecProtectionMode {
    NoOverlap(1),
    Overlap(2),
    BiasFirstPacket(3);
    
    private static final Map<Integer, FecProtectionMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(FecProtectionMode.class).iterator();
        while (it.hasNext()) {
            FecProtectionMode fecProtectionMode = (FecProtectionMode) it.next();
            lookup.put(Integer.valueOf(fecProtectionMode.getAssignedValue()), fecProtectionMode);
        }
    }

    private FecProtectionMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static FecProtectionMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
