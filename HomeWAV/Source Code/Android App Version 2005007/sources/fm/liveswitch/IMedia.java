package fm.liveswitch;

import fm.liveswitch.IAudioTrack;
import fm.liveswitch.IVideoTrack;

public interface IMedia<TIAudioTrack extends IAudioTrack, TIVideoTrack extends IVideoTrack> {
    void addOnAudioDestroyed(IAction0 iAction0);

    void addOnAudioLevel(IAction1<Double> iAction1);

    void addOnVideoDestroyed(IAction0 iAction0);

    void addOnVideoSize(IAction1<Size> iAction1);

    void destroy();

    double getAudioGain();

    boolean getAudioMuted();

    TIAudioTrack getAudioTrack();

    TIAudioTrack[] getAudioTracks();

    double getAudioVolume();

    String getId();

    boolean getVideoMuted();

    Size getVideoSize();

    TIVideoTrack getVideoTrack();

    TIVideoTrack[] getVideoTracks();

    Future<VideoBuffer> grabVideoFrame();

    void removeOnAudioDestroyed(IAction0 iAction0);

    void removeOnAudioLevel(IAction1<Double> iAction1);

    void removeOnVideoDestroyed(IAction0 iAction0);

    void removeOnVideoSize(IAction1<Size> iAction1);

    void setAudioGain(double d);

    void setAudioMuted(boolean z);

    void setAudioVolume(double d);

    void setId(String str);

    void setVideoMuted(boolean z);
}
