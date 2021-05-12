package org.bytedeco.javacv;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.UShort;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvBox2D;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint2D32f;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvSize2D32f;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_imgproc.CvMoments;

public class JavaCV {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ThreadLocal<CvMat> A3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> A8x8 = CvMat.createThreadLocal(8, 8);
    public static final double DBL_EPSILON = 2.220446049250313E-16d;
    public static final double FLT_EPSILON = 1.1920928955078125E-7d;
    private static ThreadLocal<CvMat> H13x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> H23x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> M3x2 = CvMat.createThreadLocal(3, 2);
    private static ThreadLocal<CvMat> R13x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> R23x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> S2x2 = CvMat.createThreadLocal(2, 2);
    private static ThreadLocal<CvMat> S3x3 = CvMat.createThreadLocal(3, 3);
    public static final double SQRT2 = 1.4142135623730951d;
    private static ThreadLocal<CvMat> U3x2 = CvMat.createThreadLocal(3, 2);
    private static ThreadLocal<CvMat> U3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> V2x2 = CvMat.createThreadLocal(2, 2);
    private static ThreadLocal<CvMat> V3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> b3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> b8x1 = CvMat.createThreadLocal(8, 1);
    private static ThreadLocal<CvMoments> moments = CvMoments.createThreadLocal();
    private static ThreadLocal<CvMat> n13x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> n23x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> n3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> t13x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> t23x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> x8x1 = CvMat.createThreadLocal(8, 1);

    public static double distanceToLine(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d3 - d;
        double d8 = d4 - d2;
        double d9 = (((d5 - d) * d7) + ((d6 - d2) * d8)) / ((d7 * d7) + (d8 * d8));
        double d10 = ((d7 * d9) + d) - d5;
        double d11 = (d2 + (d9 * d8)) - d6;
        return (d10 * d10) + (d11 * d11);
    }

    public static CvBox2D boundedRect(CvMat cvMat, CvBox2D cvBox2D) {
        double d;
        float f;
        CvMat cvMat2 = cvMat;
        CvBox2D cvBox2D2 = cvBox2D;
        int length = cvMat.length();
        CvMoments cvMoments = moments.get();
        opencv_imgproc.cvMoments(cvMat2, cvMoments, 0);
        double m00 = 1.0d / cvMoments.m00();
        double m10 = cvMoments.m10() * m00;
        double m01 = cvMoments.m01() * m00;
        float[] fArr = new float[8];
        CvPoint2D32f center = cvBox2D.center();
        CvSize2D32f size = cvBox2D.size();
        center.put(m10, m01);
        opencv_imgproc.cvBoxPoints(cvBox2D2, fArr);
        float f2 = Float.POSITIVE_INFINITY;
        int i = 0;
        while (i < 4) {
            int i2 = i * 2;
            double d2 = (double) fArr[i2];
            double d3 = (double) fArr[i2 + 1];
            float f3 = f2;
            int i3 = 0;
            while (i3 < length) {
                int i4 = i3 + 1;
                int i5 = i3 * 2;
                double d4 = cvMat2.get(i5);
                double d5 = cvMat2.get(i5 + 1);
                int i6 = (i4 % length) * 2;
                double d6 = cvMat2.get(i6);
                double d7 = cvMat2.get(i6 + 1) - d5;
                double d8 = d2 - m10;
                double d9 = d6 - d4;
                double d10 = d3 - m01;
                double d11 = (d7 * d8) - (d9 * d10);
                double d12 = m01 - d5;
                double d13 = m10 - d4;
                double d14 = (d9 * d12) - (d7 * d13);
                int i7 = length;
                float[] fArr2 = fArr;
                double d15 = d14 / d11;
                double d16 = ((d8 * d12) - (d10 * d13)) / d11;
                if (d16 < 0.0d) {
                    d = d2;
                    f = f3;
                } else if (d16 > 1.0d || d15 < 0.0d) {
                    d = d2;
                    f = f3;
                } else {
                    d = d2;
                    f = f3;
                    if (d15 < ((double) f)) {
                        f = (float) d15;
                    }
                }
                f3 = f;
                cvMat2 = cvMat;
                length = i7;
                i3 = i4;
                d2 = d;
                fArr = fArr2;
            }
            int i8 = length;
            float[] fArr3 = fArr;
            i++;
            f2 = f3;
            cvMat2 = cvMat;
        }
        float f4 = f2;
        size.width(f4 * size.width()).height(f4 * size.height());
        return cvBox2D2;
    }

    public static CvRect boundingRect(double[] dArr, CvRect cvRect, int i, int i2, int i3, int i4) {
        double[] dArr2 = dArr;
        int i5 = i3;
        int i6 = i4;
        double d = dArr2[0];
        int i7 = 1;
        double d2 = dArr2[1];
        double d3 = dArr2[0];
        double d4 = dArr2[1];
        int i8 = 1;
        while (i8 < dArr2.length / 2) {
            int i9 = i8 * 2;
            double d5 = dArr2[i9];
            double d6 = dArr2[i9 + i7];
            double min = Math.min(d, d5);
            d2 = Math.min(d2, d6);
            d3 = Math.max(d3, d5);
            d4 = Math.max(d4, d6);
            i8++;
            d = min;
            i7 = 1;
        }
        double d7 = (double) i;
        double d8 = (double) i5;
        int floor = ((int) Math.floor(Math.max((double) cvRect.x(), d - d7) / d8)) * i5;
        double d9 = d4;
        double d10 = (double) i2;
        double max = Math.max((double) cvRect.y(), d2 - d10);
        double d11 = (double) i6;
        int floor2 = ((int) Math.floor(max / d11)) * i6;
        return cvRect.x(floor).y(floor2).width(Math.max(0, (((int) Math.ceil(Math.min((double) cvRect.width(), d3 + d7) / d8)) * i5) - floor)).height(Math.max(0, (((int) Math.ceil(Math.min((double) cvRect.height(), d9 + d10) / d11)) * i6) - floor2));
    }

