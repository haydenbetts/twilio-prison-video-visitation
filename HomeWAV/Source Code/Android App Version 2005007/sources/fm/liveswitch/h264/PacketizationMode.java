package fm.liveswitch.h264;

public class PacketizationMode {
    public static int getInterleaved() {
        return 2;
    }

    public static int getNonInterleaved() {
        return 1;
    }

    public static int getSingleNal() {
        return 0;
    }

    public static int getDefault() {
        return getNonInterleaved();
    }
}
