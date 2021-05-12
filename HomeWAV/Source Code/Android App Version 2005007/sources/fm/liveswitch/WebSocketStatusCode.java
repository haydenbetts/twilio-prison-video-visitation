package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum WebSocketStatusCode {
    Normal(1000),
    GoingAway(1001),
    ProtocolError(1002),
    InvalidType(1003),
    NoStatus(1005),
    Abnormal(1006),
    InvalidData(1007),
    PolicyViolation(1008),
    MessageTooLarge(1009),
    UnsupportedExtension(1010),
    UnexpectedCondition(1011),
    SecureHandshakeFailure(1015);
    
    private static final Map<Integer, WebSocketStatusCode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(WebSocketStatusCode.class).iterator();
        while (it.hasNext()) {
            WebSocketStatusCode webSocketStatusCode = (WebSocketStatusCode) it.next();
            lookup.put(Integer.valueOf(webSocketStatusCode.getAssignedValue()), webSocketStatusCode);
        }
    }

    private WebSocketStatusCode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static WebSocketStatusCode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
