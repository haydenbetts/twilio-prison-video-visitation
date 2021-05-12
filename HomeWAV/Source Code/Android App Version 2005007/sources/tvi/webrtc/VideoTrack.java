package tvi.webrtc;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

@JNINamespace("webrtc::jni")
public class VideoTrack extends MediaStreamTrack {
    private final List<VideoRenderer> renderers = new ArrayList();
    private final IdentityHashMap<VideoSink, Long> sinks = new IdentityHashMap<>();

    private static native void nativeAddSink(long j, long j2);

    private static native void nativeFreeSink(long j);

    private static native void nativeRemoveSink(long j, long j2);

    private static native long nativeWrapSink(VideoSink videoSink);

    public VideoTrack(long j) {
        super(j);
    }

    public void addSink(VideoSink videoSink) {
        long nativeWrapSink = nativeWrapSink(videoSink);
        this.sinks.put(videoSink, Long.valueOf(nativeWrapSink));
        nativeAddSink(this.nativeTrack, nativeWrapSink);
    }

    public void removeSink(VideoSink videoSink) {
        long longValue = this.sinks.remove(videoSink).longValue();
        if (longValue != 0) {
            nativeRemoveSink(this.nativeTrack, longValue);
            nativeFreeSink(longValue);
        }
    }

    public void addRenderer(VideoRenderer videoRenderer) {
        this.renderers.add(videoRenderer);
        nativeAddSink(this.nativeTrack, videoRenderer.nativeVideoRenderer);
    }

    public void removeRenderer(VideoRenderer videoRenderer) {
        if (this.renderers.remove(videoRenderer)) {
            nativeRemoveSink(this.nativeTrack, videoRenderer.nativeVideoRenderer);
            videoRenderer.dispose();
        }
    }

    public void dispose() {
        for (VideoRenderer next : this.renderers) {
            nativeRemoveSink(this.nativeTrack, next.nativeVideoRenderer);
            next.dispose();
        }
        this.renderers.clear();
        for (Long longValue : this.sinks.values()) {
            long longValue2 = longValue.longValue();
            nativeRemoveSink(this.nativeTrack, longValue2);
            nativeFreeSink(longValue2);
        }
        this.sinks.clear();
        super.dispose();
    }
}
