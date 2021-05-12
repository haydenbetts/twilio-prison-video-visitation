package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum TcpSocketCipherSuites {
    Default(1),
    All(2);
    
    private static final Map<Integer, TcpSocketCipherSuites> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(TcpSocketCipherSuites.class).iterator();
        while (it.hasNext()) {
            TcpSocketCipherSuites tcpSocketCipherSuites = (TcpSocketCipherSuites) it.next();
            lookup.put(Integer.valueOf(tcpSocketCipherSuites.getAssignedValue()), tcpSocketCipherSuites);
        }
    }

    private TcpSocketCipherSuites(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static TcpSocketCipherSuites getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
