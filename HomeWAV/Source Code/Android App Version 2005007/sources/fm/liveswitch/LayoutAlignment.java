package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LayoutAlignment {
    TopLeft(1),
    Top(2),
    TopRight(3),
    Left(4),
    Center(5),
    Right(6),
    BottomLeft(7),
    Bottom(8),
    BottomRight(9);
    
    private static final Map<Integer, LayoutAlignment> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LayoutAlignment.class).iterator();
        while (it.hasNext()) {
            LayoutAlignment layoutAlignment = (LayoutAlignment) it.next();
            lookup.put(Integer.valueOf(layoutAlignment.getAssignedValue()), layoutAlignment);
        }
    }

    private LayoutAlignment(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LayoutAlignment getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
