package org.bytedeco.javacv;

import java.awt.Color;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;

public class ColorCalibrator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ProjectiveDevice device;

    public ColorCalibrator(ProjectiveDevice projectiveDevice) {
        this.device = projectiveDevice;
    }

    public double calibrate(Color[] colorArr, Color[] colorArr2) {
        CvMat cvMat;
        Color[] colorArr3 = colorArr;
        int[] rGBColorOrder = this.device.getRGBColorOrder();
        int i = 3;
        CvMat create = CvMat.create(colorArr3.length * 3, 12);
        char c = 1;
        CvMat create2 = CvMat.create(colorArr3.length * 3, 1);
        CvMat create3 = CvMat.create(12, 1);
        double responseGamma = this.device.getSettings().getResponseGamma();
        char c2 = 0;
        int i2 = 0;
        while (true) {
            cvMat = create2;
            if (i2 >= colorArr3.length) {
                break;
            }
            float[] rGBColorComponents = colorArr2[i2].getRGBColorComponents((float[]) null);
            float[] rGBColorComponents2 = colorArr3[i2].getRGBColorComponents((float[]) null);
            int i3 = i2;
            double pow = Math.pow((double) rGBColorComponents[rGBColorOrder[c2]], responseGamma);
            CvMat cvMat2 = create3;
            double pow2 = Math.pow((double) rGBColorComponents[rGBColorOrder[c]], responseGamma);
            double pow3 = Math.pow((double) rGBColorComponents[rGBColorOrder[2]], responseGamma);
            int i4 = 0;
            while (i4 < i) {
                int i5 = (i3 * 36) + (i4 * 16);
                create.put(i5, pow);
                create.put(i5 + 1, pow2);
                create.put(i5 + 2, pow3);
                double d = pow2;
                create.put(i5 + 3, 1.0d);
                if (i4 < 2) {
                    int i6 = 0;
                    while (i6 < 12) {
                        create.put(i5 + 4 + i6, 0.0d);
                        i6++;
                        i4 = i4;
                    }
                }
                i4++;
                pow2 = d;
                i = 3;
            }
            int i7 = i3 * 3;
            CvMat cvMat3 = cvMat;
            cvMat3.put(i7, (double) rGBColorComponents2[rGBColorOrder[0]]);
            cvMat3.put(i7 + 1, (double) rGBColorComponents2[rGBColorOrder[1]]);
            cvMat3.put(i7 + 2, (double) rGBColorComponents2[rGBColorOrder[2]]);
            i2 = i3 + 1;
            create2 = cvMat3;
            create3 = cvMat2;
            i = 3;
            c = 1;
            c2 = 0;
        }
        CvMat cvMat4 = create3;
        CvMat cvMat5 = cvMat;
        if (((double) opencv_core.cvSolve(create, cvMat5, cvMat4, 1)) != 1.0d) {
            System.out.println("Error solving.");
        }
        CvMat create4 = CvMat.create(cvMat5.rows(), 1);
        opencv_core.cvMatMul(create, cvMat4, create4);
        double cvNorm = (opencv_core.cvNorm(cvMat5, create4) * opencv_core.cvNorm(cvMat5, create4)) / ((double) cvMat5.rows());
        double sqrt = Math.sqrt(cvNorm);
        CvScalar cvScalar = new CvScalar();
        CvScalar cvScalar2 = new CvScalar();
        opencv_core.cvAvgSdv(cvMat5, cvScalar, cvScalar2, (CvArr) null);
        double val = 1.0d - (cvNorm / (cvScalar2.val(0) * cvScalar2.val(0)));
        this.device.colorMixingMatrix = CvMat.create(3, 3);
        this.device.additiveLight = CvMat.create(3, 1);
        int i8 = 0;
        for (int i9 = 3; i8 < i9; i9 = 3) {
            int i10 = i8 * 4;
            double d2 = cvMat4.get(i10);
            double d3 = cvMat4.get(i10 + 1);
            double d4 = cvMat4.get(i10 + 2);
            double d5 = cvMat4.get(i10 + i9);
            int i11 = i8 * 3;
            this.device.colorMixingMatrix.put(i11, d2);
            this.device.colorMixingMatrix.put(i11 + 1, d3);
            this.device.colorMixingMatrix.put(i11 + 2, d4);
            this.device.additiveLight.put(i8, d5);
            i8++;
            sqrt = sqrt;
            cvMat4 = cvMat4;
        }
        this.device.colorR2 = val;
        double d6 = sqrt;
        this.device.avgColorErr = d6;
        return d6;
    }
}
