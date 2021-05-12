package fm.liveswitch;

import fm.liveswitch.IAudioTrack;
import fm.liveswitch.IVideoTrack;

public interface ILocalMedia<TLocalMedia, TIAudioTrack extends IAudioTrack, TIVideoTrack extends IVideoTrack> extends IMedia<TIAudioTrack, TIVideoTrack> {
    void addOnAudioStarted(IAction0 iAction0);

    void addOnAudioStopped(IAction0 iAction0);

    void addOnVideoStarted(IAction0 iAction0);

    void addOnVideoStopped(IAction0 iAction0);

    Future<Object> changeAudioSourceInput(SourceInput sourceInput);

    Future<Object> changeVideoSourceInput(SourceInput sourceInput);

    AudioEncodingConfig getAudioEncoding();

    AudioEncodingConfig[] getAudioEncodings();

    boolean getAudioSimulcastDisabled();

    int getAudioSimulcastEncodingCount();

    int getAudioSimulcastPreferredBitrate();

    SourceInput getAudioSourceInput();

    Future<SourceInput[]> getAudioSourceInputs();

    LocalMediaState getState();

    VideoEncodingConfig getVideoEncoding();

    VideoEncodingConfig[] getVideoEncodings();

    double getVideoSimulcastBitsPerPixel();

    VideoDegradationPreference getVideoSimulcastDegradationPreference();

    boolean getVideoSimulcastDisabled();

    int getVideoSimulcastEncodingCount();

    int getVideoSimulcastPreferredBitrate();

    SourceInput getVideoSourceInput();

    Future<SourceInput[]> getVideoSourceInputs();

    void removeOnAudioStarted(IAction0 iAction0);

    void removeOnAudioStopped(IAction0 iAction0);

    void removeOnVideoStarted(IAction0 iAction0);

    void removeOnVideoStopped(IAction0 iAction0);

    void setAudioEncodings(AudioEncodingConfig[] audioEncodingConfigArr);

    void setAudioSimulcastDisabled(boolean z);

    void setAudioSimulcastEncodingCount(int i);

    void setAudioSimulcastPreferredBitrate(int i);

    void setAudioSourceInput(SourceInput sourceInput);

    void setVideoEncodings(VideoEncodingConfig[] videoEncodingConfigArr);

    void setVideoSimulcastBitsPerPixel(double d);

    void setVideoSimulcastDegradationPreference(VideoDegradationPreference videoDegradationPreference);

    void setVideoSimulcastDisabled(boolean z);

    void setVideoSimulcastEncodingCount(int i);

    void setVideoSimulcastPreferredBitrate(int i);

    void setVideoSourceInput(SourceInput sourceInput);

    Future<TLocalMedia> start();

    Future<TLocalMedia> stop();
}
