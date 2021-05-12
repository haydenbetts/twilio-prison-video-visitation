package fm.liveswitch.vpx;

public abstract class ErrorResilientType {
    public static int getDefault() {
        return 1;
    }

    public static int getPartitions() {
        return 2;
    }
}
