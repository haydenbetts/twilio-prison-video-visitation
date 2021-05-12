package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SignallingConcurrencyMode {
    Low(1),
    High(2),
    Default(1);
    
    private static final Map<Integer, SignallingConcurrencyMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SignallingConcurrencyMode.class).iterator();
        while (it.hasNext()) {
            SignallingConcurrencyMode signallingConcurrencyMode = (SignallingConcurrencyMode) it.next();
            lookup.put(Integer.valueOf(signallingConcurrencyMode.getAssignedValue()), signallingConcurrencyMode);
        }
    }

    private SignallingConcurrencyMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SignallingConcurrencyMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
