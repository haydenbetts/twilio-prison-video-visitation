package fm.liveswitch;

class SctpChunkType {
    public static byte getAbort() {
        return 6;
    }

    public static byte getAuth() {
        return 15;
    }

    public static byte getCookieAck() {
        return 11;
    }

    public static byte getCookieEcho() {
        return 10;
    }

    public static byte getCwr() {
        return 13;
    }

    public static byte getData() {
        return 0;
    }

    public static byte getEcne() {
        return 12;
    }

    public static byte getError() {
        return 9;
    }

    public static byte getHeartbeat() {
        return 4;
    }

    public static byte getHeartbeatAck() {
        return 5;
    }

    public static byte getInitiation() {
        return 1;
    }

    public static byte getInitiationAck() {
        return 2;
    }

    public static byte getSack() {
        return 3;
    }

    public static byte getShutdown() {
        return 7;
    }

    public static byte getShutdownAck() {
        return 8;
    }

    public static byte getShutdownComplete() {
        return 14;
    }

    public static byte getAsConf() {
        return BitAssistant.castByte(193);
    }

    public static byte getAsConfAck() {
        return BitAssistant.castByte(128);
    }

    public static byte getForwardCumulativeTSN() {
        return BitAssistant.castByte(192);
    }

    public static byte getPad() {
        return BitAssistant.castByte(132);
    }

    public static byte getReConfig() {
        return BitAssistant.castByte(130);
    }
}
