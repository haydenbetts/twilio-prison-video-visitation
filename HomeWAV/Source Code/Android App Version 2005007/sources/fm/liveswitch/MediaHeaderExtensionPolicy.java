package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum MediaHeaderExtensionPolicy {
    Negotiated(1),
    Disabled(2);
    
    private static final Map<Integer, MediaHeaderExtensionPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaHeaderExtensionPolicy.class).iterator();
        while (it.hasNext()) {
            MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy = (MediaHeaderExtensionPolicy) it.next();
            lookup.put(Integer.valueOf(mediaHeaderExtensionPolicy.getAssignedValue()), mediaHeaderExtensionPolicy);
        }
    }

    private MediaHeaderExtensionPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaHeaderExtensionPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
