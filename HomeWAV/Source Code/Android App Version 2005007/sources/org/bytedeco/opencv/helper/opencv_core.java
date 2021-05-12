package org.bytedeco.opencv.helper;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvArrArray;
import org.bytedeco.opencv.opencv_core.CvMatND;
import org.bytedeco.opencv.opencv_core.CvNArrayIterator;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.Scalar;

public class opencv_core extends org.bytedeco.opencv.presets.opencv_core {
    public static CvScalar CV_RGB(double d, double d2, double d3) {
        return org.bytedeco.opencv.global.opencv_core.cvScalar(d3, d2, d, 0.0d);
    }

    public static int cvInitNArrayIterator(int i, CvArr[] cvArrArr, CvArr cvArr, CvMatND cvMatND, CvNArrayIterator cvNArrayIterator, int i2) {
        return org.bytedeco.opencv.global.opencv_core.cvInitNArrayIterator(i, (PointerPointer) new CvArrArray(cvArrArr), cvArr, cvMatND, cvNArrayIterator, i2);
    }

    public static void cvMixChannels(CvArr[] cvArrArr, int i, CvArr[] cvArrArr2, int i2, int[] iArr, int i3) {
        org.bytedeco.opencv.global.opencv_core.cvMixChannels((PointerPointer) new CvArrArray(cvArrArr), i, (PointerPointer) new CvArrArray(cvArrArr2), i2, new IntPointer(iArr), i3);
    }

    public static void cvCalcCovarMatrix(CvArr[] cvArrArr, int i, CvArr cvArr, CvArr cvArr2, int i2) {
        org.bytedeco.opencv.global.opencv_core.cvCalcCovarMatrix((PointerPointer) new CvArrArray(cvArrArr), i, cvArr, cvArr2, i2);
    }

    public static double cvNorm(CvArr cvArr, CvArr cvArr2) {
        return org.bytedeco.opencv.global.opencv_core.cvNorm(cvArr, cvArr2, 4, (CvArr) null);
    }

    public static Scalar RGB(double d, double d2, double d3) {
        return new Scalar(d3, d2, d, 0.0d);
    }
}
