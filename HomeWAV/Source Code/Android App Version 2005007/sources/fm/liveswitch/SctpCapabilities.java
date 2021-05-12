package fm.liveswitch;

class SctpCapabilities {
    private long __advertisedReceiverWindow;
    private int __maxIncomingStreams;
    private int __requestedOutboundStreams;

    public long getAdvertisedReceiverWindow() {
        return this.__advertisedReceiverWindow;
    }

    public int getMaxIncomingStreams() {
        return this.__maxIncomingStreams;
    }

    public int getRequestedOutboundStreams() {
        return this.__requestedOutboundStreams;
    }

    public SctpCapabilities(int i, int i2, long j) {
        this.__requestedOutboundStreams = i;
        this.__maxIncomingStreams = i2;
        this.__advertisedReceiverWindow = j;
    }
}
