package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum HashType {
    Md5(1),
    Sha1(2),
    Sha256(3);
    
    private static final Map<Integer, HashType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(HashType.class).iterator();
        while (it.hasNext()) {
            HashType hashType = (HashType) it.next();
            lookup.put(Integer.valueOf(hashType.getAssignedValue()), hashType);
        }
    }

    private HashType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static HashType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
