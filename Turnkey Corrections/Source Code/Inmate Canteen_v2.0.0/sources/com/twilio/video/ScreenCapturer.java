package com.twilio.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.twilio.video.ScreenCapturer;
import com.twilio.video.VideoCapturer;
import com.twilio.video.VideoFrame;
import java.util.ArrayList;
import java.util.List;
import tvi.webrtc.ScreenCapturerAndroid;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

@TargetApi(21)
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
        @Deprecated
        public /* synthetic */ void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
            VideoCapturer.CapturerObserver.CC.$default$onByteBufferFrameCaptured(this, bArr, i, i2, i3, j);
        }

        @Deprecated
        public /* synthetic */ void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
            VideoCapturer.CapturerObserver.CC.$default$onTextureFrameCaptured(this, i, i2, i3, fArr, i4, j);
        }

        public void onCapturerStarted(boolean z) {
            ScreenCapturer.logger.d("screen capturer started");
            if (!z && ScreenCapturer.this.screenCapturerListener != null) {
                ScreenCapturer.this.listenerHandler.post(new Runnable() {
                    public final void run() {
                        ScreenCapturer.this.screenCapturerListener.onScreenCaptureError("Failed to start screen capturer");
                    }
                });
            }
            ScreenCapturer.this.capturerListener.onCapturerStarted(z);
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

        void onScreenCaptureError(@NonNull String str);
    }

    public boolean isScreencast() {
        return true;
    }

    public ScreenCapturer(@NonNull Context context2, int i, @NonNull Intent intent, @Nullable Listener listener) {
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

    @NonNull
    public synchronized List<VideoFormat> getSupportedFormats() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        arrayList.add(new VideoFormat(new VideoDimensions(displayMetrics.widthPixels, displayMetrics.heightPixels), 30, VideoPixelFormat.RGBA_8888));
        return arrayList;
    }

    public void startCapture(@NonNull VideoFormat videoFormat, @NonNull VideoCapturer.Listener listener) {
        this.capturerListener = listener;
        this.firstFrameReported = false;
        if (this.screenCaptureIntentResult != -1) {
            if (this.screenCapturerListener != null) {
                this.listenerHandler.post(new Runnable() {
                    public final void run() {
                        ScreenCapturer.this.screenCapturerListener.onScreenCaptureError("MediaProjection permissions must be granted to start ScreenCapturer");
                    }
                });
            }
            listener.onCapturerStarted(false);
            return;
        }
        this.webRtcScreenCapturer = new ScreenCapturerAndroid(this.screenCaptureIntentData, this.mediaProjectionCallback);
        this.webRtcScreenCapturer.initialize(this.surfaceTextureHelper, this.context, this.observerAdapter);
        this.webRtcScreenCapturer.startCapture(videoFormat.dimensions.width, videoFormat.dimensions.height, videoFormat.framerate);
    }

    public void stopCapture() {
        logger.d("stopCapture");
        ScreenCapturerAndroid screenCapturerAndroid = this.webRtcScreenCapturer;
        if (screenCapturerAndroid != null) {
            screenCapturerAndroid.stopCapture();
            this.webRtcScreenCapturer.dispose();
            this.webRtcScreenCapturer = null;
        }
        logger.d("stopCapture done");
    }

    /* access modifiers changed from: private */
    public int getDeviceOrientation() {
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
    @VisibleForTesting(otherwise = 5)
    public VideoDimensions getVideoDimensions() {
        return this.videoDimensions;
    }
}
