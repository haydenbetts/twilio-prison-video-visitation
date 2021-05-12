package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum DtlsTransportState {
    New(1),
    Connecting(2),
    Connected(3),
    Closed(4),
    Failed(5);
    
    private static final Map<Integer, DtlsTransportState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DtlsTransportState.class).iterator();
        while (it.hasNext()) {
            DtlsTransportState dtlsTransportState = (DtlsTransportState) it.next();
            lookup.put(Integer.valueOf(dtlsTransportState.getAssignedValue()), dtlsTransportState);
        }
    }

    private DtlsTransportState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DtlsTransportState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
