package org.bytedeco.javacv;

import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLEventList;
import com.jogamp.opencl.CLImage2d;
import com.jogamp.opencl.CLKernel;
import com.jogamp.opencl.CLMemory;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.ImageTransformerCL;
import org.bytedeco.opencv.opencv_core.CvMat;

public class ProjectiveColorTransformerCL extends ProjectiveColorTransformer implements ImageTransformerCL {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final CLBuffer<FloatBuffer> HBuffer;
    protected final CLBuffer<FloatBuffer> XBuffer;
    protected final JavaCVCL context;
    private CLKernel dotKernel;
    private CLKernel oneKernel;
    private CLKernel reduceKernel;
    private CLKernel subKernel;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProjectiveColorTransformerCL(JavaCVCL javaCVCL, CvMat cvMat, CvMat cvMat2, CvMat cvMat3, CvMat cvMat4, CvMat cvMat5, double[] dArr, double[] dArr2, CvMat cvMat6, int i, int i2) {
        super(cvMat, cvMat2, cvMat3, cvMat4, cvMat5, dArr, dArr2, cvMat6, i, i2);
        int size = createParameters().size();
        this.context = javaCVCL;
        this.HBuffer = javaCVCL.getCLContext().createFloatBuffer(size * 9, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY});
        this.XBuffer = javaCVCL.getCLContext().createFloatBuffer(size * 16, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY});
        if (getClass() == ProjectiveColorTransformerCL.class) {
            CLKernel[] buildKernels = javaCVCL.buildKernels("-cl-fast-relaxed-math -cl-mad-enable -DDOT_SIZE=" + size, "ImageTransformer.cl:ProjectiveColorTransformer.cl", "transformOne", "transformSub", "transformDot", "reduceOutputData");
            this.oneKernel = buildKernels[0];
            this.subKernel = buildKernels[1];
            this.dotKernel = buildKernels[2];
            this.reduceKernel = buildKernels[3];
        }
    }

    public JavaCVCL getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public void prepareHomographies(CLBuffer cLBuffer, int i, ImageTransformer.Parameters[] parametersArr, boolean[] zArr) {
        FloatBuffer floatBuffer = (FloatBuffer) cLBuffer.getBuffer().rewind();
        CvMat cvMat = (CvMat) H3x3.get();
        for (int i2 = 0; i2 < parametersArr.length; i2++) {
            prepareHomography(cvMat, i, parametersArr[i2], zArr == null ? false : zArr[i2]);
            for (int i3 = 0; i3 < 9; i3++) {
                floatBuffer.put((float) cvMat.get(i3));
            }
        }
        floatBuffer.rewind();
    }

    /* access modifiers changed from: protected */
    public void prepareColorTransforms(CLBuffer cLBuffer, int i, ImageTransformer.Parameters[] parametersArr, boolean[] zArr) {
        FloatBuffer floatBuffer = (FloatBuffer) cLBuffer.getBuffer().rewind();
        CvMat cvMat = (CvMat) X24x4.get();
        for (int i2 = 0; i2 < parametersArr.length; i2++) {
            prepareColorTransform(cvMat, i, parametersArr[i2], zArr == null ? false : zArr[i2]);
            for (int i3 = 0; i3 < 16; i3++) {
                floatBuffer.put((float) cvMat.get(i3));
            }
        }
        floatBuffer.rewind();
    }

    public void transform(CLImage2d cLImage2d, CLImage2d cLImage2d2, CLImage2d cLImage2d3, CLImage2d cLImage2d4, CLImage2d cLImage2d5, CLImage2d cLImage2d6, ImageTransformer.Parameters[] parametersArr, boolean[] zArr, ImageTransformerCL.InputData inputData, ImageTransformerCL.OutputData outputData) {
        CLKernel cLKernel;
        CLImage2d cLImage2d7 = cLImage2d;
        CLImage2d cLImage2d8 = cLImage2d2;
        CLImage2d cLImage2d9 = cLImage2d3;
        CLImage2d cLImage2d10 = cLImage2d5;
        CLImage2d cLImage2d11 = cLImage2d6;
        ImageTransformer.Parameters[] parametersArr2 = parametersArr;
        boolean[] zArr2 = zArr;
        ImageTransformerCL.InputData inputData2 = inputData;
        ImageTransformerCL.OutputData outputData2 = outputData;
        prepareHomographies(this.HBuffer, inputData2.pyramidLevel, parametersArr2, zArr2);
        prepareColorTransforms(this.XBuffer, inputData2.pyramidLevel, parametersArr2, zArr2);
        int size = parametersArr2[0].size();
        int i = 32;
        if (parametersArr2.length > 1) {
            i = parametersArr2.length;
        } else if (inputData2.roiWidth > 32) {
            i = 64;
        }
        int alignCeil = JavaCVCL.alignCeil(inputData2.roiWidth, i);
        int i2 = alignCeil / i;
        CLBuffer<ByteBuffer> buffer = inputData2.getBuffer(this.context);
        CLBuffer<ByteBuffer> buffer2 = outputData2.getBuffer(this.context, size, i2);
        CLEventList cLEventList = new CLEventList(1);
        int i3 = i2;
        this.context.writeBuffer(this.HBuffer, false);
        this.context.writeBuffer(this.XBuffer, false);
        if (inputData2.autoWrite) {
            inputData2.writeBuffer(this.context);
        }
        if (cLImage2d8 == null) {
            CLKernel putArg = this.oneKernel.putArg(cLImage2d7);
            if (cLImage2d10 == null) {
                cLImage2d10 = cLImage2d4;
            }
            cLKernel = putArg.putArg(cLImage2d10).putArg(cLImage2d11).putArg(this.HBuffer).putArg(this.XBuffer).putArg(buffer).putArg(buffer2).rewind();
        } else if (cLImage2d9 == null) {
            cLKernel = this.subKernel.putArg(cLImage2d7).putArg(cLImage2d8).putArg(cLImage2d4).putArg(cLImage2d10).putArg(cLImage2d11).putArg(this.HBuffer).putArg(this.XBuffer).putArg(buffer).putArg(buffer2).rewind();
        } else {
            cLKernel = this.dotKernel.putArg(cLImage2d7).putArg(cLImage2d8).putArg(cLImage2d9).putArg(cLImage2d11).putArg(this.HBuffer).putArg(this.XBuffer).putArg(buffer).putArg(buffer2).rewind();
        }
        this.context.executeKernel(cLKernel, (long) inputData2.roiX, 0, 0, (long) alignCeil, 1, (long) parametersArr2.length, (long) i, 1, (long) parametersArr2.length, cLEventList);
        int i4 = i3;
        if (i4 > 1) {
            this.reduceKernel.putArg(buffer2).rewind();
            long j = (long) i4;
            this.context.executeKernel(this.reduceKernel, 0, j, j);
        }
        ImageTransformerCL.OutputData outputData3 = outputData;
        if (outputData3.autoRead) {
            outputData3.readBuffer(this.context);
        }
    }
}
