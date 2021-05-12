package fm.liveswitch.sdp;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum MediaStreamIdSemanticToken {
    Wms(1);
    
    private static final Map<Integer, MediaStreamIdSemanticToken> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(MediaStreamIdSemanticToken.class).iterator();
        while (it.hasNext()) {
            MediaStreamIdSemanticToken mediaStreamIdSemanticToken = (MediaStreamIdSemanticToken) it.next();
            lookup.put(Integer.valueOf(mediaStreamIdSemanticToken.getAssignedValue()), mediaStreamIdSemanticToken);
        }
    }

    private MediaStreamIdSemanticToken(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static MediaStreamIdSemanticToken getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
