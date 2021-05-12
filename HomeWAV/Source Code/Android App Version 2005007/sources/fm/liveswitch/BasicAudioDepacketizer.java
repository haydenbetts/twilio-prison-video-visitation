package fm.liveswitch;

public class BasicAudioDepacketizer extends AudioDepacketizer {
    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "Basic Audio Depacketizer";
    }

    public BasicAudioDepacketizer(AudioFormat audioFormat) {
        super(audioFormat);
    }

    public BasicAudioDepacketizer(IAudioOutput iAudioOutput) {
        super(iAudioOutput);
    }

    public BasicAudioDepacketizer(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(audioFormat, audioFormat2);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioBuffer clone = audioBuffer.clone();
        clone.setDataBuffers(audioBuffer.getDataBuffers());
        clone.setFormat(super.getOutputFormat());
        audioFrame.addBuffer(clone);
        raiseFrame(audioFrame);
    }
}
