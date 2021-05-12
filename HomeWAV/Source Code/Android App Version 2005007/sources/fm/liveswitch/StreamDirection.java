package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum StreamDirection {
    SendReceive(1),
    SendOnly(2),
    ReceiveOnly(3),
    Inactive(4),
    Unset(5);
    
    private static final Map<Integer, StreamDirection> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(StreamDirection.class).iterator();
        while (it.hasNext()) {
            StreamDirection streamDirection = (StreamDirection) it.next();
            lookup.put(Integer.valueOf(streamDirection.getAssignedValue()), streamDirection);
        }
    }

    private StreamDirection(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static StreamDirection getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