    public static CvMat getPerspectiveTransform(double[] dArr, double[] dArr2, CvMat cvMat) {
        CvMat cvMat2 = cvMat;
        CvMat cvMat3 = A8x8.get();
        CvMat cvMat4 = b8x1.get();
        CvMat cvMat5 = x8x1.get();
        for (int i = 0; i < 4; i++) {
            int i2 = i * 8;
            int i3 = i * 2;
            cvMat3.put(i2 + 0, dArr[i3]);
            int i4 = i + 4;
            int i5 = i4 * 8;
            cvMat3.put(i5 + 3, dArr[i3]);
            int i6 = i3 + 1;
            cvMat3.put(i2 + 1, dArr[i6]);
            cvMat3.put(i5 + 4, dArr[i6]);
            cvMat3.put(i2 + 2, 1.0d);
            cvMat3.put(i5 + 5, 1.0d);
            cvMat3.put(i2 + 3, 0.0d);
            cvMat3.put(i2 + 4, 0.0d);
            cvMat3.put(i2 + 5, 0.0d);
            cvMat3.put(i5 + 0, 0.0d);
            cvMat3.put(i5 + 1, 0.0d);
            cvMat3.put(i5 + 2, 0.0d);
            cvMat3.put(i2 + 6, (-dArr[i3]) * dArr2[i3]);
            cvMat3.put(i2 + 7, (-dArr[i6]) * dArr2[i3]);
            cvMat3.put(i5 + 6, (-dArr[i3]) * dArr2[i6]);
            cvMat3.put(i5 + 7, (-dArr[i6]) * dArr2[i6]);
            cvMat4.put(i, dArr2[i3]);
            cvMat4.put(i4, dArr2[i6]);
        }
        opencv_core.cvSolve(cvMat3, cvMat4, cvMat5, 0);
        cvMat2.put(cvMat5.get());
        cvMat2.put(8, 1.0d);
        return cvMat2;
    }

    public static void perspectiveTransform(double[] dArr, double[] dArr2, CvMat cvMat) {
        double[] dArr3 = dArr;
        double[] dArr4 = cvMat.get();
        for (int i = 0; i < dArr3.length; i += 2) {
            double d = dArr3[i];
            int i2 = i + 1;
            double d2 = dArr3[i2];
            double d3 = (dArr4[6] * d) + (dArr4[7] * d2) + dArr4[8];
            if (Math.abs(d3) > 1.1920928955078125E-7d) {
                double d4 = 1.0d / d3;
                dArr2[i] = ((dArr4[0] * d) + (dArr4[1] * d2) + dArr4[2]) * d4;
                dArr2[i2] = ((d * dArr4[3]) + (d2 * dArr4[4]) + dArr4[5]) * d4;
            } else {
                dArr2[i2] = 0.0d;
                dArr2[i] = 0.0d;
            }
        }
    }

    public static CvMat getPlaneParameters(double[] dArr, double[] dArr2, CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5) {
        CvMat cvMat6 = cvMat3;
        CvMat cvMat7 = cvMat4;
        CvMat cvMat8 = cvMat5;
        CvMat cvMat9 = A3x3.get();
        CvMat cvMat10 = b3x1.get();
        double[] dArr3 = new double[6];
        double[] dArr4 = new double[6];
        perspectiveTransform(dArr, dArr3, cvMat);
        opencv_core.cvInvert(cvMat2, cvMat9);
        perspectiveTransform(dArr2, dArr4, cvMat9);
        for (int i = 0; i < 3; i++) {
            int i2 = i * 2;
            cvMat9.put(i, 0, ((cvMat7.get(2) * dArr4[i2]) - cvMat7.get(0)) * dArr3[i2]);
            int i3 = i2 + 1;
            cvMat9.put(i, 1, ((cvMat7.get(2) * dArr4[i2]) - cvMat7.get(0)) * dArr3[i3]);
            cvMat9.put(i, 2, (cvMat7.get(2) * dArr4[i2]) - cvMat7.get(0));
            cvMat10.put(i, ((((cvMat6.get(2, 0) * dArr3[i2]) + (cvMat6.get(2, 1) * dArr3[i3])) + cvMat6.get(2, 2)) * dArr4[i2]) - (((cvMat6.get(0, 0) * dArr3[i2]) + (cvMat6.get(0, 1) * dArr3[i3])) + cvMat6.get(0, 2)));
        }
        opencv_core.cvSolve(cvMat9, cvMat10, cvMat8, 0);
        return cvMat8;
    }

    public static CvMat getPerspectiveTransform(double[] dArr, double[] dArr2, CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5) {
        CvMat cvMat6 = cvMat5;
        CvMat cvMat7 = n3x1.get();
        getPlaneParameters(dArr, dArr2, cvMat, cvMat2, cvMat3, cvMat4, cvMat7);
        opencv_core.cvGEMM(cvMat4, cvMat7, -1.0d, cvMat3, 1.0d, cvMat5, 2);
        CvMat cvMat8 = cvMat2;
        opencv_core.cvMatMul(cvMat2, cvMat6, cvMat6);
        CvMat cvMat9 = cvMat;
        opencv_core.cvMatMul(cvMat6, cvMat, cvMat6);
        return cvMat6;
    }

    public static void perspectiveTransform(double[] dArr, double[] dArr2, CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, boolean z) {
        CvMat cvMat6 = H3x3.get();
        opencv_core.cvGEMM(cvMat4, cvMat5, -1.0d, cvMat3, 1.0d, cvMat6, 2);
        CvMat cvMat7 = cvMat2;
        opencv_core.cvMatMul(cvMat2, cvMat6, cvMat6);
        CvMat cvMat8 = cvMat;
        opencv_core.cvMatMul(cvMat6, cvMat, cvMat6);
        if (z) {
            opencv_core.cvInvert(cvMat6, cvMat6);
        }
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        perspectiveTransform(dArr, dArr2, cvMat6);
    }

