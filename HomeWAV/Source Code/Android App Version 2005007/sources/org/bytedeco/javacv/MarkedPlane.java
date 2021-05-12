package org.bytedeco.javacv;

import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class MarkedPlane {
    private static ThreadLocal<CvMat> tempWarp3x3 = CvMat.createThreadLocal(3, 3);
    private CvScalar backgroundColor;
    private CvScalar foregroundColor;
    private ThreadLocal<CvMat> localDstPts;
    private ThreadLocal<CvMat> localSrcPts;
    private Marker[] markers;
    private IplImage planeImage;
    private CvMat prewarp;
    private IplImage superPlaneImage;

    public MarkedPlane(int i, int i2, Marker[] markerArr, double d) {
        this(i, i2, markerArr, false, CvScalar.BLACK, CvScalar.WHITE, d);
    }

    public MarkedPlane(int i, int i2, Marker[] markerArr, boolean z, CvScalar cvScalar, CvScalar cvScalar2, double d) {
        MarkedPlane markedPlane = this;
        int i3 = i;
        int i4 = i2;
        Marker[] markerArr2 = markerArr;
        markedPlane.markers = null;
        markedPlane.planeImage = null;
        markedPlane.superPlaneImage = null;
        markedPlane.markers = markerArr2;
        markedPlane.foregroundColor = cvScalar;
        markedPlane.backgroundColor = cvScalar2;
        markedPlane.prewarp = null;
        char c = 2;
        char c2 = 4;
        if (z) {
            markedPlane.prewarp = CvMat.create(3, 3);
            int length = markerArr2.length;
            double d2 = Double.MIN_VALUE;
            double d3 = Double.MAX_VALUE;
            double d4 = Double.MAX_VALUE;
            int i5 = 0;
            double d5 = Double.MIN_VALUE;
            while (i5 < length) {
                double[] dArr = markerArr2[i5].corners;
                d4 = Math.min(Math.min(Math.min(Math.min(d4, dArr[1]), dArr[3]), dArr[5]), dArr[7]);
                d2 = Math.max(Math.max(Math.max(Math.max(d2, dArr[0]), dArr[2]), dArr[4]), dArr[6]);
                d5 = Math.max(Math.max(Math.max(Math.max(d5, dArr[1]), dArr[3]), dArr[5]), dArr[7]);
                i5++;
                int i6 = i2;
                markerArr2 = markerArr;
                d3 = Math.min(Math.min(Math.min(Math.min(d3, dArr[0]), dArr[c]), dArr[c2]), dArr[6]);
                c = 2;
                c2 = 4;
            }
            double d6 = d3;
            double d7 = d4;
            double d8 = (d2 - d6) / (d5 - d7);
            double d9 = (double) i3;
            i4 = i2;
            double d10 = (double) i4;
            if (d8 > d9 / d10) {
                double d11 = d10 - (d9 / d8);
                double[] dArr2 = {0.0d, d11, d9, d11, d9, d10, 0.0d, d10};
                markedPlane = this;
                JavaCV.getPerspectiveTransform(new double[]{d6, d7, d2, d7, d2, d5, d6, d5}, dArr2, markedPlane.prewarp);
            } else {
                double d12 = d10;
                markedPlane = this;
                double d13 = d8 * d12;
                JavaCV.getPerspectiveTransform(new double[]{d6, d7, d2, d7, d2, d5, d6, d5}, new double[]{0.0d, 0.0d, d13, 0.0d, d13, d12, 0.0d, d12}, markedPlane.prewarp);
            }
        }
        int i7 = i;
        if (i7 > 0 && i4 > 0) {
            markedPlane.planeImage = IplImage.create(i7, i4, 8, 1);
            if (d == 1.0d) {
                markedPlane.superPlaneImage = null;
            } else {
                markedPlane.superPlaneImage = IplImage.create((int) Math.ceil(((double) i7) * d), (int) Math.ceil(((double) i4) * d), 8, 1);
            }
            markedPlane.setPrewarp(markedPlane.prewarp);
        }
        Marker[] markerArr3 = markerArr;
        markedPlane.localSrcPts = CvMat.createThreadLocal(markerArr3.length * 4, 2);
        markedPlane.localDstPts = CvMat.createThreadLocal(markerArr3.length * 4, 2);
    }

    public CvScalar getForegroundColor() {
        return this.foregroundColor;
    }

    public void setForegroundColor(CvScalar cvScalar) {
        this.foregroundColor = cvScalar;
        setPrewarp(this.prewarp);
    }

    public CvScalar getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(CvScalar cvScalar) {
        this.backgroundColor = cvScalar;
        setPrewarp(this.prewarp);
    }

    public Marker[] getMarkers() {
        return this.markers;
    }

    public void setColors(CvScalar cvScalar, CvScalar cvScalar2) {
        this.foregroundColor = cvScalar;
        this.backgroundColor = cvScalar2;
        setPrewarp(this.prewarp);
    }

    public CvMat getPrewarp() {
        return this.prewarp;
    }

    public void setPrewarp(CvMat cvMat) {
        this.prewarp = cvMat;
        IplImage iplImage = this.superPlaneImage;
        if (iplImage == null) {
            opencv_core.cvSet(this.planeImage, this.backgroundColor);
        } else {
            opencv_core.cvSet(iplImage, this.backgroundColor);
        }
        int i = 0;
        while (true) {
            Marker[] markerArr = this.markers;
            if (i >= markerArr.length) {
                break;
            }
            IplImage iplImage2 = this.superPlaneImage;
            if (iplImage2 == null) {
                markerArr[i].draw(this.planeImage, this.foregroundColor, 1.0d, cvMat);
            } else {
                markerArr[i].draw(iplImage2, this.foregroundColor, ((double) iplImage2.width()) / ((double) this.planeImage.width()), cvMat);
            }
            i++;
        }
        IplImage iplImage3 = this.superPlaneImage;
        if (iplImage3 != null) {
            opencv_imgproc.cvResize(iplImage3, this.planeImage, 3);
        }
    }

    public IplImage getImage() {
        return this.planeImage;
    }

    public int getWidth() {
        return this.planeImage.width();
    }

    public int getHeight() {
        return this.planeImage.height();
    }

    public double getTotalWarp(Marker[] markerArr, CvMat cvMat) {
        return getTotalWarp(markerArr, cvMat, false);
    }

    public double getTotalWarp(Marker[] markerArr, CvMat cvMat, boolean z) {
        Marker[] markerArr2 = markerArr;
        CvMat cvMat2 = cvMat;
        int i = z ? 1 : 4;
        CvMat cvMat3 = this.localSrcPts.get();
        cvMat3.rows(this.markers.length * i);
        CvMat cvMat4 = this.localDstPts.get();
        cvMat4.rows(this.markers.length * i);
        int i2 = 0;
        for (Marker marker : this.markers) {
            int length = markerArr2.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                Marker marker2 = markerArr2[i3];
                if (marker.id == marker2.id) {
                    if (z) {
                        int i4 = i2 * 2;
                        cvMat3.put(i4, marker.getCenter());
                        cvMat4.put(i4, marker2.getCenter());
                    } else {
                        int i5 = i2 * 2;
                        cvMat3.put(i5, marker.corners);
                        cvMat4.put(i5, marker2.corners);
                    }
                    i2 += i;
                } else {
                    i3++;
                }
            }
        }
        if (i2 <= 4 && (cvMat3.rows() != 4 || i2 != 4)) {
            return Double.POSITIVE_INFINITY;
        }
        cvMat3.rows(i2);
        cvMat4.rows(i2);
        if (i2 == 4) {
            JavaCV.getPerspectiveTransform(cvMat3.get(), cvMat4.get(), cvMat2);
        } else {
            opencv_core.cvCopy(opencv_core.cvMat(opencv_calib3d.findHomography(opencv_core.cvarrToMat(cvMat3), opencv_core.cvarrToMat(cvMat4))), cvMat2);
        }
        cvMat3.cols(1);
        cvMat3.type(6, 2);
        cvMat4.cols(1);
        cvMat4.type(6, 2);
        opencv_core.cvPerspectiveTransform(cvMat3, cvMat3, cvMat2);
        cvMat3.cols(2);
        cvMat3.type(6, 1);
        cvMat4.cols(2);
        cvMat4.type(6, 1);
        double d = 0.0d;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i6 * 2;
            double d2 = cvMat4.get(i7) - cvMat3.get(i7);
            int i8 = i7 + 1;
            double d3 = cvMat4.get(i8) - cvMat3.get(i8);
            d += (d2 * d2) + (d3 * d3);
        }
        double sqrt = Math.sqrt(d / ((double) i2));
        if (this.prewarp != null) {
            CvMat cvMat5 = tempWarp3x3.get();
            opencv_core.cvInvert(this.prewarp, cvMat5);
            opencv_core.cvMatMul(cvMat2, cvMat5, cvMat2);
        }
        return sqrt;
    }
}
