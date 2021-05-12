package fm.liveswitch;

public interface ISynchronizableStream {
    void addOnMasterSynchronizeContextReady(IAction1<SynchronizeContext> iAction1);

    String getId();

    boolean getOutputSynchronizationDisabled();

    String getRemoteDescriptionMediaId();

    SynchronizeContext getSynchronizeContext();

    ISynchronizer[] getSynchronizers();

    StreamType getType();

    void removeOnMasterSynchronizeContextReady(IAction1<SynchronizeContext> iAction1);

    void setOutputSynchronizationDisabled(boolean z);

    void setSynchronizeContext(SynchronizeContext synchronizeContext);

    void synchronize(boolean z);
}
