package com.twilio.video;

import androidx.annotation.NonNull;
import java.util.List;

public interface VideoCapturer {

    public interface Listener {
        void onCapturerStarted(boolean z);

        void onFrameCaptured(@NonNull VideoFrame videoFrame);
    }

    @NonNull
    List<VideoFormat> getSupportedFormats();

    boolean isScreencast();

    void startCapture(@NonNull VideoFormat videoFormat, @NonNull Listener listener);

    void stopCapture();
}
