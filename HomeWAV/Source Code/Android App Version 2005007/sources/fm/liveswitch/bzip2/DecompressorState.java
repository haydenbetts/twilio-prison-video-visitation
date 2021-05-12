package fm.liveswitch.bzip2;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum DecompressorState {
    EOF(1),
    START_BLOCK(2),
    RAND_PART_A(3),
    RAND_PART_B(4),
    RAND_PART_C(5),
    NO_RAND_PART_A(6),
    NO_RAND_PART_B(7),
    NO_RAND_PART_C(8);
    
    private static final Map<Integer, DecompressorState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DecompressorState.class).iterator();
        while (it.hasNext()) {
            DecompressorState decompressorState = (DecompressorState) it.next();
            lookup.put(Integer.valueOf(decompressorState.getAssignedValue()), decompressorState);
        }
    }

    private DecompressorState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DecompressorState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
