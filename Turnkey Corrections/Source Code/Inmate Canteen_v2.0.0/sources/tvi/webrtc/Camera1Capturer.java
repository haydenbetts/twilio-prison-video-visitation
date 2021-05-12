package tvi.webrtc;

import android.content.Context;
import android.media.MediaRecorder;
import javax.annotation.Nullable;
import tvi.webrtc.CameraSession;
import tvi.webrtc.CameraVideoCapturer;
import tvi.webrtc.VideoCapturer;

public class Camera1Capturer extends CameraCapturer {
    private final boolean captureToTexture;

    public /* bridge */ /* synthetic */ void addMediaRecorderToCamera(MediaRecorder mediaRecorder, CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        super.addMediaRecorderToCamera(mediaRecorder, mediaRecorderHandler);
    }

    public /* bridge */ /* synthetic */ void changeCaptureFormat(int i, int i2, int i3) {
        super.changeCaptureFormat(i, i2, i3);
    }

    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public /* bridge */ /* synthetic */ void initialize(@Nullable SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        super.initialize(surfaceTextureHelper, context, capturerObserver);
    }

    public /* bridge */ /* synthetic */ boolean isScreencast() {
        return super.isScreencast();
    }

    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    public /* bridge */ /* synthetic */ void removeMediaRecorderFromCamera(CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        super.removeMediaRecorderFromCamera(mediaRecorderHandler);
    }

    public /* bridge */ /* synthetic */ void startCapture(int i, int i2, int i3) {
        super.startCapture(i, i2, i3);
    }

    public /* bridge */ /* synthetic */ void stopCapture() {
        super.stopCapture();
    }

    public /* bridge */ /* synthetic */ void switchCamera(String str, CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        super.switchCamera(str, cameraSwitchHandler);
    }

    public /* bridge */ /* synthetic */ void switchCamera(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        super.switchCamera(cameraSwitchHandler);
    }

    public Camera1Capturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, boolean z) {
        super(str, cameraEventsHandler, new Camera1Enumerator(z));
        this.captureToTexture = z;
    }

    /* access modifiers changed from: protected */
    public void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, @Nullable MediaRecorder mediaRecorder, String str, int i, int i2, int i3) {
        Camera1Session.create(createSessionCallback, events, this.captureToTexture || mediaRecorder != null, context, surfaceTextureHelper, mediaRecorder, Camera1Enumerator.getCameraIndex(str), i, i2, i3);
    }

    @Nullable
    public Camera1Session getCameraSession() {
        return (Camera1Session) super.getCameraSession();
    }
}
