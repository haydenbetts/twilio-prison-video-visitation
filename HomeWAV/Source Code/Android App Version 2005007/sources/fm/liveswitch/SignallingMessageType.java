package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingMessageType {
    Connect(1),
    Disconnect(2),
    Bind(3),
    Unbind(4),
    Subscribe(5),
    Unsubscribe(6),
    Publish(7),
    Service(9),
    Stream(10),
    Unknown(11);
    
    private static final Map<Integer, SignallingMessageType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingMessageType.class).iterator();
        while (it.hasNext()) {
            SignallingMessageType signallingMessageType = (SignallingMessageType) it.next();
            lookup.put(Integer.valueOf(signallingMessageType.getAssignedValue()), signallingMessageType);
        }
    }

    private SignallingMessageType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingMessageType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
