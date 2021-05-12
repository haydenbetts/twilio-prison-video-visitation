package com.twilio.video;

import androidx.annotation.NonNull;

public interface VideoRenderer {

    public interface Listener {
        void onFirstFrame();

        void onFrameDimensionsChanged(int i, int i2, int i3);
    }

    void renderFrame(@NonNull I420Frame i420Frame);
}
