package fm.liveswitch.matroska;

public class BlockFlags {
    public static byte getEbmlLacing() {
        return 17;
    }

    public static byte getFixedSizeLacing() {
        return Tnaf.POW_2_WIDTH;
    }

    public static byte getInvisible() {
        return 8;
    }

    public static byte getNoLacing() {
        return 0;
    }

    public static byte getXiphLacing() {
        return 1;
    }
}
