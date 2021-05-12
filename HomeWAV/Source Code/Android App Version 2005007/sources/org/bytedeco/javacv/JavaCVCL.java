package org.bytedeco.javacv;

import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLCommandQueue;
import com.jogamp.opencl.CLContext;
import com.jogamp.opencl.CLDevice;
import com.jogamp.opencl.CLEventList;
import com.jogamp.opencl.CLImage2d;
import com.jogamp.opencl.CLImageFormat;
import com.jogamp.opencl.CLKernel;
import com.jogamp.opencl.CLMemory;
import com.jogamp.opencl.CLObject;
import com.jogamp.opencl.CLPlatform;
import com.jogamp.opencl.CLProgram;
import com.jogamp.opencl.gl.CLGLContext;
import com.jogamp.opencl.gl.CLGLImage2d;
import com.jogamp.opencl.gl.CLGLObject;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLCapabilitiesImmutable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.util.Vector;
import java.util.logging.Logger;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSize;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;

public class JavaCVCL {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String fastCompilerOptions = "-cl-fast-relaxed-math -cl-mad-enable";
    private static final Logger logger = Logger.getLogger(JavaCVCL.class.getName());
    /* access modifiers changed from: private */
    public final CLCommandQueue commandQueue;
    private final CLContext context;
    private final GLU glu;
    private final CLKernel pyrDownKernel;
    private final CLKernel remapBayerKernel;
    private final CLKernel remapKernel;

    public JavaCVCL(CLContext cLContext) {
        this(cLContext, cLContext.getDevices()[0]);
    }

    public JavaCVCL(CLContext cLContext, CLDevice cLDevice) {
        this.context = cLContext;
        this.glu = cLContext instanceof CLGLContext ? new GLU() : null;
        this.commandQueue = cLDevice.createCommandQueue();
        CLKernel[] buildKernels = buildKernels(fastCompilerOptions, "JavaCV.cl", "pyrDown", "remap", "remapBayer");
        this.pyrDownKernel = buildKernels[0];
        this.remapKernel = buildKernels[1];
        this.remapBayerKernel = buildKernels[2];
    }

    public static GLCapabilities getDefaultGLCapabilities(GLProfile gLProfile) {
        if (gLProfile == null) {
            gLProfile = GLProfile.getDefault();
        }
        GLCapabilities gLCapabilities = new GLCapabilities(gLProfile);
        gLCapabilities.setDoubleBuffered(false);
        return gLCapabilities;
    }

