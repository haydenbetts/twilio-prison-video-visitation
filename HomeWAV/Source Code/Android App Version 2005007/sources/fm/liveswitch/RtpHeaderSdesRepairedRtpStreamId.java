package fm.liveswitch;

class RtpHeaderSdesRepairedRtpStreamId extends RtpHeaderTextDescription {
    static final String FormalName = "urn:ietf:params:rtp-hdrext:sdes:repaired-rtp-stream-id";

    public static RtpHeaderSdesRepairedRtpStreamId parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder) {
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        Holder holder = new Holder(null);
        RtpHeaderDataDescription.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder, integerHolder2, holder);
        return new RtpHeaderSdesRepairedRtpStreamId(rtpHeaderExtensionForm, integerHolder2.getValue(), (byte[]) holder.getValue());
    }

    public RtpHeaderSdesRepairedRtpStreamId(RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        super(RtpHeaderExtensionType.SdesRepairedRtpStreamId, rtpHeaderExtensionForm, i, bArr);
    }

    public RtpHeaderSdesRepairedRtpStreamId(RtpHeaderExtensionForm rtpHeaderExtensionForm, String str) {
        super(RtpHeaderExtensionType.SdesRepairedRtpStreamId, rtpHeaderExtensionForm, str);
    }
}
