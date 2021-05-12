package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum TurnAuthOperation {
    Allocate(1),
    CreatePermission(2),
    Refresh(3),
    ChannelBind(4);
    
    private static final Map<Integer, TurnAuthOperation> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(TurnAuthOperation.class).iterator();
        while (it.hasNext()) {
            TurnAuthOperation turnAuthOperation = (TurnAuthOperation) it.next();
            lookup.put(Integer.valueOf(turnAuthOperation.getAssignedValue()), turnAuthOperation);
        }
    }

    private TurnAuthOperation(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static TurnAuthOperation getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
