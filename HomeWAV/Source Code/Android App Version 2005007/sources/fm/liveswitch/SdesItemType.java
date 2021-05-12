package fm.liveswitch;

public class SdesItemType {
    public static byte getCanonicalName() {
        return 1;
    }

    public static byte getEmailAddress() {
        return 3;
    }

    public static byte getLocation() {
        return 5;
    }

    public static byte getMid() {
        return 15;
    }

    public static byte getNotice() {
        return 7;
    }

    public static byte getNull() {
        return 0;
    }

    public static byte getPhoneNumber() {
        return 4;
    }

    public static byte getPrivateExtensions() {
        return 8;
    }

    public static byte getRepairedRtpStreamId() {
        return 13;
    }

    public static byte getRtpStreamId() {
        return 12;
    }

    public static byte getToolName() {
        return 6;
    }

    public static byte getUserName() {
        return 2;
    }
}
