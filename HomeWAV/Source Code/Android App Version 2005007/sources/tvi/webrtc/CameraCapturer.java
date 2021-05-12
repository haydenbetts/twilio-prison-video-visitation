package tvi.webrtc;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import javax.annotation.Nullable;
import tvi.webrtc.CameraSession;
import tvi.webrtc.CameraVideoCapturer;
import tvi.webrtc.VideoCapturer;

abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    /* access modifiers changed from: private */
    public Context applicationContext;
    /* access modifiers changed from: private */
    public final CameraEnumerator cameraEnumerator;
    /* access modifiers changed from: private */
    public String cameraName;
    /* access modifiers changed from: private */
    @Nullable
    public final CameraSession.Events cameraSessionEventsHandler = new CameraSession.Events() {
        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession != null) {
                    Logging.w(CameraCapturer.TAG, "onCameraOpening while session was open.");
                } else {
                    CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName);
                }
            }
        }

        public void onCameraError(CameraSession cameraSession, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onCameraError from another session: " + str);
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraError(str);
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraDisconnected(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onCameraDisconnected from another session.");
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraDisconnected();
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraClosed(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                } else {
                    Logging.d(CameraCapturer.TAG, "onCameraClosed from another session.");
                }
            }
        }

        public void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    boolean unused = CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onFrameCaptured(videoFrame);
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public CameraVideoCapturer.CameraStatistics cameraStatistics;
    @Nullable
    private Handler cameraThreadHandler;
    /* access modifiers changed from: private */
    public VideoCapturer.CapturerObserver capturerObserver;
    /* access modifiers changed from: private */
    @Nullable
    public final CameraSession.CreateSessionCallback createSessionCallback = new CameraSession.CreateSessionCallback() {
        public void onDone(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.d(CameraCapturer.TAG, "Create session done. Switch state: " + CameraCapturer.this.switchState + ". MediaRecorder state: " + CameraCapturer.this.mediaRecorderState);
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                boolean unused = CameraCapturer.this.sessionOpening = false;
                CameraSession unused2 = CameraCapturer.this.currentSession = cameraSession;
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                CameraCapturer cameraCapturer = CameraCapturer.this;
                CameraVideoCapturer.CameraStatistics unused3 = cameraCapturer.cameraStatistics = new CameraVideoCapturer.CameraStatistics(cameraCapturer.surfaceHelper, CameraCapturer.this.eventsHandler);
                boolean unused4 = CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState == SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchEventsHandler != null) {
                        CameraCapturer.this.switchEventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                        CameraVideoCapturer.CameraSwitchHandler unused5 = CameraCapturer.this.switchEventsHandler = null;
                    }
                    SwitchState unused6 = CameraCapturer.this.switchState = SwitchState.IDLE;
                } else if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                    SwitchState unused7 = CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer cameraCapturer2 = CameraCapturer.this;
                    cameraCapturer2.switchCameraInternal(cameraCapturer2.switchEventsHandler);
                }
                if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE || CameraCapturer.this.mediaRecorderState == MediaRecorderState.ACTIVE_TO_IDLE) {
                    if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                        CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderSuccess();
                        CameraVideoCapturer.MediaRecorderHandler unused8 = CameraCapturer.this.mediaRecorderEventsHandler = null;
                    }
                    if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE) {
                        MediaRecorderState unused9 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.ACTIVE;
                    } else {
                        MediaRecorderState unused10 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                }
            }
        }

        public void onFailure(CameraSession.FailureType failureType, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.access$1810(CameraCapturer.this);
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, passing: " + str);
                    boolean unused = CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    if (CameraCapturer.this.switchState != SwitchState.IDLE) {
                        if (CameraCapturer.this.switchEventsHandler != null) {
                            CameraCapturer.this.switchEventsHandler.onCameraSwitchError(str);
                            CameraVideoCapturer.CameraSwitchHandler unused2 = CameraCapturer.this.switchEventsHandler = null;
                        }
                        SwitchState unused3 = CameraCapturer.this.switchState = SwitchState.IDLE;
                    }
                    if (CameraCapturer.this.mediaRecorderState != MediaRecorderState.IDLE) {
                        if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                            CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderError(str);
                            CameraVideoCapturer.MediaRecorderHandler unused4 = CameraCapturer.this.mediaRecorderEventsHandler = null;
                        }
                        MediaRecorderState unused5 = CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                    if (failureType == CameraSession.FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(str);
                    }
                } else {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, retry: " + str);
                    CameraCapturer.this.createSessionInternal(500, (MediaRecorder) null);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public CameraSession currentSession;
    /* access modifiers changed from: private */
    @Nullable
    public final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    /* access modifiers changed from: private */
    public boolean firstFrameObserved;
    /* access modifiers changed from: private */
    public int framerate;
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    @Nullable
    public CameraVideoCapturer.MediaRecorderHandler mediaRecorderEventsHandler;
    /* access modifiers changed from: private */
    public MediaRecorderState mediaRecorderState = MediaRecorderState.IDLE;
    /* access modifiers changed from: private */
    public int openAttemptsRemaining;
    /* access modifiers changed from: private */
    public final Runnable openCameraTimeoutRunnable = new Runnable() {
        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    };
    /* access modifiers changed from: private */
    public String pendingCameraName;
    /* access modifiers changed from: private */
    public boolean sessionOpening;
    /* access modifiers changed from: private */
    public final Object stateLock = new Object();
    /* access modifiers changed from: private */
    @Nullable
    public SurfaceTextureHelper surfaceHelper;
    /* access modifiers changed from: private */
    @Nullable
    public CameraVideoCapturer.CameraSwitchHandler switchEventsHandler;
    /* access modifiers changed from: private */
    public SwitchState switchState = SwitchState.IDLE;
    /* access modifiers changed from: private */
    public final Handler uiThreadHandler;
    /* access modifiers changed from: private */
    public int width;

    enum MediaRecorderState {
        IDLE,
        IDLE_TO_ACTIVE,
        ACTIVE_TO_IDLE,
        ACTIVE
    }

    enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    /* access modifiers changed from: protected */
    public abstract void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback2, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, String str, int i, int i2, int i3);

    public boolean isScreencast() {
        return false;
    }

    static /* synthetic */ int access$1810(CameraCapturer cameraCapturer) {
        int i = cameraCapturer.openAttemptsRemaining;
        cameraCapturer.openAttemptsRemaining = i - 1;
        return i;
    }

    public CameraCapturer(String str, @Nullable CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, CameraEnumerator cameraEnumerator2) {
        this.eventsHandler = cameraEventsHandler == null ? new CameraVideoCapturer.CameraEventsHandler() {
            public void onCameraClosed() {
            }

            public void onCameraDisconnected() {
            }

            public void onCameraError(String str) {
            }

            public void onCameraFreezed(String str) {
            }

            public void onCameraOpening(String str) {
            }

            public void onFirstFrameAvailable() {
            }
        } : cameraEventsHandler;
        this.cameraEnumerator = cameraEnumerator2;
        this.cameraName = str;
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        String[] deviceNames = cameraEnumerator2.getDeviceNames();
        if (deviceNames.length == 0) {
            throw new RuntimeException("No cameras attached.");
        } else if (!Arrays.asList(deviceNames).contains(this.cameraName)) {
            throw new IllegalArgumentException("Camera name " + this.cameraName + " does not match any known camera device.");
        }
    }

    public void initialize(@Nullable SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver2) {
        Handler handler;
        this.applicationContext = context;
        this.capturerObserver = capturerObserver2;
        this.surfaceHelper = surfaceTextureHelper;
        if (surfaceTextureHelper == null) {
            handler = null;
        } else {
            handler = surfaceTextureHelper.getHandler();
        }
        this.cameraThreadHandler = handler;
    }

    public void startCapture(int i, int i2, int i3) {
        Logging.d(TAG, "startCapture: " + i + "x" + i2 + "@" + i3);
        if (this.applicationContext != null) {
            synchronized (this.stateLock) {
                if (!this.sessionOpening) {
                    if (this.currentSession == null) {
                        this.width = i;
                        this.height = i2;
                        this.framerate = i3;
                        this.sessionOpening = true;
                        this.openAttemptsRemaining = 3;
                        createSessionInternal(0, (MediaRecorder) null);
                        return;
                    }
                }
                Logging.w(TAG, "Session already open");
                return;
            }
        }
        throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
    }

    /* access modifiers changed from: private */
    public void createSessionInternal(int i, final MediaRecorder mediaRecorder) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, (long) (i + 10000));
        this.cameraThreadHandler.postDelayed(new Runnable() {
            public void run() {
                CameraCapturer cameraCapturer = CameraCapturer.this;
                cameraCapturer.createCameraSession(cameraCapturer.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceHelper, mediaRecorder, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
            }
        }, (long) i);
    }

    public void stopCapture() {
        Logging.d(TAG, "Stop capture");
        synchronized (this.stateLock) {
            while (this.sessionOpening) {
                Logging.d(TAG, "Stop capture: Waiting for session to open");
                try {
                    this.stateLock.wait();
                } catch (InterruptedException unused) {
                    Logging.w(TAG, "Stop capture interrupted while waiting for the session to open.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (this.currentSession != null) {
                Logging.d(TAG, "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() {
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.d(TAG, "Stop capture: No session open");
            }
        }
        Logging.d(TAG, "Stop capture done");
    }

    public void changeCaptureFormat(int i, int i2, int i3) {
        Logging.d(TAG, "changeCaptureFormat: " + i + "x" + i2 + "@" + i3);
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(i, i2, i3);
        }
    }

    public void dispose() {
        Logging.d(TAG, "dispose");
        stopCapture();
    }

    public void switchCamera(final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                String[] deviceNames = CameraCapturer.this.cameraEnumerator.getDeviceNames();
                String unused = CameraCapturer.this.pendingCameraName = deviceNames[(Arrays.asList(deviceNames).indexOf(CameraCapturer.this.cameraName) + 1) % deviceNames.length];
                CameraCapturer.this.switchCameraInternal(cameraSwitchHandler);
            }
        });
    }

    public void switchCamera(final String str, final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                String unused = CameraCapturer.this.pendingCameraName = str;
                CameraCapturer.this.switchCameraInternal(cameraSwitchHandler);
            }
        });
    }

    public void addMediaRecorderToCamera(final MediaRecorder mediaRecorder, final CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        Logging.d(TAG, "addMediaRecorderToCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal(mediaRecorder, mediaRecorderHandler);
            }
        });
    }

    public void removeMediaRecorderFromCamera(final CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        Logging.d(TAG, "removeMediaRecorderFromCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal((MediaRecorder) null, mediaRecorderHandler);
            }
        });
    }

    public void printStackTrace() {
        Handler handler = this.cameraThreadHandler;
        Thread thread = handler != null ? handler.getLooper().getThread() : null;
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, "CameraCapturer stack trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    @Nullable
    public CameraSession getCameraSession() {
        CameraSession cameraSession;
        synchronized (this.stateLock) {
            cameraSession = this.currentSession;
        }
        return cameraSession;
    }

    private void reportCameraSwitchError(String str, @Nullable CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.e(TAG, str);
        if (cameraSwitchHandler != null) {
            cameraSwitchHandler.onCameraSwitchError(str);
        }
    }

    /* access modifiers changed from: private */
    public void switchCameraInternal(@Nullable CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        checkIsOnCameraThread();
        Logging.d(TAG, "switchCamera internal");
        if (this.cameraEnumerator.getDeviceNames().length >= 2) {
            synchronized (this.stateLock) {
                if (this.switchState != SwitchState.IDLE) {
                    reportCameraSwitchError("Camera switch already in progress.", cameraSwitchHandler);
                } else if (this.mediaRecorderState != MediaRecorderState.IDLE) {
                    reportCameraSwitchError("switchCamera: media recording is active", cameraSwitchHandler);
                } else {
                    boolean z = this.sessionOpening;
                    if (z || this.currentSession != null) {
                        this.switchEventsHandler = cameraSwitchHandler;
                        if (z) {
                            this.switchState = SwitchState.PENDING;
                            return;
                        }
                        this.switchState = SwitchState.IN_PROGRESS;
                        Logging.d(TAG, "switchCamera: Stopping session");
                        this.cameraStatistics.release();
                        this.cameraStatistics = null;
                        final CameraSession cameraSession = this.currentSession;
                        this.cameraThreadHandler.post(new Runnable() {
                            public void run() {
                                cameraSession.stop();
                            }
                        });
                        this.currentSession = null;
                        this.cameraName = this.pendingCameraName;
                        this.sessionOpening = true;
                        this.openAttemptsRemaining = 1;
                        createSessionInternal(0, (MediaRecorder) null);
                        Logging.d(TAG, "switchCamera done");
                        return;
                    }
                    reportCameraSwitchError("switchCamera: camera is not running.", cameraSwitchHandler);
                }
            }
        } else if (cameraSwitchHandler != null) {
            cameraSwitchHandler.onCameraSwitchError("No camera to switch to.");
        }
    }

    private void reportUpdateMediaRecorderError(String str, @Nullable CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        checkIsOnCameraThread();
        Logging.e(TAG, str);
        if (mediaRecorderHandler != null) {
            mediaRecorderHandler.onMediaRecorderError(str);
        }
    }

    /* access modifiers changed from: private */
    public void updateMediaRecorderInternal(@Nullable MediaRecorder mediaRecorder, CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        checkIsOnCameraThread();
        boolean z = mediaRecorder != null;
        Logging.d(TAG, "updateMediaRecoderInternal internal. State: " + this.mediaRecorderState + ". Switch state: " + this.switchState + ". Add MediaRecorder: " + z);
        synchronized (this.stateLock) {
            if (z) {
                try {
                    if (this.mediaRecorderState == MediaRecorderState.IDLE) {
                    }
                    reportUpdateMediaRecorderError("Incorrect state for MediaRecorder update.", mediaRecorderHandler);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (z || this.mediaRecorderState == MediaRecorderState.ACTIVE) {
                if (this.switchState != SwitchState.IDLE) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is switching.", mediaRecorderHandler);
                    return;
                } else if (this.currentSession == null) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is closed.", mediaRecorderHandler);
                    return;
                } else if (this.sessionOpening) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is still opening.", mediaRecorderHandler);
                    return;
                } else {
                    this.mediaRecorderEventsHandler = mediaRecorderHandler;
                    this.mediaRecorderState = z ? MediaRecorderState.IDLE_TO_ACTIVE : MediaRecorderState.ACTIVE_TO_IDLE;
                    Logging.d(TAG, "updateMediaRecoder: Stopping session");
                    this.cameraStatistics.release();
                    this.cameraStatistics = null;
                    final CameraSession cameraSession = this.currentSession;
                    this.cameraThreadHandler.post(new Runnable() {
                        public void run() {
                            cameraSession.stop();
                        }
                    });
                    this.currentSession = null;
                    this.sessionOpening = true;
                    this.openAttemptsRemaining = 1;
                    createSessionInternal(0, mediaRecorder);
                    Logging.d(TAG, "updateMediaRecoderInternal done");
                    return;
                }
            }
            reportUpdateMediaRecorderError("Incorrect state for MediaRecorder update.", mediaRecorderHandler);
        }
    }

    /* access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            Logging.e(TAG, "Check is on camera thread failed.");
            throw new RuntimeException("Not on camera thread.");
        }
    }

    /* access modifiers changed from: protected */
    public String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }
}
