package fm.liveswitch.diagnostics;

import fm.liveswitch.AtomicLong;
import fm.liveswitch.DoubleExtensions;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.StringExtensions;

public class RateTimer extends Timer {
    private int __averageCount = 0;
    private double __avg = 0.0d;
    private AtomicLong __counter = new AtomicLong();
    private boolean __hasData;
    private long __lastWatchTick = 0;
    private double __max = -1.0d;
    private double __min = -1.0d;

    public void addTick() {
        if (Timers.getEnabled()) {
            this.__hasData = true;
            this.__counter.increment();
        }
    }

    public boolean getHasData() {
        return this.__hasData;
    }

    public String getStats() {
        long elapsedMilliseconds = super.getWatch().getElapsedMilliseconds();
        long value = this.__counter.getValue();
        this.__counter.subtract(value);
        this.__lastWatchTick = elapsedMilliseconds;
        double d = ((double) (elapsedMilliseconds - this.__lastWatchTick)) / 1000.0d;
        double d2 = 0.0d;
        if (d > 0.0d) {
            d2 = ((double) value) / d;
            double d3 = this.__min;
            this.__min = d3 == -1.0d ? d2 : MathAssistant.min(d3, d2);
            double d4 = this.__max;
            this.__max = d4 == -1.0d ? d2 : MathAssistant.max(d4, d2);
            double d5 = this.__avg;
            int i = this.__averageCount + 1;
            this.__averageCount = i;
            this.__avg = d5 + ((d2 - d5) / ((double) i));
        }
        return StringExtensions.format("{0}:\n\tInstant: {1}hz\n\tMin: {2}hz\n\tMax: {3}hz\n\tAvg: {4}hz", new Object[]{super.getTag(), DoubleExtensions.toString(Double.valueOf(d2)), DoubleExtensions.toString(Double.valueOf(this.__min)), DoubleExtensions.toString(Double.valueOf(this.__max)), DoubleExtensions.toString(Double.valueOf(this.__avg))});
    }

    RateTimer(String str) {
        super(str);
    }
}
