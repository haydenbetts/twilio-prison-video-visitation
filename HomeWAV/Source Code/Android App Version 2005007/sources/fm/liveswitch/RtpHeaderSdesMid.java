package fm.liveswitch;

class RtpHeaderSdesMid extends RtpHeaderTextDescription {
    static final String FormalName = "urn:ietf:params:rtp-hdrext:sdes:mid";

    public static RtpHeaderSdesMid parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder) {
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        Holder holder = new Holder(null);
        RtpHeaderDataDescription.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder, integerHolder2, holder);
        return new RtpHeaderSdesMid(rtpHeaderExtensionForm, integerHolder2.getValue(), (byte[]) holder.getValue());
    }

    public RtpHeaderSdesMid(RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        super(RtpHeaderExtensionType.SdesMid, rtpHeaderExtensionForm, i, bArr);
    }

    public RtpHeaderSdesMid(RtpHeaderExtensionForm rtpHeaderExtensionForm, String str) {
        super(RtpHeaderExtensionType.SdesMid, rtpHeaderExtensionForm, str);
    }
}
