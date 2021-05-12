package tvi.webrtc;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import tvi.webrtc.VideoFrame;

@JNINamespace("webrtc::jni")
public class VideoRenderer {
    long nativeVideoRenderer;

    public interface Callbacks {
        void renderFrame(I420Frame i420Frame);
    }

    static native void nativeCopyPlane(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4);

    private static native long nativeCreateVideoRenderer(Callbacks callbacks);

    private static native void nativeFreeWrappedVideoRenderer(long j);

    private static native void nativeReleaseFrame(long j);

    public static class I420Frame {
        @Nullable
        private final VideoFrame.Buffer backingBuffer;
        public final int height;
        /* access modifiers changed from: private */
        public long nativeFramePointer;
        public int rotationDegree;
        @Nullable
        public final float[] samplingMatrix;
        public int textureId;
        public final int width;
        public final boolean yuvFrame;
        @Nullable
        public ByteBuffer[] yuvPlanes;
        @Nullable
        public final int[] yuvStrides;

        public I420Frame(int i, int i2, int i3, int[] iArr, ByteBuffer[] byteBufferArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = iArr;
            this.yuvPlanes = byteBufferArr;
            this.yuvFrame = true;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            this.backingBuffer = null;
            if (i3 % 90 == 0) {
                this.samplingMatrix = RendererCommon.verticalFlipMatrix();
                return;
            }
            throw new IllegalArgumentException("Rotation degree not multiple of 90: " + i3);
        }

        public I420Frame(int i, int i2, int i3, int i4, float[] fArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = null;
            this.yuvPlanes = null;
            this.samplingMatrix = fArr;
            this.textureId = i4;
            this.yuvFrame = false;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            this.backingBuffer = null;
            if (i3 % 90 != 0) {
                throw new IllegalArgumentException("Rotation degree not multiple of 90: " + i3);
            }
        }

        public I420Frame(int i, VideoFrame.Buffer buffer, long j) {
            this.width = buffer.getWidth();
            this.height = buffer.getHeight();
            this.rotationDegree = i;
            if (i % 90 == 0) {
                if (buffer instanceof VideoFrame.TextureBuffer) {
                    VideoFrame.TextureBuffer textureBuffer = (VideoFrame.TextureBuffer) buffer;
                    if (textureBuffer.getType() == VideoFrame.TextureBuffer.Type.OES) {
                        this.yuvFrame = false;
                        this.textureId = textureBuffer.getTextureId();
                        this.samplingMatrix = RendererCommon.convertMatrixFromAndroidGraphicsMatrix(textureBuffer.getTransformMatrix());
                        this.yuvStrides = null;
                        this.yuvPlanes = null;
                        this.nativeFramePointer = j;
                        this.backingBuffer = buffer;
                        return;
                    }
                }
                if (buffer instanceof VideoFrame.I420Buffer) {
                    VideoFrame.I420Buffer i420Buffer = (VideoFrame.I420Buffer) buffer;
                    this.yuvFrame = true;
                    this.yuvStrides = new int[]{i420Buffer.getStrideY(), i420Buffer.getStrideU(), i420Buffer.getStrideV()};
                    this.yuvPlanes = new ByteBuffer[]{i420Buffer.getDataY(), i420Buffer.getDataU(), i420Buffer.getDataV()};
                    this.samplingMatrix = RendererCommon.verticalFlipMatrix();
                    this.textureId = 0;
                } else {
                    this.yuvFrame = false;
                    this.textureId = 0;
                    this.samplingMatrix = null;
                    this.yuvStrides = null;
                    this.yuvPlanes = null;
                }
                this.nativeFramePointer = j;
                this.backingBuffer = buffer;
                return;
            }
            throw new IllegalArgumentException("Rotation degree not multiple of 90: " + i);
        }

        public int rotatedWidth() {
            return this.rotationDegree % 180 == 0 ? this.width : this.height;
        }

        public int rotatedHeight() {
            return this.rotationDegree % 180 == 0 ? this.height : this.width;
        }

