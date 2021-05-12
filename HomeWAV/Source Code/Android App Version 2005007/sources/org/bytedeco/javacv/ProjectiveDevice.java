package org.bytedeco.javacv;

import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.Arrays;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;

public class ProjectiveDevice {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ThreadLocal<CvMat> B4x3 = CvMat.createThreadLocal(4, 3);
    private static ThreadLocal<CvMat> P13x4 = CvMat.createThreadLocal(3, 4);
    private static ThreadLocal<CvMat> P23x4 = CvMat.createThreadLocal(3, 4);
    private static ThreadLocal<CvMat> R13x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> R23x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> a4x1 = CvMat.createThreadLocal(4, 1);
    private static ThreadLocal<CvMat> relativeR3x3 = CvMat.createThreadLocal(3, 3);
    private static ThreadLocal<CvMat> relativeT3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> t3x1 = CvMat.createThreadLocal(3, 1);
    private static ThreadLocal<CvMat> temp3x3 = CvMat.createThreadLocal(3, 3);
    public CvMat E;
    public CvMat F;
    public CvMat R;
    public CvMat T;
    public CvMat additiveLight;
    public double avgColorErr;
    public double avgEpipolarErr;
    public double avgReprojErr;
    public CvMat cameraMatrix;
    public CvMat colorMixingMatrix;
    public String colorOrder;
    public double colorR2;
    private IplImage[] distortMaps1;
    private IplImage[] distortMaps2;
    public CvMat distortionCoeffs;
    public CvMat extrParams;
    private boolean fixedPointMaps;
    public int imageHeight;
    public int imageWidth;
    private int mapsPyramidLevel;
    public double maxEpipolarErr;
    public double maxReprojErr;
    public CvMat reprojErrs;
    private Settings settings;
    private IplImage tempImage;
    private IplImage[] undistortMaps1;
    private IplImage[] undistortMaps2;

    public ProjectiveDevice(String str) {
        this.imageWidth = 0;
        this.imageHeight = 0;
        this.cameraMatrix = null;
        this.distortionCoeffs = null;
        this.extrParams = null;
        this.reprojErrs = null;
        this.R = null;
        this.T = null;
        this.E = null;
        this.F = null;
        this.colorOrder = "BGR";
        this.colorMixingMatrix = null;
        this.additiveLight = null;
        this.colorR2 = 1.0d;
        this.fixedPointMaps = false;
        this.mapsPyramidLevel = 0;
        this.undistortMaps1 = new IplImage[]{null};
        this.undistortMaps2 = new IplImage[]{null};
        this.distortMaps1 = new IplImage[]{null};
        this.distortMaps2 = new IplImage[]{null};
        this.tempImage = null;
        Settings settings2 = new Settings();
        settings2.name = str;
        setSettings(settings2);
    }

    public ProjectiveDevice(String str, File file) throws Exception {
        this(str);
        readParameters(file);
    }

    public ProjectiveDevice(String str, String str2) throws Exception {
        this(str);
        readParameters(str2);
    }

    public ProjectiveDevice(String str, FileStorage fileStorage) throws Exception {
        this(str);
        readParameters(fileStorage);
    }

    public ProjectiveDevice(Settings settings2) throws Exception {
        this.imageWidth = 0;
        this.imageHeight = 0;
        this.cameraMatrix = null;
        this.distortionCoeffs = null;
        this.extrParams = null;
        this.reprojErrs = null;
        this.R = null;
        this.T = null;
        this.E = null;
        this.F = null;
        this.colorOrder = "BGR";
        this.colorMixingMatrix = null;
        this.additiveLight = null;
        this.colorR2 = 1.0d;
        this.fixedPointMaps = false;
        this.mapsPyramidLevel = 0;
        this.undistortMaps1 = new IplImage[]{null};
        this.undistortMaps2 = new IplImage[]{null};
        this.distortMaps1 = new IplImage[]{null};
        this.distortMaps2 = new IplImage[]{null};
        this.tempImage = null;
        setSettings(settings2);
        if (settings2 instanceof CalibratedSettings) {
            readParameters(((CalibratedSettings) settings2).parametersFile);
        }
    }

    public static class Settings extends BaseChildSettings {
        String name = "";
        double responseGamma = 0.0d;

        public Settings() {
        }

        public Settings(Settings settings) {
            this.name = settings.name;
            this.responseGamma = settings.responseGamma;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            String str2 = this.name;
            this.name = str;
            firePropertyChange("name", str2, str);
        }

        public double getResponseGamma() {
            return this.responseGamma;
        }

        public void setResponseGamma(double d) {
            this.responseGamma = d;
        }
    }

    public static class CalibrationSettings extends Settings {
        int flags = 14720;
        double initAspectRatio = 1.0d;

        public CalibrationSettings() {
        }

        public CalibrationSettings(CalibrationSettings calibrationSettings) {
            super(calibrationSettings);
            this.initAspectRatio = calibrationSettings.initAspectRatio;
            this.flags = calibrationSettings.flags;
        }

        public double getInitAspectRatio() {
            return this.initAspectRatio;
        }

        public void setInitAspectRatio(double d) {
            this.initAspectRatio = d;
        }

        public boolean isUseIntrinsicGuess() {
            return (this.flags & 1) != 0;
        }

