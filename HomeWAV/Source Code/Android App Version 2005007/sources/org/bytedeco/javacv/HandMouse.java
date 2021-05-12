package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvContour;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_imgproc.CvMoments;

public class HandMouse {
    private IplImage binaryImage;
    private double centerX;
    private double centerY;
    private CvPoint contourPoints;
    private IntBuffer contourPointsBuffer;
    private int contourPointsSize;
    private double edgeX;
    private double edgeY;
    private double imageTipX;
    private double imageTipY;
    private boolean imageUpdateNeeded;
    private IntPointer intPointer;
    private CvMoments moments;
    private long prevTipTime;
    private double prevTipX;
    private double prevTipY;
    private CvPoint pt1;
    private CvPoint pt2;
    private IplImage relativeResidual;
    private CvRect roi;
    private Settings settings;
    private CvMemStorage storage;
    private long tipTime;
    private double tipX;
    private double tipY;

    public HandMouse() {
        this(new Settings());
    }

    public HandMouse(Settings settings2) {
        this.relativeResidual = null;
        this.binaryImage = null;
        this.roi = null;
        this.storage = CvMemStorage.create();
        this.contourPointsSize = 0;
        this.intPointer = new IntPointer(1);
        this.contourPoints = null;
        this.contourPointsBuffer = null;
        this.moments = new CvMoments();
        this.edgeX = 0.0d;
        this.edgeY = 0.0d;
        this.centerX = 0.0d;
        this.centerY = 0.0d;
        this.imageTipX = -1.0d;
        this.tipX = -1.0d;
        this.prevTipX = -1.0d;
        this.imageTipY = -1.0d;
        this.tipY = -1.0d;
        this.prevTipY = -1.0d;
        this.tipTime = 0;
        this.prevTipTime = 0;
        this.pt1 = new CvPoint();
        this.pt2 = new CvPoint();
        this.imageUpdateNeeded = false;
        setSettings(settings2);
    }

    public static class Settings extends BaseChildSettings {
        double brightnessMin;
        double clickSteadySize;
        long clickSteadyTime;
        double edgeAreaMax;
        double edgeAreaMin;
        int mopIterations;
        double thresholdHigh;
        double thresholdLow;
        double updateAlpha;

        public Settings() {
            this.mopIterations = 1;
            this.clickSteadySize = 0.05d;
            this.clickSteadyTime = 250;
            this.edgeAreaMin = 0.001d;
            this.edgeAreaMax = 0.1d;
            this.thresholdHigh = 0.5d;
            this.thresholdLow = 0.25d;
            this.brightnessMin = 0.1d;
            this.updateAlpha = 0.5d;
        }

        public Settings(Settings settings) {
            this.mopIterations = 1;
            this.clickSteadySize = 0.05d;
            this.clickSteadyTime = 250;
            this.edgeAreaMin = 0.001d;
            this.edgeAreaMax = 0.1d;
            this.thresholdHigh = 0.5d;
            this.thresholdLow = 0.25d;
            this.brightnessMin = 0.1d;
            this.updateAlpha = 0.5d;
            settings.mopIterations = 1;
            settings.clickSteadySize = 0.05d;
            settings.clickSteadyTime = 250;
            settings.edgeAreaMin = 0.001d;
            settings.edgeAreaMax = 0.1d;
            settings.thresholdHigh = 0.5d;
            settings.thresholdLow = 0.25d;
            settings.brightnessMin = 0.1d;
            settings.updateAlpha = 0.5d;
        }

        public int getMopIterations() {
            return this.mopIterations;
        }

        public void setMopIterations(int i) {
            this.mopIterations = i;
        }

        public double getClickSteadySize() {
            return this.clickSteadySize;
        }

        public void setClickSteadySize(double d) {
            this.clickSteadySize = d;
        }

        public long getClickSteadyTime() {
            return this.clickSteadyTime;
        }

        public void setClickSteadyTime(long j) {
            this.clickSteadyTime = j;
        }

        public double getEdgeAreaMin() {
            return this.edgeAreaMin;
        }

        public void setEdgeAreaMin(double d) {
            this.edgeAreaMin = d;
        }

        public double getEdgeAreaMax() {
            return this.edgeAreaMax;
        }

