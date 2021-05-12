package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ProcessFramePolicy {
    Synchronous(1),
    Asynchronous(2);
    
    private static final Map<Integer, ProcessFramePolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ProcessFramePolicy.class).iterator();
        while (it.hasNext()) {
            ProcessFramePolicy processFramePolicy = (ProcessFramePolicy) it.next();
            lookup.put(Integer.valueOf(processFramePolicy.getAssignedValue()), processFramePolicy);
        }
    }

    private ProcessFramePolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ProcessFramePolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
