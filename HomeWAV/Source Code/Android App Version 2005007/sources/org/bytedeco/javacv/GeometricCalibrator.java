package org.bytedeco.javacv;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import org.bytedeco.javacv.MarkerDetector;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.Point3f;
import org.bytedeco.opencv.opencv_core.Point3fVector;
import org.bytedeco.opencv.opencv_core.Point3fVectorVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;

public class GeometricCalibrator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private LinkedList<Marker[]> allImageMarkers = new LinkedList<>();
    private LinkedList<Marker[]> allObjectMarkers = new LinkedList<>();
    private Marker[] lastDetectedMarkers = null;
    private CvMat lastWarp = CvMat.create(3, 3);
    private MarkedPlane markedPlane;
    MarkerDetector markerDetector;
    private CvMat prevWarp = CvMat.create(3, 3);
    private ProjectiveDevice projectiveDevice;
    private Settings settings;
    private IplImage tempImage = null;
    private CvMat tempPts = CvMat.create(1, 4, 6, 2);
    private CvMat warp = CvMat.create(3, 3);
    private CvMat warpDstPts = CvMat.create(1, 4, 6, 2);
    private CvMat warpSrcPts = CvMat.create(1, 4, 6, 2);

    public GeometricCalibrator(Settings settings2, MarkerDetector.Settings settings3, MarkedPlane markedPlane2, ProjectiveDevice projectiveDevice2) {
        this.settings = settings2;
        this.markerDetector = new MarkerDetector(settings3);
        this.markedPlane = markedPlane2;
        this.projectiveDevice = projectiveDevice2;
        opencv_core.cvSetIdentity(this.prevWarp);
        opencv_core.cvSetIdentity(this.lastWarp);
        if (markedPlane2 != null) {
            int width = markedPlane2.getImage().width();
            int height = markedPlane2.getImage().height();
            double d = (double) width;
            double d2 = (double) height;
            this.warpSrcPts.put(0.0d, 0.0d, d, 0.0d, d, d2, 0.0d, d2);
        }
    }

    public static class Settings extends BaseChildSettings {
        double detectedBoardMin = 0.5d;
        double patternMovedSize = 0.05d;
        double patternSteadySize = 0.005d;

        public double getDetectedBoardMin() {
            return this.detectedBoardMin;
        }

        public void setDetectedBoardMin(double d) {
            this.detectedBoardMin = d;
        }

        public double getPatternSteadySize() {
            return this.patternSteadySize;
        }

        public void setPatternSteadySize(double d) {
            this.patternSteadySize = d;
        }

        public double getPatternMovedSize() {
            return this.patternMovedSize;
        }

        public void setPatternMovedSize(double d) {
            this.patternMovedSize = d;
        }
    }

    public MarkerDetector getMarkerDetector() {
        return this.markerDetector;
    }

    public MarkedPlane getMarkedPlane() {
        return this.markedPlane;
    }

    public ProjectiveDevice getProjectiveDevice() {
        return this.projectiveDevice;
    }

    public LinkedList<Marker[]> getAllObjectMarkers() {
        return this.allObjectMarkers;
    }

    public void setAllObjectMarkers(LinkedList<Marker[]> linkedList) {
        this.allObjectMarkers = linkedList;
    }

    public LinkedList<Marker[]> getAllImageMarkers() {
        return this.allImageMarkers;
    }

    public void setAllImageMarkers(LinkedList<Marker[]> linkedList) {
        this.allImageMarkers = linkedList;
    }

    public Marker[] processImage(IplImage iplImage) {
        this.projectiveDevice.imageWidth = iplImage.width();
        this.projectiveDevice.imageHeight = iplImage.height();
        boolean z = this.markedPlane.getForegroundColor().magnitude() > this.markedPlane.getBackgroundColor().magnitude();
        if (iplImage.depth() > 8) {
            IplImage iplImage2 = this.tempImage;
            if (!(iplImage2 != null && iplImage2.width() == iplImage.width() && this.tempImage.height() == iplImage.height())) {
                this.tempImage = IplImage.create(iplImage.width(), iplImage.height(), 8, 1, iplImage.origin());
            }
            opencv_core.cvConvertScale(iplImage, this.tempImage, 0.00390625d, 0.0d);
            this.lastDetectedMarkers = this.markerDetector.detect(this.tempImage, z);
        } else {
            this.lastDetectedMarkers = this.markerDetector.detect(iplImage, z);
        }
        if (((double) this.lastDetectedMarkers.length) < ((double) this.markedPlane.getMarkers().length) * this.settings.detectedBoardMin) {
            return null;
        }
        this.markedPlane.getTotalWarp(this.lastDetectedMarkers, this.warp);
        opencv_core.cvPerspectiveTransform(this.warpSrcPts, this.warpDstPts, this.warp);
        opencv_core.cvPerspectiveTransform(this.warpSrcPts, this.tempPts, this.prevWarp);
        double cvNorm = opencv_core.cvNorm(this.warpDstPts, this.tempPts);
        opencv_core.cvPerspectiveTransform(this.warpSrcPts, this.tempPts, this.lastWarp);
        double cvNorm2 = opencv_core.cvNorm(this.warpDstPts, this.tempPts);
        opencv_core.cvCopy(this.warp, this.prevWarp);
        double width = (double) ((iplImage.width() + iplImage.height()) / 2);
        if (cvNorm >= this.settings.patternSteadySize * width || cvNorm2 <= this.settings.patternMovedSize * width) {
            return null;
        }
        return this.lastDetectedMarkers;
    }

    public void drawMarkers(IplImage iplImage) {
        this.markerDetector.draw(iplImage, this.lastDetectedMarkers);
    }

    public void addMarkers() {
        addMarkers(this.markedPlane.getMarkers(), this.lastDetectedMarkers);
    }

    public void addMarkers(Marker[] markerArr) {
        addMarkers(this.markedPlane.getMarkers(), markerArr);
    }

    /* JADX WARNING: type inference failed for: r14v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r14v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addMarkers(org.bytedeco.javacv.Marker[] r14, org.bytedeco.javacv.Marker[] r15) {
        /*
            r13 = this;
            int r0 = r14.length
            int r1 = r15.length
            int r0 = java.lang.Math.min(r0, r1)
            org.bytedeco.javacv.Marker[] r1 = new org.bytedeco.javacv.Marker[r0]
            org.bytedeco.javacv.Marker[] r2 = new org.bytedeco.javacv.Marker[r0]
            int r3 = r14.length
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x000e:
            if (r5 >= r3) goto L_0x002b
            r7 = r14[r5]
            int r8 = r15.length
            r9 = 0
        L_0x0014:
            if (r9 >= r8) goto L_0x0028
            r10 = r15[r9]
            int r11 = r7.id
            int r12 = r10.id
            if (r11 != r12) goto L_0x0025
            r1[r6] = r7
            r2[r6] = r10
            int r6 = r6 + 1
            goto L_0x0028
        L_0x0025:
            int r9 = r9 + 1
            goto L_0x0014
        L_0x0028:
            int r5 = r5 + 1
            goto L_0x000e
        L_0x002b:
            if (r6 >= r0) goto L_0x003b
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r1, r6)
            r1 = r14
            org.bytedeco.javacv.Marker[] r1 = (org.bytedeco.javacv.Marker[]) r1
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r2, r6)
            r2 = r14
            org.bytedeco.javacv.Marker[] r2 = (org.bytedeco.javacv.Marker[]) r2
        L_0x003b:
            java.util.LinkedList<org.bytedeco.javacv.Marker[]> r14 = r13.allObjectMarkers
            r14.add(r1)
            java.util.LinkedList<org.bytedeco.javacv.Marker[]> r14 = r13.allImageMarkers
            r14.add(r2)
            org.bytedeco.opencv.opencv_core.CvMat r14 = r13.prevWarp
            org.bytedeco.opencv.opencv_core.CvMat r15 = r13.lastWarp
            org.bytedeco.opencv.global.opencv_core.cvCopy(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.GeometricCalibrator.addMarkers(org.bytedeco.javacv.Marker[], org.bytedeco.javacv.Marker[]):void");
    }

    public int getImageCount() {
        return this.allObjectMarkers.size();
    }

    private Point3fVectorVector getObjectPoints(CvMat cvMat, CvMat cvMat2) {
        FloatBuffer floatBuffer = cvMat.getFloatBuffer();
        IntBuffer intBuffer = cvMat2.getIntBuffer();
        int length = cvMat2.length();
        Point3fVectorVector point3fVectorVector = new Point3fVectorVector((long) length);
        for (int i = 0; i < length; i++) {
            int i2 = intBuffer.get();
            Point3fVector point3fVector = new Point3fVector((long) i2);
            for (int i3 = 0; i3 < i2; i3++) {
                point3fVector.put((long) i3, new Point3f(floatBuffer.get(), floatBuffer.get(), floatBuffer.get()));
            }
            point3fVectorVector.put((long) i, point3fVector);
        }
        return point3fVectorVector;
    }

    private Point2fVectorVector getImagePoints(CvMat cvMat, CvMat cvMat2) {
        FloatBuffer floatBuffer = cvMat.getFloatBuffer();
        IntBuffer intBuffer = cvMat2.getIntBuffer();
        int length = cvMat2.length();
        Point2fVectorVector point2fVectorVector = new Point2fVectorVector((long) length);
        for (int i = 0; i < length; i++) {
            int i2 = intBuffer.get();
            Point2fVector point2fVector = new Point2fVector((long) i2);
            for (int i3 = 0; i3 < i2; i3++) {
                point2fVector.put((long) i3, new Point2f(floatBuffer.get(), floatBuffer.get()));
            }
            point2fVectorVector.put((long) i, point2fVector);
        }
        return point2fVectorVector;
    }

    private CvMat[] getPoints(boolean z) {
        CvMat cvMat;
        Iterator it = this.allObjectMarkers.iterator();
        Iterator it2 = this.allImageMarkers.iterator();
        char c = 1;
        int i = 4;
        CvMat create = CvMat.create(1, this.allImageMarkers.size(), 4, 1);
        IntBuffer intBuffer = create.getIntBuffer();
        char c2 = 0;
        int i2 = 0;
        while (it.hasNext() && it2.hasNext()) {
            Marker[] markerArr = (Marker[]) it2.next();
            int length = ((Marker[]) it.next()).length * (z ? 1 : 4);
            intBuffer.put(length);
            i2 += length;
        }
        Iterator it3 = this.allObjectMarkers.iterator();
        Iterator it4 = this.allImageMarkers.iterator();
        CvMat create2 = CvMat.create(1, i2, 5, 3);
        CvMat create3 = CvMat.create(1, i2, 5, 2);
        FloatBuffer floatBuffer = create2.getFloatBuffer();
        FloatBuffer floatBuffer2 = create3.getFloatBuffer();
        while (it3.hasNext() && it4.hasNext()) {
            Marker[] markerArr2 = (Marker[]) it3.next();
            Marker[] markerArr3 = (Marker[]) it4.next();
            int i3 = 0;
            while (i3 < markerArr2.length) {
                if (z) {
                    double[] center = markerArr2[i3].getCenter();
                    cvMat = create2;
                    floatBuffer.put((float) center[c2]);
                    floatBuffer.put((float) center[c]);
                    floatBuffer.put(0.0f);
                    double[] center2 = markerArr3[i3].getCenter();
                    floatBuffer2.put((float) center2[c2]);
                    floatBuffer2.put((float) center2[c]);
                } else {
                    cvMat = create2;
                    int i4 = 0;
                    while (i4 < i) {
                        int i5 = i4 * 2;
                        floatBuffer.put((float) markerArr2[i3].corners[i5]);
                        int i6 = i5 + 1;
                        FloatBuffer floatBuffer3 = floatBuffer2;
                        floatBuffer.put((float) markerArr2[i3].corners[i6]);
                        floatBuffer.put(0.0f);
                        floatBuffer3.put((float) markerArr3[i3].corners[i5]);
                        floatBuffer3.put((float) markerArr3[i3].corners[i6]);
                        i4++;
                        floatBuffer2 = floatBuffer3;
                        floatBuffer = floatBuffer;
                        i = 4;
                    }
                }
                i3++;
                floatBuffer2 = floatBuffer2;
                create2 = cvMat;
                floatBuffer = floatBuffer;
                c = 1;
                i = 4;
                c2 = 0;
            }
            CvMat cvMat2 = create2;
        }
        return new CvMat[]{create2, create3, create};
    }

    public static double[] computeReprojectionError(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, CvMat cvMat6, CvMat cvMat7, CvMat cvMat8) {
        CvMat cvMat9 = cvMat8;
        CvMat create = CvMat.create(cvMat2.rows(), cvMat2.cols(), cvMat2.type());
        int rows = cvMat6.rows();
        CvMat cvMat10 = new CvMat();
        CvMat cvMat11 = new CvMat();
        CvMat cvMat12 = new CvMat();
        IntBuffer intBuffer = cvMat3.getIntBuffer();
        CvMat cvMat13 = new CvMat();
        CvMat cvMat14 = new CvMat();
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        while (i < rows) {
            cvMat10.reset();
            cvMat11.reset();
            cvMat12.reset();
            int i3 = intBuffer.get(i);
            int i4 = rows;
            int i5 = i2 + i3;
            IntBuffer intBuffer2 = intBuffer;
            opencv_core.cvGetCols(cvMat, cvMat10, i2, i5);
            opencv_core.cvGetCols(cvMat2, cvMat11, i2, i5);
            opencv_core.cvGetCols(create, cvMat12, i2, i5);
            int i6 = i + 1;
            CvMat cvMat15 = create;
            int i7 = i5;
            opencv_core.cvGetRows(cvMat6, cvMat13, i, i6, 1);
            opencv_core.cvGetRows(cvMat7, cvMat14, i, i6, 1);
            opencv_calib3d.projectPoints(opencv_core.cvarrToMat(cvMat10), opencv_core.cvarrToMat(cvMat13), opencv_core.cvarrToMat(cvMat14), opencv_core.cvarrToMat(cvMat4), opencv_core.cvarrToMat(cvMat5), opencv_core.cvarrToMat(cvMat12));
            double cvNorm = opencv_core.cvNorm(cvMat11, cvMat12);
            double d3 = cvNorm * cvNorm;
            if (cvMat9 != null) {
                cvMat9.put(i, Math.sqrt(d3 / ((double) i3)));
            }
            d += d3;
            for (int i8 = 0; i8 < i3; i8++) {
                double d4 = cvMat11.get(0, i8, 0);
                double d5 = cvMat11.get(0, i8, 1);
                double d6 = d4 - cvMat12.get(0, i8, 0);
                double d7 = d5 - cvMat12.get(0, i8, 1);
                double sqrt = Math.sqrt((d6 * d6) + (d7 * d7));
                if (sqrt > d2) {
                    d2 = sqrt;
                }
            }
            i = i6;
            rows = i4;
            intBuffer = intBuffer2;
            create = cvMat15;
            i2 = i7;
        }
        return new double[]{Math.sqrt(d / ((double) i2)), d2};
    }

    public double[] calibrate(boolean z) {
        ProjectiveDevice projectiveDevice2 = this.projectiveDevice;
        ProjectiveDevice.CalibrationSettings calibrationSettings = (ProjectiveDevice.CalibrationSettings) projectiveDevice2.getSettings();
        int i = 4;
        if (projectiveDevice2.cameraMatrix == null) {
            projectiveDevice2.cameraMatrix = CvMat.create(3, 3);
            opencv_core.cvSetZero(projectiveDevice2.cameraMatrix);
            if ((calibrationSettings.flags & 2) != 0) {
                projectiveDevice2.cameraMatrix.put(0, calibrationSettings.initAspectRatio);
                projectiveDevice2.cameraMatrix.put(4, 1.0d);
            }
        }
        if (!calibrationSettings.isFixK3()) {
            i = 5;
        }
        if (calibrationSettings.isRationalModel() && !calibrationSettings.isFixK4() && !calibrationSettings.isFixK4() && !calibrationSettings.isFixK5()) {
            i = 8;
        }
        if (projectiveDevice2.distortionCoeffs == null || projectiveDevice2.distortionCoeffs.cols() != i) {
            projectiveDevice2.distortionCoeffs = CvMat.create(1, i);
            opencv_core.cvSetZero(projectiveDevice2.distortionCoeffs);
        }
        CvMat cvMat = new CvMat();
        CvMat cvMat2 = new CvMat();
        projectiveDevice2.extrParams = CvMat.create(this.allImageMarkers.size(), 6);
        opencv_core.cvGetCols(projectiveDevice2.extrParams, cvMat, 0, 3);
        opencv_core.cvGetCols(projectiveDevice2.extrParams, cvMat2, 3, 6);
        CvMat[] points = getPoints(z);
        MatVector matVector = new MatVector();
        MatVector matVector2 = new MatVector();
        Mat mat = new Mat();
        opencv_calib3d.calibrateCamera(getObjectPoints(points[0], points[2]), getImagePoints(points[1], points[2]), new Size(projectiveDevice2.imageWidth, projectiveDevice2.imageHeight), opencv_core.cvarrToMat(projectiveDevice2.cameraMatrix), mat, matVector, matVector2, calibrationSettings.flags, new TermCriteria(3, 30, 2.220446049250313E-16d));
        int size = (int) matVector.size();
        CvMat cvMat3 = new CvMat();
        for (int i2 = 0; i2 < size; i2++) {
            long j = (long) i2;
            opencv_core.cvTranspose(opencv_core.cvMat(matVector.get(j)), opencv_core.cvGetRow(cvMat, cvMat3, i2));
            opencv_core.cvTranspose(opencv_core.cvMat(matVector2.get(j)), opencv_core.cvGetRow(cvMat2, cvMat3, i2));
        }
        projectiveDevice2.distortionCoeffs = opencv_core.cvMat(mat).clone();
        if (opencv_core.cvCheckArr(projectiveDevice2.cameraMatrix, 2, 0.0d, 0.0d) == 0 || opencv_core.cvCheckArr(projectiveDevice2.distortionCoeffs, 2, 0.0d, 0.0d) == 0 || opencv_core.cvCheckArr(projectiveDevice2.extrParams, 2, 0.0d, 0.0d) == 0) {
            projectiveDevice2.cameraMatrix = null;
            projectiveDevice2.avgReprojErr = -1.0d;
            projectiveDevice2.maxReprojErr = -1.0d;
            return null;
        }
        projectiveDevice2.reprojErrs = CvMat.create(1, this.allImageMarkers.size());
        double[] computeReprojectionError = computeReprojectionError(points[0], points[1], points[2], projectiveDevice2.cameraMatrix, projectiveDevice2.distortionCoeffs, cvMat, cvMat2, projectiveDevice2.reprojErrs);
        projectiveDevice2.avgReprojErr = computeReprojectionError[0];
        projectiveDevice2.maxReprojErr = computeReprojectionError[1];
        return computeReprojectionError;
    }

    public static double[] computeStereoError(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, CvMat cvMat6, CvMat cvMat7) {
        CvMat cvMat8 = cvMat;
        CvMat cvMat9 = cvMat2;
        int cols = cvMat.cols();
        CvMat create = CvMat.create(1, cols, 5, 3);
        CvMat create2 = CvMat.create(1, cols, 5, 3);
        opencv_calib3d.undistortPoints(opencv_core.cvarrToMat(cvMat), opencv_core.cvarrToMat(cvMat), opencv_core.cvarrToMat(cvMat3), opencv_core.cvarrToMat(cvMat4), (Mat) null, opencv_core.cvarrToMat(cvMat3));
        opencv_calib3d.undistortPoints(opencv_core.cvarrToMat(cvMat2), opencv_core.cvarrToMat(cvMat2), opencv_core.cvarrToMat(cvMat5), opencv_core.cvarrToMat(cvMat6), (Mat) null, opencv_core.cvarrToMat(cvMat5));
        opencv_calib3d.computeCorrespondEpilines(opencv_core.cvarrToMat(cvMat), 1, opencv_core.cvarrToMat(cvMat7), opencv_core.cvarrToMat(create));
        opencv_calib3d.computeCorrespondEpilines(opencv_core.cvarrToMat(cvMat2), 2, opencv_core.cvarrToMat(cvMat7), opencv_core.cvarrToMat(create2));
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < cols; i++) {
            double d3 = (cvMat8.get(0, i, 0) * create2.get(0, i, 0)) + (cvMat8.get(0, i, 1) * create2.get(0, i, 1)) + create2.get(0, i, 2);
            double d4 = (cvMat9.get(0, i, 0) * create.get(0, i, 0)) + (cvMat9.get(0, i, 1) * create.get(0, i, 1)) + create.get(0, i, 2);
            double d5 = (d3 * d3) + (d4 * d4);
            d += d5;
            double sqrt = Math.sqrt(d5);
            if (sqrt > d2) {
                d2 = sqrt;
            }
        }
        return new double[]{Math.sqrt(d / ((double) cols)), d2};
    }

    public double[] calibrateStereo(boolean z, GeometricCalibrator geometricCalibrator) {
        int i;
        CvMat cvMat;
        GeometricCalibrator geometricCalibrator2 = geometricCalibrator;
        ProjectiveDevice projectiveDevice2 = this.projectiveDevice;
        ProjectiveDevice projectiveDevice3 = geometricCalibrator2.projectiveDevice;
        ProjectiveDevice.CalibrationSettings calibrationSettings = (ProjectiveDevice.CalibrationSettings) projectiveDevice2.getSettings();
        CvMat[] points = getPoints(z);
        CvMat[] points2 = geometricCalibrator2.getPoints(z);
        FloatBuffer floatBuffer = points[0].getFloatBuffer();
        FloatBuffer floatBuffer2 = points[1].getFloatBuffer();
        IntBuffer intBuffer = points[2].getIntBuffer();
        FloatBuffer floatBuffer3 = points2[0].getFloatBuffer();
        FloatBuffer floatBuffer4 = points2[1].getFloatBuffer();
        IntBuffer intBuffer2 = points2[2].getIntBuffer();
        CvMat create = CvMat.create(1, Math.min(floatBuffer.capacity(), floatBuffer3.capacity()), 5, 3);
        CvMat create2 = CvMat.create(1, Math.min(floatBuffer2.capacity(), floatBuffer4.capacity()), 5, 2);
        ProjectiveDevice.CalibrationSettings calibrationSettings2 = (ProjectiveDevice.CalibrationSettings) projectiveDevice3.getSettings();
        CvMat create3 = CvMat.create(1, Math.min(floatBuffer2.capacity(), floatBuffer4.capacity()), 5, 2);
        CvMat create4 = CvMat.create(1, intBuffer.capacity(), 4, 1);
        FloatBuffer floatBuffer5 = create.getFloatBuffer();
        FloatBuffer floatBuffer6 = create2.getFloatBuffer();
        FloatBuffer floatBuffer7 = create3.getFloatBuffer();
        IntBuffer intBuffer3 = create4.getIntBuffer();
        ProjectiveDevice projectiveDevice4 = projectiveDevice2;
        ProjectiveDevice projectiveDevice5 = projectiveDevice3;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < intBuffer.capacity()) {
            int i5 = i3 + intBuffer.get(i2);
            IntBuffer intBuffer4 = intBuffer2;
            int i6 = i4 + intBuffer2.get(i2);
            IntBuffer intBuffer5 = intBuffer;
            int i7 = i3;
            CvMat cvMat2 = create4;
            int i8 = 0;
            while (i7 < i5) {
                int i9 = i5;
                int i10 = i7 * 3;
                CvMat cvMat3 = create3;
                float f = floatBuffer.get(i10);
                CvMat cvMat4 = create2;
                float f2 = floatBuffer.get(i10 + 1);
                float f3 = floatBuffer.get(i10 + 2);
                FloatBuffer floatBuffer8 = floatBuffer;
                int i11 = i4;
                while (true) {
                    if (i11 >= i6) {
                        i = i6;
                        cvMat = create;
                        break;
                    }
                    i = i6;
                    int i12 = i11 * 3;
                    float f4 = floatBuffer3.get(i12);
                    cvMat = create;
                    float f5 = floatBuffer3.get(i12 + 1);
                    float f6 = floatBuffer3.get(i12 + 2);
                    if (f == f4 && f2 == f5 && f3 == f6) {
                        floatBuffer5.put(f);
                        floatBuffer5.put(f2);
                        floatBuffer5.put(f3);
                        int i13 = i7 * 2;
                        floatBuffer6.put(floatBuffer2.get(i13));
                        floatBuffer6.put(floatBuffer2.get(i13 + 1));
                        int i14 = i11 * 2;
                        floatBuffer7.put(floatBuffer4.get(i14));
                        floatBuffer7.put(floatBuffer4.get(i14 + 1));
                        i8++;
                        break;
                    }
                    i11++;
                    i6 = i;
                    create = cvMat;
                }
                i7++;
                i5 = i9;
                create3 = cvMat3;
                create2 = cvMat4;
                floatBuffer = floatBuffer8;
                i6 = i;
                create = cvMat;
            }
            int i15 = i6;
            int i16 = i5;
            CvMat cvMat5 = create3;
            FloatBuffer floatBuffer9 = floatBuffer;
            CvMat cvMat6 = create;
            CvMat cvMat7 = create2;
            if (i8 > 0) {
                intBuffer3.put(i8);
            }
            i2++;
            create4 = cvMat2;
            intBuffer = intBuffer5;
            intBuffer2 = intBuffer4;
            i3 = i16;
            create3 = cvMat5;
            create2 = cvMat7;
            floatBuffer = floatBuffer9;
            i4 = i15;
            create = cvMat6;
        }
        CvMat cvMat8 = create;
        cvMat8.cols(floatBuffer5.position() / 3);
        CvMat cvMat9 = create2;
        cvMat9.cols(floatBuffer6.position() / 2);
        CvMat cvMat10 = create3;
        cvMat10.cols(floatBuffer7.position() / 2);
        CvMat cvMat11 = create4;
        cvMat11.cols(intBuffer3.position());
        ProjectiveDevice projectiveDevice6 = projectiveDevice4;
        projectiveDevice6.R = CvMat.create(3, 3);
        projectiveDevice6.R.put(1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d);
        projectiveDevice6.T = CvMat.create(3, 1);
        projectiveDevice6.T.put(0.0d, 0.0d, 0.0d);
        projectiveDevice6.E = CvMat.create(3, 3);
        opencv_core.cvSetZero(projectiveDevice6.E);
        projectiveDevice6.F = CvMat.create(3, 3);
        opencv_core.cvSetZero(projectiveDevice6.F);
        ProjectiveDevice projectiveDevice7 = projectiveDevice5;
        projectiveDevice7.R = CvMat.create(3, 3);
        projectiveDevice7.T = CvMat.create(3, 1);
        projectiveDevice7.E = CvMat.create(3, 3);
        projectiveDevice7.F = CvMat.create(3, 3);
        opencv_calib3d.stereoCalibrate(getObjectPoints(cvMat8, cvMat11), getImagePoints(cvMat9, cvMat11), getImagePoints(cvMat10, cvMat11), opencv_core.cvarrToMat(projectiveDevice6.cameraMatrix), opencv_core.cvarrToMat(projectiveDevice6.distortionCoeffs), opencv_core.cvarrToMat(projectiveDevice7.cameraMatrix), opencv_core.cvarrToMat(projectiveDevice7.distortionCoeffs), new Size(projectiveDevice6.imageWidth, projectiveDevice6.imageHeight), opencv_core.cvarrToMat(projectiveDevice7.R), opencv_core.cvarrToMat(projectiveDevice7.T), opencv_core.cvarrToMat(projectiveDevice7.E), opencv_core.cvarrToMat(projectiveDevice7.F), calibrationSettings2.flags, new TermCriteria(3, 100, 1.0E-6d));
        projectiveDevice6.avgEpipolarErr = 0.0d;
        projectiveDevice6.maxEpipolarErr = 0.0d;
        double[] computeStereoError = computeStereoError(cvMat9, cvMat10, projectiveDevice6.cameraMatrix, projectiveDevice6.distortionCoeffs, projectiveDevice7.cameraMatrix, projectiveDevice7.distortionCoeffs, projectiveDevice7.F);
        projectiveDevice7.avgEpipolarErr = computeStereoError[0];
        projectiveDevice7.maxEpipolarErr = computeStereoError[1];
        return computeStereoError;
    }
}
