package com.twilio.video;

import android.hardware.Camera;
import androidx.annotation.NonNull;

public interface CameraParameterUpdater {
    void apply(@NonNull Camera.Parameters parameters);
}
