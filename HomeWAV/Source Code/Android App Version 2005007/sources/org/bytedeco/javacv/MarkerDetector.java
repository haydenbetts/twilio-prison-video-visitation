package org.bytedeco.javacv;

import java.util.Arrays;
import org.bytedeco.artoolkitplus.ARMarkerInfo;
import org.bytedeco.artoolkitplus.MultiTracker;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvBox2D;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvPoint2D32f;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSize;
import org.bytedeco.opencv.opencv_core.CvTermCriteria;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_imgproc.CvFont;

public class MarkerDetector {
    private int channels;
    private CvPoint2D32f corners;
    private int depth;
    private CvFont font;
    private int height;
    private IntPointer markerNum;
    private CvMemStorage memory;
    private CvMat points;
    private Settings settings;
    private IplImage sqSumImage;
    private CvSize subPixelSize;
    private CvTermCriteria subPixelTermCriteria;
    private CvSize subPixelZeroZone;
    private IplImage sumImage;
    private IplImage tempImage;
    private IplImage tempImage2;
    private CvSize textSize;
    private IplImage thresholdedImage;
    private MultiTracker tracker;
    private int width;

    public MarkerDetector(Settings settings2) {
        this.tracker = null;
        this.markerNum = new IntPointer(1);
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.channels = 0;
        this.points = CvMat.create(1, 4, 5, 2);
        this.corners = new CvPoint2D32f(4);
        this.memory = CvMemStorage.create();
        this.subPixelSize = null;
        this.subPixelZeroZone = null;
        this.subPixelTermCriteria = null;
        this.font = opencv_imgproc.cvFont(1.0d, 1);
        this.textSize = new CvSize();
        setSettings(settings2);
    }

    public MarkerDetector() {
        this(new Settings());
    }

    public static class Settings extends BaseChildSettings {
        int subPixelWindow = 11;
        double thresholdKBlackMarkers = 0.6d;
        double thresholdKWhiteMarkers = 1.0d;
        double thresholdVarMultiplier = 1.0d;
        int thresholdWindowMax = 63;
        int thresholdWindowMin = 5;

        public int getThresholdWindowMin() {
            return this.thresholdWindowMin;
        }

        public void setThresholdWindowMin(int i) {
            this.thresholdWindowMin = i;
        }

        public int getThresholdWindowMax() {
            return this.thresholdWindowMax;
        }

        public void setThresholdWindowMax(int i) {
            this.thresholdWindowMax = i;
        }

        public double getThresholdVarMultiplier() {
            return this.thresholdVarMultiplier;
        }

        public void setThresholdVarMultiplier(double d) {
            this.thresholdVarMultiplier = d;
        }

        public double getThresholdKBlackMarkers() {
            return this.thresholdKBlackMarkers;
        }

        public void setThresholdKBlackMarkers(double d) {
            this.thresholdKBlackMarkers = d;
        }

        public double getThresholdKWhiteMarkers() {
            return this.thresholdKWhiteMarkers;
        }

        public void setThresholdKWhiteMarkers(double d) {
            this.thresholdKWhiteMarkers = d;
        }

        public int getSubPixelWindow() {
            return this.subPixelWindow;
        }

        public void setSubPixelWindow(int i) {
            this.subPixelWindow = i;
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings2) {
        this.settings = settings2;
        this.subPixelSize = opencv_core.cvSize(settings2.subPixelWindow / 2, settings2.subPixelWindow / 2);
        this.subPixelZeroZone = opencv_core.cvSize(-1, -1);
        this.subPixelTermCriteria = opencv_core.cvTermCriteria(2, 100, 0.001d);
    }

    public IplImage getThresholdedImage() {
        return this.thresholdedImage;
    }

    private void init(IplImage iplImage) {
        if (this.tracker == null || iplImage.width() != this.width || iplImage.height() != this.height || iplImage.depth() != this.depth || iplImage.nChannels() != this.channels) {
            this.width = iplImage.width();
            this.height = iplImage.height();
            this.depth = iplImage.depth();
            int nChannels = iplImage.nChannels();
            this.channels = nChannels;
            if (this.depth != 8 || nChannels > 1) {
                this.tempImage = IplImage.create(this.width, this.height, 8, 1);
            }
            if (this.depth != 8 && this.channels > 1) {
                this.tempImage2 = IplImage.create(this.width, this.height, 8, 3);
            }
            this.sumImage = IplImage.create(this.width + 1, this.height + 1, 64, 1);
            this.sqSumImage = IplImage.create(this.width + 1, this.height + 1, 64, 1);
            this.thresholdedImage = IplImage.create(this.width, this.height, 8, 1);
            MultiTracker multiTracker = new MultiTracker(this.thresholdedImage.widthStep(), this.thresholdedImage.height());
            this.tracker = multiTracker;
            multiTracker.setPixelFormat(7);
            this.tracker.setBorderWidth(0.125f);
            this.tracker.setUndistortionMode(0);
            this.tracker.setMarkerMode(2);
            this.tracker.setImageProcessingMode(1);
        }
    }