        public void setUseIntrinsicGuess(boolean z) {
            if (z) {
                this.flags |= 1;
            } else {
                this.flags &= -2;
            }
        }

        public boolean isFixAspectRatio() {
            return (this.flags & 2) != 0;
        }

        public void setFixAspectRatio(boolean z) {
            if (z) {
                this.flags |= 2;
            } else {
                this.flags &= -3;
            }
        }

        public boolean isFixPrincipalPoint() {
            return (this.flags & 4) != 0;
        }

        public void setFixPrincipalPoint(boolean z) {
            if (z) {
                this.flags |= 4;
            } else {
                this.flags &= -5;
            }
        }

        public boolean isZeroTangentDist() {
            return (this.flags & 8) != 0;
        }

        public void setZeroTangentDist(boolean z) {
            if (z) {
                this.flags |= 8;
            } else {
                this.flags &= -9;
            }
        }

        public boolean isFixFocalLength() {
            return (this.flags & 16) != 0;
        }

        public void setFixFocalLength(boolean z) {
            if (z) {
                this.flags |= 16;
            } else {
                this.flags &= -17;
            }
        }

        public boolean isFixK1() {
            return (this.flags & 32) != 0;
        }

        public void setFixK1(boolean z) {
            if (z) {
                this.flags |= 32;
            } else {
                this.flags &= -33;
            }
        }

        public boolean isFixK2() {
            return (this.flags & 64) != 0;
        }

        public void setFixK2(boolean z) {
            if (z) {
                this.flags |= 64;
            } else {
                this.flags &= -65;
            }
        }

        public boolean isFixK3() {
            return (this.flags & 128) != 0;
        }

        public void setFixK3(boolean z) {
            if (z) {
                this.flags |= 128;
            } else {
                this.flags &= -129;
            }
        }

        public boolean isFixK4() {
            return (this.flags & 2048) != 0;
        }

        public void setFixK4(boolean z) {
            if (z) {
                this.flags |= 2048;
            } else {
                this.flags &= -2049;
            }
        }

        public boolean isFixK5() {
            return (this.flags & 4096) != 0;
        }

        public void setFixK5(boolean z) {
            if (z) {
                this.flags |= 4096;
            } else {
                this.flags &= -4097;
            }
        }

        public boolean isFixK6() {
            return (this.flags & 8192) != 0;
        }

        public void setFixK6(boolean z) {
            if (z) {
                this.flags |= 8192;
            } else {
                this.flags &= -8193;
            }
        }

        public boolean isRationalModel() {
            return (this.flags & 16384) != 0;
        }

        public void setRationalModel(boolean z) {
            if (z) {
                this.flags |= 16384;
            } else {
                this.flags &= -16385;
            }
        }

        public boolean isStereoFixIntrinsic() {
            return (this.flags & 256) != 0;
        }

        public void setStereoFixIntrinsic(boolean z) {
            if (z) {
                this.flags |= 256;
            } else {
                this.flags &= -257;
            }
        }

        public boolean isStereoSameFocalLength() {
            return (this.flags & 512) != 0;
        }

        public void setStereoSameFocalLength(boolean z) {
            if (z) {
                this.flags |= 512;
            } else {
                this.flags &= -513;
            }
        }
    }

    public static class CalibratedSettings extends Settings {
        File parametersFile = new File("calibration.yaml");

        public CalibratedSettings() {
        }

        public CalibratedSettings(CalibratedSettings calibratedSettings) {
            super(calibratedSettings);
            this.parametersFile = calibratedSettings.parametersFile;
        }

        public File getParametersFile() {
            return this.parametersFile;
        }

        public void setParametersFile(File file) {
            this.parametersFile = file;
        }

        public String getParametersFilename() {
            File file = this.parametersFile;
            return file == null ? "" : file.getPath();
        }

        public void setParametersFilename(String str) {
            this.parametersFile = (str == null || str.length() == 0) ? null : new File(str);
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings2) {
        this.settings = settings2;
    }

    public void rescale(int i, int i2) {
        CvMat cvMat;
        int i3 = this.imageWidth;
        if ((i != i3 || i2 != this.imageHeight) && (cvMat = this.cameraMatrix) != null) {
            double d = ((double) i) / ((double) i3);
            double d2 = ((double) i2) / ((double) this.imageHeight);
            cvMat.put(0, cvMat.get(0) * d);
            CvMat cvMat2 = this.cameraMatrix;
            cvMat2.put(1, cvMat2.get(1) * d);
            CvMat cvMat3 = this.cameraMatrix;
            cvMat3.put(2, d * cvMat3.get(2));
            CvMat cvMat4 = this.cameraMatrix;
            cvMat4.put(3, cvMat4.get(3) * d2);
            CvMat cvMat5 = this.cameraMatrix;
            cvMat5.put(4, cvMat5.get(4) * d2);
            CvMat cvMat6 = this.cameraMatrix;
            cvMat6.put(5, d2 * cvMat6.get(5));
            this.imageWidth = i;
            this.imageHeight = i2;
            int i4 = this.mapsPyramidLevel;
            IplImage[] iplImageArr = this.undistortMaps1;
            IplImage[] iplImageArr2 = this.undistortMaps2;
            IplImage[] iplImageArr3 = this.distortMaps1;
            this.distortMaps2[i4] = null;
            iplImageArr3[i4] = null;
            iplImageArr2[i4] = null;
            iplImageArr[i4] = null;
        }
    }

