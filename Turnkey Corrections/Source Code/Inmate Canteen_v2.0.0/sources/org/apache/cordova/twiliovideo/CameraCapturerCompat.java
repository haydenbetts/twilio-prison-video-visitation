package org.apache.cordova.twiliovideo;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.twilio.video.Camera2Capturer;
import com.twilio.video.CameraCapturer;
import com.twilio.video.VideoCapturer;
import tvi.webrtc.Camera2Enumerator;

public class CameraCapturerCompat {
    private Pair<CameraCapturer.CameraSource, String> backCameraPair;
    private CameraCapturer camera1Capturer;
    private Camera2Capturer camera2Capturer;
    private final Camera2Capturer.Listener camera2Listener = new Camera2Capturer.Listener() {
        public void onFirstFrameAvailable() {
            Log.i(TwilioVideo.TAG, "onFirstFrameAvailable");
        }

        public void onCameraSwitched(String str) {
            Log.i(TwilioVideo.TAG, "onCameraSwitched: newCameraId = " + str);
        }

        public void onError(Camera2Capturer.Exception exception) {
            Log.e(TwilioVideo.TAG, exception.getMessage());
        }
    };
    private Pair<CameraCapturer.CameraSource, String> frontCameraPair;

    public CameraCapturerCompat(Context context, CameraCapturer.CameraSource cameraSource) {
        if (Camera2Capturer.isSupported(context)) {
            setCameraPairs(context);
            this.camera2Capturer = new Camera2Capturer(context, getCameraId(cameraSource), this.camera2Listener);
            return;
        }
        this.camera1Capturer = new CameraCapturer(context, cameraSource);
    }

    public CameraCapturer.CameraSource getCameraSource() {
        if (usingCamera1()) {
            return this.camera1Capturer.getCameraSource();
        }
        return getCameraSource(this.camera2Capturer.getCameraId());
    }

    public void switchCamera() {
        if (usingCamera1()) {
            this.camera1Capturer.switchCamera();
        } else if (getCameraSource(this.camera2Capturer.getCameraId()) == CameraCapturer.CameraSource.FRONT_CAMERA) {
            this.camera2Capturer.switchCamera((String) this.backCameraPair.second);
        } else {
            this.camera2Capturer.switchCamera((String) this.frontCameraPair.second);
        }
    }

    public VideoCapturer getVideoCapturer() {
        if (usingCamera1()) {
            return this.camera1Capturer;
        }
        return this.camera2Capturer;
    }

    private boolean usingCamera1() {
        return this.camera1Capturer != null;
    }

    private void setCameraPairs(Context context) {
        Camera2Enumerator camera2Enumerator = new Camera2Enumerator(context);
        for (String str : camera2Enumerator.getDeviceNames()) {
            if (camera2Enumerator.isFrontFacing(str)) {
                this.frontCameraPair = new Pair<>(CameraCapturer.CameraSource.FRONT_CAMERA, str);
            }
            if (camera2Enumerator.isBackFacing(str)) {
                this.backCameraPair = new Pair<>(CameraCapturer.CameraSource.BACK_CAMERA, str);
            }
        }
    }

    private String getCameraId(CameraCapturer.CameraSource cameraSource) {
        if (this.frontCameraPair.first == cameraSource) {
            return (String) this.frontCameraPair.second;
        }
        return (String) this.backCameraPair.second;
    }

    private CameraCapturer.CameraSource getCameraSource(String str) {
        if (((String) this.frontCameraPair.second).equals(str)) {
            return (CameraCapturer.CameraSource) this.frontCameraPair.first;
        }
        return (CameraCapturer.CameraSource) this.backCameraPair.first;
    }
}
