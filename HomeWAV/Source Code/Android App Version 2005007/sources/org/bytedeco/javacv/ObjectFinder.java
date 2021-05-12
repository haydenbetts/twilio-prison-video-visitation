package org.bytedeco.javacv;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.KeyPoint;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_features2d.AKAZE;
import org.bytedeco.opencv.opencv_flann.Index;
import org.bytedeco.opencv.opencv_flann.IndexParams;
import org.bytedeco.opencv.opencv_flann.LshIndexParams;
import org.bytedeco.opencv.opencv_flann.SearchParams;

public class ObjectFinder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int[] bits = new int[256];
    static final Logger logger = Logger.getLogger(ObjectFinder.class.getName());
    Mat H = null;
    Mat distsMat;
    Index flannIndex = null;
    Mat imageDescriptors = null;
    KeyPointVector imageKeypoints = null;
    IndexParams indexParams = null;
    Mat indicesMat;
    Mat mask = null;
    Mat objectDescriptors = null;
    KeyPointVector objectKeypoints = null;
    Mat pt1 = null;
    Mat pt2 = null;
    ArrayList<Integer> ptpairs = null;
    SearchParams searchParams = null;
    Settings settings;

    public ObjectFinder(IplImage iplImage) {
        Settings settings2 = new Settings();
        this.settings = settings2;
        settings2.objectImage = iplImage;
        setSettings(this.settings);
    }

    public ObjectFinder(Settings settings2) {
        setSettings(settings2);
    }

    public static class Settings extends BaseChildSettings {
        AKAZE detector = AKAZE.create();
        double distanceThreshold = 0.75d;
        int matchesMin = 4;
        IplImage objectImage = null;
        double ransacReprojThreshold = 1.0d;
        boolean useFLANN = false;

        public IplImage getObjectImage() {
            return this.objectImage;
        }

        public void setObjectImage(IplImage iplImage) {
            this.objectImage = iplImage;
        }

        public int getDescriptorType() {
            return this.detector.getDescriptorType();
        }

        public void setDescriptorType(int i) {
            this.detector.setDescriptorType(i);
        }

        public int getDescriptorSize() {
            return this.detector.getDescriptorSize();
        }

        public void setDescriptorSize(int i) {
            this.detector.setDescriptorSize(i);
        }

        public int getDescriptorChannels() {
            return this.detector.getDescriptorChannels();
        }

        public void setDescriptorChannels(int i) {
            this.detector.setDescriptorChannels(i);
        }

        public double getThreshold() {
            return this.detector.getThreshold();
        }

        public void setThreshold(double d) {
            this.detector.setThreshold(d);
        }

        public int getNOctaves() {
            return this.detector.getNOctaves();
        }

        public void setNOctaves(int i) {
            this.detector.setNOctaves(i);
        }

        public int getNOctaveLayers() {
            return this.detector.getNOctaveLayers();
        }

        public void setNOctaveLayers(int i) {
            this.detector.setNOctaveLayers(i);
        }

        public double getDistanceThreshold() {
            return this.distanceThreshold;
        }

        public void setDistanceThreshold(double d) {
            this.distanceThreshold = d;
        }

        public int getMatchesMin() {
            return this.matchesMin;
        }

        public void setMatchesMin(int i) {
            this.matchesMin = i;
        }

        public double getRansacReprojThreshold() {
            return this.ransacReprojThreshold;
        }

        public void setRansacReprojThreshold(double d) {
            this.ransacReprojThreshold = d;
        }

        public boolean isUseFLANN() {
            return this.useFLANN;
        }

        public void setUseFLANN(boolean z) {
            this.useFLANN = z;
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings2) {
        this.settings = settings2;
        this.objectKeypoints = new KeyPointVector();
        this.objectDescriptors = new Mat();
        settings2.detector.detectAndCompute(opencv_core.cvarrToMat(settings2.objectImage), new Mat(), this.objectKeypoints, this.objectDescriptors, false);
        int size = (int) this.objectKeypoints.size();
        if (settings2.useFLANN) {
            this.indicesMat = new Mat(size, 2, opencv_core.CV_32SC1);
            this.distsMat = new Mat(size, 2, opencv_core.CV_32FC1);
            this.flannIndex = new Index();
            this.indexParams = new LshIndexParams(12, 20, 2);
            SearchParams searchParams2 = new SearchParams(64, 0.0f, true);
            this.searchParams = searchParams2;
            searchParams2.deallocate(false);
        }
        this.pt1 = new Mat(size, 1, opencv_core.CV_32FC2);
        this.pt2 = new Mat(size, 1, opencv_core.CV_32FC2);
        this.mask = new Mat(size, 1, opencv_core.CV_8UC1);
        this.H = new Mat(3, 3, opencv_core.CV_64FC1);
        this.ptpairs = new ArrayList<>(this.objectDescriptors.rows() * 2);
        Logger logger2 = logger;
        logger2.info(size + " object descriptors");
    }

    static {
        for (int i = 0; i < bits.length; i++) {
            for (int i2 = i; i2 != 0; i2 >>= 1) {
                int[] iArr = bits;
                iArr[i] = iArr[i] + (i2 & 1);
            }
        }
    }

    public double[] find(IplImage iplImage) {
        if (this.objectDescriptors.rows() < this.settings.getMatchesMin()) {
            return null;
        }
        this.imageKeypoints = new KeyPointVector();
        this.imageDescriptors = new Mat();
        this.settings.detector.detectAndCompute(opencv_core.cvarrToMat(iplImage), new Mat(), this.imageKeypoints, this.imageDescriptors, false);
        if (this.imageDescriptors.rows() < this.settings.getMatchesMin()) {
            return null;
        }
        int size = (int) this.imageKeypoints.size();
        Logger logger2 = logger;
        logger2.info(size + " image descriptors");
        int width = this.settings.objectImage.width();
        double d = (double) width;
        double height = (double) this.settings.objectImage.height();
        return locatePlanarObject(this.objectKeypoints, this.objectDescriptors, this.imageKeypoints, this.imageDescriptors, new double[]{0.0d, 0.0d, d, 0.0d, d, height, 0.0d, height});
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:4:0x001b, LOOP_START, PHI: r0 
      PHI: (r0v1 int) = (r0v0 int), (r0v3 int) binds: [B:0:0x0000, B:4:0x001b] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareDescriptors(java.nio.ByteBuffer r5, java.nio.ByteBuffer r6, int r7) {
        /*
            r4 = this;
            r0 = 0
        L_0x0001:
            int r1 = r5.position()
            int r2 = r5.limit()
            if (r1 >= r2) goto L_0x001d
            int[] r1 = bits
            byte r2 = r5.get()
            byte r3 = r6.get()
            r2 = r2 ^ r3
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1[r2]
            int r0 = r0 + r1
            if (r0 <= r7) goto L_0x0001
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.ObjectFinder.compareDescriptors(java.nio.ByteBuffer, java.nio.ByteBuffer, int):int");
    }

    /* access modifiers changed from: package-private */
    public int naiveNearestNeighbor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int limit = byteBuffer.limit() - byteBuffer.position();
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        int i4 = -1;
        while (true) {
            int i5 = i3 * limit;
            if (i5 >= byteBuffer2.capacity()) {
                break;
            }
            int i6 = i3 + 1;
            int compareDescriptors = compareDescriptors((ByteBuffer) byteBuffer.reset(), (ByteBuffer) byteBuffer2.position(i5).limit(i6 * limit), i2);
            if (compareDescriptors < i) {
                i2 = i;
                i4 = i3;
                i = compareDescriptors;
            } else if (compareDescriptors < i2) {
                i2 = compareDescriptors;
            }
            i3 = i6;
        }
        if (((double) i) < this.settings.distanceThreshold * ((double) i2)) {
            return i4;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void findPairs(Mat mat, Mat mat2) {
        int cols = mat2.cols();
        ByteBuffer byteBuffer = (ByteBuffer) mat.createBuffer();
        ByteBuffer byteBuffer2 = (ByteBuffer) mat2.createBuffer();
        int i = 0;
        while (true) {
            int i2 = i * cols;
            if (i2 < byteBuffer.capacity()) {
                int i3 = i + 1;
                int naiveNearestNeighbor = naiveNearestNeighbor((ByteBuffer) byteBuffer.position(i2).limit(i3 * cols).mark(), byteBuffer2);
                if (naiveNearestNeighbor >= 0) {
                    this.ptpairs.add(Integer.valueOf(i));
                    this.ptpairs.add(Integer.valueOf(naiveNearestNeighbor));
                }
                i = i3;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flannFindPairs(Mat mat, Mat mat2) {
        int rows = mat.rows();
        this.flannIndex.build(mat2, this.indexParams, 9);
        this.flannIndex.knnSearch(mat, this.indicesMat, this.distsMat, 2, this.searchParams);
        IntBuffer intBuffer = (IntBuffer) this.indicesMat.createBuffer();
        IntBuffer intBuffer2 = (IntBuffer) this.distsMat.createBuffer();
        for (int i = 0; i < rows; i++) {
            int i2 = i * 2;
            if (((double) intBuffer2.get(i2)) < this.settings.distanceThreshold * ((double) intBuffer2.get(i2 + 1))) {
                this.ptpairs.add(Integer.valueOf(i));
                this.ptpairs.add(Integer.valueOf(intBuffer.get(i2)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public double[] locatePlanarObject(KeyPointVector keyPointVector, Mat mat, KeyPointVector keyPointVector2, Mat mat2, double[] dArr) {
        Mat mat3 = mat;
        Mat mat4 = mat2;
        double[] dArr2 = dArr;
        this.ptpairs.clear();
        if (this.settings.useFLANN) {
            flannFindPairs(mat3, mat4);
        } else {
            findPairs(mat3, mat4);
        }
        int size = this.ptpairs.size() / 2;
        Logger logger2 = logger;
        logger2.info(size + " matching pairs found");
        if (size < this.settings.matchesMin) {
            return null;
        }
        long j = (long) size;
        this.pt1.resize(j);
        this.pt2.resize(j);
        this.mask.resize(j);
        FloatBuffer floatBuffer = (FloatBuffer) this.pt1.createBuffer();
        FloatBuffer floatBuffer2 = (FloatBuffer) this.pt2.createBuffer();
        for (int i = 0; i < size; i++) {
            int i2 = i * 2;
            Point2f pt = keyPointVector.get((long) this.ptpairs.get(i2).intValue()).pt();
            floatBuffer.put(i2, pt.x());
            int i3 = i2 + 1;
            floatBuffer.put(i3, pt.y());
            Point2f pt3 = keyPointVector2.get((long) this.ptpairs.get(i3).intValue()).pt();
            floatBuffer2.put(i2, pt3.x());
            floatBuffer2.put(i3, pt3.y());
        }
        Mat findHomography = opencv_calib3d.findHomography(this.pt1, this.pt2, 8, this.settings.ransacReprojThreshold, this.mask, 2000, 0.995d);
        this.H = findHomography;
        if (findHomography.empty() || opencv_core.countNonZero(this.mask) < this.settings.matchesMin) {
            return null;
        }
        double[] dArr3 = (double[]) this.H.createIndexer(false).array();
        double[] dArr4 = new double[dArr2.length];
        for (int i4 = 0; i4 < dArr2.length / 2; i4++) {
            int i5 = i4 * 2;
            double d = dArr2[i5];
            int i6 = i5 + 1;
            double d2 = dArr2[i6];
            double d3 = 1.0d / (((dArr3[6] * d) + (dArr3[7] * d2)) + dArr3[8]);
            dArr4[i5] = ((dArr3[0] * d) + (dArr3[1] * d2) + dArr3[2]) * d3;
            dArr4[i6] = ((dArr3[3] * d) + (dArr3[4] * d2) + dArr3[5]) * d3;
        }
        return dArr4;
    }

    public static void main(String[] strArr) throws Exception {
        String[] strArr2 = strArr;
        int i = 0;
        String str = strArr2.length == 2 ? strArr2[0] : "/usr/local/share/OpenCV/samples/c/box.png";
        String str2 = strArr2.length == 2 ? strArr2[1] : "/usr/local/share/OpenCV/samples/c/box_in_scene.png";
        IplImage cvLoadImage = opencv_imgcodecs.cvLoadImage(str, 0);
        IplImage cvLoadImage2 = opencv_imgcodecs.cvLoadImage(str2, 0);
        if (cvLoadImage == null || cvLoadImage2 == null) {
            PrintStream printStream = System.err;
            printStream.println("Can not load " + str + " and/or " + str2);
            System.exit(-1);
        }
        IplImage create = IplImage.create(cvLoadImage.width(), cvLoadImage.height(), 8, 3);
        opencv_imgproc.cvCvtColor(cvLoadImage, create, 8);
        IplImage create2 = IplImage.create(cvLoadImage2.width(), cvLoadImage.height() + cvLoadImage2.height(), 8, 1);
        opencv_core.cvSetImageROI(create2, opencv_core.cvRect(0, 0, cvLoadImage.width(), cvLoadImage.height()));
        opencv_core.cvCopy(cvLoadImage, create2);
        opencv_core.cvSetImageROI(create2, opencv_core.cvRect(0, cvLoadImage.height(), create2.width(), create2.height()));
        opencv_core.cvCopy(cvLoadImage2, create2);
        opencv_core.cvResetImageROI(create2);
        Settings settings2 = new Settings();
        settings2.objectImage = cvLoadImage;
        settings2.useFLANN = true;
        settings2.ransacReprojThreshold = 5.0d;
        ObjectFinder objectFinder = new ObjectFinder(settings2);
        long currentTimeMillis = System.currentTimeMillis();
        double[] find = objectFinder.find(cvLoadImage2);
        PrintStream printStream2 = System.out;
        printStream2.println("Finding time = " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        if (find != null) {
            int i2 = 0;
            while (i2 < 4) {
                int i3 = i2 + 1;
                int i4 = i2 * 2;
                int i5 = (i3 % 4) * 2;
                opencv_imgproc.line(opencv_core.cvarrToMat(create2), new Point((int) Math.round(find[i4]), ((int) Math.round(find[i4 + 1])) + cvLoadImage.height()), new Point((int) Math.round(find[i5]), ((int) Math.round(find[i5 + 1])) + cvLoadImage.height()), Scalar.WHITE, 1, 8, 0);
                i2 = i3;
            }
        }
        for (int i6 = 0; i6 < objectFinder.ptpairs.size(); i6 += 2) {
            Point2f pt = objectFinder.objectKeypoints.get((long) objectFinder.ptpairs.get(i6).intValue()).pt();
            Point2f pt3 = objectFinder.imageKeypoints.get((long) objectFinder.ptpairs.get(i6 + 1).intValue()).pt();
            opencv_imgproc.line(opencv_core.cvarrToMat(create2), new Point(Math.round(pt.x()), Math.round(pt.y())), new Point(Math.round(pt3.x()), Math.round(pt3.y() + ((float) cvLoadImage.height()))), Scalar.WHITE, 1, 8, 0);
        }
        CanvasFrame canvasFrame = new CanvasFrame("Object");
        CanvasFrame canvasFrame2 = new CanvasFrame("Object Correspond");
        OpenCVFrameConverter.ToIplImage toIplImage = new OpenCVFrameConverter.ToIplImage();
        canvasFrame2.showImage(toIplImage.convert(create2));
        while (true) {
            long j = (long) i;
            if (j < objectFinder.objectKeypoints.size()) {
                KeyPoint keyPoint = objectFinder.objectKeypoints.get(j);
                opencv_imgproc.circle(opencv_core.cvarrToMat(create), new Point(Math.round(keyPoint.pt().x()), Math.round(keyPoint.pt().y())), Math.round(keyPoint.size() / 2.0f), Scalar.RED, 1, 8, 0);
                i++;
            } else {
                canvasFrame.showImage(toIplImage.convert(create));
                canvasFrame.waitKey();
                canvasFrame.dispose();
                canvasFrame2.dispose();
                return;
            }
        }
    }
}
