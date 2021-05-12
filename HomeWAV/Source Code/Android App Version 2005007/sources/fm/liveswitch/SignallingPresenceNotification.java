package fm.liveswitch;

import java.util.HashMap;

class SignallingPresenceNotification extends Serializable {
    private String __channel;
    private SignallingRemoteClient __remoteClient;
    private SignallingPresenceType __type;

    /* access modifiers changed from: private */
    public static SignallingPresenceType deserializePresenceType(String str) {
        String deserializeString = JsonSerializer.deserializeString(str);
        if (deserializeString.equals("subscribe")) {
            return SignallingPresenceType.Subscribe;
        }
        if (deserializeString.equals("unsubscribe")) {
            return SignallingPresenceType.Unsubscribe;
        }
        throw new RuntimeException(new Exception("Unknown presence type."));
    }

    public static SignallingPresenceNotification fromJson(String str) {
        return (SignallingPresenceNotification) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingPresenceNotification>() {
            public SignallingPresenceNotification invoke() {
                return new SignallingPresenceNotification();
            }
        }, new IAction3<SignallingPresenceNotification, String, String>() {
            public void invoke(SignallingPresenceNotification signallingPresenceNotification, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, Modules.CHANNEL_MODULE)) {
                    signallingPresenceNotification.setChannel(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "remoteClient")) {
                    signallingPresenceNotification.setRemoteClient(SignallingRemoteClient.fromJson(str2));
                } else if (Global.equals(str, "type")) {
                    signallingPresenceNotification.setType(SignallingPresenceNotification.deserializePresenceType(str2));
                }
            }
        });
    }

    public String getChannel() {
        return this.__channel;
    }

    public SignallingRemoteClient getRemoteClient() {
        return this.__remoteClient;
    }

    public SignallingPresenceType getType() {
        return this.__type;
    }

    /* access modifiers changed from: private */
    public static String serializePresenceType(SignallingPresenceType signallingPresenceType) {
        String str;
        if (signallingPresenceType == SignallingPresenceType.Subscribe) {
            str = "subscribe";
        } else if (signallingPresenceType == SignallingPresenceType.Unsubscribe) {
            str = "unsubscribe";
        } else {
            throw new RuntimeException(new Exception("Unknown presence type."));
        }
        return JsonSerializer.serializeString(str);
    }

    public void setChannel(String str) {
        if (str != null) {
            this.__channel = str;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("Channel cannot be null."));
    }

    public void setRemoteClient(SignallingRemoteClient signallingRemoteClient) {
        if (signallingRemoteClient != null) {
            this.__remoteClient = signallingRemoteClient;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("Remote client cannot be null."));
    }

    public void setType(SignallingPresenceType signallingPresenceType) {
        this.__type = signallingPresenceType;
        super.setIsDirty(true);
    }

    public static String toJson(SignallingPresenceNotification signallingPresenceNotification) {
        return JsonSerializer.serializeObjectFast(signallingPresenceNotification, new IAction2<SignallingPresenceNotification, HashMap<String, String>>() {
            public void invoke(SignallingPresenceNotification signallingPresenceNotification, HashMap<String, String> hashMap) {
                if (signallingPresenceNotification.getChannel() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Modules.CHANNEL_MODULE, JsonSerializer.serializeString(signallingPresenceNotification.getChannel()));
                }
                if (signallingPresenceNotification.getRemoteClient() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteClient", SignallingRemoteClient.toJson(signallingPresenceNotification.getRemoteClient()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", SignallingPresenceNotification.serializePresenceType(signallingPresenceNotification.getType()));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
