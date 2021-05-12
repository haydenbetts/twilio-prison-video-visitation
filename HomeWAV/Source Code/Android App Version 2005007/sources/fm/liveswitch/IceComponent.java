package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum IceComponent {
    Rtp(1),
    Rtcp(2);
    
    private static final Map<Integer, IceComponent> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(IceComponent.class).iterator();
        while (it.hasNext()) {
            IceComponent iceComponent = (IceComponent) it.next();
            lookup.put(Integer.valueOf(iceComponent.getAssignedValue()), iceComponent);
        }
    }

    private IceComponent(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static IceComponent getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
