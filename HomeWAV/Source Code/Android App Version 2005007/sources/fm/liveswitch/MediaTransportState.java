package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum MediaTransportState {
    New(1),
    Started(2),
    Starting(3),
    Stopped(4),
    Stopping(5),
    Failed(6);
    
    private static final Map<Integer, MediaTransportState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaTransportState.class).iterator();
        while (it.hasNext()) {
            MediaTransportState mediaTransportState = (MediaTransportState) it.next();
            lookup.put(Integer.valueOf(mediaTransportState.getAssignedValue()), mediaTransportState);
        }
    }

    private MediaTransportState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaTransportState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
