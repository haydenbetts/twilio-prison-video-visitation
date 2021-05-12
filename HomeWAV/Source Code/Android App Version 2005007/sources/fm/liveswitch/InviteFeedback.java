package fm.liveswitch;

import java.util.HashMap;

public class InviteFeedback {
    private String _protocol;
    private String _reason;
    private InvitationState _state;
    private String _userId;

    /* access modifiers changed from: private */
    public static InvitationState deserializeState(String str) {
        if (str.equals("pending")) {
            return InvitationState.Pending;
        }
        if (str.equals("proceeding")) {
            return InvitationState.Proceeding;
        }
        if (str.equals("sent")) {
            return InvitationState.Sent;
        }
        if (str.equals("cancelled")) {
            return InvitationState.Cancelled;
        }
        if (str.equals("cancelling")) {
            return InvitationState.Cancelling;
        }
        if (str.equals("rejected")) {
            return InvitationState.Rejected;
        }
        if (str.equals("accepted")) {
            return InvitationState.Accepted;
        }
        if (str.equals("notFound")) {
            return InvitationState.NotFound;
        }
        if (str.equals("busy")) {
            return InvitationState.Busy;
        }
        if (str.equals("noResponse")) {
            return InvitationState.NoResponse;
        }
        if (str.equals("invalid")) {
            return InvitationState.Invalid;
        }
        if (str.equals("alreadyInvited")) {
            return InvitationState.AlreadyInvited;
        }
        return InvitationState.Unknown;
    }

    public static InviteFeedback fromJson(String str) {
        return (InviteFeedback) JsonSerializer.deserializeObject(str, new IFunction0<InviteFeedback>() {
            public InviteFeedback invoke() {
                return new InviteFeedback();
            }
        }, new IAction3<InviteFeedback, String, String>() {
            public void invoke(InviteFeedback inviteFeedback, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "protocol")) {
                    inviteFeedback.setProtocol(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "userId")) {
                    inviteFeedback.setUserId(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "state")) {
                    inviteFeedback.setState(InviteFeedback.deserializeState(JsonSerializer.deserializeString(str2)));
                } else if (Global.equals(str, "reason")) {
                    inviteFeedback.setReason(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public String getProtocol() {
        return this._protocol;
    }

    public String getReason() {
        return this._reason;
    }

    public InvitationState getState() {
        return this._state;
    }

    public String getUserId() {
        return this._userId;
    }

    private InviteFeedback() {
    }

    public InviteFeedback(String str, String str2, InvitationState invitationState, String str3) {
        setUserId(str);
        setProtocol(str2);
        setState(invitationState);
        setReason(str3);
    }

    /* access modifiers changed from: private */
    public static String serializeState(InvitationState invitationState) {
        if (invitationState == InvitationState.Pending) {
            return "pending";
        }
        if (invitationState == InvitationState.Proceeding) {
            return "proceeding";
        }
        if (invitationState == InvitationState.Sent) {
            return "sent";
        }
        if (invitationState == InvitationState.Cancelled) {
            return "cancelled";
        }
        if (invitationState == InvitationState.Cancelling) {
            return "cancelling";
        }
        if (invitationState == InvitationState.Rejected) {
            return "rejected";
        }
        if (invitationState == InvitationState.Accepted) {
            return "accepted";
        }
        if (invitationState == InvitationState.NotFound) {
            return "notFound";
        }
        if (invitationState == InvitationState.Busy) {
            return "busy";
        }
        if (invitationState == InvitationState.NoResponse) {
            return "noResponse";
        }
        if (invitationState == InvitationState.Invalid) {
            return "invalid";
        }
        return invitationState == InvitationState.AlreadyInvited ? "alreadyInvited" : "unknown";
    }

    /* access modifiers changed from: private */
    public void setProtocol(String str) {
        this._protocol = str;
    }

    /* access modifiers changed from: private */
    public void setReason(String str) {
        this._reason = str;
    }

    /* access modifiers changed from: private */
    public void setState(InvitationState invitationState) {
        this._state = invitationState;
    }

    /* access modifiers changed from: private */
    public void setUserId(String str) {
        this._userId = str;
    }

    public static String toJson(InviteFeedback inviteFeedback) {
        return JsonSerializer.serializeObject(inviteFeedback, new IAction2<InviteFeedback, HashMap<String, String>>() {
            public void invoke(InviteFeedback inviteFeedback, HashMap<String, String> hashMap) {
                if (inviteFeedback.getProtocol() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "protocol", JsonSerializer.serializeString(inviteFeedback.getProtocol()));
                }
                if (inviteFeedback.getUserId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(inviteFeedback.getUserId()));
                }
                if (inviteFeedback.getReason() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "reason", JsonSerializer.serializeString(inviteFeedback.getReason()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(InviteFeedback.serializeState(inviteFeedback.getState())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
