package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum BandwidthAdaptationPolicy {
    Disabled(1),
    Enabled(2);
    
    private static final Map<Integer, BandwidthAdaptationPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(BandwidthAdaptationPolicy.class).iterator();
        while (it.hasNext()) {
            BandwidthAdaptationPolicy bandwidthAdaptationPolicy = (BandwidthAdaptationPolicy) it.next();
            lookup.put(Integer.valueOf(bandwidthAdaptationPolicy.getAssignedValue()), bandwidthAdaptationPolicy);
        }
    }

    private BandwidthAdaptationPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static BandwidthAdaptationPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
