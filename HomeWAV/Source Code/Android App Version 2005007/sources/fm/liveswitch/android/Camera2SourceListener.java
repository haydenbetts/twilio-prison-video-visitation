package fm.liveswitch.android;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

public interface Camera2SourceListener {
    void onCameraCaptureSession(CameraCaptureSession cameraCaptureSession);

    void onCameraRequestBuilder(CaptureRequest.Builder builder);
}
