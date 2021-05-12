package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum RtpHeaderExtensionForm {
    OneByte(1),
    TwoByte(2);
    
    private static final Map<Integer, RtpHeaderExtensionForm> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(RtpHeaderExtensionForm.class).iterator();
        while (it.hasNext()) {
            RtpHeaderExtensionForm rtpHeaderExtensionForm = (RtpHeaderExtensionForm) it.next();
            lookup.put(Integer.valueOf(rtpHeaderExtensionForm.getAssignedValue()), rtpHeaderExtensionForm);
        }
    }

    private RtpHeaderExtensionForm(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static RtpHeaderExtensionForm getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
