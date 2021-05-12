package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MediaPipeState {
    Initialized(1),
    Destroying(2),
    Destroyed(3);
    
    private static final Map<Integer, MediaPipeState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaPipeState.class).iterator();
        while (it.hasNext()) {
            MediaPipeState mediaPipeState = (MediaPipeState) it.next();
            lookup.put(Integer.valueOf(mediaPipeState.getAssignedValue()), mediaPipeState);
        }
    }

    private MediaPipeState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaPipeState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
