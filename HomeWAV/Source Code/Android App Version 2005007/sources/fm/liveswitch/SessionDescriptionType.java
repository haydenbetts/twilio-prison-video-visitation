package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SessionDescriptionType {
    Offer(1),
    Answer(2);
    
    private static final Map<Integer, SessionDescriptionType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SessionDescriptionType.class).iterator();
        while (it.hasNext()) {
            SessionDescriptionType sessionDescriptionType = (SessionDescriptionType) it.next();
            lookup.put(Integer.valueOf(sessionDescriptionType.getAssignedValue()), sessionDescriptionType);
        }
    }

    private SessionDescriptionType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SessionDescriptionType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
