package fm.liveswitch.sdp.ice;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;

public class RemoteCandidate {
    private int _componentId;
    private String _connectionAddress;
    private int _port;

    public int getComponentId() {
        return this._componentId;
    }

    public String getConnectionAddress() {
        return this._connectionAddress;
    }

    public int getPort() {
        return this._port;
    }

    public static RemoteCandidate parse(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        return new RemoteCandidate(ParseAssistant.parseIntegerValue(split[0]), split[1], ParseAssistant.parseIntegerValue(split[2]));
    }

    public RemoteCandidate(int i, String str, int i2) {
        if (str != null) {
            setComponentId(i);
            setConnectionAddress(str);
            setPort(i2);
            return;
        }
        throw new RuntimeException(new Exception("connectionAddress cannot be null."));
    }

    private void setComponentId(int i) {
        this._componentId = i;
    }

    private void setConnectionAddress(String str) {
        this._connectionAddress = str;
    }

    private void setPort(int i) {
        this._port = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getComponentId())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getConnectionAddress());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPort())));
        return sb.toString();
    }
}