    public Marker[] detect(IplImage iplImage, boolean z) {
        IplImage iplImage2;
        int i;
        ARMarkerInfo aRMarkerInfo;
        int i2;
        IplImage iplImage3;
        init(iplImage);
        int i3 = this.depth;
        int i4 = 11;
        int i5 = 8;
        int i6 = 1;
        if (i3 != 8 && this.channels > 1) {
            opencv_core.cvConvertScale(iplImage, this.tempImage2, 255.0d / iplImage.highValue(), 0.0d);
            IplImage iplImage4 = this.tempImage2;
            IplImage iplImage5 = this.tempImage;
            if (this.channels <= 3) {
                i4 = 6;
            }
            opencv_imgproc.cvCvtColor(iplImage4, iplImage5, i4);
            iplImage2 = this.tempImage;
        } else if (i3 != 8) {
            opencv_core.cvConvertScale(iplImage, this.tempImage, 255.0d / iplImage.highValue(), 0.0d);
            iplImage2 = this.tempImage;
        } else {
            int i7 = this.channels;
            if (i7 > 1) {
                IplImage iplImage6 = this.tempImage;
                if (i7 > 3) {
                    iplImage3 = iplImage;
                } else {
                    iplImage3 = iplImage;
                    i4 = 6;
                }
                opencv_imgproc.cvCvtColor(iplImage3, iplImage6, i4);
                iplImage2 = this.tempImage;
            } else {
                iplImage2 = iplImage;
            }
        }
        IplImage iplImage7 = this.sumImage;
        IplImage iplImage8 = this.sqSumImage;
        IplImage iplImage9 = this.thresholdedImage;
        int i8 = this.settings.thresholdWindowMax;
        int i9 = this.settings.thresholdWindowMin;
        double d = this.settings.thresholdVarMultiplier;
        Settings settings2 = this.settings;
        JavaCV.adaptiveThreshold(iplImage2, iplImage7, iplImage8, iplImage9, z, i8, i9, d, z ? settings2.thresholdKWhiteMarkers : settings2.thresholdKBlackMarkers);
        ARMarkerInfo aRMarkerInfo2 = new ARMarkerInfo((Pointer) null);
        this.tracker.arDetectMarkerLite(this.thresholdedImage.imageData(), 128, aRMarkerInfo2, this.markerNum);
        int i10 = this.markerNum.get(0);
        Marker[] markerArr = new Marker[i10];
        char c = 0;
        int i11 = 0;
        int i12 = 0;
        while (i12 < i10 && !aRMarkerInfo2.isNull()) {
            aRMarkerInfo2.position((long) i12);
            int id = aRMarkerInfo2.id();
            if (id >= 0) {
                int dir = aRMarkerInfo2.dir();
                float cf = aRMarkerInfo2.cf();
                float[] fArr = new float[i5];
                aRMarkerInfo2.vertex().get(fArr);
                float f = (float) ((this.settings.subPixelWindow / 2) + i6);
                if (fArr[c] - f >= 0.0f) {
                    int i13 = this.width;
                    if (fArr[c] + f < ((float) i13) && fArr[i6] - f >= 0.0f) {
                        int i14 = this.height;
                        if (fArr[i6] + f < ((float) i14) && fArr[2] - f >= 0.0f && fArr[2] + f < ((float) i13) && fArr[3] - f >= 0.0f && fArr[3] + f < ((float) i14) && fArr[4] - f >= 0.0f && fArr[4] + f < ((float) i13) && fArr[5] - f >= 0.0f && fArr[5] + f < ((float) i14) && fArr[6] - f >= 0.0f && fArr[6] + f < ((float) i13) && fArr[7] - f >= 0.0f && fArr[7] + f < ((float) i14)) {
                            this.points.getFloatBuffer().put(fArr);
                            CvBox2D cvMinAreaRect2 = opencv_imgproc.cvMinAreaRect2(this.points, this.memory);
                            float width2 = cvMinAreaRect2.size().width();
                            float height2 = cvMinAreaRect2.size().height();
                            opencv_core.cvClearMemStorage(this.memory);
                            if (width2 > 0.0f && height2 > 0.0f) {
                                float f2 = width2 / height2;
                                if (((double) f2) >= 0.1d && f2 <= 10.0f) {
                                    int i15 = 0;
                                    for (int i16 = 4; i15 < i16; i16 = 4) {
                                        int i17 = i15 * 2;
                                        this.corners.position((long) i15).put((double) fArr[i17], (double) fArr[i17 + 1]);
                                        i15++;
                                        aRMarkerInfo2 = aRMarkerInfo2;
                                        i10 = i10;
                                    }
                                    aRMarkerInfo = aRMarkerInfo2;
                                    i = i10;
                                    CvPoint2D32f position = this.corners.position(0);
                                    int i18 = id;
                                    CvSize cvSize = this.subPixelSize;
                                    int i19 = i11;
                                    i2 = i12;
                                    opencv_imgproc.cvFindCornerSubPix((CvArr) iplImage2, position, 4, cvSize, this.subPixelZeroZone, this.subPixelTermCriteria);
                                    long j = (long) ((4 - dir) % 4);
                                    long j2 = (long) ((5 - dir) % 4);
                                    long j3 = (long) ((6 - dir) % 4);
                                    long j4 = (long) ((7 - dir) % 4);
                                    double[] dArr = {(double) this.corners.position(j).x(), (double) this.corners.position(j).y(), (double) this.corners.position(j2).x(), (double) this.corners.position(j2).y(), (double) this.corners.position(j3).x(), (double) this.corners.position(j3).y(), (double) this.corners.position(j4).x(), (double) this.corners.position(j4).y()};
                                    i11 = i19 + 1;
                                    markerArr[i19] = new Marker(i18, dArr, (double) cf);
                                    i12 = i2 + 1;
                                    aRMarkerInfo2 = aRMarkerInfo;
                                    i10 = i;
                                    i5 = 8;
                                    i6 = 1;
                                    c = 0;
                                }
                            }
                        }
                    }
                }
            }
            aRMarkerInfo = aRMarkerInfo2;
            i = i10;
            i2 = i12;
            i11 = i11;
            i12 = i2 + 1;
            aRMarkerInfo2 = aRMarkerInfo;
            i10 = i;
            i5 = 8;
            i6 = 1;
            c = 0;
        }
        return (Marker[]) Arrays.copyOf(markerArr, i11);
    }

