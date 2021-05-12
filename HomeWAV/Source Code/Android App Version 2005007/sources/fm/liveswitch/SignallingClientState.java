package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingClientState {
    New(1),
    Connecting(2),
    Connected(3),
    Disconnecting(4),
    Disconnected(5);
    
    private static final Map<Integer, SignallingClientState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingClientState.class).iterator();
        while (it.hasNext()) {
            SignallingClientState signallingClientState = (SignallingClientState) it.next();
            lookup.put(Integer.valueOf(signallingClientState.getAssignedValue()), signallingClientState);
        }
    }

    private SignallingClientState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingClientState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
