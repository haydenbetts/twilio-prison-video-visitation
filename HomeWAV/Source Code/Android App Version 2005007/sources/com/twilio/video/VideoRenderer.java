package com.twilio.video;

public interface VideoRenderer {

    public interface Listener {
        void onFirstFrame();

        void onFrameDimensionsChanged(int i, int i2, int i3);
    }

    void renderFrame(I420Frame i420Frame);
}
