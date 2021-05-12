package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum PeerRole {
    Offerer(1),
    Answerer(2);
    
    private static final Map<Integer, PeerRole> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(PeerRole.class).iterator();
        while (it.hasNext()) {
            PeerRole peerRole = (PeerRole) it.next();
            lookup.put(Integer.valueOf(peerRole.getAssignedValue()), peerRole);
        }
    }

    private PeerRole(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static PeerRole getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