    public int[] getRGBColorOrder() {
        int[] iArr = new int[3];
        for (int i = 0; i < 3; i++) {
            char upperCase = Character.toUpperCase(this.colorOrder.charAt(i));
            if (upperCase == 'B') {
                iArr[i] = 2;
            } else if (upperCase == 'G') {
                iArr[i] = 1;
            } else if (upperCase == 'R') {
                iArr[i] = 0;
            }
        }
        return iArr;
    }

    public static double[] undistort(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int i = 0;
        double d = dArr4[0];
        double d2 = dArr4[1];
        double d3 = dArr4.length > 4 ? dArr4[4] : 0.0d;
        if (dArr4.length > 5) {
            double d4 = dArr4[5];
        }
        if (dArr4.length > 6) {
            double d5 = dArr4[6];
        }
        if (dArr4.length > 7) {
            double d6 = dArr4[7];
        }
        double d7 = dArr4[2];
        double d8 = dArr4[3];
        double[] dArr5 = (double[]) dArr.clone();
        int i2 = 0;
        for (int i3 = 2; i2 < dArr3.length / i3; i3 = 2) {
            int i4 = i2 * 2;
            double d9 = dArr5[i4];
            int i5 = i4 + 1;
            double d10 = dArr5[i5];
            double d11 = dArr3[i4];
            double d12 = dArr3[i5];
            while (i < 20) {
                double d13 = (d9 * d9) + (d10 * d10);
                double d14 = (d * d13) + 1.0d + (d2 * d13 * d13) + (d3 * d13 * d13 * d13);
                d9 = (d11 - ((((d7 * 2.0d) * d9) * d10) + ((d13 + ((d9 * 2.0d) * d9)) * d8))) / d14;
                d10 = (d12 - (((d13 + ((d10 * 2.0d) * d10)) * d7) + (((2.0d * d8) * d9) * d10))) / d14;
                i++;
            }
            dArr5[i4] = d9;
            dArr5[i5] = d10;
            i2++;
            i = 0;
        }
        return dArr5;
    }

    public double[] undistort(double... dArr) {
        return unnormalize(undistort(normalize(dArr, this.cameraMatrix), this.distortionCoeffs.get()), this.cameraMatrix);
    }

    public static double[] distort(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        double d = dArr4[0];
        double d2 = dArr4[1];
        double d3 = dArr4.length > 4 ? dArr4[4] : 0.0d;
        if (dArr4.length > 5) {
            double d4 = dArr4[5];
        }
        if (dArr4.length > 6) {
            double d5 = dArr4[6];
        }
        if (dArr4.length > 7) {
            double d6 = dArr4[7];
        }
        double d7 = dArr4[2];
        double d8 = dArr4[3];
        double[] dArr5 = (double[]) dArr.clone();
        for (int i = 0; i < dArr3.length / 2; i++) {
            int i2 = i * 2;
            double d9 = dArr3[i2];
            int i3 = i2 + 1;
            double d10 = dArr3[i3];
            double d11 = (d9 * d9) + (d10 * d10);
            double d12 = (d * d11) + 1.0d + (d2 * d11 * d11) + (d3 * d11 * d11 * d11);
            dArr5[i2] = (d9 * d12) + (d7 * 2.0d * d9 * d10) + ((d11 + (d9 * 2.0d * d9)) * d8);
            dArr5[i3] = (d10 * d12) + ((d11 + (d10 * 2.0d * d10)) * d7) + (2.0d * d8 * d9 * d10);
        }
        return dArr5;
    }

    public double[] distort(double... dArr) {
        return unnormalize(distort(normalize(dArr, this.cameraMatrix), this.distortionCoeffs.get()), this.cameraMatrix);
    }

    public static double[] normalize(double[] dArr, CvMat cvMat) {
        double[] dArr2 = dArr;
        CvMat cvMat2 = cvMat;
        double[] dArr3 = (double[]) dArr.clone();
        double d = cvMat2.get(0) / cvMat2.get(8);
        double d2 = cvMat2.get(4) / cvMat2.get(8);
        double d3 = cvMat2.get(2) / cvMat2.get(8);
        double d4 = cvMat2.get(5) / cvMat2.get(8);
        double d5 = cvMat2.get(1) / cvMat2.get(8);
        for (int i = 0; i < dArr2.length / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            dArr3[i2] = ((dArr2[i2] - d3) / d) - (((dArr2[i3] + d4) * d5) / (d * d2));
            dArr3[i3] = (dArr2[i3] - d4) / d2;
        }
        return dArr3;
    }

    public static double[] unnormalize(double[] dArr, CvMat cvMat) {
        double[] dArr2 = dArr;
        CvMat cvMat2 = cvMat;
        double[] dArr3 = (double[]) dArr.clone();
        double d = cvMat2.get(0) / cvMat2.get(8);
        double d2 = cvMat2.get(4) / cvMat2.get(8);
        double d3 = cvMat2.get(2) / cvMat2.get(8);
        double d4 = cvMat2.get(5) / cvMat2.get(8);
        double d5 = cvMat2.get(1) / cvMat2.get(8);
        for (int i = 0; i < dArr2.length / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            dArr3[i2] = (dArr2[i2] * d) + d3 + (dArr2[i3] * d5);
            dArr3[i3] = (dArr2[i3] * d2) + d4;
        }
        return dArr3;
    }

