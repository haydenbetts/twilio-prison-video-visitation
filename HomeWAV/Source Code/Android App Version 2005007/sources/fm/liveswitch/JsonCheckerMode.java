package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum JsonCheckerMode {
    Array(1),
    Done(2),
    Key(3),
    Object(4),
    String(5);
    
    private static final Map<Integer, JsonCheckerMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(JsonCheckerMode.class).iterator();
        while (it.hasNext()) {
            JsonCheckerMode jsonCheckerMode = (JsonCheckerMode) it.next();
            lookup.put(Integer.valueOf(jsonCheckerMode.getAssignedValue()), jsonCheckerMode);
        }
    }

    private JsonCheckerMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static JsonCheckerMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
