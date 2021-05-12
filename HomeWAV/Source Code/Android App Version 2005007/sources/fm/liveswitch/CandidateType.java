package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CandidateType {
    Host(1),
    ServerReflexive(2),
    Relayed(3),
    PeerReflexive(4),
    Unknown(5);
    
    private static final Map<Integer, CandidateType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CandidateType.class).iterator();
        while (it.hasNext()) {
            CandidateType candidateType = (CandidateType) it.next();
            lookup.put(Integer.valueOf(candidateType.getAssignedValue()), candidateType);
        }
    }

    private CandidateType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CandidateType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