    public JavaCVCL() {
        this(false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JavaCVCL(boolean z) {
        this(z ? getDefaultGLCapabilities((GLProfile) null) : null, (GLContext) null, (CLDevice) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JavaCVCL(GLContext gLContext) {
        this(getDefaultGLCapabilities(gLContext == null ? null : gLContext.getGLDrawable().getGLProfile()), gLContext, (CLDevice) null);
    }

    public JavaCVCL(GLCapabilitiesImmutable gLCapabilitiesImmutable, GLContext gLContext, CLDevice cLDevice) {
        GLContext current = GLContext.getCurrent();
        if (cLDevice == null && current != null) {
            CLDevice[] listCLDevices = CLPlatform.getDefault().listCLDevices();
            int length = listCLDevices.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                CLDevice cLDevice2 = listCLDevices[i];
                if (cLDevice2.isGLMemorySharingSupported()) {
                    cLDevice = cLDevice2;
                    break;
                }
                i++;
            }
        }
        if (current != null && cLDevice != null) {
            this.context = CLGLContext.create(current, new CLDevice[]{cLDevice});
            this.glu = GLU.createGLU();
        } else if (cLDevice != null) {
            this.context = CLContext.create(new CLDevice[]{cLDevice});
            this.glu = null;
        } else {
            CLContext create = CLContext.create();
            this.context = create;
            cLDevice = create.getDevices()[0];
            this.glu = null;
        }
        this.commandQueue = cLDevice.createCommandQueue();
        CLKernel[] buildKernels = buildKernels(fastCompilerOptions, "JavaCV.cl", "pyrDown", "remap", "remapBayer");
        this.pyrDownKernel = buildKernels[0];
        this.remapKernel = buildKernels[1];
        this.remapBayerKernel = buildKernels[2];
    }

    public void release() {
        if (!this.context.isReleased()) {
            this.context.release();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public CLContext getCLContext() {
        return this.context;
    }

    public CLCommandQueue getCLCommandQueue() {
        return this.commandQueue;
    }

    public CLGLContext getCLGLContext() {
        CLGLContext cLGLContext = this.context;
        if (cLGLContext instanceof CLGLContext) {
            return cLGLContext;
        }
        return null;
    }

    public GLContext getGLContext() {
        CLGLContext cLGLContext = this.context;
        if (cLGLContext instanceof CLGLContext) {
            return cLGLContext.getGLContext();
        }
        return null;
    }

    public GL getGL() {
        GLContext gLContext = getGLContext();
        if (gLContext != null) {
            return gLContext.getGL();
        }
        return null;
    }

    public GL2 getGL2() {
        GL gl = getGL();
        if (gl != null) {
            return gl.getGL2();
        }
        return null;
    }

    public GLU getGLU() {
        return this.glu;
    }

    public CLKernel buildKernel(String str, String str2) {
        return buildKernels(fastCompilerOptions, Loader.getCallerClass(2), str, str2)[0];
    }

    public CLKernel buildKernel(String str, String str2, String str3) {
        return buildKernels(str, Loader.getCallerClass(2), str2, str3)[0];
    }

    public CLKernel[] buildKernels(String str, String str2, String... strArr) {
        return buildKernels(str, Loader.getCallerClass(2), str2, strArr);
    }

    public CLKernel[] buildKernels(String str, Class cls, String str2, String... strArr) {
        InputStream inputStream;
        try {
            String[] split = str2.split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            if (split.length == 1) {
                inputStream = cls.getResourceAsStream(split[0]);
            } else {
                Vector vector = new Vector(split.length);
                for (String resourceAsStream : split) {
                    vector.addElement(cls.getResourceAsStream(resourceAsStream));
                }
                inputStream = new SequenceInputStream(vector.elements());
            }
            CLProgram createProgram = this.context.createProgram(inputStream);
            createProgram.build(str);
            CLKernel[] cLKernelArr = new CLKernel[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                cLKernelArr[i] = createProgram.createCLKernel(strArr[i]);
            }
            return cLKernelArr;
        } catch (IOException e) {
            throw ((Error) new LinkageError(e.toString()).initCause(e));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.jogamp.opencl.CLImage2d createCLImageFrom(org.bytedeco.opencv.opencv_core.IplImage r11, com.jogamp.opencl.CLMemory.Mem... r12) {
        /*
            r10 = this;
            int r0 = r11.width()
            int r4 = r11.height()
            int r1 = r11.widthStep()
            java.nio.ByteBuffer r2 = r11.getByteBuffer()
            int r3 = r11.depth()
            r5 = -2147483640(0xffffffff80000008, float:-1.1E-44)
            r6 = 0
            r7 = 1
            r8 = 4
            r9 = 2
            if (r3 == r5) goto L_0x0049
            r5 = -2147483632(0xffffffff80000010, float:-2.24E-44)
            if (r3 == r5) goto L_0x0044
            r5 = -2147483616(0xffffffff80000020, float:-4.5E-44)
            if (r3 == r5) goto L_0x003f
            r5 = 8
            if (r3 == r5) goto L_0x003c
            r5 = 16
            if (r3 == r5) goto L_0x0039
            r5 = 32
            if (r3 == r5) goto L_0x0036
            r3 = 0
            r5 = r6
            goto L_0x004d
        L_0x0036:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.FLOAT
            goto L_0x0041
        L_0x0039:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_INT16
            goto L_0x0046
        L_0x003c:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_INT8
            goto L_0x004b
        L_0x003f:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.SIGNED_INT32
        L_0x0041:
            r5 = r3
            r3 = 4
            goto L_0x004d
        L_0x0044:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.SNORM_INT16
        L_0x0046:
            r5 = r3
            r3 = 2
            goto L_0x004d
        L_0x0049:
            com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.SNORM_INT8
        L_0x004b:
            r5 = r3
            r3 = 1
        L_0x004d:
            int r11 = r11.nChannels()
            if (r11 == r7) goto L_0x006a
            if (r11 == r9) goto L_0x0065
            r7 = 3
            if (r11 == r7) goto L_0x0060
            if (r11 == r8) goto L_0x005b
            goto L_0x006c
        L_0x005b:
            com.jogamp.opencl.CLImageFormat$ChannelOrder r6 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGBA
            int r3 = r3 * 4
            goto L_0x006c
        L_0x0060:
            com.jogamp.opencl.CLImageFormat$ChannelOrder r6 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGB
            int r3 = r3 * 3
            goto L_0x006c
        L_0x0065:
            com.jogamp.opencl.CLImageFormat$ChannelOrder r6 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RG
            int r3 = r3 * 2
            goto L_0x006c
        L_0x006a:
            com.jogamp.opencl.CLImageFormat$ChannelOrder r6 = com.jogamp.opencl.CLImageFormat.ChannelOrder.LUMINANCE
        L_0x006c:
            int r1 = r1 / r3
            if (r0 == r1) goto L_0x0071
            r3 = r1
            goto L_0x0072
        L_0x0071:
            r3 = r0
        L_0x0072:
            com.jogamp.opencl.CLImageFormat r11 = new com.jogamp.opencl.CLImageFormat
            r11.<init>(r6, r5)
            com.jogamp.opencl.CLContext r1 = r10.context
            r5 = r11
            r6 = r12
            com.jogamp.opencl.CLImage2d r11 = r1.createImage2d(r2, r3, r4, r5, r6)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.JavaCVCL.createCLImageFrom(org.bytedeco.opencv.opencv_core.IplImage, com.jogamp.opencl.CLMemory$Mem[]):com.jogamp.opencl.CLImage2d");
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.jogamp.opencl.gl.CLGLImage2d createCLGLImageFrom(org.bytedeco.opencv.opencv_core.IplImage r17, com.jogamp.opencl.CLMemory.Mem... r18) {
        /*
            r16 = this;
            com.jogamp.opengl.GL2 r0 = r16.getGL2()
            if (r0 != 0) goto L_0x0008
            r0 = 0
            return r0
        L_0x0008:
            int r1 = r17.width()
            int r2 = r17.height()
            int r3 = r17.widthStep()
            int r4 = r17.nChannels()
            r7 = 3
            r8 = 32
            r9 = -2147483616(0xffffffff80000020, float:-4.5E-44)
            r10 = -2147483632(0xffffffff80000010, float:-2.24E-44)
            r11 = -2147483640(0xffffffff80000008, float:-1.1E-44)
            r12 = 2
            r13 = 1
            r14 = 0
            r15 = 16
            r5 = 4
            r6 = 8
            if (r4 == r13) goto L_0x00c2
            if (r4 == r12) goto L_0x0097
            if (r4 == r7) goto L_0x0066
            if (r4 == r5) goto L_0x0036
            goto L_0x00d2
        L_0x0036:
            int r4 = r17.depth()
            if (r4 == r11) goto L_0x0061
            if (r4 == r10) goto L_0x005d
            if (r4 == r9) goto L_0x0056
            if (r4 == r6) goto L_0x0051
            if (r4 == r15) goto L_0x004c
            if (r4 == r8) goto L_0x0048
            goto L_0x00d2
        L_0x0048:
            r4 = 34836(0x8814, float:4.8816E-41)
            goto L_0x0059
        L_0x004c:
            r4 = 32859(0x805b, float:4.6045E-41)
            goto L_0x00b7
        L_0x0051:
            r4 = 32856(0x8058, float:4.6041E-41)
            goto L_0x00ee
        L_0x0056:
            r4 = 36226(0x8d82, float:5.0763E-41)
        L_0x0059:
            r5 = 16
            goto L_0x00ee
        L_0x005d:
            r4 = 36763(0x8f9b, float:5.1516E-41)
            goto L_0x00b7
        L_0x0061:
            r4 = 36759(0x8f97, float:5.151E-41)
            goto L_0x00ee
        L_0x0066:
            int r4 = r17.depth()
            if (r4 == r11) goto L_0x0091
            if (r4 == r10) goto L_0x008b
            if (r4 == r9) goto L_0x0084
            if (r4 == r6) goto L_0x0080
            if (r4 == r15) goto L_0x007c
            if (r4 == r8) goto L_0x0078
            goto L_0x00d2
        L_0x0078:
            r4 = 34837(0x8815, float:4.8817E-41)
            goto L_0x0087
        L_0x007c:
            r4 = 32852(0x8054, float:4.6035E-41)
            goto L_0x008e
        L_0x0080:
            r4 = 32849(0x8051, float:4.6031E-41)
            goto L_0x0094
        L_0x0084:
            r4 = 36227(0x8d83, float:5.0765E-41)
        L_0x0087:
            r5 = 12
            goto L_0x00ee
        L_0x008b:
            r4 = 36762(0x8f9a, float:5.1515E-41)
        L_0x008e:
            r5 = 6
            goto L_0x00ee
        L_0x0091:
            r4 = 36758(0x8f96, float:5.1509E-41)
        L_0x0094:
            r5 = 3
            goto L_0x00ee
        L_0x0097:
            int r4 = r17.depth()
            if (r4 == r11) goto L_0x00be
            if (r4 == r10) goto L_0x00ba
            if (r4 == r9) goto L_0x00b4
            if (r4 == r6) goto L_0x00b0
            if (r4 == r15) goto L_0x00ac
            if (r4 == r8) goto L_0x00a8
            goto L_0x00d2
        L_0x00a8:
            r4 = 33328(0x8230, float:4.6702E-41)
            goto L_0x00b7
        L_0x00ac:
            r4 = 33324(0x822c, float:4.6697E-41)
            goto L_0x00ee
        L_0x00b0:
            r4 = 33323(0x822b, float:4.6695E-41)
            goto L_0x00e8
        L_0x00b4:
            r4 = 33339(0x823b, float:4.6718E-41)
        L_0x00b7:
            r5 = 8
            goto L_0x00ee
        L_0x00ba:
            r4 = 36761(0x8f99, float:5.1513E-41)
            goto L_0x00ee
        L_0x00be:
            r4 = 36757(0x8f95, float:5.1508E-41)
            goto L_0x00e8
        L_0x00c2:
            int r4 = r17.depth()
            if (r4 == r11) goto L_0x00ea
            if (r4 == r10) goto L_0x00e5
            if (r4 == r9) goto L_0x00e1
            if (r4 == r6) goto L_0x00dd
            if (r4 == r15) goto L_0x00d9
            if (r4 == r8) goto L_0x00d5
        L_0x00d2:
            r4 = 0
            r5 = 0
            goto L_0x00ee
        L_0x00d5:
            r4 = 34840(0x8818, float:4.8821E-41)
            goto L_0x00ee
        L_0x00d9:
            r4 = 32834(0x8042, float:4.601E-41)
            goto L_0x00e8
        L_0x00dd:
            r4 = 32832(0x8040, float:4.6007E-41)
            goto L_0x00ed
        L_0x00e1:
            r4 = 36230(0x8d86, float:5.0769E-41)
            goto L_0x00ee
        L_0x00e5:
            r4 = 36889(0x9019, float:5.1692E-41)
        L_0x00e8:
            r5 = 2
            goto L_0x00ee
        L_0x00ea:
            r4 = 36885(0x9015, float:5.1687E-41)
        L_0x00ed:
            r5 = 1
        L_0x00ee:
            int r3 = r3 / r5
            if (r1 == r3) goto L_0x00f2
            r1 = r3
        L_0x00f2:
            int[] r3 = new int[r13]
            r0.glGenRenderbuffers(r13, r3, r14)
            r5 = r3[r14]
            r6 = 36161(0x8d41, float:5.0672E-41)
            r0.glBindRenderbuffer(r6, r5)
            r0.glRenderbufferStorage(r6, r4, r1, r2)
            com.jogamp.opencl.gl.CLGLContext r0 = r16.getCLGLContext()
            r1 = r3[r14]
            r2 = r18
            com.jogamp.opencl.gl.CLGLImage2d r0 = r0.createFromGLRenderbuffer(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.JavaCVCL.createCLGLImageFrom(org.bytedeco.opencv.opencv_core.IplImage, com.jogamp.opencl.CLMemory$Mem[]):com.jogamp.opencl.gl.CLGLImage2d");
    }

    public void releaseCLGLImage(CLGLImage2d cLGLImage2d) {
        cLGLImage2d.release();
        getGL2().glDeleteRenderbuffers(1, new int[]{cLGLImage2d.getGLObjectID()}, 0);
    }

    public CLBuffer createPinnedBuffer(int i) {
        CLBuffer createBuffer = this.context.createBuffer(i, new CLMemory.Mem[]{CLMemory.Mem.ALLOCATE_BUFFER});
        createBuffer.use(this.commandQueue.putMapBuffer(createBuffer, CLMemory.Map.READ_WRITE, true));
        return createBuffer;
    }

    class PinnedIplImage extends IplImage {
        final CLBuffer pinnedBuffer;

        PinnedIplImage(int i, int i2, int i3, int i4) {
            super((Pointer) opencv_core.cvCreateImageHeader(new CvSize().width(i).height(i2), i3, i4));
            this.pinnedBuffer = JavaCVCL.this.createPinnedBuffer(imageSize());
            imageData(new BytePointer(getByteBuffer()));
        }

        public CLBuffer getCLBuffer() {
            return this.pinnedBuffer;
        }

        public ByteBuffer getByteBuffer() {
            return (ByteBuffer) this.pinnedBuffer.getBuffer();
        }

        public void release() {
            JavaCVCL.this.commandQueue.putUnmapMemory(this.pinnedBuffer, getByteBuffer());
            this.pinnedBuffer.release();
            opencv_core.cvReleaseImageHeader((IplImage) this);
        }
    }

    public IplImage createPinnedIplImage(int i, int i2, int i3, int i4) {
        return new PinnedIplImage(i, i2, i3, i4);
    }

    public IplImage createIplImageFrom(CLImage2d cLImage2d) {
        int i;
        int i2 = cLImage2d.width;
        int i3 = cLImage2d.height;
        CLImageFormat format = cLImage2d.getFormat();
        CLImageFormat.ChannelOrder imageChannelOrder = format.getImageChannelOrder();
        CLImageFormat.ChannelType imageChannelDataType = format.getImageChannelDataType();
        int i4 = 0;
        switch (AnonymousClass1.$SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder[imageChannelOrder.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                i = 1;
                break;
            case 5:
            case 6:
            case 7:
                i = 2;
                break;
            case 8:
            case 9:
                i = 3;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                i = 4;
                break;
            default:
                i = 0;
                break;
        }
        switch (AnonymousClass1.$SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType[imageChannelDataType.ordinal()]) {
            case 1:
            case 2:
                i4 = opencv_core.IPL_DEPTH_8S;
                break;
            case 3:
            case 4:
                i4 = 8;
                break;
            case 5:
            case 6:
                i4 = opencv_core.IPL_DEPTH_16S;
                break;
            case 7:
            case 8:
                i4 = 16;
                break;
            case 9:
            case 10:
                i4 = opencv_core.IPL_DEPTH_32S;
                break;
            case 11:
                i4 = 32;
                break;
        }
        return IplImage.create(i2, i3, i4, i);
    }

    /* renamed from: org.bytedeco.javacv.JavaCVCL$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder;
        static final /* synthetic */ int[] $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType;

        /* JADX WARNING: Can't wrap try/catch for region: R(59:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|(3:81|82|84)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(63:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(64:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(65:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(66:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(67:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(68:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(71:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00c5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00cf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00d9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00e3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x00ed */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x00f7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x0101 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x010b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0115 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x011f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0129 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x0133 */
        static {
            /*
                com.jogamp.opencl.CLImageFormat$ChannelType[] r0 = com.jogamp.opencl.CLImageFormat.ChannelType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType = r0
                r1 = 1
                com.jogamp.opencl.CLImageFormat$ChannelType r2 = com.jogamp.opencl.CLImageFormat.ChannelType.SIGNED_INT8     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jogamp.opencl.CLImageFormat$ChannelType r3 = com.jogamp.opencl.CLImageFormat.ChannelType.SNORM_INT8     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jogamp.opencl.CLImageFormat$ChannelType r4 = com.jogamp.opencl.CLImageFormat.ChannelType.UNSIGNED_INT8     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jogamp.opencl.CLImageFormat$ChannelType r5 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_INT8     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jogamp.opencl.CLImageFormat$ChannelType r6 = com.jogamp.opencl.CLImageFormat.ChannelType.SIGNED_INT16     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jogamp.opencl.CLImageFormat$ChannelType r7 = com.jogamp.opencl.CLImageFormat.ChannelType.SNORM_INT16     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jogamp.opencl.CLImageFormat$ChannelType r8 = com.jogamp.opencl.CLImageFormat.ChannelType.UNSIGNED_INT16     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.jogamp.opencl.CLImageFormat$ChannelType r9 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_INT16     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                r8 = 9
                int[] r9 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.jogamp.opencl.CLImageFormat$ChannelType r10 = com.jogamp.opencl.CLImageFormat.ChannelType.UNSIGNED_INT32     // Catch:{ NoSuchFieldError -> 0x006c }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                r9 = 10
                int[] r10 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.jogamp.opencl.CLImageFormat$ChannelType r11 = com.jogamp.opencl.CLImageFormat.ChannelType.SIGNED_INT32     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r10[r11] = r9     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                r10 = 11
                int[] r11 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.jogamp.opencl.CLImageFormat$ChannelType r12 = com.jogamp.opencl.CLImageFormat.ChannelType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r12 = r12.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r11[r12] = r10     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                r11 = 12
                int[] r12 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.jogamp.opencl.CLImageFormat$ChannelType r13 = com.jogamp.opencl.CLImageFormat.ChannelType.HALF_FLOAT     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r12[r13] = r11     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                r12 = 13
                int[] r13 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.jogamp.opencl.CLImageFormat$ChannelType r14 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_SHORT_565     // Catch:{ NoSuchFieldError -> 0x009c }
                int r14 = r14.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r13[r14] = r12     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r13 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.jogamp.opencl.CLImageFormat$ChannelType r14 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_SHORT_555     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r14 = r14.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r15 = 14
                r13[r14] = r15     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r13 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.jogamp.opencl.CLImageFormat$ChannelType r14 = com.jogamp.opencl.CLImageFormat.ChannelType.UNORM_INT_101010     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r14 = r14.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r15 = 15
                r13[r14] = r15     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                com.jogamp.opencl.CLImageFormat$ChannelOrder[] r13 = com.jogamp.opencl.CLImageFormat.ChannelOrder.values()
                int r13 = r13.length
                int[] r13 = new int[r13]
                $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder = r13
                com.jogamp.opencl.CLImageFormat$ChannelOrder r14 = com.jogamp.opencl.CLImageFormat.ChannelOrder.R     // Catch:{ NoSuchFieldError -> 0x00c5 }
                int r14 = r14.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c5 }
                r13[r14] = r1     // Catch:{ NoSuchFieldError -> 0x00c5 }
            L_0x00c5:
                int[] r1 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x00cf }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r13 = com.jogamp.opencl.CLImageFormat.ChannelOrder.A     // Catch:{ NoSuchFieldError -> 0x00cf }
                int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cf }
                r1[r13] = r0     // Catch:{ NoSuchFieldError -> 0x00cf }
            L_0x00cf:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x00d9 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.INTENSITY     // Catch:{ NoSuchFieldError -> 0x00d9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d9 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d9 }
            L_0x00d9:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x00e3 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.LUMINANCE     // Catch:{ NoSuchFieldError -> 0x00e3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e3 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00e3 }
            L_0x00e3:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x00ed }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.Rx     // Catch:{ NoSuchFieldError -> 0x00ed }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ed }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00ed }
            L_0x00ed:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x00f7 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RG     // Catch:{ NoSuchFieldError -> 0x00f7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f7 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00f7 }
            L_0x00f7:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x0101 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RA     // Catch:{ NoSuchFieldError -> 0x0101 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0101 }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x0101 }
            L_0x0101:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x010b }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGx     // Catch:{ NoSuchFieldError -> 0x010b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x010b }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x010b }
            L_0x010b:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x0115 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGB     // Catch:{ NoSuchFieldError -> 0x0115 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0115 }
                r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x0115 }
            L_0x0115:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x011f }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGBx     // Catch:{ NoSuchFieldError -> 0x011f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x011f }
                r0[r1] = r9     // Catch:{ NoSuchFieldError -> 0x011f }
            L_0x011f:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x0129 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.RGBA     // Catch:{ NoSuchFieldError -> 0x0129 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0129 }
                r0[r1] = r10     // Catch:{ NoSuchFieldError -> 0x0129 }
            L_0x0129:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x0133 }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.ARGB     // Catch:{ NoSuchFieldError -> 0x0133 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0133 }
                r0[r1] = r11     // Catch:{ NoSuchFieldError -> 0x0133 }
            L_0x0133:
                int[] r0 = $SwitchMap$com$jogamp$opencl$CLImageFormat$ChannelOrder     // Catch:{ NoSuchFieldError -> 0x013d }
                com.jogamp.opencl.CLImageFormat$ChannelOrder r1 = com.jogamp.opencl.CLImageFormat.ChannelOrder.BGRA     // Catch:{ NoSuchFieldError -> 0x013d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x013d }
                r0[r1] = r12     // Catch:{ NoSuchFieldError -> 0x013d }
            L_0x013d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.JavaCVCL.AnonymousClass1.<clinit>():void");
        }
    }

    public IplImage readImage(CLImage2d cLImage2d, IplImage iplImage, boolean z) {
        int i;
        int i2;
        int i3;
        if (iplImage == null) {
            iplImage = createIplImageFrom(cLImage2d);
        }
        int i4 = cLImage2d.width;
        int i5 = cLImage2d.height;
        int widthStep = iplImage.widthStep();
        ByteBuffer byteBuffer = iplImage.getByteBuffer();
        IplROI roi = iplImage.roi();
        int i6 = 0;
        if (roi != null) {
            int xOffset = roi.xOffset();
            int yOffset = roi.yOffset();
            int width = roi.width();
            int height = roi.height();
            i3 = yOffset;
            i2 = width;
            i = height;
            byteBuffer = iplImage.getByteBuffer((yOffset * widthStep) + (iplImage.nChannels() * ((iplImage.depth() & Integer.MAX_VALUE) / 8) * xOffset));
            i6 = xOffset;
        } else {
            i2 = i4;
            i = i5;
            i3 = 0;
        }
        cLImage2d.use(byteBuffer);
        this.commandQueue.putReadImage(cLImage2d, widthStep, i6, i3, i2, i, z);
        return iplImage;
    }

    public CLImage2d writeImage(CLImage2d cLImage2d, IplImage iplImage, boolean z) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (cLImage2d == null) {
            cLImage2d = createCLImageFrom(iplImage, new CLMemory.Mem[0]);
        }
        int width = iplImage.width();
        int height = iplImage.height();
        int widthStep = iplImage.widthStep();
        ByteBuffer byteBuffer = iplImage.getByteBuffer();
        IplROI roi = iplImage.roi();
        if (roi != null) {
            i4 = roi.xOffset();
            int yOffset = roi.yOffset();
            int width2 = roi.width();
            int height2 = roi.height();
            ByteBuffer byteBuffer2 = iplImage.getByteBuffer((yOffset * widthStep) + (iplImage.nChannels() * ((iplImage.depth() & Integer.MAX_VALUE) / 8) * i4));
            i3 = yOffset;
            i2 = width2;
            i = height2;
            byteBuffer = byteBuffer2;
        } else {
            i2 = width;
            i = height;
            i3 = 0;
        }
        cLImage2d.use(byteBuffer);
        this.commandQueue.putWriteImage(cLImage2d, widthStep, i4, i3, i2, i, z);
        return cLImage2d;
    }

    public void acquireGLObject(CLObject cLObject) {
        if (cLObject instanceof CLGLObject) {
            this.commandQueue.putAcquireGLObject((CLGLObject) cLObject);
        }
    }

    public void releaseGLObject(CLObject cLObject) {
        if (cLObject instanceof CLGLObject) {
            this.commandQueue.putReleaseGLObject((CLGLObject) cLObject);
        }
    }

    public void readBuffer(CLBuffer<?> cLBuffer, boolean z) {
        this.commandQueue.putReadBuffer(cLBuffer, z);
    }

    public void writeBuffer(CLBuffer<?> cLBuffer, boolean z) {
        this.commandQueue.putWriteBuffer(cLBuffer, z);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3) {
        this.commandQueue.put1DRangeKernel(cLKernel, j, j2, j3);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, CLEventList cLEventList) {
        this.commandQueue.put1DRangeKernel(cLKernel, j, j2, j3, cLEventList);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, CLEventList cLEventList, CLEventList cLEventList2) {
        this.commandQueue.put1DRangeKernel(cLKernel, j, j2, j3, cLEventList, cLEventList2);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6) {
        this.commandQueue.put2DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6, CLEventList cLEventList) {
        this.commandQueue.put2DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6, cLEventList);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6, CLEventList cLEventList, CLEventList cLEventList2) {
        this.commandQueue.put2DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6, cLEventList, cLEventList2);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        CLKernel cLKernel2 = cLKernel;
        this.commandQueue.put3DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6, j7, j8, j9);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, CLEventList cLEventList) {
        CLKernel cLKernel2 = cLKernel;
        this.commandQueue.put3DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6, j7, j8, j9, cLEventList);
    }

    public void executeKernel(CLKernel cLKernel, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, CLEventList cLEventList, CLEventList cLEventList2) {
        CLKernel cLKernel2 = cLKernel;
        this.commandQueue.put3DRangeKernel(cLKernel, j, j2, j3, j4, j5, j6, j7, j8, j9, cLEventList, cLEventList2);
    }

    public void finish() {
        this.commandQueue.finish();
    }

    public void flush() {
        this.commandQueue.flush();
    }

    public static int alignCeil(int i, int i2) {
        return (((i + i2) - 1) / i2) * i2;
    }

    public static int alignFloor(int i, int i2) {
        return (i / i2) * i2;
    }

    public void pyrDown(CLImage2d cLImage2d, CLImage2d cLImage2d2) {
        CLImage2d cLImage2d3 = cLImage2d2;
        this.pyrDownKernel.putArg(cLImage2d).putArg(cLImage2d3).rewind();
        long alignCeil = (long) alignCeil(cLImage2d3.height, 64);
        executeKernel(this.pyrDownKernel, 0, 0, (long) alignCeil(cLImage2d3.width, 2), alignCeil, 2, 64, (CLEventList) null);
    }

    public void remap(CLImage2d cLImage2d, CLImage2d cLImage2d2, CLImage2d cLImage2d3, CLImage2d cLImage2d4) {
        remap(cLImage2d, cLImage2d2, cLImage2d3, cLImage2d4, -1);
    }

    public void remap(CLImage2d cLImage2d, CLImage2d cLImage2d2, CLImage2d cLImage2d3, CLImage2d cLImage2d4, long j) {
        CLKernel cLKernel;
        CLImage2d cLImage2d5 = cLImage2d;
        CLImage2d cLImage2d6 = cLImage2d2;
        CLImage2d cLImage2d7 = cLImage2d3;
        CLImage2d cLImage2d8 = cLImage2d4;
        long j2 = j;
        if (j2 != -1) {
            cLKernel = this.remapBayerKernel.putArg(cLImage2d5).putArg(cLImage2d6).putArg(cLImage2d7).putArg(cLImage2d8).putArg(j2).rewind();
        } else {
            cLKernel = this.remapKernel.putArg(cLImage2d5).putArg(cLImage2d6).putArg(cLImage2d7).putArg(cLImage2d8).rewind();
        }
        executeKernel(cLKernel, 0, 0, (long) alignCeil(cLImage2d6.width, 2), (long) alignCeil(cLImage2d6.height, 64), 2, 64, (CLEventList) null);
    }

    public static void main(String[] strArr) {
        JavaCVCL javaCVCL = new JavaCVCL();
        for (CLImageFormat println : javaCVCL.getCLContext().getSupportedImage2dFormats(new CLMemory.Mem[0])) {
            System.out.println(println);
        }
        CameraDevice cameraDevice = new CameraDevice("Camera");
        cameraDevice.imageWidth = 1280;
        cameraDevice.imageHeight = 960;
        cameraDevice.cameraMatrix = CvMat.create(3, 3);
        double d = ((double) cameraDevice.imageWidth) * 2.5d;
        cameraDevice.cameraMatrix.put(d, 0.0d, (double) (cameraDevice.imageWidth / 2), 0.0d, d, (double) (cameraDevice.imageHeight / 2), 0.0d, 0.0d, 1.0d);
        cameraDevice.R = CvMat.create(3, 3);
        opencv_core.cvSetIdentity(cameraDevice.R);
        cameraDevice.T = CvMat.create(3, 1);
        opencv_core.cvSetZero(cameraDevice.T);
        cameraDevice.distortionCoeffs = CvMat.create(1, 4);
        opencv_core.cvSetZero(cameraDevice.distortionCoeffs);
        cameraDevice.distortionCoeffs.put(0.2d);
        cameraDevice.colorMixingMatrix = CvMat.create(3, 3);
        opencv_core.cvSetIdentity(cameraDevice.colorMixingMatrix);
        IplImage cvLoadImageRGBA = opencv_imgcodecs.cvLoadImageRGBA(strArr[0]);
        IplImage create = IplImage.create(cvLoadImageRGBA.width() / 2, cvLoadImageRGBA.height() / 2, 8, 4);
        cameraDevice.setFixedPointMaps(false);
        cameraDevice.setMapsPyramidLevel(1);
        IplImage undistortMap1 = cameraDevice.getUndistortMap1();
        IplImage undistortMap2 = cameraDevice.getUndistortMap2();
        long nanoTime = System.nanoTime();
        opencv_imgproc.cvRemap(cvLoadImageRGBA, create, undistortMap1, undistortMap2, 9, CvScalar.ZERO);
        System.out.println("cvRemap: " + (((double) (System.nanoTime() - nanoTime)) / 1000000.0d));
        opencv_imgcodecs.cvSaveImage("/tmp/opencv.png", create);
        CLImage2d createCLImageFrom = javaCVCL.createCLImageFrom(cvLoadImageRGBA, new CLMemory.Mem[0]);
        CLImage2d createCLImageFrom2 = javaCVCL.createCLImageFrom(create, new CLMemory.Mem[0]);
        CLImage2d createCLImageFrom3 = javaCVCL.createCLImageFrom(undistortMap1, new CLMemory.Mem[0]);
        CLImage2d createCLImageFrom4 = javaCVCL.createCLImageFrom(undistortMap2, new CLMemory.Mem[0]);
        javaCVCL.writeImage(createCLImageFrom, cvLoadImageRGBA, false);
        javaCVCL.writeImage(createCLImageFrom3, undistortMap1, false);
        javaCVCL.writeImage(createCLImageFrom4, undistortMap2, false);
        javaCVCL.remap(createCLImageFrom, createCLImageFrom2, createCLImageFrom3, createCLImageFrom4);
        javaCVCL.readImage(createCLImageFrom2, create, true);
        opencv_imgcodecs.cvSaveImage("/tmp/javacvcl.png", create);
        javaCVCL.release();
        System.exit(0);
    }
}
