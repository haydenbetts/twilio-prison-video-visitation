package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AddressType {
    IPv4(1),
    IPv6(2),
    Unknown(3);
    
    private static final Map<Integer, AddressType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(AddressType.class).iterator();
        while (it.hasNext()) {
            AddressType addressType = (AddressType) it.next();
            lookup.put(Integer.valueOf(addressType.getAssignedValue()), addressType);
        }
    }

    private AddressType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static AddressType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
