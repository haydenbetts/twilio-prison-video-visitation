package fm.liveswitch.sdp.ice;

public abstract class CandidateType {
    public static String getHost() {
        return "host";
    }

    public static String getPeerReflexive() {
        return "prflx";
    }

    public static String getRelayed() {
        return "relay";
    }

    public static String getServerReflexive() {
        return "srflx";
    }
}
