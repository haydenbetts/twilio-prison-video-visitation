package org.bytedeco.javacv;

import com.jogamp.opencl.CLImage2d;
import com.jogamp.opencl.CLImageFormat;
import com.jogamp.opencl.CLMemory;
import com.jogamp.opencl.gl.CLGLContext;
import com.jogamp.opencl.gl.CLGLImage2d;
import com.jogamp.opengl.GL2;
import java.io.PrintStream;
import java.util.Arrays;
import org.bytedeco.javacv.GNImageAligner;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.ImageTransformerCL;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.IplImage;

public class GNImageAlignerCL extends GNImageAligner implements ImageAlignerCL {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final JavaCVCL context;
    private CLImage2d[] imagesCL;
    private ImageTransformerCL.InputData inputData;
    private CLGLImage2d[] maskCL;
    private int[] maskfb;
    private int[] maskrb;
    private ImageTransformerCL.OutputData outputData;
    private CLImage2d[] residualCL;
    private CLImage2d[] targetCL;
    private CLImage2d[] templateCL;
    private boolean[] templateChanged;
    private CLImage2d[] transformedCL;

    public GNImageAlignerCL(ImageTransformerCL imageTransformerCL, ImageTransformer.Parameters parameters, CLImage2d cLImage2d, double[] dArr, CLImage2d cLImage2d2) {
        this(imageTransformerCL, parameters, cLImage2d, dArr, cLImage2d2, new GNImageAligner.Settings());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GNImageAlignerCL(ImageTransformerCL imageTransformerCL, ImageTransformer.Parameters parameters, CLImage2d cLImage2d, double[] dArr, CLImage2d cLImage2d2, GNImageAligner.Settings settings) {
        super(imageTransformerCL, parameters);
        CLImage2d cLImage2d3 = cLImage2d;
        CLImage2d cLImage2d4 = cLImage2d2;
        GNImageAligner.Settings settings2 = settings;
        this.imagesCL = new CLImage2d[5];
        setSettings(settings2);
        JavaCVCL context2 = imageTransformerCL.getContext();
        this.context = context2;
        int i = settings2.pyramidLevelMin;
        int i2 = settings2.pyramidLevelMax;
        int i3 = i2 + 1;
        this.template = new IplImage[i3];
        this.target = new IplImage[i3];
        this.transformed = new IplImage[i3];
        this.residual = new IplImage[i3];
        this.mask = new IplImage[i3];
        this.templateCL = new CLImage2d[i3];
        this.targetCL = new CLImage2d[i3];
        this.transformedCL = new CLImage2d[i3];
        this.residualCL = new CLImage2d[i3];
        this.maskCL = new CLGLImage2d[i3];
        this.maskrb = new int[i3];
        this.maskfb = new int[i3];
        int i4 = cLImage2d3 != null ? cLImage2d3.width : cLImage2d4.width;
        int i5 = cLImage2d3 != null ? cLImage2d3.height : cLImage2d4.height;
        CLGLContext cLGLContext = context2.getCLGLContext();
        GL2 gl2 = context2.getGL2();
        int i6 = 0;
        gl2.glGenRenderbuffers(i3, this.maskrb, 0);
        gl2.glGenFramebuffers(i3, this.maskfb, 0);
        CLImageFormat cLImageFormat = new CLImageFormat(CLImageFormat.ChannelOrder.RGBA, CLImageFormat.ChannelType.FLOAT);
        int i7 = i;
        while (i7 <= i2) {
            this.templateCL[i7] = (i7 != i || cLImage2d3 == null) ? cLGLContext.createImage2d(i4, i5, cLImageFormat, new CLMemory.Mem[i6]) : cLImage2d3;
            this.targetCL[i7] = (i7 != i || cLImage2d4 == null) ? cLGLContext.createImage2d(i4, i5, cLImageFormat, new CLMemory.Mem[i6]) : cLImage2d4;
            this.transformedCL[i7] = cLGLContext.createImage2d(i4, i5, cLImageFormat, new CLMemory.Mem[i6]);
            this.residualCL[i7] = cLGLContext.createImage2d(i4, i5, cLImageFormat, new CLMemory.Mem[i6]);
            gl2.glBindRenderbuffer(36161, this.maskrb[i7]);
            gl2.glBindFramebuffer(36160, this.maskfb[i7]);
            gl2.glRenderbufferStorage(36161, 32832, i4, i5);
            int i8 = i;
            gl2.glFramebufferRenderbuffer(36160, 36064, 36161, this.maskrb[i7]);
            this.maskCL[i7] = cLGLContext.createFromGLRenderbuffer(this.maskrb[i7], new CLMemory.Mem[0]);
            PrintStream printStream = System.out;
            printStream.println(this.maskCL[i7] + " " + this.maskCL[i7].getElementSize() + " " + this.maskCL[i7].getFormat());
            i4 /= 2;
            i5 /= 2;
            i7++;
            i = i8;
            i6 = 0;
        }
        this.inputData = new ImageTransformerCL.InputData();
        this.outputData = new ImageTransformerCL.OutputData(false);
        boolean[] zArr = new boolean[i3];
        this.templateChanged = zArr;
        Arrays.fill(zArr, true);
        setConstrained(settings2.constrained);
        setTemplateImageCL(cLImage2d3, dArr);
        setTargetImageCL(cLImage2d4);
    }

    public void release() {
        int i = this.settings.pyramidLevelMin;
        int i2 = this.settings.pyramidLevelMax;
        if (!(this.templateCL == null || this.targetCL == null || this.transformedCL == null || this.residualCL == null || this.maskCL == null)) {
            for (int i3 = i; i3 <= i2; i3++) {
                if (i3 > i) {
                    this.templateCL[i3].release();
                }
                if (i3 > i) {
                    this.targetCL[i3].release();
                }
                this.transformedCL[i3].release();
                this.residualCL[i3].release();
                this.maskCL[i3].release();
            }
            this.maskCL = null;
            this.residualCL = null;
            this.transformedCL = null;
            this.targetCL = null;
            this.templateCL = null;
        }
        this.context.getGLContext().makeCurrent();
        GL2 gl2 = this.context.getGL2();
        int[] iArr = this.maskfb;
        if (iArr != null) {
            gl2.glDeleteFramebuffers(i2 + 1, iArr, 0);
            this.maskfb = null;
        }
        int[] iArr2 = this.maskrb;
        if (iArr2 != null) {
            gl2.glDeleteRenderbuffers(i2 + 1, iArr2, 0);
            this.maskrb = null;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public IplImage getTemplateImage() {
        return getTemplateImage(true);
    }

    public IplImage getTemplateImage(boolean z) {
        if (!this.templateChanged[this.pyramidLevel]) {
            return this.template[this.pyramidLevel];
        }
        this.templateChanged[this.pyramidLevel] = false;
        IplImage[] iplImageArr = this.template;
        int i = this.pyramidLevel;
        IplImage readImage = this.context.readImage(getTemplateImageCL(), this.template[this.pyramidLevel], z);
        iplImageArr[i] = readImage;
        return readImage;
    }

    public void setTemplateImage(IplImage iplImage, double[] dArr) {
        this.context.writeImage(this.templateCL[this.settings.pyramidLevelMin], iplImage, false);
        setTemplateImageCL(this.templateCL[this.settings.pyramidLevelMin], dArr);
    }

    public IplImage getTargetImage() {
        return getTargetImage(true);
    }

    public IplImage getTargetImage(boolean z) {
        IplImage[] iplImageArr = this.target;
        int i = this.pyramidLevel;
        IplImage readImage = this.context.readImage(getTargetImageCL(), this.target[this.pyramidLevel], z);
        iplImageArr[i] = readImage;
        return readImage;
    }

    public void setTargetImage(IplImage iplImage) {
        this.context.writeImage(this.targetCL[this.settings.pyramidLevelMin], iplImage, false);
        setTargetImageCL(this.targetCL[this.settings.pyramidLevelMin]);
    }

    public IplImage getTransformedImage() {
        return getTransformedImage(true);
    }

    public IplImage getTransformedImage(boolean z) {
        IplImage[] iplImageArr = this.transformed;
        int i = this.pyramidLevel;
        IplImage readImage = this.context.readImage(getTransformedImageCL(), this.transformed[this.pyramidLevel], z);
        iplImageArr[i] = readImage;
        return readImage;
    }

    public IplImage getResidualImage() {
        return getResidualImage(true);
    }

    public IplImage getResidualImage(boolean z) {
        IplImage[] iplImageArr = this.residual;
        int i = this.pyramidLevel;
        IplImage readImage = this.context.readImage(getResidualImageCL(), this.residual[this.pyramidLevel], z);
        iplImageArr[i] = readImage;
        return readImage;
    }

    public IplImage getMaskImage() {
        return getMaskImage(true);
    }

    public IplImage getMaskImage(boolean z) {
        this.context.acquireGLObject(this.maskCL[this.pyramidLevel]);
        this.mask[this.pyramidLevel] = this.context.readImage(getMaskImageCL(), this.mask[this.pyramidLevel], z);
        this.context.releaseGLObject(this.maskCL[this.pyramidLevel]);
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
        return this.outputData.dstCount;
    }

    public int getOutlierCount() {
        return this.outputData.dstCountOutlier;
    }

    public CvRect getRoi() {
        if (this.residualUpdateNeeded) {
            doRoi();
        }
        return this.roi.x(this.inputData.roiX).y(this.inputData.roiY).width(this.inputData.roiWidth).height(this.inputData.roiHeight);
    }

    public IplImage[] getImages() {
        return getImages(true);
    }

    public IplImage[] getImages(boolean z) {
        this.images[0] = getTemplateImage(false);
        this.images[1] = getTargetImage(false);
        this.images[2] = getTransformedImage(false);
        this.images[3] = getResidualImage(false);
        this.images[4] = getMaskImage(z);
        return this.images;
    }

    public CLImage2d getTemplateImageCL() {
        return this.templateCL[this.pyramidLevel];
    }

    public void setTemplateImageCL(CLImage2d cLImage2d, double[] dArr) {
        int i = this.settings.pyramidLevelMin;
        int i2 = this.settings.pyramidLevelMax;
        if (dArr != null || cLImage2d == null) {
            this.srcRoiPts.put(dArr);
        } else {
            CvMat cvMat = this.srcRoiPts;
            double d = (double) (cLImage2d.width << i);
            double d2 = (double) (cLImage2d.height << i);
            cvMat.put(0.0d, 0.0d, d, 0.0d, d, d2, 0.0d, d2);
        }
        if (cLImage2d != null) {
            this.templateCL[i] = cLImage2d;
            for (int i3 = i + 1; i3 <= i2; i3++) {
                JavaCVCL javaCVCL = this.context;
                CLImage2d[] cLImage2dArr = this.templateCL;
                javaCVCL.pyrDown(cLImage2dArr[i3 - 1], cLImage2dArr[i3]);
            }
            setPyramidLevel(i2);
            Arrays.fill(this.templateChanged, true);
        }
    }

    public CLImage2d getTargetImageCL() {
        return this.targetCL[this.pyramidLevel];
    }

    public void setTargetImageCL(CLImage2d cLImage2d) {
        int i = this.settings.pyramidLevelMin;
        int i2 = this.settings.pyramidLevelMax;
        this.targetCL[i] = cLImage2d;
        while (true) {
            i++;
            if (i <= i2) {
                JavaCVCL javaCVCL = this.context;
                CLImage2d[] cLImage2dArr = this.targetCL;
                javaCVCL.pyrDown(cLImage2dArr[i - 1], cLImage2dArr[i]);
            } else {
                setPyramidLevel(i2);
                return;
            }
        }
    }

    public CLImage2d getTransformedImageCL() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.transformedCL[this.pyramidLevel];
    }

    public CLImage2d getResidualImageCL() {
        if (this.residualUpdateNeeded) {
            doRoi();
            doResidual();
        }
        return this.residualCL[this.pyramidLevel];
    }

    public CLImage2d getMaskImageCL() {
        return this.maskCL[this.pyramidLevel];
    }

    public CLImage2d[] getImagesCL() {
        this.imagesCL[0] = this.templateCL[this.pyramidLevel];
        this.imagesCL[1] = this.targetCL[this.pyramidLevel];
        this.imagesCL[2] = this.transformedCL[this.pyramidLevel];
        this.imagesCL[3] = this.residualCL[this.pyramidLevel];
        this.imagesCL[4] = this.maskCL[this.pyramidLevel];
        return this.imagesCL;
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
                    GNImageAlignerCL.this.tempParameters[i].set(GNImageAlignerCL.this.parameters);
                    GNImageAlignerCL.this.tempParameters[i].set(i, GNImageAlignerCL.this.tempParameters[i].get(i) + d);
                    dArr2[i] = GNImageAlignerCL.this.tempParameters[i].get(i) - GNImageAlignerCL.this.parameters.get(i);
                    GNImageAlignerCL.this.constraintGrad[i] = GNImageAlignerCL.this.tempParameters[i].getConstraintError() - constraintError;
                    i++;
                }
            }
        });
        this.inputData.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
        this.inputData.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
        if (this.settings.thresholdsMulRMSE) {
            this.inputData.zeroThreshold *= this.RMSE;
            this.inputData.outlierThreshold *= this.RMSE;
        }
        this.inputData.pyramidLevel = this.pyramidLevel;
        this.context.acquireGLObject(this.maskCL[this.pyramidLevel]);
        ((ImageTransformerCL) this.transformer).transform(this.templateCL[this.pyramidLevel], this.transformedCL[this.pyramidLevel], this.residualCL[this.pyramidLevel], (CLImage2d) null, (CLImage2d) null, this.maskCL[this.pyramidLevel], this.tempParameters, (boolean[]) null, this.inputData, this.outputData);
        this.context.releaseGLObject(this.maskCL[this.pyramidLevel]);
        doRegularization(this.updateScale);
        this.outputData.readBuffer(this.context);
        for (int i = 0; i < this.n; i++) {
            this.gradient.put(i, this.gradient.get(i) - ((double) this.outputData.srcDstDot.get(i)));
            for (int i2 = 0; i2 < this.n; i2++) {
                this.hessian.put(i, i2, this.hessian.get(i, i2) + ((double) this.outputData.dstDstDot.get((this.n * i) + i2)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doRoi() {
        this.transformer.transform(this.srcRoiPts, this.dstRoiPts, this.parameters, false);
        double[] dArr = this.dstRoiPts.get();
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] / ((double) (1 << this.pyramidLevel));
        }
        this.roi.x(0).y(0).width(this.maskCL[this.pyramidLevel].width).height(this.maskCL[this.pyramidLevel].height);
        JavaCV.boundingRect(dArr, this.roi, 3, 3, 16, 1);
        this.inputData.roiX = this.roi.x();
        this.inputData.roiY = this.roi.y();
        this.inputData.roiWidth = this.roi.width();
        this.inputData.roiHeight = this.roi.height();
        GL2 gl2 = this.context.getGL2();
        gl2.glBindFramebuffer(36160, this.maskfb[this.pyramidLevel]);
        gl2.glMatrixMode(5889);
        gl2.glLoadIdentity();
        this.context.getGLU().gluOrtho2D(0.0f, (float) this.maskCL[this.pyramidLevel].width, 0.0f, (float) this.maskCL[this.pyramidLevel].height);
        gl2.glMatrixMode(5888);
        gl2.glLoadIdentity();
        gl2.glViewport(0, 0, this.maskCL[this.pyramidLevel].width, this.maskCL[this.pyramidLevel].height);
        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl2.glClear(16384);
        gl2.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl2.glBegin(9);
        gl2.glVertex2d(dArr[0], dArr[1]);
        gl2.glVertex2d(dArr[2] + 1.0d, dArr[3]);
        gl2.glVertex2d(dArr[4] + 1.0d, dArr[5] + 1.0d);
        gl2.glVertex2d(dArr[6], dArr[7] + 1.0d);
        gl2.glEnd();
    }

    /* access modifiers changed from: protected */
    public void doResidual() {
        this.parameters.getConstraintError();
        this.inputData.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
        this.inputData.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
        if (this.settings.thresholdsMulRMSE) {
            this.inputData.zeroThreshold *= this.RMSE;
            this.inputData.outlierThreshold *= this.RMSE;
        }
        this.inputData.pyramidLevel = this.pyramidLevel;
        this.context.acquireGLObject(this.maskCL[this.pyramidLevel]);
        ((ImageTransformerCL) this.transformer).transform(this.templateCL[this.pyramidLevel], this.targetCL[this.pyramidLevel], (CLImage2d) null, this.transformedCL[this.pyramidLevel], this.residualCL[this.pyramidLevel], this.maskCL[this.pyramidLevel], this.parametersArray, (boolean[]) null, this.inputData, this.outputData);
        this.context.releaseGLObject(this.maskCL[this.pyramidLevel]);
        this.outputData.readBuffer(this.context);
        double d = (double) this.outputData.dstDstDot.get(0);
        int i = this.outputData.dstCount;
        this.RMSE = i < this.n ? Double.NaN : Math.sqrt(d / ((double) i));
        this.residualUpdateNeeded = false;
    }
}
