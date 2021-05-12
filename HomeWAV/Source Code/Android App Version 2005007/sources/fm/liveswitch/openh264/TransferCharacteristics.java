package fm.liveswitch.openh264;

public class TransferCharacteristics {
    private static TransferCharacteristics __bt1361e = new TransferCharacteristics(12);
    private static TransferCharacteristics __bt2020_10 = new TransferCharacteristics(14);
    private static TransferCharacteristics __bt2020_12 = new TransferCharacteristics(15);
    private static TransferCharacteristics __bt470bg = new TransferCharacteristics(5);
    private static TransferCharacteristics __bt470m = new TransferCharacteristics(4);
    private static TransferCharacteristics __bt709 = new TransferCharacteristics(1);
    private static TransferCharacteristics __iec61966_2_1 = new TransferCharacteristics(13);
    private static TransferCharacteristics __iec61966_2_4 = new TransferCharacteristics(11);
    private static TransferCharacteristics __linear = new TransferCharacteristics(8);
    private static TransferCharacteristics __log100 = new TransferCharacteristics(9);
    private static TransferCharacteristics __log316 = new TransferCharacteristics(10);
    private static TransferCharacteristics __numEnum = new TransferCharacteristics(16);
    private static TransferCharacteristics __reserved0 = new TransferCharacteristics(0);
    private static TransferCharacteristics __reserved3 = new TransferCharacteristics(3);
    private static TransferCharacteristics __smpte170M = new TransferCharacteristics(6);
    private static TransferCharacteristics __smpte240M = new TransferCharacteristics(7);
    private static TransferCharacteristics __undefined = new TransferCharacteristics(2);
    private char _value;

    public static TransferCharacteristics getBT1361E() {
        return __bt1361e;
    }

    public static TransferCharacteristics getBT2020_10() {
        return __bt2020_10;
    }

    public static TransferCharacteristics getBT2020_12() {
        return __bt2020_12;
    }

    public static TransferCharacteristics getBT470BG() {
        return __bt470bg;
    }

    public static TransferCharacteristics getBT470M() {
        return __bt470m;
    }

    public static TransferCharacteristics getBT709() {
        return __bt709;
    }

    public static TransferCharacteristics getIec61966_2_1() {
        return __iec61966_2_1;
    }

    public static TransferCharacteristics getIec61966_2_4() {
        return __iec61966_2_4;
    }

    public static TransferCharacteristics getLinear() {
        return __linear;
    }

    public static TransferCharacteristics getLog100() {
        return __log100;
    }

    public static TransferCharacteristics getLog316() {
        return __log316;
    }

    public static TransferCharacteristics getNumEnum() {
        return __numEnum;
    }

    public static TransferCharacteristics getReserved0() {
        return __reserved0;
    }

    public static TransferCharacteristics getReserved3() {
        return __reserved3;
    }

    public static TransferCharacteristics getSmpte170M() {
        return __smpte170M;
    }

    public static TransferCharacteristics getSmpte240M() {
        return __smpte240M;
    }

    public static TransferCharacteristics getUndefined() {
        return __undefined;
    }

    public char getValue() {
        return this._value;
    }

    private void setValue(char c) {
        this._value = c;
    }

    private TransferCharacteristics() {
    }

    private TransferCharacteristics(int i) {
        setValue((char) i);
    }
}
