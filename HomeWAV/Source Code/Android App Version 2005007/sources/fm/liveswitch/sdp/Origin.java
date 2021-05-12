package fm.liveswitch.sdp;

import fm.liveswitch.LockedRandomizer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.UnixTimestamp;

public class Origin {
    private String _addressType;
    private String _networkType;
    private long _sessionId;
    private long _sessionVersion;
    private String _unicastAddress;
    private String _username;

    private long generateSessionId() {
        return LockedRandomizer.nextLong();
    }

    private long generateSessionVersion() {
        return UnixTimestamp.getUtcNow();
    }

    public String getAddressType() {
        return this._addressType;
    }

    public String getNetworkType() {
        return this._networkType;
    }

    public long getSessionId() {
        return this._sessionId;
    }

    public long getSessionVersion() {
        return this._sessionVersion;
    }

    public String getUnicastAddress() {
        return this._unicastAddress;
    }

    public String getUsername() {
        return this._username;
    }

    public Origin(String str) {
        this(str, "-");
    }

    public Origin(String str, String str2) {
        if (str != null) {
            setUsername(str2);
            setSessionId(generateSessionId());
            setSessionVersion(generateSessionVersion());
            setNetworkType(NetworkType.getInternet());
            setAddressType(AddressType.getAddressTypeForAddress(str));
            setUnicastAddress(str);
            return;
        }
        throw new RuntimeException(new Exception("unicastAddress cannot be null."));
    }

    public static Origin parse(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        Origin origin = new Origin(split[5], split[0]);
        origin.setSessionId(ParseAssistant.parseLongValue(split[1]));
        origin.setSessionVersion(ParseAssistant.parseLongValue(split[2]));
        origin.setNetworkType(split[3]);
        origin.setAddressType(split[4]);
        return origin;
    }

    public void setAddressType(String str) {
        this._addressType = str;
    }

    public void setNetworkType(String str) {
        this._networkType = str;
    }

    public void setSessionId(long j) {
        this._sessionId = j;
    }

    public void setSessionVersion(long j) {
        this._sessionVersion = j;
    }

    public void setUnicastAddress(String str) {
        this._unicastAddress = str;
    }

    public void setUsername(String str) {
        this._username = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getUsername());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getSessionId())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getSessionVersion())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getNetworkType());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getAddressType());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getUnicastAddress());
        return sb.toString();
    }
}
