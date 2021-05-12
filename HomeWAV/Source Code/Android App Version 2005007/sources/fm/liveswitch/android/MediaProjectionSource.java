package fm.liveswitch.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import com.google.android.exoplayer2.C;
import fm.liveswitch.Future;
import fm.liveswitch.IAction0;
import fm.liveswitch.IAction1;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.Promise;
import fm.liveswitch.ScreenConfig;
import fm.liveswitch.ScreenSourceBase;
import fm.liveswitch.Size;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.nio.ByteBuffer;

public class MediaProjectionSource extends ScreenSourceBase {
    /* access modifiers changed from: private */
    public Context _context;
    /* access modifiers changed from: private */
    public Display _defaultDisplay;
    /* access modifiers changed from: private */
    public VirtualDisplay _display;
    /* access modifiers changed from: private */
    public ImageReader _imageReader;
    /* access modifiers changed from: private */
    public volatile boolean _isCapturing = false;
    /* access modifiers changed from: private */
    public volatile boolean _isStopped = true;
    /* access modifiers changed from: private */
    public int _lastRotation = -1;
    /* access modifiers changed from: private */
    public OrientationEventListener _orientationEventListener;
    /* access modifiers changed from: private */
    public MediaProjection _projection;

    public String getLabel() {
        return "Android MediaProjection Source";
    }

    public MediaProjectionSource(MediaProjection mediaProjection, Context context, double d) {
        super(VideoFormat.getBgra(), new ScreenConfig(0, 0, 0, 0, d));
        this._projection = mediaProjection;
        this._context = context;
        this._defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        this._orientationEventListener = new OrientationEventListener(context.getApplicationContext(), 3) {
            public void onOrientationChanged(int i) {
                MediaProjectionSource mediaProjectionSource = MediaProjectionSource.this;
                mediaProjectionSource.setRotation(mediaProjectionSource._defaultDisplay);
            }
        };
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    if (MediaProjectionSource.this._orientationEventListener.canDetectOrientation()) {
                        MediaProjectionSource.this._orientationEventListener.enable();
                    } else {
                        Log.error("Orientation event listener cannot detect orientation changes.");
                    }
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    Display defaultDisplay = ((WindowManager) MediaProjectionSource.this._context.getSystemService("window")).getDefaultDisplay();
                    defaultDisplay.getMetrics(displayMetrics);
                    Point point = new Point();
                    defaultDisplay.getSize(point);
                    MediaProjectionSource.this.setTargetSize(new Size(point.x, point.y));
                    MediaProjectionSource mediaProjectionSource = MediaProjectionSource.this;
                    mediaProjectionSource.setConfig(mediaProjectionSource.getTargetConfig());
                    ImageReader unused = MediaProjectionSource.this._imageReader = ImageReader.newInstance(point.x, point.y, 1, 2);
                    MediaProjectionSource mediaProjectionSource2 = MediaProjectionSource.this;
                    VirtualDisplay unused2 = mediaProjectionSource2._display = mediaProjectionSource2._projection.createVirtualDisplay("screencapture", point.x, point.y, displayMetrics.densityDpi, 16, MediaProjectionSource.this._imageReader.getSurface(), (VirtualDisplay.Callback) null, (Handler) null);
                    boolean unused3 = MediaProjectionSource.this._isCapturing = true;
                    boolean unused4 = MediaProjectionSource.this._isStopped = false;
                    int unused5 = MediaProjectionSource.this._lastRotation = -1;
                    MediaProjectionSource mediaProjectionSource3 = MediaProjectionSource.this;
                    mediaProjectionSource3.setRotation(mediaProjectionSource3._defaultDisplay);
                    new ManagedThread(new IAction1<ManagedThread>() {
                        public void invoke(ManagedThread managedThread) {
                            MediaProjectionSource.this.captureLoop(managedThread);
                        }
                    }).start();
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    boolean unused = MediaProjectionSource.this._isCapturing = false;
                    while (!MediaProjectionSource.this._isStopped) {
                        ManagedThread.sleep(10);
                    }
                    if (MediaProjectionSource.this._display != null) {
                        MediaProjectionSource.this._display.release();
                        VirtualDisplay unused2 = MediaProjectionSource.this._display = null;
                    }
                    if (MediaProjectionSource.this._imageReader != null) {
                        MediaProjectionSource.this._imageReader.close();
                        ImageReader unused3 = MediaProjectionSource.this._imageReader = null;
                    }
                    if (MediaProjectionSource.this._orientationEventListener != null) {
                        MediaProjectionSource.this._orientationEventListener.disable();
                    }
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    /* access modifiers changed from: private */
    public void captureLoop(ManagedThread managedThread) {
        while (this._isCapturing) {
            int width = getSize().getWidth();
            int height = getSize().getHeight();
            long nanoTime = System.nanoTime();
            if (width > 0 && height > 0) {
                int i = width;
                while (i % 2 != 0) {
                    i--;
                }
                int i2 = height;
                while (i2 % 2 != 0) {
                    i2--;
                }
                try {
                    Image acquireLatestImage = this._imageReader.acquireLatestImage();
                    if (acquireLatestImage != null) {
                        try {
                            Image.Plane[] planes = acquireLatestImage.getPlanes();
                            if (planes != null && planes.length == 1) {
                                Image.Plane plane = planes[0];
                                ByteBuffer buffer = plane.getBuffer();
                                int rowStride = plane.getRowStride() / plane.getPixelStride();
                                int[] iArr = new int[(rowStride * i2)];
                                buffer.asIntBuffer().get(iArr);
                                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                                createBitmap.setPixels(iArr, 0, rowStride, 0, 0, i, i2);
                                VideoBuffer bitmapToBuffer = ImageUtility.bitmapToBuffer(createBitmap);
                                bitmapToBuffer.setFormat(VideoFormat.getBgra());
                                raiseFrame(new VideoFrame(bitmapToBuffer));
                            }
                        } catch (Exception e) {
                            Log.error("Could not raise frame.", e);
                        } catch (Throwable th) {
                            acquireLatestImage.close();
                            throw th;
                        }
                        acquireLatestImage.close();
                    }
                } catch (Exception e2) {
                    Log.error("Could not raise screen image.", e2);
                }
            }
            ManagedThread.sleep(MathAssistant.max(0, (int) ((1000.0d / getFrameRate()) - ((double) ((System.nanoTime() - nanoTime) / C.MICROS_PER_SECOND)))));
        }
        this._isStopped = true;
    }

    public boolean setRotation(Display display) {
        int rotation = display.getRotation();
        if (rotation == this._lastRotation) {
            return false;
        }
        this._lastRotation = rotation;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        Point point = new Point();
        display.getSize(point);
        setTargetSize(new Size(point.x, point.y));
        setConfig(getTargetConfig());
        VirtualDisplay virtualDisplay = this._display;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            ImageReader imageReader = this._imageReader;
            if (imageReader != null) {
                imageReader.close();
                this._imageReader = null;
            }
            this._imageReader = ImageReader.newInstance(point.x, point.y, 1, 2);
            this._display = this._projection.createVirtualDisplay("screencapture", point.x, point.y, displayMetrics.densityDpi, 16, this._imageReader.getSurface(), (VirtualDisplay.Callback) null, (Handler) null);
        }
        return true;
    }
}
