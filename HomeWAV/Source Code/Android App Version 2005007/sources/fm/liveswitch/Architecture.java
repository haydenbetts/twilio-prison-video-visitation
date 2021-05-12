package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Architecture {
    Unknown(1),
    X86(2),
    X64(3),
    Armv7(4),
    Armv8(5),
    Arm64(6),
    Mips(7),
    Mips64(8);
    
    private static final Map<Integer, Architecture> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(Architecture.class).iterator();
        while (it.hasNext()) {
            Architecture architecture = (Architecture) it.next();
            lookup.put(Integer.valueOf(architecture.getAssignedValue()), architecture);
        }
    }

    private Architecture(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static Architecture getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
