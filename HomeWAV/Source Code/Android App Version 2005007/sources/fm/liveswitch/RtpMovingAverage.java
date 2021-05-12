package fm.liveswitch;

class RtpMovingAverage {
    private int[] __values;
    private int __valuesDivisor;
    private int __valuesIndex = 0;
    private int __valuesLookback;
    private long __valuesSum = 0;
    private int _average;

    public void add(int i) {
        long j = this.__valuesSum;
        int[] iArr = this.__values;
        int i2 = this.__valuesIndex;
        long j2 = j - ((long) iArr[i2]);
        this.__valuesSum = j2;
        iArr[i2] = i;
        long j3 = j2 + ((long) i);
        this.__valuesSum = j3;
        int i3 = this.__valuesLookback;
        this.__valuesIndex = (i2 + 1) % i3;
        int i4 = this.__valuesDivisor;
        if (i4 < i3) {
            this.__valuesDivisor = i4 + 1;
        }
        setAverage((int) (j3 / ((long) this.__valuesDivisor)));
    }

    public int getAverage() {
        return this._average;
    }

    public RtpMovingAverage(int i) {
        if (i >= 1) {
            setAverage(-1);
            this.__valuesLookback = i;
            this.__values = new int[i];
            for (int i2 = 0; i2 < ArrayExtensions.getLength(this.__values); i2++) {
                this.__values[i2] = 0;
            }
            return;
        }
        throw new RuntimeException(new Exception("Lookback must be a positive integer."));
    }

    private void setAverage(int i) {
        this._average = i;
    }
}
