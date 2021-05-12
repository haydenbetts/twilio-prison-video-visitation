package com.twilio.video;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.twilio.video.VideoRenderer;
import java.util.concurrent.CountDownLatch;
import tvi.webrtc.EglBase;
import tvi.webrtc.EglRenderer;
import tvi.webrtc.GlRectDrawer;
import tvi.webrtc.RendererCommon;
import tvi.webrtc.ThreadUtils;

public class VideoTextureView extends TextureView implements VideoRenderer, TextureView.SurfaceTextureListener {
    private static final Logger logger = Logger.getLogger(VideoTextureView.class);
    private EglBaseProvider eglBaseProvider;
    private final EglRenderer eglRenderer;
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private final Object layoutLock;
    /* access modifiers changed from: private */
    public VideoRenderer.Listener listener;
    private boolean mirror;
    private RendererCommon.RendererEvents rendererEvents;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private Handler uiThreadHandler;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure;
    private VideoScaleType videoScaleType;

    public VideoTextureView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public VideoTextureView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        this.videoScaleType = VideoScaleType.ASPECT_FIT;
        this.rendererEvents = new RendererCommon.RendererEvents() {
            public void onFirstFrameRendered() {
                if (VideoTextureView.this.listener != null) {
                    VideoTextureView.this.listener.onFirstFrame();
                }
            }

            public void onFrameResolutionChanged(int i, int i2, int i3) {
                if (VideoTextureView.this.listener != null) {
                    VideoTextureView.this.listener.onFrameDimensionsChanged(i, i2, i3);
                }
            }
        };
        this.layoutLock = new Object();
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        this.eglRenderer = new EglRenderer(getResourceName());
        setSurfaceTextureListener(this);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.VideoTextureView, 0, 0);
        try {
            this.mirror = obtainStyledAttributes.getBoolean(R.styleable.VideoTextureView_tviMirror, false);
            this.videoScaleType = VideoScaleType.fromInt(obtainStyledAttributes.getInteger(R.styleable.VideoTextureView_tviScaleType, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.eglBaseProvider = EglBaseProvider.instance(this);
            init(this.eglBaseProvider.getRootEglBase().getEglBaseContext(), this.rendererEvents);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.eglRenderer.release();
        this.eglBaseProvider.release(this);
        super.onDetachedFromWindow();
    }

    public boolean getMirror() {
        return this.mirror;
    }

    public void setMirror(boolean z) {
        this.eglRenderer.setMirror(z);
        this.mirror = z;
        requestLayout();
    }

    @NonNull
    public VideoScaleType getVideoScaleType() {
        return this.videoScaleType;
    }

    public void setVideoScaleType(@NonNull VideoScaleType videoScaleType2) {
        ThreadUtils.checkIsOnMainThread();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null && (layoutParams.width == -1 || layoutParams.height == -1)) {
            logger.w(String.format("Scale type may not be applied as expected because video view uses MATCH_PARENT. Scaling will be applied as follows: width=%s, height=%s", new Object[]{(layoutParams.width == -1 ? VideoScaleType.ASPECT_FILL : this.videoScaleType).name(), (layoutParams.height == -1 ? VideoScaleType.ASPECT_FILL : this.videoScaleType).name()}));
        }
        this.videoLayoutMeasure.setScalingType(convertToWebRtcScaleType(videoScaleType2));
        this.videoScaleType = videoScaleType2;
        requestLayout();
    }

    public void setListener(@Nullable VideoRenderer.Listener listener2) {
        this.listener = listener2;
    }

    public void renderFrame(@NonNull I420Frame i420Frame) {
        updateFrameDimensionsAndReportEvents(i420Frame);
        this.eglRenderer.renderFrame(i420Frame.webRtcI420Frame);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Point measure;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            measure = this.videoLayoutMeasure.measure(i, i2, this.rotatedFrameWidth, this.rotatedFrameHeight);
        }
        setMeasuredDimension(measure.x, measure.y);
        Logger logger2 = logger;
        logger2.v("onMeasure(). New size: " + measure.x + "x" + measure.y);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.setLayoutAspectRatio(((float) (i3 - i)) / ((float) (i4 - i2)));
        updateSurfaceSize();
    }

    private void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents2) {
        init(context, rendererEvents2, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    private void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents2, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = rendererEvents2;
        synchronized (this.layoutLock) {
            this.rotatedFrameWidth = 0;
            this.rotatedFrameHeight = 0;
            this.frameRotation = 0;
        }
        this.eglRenderer.init(context, iArr, glDrawer);
    }

    private void updateSurfaceSize() {
        int i;
        int i2;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            if (this.rotatedFrameWidth == 0 || this.rotatedFrameHeight == 0 || getWidth() == 0 || getHeight() == 0) {
                this.surfaceHeight = 0;
                this.surfaceWidth = 0;
            } else {
                float width = ((float) getWidth()) / ((float) getHeight());
                if (((float) this.rotatedFrameWidth) / ((float) this.rotatedFrameHeight) > width) {
                    i2 = (int) (((float) this.rotatedFrameHeight) * width);
                    i = this.rotatedFrameHeight;
                } else {
                    int i3 = this.rotatedFrameWidth;
                    i = (int) (((float) this.rotatedFrameWidth) / width);
                    i2 = i3;
                }
                int min = Math.min(getWidth(), i2);
                int min2 = Math.min(getHeight(), i);
                Logger logger2 = logger;
                logger2.v("updateSurfaceSize. Layout size: " + getWidth() + "x" + getHeight() + ", frame size: " + this.rotatedFrameWidth + "x" + this.rotatedFrameHeight + ", requested surface size: " + min + "x" + min2 + ", old surface size: " + this.surfaceWidth + "x" + this.surfaceHeight);
                if (!(min == this.surfaceWidth && min2 == this.surfaceHeight)) {
                    this.surfaceWidth = min;
                    this.surfaceHeight = min2;
                }
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.createEglSurface(surfaceTexture);
        this.surfaceWidth = i;
        this.surfaceHeight = i2;
        updateSurfaceSize();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        ThreadUtils.checkIsOnMainThread();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        EglRenderer eglRenderer2 = this.eglRenderer;
        countDownLatch.getClass();
        eglRenderer2.releaseEglSurface(new Runnable(countDownLatch) {
            private final /* synthetic */ CountDownLatch f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        ThreadUtils.checkIsOnMainThread();
        Logger logger2 = logger;
        logger2.v("surfaceChanged: size: " + i + "x" + i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        ThreadUtils.checkIsOnMainThread();
        logger.v("onSurfaceTextureUpdated");
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private void updateFrameDimensionsAndReportEvents(I420Frame i420Frame) {
        synchronized (this.layoutLock) {
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                logger.v("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (!(this.rotatedFrameWidth == i420Frame.rotatedWidth() && this.rotatedFrameHeight == i420Frame.rotatedHeight() && this.frameRotation == i420Frame.rotationDegree)) {
                Logger logger2 = logger;
                logger2.v("Reporting frame resolution changed to " + i420Frame.width + "x" + i420Frame.height + " with rotation " + i420Frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.rotatedFrameWidth = i420Frame.rotatedWidth();
                this.rotatedFrameHeight = i420Frame.rotatedHeight();
                this.frameRotation = i420Frame.rotationDegree;
                this.uiThreadHandler.post(new Runnable() {
                    public final void run() {
                        VideoTextureView.lambda$updateFrameDimensionsAndReportEvents$0(VideoTextureView.this);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$updateFrameDimensionsAndReportEvents$0(VideoTextureView videoTextureView) {
        videoTextureView.updateSurfaceSize();
        videoTextureView.requestLayout();
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
