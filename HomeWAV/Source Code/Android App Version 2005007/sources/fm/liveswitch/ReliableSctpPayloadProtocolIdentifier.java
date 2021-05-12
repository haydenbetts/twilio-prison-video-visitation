package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum ReliableSctpPayloadProtocolIdentifier {
    WebRtcDcep(50),
    WebRtcString(51),
    WebRtcBinary(53),
    WebRtcEmptyString(56),
    WebRtcEmptyBinary(57);
    
    private static final Map<Integer, ReliableSctpPayloadProtocolIdentifier> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ReliableSctpPayloadProtocolIdentifier.class).iterator();
        while (it.hasNext()) {
            ReliableSctpPayloadProtocolIdentifier reliableSctpPayloadProtocolIdentifier = (ReliableSctpPayloadProtocolIdentifier) it.next();
            lookup.put(Integer.valueOf(reliableSctpPayloadProtocolIdentifier.getAssignedValue()), reliableSctpPayloadProtocolIdentifier);
        }
    }

    private ReliableSctpPayloadProtocolIdentifier(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ReliableSctpPayloadProtocolIdentifier getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
