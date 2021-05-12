package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MacType {
    HmacMd5(1),
    HmacSha1(2),
    HmacSha256(3);
    
    private static final Map<Integer, MacType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MacType.class).iterator();
        while (it.hasNext()) {
            MacType macType = (MacType) it.next();
            lookup.put(Integer.valueOf(macType.getAssignedValue()), macType);
        }
    }

    private MacType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MacType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
