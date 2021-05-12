package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CodecType {
    Encode(1),
    Decode(2);
    
    private static final Map<Integer, CodecType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CodecType.class).iterator();
        while (it.hasNext()) {
            CodecType codecType = (CodecType) it.next();
            lookup.put(Integer.valueOf(codecType.getAssignedValue()), codecType);
        }
    }

    private CodecType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CodecType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
