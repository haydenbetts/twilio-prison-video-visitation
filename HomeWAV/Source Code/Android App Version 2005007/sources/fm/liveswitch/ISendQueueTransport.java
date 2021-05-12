package fm.liveswitch;

interface ISendQueueTransport {
    void endBatch();

    boolean getIsConnected();

    Future<SendItem> send(SendItem sendItem);

    void startBatch();
}
