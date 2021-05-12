package fm.liveswitch;

public interface IMediaStream extends IStream {
    void addOnDiscardBitrateNotification(IAction1<BitrateNotification> iAction1);

    void addOnDiscardBitrateRequest(IAction1<BitrateRequest> iAction1);

    void addOnLocalEncodingDisabled(IAction1<EncodingInfo> iAction1);

    void addOnLocalEncodingEnabled(IAction1<EncodingInfo> iAction1);

    boolean getCodecDisabled(String str);

    TransportInfo getControlTransportInfo();

    MediaStreamInfo getInfo();

    boolean getInputMuted();

    int getLocalBandwidth();

    String getLocalCanonicalName();

    int getMaxReceiveBitrate();

    int getMaxSendBitrate();

    boolean getOutputMuted();

    String[] getPreferredCodecs();

    int getRemoteBandwidth();

    String getRemoteCanonicalName();

    EncodingInfo getRemoteEncoding();

    SimulcastMode getSimulcastMode();

    boolean raiseBitrateNotification(BitrateNotification bitrateNotification);

    boolean raiseBitrateRequest(BitrateRequest bitrateRequest);

    void removeOnDiscardBitrateNotification(IAction1<BitrateNotification> iAction1);

    void removeOnDiscardBitrateRequest(IAction1<BitrateRequest> iAction1);

    void removeOnLocalEncodingDisabled(IAction1<EncodingInfo> iAction1);

    void removeOnLocalEncodingEnabled(IAction1<EncodingInfo> iAction1);

    void setCodecDisabled(String str, boolean z);

    void setInputMuted(boolean z);

    void setLocalBandwidth(int i);

    void setMaxReceiveBitrate(int i);

    void setMaxSendBitrate(int i);

    void setOutputMuted(boolean z);

    void setPreferredCodecs(String[] strArr);

    void setRemoteEncoding(EncodingInfo encodingInfo);

    void setSimulcastMode(SimulcastMode simulcastMode);
}
