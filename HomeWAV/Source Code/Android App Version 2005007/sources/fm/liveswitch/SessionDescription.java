package fm.liveswitch;

import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.Origin;
import java.util.HashMap;

public class SessionDescription {
    private boolean _renegotiation;
    private Message _sdpMessage;
    private String _tieBreaker;
    private SessionDescriptionType _type;

    public static SessionDescription fromJson(String str) {
        return (SessionDescription) JsonSerializer.deserializeObject(str, new IFunction0<SessionDescription>() {
            public SessionDescription invoke() {
                return new SessionDescription();
            }
        }, new IAction3<SessionDescription, String, String>() {
            public void invoke(SessionDescription sessionDescription, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "sdpMessage")) {
                    sessionDescription.setSdpMessage(Message.parse(JsonSerializer.deserializeString(str2)));
                } else if (Global.equals(str, "tieBreaker")) {
                    sessionDescription.setTieBreaker(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "isOffer")) {
                    sessionDescription.setType((!JsonSerializer.deserializeBoolean(str2).hasValue() || !JsonSerializer.deserializeBoolean(str2).getValue()) ? SessionDescriptionType.Answer : SessionDescriptionType.Offer);
                }
            }
        });
    }

    public boolean getHasAudio() {
        Message sdpMessage = getSdpMessage();
        if (sdpMessage == null || sdpMessage.getAudioDescription() == null) {
            return false;
        }
        return true;
    }

    public boolean getHasData() {
        Message sdpMessage = getSdpMessage();
        if (sdpMessage == null) {
            return false;
        }
        if (sdpMessage.getApplicationDescription() == null && sdpMessage.getTextDescription() == null && sdpMessage.getMessageDescription() == null) {
            return false;
        }
        return true;
    }

    public boolean getHasVideo() {
        Message sdpMessage = getSdpMessage();
        if (sdpMessage == null || sdpMessage.getVideoDescription() == null) {
            return false;
        }
        return true;
    }

    public boolean getIsOffer() {
        return Global.equals(getType(), SessionDescriptionType.Offer);
    }

    /* access modifiers changed from: package-private */
    public boolean getRenegotiation() {
        return this._renegotiation;
    }

    public Message getSdpMessage() {
        return this._sdpMessage;
    }

    public long getSessionId() {
        Origin origin;
        if (getSdpMessage() == null || (origin = getSdpMessage().getOrigin()) == null) {
            return -1;
        }
        return origin.getSessionId();
    }

    public long getSessionVersion() {
        Origin origin;
        if (getSdpMessage() == null || (origin = getSdpMessage().getOrigin()) == null) {
            return -1;
        }
        return origin.getSessionVersion();
    }

    public String getTieBreaker() {
        return this._tieBreaker;
    }

    public SessionDescriptionType getType() {
        return this._type;
    }

    /* access modifiers changed from: package-private */
    public void setRenegotiation(boolean z) {
        this._renegotiation = z;
    }

    public void setSdpMessage(Message message) {
        this._sdpMessage = message;
    }

    public void setTieBreaker(String str) {
        this._tieBreaker = str;
    }

    public void setType(SessionDescriptionType sessionDescriptionType) {
        this._type = sessionDescriptionType;
    }

    public static String toJson(SessionDescription sessionDescription) {
        return JsonSerializer.serializeObject(sessionDescription, new IAction2<SessionDescription, HashMap<String, String>>(sessionDescription) {
            final /* synthetic */ SessionDescription val$sessionDescription;

            {
                this.val$sessionDescription = r1;
            }

            public void invoke(SessionDescription sessionDescription, HashMap<String, String> hashMap) {
                if (this.val$sessionDescription.getSdpMessage() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sdpMessage", JsonSerializer.serializeString(this.val$sessionDescription.getSdpMessage().toString()));
                }
                if (this.val$sessionDescription.getTieBreaker() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tieBreaker", JsonSerializer.serializeString(this.val$sessionDescription.getTieBreaker()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isOffer", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$sessionDescription.getIsOffer())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
