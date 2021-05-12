package fm.liveswitch;

import fm.liveswitch.IAudioTrack;
import fm.liveswitch.IVideoTrack;

public abstract class MediaBase<TIAudioTrack extends IAudioTrack, TIVideoTrack extends IVideoTrack> extends Dynamic implements IMedia<TIAudioTrack, TIVideoTrack> {
    private String _id;

    public abstract void addOnAudioDestroyed(IAction0 iAction0);

    public abstract void addOnAudioLevel(IAction1<Double> iAction1);

    public abstract void addOnVideoDestroyed(IAction0 iAction0);

    public abstract void addOnVideoSize(IAction1<Size> iAction1);

    public abstract void destroy();

    public abstract TIAudioTrack[] getAudioTracks();

    public abstract Size getVideoSize();

    public abstract TIVideoTrack[] getVideoTracks();

    public abstract Future<VideoBuffer> grabVideoFrame();

    public abstract void removeOnAudioDestroyed(IAction0 iAction0);

    public abstract void removeOnAudioLevel(IAction1<Double> iAction1);

    public abstract void removeOnVideoDestroyed(IAction0 iAction0);

    public abstract void removeOnVideoSize(IAction1<Size> iAction1);

    public double getAudioGain() {
        IAudioTrack audioTrack = getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getGain();
        }
        return 1.0d;
    }

    public boolean getAudioMuted() {
        IAudioTrack audioTrack = getAudioTrack();
        return audioTrack != null && audioTrack.getMuted();
    }

    public TIAudioTrack getAudioTrack() {
        return (IAudioTrack) Utility.firstOrDefault((T[]) getAudioTracks());
    }

    public double getAudioVolume() {
        IAudioTrack audioTrack = getAudioTrack();
        if (audioTrack != null) {
            return audioTrack.getVolume();
        }
        return 1.0d;
    }

    public String getId() {
        return this._id;
    }

    public boolean getVideoMuted() {
        IVideoTrack videoTrack = getVideoTrack();
        return videoTrack != null && videoTrack.getMuted();
    }

    public TIVideoTrack getVideoTrack() {
        return (IVideoTrack) Utility.firstOrDefault((T[]) getVideoTracks());
    }

    public MediaBase() {
        setId(Utility.generateId());
    }

    public void setAudioGain(double d) {
        IAudioTrack audioTrack = getAudioTrack();
        if (audioTrack != null) {
            audioTrack.setGain(d);
        }
    }

    public void setAudioMuted(boolean z) {
        IAudioTrack audioTrack = getAudioTrack();
        if (audioTrack != null) {
            audioTrack.setMuted(z);
        }
    }

    public void setAudioVolume(double d) {
        IAudioTrack audioTrack = getAudioTrack();
        if (audioTrack != null) {
            audioTrack.setVolume(d);
        }
    }

    public void setId(String str) {
        this._id = str;
    }

    public void setVideoMuted(boolean z) {
        IVideoTrack videoTrack = getVideoTrack();
        if (videoTrack != null) {
            videoTrack.setMuted(z);
        }
    }
}
