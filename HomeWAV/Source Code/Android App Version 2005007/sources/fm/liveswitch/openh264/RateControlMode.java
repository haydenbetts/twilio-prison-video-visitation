package fm.liveswitch.openh264;

public class RateControlMode {
    private static RateControlMode __bitrateMode = new RateControlMode(1);
    private static RateControlMode __bitrateModePostSkip = new RateControlMode(4);
    private static RateControlMode __bufferBasedMode = new RateControlMode(2);
    private static RateControlMode __offMode = new RateControlMode(-1);
    private static RateControlMode __qualityMode = new RateControlMode(0);
    private static RateControlMode __timestampMode = new RateControlMode(3);
    private int _value;

    public static RateControlMode getBitrateMode() {
        return __bitrateMode;
    }

    public static RateControlMode getBitrateModePostSkip() {
        return __bitrateModePostSkip;
    }

    public static RateControlMode getBufferBasedMode() {
        return __bufferBasedMode;
    }

    public static RateControlMode getOffMode() {
        return __offMode;
    }

    public static RateControlMode getQualityMode() {
        return __qualityMode;
    }

    public static RateControlMode getTimestampMode() {
        return __timestampMode;
    }

    public int getValue() {
        return this._value;
    }

    private RateControlMode() {
    }

    private RateControlMode(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
