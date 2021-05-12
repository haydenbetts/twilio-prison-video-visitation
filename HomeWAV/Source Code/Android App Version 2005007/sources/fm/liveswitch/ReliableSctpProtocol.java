package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum ReliableSctpProtocol {
    RtcDataChannelSctpProtocol(1);
    
    private static final Map<Integer, ReliableSctpProtocol> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ReliableSctpProtocol.class).iterator();
        while (it.hasNext()) {
            ReliableSctpProtocol reliableSctpProtocol = (ReliableSctpProtocol) it.next();
            lookup.put(Integer.valueOf(reliableSctpProtocol.getAssignedValue()), reliableSctpProtocol);
        }
    }

    private ReliableSctpProtocol(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ReliableSctpProtocol getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
