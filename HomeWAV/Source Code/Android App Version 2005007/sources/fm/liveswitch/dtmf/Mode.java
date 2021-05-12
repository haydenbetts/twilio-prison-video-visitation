package fm.liveswitch.dtmf;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Mode {
    Augment(1),
    Replace(2);
    
    private static final Map<Integer, Mode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(Mode.class).iterator();
        while (it.hasNext()) {
            Mode mode = (Mode) it.next();
            lookup.put(Integer.valueOf(mode.getAssignedValue()), mode);
        }
    }

    private Mode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static Mode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