        public void setEdgeAreaMax(double d) {
            this.edgeAreaMax = d;
        }

        public double getThresholdHigh() {
            return this.thresholdHigh;
        }

        public void setThresholdHigh(double d) {
            this.thresholdHigh = d;
        }

        public double getThresholdLow() {
            return this.thresholdLow;
        }

        public void setThresholdLow(double d) {
            this.thresholdLow = d;
        }

        public double getBrightnessMin() {
            return this.brightnessMin;
        }

        public void setBrightnessMin(double d) {
            this.brightnessMin = d;
        }

        public double getUpdateAlpha() {
            return this.updateAlpha;
        }

        public void setUpdateAlpha(double d) {
            this.updateAlpha = d;
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings2) {
        this.settings = settings2;
    }

    public void reset() {
        this.prevTipY = -1.0d;
        this.prevTipX = -1.0d;
        this.tipY = -1.0d;
        this.tipX = -1.0d;
    }

    public void update(IplImage[] iplImageArr, int i, CvRect cvRect, double[] dArr) {
        int i2;
        double[] dArr2;
        FloatBuffer floatBuffer;
        FloatBuffer floatBuffer2;
        ByteBuffer byteBuffer;
        double d;
        FloatBuffer floatBuffer3;
        FloatBuffer floatBuffer4;
        HandMouse handMouse = this;
        CvRect cvRect2 = cvRect;
        double[] dArr3 = dArr;
        handMouse.roi = cvRect2;
        IplImage iplImage = iplImageArr[1];
        IplImage iplImage2 = iplImageArr[2];
        IplImage iplImage3 = iplImageArr[3];
        IplImage iplImage4 = iplImageArr[4];
        int width = cvRect.width();
        int height = cvRect.height();
        int nChannels = iplImage3.nChannels();
        handMouse.relativeResidual = IplImage.createIfNotCompatible(handMouse.relativeResidual, iplImage4);
        handMouse.binaryImage = IplImage.createIfNotCompatible(handMouse.binaryImage, iplImage4);
        opencv_core.cvResetImageROI(handMouse.relativeResidual);
        opencv_core.cvResetImageROI(handMouse.binaryImage);
        double d2 = ((double) (nChannels > 3 ? 3 : nChannels)) * handMouse.settings.brightnessMin;
        double d3 = (double) (((width + height) / 2) * width * height);
        double d4 = handMouse.settings.edgeAreaMax * d3;
        IplImage iplImage5 = iplImage2;
        double d5 = d3 * handMouse.settings.edgeAreaMin;
        ByteBuffer byteBuffer2 = iplImage4.getByteBuffer();
        FloatBuffer floatBuffer5 = iplImage3.getFloatBuffer();
        FloatBuffer floatBuffer6 = iplImage.getFloatBuffer();
        FloatBuffer floatBuffer7 = iplImage5.getFloatBuffer();
        ByteBuffer byteBuffer3 = handMouse.relativeResidual.getByteBuffer();
        while (true) {
            double d6 = 0.0d;
            if (!byteBuffer2.hasRemaining() || !floatBuffer5.hasRemaining() || !floatBuffer6.hasRemaining() || !floatBuffer7.hasRemaining() || !byteBuffer3.hasRemaining()) {
                double d7 = d4;
                JavaCV.hysteresisThreshold(handMouse.relativeResidual, handMouse.binaryImage, 255.0d, (handMouse.settings.thresholdLow * 255.0d) / handMouse.settings.thresholdHigh, 255.0d);
                int x = cvRect.x();
                int y = cvRect.y();
                opencv_core.cvSetImageROI(handMouse.binaryImage, cvRect2);
            } else {
                if (byteBuffer2.get() == 0) {
                    floatBuffer5.position(floatBuffer5.position() + nChannels);
                    floatBuffer6.position(floatBuffer6.position() + nChannels);
                    floatBuffer7.position(floatBuffer7.position() + nChannels);
                    byteBuffer3.put((byte) 0);
                    byteBuffer = byteBuffer2;
                    floatBuffer2 = floatBuffer5;
                    floatBuffer = floatBuffer6;
                    floatBuffer3 = floatBuffer7;
                    d = d4;
                } else {
                    d = d4;
                    double d8 = 0.0d;
                    int i3 = 0;
                    while (i3 < nChannels) {
                        float abs = Math.abs(floatBuffer5.get());
                        ByteBuffer byteBuffer4 = byteBuffer2;
                        float f = floatBuffer6.get();
                        FloatBuffer floatBuffer8 = floatBuffer5;
                        float f2 = floatBuffer7.get();
                        FloatBuffer floatBuffer9 = floatBuffer6;
                        if (i3 < 3) {
                            float max = Math.max(f, f2);
                            floatBuffer4 = floatBuffer7;
                            d6 += (double) max;
                            d8 = Math.max((double) (abs / max), d8);
                        } else {
                            floatBuffer4 = floatBuffer7;
                        }
                        i3++;
                        floatBuffer7 = floatBuffer4;
                        byteBuffer2 = byteBuffer4;
                        floatBuffer5 = floatBuffer8;
                        floatBuffer6 = floatBuffer9;
                    }
                    byteBuffer = byteBuffer2;
                    floatBuffer2 = floatBuffer5;
                    floatBuffer = floatBuffer6;
                    floatBuffer3 = floatBuffer7;
                    if (d6 < d2) {
                        byteBuffer3.put((byte) 0);
                    } else {
                        byteBuffer3.put((byte) ((int) Math.round((255.0d / handMouse.settings.thresholdHigh) * Math.min(d8, handMouse.settings.thresholdHigh))));
                    }
                }
                floatBuffer7 = floatBuffer3;
                d4 = d;
                byteBuffer2 = byteBuffer;
                floatBuffer5 = floatBuffer2;
                floatBuffer6 = floatBuffer;
            }
        }
        double d72 = d4;
        JavaCV.hysteresisThreshold(handMouse.relativeResidual, handMouse.binaryImage, 255.0d, (handMouse.settings.thresholdLow * 255.0d) / handMouse.settings.thresholdHigh, 255.0d);
        int x2 = cvRect.x();
        int y2 = cvRect.y();
        opencv_core.cvSetImageROI(handMouse.binaryImage, cvRect2);
        if (handMouse.settings.mopIterations > 0) {
            IplImage iplImage6 = handMouse.binaryImage;
            opencv_imgproc.cvMorphologyEx(iplImage6, iplImage6, (CvArr) null, (IplConvKernel) null, 2, handMouse.settings.mopIterations);
            IplImage iplImage7 = handMouse.binaryImage;
            opencv_imgproc.cvMorphologyEx(iplImage7, iplImage7, (CvArr) null, (IplConvKernel) null, 3, handMouse.settings.mopIterations);
        }
        CvSeq cvSeq = null;
        CvSeq cvContour = new CvContour((Pointer) null);
        opencv_imgproc.cvFindContours(handMouse.binaryImage, handMouse.storage, cvContour, Loader.sizeof(CvContour.class), 0, 1);
        double d9 = 0.0d;
        while (cvContour != null && !cvContour.isNull()) {
            handMouse.contourPointsSize = cvContour.total();
            CvPoint cvPoint = handMouse.contourPoints;
            if (cvPoint == null || cvPoint.capacity() < ((long) handMouse.contourPointsSize)) {
                CvPoint cvPoint2 = new CvPoint((long) handMouse.contourPointsSize);
                handMouse.contourPoints = cvPoint2;
                handMouse.contourPointsBuffer = cvPoint2.asByteBuffer().asIntBuffer();
            }
            opencv_core.cvCvtSeqToArray(cvContour, handMouse.contourPoints.position(0));
            int length = dArr3.length;
            double[] dArr4 = new double[length];
            int i4 = 0;
            while (i4 < dArr3.length / 2) {
                int i5 = i4 * 2;
                CvSeq cvSeq2 = cvSeq;
                double d10 = (double) (1 << i);
                dArr4[i5] = (dArr3[i5] / d10) - ((double) x2);
                int i6 = i5 + 1;
                dArr4[i6] = (dArr3[i6] / d10) - ((double) y2);
                i4++;
                cvSeq = cvSeq2;
                d9 = d9;
                d5 = d5;
            }
            double d11 = d5;
            CvSeq cvSeq3 = cvSeq;
            double d12 = d9;
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            int i7 = 0;
            while (i7 < handMouse.contourPointsSize) {
                int i8 = i7 * 2;
                int i9 = handMouse.contourPointsBuffer.get(i8);
                int i10 = y2;
                int i11 = handMouse.contourPointsBuffer.get(i8 + 1);
                int i12 = x2;
                int i13 = 0;
                while (true) {
                    if (i13 >= dArr3.length / 2) {
                        i2 = length;
                        dArr2 = dArr4;
                        break;
                    }
                    int i14 = i13 * 2;
                    double d16 = dArr4[i14];
                    double d17 = dArr4[i14 + 1];
                    double d18 = dArr4[(i14 + 2) % length] - d16;
                    double d19 = dArr4[(i14 + 3) % length] - d17;
                    int i15 = length;
                    dArr2 = dArr4;
                    double d20 = (double) i9;
                    int i16 = i9;
                    i2 = i15;
                    double d21 = (double) i11;
                    double d22 = (((d20 - d16) * d18) + ((d21 - d17) * d19)) / ((d18 * d18) + (d19 * d19));
                    double d23 = (d16 + (d18 * d22)) - d20;
                    double d24 = (d17 + (d22 * d19)) - d21;
                    if ((d23 * d23) + (d24 * d24) < 2.0d) {
                        d13 += 1.0d;
                        d14 += d20;
                        d15 += d21;
                        break;
                    }
                    i13++;
                    dArr4 = dArr2;
                    i9 = i16;
                    length = i2;
                }
                i7++;
                y2 = i10;
                x2 = i12;
                dArr4 = dArr2;
                length = i2;
            }
            int i17 = x2;
            int i18 = y2;
            double abs2 = Math.abs(opencv_imgproc.cvContourArea(cvContour, opencv_core.CV_WHOLE_SEQ, 0)) * d13;
            if (abs2 <= d11 || abs2 >= d72 || abs2 <= d12) {
                cvSeq = cvSeq3;
                d9 = d12;
            } else {
                double d25 = 1.0d / d13;
                handMouse.edgeX = d14 * d25;
                handMouse.edgeY = d15 * d25;
                cvSeq = cvContour;
                d9 = abs2;
            }
            cvContour = cvContour.h_next();
            y2 = i18;
            d5 = d11;
            x2 = i17;
        }
        int i19 = x2;
        int i20 = y2;
        CvSeq cvSeq4 = cvSeq;
        if (isClick()) {
            handMouse.prevTipX = -1.0d;
            handMouse.prevTipY = -1.0d;
            handMouse.prevTipTime = 0;
        } else if (!isSteady()) {
            handMouse.prevTipX = handMouse.tipX;
            handMouse.prevTipY = handMouse.tipY;
            handMouse.prevTipTime = System.currentTimeMillis();
        }
        if (cvSeq4 == null) {
            handMouse.tipX = -1.0d;
            handMouse.tipY = -1.0d;
            handMouse.tipTime = 0;
            handMouse.imageUpdateNeeded = false;
        } else {
            int i21 = 0;
            CvSeq cvSeq5 = cvSeq4;
            opencv_imgproc.cvMoments(cvSeq5, handMouse.moments, 0);
            double m00 = 1.0d / handMouse.moments.m00();
            handMouse.centerX = handMouse.moments.m10() * m00;
            handMouse.centerY = handMouse.moments.m01() * m00;
            handMouse.contourPointsSize = cvSeq5.total();
            opencv_core.cvCvtSeqToArray(cvSeq5, handMouse.contourPoints.position(0));
            double d26 = 0.0d;
            int i22 = 0;
            while (i22 < handMouse.contourPointsSize) {
                int i23 = i22 * 2;
                int i24 = handMouse.contourPointsBuffer.get(i23);
                int i25 = handMouse.contourPointsBuffer.get(i23 + 1);
                double d27 = handMouse.centerX;
                double d28 = handMouse.edgeX;
                double d29 = d27 - d28;
                double d30 = handMouse.centerY;
                double d31 = handMouse.edgeY;
                double d32 = d30 - d31;
                int i26 = i21;
                double d33 = (((((double) i24) - d28) * d29) + ((((double) i25) - d31) * d32)) / ((d29 * d29) + (d32 * d32));
                double d34 = ((d29 * d33) + d28) - d28;
                double d35 = ((d33 * d32) + d31) - d31;
                double d36 = (d34 * d34) + (d35 * d35);
                if (d36 > d26) {
                    i21 = i22;
                    d26 = d36;
                } else {
                    i21 = i26;
                }
                i22++;
                handMouse = this;
            }
            int i27 = i21;
            double d37 = (handMouse.imageTipX < 0.0d || handMouse.imageTipY < 0.0d) ? 1.0d : handMouse.settings.updateAlpha;
            int i28 = i27 * 2;
            double d38 = 1.0d - d37;
            handMouse.imageTipX = (((double) handMouse.contourPointsBuffer.get(i28)) * d37) + (handMouse.imageTipX * d38);
            double d39 = (d37 * ((double) handMouse.contourPointsBuffer.get(i28 + 1))) + (d38 * handMouse.imageTipY);
            handMouse.imageTipY = d39;
            double d40 = (double) (1 << i);
            handMouse.tipX = (handMouse.imageTipX + ((double) i19)) * d40;
            handMouse.tipY = (d39 + ((double) i20)) * d40;
            handMouse.tipTime = System.currentTimeMillis();
            handMouse.imageUpdateNeeded = true;
        }
        opencv_core.cvClearMemStorage(handMouse.storage);
    }

