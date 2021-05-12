package fm.liveswitch;

public class NumberFormatInfo implements IFormatProvider {
    public static NumberFormatInfo getCurrentInfo() {
        return new NumberFormatInfo();
    }

    public static NumberFormatInfo getInvariantInfo() {
        return new NumberFormatInfo();
    }
}
