package fm.liveswitch.vpx;

public class KeyframeMode {
    private static KeyframeMode __auto = new KeyframeMode(1);
    private static KeyframeMode __disabled = new KeyframeMode(0);
    private int _value;

    public static KeyframeMode getAuto() {
        return __auto;
    }

    public static KeyframeMode getDisabled() {
        return __disabled;
    }

    public static KeyframeMode getFixed() {
        return getDisabled();
    }

    public int getValue() {
        return this._value;
    }

    private KeyframeMode() {
    }

    private KeyframeMode(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
