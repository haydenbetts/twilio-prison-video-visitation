package tvi.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import tvi.webrtc.CameraEnumerationAndroid;
import tvi.webrtc.CameraSession;
import tvi.webrtc.SurfaceTextureHelper;

@TargetApi(21)
public class Camera2Session implements CameraSession {
    private static final String TAG = "Camera2Session";
    private static final Histogram camera2ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera2.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    /* access modifiers changed from: private */
    public static final Histogram camera2StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StartTimeMs", 1, 10000, 50);
    private static final Histogram camera2StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StopTimeMs", 1, 10000, 50);
    private final Context applicationContext;
    /* access modifiers changed from: private */
    public final CameraSession.CreateSessionCallback callback;
    private CameraCharacteristics cameraCharacteristics;
    /* access modifiers changed from: private */
    @Nullable
    public CameraDevice cameraDevice;
    private final String cameraId;
    private final CameraManager cameraManager;
    /* access modifiers changed from: private */
    public int cameraOrientation;
    public final Handler cameraThreadHandler;
    /* access modifiers changed from: private */
    public CameraEnumerationAndroid.CaptureFormat captureFormat;
    /* access modifiers changed from: private */
    @Nullable
    public CameraCaptureSession captureSession;
    /* access modifiers changed from: private */
    public final long constructionTimeNs;
    /* access modifiers changed from: private */
    public final CameraSession.Events events;
    /* access modifiers changed from: private */
    public boolean firstFrameReported = false;
    private int fpsUnitFactor;
    private final int framerate;
    private final int height;
    /* access modifiers changed from: private */
    public boolean isCameraFrontFacing;
    /* access modifiers changed from: private */
    @Nullable
    public final Surface mediaRecorderSurface;
    /* access modifiers changed from: private */
    public SessionState state = SessionState.RUNNING;
    /* access modifiers changed from: private */
    @Nullable
    public Surface surface;
    /* access modifiers changed from: private */
    public final SurfaceTextureHelper surfaceTextureHelper;
    private final int width;

    private enum SessionState {
        RUNNING,
        STOPPED
    }

    private class CameraStateCallback extends CameraDevice.StateCallback {
        private CameraStateCallback() {
        }

        private String getErrorDescription(int i) {
            switch (i) {
                case 1:
                    return "Camera device is in use already.";
                case 2:
                    return "Camera device could not be opened because there are too many other open camera devices.";
                case 3:
                    return "Camera device could not be opened due to a device policy.";
                case 4:
                    return "Camera device has encountered a fatal error.";
                case 5:
                    return "Camera service has encountered a fatal error.";
                default:
                    return "Unknown camera error: " + i;
            }
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            boolean z = Camera2Session.this.captureSession == null && Camera2Session.this.state != SessionState.STOPPED;
            SessionState unused = Camera2Session.this.state = SessionState.STOPPED;
            Camera2Session.this.stopInternal();
            if (z) {
                Camera2Session.this.callback.onFailure(CameraSession.FailureType.DISCONNECTED, "Camera disconnected / evicted.");
            } else {
                Camera2Session.this.events.onCameraDisconnected(Camera2Session.this);
            }
        }

        public void onError(CameraDevice cameraDevice, int i) {
            Camera2Session.this.checkIsOnCameraThread();
            Camera2Session.this.reportError(getErrorDescription(i));
        }

        public void onOpened(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera opened.");
            CameraDevice unused = Camera2Session.this.cameraDevice = cameraDevice;
            SurfaceTexture surfaceTexture = Camera2Session.this.surfaceTextureHelper.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height);
            Surface unused2 = Camera2Session.this.surface = new Surface(surfaceTexture);
            ArrayList arrayList = new ArrayList();
            arrayList.add(Camera2Session.this.surface);
            if (Camera2Session.this.mediaRecorderSurface != null) {
                Logging.d(Camera2Session.TAG, "Add MediaRecorder surface to capture session.");
                arrayList.add(Camera2Session.this.mediaRecorderSurface);
            }
            try {
                cameraDevice.createCaptureSession(arrayList, new CaptureSessionCallback(), Camera2Session.this.cameraThreadHandler);
            } catch (CameraAccessException e) {
                Camera2Session camera2Session = Camera2Session.this;
                camera2Session.reportError("Failed to create capture session. " + e);
            }
        }

