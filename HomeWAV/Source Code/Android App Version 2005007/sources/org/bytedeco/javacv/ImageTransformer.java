package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.IplImage;

public interface ImageTransformer {

    public interface Parameters extends Cloneable {
        Parameters clone();

        void compose(Parameters parameters, boolean z, Parameters parameters2, boolean z2);

        double get(int i);

        double[] get();

        double getConstraintError();

        double[] getSubspace();

        boolean preoptimize();

        void reset(boolean z);

        void set(int i, double d);

        void set(Parameters parameters);

        void set(double... dArr);

        void setSubspace(double... dArr);

        int size();
    }

    Parameters createParameters();

    void transform(CvMat cvMat, CvMat cvMat2, Parameters parameters, boolean z);

    void transform(Data[] dataArr, CvRect cvRect, Parameters[] parametersArr, boolean[] zArr);

    public static class Data {
        public int dstCount;
        public int dstCountOutlier;
        public int dstCountZero;
        public DoubleBuffer dstDstDot;
        public IplImage dstImg;
        public IplImage mask;
        public double outlierThreshold;
        public int pyramidLevel;
        public IplImage srcDotImg;
        public double srcDstDot;
        public IplImage srcImg;
        public IplImage subImg;
        public IplImage transImg;
        public double zeroThreshold;

        public Data() {
            this((IplImage) null, (IplImage) null, (IplImage) null, (IplImage) null, 0.0d, 0.0d, 0, (IplImage) null, (IplImage) null, 0);
        }

        public Data(IplImage iplImage, IplImage iplImage2, IplImage iplImage3, IplImage iplImage4, double d, double d2, int i, IplImage iplImage5, IplImage iplImage6, int i2) {
            DoubleBuffer doubleBuffer;
            this.srcImg = iplImage;
            this.subImg = iplImage2;
            this.srcDotImg = iplImage3;
            this.mask = iplImage4;
            this.zeroThreshold = d;
            this.outlierThreshold = d2;
            this.pyramidLevel = i;
            this.transImg = iplImage5;
            this.dstImg = iplImage6;
            if (i2 == 0) {
                doubleBuffer = null;
            } else {
                doubleBuffer = ByteBuffer.allocateDirect(i2 * 8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
            }
            this.dstDstDot = doubleBuffer;
        }
    }
}
