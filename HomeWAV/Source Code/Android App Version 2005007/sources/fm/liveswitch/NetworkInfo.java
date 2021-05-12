package fm.liveswitch;

import java.util.HashMap;

public class NetworkInfo {
    private String[] _ipAddresses;
    private int[] _ports;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "iPAddresses")) {
            setIPAddresses(JsonSerializer.deserializeStringArray(str2));
        } else if (Global.equals(str, "ports")) {
            setPorts(JsonSerializer.deserializeIntegerArray(str2));
        }
    }

    public static NetworkInfo fromJson(String str) {
        return (NetworkInfo) JsonSerializer.deserializeObject(str, new IFunction0<NetworkInfo>() {
            public NetworkInfo invoke() {
                return new NetworkInfo();
            }
        }, new IAction3<NetworkInfo, String, String>() {
            public void invoke(NetworkInfo networkInfo, String str, String str2) {
                networkInfo.deserializeProperties(str, str2);
            }
        });
    }

    public String[] getIPAddresses() {
        return this._ipAddresses;
    }

    public int[] getPorts() {
        return this._ports;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        if (getIPAddresses() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "iPAddresses", JsonSerializer.serializeStringArray(getIPAddresses()));
        }
        if (getPorts() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ports", JsonSerializer.serializeIntegerArray(getPorts()));
        }
    }

    public void setIPAddresses(String[] strArr) {
        this._ipAddresses = strArr;
    }

    public void setPorts(int[] iArr) {
        this._ports = iArr;
    }

    public static String toJson(NetworkInfo networkInfo) {
        return JsonSerializer.serializeObject(networkInfo, new IAction2<NetworkInfo, HashMap<String, String>>() {
            public void invoke(NetworkInfo networkInfo, HashMap<String, String> hashMap) {
                networkInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
