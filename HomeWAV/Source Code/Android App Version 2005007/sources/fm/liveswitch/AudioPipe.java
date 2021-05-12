package fm.liveswitch;

public abstract class AudioPipe extends MediaPipe<IAudioOutput, IAudioOutputCollection, IAudioInput, IAudioInputCollection, AudioPipe, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat> implements IAudioInput, IMediaInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IInput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioOutput, IMediaOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IOutput<IAudioOutput, IAudioInput, AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat>, IAudioElement, IMediaElement, IElement {
    private double __gain = 1.0d;
    private double __volume = 1.0d;

    public AudioPipe(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(audioFormat, audioFormat2);
    }

    public AudioPipe(AudioFormat audioFormat) {
        super(null, audioFormat);
    }

    /* access modifiers changed from: protected */
    public AudioFrame createFrame(AudioBuffer audioBuffer) {
        return new AudioFrame(-1, audioBuffer);
    }

    /* access modifiers changed from: protected */
    public IAudioInputCollection createInputCollection(IAudioOutput iAudioOutput) {
        return new IAudioInputCollection(iAudioOutput);
    }

    /* access modifiers changed from: protected */
    public IAudioOutputCollection createOutputCollection(IAudioInput iAudioInput) {
        return new IAudioOutputCollection(iAudioInput);
    }

    public AudioConfig getConfig() {
        return getOutputConfig();
    }

    public double getGain() {
        return this.__gain;
    }

    public AudioConfig getInputConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getInputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    public AudioConfig getOutputConfig() {
        AudioFormat audioFormat = (AudioFormat) super.getOutputFormat();
        if (audioFormat != null) {
            return audioFormat.getConfig();
        }
        return null;
    }

    public double getVolume() {
        return this.__volume;
    }

    public boolean processFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer;
        if (!(!((AudioFormat) super.getInputFormat()).getIsPcm() || (audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName())) == null || getGain() == 1.0d)) {
            audioBuffer.applyGain(getGain());
        }
        return super.processFrame(audioFrame);
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(AudioFrame audioFrame) {
        AudioBuffer audioBuffer;
        if (!(!((AudioFormat) super.getOutputFormat()).getIsPcm() || (audioBuffer = (AudioBuffer) audioFrame.getBuffer(AudioFormat.getPcmName())) == null || getVolume() == 1.0d)) {
            audioBuffer.applyGain(getVolume());
        }
        super.raiseFrame(audioFrame);
    }

    public void setGain(double d) {
        this.__gain = MathAssistant.max(d, 0.0d);
    }

    public void setVolume(double d) {
        this.__volume = MathAssistant.min(MathAssistant.max(d, 0.0d), 1.0d);
    }
}
