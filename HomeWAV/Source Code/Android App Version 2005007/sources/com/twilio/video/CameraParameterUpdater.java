package com.twilio.video;

import android.hardware.Camera;

public interface CameraParameterUpdater {
    void apply(Camera.Parameters parameters);
}
