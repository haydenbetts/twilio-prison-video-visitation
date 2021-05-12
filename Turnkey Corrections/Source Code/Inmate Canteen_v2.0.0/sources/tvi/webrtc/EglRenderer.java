package tvi.webrtc;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase;
import tvi.webrtc.EglRenderer;
import tvi.webrtc.RendererCommon;
import tvi.webrtc.VideoRenderer;

public class EglRenderer implements VideoRenderer.Callbacks, VideoSink {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final String TAG = "EglRenderer";
    @Nullable
    private GlTextureFrameBuffer bitmapTextureFramebuffer;
    private final Matrix drawMatrix = new Matrix();
    @Nullable
    private RendererCommon.GlDrawer drawer;
    /* access modifiers changed from: private */
    @Nullable
    public EglBase eglBase;
    private final EglSurfaceCreation eglSurfaceCreationRunnable = new EglSurfaceCreation();
    private final Object fpsReductionLock = new Object();
    private final VideoFrameDrawer frameDrawer = new VideoFrameDrawer();
    private final ArrayList<FrameListenerAndParams> frameListeners = new ArrayList<>();
    private final Object frameLock = new Object();
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    /* access modifiers changed from: private */
    public final Object handlerLock = new Object();
    private float layoutAspectRatio;
    private final Object layoutLock = new Object();
    /* access modifiers changed from: private */
    public final Runnable logStatisticsRunnable = new Runnable() {
        public void run() {
            EglRenderer.this.logStatistics();
            synchronized (EglRenderer.this.handlerLock) {
                if (EglRenderer.this.renderThreadHandler != null) {
                    EglRenderer.this.renderThreadHandler.removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                    EglRenderer.this.renderThreadHandler.postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
                }
            }
        }
    };
    private long minRenderPeriodNs;
    private boolean mirror;
    protected final String name;
    private long nextFrameTimeNs;
    @Nullable
    private VideoFrame pendingFrame;
    private long renderSwapBufferTimeNs;
    /* access modifiers changed from: private */
    @Nullable
    public Handler renderThreadHandler;
    private long renderTimeNs;
    private final Object statisticsLock = new Object();
    private long statisticsStartTimeNs;

    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    private static class FrameListenerAndParams {
        public final boolean applyFpsReduction;
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener frameListener, float f, RendererCommon.GlDrawer glDrawer, boolean z) {
            this.listener = frameListener;
            this.scale = f;
            this.drawer = glDrawer;
            this.applyFpsReduction = z;
        }
    }

    private class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        public synchronized void setSurface(Object obj) {
            this.surface = obj;
        }

        public synchronized void run() {
            if (!(this.surface == null || EglRenderer.this.eglBase == null || EglRenderer.this.eglBase.hasSurface())) {
                if (this.surface instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else if (this.surface instanceof SurfaceTexture) {
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                } else {
                    throw new IllegalStateException("Invalid surface: " + this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }
    }

    public EglRenderer(String str) {
        this.name = str;
    }

    public void init(@Nullable EglBase.Context context, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                logD("Initializing EglRenderer");
                this.drawer = glDrawer;
                HandlerThread handlerThread = new HandlerThread(this.name + TAG);
                handlerThread.start();
                this.renderThreadHandler = new Handler(handlerThread.getLooper());
                ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, (Runnable) new Runnable(context, iArr) {
                    private final /* synthetic */ EglBase.Context f$1;
                    private final /* synthetic */ int[] f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        EglRenderer.lambda$init$0(EglRenderer.this, this.f$1, this.f$2);
                    }
                });
                this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);
                resetStatistics(System.nanoTime());
                this.renderThreadHandler.postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
            } else {
                throw new IllegalStateException(this.name + "Already initialized");
            }
        }
    }

    public static /* synthetic */ void lambda$init$0(EglRenderer eglRenderer, EglBase.Context context, int[] iArr) {
        if (context == null) {
            eglRenderer.logD("EglBase10.create context");
            eglRenderer.eglBase = EglBase.CC.createEgl10(iArr);
            return;
        }
        eglRenderer.logD("EglBase.create shared context");
        eglRenderer.eglBase = EglBase.CC.create(context, iArr);
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }

    private void createEglSurfaceInternal(Object obj) {
        this.eglSurfaceCreationRunnable.setSurface(obj);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
        tvi.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r0 = r5.frameLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        if (r5.pendingFrame == null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r5.pendingFrame.release();
        r5.pendingFrame = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        logD("Releasing done.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
            r5 = this;
            java.lang.String r0 = "Releasing."
            r5.logD(r0)
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r1 = 1
            r0.<init>(r1)
            java.lang.Object r1 = r5.handlerLock
            monitor-enter(r1)
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x0019
            java.lang.String r0 = "Already released"
            r5.logD(r0)     // Catch:{ all -> 0x0059 }
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            return
        L_0x0019:
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0059 }
            java.lang.Runnable r3 = r5.logStatisticsRunnable     // Catch:{ all -> 0x0059 }
            r2.removeCallbacks(r3)     // Catch:{ all -> 0x0059 }
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0059 }
            tvi.webrtc.-$$Lambda$EglRenderer$3-eVGOWJj9JL2mk2RGltJJDJc44 r3 = new tvi.webrtc.-$$Lambda$EglRenderer$3-eVGOWJj9JL2mk2RGltJJDJc44     // Catch:{ all -> 0x0059 }
            r3.<init>(r0)     // Catch:{ all -> 0x0059 }
            r2.postAtFrontOfQueue(r3)     // Catch:{ all -> 0x0059 }
            android.os.Handler r2 = r5.renderThreadHandler     // Catch:{ all -> 0x0059 }
            android.os.Looper r2 = r2.getLooper()     // Catch:{ all -> 0x0059 }
            android.os.Handler r3 = r5.renderThreadHandler     // Catch:{ all -> 0x0059 }
            tvi.webrtc.-$$Lambda$EglRenderer$okkmcX-ZMDMZpK90A1fiQIzsJ9M r4 = new tvi.webrtc.-$$Lambda$EglRenderer$okkmcX-ZMDMZpK90A1fiQIzsJ9M     // Catch:{ all -> 0x0059 }
            r4.<init>(r2)     // Catch:{ all -> 0x0059 }
            r3.post(r4)     // Catch:{ all -> 0x0059 }
            r2 = 0
            r5.renderThreadHandler = r2     // Catch:{ all -> 0x0059 }
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            tvi.webrtc.ThreadUtils.awaitUninterruptibly(r0)
            java.lang.Object r0 = r5.frameLock
            monitor-enter(r0)
            tvi.webrtc.VideoFrame r1 = r5.pendingFrame     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x004f
            tvi.webrtc.VideoFrame r1 = r5.pendingFrame     // Catch:{ all -> 0x0056 }
            r1.release()     // Catch:{ all -> 0x0056 }
            r5.pendingFrame = r2     // Catch:{ all -> 0x0056 }
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            java.lang.String r0 = "Releasing done."
            r5.logD(r0)
            return
        L_0x0056:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r1
        L_0x0059:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.EglRenderer.release():void");
    }

    public static /* synthetic */ void lambda$release$1(EglRenderer eglRenderer, CountDownLatch countDownLatch) {
        RendererCommon.GlDrawer glDrawer = eglRenderer.drawer;
        if (glDrawer != null) {
            glDrawer.release();
            eglRenderer.drawer = null;
        }
        eglRenderer.frameDrawer.release();
        GlTextureFrameBuffer glTextureFrameBuffer = eglRenderer.bitmapTextureFramebuffer;
        if (glTextureFrameBuffer != null) {
            glTextureFrameBuffer.release();
            eglRenderer.bitmapTextureFramebuffer = null;
        }
        if (eglRenderer.eglBase != null) {
            eglRenderer.logD("eglBase detach and release.");
            eglRenderer.eglBase.detachCurrent();
            eglRenderer.eglBase.release();
            eglRenderer.eglBase = null;
        }
        eglRenderer.frameListeners.clear();
        countDownLatch.countDown();
    }

    public static /* synthetic */ void lambda$release$2(EglRenderer eglRenderer, Looper looper) {
        eglRenderer.logD("Quitting render thread.");
        looper.quit();
    }

    private void resetStatistics(long j) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = j;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0;
            this.renderSwapBufferTimeNs = 0;
        }
    }

    public void printStackTrace() {
        synchronized (this.handlerLock) {
            Thread thread = this.renderThreadHandler == null ? null : this.renderThreadHandler.getLooper().getThread();
            if (thread != null) {
                StackTraceElement[] stackTrace = thread.getStackTrace();
                if (stackTrace.length > 0) {
                    logD("EglRenderer stack trace:");
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        logD(stackTraceElement.toString());
                    }
                }
            }
        }
    }

    public void setMirror(boolean z) {
        logD("setMirror: " + z);
        synchronized (this.layoutLock) {
            this.mirror = z;
        }
    }

    public void setLayoutAspectRatio(float f) {
        logD("setLayoutAspectRatio: " + f);
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = f;
        }
    }

    public void setFpsReduction(float f) {
        logD("setFpsReduction: " + f);
        synchronized (this.fpsReductionLock) {
            long j = this.minRenderPeriodNs;
            if (f <= 0.0f) {
                this.minRenderPeriodNs = Long.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = (long) (((float) TimeUnit.SECONDS.toNanos(1)) / f);
            }
            if (this.minRenderPeriodNs != j) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void addFrameListener(FrameListener frameListener, float f) {
        addFrameListener(frameListener, f, (RendererCommon.GlDrawer) null, false);
    }

    public void addFrameListener(FrameListener frameListener, float f, RendererCommon.GlDrawer glDrawer) {
        addFrameListener(frameListener, f, glDrawer, false);
    }

    public void addFrameListener(FrameListener frameListener, float f, @Nullable RendererCommon.GlDrawer glDrawer, boolean z) {
        postToRenderThread(new Runnable(glDrawer, frameListener, f, z) {
            private final /* synthetic */ RendererCommon.GlDrawer f$1;
            private final /* synthetic */ EglRenderer.FrameListener f$2;
            private final /* synthetic */ float f$3;
            private final /* synthetic */ boolean f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                EglRenderer.lambda$addFrameListener$3(EglRenderer.this, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    public static /* synthetic */ void lambda$addFrameListener$3(EglRenderer eglRenderer, RendererCommon.GlDrawer glDrawer, FrameListener frameListener, float f, boolean z) {
        if (glDrawer == null) {
            glDrawer = eglRenderer.drawer;
        }
        eglRenderer.frameListeners.add(new FrameListenerAndParams(frameListener, f, glDrawer, z));
    }

    public void removeFrameListener(FrameListener frameListener) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                if (Thread.currentThread() != this.renderThreadHandler.getLooper().getThread()) {
                    postToRenderThread(new Runnable(countDownLatch, frameListener) {
                        private final /* synthetic */ CountDownLatch f$1;
                        private final /* synthetic */ EglRenderer.FrameListener f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            EglRenderer.lambda$removeFrameListener$4(EglRenderer.this, this.f$1, this.f$2);
                        }
                    });
                    ThreadUtils.awaitUninterruptibly(countDownLatch);
                    return;
                }
                throw new RuntimeException("removeFrameListener must not be called on the render thread.");
            }
        }
    }

    public static /* synthetic */ void lambda$removeFrameListener$4(EglRenderer eglRenderer, CountDownLatch countDownLatch, FrameListener frameListener) {
        countDownLatch.countDown();
        Iterator<FrameListenerAndParams> it = eglRenderer.frameListeners.iterator();
        while (it.hasNext()) {
            if (it.next().listener == frameListener) {
                it.remove();
            }
        }
    }

    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        VideoFrame videoFrame = i420Frame.toVideoFrame();
        onFrame(videoFrame);
        videoFrame.release();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        if (r3 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
        r6 = r5.statisticsLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r5.framesDropped++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0046, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFrame(tvi.webrtc.VideoFrame r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.statisticsLock
            monitor-enter(r0)
            int r1 = r5.framesReceived     // Catch:{ all -> 0x0052 }
            r2 = 1
            int r1 = r1 + r2
            r5.framesReceived = r1     // Catch:{ all -> 0x0052 }
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r5.handlerLock
            monitor-enter(r1)
            android.os.Handler r0 = r5.renderThreadHandler     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0018
            java.lang.String r6 = "Dropping frame - Not initialized or already released."
            r5.logD(r6)     // Catch:{ all -> 0x004f }
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            return
        L_0x0018:
            java.lang.Object r0 = r5.frameLock     // Catch:{ all -> 0x004f }
            monitor-enter(r0)     // Catch:{ all -> 0x004f }
            tvi.webrtc.VideoFrame r3 = r5.pendingFrame     // Catch:{ all -> 0x004c }
            if (r3 == 0) goto L_0x0021
            r3 = 1
            goto L_0x0022
        L_0x0021:
            r3 = 0
        L_0x0022:
            if (r3 == 0) goto L_0x0029
            tvi.webrtc.VideoFrame r4 = r5.pendingFrame     // Catch:{ all -> 0x004c }
            r4.release()     // Catch:{ all -> 0x004c }
        L_0x0029:
            r5.pendingFrame = r6     // Catch:{ all -> 0x004c }
            tvi.webrtc.VideoFrame r6 = r5.pendingFrame     // Catch:{ all -> 0x004c }
            r6.retain()     // Catch:{ all -> 0x004c }
            android.os.Handler r6 = r5.renderThreadHandler     // Catch:{ all -> 0x004c }
            tvi.webrtc.-$$Lambda$EglRenderer$Sx3nRsr2-qgPcNAc4Zo72ytb4R8 r4 = new tvi.webrtc.-$$Lambda$EglRenderer$Sx3nRsr2-qgPcNAc4Zo72ytb4R8     // Catch:{ all -> 0x004c }
            r4.<init>()     // Catch:{ all -> 0x004c }
            r6.post(r4)     // Catch:{ all -> 0x004c }
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x004b
            java.lang.Object r6 = r5.statisticsLock
            monitor-enter(r6)
            int r0 = r5.framesDropped     // Catch:{ all -> 0x0048 }
            int r0 = r0 + r2
            r5.framesDropped = r0     // Catch:{ all -> 0x0048 }
            monitor-exit(r6)     // Catch:{ all -> 0x0048 }
            goto L_0x004b
        L_0x0048:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0048 }
            throw r0
        L_0x004b:
            return
        L_0x004c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            throw r6     // Catch:{ all -> 0x004f }
        L_0x004f:
            r6 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            throw r6
        L_0x0052:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.EglRenderer.onFrame(tvi.webrtc.VideoFrame):void");
    }

    public void releaseEglSurface(Runnable runnable) {
        this.eglSurfaceCreationRunnable.setSurface((Object) null);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.removeCallbacks(this.eglSurfaceCreationRunnable);
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable(runnable) {
                    private final /* synthetic */ Runnable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EglRenderer.lambda$releaseEglSurface$5(EglRenderer.this, this.f$1);
                    }
                });
                return;
            }
            runnable.run();
        }
    }

    public static /* synthetic */ void lambda$releaseEglSurface$5(EglRenderer eglRenderer, Runnable runnable) {
        EglBase eglBase2 = eglRenderer.eglBase;
        if (eglBase2 != null) {
            eglBase2.detachCurrent();
            eglRenderer.eglBase.releaseSurface();
        }
        runnable.run();
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearSurfaceOnRenderThread(float f, float f2, float f3, float f4) {
        EglBase eglBase2 = this.eglBase;
        if (eglBase2 != null && eglBase2.hasSurface()) {
            logD("clearSurface");
            GLES20.glClearColor(f, f2, f3, f4);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    public void clearImage() {
        clearImage(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clearImage(float f, float f2, float f3, float f4) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable(f, f2, f3, f4) {
                    private final /* synthetic */ float f$1;
                    private final /* synthetic */ float f$2;
                    private final /* synthetic */ float f$3;
                    private final /* synthetic */ float f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        EglRenderer.this.clearSurfaceOnRenderThread(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r0 == null) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r0.hasSurface() != false) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r0 = r14.fpsReductionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        if (r14.minRenderPeriodNs != Long.MAX_VALUE) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        if (r14.minRenderPeriodNs > 0) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        r2 = java.lang.System.nanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
        if (r2 >= r14.nextFrameTimeNs) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        logD("Skipping frame rendering - fps reduction is active.");
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        r14.nextFrameTimeNs += r14.minRenderPeriodNs;
        r14.nextFrameTimeNs = java.lang.Math.max(r14.nextFrameTimeNs, r2);
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
        r12 = java.lang.System.nanoTime();
        r0 = ((float) r1.getRotatedWidth()) / ((float) r1.getRotatedHeight());
        r2 = r14.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0070, code lost:
        if (r14.layoutAspectRatio == 0.0f) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
        r3 = r14.layoutAspectRatio;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0075, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0076, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r0 <= r3) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007d, code lost:
        r0 = r3 / r0;
        r3 = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0082, code lost:
        r3 = r0 / r3;
        r0 = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0086, code lost:
        r14.drawMatrix.reset();
        r14.drawMatrix.preTranslate(0.5f, 0.5f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0094, code lost:
        if (r14.mirror == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        r14.drawMatrix.preScale(-1.0f, 1.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009d, code lost:
        r14.drawMatrix.preScale(r0, r3);
        r14.drawMatrix.preTranslate(-0.5f, -0.5f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a9, code lost:
        if (r11 == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ab, code lost:
        android.opengl.GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        android.opengl.GLES20.glClear(16384);
        r14.frameDrawer.drawFrame(r1, r14.drawer, r14.drawMatrix, 0, 0, r14.eglBase.surfaceWidth(), r14.eglBase.surfaceHeight());
        r2 = java.lang.System.nanoTime();
        r14.eglBase.swapBuffers();
        r4 = java.lang.System.nanoTime();
        r0 = r14.statisticsLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00da, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r14.framesRendered++;
        r14.renderTimeNs += r4 - r12;
        r14.renderSwapBufferTimeNs += r4 - r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ed, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f2, code lost:
        notifyCallbacks(r1, r11);
        r1.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f8, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ff, code lost:
        logD("Dropping frame - No surface");
        r1.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0107, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        r0 = r14.eglBase;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderFrameOnRenderThread() {
        /*
            r14 = this;
            java.lang.Object r0 = r14.frameLock
            monitor-enter(r0)
            tvi.webrtc.VideoFrame r1 = r14.pendingFrame     // Catch:{ all -> 0x0108 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0108 }
            return
        L_0x0009:
            tvi.webrtc.VideoFrame r1 = r14.pendingFrame     // Catch:{ all -> 0x0108 }
            r2 = 0
            r14.pendingFrame = r2     // Catch:{ all -> 0x0108 }
            monitor-exit(r0)     // Catch:{ all -> 0x0108 }
            tvi.webrtc.EglBase r0 = r14.eglBase
            if (r0 == 0) goto L_0x00ff
            boolean r0 = r0.hasSurface()
            if (r0 != 0) goto L_0x001b
            goto L_0x00ff
        L_0x001b:
            java.lang.Object r0 = r14.fpsReductionLock
            monitor-enter(r0)
            long r2 = r14.minRenderPeriodNs     // Catch:{ all -> 0x00fc }
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = 0
            r10 = 1
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x002d
            r11 = 0
            goto L_0x0058
        L_0x002d:
            long r2 = r14.minRenderPeriodNs     // Catch:{ all -> 0x00fc }
            r4 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x0037
            r11 = 1
            goto L_0x0058
        L_0x0037:
            long r2 = java.lang.System.nanoTime()     // Catch:{ all -> 0x00fc }
            long r4 = r14.nextFrameTimeNs     // Catch:{ all -> 0x00fc }
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 >= 0) goto L_0x0048
            java.lang.String r2 = "Skipping frame rendering - fps reduction is active."
            r14.logD(r2)     // Catch:{ all -> 0x00fc }
            r11 = 0
            goto L_0x0058
        L_0x0048:
            long r4 = r14.nextFrameTimeNs     // Catch:{ all -> 0x00fc }
            long r6 = r14.minRenderPeriodNs     // Catch:{ all -> 0x00fc }
            long r4 = r4 + r6
            r14.nextFrameTimeNs = r4     // Catch:{ all -> 0x00fc }
            long r4 = r14.nextFrameTimeNs     // Catch:{ all -> 0x00fc }
            long r2 = java.lang.Math.max(r4, r2)     // Catch:{ all -> 0x00fc }
            r14.nextFrameTimeNs = r2     // Catch:{ all -> 0x00fc }
            r11 = 1
        L_0x0058:
            monitor-exit(r0)     // Catch:{ all -> 0x00fc }
            long r12 = java.lang.System.nanoTime()
            int r0 = r1.getRotatedWidth()
            float r0 = (float) r0
            int r2 = r1.getRotatedHeight()
            float r2 = (float) r2
            float r0 = r0 / r2
            java.lang.Object r2 = r14.layoutLock
            monitor-enter(r2)
            float r3 = r14.layoutAspectRatio     // Catch:{ all -> 0x00f9 }
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x0075
            float r3 = r14.layoutAspectRatio     // Catch:{ all -> 0x00f9 }
            goto L_0x0076
        L_0x0075:
            r3 = r0
        L_0x0076:
            monitor-exit(r2)     // Catch:{ all -> 0x00f9 }
            r2 = 1065353216(0x3f800000, float:1.0)
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0082
            float r0 = r3 / r0
            r3 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0086
        L_0x0082:
            float r0 = r0 / r3
            r3 = r0
            r0 = 1065353216(0x3f800000, float:1.0)
        L_0x0086:
            android.graphics.Matrix r5 = r14.drawMatrix
            r5.reset()
            android.graphics.Matrix r5 = r14.drawMatrix
            r6 = 1056964608(0x3f000000, float:0.5)
            r5.preTranslate(r6, r6)
            boolean r5 = r14.mirror
            if (r5 == 0) goto L_0x009d
            android.graphics.Matrix r5 = r14.drawMatrix
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5.preScale(r6, r2)
        L_0x009d:
            android.graphics.Matrix r2 = r14.drawMatrix
            r2.preScale(r0, r3)
            android.graphics.Matrix r0 = r14.drawMatrix
            r2 = -1090519040(0xffffffffbf000000, float:-0.5)
            r0.preTranslate(r2, r2)
            if (r11 == 0) goto L_0x00f2
            android.opengl.GLES20.glClearColor(r4, r4, r4, r4)
            r0 = 16384(0x4000, float:2.2959E-41)
            android.opengl.GLES20.glClear(r0)
            tvi.webrtc.VideoFrameDrawer r2 = r14.frameDrawer
            tvi.webrtc.RendererCommon$GlDrawer r4 = r14.drawer
            android.graphics.Matrix r5 = r14.drawMatrix
            r6 = 0
            r7 = 0
            tvi.webrtc.EglBase r0 = r14.eglBase
            int r8 = r0.surfaceWidth()
            tvi.webrtc.EglBase r0 = r14.eglBase
            int r9 = r0.surfaceHeight()
            r3 = r1
            r2.drawFrame(r3, r4, r5, r6, r7, r8, r9)
            long r2 = java.lang.System.nanoTime()
            tvi.webrtc.EglBase r0 = r14.eglBase
            r0.swapBuffers()
            long r4 = java.lang.System.nanoTime()
            java.lang.Object r0 = r14.statisticsLock
            monitor-enter(r0)
            int r6 = r14.framesRendered     // Catch:{ all -> 0x00ef }
            int r6 = r6 + r10
            r14.framesRendered = r6     // Catch:{ all -> 0x00ef }
            long r6 = r14.renderTimeNs     // Catch:{ all -> 0x00ef }
            long r8 = r4 - r12
            long r6 = r6 + r8
            r14.renderTimeNs = r6     // Catch:{ all -> 0x00ef }
            long r6 = r14.renderSwapBufferTimeNs     // Catch:{ all -> 0x00ef }
            long r4 = r4 - r2
            long r6 = r6 + r4
            r14.renderSwapBufferTimeNs = r6     // Catch:{ all -> 0x00ef }
            monitor-exit(r0)     // Catch:{ all -> 0x00ef }
            goto L_0x00f2
        L_0x00ef:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ef }
            throw r1
        L_0x00f2:
            r14.notifyCallbacks(r1, r11)
            r1.release()
            return
        L_0x00f9:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00f9 }
            throw r0
        L_0x00fc:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00fc }
            throw r1
        L_0x00ff:
            java.lang.String r0 = "Dropping frame - No surface"
            r14.logD(r0)
            r1.release()
            return
        L_0x0108:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0108 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.EglRenderer.renderFrameOnRenderThread():void");
    }

    private void notifyCallbacks(VideoFrame videoFrame, boolean z) {
        if (!this.frameListeners.isEmpty()) {
            this.drawMatrix.reset();
            this.drawMatrix.preTranslate(0.5f, 0.5f);
            if (this.mirror) {
                this.drawMatrix.preScale(-1.0f, 1.0f);
            }
            this.drawMatrix.preScale(1.0f, -1.0f);
            this.drawMatrix.preTranslate(-0.5f, -0.5f);
            Iterator<FrameListenerAndParams> it = this.frameListeners.iterator();
            while (it.hasNext()) {
                FrameListenerAndParams next = it.next();
                if (z || !next.applyFpsReduction) {
                    it.remove();
                    int rotatedWidth = (int) (next.scale * ((float) videoFrame.getRotatedWidth()));
                    int rotatedHeight = (int) (next.scale * ((float) videoFrame.getRotatedHeight()));
                    if (rotatedWidth == 0 || rotatedHeight == 0) {
                        next.listener.onFrame((Bitmap) null);
                    } else {
                        if (this.bitmapTextureFramebuffer == null) {
                            this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
                        }
                        this.bitmapTextureFramebuffer.setSize(rotatedWidth, rotatedHeight);
                        GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
                        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
                        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                        GLES20.glClear(16384);
                        this.frameDrawer.drawFrame(videoFrame, next.drawer, this.drawMatrix, 0, 0, rotatedWidth, rotatedHeight);
                        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(rotatedWidth * rotatedHeight * 4);
                        GLES20.glViewport(0, 0, rotatedWidth, rotatedHeight);
                        GLES20.glReadPixels(0, 0, rotatedWidth, rotatedHeight, 6408, 5121, allocateDirect);
                        GLES20.glBindFramebuffer(36160, 0);
                        GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                        Bitmap createBitmap = Bitmap.createBitmap(rotatedWidth, rotatedHeight, Bitmap.Config.ARGB_8888);
                        createBitmap.copyPixelsFromBuffer(allocateDirect);
                        next.listener.onFrame(createBitmap);
                    }
                }
            }
        }
    }

    private String averageTimeAsString(long j, int i) {
        if (i <= 0) {
            return "NA";
        }
        return TimeUnit.NANOSECONDS.toMicros(j / ((long) i)) + " μs";
    }

    /* access modifiers changed from: private */
    public void logStatistics() {
        long nanoTime = System.nanoTime();
        synchronized (this.statisticsLock) {
            long j = nanoTime - this.statisticsStartTimeNs;
            if (j > 0) {
                float nanos = ((float) (((long) this.framesRendered) * TimeUnit.SECONDS.toNanos(1))) / ((float) j);
                logD("Duration: " + TimeUnit.NANOSECONDS.toMillis(j) + " ms. Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered + ". Render fps: " + String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(nanos)}) + ". Average render time: " + averageTimeAsString(this.renderTimeNs, this.framesRendered) + ". Average swapBuffer time: " + averageTimeAsString(this.renderSwapBufferTimeNs, this.framesRendered) + ".");
                resetStatistics(nanoTime);
            }
        }
    }

    private void logD(String str) {
        Logging.d(TAG, this.name + str);
    }
}
