package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum VirtualPacketType {
    Data(1),
    DataAck(2),
    Connect(3),
    ConnectAck(4);
    
    private static final Map<Integer, VirtualPacketType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(VirtualPacketType.class).iterator();
        while (it.hasNext()) {
            VirtualPacketType virtualPacketType = (VirtualPacketType) it.next();
            lookup.put(Integer.valueOf(virtualPacketType.getAssignedValue()), virtualPacketType);
        }
    }

    private VirtualPacketType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static VirtualPacketType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
