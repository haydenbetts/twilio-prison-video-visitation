package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum OperatingSystem {
    Unknown(1),
    Windows(2),
    Android(3),
    MacOS(4),
    IOS(5),
    Linux(6),
    TvOS(7),
    WatchOS(8);
    
    private static final Map<Integer, OperatingSystem> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(OperatingSystem.class).iterator();
        while (it.hasNext()) {
            OperatingSystem operatingSystem = (OperatingSystem) it.next();
            lookup.put(Integer.valueOf(operatingSystem.getAssignedValue()), operatingSystem);
        }
    }

    private OperatingSystem(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static OperatingSystem getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
