package fm.liveswitch.openh264;

public class ParameterSetStrategy {
    private static ParameterSetStrategy __constantId = new ParameterSetStrategy(0);
    private static ParameterSetStrategy __increasingId = new ParameterSetStrategy(1);
    private static ParameterSetStrategy __spsListing = new ParameterSetStrategy(2);
    private static ParameterSetStrategy __spsListingAndPpsIncreasing = new ParameterSetStrategy(3);
    private static ParameterSetStrategy __spsPpsListing = new ParameterSetStrategy(6);
    private int _value;

    public static ParameterSetStrategy getConstantId() {
        return __constantId;
    }

    public static ParameterSetStrategy getIncreasingId() {
        return __increasingId;
    }

    public static ParameterSetStrategy getSpsListing() {
        return __spsListing;
    }

    public static ParameterSetStrategy getSpsListingAndPpsIncreasing() {
        return __spsListingAndPpsIncreasing;
    }

    public static ParameterSetStrategy getSpsPpsListing() {
        return __spsPpsListing;
    }

    public int getValue() {
        return this._value;
    }

    private ParameterSetStrategy() {
    }

    private ParameterSetStrategy(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
