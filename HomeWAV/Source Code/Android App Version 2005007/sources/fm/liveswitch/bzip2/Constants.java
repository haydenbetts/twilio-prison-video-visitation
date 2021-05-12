package fm.liveswitch.bzip2;

abstract class Constants {
    public static int getBlockSizeMultiple() {
        return 100000;
    }

    public static int getG_size() {
        return 50;
    }

    public static int getMaxAlphaSize() {
        return 258;
    }

    public static int getMaxCodeLength() {
        return 23;
    }

    public static int getNGroups() {
        return 6;
    }

    public static char getRuna() {
        return 0;
    }

    public static char getRunb() {
        return 1;
    }

    Constants() {
    }

    public static int getMaxSelectors() {
        return (900000 / getG_size()) + 2;
    }
}
