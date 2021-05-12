package fm.liveswitch.openh264;

public class SampleAspectRatio {
    private static SampleAspectRatio __160x99 = new SampleAspectRatio(13);
    private static SampleAspectRatio __64x33 = new SampleAspectRatio(12);
    private static SampleAspectRatio __asp10x11 = new SampleAspectRatio(3);
    private static SampleAspectRatio __asp12x11 = new SampleAspectRatio(2);
    private static SampleAspectRatio __asp15x11 = new SampleAspectRatio(11);
    private static SampleAspectRatio __asp16x11 = new SampleAspectRatio(4);
    private static SampleAspectRatio __asp18x11 = new SampleAspectRatio(10);
    private static SampleAspectRatio __asp1x1 = new SampleAspectRatio(1);
    private static SampleAspectRatio __asp20x11 = new SampleAspectRatio(7);
    private static SampleAspectRatio __asp24x11 = new SampleAspectRatio(6);
    private static SampleAspectRatio __asp32x11 = new SampleAspectRatio(8);
    private static SampleAspectRatio __asp40x33 = new SampleAspectRatio(5);
    private static SampleAspectRatio __asp80x33 = new SampleAspectRatio(9);
    private static SampleAspectRatio __extSar = new SampleAspectRatio(255);
    private static SampleAspectRatio __unspecified = new SampleAspectRatio(0);
    private int _value;

    public static SampleAspectRatio getAsp10x11() {
        return __asp10x11;
    }

    public static SampleAspectRatio getAsp12x11() {
        return __asp12x11;
    }

    public static SampleAspectRatio getAsp15x11() {
        return __asp15x11;
    }

    public static SampleAspectRatio getAsp160x99() {
        return __160x99;
    }

    public static SampleAspectRatio getAsp16x11() {
        return __asp16x11;
    }

    public static SampleAspectRatio getAsp18x11() {
        return __asp18x11;
    }

    public static SampleAspectRatio getAsp1x1() {
        return __asp1x1;
    }

    public static SampleAspectRatio getAsp20x11() {
        return __asp20x11;
    }

    public static SampleAspectRatio getAsp24x11() {
        return __asp24x11;
    }

    public static SampleAspectRatio getAsp32x11() {
        return __asp32x11;
    }

    public static SampleAspectRatio getAsp40x33() {
        return __asp40x33;
    }

    public static SampleAspectRatio getAsp64x33() {
        return __64x33;
    }

    public static SampleAspectRatio getAsp80x33() {
        return __asp80x33;
    }

    public static SampleAspectRatio getAspExtSar() {
        return __extSar;
    }

    public static SampleAspectRatio getUnspecified() {
        return __unspecified;
    }

    public int getValue() {
        return this._value;
    }

    private SampleAspectRatio() {
    }

    private SampleAspectRatio(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
