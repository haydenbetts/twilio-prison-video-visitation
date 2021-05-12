package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ConnectionState {
    New(1),
    Initializing(2),
    Connecting(3),
    Connected(4),
    Failing(5),
    Failed(6),
    Closing(7),
    Closed(8);
    
    private static final Map<Integer, ConnectionState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ConnectionState.class).iterator();
        while (it.hasNext()) {
            ConnectionState connectionState = (ConnectionState) it.next();
            lookup.put(Integer.valueOf(connectionState.getAssignedValue()), connectionState);
        }
    }

    private ConnectionState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ConnectionState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
