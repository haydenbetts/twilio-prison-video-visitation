package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum DtlsProtocolVersion {
    Dtls10(1),
    Dtls12(2);
    
    private static final Map<Integer, DtlsProtocolVersion> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DtlsProtocolVersion.class).iterator();
        while (it.hasNext()) {
            DtlsProtocolVersion dtlsProtocolVersion = (DtlsProtocolVersion) it.next();
            lookup.put(Integer.valueOf(dtlsProtocolVersion.getAssignedValue()), dtlsProtocolVersion);
        }
    }

    private DtlsProtocolVersion(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DtlsProtocolVersion getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
