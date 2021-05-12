package fm.liveswitch.vpx;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Codec {
    Vp8(1),
    Vp9(2);
    
    private static final Map<Integer, Codec> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(Codec.class).iterator();
        while (it.hasNext()) {
            Codec codec = (Codec) it.next();
            lookup.put(Integer.valueOf(codec.getAssignedValue()), codec);
        }
    }

    private Codec(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static Codec getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
