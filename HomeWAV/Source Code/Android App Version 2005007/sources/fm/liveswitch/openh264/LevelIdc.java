package fm.liveswitch.openh264;

public class LevelIdc {
    private static LevelIdc __level10 = new LevelIdc(10);
    private static LevelIdc __level11 = new LevelIdc(11);
    private static LevelIdc __level12 = new LevelIdc(12);
    private static LevelIdc __level13 = new LevelIdc(13);
    private static LevelIdc __level1B = new LevelIdc(9);
    private static LevelIdc __level20 = new LevelIdc(20);
    private static LevelIdc __level21 = new LevelIdc(21);
    private static LevelIdc __level22 = new LevelIdc(22);
    private static LevelIdc __level30 = new LevelIdc(30);
    private static LevelIdc __level31 = new LevelIdc(31);
    private static LevelIdc __level32 = new LevelIdc(32);
    private static LevelIdc __level40 = new LevelIdc(40);
    private static LevelIdc __level41 = new LevelIdc(41);
    private static LevelIdc __level42 = new LevelIdc(42);
    private static LevelIdc __level50 = new LevelIdc(50);
    private static LevelIdc __level51 = new LevelIdc(51);
    private static LevelIdc __level52 = new LevelIdc(52);
    private static LevelIdc __unknown = new LevelIdc(0);
    private int _value;

    public static LevelIdc getLevel10() {
        return __level10;
    }

    public static LevelIdc getLevel11() {
        return __level11;
    }

    public static LevelIdc getLevel12() {
        return __level12;
    }

    public static LevelIdc getLevel13() {
        return __level13;
    }

    public static LevelIdc getLevel1B() {
        return __level1B;
    }

    public static LevelIdc getLevel20() {
        return __level20;
    }

    public static LevelIdc getLevel21() {
        return __level21;
    }

    public static LevelIdc getLevel22() {
        return __level22;
    }

    public static LevelIdc getLevel30() {
        return __level30;
    }

    public static LevelIdc getLevel31() {
        return __level31;
    }

    public static LevelIdc getLevel32() {
        return __level32;
    }

    public static LevelIdc getLevel40() {
        return __level40;
    }

    public static LevelIdc getLevel41() {
        return __level41;
    }

    public static LevelIdc getLevel42() {
        return __level42;
    }

    public static LevelIdc getLevel50() {
        return __level50;
    }

    public static LevelIdc getLevel51() {
        return __level51;
    }

    public static LevelIdc getLevel52() {
        return __level52;
    }

    public static LevelIdc getUnknown() {
        return __unknown;
    }

    public int getValue() {
        return this._value;
    }

    private LevelIdc() {
    }

    private LevelIdc(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
