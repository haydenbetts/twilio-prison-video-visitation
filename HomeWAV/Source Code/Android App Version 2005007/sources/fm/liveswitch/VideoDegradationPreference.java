package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum VideoDegradationPreference {
    Automatic(1),
    Balanced(2),
    FrameRate(3),
    Resolution(4);
    
    private static final Map<Integer, VideoDegradationPreference> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(VideoDegradationPreference.class).iterator();
        while (it.hasNext()) {
            VideoDegradationPreference videoDegradationPreference = (VideoDegradationPreference) it.next();
            lookup.put(Integer.valueOf(videoDegradationPreference.getAssignedValue()), videoDegradationPreference);
        }
    }

    private VideoDegradationPreference(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static VideoDegradationPreference getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
