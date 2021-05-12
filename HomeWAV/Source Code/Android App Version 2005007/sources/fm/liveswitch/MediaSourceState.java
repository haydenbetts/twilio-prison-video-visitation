package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MediaSourceState {
    New(1),
    Starting(2),
    Started(3),
    Stopping(4),
    Stopped(5),
    Destroying(6),
    Destroyed(7);
    
    private static final Map<Integer, MediaSourceState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaSourceState.class).iterator();
        while (it.hasNext()) {
            MediaSourceState mediaSourceState = (MediaSourceState) it.next();
            lookup.put(Integer.valueOf(mediaSourceState.getAssignedValue()), mediaSourceState);
        }
    }

    private MediaSourceState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaSourceState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
