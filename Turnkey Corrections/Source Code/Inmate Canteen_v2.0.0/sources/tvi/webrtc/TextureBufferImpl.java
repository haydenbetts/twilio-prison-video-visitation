package tvi.webrtc;

import android.graphics.Matrix;
import tvi.webrtc.VideoFrame;

class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;
    private final int id;
    private int refCount;
    private final Object refCountLock = new Object();
    private final Runnable releaseCallback;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int width;

    public TextureBufferImpl(int i, int i2, VideoFrame.TextureBuffer.Type type2, int i3, Matrix matrix, SurfaceTextureHelper surfaceTextureHelper2, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.type = type2;
        this.id = i3;
        this.transformMatrix = matrix;
        this.surfaceTextureHelper = surfaceTextureHelper2;
        this.releaseCallback = runnable;
        this.refCount = 1;
    }

    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
    }

    public int getTextureId() {
        return this.id;
    }

    public Matrix getTransformMatrix() {
        return this.transformMatrix;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public VideoFrame.I420Buffer toI420() {
        return this.surfaceTextureHelper.textureToYuv(this);
    }

    public void retain() {
        synchronized (this.refCountLock) {
            this.refCount++;
        }
    }

    public void release() {
        synchronized (this.refCountLock) {
            int i = this.refCount - 1;
            this.refCount = i;
            if (i == 0 && this.releaseCallback != null) {
                this.releaseCallback.run();
            }
        }
    }

    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        retain();
        Matrix matrix = new Matrix(this.transformMatrix);
        matrix.postScale(((float) i3) / ((float) this.width), ((float) i4) / ((float) this.height));
        matrix.postTranslate(((float) i) / ((float) this.width), ((float) i2) / ((float) this.height));
        return new TextureBufferImpl(i5, i6, this.type, this.id, matrix, this.surfaceTextureHelper, new Runnable() {
            public void run() {
                TextureBufferImpl.this.release();
            }
        });
    }
}
