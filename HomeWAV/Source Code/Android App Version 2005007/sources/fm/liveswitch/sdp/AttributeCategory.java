package fm.liveswitch.sdp;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AttributeCategory {
    Normal(1),
    Caution(2),
    Identical(3),
    Sum(4),
    Transport(5),
    Inherit(6),
    IdenticalPerPT(7),
    Special(8);
    
    private static final Map<Integer, AttributeCategory> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(AttributeCategory.class).iterator();
        while (it.hasNext()) {
            AttributeCategory attributeCategory = (AttributeCategory) it.next();
            lookup.put(Integer.valueOf(attributeCategory.getAssignedValue()), attributeCategory);
        }
    }

    private AttributeCategory(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static AttributeCategory getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
