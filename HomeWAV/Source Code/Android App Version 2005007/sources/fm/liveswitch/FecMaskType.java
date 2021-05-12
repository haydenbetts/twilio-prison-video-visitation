package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum FecMaskType {
    Random(1),
    Bursty(2);
    
    private static final Map<Integer, FecMaskType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(FecMaskType.class).iterator();
        while (it.hasNext()) {
            FecMaskType fecMaskType = (FecMaskType) it.next();
            lookup.put(Integer.valueOf(fecMaskType.getAssignedValue()), fecMaskType);
        }
    }

    private FecMaskType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static FecMaskType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
