package fm.liveswitch;

public interface IMediaTrack {
    void addOnDestroyed(IAction0 iAction0);

    void addOnStarted(IAction0 iAction0);

    void addOnStopped(IAction0 iAction0);

    Future<Object> changeSinkOutput(SinkOutput sinkOutput);

    Future<Object> changeSourceInput(SourceInput sourceInput);

    boolean destroy();

    boolean getMuted();

    SinkOutput getSinkOutput();

    Future<SinkOutput[]> getSinkOutputs();

    SourceInput getSourceInput();

    Future<SourceInput[]> getSourceInputs();

    void removeOnDestroyed(IAction0 iAction0);

    void removeOnStarted(IAction0 iAction0);

    void removeOnStopped(IAction0 iAction0);

    void setMuted(boolean z);

    void setSinkOutput(SinkOutput sinkOutput);

    void setSourceInput(SourceInput sourceInput);
}
