package org.bytedeco.javacv;

import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

public class ProjectiveTransformer implements ImageTransformer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal(3, 3);
    protected static ThreadLocal<CvMat> pts4x1 = CvMat.createThreadLocal(4, 1, 6, 2);
    protected CvMat[] H;
    protected CvMat K1;
    protected CvMat K2;
    protected CvMat R;
    protected CvScalar fillColor;
    protected CvMat invK1;
    protected CvMat invK2;
    protected cvkernels.KernelData kernelData;
    protected CvMat n;
    protected double[] referencePoints1;
    protected double[] referencePoints2;
    protected CvMat t;

    public ProjectiveTransformer() {
        this((CvMat) null, (CvMat) null, (CvMat) null, (CvMat) null, (CvMat) null, new double[0], (double[]) null);
    }

    public ProjectiveTransformer(double[] dArr) {
        this((CvMat) null, (CvMat) null, (CvMat) null, (CvMat) null, (CvMat) null, dArr, (double[]) null);
    }

    public ProjectiveTransformer(ProjectiveDevice projectiveDevice, ProjectiveDevice projectiveDevice2, CvMat cvMat, double[] dArr, double[] dArr2) {
        this(projectiveDevice.cameraMatrix, projectiveDevice2.cameraMatrix, projectiveDevice2.R, projectiveDevice2.T, cvMat, dArr, dArr2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: double[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProjectiveTransformer(org.bytedeco.opencv.opencv_core.CvMat r13, org.bytedeco.opencv.opencv_core.CvMat r14, org.bytedeco.opencv.opencv_core.CvMat r15, org.bytedeco.opencv.opencv_core.CvMat r16, org.bytedeco.opencv.opencv_core.CvMat r17, double[] r18, double[] r19) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r12.<init>()
            r3 = 0
            r0.K1 = r3
            r0.K2 = r3
            r0.invK1 = r3
            r0.invK2 = r3
            r0.R = r3
            r0.t = r3
            r0.n = r3
            r0.referencePoints1 = r3
            r0.referencePoints2 = r3
            r4 = 0
            r6 = 0
            r8 = 0
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            org.bytedeco.opencv.opencv_core.CvScalar r4 = org.bytedeco.opencv.global.opencv_core.cvScalar(r4, r6, r8, r10)
            r0.fillColor = r4
            r0.kernelData = r3
            r0.H = r3
            if (r1 != 0) goto L_0x002f
            r4 = r3
            goto L_0x0033
        L_0x002f:
            org.bytedeco.opencv.opencv_core.CvMat r4 = r13.clone()
        L_0x0033:
            r0.K1 = r4
            if (r2 != 0) goto L_0x0039
            r4 = r3
            goto L_0x003d
        L_0x0039:
            org.bytedeco.opencv.opencv_core.CvMat r4 = r14.clone()
        L_0x003d:
            r0.K2 = r4
            if (r1 != 0) goto L_0x0043
            r4 = r3
            goto L_0x0047
        L_0x0043:
            org.bytedeco.opencv.opencv_core.CvMat r4 = r13.clone()
        L_0x0047:
            r0.invK1 = r4
            if (r2 != 0) goto L_0x004d
            r4 = r3
            goto L_0x0051
        L_0x004d:
            org.bytedeco.opencv.opencv_core.CvMat r4 = r14.clone()
        L_0x0051:
            r0.invK2 = r4
            if (r1 == 0) goto L_0x005a
            org.bytedeco.opencv.opencv_core.CvMat r4 = r0.invK1
            org.bytedeco.opencv.global.opencv_core.cvInvert(r13, r4)
        L_0x005a:
            if (r2 == 0) goto L_0x0061
            org.bytedeco.opencv.opencv_core.CvMat r1 = r0.invK2
            org.bytedeco.opencv.global.opencv_core.cvInvert(r14, r1)
        L_0x0061:
            if (r15 != 0) goto L_0x0065
            r1 = r3
            goto L_0x0069
        L_0x0065:
            org.bytedeco.opencv.opencv_core.CvMat r1 = r15.clone()
        L_0x0069:
            r0.R = r1
            if (r16 != 0) goto L_0x006f
            r1 = r3
            goto L_0x0073
        L_0x006f:
            org.bytedeco.opencv.opencv_core.CvMat r1 = r16.clone()
        L_0x0073:
            r0.t = r1
            if (r17 != 0) goto L_0x0079
            r1 = r3
            goto L_0x007d
        L_0x0079:
            org.bytedeco.opencv.opencv_core.CvMat r1 = r17.clone()
        L_0x007d:
            r0.n = r1
            if (r18 != 0) goto L_0x0083
            r1 = r3
            goto L_0x0089
        L_0x0083:
            java.lang.Object r1 = r18.clone()
            double[] r1 = (double[]) r1
        L_0x0089:
            r0.referencePoints1 = r1
            if (r19 != 0) goto L_0x008e
            goto L_0x0095
        L_0x008e:
            java.lang.Object r1 = r19.clone()
            r3 = r1
            double[] r3 = (double[]) r3
        L_0x0095:
            r0.referencePoints2 = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.ProjectiveTransformer.<init>(org.bytedeco.opencv.opencv_core.CvMat, org.bytedeco.opencv.opencv_core.CvMat, org.bytedeco.opencv.opencv_core.CvMat, org.bytedeco.opencv.opencv_core.CvMat, org.bytedeco.opencv.opencv_core.CvMat, double[], double[]):void");
    }

    public CvScalar getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(CvScalar cvScalar) {
        this.fillColor = cvScalar;
    }

    public double[] getReferencePoints1() {
        return this.referencePoints1;
    }

    public double[] getReferencePoints2() {
        return this.referencePoints2;
    }

    public CvMat getK1() {
        return this.K1;
    }

    public CvMat getK2() {
        return this.K2;
    }

    public CvMat getInvK1() {
        return this.invK1;
    }

    public CvMat getInvK2() {
        return this.invK2;
    }

    public CvMat getR() {
        return this.R;
    }

    public CvMat getT() {
        return this.t;
    }

    public CvMat getN() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public void prepareHomography(CvMat cvMat, int i, Parameters parameters, boolean z) {
        if (this.K2 == null || this.invK1 == null || this.R == null || this.t == null || !parameters.fakeIdentity) {
            if (z) {
                cvMat.put(parameters.getH());
            } else {
                opencv_core.cvInvert(parameters.getH(), cvMat);
            }
            if (i > 0) {
                double d = (double) (1 << i);
                cvMat.put(2, cvMat.get(2) / d);
                cvMat.put(5, cvMat.get(5) / d);
                cvMat.put(6, cvMat.get(6) * d);
                cvMat.put(7, cvMat.get(7) * d);
                return;
            }
            return;
        }
        opencv_core.cvSetIdentity(cvMat);
    }

    public void transform(IplImage iplImage, IplImage iplImage2, CvRect cvRect, int i, ImageTransformer.Parameters parameters, boolean z) {
        IplImage iplImage3 = iplImage;
        IplImage iplImage4 = iplImage2;
        Parameters parameters2 = (Parameters) parameters;
        if (this.K2 == null || this.invK1 == null || this.R == null || this.t == null || !parameters2.fakeIdentity) {
            CvMat cvMat = H3x3.get();
            prepareHomography(cvMat, i, parameters2, true);
            int i2 = 0;
            if (!(cvRect == null || (cvRect.x() == 0 && cvRect.y() == 0))) {
                int x = cvRect.x();
                int y = cvRect.y();
                if (z) {
                    double d = (double) x;
                    double d2 = (double) y;
                    cvMat.put(2, cvMat.get(2) + (cvMat.get(0) * d) + (cvMat.get(1) * d2));
                    cvMat.put(5, (cvMat.get(3) * d) + (cvMat.get(4) * d2) + cvMat.get(5));
                    cvMat.put(8, (cvMat.get(6) * d) + (cvMat.get(7) * d2) + cvMat.get(8));
                } else {
                    double d3 = (double) x;
                    cvMat.put(0, cvMat.get(0) - (cvMat.get(6) * d3));
                    cvMat.put(1, cvMat.get(1) - (cvMat.get(7) * d3));
                    cvMat.put(2, cvMat.get(2) - (d3 * cvMat.get(8)));
                    double d4 = (double) y;
                    cvMat.put(3, cvMat.get(3) - (cvMat.get(6) * d4));
                    cvMat.put(4, cvMat.get(4) - (cvMat.get(7) * d4));
                    cvMat.put(5, cvMat.get(5) - (d4 * cvMat.get(8)));
                }
            }
            iplImage4.origin(iplImage.origin());
            if (cvRect == null) {
                opencv_core.cvResetImageROI(iplImage2);
            } else {
                opencv_core.cvSetImageROI(iplImage2, cvRect);
            }
            if (z) {
                i2 = 16;
            }
            opencv_imgproc.cvWarpPerspective(iplImage3, iplImage4, cvMat, i2 | 9, getFillColor());
        } else if (iplImage3 != iplImage4) {
            opencv_core.cvCopy(iplImage, iplImage2);
        }
    }

    public void transform(CvMat cvMat, CvMat cvMat2, ImageTransformer.Parameters parameters, boolean z) {
        CvMat cvMat3;
        Parameters parameters2 = (Parameters) parameters;
        if (z) {
            cvMat3 = H3x3.get();
            opencv_core.cvInvert(parameters2.getH(), cvMat3);
        } else {
            cvMat3 = parameters2.getH();
        }
        opencv_core.cvPerspectiveTransform(cvMat, cvMat2, cvMat3);
    }

    public void transform(ImageTransformer.Data[] dataArr, CvRect cvRect, ImageTransformer.Parameters[] parametersArr, boolean[] zArr) {
        cvkernels.KernelData kernelData2 = this.kernelData;
        if (kernelData2 == null || kernelData2.capacity() < ((long) dataArr.length)) {
            this.kernelData = new cvkernels.KernelData((long) dataArr.length);
        }
        CvMat[] cvMatArr = this.H;
        if (cvMatArr == null || cvMatArr.length < dataArr.length) {
            this.H = new CvMat[dataArr.length];
            int i = 0;
            while (true) {
                CvMat[] cvMatArr2 = this.H;
                if (i >= cvMatArr2.length) {
                    break;
                }
                cvMatArr2[i] = CvMat.create(3, 3);
                i++;
            }
        }
        for (int i2 = 0; i2 < dataArr.length; i2++) {
            this.kernelData.position((long) i2);
            this.kernelData.srcImg(dataArr[i2].srcImg);
            this.kernelData.srcImg2((IplImage) null);
            this.kernelData.subImg(dataArr[i2].subImg);
            this.kernelData.srcDotImg(dataArr[i2].srcDotImg);
            this.kernelData.mask(dataArr[i2].mask);
            this.kernelData.zeroThreshold(dataArr[i2].zeroThreshold);
            this.kernelData.outlierThreshold(dataArr[i2].outlierThreshold);
            prepareHomography(this.H[i2], dataArr[i2].pyramidLevel, parametersArr[i2], zArr == null ? false : zArr[i2]);
            this.kernelData.H1(this.H[i2]);
            this.kernelData.H2((CvMat) null);
            this.kernelData.X((CvMat) null);
            this.kernelData.transImg(dataArr[i2].transImg);
            this.kernelData.dstImg(dataArr[i2].dstImg);
            this.kernelData.dstDstDot(dataArr[i2].dstDstDot);
        }
        long capacity = this.kernelData.capacity();
        this.kernelData.capacity((long) dataArr.length);
        cvkernels.multiWarpColorTransform(this.kernelData, cvRect, getFillColor());
        this.kernelData.capacity(capacity);
        for (int i3 = 0; i3 < dataArr.length; i3++) {
            this.kernelData.position((long) i3);
            dataArr[i3].dstCount = this.kernelData.dstCount();
            dataArr[i3].dstCountZero = this.kernelData.dstCountZero();
            dataArr[i3].dstCountOutlier = this.kernelData.dstCountOutlier();
            dataArr[i3].srcDstDot = this.kernelData.srcDstDot();
        }
    }

    public Parameters createParameters() {
        return new Parameters();
    }

    public class Parameters implements ImageTransformer.Parameters {
        private CvMat H = CvMat.create(3, 3);
        private CvMat R2 = null;
        private double constraintError = 0.0d;
        protected boolean fakeIdentity = false;
        private CvMat n2 = null;
        protected double[] projectiveParameters = null;
        private CvMat t2 = null;
        private boolean updateNeeded = true;

        public double[] getSubspace() {
            return null;
        }

        public boolean preoptimize() {
            return false;
        }

        public void setSubspace(double... dArr) {
        }

        protected Parameters() {
            reset(false);
        }

        public boolean isUpdateNeeded() {
            return this.updateNeeded;
        }

        public void setUpdateNeeded(boolean z) {
            this.updateNeeded = z;
        }

        public int size() {
            return this.projectiveParameters.length;
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
            return this.projectiveParameters[i];
        }

        public void set(double... dArr) {
            for (int i = 0; i < dArr.length; i++) {
                set(i, dArr[i]);
            }
        }

        public void set(int i, double d) {
            double[] dArr = this.projectiveParameters;
            if (dArr[i] != d) {
                dArr[i] = d;
                setUpdateNeeded(true);
            }
        }

        public void set(ImageTransformer.Parameters parameters) {
            set(parameters.get());
            this.fakeIdentity = ((Parameters) parameters).fakeIdentity;
        }

        public void reset(boolean z) {
            setUpdateNeeded(true);
            if (ProjectiveTransformer.this.referencePoints1 == null || !(ProjectiveTransformer.this.referencePoints1.length == 0 || ProjectiveTransformer.this.referencePoints1.length == 8)) {
                if (ProjectiveTransformer.this.K2 != null && ProjectiveTransformer.this.invK1 != null) {
                    if (ProjectiveTransformer.this.R != null && ProjectiveTransformer.this.t != null) {
                        this.projectiveParameters = new double[]{ProjectiveTransformer.this.referencePoints1[0], ProjectiveTransformer.this.referencePoints1[2], ProjectiveTransformer.this.referencePoints1[4]};
                    } else if (ProjectiveTransformer.this.n != null) {
                        this.projectiveParameters = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
                    } else {
                        this.projectiveParameters = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
                    }
                }
            } else if (ProjectiveTransformer.this.referencePoints1.length == 0) {
                this.projectiveParameters = new double[]{1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d};
            } else {
                this.projectiveParameters = (double[]) ProjectiveTransformer.this.referencePoints1.clone();
            }
        }

        public double getConstraintError() {
            update();
            return this.constraintError;
        }

        public void set(CvMat cvMat, boolean z) {
            if (this.projectiveParameters.length != 8 || ProjectiveTransformer.this.referencePoints1 == null) {
                throw new UnsupportedOperationException("Set homography operation not supported.");
            }
            if (z) {
                opencv_core.cvInvert(cvMat, this.H);
            } else {
                CvMat cvMat2 = this.H;
                if (cvMat != cvMat2) {
                    opencv_core.cvCopy(cvMat, cvMat2);
                }
            }
            if (ProjectiveTransformer.this.referencePoints1.length == 0) {
                for (int i = 0; i < 8; i++) {
                    this.projectiveParameters[i] = this.H.get(i) / this.H.get(8);
                }
            } else {
                CvMat put = ProjectiveTransformer.pts4x1.get().put(ProjectiveTransformer.this.referencePoints1);
                opencv_core.cvPerspectiveTransform(put, put, this.H);
                put.get(this.projectiveParameters);
            }
            setUpdateNeeded(true);
        }

        public void compose(ImageTransformer.Parameters parameters, boolean z, ImageTransformer.Parameters parameters2, boolean z2) {
            Parameters parameters3 = (Parameters) parameters;
            Parameters parameters4 = (Parameters) parameters2;
            if (ProjectiveTransformer.this.K2 == null || ProjectiveTransformer.this.invK1 == null || ProjectiveTransformer.this.R == null || ProjectiveTransformer.this.t == null || !parameters3.fakeIdentity) {
                compose(parameters3.getH(), z, parameters4.getH(), z2);
            }
        }

        public void compose(CvMat cvMat, boolean z, CvMat cvMat2, boolean z2) {
            if (z && z2) {
                opencv_core.cvMatMul(cvMat2, cvMat, this.H);
                CvMat cvMat3 = this.H;
                opencv_core.cvInvert(cvMat3, cvMat3);
            } else if (z) {
                opencv_core.cvInvert(cvMat, this.H);
                CvMat cvMat4 = this.H;
                opencv_core.cvMatMul(cvMat4, cvMat2, cvMat4);
            } else if (z2) {
                opencv_core.cvInvert(cvMat2, this.H);
                CvMat cvMat5 = this.H;
                opencv_core.cvMatMul(cvMat, cvMat5, cvMat5);
            } else {
                opencv_core.cvMatMul(cvMat, cvMat2, this.H);
            }
            set(this.H, false);
        }

        public CvMat getH() {
            update();
            return this.H;
        }

        public CvMat getN() {
            update();
            return this.n2;
        }

        public CvMat getR() {
            update();
            return this.R2;
        }

        public CvMat getT() {
            update();
            return this.t2;
        }

        /* access modifiers changed from: protected */
        public void update() {
            if (isUpdateNeeded()) {
                if (ProjectiveTransformer.this.referencePoints1 == null || !(ProjectiveTransformer.this.referencePoints1.length == 0 || ProjectiveTransformer.this.referencePoints1.length == 8)) {
                    if (!(ProjectiveTransformer.this.K2 == null || ProjectiveTransformer.this.invK1 == null)) {
                        if (ProjectiveTransformer.this.R == null || ProjectiveTransformer.this.t == null) {
                            if (ProjectiveTransformer.this.n != null) {
                                this.n2 = ProjectiveTransformer.this.n;
                            } else {
                                if (this.n2 == null) {
                                    this.n2 = CvMat.create(3, 1);
                                }
                                this.n2.put(0, this.projectiveParameters, 8, 3);
                            }
                            if (this.R2 == null) {
                                this.R2 = CvMat.create(3, 3);
                            }
                            if (this.t2 == null) {
                                this.t2 = CvMat.create(3, 1);
                            }
                            this.t2.put(0, this.projectiveParameters, 0, 3);
                            opencv_calib3d.Rodrigues(opencv_core.cvarrToMat(this.t2), opencv_core.cvarrToMat(this.R2), (Mat) null);
                            this.t2.put(0, this.projectiveParameters, 3, 3);
                            opencv_core.cvGEMM(this.t2, this.n2, -1.0d, this.R2, 1.0d, this.H, 2);
                        } else {
                            double[] dArr = ProjectiveTransformer.this.referencePoints2;
                            double[] dArr2 = {this.projectiveParameters[0], ProjectiveTransformer.this.referencePoints1[1], this.projectiveParameters[1], ProjectiveTransformer.this.referencePoints1[3], this.projectiveParameters[2], ProjectiveTransformer.this.referencePoints1[5]};
                            if (this.R2 == null) {
                                this.R2 = CvMat.create(3, 3);
                            }
                            if (this.t2 == null) {
                                this.t2 = CvMat.create(3, 1);
                            }
                            opencv_core.cvTranspose(ProjectiveTransformer.this.R, this.R2);
                            opencv_core.cvGEMM(this.R2, ProjectiveTransformer.this.t, -1.0d, (CvArr) null, 0.0d, this.t2, 0);
                            JavaCV.getPerspectiveTransform(dArr, dArr2, ProjectiveTransformer.this.invK2, ProjectiveTransformer.this.K1, this.R2, this.t2, this.H);
                        }
                    }
                } else if (ProjectiveTransformer.this.referencePoints1.length == 0) {
                    this.H.put(0, this.projectiveParameters, 0, 8);
                    this.H.put(8, 1.0d);
                } else {
                    JavaCV.getPerspectiveTransform(ProjectiveTransformer.this.referencePoints1, this.projectiveParameters, this.H);
                }
                setUpdateNeeded(false);
            }
        }

        public Parameters clone() {
            Parameters parameters = new Parameters();
            parameters.set((ImageTransformer.Parameters) this);
            return parameters;
        }

        public String toString() {
            double[] dArr = get();
            String str = "[";
            for (int i = 0; i < dArr.length; i++) {
                str = str + ((float) dArr[i]);
                if (i < dArr.length - 1) {
                    str = str + ", ";
                }
            }
            return str + "]";
        }
    }
}
