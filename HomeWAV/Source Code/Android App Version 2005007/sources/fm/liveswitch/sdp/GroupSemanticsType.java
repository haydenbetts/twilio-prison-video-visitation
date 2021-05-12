package fm.liveswitch.sdp;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum GroupSemanticsType {
    LipSynchronization(1),
    FlowIdentification(2),
    Bundling(3),
    Unknown(4);
    
    private static final Map<Integer, GroupSemanticsType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(GroupSemanticsType.class).iterator();
        while (it.hasNext()) {
            GroupSemanticsType groupSemanticsType = (GroupSemanticsType) it.next();
            lookup.put(Integer.valueOf(groupSemanticsType.getAssignedValue()), groupSemanticsType);
        }
    }

    private GroupSemanticsType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static GroupSemanticsType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
