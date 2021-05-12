package fm.liveswitch;

public interface ISynchronizer {
    void activate(boolean z, ISynchronizer[] iSynchronizerArr);

    boolean getActive();

    boolean getMaster();

    long getMasterSystemTimestamp();

    ISynchronizer[] getSlaves();

    void setMasterSystemTimestamp(long j);
}
