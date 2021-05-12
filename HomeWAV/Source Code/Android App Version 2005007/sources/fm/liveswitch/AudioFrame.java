package fm.liveswitch;

import fm.liveswitch.pcm.Format;

public class AudioFrame extends MediaFrame<AudioBuffer, AudioBufferCollection, AudioFormat, AudioFrame> {
    private int __duration;

    public AudioFrame(int i) {
        setDuration(i);
    }

    public AudioFrame(int i, AudioBuffer audioBuffer) {
        super(audioBuffer);
        setDuration(i);
    }

    public AudioFrame(int i, AudioBuffer[] audioBufferArr) {
        super((TBuffer[]) audioBufferArr);
        setDuration(i);
    }

    public static int calculateTimestampDelta(int i, int i2) {
        return (i2 * i) / Constants.getMillisecondsPerSecond();
    }

    public AudioFrame clone() {
        AudioFrame audioFrame = (AudioFrame) super.clone();
        audioFrame.setDuration(getDuration());
        return audioFrame;
    }

    public AudioFrame createInstance() {
        return new AudioFrame(-1);
    }

    /* access modifiers changed from: protected */
    public AudioBufferCollection createMediaBufferCollection() {
        return new AudioBufferCollection();
    }

    public static AudioFrame generatePcmFrame(int i, AudioConfig audioConfig) {
        return new AudioFrame(i, new AudioBuffer(DataBuffer.allocate(SoundUtility.calculateDataLength(i, audioConfig), true), (AudioFormat) new Format(audioConfig)));
    }

    public int getDuration() {
        AudioBuffer audioBuffer;
        if (this.__duration == -1 && (audioBuffer = (AudioBuffer) super.getBuffer(AudioFormat.getPcmName())) != null) {
            this.__duration = SoundUtility.calculateDuration(audioBuffer.getDataBuffer().getLength(), ((AudioFormat) audioBuffer.getFormat()).getConfig());
        }
        return this.__duration;
    }

    public void setDuration(int i) {
        this.__duration = i;
    }

    public String toString() {
        return StringExtensions.concat("Audio Frame (", IntegerExtensions.toString(Integer.valueOf(getDuration())), "ms)");
    }
}
