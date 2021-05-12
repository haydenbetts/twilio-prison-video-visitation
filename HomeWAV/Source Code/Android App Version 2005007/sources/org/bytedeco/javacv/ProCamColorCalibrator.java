package org.bytedeco.javacv;

import java.awt.Color;
import org.bytedeco.javacv.MarkerDetector;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ProCamColorCalibrator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> R3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> n3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> t3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> z3x1 = CvMat.createThreadLocal(3, 1);
    private CvMat boardDstPts;
    private MarkedPlane boardPlane = null;
    private CvMat boardSrcPts;
    private CvMat camKinv;
    private CameraDevice camera = null;
    private Color[] cameraColors = null;
    private int counter = 0;
    private MarkerDetector markerDetector = null;
    private IplImage mask;
    private IplImage mask2;
    private CvMat projDstPts;
    private CvMat projSrcPts;
    private ProjectorDevice projector = null;
    private Color[] projectorColors = null;
    private Settings settings;
    private IplImage undistImage;

    public ProCamColorCalibrator(Settings settings2, MarkerDetector.Settings settings3, MarkedPlane markedPlane, CameraDevice cameraDevice, ProjectorDevice projectorDevice) {
        CameraDevice cameraDevice2 = cameraDevice;
        ProjectorDevice projectorDevice2 = projectorDevice;
        this.settings = settings2;
        this.markerDetector = new MarkerDetector(settings3);
        this.boardPlane = markedPlane;
        this.camera = cameraDevice2;
        this.projector = projectorDevice2;
        Marker[] markers = markedPlane.getMarkers();
        this.boardSrcPts = CvMat.create((markers.length * 4) + 4, 1, 6, 2);
        this.boardDstPts = CvMat.create((markers.length * 4) + 4, 1, 6, 2);
        this.boardSrcPts.put(0.0d, 0.0d, (double) markedPlane.getWidth(), 0.0d, (double) markedPlane.getWidth(), (double) markedPlane.getHeight(), 0.0d, (double) markedPlane.getHeight());
        for (int i = 0; i < markers.length; i++) {
            this.boardSrcPts.put((i * 8) + 8, markers[i].corners);
        }
        this.projSrcPts = CvMat.create(4, 1, 6, 2);
        this.projDstPts = CvMat.create(4, 1, 6, 2);
        this.projSrcPts.put(0.0d, 0.0d, (double) (projectorDevice2.imageWidth - 1), 0.0d, (double) (projectorDevice2.imageWidth - 1), (double) (projectorDevice2.imageHeight - 1), 0.0d, (double) (projectorDevice2.imageHeight - 1));
        this.camKinv = CvMat.create(3, 3);
        opencv_core.cvInvert(cameraDevice2.cameraMatrix, this.camKinv);
    }

    public static class Settings extends BaseChildSettings {
        double detectedBoardMin = 0.5d;
        int samplesPerChannel = 4;
        double trimmingFraction = 0.01d;

        public int getSamplesPerChannel() {
            return this.samplesPerChannel;
        }

        public void setSamplesPerChannel(int i) {
            this.samplesPerChannel = i;
        }

        public double getDetectedBoardMin() {
            return this.detectedBoardMin;
        }

        public void setDetectedBoardMin(double d) {
            this.detectedBoardMin = d;
        }
    }

    public int getColorCount() {
        return this.counter;
    }

    public Color[] getProjectorColors() {
        double responseGamma = 1.0d / this.projector.getSettings().getResponseGamma();
        int i = this.settings.samplesPerChannel;
        if (this.projectorColors == null) {
            int i2 = i * i * i;
            this.projectorColors = new Color[i2];
            this.cameraColors = new Color[i2];
            for (int i3 = 0; i3 < this.projectorColors.length; i3++) {
                int i4 = i3 / i;
                double d = (double) (i - 1);
                this.projectorColors[i3] = new Color((float) Math.pow(((double) (i3 % i)) / d, responseGamma), (float) Math.pow(((double) (i4 % i)) / d, responseGamma), (float) Math.pow(((double) ((i4 / i) % i)) / d, responseGamma));
            }
        }
        return this.projectorColors;
    }

    public Color getProjectorColor() {
        return getProjectorColors()[this.counter];
    }

    public Color[] getCameraColors() {
        return this.cameraColors;
    }

    public Color getCameraColor() {
        return getCameraColors()[this.counter];
    }

    public void addCameraColor() {
        this.counter++;
    }

    public void addCameraColor(Color color) {
        Color[] colorArr = this.cameraColors;
        int i = this.counter;
        this.counter = i + 1;
        colorArr[i] = color;
    }

    public IplImage getMaskImage() {
        return this.mask;
    }

    public IplImage getUndistortedCameraImage() {
        return this.undistImage;
    }

    public boolean processCameraImage(IplImage iplImage) {
        int i;
        IplImage iplImage2 = this.undistImage;
        if (!(iplImage2 != null && iplImage2.width() == iplImage.width() && this.undistImage.height() == iplImage.height() && this.undistImage.depth() == iplImage.depth())) {
            this.undistImage = iplImage.clone();
        }
        IplImage iplImage3 = this.mask;
        int i2 = 1;
        if (!(iplImage3 != null && this.mask2 != null && iplImage3.width() == iplImage.width() && this.mask2.width() == iplImage.width() && this.mask.height() == iplImage.height() && this.mask2.height() == iplImage.width())) {
            this.mask = IplImage.create(iplImage.width(), iplImage.height(), 8, 1, iplImage.origin());
            this.mask2 = IplImage.create(iplImage.width(), iplImage.height(), 8, 1, iplImage.origin());
        }
        CvMat cvMat = H3x3.get();
        CvMat cvMat2 = R3x3.get();
        CvMat cvMat3 = t3x1.get();
        CvMat cvMat4 = n3x1.get();
        CvMat cvMat5 = z3x1.get();
        cvMat5.put(0.0d, 0.0d, 1.0d);
        this.camera.undistort(iplImage, this.undistImage);
        Marker[] detect = this.markerDetector.detect(this.undistImage, false);
        if (((double) detect.length) < ((double) this.boardPlane.getMarkers().length) * this.settings.detectedBoardMin) {
            return false;
        }
        this.boardPlane.getTotalWarp(detect, cvMat);
        opencv_core.cvPerspectiveTransform(this.boardSrcPts, this.boardDstPts, cvMat);
        double[] dArr = this.boardDstPts.get();
        opencv_core.cvMatMul(this.camKinv, cvMat, cvMat2);
        JavaCV.HnToRt(cvMat2, cvMat5, cvMat2, cvMat3);
        opencv_core.cvMatMul(cvMat2, cvMat5, cvMat4);
        double cvDotProduct = opencv_core.cvDotProduct(cvMat3, cvMat5);
        opencv_core.cvGEMM(this.projector.T, cvMat4, -1.0d / cvDotProduct, this.projector.R, 1.0d, cvMat, 2);
        opencv_core.cvMatMul(this.projector.cameraMatrix, cvMat, cvMat);
        opencv_core.cvMatMul(cvMat, this.camKinv, cvMat);
        opencv_core.cvConvertScale(cvMat, cvMat, 1.0d / cvMat.get(8), 0.0d);
        opencv_core.cvInvert(cvMat, cvMat);
        opencv_core.cvConvertScale(cvMat, cvMat, 1.0d / cvMat.get(8), 0.0d);
        opencv_core.cvPerspectiveTransform(this.projSrcPts, this.projDstPts, cvMat);
        double[] dArr2 = this.projDstPts.get();
        opencv_core.cvSetZero(this.mask);
        int i3 = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            int i4 = i3 * 2;
            d += dArr[i4];
            d2 += dArr[i4 + 1];
            i3++;
        }
        double d3 = d / 4.0d;
        double d4 = d2 / 4.0d;
        int i5 = 0;
        while (i5 < 4) {
            int i6 = i5 * 2;
            dArr[i6] = dArr[i6] - ((dArr[i6] - d3) * this.settings.trimmingFraction);
            int i7 = i6 + i2;
            dArr[i7] = dArr[i7] - ((dArr[i7] - d4) * this.settings.trimmingFraction);
            i5++;
            i2 = 1;
        }
        opencv_imgproc.cvFillConvexPoly((CvArr) this.mask, new CvPoint(4).put(Tnaf.POW_2_WIDTH, dArr, 0, 8), 4, CvScalar.WHITE, 8, 16);
        for (int i8 = 0; i8 < (dArr.length - 8) / 8; i8++) {
            opencv_imgproc.cvFillConvexPoly((CvArr) this.mask, new CvPoint(4).put(Tnaf.POW_2_WIDTH, dArr, (i8 * 8) + 8, 8), 4, CvScalar.BLACK, 8, 16);
        }
        opencv_core.cvSetZero(this.mask2);
        double d5 = 0.0d;
        double d6 = 0.0d;
        for (int i9 = 0; i9 < 4; i9++) {
            int i10 = i9 * 2;
            d6 += dArr2[i10];
            d5 += dArr2[i10 + 1];
        }
        double d7 = d6 / 4.0d;
        double d8 = d5 / 4.0d;
        int i11 = 0;
        for (i = 4; i11 < i; i = 4) {
            int i12 = i11 * 2;
            dArr2[i12] = dArr2[i12] - ((dArr2[i12] - d7) * this.settings.trimmingFraction);
            int i13 = i12 + 1;
            dArr2[i13] = dArr2[i13] - ((dArr2[i13] - d8) * this.settings.trimmingFraction);
            i11++;
        }
        opencv_imgproc.cvFillConvexPoly((CvArr) this.mask2, new CvPoint(4).put(Tnaf.POW_2_WIDTH, dArr2, 0, 8), 4, CvScalar.WHITE, 8, 16);
        IplImage iplImage4 = this.mask;
        opencv_core.cvAnd(iplImage4, this.mask2, iplImage4, (CvArr) null);
        IplImage iplImage5 = this.mask;
        opencv_imgproc.cvErode(iplImage5, iplImage5, (IplConvKernel) null, 1);
        CvScalar cvAvg = opencv_core.cvAvg(this.undistImage, this.mask);
        int[] rGBColorOrder = this.camera.getRGBColorOrder();
        double highValue = iplImage.highValue();
        this.cameraColors[this.counter] = new Color((float) (cvAvg.val(rGBColorOrder[0]) / highValue), (float) (cvAvg.val(rGBColorOrder[1]) / highValue), (float) (cvAvg.val(rGBColorOrder[2]) / highValue));
        return true;
    }

    public double calibrate() {
        Color[] cameraColors2 = getCameraColors();
        Color[] projectorColors2 = getProjectorColors();
        ColorCalibrator colorCalibrator = new ColorCalibrator(this.projector);
        this.projector.avgColorErr = colorCalibrator.calibrate(cameraColors2, projectorColors2);
        this.camera.colorMixingMatrix = CvMat.create(3, 3);
        this.camera.additiveLight = CvMat.create(3, 1);
        opencv_core.cvSetIdentity(this.camera.colorMixingMatrix);
        opencv_core.cvSetZero(this.camera.additiveLight);
        this.counter = 0;
        return this.projector.avgColorErr;
    }
}
