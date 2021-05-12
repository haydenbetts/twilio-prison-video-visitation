package fm.liveswitch;

import java.util.HashMap;

public class ExternalNetworkInfo extends NetworkInfo {
    private String _publicHostname;
    private String _publicIPAddress;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "publicIPAddress")) {
            setPublicIPAddress(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "publicHostname")) {
            setPublicHostname(JsonSerializer.deserializeString(str2));
        }
    }

    public static ExternalNetworkInfo fromJson(String str) {
        return (ExternalNetworkInfo) JsonSerializer.deserializeObject(str, new IFunction0<ExternalNetworkInfo>() {
            public ExternalNetworkInfo invoke() {
                return new ExternalNetworkInfo();
            }
        }, new IAction3<ExternalNetworkInfo, String, String>() {
            public void invoke(ExternalNetworkInfo externalNetworkInfo, String str, String str2) {
                externalNetworkInfo.deserializeProperties(str, str2);
            }
        });
    }

    public String getPublicHostname() {
        return this._publicHostname;
    }

    public String getPublicIPAddress() {
        return this._publicIPAddress;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getPublicIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "publicIPAddress", JsonSerializer.serializeString(getPublicIPAddress()));
        }
        if (getPublicHostname() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "publicHostname", JsonSerializer.serializeString(getPublicHostname()));
        }
    }

    public void setPublicHostname(String str) {
        this._publicHostname = str;
    }

    public void setPublicIPAddress(String str) {
        this._publicIPAddress = str;
    }

    public static String toJson(ExternalNetworkInfo externalNetworkInfo) {
        return JsonSerializer.serializeObject(externalNetworkInfo, new IAction2<ExternalNetworkInfo, HashMap<String, String>>() {
            public void invoke(ExternalNetworkInfo externalNetworkInfo, HashMap<String, String> hashMap) {
                externalNetworkInfo.serializeProperties(hashMap);
            }
        });
    }
}
