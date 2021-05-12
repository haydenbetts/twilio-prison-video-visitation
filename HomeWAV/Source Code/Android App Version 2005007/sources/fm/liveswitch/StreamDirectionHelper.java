package fm.liveswitch;

public class StreamDirectionHelper {
    public static StreamDirection directionFromString(String str) {
        if (!(str == null || str == null)) {
            if (Global.equals(str, "sendrecv")) {
                return StreamDirection.SendReceive;
            }
            if (Global.equals(str, "sendonly")) {
                return StreamDirection.SendOnly;
            }
            if (Global.equals(str, "recvonly")) {
                return StreamDirection.ReceiveOnly;
            }
            if (Global.equals(str, "inactive")) {
                return StreamDirection.Inactive;
            }
        }
        return StreamDirection.Unset;
    }

    public static String directionToString(StreamDirection streamDirection) {
        if (streamDirection == StreamDirection.SendReceive) {
            return "sendrecv";
        }
        if (streamDirection == StreamDirection.SendOnly) {
            return "sendonly";
        }
        if (streamDirection == StreamDirection.ReceiveOnly) {
            return "recvonly";
        }
        return streamDirection == StreamDirection.Inactive ? "inactive" : "unset";
    }

    public static boolean isReceiveDisabled(StreamDirection streamDirection) {
        return Global.equals(streamDirection, StreamDirection.Inactive) || Global.equals(streamDirection, StreamDirection.SendOnly);
    }

    public static boolean isReceiveDisabled(String str) {
        return isReceiveDisabled(directionFromString(str));
    }

    public static boolean isSendDisabled(StreamDirection streamDirection) {
        return Global.equals(streamDirection, StreamDirection.Inactive) || Global.equals(streamDirection, StreamDirection.ReceiveOnly);
    }

    public static boolean isSendDisabled(String str) {
        return isSendDisabled(directionFromString(str));
    }

    public static StreamDirection setReceiveDisabled(StreamDirection streamDirection, boolean z) {
        return Global.equals(Boolean.valueOf(z), Boolean.valueOf(isReceiveDisabled(streamDirection))) ? streamDirection : toggleReceive(streamDirection);
    }

    public static String setReceiveDisabled(String str, boolean z) {
        return directionToString(setReceiveDisabled(directionFromString(str), z));
    }

    public static StreamDirection setSendDisabled(StreamDirection streamDirection, boolean z) {
        return Global.equals(Boolean.valueOf(z), Boolean.valueOf(isSendDisabled(streamDirection))) ? streamDirection : toggleSend(streamDirection);
    }

    public static String setSendDisabled(String str, boolean z) {
        return directionToString(setSendDisabled(directionFromString(str), z));
    }

    public static StreamDirection toggleReceive(StreamDirection streamDirection) {
        if (Global.equals(streamDirection, StreamDirection.Inactive)) {
            return StreamDirection.ReceiveOnly;
        }
        if (Global.equals(streamDirection, StreamDirection.ReceiveOnly)) {
            return StreamDirection.Inactive;
        }
        if (Global.equals(streamDirection, StreamDirection.SendReceive)) {
            return StreamDirection.SendOnly;
        }
        if (Global.equals(streamDirection, StreamDirection.SendOnly)) {
            return StreamDirection.SendReceive;
        }
        return StreamDirection.Unset;
    }

    public static String toggleReceive(String str) {
        return directionToString(toggleReceive(directionFromString(str)));
    }

    public static StreamDirection toggleSend(StreamDirection streamDirection) {
        if (Global.equals(streamDirection, StreamDirection.Inactive)) {
            return StreamDirection.SendOnly;
        }
        if (Global.equals(streamDirection, StreamDirection.SendOnly)) {
            return StreamDirection.Inactive;
        }
        if (Global.equals(streamDirection, StreamDirection.SendReceive)) {
            return StreamDirection.ReceiveOnly;
        }
        if (Global.equals(streamDirection, StreamDirection.ReceiveOnly)) {
            return StreamDirection.SendReceive;
        }
        return StreamDirection.Unset;
    }

    public static String toggleSend(String str) {
        return directionToString(toggleSend(directionFromString(str)));
    }
}
