package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CandidatePairState {
    New(1),
    Waiting(2),
    InProgress(3),
    Succeeded(4),
    Failed(5),
    Closed(6),
    ConnectivityLost(7);
    
    private static final Map<Integer, CandidatePairState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CandidatePairState.class).iterator();
        while (it.hasNext()) {
            CandidatePairState candidatePairState = (CandidatePairState) it.next();
            lookup.put(Integer.valueOf(candidatePairState.getAssignedValue()), candidatePairState);
        }
    }

    private CandidatePairState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CandidatePairState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
