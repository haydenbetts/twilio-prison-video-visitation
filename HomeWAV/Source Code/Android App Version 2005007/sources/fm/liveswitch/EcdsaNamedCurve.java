package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum EcdsaNamedCurve {
    P256(1),
    P384(2),
    P521(3);
    
    private static final Map<Integer, EcdsaNamedCurve> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(EcdsaNamedCurve.class).iterator();
        while (it.hasNext()) {
            EcdsaNamedCurve ecdsaNamedCurve = (EcdsaNamedCurve) it.next();
            lookup.put(Integer.valueOf(ecdsaNamedCurve.getAssignedValue()), ecdsaNamedCurve);
        }
    }

    private EcdsaNamedCurve(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static EcdsaNamedCurve getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