    public static void HtoRt(CvMat cvMat, CvMat cvMat2, CvMat cvMat3) {
        CvMat cvMat4 = cvMat;
        CvMat cvMat5 = M3x2.get();
        CvMat cvMat6 = S2x2.get();
        CvMat cvMat7 = U3x2.get();
        CvMat cvMat8 = V2x2.get();
        cvMat5.put(cvMat4.get(0), cvMat4.get(1), cvMat4.get(3), cvMat4.get(4), cvMat4.get(6), cvMat4.get(7));
        opencv_core.cvSVD(cvMat5, cvMat6, cvMat7, cvMat8, 4);
        double d = cvMat6.get(3);
        cvMat3.put(cvMat4.get(2) / d, cvMat4.get(5) / d, cvMat4.get(8) / d);
        opencv_core.cvMatMul(cvMat7, cvMat8, cvMat5);
        cvMat2.put(cvMat5.get(0), cvMat5.get(1), (cvMat5.get(2) * cvMat5.get(5)) - (cvMat5.get(3) * cvMat5.get(4)), cvMat5.get(2), cvMat5.get(3), (cvMat5.get(1) * cvMat5.get(4)) - (cvMat5.get(0) * cvMat5.get(5)), cvMat5.get(4), cvMat5.get(5), (cvMat5.get(0) * cvMat5.get(3)) - (cvMat5.get(1) * cvMat5.get(2)));
    }

    public static double HnToRt(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4) {
        CvMat cvMat5 = cvMat2;
        CvMat cvMat6 = cvMat3;
        CvMat cvMat7 = cvMat4;
        CvMat cvMat8 = S3x3.get();
        CvMat cvMat9 = U3x3.get();
        CvMat cvMat10 = V3x3.get();
        opencv_core.cvSVD(cvMat, cvMat8, cvMat9, cvMat10, 0);
        CvMat cvMat11 = R13x3.get();
        CvMat cvMat12 = R23x3.get();
        CvMat cvMat13 = t13x1.get();
        CvMat cvMat14 = t23x1.get();
        CvMat cvMat15 = H13x3.get();
        CvMat cvMat16 = H23x3.get();
        CvMat cvMat17 = cvMat11;
        homogToRt(cvMat8, cvMat9, cvMat10, cvMat17, cvMat13, n13x1.get(), cvMat12, cvMat14, n23x1.get());
        CvMat cvMat18 = cvMat;
        opencv_core.cvGEMM(cvMat17, cvMat18, 1.0d / cvMat8.get(4), (CvArr) null, 0.0d, cvMat15, 1);
        opencv_core.cvGEMM(cvMat12, cvMat18, 1.0d / cvMat8.get(4), (CvArr) null, 0.0d, cvMat16, 1);
        cvMat15.put(0, cvMat15.get(0) - 1.0d);
        cvMat15.put(4, cvMat15.get(4) - 1.0d);
        cvMat15.put(8, cvMat15.get(8) - 1.0d);
        cvMat16.put(0, cvMat16.get(0) - 1.0d);
        cvMat16.put(4, cvMat16.get(4) - 1.0d);
        cvMat16.put(8, cvMat16.get(8) - 1.0d);
        double abs = Math.abs(cvMat5.get(0)) + Math.abs(cvMat5.get(1)) + Math.abs(cvMat5.get(2));
        CvMat cvMat19 = cvMat16;
        double[] dArr = {-Math.signum(cvMat5.get(0)), -Math.signum(cvMat5.get(1)), -Math.signum(cvMat5.get(2))};
        cvMat13.put(0.0d, 0.0d, 0.0d);
        cvMat14.put(0.0d, 0.0d, 0.0d);
        int i = 0;
        for (int i2 = 3; i < i2; i2 = 3) {
            cvMat13.put(0, cvMat13.get(0) + ((dArr[i] * cvMat15.get(i)) / abs));
            int i3 = i + 3;
            CvMat cvMat20 = cvMat15;
            cvMat13.put(1, cvMat13.get(1) + ((dArr[i] * cvMat15.get(i3)) / abs));
            int i4 = i + 6;
            cvMat13.put(2, cvMat13.get(2) + ((dArr[i] * cvMat20.get(i4)) / abs));
            CvMat cvMat21 = cvMat19;
            cvMat14.put(0, cvMat14.get(0) + ((dArr[i] * cvMat21.get(i)) / abs));
            cvMat14.put(1, cvMat14.get(1) + ((dArr[i] * cvMat21.get(i3)) / abs));
            cvMat14.put(2, cvMat14.get(2) + ((dArr[i] * cvMat21.get(i4)) / abs));
            i++;
            cvMat19 = cvMat21;
            cvMat12 = cvMat12;
            cvMat11 = cvMat11;
            cvMat15 = cvMat20;
        }
        CvMat cvMat22 = cvMat15;
        CvMat cvMat23 = cvMat19;
        CvMat cvMat24 = cvMat2;
        CvMat cvMat25 = cvMat15;
        CvMat cvMat26 = cvMat13;
        CvMat cvMat27 = cvMat14;
        CvMat cvMat28 = cvMat12;
        CvMat cvMat29 = cvMat11;
        opencv_core.cvGEMM(cvMat13, cvMat24, 1.0d, cvMat15, 1.0d, cvMat25, 2);
        opencv_core.cvGEMM(cvMat27, cvMat24, 1.0d, cvMat23, 1.0d, cvMat23, 2);
        double cvNorm = opencv_core.cvNorm(cvMat25);
        double cvNorm2 = opencv_core.cvNorm(cvMat23);
        if (cvNorm < cvNorm2) {
            if (cvMat6 != null) {
                cvMat6.put(cvMat29);
            }
            if (cvMat7 == null) {
                return cvNorm;
            }
            cvMat7.put(cvMat26);
            return cvNorm;
        }
        if (cvMat6 != null) {
            cvMat6.put(cvMat28);
        }
        if (cvMat7 != null) {
            cvMat7.put(cvMat27);
        }
        return cvNorm2;
    }

