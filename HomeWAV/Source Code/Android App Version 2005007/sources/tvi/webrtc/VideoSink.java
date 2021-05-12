package tvi.webrtc;

public interface VideoSink {
    void onFrame(VideoFrame videoFrame);
}
