package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LocalMediaState {
    New(1),
    Starting(2),
    Started(3),
    Stopping(4),
    Stopped(5),
    Destroying(6),
    Destroyed(7);
    
    private static final Map<Integer, LocalMediaState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LocalMediaState.class).iterator();
        while (it.hasNext()) {
            LocalMediaState localMediaState = (LocalMediaState) it.next();
            lookup.put(Integer.valueOf(localMediaState.getAssignedValue()), localMediaState);
        }
    }

    private LocalMediaState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LocalMediaState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
