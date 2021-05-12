package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum X509TimeType {
    Utc(1),
    Generalized(2);
    
    private static final Map<Integer, X509TimeType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(X509TimeType.class).iterator();
        while (it.hasNext()) {
            X509TimeType x509TimeType = (X509TimeType) it.next();
            lookup.put(Integer.valueOf(x509TimeType.getAssignedValue()), x509TimeType);
        }
    }

    private X509TimeType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static X509TimeType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