    public IplImage getRelativeResidual() {
        return this.relativeResidual;
    }

    public IplImage getResultImage() {
        if (this.imageUpdateNeeded) {
            opencv_core.cvSetZero(this.binaryImage);
            opencv_imgproc.cvFillPoly((CvArr) this.binaryImage, this.contourPoints, this.intPointer.put(this.contourPointsSize), 1, CvScalar.WHITE, 8, 0);
            this.pt1.put((byte) Tnaf.POW_2_WIDTH, this.edgeX, this.edgeY);
            opencv_imgproc.cvCircle((CvArr) this.binaryImage, this.pt1, 327680, CvScalar.GRAY, 2, 8, 16);
            this.pt1.put((byte) Tnaf.POW_2_WIDTH, this.centerX - 5.0d, this.centerY - 5.0d);
            this.pt2.put((byte) Tnaf.POW_2_WIDTH, this.centerX + 5.0d, this.centerY + 5.0d);
            opencv_imgproc.cvRectangle((CvArr) this.binaryImage, this.pt1, this.pt2, CvScalar.GRAY, 2, 8, 16);
            this.pt1.put((byte) Tnaf.POW_2_WIDTH, this.imageTipX - 5.0d, this.imageTipY - 5.0d);
            this.pt2.put((byte) Tnaf.POW_2_WIDTH, this.imageTipX + 5.0d, this.imageTipY + 5.0d);
            opencv_imgproc.cvLine((CvArr) this.binaryImage, this.pt1, this.pt2, CvScalar.GRAY, 2, 8, 16);
            this.pt1.put((byte) Tnaf.POW_2_WIDTH, this.imageTipX - 5.0d, this.imageTipY + 5.0d);
            this.pt2.put((byte) Tnaf.POW_2_WIDTH, this.imageTipX + 5.0d, this.imageTipY - 5.0d);
            opencv_imgproc.cvLine((CvArr) this.binaryImage, this.pt1, this.pt2, CvScalar.GRAY, 2, 8, 16);
            opencv_core.cvResetImageROI(this.binaryImage);
            this.imageUpdateNeeded = false;
        }
        return this.binaryImage;
    }

    public double getX() {
        return this.tipX;
    }

    public double getY() {
        return this.tipY;
    }

    public boolean isSteady() {
        double d = this.tipX;
        if (d < 0.0d) {
            return false;
        }
        double d2 = this.tipY;
        if (d2 < 0.0d) {
            return false;
        }
        double d3 = this.prevTipX;
        if (d3 < 0.0d) {
            return false;
        }
        double d4 = this.prevTipY;
        if (d4 < 0.0d) {
            return false;
        }
        double d5 = d - d3;
        double d6 = d2 - d4;
        double width = this.settings.clickSteadySize * ((double) ((this.roi.width() + this.roi.height()) / 2));
        if ((d5 * d5) + (d6 * d6) < width * width) {
            return true;
        }
        return false;
    }

    public boolean isClick() {
        return isSteady() && this.tipTime - this.prevTipTime > this.settings.clickSteadyTime;
    }
}
