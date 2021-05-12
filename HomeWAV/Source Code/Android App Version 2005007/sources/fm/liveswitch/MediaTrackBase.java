package fm.liveswitch;

public abstract class MediaTrackBase extends Dynamic implements IMediaTrack {
    public abstract void addOnDestroyed(IAction0 iAction0);

    public abstract void addOnStarted(IAction0 iAction0);

    public abstract void addOnStopped(IAction0 iAction0);

    public abstract Future<Object> changeSinkOutput(SinkOutput sinkOutput);

    public abstract Future<Object> changeSourceInput(SourceInput sourceInput);

    public abstract boolean destroy();

    public abstract boolean getMuted();

    public abstract SinkOutput getSinkOutput();

    public abstract Future<SinkOutput[]> getSinkOutputs();

    public abstract SourceInput getSourceInput();

    public abstract Future<SourceInput[]> getSourceInputs();

    public abstract void removeOnDestroyed(IAction0 iAction0);

    public abstract void removeOnStarted(IAction0 iAction0);

    public abstract void removeOnStopped(IAction0 iAction0);

    public abstract void setMuted(boolean z);

    public abstract void setSinkOutput(SinkOutput sinkOutput);

    public abstract void setSourceInput(SourceInput sourceInput);

    protected MediaTrackBase() {
    }
}
