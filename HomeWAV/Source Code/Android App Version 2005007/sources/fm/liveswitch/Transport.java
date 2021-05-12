package fm.liveswitch;

public abstract class Transport extends Dynamic {
    public abstract void addOnReceive(IAction1<DataBuffer> iAction1);

    public abstract boolean getIsClosed();

    public int getRoundTripTime() {
        return -1;
    }

    public abstract void removeOnReceive(IAction1<DataBuffer> iAction1);

    public abstract void send(DataBuffer dataBuffer);

    protected Transport() {
    }
}
