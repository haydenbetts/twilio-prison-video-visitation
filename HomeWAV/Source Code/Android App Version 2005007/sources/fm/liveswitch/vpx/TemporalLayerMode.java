package fm.liveswitch.vpx;

public class TemporalLayerMode {
    private static TemporalLayerMode __bypass = new TemporalLayerMode(1);
    private static TemporalLayerMode __mode0101 = new TemporalLayerMode(2);
    private static TemporalLayerMode __mode0212 = new TemporalLayerMode(3);
    private static TemporalLayerMode __noLayering = new TemporalLayerMode(0);
    private int _value;

    public static TemporalLayerMode getBypass() {
        return __bypass;
    }

    public static TemporalLayerMode getMode0101() {
        return __mode0101;
    }

    public static TemporalLayerMode getMode0212() {
        return __mode0212;
    }

    public static TemporalLayerMode getNoLayering() {
        return __noLayering;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }

    private TemporalLayerMode() {
    }

    private TemporalLayerMode(int i) {
        setValue(i);
    }
}
