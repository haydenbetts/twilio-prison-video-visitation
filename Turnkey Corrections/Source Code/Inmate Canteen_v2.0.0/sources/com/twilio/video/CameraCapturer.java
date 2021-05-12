package com.twilio.video;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.twilio.video.CameraCapturer;
import com.twilio.video.VideoCapturer;
import com.twilio.video.VideoFrame;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import tvi.webrtc.Camera1Capturer;
import tvi.webrtc.Camera1Session;
import tvi.webrtc.CameraVideoCapturer;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.ThreadUtils;
import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

public class CameraCapturer implements VideoCapturer {
    private static final String CAMERA_CLOSED_FAILED = "Failed to close camera";
    private static final int CAMERA_CLOSED_TIMEOUT_MS = 3000;
    public static final int ERROR_CAMERA_FREEZE = 0;
    public static final int ERROR_CAMERA_PERMISSION_NOT_GRANTED = 3;
    public static final int ERROR_CAMERA_SERVER_STOPPED = 1;
    public static final int ERROR_CAMERA_SWITCH_FAILED = 5;
    private static final String ERROR_MESSAGE_CAMERA_SERVER_DIED = "Camera server died!";
    private static final String ERROR_MESSAGE_UNKNOWN = "Camera error:";
    public static final int ERROR_UNKNOWN = 6;
    public static final int ERROR_UNSUPPORTED_SOURCE = 2;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(CameraCapturer.class);
    /* access modifiers changed from: private */
    public Camera1Session camera1Session;
    /* access modifiers changed from: private */
    public CountDownLatch cameraClosed;
    private final CameraVideoCapturer.CameraEventsHandler cameraEventsHandler;
    /* access modifiers changed from: private */
    public CameraParameterUpdater cameraParameterUpdater;
    /* access modifiers changed from: private */
    public CameraSource cameraSource;
    private final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler;
    private final Context context;
    private final CameraCapturerFormatProvider formatProvider;
    /* access modifiers changed from: private */
    public Listener listener;
    private final VideoCapturer.CapturerObserver observerAdapter;
    private final AtomicBoolean parameterUpdatePending;
    /* access modifiers changed from: private */
    public PictureListener pictureListener;
    private final AtomicBoolean picturePending;
    /* access modifiers changed from: private */
    public State state;
    /* access modifiers changed from: private */
    public final Object stateLock;
    private SurfaceTextureHelper surfaceTextureHelper;
    /* access modifiers changed from: private */
    public VideoCapturer.Listener videoCapturerListener;
    /* access modifiers changed from: private */
    public Camera1Capturer webRtcCameraCapturer;

    public enum CameraSource {
        FRONT_CAMERA,
        BACK_CAMERA
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Error {
    }

    public interface Listener {
        void onCameraSwitched();

        void onError(int i);

        void onFirstFrameAvailable();
    }

    public interface PictureListener {
        void onPictureTaken(@NonNull byte[] bArr);

        void onShutter();
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

    public static boolean isSourceAvailable(@NonNull CameraSource cameraSource2) {
        Preconditions.checkNotNull(cameraSource2, "Camera source must not be null");
        return isSourceAvailable(new CameraCapturerFormatProvider(), cameraSource2);
    }

    static boolean isSourceAvailable(@NonNull CameraCapturerFormatProvider cameraCapturerFormatProvider, @NonNull CameraSource cameraSource2) {
        return cameraCapturerFormatProvider.getCameraId(cameraSource2) != -1;
    }

    public CameraCapturer(@NonNull Context context2, @NonNull CameraSource cameraSource2) {
        this(context2, cameraSource2, (Listener) null);
    }

    public CameraCapturer(@NonNull Context context2, @NonNull CameraSource cameraSource2, @Nullable Listener listener2) {
        this(context2, cameraSource2, listener2, new CameraCapturerFormatProvider());
    }

    @VisibleForTesting(otherwise = 2)
    CameraCapturer(@NonNull Context context2, @NonNull CameraSource cameraSource2, @Nullable Listener listener2, @NonNull CameraCapturerFormatProvider cameraCapturerFormatProvider) {
        this.stateLock = new Object();
        this.state = State.IDLE;
        this.picturePending = new AtomicBoolean(false);
        this.parameterUpdatePending = new AtomicBoolean(false);
        this.observerAdapter = new VideoCapturer.CapturerObserver() {
            @Deprecated
            public /* synthetic */ void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
                VideoCapturer.CapturerObserver.CC.$default$onByteBufferFrameCaptured(this, bArr, i, i2, i3, j);
            }

            public void onCapturerStopped() {
            }

            @Deprecated
            public /* synthetic */ void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
                VideoCapturer.CapturerObserver.CC.$default$onTextureFrameCaptured(this, i, i2, i3, fArr, i4, j);
            }

