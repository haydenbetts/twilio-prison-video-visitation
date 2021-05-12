package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum TransportType {
    Gatherer(1),
    IceTransport(2),
    DtlsTransport(3),
    SctpTransport(4),
    ReliableDataTransport(5),
    SrtpTransport(6),
    Unset(7),
    MediaTransport(8);
    
    private static final Map<Integer, TransportType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(TransportType.class).iterator();
        while (it.hasNext()) {
            TransportType transportType = (TransportType) it.next();
            lookup.put(Integer.valueOf(transportType.getAssignedValue()), transportType);
        }
    }

    private TransportType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static TransportType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
