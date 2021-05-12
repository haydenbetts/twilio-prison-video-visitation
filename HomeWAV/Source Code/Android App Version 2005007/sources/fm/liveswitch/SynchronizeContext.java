package fm.liveswitch;

public class SynchronizeContext {
    private static ILog __log = Log.getLogger(SynchronizeContext.class);
    private long _systemTimestamp;

    public long getNtpTimestampTicks() {
        __log.warn("Getting the value of NtpTimestampTicks is deprecated. Use SystemTimestamp instead.");
        return getSystemTimestamp();
    }

    public long getSystemTimestamp() {
        return this._systemTimestamp;
    }

    public void setNtpTimestampTicks(long j) {
        __log.warn("Setting the value of NtpTimestampTicks is deprecated. Use SystemTimestamp instead.");
        setSystemTimestamp(j);
    }

    public void setSystemTimestamp(long j) {
        this._systemTimestamp = j;
    }

    public SynchronizeContext(long j, long j2) {
        __log.warn("SynchronizeContext(long,long) is deprecated. Use SynchronizeContext(long) instead.");
        if (j != j2) {
            __log.warn("NtpTimestampTicks is deprecated and ignored. Use SystemTimestamp instead.");
        }
        setSystemTimestamp(j2);
    }

    public SynchronizeContext(long j) {
        setSystemTimestamp(j);
    }
}
