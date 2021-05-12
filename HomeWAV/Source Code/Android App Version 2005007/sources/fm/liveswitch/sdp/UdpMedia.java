package fm.liveswitch.sdp;

public class UdpMedia extends Media {
    public static String getUdpTransportProtocol() {
        return "udp";
    }

    public UdpMedia(String str, int i, String str2) {
        super(str, i, getUdpTransportProtocol(), str2);
    }
}
