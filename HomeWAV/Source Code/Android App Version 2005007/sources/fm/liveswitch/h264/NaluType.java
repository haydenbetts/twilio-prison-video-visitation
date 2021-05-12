package fm.liveswitch.h264;

class NaluType {
    public static final int Aud = 9;
    public static final int FuA = 28;
    public static final int FuB = 29;
    public static final int Idr = 5;
    public static final int Mtap16 = 26;
    public static final int Mtap24 = 27;
    public static final int Pps = 8;
    public static final int Sei = 6;
    public static final int Slice = 1;
    public static final int Sps = 7;
    public static final int StapA = 24;
    public static final int StapB = 25;
    public static final int Unknown = 0;

    public static int getNaluType(int i) {
        int i2 = i & 31;
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 5) {
            return 5;
        }
        if (i2 == 6) {
            return 6;
        }
        if (i2 == 7) {
            return 7;
        }
        if (i2 == 8) {
            return 8;
        }
        if (i2 == 24) {
            return 24;
        }
        if (i2 == 25) {
            return 25;
        }
        if (i2 == 26) {
            return 26;
        }
        if (i2 == 27) {
            return 27;
        }
        return (i2 == 28 || i2 == 29) ? 28 : 0;
    }

    public static boolean isSingleNalu(int i) {
        return i >= 0 && i < 23;
    }
}
