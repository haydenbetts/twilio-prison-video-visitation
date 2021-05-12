package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import com.urbanairship.util.Attributes;
import java.util.ArrayList;
import java.util.HashMap;

public class IceServer {
    private String __ipAddress;
    private String[] __ipAddresses;
    private String _password;
    private String _url;
    private String _username;

    public static int getDefaultStunPort() {
        return 3478;
    }

    public static int getDefaultStunsPort() {
        return 5349;
    }

    public static int getDefaultTurnPort() {
        return 3478;
    }

    public static int getDefaultTurnsPort() {
        return 5349;
    }

    public static IceServer fromJson(String str) {
        return (IceServer) JsonSerializer.deserializeObject(str, new IFunction0<IceServer>() {
            public IceServer invoke() {
                return new IceServer();
            }
        }, new IAction3<IceServer, String, String>() {
            public void invoke(IceServer iceServer, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "url")) {
                    iceServer.setUrl(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, Attributes.USERNAME)) {
                    iceServer.setUsername(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "password")) {
                    iceServer.setPassword(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public static IceServer[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, IceServer>() {
            public String getId() {
                return "fm.liveswitch.IceServer.fromJson";
            }

            public IceServer invoke(String str) {
                return IceServer.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (IceServer[]) deserializeObjectArray.toArray(new IceServer[0]);
    }

    public static int getDefaultPort() {
        Log.warn("Getting the value of IceServer.DefaultPort is deprecated. Get the value of IceServer.DefaultStunsPort/DefaultStunPort/DefaultTurnsPort/DefaultTurnPort instead.");
        return 3478;
    }

    public String getHost() {
        Holder holder = new Holder(null);
        IntegerHolder integerHolder = new IntegerHolder(-1);
        parseAddress(getUrl(), holder, integerHolder);
        String str = (String) holder.getValue();
        integerHolder.getValue();
        return str;
    }

    /* access modifiers changed from: package-private */
    public String getIPAddress() {
        String[] strArr;
        if (this.__ipAddress == null && (strArr = this.__ipAddresses) != null && ArrayExtensions.getLength((Object[]) strArr) > 0) {
            this.__ipAddress = this.__ipAddresses[0];
        }
        return this.__ipAddress;
    }

    /* access modifiers changed from: package-private */
    public String[] getIPAddresses() {
        String str;
        if (this.__ipAddresses == null && (str = this.__ipAddress) != null) {
            this.__ipAddresses = new String[]{str};
        }
        return this.__ipAddresses;
    }

    public boolean getIsSecure() {
        return getUrl().startsWith("stuns:") || getUrl().startsWith("turns:");
    }

    public boolean getIsStun() {
        return getUrl().startsWith("stun:") || getUrl().startsWith("stuns:");
    }

    public boolean getIsTcp() {
        return !getUrl().endsWith("?transport=udp");
    }

    public boolean getIsTurn() {
        return getUrl().startsWith("turn:") || getUrl().startsWith("turns:");
    }

    public boolean getIsUdp() {
        return !getUrl().endsWith("?transport=tcp");
    }

    public String getPassword() {
        return this._password;
    }

    public int getPort() {
        Holder holder = new Holder(null);
        IntegerHolder integerHolder = new IntegerHolder(-1);
        parseAddress(getUrl(), holder, integerHolder);
        String str = (String) holder.getValue();
        int value = integerHolder.getValue();
        if (value != -1) {
            return value;
        }
        if (!getIsSecure() && getIsTurn()) {
            return getDefaultTurnPort();
        }
        if (!getIsSecure() && getIsStun()) {
            return getDefaultStunPort();
        }
        if (!getIsSecure() || !getIsTurn()) {
            return getDefaultStunsPort();
        }
        return getDefaultTurnsPort();
    }

    public String getUrl() {
        return this._url;
    }

    public String getUsername() {
        return this._username;
    }

    private IceServer() {
    }

    public IceServer(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            throw new RuntimeException(new Exception("URL cannot be null or empty."));
        } else if (str.startsWith("turn:") || str.startsWith("turns:")) {
            throw new RuntimeException(new Exception("TURN servers require a username and password."));
        } else {
            if (!str.startsWith("stun:") && !str.startsWith("stuns:")) {
                str = StringExtensions.format("stun:{0}", (Object) str);
            }
            setUrl(!str.contains("?transport=") ? StringExtensions.format("{0}?transport=udp", (Object) str) : str);
        }
    }

    public IceServer(String str, String str2, String str3) {
        if (StringExtensions.isNullOrEmpty(str)) {
            throw new RuntimeException(new Exception("URL cannot be null or empty."));
        } else if (str2 == null) {
            throw new RuntimeException(new Exception("Username cannot be null."));
        } else if (str3 == null) {
            throw new RuntimeException(new Exception("Password cannot be null."));
        } else if (str.startsWith("stun:") || str.startsWith("stuns:")) {
            throw new RuntimeException(new Exception("STUN servers do not have a username or password."));
        } else {
            if (!str.startsWith("turn:") && !str.startsWith("turns:")) {
                str = StringExtensions.format("turn:{0}", (Object) str);
            }
            setUrl(str);
            setUsername(str2);
            setPassword(str3);
        }
    }

    private static boolean parseAddress(String str, Holder<String> holder, IntegerHolder integerHolder) {
        holder.setValue(null);
        integerHolder.setValue(-1);
        if (str == null) {
            return false;
        }
        String str2 = StringExtensions.split(str, new char[]{'?'})[0];
        if (str2.startsWith("stun:") || str2.startsWith("stuns:") || str2.startsWith("turn:") || str2.startsWith("turns:")) {
            str2 = str2.substring(StringExtensions.indexOf(str2, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR) + 1);
        }
        if (str2.startsWith("[")) {
            String[] split = StringExtensions.split(StringExtensions.substring(str2, 1, StringExtensions.getLength(str2) - 1), new char[]{']'});
            holder.setValue(split[0]);
            if (ArrayExtensions.getLength((Object[]) split) > 1 && split[1].startsWith(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) {
                integerHolder.setValue(parsePort(split[1].substring(1)));
            }
        } else {
            String[] split2 = StringExtensions.split(str2, new char[]{':'});
            if (ArrayExtensions.getLength((Object[]) split2) == 1) {
                holder.setValue(str2);
            } else if (ArrayExtensions.getLength((Object[]) split2) == 2) {
                holder.setValue(split2[0]);
                integerHolder.setValue(parsePort(split2[1]));
            } else if (ArrayExtensions.getLength((Object[]) split2) > 2) {
                holder.setValue(str2);
            }
        }
        return true;
    }

    private static int parsePort(String str) {
        IntegerHolder integerHolder = new IntegerHolder(-1);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str, integerHolder);
        int value = integerHolder.getValue();
        if (tryParseIntegerValue) {
            return value;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Could not parse port when trying to interpret uri in the IceServer constructor. Please verify that the port was given as an integer. Tried parsing value {0}.", (Object) str)));
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress(String str) {
        this.__ipAddress = str;
    }

    /* access modifiers changed from: package-private */
    public void setIPAddresses(String[] strArr) {
        this.__ipAddresses = strArr;
    }

    /* access modifiers changed from: private */
    public void setPassword(String str) {
        this._password = str;
    }

    /* access modifiers changed from: private */
    public void setUrl(String str) {
        this._url = str;
    }

    /* access modifiers changed from: private */
    public void setUsername(String str) {
        this._username = str;
    }

    public static String toJson(IceServer iceServer) {
        return JsonSerializer.serializeObject(iceServer, new IAction2<IceServer, HashMap<String, String>>(iceServer) {
            final /* synthetic */ IceServer val$iceServer;

            {
                this.val$iceServer = r1;
            }

            public void invoke(IceServer iceServer, HashMap<String, String> hashMap) {
                if (this.val$iceServer.getUrl() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "url", JsonSerializer.serializeString(this.val$iceServer.getUrl()));
                }
                if (this.val$iceServer.getUsername() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Attributes.USERNAME, JsonSerializer.serializeString(this.val$iceServer.getUsername()));
                }
                if (this.val$iceServer.getPassword() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "password", JsonSerializer.serializeString(this.val$iceServer.getPassword()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(IceServer[] iceServerArr) {
        return JsonSerializer.serializeObjectArray(iceServerArr, new IFunctionDelegate1<IceServer, String>() {
            public String getId() {
                return "fm.liveswitch.IceServer.toJson";
            }

            public String invoke(IceServer iceServer) {
                return IceServer.toJson(iceServer);
            }
        });
    }
}
