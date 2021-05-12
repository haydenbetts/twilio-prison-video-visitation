package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingPresenceType {
    Subscribe(1),
    Unsubscribe(2);
    
    private static final Map<Integer, SignallingPresenceType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingPresenceType.class).iterator();
        while (it.hasNext()) {
            SignallingPresenceType signallingPresenceType = (SignallingPresenceType) it.next();
            lookup.put(Integer.valueOf(signallingPresenceType.getAssignedValue()), signallingPresenceType);
        }
    }

    private SignallingPresenceType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingPresenceType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
