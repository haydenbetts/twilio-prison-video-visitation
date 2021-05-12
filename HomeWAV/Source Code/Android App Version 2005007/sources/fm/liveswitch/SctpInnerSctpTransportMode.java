package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SctpInnerSctpTransportMode {
    DeliverAllPackets(1),
    FilterOutINITChunks(2);
    
    private static final Map<Integer, SctpInnerSctpTransportMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SctpInnerSctpTransportMode.class).iterator();
        while (it.hasNext()) {
            SctpInnerSctpTransportMode sctpInnerSctpTransportMode = (SctpInnerSctpTransportMode) it.next();
            lookup.put(Integer.valueOf(sctpInnerSctpTransportMode.getAssignedValue()), sctpInnerSctpTransportMode);
        }
    }

    private SctpInnerSctpTransportMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SctpInnerSctpTransportMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
