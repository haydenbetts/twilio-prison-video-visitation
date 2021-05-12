package fm.liveswitch;

class RtpHeaderAbsSendTime extends RtpHeaderDataDescription {
    static final String FormalName = "http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time";

    public int getAbsSendTime24() {
        return Binary.fromBytes24(getData(), 0, false);
    }

    private static int ntpTimestampToAbsSendTime24(long j) {
        return (int) (BitAssistant.rightShiftLong(j, 14) & 16777215);
    }

    public static RtpHeaderAbsSendTime parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder) {
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        Holder holder = new Holder(null);
        RtpHeaderDataDescription.parseBytes(dataBuffer, i, rtpHeaderExtensionForm, integerHolder, integerHolder2, holder);
        return new RtpHeaderAbsSendTime(rtpHeaderExtensionForm, integerHolder2.getValue(), (byte[]) holder.getValue());
    }

    public RtpHeaderAbsSendTime(RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        super(RtpHeaderExtensionType.AbsSendTime, rtpHeaderExtensionForm, i, bArr);
    }

    public RtpHeaderAbsSendTime(RtpHeaderExtensionForm rtpHeaderExtensionForm, long j) {
        super(RtpHeaderExtensionType.AbsSendTime, rtpHeaderExtensionForm, -1, Binary.toBytes24(ntpTimestampToAbsSendTime24(j), false));
    }
}