            public void onCapturerStarted(boolean z) {
                CameraCapturer.this.videoCapturerListener.onCapturerStarted(z);
                CameraCapturer cameraCapturer = CameraCapturer.this;
                Camera1Session unused = cameraCapturer.camera1Session = cameraCapturer.webRtcCameraCapturer.getCameraSession();
                synchronized (CameraCapturer.this.stateLock) {
                    if (CameraCapturer.this.state == State.STARTING) {
                        State unused2 = CameraCapturer.this.state = State.RUNNING;
                        if (CameraCapturer.this.cameraParameterUpdater != null) {
                            CameraCapturer.this.updateCameraParametersOnCameraThread(CameraCapturer.this.cameraParameterUpdater);
                            CameraParameterUpdater unused3 = CameraCapturer.this.cameraParameterUpdater = null;
                        }
                        if (CameraCapturer.this.pictureListener != null) {
                            CameraCapturer.this.takePicture(CameraCapturer.this.pictureListener);
                            PictureListener unused4 = CameraCapturer.this.pictureListener = null;
                        }
                    } else {
                        Logger access$800 = CameraCapturer.logger;
                        access$800.w("Attempted to transition from " + CameraCapturer.this.state + " to RUNNING");
                    }
                }
            }

            public void onFrameCaptured(VideoFrame videoFrame) {
                VideoFrame.Buffer buffer = videoFrame.getBuffer();
                CameraCapturer.this.videoCapturerListener.onFrameCaptured(new VideoFrame(videoFrame, new VideoDimensions(buffer.getWidth(), buffer.getHeight()), VideoFrame.RotationAngle.fromInt(videoFrame.getRotation())));
            }
        };
        this.cameraEventsHandler = new CameraVideoCapturer.CameraEventsHandler() {
            public void onCameraDisconnected() {
            }

            public void onCameraOpening(String str) {
            }

            public void onCameraError(String str) {
                if (CameraCapturer.this.listener != null) {
                    if (str.equals(CameraCapturer.ERROR_MESSAGE_CAMERA_SERVER_DIED)) {
                        CameraCapturer.logger.e("Camera server stopped.");
                        CameraCapturer.this.listener.onError(1);
                    } else if (str.contains(CameraCapturer.ERROR_MESSAGE_UNKNOWN)) {
                        CameraCapturer.logger.e("Unknown camera error occurred.");
                        CameraCapturer.this.listener.onError(6);
                    }
                }
                synchronized (CameraCapturer.this.stateLock) {
                    State unused = CameraCapturer.this.state = State.IDLE;
                }
            }

            public void onCameraFreezed(String str) {
                CameraCapturer.logger.e("Camera froze.");
                if (CameraCapturer.this.listener != null) {
                    CameraCapturer.this.listener.onError(0);
                }
                synchronized (CameraCapturer.this.stateLock) {
                    State unused = CameraCapturer.this.state = State.IDLE;
                }
            }

            public void onFirstFrameAvailable() {
                if (CameraCapturer.this.listener != null) {
                    CameraCapturer.this.listener.onFirstFrameAvailable();
                }
            }

            public void onCameraClosed() {
                synchronized (CameraCapturer.this.stateLock) {
                    if (CameraCapturer.this.state == State.STOPPING) {
                        Camera1Session unused = CameraCapturer.this.camera1Session = null;
                        CameraCapturer.this.cameraClosed.countDown();
                    }
                }
            }
        };
        this.cameraSwitchHandler = new CameraVideoCapturer.CameraSwitchHandler() {
            public void onCameraSwitchDone(boolean z) {
                synchronized (CameraCapturer.this) {
                    CameraSource unused = CameraCapturer.this.cameraSource = CameraCapturer.this.cameraSource == CameraSource.FRONT_CAMERA ? CameraSource.BACK_CAMERA : CameraSource.FRONT_CAMERA;
                }
                if (CameraCapturer.this.listener != null) {
                    CameraCapturer.this.listener.onCameraSwitched();
                }
            }

            public void onCameraSwitchError(String str) {
                Logger access$800 = CameraCapturer.logger;
                access$800.e("Failed to switch to camera source " + CameraCapturer.this.cameraSource);
                if (CameraCapturer.this.listener != null) {
                    CameraCapturer.this.listener.onError(5);
                }
                synchronized (CameraCapturer.this.stateLock) {
                    State unused = CameraCapturer.this.state = State.IDLE;
                }
            }
        };
        Preconditions.checkNotNull(context2, "Context must not be null");
        Preconditions.checkNotNull(cameraSource2, "Camera source must not be null");
        Preconditions.checkArgument(isSourceAvailable(cameraCapturerFormatProvider, cameraSource2), String.format("%s is not supported on this device", new Object[]{cameraSource2}));
        this.context = context2;
        this.cameraSource = cameraSource2;
        this.listener = listener2;
        this.formatProvider = cameraCapturerFormatProvider;
    }

