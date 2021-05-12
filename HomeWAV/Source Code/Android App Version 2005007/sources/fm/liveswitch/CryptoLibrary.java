package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum CryptoLibrary {
    BouncyCastle(3);
    
    private static final Map<Integer, CryptoLibrary> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(CryptoLibrary.class).iterator();
        while (it.hasNext()) {
            CryptoLibrary cryptoLibrary = (CryptoLibrary) it.next();
            lookup.put(Integer.valueOf(cryptoLibrary.getAssignedValue()), cryptoLibrary);
        }
    }

    private CryptoLibrary(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static CryptoLibrary getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
