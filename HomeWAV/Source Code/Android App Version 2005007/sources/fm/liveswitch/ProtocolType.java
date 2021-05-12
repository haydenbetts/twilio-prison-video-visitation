package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ProtocolType {
    Udp(1),
    Tcp(2),
    Tls(3),
    Unknown(4);
    
    private static final Map<Integer, ProtocolType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ProtocolType.class).iterator();
        while (it.hasNext()) {
            ProtocolType protocolType = (ProtocolType) it.next();
            lookup.put(Integer.valueOf(protocolType.getAssignedValue()), protocolType);
        }
    }

    private ProtocolType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ProtocolType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
