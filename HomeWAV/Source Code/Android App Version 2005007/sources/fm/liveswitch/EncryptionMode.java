package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum EncryptionMode {
    Null(1),
    Aes128Strong(2),
    Aes128Weak(3),
    NullStrong(4),
    NullWeak(5);
    
    private static final Map<Integer, EncryptionMode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(EncryptionMode.class).iterator();
        while (it.hasNext()) {
            EncryptionMode encryptionMode = (EncryptionMode) it.next();
            lookup.put(Integer.valueOf(encryptionMode.getAssignedValue()), encryptionMode);
        }
    }

    private EncryptionMode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static EncryptionMode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
