package tvi.webrtc;

import android.graphics.Matrix;
import java.nio.ByteBuffer;
import java.util.Objects;
import tvi.webrtc.VideoFrame;

@JNINamespace("webrtc::jni")
public class VideoFrame {
    private final Buffer buffer;
    private final int rotation;
    private final long timestampNs;

    public interface Buffer {
        @CalledByNative("Buffer")
        Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6);

        @CalledByNative("Buffer")
        int getHeight();

        @CalledByNative("Buffer")
        int getWidth();

        @CalledByNative("Buffer")
        void release();

        @CalledByNative("Buffer")
        void retain();

        @CalledByNative("Buffer")
        I420Buffer toI420();
    }

    public interface I420Buffer extends Buffer {
        @CalledByNative("I420Buffer")
        ByteBuffer getDataU();

        @CalledByNative("I420Buffer")
        ByteBuffer getDataV();

        @CalledByNative("I420Buffer")
        ByteBuffer getDataY();

        @CalledByNative("I420Buffer")
        int getStrideU();

        @CalledByNative("I420Buffer")
        int getStrideV();

        @CalledByNative("I420Buffer")
        int getStrideY();
    }

    private static native void nativeCropAndScaleI420(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, ByteBuffer byteBuffer3, int i3, int i4, int i5, int i6, int i7, ByteBuffer byteBuffer4, int i8, ByteBuffer byteBuffer5, int i9, ByteBuffer byteBuffer6, int i10, int i11, int i12);

    public interface TextureBuffer extends Buffer {
        int getTextureId();

        Matrix getTransformMatrix();

        Type getType();

        public enum Type {
            OES(36197),
            RGB(3553);
            
            private final int glTarget;

            private Type(int i) {
                this.glTarget = i;
            }

            public int getGlTarget() {
                return this.glTarget;
            }
        }
    }

    @CalledByNative
    public VideoFrame(Buffer buffer2, int i, long j) {
        if (buffer2 == null) {
            throw new IllegalArgumentException("buffer not allowed to be null");
        } else if (i % 90 == 0) {
            this.buffer = buffer2;
            this.rotation = i;
            this.timestampNs = j;
        } else {
            throw new IllegalArgumentException("rotation must be a multiple of 90");
        }
    }

    @CalledByNative
    public Buffer getBuffer() {
        return this.buffer;
    }

    @CalledByNative
    public int getRotation() {
        return this.rotation;
    }

    @CalledByNative
    public long getTimestampNs() {
        return this.timestampNs;
    }

    public int getRotatedWidth() {
        if (this.rotation % 180 == 0) {
            return this.buffer.getWidth();
        }
        return this.buffer.getHeight();
    }

    public int getRotatedHeight() {
        if (this.rotation % 180 == 0) {
            return this.buffer.getHeight();
        }
        return this.buffer.getWidth();
    }

    public void retain() {
        this.buffer.retain();
    }

    @CalledByNative
    public void release() {
        this.buffer.release();
    }

    public static Buffer cropAndScaleI420(I420Buffer i420Buffer, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i3 != i5) {
            I420Buffer i420Buffer2 = i420Buffer;
            int i7 = i4;
            int i8 = i6;
        } else if (i4 == i6) {
            ByteBuffer dataY = i420Buffer.getDataY();
            ByteBuffer dataU = i420Buffer.getDataU();
            ByteBuffer dataV = i420Buffer.getDataV();
            dataY.position(i + (i420Buffer.getStrideY() * i2));
            int i9 = i / 2;
            int i10 = i2 / 2;
            dataU.position((i420Buffer.getStrideU() * i10) + i9);
            dataV.position(i9 + (i10 * i420Buffer.getStrideV()));
            i420Buffer.retain();
            int width = i420Buffer.getWidth();
            int height = i420Buffer.getHeight();
            ByteBuffer slice = dataY.slice();
            int strideY = i420Buffer.getStrideY();
            ByteBuffer slice2 = dataU.slice();
            int strideU = i420Buffer.getStrideU();
            ByteBuffer slice3 = dataV.slice();
            int strideV = i420Buffer.getStrideV();
            Objects.requireNonNull(i420Buffer);
            return JavaI420Buffer.wrap(width, height, slice, strideY, slice2, strideU, slice3, strideV, new Runnable() {
                public final void run() {
                    VideoFrame.I420Buffer.this.release();
                }
            });
        } else {
            I420Buffer i420Buffer3 = i420Buffer;
        }
        JavaI420Buffer allocate = JavaI420Buffer.allocate(i5, i6);
        nativeCropAndScaleI420(i420Buffer.getDataY(), i420Buffer.getStrideY(), i420Buffer.getDataU(), i420Buffer.getStrideU(), i420Buffer.getDataV(), i420Buffer.getStrideV(), i, i2, i3, i4, allocate.getDataY(), allocate.getStrideY(), allocate.getDataU(), allocate.getStrideU(), allocate.getDataV(), allocate.getStrideV(), i5, i6);
        return allocate;
    }
}
