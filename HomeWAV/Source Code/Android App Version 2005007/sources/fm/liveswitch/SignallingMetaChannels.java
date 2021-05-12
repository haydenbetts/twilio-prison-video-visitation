package fm.liveswitch;

abstract class SignallingMetaChannels {
    public static String getBind() {
        return "/meta/bind";
    }

    public static String getConnect() {
        return "/meta/connect";
    }

    public static String getDisconnect() {
        return "/meta/disconnect";
    }

    public static String getHandshake() {
        return "/meta/handshake";
    }

    public static String getMetaPrefix() {
        return "/meta/";
    }

    public static String getServicePrefix() {
        return "/service/";
    }

    public static String getSubscribe() {
        return "/meta/subscribe";
    }

    public static String getUnbind() {
        return "/meta/unbind";
    }

    public static String getUnsubscribe() {
        return "/meta/unsubscribe";
    }

    SignallingMetaChannels() {
    }

    public static String convertChannelFromServiced(String str) {
        if (!isServiceChannel(str)) {
            return str;
        }
        return str.substring(StringExtensions.getLength(getServicePrefix()) - 1);
    }

    public static String convertChannelToServiced(String str) {
        if (str == null || StringExtensions.getLength(str) < 1) {
            return getServicePrefix();
        }
        return StringExtensions.concat(getServicePrefix(), str.substring(1));
    }

    static SignallingMessageType getMessageType(String str) {
        if (Global.equals(str, getHandshake())) {
            return SignallingMessageType.Connect;
        }
        if (Global.equals(str, getConnect())) {
            return SignallingMessageType.Stream;
        }
        if (Global.equals(str, getDisconnect())) {
            return SignallingMessageType.Disconnect;
        }
        if (Global.equals(str, getBind())) {
            return SignallingMessageType.Bind;
        }
        if (Global.equals(str, getUnbind())) {
            return SignallingMessageType.Unbind;
        }
        if (Global.equals(str, getSubscribe())) {
            return SignallingMessageType.Subscribe;
        }
        if (Global.equals(str, getUnsubscribe())) {
            return SignallingMessageType.Unsubscribe;
        }
        if (isServiceChannel(str)) {
            return SignallingMessageType.Service;
        }
        if (!isMetaChannel(str)) {
            return SignallingMessageType.Publish;
        }
        return SignallingMessageType.Unknown;
    }

    public static boolean isMetaChannel(String str) {
        return str != null && StringExtensions.startsWith(str, getMetaPrefix(), StringComparison.Ordinal);
    }

    public static boolean isReservedChannel(String str) {
        return isMetaChannel(str) || isServiceChannel(str);
    }

    public static boolean isServiceChannel(String str) {
        return str != null && StringExtensions.startsWith(str, getServicePrefix(), StringComparison.Ordinal);
    }
}
