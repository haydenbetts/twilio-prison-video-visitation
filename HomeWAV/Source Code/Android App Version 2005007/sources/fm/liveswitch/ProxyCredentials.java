package fm.liveswitch;

import com.urbanairship.util.Attributes;
import java.util.HashMap;

public class ProxyCredentials {
    private String _password;
    private String _username;

    public static ProxyCredentials fromJson(String str) {
        return (ProxyCredentials) JsonSerializer.deserializeObject(str, new IFunction0<ProxyCredentials>() {
            public ProxyCredentials invoke() {
                return new ProxyCredentials();
            }
        }, new IAction3<ProxyCredentials, String, String>() {
            public void invoke(ProxyCredentials proxyCredentials, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, Attributes.USERNAME)) {
                    proxyCredentials.setUsername(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "password")) {
                    proxyCredentials.setPassword(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public String getPassword() {
        return this._password;
    }

    public String getUsername() {
        return this._username;
    }

    private ProxyCredentials() {
    }

    public ProxyCredentials(String str, String str2) {
        setUsername(str);
        setPassword(str2);
    }

    public void setPassword(String str) {
        this._password = str;
    }

    public void setUsername(String str) {
        this._username = str;
    }

    public static String toJson(ProxyCredentials proxyCredentials) {
        return JsonSerializer.serializeObject(proxyCredentials, new IAction2<ProxyCredentials, HashMap<String, String>>() {
            public void invoke(ProxyCredentials proxyCredentials, HashMap<String, String> hashMap) {
                if (proxyCredentials.getUsername() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Attributes.USERNAME, JsonSerializer.serializeString(proxyCredentials.getUsername()));
                }
                if (proxyCredentials.getPassword() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "password", JsonSerializer.serializeString(proxyCredentials.getPassword()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
