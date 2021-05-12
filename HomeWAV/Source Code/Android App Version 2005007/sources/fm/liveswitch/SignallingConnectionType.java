package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingConnectionType {
    WebSocket(1),
    LongPolling(2),
    CallbackPolling(3),
    IFrame(4),
    Flash(5),
    NotSet(99);
    
    private static final Map<Integer, SignallingConnectionType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingConnectionType.class).iterator();
        while (it.hasNext()) {
            SignallingConnectionType signallingConnectionType = (SignallingConnectionType) it.next();
            lookup.put(Integer.valueOf(signallingConnectionType.getAssignedValue()), signallingConnectionType);
        }
    }

    private SignallingConnectionType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingConnectionType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
