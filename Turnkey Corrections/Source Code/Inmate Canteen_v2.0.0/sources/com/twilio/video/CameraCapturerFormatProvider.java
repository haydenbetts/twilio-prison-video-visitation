package com.twilio.video;

import android.hardware.Camera;
import com.twilio.video.CameraCapturer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tvi.webrtc.Camera1Enumerator;

class CameraCapturerFormatProvider {
    private static final Logger logger = Logger.getLogger(CameraCapturerFormatProvider.class);
    private final Camera1Enumerator camera1Enumerator = new Camera1Enumerator(false);
    private final Map<CameraCapturer.CameraSource, List<VideoFormat>> supportedFormatsMap = new HashMap();

    CameraCapturerFormatProvider() {
    }

    /* access modifiers changed from: package-private */
    public int getCameraId(CameraCapturer.CameraSource cameraSource) {
        String[] deviceNames = this.camera1Enumerator.getDeviceNames();
        for (int i = 0; i < deviceNames.length; i++) {
            if (this.camera1Enumerator.isFrontFacing(deviceNames[i]) && cameraSource == CameraCapturer.CameraSource.FRONT_CAMERA) {
                return i;
            }
            if (this.camera1Enumerator.isBackFacing(deviceNames[i]) && cameraSource == CameraCapturer.CameraSource.BACK_CAMERA) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public String getDeviceName(int i) {
        String[] deviceNames = this.camera1Enumerator.getDeviceNames();
        if (i >= 0 && i < deviceNames.length) {
            return deviceNames[i];
        }
        throw new IllegalArgumentException("cameraId not available on this device");
    }

    /* access modifiers changed from: package-private */
    public List<VideoFormat> getSupportedFormats(CameraCapturer.CameraSource cameraSource) {
        List<VideoFormat> list = this.supportedFormatsMap.get(cameraSource);
        if (list != null) {
            return list;
        }
        List<VideoFormat> supportedFormats = getSupportedFormats(getCameraId(cameraSource));
        this.supportedFormatsMap.put(cameraSource, supportedFormats);
        return supportedFormats;
    }

    private List<VideoFormat> getSupportedFormats(int i) {
        ArrayList arrayList = new ArrayList();
        Camera camera = null;
        try {
            Camera open = Camera.open(i);
            Camera.Parameters parameters = open.getParameters();
            if (open != null) {
                open.release();
            }
            int i2 = 0;
            List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
            if (supportedPreviewFpsRange != null) {
                i2 = (supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1)[1] + 999) / 1000;
            }
            for (Camera.Size next : parameters.getSupportedPreviewSizes()) {
                arrayList.add(new VideoFormat(new VideoDimensions(next.width, next.height), i2, VideoPixelFormat.NV21));
            }
            return arrayList;
        } catch (RuntimeException e) {
            logger.e(e.getMessage());
            if (camera != null) {
                camera.release();
            }
            return arrayList;
        } catch (Throwable th) {
            if (camera != null) {
                camera.release();
            }
            throw th;
        }
    }
}
