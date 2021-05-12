package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum StatControlFrameType {
    FrameDecoded(1);
    
    private static final Map<Integer, StatControlFrameType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(StatControlFrameType.class).iterator();
        while (it.hasNext()) {
            StatControlFrameType statControlFrameType = (StatControlFrameType) it.next();
            lookup.put(Integer.valueOf(statControlFrameType.getAssignedValue()), statControlFrameType);
        }
    }

    private StatControlFrameType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static StatControlFrameType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
