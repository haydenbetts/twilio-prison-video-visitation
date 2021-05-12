package fm.liveswitch;

import java.util.HashMap;

public class MessageInfo extends Info {
    private String _clientId;
    private String _deviceId;
    private String _message;
    private String _userId;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "userId")) {
            setUserId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "deviceId")) {
            setDeviceId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "clientId")) {
            setClientId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "message")) {
            setMessage(JsonSerializer.deserializeString(str2));
        }
    }

    public static MessageInfo fromJson(String str) {
        return (MessageInfo) JsonSerializer.deserializeObject(str, new IFunction0<MessageInfo>() {
            public MessageInfo invoke() {
                return new MessageInfo();
            }
        }, new IAction3<MessageInfo, String, String>() {
            public void invoke(MessageInfo messageInfo, String str, String str2) {
                messageInfo.deserializeProperties(str, str2);
            }
        });
    }

    public String getClientId() {
        return this._clientId;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public String getMessage() {
        return this._message;
    }

    public String getUserId() {
        return this._userId;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getUserId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(getUserId()));
        }
        if (getDeviceId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceId", JsonSerializer.serializeString(getDeviceId()));
        }
        if (getClientId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientId", JsonSerializer.serializeString(getClientId()));
        }
        if (getMessage() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "message", JsonSerializer.serializeString(getMessage()));
        }
    }

    public void setClientId(String str) {
        this._clientId = str;
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setMessage(String str) {
        this._message = str;
    }

    public void setUserId(String str) {
        this._userId = str;
    }

    public static String toJson(MessageInfo messageInfo) {
        return JsonSerializer.serializeObject(messageInfo, new IAction2<MessageInfo, HashMap<String, String>>() {
            public void invoke(MessageInfo messageInfo, HashMap<String, String> hashMap) {
                messageInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
