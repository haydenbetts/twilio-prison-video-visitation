package tvi.webrtc;

public interface VideoSink {
    @CalledByNative
    void onFrame(VideoFrame videoFrame);
}
