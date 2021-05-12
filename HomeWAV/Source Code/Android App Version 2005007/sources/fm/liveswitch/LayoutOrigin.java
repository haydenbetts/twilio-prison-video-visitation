package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LayoutOrigin {
    TopLeft(1),
    TopRight(2),
    BottomRight(3),
    BottomLeft(4);
    
    private static final Map<Integer, LayoutOrigin> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LayoutOrigin.class).iterator();
        while (it.hasNext()) {
            LayoutOrigin layoutOrigin = (LayoutOrigin) it.next();
            lookup.put(Integer.valueOf(layoutOrigin.getAssignedValue()), layoutOrigin);
        }
    }

    private LayoutOrigin(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LayoutOrigin getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
