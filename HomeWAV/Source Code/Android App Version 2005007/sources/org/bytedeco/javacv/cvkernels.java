package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class cvkernels extends org.bytedeco.opencv.cvkernels {
    private static ThreadLocal<ParallelData[]> parallelData = new ThreadLocal<ParallelData[]>() {
        /* access modifiers changed from: protected */
        public ParallelData[] initialValue() {
            int numThreads = Parallel.getNumThreads();
            ParallelData[] parallelDataArr = new ParallelData[numThreads];
            for (int i = 0; i < numThreads; i++) {
                parallelDataArr[i] = new ParallelData();
            }
            return parallelDataArr;
        }
    };

    private static class ParallelData {
        cvkernels.KernelData data;
        CvRect roi;

        private ParallelData() {
            this.data = null;
            this.roi = new CvRect();
        }
    }

    public static void multiWarpColorTransform(cvkernels.KernelData kernelData, CvRect cvRect, CvScalar cvScalar) {
        int i;
        int i2;
        int i3;
        final int i4;
        cvkernels.KernelData kernelData2 = kernelData;
        int capacity = (int) kernelData.capacity();
        ParallelData[] parallelDataArr = parallelData.get();
        for (int i5 = 0; i5 < parallelDataArr.length; i5++) {
            if (parallelDataArr[i5].data == null || parallelDataArr[i5].data.capacity() < ((long) capacity)) {
                parallelDataArr[i5].data = new cvkernels.KernelData((long) capacity);
                for (int i6 = 0; i6 < capacity; i6++) {
                    long j = (long) i6;
                    cvkernels.KernelData position = parallelDataArr[i5].data.position(j);
                    kernelData2.position(j);
                    if (kernelData.dstDstDot() != null) {
                        position.dstDstDot(ByteBuffer.allocateDirect(kernelData.dstDstDot().capacity() * 8).order(ByteOrder.nativeOrder()).asDoubleBuffer());
                    }
                }
            }
            for (int i7 = 0; i7 < capacity; i7++) {
                long j2 = (long) i7;
                cvkernels.KernelData position2 = parallelDataArr[i5].data.position(j2);
                position2.put(kernelData2.position(j2));
                position2.dstDstDot(position2.dstDstDot());
            }
        }
        IplImage srcImg = kernelData2.position(0).srcImg();
        final int depth = srcImg.depth();
        if (cvRect != null) {
            int x = cvRect.x();
            int y = cvRect.y();
            int width = cvRect.width();
            i3 = cvRect.height();
            i = y;
            i2 = width;
            i4 = x;
        } else {
            int width2 = srcImg.width();
            i3 = srcImg.height();
            i2 = width2;
            i4 = 0;
            i = 0;
        }
        final ParallelData[] parallelDataArr2 = parallelDataArr;
        final int i8 = i2;
        final int i9 = capacity;
        final CvScalar cvScalar2 = cvScalar;
        Parallel.loop(i, i + i3, parallelDataArr.length, new Parallel.Looper() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<cvkernels> cls = cvkernels.class;
            }

            public void loop(int i, int i2, int i3) {
                CvRect height = parallelDataArr2[i3].roi.x(i4).y(i).width(i8).height(i2 - i);
                int i4 = depth;
                if (i4 == 32) {
                    org.bytedeco.opencv.cvkernels.multiWarpColorTransform32F(parallelDataArr2[i3].data.position(0), i9, height, cvScalar2);
                } else if (i4 == 8) {
                    org.bytedeco.opencv.cvkernels.multiWarpColorTransform8U(parallelDataArr2[i3].data.position(0), i9, height, cvScalar2);
                }
            }
        });
        for (int i10 = 0; i10 < capacity; i10++) {
            double d = 0.0d;
            double[] dArr = null;
            if (kernelData.dstDstDot() != null) {
                dArr = new double[kernelData.dstDstDot().capacity()];
            }
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            for (ParallelData parallelData2 : parallelDataArr) {
                cvkernels.KernelData position3 = parallelData2.data.position((long) i10);
                i11 += position3.dstCount();
                i12 += position3.dstCountZero();
                i13 += position3.dstCountOutlier();
                d += position3.srcDstDot();
                if (!(dArr == null || position3.dstDstDot() == null)) {
                    for (int i14 = 0; i14 < dArr.length; i14++) {
                        dArr[i14] = dArr[i14] + position3.dstDstDot().get(i14);
                    }
                }
            }
            kernelData2.position((long) i10);
            kernelData2.dstCount(i11);
            kernelData2.dstCountZero(i12);
            kernelData2.dstCountOutlier(i13);
            kernelData2.srcDstDot(d);
            if (dArr != null && kernelData.dstDstDot() != null) {
                kernelData.dstDstDot().position(0);
                kernelData.dstDstDot().put(dArr);
            }
        }
    }
}