    public void draw(IplImage iplImage, Marker[] markerArr) {
        Marker[] markerArr2 = markerArr;
        int length = markerArr2.length;
        char c = 0;
        int i = 0;
        while (i < length) {
            Marker marker = markerArr2[i];
            int[] iArr = new int[8];
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < 4; i4++) {
                int i5 = i4 * 2;
                int round = (int) Math.round(marker.corners[i5] * 65536.0d);
                int i6 = i5 + 1;
                int round2 = (int) Math.round(marker.corners[i6] * 65536.0d);
                iArr[i5] = round;
                iArr[i6] = round2;
                i2 += round;
                i3 += round2;
            }
            int i7 = i2 / 4;
            int i8 = i3 / 4;
            int[] iArr2 = new int[1];
            iArr2[c] = 4;
            opencv_imgproc.cvPolyLine((CvArr) iplImage, iArr, iArr2, 1, 1, opencv_core.CV_RGB(0.0d, 0.0d, iplImage.highValue()), 1, 16, 16);
            String num = Integer.toString(marker.id);
            opencv_imgproc.cvGetTextSize(num, this.font, this.textSize, new int[1]);
            int i9 = i8;
            IplImage iplImage2 = iplImage;
            opencv_imgproc.cvRectangle((CvArr) iplImage2, new int[]{i7 - ((((this.textSize.width() * 3) / 2) << 16) / 2), ((((this.textSize.height() * 3) / 2) << 16) / 2) + i9}, new int[]{((((this.textSize.width() * 3) / 2) << 16) / 2) + i7, i9 - ((((this.textSize.height() * 3) / 2) << 16) / 2)}, opencv_core.CV_RGB(0.0d, iplImage.highValue(), 0.0d), -1, 16, 16);
            IplImage iplImage3 = iplImage;
            opencv_imgproc.cvPutText((CvArr) iplImage3, num, new int[]{(int) Math.round((((double) i7) / 65536.0d) - ((double) (this.textSize.width() / 2))), ((int) Math.round((((double) i9) / 65536.0d) + ((double) (this.textSize.height() / 2)))) + 1}, this.font, CvScalar.BLACK);
            i++;
            c = 0;
        }
    }
}
