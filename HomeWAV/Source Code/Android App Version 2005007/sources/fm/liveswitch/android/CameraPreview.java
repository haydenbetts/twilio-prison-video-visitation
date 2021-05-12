package fm.liveswitch.android;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import fm.liveswitch.IAction0;
import fm.liveswitch.LayoutFrame;
import fm.liveswitch.LayoutScale;
import fm.liveswitch.Log;

public class CameraPreview implements TextureView.SurfaceTextureListener, View.OnLayoutChangeListener {
    /* access modifiers changed from: private */
    public Camera _camera;
    /* access modifiers changed from: private */
    public Camera2Source _cameraDevice;
    private Object _cameraLock = new Object();
    /* access modifiers changed from: private */
    public TextureView _cameraView;
    /* access modifiers changed from: private */
    public ViewGroup _cameraViewContainer;
    /* access modifiers changed from: private */
    public Context _context;
    /* access modifiers changed from: private */
    public LayoutScale _scale;
    /* access modifiers changed from: private */
    public int cameraRotation = 0;
    private boolean muted = false;

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public Context getContext() {
        return this._context;
    }

    public LayoutScale getViewScale() {
        return this._scale;
    }

    public void setViewScale(LayoutScale layoutScale) {
        this._scale = layoutScale;
    }

    /* access modifiers changed from: protected */
    public TextureView getCameraView() {
        return this._cameraView;
    }

    public View getView() {
        return this._cameraViewContainer;
    }

