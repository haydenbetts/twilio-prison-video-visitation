package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum IceRole {
    Controlling(1),
    Controlled(2);
    
    private static final Map<Integer, IceRole> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceRole.class).iterator();
        while (it.hasNext()) {
            IceRole iceRole = (IceRole) it.next();
            lookup.put(Integer.valueOf(iceRole.getAssignedValue()), iceRole);
        }
    }

    private IceRole(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceRole getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
