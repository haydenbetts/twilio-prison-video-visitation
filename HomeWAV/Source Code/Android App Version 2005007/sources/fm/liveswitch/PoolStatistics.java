package fm.liveswitch;

public class PoolStatistics {
    AtomicLong __creates = new AtomicLong();
    AtomicLong __hits = new AtomicLong();
    AtomicLong __misses = new AtomicLong();
    AtomicLong __paddingWasted = new AtomicLong();
    AtomicLong __pendingPoolSize = new AtomicLong();
    AtomicLong __returnedSize = new AtomicLong();
    AtomicLong __takenSize = new AtomicLong();
    AtomicLong __totalPoolSize = new AtomicLong();
    private String _tag;

    public long getActivePools() {
        return this.__creates.getValue();
    }

    public double getHitPercentage() {
        if (getTotalCalls() == 0) {
            return -1.0d;
        }
        return (((double) this.__hits.getValue()) / ((double) getTotalCalls())) * 100.0d;
    }

    public double getMissPercentage() {
        if (getTotalCalls() == 0) {
            return -1.0d;
        }
        return (((double) this.__misses.getValue()) / ((double) getTotalCalls())) * 100.0d;
    }

    public long getPaddingWasted() {
        return this.__paddingWasted.getValue();
    }

    public long getPendingPoolSize() {
        return this.__pendingPoolSize.getValue();
    }

    public double getReturnPercentage() {
        long value = this.__returnedSize.getValue();
        long value2 = this.__takenSize.getValue();
        if (value2 == 0) {
            return -1.0d;
        }
        return (((double) value) / ((double) value2)) * 100.0d;
    }

    public String getTag() {
        return this._tag;
    }

    public long getTotalCalls() {
        return this.__hits.getValue() + this.__misses.getValue();
    }

    public long getTotalPools() {
        return this.__creates.getValue();
    }

    public long getTotalPoolSize() {
        return this.__totalPoolSize.getValue();
    }

    PoolStatistics(String str) {
        setTag(str);
    }

    private void setTag(String str) {
        this._tag = str;
    }

    public String toString() {
        long value = this.__hits.getValue();
        long value2 = this.__misses.getValue();
        long j = value + value2;
        double d = -1.0d;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        double d2 = i == 0 ? -1.0d : (((double) value) / ((double) j)) * 100.0d;
        double d3 = i == 0 ? -1.0d : (((double) value2) / ((double) j)) * 100.0d;
        long value3 = this.__returnedSize.getValue();
        long value4 = this.__takenSize.getValue();
        if (value4 != 0) {
            d = (((double) value3) / ((double) value4)) * 100.0d;
        }
        return StringExtensions.format("{0}:\n\tHit Rate: {1}%\n\tMiss Rate: {2}%\n\tReturn Rate: {3}%\n\tPools: {4}\n\tAllocated: {5} bytes\n\tReady: {6} bytes", new Object[]{getTag(), DoubleExtensions.toString(Double.valueOf(d2)), DoubleExtensions.toString(Double.valueOf(d3)), DoubleExtensions.toString(Double.valueOf(d)), LongExtensions.toString(Long.valueOf(getTotalPools())), LongExtensions.toString(Long.valueOf(getTotalPoolSize())), LongExtensions.toString(Long.valueOf(getPendingPoolSize()))});
    }
}
