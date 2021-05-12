package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum ReliableChannelType {
    Reliable(1),
    ReliableUnordered(2),
    PartialReliableREXMIT(3),
    PartialReliableREXMITUnordered(4),
    PartialReliableTimed(5),
    PartialReliableTimedUnordered(6);
    
    private static final Map<Integer, ReliableChannelType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ReliableChannelType.class).iterator();
        while (it.hasNext()) {
            ReliableChannelType reliableChannelType = (ReliableChannelType) it.next();
            lookup.put(Integer.valueOf(reliableChannelType.getAssignedValue()), reliableChannelType);
        }
    }

    private ReliableChannelType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ReliableChannelType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
