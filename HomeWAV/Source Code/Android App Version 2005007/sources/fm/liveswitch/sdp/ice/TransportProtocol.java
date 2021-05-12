package fm.liveswitch.sdp.ice;

public abstract class TransportProtocol {
    public static String getTcp() {
        return "tcp";
    }

    public static String getUdp() {
        return "udp";
    }
}
