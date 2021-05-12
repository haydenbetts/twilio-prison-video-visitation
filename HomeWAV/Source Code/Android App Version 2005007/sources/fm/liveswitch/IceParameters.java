package fm.liveswitch;

public class IceParameters {
    private String _password;
    private String _usernameFragment;

    /* access modifiers changed from: package-private */
    public boolean equals(IceParameters iceParameters) {
        String str = null;
        String usernameFragment = iceParameters == null ? null : iceParameters.getUsernameFragment();
        if (iceParameters != null) {
            str = iceParameters.getPassword();
        }
        return (getUsernameFragment() == null || usernameFragment == null || getPassword() == null || str == null || !Global.equals(getUsernameFragment(), usernameFragment) || !Global.equals(getPassword(), str)) ? false : true;
    }

    public String getPassword() {
        return this._password;
    }

    public String getUsernameFragment() {
        return this._usernameFragment;
    }

    public IceParameters(String str, String str2) {
        setUsernameFragment(str);
        setPassword(str2);
    }

    public void setPassword(String str) {
        this._password = str;
    }

    public void setUsernameFragment(String str) {
        this._usernameFragment = str;
    }
}