    public boolean isFixedPointMaps() {
        return this.fixedPointMaps;
    }

    public void setFixedPointMaps(boolean z) {
        if (this.fixedPointMaps != z) {
            this.fixedPointMaps = z;
            int i = this.mapsPyramidLevel;
            IplImage[] iplImageArr = this.undistortMaps1;
            IplImage[] iplImageArr2 = this.undistortMaps2;
            IplImage[] iplImageArr3 = this.distortMaps1;
            this.distortMaps2[i] = null;
            iplImageArr3[i] = null;
            iplImageArr2[i] = null;
            iplImageArr[i] = null;
        }
    }

    public int getMapsPyramidLevel() {
        return this.mapsPyramidLevel;
    }

    public void setMapsPyramidLevel(int i) {
        if (this.mapsPyramidLevel != i) {
            this.mapsPyramidLevel = i;
            IplImage[] iplImageArr = this.undistortMaps1;
            if (i >= iplImageArr.length || i >= this.undistortMaps2.length || i >= this.distortMaps1.length || i >= this.distortMaps2.length) {
                int i2 = i + 1;
                this.undistortMaps1 = (IplImage[]) Arrays.copyOf(iplImageArr, i2);
                this.undistortMaps2 = (IplImage[]) Arrays.copyOf(this.undistortMaps2, i2);
                this.distortMaps1 = (IplImage[]) Arrays.copyOf(this.distortMaps1, i2);
                this.distortMaps2 = (IplImage[]) Arrays.copyOf(this.distortMaps2, i2);
            }
        }
    }

    private void initUndistortMaps() {
        int i = this.mapsPyramidLevel;
        IplImage[] iplImageArr = this.undistortMaps1;
        if (iplImageArr[i] == null || this.undistortMaps2[i] == null) {
            if (this.fixedPointMaps) {
                iplImageArr[i] = IplImage.create(this.imageWidth, this.imageHeight, (int) opencv_core.IPL_DEPTH_16S, 2);
                this.undistortMaps2[i] = IplImage.create(this.imageWidth, this.imageHeight, 16, 1);
            } else {
                iplImageArr[i] = IplImage.create(this.imageWidth, this.imageHeight, 32, 1);
                this.undistortMaps2[i] = IplImage.create(this.imageWidth, this.imageHeight, 32, 1);
            }
            Mat cvarrToMat = opencv_core.cvarrToMat(this.cameraMatrix);
            Mat cvarrToMat2 = opencv_core.cvarrToMat(this.undistortMaps1[i]);
            Mat mat = cvarrToMat;
            opencv_calib3d.initUndistortRectifyMap(mat, opencv_core.cvarrToMat(this.distortionCoeffs), new Mat(), cvarrToMat, cvarrToMat2.size(), cvarrToMat2.type(), cvarrToMat2, opencv_core.cvarrToMat(this.undistortMaps2[i]));
            if (this.mapsPyramidLevel > 0) {
                IplImage[] iplImageArr2 = this.undistortMaps1;
                IplImage iplImage = iplImageArr2[i];
                IplImage iplImage2 = this.undistortMaps2[i];
                int i2 = this.imageWidth >> i;
                int i3 = this.imageHeight >> i;
                iplImageArr2[i] = IplImage.create(i2, i3, iplImage.depth(), iplImage.nChannels());
                this.undistortMaps2[i] = IplImage.create(i2, i3, iplImage2.depth(), iplImage2.nChannels());
                opencv_imgproc.cvResize(iplImage, this.undistortMaps1[i], 0);
                opencv_imgproc.cvResize(iplImage2, this.undistortMaps2[i], 0);
            }
        }
    }

    public IplImage getUndistortMap1() {
        initUndistortMaps();
        return this.undistortMaps1[this.mapsPyramidLevel];
    }

    public IplImage getUndistortMap2() {
        initUndistortMaps();
        return this.undistortMaps2[this.mapsPyramidLevel];
    }

    public void undistort(IplImage iplImage, IplImage iplImage2) {
        if (iplImage != null && iplImage2 != null) {
            initUndistortMaps();
            IplImage[] iplImageArr = this.undistortMaps1;
            int i = this.mapsPyramidLevel;
            opencv_imgproc.cvRemap(iplImage, iplImage2, iplImageArr[i], this.undistortMaps2[i], 9, CvScalar.ZERO);
        }
    }

    public IplImage undistort(IplImage iplImage) {
        if (iplImage == null) {
            return null;
        }
        initUndistortMaps();
        IplImage createIfNotCompatible = IplImage.createIfNotCompatible(this.tempImage, iplImage);
        this.tempImage = createIfNotCompatible;
        opencv_core.cvResetImageROI(createIfNotCompatible);
        IplImage iplImage2 = this.tempImage;
        IplImage[] iplImageArr = this.undistortMaps1;
        int i = this.mapsPyramidLevel;
        opencv_imgproc.cvRemap(iplImage, iplImage2, iplImageArr[i], this.undistortMaps2[i], 9, CvScalar.ZERO);
        return this.tempImage;
    }

