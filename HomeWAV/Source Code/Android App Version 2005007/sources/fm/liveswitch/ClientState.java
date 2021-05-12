package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ClientState {
    New(1),
    Registering(2),
    Registered(3),
    Unregistering(4),
    Unregistered(5);
    
    private static final Map<Integer, ClientState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ClientState.class).iterator();
        while (it.hasNext()) {
            ClientState clientState = (ClientState) it.next();
            lookup.put(Integer.valueOf(clientState.getAssignedValue()), clientState);
        }
    }

    private ClientState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ClientState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
