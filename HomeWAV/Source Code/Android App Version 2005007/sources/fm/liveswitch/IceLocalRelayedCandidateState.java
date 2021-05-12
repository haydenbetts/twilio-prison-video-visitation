package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum IceLocalRelayedCandidateState {
    Allocated(1),
    Refreshing(2),
    Closing(3),
    Closed(4),
    Failed(5);
    
    private static final Map<Integer, IceLocalRelayedCandidateState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceLocalRelayedCandidateState.class).iterator();
        while (it.hasNext()) {
            IceLocalRelayedCandidateState iceLocalRelayedCandidateState = (IceLocalRelayedCandidateState) it.next();
            lookup.put(Integer.valueOf(iceLocalRelayedCandidateState.getAssignedValue()), iceLocalRelayedCandidateState);
        }
    }

    private IceLocalRelayedCandidateState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceLocalRelayedCandidateState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
