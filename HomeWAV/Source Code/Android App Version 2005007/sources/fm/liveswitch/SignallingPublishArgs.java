package fm.liveswitch;

class SignallingPublishArgs extends SignallingInputArgs {
    String __channel;
    byte[] __dataBytes;
    String __dataJson;
    private IAction1<SignallingPublishFailureArgs> _onFailure;
    private IAction1<SignallingPublishSuccessArgs> _onSuccess;

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

    public IAction1<SignallingPublishFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingPublishSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public NullableBoolean getReturnData() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getReturnDataExtensionName()));
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName()));
    }

    public void setChannel(String str) {
        Holder holder = new Holder(null);
        boolean validateChannel = SignallingExtensible.validateChannel(str, holder);
        String str2 = (String) holder.getValue();
        if (validateChannel) {
            this.__channel = str;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid channel. {0}", (Object) str2)));
    }

    public void setDataBytes(byte[] bArr) {
        this.__dataJson = null;
        this.__dataBytes = bArr;
    }

    public void setDataJson(String str) {
        if (str == null || JsonSerializer.isValidJson(str)) {
            this.__dataJson = str;
            this.__dataBytes = null;
            return;
        }
        throw new RuntimeException(new Exception("The value is not valid JSON."));
    }

    public void setOnFailure(IAction1<SignallingPublishFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingPublishSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setReturnData(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getReturnDataExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
    }

    public void setTag(String str) {
        super.setExtensionValueJson(SignallingExtensible.getTagExtensionName(), JsonSerializer.serializeString(str), false);
    }

    public SignallingPublishArgs() {
    }

    public SignallingPublishArgs(String str, byte[] bArr) {
        this(str, bArr, (String) null);
    }

    public SignallingPublishArgs(String str, byte[] bArr, String str2) {
        setChannel(str);
        setDataBytes(bArr);
        setTag(str2);
    }

    public SignallingPublishArgs(String str, String str2) {
        this(str, str2, (String) null);
    }

    public SignallingPublishArgs(String str, String str2, String str3) {
        setChannel(str);
        setDataJson(str2);
        setTag(str3);
    }
}