    @NonNull
    public synchronized List<VideoFormat> getSupportedFormats() {
        List<VideoFormat> supportedFormats;
        Preconditions.checkState(Util.permissionGranted(this.context, "android.permission.CAMERA"), "CAMERA permission must be granted to create videotrack with CameraCapturer");
        supportedFormats = this.formatProvider.getSupportedFormats(this.cameraSource);
        Preconditions.checkState(!supportedFormats.isEmpty(), "Supported formats could not be retrieved because an error occurred connecting to the camera service");
        return supportedFormats;
    }

    public void startCapture(@NonNull VideoFormat videoFormat, @NonNull VideoCapturer.Listener listener2) {
        if (createWebRtcCameraCapturer()) {
            synchronized (this.stateLock) {
                this.state = State.STARTING;
            }
            this.videoCapturerListener = listener2;
            this.webRtcCameraCapturer.initialize(this.surfaceTextureHelper, this.context, this.observerAdapter);
            this.webRtcCameraCapturer.startCapture(videoFormat.dimensions.width, videoFormat.dimensions.height, videoFormat.framerate);
            return;
        }
        logger.e("Failed to startCapture");
        listener2.onCapturerStarted(false);
    }

    public void stopCapture() {
        if (this.webRtcCameraCapturer != null) {
            synchronized (this.stateLock) {
                this.state = State.STOPPING;
                this.cameraClosed = new CountDownLatch(1);
            }
            this.webRtcCameraCapturer.stopCapture();
            this.webRtcCameraCapturer.dispose();
            this.webRtcCameraCapturer = null;
            if (!ThreadUtils.awaitUninterruptibly(this.cameraClosed, TestUtils.THREE_SECONDS)) {
                logger.e("Camera closed event not received");
            }
            synchronized (this.stateLock) {
                this.cameraClosed = null;
                this.state = State.IDLE;
            }
        }
    }

    @NonNull
    public synchronized CameraSource getCameraSource() {
        return this.cameraSource;
    }

    public synchronized void switchCamera() {
        CameraSource cameraSource2 = this.cameraSource == CameraSource.FRONT_CAMERA ? CameraSource.BACK_CAMERA : CameraSource.FRONT_CAMERA;
        if (!isSourceAvailable(this.formatProvider, cameraSource2)) {
            logger.w(String.format("Cannot switch to unsupported camera source %s", new Object[]{cameraSource2}));
            return;
        }
        synchronized (this.stateLock) {
            if (this.state != State.IDLE) {
                this.webRtcCameraCapturer.switchCamera(this.cameraSwitchHandler);
            } else {
                this.cameraSource = cameraSource2;
                if (this.listener != null) {
                    this.listener.onCameraSwitched();
                }
            }
        }
    }

