package fm.liveswitch;

public abstract class DatagramSocket extends ManagedSocket {
    private static ILog __log = Log.getLogger(DatagramSocket.class);

    public abstract int getMaxQueuedPackets();

    public abstract int getReceiveBufferSize();

    public abstract int getSendBufferSize();

    public abstract void receiveAsync(IAction3<DataBuffer, String, Integer> iAction3, IAction1<Exception> iAction1);

    public abstract Error send(DataBuffer dataBuffer, String str, int i);

    public abstract void setMaxQueuedPackets(int i);

    protected DatagramSocket() {
    }

    /* access modifiers changed from: protected */
    public void raiseReceiveFailure(IAction1<Exception> iAction1, Exception exc) {
        if (iAction1 != null) {
            try {
                iAction1.invoke(exc);
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising receive failure.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void raiseReceiveSuccess(IAction3<DataBuffer, String, Integer> iAction3, DataBuffer dataBuffer, String str, int i) {
        if (iAction3 != null) {
            try {
                iAction3.invoke(dataBuffer, str, Integer.valueOf(i));
            } catch (Exception e) {
                __log.error("An unexpected exception was thrown while raising receive success.", e);
            }
        }
    }
}
