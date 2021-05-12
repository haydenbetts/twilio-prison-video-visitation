package fm.liveswitch;

public interface IAudioOutput extends IMediaOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    AudioConfig getConfig();

    double getVolume();

    void setVolume(double d);
}
