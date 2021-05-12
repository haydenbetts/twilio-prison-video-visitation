package org.bytedeco.javacv;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import org.bytedeco.javacv.GeometricCalibrator;
import org.bytedeco.javacv.MarkerDetector;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ProCamGeometricCalibrator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ThreadLocal<CvMat> tempWarp3x3 = CvMat.createThreadLocal(3, 3);
    private final int LSB_IMAGE_SHIFT;
    private final int MSB_IMAGE_SHIFT;
    LinkedList<Marker[]>[] allImagedBoardMarkers;
    private final MarkedPlane boardPlane;
    private CvMat[] boardWarp;
    private final CvMat boardWarpSrcPts;
    /* access modifiers changed from: private */
    public GeometricCalibrator[] cameraCalibrators;
    private MarkerDetector.Settings detectorSettings;
    /* access modifiers changed from: private */
    public IplImage[] grayscaleImage;
    private CvMat[] lastBoardWarp;
    /* access modifiers changed from: private */
    public Marker[][] lastDetectedMarkers1;
    /* access modifiers changed from: private */
    public Marker[][] lastDetectedMarkers2;
    /* access modifiers changed from: private */
    public MarkerDetector[] markerDetectors;
    private CvMat[] prevBoardWarp;
    private CvMat[] projWarp;
    private final GeometricCalibrator projectorCalibrator;
    private final MarkedPlane projectorPlane;
    private double[] rmseBoardWarp;
    private double[] rmseProjWarp;
    private Settings settings;
    /* access modifiers changed from: private */
    public IplImage[] tempImage1;
    /* access modifiers changed from: private */
    public IplImage[] tempImage2;
    private CvMat[] tempPts1;
    private CvMat[] tempPts2;
    private boolean updatePrewarp;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProCamGeometricCalibrator(org.bytedeco.javacv.ProCamGeometricCalibrator.Settings r9, org.bytedeco.javacv.MarkerDetector.Settings r10, org.bytedeco.javacv.MarkedPlane r11, org.bytedeco.javacv.MarkedPlane r12, org.bytedeco.javacv.ProjectiveDevice r13, org.bytedeco.javacv.ProjectiveDevice r14) {
        /*
            r8 = this;
            r0 = 1
            org.bytedeco.javacv.GeometricCalibrator[] r6 = new org.bytedeco.javacv.GeometricCalibrator[r0]
            org.bytedeco.javacv.GeometricCalibrator r0 = new org.bytedeco.javacv.GeometricCalibrator
            r0.<init>(r9, r10, r11, r13)
            r13 = 0
            r6[r13] = r0
            org.bytedeco.javacv.GeometricCalibrator r7 = new org.bytedeco.javacv.GeometricCalibrator
            r7.<init>(r9, r10, r12, r14)
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r1.<init>((org.bytedeco.javacv.ProCamGeometricCalibrator.Settings) r2, (org.bytedeco.javacv.MarkerDetector.Settings) r3, (org.bytedeco.javacv.MarkedPlane) r4, (org.bytedeco.javacv.MarkedPlane) r5, (org.bytedeco.javacv.GeometricCalibrator[]) r6, (org.bytedeco.javacv.GeometricCalibrator) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.ProCamGeometricCalibrator.<init>(org.bytedeco.javacv.ProCamGeometricCalibrator$Settings, org.bytedeco.javacv.MarkerDetector$Settings, org.bytedeco.javacv.MarkedPlane, org.bytedeco.javacv.MarkedPlane, org.bytedeco.javacv.ProjectiveDevice, org.bytedeco.javacv.ProjectiveDevice):void");
    }

    public ProCamGeometricCalibrator(Settings settings2, MarkerDetector.Settings settings3, MarkedPlane markedPlane, MarkedPlane markedPlane2, GeometricCalibrator[] geometricCalibratorArr, GeometricCalibrator geometricCalibrator) {
        MarkerDetector.Settings settings4 = settings3;
        MarkedPlane markedPlane3 = markedPlane;
        MarkedPlane markedPlane4 = markedPlane2;
        GeometricCalibrator[] geometricCalibratorArr2 = geometricCalibratorArr;
        this.MSB_IMAGE_SHIFT = 8;
        this.LSB_IMAGE_SHIFT = 7;
        this.updatePrewarp = false;
        this.settings = settings2;
        this.detectorSettings = settings4;
        this.boardPlane = markedPlane3;
        this.projectorPlane = markedPlane4;
        this.cameraCalibrators = geometricCalibratorArr2;
        int length = geometricCalibratorArr2.length;
        this.markerDetectors = new MarkerDetector[length];
        this.allImagedBoardMarkers = new LinkedList[length];
        this.grayscaleImage = new IplImage[length];
        this.tempImage1 = new IplImage[length];
        this.tempImage2 = new IplImage[length];
        this.lastDetectedMarkers1 = new Marker[length][];
        this.lastDetectedMarkers2 = new Marker[length][];
        this.rmseBoardWarp = new double[length];
        this.rmseProjWarp = new double[length];
        this.boardWarp = new CvMat[length];
        this.projWarp = new CvMat[length];
        this.prevBoardWarp = new CvMat[length];
        this.lastBoardWarp = new CvMat[length];
        this.tempPts1 = new CvMat[length];
        this.tempPts2 = new CvMat[length];
        for (int i = 0; i < length; i++) {
            this.markerDetectors[i] = new MarkerDetector(settings4);
            this.allImagedBoardMarkers[i] = new LinkedList<>();
            this.grayscaleImage[i] = null;
            this.tempImage1[i] = null;
            this.tempImage2[i] = null;
            this.lastDetectedMarkers1[i] = null;
            this.lastDetectedMarkers2[i] = null;
            this.rmseBoardWarp[i] = Double.POSITIVE_INFINITY;
            this.rmseProjWarp[i] = Double.POSITIVE_INFINITY;
            this.boardWarp[i] = CvMat.create(3, 3);
            this.projWarp[i] = CvMat.create(3, 3);
            this.prevBoardWarp[i] = CvMat.create(3, 3);
            this.lastBoardWarp[i] = CvMat.create(3, 3);
            opencv_core.cvSetIdentity(this.prevBoardWarp[i]);
            opencv_core.cvSetIdentity(this.lastBoardWarp[i]);
            this.tempPts1[i] = CvMat.create(1, 4, 6, 2);
            this.tempPts2[i] = CvMat.create(1, 4, 6, 2);
        }
        this.projectorCalibrator = geometricCalibrator;
        CvMat create = CvMat.create(1, 4, 6, 2);
        this.boardWarpSrcPts = create;
        if (markedPlane3 != null) {
            int width = markedPlane.getImage().width();
            double d = (double) width;
            double height = (double) markedPlane.getImage().height();
            create.put(0.0d, 0.0d, d, 0.0d, d, height, 0.0d, height);
        }
        if (markedPlane4 != null) {
            int width2 = markedPlane2.getImage().width();
            int height2 = markedPlane2.getImage().height();
            geometricCalibrator.getProjectiveDevice().imageWidth = width2;
            geometricCalibrator.getProjectiveDevice().imageHeight = height2;
        }
    }

    public static class Settings extends GeometricCalibrator.Settings {
        double detectedProjectorMin = 0.5d;
        double prewarpUpdateErrorMax = 0.01d;
        boolean useOnlyIntersection = true;

        public double getDetectedProjectorMin() {
            return this.detectedProjectorMin;
        }

        public void setDetectedProjectorMin(double d) {
            this.detectedProjectorMin = d;
        }

        public boolean isUseOnlyIntersection() {
            return this.useOnlyIntersection;
        }

        public void setUseOnlyIntersection(boolean z) {
            this.useOnlyIntersection = z;
        }

        public double getPrewarpUpdateErrorMax() {
            return this.prewarpUpdateErrorMax;
        }

        public void setPrewarpUpdateErrorMax(double d) {
            this.prewarpUpdateErrorMax = d;
        }
    }

    public MarkedPlane getBoardPlane() {
        return this.boardPlane;
    }

    public MarkedPlane getProjectorPlane() {
        return this.projectorPlane;
    }

    public GeometricCalibrator[] getCameraCalibrators() {
        return this.cameraCalibrators;
    }

    public GeometricCalibrator getProjectorCalibrator() {
        return this.projectorCalibrator;
    }

    public int getImageCount() {
        int imageCount = this.projectorCalibrator.getImageCount();
        GeometricCalibrator[] geometricCalibratorArr = this.cameraCalibrators;
        int length = imageCount / geometricCalibratorArr.length;
        for (GeometricCalibrator geometricCalibrator : geometricCalibratorArr) {
        }
        return length;
    }

    public Marker[][] processCameraImage(IplImage iplImage) {
        return processCameraImage(iplImage, 0);
    }

    public Marker[][] processCameraImage(IplImage iplImage, final int i) {
        this.cameraCalibrators[i].getProjectiveDevice().imageWidth = iplImage.width();
        this.cameraCalibrators[i].getProjectiveDevice().imageHeight = iplImage.height();
        if (iplImage.nChannels() > 1) {
            IplImage[] iplImageArr = this.grayscaleImage;
            if (!(iplImageArr[i] != null && iplImageArr[i].width() == iplImage.width() && this.grayscaleImage[i].height() == iplImage.height() && this.grayscaleImage[i].depth() == iplImage.depth())) {
                this.grayscaleImage[i] = IplImage.create(iplImage.width(), iplImage.height(), iplImage.depth(), 1, iplImage.origin());
            }
            opencv_imgproc.cvCvtColor(iplImage, this.grayscaleImage[i], 6);
        } else {
            this.grayscaleImage[i] = iplImage;
        }
        final boolean z = this.boardPlane.getForegroundColor().magnitude() > this.boardPlane.getBackgroundColor().magnitude();
        final boolean z2 = this.projectorPlane.getForegroundColor().magnitude() > this.projectorPlane.getBackgroundColor().magnitude();
        if (this.grayscaleImage[i].depth() > 8) {
            IplImage[] iplImageArr2 = this.tempImage1;
            if (!(iplImageArr2[i] != null && iplImageArr2[i].width() == this.grayscaleImage[i].width() && this.tempImage1[i].height() == this.grayscaleImage[i].height())) {
                this.tempImage1[i] = IplImage.create(this.grayscaleImage[i].width(), this.grayscaleImage[i].height(), 8, 1, this.grayscaleImage[i].origin());
                this.tempImage2[i] = IplImage.create(this.grayscaleImage[i].width(), this.grayscaleImage[i].height(), 8, 1, this.grayscaleImage[i].origin());
            }
            Parallel.run(new Runnable() {
                public void run() {
                    opencv_core.cvConvertScale(ProCamGeometricCalibrator.this.grayscaleImage[i], ProCamGeometricCalibrator.this.tempImage1[i], 0.0078125d, 0.0d);
                    ProCamGeometricCalibrator.this.lastDetectedMarkers1[i] = ProCamGeometricCalibrator.this.cameraCalibrators[i].markerDetector.detect(ProCamGeometricCalibrator.this.tempImage1[i], z);
                }
            }, new Runnable() {
                public void run() {
                    opencv_core.cvConvertScale(ProCamGeometricCalibrator.this.grayscaleImage[i], ProCamGeometricCalibrator.this.tempImage2[i], 0.00390625d, 0.0d);
                    ProCamGeometricCalibrator.this.lastDetectedMarkers2[i] = ProCamGeometricCalibrator.this.markerDetectors[i].detect(ProCamGeometricCalibrator.this.tempImage2[i], z2);
                }
            });
        } else {
            Parallel.run(new Runnable() {
                public void run() {
                    ProCamGeometricCalibrator.this.lastDetectedMarkers1[i] = ProCamGeometricCalibrator.this.cameraCalibrators[i].markerDetector.detect(ProCamGeometricCalibrator.this.grayscaleImage[i], z);
                }
            }, new Runnable() {
                public void run() {
                    ProCamGeometricCalibrator.this.lastDetectedMarkers2[i] = ProCamGeometricCalibrator.this.markerDetectors[i].detect(ProCamGeometricCalibrator.this.grayscaleImage[i], z2);
                }
            });
        }
        if (!processMarkers(i)) {
            return null;
        }
        return new Marker[][]{this.lastDetectedMarkers1[i], this.lastDetectedMarkers2[i]};
    }

    public void drawMarkers(IplImage iplImage) {
        drawMarkers(iplImage, 0);
    }

    public void drawMarkers(IplImage iplImage, int i) {
        this.cameraCalibrators[i].markerDetector.draw(iplImage, this.lastDetectedMarkers1[i]);
        this.projectorCalibrator.markerDetector.draw(iplImage, this.lastDetectedMarkers2[i]);
    }

    public boolean processMarkers() {
        return processMarkers(0);
    }

    public boolean processMarkers(int i) {
        return processMarkers(this.lastDetectedMarkers1[i], this.lastDetectedMarkers2[i], i);
    }

    public boolean processMarkers(Marker[] markerArr, Marker[] markerArr2) {
        return processMarkers(markerArr, markerArr2, 0);
    }

    public boolean processMarkers(Marker[] markerArr, Marker[] markerArr2, int i) {
        this.rmseBoardWarp[i] = this.boardPlane.getTotalWarp(markerArr, this.boardWarp[i]);
        this.rmseProjWarp[i] = this.projectorPlane.getTotalWarp(markerArr2, this.projWarp[i]);
        double d = (double) ((this.cameraCalibrators[i].getProjectiveDevice().imageWidth + this.cameraCalibrators[i].getProjectiveDevice().imageHeight) / 2);
        if (this.rmseBoardWarp[i] <= this.settings.prewarpUpdateErrorMax * d && this.rmseProjWarp[i] <= this.settings.prewarpUpdateErrorMax * d) {
            this.updatePrewarp = true;
            if (((double) markerArr.length) >= ((double) this.boardPlane.getMarkers().length) * this.settings.detectedBoardMin && ((double) markerArr2.length) >= ((double) this.projectorPlane.getMarkers().length) * this.settings.detectedProjectorMin) {
                opencv_core.cvPerspectiveTransform(this.boardWarpSrcPts, this.tempPts1[i], this.boardWarp[i]);
                opencv_core.cvPerspectiveTransform(this.boardWarpSrcPts, this.tempPts2[i], this.prevBoardWarp[i]);
                double cvNorm = opencv_core.cvNorm(this.tempPts1[i], this.tempPts2[i]);
                opencv_core.cvPerspectiveTransform(this.boardWarpSrcPts, this.tempPts2[i], this.lastBoardWarp[i]);
                double cvNorm2 = opencv_core.cvNorm(this.tempPts1[i], this.tempPts2[i]);
                opencv_core.cvCopy(this.boardWarp[i], this.prevBoardWarp[i]);
                return cvNorm < this.settings.patternSteadySize * d && cvNorm2 > this.settings.patternMovedSize * d;
            }
        }
    }

    public void addMarkers() throws InterruptedException {
        addMarkers(0);
    }

    public void addMarkers(int i) throws InterruptedException {
        addMarkers(this.lastDetectedMarkers1[i], this.lastDetectedMarkers2[i], i);
    }

    public void addMarkers(Marker[] markerArr, Marker[] markerArr2) throws InterruptedException {
        addMarkers(markerArr, markerArr2, 0);
    }

    public void addMarkers(Marker[] markerArr, Marker[] markerArr2, int i) throws InterruptedException {
        int i2;
        int i3;
        Marker[] markerArr3;
        boolean z;
        Marker[] markerArr4 = markerArr;
        int i4 = i;
        CvMat cvMat = tempWarp3x3.get();
        if (!this.settings.useOnlyIntersection) {
            this.cameraCalibrators[i4].addMarkers(this.boardPlane.getMarkers(), markerArr4);
            this.allImagedBoardMarkers[i4].add(markerArr4);
        } else {
            int length = markerArr4.length;
            Marker[] markerArr5 = new Marker[length];
            for (int i5 = 0; i5 < length; i5++) {
                markerArr5[i5] = markerArr4[i5].clone();
            }
            opencv_core.cvInvert(this.projWarp[i4], cvMat);
            Marker.applyWarp(markerArr5, cvMat);
            int width = this.projectorPlane.getImage().width();
            int height = this.projectorPlane.getImage().height();
            Marker[] markerArr6 = new Marker[markerArr4.length];
            int i6 = 0;
            int i7 = 0;
            while (i6 < length) {
                double[] dArr = markerArr5[i6].corners;
                int i8 = 0;
                while (true) {
                    if (i8 >= 4) {
                        i2 = width;
                        i3 = length;
                        markerArr3 = markerArr5;
                        z = false;
                        break;
                    }
                    int i9 = this.detectorSettings.subPixelWindow / 2;
                    int i10 = i8 * 2;
                    markerArr3 = markerArr5;
                    double d = (double) i9;
                    if (dArr[i10] < d) {
                        i2 = width;
                        i3 = length;
                        break;
                    }
                    i3 = length;
                    i2 = width;
                    if (dArr[i10] >= ((double) (width - i9))) {
                        break;
                    }
                    int i11 = i10 + 1;
                    if (dArr[i11] < d || dArr[i11] >= ((double) (height - i9))) {
                        break;
                    }
                    i8++;
                    markerArr5 = markerArr3;
                    length = i3;
                    width = i2;
                }
                z = true;
                if (!z) {
                    markerArr6[i7] = markerArr4[i6];
                    i7++;
                }
                i6++;
                markerArr5 = markerArr3;
                length = i3;
                width = i2;
            }
            Marker[] markerArr7 = (Marker[]) Arrays.copyOf(markerArr6, i7);
            this.cameraCalibrators[i4].addMarkers(this.boardPlane.getMarkers(), markerArr7);
            this.allImagedBoardMarkers[i4].add(markerArr7);
        }
        int length2 = this.projectorPlane.getMarkers().length;
        Marker[] markerArr8 = new Marker[length2];
        for (int i12 = 0; i12 < length2; i12++) {
            markerArr8[i12] = this.projectorPlane.getMarkers()[i12].clone();
        }
        Marker.applyWarp(markerArr8, this.projectorPlane.getPrewarp());
        synchronized (this.projectorCalibrator) {
            while (this.projectorCalibrator.getImageCount() % this.cameraCalibrators.length < i4) {
                this.projectorCalibrator.wait();
            }
            this.projectorCalibrator.addMarkers(markerArr2, markerArr8);
            this.projectorCalibrator.notify();
        }
        opencv_core.cvCopy(this.boardWarp[i4], this.lastBoardWarp[i4]);
    }

    public IplImage getProjectorImage() {
        if (this.updatePrewarp) {
            double d = Double.MAX_VALUE;
            int i = 0;
            for (int i2 = 0; i2 < this.cameraCalibrators.length; i2++) {
                double d2 = this.rmseBoardWarp[i2] + this.rmseProjWarp[i2];
                if (d2 < d) {
                    i = i2;
                    d = d2;
                }
            }
            CvMat prewarp = this.projectorPlane.getPrewarp();
            opencv_core.cvInvert(this.projWarp[i], prewarp);
            opencv_core.cvMatMul(prewarp, this.boardWarp[i], prewarp);
            this.projectorPlane.setPrewarp(prewarp);
        }
        return this.projectorPlane.getImage();
    }

    public double[] calibrate(boolean z, boolean z2) {
        return calibrate(z, z2);
    }

    public double[] calibrate(boolean z, boolean z2, int i) {
        boolean z3 = z;
        GeometricCalibrator geometricCalibrator = this.cameraCalibrators[i];
        if (z2) {
            int i2 = 0;
            while (true) {
                GeometricCalibrator[] geometricCalibratorArr = this.cameraCalibrators;
                if (i2 >= geometricCalibratorArr.length) {
                    break;
                }
                geometricCalibratorArr[i2].calibrate(z3);
                GeometricCalibrator[] geometricCalibratorArr2 = this.cameraCalibrators;
                if (geometricCalibratorArr2[i2] != geometricCalibrator) {
                    geometricCalibrator.calibrateStereo(z3, geometricCalibratorArr2[i2]);
                }
                i2++;
            }
        }
        LinkedList<Marker[]> allObjectMarkers = this.projectorCalibrator.getAllObjectMarkers();
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        Iterator it = allObjectMarkers.iterator();
        Iterator[] itArr = new Iterator[this.cameraCalibrators.length];
        for (int i3 = 0; i3 < this.cameraCalibrators.length; i3++) {
            itArr[i3] = this.allImagedBoardMarkers[i3].iterator();
        }
        while (it.hasNext()) {
            int i4 = 0;
            while (i4 < this.cameraCalibrators.length) {
                double d = (this.settings.prewarpUpdateErrorMax * ((double) (this.cameraCalibrators[i4].getProjectiveDevice().imageWidth + this.cameraCalibrators[i4].getProjectiveDevice().imageHeight))) / 2.0d;
                Marker[] markerArr = (Marker[]) itArr[i4].next();
                Marker[] markerArr2 = (Marker[]) it.next();
                Marker[] markerArr3 = new Marker[markerArr.length];
                Marker[] markerArr4 = new Marker[markerArr2.length];
                Iterator it2 = it;
                Iterator[] itArr2 = itArr;
                int i5 = 0;
                while (i5 < markerArr.length) {
                    Marker clone = markerArr[i5].clone();
                    markerArr3[i5] = clone;
                    clone.corners = this.cameraCalibrators[i4].getProjectiveDevice().undistort(clone.corners);
                    i5++;
                    markerArr = markerArr;
                    allObjectMarkers = allObjectMarkers;
                }
                LinkedList<Marker[]> linkedList4 = allObjectMarkers;
                for (int i6 = 0; i6 < markerArr2.length; i6++) {
                    Marker clone2 = markerArr2[i6].clone();
                    markerArr4[i6] = clone2;
                    clone2.corners = this.cameraCalibrators[i4].getProjectiveDevice().undistort(clone2.corners);
                }
                int i7 = (this.boardPlane.getTotalWarp(markerArr3, this.boardWarp[i4]) > d ? 1 : (this.boardPlane.getTotalWarp(markerArr3, this.boardWarp[i4]) == d ? 0 : -1));
                CvMat[] cvMatArr = this.boardWarp;
                opencv_core.cvInvert(cvMatArr[i4], cvMatArr[i4]);
                Marker.applyWarp(markerArr4, this.boardWarp[i4]);
                linkedList2.add(markerArr4);
                if (this.cameraCalibrators[i4] == geometricCalibrator) {
                    linkedList3.add(markerArr4);
                    linkedList.add(markerArr2);
                } else {
                    linkedList3.add(new Marker[0]);
                    linkedList.add(new Marker[0]);
                }
                i4++;
                it = it2;
                itArr = itArr2;
                allObjectMarkers = linkedList4;
            }
        }
        LinkedList<Marker[]> linkedList5 = allObjectMarkers;
        this.projectorCalibrator.setAllObjectMarkers(linkedList2);
        double[] calibrate = this.projectorCalibrator.calibrate(z3);
        LinkedList<Marker[]> allObjectMarkers2 = geometricCalibrator.getAllObjectMarkers();
        LinkedList<Marker[]> allImageMarkers = geometricCalibrator.getAllImageMarkers();
        geometricCalibrator.setAllObjectMarkers(linkedList3);
        geometricCalibrator.setAllImageMarkers(linkedList);
        double[] calibrateStereo = geometricCalibrator.calibrateStereo(z3, this.projectorCalibrator);
        this.projectorCalibrator.setAllObjectMarkers(linkedList5);
        geometricCalibrator.setAllObjectMarkers(allObjectMarkers2);
        geometricCalibrator.setAllImageMarkers(allImageMarkers);
        return new double[]{calibrate[0], calibrate[1], calibrateStereo[0], calibrateStereo[1]};
    }
}
