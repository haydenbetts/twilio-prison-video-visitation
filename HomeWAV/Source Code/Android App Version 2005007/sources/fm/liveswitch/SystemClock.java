package fm.liveswitch;

public class SystemClock implements ISystemClock {
    public long getTimestamp() {
        return ManagedStopwatch.getTimestamp();
    }
}
