package fm.liveswitch;

public interface IDataChannel<TDataChannel> {
    void addOnStateChange(IAction1<TDataChannel> iAction1);

    String getId();

    DataChannelInfo getInfo();

    String getLabel();

    IAction1<DataChannelReceiveArgs> getOnReceive();

    boolean getOrdered();

    DataChannelState getState();

    String getSubprotocol();

    void removeOnStateChange(IAction1<TDataChannel> iAction1);

    Future<Object> sendDataBytes(DataBuffer dataBuffer);

    Future<Object> sendDataString(String str);

    void setOnReceive(IAction1<DataChannelReceiveArgs> iAction1);
}
