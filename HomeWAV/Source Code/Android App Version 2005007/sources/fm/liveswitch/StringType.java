package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum StringType {
    None(1),
    Single(2),
    Double(3);
    
    private static final Map<Integer, StringType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(StringType.class).iterator();
        while (it.hasNext()) {
            StringType stringType = (StringType) it.next();
            lookup.put(Integer.valueOf(stringType.getAssignedValue()), stringType);
        }
    }

    private StringType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static StringType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
