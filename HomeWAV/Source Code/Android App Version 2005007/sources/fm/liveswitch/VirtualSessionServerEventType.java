package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum VirtualSessionServerEventType {
    AddedClient(1),
    RemovedClient(2);
    
    private static final Map<Integer, VirtualSessionServerEventType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(VirtualSessionServerEventType.class).iterator();
        while (it.hasNext()) {
            VirtualSessionServerEventType virtualSessionServerEventType = (VirtualSessionServerEventType) it.next();
            lookup.put(Integer.valueOf(virtualSessionServerEventType.getAssignedValue()), virtualSessionServerEventType);
        }
    }

    private VirtualSessionServerEventType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static VirtualSessionServerEventType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