        public String toString() {
            String str;
            if (this.yuvFrame) {
                str = "Y: " + this.yuvStrides[0] + ", U: " + this.yuvStrides[1] + ", V: " + this.yuvStrides[2];
            } else {
                str = "Texture: " + this.textureId;
            }
            return this.width + "x" + this.height + ", " + str;
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [tvi.webrtc.JavaI420Buffer] */
        /* JADX WARNING: type inference failed for: r0v7, types: [tvi.webrtc.VideoFrame$Buffer] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public tvi.webrtc.VideoFrame toVideoFrame() {
            /*
                r11 = this;
                tvi.webrtc.VideoFrame$Buffer r0 = r11.backingBuffer
                if (r0 == 0) goto L_0x000d
                r0.retain()
                tvi.webrtc.VideoRenderer.renderFrameDone(r11)
                tvi.webrtc.VideoFrame$Buffer r0 = r11.backingBuffer
                goto L_0x0053
            L_0x000d:
                boolean r0 = r11.yuvFrame
                if (r0 == 0) goto L_0x0039
                int r1 = r11.width
                int r2 = r11.height
                java.nio.ByteBuffer[] r0 = r11.yuvPlanes
                r3 = 0
                r4 = r0[r3]
                int[] r5 = r11.yuvStrides
                r6 = r5[r3]
                r3 = 1
                r7 = r0[r3]
                r8 = r5[r3]
                r3 = 2
                r0 = r0[r3]
                r9 = r5[r3]
                tvi.webrtc.-$$Lambda$VideoRenderer$I420Frame$cMgln2li9T31WW4Bj0nuB2U664U r10 = new tvi.webrtc.-$$Lambda$VideoRenderer$I420Frame$cMgln2li9T31WW4Bj0nuB2U664U
                r10.<init>()
                r3 = r4
                r4 = r6
                r5 = r7
                r6 = r8
                r7 = r0
                r8 = r9
                r9 = r10
                tvi.webrtc.JavaI420Buffer r0 = tvi.webrtc.JavaI420Buffer.wrap(r1, r2, r3, r4, r5, r6, r7, r8, r9)
                goto L_0x0053
            L_0x0039:
                tvi.webrtc.TextureBufferImpl r0 = new tvi.webrtc.TextureBufferImpl
                int r2 = r11.width
                int r3 = r11.height
                tvi.webrtc.VideoFrame$TextureBuffer$Type r4 = tvi.webrtc.VideoFrame.TextureBuffer.Type.OES
                int r5 = r11.textureId
                float[] r1 = r11.samplingMatrix
                android.graphics.Matrix r6 = tvi.webrtc.RendererCommon.convertMatrixToAndroidGraphicsMatrix(r1)
                r7 = 0
                tvi.webrtc.-$$Lambda$VideoRenderer$I420Frame$3vRwBM307rG1L5SYN70eWyr5HbE r8 = new tvi.webrtc.-$$Lambda$VideoRenderer$I420Frame$3vRwBM307rG1L5SYN70eWyr5HbE
                r8.<init>()
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            L_0x0053:
                tvi.webrtc.VideoFrame r1 = new tvi.webrtc.VideoFrame
                int r2 = r11.rotationDegree
                r3 = 0
                r1.<init>(r0, r2, r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.VideoRenderer.I420Frame.toVideoFrame():tvi.webrtc.VideoFrame");
        }

        public /* synthetic */ void lambda$toVideoFrame$0$VideoRenderer$I420Frame() {
            VideoRenderer.renderFrameDone(this);
        }

        public /* synthetic */ void lambda$toVideoFrame$1$VideoRenderer$I420Frame() {
            VideoRenderer.renderFrameDone(this);
        }

        static I420Frame createI420Frame(int i, int i2, int i3, int i4, ByteBuffer byteBuffer, int i5, ByteBuffer byteBuffer2, int i6, ByteBuffer byteBuffer3, long j) {
            return new I420Frame(i, i2, i3, new int[]{i4, i5, i6}, new ByteBuffer[]{byteBuffer, byteBuffer2, byteBuffer3}, j);
        }

        static I420Frame createTextureFrame(int i, int i2, int i3, int i4, float[] fArr, long j) {
            return new I420Frame(i, i2, i3, i4, fArr, j);
        }
    }

    public static void renderFrameDone(I420Frame i420Frame) {
        i420Frame.yuvPlanes = null;
        i420Frame.textureId = 0;
        if (i420Frame.nativeFramePointer != 0) {
            nativeReleaseFrame(i420Frame.nativeFramePointer);
            long unused = i420Frame.nativeFramePointer = 0;
        }
    }

    public VideoRenderer(Callbacks callbacks) {
        this.nativeVideoRenderer = nativeCreateVideoRenderer(callbacks);
    }

    public void dispose() {
        long j = this.nativeVideoRenderer;
        if (j != 0) {
            nativeFreeWrappedVideoRenderer(j);
            this.nativeVideoRenderer = 0;
        }
    }
}