    public static double homogToRt(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, CvMat cvMat6, CvMat cvMat7) {
        CvMat cvMat8 = S3x3.get();
        CvMat cvMat9 = U3x3.get();
        CvMat cvMat10 = V3x3.get();
        CvMat cvMat11 = cvMat;
        opencv_core.cvSVD(cvMat, cvMat8, cvMat9, cvMat10, 0);
        return homogToRt(cvMat8, cvMat9, cvMat10, cvMat2, cvMat3, cvMat4, cvMat5, cvMat6, cvMat7);
    }

    public static double homogToRt(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, CvMat cvMat6, CvMat cvMat7, CvMat cvMat8, CvMat cvMat9) {
        CvMat cvMat10 = cvMat;
        CvMat cvMat11 = cvMat3;
        CvMat cvMat12 = cvMat5;
        CvMat cvMat13 = cvMat6;
        CvMat cvMat14 = cvMat8;
        CvMat cvMat15 = cvMat9;
        double d = cvMat10.get(0) / cvMat10.get(4);
        double d2 = cvMat10.get(8) / cvMat10.get(4);
        double d3 = d - d2;
        double d4 = 1.0d;
        double sqrt = Math.sqrt(1.0d - (d2 * d2));
        double sqrt2 = Math.sqrt((d * d) - 1.0d);
        double[] unitize = unitize(sqrt, sqrt2);
        double[] unitize2 = unitize((d * d2) + 1.0d, sqrt * sqrt2);
        double[] unitize3 = unitize((-unitize[1]) / d, (-unitize[0]) / d2);
        cvMat4.put(unitize2[0], 0.0d, unitize2[1], 0.0d, 1.0d, 0.0d, -unitize2[1], 0.0d, unitize2[0]);
        opencv_core.cvGEMM(cvMat2, cvMat4, 1.0d, (CvArr) null, 0.0d, cvMat4, 0);
        opencv_core.cvGEMM(cvMat4, cvMat3, 1.0d, (CvArr) null, 0.0d, cvMat4, 2);
        cvMat7.put(unitize2[0], 0.0d, -unitize2[1], 0.0d, 1.0d, 0.0d, unitize2[1], 0.0d, unitize2[0]);
        opencv_core.cvGEMM(cvMat2, cvMat7, 1.0d, (CvArr) null, 0.0d, cvMat7, 0);
        CvMat cvMat16 = cvMat3;
        opencv_core.cvGEMM(cvMat7, cvMat16, 1.0d, (CvArr) null, 0.0d, cvMat7, 2);
        char c = 0;
        char c2 = 1;
        double[] dArr = {cvMat16.get(0), cvMat16.get(3), cvMat16.get(6)};
        double[] dArr2 = {cvMat16.get(2), cvMat16.get(5), cvMat16.get(8)};
        double d5 = 1.0d;
        int i = 2;
        while (i >= 0) {
            double[] dArr3 = dArr2;
            cvMat13.put(i, d4 * ((unitize[c2] * dArr[i]) - (unitize[c] * dArr2[i])));
            cvMat15.put(i, ((unitize[c2] * dArr[i]) + (unitize[0] * dArr3[i])) * d5);
            c2 = 1;
            cvMat12.put(i, ((unitize3[0] * dArr[i]) + (unitize3[1] * dArr3[i])) * d4);
            cvMat14.put(i, ((unitize3[0] * dArr[i]) - (unitize3[1] * dArr3[i])) * d5);
            if (i == 2) {
                if (cvMat13.get(2) < 0.0d) {
                    cvMat13.put(2, -cvMat13.get(2));
                    cvMat12.put(2, -cvMat12.get(2));
                    d4 = -1.0d;
                }
                if (cvMat15.get(2) < 0.0d) {
                    cvMat15.put(2, -cvMat15.get(2));
                    cvMat14.put(2, -cvMat14.get(2));
                    d5 = -1.0d;
                }
            }
            i--;
            dArr2 = dArr3;
            c = 0;
        }
        return d3;
    }

    public static double[] unitize(double d, double d2) {
        double sqrt = Math.sqrt((d * d) + (d2 * d2));
        if (sqrt > 1.1920928955078125E-7d) {
            d /= sqrt;
            d2 /= sqrt;
        }
        return new double[]{d, d2};
    }

    public static void adaptiveThreshold(IplImage iplImage, IplImage iplImage2, IplImage iplImage3, IplImage iplImage4, boolean z, int i, int i2, double d, double d2) {
        IplImage iplImage5;
        final int width = iplImage.width();
        int height = iplImage.height();
        int nChannels = iplImage.nChannels();
        final int depth = iplImage.depth();
        int depth2 = iplImage4.depth();
        if (nChannels <= 1 || depth2 != 8) {
            iplImage5 = iplImage;
            IplImage iplImage6 = iplImage4;
        } else {
            IplImage iplImage7 = iplImage4;
            opencv_imgproc.cvCvtColor(iplImage, iplImage7, nChannels == 4 ? 11 : 6);
            iplImage5 = iplImage7;
        }
        final ByteBuffer byteBuffer = iplImage5.getByteBuffer();
        final ByteBuffer byteBuffer2 = iplImage4.getByteBuffer();
        DoubleBuffer doubleBuffer = iplImage2.getDoubleBuffer();
        DoubleBuffer doubleBuffer2 = iplImage3.getDoubleBuffer();
        final DoubleBuffer doubleBuffer3 = doubleBuffer2;
        final int widthStep = iplImage5.widthStep();
        final int widthStep2 = iplImage4.widthStep();
        int widthStep3 = iplImage2.widthStep();
        int i3 = widthStep3;
        int widthStep4 = iplImage3.widthStep();
        final int i4 = widthStep4;
        opencv_imgproc.cvIntegral(iplImage5, iplImage2, iplImage3, (CvArr) null);
        int i5 = height - 1;
        int i6 = (widthStep3 * i5) / 8;
        int i7 = width - 1;
        double d3 = (double) (width * height);
        double d4 = (((doubleBuffer.get(i6 + i7) - doubleBuffer.get(i6)) - doubleBuffer.get(i7)) + doubleBuffer.get(0)) / d3;
        int i8 = (i5 * widthStep4) / 8;
        final double d5 = (((((doubleBuffer2.get(i8 + i7) - doubleBuffer2.get(i8)) - doubleBuffer2.get(i7)) + doubleBuffer2.get(0)) / d3) - (d4 * d4)) * d;
        final int i9 = i;
        final int i10 = i2;
        final int i11 = height;
        final boolean z2 = z;
        int i12 = height;
        final double d6 = d2;
        final int i13 = i3;
        AnonymousClass1 r26 = r0;
        final DoubleBuffer doubleBuffer4 = doubleBuffer;
        AnonymousClass1 r0 = new Parallel.Looper() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<JavaCV> cls = JavaCV.class;
            }

