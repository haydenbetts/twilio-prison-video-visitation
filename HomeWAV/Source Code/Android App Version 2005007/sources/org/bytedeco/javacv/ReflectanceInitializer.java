package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.logging.Logger;
import org.bytedeco.javacv.GNImageAligner;
import org.bytedeco.javacv.ProCamTransformer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ReflectanceInitializer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ThreadLocal<CvMat> mat3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> mat3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> mat4x4 = CvMat.createThreadLocal(4, 4);
    private GNImageAligner.Settings alignerSettings;
    private CameraDevice cameraDevice;
    private ProjectorDevice projectorDevice;
    private IplImage[] projectorImages;
    private double reflectanceMin;
    private int smoothingSize;

    public ReflectanceInitializer(CameraDevice cameraDevice2, ProjectorDevice projectorDevice2, int i, GNImageAligner.Settings settings) {
        this(cameraDevice2, projectorDevice2, i, settings, 51, 0.01d);
    }

    public ReflectanceInitializer(CameraDevice cameraDevice2, ProjectorDevice projectorDevice2, int i, GNImageAligner.Settings settings, int i2, double d) {
        this.alignerSettings = settings;
        this.smoothingSize = i2;
        this.reflectanceMin = d;
        this.cameraDevice = cameraDevice2;
        this.projectorDevice = projectorDevice2;
        this.projectorImages = new IplImage[3];
        int i3 = 0;
        while (true) {
            IplImage[] iplImageArr = this.projectorImages;
            if (i3 < iplImageArr.length) {
                iplImageArr[i3] = IplImage.create(projectorDevice2.imageWidth, projectorDevice2.imageHeight, 32, i);
                i3++;
            } else {
                opencv_core.cvSetZero(iplImageArr[0]);
                opencv_core.cvSet(this.projectorImages[1], CvScalar.ONE);
                CvMat cvMat = mat3x3.get();
                projectorDevice2.getRectifyingHomography(cameraDevice2, cvMat);
                JavaCV.fractalTriangleWave(this.projectorImages[2], cvMat);
                return;
            }
        }
    }

    public IplImage[] getProjectorImages() {
        return this.projectorImages;
    }

    public IplImage initializeReflectance(IplImage[] iplImageArr, IplImage iplImage, double[] dArr, double[] dArr2) {
        CvMat cvMat;
        FloatBuffer floatBuffer;
        IplImage iplImage2;
        FloatBuffer floatBuffer2;
        IplImage iplImage3 = iplImage;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int width = iplImageArr[0].width();
        int height = iplImageArr[0].height();
        int nChannels = iplImageArr[0].nChannels();
        IplImage create = IplImage.create(width, height, 8, 1);
        opencv_core.cvSetZero(create);
        opencv_imgproc.cvFillConvexPoly((CvArr) create, new CvPoint((long) (dArr3.length / 2)).put((byte) (16 - this.cameraDevice.getMapsPyramidLevel()), dArr3), 4, CvScalar.WHITE, 8, 16);
        IplImage iplImage4 = iplImageArr[0];
        IplImage iplImage5 = iplImageArr[1];
        opencv_core.cvCopy(iplImage5, iplImage3);
        opencv_imgproc.cvSmooth(iplImage4, iplImage4, 2, this.smoothingSize, 0, 0.0d, 0.0d);
        opencv_imgproc.cvSmooth(iplImage5, iplImage5, 2, this.smoothingSize, 0, 0.0d, 0.0d);
        opencv_core.cvSub(iplImage5, iplImage4, iplImage5, (CvArr) null);
        CvMat cvMat2 = mat3x1.get();
        cvMat2.put(1.0d, 1.0d, 1.0d);
        opencv_core.cvMatMul(this.projectorDevice.colorMixingMatrix, cvMat2, cvMat2);
        double d = 1.0d;
        if (iplImage5.nChannels() == 4) {
            cvMat = mat4x4.get();
            cvMat.put(1.0d / cvMat2.get(0), 0.0d, 0.0d, 0.0d, 0.0d, 1.0d / cvMat2.get(1), 0.0d, 0.0d, 0.0d, 0.0d, 1.0d / cvMat2.get(2), 0.0d, 0.0d, 0.0d, 0.0d, 1.0d);
        } else {
            cvMat = mat3x3.get();
            cvMat.put(1.0d / cvMat2.get(0), 0.0d, 0.0d, 0.0d, 1.0d / cvMat2.get(1), 0.0d, 0.0d, 0.0d, 1.0d / cvMat2.get(2));
        }
        opencv_core.cvTransform(iplImage5, iplImage5, cvMat, (CvMat) null);
        FloatBuffer floatBuffer3 = iplImage4.getFloatBuffer();
        FloatBuffer floatBuffer4 = iplImage5.getFloatBuffer();
        ByteBuffer byteBuffer = create.getByteBuffer();
        int[] iArr = new int[nChannels];
        int i = 0;
        int i2 = 0;
        while (i < floatBuffer3.capacity()) {
            int i3 = 0;
            while (i3 < nChannels) {
                int i4 = i + i3;
                float f = floatBuffer3.get(i4);
                float f2 = floatBuffer4.get(i4);
                float f3 = f2 == 0.0f ? 0.0f : f / f2;
                floatBuffer3.put(i4, f3);
                if (byteBuffer.get(i2) != 0) {
                    floatBuffer2 = floatBuffer4;
                    floatBuffer = floatBuffer3;
                    iplImage2 = create;
                    if (((double) f2) > this.reflectanceMin) {
                        iArr[i3] = iArr[i3] + 1;
                        int i5 = i3 + 1;
                        double[] dArr5 = dArr2;
                        dArr5[i5] = dArr5[i5] + ((double) f3);
                        i3++;
                        floatBuffer4 = floatBuffer2;
                        create = iplImage2;
                        floatBuffer3 = floatBuffer;
                    }
                } else {
                    floatBuffer = floatBuffer3;
                    iplImage2 = create;
                    floatBuffer2 = floatBuffer4;
                }
                double[] dArr6 = dArr2;
                i3++;
                floatBuffer4 = floatBuffer2;
                create = iplImage2;
                floatBuffer3 = floatBuffer;
            }
            FloatBuffer floatBuffer5 = floatBuffer3;
            IplImage iplImage6 = create;
            FloatBuffer floatBuffer6 = floatBuffer4;
            double[] dArr7 = dArr2;
            i2++;
            i += nChannels;
            create = iplImage6;
            d = 1.0d;
        }
        IplImage iplImage7 = create;
        double[] dArr8 = dArr2;
        dArr8[0] = d;
        int i6 = 0;
        while (i6 < dArr8.length - 1) {
            int i7 = i6 + 1;
            dArr8[i7] = iArr[i6] == 0 ? 0.0d : dArr8[i7] / ((double) iArr[i6]);
            i6 = i7;
        }
        opencv_core.cvAddS(iplImage4, opencv_core.cvScalar(cvMat2.get(0), cvMat2.get(1), cvMat2.get(2), 0.0d), iplImage4, (CvArr) null);
        opencv_core.cvDiv(iplImage3, iplImage4, iplImage3, 1.0d);
        IplImage iplImage8 = iplImage7;
        opencv_core.cvNot(iplImage8, iplImage8);
        opencv_imgproc.cvErode(iplImage8, iplImage8, (IplConvKernel) null, 15);
        opencv_core.cvSet(iplImage3, CvScalar.ZERO, iplImage8);
        return iplImage3;
    }

    public CvMat initializePlaneParameters(IplImage iplImage, IplImage iplImage2, double[] dArr, double[] dArr2, double[] dArr3) {
        ProCamTransformer proCamTransformer = new ProCamTransformer(dArr, this.cameraDevice, this.projectorDevice, (CvMat) null);
        boolean z = false;
        proCamTransformer.setProjectorImage(this.projectorImages[2], 0, this.alignerSettings.pyramidLevelMax);
        ProCamTransformer.Parameters createParameters = proCamTransformer.createParameters();
        int size = createParameters.size() - dArr3.length;
        int size2 = createParameters.size();
        for (int i = size; i < size2; i++) {
            createParameters.set(i, dArr3[i - size]);
        }
        GNImageAligner gNImageAligner = new GNImageAligner(proCamTransformer, createParameters, iplImage, dArr2, iplImage2, this.alignerSettings);
        double[] dArr4 = new double[(createParameters.size() + 1)];
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        while (!z && i2 < 100) {
            z = gNImageAligner.iterate(dArr4);
            i2++;
        }
        Logger logger = Logger.getLogger(ReflectanceInitializer.class.getName());
        logger.info("iteratingTime = " + (System.currentTimeMillis() - currentTimeMillis) + "  iterations = " + i2 + "  objectiveRMSE = " + ((float) gNImageAligner.getRMSE()));
        return ((ProCamTransformer.Parameters) gNImageAligner.getParameters()).getN0();
    }
}
