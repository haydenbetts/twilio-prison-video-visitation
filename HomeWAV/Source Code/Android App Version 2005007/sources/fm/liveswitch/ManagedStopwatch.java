package fm.liveswitch;

public class ManagedStopwatch {
    private long startTime = 0;
    private long stopTime = 0;

    public static long getTimestamp() {
        return System.nanoTime() / ((long) Constants.getNanosecondsPerTick());
    }

    public long getElapsedTicks() {
        long j = this.startTime;
        if (j == 0) {
            return 0;
        }
        long j2 = this.stopTime;
        if (j2 == 0) {
            return (System.nanoTime() - this.startTime) / ((long) Constants.getNanosecondsPerTick());
        }
        return (j2 - j) / ((long) Constants.getNanosecondsPerTick());
    }

    public long getElapsedMilliseconds() {
        return getElapsedTicks() / ((long) Constants.getTicksPerMillisecond());
    }

    public void start() {
        this.startTime = System.nanoTime();
        this.stopTime = 0;
    }

    public void stop() {
        this.stopTime = System.nanoTime();
    }

    public void restart() {
        start();
    }
}
