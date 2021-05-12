package fm.liveswitch.matroska;

public class TrackType {
    public static long getAudio() {
        return 2;
    }

    public static long getButton() {
        return 18;
    }

    public static long getComplex() {
        return 3;
    }

    public static long getControl() {
        return 32;
    }

    public static long getLogo() {
        return 16;
    }

    public static long getSubtitle() {
        return 17;
    }

    public static long getVideo() {
        return 1;
    }
}
