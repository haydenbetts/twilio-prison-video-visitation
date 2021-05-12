package fm.liveswitch.sdp;

public abstract class BandwidthType {
    public static String getApplicationSpecific() {
        return "AS";
    }

    public static String getConferenceTotal() {
        return "CT";
    }

    public static String getRtcpReceivers() {
        return "RR";
    }

    public static String getRtcpSenders() {
        return "RS";
    }

    public static String getTransportIndependentApplicationSpecificMaximum() {
        return "TIAS";
    }
}
