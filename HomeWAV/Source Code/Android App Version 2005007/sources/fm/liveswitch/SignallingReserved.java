package fm.liveswitch;

class SignallingReserved {
    private static String __clientChannelPrefix = "/fm/client";
    private static String __deviceChannelPrefix = "/fm/device";
    private static String __userChannelPrefix = "/fm/user";

    public static String getClientChannel(String str) {
        return StringExtensions.format("{0}/{1}", getClientChannelPrefix(), str);
    }

    public static String getClientChannelPrefix() {
        return __clientChannelPrefix;
    }

    public static String getDeviceChannel(String str) {
        return StringExtensions.format("{0}/{1}", getDeviceChannelPrefix(), str);
    }

    public static String getDeviceChannelPrefix() {
        return __deviceChannelPrefix;
    }

    public static String getUserChannel(String str) {
        return StringExtensions.format("{0}/{1}", getUserChannelPrefix(), str);
    }

    public static String getUserChannelPrefix() {
        return __userChannelPrefix;
    }
}
