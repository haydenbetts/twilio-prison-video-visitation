package fm.liveswitch.vpx;

public class BitDepth {
    private static BitDepth __bits10 = new BitDepth(10);
    private static BitDepth __bits12 = new BitDepth(12);
    private static BitDepth __bits8 = new BitDepth(8);
    private int _value;

    private BitDepth() {
    }

    private BitDepth(int i) {
        setValue(i);
    }

    public static BitDepth getBits10() {
        return __bits10;
    }

    public static BitDepth getBits12() {
        return __bits12;
    }

    public static BitDepth getBits8() {
        return __bits8;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
