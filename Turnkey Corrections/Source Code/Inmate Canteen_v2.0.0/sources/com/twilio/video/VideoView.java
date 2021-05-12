package com.twilio.video;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.twilio.video.VideoRenderer;
import tvi.webrtc.RendererCommon;
import tvi.webrtc.SurfaceViewRenderer;

public class VideoView extends SurfaceViewRenderer implements VideoRenderer {
    private static final Logger logger = Logger.getLogger(VideoView.class);
    private EglBaseProvider eglBaseProvider;
    private final RendererCommon.RendererEvents internalEventListener;
    /* access modifiers changed from: private */
    public VideoRenderer.Listener listener;
    private boolean mirror;
    private boolean overlaySurface;
    private final Handler uiThreadHandler;
    private VideoScaleType videoScaleType;

    public VideoView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public VideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        this.internalEventListener = new RendererCommon.RendererEvents() {
            public void onFirstFrameRendered() {
                VideoView.this.refreshRenderer();
                if (VideoView.this.listener != null) {
                    VideoView.this.listener.onFirstFrame();
                }
            }

            public void onFrameResolutionChanged(int i, int i2, int i3) {
                VideoView.this.refreshRenderer();
                if (VideoView.this.listener != null) {
                    VideoView.this.listener.onFrameDimensionsChanged(i, i2, i3);
                }
            }
        };
        this.mirror = false;
        this.overlaySurface = false;
        this.videoScaleType = VideoScaleType.ASPECT_FIT;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.VideoView, 0, 0);
        try {
            this.mirror = obtainStyledAttributes.getBoolean(R.styleable.VideoView_tviMirror, false);
            this.videoScaleType = VideoScaleType.fromInt(obtainStyledAttributes.getInteger(R.styleable.VideoView_tviScaleType, 0));
            this.overlaySurface = obtainStyledAttributes.getBoolean(R.styleable.VideoView_tviOverlaySurface, false);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.eglBaseProvider = EglBaseProvider.instance(this);
            setupRenderer();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.release();
        this.eglBaseProvider.release(this);
        super.onDetachedFromWindow();
    }

    public boolean getMirror() {
        return this.mirror;
    }

    public void setMirror(boolean z) {
        this.mirror = z;
        super.setMirror(z);
        refreshRenderer();
    }

    @NonNull
    public VideoScaleType getVideoScaleType() {
        return this.videoScaleType;
    }

    public void setVideoScaleType(@NonNull VideoScaleType videoScaleType2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null && (layoutParams.width == -1 || layoutParams.height == -1)) {
            logger.w(String.format("Scale type may not be applied as expected because video view uses MATCH_PARENT. Scaling will be applied as follows: width=%s, height=%s", new Object[]{(layoutParams.width == -1 ? VideoScaleType.ASPECT_FILL : videoScaleType2).name(), (layoutParams.height == -1 ? VideoScaleType.ASPECT_FILL : videoScaleType2).name()}));
        }
        this.videoScaleType = videoScaleType2;
        setScalingType(convertToWebRtcScaleType(videoScaleType2));
        refreshRenderer();
    }

    public void setListener(@Nullable VideoRenderer.Listener listener2) {
        this.listener = listener2;
    }

    public void renderFrame(@NonNull I420Frame i420Frame) {
        super.renderFrame(i420Frame.webRtcI420Frame);
    }

    public void applyZOrder(boolean z) {
        this.overlaySurface = z;
        setZOrderMediaOverlay(z);
    }

    private void setupRenderer() {
        init(this.eglBaseProvider.getRootEglBase().getEglBaseContext(), this.internalEventListener);
        setMirror(this.mirror);
        setScalingType(convertToWebRtcScaleType(this.videoScaleType));
        setZOrderMediaOverlay(this.overlaySurface);
    }

    /* access modifiers changed from: private */
    public void refreshRenderer() {
        this.uiThreadHandler.post(new Runnable() {
            public final void run() {
                VideoView.this.requestLayout();
            }
        });
    }

    private RendererCommon.ScalingType convertToWebRtcScaleType(VideoScaleType videoScaleType2) {
        switch (videoScaleType2) {
            case ASPECT_FIT:
                return RendererCommon.ScalingType.SCALE_ASPECT_FIT;
            case ASPECT_FILL:
                return RendererCommon.ScalingType.SCALE_ASPECT_FILL;
            case ASPECT_BALANCED:
                return RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
            default:
                return RendererCommon.ScalingType.SCALE_ASPECT_FIT;
        }
    }
}