        public void onClosed(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera device closed.");
            Camera2Session.this.events.onCameraClosed(Camera2Session.this);
        }
    }

    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        private CaptureSessionCallback() {
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Camera2Session.this.checkIsOnCameraThread();
            cameraCaptureSession.close();
            Camera2Session.this.reportError("Failed to configure capture session.");
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera capture session configured.");
            CameraCaptureSession unused = Camera2Session.this.captureSession = cameraCaptureSession;
            Camera2Session camera2Session = Camera2Session.this;
            if (!camera2Session.setSessionRepeatingRequest(camera2Session.configureCaptureRequestBuilder())) {
                Camera2Session.this.reportError("Failed to start capture request.");
                return;
            }
            Camera2Session.this.surfaceTextureHelper.startListening(new SurfaceTextureHelper.OnTextureFrameAvailableListener() {
                public void onTextureFrameAvailable(int i, float[] fArr, long j) {
                    Camera2Session.this.checkIsOnCameraThread();
                    if (Camera2Session.this.state != SessionState.RUNNING) {
                        Logging.d(Camera2Session.TAG, "Texture frame captured but camera is no longer running.");
                        Camera2Session.this.surfaceTextureHelper.returnTextureFrame();
                        return;
                    }
                    if (!Camera2Session.this.firstFrameReported) {
                        boolean unused = Camera2Session.this.firstFrameReported = true;
                        Camera2Session.camera2StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera2Session.this.constructionTimeNs));
                    }
                    int access$1500 = Camera2Session.this.getFrameOrientation();
                    if (Camera2Session.this.isCameraFrontFacing) {
                        fArr = RendererCommon.multiplyMatrices(fArr, RendererCommon.horizontalFlipMatrix());
                    }
                    VideoFrame videoFrame = new VideoFrame(Camera2Session.this.surfaceTextureHelper.createTextureBuffer(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(RendererCommon.rotateTextureMatrix(fArr, (float) (-Camera2Session.this.cameraOrientation)))), access$1500, j);
                    Camera2Session.this.events.onFrameCaptured(Camera2Session.this, videoFrame);
                    videoFrame.release();
                }
            });
            Logging.d(Camera2Session.TAG, "Camera device successfully started.");
            Camera2Session.this.callback.onDone(Camera2Session.this);
        }
    }

    private static class CameraCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private CameraCaptureCallback() {
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            Logging.d(Camera2Session.TAG, "Capture failed: " + captureFailure);
        }
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events2, Context context, CameraManager cameraManager2, SurfaceTextureHelper surfaceTextureHelper2, MediaRecorder mediaRecorder, String str, int i, int i2, int i3) {
        new Camera2Session(createSessionCallback, events2, context, cameraManager2, surfaceTextureHelper2, mediaRecorder, str, i, i2, i3);
    }

    public boolean setSessionRepeatingRequest(@Nullable CaptureRequest.Builder builder) {
        if (builder == null) {
            Logging.e(TAG, "Cannot setSessionRepeatingRequest with null CaptureRequestBuilder");
            return false;
        }
        try {
            this.captureSession.setRepeatingRequest(builder.build(), new CameraCaptureCallback(), this.cameraThreadHandler);
            return true;
        } catch (CameraAccessException e) {
            Logging.e(TAG, "Failed to setSessionRepeatingRequest: " + e);
            return false;
        }
    }

    @Nullable
    public CaptureRequest.Builder configureCaptureRequestBuilder() {
        CaptureRequest.Builder builder = null;
        try {
            builder = this.cameraDevice.createCaptureRequest(3);
            builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(this.captureFormat.framerate.min / this.fpsUnitFactor), Integer.valueOf(this.captureFormat.framerate.max / this.fpsUnitFactor)));
            builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            builder.set(CaptureRequest.CONTROL_AE_LOCK, false);
            chooseStabilizationMode(builder);
            chooseFocusMode(builder);
            builder.addTarget(this.surface);
            if (this.mediaRecorderSurface != null) {
                Logging.d(TAG, "Add MediaRecorder surface to CaptureRequest.Builder");
                builder.addTarget(this.mediaRecorderSurface);
            }
        } catch (CameraAccessException e) {
            Logging.e(TAG, "Failed to create CaptureRequestBuilder: " + e);
        }
        return builder;
    }

    private void chooseStabilizationMode(CaptureRequest.Builder builder) {
        int[] iArr = (int[]) this.cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
        if (iArr != null) {
            for (int i : iArr) {
                if (i == 1) {
                    builder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 1);
                    builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
                    Logging.d(TAG, "Using optical stabilization.");
                    return;
                }
            }
        }
        for (int i2 : (int[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES)) {
            if (i2 == 1) {
                builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
                builder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 0);
                Logging.d(TAG, "Using video stabilization.");
                return;
            }
        }
        Logging.d(TAG, "Stabilization not available.");
    }

    private void chooseFocusMode(CaptureRequest.Builder builder) {
        for (int i : (int[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES)) {
            if (i == 3) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, 3);
                Logging.d(TAG, "Using continuous video auto-focus.");
                return;
            }
        }
        Logging.d(TAG, "Auto-focus is not available.");
    }

    private Camera2Session(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events2, Context context, CameraManager cameraManager2, SurfaceTextureHelper surfaceTextureHelper2, @Nullable MediaRecorder mediaRecorder, String str, int i, int i2, int i3) {
        Logging.d(TAG, "Create new camera2 session on camera " + str);
        this.constructionTimeNs = System.nanoTime();
        this.cameraThreadHandler = new Handler();
        this.callback = createSessionCallback;
        this.events = events2;
        this.applicationContext = context;
        this.cameraManager = cameraManager2;
        this.surfaceTextureHelper = surfaceTextureHelper2;
        this.mediaRecorderSurface = mediaRecorder != null ? mediaRecorder.getSurface() : null;
        this.cameraId = str;
        this.width = i;
        this.height = i2;
        this.framerate = i3;
        start();
    }

    private void start() {
        checkIsOnCameraThread();
        Logging.d(TAG, "start");
        try {
            this.cameraCharacteristics = this.cameraManager.getCameraCharacteristics(this.cameraId);
            this.cameraOrientation = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            this.isCameraFrontFacing = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
            findCaptureFormat();
            openCamera();
        } catch (CameraAccessException e) {
            reportError("getCameraCharacteristics(): " + e.getMessage());
        }
    }

    private void findCaptureFormat() {
        checkIsOnCameraThread();
        Range[] rangeArr = (Range[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        this.fpsUnitFactor = Camera2Enumerator.getFpsUnitFactor(rangeArr);
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates = Camera2Enumerator.convertFramerates(rangeArr, this.fpsUnitFactor);
        List<Size> supportedSizes = Camera2Enumerator.getSupportedSizes(this.cameraCharacteristics);
        Logging.d(TAG, "Available preview sizes: " + supportedSizes);
        Logging.d(TAG, "Available fps ranges: " + convertFramerates);
        if (convertFramerates.isEmpty() || supportedSizes.isEmpty()) {
            reportError("No supported capture formats.");
            return;
        }
        CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(convertFramerates, this.framerate);
        Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(supportedSizes, this.width, this.height);
        CameraEnumerationAndroid.reportCameraResolution(camera2ResolutionHistogram, closestSupportedSize);
        this.captureFormat = new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
        Logging.d(TAG, "Using capture format: " + this.captureFormat);
    }

    private void openCamera() {
        checkIsOnCameraThread();
        Logging.d(TAG, "Opening camera " + this.cameraId);
        this.events.onCameraOpening();
        try {
            this.cameraManager.openCamera(this.cameraId, new CameraStateCallback(), this.cameraThreadHandler);
        } catch (CameraAccessException e) {
            reportError("Failed to open camera: " + e);
        }
    }

    public void stop() {
        Logging.d(TAG, "Stop camera2 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long nanoTime = System.nanoTime();
            this.state = SessionState.STOPPED;
            stopInternal();
            camera2StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        }
    }

    /* access modifiers changed from: private */
    public void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        this.surfaceTextureHelper.stopListening();
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.captureSession = null;
        }
        Surface surface2 = this.surface;
        if (surface2 != null) {
            surface2.release();
            this.surface = null;
        }
        CameraDevice cameraDevice2 = this.cameraDevice;
        if (cameraDevice2 != null) {
            cameraDevice2.close();
            this.cameraDevice = null;
        }
        Logging.d(TAG, "Stop done");
    }

    /* access modifiers changed from: private */
    public void reportError(String str) {
        checkIsOnCameraThread();
        Logging.e(TAG, "Error: " + str);
        boolean z = this.captureSession == null && this.state != SessionState.STOPPED;
        this.state = SessionState.STOPPED;
        stopInternal();
        if (z) {
            this.callback.onFailure(CameraSession.FailureType.ERROR, str);
        } else {
            this.events.onCameraError(this, str);
        }
    }

    private int getDeviceOrientation() {
        switch (((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    public int getFrameOrientation() {
        int deviceOrientation = getDeviceOrientation();
        if (!this.isCameraFrontFacing) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (this.cameraOrientation + deviceOrientation) % 360;
    }

    public void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }
}
