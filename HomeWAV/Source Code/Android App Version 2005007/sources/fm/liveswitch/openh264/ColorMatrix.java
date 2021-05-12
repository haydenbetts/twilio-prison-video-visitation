package fm.liveswitch.openh264;

public class ColorMatrix {
    private static ColorMatrix __bt2020c = new ColorMatrix(10);
    private static ColorMatrix __bt2020nc = new ColorMatrix(9);
    private static ColorMatrix __bt470bg = new ColorMatrix(5);
    private static ColorMatrix __bt709 = new ColorMatrix(1);
    private static ColorMatrix __fcc = new ColorMatrix(4);
    private static ColorMatrix __gbr = new ColorMatrix(0);
    private static ColorMatrix __numEnum = new ColorMatrix(11);
    private static ColorMatrix __reserved3 = new ColorMatrix(3);
    private static ColorMatrix __smpte170M = new ColorMatrix(6);
    private static ColorMatrix __smpte240M = new ColorMatrix(7);
    private static ColorMatrix __undefined = new ColorMatrix(2);
    private static ColorMatrix __ycgco = new ColorMatrix(8);
    private char _value;

    private ColorMatrix() {
    }

    private ColorMatrix(int i) {
        setValue((char) i);
    }

    public static ColorMatrix getBT2020C() {
        return __bt2020c;
    }

    public static ColorMatrix getBT2020NC() {
        return __bt2020nc;
    }

    public static ColorMatrix getBT470BG() {
        return __bt470bg;
    }

    public static ColorMatrix getBT709() {
        return __bt709;
    }

    public static ColorMatrix getFCC() {
        return __fcc;
    }

    public static ColorMatrix getGbr() {
        return __gbr;
    }

    public static ColorMatrix getNumEnum() {
        return __numEnum;
    }

    public static ColorMatrix getReserved3() {
        return __reserved3;
    }

    public static ColorMatrix getSmpte170M() {
        return __smpte170M;
    }

    public static ColorMatrix getSmpte240M() {
        return __smpte240M;
    }

    public static ColorMatrix getUndefined() {
        return __undefined;
    }

    public char getValue() {
        return this._value;
    }

    public static ColorMatrix getYCGCO() {
        return __ycgco;
    }

    private void setValue(char c) {
        this._value = c;
    }
}
