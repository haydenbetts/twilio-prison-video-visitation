package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum EncryptionPolicy {
    Required(1),
    Negotiated(2),
    Disabled(3);
    
    private static final Map<Integer, EncryptionPolicy> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(EncryptionPolicy.class).iterator();
        while (it.hasNext()) {
            EncryptionPolicy encryptionPolicy = (EncryptionPolicy) it.next();
            lookup.put(Integer.valueOf(encryptionPolicy.getAssignedValue()), encryptionPolicy);
        }
    }

    private EncryptionPolicy(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static EncryptionPolicy getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
