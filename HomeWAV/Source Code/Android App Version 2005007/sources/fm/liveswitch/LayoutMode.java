package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LayoutMode {
    FloatLocal(1),
    FloatRemote(2),
    Block(3),
    Inline(4),
    InlineOverflow(5);
    
    private static final Map<Integer, LayoutMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LayoutMode.class).iterator();
        while (it.hasNext()) {
            LayoutMode layoutMode = (LayoutMode) it.next();
            lookup.put(Integer.valueOf(layoutMode.getAssignedValue()), layoutMode);
        }
    }

    private LayoutMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LayoutMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
