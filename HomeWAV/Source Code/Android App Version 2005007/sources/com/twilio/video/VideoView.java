package com.twilio.video;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ViewGroup;
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

    public VideoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
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

    public VideoScaleType getVideoScaleType() {
        return this.videoScaleType;
    }

    public void setVideoScaleType(VideoScaleType videoScaleType2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null && (layoutParams.width == -1 || layoutParams.height == -1)) {
            logger.w(String.format("Scale type may not be applied as expected because video view uses MATCH_PARENT. Scaling will be applied as follows: width=%s, height=%s", new Object[]{(layoutParams.width == -1 ? VideoScaleType.ASPECT_FILL : videoScaleType2).name(), (layoutParams.height == -1 ? VideoScaleType.ASPECT_FILL : videoScaleType2).name()}));
        }
        this.videoScaleType = videoScaleType2;
        setScalingType(convertToWebRtcScaleType(videoScaleType2));
        refreshRenderer();
    }

    public void setListener(VideoRenderer.Listener listener2) {
        this.listener = listener2;
    }

    public void renderFrame(I420Frame i420Frame) {
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

    /* renamed from: com.twilio.video.VideoView$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$twilio$video$VideoScaleType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.twilio.video.VideoScaleType[] r0 = com.twilio.video.VideoScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$twilio$video$VideoScaleType = r0
                com.twilio.video.VideoScaleType r1 = com.twilio.video.VideoScaleType.ASPECT_FIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$twilio$video$VideoScaleType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.twilio.video.VideoScaleType r1 = com.twilio.video.VideoScaleType.ASPECT_FILL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$twilio$video$VideoScaleType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.twilio.video.VideoScaleType r1 = com.twilio.video.VideoScaleType.ASPECT_BALANCED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.twilio.video.VideoView.AnonymousClass2.<clinit>():void");
        }
    }

    private RendererCommon.ScalingType convertToWebRtcScaleType(VideoScaleType videoScaleType2) {
        int i = AnonymousClass2.$SwitchMap$com$twilio$video$VideoScaleType[videoScaleType2.ordinal()];
        if (i == 1) {
            return RendererCommon.ScalingType.SCALE_ASPECT_FIT;
        }
        if (i == 2) {
            return RendererCommon.ScalingType.SCALE_ASPECT_FILL;
        }
        if (i != 3) {
            return RendererCommon.ScalingType.SCALE_ASPECT_FIT;
        }
        return RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
    }
}
