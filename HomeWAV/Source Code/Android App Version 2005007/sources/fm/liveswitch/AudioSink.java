package fm.liveswitch;

public abstract class AudioSink extends MediaSink<IAudioOutput, IAudioOutputCollection, IAudioInput, AudioSink, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> implements IAudioInput, IMediaInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    private double __gain = 1.0d;

    public AudioSink() {
    }

    public AudioSink(AudioFormat audioFormat) {
        super(audioFormat);
    }

    /* access modifiers changed from: protected */
    public IAudioOutputCollection createOutputCollection(IAudioInput iAudioInput) {
        return new IAudioOutputCollection(iAudioInput);
    }

    public AudioConfig getConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getInputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    public double getGain() {
        return this.__gain;
    }

    public boolean processFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer;
        if (!(!((AudioFormat) super.getInputFormat()).getIsPcm() || (audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName())) == null || getGain() == 1.0d)) {
            audioBuffer.applyGain(getGain());
        }
        return super.processFrame(audioFrame);
    }

    public void setGain(double d) {
        this.__gain = MathAssistant.max(d, 0.0d);
    }
}
