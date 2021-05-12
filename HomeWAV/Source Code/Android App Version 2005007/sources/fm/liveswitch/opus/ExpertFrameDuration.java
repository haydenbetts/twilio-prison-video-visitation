package fm.liveswitch.opus;

public class ExpertFrameDuration {
    private static ExpertFrameDuration __argument = new ExpertFrameDuration(5000);
    private static ExpertFrameDuration __size10ms = new ExpertFrameDuration(5003);
    private static ExpertFrameDuration __size20ms = new ExpertFrameDuration(5004);
    private static ExpertFrameDuration __size2_5ms = new ExpertFrameDuration(5001);
    private static ExpertFrameDuration __size40ms = new ExpertFrameDuration(5005);
    private static ExpertFrameDuration __size5ms = new ExpertFrameDuration(5002);
    private static ExpertFrameDuration __size60ms = new ExpertFrameDuration(5006);
    private static ExpertFrameDuration __variable = new ExpertFrameDuration(5010);
    private int _value;

    private ExpertFrameDuration() {
    }

    private ExpertFrameDuration(int i) {
        setValue(i);
    }

    public static ExpertFrameDuration getArgument() {
        return __argument;
    }

    public static ExpertFrameDuration getSize10ms() {
        return __size10ms;
    }

    public static ExpertFrameDuration getSize2_5ms() {
        return __size2_5ms;
    }

    public static ExpertFrameDuration getSize20ms() {
        return __size20ms;
    }

    public static ExpertFrameDuration getSize40ms() {
        return __size40ms;
    }

    public static ExpertFrameDuration getSize5ms() {
        return __size5ms;
    }

    public static ExpertFrameDuration getSize60ms() {
        return __size60ms;
    }

    public int getValue() {
        return this._value;
    }

    public static ExpertFrameDuration getVariable() {
        return __variable;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
