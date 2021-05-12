package fm.liveswitch;

class SrtpProtectionParameters {
    private DataBuffer _key;
    private int _protectionProfileCode;
    private String _protectionProfileString;
    private DataBuffer _salt;

    public DataBuffer getKey() {
        return this._key;
    }

    public int getProtectionProfileCode() {
        return this._protectionProfileCode;
    }

    public String getProtectionProfileString() {
        return this._protectionProfileString;
    }

    public DataBuffer getSalt() {
        return this._salt;
    }

    private void setKey(DataBuffer dataBuffer) {
        this._key = dataBuffer;
    }

    private void setProtectionProfileCode(int i) {
        this._protectionProfileCode = i;
    }

    private void setProtectionProfileString(String str) {
        this._protectionProfileString = str;
    }

    private void setSalt(DataBuffer dataBuffer) {
        this._salt = dataBuffer;
    }

    public SrtpProtectionParameters(int i, DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        setProtectionProfileCode(i);
        setProtectionProfileString(SrtpProtectionProfile.protectionProfileStringFromCode(i));
        setKey(dataBuffer);
        setSalt(dataBuffer2);
    }

    public SrtpProtectionParameters(String str, DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        setProtectionProfileString(str);
        setProtectionProfileCode(SrtpProtectionProfile.protectionProfileCodeFromString(str));
        setKey(dataBuffer);
        setSalt(dataBuffer2);
    }
}
