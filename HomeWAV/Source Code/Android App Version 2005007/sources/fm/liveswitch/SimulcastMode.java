package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SimulcastMode {
    Disabled(1),
    SynchronizationSource(2),
    RtpStreamId(3);
    
    private static final Map<Integer, SimulcastMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SimulcastMode.class).iterator();
        while (it.hasNext()) {
            SimulcastMode simulcastMode = (SimulcastMode) it.next();
            lookup.put(Integer.valueOf(simulcastMode.getAssignedValue()), simulcastMode);
        }
    }

    private SimulcastMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SimulcastMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
