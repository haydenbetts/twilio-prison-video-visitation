package fm.liveswitch.stun;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MessageType {
    Request(1),
    Indication(2),
    SuccessResponse(3),
    ErrorResponse(4);
    
    private static final Map<Integer, MessageType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MessageType.class).iterator();
        while (it.hasNext()) {
            MessageType messageType = (MessageType) it.next();
            lookup.put(Integer.valueOf(messageType.getAssignedValue()), messageType);
        }
    }

    private MessageType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MessageType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
