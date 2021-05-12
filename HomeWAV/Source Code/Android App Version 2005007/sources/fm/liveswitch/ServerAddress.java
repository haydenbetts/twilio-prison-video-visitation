package fm.liveswitch;

public class ServerAddress extends TransportAddress {
    private String _publicIPAddress;

    public String getPublicIPAddress() {
        return this._publicIPAddress;
    }

    public ServerAddress(String str, int i) {
        this(str, i, (String) null);
    }

    public ServerAddress(String str, int i, String str2) {
        super(str, i);
        setPublicIPAddress(str2);
    }

    private void setPublicIPAddress(String str) {
        this._publicIPAddress = str;
    }
}
