package fm.liveswitch.opus;

public class Bandwidth {
    private static Bandwidth __auto = new Bandwidth(OpusAuto.getValue());
    private static Bandwidth __fullBand = new Bandwidth(1105);
    private static Bandwidth __mediumBand = new Bandwidth(1102);
    private static Bandwidth __narrowBand = new Bandwidth(1101);
    private static Bandwidth __superWideBand = new Bandwidth(1104);
    private static Bandwidth __wideBand = new Bandwidth(1103);
    private int _value;

    private Bandwidth() {
    }

    private Bandwidth(int i) {
        setValue(i);
    }

    public static Bandwidth getAuto() {
        return __auto;
    }

    public static Bandwidth getFullBand() {
        return __fullBand;
    }

    public static Bandwidth getMediumBand() {
        return __mediumBand;
    }

    public static Bandwidth getNarrowBand() {
        return __narrowBand;
    }

    public static Bandwidth getSuperWideBand() {
        return __superWideBand;
    }

    public int getValue() {
        return this._value;
    }

    public static Bandwidth getWideBand() {
        return __wideBand;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