    private void initDistortMaps() {
        int i = this.mapsPyramidLevel;
        if (this.distortMaps1[i] == null || this.distortMaps2[i] == null) {
            IplImage create = IplImage.create(this.imageWidth, this.imageHeight, 32, 1);
            IplImage create2 = IplImage.create(this.imageWidth, this.imageHeight, 32, 1);
            FloatBuffer floatBuffer = create.getFloatBuffer();
            FloatBuffer floatBuffer2 = create2.getFloatBuffer();
            int width = create.width();
            int height = create.height();
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    double[] undistort = undistort((double) i3, (double) i2);
                    floatBuffer.put((float) undistort[0]);
                    floatBuffer2.put((float) undistort[1]);
                }
            }
            if (this.fixedPointMaps) {
                this.distortMaps1[i] = IplImage.create(this.imageWidth, this.imageHeight, (int) opencv_core.IPL_DEPTH_16S, 2);
                this.distortMaps2[i] = IplImage.create(this.imageWidth, this.imageHeight, 16, 1);
                opencv_imgproc.cvConvertMaps(create, create2, this.distortMaps1[i], this.distortMaps2[i]);
                create.release();
                create2.release();
            } else {
                this.distortMaps1[i] = create;
                this.distortMaps2[i] = create2;
            }
            if (this.mapsPyramidLevel > 0) {
                IplImage[] iplImageArr = this.distortMaps1;
                IplImage iplImage = iplImageArr[i];
                IplImage iplImage2 = this.distortMaps2[i];
                int i4 = this.imageWidth >> i;
                int i5 = this.imageHeight >> i;
                iplImageArr[i] = IplImage.create(i4, i5, iplImage.depth(), iplImage.nChannels());
                this.distortMaps2[i] = IplImage.create(i4, i5, iplImage2.depth(), iplImage2.nChannels());
                opencv_imgproc.cvResize(iplImage, this.distortMaps1[i], 0);
                opencv_imgproc.cvResize(iplImage2, this.distortMaps2[i], 0);
            }
        }
    }

    public IplImage getDistortMap1() {
        initDistortMaps();
        return this.distortMaps1[this.mapsPyramidLevel];
    }

    public IplImage getDistortMap2() {
        initDistortMaps();
        return this.distortMaps2[this.mapsPyramidLevel];
    }

    public void distort(IplImage iplImage, IplImage iplImage2) {
        if (iplImage != null && iplImage2 != null) {
            initDistortMaps();
            IplImage[] iplImageArr = this.distortMaps1;
            int i = this.mapsPyramidLevel;
            opencv_imgproc.cvRemap(iplImage, iplImage2, iplImageArr[i], this.distortMaps2[i], 9, CvScalar.ZERO);
        }
    }

    public IplImage distort(IplImage iplImage) {
        if (iplImage == null) {
            return null;
        }
        initDistortMaps();
        IplImage createIfNotCompatible = IplImage.createIfNotCompatible(this.tempImage, iplImage);
        this.tempImage = createIfNotCompatible;
        IplImage[] iplImageArr = this.distortMaps1;
        int i = this.mapsPyramidLevel;
        opencv_imgproc.cvRemap(iplImage, createIfNotCompatible, iplImageArr[i], this.distortMaps2[i], 9, CvScalar.ZERO);
        return this.tempImage;
    }

    public CvMat getBackProjectionMatrix(CvMat cvMat, double d, CvMat cvMat2) {
        CvMat cvMat3 = temp3x3.get();
        cvMat3.cols(1);
        cvMat3.step(cvMat3.step() / 3);
        cvMat2.rows(3);
        opencv_core.cvGEMM(this.R, this.T, -1.0d, (CvArr) null, 0.0d, cvMat3, 1);
        opencv_core.cvGEMM(cvMat3, cvMat, 1.0d, (CvArr) null, 0.0d, cvMat2, 2);
        double cvDotProduct = opencv_core.cvDotProduct(cvMat, cvMat3) + d;
        cvMat2.put(0, cvMat2.get(0) - cvDotProduct);
        cvMat2.put(4, cvMat2.get(4) - cvDotProduct);
        cvMat2.put(8, cvMat2.get(8) - cvDotProduct);
        cvMat2.rows(4);
        cvMat3.cols(3);
        cvMat3.step(cvMat3.step() * 3);
        cvMat2.put(9, cvMat.get());
        opencv_core.cvMatMul(this.cameraMatrix, this.R, cvMat3);
        opencv_core.cvInvert(cvMat3, cvMat3, 0);
        opencv_core.cvMatMul(cvMat2, cvMat3, cvMat2);
        opencv_core.cvConvertScale(cvMat2, cvMat2, 1.0d / cvMat2.get(11), 0.0d);
        return cvMat2;
    }

    public CvMat getFrontoParallelH(double[] dArr, CvMat cvMat, CvMat cvMat2) {
        double d;
        double d2;
        CvMat cvMat3 = cvMat;
        CvMat cvMat4 = cvMat2;
        CvMat cvMat5 = B4x3.get();
        CvMat cvMat6 = a4x1.get();
        CvMat cvMat7 = t3x1.get();
        double signum = Math.signum(cvMat3.get(2));
        double[] unitize = JavaCV.unitize((-signum) * cvMat3.get(1), cvMat3.get(0) * signum);
        double acos = Math.acos((signum * cvMat3.get(2)) / JavaCV.norm(cvMat.get()));
        cvMat7.put(unitize[0] * acos, acos * unitize[1], 0.0d);
        opencv_calib3d.Rodrigues(opencv_core.cvarrToMat(cvMat7), opencv_core.cvarrToMat(cvMat2), (Mat) null);
        opencv_core.cvMatMul(this.R, cvMat4, cvMat4);
        if (dArr != null) {
            double d3 = dArr[0];
            double d4 = dArr[1];
            double d5 = dArr[4];
            double d6 = dArr[5];
            double d7 = dArr[2];
            double d8 = dArr[3];
            double d9 = dArr[6] - d7;
            double d10 = dArr[7] - d8;
            double d11 = d5 - d3;
            double d12 = d6 - d4;
            double d13 = (((d4 - d8) * d9) - ((d3 - d7) * d10)) / ((d10 * d11) - (d9 * d12));
            d2 = d4 + (d13 * d12);
            d = d3 + (d11 * d13);
        } else {
            d2 = 0.0d;
            d = 0.0d;
        }
        getBackProjectionMatrix(cvMat3, -1.0d, cvMat5);
        cvMat7.put(d, d2, 1.0d);
        opencv_core.cvMatMul(cvMat5, cvMat7, cvMat6);
        cvMat4.put(2, cvMat6.get(0) / cvMat6.get(3));
        cvMat4.put(5, cvMat6.get(1) / cvMat6.get(3));
        cvMat4.put(8, cvMat6.get(2) / cvMat6.get(3));
        return cvMat4;
    }

    public CvMat getRectifyingHomography(ProjectiveDevice projectiveDevice, CvMat cvMat) {
        ProjectiveDevice projectiveDevice2 = projectiveDevice;
        CvMat cvMat2 = cvMat;
        CvMat cvMat3 = relativeR3x3.get();
        CvMat cvMat4 = relativeT3x1.get();
        opencv_core.cvGEMM(this.R, projectiveDevice2.R, 1.0d, (CvArr) null, 0.0d, cvMat3, 2);
        opencv_core.cvGEMM(cvMat3, projectiveDevice2.T, -1.0d, this.T, 1.0d, cvMat4, 0);
        CvMat cvMat5 = R13x3.get();
        CvMat cvMat6 = R23x3.get();
        Size size = r8;
        Size size2 = new Size((projectiveDevice2.imageWidth + this.imageWidth) / 2, (projectiveDevice2.imageHeight + this.imageHeight) / 2);
        Mat cvarrToMat = opencv_core.cvarrToMat(projectiveDevice2.cameraMatrix);
        Mat cvarrToMat2 = opencv_core.cvarrToMat(this.cameraMatrix);
        Mat cvarrToMat3 = opencv_core.cvarrToMat(projectiveDevice2.distortionCoeffs);
        Mat cvarrToMat4 = opencv_core.cvarrToMat(this.distortionCoeffs);
        Mat cvarrToMat5 = opencv_core.cvarrToMat(cvMat3);
        Mat cvarrToMat6 = opencv_core.cvarrToMat(cvMat4);
        Mat cvarrToMat7 = opencv_core.cvarrToMat(cvMat5);
        Mat cvarrToMat8 = opencv_core.cvarrToMat(cvMat6);
        Mat cvarrToMat9 = opencv_core.cvarrToMat(P13x4.get());
        Mat cvarrToMat10 = opencv_core.cvarrToMat(P23x4.get());
        Mat mat = r1;
        Mat mat2 = new Mat();
        Size size3 = r1;
        Size size4 = new Size();
        opencv_calib3d.stereoRectify(cvarrToMat, cvarrToMat2, cvarrToMat3, cvarrToMat4, size, cvarrToMat5, cvarrToMat6, cvarrToMat7, cvarrToMat8, cvarrToMat9, cvarrToMat10, mat, 0, -1.0d, size3, (Rect) null, (Rect) null);
        opencv_core.cvMatMul(this.cameraMatrix, cvMat6, cvMat6);
        opencv_core.cvInvert(this.cameraMatrix, cvMat5);
        opencv_core.cvMatMul(cvMat6, cvMat5, cvMat2);
        return cvMat2;
    }

    public static class Exception extends Exception {
        public Exception(String str) {
            super(str);
        }

        public Exception(String str, Throwable th) {
            super(str, th);
        }
    }

    public static ProjectiveDevice[] read(String str) throws Exception {
        int i = 0;
        FileStorage fileStorage = new FileStorage(str, 0);
        CameraDevice[] read = CameraDevice.read(fileStorage);
        ProjectorDevice[] read2 = ProjectorDevice.read(fileStorage);
        ProjectiveDevice[] projectiveDeviceArr = new ProjectiveDevice[(read.length + read2.length)];
        int length = read.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            projectiveDeviceArr[i3] = read[i2];
            i2++;
            i3++;
        }
        int length2 = read2.length;
        while (i < length2) {
            projectiveDeviceArr[i3] = read2[i];
            i++;
            i3++;
        }
        fileStorage.release();
        return projectiveDeviceArr;
    }

    public static void write(String str, ProjectiveDevice[]... projectiveDeviceArr) {
        int i = 0;
        for (ProjectiveDevice[] length : projectiveDeviceArr) {
            i += length.length;
        }
        ProjectiveDevice[] projectiveDeviceArr2 = new ProjectiveDevice[i];
        int i2 = 0;
        for (ProjectiveDevice[] projectiveDeviceArr3 : projectiveDeviceArr) {
            int length2 = projectiveDeviceArr3.length;
            int i3 = 0;
            while (i3 < length2) {
                projectiveDeviceArr2[i2] = projectiveDeviceArr3[i3];
                i3++;
                i2++;
            }
        }
        write(str, projectiveDeviceArr2);
    }

    public static void write(String str, ProjectiveDevice... projectiveDeviceArr) {
        FileStorage fileStorage = new FileStorage(str, 1);
        opencv_core.shiftLeft(opencv_core.shiftLeft(fileStorage, "Cameras"), "[");
        for (ProjectiveDevice projectiveDevice : projectiveDeviceArr) {
            if (projectiveDevice instanceof CameraDevice) {
                opencv_core.write(fileStorage, projectiveDevice.getSettings().getName());
            }
        }
        opencv_core.shiftLeft(fileStorage, "]");
        opencv_core.shiftLeft(opencv_core.shiftLeft(fileStorage, "Projectors"), "[");
        for (ProjectiveDevice projectiveDevice2 : projectiveDeviceArr) {
            if (projectiveDevice2 instanceof ProjectorDevice) {
                opencv_core.write(fileStorage, projectiveDevice2.getSettings().getName());
            }
        }
        opencv_core.shiftLeft(fileStorage, "]");
        for (ProjectiveDevice writeParameters : projectiveDeviceArr) {
            writeParameters.writeParameters(fileStorage);
        }
        fileStorage.release();
    }

    public void writeParameters(File file) {
        writeParameters(file.getAbsolutePath());
    }

    public void writeParameters(String str) {
        FileStorage fileStorage = new FileStorage(str, 1);
        writeParameters(fileStorage);
        fileStorage.release();
    }

    public void writeParameters(FileStorage fileStorage) {
        opencv_core.shiftLeft(opencv_core.shiftLeft(fileStorage, getSettings().getName()), "{");
        opencv_core.write(fileStorage, "imageWidth", this.imageWidth);
        opencv_core.write(fileStorage, "imageHeight", this.imageHeight);
        opencv_core.write(fileStorage, "responseGamma", getSettings().getResponseGamma());
        CvMat cvMat = this.cameraMatrix;
        if (cvMat != null) {
            opencv_core.write(fileStorage, "cameraMatrix", opencv_core.cvarrToMat(cvMat));
        }
        CvMat cvMat2 = this.distortionCoeffs;
        if (cvMat2 != null) {
            opencv_core.write(fileStorage, "distortionCoeffs", opencv_core.cvarrToMat(cvMat2));
        }
        CvMat cvMat3 = this.extrParams;
        if (cvMat3 != null) {
            opencv_core.write(fileStorage, "extrParams", opencv_core.cvarrToMat(cvMat3));
        }
        CvMat cvMat4 = this.reprojErrs;
        if (cvMat4 != null) {
            opencv_core.write(fileStorage, "reprojErrs", opencv_core.cvarrToMat(cvMat4));
        }
        opencv_core.write(fileStorage, "avgReprojErr", this.avgReprojErr);
        opencv_core.write(fileStorage, "maxReprojErr", this.maxReprojErr);
        CvMat cvMat5 = this.R;
        if (cvMat5 != null) {
            opencv_core.write(fileStorage, "R", opencv_core.cvarrToMat(cvMat5));
        }
        CvMat cvMat6 = this.T;
        if (cvMat6 != null) {
            opencv_core.write(fileStorage, ExifInterface.GPS_DIRECTION_TRUE, opencv_core.cvarrToMat(cvMat6));
        }
        CvMat cvMat7 = this.E;
        if (cvMat7 != null) {
            opencv_core.write(fileStorage, ExifInterface.LONGITUDE_EAST, opencv_core.cvarrToMat(cvMat7));
        }
        CvMat cvMat8 = this.F;
        if (cvMat8 != null) {
            opencv_core.write(fileStorage, "F", opencv_core.cvarrToMat(cvMat8));
        }
        opencv_core.write(fileStorage, "avgEpipolarErr", this.avgEpipolarErr);
        opencv_core.write(fileStorage, "maxEpipolarErr", this.maxEpipolarErr);
        opencv_core.write(fileStorage, "colorOrder", this.colorOrder);
        CvMat cvMat9 = this.colorMixingMatrix;
        if (cvMat9 != null) {
            opencv_core.write(fileStorage, "colorMixingMatrix", opencv_core.cvarrToMat(cvMat9));
        }
        CvMat cvMat10 = this.additiveLight;
        if (cvMat10 != null) {
            opencv_core.write(fileStorage, "additiveLight", opencv_core.cvarrToMat(cvMat10));
        }
        opencv_core.write(fileStorage, "avgColorErr", this.avgColorErr);
        opencv_core.write(fileStorage, "colorR2", this.colorR2);
        opencv_core.shiftLeft(fileStorage, "}");
    }

    public void readParameters(File file) throws Exception {
        readParameters(file.getAbsolutePath());
    }

    public void readParameters(String str) throws Exception {
        FileStorage fileStorage = new FileStorage(str, 0);
        readParameters(fileStorage);
        fileStorage.release();
    }

    public void readParameters(FileStorage fileStorage) throws Exception {
        if (fileStorage != null) {
            FileNode fileNode = fileStorage.get(getSettings().getName());
            if (fileNode != null) {
                FileNode fileNode2 = fileNode.get("imageWidth");
                if (fileNode2.isInt()) {
                    this.imageWidth = fileNode2.asInt();
                }
                FileNode fileNode3 = fileNode.get("imageHeight");
                if (fileNode3.isInt()) {
                    this.imageHeight = fileNode3.asInt();
                }
                FileNode fileNode4 = fileNode.get("gamma");
                if (fileNode4.isReal()) {
                    getSettings().setResponseGamma(fileNode4.asDouble());
                }
                Mat mat = new Mat();
                opencv_core.read(fileNode.get("cameraMatrix"), mat);
                CvMat cvMat = null;
                this.cameraMatrix = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get("distortionCoeffs"), mat);
                this.distortionCoeffs = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get("extrParams"), mat);
                this.extrParams = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get("reprojErrs"), mat);
                this.reprojErrs = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                FileNode fileNode5 = fileNode.get("avgReprojErr");
                if (fileNode5.isReal()) {
                    this.avgReprojErr = fileNode5.asDouble();
                }
                FileNode fileNode6 = fileNode.get("maxReprojErr");
                if (fileNode6.isReal()) {
                    this.maxReprojErr = fileNode6.asDouble();
                }
                opencv_core.read(fileNode.get("R"), mat);
                this.R = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get(ExifInterface.GPS_DIRECTION_TRUE), mat);
                this.T = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get(ExifInterface.LONGITUDE_EAST), mat);
                this.E = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get("F"), mat);
                this.F = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                FileNode fileNode7 = fileNode.get("avgEpipolarErr");
                if (fileNode7.isReal()) {
                    this.avgEpipolarErr = fileNode7.asDouble();
                }
                FileNode fileNode8 = fileNode.get("maxEpipolarErr");
                if (fileNode8.isReal()) {
                    this.maxEpipolarErr = fileNode8.asDouble();
                }
                FileNode fileNode9 = fileNode.get("colorOrder");
                if (fileNode9.isString()) {
                    this.colorOrder = fileNode9.asBytePointer().getString();
                }
                opencv_core.read(fileNode.get("colorMixingMatrix"), mat);
                this.colorMixingMatrix = mat.empty() ? null : opencv_core.cvMat(mat).clone();
                opencv_core.read(fileNode.get("additiveLight"), mat);
                if (!mat.empty()) {
                    cvMat = opencv_core.cvMat(mat).clone();
                }
                this.additiveLight = cvMat;
                FileNode fileNode10 = fileNode.get("avgColorErr");
                if (fileNode10.isReal()) {
                    this.avgColorErr = fileNode10.asDouble();
                }
                FileNode fileNode11 = fileNode.get("colorR2");
                if (fileNode11.isReal()) {
                    this.colorR2 = fileNode11.asDouble();
                    return;
                }
                return;
            }
            throw new Exception("Error: FileNode is null, cannot read parameters for device " + getSettings().getName() + ". Is the name correct?");
        }
        throw new Exception("Error: FileStorage is null, cannot read parameters for device " + getSettings().getName() + ". Is the parametersFile correct?");
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = getSettings().getName() + " (" + this.imageWidth + " x " + this.imageHeight + ")\n";
        for (int i = 0; i < getSettings().getName().length(); i++) {
            str5 = str5 + "=";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str5);
        sb.append("\nIntrinsics\n----------\ncamera matrix = ");
        CvMat cvMat = this.cameraMatrix;
        String str6 = "null";
        if (cvMat == null) {
            str = str6;
        } else {
            str = cvMat.toString(16);
        }
        sb.append(str);
        sb.append("\ndistortion coefficients = ");
        Object obj = this.distortionCoeffs;
        if (obj == null) {
            obj = str6;
        }
        sb.append(obj);
        sb.append("\nreprojection RMS/max error (pixels) = ");
        sb.append((float) this.avgReprojErr);
        sb.append(" / ");
        sb.append((float) this.maxReprojErr);
        sb.append("\n\nExtrinsics\n----------\nrotation = ");
        CvMat cvMat2 = this.R;
        if (cvMat2 == null) {
            str2 = str6;
        } else {
            str2 = cvMat2.toString(11);
        }
        sb.append(str2);
        sb.append("\ntranslation = ");
        CvMat cvMat3 = this.T;
        if (cvMat3 == null) {
            str3 = str6;
        } else {
            str3 = cvMat3.toString(14);
        }
        sb.append(str3);
        sb.append("\nepipolar RMS/max error (pixels) = ");
        sb.append((float) this.avgEpipolarErr);
        sb.append(" / ");
        sb.append((float) this.maxEpipolarErr);
        sb.append("\n\nColor\n-----\norder = ");
        sb.append(this.colorOrder);
        sb.append("\nmixing matrix = ");
        CvMat cvMat4 = this.colorMixingMatrix;
        if (cvMat4 == null) {
            str4 = str6;
        } else {
            str4 = cvMat4.toString(16);
        }
        sb.append(str4);
        sb.append("\nadditive light = ");
        CvMat cvMat5 = this.additiveLight;
        if (cvMat5 != null) {
            str6 = cvMat5.toString(17);
        }
        sb.append(str6);
        sb.append("\nnormalized RMSE (intensity) = ");
        sb.append((float) this.avgColorErr);
        sb.append("\nR^2 (intensity) = ");
        sb.append((float) this.colorR2);
        return sb.toString();
    }
}
