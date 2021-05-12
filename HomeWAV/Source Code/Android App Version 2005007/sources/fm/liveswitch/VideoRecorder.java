package fm.liveswitch;

public abstract class VideoRecorder extends MediaRecorder<VideoBuffer, VideoFormat> {
    public VideoRecorder(String str) {
        super(str);
    }
}
