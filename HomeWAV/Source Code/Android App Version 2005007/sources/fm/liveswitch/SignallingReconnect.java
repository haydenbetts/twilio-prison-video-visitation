package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingReconnect {
    Retry(1),
    Handshake(2),
    None(3),
    NotSet(99);
    
    private static final Map<Integer, SignallingReconnect> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingReconnect.class).iterator();
        while (it.hasNext()) {
            SignallingReconnect signallingReconnect = (SignallingReconnect) it.next();
            lookup.put(Integer.valueOf(signallingReconnect.getAssignedValue()), signallingReconnect);
        }
    }

    private SignallingReconnect(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingReconnect getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
