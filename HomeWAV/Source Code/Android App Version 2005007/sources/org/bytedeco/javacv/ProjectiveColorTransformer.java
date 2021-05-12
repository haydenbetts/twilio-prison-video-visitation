package org.bytedeco.javacv;

import java.util.Arrays;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.ProjectiveTransformer;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ProjectiveColorTransformer extends ProjectiveTransformer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static ThreadLocal<CvMat> X24x4 = CvMat.createThreadLocal(4, 4);
    protected static ThreadLocal<CvMat> temp3x1 = CvMat.createThreadLocal(3, 1);
    protected CvMat X = null;
    protected CvMat[] X2 = null;
    protected int numBiases = 0;
    protected int numGains = 0;

    public ProjectiveColorTransformer(CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, double[] dArr, double[] dArr2, CvMat cvMat6, int i, int i2) {
        super(cvMat, cvMat2, cvMat3, cvMat4, cvMat5, dArr, dArr2);
        CvMat cvMat7 = null;
        this.X = cvMat6 != null ? cvMat6.clone() : cvMat7;
        this.numGains = i;
        this.numBiases = i2;
    }

    public CvMat getX() {
        return this.X;
    }

    public int getNumGains() {
        return this.numGains;
    }

    public int getNumBiases() {
        return this.numBiases;
    }

    public void transformColor(IplImage iplImage, IplImage iplImage2, CvRect cvRect, int i, ImageTransformer.Parameters parameters, boolean z) {
        Parameters parameters2 = (Parameters) parameters;
        if ((!Arrays.equals(parameters2.getColorParameters(), parameters2.getIdentityColorParameters()) || (this.X != null && !parameters2.fakeIdentity)) && !(this.X == null && this.numGains == 0 && this.numBiases == 0)) {
            CvMat cvMat = X24x4.get();
            prepareColorTransform(cvMat, i, parameters2, z);
            cvMat.rows(3);
            if (cvRect == null) {
                opencv_core.cvResetImageROI(iplImage2);
            } else {
                opencv_core.cvSetImageROI(iplImage2, cvRect);
            }
            cvMat.put(0, 3, cvMat.get(0, 3) * iplImage2.highValue());
            cvMat.put(1, 3, cvMat.get(1, 3) * iplImage2.highValue());
            cvMat.put(2, 3, cvMat.get(2, 3) * iplImage2.highValue());
            opencv_core.cvTransform(iplImage, iplImage2, cvMat, (CvMat) null);
            cvMat.rows(4);
        } else if (iplImage != iplImage2) {
            opencv_core.cvCopy(iplImage, iplImage2);
        }
    }

    /* access modifiers changed from: protected */
    public void prepareColorTransform(CvMat cvMat, int i, Parameters parameters, boolean z) {
        CvMat cvMat2;
        CvMat a = parameters.getA();
        CvMat b = parameters.getB();
        opencv_core.cvSetIdentity(cvMat);
        cvMat.rows(3);
        cvMat.cols(3);
        if (parameters.fakeIdentity && !z) {
            cvMat.put(a);
        } else if (a == null || (cvMat2 = this.X) == null) {
            CvMat cvMat3 = this.X;
            if (cvMat3 == null) {
                cvMat.put(a);
            } else if (a == null) {
                cvMat.put(cvMat3);
            }
        } else {
            opencv_core.cvMatMul(cvMat2, a, cvMat);
        }
        cvMat.rows(4);
        cvMat.cols(4);
        if (b != null) {
            cvMat.put(0, 3, b.get(0));
            cvMat.put(1, 3, b.get(1));
            cvMat.put(2, 3, b.get(2));
        }
        if (z) {
            opencv_core.cvInvert(cvMat, cvMat, 1);
        }
    }

    public void transform(ImageTransformer.Data[] dataArr, CvRect cvRect, ImageTransformer.Parameters[] parametersArr, boolean[] zArr) {
        boolean z;
        if (this.kernelData == null || this.kernelData.capacity() < ((long) dataArr.length)) {
            this.kernelData = new cvkernels.KernelData((long) dataArr.length);
        }
        if (this.H == null || this.H.length < dataArr.length) {
            this.H = new CvMat[dataArr.length];
            for (int i = 0; i < this.H.length; i++) {
                this.H[i] = CvMat.create(3, 3);
            }
        }
        CvMat[] cvMatArr = this.X2;
        if (cvMatArr == null || cvMatArr.length < dataArr.length) {
            this.X2 = new CvMat[dataArr.length];
            int i2 = 0;
            while (true) {
                CvMat[] cvMatArr2 = this.X2;
                if (i2 >= cvMatArr2.length) {
                    break;
                }
                cvMatArr2[i2] = CvMat.create(4, 4);
                i2++;
            }
        }
        for (int i3 = 0; i3 < dataArr.length; i3++) {
            this.kernelData.position((long) i3);
            this.kernelData.srcImg(dataArr[i3].srcImg);
            this.kernelData.srcImg2((IplImage) null);
            this.kernelData.subImg(dataArr[i3].subImg);
            this.kernelData.srcDotImg(dataArr[i3].srcDotImg);
            this.kernelData.mask(dataArr[i3].mask);
            this.kernelData.zeroThreshold(dataArr[i3].zeroThreshold);
            this.kernelData.outlierThreshold(dataArr[i3].outlierThreshold);
            if (zArr == null) {
                z = false;
            } else {
                z = zArr[i3];
            }
            prepareHomography(this.H[i3], dataArr[i3].pyramidLevel, parametersArr[i3], z);
            prepareColorTransform(this.X2[i3], dataArr[i3].pyramidLevel, parametersArr[i3], z);
            this.kernelData.H1(this.H[i3]);
            this.kernelData.H2((CvMat) null);
            this.kernelData.X(this.X2[i3]);
            this.kernelData.transImg(dataArr[i3].transImg);
            this.kernelData.dstImg(dataArr[i3].dstImg);
            this.kernelData.dstDstDot(dataArr[i3].dstDstDot);
        }
        long capacity = this.kernelData.capacity();
        this.kernelData.capacity((long) dataArr.length);
        cvkernels.multiWarpColorTransform(this.kernelData, cvRect, getFillColor());
        this.kernelData.capacity(capacity);
        for (int i4 = 0; i4 < dataArr.length; i4++) {
            this.kernelData.position((long) i4);
            dataArr[i4].dstCount = this.kernelData.dstCount();
            dataArr[i4].dstCountZero = this.kernelData.dstCountZero();
            dataArr[i4].dstCountOutlier = this.kernelData.dstCountOutlier();
            dataArr[i4].srcDstDot = this.kernelData.srcDstDot();
        }
    }

    public Parameters createParameters() {
        return new Parameters();
    }

    public class Parameters extends ProjectiveTransformer.Parameters {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private CvMat A = null;
        private CvMat b = null;
        protected double[] colorParameters = null;
        protected double[] identityColorParameters = null;

        static {
            Class<ProjectiveColorTransformer> cls = ProjectiveColorTransformer.class;
        }

        protected Parameters() {
            super();
            this.identityColorParameters = new double[(ProjectiveColorTransformer.this.numGains + ProjectiveColorTransformer.this.numBiases)];
            if (ProjectiveColorTransformer.this.numGains > 0) {
                CvMat create = CvMat.create(3, 3);
                this.A = create;
                opencv_core.cvSetIdentity(create);
            }
            if (ProjectiveColorTransformer.this.numBiases > 0) {
                CvMat create2 = CvMat.create(3, 1);
                this.b = create2;
                opencv_core.cvSetZero(create2);
            }
            int i = ProjectiveColorTransformer.this.numGains;
            if (i != 0) {
                if (i == 1) {
                    this.identityColorParameters[0] = ((this.A.get(0) + this.A.get(4)) + this.A.get(8)) / 3.0d;
                } else if (i == 3) {
                    this.identityColorParameters[0] = this.A.get(0);
                    this.identityColorParameters[1] = this.A.get(4);
                    this.identityColorParameters[2] = this.A.get(8);
                } else if (i == 9) {
                    this.A.get(0, this.identityColorParameters, 0, 9);
                }
            }
            int i2 = ProjectiveColorTransformer.this.numBiases;
            if (i2 != 0) {
                if (i2 == 1) {
                    this.identityColorParameters[ProjectiveColorTransformer.this.numGains] = ((this.b.get(0) + this.b.get(1)) + this.b.get(2)) / 3.0d;
                } else if (i2 == 3) {
                    this.b.get(0, this.identityColorParameters, ProjectiveColorTransformer.this.numGains, 3);
                }
            }
            reset(false);
        }

        public double[] getColorParameters() {
            return this.colorParameters;
        }

        public double[] getIdentityColorParameters() {
            return this.identityColorParameters;
        }

        public int size() {
            return super.size() + ProjectiveColorTransformer.this.numGains + ProjectiveColorTransformer.this.numBiases;
        }

        public double get(int i) {
            int size = super.size();
            if (i < size) {
                return super.get(i);
            }
            return this.colorParameters[i - size];
        }

        public void set(int i, double d) {
            int size = super.size();
            if (i < size) {
                super.set(i, d);
                return;
            }
            double[] dArr = this.colorParameters;
            int i2 = i - size;
            if (dArr[i2] != d) {
                dArr[i2] = d;
                setUpdateNeeded(true);
            }
        }

        public void reset(boolean z) {
            super.reset(z);
            resetColor(z);
        }

        public void resetColor(boolean z) {
            double[] dArr = this.identityColorParameters;
            if (dArr == null) {
                return;
            }
            if (!Arrays.equals(this.colorParameters, dArr) || this.fakeIdentity != z) {
                this.fakeIdentity = z;
                this.colorParameters = (double[]) this.identityColorParameters.clone();
                setUpdateNeeded(true);
            }
        }

        public void compose(ImageTransformer.Parameters parameters, boolean z, ImageTransformer.Parameters parameters2, boolean z2) {
            super.compose(parameters, z, parameters2, z2);
            composeColor(parameters, z, parameters2, z2);
        }

        public void composeColor(ImageTransformer.Parameters parameters, boolean z, ImageTransformer.Parameters parameters2, boolean z2) {
            CvMat cvMat;
            Parameters parameters3 = (Parameters) parameters;
            Parameters parameters4 = (Parameters) parameters2;
            CvMat a = parameters3.getA();
            CvMat b2 = parameters3.getB();
            CvMat a2 = parameters4.getA();
            CvMat b3 = parameters4.getB();
            if (this.b != null) {
                if (!parameters3.fakeIdentity || ProjectiveColorTransformer.this.X == null) {
                    cvMat = b2;
                } else {
                    CvMat cvMat2 = ProjectiveColorTransformer.temp3x1.get();
                    opencv_core.cvMatMul(ProjectiveColorTransformer.this.X, b2, cvMat2);
                    cvMat = cvMat2;
                }
                if (a2 == null && b3 == null) {
                    opencv_core.cvCopy(cvMat, this.b);
                } else if (cvMat == null) {
                    opencv_core.cvCopy(b3, this.b);
                } else if (b3 == null) {
                    opencv_core.cvMatMul(a2, cvMat, this.b);
                } else {
                    opencv_core.cvGEMM(a2, cvMat, 1.0d, b3, 1.0d, this.b, 0);
                }
            }
            CvMat cvMat3 = this.A;
            if (cvMat3 != null) {
                if (a == null) {
                    opencv_core.cvCopy(a2, cvMat3);
                } else if (a2 == null) {
                    opencv_core.cvCopy(a, cvMat3);
                } else {
                    opencv_core.cvMatMul(a2, a, cvMat3);
                }
            }
            int i = ProjectiveColorTransformer.this.numGains;
            if (i != 0) {
                if (i == 1) {
                    this.colorParameters[0] = ((this.A.get(0) + this.A.get(4)) + this.A.get(8)) / 3.0d;
                } else if (i == 3) {
                    this.colorParameters[0] = this.A.get(0);
                    this.colorParameters[1] = this.A.get(4);
                    this.colorParameters[2] = this.A.get(8);
                } else if (i == 9) {
                    this.A.get(0, this.colorParameters, 0, 9);
                }
            }
            int i2 = ProjectiveColorTransformer.this.numBiases;
            if (i2 == 0) {
                return;
            }
            if (i2 == 1) {
                this.colorParameters[ProjectiveColorTransformer.this.numGains] = ((this.b.get(0) + this.b.get(1)) + this.b.get(2)) / 3.0d;
            } else if (i2 == 3) {
                this.b.get(0, this.colorParameters, ProjectiveColorTransformer.this.numGains, 3);
            }
        }

        public CvMat getA() {
            update();
            return this.A;
        }

        public CvMat getB() {
            update();
            return this.b;
        }

        /* access modifiers changed from: protected */
        public void update() {
            if (isUpdateNeeded()) {
                int i = ProjectiveColorTransformer.this.numGains;
                if (i != 0) {
                    if (i == 1) {
                        this.A.put(0, this.colorParameters[0]);
                        this.A.put(4, this.colorParameters[0]);
                        this.A.put(8, this.colorParameters[0]);
                    } else if (i == 3) {
                        this.A.put(0, this.colorParameters[0]);
                        this.A.put(4, this.colorParameters[1]);
                        this.A.put(8, this.colorParameters[2]);
                    } else if (i == 9) {
                        this.A.put(0, this.colorParameters, 0, 9);
                    }
                }
                int i2 = ProjectiveColorTransformer.this.numBiases;
                if (i2 != 0) {
                    if (i2 == 1) {
                        this.b.put(0, this.colorParameters[ProjectiveColorTransformer.this.numGains]);
                        this.b.put(1, this.colorParameters[ProjectiveColorTransformer.this.numGains]);
                        this.b.put(2, this.colorParameters[ProjectiveColorTransformer.this.numGains]);
                    } else if (i2 == 3) {
                        this.b.put(0, this.colorParameters, ProjectiveColorTransformer.this.numGains, 3);
                    }
                }
                super.update();
                setUpdateNeeded(false);
            }
        }

        public Parameters clone() {
            Parameters parameters = new Parameters();
            parameters.set((ImageTransformer.Parameters) this);
            return parameters;
        }
    }
}
