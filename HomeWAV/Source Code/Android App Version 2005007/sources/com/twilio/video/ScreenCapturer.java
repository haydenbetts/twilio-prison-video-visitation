package com.twilio.video;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.twilio.video.ScreenCapturer;
import com.twilio.video.VideoCapturer;
import com.twilio.video.VideoFrame;
import java.util.ArrayList;
import java.util.List;
import tvi.webrtc.ScreenCapturerAndroid;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

public class ScreenCapturer implements VideoCapturer {
    private static final int SCREENCAPTURE_FRAME_RATE = 30;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ScreenCapturer.class);
    /* access modifiers changed from: private */
    public VideoCapturer.Listener capturerListener;
    private final Context context;
    /* access modifiers changed from: private */
    public boolean firstFrameReported;
    /* access modifiers changed from: private */
    public final Handler listenerHandler;
    private final MediaProjection.Callback mediaProjectionCallback = new MediaProjection.Callback() {
        public void onStop() {
            super.onStop();
            ScreenCapturer.logger.d("media projection stopped");
        }
    };
    private final VideoCapturer.CapturerObserver observerAdapter = new VideoCapturer.CapturerObserver() {
        public /* synthetic */ void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
            VideoCapturer.CapturerObserver.CC.$default$onByteBufferFrameCaptured(this, bArr, i, i2, i3, j);
        }

        public /* synthetic */ void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
            VideoCapturer.CapturerObserver.CC.$default$onTextureFrameCaptured(this, i, i2, i3, fArr, i4, j);
        }

        public void onCapturerStarted(boolean z) {
            ScreenCapturer.logger.d("screen capturer started");
            if (!z && ScreenCapturer.this.screenCapturerListener != null) {
                ScreenCapturer.this.listenerHandler.post(new Runnable() {
                    public final void run() {
                        ScreenCapturer.AnonymousClass2.this.lambda$onCapturerStarted$0$ScreenCapturer$2();
                    }
                });
            }
            ScreenCapturer.this.capturerListener.onCapturerStarted(z);
        }

        public /* synthetic */ void lambda$onCapturerStarted$0$ScreenCapturer$2() {
            ScreenCapturer.this.screenCapturerListener.onScreenCaptureError("Failed to start screen capturer");
        }

        public void onCapturerStopped() {
            ScreenCapturer.logger.d("screen capturer stopped");
        }

        public void onFrameCaptured(VideoFrame videoFrame) {
            if (!ScreenCapturer.this.firstFrameReported) {
                if (ScreenCapturer.this.screenCapturerListener != null) {
                    Handler access$200 = ScreenCapturer.this.listenerHandler;
                    Listener access$100 = ScreenCapturer.this.screenCapturerListener;
                    access$100.getClass();
                    access$200.post(new Runnable() {
                        public final void run() {
                            ScreenCapturer.Listener.this.onFirstFrameAvailable();
                        }
                    });
                }
                boolean unused = ScreenCapturer.this.firstFrameReported = true;
            }
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            VideoDimensions unused2 = ScreenCapturer.this.videoDimensions = new VideoDimensions(buffer.getWidth(), buffer.getHeight());
            VideoFrame.RotationAngle fromInt = VideoFrame.RotationAngle.fromInt(ScreenCapturer.this.getDeviceOrientation());
            ScreenCapturer.this.capturerListener.onFrameCaptured(new VideoFrame(videoFrame, ScreenCapturer.this.videoDimensions, ScreenCapturer.this.orientation));
            ScreenCapturer screenCapturer = ScreenCapturer.this;
            if (screenCapturer.updateCaptureDimensions(screenCapturer.orientation, fromInt)) {
                ScreenCapturer.logger.d("Swapping width and height of frame due to orientation");
                VideoFrame.RotationAngle unused3 = ScreenCapturer.this.orientation = fromInt;
                ScreenCapturer.this.webRtcScreenCapturer.changeCaptureFormat(buffer.getHeight(), buffer.getWidth(), 30);
            }
        }
    };
    /* access modifiers changed from: private */
    public VideoFrame.RotationAngle orientation;
    private final Intent screenCaptureIntentData;
    private final int screenCaptureIntentResult;
    /* access modifiers changed from: private */
    public final Listener screenCapturerListener;
    private SurfaceTextureHelper surfaceTextureHelper;
    /* access modifiers changed from: private */
    public VideoDimensions videoDimensions;
    /* access modifiers changed from: private */
    public ScreenCapturerAndroid webRtcScreenCapturer;

    public interface Listener {
        void onFirstFrameAvailable();

        void onScreenCaptureError(String str);
    }

    public boolean isScreencast() {
        return true;
    }

    public ScreenCapturer(Context context2, int i, Intent intent, Listener listener) {
        boolean z = Build.VERSION.SDK_INT >= 21;
        Preconditions.checkState(z, "Screen capturing unavailable for " + Build.VERSION.SDK_INT);
        Preconditions.checkNotNull(context2, "context must not be null");
        Preconditions.checkNotNull(intent, "intent must not be null");
        this.context = context2;
        this.screenCaptureIntentData = intent;
        this.screenCaptureIntentResult = i;
        this.screenCapturerListener = listener;
        this.listenerHandler = Util.createCallbackHandler();
        this.orientation = VideoFrame.RotationAngle.fromInt(getDeviceOrientation());
    }

    public synchronized List<VideoFormat> getSupportedFormats() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        arrayList.add(new VideoFormat(new VideoDimensions(displayMetrics.widthPixels, displayMetrics.heightPixels), 30, VideoPixelFormat.RGBA_8888));
        return arrayList;
    }

    public void startCapture(VideoFormat videoFormat, VideoCapturer.Listener listener) {
        this.capturerListener = listener;
        this.firstFrameReported = false;
        if (this.screenCaptureIntentResult != -1) {
            if (this.screenCapturerListener != null) {
                this.listenerHandler.post(new Runnable() {
                    public final void run() {
                        ScreenCapturer.this.lambda$startCapture$0$ScreenCapturer();
                    }
                });
            }
            listener.onCapturerStarted(false);
            return;
        }
        ScreenCapturerAndroid screenCapturerAndroid = new ScreenCapturerAndroid(this.screenCaptureIntentData, this.mediaProjectionCallback);
        this.webRtcScreenCapturer = screenCapturerAndroid;
        screenCapturerAndroid.initialize(this.surfaceTextureHelper, this.context, this.observerAdapter);
        this.webRtcScreenCapturer.startCapture(videoFormat.dimensions.width, videoFormat.dimensions.height, videoFormat.framerate);
    }

    public /* synthetic */ void lambda$startCapture$0$ScreenCapturer() {
        this.screenCapturerListener.onScreenCaptureError("MediaProjection permissions must be granted to start ScreenCapturer");
    }

    public void stopCapture() {
        Logger logger2 = logger;
        logger2.d("stopCapture");
        ScreenCapturerAndroid screenCapturerAndroid = this.webRtcScreenCapturer;
        if (screenCapturerAndroid != null) {
            screenCapturerAndroid.stopCapture();
            this.webRtcScreenCapturer.dispose();
            this.webRtcScreenCapturer = null;
        }
        logger2.d("stopCapture done");
    }

    /* access modifiers changed from: private */
    public int getDeviceOrientation() {
        int rotation = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    /* access modifiers changed from: private */
    public boolean updateCaptureDimensions(VideoFrame.RotationAngle rotationAngle, VideoFrame.RotationAngle rotationAngle2) {
        if (rotationAngle == rotationAngle2 || Math.abs(rotationAngle.getValue() - rotationAngle2.getValue()) == 180) {
            logger.d("No orientation change detected");
            return false;
        }
        logger.d("Orientation change detected");
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setSurfaceTextureHelper(SurfaceTextureHelper surfaceTextureHelper2) {
        this.surfaceTextureHelper = surfaceTextureHelper2;
    }

    /* access modifiers changed from: package-private */
    public VideoDimensions getVideoDimensions() {
        return this.videoDimensions;
    }
}
