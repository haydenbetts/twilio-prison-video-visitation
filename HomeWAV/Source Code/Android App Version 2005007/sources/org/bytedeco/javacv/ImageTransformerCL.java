package org.bytedeco.javacv;

import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLImage2d;
import com.jogamp.opencl.CLMemory;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacv.ImageTransformer;

public interface ImageTransformerCL extends ImageTransformer {
    JavaCVCL getContext();

    void transform(CLImage2d cLImage2d, CLImage2d cLImage2d2, CLImage2d cLImage2d3, CLImage2d cLImage2d4, CLImage2d cLImage2d5, CLImage2d cLImage2d6, ImageTransformer.Parameters[] parametersArr, boolean[] zArr, InputData inputData, OutputData outputData);

    public static class InputData {
        boolean autoWrite;
        CLBuffer<ByteBuffer> buffer;
        public double outlierThreshold;
        public int pyramidLevel;
        public int roiHeight;
        public int roiWidth;
        public int roiX;
        public int roiY;
        public double zeroThreshold;

        public InputData() {
            this(true);
        }

        public InputData(boolean z) {
            this.pyramidLevel = 0;
            this.roiX = 0;
            this.roiY = 0;
            this.roiWidth = 0;
            this.roiHeight = 0;
            this.zeroThreshold = 0.0d;
            this.outlierThreshold = 0.0d;
            this.buffer = null;
            this.autoWrite = true;
            this.autoWrite = z;
        }

        /* access modifiers changed from: package-private */
        public CLBuffer<ByteBuffer> getBuffer(JavaCVCL javaCVCL) {
            CLBuffer<ByteBuffer> cLBuffer = this.buffer;
            if (cLBuffer == null || cLBuffer.getCLSize() < ((long) 16)) {
                CLBuffer<ByteBuffer> cLBuffer2 = this.buffer;
                if (cLBuffer2 != null) {
                    cLBuffer2.release();
                }
                this.buffer = javaCVCL.getCLContext().createByteBuffer(16, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY});
            }
            return this.buffer;
        }

        public CLBuffer<ByteBuffer> writeBuffer(JavaCVCL javaCVCL) {
            getBuffer(javaCVCL);
            ((ByteBuffer) ((ByteBuffer) this.buffer.getBuffer()).rewind()).putInt(this.roiY).putInt(this.roiHeight).putFloat((float) this.zeroThreshold).putFloat((float) this.outlierThreshold).rewind();
            javaCVCL.writeBuffer(this.buffer, false);
            return this.buffer;
        }
    }

    public static class OutputData {
        boolean autoRead;
        CLBuffer<ByteBuffer> buffer;
        public int dstCount;
        public int dstCountOutlier;
        public int dstCountZero;
        public FloatBuffer dstDstDot;
        public FloatBuffer srcDstDot;

        public OutputData() {
            this(true);
        }

        public OutputData(boolean z) {
            this.dstCount = 0;
            this.dstCountZero = 0;
            this.dstCountOutlier = 0;
            this.srcDstDot = null;
            this.dstDstDot = null;
            this.buffer = null;
            this.autoRead = true;
            this.autoRead = z;
        }

        /* access modifiers changed from: package-private */
        public CLBuffer<ByteBuffer> getBuffer(JavaCVCL javaCVCL, int i, int i2) {
            int i3 = i + 4;
            int i4 = ((i * i) + i3) * 4;
            CLBuffer<ByteBuffer> cLBuffer = this.buffer;
            if (cLBuffer == null || cLBuffer.getCLSize() < ((long) (i4 * i2))) {
                CLBuffer<ByteBuffer> cLBuffer2 = this.buffer;
                if (cLBuffer2 != null) {
                    cLBuffer2.release();
                }
                CLBuffer<ByteBuffer> createByteBuffer = javaCVCL.getCLContext().createByteBuffer(i4 * i2, new CLMemory.Mem[0]);
                this.buffer = createByteBuffer;
                ByteBuffer byteBuffer = (ByteBuffer) createByteBuffer.getBuffer();
                byteBuffer.position(16);
                this.srcDstDot = byteBuffer.asFloatBuffer();
                byteBuffer.position(i3 * 4);
                this.dstDstDot = byteBuffer.asFloatBuffer();
                byteBuffer.rewind();
            }
            return this.buffer;
        }

        public CLBuffer<ByteBuffer> readBuffer(JavaCVCL javaCVCL) {
            javaCVCL.readBuffer(this.buffer, true);
            ByteBuffer byteBuffer = (ByteBuffer) this.buffer.getBuffer();
            this.dstCount = byteBuffer.getInt(4);
            this.dstCountZero = byteBuffer.getInt(8);
            this.dstCountOutlier = byteBuffer.getInt(12);
            return this.buffer;
        }
    }
}
