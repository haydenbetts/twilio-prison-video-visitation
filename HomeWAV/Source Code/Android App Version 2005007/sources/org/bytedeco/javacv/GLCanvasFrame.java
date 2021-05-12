package org.bytedeco.javacv;

import com.jogamp.opencl.CLMemory;
import com.jogamp.opencl.gl.CLGLImage2d;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilitiesImmutable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Gamma;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;

public class GLCanvasFrame extends CanvasFrame {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public static GLCanvasFrame canvasFrame;
    /* access modifiers changed from: private */
    public Buffer buffer;
    /* access modifiers changed from: private */
    public Color color;
    /* access modifiers changed from: private */
    public GLEventListener eventListener;
    /* access modifiers changed from: private */
    public int format;
    /* access modifiers changed from: private */
    public int frameBuffer;
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    public int[] params;
    /* access modifiers changed from: private */
    public int renderBuffer;
    /* access modifiers changed from: private */
    public int type;
    /* access modifiers changed from: private */
    public int width;

    /* access modifiers changed from: protected */
    public void initCanvas(boolean z, DisplayMode displayMode, double d) {
    }

    public GLCanvasFrame(String str) {
        this(str, 0.0d);
    }

    public GLCanvasFrame(String str, double d) {
        super(str, d);
        this.params = new int[2];
        this.color = null;
        this.buffer = null;
        this.frameBuffer = 0;
        this.renderBuffer = 0;
        this.eventListener = new GLEventListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            public void reshape(GLAutoDrawable gLAutoDrawable, int i, int i2, int i3, int i4) {
            }

            static {
                Class<GLCanvasFrame> cls = GLCanvasFrame.class;
            }

            public void init(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                gl2.setSwapInterval(1);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.setDisplayGamma(gLAutoDrawable, (float) GLCanvasFrame.this.inverseGamma, 0.0f, 1.0f);
                }
                gl2.glGenFramebuffers(1, GLCanvasFrame.this.params, 0);
                GLCanvasFrame gLCanvasFrame = GLCanvasFrame.this;
                int unused = gLCanvasFrame.frameBuffer = gLCanvasFrame.params[0];
            }

