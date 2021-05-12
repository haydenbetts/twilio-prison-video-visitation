package fm.liveswitch.diagnostics;

import fm.liveswitch.DoubleExtensions;
import fm.liveswitch.Holder;
import fm.liveswitch.ManagedConcurrentQueue;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.StringExtensions;

public class DurationTimer extends Timer {
    private int __averageCount = 0;
    private double __avg = 0.0d;
    private double __cum = 0.0d;
    private double __max = -1.0d;
    private double __min = -1.0d;
    private ManagedConcurrentQueue<DurationSample> __samples = new ManagedConcurrentQueue<>();

    public DurationSample beginSample() {
        if (Timers.getEnabled()) {
            return new DurationSample(super.getWatch().getElapsedMilliseconds());
        }
        return null;
    }

    DurationTimer(String str) {
        super(str);
    }

    public boolean endSample(DurationSample durationSample) {
        if (!Timers.getEnabled()) {
            return false;
        }
        if (durationSample != null) {
            durationSample.end(super.getWatch().getElapsedMilliseconds());
            this.__samples.enqueue(durationSample);
            return true;
        }
        throw new RuntimeException(new Exception("Sample cannot be null."));
    }

    public boolean getHasData() {
        return this.__min != -1.0d || this.__samples.getCount() > 0;
    }

    public String getStats() {
        String str;
        int count = this.__samples.getCount();
        double d = 0.0d;
        if (count > 0) {
            DurationSample durationSample = null;
            int i = 0;
            while (i < count) {
                Holder holder = new Holder(durationSample);
                boolean tryDequeue = this.__samples.tryDequeue(holder);
                DurationSample durationSample2 = (DurationSample) holder.getValue();
                if (tryDequeue) {
                    long endMillis = durationSample2.getEndMillis() - durationSample2.getBeginMillis();
                    double d2 = this.__min;
                    this.__min = d2 == -1.0d ? (double) endMillis : MathAssistant.min(d2, (double) endMillis);
                    double d3 = this.__max;
                    this.__max = d3 == -1.0d ? (double) endMillis : MathAssistant.max(d3, (double) endMillis);
                    double d4 = (double) endMillis;
                    this.__cum += d4;
                    d += (d4 - d) / ((double) (i + 1));
                    double d5 = this.__avg;
                    int i2 = this.__averageCount + 1;
                    this.__averageCount = i2;
                    this.__avg = d5 + ((d - d5) / ((double) i2));
                }
                i++;
                durationSample = durationSample2;
            }
        }
        if (count == 0) {
            str = "No Samples";
        } else {
            str = DoubleExtensions.toString(Double.valueOf(d));
        }
        return StringExtensions.format("{0}:\n\tInstant Avg: {1}ms\n\tMin: {2}ms\n\tMax: {3}ms\n\tAvg: {4}ms\n\tCum: {5}ms", new Object[]{super.getTag(), str, DoubleExtensions.toString(Double.valueOf(this.__min)), DoubleExtensions.toString(Double.valueOf(this.__max)), DoubleExtensions.toString(Double.valueOf(this.__avg)), DoubleExtensions.toString(Double.valueOf(this.__cum))});
    }
}
