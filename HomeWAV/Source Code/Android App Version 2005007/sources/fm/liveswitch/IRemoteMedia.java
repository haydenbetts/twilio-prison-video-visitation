package fm.liveswitch;

import fm.liveswitch.IAudioTrack;
import fm.liveswitch.IVideoTrack;

public interface IRemoteMedia<TIAudioTrack extends IAudioTrack, TIVideoTrack extends IVideoTrack> extends IMedia<TIAudioTrack, TIVideoTrack> {
    Future<Object> changeAudioSinkOutput(SinkOutput sinkOutput);

    Future<Object> changeVideoSinkOutput(SinkOutput sinkOutput);

    SinkOutput getAudioSinkOutput();

    Future<SinkOutput[]> getAudioSinkOutputs();

    SinkOutput getVideoSinkOutput();

    Future<SinkOutput[]> getVideoSinkOutputs();

    void setAudioSinkOutput(SinkOutput sinkOutput);

    void setVideoSinkOutput(SinkOutput sinkOutput);
}
