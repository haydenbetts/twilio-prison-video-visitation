package org.bytedeco.javacv;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.bytedeco.javacv.ImageAligner;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;

public class GNImageAligner implements ImageAligner {
    protected double RMSE;
    protected double[] constraintGrad;
    protected CvMat dstRoiPts;
    protected CvPoint dstRoiPtsArray;
    protected CvMat gradient;
    protected CvMat hessian;
    protected ImageTransformer.Data[] hessianGradientTransformerData;
    protected IplImage[] images;
    protected int lastLinePosition;
    protected IplImage[] mask;
    protected final int n;
    protected ImageTransformer.Parameters parameters;
    protected ImageTransformer.Parameters[] parametersArray;
    protected CvMat prior;
    protected ImageTransformer.Parameters priorParameters;
    protected int pyramidLevel;
    protected IplImage[] residual;
    protected ImageTransformer.Data[] residualTransformerData;
    protected boolean residualUpdateNeeded;
    protected CvRect roi;
    protected Settings settings;
    protected CvMat srcRoiPts;
    protected boolean[] subspaceCorrelated;
    protected double[][] subspaceJacobian;
    protected double[] subspaceParameters;
    protected double[] subspaceResidual;
    protected IplImage[] target;
    protected ImageTransformer.Parameters[] tempParameters;
    protected double[][] tempSubspaceParameters;
    protected IplImage[] template;
    protected CvRect temproi;
    protected IplImage[] transformed;
    protected ImageTransformer transformer;
    protected int trials;
    protected CvMat update;
    protected double[] updateScale;