    public void setMuted(final boolean z) {
        if (this.muted != z) {
            this.muted = z;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (z) {
                        CameraPreview.this._cameraViewContainer.setAlpha(0.0f);
                    } else {
                        CameraPreview.this._cameraViewContainer.setAlpha(1.0f);
                    }
                }
            });
        }
    }

    public boolean getMuted() {
        return this.muted;
    }

    public CameraPreview(Context context, LayoutScale layoutScale) {
        this._scale = layoutScale;
        if (context != null) {
            this._context = context.getApplicationContext();
            Utility.dispatchToMainThread(new IAction0(this) {
                final /* synthetic */ View.OnLayoutChangeListener val$olcl;

                {
                    this.val$olcl = r2;
                }

                public void invoke() {
                    TextureView unused = CameraPreview.this._cameraView = new TextureView(CameraPreview.this._context);
                    ViewGroup unused2 = CameraPreview.this._cameraViewContainer = new FrameLayout(CameraPreview.this._context);
                    CameraPreview.this._cameraViewContainer.addView(CameraPreview.this._cameraView, new FrameLayout.LayoutParams(0, 0));
                    CameraPreview.this._cameraViewContainer.addOnLayoutChangeListener(this.val$olcl);
                    CameraPreview.this._cameraView.setSurfaceTextureListener(this);
                }
            }, true);
            return;
        }
        throw new RuntimeException("Context cannot be null.");
    }

    private void setSurface() {
        SurfaceTexture surfaceTexture = this._cameraView.getSurfaceTexture();
        if (surfaceTexture != null) {
            startPreview(surfaceTexture);
        } else {
            updateSurfaceSize();
        }
    }

    /* access modifiers changed from: protected */
    public void setCamera(Camera camera) {
        if (camera != null) {
            synchronized (this._cameraLock) {
                this._camera = camera;
                setSurface();
            }
            return;
        }
        stopPreview();
    }

    /* access modifiers changed from: protected */
    public void setCamera(Camera2Source camera2Source) {
        if (camera2Source != null) {
            synchronized (this._cameraLock) {
                this._cameraDevice = camera2Source;
                setSurface();
            }
            return;
        }
        stopPreview();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        startPreview(surfaceTexture);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        stopPreview();
        return true;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
            updateSurfaceSize();
        }
    }

    /* access modifiers changed from: protected */
    public void updateSurfaceSize() {
        Utility.dispatchToMainThread(new IAction0() {
            public void invoke() {
                int i;
                int i2;
                int i3;
                try {
                    ViewGroup.LayoutParams layoutParams = CameraPreview.this._cameraViewContainer.getLayoutParams();
                    int i4 = 0;
                    if (layoutParams != null) {
                        i = layoutParams.width;
                        i2 = layoutParams.height;
                    } else {
                        i2 = 0;
                        i = 0;
                    }
                    if (i < 0 || i2 < 0) {
                        i = CameraPreview.this._cameraViewContainer.getWidth();
                        i2 = CameraPreview.this._cameraViewContainer.getHeight();
                    }
                    if (i < 0 || i2 < 0) {
                        i = CameraPreview.this._cameraViewContainer.getMeasuredWidth();
                        i2 = CameraPreview.this._cameraViewContainer.getMeasuredHeight();
                    }
                    if (CameraPreview.this._camera != null) {
                        Camera.Size previewSize = CameraPreview.this._camera.getParameters().getPreviewSize();
                        int i5 = previewSize.width;
                        i3 = previewSize.height;
                        i4 = i5;
                    } else if (CameraPreview.this._cameraDevice != null) {
                        i4 = CameraPreview.this._cameraDevice.getSelectedSize().getWidth();
                        i3 = CameraPreview.this._cameraDevice.getSelectedSize().getHeight();
                    } else {
                        i3 = 0;
                    }
                    if (i4 > 0 && i3 > 0 && i > 0 && i2 > 0) {
                        if (CameraPreview.this.cameraRotation % 180 != 0) {
                            int i6 = i3;
                            i3 = i4;
                            i4 = i6;
                        }
                        LayoutFrame scaledFrame = LayoutFrame.getScaledFrame(CameraPreview.this._scale, i, i2, i4, i3);
                        int x = scaledFrame.getX();
                        int y = scaledFrame.getY();
                        int width = scaledFrame.getWidth();
                        int height = scaledFrame.getHeight();
                        CameraPreview.this._cameraView.setX((float) x);
                        CameraPreview.this._cameraView.setY((float) y);
                        ViewGroup.LayoutParams layoutParams2 = CameraPreview.this._cameraView.getLayoutParams();
                        layoutParams2.width = width;
                        layoutParams2.height = height;
                        CameraPreview.this._cameraView.setLayoutParams(layoutParams2);
                        if (CameraPreview.this._cameraDevice != null) {
                            CameraPreview.this._cameraDevice.transformImage(width, height);
                        }
                    }
                } catch (Exception e) {
                    Log.error("Could not update camera preview surface size.", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setCameraRotation(int i) {
        this.cameraRotation = i;
    }

    /* access modifiers changed from: protected */
    public void startPreview(SurfaceTexture surfaceTexture) {
        synchronized (this._cameraLock) {
            Camera camera = this._camera;
            if (camera != null) {
                try {
                    camera.setPreviewTexture(surfaceTexture);
                    this._camera.startPreview();
                    updateSurfaceSize();
                } catch (Exception e) {
                    Log.error("Could not start camera preview.", e);
                }
            } else {
                Camera2Source camera2Source = this._cameraDevice;
                if (camera2Source != null) {
                    camera2Source.setPreviewTexture(surfaceTexture);
                    this._cameraDevice.startCaptureSession();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void stopPreview() {
        synchronized (this._cameraLock) {
            Camera camera = this._camera;
            if (camera != null) {
                try {
                    camera.stopPreview();
                    this._camera = null;
                } catch (Exception e) {
                    Log.error("Could not stop camera preview.", e);
                }
            } else {
                Camera2Source camera2Source = this._cameraDevice;
                if (camera2Source != null) {
                    try {
                        camera2Source.doStop();
                        this._cameraDevice = null;
                    } catch (Exception e2) {
                        Log.error("Could not stop camera preview.", e2);
                    }
                }
            }
        }
    }
}
