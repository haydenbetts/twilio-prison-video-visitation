package fm.liveswitch;

public abstract class EventOrigin {
    public static String getClient() {
        return "client";
    }

    public static String getGateway() {
        return "gateway";
    }

    public static String getMediaServer() {
        return "mediaserver";
    }

    public static String getSipConnector() {
        return "sipconnector";
    }
}