            public void loop(int i, int i2, int i3) {
                double d;
                int i4 = i2;
                for (int i5 = i; i5 < i4; i5++) {
                    int i6 = 0;
                    int i7 = 0;
                    while (i7 < width) {
                        int i8 = i9;
                        int i9 = i10;
                        double d2 = 0.0d;
                        int i10 = i8;
                        while (true) {
                            if (i8 - i9 <= 2) {
                                break;
                            }
                            int i11 = i10 / 2;
                            int max = Math.max(i7 - i11, i6);
                            int min = Math.min(i7 + i11 + 1, width);
                            int max2 = Math.max(i5 - i11, i6);
                            int min2 = Math.min(i11 + i5 + 1, i11);
                            int i12 = max2;
                            double d3 = (double) (i10 * i10);
                            double d4 = (((doubleBuffer4.get(((i13 * min2) / 8) + min) - doubleBuffer4.get(((i13 * min2) / 8) + max)) - doubleBuffer4.get(((i13 * max2) / 8) + min)) + doubleBuffer4.get(((i13 * max2) / 8) + max)) / d3;
                            double d5 = ((((doubleBuffer3.get(((i4 * min2) / 8) + min) - doubleBuffer3.get(((min2 * i4) / 8) + max)) - doubleBuffer3.get(((i4 * i12) / 8) + min)) + doubleBuffer3.get(((i4 * i12) / 8) + max)) / d3) - (d4 * d4);
                            if (i10 == i8 && d5 < d5) {
                                d2 = d4;
                                break;
                            }
                            if (d5 > d5) {
                                i8 = i10;
                            } else {
                                i9 = i10;
                            }
                            i10 = (((((i8 - i9) / 2) + i9) / 2) * 2) + 1;
                            d2 = d4;
                            i6 = 0;
                        }
                        int i13 = depth;
                        if (i13 == 8) {
                            d = (double) (byteBuffer.get((widthStep * i5) + i7) & UByte.MAX_VALUE);
                        } else if (i13 == 32) {
                            d = (double) byteBuffer.getFloat((widthStep * i5) + (i7 * 4));
                        } else {
                            d = i13 == 64 ? byteBuffer.getDouble((widthStep * i5) + (i7 * 8)) : 0.0d;
                        }
                        byte b = -1;
                        if (z2) {
                            ByteBuffer byteBuffer = byteBuffer2;
                            int i14 = (widthStep2 * i5) + i7;
                            if (d >= 255.0d - ((255.0d - d2) * d6)) {
                                b = 0;
                            }
                            byteBuffer.put(i14, b);
                        } else {
                            ByteBuffer byteBuffer2 = byteBuffer2;
                            int i15 = (widthStep2 * i5) + i7;
                            if (d <= d2 * d6) {
                                b = 0;
                            }
                            byteBuffer2.put(i15, b);
                        }
                        i7++;
                        i6 = 0;
                    }
                }
            }
        };
        Parallel.loop(0, i12, r26);
    }

    public static void hysteresisThreshold(IplImage iplImage, IplImage iplImage2, double d, double d2, double d3) {
        int i;
        int i2;
        int i3;
        int round = (int) Math.round(d);
        int round2 = (int) Math.round(d2);
        byte round3 = (byte) ((int) Math.round(d3 / 2.0d));
        byte round4 = (byte) ((int) Math.round(d3));
        int height = iplImage.height();
        int width = iplImage.width();
        ByteBuffer byteBuffer = iplImage.getByteBuffer();
        ByteBuffer byteBuffer2 = iplImage2.getByteBuffer();
        int widthStep = iplImage.widthStep();
        int widthStep2 = iplImage2.widthStep();
        byte b = 0;
        byte b2 = byteBuffer.get(0) & UByte.MAX_VALUE;
        if (b2 >= round) {
            byteBuffer2.put(0, round4);
        } else if (b2 < round2) {
            byteBuffer2.put(0, (byte) 0);
        } else {
            byteBuffer2.put(0, round3);
        }
        int i4 = 1;
        int i5 = 1;
        while (true) {
            i = width - 1;
            if (i5 >= i) {
                break;
            }
            int i6 = 0 + i5;
            byte b3 = byteBuffer.get(i6) & UByte.MAX_VALUE;
            if (b3 >= round) {
                byteBuffer2.put(i6, round4);
            } else if (b3 < round2) {
                byteBuffer2.put(i6, (byte) 0);
            } else if (byteBuffer2.get(i6 - 1) == round4) {
                byteBuffer2.put(i6, round4);
            } else {
                byteBuffer2.put(i6, round3);
            }
            i5++;
        }
        int i7 = 0 + i;
        byte b4 = byteBuffer.get(i7) & UByte.MAX_VALUE;
        if (b4 >= round) {
            byteBuffer2.put(i7, round4);
        } else if (b4 < round2) {
            byteBuffer2.put(i7, (byte) 0);
        } else if (byteBuffer2.get(i7 - 1) == round4) {
            byteBuffer2.put(i7, round4);
        } else {
            byteBuffer2.put(i7, round3);
        }
        int i8 = height - 1;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            int i11 = i8 - 1;
            if (i8 <= 0) {
                break;
            }
            i9 += widthStep;
            i10 += widthStep2;
            byte b5 = byteBuffer.get(i9 + 0) & UByte.MAX_VALUE;
            if (b5 >= round) {
                byteBuffer2.put(i10 + 0, round4);
            } else if (b5 < round2) {
                byteBuffer2.put(i10 + 0, b);
            } else {
                int i12 = i10 + 0;
                int i13 = i12 - widthStep2;
                byte b6 = byteBuffer2.get(i13);
                byte b7 = byteBuffer2.get(i13 + i4);
                if (b6 == round4 || b7 == round4) {
                    byteBuffer2.put(i12, round4);
                } else {
                    byteBuffer2.put(i12, round3);
                }
            }
            int i14 = 1;
            while (i14 < i) {
                byte b8 = byteBuffer.get(i9 + i14) & UByte.MAX_VALUE;
                if (b8 >= round) {
                    byteBuffer2.put(i10 + i14, round4);
                } else if (b8 < round2) {
                    byteBuffer2.put(i10 + i14, (byte) 0);
                } else {
                    int i15 = i10 + i14;
                    byte b9 = byteBuffer2.get(i15 - 1);
                    int i16 = i15 - widthStep2;
                    i3 = widthStep;
                    byte b10 = byteBuffer2.get(i16 - 1);
                    i2 = i11;
                    byte b11 = byteBuffer2.get(i16);
                    byte b12 = byteBuffer2.get(i16 + 1);
                    if (b9 == round4 || b10 == round4 || b11 == round4 || b12 == round4) {
                        byteBuffer2.put(i15, round4);
                        i14++;
                        widthStep = i3;
                        i11 = i2;
                    } else {
                        byteBuffer2.put(i15, round3);
                        i14++;
                        widthStep = i3;
                        i11 = i2;
                    }
                }
                i3 = widthStep;
                i2 = i11;
                i14++;
                widthStep = i3;
                i11 = i2;
            }
            int i17 = widthStep;
            int i18 = i11;
            byte b13 = byteBuffer.get(i9 + i) & UByte.MAX_VALUE;
            if (b13 >= round) {
                byteBuffer2.put(i10 + i, round4);
            } else if (b13 < round2) {
                byteBuffer2.put(i10 + i, (byte) 0);
            } else {
                int i19 = i10 + i;
                byte b14 = byteBuffer2.get(i19 - 1);
                int i20 = i19 - widthStep2;
                byte b15 = byteBuffer2.get(i20 - 1);
                byte b16 = byteBuffer2.get(i20);
                if (b14 == round4 || b15 == round4 || b16 == round4) {
                    byteBuffer2.put(i19, round4);
                } else {
                    byteBuffer2.put(i19, round3);
                }
            }
            widthStep = i17;
            i8 = i18;
            b = 0;
            i4 = 1;
        }
        int height2 = iplImage.height();
        int width2 = iplImage.width();
        int i21 = (height2 - 1) * widthStep2;
        int i22 = width2 - 1;
        int i23 = i21 + i22;
        if (byteBuffer2.get(i23) == round3) {
            byteBuffer2.put(i23, (byte) 0);
        }
        int i24 = width2 - 2;
        for (int i25 = i24; i25 > 0; i25--) {
            int i26 = i21 + i25;
            if (byteBuffer2.get(i26) == round3) {
                if (byteBuffer2.get(i26 + 1) == round4) {
                    byteBuffer2.put(i26, round4);
                } else {
                    byteBuffer2.put(i26, (byte) 0);
                }
            }
        }
        int i27 = i21 + 0;
        if (byteBuffer2.get(i27) == round3) {
            if (byteBuffer2.get(i27 + 1) == round4) {
                byteBuffer2.put(i27, round4);
            } else {
                byteBuffer2.put(i27, (byte) 0);
            }
        }
        int i28 = height2 - 1;
        while (true) {
            int i29 = i28 - 1;
            if (i28 > 0) {
                i21 -= widthStep2;
                int i30 = i21 + i22;
                if (byteBuffer2.get(i30) == round3) {
                    int i31 = i30 + widthStep2;
                    if (byteBuffer2.get(i31) == round4 || byteBuffer2.get(i31 - 1) == round4) {
                        byteBuffer2.put(i30, round4);
                    } else {
                        byteBuffer2.put(i30, (byte) 0);
                    }
                }
                for (int i32 = i24; i32 > 0; i32--) {
                    int i33 = i21 + i32;
                    if (byteBuffer2.get(i33) == round3) {
                        if (byteBuffer2.get(i33 + 1) != round4) {
                            int i34 = i33 + widthStep2;
                            if (!(byteBuffer2.get(i34 + 1) == round4 || byteBuffer2.get(i34) == round4 || byteBuffer2.get(i34 - 1) == round4)) {
                                byteBuffer2.put(i33, (byte) 0);
                            }
                        }
                        byteBuffer2.put(i33, round4);
                    }
                }
                int i35 = i21 + 0;
                if (byteBuffer2.get(i35) == round3) {
                    if (byteBuffer2.get(i35 + 1) != round4) {
                        int i36 = i35 + widthStep2;
                        if (!(byteBuffer2.get(i36 + 1) == round4 || byteBuffer2.get(i36) == round4)) {
                            byteBuffer2.put(i35, (byte) 0);
                        }
                    }
                    byteBuffer2.put(i35, round4);
                }
                i28 = i29;
            } else {
                return;
            }
        }
    }

    public static void clamp(IplImage iplImage, IplImage iplImage2, double d, double d2) {
        int depth = iplImage.depth();
        int i = 0;
        if (depth == -2147483640) {
            ByteBuffer byteBuffer = iplImage.getByteBuffer();
            ByteBuffer byteBuffer2 = iplImage2.getByteBuffer();
            while (i < byteBuffer.capacity()) {
                byteBuffer2.put(i, (byte) ((int) Math.max(Math.min((double) byteBuffer.get(i), d2), d)));
                i++;
            }
        } else if (depth == -2147483632) {
            ShortBuffer shortBuffer = iplImage.getShortBuffer();
            ShortBuffer shortBuffer2 = iplImage2.getShortBuffer();
            while (i < shortBuffer.capacity()) {
                shortBuffer2.put(i, (short) ((int) Math.max(Math.min((double) shortBuffer.get(i), d2), d)));
                i++;
            }
        } else if (depth == -2147483616) {
            IntBuffer intBuffer = iplImage.getIntBuffer();
            IntBuffer intBuffer2 = iplImage2.getIntBuffer();
            while (i < intBuffer.capacity()) {
                intBuffer2.put(i, (int) Math.max(Math.min((double) intBuffer.get(i), d2), d));
                i++;
            }
        } else if (depth == 8) {
            ByteBuffer byteBuffer3 = iplImage.getByteBuffer();
            ByteBuffer byteBuffer4 = iplImage2.getByteBuffer();
            while (i < byteBuffer3.capacity()) {
                byteBuffer4.put(i, (byte) ((int) Math.max(Math.min((double) (byteBuffer3.get(i) & UByte.MAX_VALUE), d2), d)));
                i++;
            }
        } else if (depth == 16) {
            ShortBuffer shortBuffer3 = iplImage.getShortBuffer();
            ShortBuffer shortBuffer4 = iplImage2.getShortBuffer();
            while (i < shortBuffer3.capacity()) {
                shortBuffer4.put(i, (short) ((int) Math.max(Math.min((double) (shortBuffer3.get(i) & UShort.MAX_VALUE), d2), d)));
                i++;
            }
        } else if (depth == 32) {
            FloatBuffer floatBuffer = iplImage.getFloatBuffer();
            FloatBuffer floatBuffer2 = iplImage2.getFloatBuffer();
            while (i < floatBuffer.capacity()) {
                floatBuffer2.put(i, (float) Math.max(Math.min((double) floatBuffer.get(i), d2), d));
                i++;
            }
        } else if (depth == 64) {
            DoubleBuffer doubleBuffer = iplImage.getDoubleBuffer();
            DoubleBuffer doubleBuffer2 = iplImage2.getDoubleBuffer();
            while (i < doubleBuffer.capacity()) {
                doubleBuffer2.put(i, Math.max(Math.min(doubleBuffer.get(i), d2), d));
                i++;
            }
        }
    }

    public static double norm(double[] dArr) {
        return norm(dArr, 2.0d);
    }

    public static double norm(double[] dArr, double d) {
        int i = 0;
        double d2 = 0.0d;
        if (d == 1.0d) {
            int length = dArr.length;
            while (i < length) {
                d2 += Math.abs(dArr[i]);
                i++;
            }
            return d2;
        } else if (d == 2.0d) {
            int length2 = dArr.length;
            while (i < length2) {
                double d3 = dArr[i];
                d2 += d3 * d3;
                i++;
            }
            return Math.sqrt(d2);
        } else if (d == Double.POSITIVE_INFINITY) {
            int length3 = dArr.length;
            while (i < length3) {
                double abs = Math.abs(dArr[i]);
                if (abs > d2) {
                    d2 = abs;
                }
                i++;
            }
            return d2;
        } else if (d == Double.NEGATIVE_INFINITY) {
            int length4 = dArr.length;
            double d4 = Double.MAX_VALUE;
            while (i < length4) {
                double abs2 = Math.abs(dArr[i]);
                if (abs2 < d4) {
                    d4 = abs2;
                }
                i++;
            }
            return d4;
        } else {
            int length5 = dArr.length;
            while (i < length5) {
                d2 += Math.pow(Math.abs(dArr[i]), d);
                i++;
            }
            return Math.pow(d2, 1.0d / d);
        }
    }

    public static double norm(CvMat cvMat) {
        return norm(cvMat, 2.0d);
    }

    public static double norm(CvMat cvMat, double d) {
        return norm(cvMat, d, (CvMat) null);
    }

    public static double norm(CvMat cvMat, double d, CvMat cvMat2) {
        double d2 = -1.0d;
        if (d == 1.0d) {
            int cols = cvMat.cols();
            int rows = cvMat.rows();
            for (int i = 0; i < cols; i++) {
                double d3 = 0.0d;
                for (int i2 = 0; i2 < rows; i2++) {
                    d3 += Math.abs(cvMat.get(i2, i));
                }
                d2 = Math.max(d3, d2);
            }
            return d2;
        } else if (d == 2.0d) {
            int min = Math.min(cvMat.rows(), cvMat.cols());
            if (!(cvMat2 != null && cvMat2.rows() == min && cvMat2.cols() == 1)) {
                cvMat2 = CvMat.create(min, 1);
            }
            opencv_core.cvSVD(cvMat, cvMat2, (CvArr) null, (CvArr) null, 0);
            return cvMat2.get(0);
        } else if (d != Double.POSITIVE_INFINITY) {
            return -1.0d;
        } else {
            int rows2 = cvMat.rows();
            int cols2 = cvMat.cols();
            for (int i3 = 0; i3 < rows2; i3++) {
                double d4 = 0.0d;
                for (int i4 = 0; i4 < cols2; i4++) {
                    d4 += Math.abs(cvMat.get(i3, i4));
                }
                d2 = Math.max(d4, d2);
            }
            return d2;
        }
    }

    public static double cond(CvMat cvMat) {
        return cond(cvMat, 2.0d);
    }

    public static double cond(CvMat cvMat, double d) {
        return cond(cvMat, d, (CvMat) null);
    }

    public static double cond(CvMat cvMat, double d, CvMat cvMat2) {
        if (d == 2.0d) {
            int min = Math.min(cvMat.rows(), cvMat.cols());
            if (!(cvMat2 != null && cvMat2.rows() == min && cvMat2.cols() == 1)) {
                cvMat2 = CvMat.create(min, 1);
            }
            opencv_core.cvSVD(cvMat, cvMat2, (CvArr) null, (CvArr) null, 0);
            return cvMat2.get(0) / cvMat2.get(cvMat2.length() - 1);
        }
        int rows = cvMat.rows();
        int cols = cvMat.cols();
        if (!(cvMat2 != null && cvMat2.rows() == rows && cvMat2.cols() == cols)) {
            cvMat2 = CvMat.create(rows, cols);
        }
        opencv_core.cvInvert(cvMat, cvMat2);
        return norm(cvMat2, d) * norm(cvMat, d);
    }

    public static double median(double[] dArr) {
        double[] dArr2 = (double[]) dArr.clone();
        Arrays.sort(dArr2);
        if (dArr.length % 2 == 0) {
            return (dArr2[(dArr.length / 2) - 1] + dArr2[dArr.length / 2]) / 2.0d;
        }
        return dArr2[dArr.length / 2];
    }

    public static <T> T median(T[] tArr) {
        T[] tArr2 = (Object[]) tArr.clone();
        Arrays.sort(tArr2);
        return tArr2[tArr2.length / 2];
    }

    public static void fractalTriangleWave(double[] dArr, int i, int i2, double d) {
        fractalTriangleWave(dArr, i, i2, d, -1);
    }

    public static void fractalTriangleWave(double[] dArr, int i, int i2, double d, int i3) {
        int i4 = i2 - i;
        int i5 = (i4 / 2) + i;
        if (i != i2 && i != i5) {
            dArr[i5] = ((dArr[i] + dArr[i2]) / 2.0d) + d;
            if (i3 <= 0 || dArr.length <= i4 * i3) {
                double[] dArr2 = dArr;
                int i6 = i3;
                fractalTriangleWave(dArr2, i, i5, d / 1.4142135623730951d, i6);
                fractalTriangleWave(dArr2, i5, i2, (-d) / 1.4142135623730951d, i6);
                return;
            }
            double[] dArr3 = dArr;
            int i7 = i3;
            fractalTriangleWave(dArr3, i, i5, 0.0d, i7);
            fractalTriangleWave(dArr3, i5, i2, 0.0d, i7);
        }
    }

    public static void fractalTriangleWave(IplImage iplImage, CvMat cvMat) {
        fractalTriangleWave(iplImage, cvMat, -1);
    }

    public static void fractalTriangleWave(IplImage iplImage, CvMat cvMat, int i) {
        int i2;
        double[] dArr;
        double[] dArr2;
        double[] dArr3;
        int i3;
        double d;
        int i4;
        int width = iplImage.width();
        double[] dArr4 = new double[width];
        int i5 = width / 2;
        double[] dArr5 = dArr4;
        int i6 = i;
        fractalTriangleWave(dArr5, 0, i5, 1.0d, i6);
        fractalTriangleWave(dArr5, i5, width - 1, -1.0d, i6);
        double[] dArr6 = {Double.MAX_VALUE, Double.MIN_VALUE};
        int height = iplImage.height();
        int width2 = iplImage.width();
        int nChannels = iplImage.nChannels();
        int widthStep = iplImage.widthStep();
        char c = 0;
        if (iplImage.roi() != null) {
            height = iplImage.roi().height();
            width2 = iplImage.roi().width();
            i2 = ((iplImage.roi().yOffset() * widthStep) / 4) + (iplImage.roi().xOffset() * nChannels);
        } else {
            i2 = 0;
        }
        FloatBuffer floatBuffer = iplImage.getFloatBuffer(i2);
        if (cvMat == null) {
            dArr = null;
        } else {
            dArr = cvMat.get();
        }
        int i7 = 0;
        while (i7 < height) {
            int i8 = 0;
            while (i8 < width2) {
                int i9 = 0;
                while (i9 < nChannels) {
                    if (dArr == null) {
                        dArr2 = dArr6;
                        dArr3 = dArr;
                        d = dArr4[i8] + 0.0d;
                        i3 = width2;
                        i4 = height;
                    } else {
                        dArr2 = dArr6;
                        double d2 = (double) i8;
                        dArr3 = dArr;
                        double d3 = (double) i7;
                        double d4 = (((dArr[c] * d2) + (dArr[1] * d3)) + dArr3[2]) / (((dArr3[6] * d2) + (dArr3[7] * d3)) + dArr3[8]);
                        while (d4 < 0.0d) {
                            d4 += (double) width;
                        }
                        int i10 = (int) d4;
                        i3 = width2;
                        double d5 = d4 - ((double) i10);
                        d = 0.0d + (dArr4[i10 % width] * (1.0d - d5)) + (dArr4[(i10 + 1) % width] * d5);
                        i4 = height;
                    }
                    dArr2[0] = Math.min(dArr2[0], d);
                    dArr2[1] = Math.max(dArr2[1], d);
                    floatBuffer.put(((i7 * widthStep) / 4) + (i8 * nChannels) + i9, (float) d);
                    i9++;
                    height = i4;
                    width2 = i3;
                    dArr6 = dArr2;
                    dArr = dArr3;
                    c = 0;
                }
                double[] dArr7 = dArr6;
                int i11 = width2;
                double[] dArr8 = dArr;
                int i12 = height;
                i8++;
                width2 = i11;
                c = 0;
            }
            double[] dArr9 = dArr6;
            int i13 = width2;
            double[] dArr10 = dArr;
            int i14 = height;
            i7++;
            width2 = i13;
            c = 0;
        }
        double[] dArr11 = dArr6;
        opencv_core.cvConvertScale(iplImage, iplImage, 1.0d / (dArr11[1] - dArr11[0]), (-dArr11[0]) / (dArr11[1] - dArr11[0]));
    }

    public static void main(String[] strArr) {
        String implementationVersion = JavaCV.class.getPackage().getImplementationVersion();
        if (implementationVersion == null) {
            implementationVersion = "unknown";
        }
        PrintStream printStream = System.out;
        printStream.println("JavaCV version " + implementationVersion + "\nCopyright (C) 2009-2018 Samuel Audet <samuel.audet@gmail.com>\nProject site: https://github.com/bytedeco/javacv");
        System.exit(0);
    }
}
