package fm.liveswitch;

class DatamessageheaderType {
    public static byte getConnectionId() {
        return 3;
    }

    public static byte getDeliveryAttempts() {
        return 2;
    }

    public static byte getTimeToLive() {
        return 1;
    }
}
