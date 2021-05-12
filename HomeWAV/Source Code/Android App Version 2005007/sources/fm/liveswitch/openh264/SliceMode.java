package fm.liveswitch.openh264;

public class SliceMode {
    private static SliceMode __fixedSliceNum = new SliceMode(1);
    private static SliceMode __raster = new SliceMode(2);
    private static SliceMode __reserved = new SliceMode(4);
    private static SliceMode __single = new SliceMode(0);
    private static SliceMode __sizeLimited = new SliceMode(3);
    private int _value;

    public static SliceMode getFixedSliceNum() {
        return __fixedSliceNum;
    }

    public static SliceMode getRaster() {
        return __raster;
    }

    public static SliceMode getReserved() {
        return __reserved;
    }

    public static SliceMode getSingle() {
        return __single;
    }

    public static SliceMode getSizeLimited() {
        return __sizeLimited;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }

    private SliceMode() {
    }

    private SliceMode(int i) {
        setValue(i);
    }
}
