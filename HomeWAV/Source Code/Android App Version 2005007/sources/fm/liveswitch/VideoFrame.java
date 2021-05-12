package fm.liveswitch;

public class VideoFrame extends MediaFrame<VideoBuffer, VideoBufferCollection, VideoFormat, VideoFrame> {
    public String toString() {
        return "Video Frame";
    }

    public VideoFrame clone() {
        return (VideoFrame) super.clone();
    }

    public VideoFrame createInstance() {
        return new VideoFrame();
    }

    /* access modifiers changed from: protected */
    public VideoBufferCollection createMediaBufferCollection() {
        return new VideoBufferCollection();
    }

    public static VideoFrame generateFrame(int i, int i2, String str) {
        return new VideoFrame(VideoBuffer.createBlack(i, i2, str));
    }

    public VideoFrame() {
    }

    public VideoFrame(VideoBuffer videoBuffer) {
        super(videoBuffer);
    }

    public VideoFrame(VideoBuffer[] videoBufferArr) {
        super((TBuffer[]) videoBufferArr);
    }
}
