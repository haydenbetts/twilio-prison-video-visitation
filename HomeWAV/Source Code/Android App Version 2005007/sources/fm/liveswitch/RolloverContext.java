package fm.liveswitch;

class RolloverContext {
    private int __bits;
    private long __rolloverSize;
    private long __rolloverSize_2;
    private long _highestValue;
    private long _rolloverCounter;

    public int getBits() {
        return this.__bits;
    }

    public long getHighestValue() {
        return this._highestValue;
    }

    public long getIndex(long j) {
        LongHolder longHolder = new LongHolder(0);
        long index = getIndex(j, longHolder);
        longHolder.getValue();
        return index;
    }

    public long getIndex(long j, LongHolder longHolder) {
        long j2;
        if (getHighestValue() == -1) {
            setHighestValue(j);
            longHolder.setValue(getRolloverCounter());
            return j;
        }
        if (getHighestValue() < this.__rolloverSize_2) {
            if (j - getHighestValue() > this.__rolloverSize_2) {
                j2 = (getRolloverCounter() - 1) % 4294967296L;
            } else {
                j2 = getRolloverCounter();
                setHighestValue(MathAssistant.max(getHighestValue(), j));
            }
        } else if (getHighestValue() - this.__rolloverSize_2 >= j) {
            j2 = (getRolloverCounter() + 1) % 4294967296L;
            setHighestValue(j);
            setRolloverCounter(j2);
        } else {
            j2 = getRolloverCounter();
            setHighestValue(MathAssistant.max(getHighestValue(), j));
        }
        longHolder.setValue(j2);
        return (this.__rolloverSize * j2) + j;
    }

    public long getRolloverCounter() {
        return this._rolloverCounter;
    }

    public RolloverContext(int i) {
        if (i >= 2) {
            setBits(i);
            setRolloverCounter(0);
            setHighestValue(-1);
            return;
        }
        throw new RuntimeException(new Exception("Minimum bits is 2."));
    }

    public void setBits(int i) {
        this.__bits = i;
        long pow = (long) ((int) MathAssistant.pow(2.0d, (double) i));
        this.__rolloverSize = pow;
        this.__rolloverSize_2 = pow / 2;
    }

    private void setHighestValue(long j) {
        this._highestValue = j;
    }

    private void setRolloverCounter(long j) {
        this._rolloverCounter = j;
    }
}
