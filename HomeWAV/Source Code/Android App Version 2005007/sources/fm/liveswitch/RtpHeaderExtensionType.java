package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum RtpHeaderExtensionType {
    AbsSendTime(1),
    SdesRtpStreamId(2),
    SdesRepairedRtpStreamId(3),
    SdesMid(4),
    Unknown(16);
    
    private static final Map<Integer, RtpHeaderExtensionType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(RtpHeaderExtensionType.class).iterator();
        while (it.hasNext()) {
            RtpHeaderExtensionType rtpHeaderExtensionType = (RtpHeaderExtensionType) it.next();
            lookup.put(Integer.valueOf(rtpHeaderExtensionType.getAssignedValue()), rtpHeaderExtensionType);
        }
    }

    private RtpHeaderExtensionType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static RtpHeaderExtensionType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
