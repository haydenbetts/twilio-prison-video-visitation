package fm.liveswitch;

import java.util.HashMap;

public class ClientConfig {
    private String _deviceAlias;
    private String[] _roles;
    private String _tag;
    private String _userAlias;

    ClientConfig() {
    }

    public static ClientConfig fromJson(String str) {
        return (ClientConfig) JsonSerializer.deserializeObject(str, new IFunction0<ClientConfig>() {
            public ClientConfig invoke() {
                return new ClientConfig();
            }
        }, new IAction3<ClientConfig, String, String>() {
            public void invoke(ClientConfig clientConfig, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "userAlias")) {
                    clientConfig.setUserAlias(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "deviceAlias")) {
                    clientConfig.setDeviceAlias(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "tag")) {
                    clientConfig.setTag(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "roles")) {
                    clientConfig.setRoles(JsonSerializer.deserializeStringArray(str2));
                }
            }
        });
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String[] getRoles() {
        return this._roles;
    }

    public String getTag() {
        return this._tag;
    }

    public String getUserAlias() {
        return this._userAlias;
    }

    public void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    public void setRoles(String[] strArr) {
        this._roles = strArr;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setUserAlias(String str) {
        this._userAlias = str;
    }

    public static String toJson(ClientConfig clientConfig) {
        return JsonSerializer.serializeObject(clientConfig, new IAction2<ClientConfig, HashMap<String, String>>() {
            public void invoke(ClientConfig clientConfig, HashMap<String, String> hashMap) {
                if (clientConfig.getUserAlias() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userAlias", JsonSerializer.serializeString(clientConfig.getUserAlias()));
                }
                if (clientConfig.getDeviceAlias() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceAlias", JsonSerializer.serializeString(clientConfig.getDeviceAlias()));
                }
                if (clientConfig.getTag() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(clientConfig.getTag()));
                }
                if (clientConfig.getRoles() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "roles", JsonSerializer.serializeStringArray(clientConfig.getRoles()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
