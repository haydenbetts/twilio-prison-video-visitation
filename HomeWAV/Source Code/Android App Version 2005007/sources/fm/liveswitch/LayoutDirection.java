package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LayoutDirection {
    Horizontal(1),
    Vertical(2);
    
    private static final Map<Integer, LayoutDirection> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LayoutDirection.class).iterator();
        while (it.hasNext()) {
            LayoutDirection layoutDirection = (LayoutDirection) it.next();
            lookup.put(Integer.valueOf(layoutDirection.getAssignedValue()), layoutDirection);
        }
    }

    private LayoutDirection(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LayoutDirection getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
