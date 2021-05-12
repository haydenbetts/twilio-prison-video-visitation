package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum VideoType {
    Unknown(1),
    Camera(2),
    Screen(3);
    
    private static final Map<Integer, VideoType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(VideoType.class).iterator();
        while (it.hasNext()) {
            VideoType videoType = (VideoType) it.next();
            lookup.put(Integer.valueOf(videoType.getAssignedValue()), videoType);
        }
    }

    private VideoType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static VideoType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
