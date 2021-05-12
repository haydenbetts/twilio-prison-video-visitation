package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum DtlsRole {
    Auto(1),
    Client(2),
    Server(3);
    
    private static final Map<Integer, DtlsRole> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DtlsRole.class).iterator();
        while (it.hasNext()) {
            DtlsRole dtlsRole = (DtlsRole) it.next();
            lookup.put(Integer.valueOf(dtlsRole.getAssignedValue()), dtlsRole);
        }
    }

    private DtlsRole(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DtlsRole getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
