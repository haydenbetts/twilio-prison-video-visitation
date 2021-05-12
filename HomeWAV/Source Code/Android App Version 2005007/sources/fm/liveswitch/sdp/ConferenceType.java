package fm.liveswitch.sdp;

public abstract class ConferenceType {
    public static String getBroadcast() {
        return "broadcast";
    }

    public static String getH332() {
        return "H332";
    }

    public static String getMeeting() {
        return "meeting";
    }

    public static String getModerated() {
        return "moderated";
    }

    public static String getTest() {
        return "test";
    }
}
