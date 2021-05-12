package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkInterfaceInfo extends Info {
    private long _adapterSpeed;
    private String _ipAddress;
    private String _mask;
    private String _type;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "type")) {
            setType(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "ipAddress")) {
            setIPAddress(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "mask")) {
            setMask(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "adapterSpeed")) {
            setAdapterSpeed(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static NetworkInterfaceInfo fromJson(String str) {
        return (NetworkInterfaceInfo) JsonSerializer.deserializeObject(str, new IFunction0<NetworkInterfaceInfo>() {
            public NetworkInterfaceInfo invoke() {
                return new NetworkInterfaceInfo();
            }
        }, new IAction3<NetworkInterfaceInfo, String, String>() {
            public void invoke(NetworkInterfaceInfo networkInterfaceInfo, String str, String str2) {
                networkInterfaceInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static NetworkInterfaceInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, NetworkInterfaceInfo>() {
            public String getId() {
                return "fm.liveswitch.NetworkInterfaceInfo.fromJson";
            }

            public NetworkInterfaceInfo invoke(String str) {
                return NetworkInterfaceInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (NetworkInterfaceInfo[]) deserializeObjectArray.toArray(new NetworkInterfaceInfo[0]);
    }

    public long getAdapterSpeed() {
        return this._adapterSpeed;
    }

    public String getIPAddress() {
        return this._ipAddress;
    }

    public String getMask() {
        return this._mask;
    }

    public String getType() {
        return this._type;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(getType()));
        }
        if (getIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ipAddress", JsonSerializer.serializeString(getIPAddress()));
        }
        if (getMask() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mask", JsonSerializer.serializeString(getMask()));
        }
        if (getAdapterSpeed() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "adapterSpeed", JsonSerializer.serializeLong(new NullableLong(getAdapterSpeed())));
        }
    }

    public void setAdapterSpeed(long j) {
        this._adapterSpeed = j;
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setMask(String str) {
        this._mask = str;
    }

    public void setType(String str) {
        this._type = str;
    }

    public static String toJson(NetworkInterfaceInfo networkInterfaceInfo) {
        return JsonSerializer.serializeObject(networkInterfaceInfo, new IAction2<NetworkInterfaceInfo, HashMap<String, String>>() {
            public void invoke(NetworkInterfaceInfo networkInterfaceInfo, HashMap<String, String> hashMap) {
                networkInterfaceInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(NetworkInterfaceInfo[] networkInterfaceInfoArr) {
        return JsonSerializer.serializeObjectArray(networkInterfaceInfoArr, new IFunctionDelegate1<NetworkInterfaceInfo, String>() {
            public String getId() {
                return "fm.liveswitch.NetworkInterfaceInfo.toJson";
            }

            public String invoke(NetworkInterfaceInfo networkInterfaceInfo) {
                return NetworkInterfaceInfo.toJson(networkInterfaceInfo);
            }
        });
    }
}
