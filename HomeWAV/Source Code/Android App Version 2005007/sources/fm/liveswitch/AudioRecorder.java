package fm.liveswitch;

public abstract class AudioRecorder extends MediaRecorder<AudioBuffer, AudioFormat> {
    public AudioRecorder(String str) {
        super(str);
    }
}
