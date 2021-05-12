package fm.liveswitch;

public class IcePortRange {
    private int __maximum;
    private int __minimum;

    public int getMaximum() {
        return this.__maximum;
    }

    public int getMinimum() {
        return this.__minimum;
    }

    public IcePortRange() {
        this(SctpParameterType.ForwardTsnSupportedParameter);
    }

    public IcePortRange(int i) {
        this(i, 65535);
    }

    public IcePortRange(int i, int i2) {
        this.__minimum = i;
        this.__maximum = i2;
    }
}
