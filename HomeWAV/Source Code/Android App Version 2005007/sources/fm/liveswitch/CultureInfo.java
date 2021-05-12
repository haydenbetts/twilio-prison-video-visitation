package fm.liveswitch;

public class CultureInfo implements IFormatProvider {
    public static CultureInfo getCurrentCulture() {
        return new CultureInfo();
    }

    public static CultureInfo getInvariantCulture() {
        return new CultureInfo();
    }
}
