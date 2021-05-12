package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class SignallingRemoteClient extends Serializable {
    SignallingRecords __boundRecords;
    String __clientId;
    String __deviceId;
    String __streamId;
    String __userId;

    /* access modifiers changed from: private */
    public static SignallingRemoteClient createRemoteClient() {
        return new SignallingRemoteClient();
    }

    private static SignallingRemoteClient deserializeRemoteClient(String str) {
        return (SignallingRemoteClient) JsonSerializer.deserializeObjectFast(str, new IFunctionDelegate0<SignallingRemoteClient>() {
            public String getId() {
                return "fm.liveswitch.SignallingRemoteClient.createRemoteClient";
            }

            public SignallingRemoteClient invoke() {
                return SignallingRemoteClient.createRemoteClient();
            }
        }, new IActionDelegate3<SignallingRemoteClient, String, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingRemoteClient.deserializeRemoteClientCallback";
            }

            public void invoke(SignallingRemoteClient signallingRemoteClient, String str, String str2) {
                SignallingRemoteClient.deserializeRemoteClientCallback(signallingRemoteClient, str, str2);
            }
        });
    }

    private static SignallingRemoteClient[] deserializeRemoteClientArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingRemoteClient>() {
            public String getId() {
                return "fm.liveswitch.SignallingRemoteClient.fromJson";
            }

            public SignallingRemoteClient invoke(String str) {
                return SignallingRemoteClient.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingRemoteClient[]) deserializeObjectArray.toArray(new SignallingRemoteClient[0]);
    }

    /* access modifiers changed from: private */
    public static void deserializeRemoteClientCallback(SignallingRemoteClient signallingRemoteClient, String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "clientId")) {
            signallingRemoteClient.__clientId = JsonSerializer.deserializeString(str2);
        } else if (Global.equals(str, "streamId")) {
            signallingRemoteClient.__streamId = JsonSerializer.deserializeString(str2);
        } else if (Global.equals(str, "userId")) {
            signallingRemoteClient.__userId = JsonSerializer.deserializeString(str2);
        } else if (Global.equals(str, "deviceId")) {
            signallingRemoteClient.__deviceId = JsonSerializer.deserializeString(str2);
        } else if (Global.equals(str, "boundRecords")) {
            signallingRemoteClient.__boundRecords = SignallingRecords.fromJson(str2);
        }
    }

    public static SignallingRemoteClient fromJson(String str) {
        return deserializeRemoteClient(str);
    }

    public static SignallingRemoteClient[] fromJsonArray(String str) {
        return deserializeRemoteClientArray(str);
    }

    public SignallingRecords getBoundRecords() {
        return this.__boundRecords;
    }

    public String getBoundValueJson(String str) {
        if (getBoundRecords() == null) {
            return null;
        }
        return getBoundRecords().getValueJson(str);
    }

    public String getClientId() {
        return this.__clientId;
    }

    public String getDeviceId() {
        return this.__deviceId;
    }

    public String getStreamId() {
        return this.__streamId;
    }

    public String getUserId() {
        return this.__userId;
    }

    private static String serializeRemoteClient(SignallingRemoteClient signallingRemoteClient) {
        return JsonSerializer.serializeObjectFast(signallingRemoteClient, new IActionDelegate2<SignallingRemoteClient, HashMap<String, String>>() {
            public String getId() {
                return "fm.liveswitch.SignallingRemoteClient.serializeRemoteClientCallback";
            }

            public void invoke(SignallingRemoteClient signallingRemoteClient, HashMap<String, String> hashMap) {
                SignallingRemoteClient.serializeRemoteClientCallback(signallingRemoteClient, hashMap);
            }
        });
    }

    private static String serializeRemoteClientArray(SignallingRemoteClient[] signallingRemoteClientArr) {
        return JsonSerializer.serializeObjectArray(signallingRemoteClientArr, new IFunctionDelegate1<SignallingRemoteClient, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingRemoteClient.toJson";
            }

            public String invoke(SignallingRemoteClient signallingRemoteClient) {
                return SignallingRemoteClient.toJson(signallingRemoteClient);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void serializeRemoteClientCallback(SignallingRemoteClient signallingRemoteClient, HashMap<String, String> hashMap) {
        if (signallingRemoteClient.getClientId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientId", JsonSerializer.serializeString(signallingRemoteClient.getClientId()));
        }
        if (signallingRemoteClient.getStreamId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "streamId", JsonSerializer.serializeString(signallingRemoteClient.getStreamId()));
        }
        if (signallingRemoteClient.getUserId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(signallingRemoteClient.getUserId()));
        }
        if (signallingRemoteClient.getDeviceId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceId", JsonSerializer.serializeString(signallingRemoteClient.getDeviceId()));
        }
        if (signallingRemoteClient.getBoundRecords() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "boundRecords", SignallingRecords.toJson(signallingRemoteClient.getBoundRecords()));
        }
    }

    public SignallingRemoteClient() {
        this((String) null, (String) null, (String) null, (String) null, new SignallingRecords());
    }

    public SignallingRemoteClient(String str, String str2, String str3, String str4, SignallingRecords signallingRecords) {
        this.__clientId = str;
        this.__streamId = str2;
        this.__userId = str3;
        this.__deviceId = str4;
        this.__boundRecords = signallingRecords;
    }

    public static String toJson(SignallingRemoteClient signallingRemoteClient) {
        return serializeRemoteClient(signallingRemoteClient);
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingRemoteClient[] signallingRemoteClientArr) {
        return serializeRemoteClientArray(signallingRemoteClientArr);
    }
}
