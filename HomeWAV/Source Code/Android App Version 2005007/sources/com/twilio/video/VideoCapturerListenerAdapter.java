package com.twilio.video;

import com.twilio.video.VideoCapturer;
import tvi.webrtc.VideoCapturer;

final class VideoCapturerListenerAdapter implements VideoCapturer.Listener {
    private final VideoCapturer.CapturerObserver webRtcCapturerObserver;

    public VideoCapturerListenerAdapter(VideoCapturer.CapturerObserver capturerObserver) {
        this.webRtcCapturerObserver = capturerObserver;
    }

    public void onCapturerStarted(boolean z) {
        this.webRtcCapturerObserver.onCapturerStarted(z);
    }

    public void onFrameCaptured(VideoFrame videoFrame) {
        if (videoFrame.webRtcVideoFrame != null) {
            this.webRtcCapturerObserver.onFrameCaptured(videoFrame.webRtcVideoFrame);
        } else {
            this.webRtcCapturerObserver.onByteBufferFrameCaptured(videoFrame.imageBuffer, videoFrame.dimensions.width, videoFrame.dimensions.height, videoFrame.orientation.getValue(), videoFrame.timestamp);
        }
    }
}
