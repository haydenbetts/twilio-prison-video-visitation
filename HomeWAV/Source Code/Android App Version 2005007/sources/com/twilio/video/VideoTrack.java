package com.twilio.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tvi.webrtc.VideoRenderer;

public abstract class VideoTrack implements Track {
    private static final Logger logger = Logger.getLogger(VideoTrack.class);
    private boolean isEnabled;
    private boolean isReleased = false;
    private final String name;
    private Map<VideoRenderer, VideoRenderer> videoRenderersMap = new HashMap();
    private final tvi.webrtc.VideoTrack webRtcVideoTrack;

    VideoTrack(tvi.webrtc.VideoTrack videoTrack, boolean z, String str) {
        this.isEnabled = z;
        this.name = str;
        this.webRtcVideoTrack = videoTrack;
    }

    public synchronized void addRenderer(VideoRenderer videoRenderer) {
        Preconditions.checkNotNull(videoRenderer, "Video renderer must not be null");
        if (!this.isReleased) {
            VideoRenderer createWebRtcVideoRenderer = createWebRtcVideoRenderer(videoRenderer);
            this.videoRenderersMap.put(videoRenderer, createWebRtcVideoRenderer);
            tvi.webrtc.VideoTrack videoTrack = this.webRtcVideoTrack;
            if (videoTrack != null) {
                videoTrack.addRenderer(createWebRtcVideoRenderer);
            }
        } else {
            logger.w("Attempting to add renderer to track that has been removed");
        }
    }

    public synchronized void removeRenderer(VideoRenderer videoRenderer) {
        Preconditions.checkNotNull(videoRenderer, "Video renderer must not be null");
        if (!this.isReleased) {
            VideoRenderer remove = this.videoRenderersMap.remove(videoRenderer);
            tvi.webrtc.VideoTrack videoTrack = this.webRtcVideoTrack;
            if (!(videoTrack == null || remove == null)) {
                videoTrack.removeRenderer(remove);
            }
        } else {
            logger.w("Attempting to remove renderer from track that has been removed");
        }
    }

    public synchronized List<VideoRenderer> getRenderers() {
        return new ArrayList(this.videoRenderersMap.keySet());
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void release() {
        if (!this.isReleased) {
            invalidateWebRtcTrack();
            this.videoRenderersMap.clear();
            this.isReleased = true;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void invalidateWebRtcTrack() {
        if (this.webRtcVideoTrack != null) {
            for (Map.Entry<VideoRenderer, VideoRenderer> value : this.videoRenderersMap.entrySet()) {
                this.webRtcVideoTrack.removeRenderer((VideoRenderer) value.getValue());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized tvi.webrtc.VideoTrack getWebRtcTrack() {
        return this.webRtcVideoTrack;
    }

    private VideoRenderer createWebRtcVideoRenderer(VideoRenderer videoRenderer) {
        return new VideoRenderer(new VideoRendererCallbackAdapter(videoRenderer));
    }

    private class VideoRendererCallbackAdapter implements VideoRenderer.Callbacks {
        private final VideoRenderer videoRenderer;

        VideoRendererCallbackAdapter(VideoRenderer videoRenderer2) {
            this.videoRenderer = videoRenderer2;
        }

        public void renderFrame(VideoRenderer.I420Frame i420Frame) {
            this.videoRenderer.renderFrame(new I420Frame(i420Frame));
        }
    }
}
