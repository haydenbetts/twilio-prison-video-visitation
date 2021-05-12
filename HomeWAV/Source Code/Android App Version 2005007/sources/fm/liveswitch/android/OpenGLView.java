package fm.liveswitch.android;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import androidx.work.Data;
import fm.liveswitch.LayoutScale;
import fm.liveswitch.Log;
import fm.liveswitch.VideoBuffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private int bufferHeight;
    private int bufferWidth;
    private boolean buffersInitialized;
    private String fragShaderCode;
    private IntBuffer indexVBO;
    private OpenGLState lastState;
    private IntBuffer positionVBO;
    private int program;
    private boolean reconfigureBuffers;
    private volatile boolean renderRequested;
    private LayoutScale scale;
    private OpenGLState state;
    private IntBuffer texcoordVBO;
    private int uTexture;
    private int[] uniforms;
    private int vTexture;
    private String vertShaderCode;
    private int yTexture;

    enum ATTRIB {
        VERTEX,
        TEXCOORD
    }

    enum UNIFORM {
        Y,
        U,
        V
    }

    public LayoutScale getScale() {
        return this.scale;
    }

    public OpenGLView(Context context) {
        this(context, LayoutScale.Contain);
    }

    public OpenGLView(Context context, LayoutScale layoutScale) {
        super(context);
        this.vertShaderCode = "attribute vec4 position;\nattribute vec2 texCoord;\n\nvarying vec2 texCoordVarying;\n\nvoid main()\n{\n    gl_Position = position;\n    texCoordVarying = texCoord;\n}";
        this.fragShaderCode = "uniform sampler2D SamplerY;\nuniform sampler2D SamplerU;\nuniform sampler2D SamplerV;\n\nvarying highp vec2 texCoordVarying;\n\nvoid main()\n{\n    mediump vec3 yuv;\n    lowp vec3 rgb;\n\n    yuv.x = texture2D(SamplerY, texCoordVarying).r;\n    yuv.y = texture2D(SamplerU, texCoordVarying).r - 0.5;\n    yuv.z = texture2D(SamplerV, texCoordVarying).r - 0.5;\n\n    rgb = mat3(      1,       1,      1,\n                     0, -.18732, 1.8556,\n               1.57481, -.46813,      0) * yuv;\n\n    gl_FragColor = vec4(rgb, 1);\n}";
        this.uniforms = new int[3];
        this.texcoordVBO = IntBuffer.allocate(1);
        this.positionVBO = IntBuffer.allocate(1);
        this.indexVBO = IntBuffer.allocate(1);
        this.buffersInitialized = false;
        this.reconfigureBuffers = false;
        this.bufferWidth = 0;
        this.bufferHeight = 0;
        this.scale = layoutScale;
        this.renderRequested = false;
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(0);
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glActiveTexture(34016);
        GLES20.glViewport(0, 0, i, i2);
        this.bufferWidth = 0;
        this.bufferHeight = 0;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        loadShaders();
        int[] iArr = new int[3];
        GLES20.glEnable(3553);
        GLES20.glGenTextures(3, iArr, 0);
        this.yTexture = iArr[0];
        this.uTexture = iArr[1];
        this.vTexture = iArr[2];
        GLES20.glUseProgram(this.program);
        GLES20.glUniform1i(this.uniforms[UNIFORM.Y.ordinal()], 0);
        GLES20.glUniform1i(this.uniforms[UNIFORM.U.ordinal()], 1);
        GLES20.glUniform1i(this.uniforms[UNIFORM.V.ordinal()], 2);
    }

    public final void onDrawFrame(GL10 gl10) {
        OpenGLState openGLState = this.lastState;
        if (openGLState != null) {
            openGLState.free();
            this.lastState = null;
        }
        GLES20.glClear(16384);
        OpenGLState openGLState2 = this.state;
        if (openGLState2 != null) {
            int i = openGLState2.width;
            int i2 = openGLState2.height;
            ByteBuffer byteBuffer = openGLState2.yBuffer;
            ByteBuffer byteBuffer2 = openGLState2.uBuffer;
            ByteBuffer byteBuffer3 = openGLState2.vBuffer;
            boolean z = this.buffersInitialized;
            if (z && !(this.bufferWidth == i && this.bufferHeight == i2)) {
                this.reconfigureBuffers = true;
            }
            if (!z || this.reconfigureBuffers) {
                initializeBuffers(i, i2);
                this.reconfigureBuffers = false;
            }
            GLES20.glActiveTexture(33984);
            GLES20.glTexImage2D(3553, 0, 6409, i, i2, 0, 6409, 5121, byteBuffer);
            GLES20.glBindTexture(3553, this.yTexture);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, Data.MAX_DATA_BYTES, 9729);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glActiveTexture(33985);
            int i3 = i / 2;
            int i4 = i2 / 2;
            GLES20.glTexImage2D(3553, 0, 6409, i3, i4, 0, 6409, 5121, byteBuffer2);
            GLES20.glBindTexture(3553, this.uTexture);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, Data.MAX_DATA_BYTES, 9729);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glActiveTexture(33986);
            GLES20.glTexImage2D(3553, 0, 6409, i3, i4, 0, 6409, 5121, byteBuffer3);
            GLES20.glBindTexture(3553, this.vTexture);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, Data.MAX_DATA_BYTES, 9729);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glDrawElements(5, 6, 5123, 0);
            this.lastState = openGLState2;
            this.state = null;
        }
        this.renderRequested = false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x017b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initializeBuffers(int r23, int r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r22.destroyBuffers()
            r3 = 1
            r0.buffersInitialized = r3
            r0.bufferWidth = r1
            r0.bufferHeight = r2
            int r4 = r22.getWidth()
            int r5 = r22.getHeight()
            fm.liveswitch.LayoutScale r6 = r0.scale
            fm.liveswitch.LayoutScale r7 = fm.liveswitch.LayoutScale.Cover
            r8 = 0
            r9 = 1065353216(0x3f800000, float:1.0)
            if (r6 != r7) goto L_0x0048
            float r6 = (float) r5
            float r7 = (float) r4
            float r6 = r6 / r7
            float r7 = (float) r2
            float r10 = (float) r1
            float r7 = r7 / r10
            r10 = 1073741824(0x40000000, float:2.0)
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x003c
            int r6 = r1 * r5
            float r6 = (float) r6
            int r7 = r2 * r4
            float r7 = (float) r7
            float r6 = r6 / r7
            float r7 = r9 - r6
            float r7 = r7 / r10
            r10 = r7
            r7 = r6
            r6 = 1065353216(0x3f800000, float:1.0)
            goto L_0x004d
        L_0x003c:
            int r6 = r2 * r4
            float r6 = (float) r6
            int r7 = r1 * r5
            float r7 = (float) r7
            float r6 = r6 / r7
            float r7 = r9 - r6
            float r7 = r7 / r10
            r8 = r7
            goto L_0x004a
        L_0x0048:
            r6 = 1065353216(0x3f800000, float:1.0)
        L_0x004a:
            r7 = 1065353216(0x3f800000, float:1.0)
            r10 = 0
        L_0x004d:
            fm.liveswitch.LayoutScale r11 = r0.scale
            fm.liveswitch.LayoutScale r12 = fm.liveswitch.LayoutScale.Contain
            if (r11 != r12) goto L_0x0066
            float r4 = (float) r4
            float r5 = (float) r5
            float r4 = r4 / r5
            float r5 = (float) r1
            float r2 = (float) r2
            float r5 = r5 / r2
            int r2 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x005f
            float r5 = r5 / r4
            goto L_0x0063
        L_0x005f:
            float r4 = r4 / r5
            r9 = r4
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x0063:
            r2 = r9
            r9 = r5
            goto L_0x0068
        L_0x0066:
            r2 = 1065353216(0x3f800000, float:1.0)
        L_0x0068:
            r4 = 8
            float[] r5 = new float[r4]
            float r11 = -r9
            r12 = 0
            r5[r12] = r11
            r5[r3] = r2
            r13 = 2
            r5[r13] = r9
            r14 = 3
            r5[r14] = r2
            r15 = 4
            r5[r15] = r11
            float r2 = -r2
            r11 = 5
            r5[r11] = r2
            r11 = 6
            r5[r11] = r9
            r9 = 7
            r5[r9] = r2
            r2 = 32
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.allocateDirect(r2)
            java.nio.ByteOrder r2 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r2 = r9.order(r2)
            java.nio.FloatBuffer r2 = r2.asFloatBuffer()
            java.nio.FloatBuffer r5 = r2.put(r5)
            r5.position(r12)
            float[] r5 = new float[r4]
            r5[r12] = r8
            r5[r3] = r10
            float r6 = r6 + r8
            r5[r13] = r6
            r5[r14] = r10
            r5[r15] = r8
            float r10 = r10 + r7
            r7 = 5
            r5[r7] = r10
            r5[r11] = r6
            r6 = 7
            r5[r6] = r10
            r6 = 32
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocateDirect(r6)
            java.nio.ByteOrder r7 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r6 = r6.order(r7)
            java.nio.FloatBuffer r6 = r6.asFloatBuffer()
            java.nio.FloatBuffer r5 = r6.put(r5)
            r5.position(r12)
            short[] r5 = new short[r11]
            r5 = {0, 1, 2, 1, 2, 3} // fill-array
            r7 = 12
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.allocateDirect(r7)
            java.nio.ByteOrder r8 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r7 = r7.order(r8)
            java.nio.ShortBuffer r7 = r7.asShortBuffer()
            java.nio.ShortBuffer r5 = r7.put(r5)
            r5.position(r12)
            java.nio.IntBuffer r5 = r0.indexVBO
            android.opengl.GLES20.glGenBuffers(r3, r5)
            java.nio.IntBuffer r5 = r0.indexVBO
            int r5 = r5.get(r12)
            r8 = 34963(0x8893, float:4.8994E-41)
            android.opengl.GLES20.glBindBuffer(r8, r5)
            int r5 = r7.capacity()
            int r5 = r5 * 2
            r9 = 35044(0x88e4, float:4.9107E-41)
            android.opengl.GLES20.glBufferData(r8, r5, r7, r9)
            java.nio.IntBuffer r5 = r0.positionVBO
            android.opengl.GLES20.glGenBuffers(r3, r5)
            java.nio.IntBuffer r5 = r0.positionVBO
            int r5 = r5.get(r12)
            r7 = 34962(0x8892, float:4.8992E-41)
            android.opengl.GLES20.glBindBuffer(r7, r5)
            int r5 = r2.capacity()
            int r5 = r5 * 4
            android.opengl.GLES20.glBufferData(r7, r5, r2, r9)
            fm.liveswitch.android.OpenGLView$ATTRIB r2 = fm.liveswitch.android.OpenGLView.ATTRIB.VERTEX
            int r2 = r2.ordinal()
            android.opengl.GLES20.glEnableVertexAttribArray(r2)
            fm.liveswitch.android.OpenGLView$ATTRIB r2 = fm.liveswitch.android.OpenGLView.ATTRIB.VERTEX
            int r16 = r2.ordinal()
            r17 = 2
            r18 = 5126(0x1406, float:7.183E-42)
            r19 = 0
            r20 = 8
            r21 = 0
            android.opengl.GLES20.glVertexAttribPointer(r16, r17, r18, r19, r20, r21)
            java.nio.IntBuffer r2 = r0.texcoordVBO
            android.opengl.GLES20.glGenBuffers(r3, r2)
            java.nio.IntBuffer r2 = r0.texcoordVBO
            int r2 = r2.get(r12)
            android.opengl.GLES20.glBindBuffer(r7, r2)
            int r2 = r6.capacity()
            int r2 = r2 * 4
            r5 = 35048(0x88e8, float:4.9113E-41)
            android.opengl.GLES20.glBufferData(r7, r2, r6, r5)
            fm.liveswitch.android.OpenGLView$ATTRIB r2 = fm.liveswitch.android.OpenGLView.ATTRIB.TEXCOORD
            int r2 = r2.ordinal()
            android.opengl.GLES20.glEnableVertexAttribArray(r2)
            fm.liveswitch.android.OpenGLView$ATTRIB r2 = fm.liveswitch.android.OpenGLView.ATTRIB.TEXCOORD
            int r5 = r2.ordinal()
            r6 = 2
            r7 = 5126(0x1406, float:7.183E-42)
            r8 = 0
            r9 = 8
            r10 = 0
            android.opengl.GLES20.glVertexAttribPointer(r5, r6, r7, r8, r9, r10)
            int r2 = r1 % 16
            r5 = 3317(0xcf5, float:4.648E-42)
            if (r2 != 0) goto L_0x017b
            android.opengl.GLES20.glPixelStorei(r5, r4)
            goto L_0x018d
        L_0x017b:
            int r2 = r1 % 8
            if (r2 != 0) goto L_0x0183
            android.opengl.GLES20.glPixelStorei(r5, r15)
            goto L_0x018d
        L_0x0183:
            int r1 = r1 % r15
            if (r1 != 0) goto L_0x018a
            android.opengl.GLES20.glPixelStorei(r5, r13)
            goto L_0x018d
        L_0x018a:
            android.opengl.GLES20.glPixelStorei(r5, r3)
        L_0x018d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.OpenGLView.initializeBuffers(int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void destroyBuffers() {
        if (this.buffersInitialized) {
            GLES20.glDeleteBuffers(1, this.texcoordVBO);
            GLES20.glDeleteBuffers(1, this.positionVBO);
            GLES20.glDeleteBuffers(1, this.indexVBO);
            this.buffersInitialized = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean loadShaders() {
        this.program = GLES20.glCreateProgram();
        int compileShader = compileShader(35633, this.vertShaderCode);
        if (compileShader == 0) {
            Log.error("Could not compile vertex shader.");
            return false;
        }
        int compileShader2 = compileShader(35632, this.fragShaderCode);
        if (compileShader2 == 0) {
            Log.error("Could not compile fragment shader.");
            return false;
        }
        GLES20.glAttachShader(this.program, compileShader);
        GLES20.glAttachShader(this.program, compileShader2);
        GLES20.glBindAttribLocation(this.program, ATTRIB.VERTEX.ordinal(), "position");
        GLES20.glBindAttribLocation(this.program, ATTRIB.TEXCOORD.ordinal(), "texCoord");
        if (!linkProgram(this.program)) {
            Log.error("Could not link program.");
            if (compileShader > 0) {
                GLES20.glDeleteShader(compileShader);
            }
            if (compileShader2 > 0) {
                GLES20.glDeleteShader(compileShader2);
            }
            int i = this.program;
            if (i > 0) {
                GLES20.glDeleteProgram(i);
                this.program = 0;
            }
            return false;
        }
        this.uniforms[UNIFORM.Y.ordinal()] = GLES20.glGetUniformLocation(this.program, "SamplerY");
        this.uniforms[UNIFORM.U.ordinal()] = GLES20.glGetUniformLocation(this.program, "SamplerU");
        this.uniforms[UNIFORM.V.ordinal()] = GLES20.glGetUniformLocation(this.program, "SamplerV");
        if (compileShader > 0) {
            GLES20.glDetachShader(this.program, compileShader);
            GLES20.glDeleteShader(compileShader);
        }
        if (compileShader2 <= 0) {
            return true;
        }
        GLES20.glDetachShader(this.program, compileShader2);
        GLES20.glDeleteShader(compileShader2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public int compileShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        IntBuffer allocate = IntBuffer.allocate(1);
        GLES20.glGetShaderiv(glCreateShader, 35713, allocate);
        if (allocate.get(0) != 0) {
            return glCreateShader;
        }
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean linkProgram(int i) {
        GLES20.glLinkProgram(i);
        IntBuffer allocate = IntBuffer.allocate(1);
        GLES20.glGetProgramiv(i, 35714, allocate);
        if (allocate.get(0) == 0) {
            return false;
        }
        return true;
    }

    public void render(VideoBuffer videoBuffer) {
        if (!this.renderRequested) {
            this.renderRequested = true;
            if (this.state == null) {
                this.state = new OpenGLState(videoBuffer);
            }
            requestRender();
        }
    }
}
