package fm.liveswitch;

public class TurnAuthArgs {
    private TurnAuthOperation _operation;
    private String _realm;
    private String _username;

    public TurnAuthOperation getOperation() {
        return this._operation;
    }

    public String getRealm() {
        return this._realm;
    }

    public String getUsername() {
        return this._username;
    }

    private void setOperation(TurnAuthOperation turnAuthOperation) {
        this._operation = turnAuthOperation;
    }

    private void setRealm(String str) {
        this._realm = str;
    }

    private void setUsername(String str) {
        this._username = str;
    }

    public TurnAuthArgs(String str, String str2, TurnAuthOperation turnAuthOperation) {
        setUsername(str);
        setRealm(str2);
        setOperation(turnAuthOperation);
    }
}
