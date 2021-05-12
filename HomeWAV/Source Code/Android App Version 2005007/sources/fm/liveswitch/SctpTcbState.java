package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum SctpTcbState {
    CookieWait(2),
    CookieEchoed(3),
    Established(4),
    ClosedNeverOpened(9),
    Closed(10),
    Failed(11),
    Closing(12),
    Failing(13);
    
    private static final Map<Integer, SctpTcbState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SctpTcbState.class).iterator();
        while (it.hasNext()) {
            SctpTcbState sctpTcbState = (SctpTcbState) it.next();
            lookup.put(Integer.valueOf(sctpTcbState.getAssignedValue()), sctpTcbState);
        }
    }

    private SctpTcbState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SctpTcbState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
