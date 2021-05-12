package fm.liveswitch;

class SignallingServiceSuccessArgs extends SignallingSuccessArgs {
    private String __channel;
    private byte[] __dataBytes;
    private String __dataJson;

    public String getChannel() {
        return this.__channel;
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

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName()));
    }

    public SignallingServiceSuccessArgs(String str, String str2, byte[] bArr) {
        this.__channel = str;
        this.__dataJson = str2;
        this.__dataBytes = bArr;
    }
}
