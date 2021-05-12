package fm.liveswitch.sdp.ice;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum OptionTagType {
    Unknown(1),
    Trickle(2);
    
    private static final Map<Integer, OptionTagType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(OptionTagType.class).iterator();
        while (it.hasNext()) {
            OptionTagType optionTagType = (OptionTagType) it.next();
            lookup.put(Integer.valueOf(optionTagType.getAssignedValue()), optionTagType);
        }
    }

    private OptionTagType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static OptionTagType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
