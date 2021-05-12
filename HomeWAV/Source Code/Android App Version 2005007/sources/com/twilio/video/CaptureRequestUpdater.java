package com.twilio.video;

import android.hardware.camera2.CaptureRequest;

public interface CaptureRequestUpdater {
    void apply(CaptureRequest.Builder builder);
}
