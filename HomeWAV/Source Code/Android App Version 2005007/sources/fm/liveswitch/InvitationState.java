package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum InvitationState {
    Unknown(1),
    Pending(2),
    Proceeding(3),
    Sent(4),
    Cancelled(5),
    Cancelling(6),
    Rejected(7),
    Accepted(8),
    NotFound(9),
    Busy(10),
    NoResponse(11),
    Invalid(12),
    AlreadyInvited(13);
    
    private static final Map<Integer, InvitationState> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(InvitationState.class).iterator();
        while (it.hasNext()) {
            InvitationState invitationState = (InvitationState) it.next();
            lookup.put(Integer.valueOf(invitationState.getAssignedValue()), invitationState);
        }
    }

    private InvitationState(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static InvitationState getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