    public GNImageAligner(ImageTransformer imageTransformer, ImageTransformer.Parameters parameters2, IplImage iplImage, double[] dArr, IplImage iplImage2) {
        this(imageTransformer, parameters2, iplImage, dArr, iplImage2, new Settings());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GNImageAligner(ImageTransformer imageTransformer, ImageTransformer.Parameters parameters2, IplImage iplImage, double[] dArr, IplImage iplImage2, Settings settings2) {
        this(imageTransformer, parameters2);
        IplImage iplImage3 = iplImage;
        IplImage iplImage4 = iplImage2;
        Settings settings3 = settings2;
        setSettings(settings3);
        int i = settings3.pyramidLevelMin;
        int i2 = settings3.pyramidLevelMax;
        int i3 = i2 + 1;
        this.template = new IplImage[i3];
        this.target = new IplImage[i3];
        this.transformed = new IplImage[i3];
        this.residual = new IplImage[i3];
        this.mask = new IplImage[i3];
        int width = iplImage3 != null ? iplImage.width() : iplImage2.width();
        int height = iplImage3 != null ? iplImage.height() : iplImage2.height();
        int nChannels = iplImage3 != null ? iplImage.nChannels() : iplImage2.nChannels();
        int origin = iplImage3 != null ? iplImage.origin() : iplImage2.origin();
        for (int i4 = i; i4 <= i2; i4++) {
            if (i4 == i && iplImage3 != null && iplImage.depth() == 32) {
                this.template[i4] = iplImage3;
            } else {
                this.template[i4] = IplImage.create(width, height, 32, nChannels, origin);
            }
            if (i4 == i && iplImage4 != null && iplImage2.depth() == 32) {
                this.target[i4] = iplImage4;
            } else {
                this.target[i4] = IplImage.create(width, height, 32, nChannels, origin);
            }
            this.transformed[i4] = IplImage.create(width, height, 32, nChannels, origin);
            this.residual[i4] = IplImage.create(width, height, 32, nChannels, origin);
            this.mask[i4] = IplImage.create(width, height, 8, 1, origin);
            width /= 2;
            height /= 2;
        }
        this.hessianGradientTransformerData = new ImageTransformer.Data[this.n];
        int i5 = 0;
        while (true) {
            int i6 = this.n;
            if (i5 < i6) {
                ImageTransformer.Data[] dataArr = this.hessianGradientTransformerData;
                IplImage[] iplImageArr = this.template;
                int i7 = this.pyramidLevel;
                dataArr[i5] = new ImageTransformer.Data(iplImageArr[i7], this.transformed[i7], this.residual[i7], this.mask[i7], 0.0d, 0.0d, i7, (IplImage) null, (IplImage) null, i6);
                i5++;
            } else {
                IplImage[] iplImageArr2 = this.template;
                int i8 = this.pyramidLevel;
                this.residualTransformerData = new ImageTransformer.Data[]{new ImageTransformer.Data(iplImageArr2[i8], this.target[i8], (IplImage) null, this.mask[i8], 0.0d, 0.0d, i8, this.transformed[i8], this.residual[i8], 1)};
                setConstrained(settings3.constrained);
                setTemplateImage(iplImage3, dArr);
                setTargetImage(iplImage4);
                return;
            }
        }
    }

    protected GNImageAligner(ImageTransformer imageTransformer, ImageTransformer.Parameters parameters2) {
        this.images = new IplImage[5];
        this.residualUpdateNeeded = true;
        int i = 0;
        this.lastLinePosition = 0;
        this.trials = 0;
        int size = parameters2.size();
        this.n = size;
        this.srcRoiPts = CvMat.create(4, 1, 6, 2);
        this.dstRoiPts = CvMat.create(4, 1, 6, 2);
        this.dstRoiPtsArray = new CvPoint(4);
        this.roi = new CvRect();
        this.temproi = new CvRect();
        this.transformer = imageTransformer;
        ImageTransformer.Parameters clone = parameters2.clone();
        this.parameters = clone;
        this.parametersArray = new ImageTransformer.Parameters[]{clone};
        this.tempParameters = new ImageTransformer.Parameters[size];
        int i2 = 0;
        while (true) {
            ImageTransformer.Parameters[] parametersArr = this.tempParameters;
            if (i2 >= parametersArr.length) {
                break;
            }
            parametersArr[i2] = parameters2.clone();
            i2++;
        }
        double[] subspace = this.parameters.getSubspace();
        this.subspaceParameters = subspace;
        if (subspace != null) {
            this.tempSubspaceParameters = new double[Parallel.getNumThreads()][];
            while (true) {
                double[][] dArr = this.tempSubspaceParameters;
                if (i < dArr.length) {
                    dArr[i] = (double[]) this.subspaceParameters.clone();
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static class Settings extends ImageAligner.Settings implements Cloneable {
        double alphaSubspace = 0.1d;
        double alphaTikhonov = 0.0d;
        boolean constrained = false;
        double deltaMax = 300.0d;
        double deltaMin = 10.0d;
        double displacementMax = 0.2d;
        CvMat gammaTgamma = null;
        double[] lineSearch = {1.0d, 0.25d};
        double stepSize = 0.1d;

        public Settings() {
        }

        public Settings(Settings settings) {
            super(settings);
            this.stepSize = settings.stepSize;
            this.lineSearch = settings.lineSearch;
            this.deltaMin = settings.deltaMin;
            this.deltaMax = settings.deltaMax;
            this.displacementMax = settings.displacementMax;
            this.alphaSubspace = settings.alphaSubspace;
            this.alphaTikhonov = settings.alphaTikhonov;
            this.gammaTgamma = settings.gammaTgamma;
            this.constrained = settings.constrained;
        }

        public double getStepSize() {
            return this.stepSize;
        }

        public void setStepSize(double d) {
            this.stepSize = d;
        }

        public double[] getLineSearch() {
            return this.lineSearch;
        }

        public void setLineSearch(double[] dArr) {
            this.lineSearch = dArr;
        }

        public double getDeltaMin() {
            return this.deltaMin;
        }

        public void setDeltaMin(double d) {
            this.deltaMin = d;
        }

        public double getDeltaMax() {
            return this.deltaMax;
        }

        public void setDeltaMax(double d) {
            this.deltaMax = d;
        }

        public double getDisplacementMax() {
            return this.displacementMax;
        }

        public void setDisplacementMax(double d) {
            this.displacementMax = d;
        }

        public double getAlphaSubspace() {
            return this.alphaSubspace;
        }

        public void setAlphaSubspace(double d) {
            this.alphaSubspace = d;
        }

        public double getAlphaTikhonov() {
            return this.alphaTikhonov;
        }

        public void setAlphaTikhonov(double d) {
            this.alphaTikhonov = d;
        }

        public CvMat getGammaTgamma() {
            return this.gammaTgamma;
        }

        public void setGammaTgamma(CvMat cvMat) {
            this.gammaTgamma = cvMat;
        }

        public Settings clone() {
            return new Settings(this);
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(ImageAligner.Settings settings2) {
        this.settings = (Settings) settings2;
    }

    public IplImage getTemplateImage() {
        return this.template[this.pyramidLevel];
    }

    public void setTemplateImage(IplImage iplImage, double[] dArr) {
        int i = this.settings.pyramidLevelMin;
        int i2 = this.settings.pyramidLevelMax;
        if (dArr == null && iplImage != null) {
            CvMat cvMat = this.srcRoiPts;
            double width = (double) (iplImage.width() << i);
            double height = (double) (iplImage.height() << i);
            cvMat.put(0.0d, 0.0d, width, 0.0d, width, height, 0.0d, height);
        } else if (dArr != null) {
            this.srcRoiPts.put(dArr);
        }
        if (iplImage != null) {
            if (iplImage.depth() == 32) {
                this.template[i] = iplImage;
            } else {
                opencv_core.cvConvertScale(iplImage, this.template[i], 1.0d / iplImage.highValue(), 0.0d);
            }
            for (int i3 = i + 1; i3 <= i2; i3++) {
                IplImage[] iplImageArr = this.template;
                opencv_imgproc.cvPyrDown(iplImageArr[i3 - 1], iplImageArr[i3], 7);
            }
            setPyramidLevel(i2);
        }
    }

    public IplImage getTargetImage() {
        return this.target[this.pyramidLevel];
    }

    public void setTargetImage(IplImage iplImage) {
        IplImage iplImage2 = iplImage;
        int i = this.settings.pyramidLevelMin;
        int i2 = this.settings.pyramidLevelMax;
        if (iplImage2 != null) {
            if (iplImage.depth() == 32) {
                this.target[i] = iplImage2;
            }
            if (this.settings.displacementMax > 0.0d) {
                this.transformer.transform(this.srcRoiPts, this.dstRoiPts, this.parameters, false);
                double[] dArr = this.dstRoiPts.get();
                for (int i3 = 0; i3 < dArr.length; i3++) {
                    dArr[i3] = dArr[i3] / ((double) (1 << i));
                }
                int width = this.target[i].width();
                int height = this.target[i].height();
                this.temproi.x(0).y(0).width(width).height(height);
                int i4 = 1 << (i2 + 1);
                JavaCV.boundingRect(dArr, this.temproi, ((int) Math.round(this.settings.displacementMax * ((double) width))) + 3, ((int) Math.round(this.settings.displacementMax * ((double) height))) + 3, i4, i4);
                opencv_core.cvSetImageROI(iplImage2, this.temproi);
                opencv_core.cvSetImageROI(this.target[i], this.temproi);
            } else {
                opencv_core.cvResetImageROI(iplImage);
                opencv_core.cvResetImageROI(this.target[i]);
            }
            if (iplImage.depth() != 32) {
                opencv_core.cvConvertScale(iplImage, this.target[i], 1.0d / iplImage.highValue(), 0.0d);
                opencv_core.cvResetImageROI(iplImage);
            }
            for (int i5 = i + 1; i5 <= i2; i5++) {
                int i6 = i5 - 1;
                IplROI roi2 = this.target[i6].roi();
                if (roi2 != null) {
                    this.temproi.x(roi2.xOffset() / 2);
                    this.temproi.width(roi2.width() / 2);
                    this.temproi.y(roi2.yOffset() / 2);
                    this.temproi.height(roi2.height() / 2);
                    opencv_core.cvSetImageROI(this.target[i5], this.temproi);
                } else {
                    opencv_core.cvResetImageROI(this.target[i5]);
                }
                IplImage[] iplImageArr = this.target;
                opencv_imgproc.cvPyrDown(iplImageArr[i6], iplImageArr[i5], 7);
            }
            setPyramidLevel(i2);
        }
    }

    public int getPyramidLevel() {
        return this.pyramidLevel;
    }

    public void setPyramidLevel(int i) {
        this.pyramidLevel = i;
        this.residualUpdateNeeded = true;
        this.trials = 0;
    }

    public boolean isConstrained() {
        return this.settings.constrained;
    }

    public void setConstrained(boolean z) {
        if (this.settings.constrained != z || this.hessian == null || this.gradient == null || this.update == null) {
            this.settings.constrained = z;
            int i = z ? this.n + 1 : this.n;
            if (!(this.subspaceParameters == null || this.settings.alphaSubspace == 0.0d)) {
                i += this.subspaceParameters.length;
            }
            this.hessian = CvMat.create(i, i);
            this.gradient = CvMat.create(i, 1);
            this.update = CvMat.create(i, 1);
            this.updateScale = new double[i];
            this.prior = CvMat.create(this.n, 1);
            int i2 = this.n;
            this.constraintGrad = new double[i2];
            this.subspaceResidual = new double[i2];
            int[] iArr = new int[2];
            iArr[1] = i2;
            iArr[0] = i;
            this.subspaceJacobian = (double[][]) Array.newInstance(double.class, iArr);
            this.subspaceCorrelated = new boolean[this.n];
        }
    }

    public ImageTransformer.Parameters getParameters() {
        return this.parameters;
    }

    public void setParameters(ImageTransformer.Parameters parameters2) {
        this.parameters.set(parameters2);
        double[] subspace = parameters2.getSubspace();
        this.subspaceParameters = subspace;
        if (subspace != null && this.settings.alphaSubspace != 0.0d) {
            int i = 0;
            while (true) {
                double[][] dArr = this.tempSubspaceParameters;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = (double[]) this.subspaceParameters.clone();
                i++;
            }
        }
        this.residualUpdateNeeded = true;
    }

    public ImageTransformer.Parameters getPriorParameters() {
        return this.priorParameters;
    }

    public void setPriorParameters(ImageTransformer.Parameters parameters2) {
        this.priorParameters.set(parameters2);
    }

    public double[] getTransformedRoiPts() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.dstRoiPts.get();
    }

    public IplImage getTransformedImage() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.transformed[this.pyramidLevel];
    }

    public IplImage getResidualImage() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.residual[this.pyramidLevel];
    }

    public IplImage getMaskImage() {
        return this.mask[this.pyramidLevel];
    }

    public double getRMSE() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.RMSE;
    }

    public int getPixelCount() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.residualTransformerData[0].dstCount;
    }

    public int getOutlierCount() {
        return this.hessianGradientTransformerData[0].dstCountOutlier;
    }

    public CvRect getRoi() {
        if (this.residualUpdateNeeded) {
            doRoi();
        }
        return this.roi;
    }

    public int getLastLinePosition() {
        return this.lastLinePosition;
    }

    public IplImage[] getImages() {
        this.images[0] = getTemplateImage();
        this.images[1] = getTargetImage();
        this.images[2] = getTransformedImage();
        this.images[3] = getResidualImage();
        this.images[4] = getMaskImage();
        return this.images;
    }

    public boolean iterate(double[] dArr) {
        int i;
        int i2;
        double[] dArr2 = dArr;
        double rmse = getRMSE();
        double[] dArr3 = this.parameters.get();
        double[] dArr4 = this.subspaceParameters;
        double[] dArr5 = null;
        double[] dArr6 = dArr4 == null ? null : (double[]) dArr4.clone();
        if (this.trials == 0 && this.parameters.preoptimize()) {
            setParameters(this.parameters);
            doResidual();
        }
        double[] dArr7 = this.parameters.get();
        double[] dArr8 = this.subspaceParameters;
        if (dArr8 != null) {
            dArr5 = (double[]) dArr8.clone();
        }
        doHessianGradient(this.updateScale);
        int i3 = 0;
        this.lastLinePosition = 0;
        opencv_core.cvSolve(this.hessian, this.gradient, this.update, 1);
        int i4 = 0;
        while (true) {
            i = this.n;
            if (i4 >= i) {
                break;
            }
            ImageTransformer.Parameters parameters2 = this.parameters;
            parameters2.set(i4, parameters2.get(i4) + (this.settings.lineSearch[0] * this.update.get(i4) * this.updateScale[i4]));
            i4++;
        }
        while (i < this.update.length()) {
            double[] dArr9 = this.subspaceParameters;
            int i5 = i - this.n;
            dArr9[i5] = dArr9[i5] + (this.settings.lineSearch[0] * this.update.get(i) * this.updateScale[i]);
            i++;
        }
        this.residualUpdateNeeded = true;
        int i6 = 1;
        while (i6 < this.settings.lineSearch.length && getRMSE() > rmse) {
            this.RMSE = rmse;
            this.parameters.set(dArr7);
            double[] dArr10 = this.subspaceParameters;
            if (dArr10 != null) {
                System.arraycopy(dArr5, i3, dArr10, i3, dArr10.length);
            }
            this.lastLinePosition = i6;
            int i7 = 0;
            while (true) {
                i2 = this.n;
                if (i7 >= i2) {
                    break;
                }
                ImageTransformer.Parameters parameters3 = this.parameters;
                parameters3.set(i7, parameters3.get(i7) + (this.settings.lineSearch[i6] * this.update.get(i7) * this.updateScale[i7]));
                i7++;
            }
            while (i2 < this.update.length()) {
                double[] dArr11 = this.subspaceParameters;
                int i8 = i2 - this.n;
                dArr11[i8] = dArr11[i8] + (this.settings.lineSearch[i6] * this.update.get(i2) * this.updateScale[i2]);
                i2++;
            }
            this.residualUpdateNeeded = true;
            i6++;
            i3 = 0;
        }
        double d = 0.0d;
        if (dArr2 != null) {
            int i9 = 0;
            while (i9 < dArr2.length && i9 < this.updateScale.length) {
                dArr2[i9] = this.settings.lineSearch[this.lastLinePosition] * this.update.get(i9) * this.updateScale[i9];
                i9++;
            }
            d = JavaCV.norm(Arrays.copyOf(dArr2, this.n));
        }
        boolean z = getRMSE() > rmse || d > this.settings.deltaMax || Double.isNaN(this.RMSE) || Double.isInfinite(this.RMSE);
        if (z) {
            this.RMSE = rmse;
            this.parameters.set(dArr3);
            double[] dArr12 = this.subspaceParameters;
            if (dArr12 != null) {
                System.arraycopy(dArr6, 0, dArr12, 0, dArr12.length);
            }
            this.residualUpdateNeeded = true;
        }
        if (z && d > this.settings.deltaMin) {
            int i10 = this.trials + 1;
            this.trials = i10;
            if (i10 < 2) {
                return false;
            }
        }
        if (z || d < this.settings.deltaMin) {
            this.trials = 0;
            if (this.pyramidLevel <= this.settings.pyramidLevelMin) {
                return true;
            }
            setPyramidLevel(this.pyramidLevel - 1);
        } else {
            this.trials = 0;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void doHessianGradient(double[] dArr) {
        final double constraintError = this.parameters.getConstraintError();
        final double d = this.settings.stepSize;
        opencv_core.cvSetZero(this.gradient);
        opencv_core.cvSetZero(this.hessian);
        final double[] dArr2 = dArr;
        Parallel.loop(0, this.n, new Parallel.Looper() {
            public void loop(int i, int i2, int i3) {
                while (i < i2) {
                    GNImageAligner.this.tempParameters[i].set(GNImageAligner.this.parameters);
                    GNImageAligner.this.tempParameters[i].set(i, GNImageAligner.this.tempParameters[i].get(i) + d);
                    dArr2[i] = GNImageAligner.this.tempParameters[i].get(i) - GNImageAligner.this.parameters.get(i);
                    GNImageAligner.this.constraintGrad[i] = GNImageAligner.this.tempParameters[i].getConstraintError() - constraintError;
                    i++;
                }
            }
        });
        for (int i = 0; i < this.n; i++) {
            ImageTransformer.Data data = this.hessianGradientTransformerData[i];
            data.srcImg = this.template[this.pyramidLevel];
            data.subImg = this.transformed[this.pyramidLevel];
            data.srcDotImg = this.residual[this.pyramidLevel];
            data.transImg = null;
            data.dstImg = null;
            data.mask = this.mask[this.pyramidLevel];
            data.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
            data.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
            if (this.settings.thresholdsMulRMSE) {
                data.zeroThreshold *= this.RMSE;
                data.outlierThreshold *= this.RMSE;
            }
            data.pyramidLevel = this.pyramidLevel;
        }
        this.transformer.transform(this.hessianGradientTransformerData, this.roi, this.tempParameters, (boolean[]) null);
        for (int i2 = 0; i2 < this.n; i2++) {
            ImageTransformer.Data data2 = this.hessianGradientTransformerData[i2];
            CvMat cvMat = this.gradient;
            cvMat.put(i2, cvMat.get(i2) - data2.srcDstDot);
            for (int i3 = 0; i3 < this.n; i3++) {
                CvMat cvMat2 = this.hessian;
                cvMat2.put(i2, i3, cvMat2.get(i2, i3) + data2.dstDstDot.get(i3));
            }
        }
        doRegularization(this.updateScale);
    }

    /* access modifiers changed from: protected */
    public void doRegularization(double[] dArr) {
        int i;
        double constraintError = this.parameters.getConstraintError();
        final double d = this.settings.stepSize;
        if (!((this.settings.gammaTgamma == null && this.settings.alphaTikhonov == 0.0d) || this.prior == null || this.priorParameters == null)) {
            for (int i2 = 0; i2 < this.n; i2++) {
                this.prior.put(i2, this.parameters.get(i2) - this.priorParameters.get(i2));
            }
            CvMat cvMat = this.hessian;
            CvMat cvMat2 = this.prior;
            opencv_core.cvMatMul(cvMat, cvMat2, cvMat2);
            for (int i3 = 0; i3 < this.n; i3++) {
                CvMat cvMat3 = this.gradient;
                cvMat3.put(i3, cvMat3.get(i3) + this.prior.get(i3));
            }
        }
        if (this.settings.constrained) {
            double d2 = 0.0d;
            for (double d3 : this.constraintGrad) {
                d2 += d3;
            }
            int i4 = this.n;
            dArr[i4] = ((double) i4) * d2;
            int i5 = 0;
            while (true) {
                i = this.n;
                if (i5 >= i) {
                    break;
                }
                double d4 = this.constraintGrad[i5] * dArr[i];
                this.hessian.put(i5, i, d4);
                this.hessian.put(this.n, i5, d4);
                i5++;
            }
            this.gradient.put(i, (-constraintError) * dArr[i]);
        }
        double[] dArr2 = this.subspaceParameters;
        if (!(dArr2 == null || dArr2.length <= 0 || this.settings.alphaSubspace == 0.0d)) {
            final int length = this.subspaceParameters.length;
            Arrays.fill(this.subspaceCorrelated, false);
            this.tempParameters[0].set(this.parameters);
            this.tempParameters[0].setSubspace(this.subspaceParameters);
            final double[] dArr3 = dArr;
            final int i6 = length;
            Parallel.loop(0, this.n + length, this.tempSubspaceParameters.length, new Parallel.Looper() {
                public void loop(int i, int i2, int i3) {
                    while (i < i2) {
                        if (i < GNImageAligner.this.n) {
                            Arrays.fill(GNImageAligner.this.subspaceJacobian[i], 0.0d);
                            GNImageAligner.this.subspaceJacobian[i][i] = dArr3[i];
                        } else {
                            System.arraycopy(GNImageAligner.this.subspaceParameters, 0, GNImageAligner.this.tempSubspaceParameters[i3], 0, i6);
                            double[] dArr = GNImageAligner.this.tempSubspaceParameters[i3];
                            int i4 = i - GNImageAligner.this.n;
                            dArr[i4] = dArr[i4] + d;
                            GNImageAligner.this.tempParameters[(i - GNImageAligner.this.n) + 1].set(GNImageAligner.this.parameters);
                            GNImageAligner.this.tempParameters[(i - GNImageAligner.this.n) + 1].setSubspace(GNImageAligner.this.tempSubspaceParameters[i3]);
                            dArr3[i] = GNImageAligner.this.tempSubspaceParameters[i3][i - GNImageAligner.this.n] - GNImageAligner.this.subspaceParameters[i - GNImageAligner.this.n];
                            for (int i5 = 0; i5 < GNImageAligner.this.n; i5++) {
                                GNImageAligner.this.subspaceJacobian[i][i5] = GNImageAligner.this.tempParameters[0].get(i5) - GNImageAligner.this.tempParameters[(i - GNImageAligner.this.n) + 1].get(i5);
                                boolean[] zArr = GNImageAligner.this.subspaceCorrelated;
                                zArr[i5] = zArr[i5] | (GNImageAligner.this.subspaceJacobian[i][i5] != 0.0d);
                            }
                        }
                        i++;
                    }
                }
            });
            int i7 = 0;
            for (int i8 = 0; i8 < this.n; i8++) {
                this.subspaceResidual[i8] = this.parameters.get(i8) - this.tempParameters[0].get(i8);
                if (this.subspaceCorrelated[i8]) {
                    i7++;
                }
            }
            double d5 = this.settings.alphaSubspace * this.settings.alphaSubspace;
            double d6 = this.RMSE;
            final double d7 = ((d5 * d6) * d6) / ((double) i7);
            Parallel.loop(0, this.n + length, new Parallel.Looper() {
                public void loop(int i, int i2, int i3) {
                    int i4;
                    double d;
                    while (i < i2) {
                        if (i >= GNImageAligner.this.n || GNImageAligner.this.subspaceCorrelated[i]) {
                            int i5 = i;
                            while (true) {
                                i4 = 0;
                                d = 0.0d;
                                if (i5 >= GNImageAligner.this.n + length) {
                                    break;
                                }
                                if (i5 >= GNImageAligner.this.n || GNImageAligner.this.subspaceCorrelated[i5]) {
                                    while (i4 < GNImageAligner.this.n) {
                                        d += GNImageAligner.this.subspaceJacobian[i][i4] * GNImageAligner.this.subspaceJacobian[i5][i4];
                                        i4++;
                                    }
                                    double d2 = GNImageAligner.this.hessian.get(i, i5) + (d7 * d);
                                    GNImageAligner.this.hessian.put(i, i5, d2);
                                    GNImageAligner.this.hessian.put(i5, i, d2);
                                }
                                i5++;
                            }
                            while (i4 < GNImageAligner.this.n) {
                                d -= GNImageAligner.this.subspaceJacobian[i][i4] * GNImageAligner.this.subspaceResidual[i4];
                                i4++;
                            }
                            GNImageAligner.this.gradient.put(i, GNImageAligner.this.gradient.get(i) + (d7 * d));
                        }
                        i++;
                    }
                }
            });
        }
        int rows = this.hessian.rows();
        int cols = this.hessian.cols();
        int i9 = 0;
        while (i9 < rows) {
            int i10 = 0;
            while (i10 < cols) {
                this.hessian.put(i9, i10, this.hessian.get(i9, i10) + ((this.settings.gammaTgamma == null || i9 >= this.settings.gammaTgamma.rows() || i10 >= this.settings.gammaTgamma.cols()) ? 0.0d : this.settings.gammaTgamma.get(i9, i10)) + ((i9 != i10 || i9 >= this.n) ? 0.0d : this.settings.alphaTikhonov * this.settings.alphaTikhonov));
                i10++;
            }
            i9++;
        }
    }

    /* access modifiers changed from: protected */
    public void doRoi() {
        this.transformer.transform(this.srcRoiPts, this.dstRoiPts, this.parameters, false);
        double[] dArr = this.dstRoiPts.get();
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] / ((double) (1 << this.pyramidLevel));
        }
        this.roi.x(0).y(0).width(this.mask[this.pyramidLevel].width()).height(this.mask[this.pyramidLevel].height());
        JavaCV.boundingRect(dArr, this.roi, 3, 3, 16, 1);
        opencv_core.cvSetZero(this.mask[this.pyramidLevel]);
        this.dstRoiPtsArray.put((byte) Tnaf.POW_2_WIDTH, dArr);
        opencv_imgproc.cvFillConvexPoly((CvArr) this.mask[this.pyramidLevel], this.dstRoiPtsArray, 4, CvScalar.WHITE, 8, 16);
    }

    /* access modifiers changed from: protected */
    public void doResidual() {
        this.parameters.getConstraintError();
        ImageTransformer.Data data = this.residualTransformerData[0];
        data.srcImg = this.template[this.pyramidLevel];
        data.subImg = this.target[this.pyramidLevel];
        data.srcDotImg = null;
        data.transImg = this.transformed[this.pyramidLevel];
        data.dstImg = this.residual[this.pyramidLevel];
        data.mask = this.mask[this.pyramidLevel];
        data.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
        data.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
        if (this.settings.thresholdsMulRMSE) {
            data.zeroThreshold *= this.RMSE;
            data.outlierThreshold *= this.RMSE;
        }
        data.pyramidLevel = this.pyramidLevel;
        this.transformer.transform(this.residualTransformerData, this.roi, this.parametersArray, (boolean[]) null);
        double d = this.residualTransformerData[0].dstDstDot.get(0);
        int i = this.residualTransformerData[0].dstCount;
        this.RMSE = i < this.n ? Double.NaN : Math.sqrt(d / ((double) i));
        this.residualUpdateNeeded = false;
    }
}
