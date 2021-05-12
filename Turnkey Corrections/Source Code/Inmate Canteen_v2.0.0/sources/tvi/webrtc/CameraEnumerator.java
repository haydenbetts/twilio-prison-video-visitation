package tvi.webrtc;

import java.util.List;
import tvi.webrtc.CameraEnumerationAndroid;
import tvi.webrtc.CameraVideoCapturer;

public interface CameraEnumerator {
    CameraVideoCapturer createCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler);

    String[] getDeviceNames();

    List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String str);

    boolean isBackFacing(String str);

    boolean isFrontFacing(String str);
}
