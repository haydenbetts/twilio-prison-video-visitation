package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum NetworkType {
    Unknown(1),
    Wired(2),
    Wireless(3),
    Cellular(4),
    Vpn(5);
    
    private static final Map<Integer, NetworkType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(NetworkType.class).iterator();
        while (it.hasNext()) {
            NetworkType networkType = (NetworkType) it.next();
            lookup.put(Integer.valueOf(networkType.getAssignedValue()), networkType);
        }
    }

    private NetworkType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static NetworkType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
