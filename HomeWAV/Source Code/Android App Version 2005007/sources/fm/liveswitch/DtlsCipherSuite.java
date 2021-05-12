package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum DtlsCipherSuite {
    RsaAes128Sha(1),
    EcdheRsaAes128Sha(2),
    EcdheEcdsaAes128Sha(3),
    RsaAes128GcmSha256(4),
    EcdheRsaAes128GcmSha256(5),
    EcdheEcdsaAes128GcmSha256(6),
    RsaAes128CbcSha256(7),
    EcdheRsaAes128CbcSha256(8),
    EcdheEcdsaAes128CbcSha256(9);
    
    private static final Map<Integer, DtlsCipherSuite> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(DtlsCipherSuite.class).iterator();
        while (it.hasNext()) {
            DtlsCipherSuite dtlsCipherSuite = (DtlsCipherSuite) it.next();
            lookup.put(Integer.valueOf(dtlsCipherSuite.getAssignedValue()), dtlsCipherSuite);
        }
    }

    private DtlsCipherSuite(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static DtlsCipherSuite getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