    public synchronized boolean updateCameraParameters(@NonNull CameraParameterUpdater cameraParameterUpdater2) {
        synchronized (this.stateLock) {
            if (this.state != State.RUNNING) {
                logger.i("Camera capturer is not running. Parameters will be applied when camera capturer is resumed");
                this.cameraParameterUpdater = cameraParameterUpdater2;
                return true;
            } else if (!this.parameterUpdatePending.get()) {
                this.parameterUpdatePending.set(true);
                boolean post = this.camera1Session.cameraThreadHandler.post(new Runnable(cameraParameterUpdater2) {
                    private final /* synthetic */ CameraParameterUpdater f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        CameraCapturer.this.updateCameraParametersOnCameraThread(this.f$1);
                    }
                });
                return post;
            } else {
                logger.w("Parameters will not be applied with parameter update pending");
                return false;
            }
        }
    }

    public synchronized boolean takePicture(@NonNull PictureListener pictureListener2) {
        Preconditions.checkNotNull(pictureListener2);
        synchronized (this.stateLock) {
            if (this.state != State.RUNNING) {
                logger.i("Camera capturer is not running. Picture request will be serviced when camera capturer is resumed");
                this.pictureListener = pictureListener2;
                return true;
            } else if (!this.picturePending.get()) {
                this.picturePending.set(true);
                boolean post = this.camera1Session.cameraThreadHandler.post(new Runnable(Util.createCallbackHandler(), pictureListener2) {
                    private final /* synthetic */ Handler f$1;
                    private final /* synthetic */ CameraCapturer.PictureListener f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        CameraCapturer.this.takePictureOnCameraThread(this.f$1, this.f$2);
                    }
                });
                return post;
            } else {
                logger.w("Picture cannot be taken while picture is pending");
                return false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSurfaceTextureHelper(@NonNull SurfaceTextureHelper surfaceTextureHelper2) {
        this.surfaceTextureHelper = surfaceTextureHelper2;
    }

    @NonNull
    private List<VideoFormat> defaultFormats() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VideoFormat(new VideoDimensions(VideoDimensions.VGA_VIDEO_WIDTH, 480), 30, VideoPixelFormat.NV21));
        return arrayList;
    }

    private boolean createWebRtcCameraCapturer() {
        if (!Util.permissionGranted(this.context, "android.permission.CAMERA")) {
            logger.e("CAMERA permission must be granted to start capturer");
            Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onError(3);
            }
            synchronized (this.stateLock) {
                this.state = State.IDLE;
            }
            return false;
        }
        int cameraId = this.formatProvider.getCameraId(this.cameraSource);
        String deviceName = this.formatProvider.getDeviceName(cameraId);
        if (cameraId < 0 || deviceName == null) {
            logger.e("Failed to find camera source");
            Listener listener3 = this.listener;
            if (listener3 != null) {
                listener3.onError(2);
            }
            synchronized (this.stateLock) {
                this.state = State.IDLE;
            }
            return false;
        }
        this.webRtcCameraCapturer = new Camera1Capturer(deviceName, this.cameraEventsHandler, false);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r9 != 270) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] alignPicture(@androidx.annotation.NonNull android.hardware.Camera.CameraInfo r9, @androidx.annotation.NonNull byte[] r10) {
        /*
            r8 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x001a
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r10)
            android.graphics.ImageDecoder$Source r0 = android.graphics.ImageDecoder.createSource(r0)
            android.graphics.Bitmap r0 = android.graphics.ImageDecoder.decodeBitmap(r0)     // Catch:{ IOException -> 0x0013 }
            goto L_0x0018
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()
            r0 = 0
        L_0x0018:
            r1 = r0
            goto L_0x0021
        L_0x001a:
            r0 = 0
            int r1 = r10.length
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeByteArray(r10, r0, r1)
            r1 = r0
        L_0x0021:
            if (r1 == 0) goto L_0x0070
            int r9 = r8.getFrameOrientation(r9)
            android.graphics.Matrix r6 = new android.graphics.Matrix
            r6.<init>()
            com.twilio.video.CameraCapturer$CameraSource r10 = r8.cameraSource
            com.twilio.video.CameraCapturer$CameraSource r0 = com.twilio.video.CameraCapturer.CameraSource.FRONT_CAMERA
            if (r10 != r0) goto L_0x004c
            r10 = 1065353216(0x3f800000, float:1.0)
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r9 == 0) goto L_0x0049
            r2 = 90
            if (r9 == r2) goto L_0x0045
            r2 = 180(0xb4, float:2.52E-43)
            if (r9 == r2) goto L_0x0049
            r2 = 270(0x10e, float:3.78E-43)
            if (r9 == r2) goto L_0x0045
            goto L_0x004c
        L_0x0045:
            r6.setScale(r10, r0)
            goto L_0x004c
        L_0x0049:
            r6.setScale(r0, r10)
        L_0x004c:
            float r9 = (float) r9
            r6.postRotate(r9)
            r2 = 0
            r3 = 0
            int r4 = r1.getWidth()
            int r5 = r1.getHeight()
            r7 = 1
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createBitmap(r1, r2, r3, r4, r5, r6, r7)
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream
            r10.<init>()
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
            r1 = 100
            r9.compress(r0, r1, r10)
            byte[] r9 = r10.toByteArray()
            return r9
        L_0x0070:
            com.twilio.video.Logger r9 = logger
            java.lang.String r0 = "Failed to align picture data. Returning original image."
            r9.e(r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.CameraCapturer.alignPicture(android.hardware.Camera$CameraInfo, byte[]):byte[]");
    }

    private int getFrameOrientation(@NonNull Camera.CameraInfo cameraInfo) {
        int deviceOrientation = getDeviceOrientation();
        if (cameraInfo.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (cameraInfo.orientation + deviceOrientation) % 360;
    }

    private int getDeviceOrientation() {
        switch (((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRotation()) {
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
    public void updateCameraParametersOnCameraThread(@NonNull CameraParameterUpdater cameraParameterUpdater2) {
        synchronized (this.stateLock) {
            if (this.state == State.RUNNING) {
                this.camera1Session.checkIsOnCameraThread();
                Camera.Parameters parameters = this.camera1Session.camera.getParameters();
                logger.i("Applying camera parameters");
                cameraParameterUpdater2.apply(parameters);
                this.camera1Session.camera.stopPreview();
                this.camera1Session.camera.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
                this.camera1Session.camera.setParameters(parameters);
                int frameSize = this.camera1Session.captureFormat.frameSize();
                for (int i = 0; i < 3; i++) {
                    this.camera1Session.camera.addCallbackBuffer(ByteBuffer.allocateDirect(frameSize).array());
                }
                this.camera1Session.listenForBytebufferFrames();
                this.camera1Session.camera.startPreview();
            } else {
                logger.w("Attempted to update camera parameters while camera capturer is not running");
            }
            this.parameterUpdatePending.set(false);
        }
    }

    /* access modifiers changed from: private */
    public void takePictureOnCameraThread(@NonNull Handler handler, @NonNull PictureListener pictureListener2) {
        synchronized (this.stateLock) {
            if (this.state == State.RUNNING) {
                this.camera1Session.checkIsOnCameraThread();
                this.camera1Session.camera.takePicture(new Camera.ShutterCallback(handler, pictureListener2) {
                    private final /* synthetic */ Handler f$0;
                    private final /* synthetic */ CameraCapturer.PictureListener f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void onShutter() {
                        CameraCapturer.lambda$takePictureOnCameraThread$2(this.f$0, this.f$1);
                    }
                }, (Camera.PictureCallback) null, new Camera.PictureCallback(this.camera1Session.info, handler, pictureListener2) {
                    private final /* synthetic */ Camera.CameraInfo f$1;
                    private final /* synthetic */ Handler f$2;
                    private final /* synthetic */ CameraCapturer.PictureListener f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onPictureTaken(byte[] bArr, Camera camera) {
                        CameraCapturer.lambda$takePictureOnCameraThread$4(CameraCapturer.this, this.f$1, this.f$2, this.f$3, bArr, camera);
                    }
                });
            } else {
                logger.w("Attempted to take picture while capturing is not running");
            }
        }
    }

    static /* synthetic */ void lambda$takePictureOnCameraThread$2(Handler handler, PictureListener pictureListener2) {
        pictureListener2.getClass();
        handler.post(new Runnable() {
            public final void run() {
                CameraCapturer.PictureListener.this.onShutter();
            }
        });
    }

    public static /* synthetic */ void lambda$takePictureOnCameraThread$4(CameraCapturer cameraCapturer, Camera.CameraInfo cameraInfo, Handler handler, PictureListener pictureListener2, byte[] bArr, Camera camera) {
        handler.post(new Runnable(pictureListener2, cameraCapturer.alignPicture(cameraInfo, bArr)) {
            private final /* synthetic */ CameraCapturer.PictureListener f$1;
            private final /* synthetic */ byte[] f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                CameraCapturer.lambda$null$3(CameraCapturer.this, this.f$1, this.f$2);
            }
        });
        synchronized (cameraCapturer.stateLock) {
            if (cameraCapturer.state == State.RUNNING) {
                cameraCapturer.camera1Session.camera.startPreview();
            }
        }
    }

    public static /* synthetic */ void lambda$null$3(CameraCapturer cameraCapturer, PictureListener pictureListener2, byte[] bArr) {
        pictureListener2.onPictureTaken(bArr);
        cameraCapturer.picturePending.set(false);
    }
}
