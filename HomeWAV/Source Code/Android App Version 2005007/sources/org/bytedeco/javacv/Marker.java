package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.bytedeco.artoolkitplus.global.ARToolKitPlus;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class Marker implements Cloneable {
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> dstPts4x1 = CvMat.createThreadLocal(4, 1, 6, 2);
    private static IplImage[] imageCache = new IplImage[4096];
    private static final double[] src = {0.0d, 0.0d, 8.0d, 0.0d, 8.0d, 8.0d, 0.0d, 8.0d};
    private static ThreadLocal<CvMat> srcPts4x1 = CvMat.createThreadLocal(4, 1, 6, 2);
    public double confidence;
    public double[] corners;
    public int id;

    public Marker(int i, double[] dArr, double d) {
        this.id = i;
        this.corners = dArr;
        this.confidence = d;
    }

    public Marker(int i, double... dArr) {
        this(i, dArr, 1.0d);
    }

    public Marker clone() {
        return new Marker(this.id, (double[]) this.corners.clone(), this.confidence);
    }

    public int hashCode() {
        int i = (259 + this.id) * 37;
        double[] dArr = this.corners;
        return i + (dArr != null ? dArr.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Marker)) {
            return false;
        }
        Marker marker = (Marker) obj;
        if (marker.id != this.id || !Arrays.equals(marker.corners, this.corners)) {
            return false;
        }
        return true;
    }

    public double[] getCenter() {
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < 4; i++) {
            double[] dArr = this.corners;
            int i2 = i * 2;
            d += dArr[i2];
            d2 += dArr[i2 + 1];
        }
        return new double[]{d / 4.0d, d2 / 4.0d};
    }

    public IplImage getImage() {
        return getImage(this.id);
    }

    public static IplImage getImage(int i) {
        IplImage[] iplImageArr = imageCache;
        if (iplImageArr[i] == null) {
            iplImageArr[i] = IplImage.create(8, 8, 8, 1);
            ARToolKitPlus.createImagePatternBCH(i, imageCache[i].getByteBuffer());
        }
        return imageCache[i];
    }

    public void draw(IplImage iplImage) {
        draw(iplImage, CvScalar.BLACK, 1.0d, (CvMat) null);
    }

    public void draw(IplImage iplImage, CvScalar cvScalar, double d, CvMat cvMat) {
        draw(iplImage, cvScalar, d, d, cvMat);
    }

    public void draw(IplImage iplImage, CvScalar cvScalar, double d, double d2, CvMat cvMat) {
        CvMat cvMat2;
        CvMat cvMat3;
        int i;
        ByteBuffer byteBuffer;
        int i2;
        int i3;
        CvMat cvMat4 = H3x3.get();
        JavaCV.getPerspectiveTransform(src, this.corners, cvMat4);
        if (cvMat != null) {
            opencv_core.cvGEMM(cvMat, cvMat4, 1.0d, (CvArr) null, 0.0d, cvMat4, 0);
        }
        IplImage image = getImage();
        ByteBuffer byteBuffer2 = image.getByteBuffer();
        CvMat cvMat5 = srcPts4x1.get();
        CvMat cvMat6 = dstPts4x1.get();
        CvPoint cvPoint = new CvPoint(4);
        int height = image.height();
        int width = image.width();
        char c = 0;
        int i4 = 0;
        while (i4 < height) {
            int i5 = 0;
            while (i5 < width) {
                if (byteBuffer2.get((i4 * width) + i5) == 0) {
                    double[] dArr = new double[8];
                    double d3 = (double) i5;
                    dArr[c] = d3;
                    double d4 = (double) i4;
                    dArr[1] = d4;
                    i = width;
                    byteBuffer = byteBuffer2;
                    double d5 = (double) (i5 + 1);
                    dArr[2] = d5;
                    dArr[3] = d4;
                    dArr[4] = d5;
                    double d6 = (double) (i4 + 1);
                    dArr[5] = d6;
                    dArr[6] = d3;
                    dArr[7] = d6;
                    cvMat5.put(dArr);
                    opencv_core.cvPerspectiveTransform(cvMat5, cvMat6, cvMat4);
                    double d7 = 0.0d;
                    double d8 = 0.0d;
                    for (int i6 = 0; i6 < 4; i6++) {
                        int i7 = i6 * 2;
                        d7 += cvMat6.get(i7);
                        d8 += cvMat6.get(i7 + 1);
                    }
                    double d9 = d7 / 4.0d;
                    double d10 = d8 / 4.0d;
                    int i8 = 0;
                    for (int i9 = 4; i8 < i9; i9 = 4) {
                        int i10 = i8 * 2;
                        double d11 = cvMat6.get(i10);
                        double d12 = cvMat6.get(i10 + 1);
                        double d13 = d10 - d12;
                        double d14 = -1.0d;
                        double d15 = d9 - d11 < 0.0d ? -1.0d : 0.0d;
                        if (d13 < 0.0d) {
                            i3 = i4;
                        } else {
                            i3 = i4;
                            d14 = 0.0d;
                        }
                        long j = (long) i8;
                        cvPoint.position(j).x((int) Math.round(((d11 * d) + d15) * 65536.0d));
                        cvPoint.position(j).y((int) Math.round(((d12 * d2) + d14) * 65536.0d));
                        i8++;
                        i4 = i3;
                        cvMat5 = cvMat5;
                        cvMat6 = cvMat6;
                    }
                    cvMat3 = cvMat5;
                    cvMat2 = cvMat6;
                    i2 = i4;
                    opencv_imgproc.cvFillConvexPoly((CvArr) iplImage, cvPoint.position(0), 4, cvScalar, 8, 16);
                } else {
                    i = width;
                    byteBuffer = byteBuffer2;
                    cvMat3 = cvMat5;
                    cvMat2 = cvMat6;
                    i2 = i4;
                }
                i5++;
                i4 = i2;
                byteBuffer2 = byteBuffer;
                width = i;
                cvMat5 = cvMat3;
                cvMat6 = cvMat2;
                c = 0;
            }
            int i11 = width;
            CvMat cvMat7 = cvMat5;
            CvMat cvMat8 = cvMat6;
            i4++;
            byteBuffer2 = byteBuffer2;
            c = 0;
        }
    }

    public static class ArraySettings extends BaseChildSettings {
        boolean checkered = true;
        int columns = 12;
        int rows = 8;
        double sizeX = 200.0d;
        double sizeY = 200.0d;
        double spacingX = 300.0d;
        double spacingY = 300.0d;

        public int getRows() {
            return this.rows;
        }

        public void setRows(int i) {
            Integer valueOf = Integer.valueOf(this.rows);
            this.rows = i;
            firePropertyChange("rows", valueOf, Integer.valueOf(i));
        }

        public int getColumns() {
            return this.columns;
        }

        public void setColumns(int i) {
            Integer valueOf = Integer.valueOf(this.columns);
            this.columns = i;
            firePropertyChange("columns", valueOf, Integer.valueOf(i));
        }

        public double getSizeX() {
            return this.sizeX;
        }

        public void setSizeX(double d) {
            Double valueOf = Double.valueOf(this.sizeX);
            this.sizeX = d;
            firePropertyChange("sizeX", valueOf, Double.valueOf(d));
        }

        public double getSizeY() {
            return this.sizeY;
        }

        public void setSizeY(double d) {
            Double valueOf = Double.valueOf(this.sizeY);
            this.sizeY = d;
            firePropertyChange("sizeY", valueOf, Double.valueOf(d));
        }

        public double getSpacingX() {
            return this.spacingX;
        }

        public void setSpacingX(double d) {
            Double valueOf = Double.valueOf(this.spacingX);
            this.spacingX = d;
            firePropertyChange("spacingX", valueOf, Double.valueOf(d));
        }

        public double getSpacingY() {
            return this.spacingY;
        }

        public void setSpacingY(double d) {
            Double valueOf = Double.valueOf(this.spacingY);
            this.spacingY = d;
            firePropertyChange("spacingY", valueOf, Double.valueOf(d));
        }

        public boolean isCheckered() {
            return this.checkered;
        }

        public void setCheckered(boolean z) {
            Boolean valueOf = Boolean.valueOf(this.checkered);
            this.checkered = z;
            firePropertyChange("checkered", valueOf, Boolean.valueOf(z));
        }
    }

    public static Marker[][] createArray(ArraySettings arraySettings) {
        return createArray(arraySettings, 0.0d, 0.0d);
    }

    public static Marker[][] createArray(ArraySettings arraySettings, double d, double d2) {
        ArraySettings arraySettings2 = arraySettings;
        int i = arraySettings2.rows * arraySettings2.columns;
        Marker[] markerArr = new Marker[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < arraySettings2.rows) {
            int i4 = 0;
            while (i4 < arraySettings2.columns) {
                double d3 = arraySettings2.sizeX / 2.0d;
                double d4 = arraySettings2.sizeY / 2.0d;
                double d5 = (((double) i4) * arraySettings2.spacingX) + d3 + d;
                double d6 = (((double) i2) * arraySettings2.spacingY) + d4 + d2;
                double d7 = d5 - d3;
                double d8 = d6 - d4;
                double d9 = d5 + d3;
                double d10 = d6 + d4;
                markerArr[i3] = new Marker(i3, new double[]{d7, d8, d9, d8, d9, d10, d7, d10}, 1.0d);
                i3++;
                i4++;
                i2 = i2;
            }
            i2++;
        }
        if (!arraySettings2.checkered) {
            return new Marker[][]{markerArr};
        }
        int i5 = i / 2;
        Marker[] markerArr2 = new Marker[i5];
        Marker[] markerArr3 = new Marker[i5];
        for (int i6 = 0; i6 < i; i6++) {
            if (((i6 % arraySettings2.columns) % 2 == 0) ^ ((i6 / arraySettings2.columns) % 2 == 0)) {
                markerArr3[i6 / 2] = markerArr[i6];
            } else {
                markerArr2[i6 / 2] = markerArr[i6];
            }
        }
        return new Marker[][]{markerArr3, markerArr2};
    }

    public static Marker[][] createArray(int i, int i2, double d, double d2, double d3, double d4, boolean z, double d5, double d6) {
        ArraySettings arraySettings = new ArraySettings();
        arraySettings.rows = i;
        arraySettings.columns = i2;
        arraySettings.sizeX = d;
        arraySettings.sizeY = d2;
        arraySettings.spacingX = d3;
        arraySettings.spacingY = d4;
        arraySettings.checkered = z;
        return createArray(arraySettings, d5, d6);
    }

    public static void applyWarp(Marker[] markerArr, CvMat cvMat) {
        CvMat cvMat2 = srcPts4x1.get();
        for (Marker marker : markerArr) {
            opencv_core.cvPerspectiveTransform(cvMat2.put(marker.corners), cvMat2, cvMat);
            cvMat2.get(marker.corners);
        }
    }

    public String toString() {
        return "[" + this.id + ": (" + this.corners[0] + ", " + this.corners[1] + ") (" + this.corners[2] + ", " + this.corners[3] + ") (" + this.corners[4] + ", " + this.corners[5] + ") (" + this.corners[6] + ", " + this.corners[7] + ")]";
    }
}
