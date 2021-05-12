package fm.liveswitch;

interface IMediaTransport {
    void addOnStateChange(IAction1<IMediaTransport> iAction1);

    String getId();

    RtpIParameters getParameters();

    MediaTransportState getState();

    void removeOnStateChange(IAction1<IMediaTransport> iAction1);
}
