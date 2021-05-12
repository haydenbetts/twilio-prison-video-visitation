package fm.liveswitch;

public interface IAudioInput extends IMediaInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    AudioConfig getConfig();

    double getGain();

    void setGain(double d);
}
