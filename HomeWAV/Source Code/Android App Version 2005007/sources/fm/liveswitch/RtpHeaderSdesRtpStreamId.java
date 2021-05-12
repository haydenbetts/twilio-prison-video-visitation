package fm.liveswitch;

class RtpHeaderSdesRtpStreamId extends RtpHeaderTextDescription {
    static final String FormalName = "urn:ietf:params:rtp-hdrext:sdes:rtp-stream-id";

    public static RtpHeaderSdesRtpStreamId parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder) {
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        Holder holder = new Holder(null);
        RtpHeaderDataDescription.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder, integerHolder2, holder);
        return new RtpHeaderSdesRtpStreamId(rtpHeaderExtensionForm, integerHolder2.getValue(), (byte[]) holder.getValue());
    }

    public RtpHeaderSdesRtpStreamId(RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        super(RtpHeaderExtensionType.SdesRtpStreamId, rtpHeaderExtensionForm, i, bArr);
    }

    public RtpHeaderSdesRtpStreamId(RtpHeaderExtensionForm rtpHeaderExtensionForm, String str) {
        super(RtpHeaderExtensionType.SdesRtpStreamId, rtpHeaderExtensionForm, str);
    }
}
