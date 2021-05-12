package fm.liveswitch.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
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
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public class ViewSource extends ScreenSourceBase {
    private View _view;
    private Canvas canvas;
    private Bitmap image;
    /* access modifiers changed from: private */
    public volatile boolean isCapturing = false;
    /* access modifiers changed from: private */
    public volatile boolean isStopped = true;

    public String getLabel() {
        return "Android View Source";
    }

    public View getView() {
        return this._view;
    }

    public void setView(View view) {
        this._view = view;
    }

    public ViewSource(View view, double d) {
        super(VideoFormat.getArgb(), new ScreenConfig(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), d));
        this._view = view;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    ViewSource viewSource = ViewSource.this;
                    viewSource.setConfig(viewSource.getTargetConfig());
                    boolean unused = ViewSource.this.isCapturing = true;
                    boolean unused2 = ViewSource.this.isStopped = false;
                    new ManagedThread(new IAction1<ManagedThread>() {
                        public void invoke(ManagedThread managedThread) {
                            ViewSource.this.captureLoop(managedThread);
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
                    boolean unused = ViewSource.this.isCapturing = false;
                    while (!ViewSource.this.isStopped) {
                        ManagedThread.sleep(10);
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
        while (this.isCapturing) {
            final View view = this._view;
            int width = getSize().getWidth();
            int height = getSize().getHeight();
            long nanoTime = System.nanoTime();
            if (width > 0 && height > 0) {
                try {
                    Utility.dispatchToMainThread(new IAction0() {
                        public void invoke() {
                            view.setDrawingCacheEnabled(true);
                            view.buildDrawingCache();
                        }
                    }, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while (width % 2 != 0) {
                    width--;
                }
                while (height % 2 != 0) {
                    height--;
                }
                try {
                    Bitmap bitmap = this.image;
                    if (!(bitmap != null && bitmap.getWidth() == width && this.image.getHeight() == height)) {
                        this.image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        this.canvas = new Canvas(this.image);
                    }
                    view.draw(this.canvas);
                    raiseFrame(new VideoFrame(ImageUtility.bitmapToBuffer(this.image)));
                } catch (Exception e2) {
                    Log.error("Could not raise screen image.", e2);
                }
            }
            ManagedThread.sleep(MathAssistant.max(0, (int) ((1000.0d / getFrameRate()) - ((double) ((System.nanoTime() - nanoTime) / C.MICROS_PER_SECOND)))));
        }
    }
}
