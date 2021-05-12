package fm.liveswitch;

public class ProxyAuthCheckResult {
    private boolean _needsAuthentication;
    private String[] _proxyIPAddresses;

    public boolean getNeedsAuthentication() {
        return this._needsAuthentication;
    }

    public String getProxyIPAddress() {
        String[] proxyIPAddresses = getProxyIPAddresses();
        if (ArrayExtensions.getLength((Object[]) proxyIPAddresses) > 0) {
            return proxyIPAddresses[0];
        }
        return null;
    }

    public String[] getProxyIPAddresses() {
        return this._proxyIPAddresses;
    }

    public ProxyAuthCheckResult(boolean z, String[] strArr) {
        setNeedsAuthentication(z);
        setProxyIPAddresses(strArr);
    }

    public void setNeedsAuthentication(boolean z) {
        this._needsAuthentication = z;
    }

    public void setProxyIPAddresses(String[] strArr) {
        this._proxyIPAddresses = strArr;
    }
}
