package tvi.webrtc;

import android.media.MediaRecorder;

public interface CameraVideoCapturer extends VideoCapturer {

    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraDisconnected();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(String str);

        void onFirstFrameAvailable();
    }

    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean z);

        void onCameraSwitchError(String str);
    }

    public interface MediaRecorderHandler {
        void onMediaRecorderError(String str);

        void onMediaRecorderSuccess();
    }

    void addMediaRecorderToCamera(MediaRecorder mediaRecorder, MediaRecorderHandler mediaRecorderHandler);

    void removeMediaRecorderFromCamera(MediaRecorderHandler mediaRecorderHandler);

    void switchCamera(CameraSwitchHandler cameraSwitchHandler);

    public static class CameraStatistics {
        private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
        private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
        private static final String TAG = "CameraStatistics";
        private final Runnable cameraObserver;
        /* access modifiers changed from: private */
        public final CameraEventsHandler eventsHandler;
        /* access modifiers changed from: private */
        public int frameCount;
        /* access modifiers changed from: private */
        public int freezePeriodCount;
        /* access modifiers changed from: private */
        public final SurfaceTextureHelper surfaceTextureHelper;

        static /* synthetic */ int access$104(CameraStatistics cameraStatistics) {
            int i = cameraStatistics.freezePeriodCount + 1;
            cameraStatistics.freezePeriodCount = i;
            return i;
        }

        public CameraStatistics(SurfaceTextureHelper surfaceTextureHelper2, CameraEventsHandler cameraEventsHandler) {
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    int round = Math.round((((float) CameraStatistics.this.frameCount) * 1000.0f) / 2000.0f);
                    Logging.d(CameraStatistics.TAG, "Camera fps: " + round + ".");
                    if (CameraStatistics.this.frameCount == 0) {
                        CameraStatistics.access$104(CameraStatistics.this);
                        if (CameraStatistics.this.freezePeriodCount * 2000 >= CameraStatistics.CAMERA_FREEZE_REPORT_TIMOUT_MS && CameraStatistics.this.eventsHandler != null) {
                            Logging.e(CameraStatistics.TAG, "Camera freezed.");
                            if (CameraStatistics.this.surfaceTextureHelper.isTextureInUse()) {
                                CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure. Client must return video buffers.");
                                return;
                            } else {
                                CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure.");
                                return;
                            }
                        }
                    } else {
                        int unused = CameraStatistics.this.freezePeriodCount = 0;
                    }
                    int unused2 = CameraStatistics.this.frameCount = 0;
                    CameraStatistics.this.surfaceTextureHelper.getHandler().postDelayed(this, 2000);
                }
            };
            this.cameraObserver = r0;
            if (surfaceTextureHelper2 != null) {
                this.surfaceTextureHelper = surfaceTextureHelper2;
                this.eventsHandler = cameraEventsHandler;
                this.frameCount = 0;
                this.freezePeriodCount = 0;
                surfaceTextureHelper2.getHandler().postDelayed(r0, 2000);
                return;
            }
            throw new IllegalArgumentException("SurfaceTextureHelper is null");
        }

        private void checkThread() {
            if (Thread.currentThread() != this.surfaceTextureHelper.getHandler().getLooper().getThread()) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void addFrame() {
            checkThread();
            this.frameCount++;
        }

        public void release() {
            this.surfaceTextureHelper.getHandler().removeCallbacks(this.cameraObserver);
        }
    }
}
