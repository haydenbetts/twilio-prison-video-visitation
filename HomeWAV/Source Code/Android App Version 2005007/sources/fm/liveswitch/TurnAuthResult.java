package fm.liveswitch;

public class TurnAuthResult {
    private byte[] _longTermKeyBytes;
    private String _password;

    public static TurnAuthResult fromLongTermKeyBytes(byte[] bArr) {
        TurnAuthResult turnAuthResult = new TurnAuthResult();
        turnAuthResult.setLongTermKeyBytes(bArr);
        return turnAuthResult;
    }

    public static TurnAuthResult fromPassword(String str) {
        TurnAuthResult turnAuthResult = new TurnAuthResult();
        turnAuthResult.setPassword(str);
        return turnAuthResult;
    }

    public byte[] getLongTermKeyBytes() {
        return this._longTermKeyBytes;
    }

    public String getPassword() {
        return this._password;
    }

    private void setLongTermKeyBytes(byte[] bArr) {
        this._longTermKeyBytes = bArr;
    }

    private void setPassword(String str) {
        this._password = str;
    }

    private TurnAuthResult() {
    }
}
