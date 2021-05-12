package org.bytedeco.javacv;

import com.jogamp.common.os.Platform;
import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLEventList;
import com.jogamp.opencl.CLImage2d;
import com.jogamp.opencl.CLImageFormat;
import com.jogamp.opencl.CLKernel;
import com.jogamp.opencl.CLMemory;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.ImageTransformerCL;
import org.bytedeco.javacv.ProCamTransformer;
import org.bytedeco.opencv.opencv_core.CvMat;

public class ProCamTransformerCL extends ProCamTransformer implements ImageTransformerCL {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ThreadLocal<CvMat> H13x3 = CvMat.createThreadLocal(3, 3);
    private static final ThreadLocal<CvMat> H23x3 = CvMat.createThreadLocal(3, 3);
    private static final ThreadLocal<CvMat> X4x4 = CvMat.createThreadLocal(4, 4);
    protected final CLBuffer<FloatBuffer> H1Buffer;
    protected final CLBuffer<FloatBuffer> H2Buffer;
    protected final CLBuffer<FloatBuffer> XBuffer;
    protected final JavaCVCL context;
    private CLKernel dotKernel;
    protected final int nullSize;
    private CLKernel oneKernel;
    protected CLImage2d[] projectorImageCL;
    private CLKernel reduceKernel;
    private CLKernel subKernel;
    protected CLImage2d[] surfaceImageCL;

    public ProCamTransformerCL(JavaCVCL javaCVCL, double[] dArr, CameraDevice cameraDevice, ProjectorDevice projectorDevice) {
        this(javaCVCL, dArr, cameraDevice, projectorDevice, (CvMat) null);
    }

