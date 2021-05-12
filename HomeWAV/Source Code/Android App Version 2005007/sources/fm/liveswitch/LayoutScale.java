package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LayoutScale {
    Contain(1),
    Cover(2),
    Stretch(3);
    
    private static final Map<Integer, LayoutScale> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LayoutScale.class).iterator();
        while (it.hasNext()) {
            LayoutScale layoutScale = (LayoutScale) it.next();
            lookup.put(Integer.valueOf(layoutScale.getAssignedValue()), layoutScale);
        }
    }

    private LayoutScale(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LayoutScale getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
