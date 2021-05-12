package fm.liveswitch;

import com.urbanairship.json.matchers.VersionMatcher;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientInfo extends Info {
    private String _applicationId;
    private String _architecture;
    private int _coreCount;
    private String _deviceAlias;
    private String _deviceId;
    private String _externalId;
    private String _machineName;
    private String _operatingSystem;
    private String _operatingSystemVersion;
    private long _physicalMemory;
    private String _region;
    private String[] _roles;
    private String _sourceLanguage;
    private String _tag;
    private String _userAlias;
    private String _userId;
    private String _version;

    public ClientInfo() {
    }

    public ClientInfo(String str, String str2, String str3) {
        this(str, (String) null, str2, (String) null, str3, (String) null, (String[]) null);
    }

    public ClientInfo(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr) {
        this(str, str2, str3, str4, str5, str6, strArr, (String) null);
    }

    public ClientInfo(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7) {
        setUserId(str);
        setUserAlias(str2);
        setDeviceId(str3);
        setDeviceAlias(str4);
        super.setId(str5);
        setTag(str6);
        setRoles(strArr);
        setRegion(str7);
    }

    /* access modifiers changed from: package-private */
    public ClientInfo deflate() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setUserId(getUserId());
        clientInfo.setDeviceId(getDeviceId());
        clientInfo.setId(super.getId());
        return clientInfo;
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals(VersionMatcher.ALTERNATE_VERSION_KEY)) {
            setVersion(JsonSerializer.deserializeString(str2));
        } else if (str.equals("applicationId")) {
            setApplicationId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("userId")) {
            setUserId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("userAlias")) {
            setUserAlias(JsonSerializer.deserializeString(str2));
        } else if (str.equals("deviceId")) {
            setDeviceId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("deviceAlias")) {
            setDeviceAlias(JsonSerializer.deserializeString(str2));
        } else if (str.equals("externalId")) {
            setExternalId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("tag")) {
            setTag(JsonSerializer.deserializeString(str2));
        } else if (str.equals("region")) {
            setRegion(JsonSerializer.deserializeString(str2));
        } else if (str.equals("roles")) {
            setRoles(JsonSerializer.deserializeStringArray(str2));
        } else if (str.equals("sourceLanguage")) {
            setSourceLanguage(JsonSerializer.deserializeString(str2));
        } else if (str.equals("machineName")) {
            setMachineName(JsonSerializer.deserializeString(str2));
        } else if (str.equals("coreCount")) {
            setCoreCount(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("physicalMemory")) {
            setPhysicalMemory(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("operatingSystem")) {
            setOperatingSystem(JsonSerializer.deserializeString(str2));
        } else if (str.equals("operatingSystemVersion")) {
            setOperatingSystemVersion(JsonSerializer.deserializeString(str2));
        } else if (str.equals("architecture")) {
            setArchitecture(JsonSerializer.deserializeString(str2));
        }
    }

    public static ClientInfo fromJson(String str) {
        return (ClientInfo) JsonSerializer.deserializeObject(str, new IFunction0<ClientInfo>() {
            public ClientInfo invoke() {
                return new ClientInfo();
            }
        }, new IAction3<ClientInfo, String, String>() {
            public void invoke(ClientInfo clientInfo, String str, String str2) {
                clientInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static ClientInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, ClientInfo>() {
            public String getId() {
                return "fm.liveswitch.ClientInfo.fromJson";
            }

            public ClientInfo invoke(String str) {
                return ClientInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (ClientInfo[]) deserializeObjectArray.toArray(new ClientInfo[0]);
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public String getArchitecture() {
        return this._architecture;
    }

    public int getCoreCount() {
        return this._coreCount;
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public String getExternalId() {
        return this._externalId;
    }

    public String getMachineName() {
        return this._machineName;
    }

    public String getOperatingSystem() {
        return this._operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return this._operatingSystemVersion;
    }

    public long getPhysicalMemory() {
        return this._physicalMemory;
    }

    public String getRegion() {
        return this._region;
    }

    public String[] getRoles() {
        return this._roles;
    }

    public String getSourceLanguage() {
        return this._sourceLanguage;
    }

    public String getTag() {
        return this._tag;
    }

    public String getUserAlias() {
        return this._userAlias;
    }

    public String getUserId() {
        return this._userId;
    }

    public String getVersion() {
        return this._version;
    }

    /* access modifiers changed from: package-private */
    public void inflate(String str, String str2, String str3, String[] strArr) {
        setUserAlias(str);
        setDeviceAlias(str2);
        setTag(str3);
        setRoles(strArr);
    }

    public boolean isEquivalent(ClientInfo clientInfo) {
        return clientInfo != null && isEquivalent(clientInfo.getUserId(), clientInfo.getDeviceId(), clientInfo.getId());
    }

    public boolean isEquivalent(ConnectionInfo connectionInfo) {
        return connectionInfo != null && isEquivalent(connectionInfo.getUserId(), connectionInfo.getDeviceId(), connectionInfo.getClientId());
    }

    public boolean isEquivalent(String str, String str2, String str3) {
        return Global.equals(str, getUserId()) && Global.equals(str2, getDeviceId()) && Global.equals(str3, super.getId());
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getVersion() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), VersionMatcher.ALTERNATE_VERSION_KEY, JsonSerializer.serializeString(getVersion()));
        }
        if (getApplicationId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "applicationId", JsonSerializer.serializeString(getApplicationId()));
        }
        if (getUserId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(getUserId()));
        }
        if (getUserAlias() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userAlias", JsonSerializer.serializeString(getUserAlias()));
        }
        if (getDeviceId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceId", JsonSerializer.serializeString(getDeviceId()));
        }
        if (getDeviceAlias() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceAlias", JsonSerializer.serializeString(getDeviceAlias()));
        }
        if (getExternalId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "externalId", JsonSerializer.serializeString(getExternalId()));
        }
        if (getTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (getRegion() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "region", JsonSerializer.serializeString(getRegion()));
        }
        if (getRoles() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "roles", JsonSerializer.serializeStringArray(getRoles()));
        }
        if (getSourceLanguage() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sourceLanguage", JsonSerializer.serializeString(getSourceLanguage()));
        }
        if (getMachineName() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "machineName", JsonSerializer.serializeString(getMachineName()));
        }
        if (getCoreCount() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "coreCount", JsonSerializer.serializeInteger(new NullableInteger(getCoreCount())));
        }
        if (getPhysicalMemory() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "physicalMemory", JsonSerializer.serializeLong(new NullableLong(getPhysicalMemory())));
        }
        if (getOperatingSystem() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "operatingSystem", JsonSerializer.serializeString(getOperatingSystem()));
        }
        if (getOperatingSystemVersion() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "operatingSystemVersion", JsonSerializer.serializeString(getOperatingSystemVersion()));
        }
        if (getArchitecture() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "architecture", JsonSerializer.serializeString(getArchitecture()));
        }
    }

    public void setApplicationId(String str) {
        this._applicationId = str;
    }

    public void setArchitecture(String str) {
        this._architecture = str;
    }

    public void setCoreCount(int i) {
        this._coreCount = i;
    }

    public void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    public void setMachineName(String str) {
        this._machineName = str;
    }

    public void setOperatingSystem(String str) {
        this._operatingSystem = str;
    }

    public void setOperatingSystemVersion(String str) {
        this._operatingSystemVersion = str;
    }

    public void setPhysicalMemory(long j) {
        this._physicalMemory = j;
    }

    public void setRegion(String str) {
        this._region = str;
    }

    public void setRoles(String[] strArr) {
        this._roles = strArr;
    }

    public void setSourceLanguage(String str) {
        this._sourceLanguage = str;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setUserAlias(String str) {
        this._userAlias = str;
    }

    public void setUserId(String str) {
        this._userId = str;
    }

    public void setVersion(String str) {
        this._version = str;
    }

    public static String toJson(ClientInfo clientInfo) {
        return JsonSerializer.serializeObject(clientInfo, new IAction2<ClientInfo, HashMap<String, String>>() {
            public void invoke(ClientInfo clientInfo, HashMap<String, String> hashMap) {
                clientInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(ClientInfo[] clientInfoArr) {
        return JsonSerializer.serializeObjectArray(clientInfoArr, new IFunctionDelegate1<ClientInfo, String>() {
            public String getId() {
                return "fm.liveswitch.ClientInfo.toJson";
            }

            public String invoke(ClientInfo clientInfo) {
                return ClientInfo.toJson(clientInfo);
            }
        });
    }
}
