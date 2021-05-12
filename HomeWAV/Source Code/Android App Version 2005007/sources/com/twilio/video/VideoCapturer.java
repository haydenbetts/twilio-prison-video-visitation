package com.twilio.video;

import java.util.List;

public interface VideoCapturer {

    public interface Listener {
        void onCapturerStarted(boolean z);

        void onFrameCaptured(VideoFrame videoFrame);
    }

    List<VideoFormat> getSupportedFormats();

    boolean isScreencast();

    void startCapture(VideoFormat videoFormat, Listener listener);

    void stopCapture();
}
