package fm.liveswitch;

public interface IConnection<TConnection, TStream, TAudioStream, TVideoStream, TDataStream> {
    void addIceServer(IceServer iceServer);

    void addIceServers(IceServer[] iceServerArr);

    void addOnExternalIdChange(IAction2<String, String> iAction2);

    void addOnGatheringStateChange(IAction1<TConnection> iAction1);

    void addOnIceConnectionStateChange(IAction1<TConnection> iAction1);

    void addOnLocalCandidate(IAction2<TConnection, Candidate> iAction2);

    void addOnLocalDescription(IAction2<TConnection, SessionDescription> iAction2);

    void addOnRemoteCandidate(IAction2<TConnection, Candidate> iAction2);

    void addOnRemoteDescription(IAction2<TConnection, SessionDescription> iAction2);

    void addOnSignallingStateChange(IAction1<TConnection> iAction1);

    void addOnStateChange(IAction1<TConnection> iAction1);

    Future<Candidate> addRemoteCandidate(Candidate candidate);

    boolean close();

    Future<SessionDescription> createAnswer();

    Future<SessionDescription> createOffer();

    TAudioStream getAudioStream();

    TAudioStream[] getAudioStreams();

    BundlePolicy getBundlePolicy();

    String getCanonicalName();

    TDataStream getDataStream();

    TDataStream[] getDataStreams();

    int getDeadStreamTimeout();

    Error getError();

    String getExternalId();

    IceGatheringState getGatheringState();

    boolean getHasAudio();

    boolean getHasData();

    boolean getHasVideo();

    IceConnectionState getIceConnectionState();

    IceGatherPolicy getIceGatherPolicy();

    IceServer getIceServer();

    IceServer[] getIceServers();

    String getId();

    boolean getLegacyTimeout();

    SessionDescription getLocalDescription();

    SessionDescription getRemoteDescription();

    SignallingState getSignallingState();

    ConnectionState getState();

    Future<ConnectionStats> getStats();

    TStream[] getStreams();

    String getTieBreaker();

    int getTimeout();

    TrickleIcePolicy getTrickleIcePolicy();

    TVideoStream getVideoStream();

    TVideoStream[] getVideoStreams();

    void removeIceServer(IceServer iceServer);

    void removeIceServers(IceServer[] iceServerArr);

    void removeOnExternalIdChange(IAction2<String, String> iAction2);

    void removeOnGatheringStateChange(IAction1<TConnection> iAction1);

    void removeOnIceConnectionStateChange(IAction1<TConnection> iAction1);

    void removeOnLocalCandidate(IAction2<TConnection, Candidate> iAction2);

    void removeOnLocalDescription(IAction2<TConnection, SessionDescription> iAction2);

    void removeOnRemoteCandidate(IAction2<TConnection, Candidate> iAction2);

    void removeOnRemoteDescription(IAction2<TConnection, SessionDescription> iAction2);

    void removeOnSignallingStateChange(IAction1<TConnection> iAction1);

    void removeOnStateChange(IAction1<TConnection> iAction1);

    void setBundlePolicy(BundlePolicy bundlePolicy);

    void setDeadStreamTimeout(int i);

    void setError(Error error);

    void setExternalId(String str);

    void setIceGatherPolicy(IceGatherPolicy iceGatherPolicy);

    void setIceServer(IceServer iceServer);

    void setIceServers(IceServer[] iceServerArr);

    void setLegacyTimeout(boolean z);

    Future<SessionDescription> setLocalDescription(SessionDescription sessionDescription);

    Future<SessionDescription> setRemoteDescription(SessionDescription sessionDescription);

    void setTieBreaker(String str);

    void setTimeout(int i);

    void setTrickleIcePolicy(TrickleIcePolicy trickleIcePolicy);
}
