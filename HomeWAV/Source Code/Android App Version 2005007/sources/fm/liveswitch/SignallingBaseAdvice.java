package fm.liveswitch;

import java.util.HashMap;

class SignallingBaseAdvice extends Serializable {
    private String[] __hosts;
    private NullableInteger __interval = new NullableInteger();
    private SignallingReconnect __reconnect = SignallingReconnect.NotSet;

    protected static SignallingReconnect deserializeReconnect(String str) {
        String deserializeString = JsonSerializer.deserializeString(str);
        if (deserializeString.equals("retry")) {
            return SignallingReconnect.Retry;
        }
        if (deserializeString.equals("handshake")) {
            return SignallingReconnect.Handshake;
        }
        if (deserializeString.equals("none")) {
            return SignallingReconnect.None;
        }
        return SignallingReconnect.NotSet;
    }

    public static SignallingBaseAdvice fromJson(String str) {
        return (SignallingBaseAdvice) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingBaseAdvice>() {
            public SignallingBaseAdvice invoke() {
                return new SignallingBaseAdvice();
            }
        }, new IAction3<SignallingBaseAdvice, String, String>() {
            public void invoke(SignallingBaseAdvice signallingBaseAdvice, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "hosts")) {
                    signallingBaseAdvice.setHosts(JsonSerializer.deserializeStringArray(str2));
                } else if (Global.equals(str, "interval")) {
                    signallingBaseAdvice.setInterval(JsonSerializer.deserializeInteger(str2));
                } else if (Global.equals(str, "reconnect")) {
                    signallingBaseAdvice.setReconnect(SignallingBaseAdvice.deserializeReconnect(str2));
                }
            }
        });
    }

    public String[] getHosts() {
        return this.__hosts;
    }

    public NullableInteger getInterval() {
        return this.__interval;
    }

    public SignallingReconnect getReconnect() {
        return this.__reconnect;
    }

    protected static String serializeReconnect(SignallingReconnect signallingReconnect) {
        String str;
        if (signallingReconnect == SignallingReconnect.Retry) {
            str = "retry";
        } else if (signallingReconnect == SignallingReconnect.Handshake) {
            str = "handshake";
        } else {
            str = signallingReconnect == SignallingReconnect.None ? "none" : null;
        }
        return JsonSerializer.serializeString(str);
    }

    public void setHosts(String[] strArr) {
        this.__hosts = strArr;
        super.setIsDirty(true);
    }

    public void setInterval(NullableInteger nullableInteger) {
        this.__interval = nullableInteger;
        super.setIsDirty(true);
    }

    public void setReconnect(SignallingReconnect signallingReconnect) {
        this.__reconnect = signallingReconnect;
        super.setIsDirty(true);
    }

    public static String toJson(SignallingBaseAdvice signallingBaseAdvice) {
        return JsonSerializer.serializeObjectFast(signallingBaseAdvice, new IAction2<SignallingBaseAdvice, HashMap<String, String>>() {
            public void invoke(SignallingBaseAdvice signallingBaseAdvice, HashMap<String, String> hashMap) {
                if (signallingBaseAdvice.getHosts() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "hosts", JsonSerializer.serializeStringArray(signallingBaseAdvice.getHosts()));
                }
                if (signallingBaseAdvice.getInterval().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "interval", JsonSerializer.serializeInteger(signallingBaseAdvice.getInterval()));
                }
                if (!Global.equals(signallingBaseAdvice.getReconnect(), SignallingReconnect.NotSet)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "reconnect", SignallingBaseAdvice.serializeReconnect(signallingBaseAdvice.getReconnect()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