    public ProCamTransformerCL(JavaCVCL javaCVCL, double[] dArr, CameraDevice cameraDevice, ProjectorDevice projectorDevice, CvMat cvMat) {
        super(dArr, cameraDevice, projectorDevice, cvMat);
        CLBuffer<FloatBuffer> cLBuffer = null;
        this.projectorImageCL = null;
        this.surfaceImageCL = null;
        int size = createParameters().size();
        this.context = javaCVCL;
        this.nullSize = Platform.is32Bit() ? 4 : 8;
        this.H1Buffer = this.surfaceTransformer != null ? javaCVCL.getCLContext().createFloatBuffer(size * 9, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY}) : cLBuffer;
        this.H2Buffer = javaCVCL.getCLContext().createFloatBuffer(size * 9, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY});
        this.XBuffer = javaCVCL.getCLContext().createFloatBuffer(size * 16, new CLMemory.Mem[]{CLMemory.Mem.READ_ONLY});
        if (getClass() == ProCamTransformerCL.class) {
            CLKernel[] buildKernels = javaCVCL.buildKernels("-cl-fast-relaxed-math -cl-mad-enable -cl-nv-maxrregcount=32 -DDOT_SIZE=" + size, "ImageTransformer.cl:ProCamTransformer.cl", "transformOne", "transformSub", "transformDot", "reduceOutputData");
            this.oneKernel = buildKernels[0];
            this.subKernel = buildKernels[1];
            this.dotKernel = buildKernels[2];
            this.reduceKernel = buildKernels[3];
        }
    }

    public JavaCVCL getContext() {
        return this.context;
    }

    public ProjectiveColorTransformerCL getSurfaceTransformerCL() {
        return (ProjectiveColorTransformerCL) this.surfaceTransformer;
    }

    public ProjectiveColorTransformerCL getProjectorTransformerCL() {
        return (ProjectiveColorTransformerCL) this.projectorTransformer;
    }

    public CLImage2d getProjectorImageCL(int i) {
        return this.projectorImageCL[i];
    }

    public void setProjectorImageCL(CLImage2d cLImage2d, int i, int i2) {
        CLImage2d[] cLImage2dArr = this.projectorImageCL;
        if (cLImage2dArr == null || cLImage2dArr.length != i2 + 1) {
            this.projectorImageCL = new CLImage2d[(i2 + 1)];
        }
        this.projectorImageCL[i] = cLImage2d;
        while (true) {
            i++;
            if (i <= i2) {
                CLImage2d[] cLImage2dArr2 = this.projectorImageCL;
                if (cLImage2dArr2[i] == null) {
                    int i3 = i - 1;
                    this.projectorImageCL[i] = this.context.getCLContext().createImage2d(cLImage2dArr2[i3].width / 2, this.projectorImageCL[i3].height / 2, new CLImageFormat(CLImageFormat.ChannelOrder.RGBA, CLImageFormat.ChannelType.FLOAT), new CLMemory.Mem[0]);
                }
                JavaCVCL javaCVCL = this.context;
                CLImage2d[] cLImage2dArr3 = this.projectorImageCL;
                javaCVCL.pyrDown(cLImage2dArr3[i - 1], cLImage2dArr3[i]);
            } else {
                return;
            }
        }
    }

    public CLImage2d getSurfaceImageCL(int i) {
        return this.surfaceImageCL[i];
    }

    public void setSurfaceImageCL(CLImage2d cLImage2d, int i) {
        CLImage2d[] cLImage2dArr = this.surfaceImageCL;
        if (cLImage2dArr == null || cLImage2dArr.length != i) {
            this.surfaceImageCL = new CLImage2d[i];
        }
        this.surfaceImageCL[0] = cLImage2d;
        for (int i2 = 1; i2 < i; i2++) {
            CLImage2d[] cLImage2dArr2 = this.surfaceImageCL;
            if (cLImage2dArr2[i2] == null) {
                int i3 = i2 - 1;
                this.surfaceImageCL[i2] = this.context.getCLContext().createImage2d(cLImage2dArr2[i3].width / 2, this.surfaceImageCL[i3].height / 2, new CLImageFormat(CLImageFormat.ChannelOrder.RGBA, CLImageFormat.ChannelType.FLOAT), new CLMemory.Mem[0]);
            }
            JavaCVCL javaCVCL = this.context;
            CLImage2d[] cLImage2dArr3 = this.surfaceImageCL;
            javaCVCL.pyrDown(cLImage2dArr3[i2 - 1], cLImage2dArr3[i2]);
        }
    }

    /* access modifiers changed from: protected */
    public void prepareTransforms(CLBuffer cLBuffer, CLBuffer cLBuffer2, CLBuffer cLBuffer3, int i, ImageTransformer.Parameters[] parametersArr) {
        ImageTransformer.Parameters[] parametersArr2 = parametersArr;
        FloatBuffer floatBuffer = this.surfaceTransformer == null ? null : (FloatBuffer) cLBuffer.getBuffer().rewind();
        FloatBuffer floatBuffer2 = (FloatBuffer) cLBuffer2.getBuffer().rewind();
        FloatBuffer floatBuffer3 = (FloatBuffer) cLBuffer3.getBuffer().rewind();
        CvMat cvMat = H13x3.get();
        CvMat cvMat2 = H23x3.get();
        CvMat cvMat3 = X4x4.get();
        int i2 = 0;
        while (i2 < parametersArr2.length) {
            int i3 = i2;
            prepareTransforms(this.surfaceTransformer == null ? null : cvMat, cvMat2, cvMat3, i, (ProCamTransformer.Parameters) parametersArr2[i2]);
            for (int i4 = 0; i4 < 9; i4++) {
                if (this.surfaceTransformer != null) {
                    floatBuffer.put((float) cvMat.get(i4));
                }
                floatBuffer2.put((float) cvMat2.get(i4));
            }
            for (int i5 = 0; i5 < 16; i5++) {
                floatBuffer3.put((float) cvMat3.get(i5));
            }
            i2 = i3 + 1;
        }
        if (this.surfaceTransformer != null) {
            floatBuffer.rewind();
        }
        floatBuffer2.rewind();
        floatBuffer3.rewind();
    }

    public void transform(CLImage2d cLImage2d, CLImage2d cLImage2d2, CLImage2d cLImage2d3, CLImage2d cLImage2d4, CLImage2d cLImage2d5, CLImage2d cLImage2d6, ImageTransformer.Parameters[] parametersArr, boolean[] zArr, ImageTransformerCL.InputData inputData, ImageTransformerCL.OutputData outputData) {
        int i;
        boolean z;
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
        if (zArr2 != null) {
            int i2 = 0;
            while (i2 < zArr2.length) {
                if (!zArr2[i2]) {
                    i2++;
                } else {
                    throw new UnsupportedOperationException("Inverse transform not supported.");
                }
            }
        }
        prepareTransforms(this.H1Buffer, this.H2Buffer, this.XBuffer, inputData2.pyramidLevel, parametersArr);
        int size = parametersArr2[0].size();
        int i3 = 32;
        if (parametersArr2.length > 1) {
            i3 = parametersArr2.length;
        } else if (inputData2.roiWidth > 32) {
            i3 = 64;
        }
        int alignCeil = JavaCVCL.alignCeil(inputData2.roiWidth, i3);
        int i4 = alignCeil / i3;
        CLBuffer<ByteBuffer> buffer = inputData2.getBuffer(this.context);
        CLBuffer<ByteBuffer> buffer2 = outputData2.getBuffer(this.context, size, i4);
        CLEventList cLEventList = new CLEventList(1);
        if (this.surfaceTransformer != null) {
            i = i4;
            z = false;
            this.context.writeBuffer(this.H1Buffer, false);
        } else {
            i = i4;
            z = false;
        }
        this.context.writeBuffer(this.H2Buffer, z);
        this.context.writeBuffer(this.XBuffer, z);
        if (inputData2.autoWrite) {
            inputData2.writeBuffer(this.context);
        }
        CLMemory cLMemory = this.projectorImageCL[inputData2.pyramidLevel];
        if (cLImage2d8 == null) {
            CLKernel putArg = this.oneKernel.putArg(cLMemory).putArg(cLImage2d7);
            if (cLImage2d10 == null) {
                cLImage2d10 = cLImage2d4;
            }
            cLKernel = putArg.putArg(cLImage2d10).putArg(cLImage2d11).putArg(this.H2Buffer);
        } else if (cLImage2d9 == null) {
            cLKernel = this.subKernel.putArg(cLMemory).putArg(cLImage2d7).putArg(cLImage2d8).putArg(cLImage2d4).putArg(cLImage2d10).putArg(cLImage2d11).putArg(this.H2Buffer);
        } else {
            cLKernel = this.dotKernel.putArg(cLMemory).putArg(cLImage2d7).putArg(cLImage2d8).putArg(cLImage2d9).putArg(cLImage2d11).putArg(this.H2Buffer);
        }
        CLBuffer<FloatBuffer> cLBuffer = this.H1Buffer;
        if (cLBuffer != null) {
            cLKernel.putArg(cLBuffer);
        } else {
            cLKernel.putNullArg(this.nullSize);
        }
        cLKernel.putArg(this.XBuffer).putArg(buffer).putArg(buffer2).rewind();
        this.context.executeKernel(cLKernel, (long) inputData2.roiX, 0, 0, (long) alignCeil, 1, (long) parametersArr2.length, (long) i3, 1, (long) parametersArr2.length, cLEventList);
        int i5 = i;
        if (i5 > 1) {
            this.reduceKernel.putArg(buffer2).rewind();
            long j = (long) i5;
            this.context.executeKernel(this.reduceKernel, 0, j, j);
        }
        ImageTransformerCL.OutputData outputData3 = outputData;
        if (outputData3.autoRead) {
            outputData3.readBuffer(this.context);
        }
    }
}
