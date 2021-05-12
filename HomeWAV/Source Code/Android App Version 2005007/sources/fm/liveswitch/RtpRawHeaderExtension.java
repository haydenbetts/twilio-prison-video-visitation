package fm.liveswitch;

class RtpRawHeaderExtension implements IRtpHeaderExtension {
    private int _appBits;
    private RtpHeaderExtensionForm _form;
    private byte[] _id;
    private byte[] _payload;

    public void fillBuffer(DataBuffer dataBuffer, int i) {
        dataBuffer.writeBytes(getPayload(), i);
    }

    public int getAppBits() {
        return this._appBits;
    }

    public RtpHeaderExtensionForm getForm() {
        return this._form;
    }

    public byte[] getId() {
        return this._id;
    }

    public int getLength() {
        return ArrayExtensions.getLength(getPayload()) / 4;
    }

    public byte[] getPayload() {
        return this._payload;
    }

    public RtpRawHeaderExtension(byte[] bArr, byte[] bArr2) {
        setId(bArr);
        setPayload(bArr2);
        if (bArr[0] == 16) {
            setForm(RtpHeaderExtensionForm.TwoByte);
            setAppBits(bArr[1]);
            return;
        }
        setForm(RtpHeaderExtensionForm.OneByte);
        setAppBits(0);
    }

    private void setAppBits(int i) {
        this._appBits = i;
    }

    private void setForm(RtpHeaderExtensionForm rtpHeaderExtensionForm) {
        this._form = rtpHeaderExtensionForm;
    }

    private void setId(byte[] bArr) {
        this._id = bArr;
    }

    private void setPayload(byte[] bArr) {
        this._payload = bArr;
    }
}
