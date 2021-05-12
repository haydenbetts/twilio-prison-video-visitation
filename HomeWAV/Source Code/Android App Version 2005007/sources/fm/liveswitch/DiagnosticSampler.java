package fm.liveswitch;

public class DiagnosticSampler {
    private AtomicInteger __arrayPointer;
    private AtomicLong __count;
    private AtomicLong __max;
    private AtomicLong __min;
    private long[] __samples;
    private AtomicLong __sum;
    private String _label;
    private long _lastValue;

    public void addSample(long j) {
        setLastValue(j);
        this.__count.increment();
        this.__sum.add(j);
        this.__samples[this.__arrayPointer.increment() % ArrayExtensions.getLength(this.__samples)] = j;
        if (this.__min.getValue() > j) {
            long value = this.__min.getValue();
            while (true) {
                if (this.__min.compareAndSwap(value, MathAssistant.min(value, j)) == value) {
                    break;
                }
                value = this.__min.getValue();
            }
        }
        if (this.__max.getValue() < j) {
            long value2 = this.__max.getValue();
            while (true) {
                if (this.__max.compareAndSwap(value2, MathAssistant.max(value2, j)) != value2) {
                    value2 = this.__max.getValue();
                } else {
                    return;
                }
            }
        }
    }

    public DiagnosticSampler() {
        this((String) null);
    }

    public DiagnosticSampler(int i) {
        this(i, (String) null);
    }

    public DiagnosticSampler(int i, String str) {
        setLastValue(0);
        setLabel(str);
        this.__min = new AtomicLong(Long.MAX_VALUE);
        this.__max = new AtomicLong();
        this.__count = new AtomicLong();
        this.__sum = new AtomicLong();
        this.__arrayPointer = new AtomicInteger();
        this.__samples = new long[i];
    }

    public DiagnosticSampler(String str) {
        this(100, str);
    }

    public double getAverage() {
        long min = MathAssistant.min((long) ArrayExtensions.getLength(this.__samples), getCount());
        double d = 0.0d;
        for (int i = 0; ((long) i) < min; i++) {
            d += (double) this.__samples[i];
        }
        return d / ((double) min);
    }

    public long getCount() {
        return this.__count.getValue();
    }

    public String getLabel() {
        return this._label;
    }

    public long getLastValue() {
        return this._lastValue;
    }

    public long getMax() {
        return this.__max.getValue();
    }

    public long getMin() {
        return this.__min.getValue();
    }

    public int getSamplesInAverage() {
        return ArrayExtensions.getLength(this.__samples);
    }

    public long getSum() {
        return this.__sum.getValue();
    }

    private void setLabel(String str) {
        this._label = str;
    }

    private void setLastValue(long j) {
        this._lastValue = j;
    }
}
