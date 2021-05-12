package com.twilio.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.twilio.video.Camera2Capturer;
import com.twilio.video.VideoCapturer;
import com.twilio.video.VideoFrame;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import tvi.webrtc.Camera2Enumerator;
import tvi.webrtc.Camera2Session;
import tvi.webrtc.CameraEnumerationAndroid;
import tvi.webrtc.CameraVideoCapturer;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

@TargetApi(21)
public class Camera2Capturer implements VideoCapturer {
    private static final String CAMERA_SWITCH_PENDING_ERROR_MESSAGE = "Camera switch already in progress.";
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Camera2Capturer.class);
    private final Context applicationContext;
    private final Camera2Enumerator camera2Enumerator;
    /* access modifiers changed from: private */
    public Camera2Session camera2Session;
    private final CameraVideoCapturer.CameraEventsHandler cameraEventsHandler;
    /* access modifiers changed from: private */
    public String cameraId;
    private final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler;
    private final AtomicBoolean captureRequestUpdatePending;
    /* access modifiers changed from: private */
    public CaptureRequestUpdater captureRequestUpdater;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public final Listener listener;
    private final VideoCapturer.CapturerObserver observerAdapter;
    /* access modifiers changed from: private */
    public String pendingCameraId;
    /* access modifiers changed from: private */
    public State state;
    /* access modifiers changed from: private */
    public final Object stateLock;
    private final Map<String, List<VideoFormat>> supportedFormatsMap;
    private SurfaceTextureHelper surfaceTextureHelper;
    /* access modifiers changed from: private */
    public VideoCapturer.Listener videoCapturerListener;
    /* access modifiers changed from: private */
    public tvi.webrtc.Camera2Capturer webrtcCamera2Capturer;

    public interface Listener {
        void onCameraSwitched(@NonNull String str);

        void onError(@NonNull Exception exception);

        void onFirstFrameAvailable();
    }

    private enum State {
        IDLE,
        STARTING,
        RUNNING,
        STOPPING
    }

    public boolean isScreencast() {
        return false;
    }

    public static boolean isSupported(@NonNull Context context) {
        Preconditions.checkNotNull(context, "Context must not be null");
        return Camera2Enumerator.isSupported(context);
    }

    public Camera2Capturer(@NonNull Context context, @NonNull String str, @NonNull Listener listener2) {
        this(context, str, listener2, Util.createCallbackHandler());
    }

    @VisibleForTesting
    Camera2Capturer(@NonNull Context context, @NonNull String str, @NonNull Listener listener2, @NonNull Handler handler2) {
        this.stateLock = new Object();
        this.state = State.IDLE;
        this.supportedFormatsMap = new HashMap();
        boolean z = false;
        this.captureRequestUpdatePending = new AtomicBoolean(false);
        this.cameraEventsHandler = new CameraVideoCapturer.CameraEventsHandler() {
            public void onCameraClosed() {
            }

            public void onCameraDisconnected() {
            }

            public void onCameraOpening(String str) {
            }

            public void onCameraError(String str) {
                Camera2Capturer.this.handler.post(new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Camera2Capturer.this.listener.onError(new Camera2Capturer.Exception(2, this.f$1));
                    }
                });
            }

            public void onCameraFreezed(String str) {
                Camera2Capturer.logger.e("Camera froze.");
                Camera2Capturer.this.handler.post(new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Camera2Capturer.this.listener.onError(new Camera2Capturer.Exception(0, this.f$1));
                    }
                });
            }

            public void onFirstFrameAvailable() {
                Handler access$000 = Camera2Capturer.this.handler;
                Listener access$200 = Camera2Capturer.this.listener;
                access$200.getClass();
                access$000.post(new Runnable() {
                    public final void run() {
                        Camera2Capturer.Listener.this.onFirstFrameAvailable();
                    }
                });
            }
        };
        this.observerAdapter = new VideoCapturer.CapturerObserver() {
            @Deprecated
            public /* synthetic */ void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
                VideoCapturer.CapturerObserver.CC.$default$onByteBufferFrameCaptured(this, bArr, i, i2, i3, j);
            }

            @Deprecated
            public /* synthetic */ void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
                VideoCapturer.CapturerObserver.CC.$default$onTextureFrameCaptured(this, i, i2, i3, fArr, i4, j);
            }

            public void onCapturerStarted(boolean z) {
                Camera2Capturer.this.videoCapturerListener.onCapturerStarted(z);
                Camera2Capturer camera2Capturer = Camera2Capturer.this;
                Camera2Session unused = camera2Capturer.camera2Session = (Camera2Session) camera2Capturer.webrtcCamera2Capturer.getCameraSession();
                synchronized (Camera2Capturer.this.stateLock) {
                    State unused2 = Camera2Capturer.this.state = State.RUNNING;
                    if (Camera2Capturer.this.captureRequestUpdater != null) {
                        Camera2Capturer.this.updateCaptureRequestOnCameraThread(Camera2Capturer.this.captureRequestUpdater);
                        CaptureRequestUpdater unused3 = Camera2Capturer.this.captureRequestUpdater = null;
                    }
                }
            }

            public void onCapturerStopped() {
                synchronized (Camera2Capturer.this.stateLock) {
                    State unused = Camera2Capturer.this.state = State.IDLE;
                }
            }

            public void onFrameCaptured(VideoFrame videoFrame) {
                VideoFrame.Buffer buffer = videoFrame.getBuffer();
                Camera2Capturer.this.videoCapturerListener.onFrameCaptured(new VideoFrame(videoFrame, new VideoDimensions(buffer.getWidth(), buffer.getHeight()), VideoFrame.RotationAngle.fromInt(videoFrame.getRotation())));
            }
        };
        this.cameraSwitchHandler = new CameraVideoCapturer.CameraSwitchHandler() {
            public void onCameraSwitchDone(boolean z) {
                synchronized (Camera2Capturer.this) {
                    String unused = Camera2Capturer.this.cameraId = Camera2Capturer.this.pendingCameraId;
                    String unused2 = Camera2Capturer.this.pendingCameraId = null;
                }
                Camera2Capturer.this.handler.post(new Runnable() {
                    public final void run() {
                        Camera2Capturer.this.listener.onCameraSwitched(Camera2Capturer.this.cameraId);
                    }
                });
            }

            public void onCameraSwitchError(String str) {
                Logger access$100 = Camera2Capturer.logger;
                access$100.e("Failed to switch to camera with ID: " + Camera2Capturer.this.pendingCameraId);
                synchronized (Camera2Capturer.this) {
                    String unused = Camera2Capturer.this.pendingCameraId = null;
                }
                Camera2Capturer.this.handler.post(new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Camera2Capturer.this.listener.onError(new Camera2Capturer.Exception(1, this.f$1));
                    }
                });
            }
        };
        z = Build.VERSION.SDK_INT >= 21 ? true : z;
        Preconditions.checkState(z, "Camera2Capturer unavailable for " + Build.VERSION.SDK_INT);
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkState(isSupported(context), "Camera2Capturer is not supported on this device");
        Preconditions.checkNotNull(str, "Camera ID must not be null");
        Preconditions.checkNotNull(listener2, "Listener must not be null");
        Preconditions.checkArgument(!str.isEmpty(), "Camera ID must not be empty");
        this.applicationContext = context.getApplicationContext();
        this.camera2Enumerator = new Camera2Enumerator(this.applicationContext);
        this.cameraId = str;
        this.listener = listener2;
        this.handler = handler2;
    }

    @NonNull
    public synchronized List<VideoFormat> getSupportedFormats() {
        List<VideoFormat> list;
        checkCapturerState();
        list = this.supportedFormatsMap.get(this.cameraId);
        if (list == null) {
            list = convertToVideoFormats(this.camera2Enumerator.getSupportedFormats(this.cameraId));
            this.supportedFormatsMap.put(this.cameraId, list);
        }
        return list;
    }

    public void startCapture(@NonNull VideoFormat videoFormat, @NonNull VideoCapturer.Listener listener2) {
        checkCapturerState();
        synchronized (this.stateLock) {
            this.state = State.STARTING;
        }
        this.webrtcCamera2Capturer = (tvi.webrtc.Camera2Capturer) this.camera2Enumerator.createCapturer(this.cameraId, this.cameraEventsHandler);
        this.videoCapturerListener = listener2;
        this.webrtcCamera2Capturer.initialize(this.surfaceTextureHelper, this.applicationContext, this.observerAdapter);
        this.webrtcCamera2Capturer.startCapture(videoFormat.dimensions.width, videoFormat.dimensions.height, videoFormat.framerate);
    }

    public void stopCapture() {
        if (this.webrtcCamera2Capturer != null) {
            synchronized (this.stateLock) {
                this.state = State.STOPPING;
            }
            this.webrtcCamera2Capturer.stopCapture();
            this.webrtcCamera2Capturer.dispose();
            this.webrtcCamera2Capturer = null;
        }
    }

    @NonNull
    public synchronized String getCameraId() {
        return this.cameraId;
    }

    public synchronized void switchCamera(@NonNull String str) {
        Preconditions.checkNotNull(str, "Camera ID must not be null");
        boolean z = true;
        Preconditions.checkArgument(!str.isEmpty(), "Camera ID must not be empty");
        if (str.equals(this.cameraId)) {
            z = false;
        }
        Preconditions.checkArgument(z, "Camera ID must be different from current camera ID");
        Preconditions.checkArgument(Camera2Utils.cameraIdSupported(this.applicationContext, str), "Camera ID %s is not supported or could not be validated", (Object) str);
        synchronized (this.stateLock) {
            if (this.state == State.IDLE) {
                this.cameraId = str;
                this.listener.onCameraSwitched(this.cameraId);
            } else if (this.pendingCameraId == null) {
                this.pendingCameraId = str;
                this.webrtcCamera2Capturer.switchCamera(str, this.cameraSwitchHandler);
            } else {
                this.handler.post(new Runnable() {
                    public final void run() {
                        Camera2Capturer.this.listener.onError(new Camera2Capturer.Exception(1, Camera2Capturer.CAMERA_SWITCH_PENDING_ERROR_MESSAGE));
                    }
                });
            }
        }
    }

    public synchronized boolean updateCaptureRequest(@NonNull CaptureRequestUpdater captureRequestUpdater2) {
        synchronized (this.stateLock) {
            if (this.state != State.RUNNING) {
                logger.i("Camera2Capturer is not running. The CaptureRequest update will be applied when it is resumed");
                this.captureRequestUpdater = captureRequestUpdater2;
                return true;
            } else if (!this.captureRequestUpdatePending.get()) {
                this.captureRequestUpdatePending.set(true);
                boolean post = this.camera2Session.cameraThreadHandler.post(new Runnable(captureRequestUpdater2) {
                    private final /* synthetic */ CaptureRequestUpdater f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Camera2Capturer.this.updateCaptureRequestOnCameraThread(this.f$1);
                    }
                });
                return post;
            } else {
                logger.w("Parameters will not be applied with parameter update pending");
                return false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSurfaceTextureHelper(@NonNull SurfaceTextureHelper surfaceTextureHelper2) {
        this.surfaceTextureHelper = surfaceTextureHelper2;
    }

    private void checkCapturerState() {
        Preconditions.checkState(Util.permissionGranted(this.applicationContext, "android.permission.CAMERA"), "CAMERA permission must be granted to create videotrack with Camera2Capturer");
        Preconditions.checkState(Camera2Utils.cameraIdSupported(this.applicationContext, this.cameraId), "Camera ID %s is not supported or could not be validated", (Object) this.cameraId);
    }

    private void reportError(Exception exception) {
        logger.e(exception.getMessage(), exception);
        this.handler.post(new Runnable(exception) {
            private final /* synthetic */ Camera2Capturer.Exception f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                Camera2Capturer.this.listener.onError(this.f$1);
            }
        });
    }

    @NonNull
    private List<VideoFormat> convertToVideoFormats(@NonNull List<CameraEnumerationAndroid.CaptureFormat> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (CameraEnumerationAndroid.CaptureFormat next : list) {
            arrayList.add(new VideoFormat(new VideoDimensions(next.width, next.height), (next.framerate.max + 999) / 1000, VideoPixelFormat.NV21));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void updateCaptureRequestOnCameraThread(@NonNull CaptureRequestUpdater captureRequestUpdater2) {
        Preconditions.checkNotNull(captureRequestUpdater2, "captureRequestUpdate must not be null");
        synchronized (this.stateLock) {
            if (this.state == State.RUNNING) {
                logger.i("Applying custom capture request");
                this.camera2Session.checkIsOnCameraThread();
                CaptureRequest.Builder configureCaptureRequestBuilder = this.camera2Session.configureCaptureRequestBuilder();
                if (configureCaptureRequestBuilder == null) {
                    reportError(new Exception(3, "Failed to create capture request builder"));
                    this.captureRequestUpdatePending.set(false);
                    return;
                }
                captureRequestUpdater2.apply(configureCaptureRequestBuilder);
                if (!this.camera2Session.setSessionRepeatingRequest(configureCaptureRequestBuilder)) {
                    reportError(new Exception(3, "Failed to set session repeating request"));
                    this.captureRequestUpdatePending.set(false);
                    return;
                }
            } else {
                logger.w("Attempted to update camera parameters while camera capturer is not running");
            }
            this.captureRequestUpdatePending.set(false);
        }
    }

    public static class Exception extends TwilioException {
        public static final int CAMERA_FROZE = 0;
        public static final int CAMERA_SWITCH_FAILED = 1;
        public static final int CAPTURE_REQUEST_UPDATE_FAILED = 3;
        public static final int UNKNOWN = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Code {
        }

        Exception(int i, @NonNull String str, @Nullable String str2) {
            super(i, str, str2);
        }

        Exception(int i, @NonNull String str) {
            this(i, str, (String) null);
        }
    }
}
