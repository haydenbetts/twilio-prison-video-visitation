package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum FailureSource {
    None(1),
    Network(2),
    Message(3);
    
    private static final Map<Integer, FailureSource> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(FailureSource.class).iterator();
        while (it.hasNext()) {
            FailureSource failureSource = (FailureSource) it.next();
            lookup.put(Integer.valueOf(failureSource.getAssignedValue()), failureSource);
        }
    }

    private FailureSource(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static FailureSource getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
