package org.bytedeco.javacv;

import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.ProjectiveColorTransformer;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;
import org.bytedeco.opencv.opencv_core.Mat;

public class ProCamTransformer implements ImageTransformer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected CvMat[] H1;
    protected CvMat[] H2;
    protected CvMat[] X;
    protected CameraDevice camera;
    protected CvScalar fillColor;
    protected CvMat frontoParallelH;
    protected CvMat invCameraMatrix;
    protected CvMat invFrontoParallelH;
    protected cvkernels.KernelData kernelData;
    protected ProjectorDevice projector;
    protected IplImage[] projectorImage;
    protected ProjectiveColorTransformer projectorTransformer;
    protected CvRect roi;
    protected IplImage[] surfaceImage;
    protected ProjectiveColorTransformer surfaceTransformer;

    public ProCamTransformer(double[] dArr, CameraDevice cameraDevice, ProjectorDevice projectorDevice) {
        this(dArr, cameraDevice, projectorDevice, (CvMat) null);
    }

    public ProCamTransformer(double[] dArr, CameraDevice cameraDevice, ProjectorDevice projectorDevice, CvMat cvMat) {
        double[] dArr2 = dArr;
        CameraDevice cameraDevice2 = cameraDevice;
        ProjectorDevice projectorDevice2 = projectorDevice;
        CvMat cvMat2 = cvMat;
        this.camera = null;
        this.projector = null;
        this.surfaceTransformer = null;
        this.projectorTransformer = null;
        this.projectorImage = null;
        this.surfaceImage = null;
        this.fillColor = opencv_core.cvScalar(0.0d, 0.0d, 0.0d, 1.0d);
        this.roi = new CvRect();
        this.frontoParallelH = null;
        this.invFrontoParallelH = null;
        this.invCameraMatrix = null;
        this.kernelData = null;
        this.H1 = null;
        this.H2 = null;
        this.X = null;
        this.camera = cameraDevice2;
        this.projector = projectorDevice2;
        if (dArr2 != null) {
            ProjectiveColorTransformer projectiveColorTransformer = r1;
            ProjectiveColorTransformer projectiveColorTransformer2 = new ProjectiveColorTransformer(cameraDevice2.cameraMatrix, cameraDevice2.cameraMatrix, (CvMat) null, (CvMat) null, cvMat, dArr, (double[]) null, (CvMat) null, 3, 0);
            this.surfaceTransformer = projectiveColorTransformer;
        }
        double[] dArr3 = {0.0d, 0.0d, (double) (cameraDevice2.imageWidth / 2), (double) cameraDevice2.imageHeight, (double) cameraDevice2.imageWidth, 0.0d};
        double[] dArr4 = {0.0d, 0.0d, (double) (projectorDevice2.imageWidth / 2), (double) projectorDevice2.imageHeight, (double) projectorDevice2.imageWidth, 0.0d};
        if (cvMat2 != null) {
            this.invCameraMatrix = CvMat.create(3, 3);
            opencv_core.cvInvert(cameraDevice2.cameraMatrix, this.invCameraMatrix);
            JavaCV.perspectiveTransform(dArr4, dArr3, this.invCameraMatrix, projectorDevice2.cameraMatrix, projectorDevice2.R, projectorDevice2.T, cvMat, true);
        }
        this.projectorTransformer = new ProjectiveColorTransformer(cameraDevice2.cameraMatrix, projectorDevice2.cameraMatrix, projectorDevice2.R, projectorDevice2.T, (CvMat) null, dArr3, dArr4, projectorDevice2.colorMixingMatrix, 1, 3);
        double[] dArr5 = dArr;
        if (dArr5 != null && cvMat2 != null) {
            CvMat frontoParallelH2 = cameraDevice2.getFrontoParallelH(dArr5, cvMat2, CvMat.create(3, 3));
            this.frontoParallelH = frontoParallelH2;
            CvMat clone = frontoParallelH2.clone();
            this.invFrontoParallelH = clone;
            opencv_core.cvInvert(this.frontoParallelH, clone);
        }
    }

    public int getNumGains() {
        return this.projectorTransformer.getNumGains();
    }

    public int getNumBiases() {
        return this.projectorTransformer.getNumBiases();
    }

    public CvScalar getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(CvScalar cvScalar) {
        this.fillColor = cvScalar;
    }

    public ProjectiveColorTransformer getSurfaceTransformer() {
        return this.surfaceTransformer;
    }

    public ProjectiveColorTransformer getProjectorTransformer() {
        return this.projectorTransformer;
    }

    public IplImage getProjectorImage(int i) {
        return this.projectorImage[i];
    }

    public void setProjectorImage(IplImage iplImage, int i, int i2) {
        setProjectorImage(iplImage, i, i2, true);
    }

    public void setProjectorImage(IplImage iplImage, int i, int i2, boolean z) {
        IplImage[] iplImageArr = this.projectorImage;
        if (iplImageArr == null || iplImageArr.length != i2 + 1) {
            this.projectorImage = new IplImage[(i2 + 1)];
        }
        if (iplImage.depth() == 32 || !z) {
            this.projectorImage[i] = iplImage;
        } else {
            IplImage[] iplImageArr2 = this.projectorImage;
            if (iplImageArr2[i] == null) {
                iplImageArr2[i] = IplImage.create(iplImage.width(), iplImage.height(), 32, iplImage.nChannels(), iplImage.origin());
            }
            IplROI roi2 = iplImage.roi();
            if (roi2 != null) {
                int i3 = 1 << (i2 + 1);
                double d = (double) i3;
                this.roi.x(Math.max(0, ((int) Math.floor(((double) roi2.xOffset()) / d)) * i3));
                this.roi.y(Math.max(0, ((int) Math.floor(((double) roi2.yOffset()) / d)) * i3));
                this.roi.width(Math.min(iplImage.width(), ((int) Math.ceil(((double) roi2.width()) / d)) * i3));
                this.roi.height(Math.min(iplImage.height(), ((int) Math.ceil(((double) roi2.height()) / d)) * i3));
                opencv_core.cvSetImageROI(iplImage, this.roi);
                opencv_core.cvSetImageROI(this.projectorImage[i], this.roi);
            } else {
                opencv_core.cvResetImageROI(iplImage);
                opencv_core.cvResetImageROI(this.projectorImage[i]);
            }
            opencv_core.cvConvertScale(iplImage, this.projectorImage[i], 0.00392156862745098d, 0.0d);
        }
        for (int i4 = i + 1; i4 <= i2; i4++) {
            int i5 = i4 - 1;
            int width = this.projectorImage[i5].width() / 2;
            int height = this.projectorImage[i5].height() / 2;
            int depth = this.projectorImage[i5].depth();
            int nChannels = this.projectorImage[i5].nChannels();
            int origin = this.projectorImage[i5].origin();
            IplImage[] iplImageArr3 = this.projectorImage;
            if (iplImageArr3[i4] == null) {
                iplImageArr3[i4] = IplImage.create(width, height, depth, nChannels, origin);
            }
            IplROI roi3 = this.projectorImage[i5].roi();
            if (roi3 != null) {
                this.roi.x(roi3.xOffset() / 2);
                this.roi.width(roi3.width() / 2);
                this.roi.y(roi3.yOffset() / 2);
                this.roi.height(roi3.height() / 2);
                opencv_core.cvSetImageROI(this.projectorImage[i4], this.roi);
            } else {
                opencv_core.cvResetImageROI(this.projectorImage[i4]);
            }
            IplImage[] iplImageArr4 = this.projectorImage;
            opencv_imgproc.cvPyrDown(iplImageArr4[i5], iplImageArr4[i4], 7);
            opencv_core.cvResetImageROI(this.projectorImage[i5]);
        }
    }

    public IplImage getSurfaceImage(int i) {
        return this.surfaceImage[i];
    }

    public void setSurfaceImage(IplImage iplImage, int i) {
        IplImage[] iplImageArr = this.surfaceImage;
        if (iplImageArr == null || iplImageArr.length != i) {
            this.surfaceImage = new IplImage[i];
        }
        this.surfaceImage[0] = iplImage;
        opencv_core.cvResetImageROI(iplImage);
        for (int i2 = 1; i2 < i; i2++) {
            int i3 = i2 - 1;
            int width = this.surfaceImage[i3].width() / 2;
            int height = this.surfaceImage[i3].height() / 2;
            int depth = this.surfaceImage[i3].depth();
            int nChannels = this.surfaceImage[i3].nChannels();
            int origin = this.surfaceImage[i3].origin();
            IplImage[] iplImageArr2 = this.surfaceImage;
            if (iplImageArr2[i2] == null) {
                iplImageArr2[i2] = IplImage.create(width, height, depth, nChannels, origin);
            } else {
                opencv_core.cvResetImageROI(iplImageArr2[i2]);
            }
            IplImage[] iplImageArr3 = this.surfaceImage;
            opencv_imgproc.cvPyrDown(iplImageArr3[i3], iplImageArr3[i2], 7);
        }
    }

    /* access modifiers changed from: protected */
    public void prepareTransforms(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, int i, Parameters parameters) {
        ProjectiveColorTransformer.Parameters surfaceParameters = parameters.getSurfaceParameters();
        ProjectiveColorTransformer.Parameters projectorParameters = parameters.getProjectorParameters();
        if (this.surfaceTransformer != null) {
            opencv_core.cvInvert(surfaceParameters.getH(), cvMat);
        }
        opencv_core.cvInvert(projectorParameters.getH(), cvMat2);
        if (i > 0) {
            int i2 = 1 << i;
            if (this.surfaceTransformer != null) {
                double d = (double) i2;
                cvMat.put(2, cvMat.get(2) / d);
                cvMat.put(5, cvMat.get(5) / d);
                cvMat.put(6, cvMat.get(6) * d);
                cvMat.put(7, cvMat.get(7) * d);
            }
            double d2 = (double) i2;
            cvMat2.put(2, cvMat2.get(2) / d2);
            cvMat2.put(5, cvMat2.get(5) / d2);
            cvMat2.put(6, cvMat2.get(6) * d2);
            cvMat2.put(7, cvMat2.get(7) * d2);
        }
        double[] dArr = this.projector.colorMixingMatrix.get();
        double[] colorParameters = projectorParameters.getColorParameters();
        double d3 = colorParameters[0];
        cvMat3.put(dArr[0] * d3, dArr[1] * d3, dArr[2] * d3, colorParameters[1], dArr[3] * d3, dArr[4] * d3, dArr[5] * d3, colorParameters[2], dArr[6] * d3, dArr[7] * d3, d3 * dArr[8], colorParameters[3], 0.0d, 0.0d, 0.0d, 1.0d);
    }

    public void transform(IplImage iplImage, IplImage iplImage2, CvRect cvRect, int i, ImageTransformer.Parameters parameters, boolean z) {
        IplImage iplImage3 = iplImage2;
        CvRect cvRect2 = cvRect;
        int i2 = i;
        if (!z) {
            Parameters parameters2 = (Parameters) parameters;
            ProjectiveColorTransformer.Parameters surfaceParameters = parameters2.getSurfaceParameters();
            ProjectiveColorTransformer.Parameters projectorParameters = parameters2.getProjectorParameters();
            if (parameters2.tempImage == null || parameters2.tempImage.length <= i2) {
                IplImage[] unused = parameters2.tempImage = new IplImage[(i2 + 1)];
            }
            parameters2.tempImage[i2] = IplImage.createIfNotCompatible(parameters2.tempImage[i2], iplImage2);
            if (cvRect2 == null) {
                opencv_core.cvResetImageROI(parameters2.tempImage[i2]);
            } else {
                opencv_core.cvSetImageROI(parameters2.tempImage[i2], cvRect2);
            }
            ProjectiveColorTransformer projectiveColorTransformer = this.surfaceTransformer;
            if (projectiveColorTransformer != null) {
                projectiveColorTransformer.transform(iplImage, parameters2.tempImage[i2], cvRect, i, surfaceParameters, false);
            }
            this.projectorTransformer.transform(this.projectorImage[i2], iplImage2, cvRect, i, projectorParameters, false);
            if (this.surfaceTransformer != null) {
                opencv_core.cvMul(iplImage2, parameters2.tempImage[i2], iplImage2, 1.0d / iplImage2.highValue());
            } else {
                opencv_core.cvCopy(parameters2.tempImage[i2], iplImage2);
            }
        } else {
            throw new UnsupportedOperationException("Inverse transform not supported.");
        }
    }

    public void transform(CvMat cvMat, CvMat cvMat2, ImageTransformer.Parameters parameters, boolean z) {
        ProjectiveColorTransformer projectiveColorTransformer = this.surfaceTransformer;
        if (projectiveColorTransformer != null) {
            projectiveColorTransformer.transform(cvMat, cvMat2, (ImageTransformer.Parameters) ((Parameters) parameters).surfaceParameters, z);
        } else if (cvMat2 != cvMat) {
            cvMat2.put(cvMat);
        }
    }

    public void transform(ImageTransformer.Data[] dataArr, CvRect cvRect, ImageTransformer.Parameters[] parametersArr, boolean[] zArr) {
        cvkernels.KernelData kernelData2 = this.kernelData;
        if (kernelData2 == null || kernelData2.capacity() < ((long) dataArr.length)) {
            this.kernelData = new cvkernels.KernelData((long) dataArr.length);
        }
        CvMat[] cvMatArr = this.H1;
        if ((cvMatArr == null || cvMatArr.length < dataArr.length) && this.surfaceTransformer != null) {
            this.H1 = new CvMat[dataArr.length];
            int i = 0;
            while (true) {
                CvMat[] cvMatArr2 = this.H1;
                if (i >= cvMatArr2.length) {
                    break;
                }
                cvMatArr2[i] = CvMat.create(3, 3);
                i++;
            }
        }
        CvMat[] cvMatArr3 = this.H2;
        if (cvMatArr3 == null || cvMatArr3.length < dataArr.length) {
            this.H2 = new CvMat[dataArr.length];
            int i2 = 0;
            while (true) {
                CvMat[] cvMatArr4 = this.H2;
                if (i2 >= cvMatArr4.length) {
                    break;
                }
                cvMatArr4[i2] = CvMat.create(3, 3);
                i2++;
            }
        }
        CvMat[] cvMatArr5 = this.X;
        if (cvMatArr5 == null || cvMatArr5.length < dataArr.length) {
            this.X = new CvMat[dataArr.length];
            int i3 = 0;
            while (true) {
                CvMat[] cvMatArr6 = this.X;
                if (i3 >= cvMatArr6.length) {
                    break;
                }
                cvMatArr6[i3] = CvMat.create(4, 4);
                i3++;
            }
        }
        int i4 = 0;
        while (i4 < dataArr.length) {
            this.kernelData.position((long) i4);
            this.kernelData.srcImg(this.projectorImage[dataArr[i4].pyramidLevel]);
            CvMat cvMat = null;
            this.kernelData.srcImg2(this.surfaceTransformer == null ? null : dataArr[i4].srcImg);
            this.kernelData.subImg(dataArr[i4].subImg);
            this.kernelData.srcDotImg(dataArr[i4].srcDotImg);
            this.kernelData.mask(dataArr[i4].mask);
            this.kernelData.zeroThreshold(dataArr[i4].zeroThreshold);
            this.kernelData.outlierThreshold(dataArr[i4].outlierThreshold);
            if (zArr == null || !zArr[i4]) {
                prepareTransforms(this.surfaceTransformer == null ? null : this.H1[i4], this.H2[i4], this.X[i4], dataArr[i4].pyramidLevel, parametersArr[i4]);
                this.kernelData.H1(this.H2[i4]);
                cvkernels.KernelData kernelData3 = this.kernelData;
                if (this.surfaceTransformer != null) {
                    cvMat = this.H1[i4];
                }
                kernelData3.H2(cvMat);
                this.kernelData.X(this.X[i4]);
                this.kernelData.transImg(dataArr[i4].transImg);
                this.kernelData.dstImg(dataArr[i4].dstImg);
                this.kernelData.dstDstDot(dataArr[i4].dstDstDot);
                i4++;
            } else {
                throw new UnsupportedOperationException("Inverse transform not supported.");
            }
        }
        long capacity = this.kernelData.capacity();
        this.kernelData.capacity((long) dataArr.length);
        cvkernels.multiWarpColorTransform(this.kernelData, cvRect, getFillColor());
        this.kernelData.capacity(capacity);
        for (int i5 = 0; i5 < dataArr.length; i5++) {
            this.kernelData.position((long) i5);
            dataArr[i5].dstCount = this.kernelData.dstCount();
            dataArr[i5].dstCountZero = this.kernelData.dstCountZero();
            dataArr[i5].dstCountOutlier = this.kernelData.dstCountOutlier();
            dataArr[i5].srcDstDot = this.kernelData.srcDstDot();
        }
    }

    public Parameters createParameters() {
        return new Parameters();
    }

    public class Parameters implements ImageTransformer.Parameters {
        private CvMat H = CvMat.create(3, 3);
        private CvMat R = CvMat.create(3, 3);
        private CvMat n = CvMat.create(3, 1);
        private ProjectiveColorTransformer.Parameters projectorParameters = null;
        /* access modifiers changed from: private */
        public ProjectiveColorTransformer.Parameters surfaceParameters = null;
        private CvMat t = CvMat.create(3, 1);
        /* access modifiers changed from: private */
        public IplImage[] tempImage = null;

        protected Parameters() {
            reset(false);
        }

        protected Parameters(ProjectiveColorTransformer.Parameters parameters, ProjectiveColorTransformer.Parameters parameters2) {
            reset(parameters, parameters2);
        }

        public ProjectiveColorTransformer.Parameters getSurfaceParameters() {
            return this.surfaceParameters;
        }

        public ProjectiveColorTransformer.Parameters getProjectorParameters() {
            return this.projectorParameters;
        }

        private int getSizeForSurface() {
            if (ProCamTransformer.this.surfaceTransformer == null) {
                return 0;
            }
            return (this.surfaceParameters.size() - ProCamTransformer.this.surfaceTransformer.getNumGains()) - ProCamTransformer.this.surfaceTransformer.getNumBiases();
        }

        private int getSizeForProjector() {
            return this.projectorParameters.size();
        }

        public int size() {
            return getSizeForSurface() + getSizeForProjector();
        }

        public double[] get() {
            int size = size();
            double[] dArr = new double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = get(i);
            }
            return dArr;
        }

        public double get(int i) {
            if (i < getSizeForSurface()) {
                return this.surfaceParameters.get(i);
            }
            return this.projectorParameters.get(i - getSizeForSurface());
        }

        public void set(double... dArr) {
            for (int i = 0; i < dArr.length; i++) {
                set(i, dArr[i]);
            }
        }

        public void set(int i, double d) {
            if (i < getSizeForSurface()) {
                this.surfaceParameters.set(i, d);
            } else {
                this.projectorParameters.set(i - getSizeForSurface(), d);
            }
        }

        public void set(ImageTransformer.Parameters parameters) {
            Parameters parameters2 = (Parameters) parameters;
            if (ProCamTransformer.this.surfaceTransformer != null) {
                this.surfaceParameters.set((ImageTransformer.Parameters) parameters2.getSurfaceParameters());
                this.surfaceParameters.resetColor(false);
            }
            this.projectorParameters.set((ImageTransformer.Parameters) parameters2.getProjectorParameters());
        }

        public void reset(boolean z) {
            reset((ProjectiveColorTransformer.Parameters) null, (ProjectiveColorTransformer.Parameters) null);
        }

        public void reset(ProjectiveColorTransformer.Parameters parameters, ProjectiveColorTransformer.Parameters parameters2) {
            if (parameters == null && ProCamTransformer.this.surfaceTransformer != null) {
                parameters = ProCamTransformer.this.surfaceTransformer.createParameters();
            }
            if (parameters2 == null) {
                parameters2 = ProCamTransformer.this.projectorTransformer.createParameters();
            }
            this.surfaceParameters = parameters;
            this.projectorParameters = parameters2;
            setSubspace(getSubspace());
        }

        public double getConstraintError() {
            double constraintError = ProCamTransformer.this.surfaceTransformer == null ? 0.0d : this.surfaceParameters.getConstraintError();
            this.projectorParameters.update();
            return constraintError;
        }

        public void compose(ImageTransformer.Parameters parameters, boolean z, ImageTransformer.Parameters parameters2, boolean z2) {
            throw new UnsupportedOperationException("Compose operation not supported.");
        }

        public boolean preoptimize() {
            double[] subspaceInternal = setSubspaceInternal(getSubspaceInternal());
            if (subspaceInternal == null) {
                return false;
            }
            set(8, subspaceInternal[8]);
            set(9, subspaceInternal[9]);
            set(10, subspaceInternal[10]);
            return true;
        }

        public void setSubspace(double... dArr) {
            double[] subspaceInternal = setSubspaceInternal(dArr);
            if (subspaceInternal != null) {
                set(subspaceInternal);
            }
        }

        public double[] getSubspace() {
            return getSubspaceInternal();
        }

        private double[] setSubspaceInternal(double... dArr) {
            if (ProCamTransformer.this.invFrontoParallelH == null) {
                return null;
            }
            double[] dArr2 = new double[11];
            this.t.put(dArr[0], dArr[1], dArr[2]);
            opencv_calib3d.Rodrigues(opencv_core.cvarrToMat(this.t), opencv_core.cvarrToMat(this.R), (Mat) null);
            this.t.put(dArr[3], dArr[4], dArr[5]);
            this.H.put(this.R.get(0), this.R.get(1), this.t.get(0), this.R.get(3), this.R.get(4), this.t.get(1), this.R.get(6), this.R.get(7), this.t.get(2));
            opencv_core.cvMatMul(this.H, ProCamTransformer.this.invFrontoParallelH, this.H);
            CvMat k2 = ProCamTransformer.this.surfaceTransformer.getK2();
            CvMat cvMat = this.H;
            opencv_core.cvMatMul(k2, cvMat, cvMat);
            opencv_core.cvMatMul(this.H, ProCamTransformer.this.surfaceTransformer.getInvK1(), this.H);
            CvMat cvMat2 = this.R;
            CvMat cvMat3 = this.t;
            opencv_core.cvGEMM(cvMat2, cvMat3, 1.0d, (CvArr) null, 0.0d, cvMat3, 1);
            this.n.put(0.0d, 0.0d, 1.0d);
            CvMat cvMat4 = this.R;
            CvMat cvMat5 = this.n;
            opencv_core.cvGEMM(cvMat4, cvMat5, 1.0d / this.t.get(2), (CvArr) null, 0.0d, cvMat5, 0);
            JavaCV.perspectiveTransform(ProCamTransformer.this.projectorTransformer.getReferencePoints2(), dArr2, ProCamTransformer.this.projectorTransformer.getInvK1(), ProCamTransformer.this.projectorTransformer.getK2(), ProCamTransformer.this.projectorTransformer.getR(), ProCamTransformer.this.projectorTransformer.getT(), this.n, true);
            dArr2[8] = dArr2[0];
            dArr2[9] = dArr2[2];
            dArr2[10] = dArr2[4];
            JavaCV.perspectiveTransform(ProCamTransformer.this.surfaceTransformer.getReferencePoints1(), dArr2, this.H);
            return dArr2;
        }

        private double[] getSubspaceInternal() {
            if (ProCamTransformer.this.frontoParallelH == null) {
                return null;
            }
            opencv_core.cvMatMul(ProCamTransformer.this.surfaceTransformer.getK1(), ProCamTransformer.this.frontoParallelH, this.H);
            CvMat h = this.surfaceParameters.getH();
            CvMat cvMat = this.H;
            opencv_core.cvMatMul(h, cvMat, cvMat);
            CvMat invK2 = ProCamTransformer.this.surfaceTransformer.getInvK2();
            CvMat cvMat2 = this.H;
            opencv_core.cvMatMul(invK2, cvMat2, cvMat2);
            JavaCV.HtoRt(this.H, this.R, this.t);
            opencv_calib3d.Rodrigues(opencv_core.cvarrToMat(this.R), opencv_core.cvarrToMat(this.n), (Mat) null);
            return new double[]{this.n.get(0), this.n.get(1), this.n.get(2), this.t.get(0), this.t.get(1), this.t.get(2)};
        }

        public CvMat getN() {
            double[] referencePoints2 = ProCamTransformer.this.projectorTransformer.getReferencePoints2();
            double[] dArr = (double[]) ProCamTransformer.this.projectorTransformer.getReferencePoints1().clone();
            dArr[0] = this.projectorParameters.get(0);
            dArr[2] = this.projectorParameters.get(1);
            dArr[4] = this.projectorParameters.get(2);
            opencv_core.cvTranspose(ProCamTransformer.this.projectorTransformer.getR(), this.R);
            opencv_core.cvGEMM(this.R, ProCamTransformer.this.projectorTransformer.getT(), -1.0d, (CvArr) null, 0.0d, this.t, 0);
            JavaCV.getPlaneParameters(referencePoints2, dArr, ProCamTransformer.this.projectorTransformer.getInvK2(), ProCamTransformer.this.projectorTransformer.getK1(), this.R, this.t, this.n);
            CvMat cvMat = this.R;
            CvMat cvMat2 = this.n;
            opencv_core.cvGEMM(cvMat, cvMat2, 1.0d / (opencv_core.cvDotProduct(this.n, ProCamTransformer.this.projectorTransformer.getT()) + 1.0d), (CvArr) null, 0.0d, cvMat2, 0);
            return this.n;
        }

        public CvMat getN0() {
            this.n = getN();
            if (ProCamTransformer.this.surfaceTransformer == null) {
                return this.n;
            }
            ProCamTransformer.this.camera.getFrontoParallelH(this.surfaceParameters.get(), this.n, this.R);
            opencv_core.cvInvert(this.surfaceParameters.getH(), this.H);
            opencv_core.cvMatMul(this.H, ProCamTransformer.this.surfaceTransformer.getK2(), this.H);
            CvMat cvMat = this.H;
            opencv_core.cvMatMul(cvMat, this.R, cvMat);
            CvMat invK1 = ProCamTransformer.this.surfaceTransformer.getInvK1();
            CvMat cvMat2 = this.H;
            opencv_core.cvMatMul(invK1, cvMat2, cvMat2);
            JavaCV.HtoRt(this.H, this.R, this.t);
            CvMat cvMat3 = this.R;
            CvMat cvMat4 = this.t;
            opencv_core.cvGEMM(cvMat3, cvMat4, 1.0d, (CvArr) null, 0.0d, cvMat4, 1);
            this.n.put(0.0d, 0.0d, 1.0d);
            CvMat cvMat5 = this.R;
            CvMat cvMat6 = this.n;
            opencv_core.cvGEMM(cvMat5, cvMat6, 1.0d / this.t.get(2), (CvArr) null, 0.0d, cvMat6, 0);
            return this.n;
        }

        public Parameters clone() {
            Parameters parameters = new Parameters();
            ProjectiveColorTransformer.Parameters parameters2 = this.surfaceParameters;
            parameters.surfaceParameters = parameters2 == null ? null : parameters2.clone();
            parameters.projectorParameters = this.projectorParameters.clone();
            return parameters;
        }

        public String toString() {
            if (this.surfaceParameters == null) {
                return this.projectorParameters.toString();
            }
            return this.surfaceParameters.toString() + this.projectorParameters.toString();
        }
    }
}
