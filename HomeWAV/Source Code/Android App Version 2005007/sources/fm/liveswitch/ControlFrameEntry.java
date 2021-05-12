package fm.liveswitch;

public abstract class ControlFrameEntry {
    public abstract long getSynchronizationSource();

    public abstract void setSynchronizationSource(long j);

    protected ControlFrameEntry() {
    }
}
