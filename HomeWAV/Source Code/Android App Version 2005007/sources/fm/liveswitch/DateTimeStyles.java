package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum DateTimeStyles {
    None(0),
    AllowLeadingWhite(1),
    AllowTrailingWhite(2),
    AllowInnerWhite(4),
    AllowWhiteSpaces(7),
    NoCurrentDateDefault(8),
    AdjustToUniversal(16),
    AssumeLocal(32),
    AssumeUniversal(64),
    RoundtripKind(128);
    
    private static final Map<Integer, DateTimeStyles> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DateTimeStyles.class).iterator();
        while (it.hasNext()) {
            DateTimeStyles dateTimeStyles = (DateTimeStyles) it.next();
            lookup.put(Integer.valueOf(dateTimeStyles.getAssignedValue()), dateTimeStyles);
        }
    }

    private DateTimeStyles(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DateTimeStyles getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
