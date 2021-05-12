package fm.liveswitch.sdp.sctp;

import fm.liveswitch.Global;

public class Media extends fm.liveswitch.sdp.Media {
    public static String getDtlsSctpTransportProtocol() {
        return "DTLS/SCTP";
    }

    public static String getSctpDtlsTransportProtocol() {
        return "SCTP/DTLS";
    }

    public static String getSctpTransportProtocol() {
        return "SCTP";
    }

    public static String getTcpDtlsSctpTransportProtocol() {
        return "TCP/DTLS/SCTP";
    }

    public static String getUdpDtlsSctpTransportProtocol() {
        return "UDP/DTLS/SCTP";
    }

    public static String getWebRtcDatachannelAssociationUsage() {
        return "webrtc-datachannel";
    }

    public static boolean isSupported(String str) {
        return Global.equals(str, getSctpTransportProtocol()) || Global.equals(str, getSctpDtlsTransportProtocol()) || Global.equals(str, getDtlsSctpTransportProtocol()) || Global.equals(str, getUdpDtlsSctpTransportProtocol()) || Global.equals(str, getTcpDtlsSctpTransportProtocol());
    }

    public Media(String str, int i, String str2, String str3) {
        super(str, i, str2);
        if (str3 != null) {
            super.setFormatDescription(str3);
            return;
        }
        throw new RuntimeException(new Exception("associationUsage cannot be null."));
    }

    public static boolean supportsEncryption(String str) {
        return Global.equals(str, getUdpDtlsSctpTransportProtocol()) || Global.equals(str, getDtlsSctpTransportProtocol()) || Global.equals(str, getTcpDtlsSctpTransportProtocol());
    }
}
