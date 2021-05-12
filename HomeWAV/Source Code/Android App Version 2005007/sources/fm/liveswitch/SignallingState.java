package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SignallingState {
    New(1),
    HaveLocalOffer(2),
    HaveRemoteOffer(3),
    Stable(4);
    
    private static final Map<Integer, SignallingState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingState.class).iterator();
        while (it.hasNext()) {
            SignallingState signallingState = (SignallingState) it.next();
            lookup.put(Integer.valueOf(signallingState.getAssignedValue()), signallingState);
        }
    }

    private SignallingState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
