package fm.liveswitch.opus;

public class Signal {
    private static Signal __auto = new Signal(OpusAuto.getValue());
    private static Signal __music = new Signal(3002);
    private static Signal __voice = new Signal(3001);
    private int _value;

    public static Signal getAuto() {
        return __auto;
    }

    public static Signal getMusic() {
        return __music;
    }

    public int getValue() {
        return this._value;
    }

    public static Signal getVoice() {
        return __voice;
    }

    private void setValue(int i) {
        this._value = i;
    }

    private Signal() {
    }

    private Signal(int i) {
        setValue(i);
    }
}
