package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MediaSinkState {
    Initialized(1),
    Destroying(2),
    Destroyed(3);
    
    private static final Map<Integer, MediaSinkState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaSinkState.class).iterator();
        while (it.hasNext()) {
            MediaSinkState mediaSinkState = (MediaSinkState) it.next();
            lookup.put(Integer.valueOf(mediaSinkState.getAssignedValue()), mediaSinkState);
        }
    }

    private MediaSinkState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaSinkState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
