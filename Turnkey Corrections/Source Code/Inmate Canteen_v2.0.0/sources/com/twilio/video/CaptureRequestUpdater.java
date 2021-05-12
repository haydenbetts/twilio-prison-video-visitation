package com.twilio.video;

import android.annotation.TargetApi;
import android.hardware.camera2.CaptureRequest;
import androidx.annotation.NonNull;

@TargetApi(21)
public interface CaptureRequestUpdater {
    void apply(@NonNull CaptureRequest.Builder builder);
}
