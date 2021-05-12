package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum StreamType {
    Audio(1),
    Video(2),
    Application(3),
    Message(4),
    Text(5);
    
    private static final Map<Integer, StreamType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(StreamType.class).iterator();
        while (it.hasNext()) {
            StreamType streamType = (StreamType) it.next();
            lookup.put(Integer.valueOf(streamType.getAssignedValue()), streamType);
        }
    }

    private StreamType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static StreamType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
