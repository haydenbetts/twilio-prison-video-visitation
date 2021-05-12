package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum IceLocalCandidateState {
    Ready(1),
    Closed(2),
    Failed(3);
    
    private static final Map<Integer, IceLocalCandidateState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceLocalCandidateState.class).iterator();
        while (it.hasNext()) {
            IceLocalCandidateState iceLocalCandidateState = (IceLocalCandidateState) it.next();
            lookup.put(Integer.valueOf(iceLocalCandidateState.getAssignedValue()), iceLocalCandidateState);
        }
    }

    private IceLocalCandidateState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceLocalCandidateState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
