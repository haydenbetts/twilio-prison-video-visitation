package fm.liveswitch;

class RtpUnknownHeaderExtensionElement extends RtpHeaderExtensionElement {
    private int __length;
    private DataBuffer _payload;

    /* access modifiers changed from: package-private */
    public void fillBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        if (Global.equals(super.getForm(), RtpHeaderExtensionForm.OneByte)) {
            dataBuffer.write4(super.getId(), i, 0);
            dataBuffer.write4(this.__length - 1, i, 4);
        } else {
            dataBuffer.write8(super.getId(), i);
            dataBuffer.write8(this.__length - 1, i + 1);
        }
        dataBuffer.append(getPayload());
        integerHolder.setValue(getLength());
    }

    public int getLength() {
        return this.__length + (Global.equals(super.getForm(), RtpHeaderExtensionForm.OneByte) ? 1 : 2);
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public static RtpUnknownHeaderExtensionElement parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder) {
        int i2 = Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.OneByte) ? 1 : 2;
        int read4 = Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.OneByte) ? dataBuffer.read4(i, 0) : dataBuffer.read8(i);
        int read42 = Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.OneByte) ? dataBuffer.read4(i, 4) + 1 : dataBuffer.read8(i + 1);
        RtpUnknownHeaderExtensionElement rtpUnknownHeaderExtensionElement = new RtpUnknownHeaderExtensionElement(rtpHeaderExtensionForm, read4, read42, dataBuffer.subset(i + i2, read42));
        Log.debug(StringExtensions.format("RTP Header Extension processing: encountered not registered RTP extension header type {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(read4))));
        integerHolder.setValue(i2 == 1 ? read42 + 1 : read42 + 2);
        return rtpUnknownHeaderExtensionElement;
    }

    public RtpUnknownHeaderExtensionElement(RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, int i2, DataBuffer dataBuffer) {
        super.setType(RtpHeaderExtensionType.Unknown);
        super.setId(i);
        super.setForm(rtpHeaderExtensionForm);
        this.__length = i2;
    }

    private void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }
}
