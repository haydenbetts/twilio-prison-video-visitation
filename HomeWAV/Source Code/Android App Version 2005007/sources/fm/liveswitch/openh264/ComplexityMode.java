package fm.liveswitch.openh264;

public class ComplexityMode {
    private static ComplexityMode __high = new ComplexityMode(2);
    private static ComplexityMode __low = new ComplexityMode(0);
    private static ComplexityMode __medium = new ComplexityMode(1);
    private int _value;

    private ComplexityMode() {
    }

    private ComplexityMode(int i) {
        setValue(i);
    }

    public static ComplexityMode getHigh() {
        return __high;
    }

    public static ComplexityMode getLow() {
        return __low;
    }

    public static ComplexityMode getMedium() {
        return __medium;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
