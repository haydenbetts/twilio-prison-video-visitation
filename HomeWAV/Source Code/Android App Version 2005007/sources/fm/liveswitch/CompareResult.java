package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CompareResult {
    Equal(1),
    Negative(2),
    Positive(3);
    
    private static final Map<Integer, CompareResult> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CompareResult.class).iterator();
        while (it.hasNext()) {
            CompareResult compareResult = (CompareResult) it.next();
            lookup.put(Integer.valueOf(compareResult.getAssignedValue()), compareResult);
        }
    }

    private CompareResult(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CompareResult getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
