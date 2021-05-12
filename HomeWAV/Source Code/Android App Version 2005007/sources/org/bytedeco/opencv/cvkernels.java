package org.bytedeco.opencv;

import java.nio.DoubleBuffer;
import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class}, value = {@Platform(compiler = {"fastfpu"}, define = {"MAX_SIZE 16", "CV_INLINE static inline"}, include = {"cvkernels.h"})})
public class cvkernels {
    public static native void multiWarpColorTransform32F(KernelData kernelData, int i, CvRect cvRect, CvScalar cvScalar);

    public static native void multiWarpColorTransform8U(KernelData kernelData, int i, CvRect cvRect, CvScalar cvScalar);

    static {
        Loader.load();
    }

    public static class KernelData extends Pointer {
        private DoubleBuffer[] dstDstDotBuffers = new DoubleBuffer[1];

        private native void allocate();

        private native void allocateArray(long j);

        @ByRef
        @Name({"operator="})
        private native KernelData put(@ByRef KernelData kernelData);

        @MemberSetter
        @Name({"dstDstDot"})
        private native KernelData setDstDstDot(DoubleBuffer doubleBuffer);

        public native KernelData H1(CvMat cvMat);

        public native CvMat H1();

        public native KernelData H2(CvMat cvMat);

        public native CvMat H2();

        public native KernelData X(CvMat cvMat);

        public native CvMat X();

        public native int dstCount();

        public native KernelData dstCount(int i);

        public native int dstCountOutlier();

        public native KernelData dstCountOutlier(int i);

        public native int dstCountZero();

        public native KernelData dstCountZero(int i);

        public native KernelData dstImg(IplImage iplImage);

        public native IplImage dstImg();

        public native KernelData mask(IplImage iplImage);

        public native IplImage mask();

        public native double outlierThreshold();

        public native KernelData outlierThreshold(double d);

        public native KernelData srcDotImg(IplImage iplImage);

        public native IplImage srcDotImg();

        public native double srcDstDot();

        public native KernelData srcDstDot(double d);

        public native KernelData srcImg(IplImage iplImage);

        public native IplImage srcImg();

        public native KernelData srcImg2(IplImage iplImage);

        public native IplImage srcImg2();

        public native KernelData subImg(IplImage iplImage);

        public native IplImage subImg();

        public native KernelData transImg(IplImage iplImage);

        public native IplImage transImg();

        public native double zeroThreshold();

        public native KernelData zeroThreshold(double d);

        static {
            Loader.load();
        }

        public KernelData() {
            allocate();
        }

        public KernelData(long j) {
            allocateArray(j);
        }

        public KernelData(Pointer pointer) {
            super(pointer);
        }

        public KernelData position(long j) {
            return (KernelData) super.position(j);
        }

        public DoubleBuffer dstDstDot() {
            return this.dstDstDotBuffers[(int) this.position];
        }

        public KernelData dstDstDot(DoubleBuffer doubleBuffer) {
            if (((long) this.dstDstDotBuffers.length) < this.capacity) {
                this.dstDstDotBuffers = (DoubleBuffer[]) Arrays.copyOf(this.dstDstDotBuffers, (int) this.capacity);
            }
            this.dstDstDotBuffers[(int) this.position] = doubleBuffer;
            return setDstDstDot(doubleBuffer);
        }
    }
}
