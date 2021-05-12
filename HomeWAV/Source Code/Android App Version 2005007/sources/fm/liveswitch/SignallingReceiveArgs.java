package fm.liveswitch;

abstract class SignallingReceiveArgs extends SignallingSuccessArgs {
    SignallingConnectionType __connectionType;
    byte[] __dataBytes;
    String __dataJson;
    private int _reconnectAfter;

    public abstract SignallingRemoteClient getRemoteClient();

    public SignallingConnectionType getConnectionType() {
        return this.__connectionType;
    }

    public byte[] getDataBytes() {
        byte[] bArr = this.__dataBytes;
        String str = this.__dataJson;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = null;
        if (str != null) {
            Holder holder = new Holder(bArr);
            boolean booleanValue = Base64.tryDecode(JsonSerializer.deserializeString(str), holder).booleanValue();
            byte[] bArr3 = (byte[]) holder.getValue();
            if (booleanValue) {
                bArr2 = bArr3;
            }
            this.__dataBytes = bArr2;
        }
        return bArr2;
    }

    public String getDataJson() {
        String str = this.__dataJson;
        byte[] bArr = this.__dataBytes;
        if (str != null) {
            return str;
        }
        if (bArr == null) {
            return null;
        }
        String serializeString = JsonSerializer.serializeString(Base64.encode(bArr));
        this.__dataJson = serializeString;
        return serializeString;
    }

    public boolean getIsBinary() {
        return getDataBytes() != null;
    }

    public int getReconnectAfter() {
        return this._reconnectAfter;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName()));
    }

    public void setReconnectAfter(int i) {
        this._reconnectAfter = i;
    }

    public SignallingReceiveArgs(String str, byte[] bArr, SignallingConnectionType signallingConnectionType, int i) {
        this.__dataJson = str;
        this.__dataBytes = bArr;
        this.__connectionType = signallingConnectionType;
        setReconnectAfter(i);
    }
}
