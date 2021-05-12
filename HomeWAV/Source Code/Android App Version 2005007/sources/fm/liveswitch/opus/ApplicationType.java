package fm.liveswitch.opus;

public class ApplicationType {
    private static ApplicationType __audio = new ApplicationType(2049);
    private static ApplicationType __restrictedLowDelay = new ApplicationType(2051);
    private static ApplicationType __voip = new ApplicationType(2048);
    private int _value;

    private ApplicationType() {
    }

    private ApplicationType(int i) {
        setValue(i);
    }

    public static ApplicationType getAudio() {
        return __audio;
    }

    public static ApplicationType getRestrictedLowDelay() {
        return __restrictedLowDelay;
    }

    public int getValue() {
        return this._value;
    }

    public static ApplicationType getVoip() {
        return __voip;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
