package fm.liveswitch;

public interface IStream {
    void addOnDirectionChange(IAction0 iAction0);

    void addOnStateChange(IAction0 iAction0);

    Error changeDirection(StreamDirection streamDirection);

    StreamDirection getDirection();

    String getExternalId();

    String getId();

    String getLabel();

    StreamDirection getLocalDirection();

    boolean getLocalReceive();

    boolean getLocalSend();

    String getMediaDescriptionId();

    StreamDirection getRemoteDirection();

    boolean getRemoteReceive();

    boolean getRemoteSend();

    StreamState getState();

    String getTag();

    TransportInfo getTransportInfo();

    StreamType getType();

    void removeOnDirectionChange(IAction0 iAction0);

    void removeOnStateChange(IAction0 iAction0);

    void setExternalId(String str);

    void setLocalDirection(StreamDirection streamDirection);

    void setLocalReceive(boolean z);

    void setLocalSend(boolean z);

    void setTag(String str);
}
