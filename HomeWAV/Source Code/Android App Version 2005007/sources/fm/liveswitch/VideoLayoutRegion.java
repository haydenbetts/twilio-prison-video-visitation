package fm.liveswitch;

public class VideoLayoutRegion {
    private LayoutFrame _bounds;
    private String _clientId;
    private String[] _clientRoles;
    private String _clientTag;
    private String _connectionId;
    private String _connectionTag;
    private String _deviceAlias;
    private String _deviceId;
    private LayoutFrame _frame;
    private String _userAlias;
    private String _userId;

    public LayoutFrame getBounds() {
        return this._bounds;
    }

    public String getClientId() {
        return this._clientId;
    }

    public String[] getClientRoles() {
        return this._clientRoles;
    }

    public String getClientTag() {
        return this._clientTag;
    }

    public String getConnectionId() {
        return this._connectionId;
    }

    public String getConnectionTag() {
        return this._connectionTag;
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public LayoutFrame getFrame() {
        return this._frame;
    }

    public String getUserAlias() {
        return this._userAlias;
    }

    public String getUserId() {
        return this._userId;
    }

    private void setBounds(LayoutFrame layoutFrame) {
        this._bounds = layoutFrame;
    }

    private void setClientId(String str) {
        this._clientId = str;
    }

    private void setClientRoles(String[] strArr) {
        this._clientRoles = strArr;
    }

    private void setClientTag(String str) {
        this._clientTag = str;
    }

    private void setConnectionId(String str) {
        this._connectionId = str;
    }

    private void setConnectionTag(String str) {
        this._connectionTag = str;
    }

    private void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    private void setDeviceId(String str) {
        this._deviceId = str;
    }

    private void setFrame(LayoutFrame layoutFrame) {
        this._frame = layoutFrame;
    }

    private void setUserAlias(String str) {
        this._userAlias = str;
    }

    private void setUserId(String str) {
        this._userId = str;
    }

    public VideoLayoutRegion(LayoutFrame layoutFrame, LayoutFrame layoutFrame2, String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, String str8) {
        setFrame(layoutFrame);
        setBounds(layoutFrame2);
        setUserId(str);
        setUserAlias(str2);
        setDeviceId(str3);
        setDeviceAlias(str4);
        setClientId(str5);
        setClientTag(str6);
        setClientRoles(strArr);
        setConnectionId(str7);
        setConnectionTag(str8);
    }
}
