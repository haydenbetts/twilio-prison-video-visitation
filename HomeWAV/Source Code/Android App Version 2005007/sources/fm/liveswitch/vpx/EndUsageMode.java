package fm.liveswitch.vpx;

public class EndUsageMode {
    private static EndUsageMode __cbr = new EndUsageMode(1);
    private static EndUsageMode __cq = new EndUsageMode(2);
    private static EndUsageMode __q = new EndUsageMode(3);
    private static EndUsageMode __vbr = new EndUsageMode(0);
    private int _value;

    private EndUsageMode() {
    }

    private EndUsageMode(int i) {
        setValue(i);
    }

    public static EndUsageMode getCbr() {
        return __cbr;
    }

    public static EndUsageMode getCQ() {
        return __cq;
    }

    public static EndUsageMode getQ() {
        return __q;
    }

    public int getValue() {
        return this._value;
    }

    public static EndUsageMode getVbr() {
        return __vbr;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
