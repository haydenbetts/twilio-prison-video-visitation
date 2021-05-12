package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum WebSocketFrameType {
    Unknown(1),
    Continuation(2),
    Text(3),
    Binary(4),
    Close(5),
    Ping(6),
    Pong(7);
    
    private static final Map<Integer, WebSocketFrameType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(WebSocketFrameType.class).iterator();
        while (it.hasNext()) {
            WebSocketFrameType webSocketFrameType = (WebSocketFrameType) it.next();
            lookup.put(Integer.valueOf(webSocketFrameType.getAssignedValue()), webSocketFrameType);
        }
    }

    private WebSocketFrameType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static WebSocketFrameType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
