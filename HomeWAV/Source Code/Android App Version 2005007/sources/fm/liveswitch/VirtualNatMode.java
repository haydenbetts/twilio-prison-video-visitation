package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum VirtualNatMode {
    FullCone(1),
    AddressRestrictedCone(2),
    PortRestrictedCone(3),
    Symmetric(4);
    
    private static final Map<Integer, VirtualNatMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(VirtualNatMode.class).iterator();
        while (it.hasNext()) {
            VirtualNatMode virtualNatMode = (VirtualNatMode) it.next();
            lookup.put(Integer.valueOf(virtualNatMode.getAssignedValue()), virtualNatMode);
        }
    }

    private VirtualNatMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static VirtualNatMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
