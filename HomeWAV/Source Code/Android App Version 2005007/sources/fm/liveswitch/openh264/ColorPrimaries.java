package fm.liveswitch.openh264;

public class ColorPrimaries {
    private static ColorPrimaries __bt2020 = new ColorPrimaries(9);
    private static ColorPrimaries __bt470bg = new ColorPrimaries(5);
    private static ColorPrimaries __bt470m = new ColorPrimaries(4);
    private static ColorPrimaries __bt709 = new ColorPrimaries(1);
    private static ColorPrimaries __film = new ColorPrimaries(8);
    private static ColorPrimaries __numEnum = new ColorPrimaries(10);
    private static ColorPrimaries __reserved0 = new ColorPrimaries(0);
    private static ColorPrimaries __reserved3 = new ColorPrimaries(3);
    private static ColorPrimaries __smpte170M = new ColorPrimaries(6);
    private static ColorPrimaries __smpte240M = new ColorPrimaries(7);
    private static ColorPrimaries __undefined = new ColorPrimaries(2);
    private char _value;

    private ColorPrimaries() {
    }

    private ColorPrimaries(int i) {
        setValue((char) i);
    }

    public static ColorPrimaries getBT2020() {
        return __bt2020;
    }

    public static ColorPrimaries getBT470BG() {
        return __bt470bg;
    }

    public static ColorPrimaries getBT470M() {
        return __bt470m;
    }

    public static ColorPrimaries getBT709() {
        return __bt709;
    }

    public static ColorPrimaries getFilm() {
        return __film;
    }

    public static ColorPrimaries getNumEnum() {
        return __numEnum;
    }

    public static ColorPrimaries getReserved0() {
        return __reserved0;
    }

    public static ColorPrimaries getReserved3() {
        return __reserved3;
    }

    public static ColorPrimaries getSmpte170M() {
        return __smpte170M;
    }

    public static ColorPrimaries getSmpte240M() {
        return __smpte240M;
    }

    public static ColorPrimaries getUndefined() {
        return __undefined;
    }

    public char getValue() {
        return this._value;
    }

    private void setValue(char c) {
        this._value = c;
    }
}
