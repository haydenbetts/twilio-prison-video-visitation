package fm.liveswitch;

abstract class RtpHeaderTextDescription extends RtpHeaderDataDescription {
    public String getText() {
        return Utf8.decode(getData());
    }

    public RtpHeaderTextDescription(RtpHeaderExtensionType rtpHeaderExtensionType, RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        super(rtpHeaderExtensionType, rtpHeaderExtensionForm, i, bArr);
    }

    public RtpHeaderTextDescription(RtpHeaderExtensionType rtpHeaderExtensionType, RtpHeaderExtensionForm rtpHeaderExtensionForm, String str) {
        super(rtpHeaderExtensionType, rtpHeaderExtensionForm, -1, Utf8.encode(str));
    }
}
