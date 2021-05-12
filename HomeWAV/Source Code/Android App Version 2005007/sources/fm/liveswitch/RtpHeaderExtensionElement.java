package fm.liveswitch;

abstract class RtpHeaderExtensionElement {
    private RtpHeaderExtensionForm _form;
    private int _id;
    private RtpHeaderExtensionType _type;

    /* access modifiers changed from: package-private */
    public abstract void fillBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder);

    public int getLength() {
        return 1;
    }

    public RtpHeaderExtensionForm getForm() {
        return this._form;
    }

    public int getId() {
        return this._id;
    }

    public RtpHeaderExtensionType getType() {
        return this._type;
    }

    static RtpHeaderExtensionElement parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry, IntegerHolder integerHolder) {
        int read4 = Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.OneByte) ? dataBuffer.read4(i, 0) : dataBuffer.read8(i);
        if (read4 == 0) {
            integerHolder.setValue(1);
            return null;
        }
        RtpHeaderExtensionType registeredTypeForRtpHeaderExtensionId = rtpHeaderExtensionRegistry.registeredTypeForRtpHeaderExtensionId(read4);
        if (registeredTypeForRtpHeaderExtensionId == RtpHeaderExtensionType.AbsSendTime) {
            return RtpHeaderAbsSendTime.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder);
        }
        if (registeredTypeForRtpHeaderExtensionId == RtpHeaderExtensionType.SdesRtpStreamId) {
            return RtpHeaderSdesRtpStreamId.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder);
        }
        if (registeredTypeForRtpHeaderExtensionId == RtpHeaderExtensionType.SdesRepairedRtpStreamId) {
            return RtpHeaderSdesRepairedRtpStreamId.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder);
        }
        if (registeredTypeForRtpHeaderExtensionId == RtpHeaderExtensionType.SdesMid) {
            return RtpHeaderSdesMid.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder);
        }
        if (registeredTypeForRtpHeaderExtensionId == RtpHeaderExtensionType.Unknown) {
            return RtpUnknownHeaderExtensionElement.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder);
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Error parsing an RTP Header Extension element of type {0}.", (Object) registeredTypeForRtpHeaderExtensionId.toString())));
    }

    protected RtpHeaderExtensionElement() {
    }

    /* access modifiers changed from: protected */
    public void setForm(RtpHeaderExtensionForm rtpHeaderExtensionForm) {
        this._form = rtpHeaderExtensionForm;
    }

    public void setId(int i) {
        this._id = i;
    }

    /* access modifiers changed from: protected */
    public void setType(RtpHeaderExtensionType rtpHeaderExtensionType) {
        this._type = rtpHeaderExtensionType;
    }
}
