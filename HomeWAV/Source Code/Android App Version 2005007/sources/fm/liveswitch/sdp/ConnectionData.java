package fm.liveswitch.sdp;

import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;

public class ConnectionData {
    private String _addressType;
    private String _connectionAddress;
    private String _networkType;

    public ConnectionData(String str) {
        if (str != null) {
            update(str);
            return;
        }
        throw new RuntimeException(new Exception("connectionAddress cannot be null."));
    }

    public String getAddressType() {
        return this._addressType;
    }

    public String getConnectionAddress() {
        return this._connectionAddress;
    }

    public String getNetworkType() {
        return this._networkType;
    }

    public static ConnectionData parse(String str) {
        String[] split = StringExtensions.split(str.substring(2), new char[]{' '});
        ConnectionData connectionData = new ConnectionData(split[2]);
        connectionData.setNetworkType(split[0]);
        connectionData.setAddressType(split[1]);
        return connectionData;
    }

    public void setAddressType(String str) {
        this._addressType = str;
    }

    public void setConnectionAddress(String str) {
        this._connectionAddress = str;
    }

    public void setNetworkType(String str) {
        this._networkType = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "c=");
        StringBuilderExtensions.append(sb, getNetworkType());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getAddressType());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getConnectionAddress());
        return sb.toString();
    }

    public void update(String str) {
        setNetworkType(NetworkType.getInternet());
        setAddressType(AddressType.getAddressTypeForAddress(str));
        setConnectionAddress(str);
    }
}
