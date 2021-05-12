package org.bytedeco.opencv.helper;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvArrArray;
import org.bytedeco.opencv.opencv_core.CvHistogram;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.CvSize;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplImageArray;
import org.bytedeco.opencv.opencv_imgproc.CvContourScanner;

public class opencv_imgproc extends org.bytedeco.opencv.presets.opencv_imgproc {
    public static int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, CvSeq cvSeq, int i, int i2, int i3) {
        return org.bytedeco.opencv.global.opencv_imgproc.cvFindContours(cvArr, cvMemStorage, cvSeq, i, i2, i3, CvPoint.ZERO);
    }

    public static CvContourScanner cvStartFindContours(CvArr cvArr, CvMemStorage cvMemStorage, int i, int i2, int i3) {
        return org.bytedeco.opencv.global.opencv_imgproc.cvStartFindContours(cvArr, cvMemStorage, i, i2, i3, CvPoint.ZERO);
    }

    public static CvHistogram cvCreateHist(int i, int[] iArr, int i2, float[][] fArr, int i3) {
        return org.bytedeco.opencv.global.opencv_imgproc.cvCreateHist(i, new IntPointer(iArr), i2, fArr == null ? null : new PointerPointer(fArr), i3);
    }

    public static void cvSetHistBinRanges(CvHistogram cvHistogram, float[][] fArr, int i) {
        org.bytedeco.opencv.global.opencv_imgproc.cvSetHistBinRanges(cvHistogram, fArr == null ? null : new PointerPointer(fArr), i);
    }

    public static CvHistogram cvMakeHistHeaderForArray(int i, int[] iArr, CvHistogram cvHistogram, float[] fArr, float[][] fArr2, int i2) {
        return org.bytedeco.opencv.global.opencv_imgproc.cvMakeHistHeaderForArray(i, new IntPointer(iArr), cvHistogram, new FloatPointer(fArr), fArr2 == null ? null : new PointerPointer(fArr2), i2);
    }

    public static CvHistogram cvMakeHistHeaderForArray(int i, int[] iArr, CvHistogram cvHistogram, FloatPointer floatPointer, float[][] fArr, int i2) {
        return org.bytedeco.opencv.global.opencv_imgproc.cvMakeHistHeaderForArray(i, new IntPointer(iArr), cvHistogram, new FloatPointer((Pointer) floatPointer), fArr == null ? null : new PointerPointer(fArr), i2);
    }

    public static void cvCalcArrHist(CvArr[] cvArrArr, CvHistogram cvHistogram, int i, CvArr cvArr) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrHist((PointerPointer) new CvArrArray(cvArrArr), cvHistogram, i, cvArr);
    }

    public static void cvCalcHist(IplImage[] iplImageArr, CvHistogram cvHistogram, int i, CvArr cvArr) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcHist(new IplImageArray(iplImageArr), cvHistogram, i, cvArr);
    }

    public static void cvCalcHist(IplImageArray iplImageArray, CvHistogram cvHistogram, int i, CvArr cvArr) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrHist((PointerPointer) iplImageArray, cvHistogram, i, cvArr);
    }

    public static void cvCalcArrBackProject(CvArr[] cvArrArr, CvArr cvArr, CvHistogram cvHistogram) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrBackProject((PointerPointer) new CvArrArray(cvArrArr), cvArr, cvHistogram);
    }

    public static void cvCalcBackProject(IplImage[] iplImageArr, CvArr cvArr, CvHistogram cvHistogram) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcBackProject(new IplImageArray(iplImageArr), cvArr, cvHistogram);
    }

    public static void cvCalcBackProject(IplImageArray iplImageArray, CvArr cvArr, CvHistogram cvHistogram) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrBackProject((PointerPointer) iplImageArray, cvArr, cvHistogram);
    }

    public static void cvCalcArrBackProjectPatch(CvArr[] cvArrArr, CvArr cvArr, CvSize cvSize, CvHistogram cvHistogram, int i, double d) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrBackProjectPatch((PointerPointer) new CvArrArray(cvArrArr), cvArr, cvSize, cvHistogram, i, d);
    }

    public static void cvCalcBackProjectPatch(IplImage[] iplImageArr, CvArr cvArr, CvSize cvSize, CvHistogram cvHistogram, int i, double d) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcBackProjectPatch(new IplImageArray(iplImageArr), cvArr, cvSize, cvHistogram, i, d);
    }

    public static void cvCalcBackProjectPatch(IplImageArray iplImageArray, CvArr cvArr, CvSize cvSize, CvHistogram cvHistogram, int i, double d) {
        org.bytedeco.opencv.global.opencv_imgproc.cvCalcArrBackProjectPatch((PointerPointer) iplImageArray, cvArr, cvSize, cvHistogram, i, d);
    }

    public static void cvFillPoly(CvArr cvArr, CvPoint[] cvPointArr, int[] iArr, int i, CvScalar cvScalar, int i2, int i3) {
        org.bytedeco.opencv.global.opencv_imgproc.cvFillPoly(cvArr, new PointerPointer((P[]) cvPointArr), new IntPointer(iArr), i, cvScalar, i2, i3);
    }

    public static void cvPolyLine(CvArr cvArr, CvPoint[] cvPointArr, int[] iArr, int i, int i2, CvScalar cvScalar, int i3, int i4, int i5) {
        CvPoint[] cvPointArr2 = cvPointArr;
        int[] iArr2 = iArr;
        org.bytedeco.opencv.global.opencv_imgproc.cvPolyLine(cvArr, new PointerPointer((P[]) cvPointArr), new IntPointer(iArr), i, i2, cvScalar, i3, i4, i5);
    }

    public static void cvDrawPolyLine(CvArr cvArr, CvPoint[] cvPointArr, int[] iArr, int i, int i2, CvScalar cvScalar, int i3, int i4, int i5) {
        cvPolyLine(cvArr, cvPointArr, iArr, i, i2, cvScalar, i3, i4, i5);
    }

    public static void cvDrawContours(CvArr cvArr, CvSeq cvSeq, CvScalar cvScalar, CvScalar cvScalar2, int i, int i2, int i3) {
        org.bytedeco.opencv.global.opencv_imgproc.cvDrawContours(cvArr, cvSeq, cvScalar, cvScalar2, i, i2, i3, CvPoint.ZERO);
    }
}
