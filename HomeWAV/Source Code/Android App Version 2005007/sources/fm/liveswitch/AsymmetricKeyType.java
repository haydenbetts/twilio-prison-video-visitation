package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AsymmetricKeyType {
    Rsa(1),
    Ecdsa(2);
    
    private static final Map<Integer, AsymmetricKeyType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(AsymmetricKeyType.class).iterator();
        while (it.hasNext()) {
            AsymmetricKeyType asymmetricKeyType = (AsymmetricKeyType) it.next();
            lookup.put(Integer.valueOf(asymmetricKeyType.getAssignedValue()), asymmetricKeyType);
        }
    }

    private AsymmetricKeyType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static AsymmetricKeyType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