            public void dispose(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                GLCanvasFrame.this.params[0] = GLCanvasFrame.this.frameBuffer;
                gl2.glDeleteFramebuffers(1, GLCanvasFrame.this.params, 0);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.resetDisplayGamma(gLAutoDrawable);
                }
            }

            public void display(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                if (GLCanvasFrame.this.color != null) {
                    gl2.glClearColor(((float) GLCanvasFrame.this.color.getRed()) / 255.0f, ((float) GLCanvasFrame.this.color.getGreen()) / 255.0f, ((float) GLCanvasFrame.this.color.getBlue()) / 255.0f, 1.0f);
                    gl2.glClear(16384);
                } else if (GLCanvasFrame.this.buffer != null) {
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.width) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.height) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glWindowPos2i(0, GLCanvasFrame.this.canvas.getHeight());
                    gl2.glPixelZoom(((float) GLCanvasFrame.this.canvas.getWidth()) / ((float) GLCanvasFrame.this.width), (-((float) GLCanvasFrame.this.canvas.getHeight())) / ((float) GLCanvasFrame.this.height));
                    gl2.glDrawPixels(GLCanvasFrame.this.width, GLCanvasFrame.this.height, GLCanvasFrame.this.format, GLCanvasFrame.this.type, GLCanvasFrame.this.buffer);
                } else if (GLCanvasFrame.this.renderBuffer > 0) {
                    gl2.glBindRenderbuffer(36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glGetRenderbufferParameteriv(36161, 36162, GLCanvasFrame.this.params, 0);
                    gl2.glGetRenderbufferParameteriv(36161, 36163, GLCanvasFrame.this.params, 1);
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.params[0]) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.params[1]) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glBindFramebuffer(36008, GLCanvasFrame.this.frameBuffer);
                    gl2.glFramebufferRenderbuffer(36008, 36064, 36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glBlitFramebuffer(0, 0, GLCanvasFrame.this.params[0], GLCanvasFrame.this.params[1], 0, GLCanvasFrame.this.canvas.getHeight(), GLCanvasFrame.this.canvas.getWidth(), 0, 16384, 9729);
                }
            }
        };
        init(false, (GLCapabilitiesImmutable) null, (GLContext) null);
    }

    public GLCanvasFrame(String str, GraphicsConfiguration graphicsConfiguration, GLCapabilitiesImmutable gLCapabilitiesImmutable, GLContext gLContext) {
        this(str, graphicsConfiguration, gLCapabilitiesImmutable, gLContext, 0.0d);
    }

    public GLCanvasFrame(String str, GraphicsConfiguration graphicsConfiguration, GLCapabilitiesImmutable gLCapabilitiesImmutable, GLContext gLContext, double d) {
        super(str, graphicsConfiguration, d);
        this.params = new int[2];
        this.color = null;
        this.buffer = null;
        this.frameBuffer = 0;
        this.renderBuffer = 0;
        this.eventListener = new GLEventListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            public void reshape(GLAutoDrawable gLAutoDrawable, int i, int i2, int i3, int i4) {
            }

            static {
                Class<GLCanvasFrame> cls = GLCanvasFrame.class;
            }

            public void init(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                gl2.setSwapInterval(1);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.setDisplayGamma(gLAutoDrawable, (float) GLCanvasFrame.this.inverseGamma, 0.0f, 1.0f);
                }
                gl2.glGenFramebuffers(1, GLCanvasFrame.this.params, 0);
                GLCanvasFrame gLCanvasFrame = GLCanvasFrame.this;
                int unused = gLCanvasFrame.frameBuffer = gLCanvasFrame.params[0];
            }

            public void dispose(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                GLCanvasFrame.this.params[0] = GLCanvasFrame.this.frameBuffer;
                gl2.glDeleteFramebuffers(1, GLCanvasFrame.this.params, 0);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.resetDisplayGamma(gLAutoDrawable);
                }
            }

            public void display(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                if (GLCanvasFrame.this.color != null) {
                    gl2.glClearColor(((float) GLCanvasFrame.this.color.getRed()) / 255.0f, ((float) GLCanvasFrame.this.color.getGreen()) / 255.0f, ((float) GLCanvasFrame.this.color.getBlue()) / 255.0f, 1.0f);
                    gl2.glClear(16384);
                } else if (GLCanvasFrame.this.buffer != null) {
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.width) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.height) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glWindowPos2i(0, GLCanvasFrame.this.canvas.getHeight());
                    gl2.glPixelZoom(((float) GLCanvasFrame.this.canvas.getWidth()) / ((float) GLCanvasFrame.this.width), (-((float) GLCanvasFrame.this.canvas.getHeight())) / ((float) GLCanvasFrame.this.height));
                    gl2.glDrawPixels(GLCanvasFrame.this.width, GLCanvasFrame.this.height, GLCanvasFrame.this.format, GLCanvasFrame.this.type, GLCanvasFrame.this.buffer);
                } else if (GLCanvasFrame.this.renderBuffer > 0) {
                    gl2.glBindRenderbuffer(36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glGetRenderbufferParameteriv(36161, 36162, GLCanvasFrame.this.params, 0);
                    gl2.glGetRenderbufferParameteriv(36161, 36163, GLCanvasFrame.this.params, 1);
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.params[0]) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.params[1]) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glBindFramebuffer(36008, GLCanvasFrame.this.frameBuffer);
                    gl2.glFramebufferRenderbuffer(36008, 36064, 36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glBlitFramebuffer(0, 0, GLCanvasFrame.this.params[0], GLCanvasFrame.this.params[1], 0, GLCanvasFrame.this.canvas.getHeight(), GLCanvasFrame.this.canvas.getWidth(), 0, 16384, 9729);
                }
            }
        };
        init(false, gLCapabilitiesImmutable, gLContext);
    }

    public GLCanvasFrame(String str, int i, DisplayMode displayMode) throws CanvasFrame.Exception {
        this(str, i, displayMode, 0.0d);
    }

    public GLCanvasFrame(String str, int i, DisplayMode displayMode, double d) throws CanvasFrame.Exception {
        super(str, i, displayMode, d);
        this.params = new int[2];
        this.color = null;
        this.buffer = null;
        this.frameBuffer = 0;
        this.renderBuffer = 0;
        this.eventListener = new GLEventListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            public void reshape(GLAutoDrawable gLAutoDrawable, int i, int i2, int i3, int i4) {
            }

            static {
                Class<GLCanvasFrame> cls = GLCanvasFrame.class;
            }

            public void init(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                gl2.setSwapInterval(1);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.setDisplayGamma(gLAutoDrawable, (float) GLCanvasFrame.this.inverseGamma, 0.0f, 1.0f);
                }
                gl2.glGenFramebuffers(1, GLCanvasFrame.this.params, 0);
                GLCanvasFrame gLCanvasFrame = GLCanvasFrame.this;
                int unused = gLCanvasFrame.frameBuffer = gLCanvasFrame.params[0];
            }

            public void dispose(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                GLCanvasFrame.this.params[0] = GLCanvasFrame.this.frameBuffer;
                gl2.glDeleteFramebuffers(1, GLCanvasFrame.this.params, 0);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.resetDisplayGamma(gLAutoDrawable);
                }
            }

            public void display(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                if (GLCanvasFrame.this.color != null) {
                    gl2.glClearColor(((float) GLCanvasFrame.this.color.getRed()) / 255.0f, ((float) GLCanvasFrame.this.color.getGreen()) / 255.0f, ((float) GLCanvasFrame.this.color.getBlue()) / 255.0f, 1.0f);
                    gl2.glClear(16384);
                } else if (GLCanvasFrame.this.buffer != null) {
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.width) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.height) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glWindowPos2i(0, GLCanvasFrame.this.canvas.getHeight());
                    gl2.glPixelZoom(((float) GLCanvasFrame.this.canvas.getWidth()) / ((float) GLCanvasFrame.this.width), (-((float) GLCanvasFrame.this.canvas.getHeight())) / ((float) GLCanvasFrame.this.height));
                    gl2.glDrawPixels(GLCanvasFrame.this.width, GLCanvasFrame.this.height, GLCanvasFrame.this.format, GLCanvasFrame.this.type, GLCanvasFrame.this.buffer);
                } else if (GLCanvasFrame.this.renderBuffer > 0) {
                    gl2.glBindRenderbuffer(36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glGetRenderbufferParameteriv(36161, 36162, GLCanvasFrame.this.params, 0);
                    gl2.glGetRenderbufferParameteriv(36161, 36163, GLCanvasFrame.this.params, 1);
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.params[0]) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.params[1]) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glBindFramebuffer(36008, GLCanvasFrame.this.frameBuffer);
                    gl2.glFramebufferRenderbuffer(36008, 36064, 36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glBlitFramebuffer(0, 0, GLCanvasFrame.this.params[0], GLCanvasFrame.this.params[1], 0, GLCanvasFrame.this.canvas.getHeight(), GLCanvasFrame.this.canvas.getWidth(), 0, 16384, 9729);
                }
            }
        };
        init(true, (GLCapabilitiesImmutable) null, (GLContext) null);
    }

    public GLCanvasFrame(String str, int i, DisplayMode displayMode, GLCapabilitiesImmutable gLCapabilitiesImmutable, GLContext gLContext) throws CanvasFrame.Exception {
        this(str, i, displayMode, gLCapabilitiesImmutable, gLContext, 0.0d);
    }

    public GLCanvasFrame(String str, int i, DisplayMode displayMode, GLCapabilitiesImmutable gLCapabilitiesImmutable, GLContext gLContext, double d) throws CanvasFrame.Exception {
        super(str, i, displayMode, d);
        this.params = new int[2];
        this.color = null;
        this.buffer = null;
        this.frameBuffer = 0;
        this.renderBuffer = 0;
        this.eventListener = new GLEventListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            public void reshape(GLAutoDrawable gLAutoDrawable, int i, int i2, int i3, int i4) {
            }

            static {
                Class<GLCanvasFrame> cls = GLCanvasFrame.class;
            }

            public void init(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                gl2.setSwapInterval(1);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.setDisplayGamma(gLAutoDrawable, (float) GLCanvasFrame.this.inverseGamma, 0.0f, 1.0f);
                }
                gl2.glGenFramebuffers(1, GLCanvasFrame.this.params, 0);
                GLCanvasFrame gLCanvasFrame = GLCanvasFrame.this;
                int unused = gLCanvasFrame.frameBuffer = gLCanvasFrame.params[0];
            }

            public void dispose(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                GLCanvasFrame.this.params[0] = GLCanvasFrame.this.frameBuffer;
                gl2.glDeleteFramebuffers(1, GLCanvasFrame.this.params, 0);
                if (GLCanvasFrame.this.inverseGamma != 1.0d) {
                    Gamma.resetDisplayGamma(gLAutoDrawable);
                }
            }

            public void display(GLAutoDrawable gLAutoDrawable) {
                GL2 gl2 = gLAutoDrawable.getGL().getGL2();
                if (GLCanvasFrame.this.color != null) {
                    gl2.glClearColor(((float) GLCanvasFrame.this.color.getRed()) / 255.0f, ((float) GLCanvasFrame.this.color.getGreen()) / 255.0f, ((float) GLCanvasFrame.this.color.getBlue()) / 255.0f, 1.0f);
                    gl2.glClear(16384);
                } else if (GLCanvasFrame.this.buffer != null) {
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.width) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.height) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glWindowPos2i(0, GLCanvasFrame.this.canvas.getHeight());
                    gl2.glPixelZoom(((float) GLCanvasFrame.this.canvas.getWidth()) / ((float) GLCanvasFrame.this.width), (-((float) GLCanvasFrame.this.canvas.getHeight())) / ((float) GLCanvasFrame.this.height));
                    gl2.glDrawPixels(GLCanvasFrame.this.width, GLCanvasFrame.this.height, GLCanvasFrame.this.format, GLCanvasFrame.this.type, GLCanvasFrame.this.buffer);
                } else if (GLCanvasFrame.this.renderBuffer > 0) {
                    gl2.glBindRenderbuffer(36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glGetRenderbufferParameteriv(36161, 36162, GLCanvasFrame.this.params, 0);
                    gl2.glGetRenderbufferParameteriv(36161, 36163, GLCanvasFrame.this.params, 1);
                    if (GLCanvasFrame.this.isResizable() && GLCanvasFrame.this.needInitialResize) {
                        GLCanvasFrame.this.setCanvasSize((int) Math.round(((double) GLCanvasFrame.this.params[0]) * GLCanvasFrame.this.initialScale), (int) Math.round(((double) GLCanvasFrame.this.params[1]) * GLCanvasFrame.this.initialScale));
                    }
                    gl2.glBindFramebuffer(36008, GLCanvasFrame.this.frameBuffer);
                    gl2.glFramebufferRenderbuffer(36008, 36064, 36161, GLCanvasFrame.this.renderBuffer);
                    gl2.glBlitFramebuffer(0, 0, GLCanvasFrame.this.params[0], GLCanvasFrame.this.params[1], 0, GLCanvasFrame.this.canvas.getHeight(), GLCanvasFrame.this.canvas.getWidth(), 0, 16384, 9729);
                }
            }
        };
        init(true, gLCapabilitiesImmutable, gLContext);
    }

    private void init(final boolean z, final GLCapabilitiesImmutable gLCapabilitiesImmutable, final GLContext gLContext) {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                String property = System.setProperty("sun.awt.noerasebackground", "true");
                GLCanvasFrame.this.canvas = new GLCanvas(gLCapabilitiesImmutable);
                if (gLContext != null) {
                    GLCanvasFrame.this.canvas.setSharedContext(gLContext);
                }
                GLCanvasFrame.this.canvas.addGLEventListener(GLCanvasFrame.this.eventListener);
                if (z) {
                    GLCanvasFrame.this.canvas.setSize(GLCanvasFrame.this.getSize());
                    GLCanvasFrame.this.needInitialResize = false;
                } else {
                    GLCanvasFrame.this.canvas.setSize(1, 1);
                    GLCanvasFrame.this.needInitialResize = true;
                }
                GLCanvasFrame.this.getContentPane().add(GLCanvasFrame.this.canvas);
                GLCanvasFrame.this.canvas.setVisible(true);
                if (property != null) {
                    System.setProperty("sun.awt.noerasebackground", property);
                } else {
                    System.clearProperty("sun.awt.noerasebackground");
                }
            }
        };
        if (EventQueue.isDispatchThread()) {
            r0.run();
            return;
        }
        try {
            EventQueue.invokeAndWait(r0);
        } catch (Exception unused) {
        }
    }

    public GLCanvas getGLCanvas() {
        return this.canvas;
    }

    public void showColor(Color color2) {
        this.color = color2;
        this.buffer = null;
        getGLCanvas().display();
    }

    public void showImage(Frame frame) {
        showImage(frame, false);
    }

    public void showImage(Frame frame, boolean z) {
        if (z) {
            throw new RuntimeException("GLCanvasFrame does not support channel flipping.");
        } else if (frame != null) {
            this.color = null;
            this.width = frame.imageWidth;
            this.height = frame.imageHeight;
            this.buffer = frame.image[0];
            int i = frame.imageDepth;
            if (i == -32) {
                this.type = 5124;
            } else if (i == -16) {
                this.type = 5122;
            } else if (i == -8) {
                this.type = 5120;
            } else if (i == 8) {
                this.type = 5121;
            } else if (i == 16) {
                this.type = 5123;
            } else if (i == 32) {
                this.type = 5126;
            } else if (i == 64) {
                this.type = 5130;
            }
            int i2 = frame.imageChannels;
            if (i2 == 1) {
                this.format = 6409;
            } else if (i2 == 2) {
                this.format = 33319;
            } else if (i2 == 3) {
                this.format = 6407;
            } else if (i2 == 4) {
                this.format = 6408;
            }
            getGLCanvas().display();
        }
    }

    public void showImage(Image image) {
        if (image instanceof BufferedImage) {
            showImage((BufferedImage) image);
            return;
        }
        throw new RuntimeException("GLCanvasFrame does not support " + image + ", BufferedImage required.");
    }

    public void showImage(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            this.color = null;
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();
            DataBufferByte dataBuffer = bufferedImage.getRaster().getDataBuffer();
            if (dataBuffer instanceof DataBufferByte) {
                this.buffer = ByteBuffer.wrap(dataBuffer.getData());
                this.type = 5121;
            } else if (dataBuffer instanceof DataBufferDouble) {
                this.buffer = DoubleBuffer.wrap(((DataBufferDouble) dataBuffer).getData());
                this.type = 5130;
            } else if (dataBuffer instanceof DataBufferFloat) {
                this.buffer = FloatBuffer.wrap(((DataBufferFloat) dataBuffer).getData());
                this.type = 5126;
            } else if (dataBuffer instanceof DataBufferInt) {
                this.buffer = IntBuffer.wrap(((DataBufferInt) dataBuffer).getData());
                this.type = 5124;
            } else if (dataBuffer instanceof DataBufferShort) {
                this.buffer = ShortBuffer.wrap(((DataBufferShort) dataBuffer).getData());
                this.type = 5122;
            } else if (dataBuffer instanceof DataBufferUShort) {
                this.buffer = ShortBuffer.wrap(((DataBufferUShort) dataBuffer).getData());
                this.type = 5123;
            }
            int numBands = bufferedImage.getSampleModel().getNumBands();
            if (numBands == 1) {
                this.format = 6409;
            } else if (numBands == 2) {
                this.format = 33319;
            } else if (numBands == 3) {
                this.format = 6407;
            } else if (numBands == 4) {
                this.format = 6408;
            }
            getGLCanvas().display();
        }
    }

    public void showImage(int i) {
        if (i > 0) {
            this.color = null;
            this.buffer = null;
            this.renderBuffer = i;
            getGLCanvas().display();
        }
    }

    public static void main(String[] strArr) throws Exception {
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                try {
                    GLCanvasFrame unused = GLCanvasFrame.canvasFrame = new GLCanvasFrame("Some Title");
                    GLCanvasFrame.canvasFrame.setDefaultCloseOperation(3);
                    GLCanvasFrame.canvasFrame.showColor(Color.BLUE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JavaCVCL javaCVCL = new JavaCVCL(canvasFrame.getGLCanvas().getContext());
        IplImage cvLoadImageBGRA = opencv_imgcodecs.cvLoadImageBGRA("/usr/share/opencv/samples/c/lena.jpg");
        CLGLImage2d createCLGLImageFrom = javaCVCL.createCLGLImageFrom(cvLoadImageBGRA, new CLMemory.Mem[0]);
        javaCVCL.acquireGLObject(createCLGLImageFrom);
        javaCVCL.writeImage(createCLGLImageFrom, cvLoadImageBGRA, true);
        javaCVCL.releaseGLObject(createCLGLImageFrom);
        canvasFrame.setCanvasScale(0.5d);
        for (int i = 0; i < 1000; i++) {
            canvasFrame.showImage(createCLGLImageFrom.getGLObjectID());
            Thread.sleep(10);
            canvasFrame.showColor(Color.RED);
            Thread.sleep(10);
        }
        canvasFrame.waitKey();
        javaCVCL.release();
        System.exit(0);
    }
}
