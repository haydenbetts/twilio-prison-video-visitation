package org.bytedeco.javacv;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JFrame;

public class CanvasFrame extends JFrame {
    public static final long DEFAULT_LATENCY = 200;
    public static CanvasFrame global;
    /* access modifiers changed from: private */
    public BufferedImage buffer;
    protected Canvas canvas;
    /* access modifiers changed from: private */
    public Color color;
    private Java2DFrameConverter converter;
    /* access modifiers changed from: private */
    public Image image;
    protected double initialScale;
    protected double inverseGamma;
    /* access modifiers changed from: private */
    public KeyEvent keyEvent;
    /* access modifiers changed from: private */
    public KeyEventDispatcher keyEventDispatch;
    private long latency;
    protected boolean needInitialResize;

    public static class Exception extends Exception {
        public Exception(String str) {
            super(str);
        }

        public Exception(String str, Throwable th) {
            super(str, th);
        }
    }

    public static String[] getScreenDescriptions() {
        GraphicsDevice[] screenDevices = getScreenDevices();
        String[] strArr = new String[screenDevices.length];
        for (int i = 0; i < screenDevices.length; i++) {
            strArr[i] = screenDevices[i].getIDstring();
        }
        return strArr;
    }

    public static DisplayMode getDisplayMode(int i) {
        GraphicsDevice[] screenDevices = getScreenDevices();
        if (i < 0 || i >= screenDevices.length) {
            return null;
        }
        return screenDevices[i].getDisplayMode();
    }

    public static double getGamma(int i) {
        GraphicsDevice[] screenDevices = getScreenDevices();
        if (i < 0 || i >= screenDevices.length) {
            return 0.0d;
        }
        return getGamma(screenDevices[i]);
    }

    public static double getDefaultGamma() {
        return getGamma(getDefaultScreenDevice());
    }

    public static double getGamma(GraphicsDevice graphicsDevice) {
        ICC_ColorSpace colorSpace = graphicsDevice.getDefaultConfiguration().getColorModel().getColorSpace();
        if (colorSpace.isCS_sRGB()) {
            return 2.2d;
        }
        try {
            return (double) colorSpace.getProfile().getGamma(0);
        } catch (RuntimeException unused) {
            return 0.0d;
        }
    }

    public static GraphicsDevice getScreenDevice(int i) throws Exception {
        GraphicsDevice[] screenDevices = getScreenDevices();
        if (i < screenDevices.length) {
            return screenDevices[i];
        }
        throw new Exception("CanvasFrame Error: Screen number " + i + " not found. There are only " + screenDevices.length + " screens.");
    }

    public static GraphicsDevice[] getScreenDevices() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    }

    public static GraphicsDevice getDefaultScreenDevice() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    public CanvasFrame(String str) {
        this(str, 0.0d);
    }

    public CanvasFrame(String str, double d) {
        super(str);
        this.latency = 200;
        this.keyEvent = null;
        this.keyEventDispatch = new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getID() != 401) {
                    return false;
                }
                synchronized (CanvasFrame.this) {
                    KeyEvent unused = CanvasFrame.this.keyEvent = keyEvent;
                    CanvasFrame.this.notify();
                }
                return false;
            }
        };
        this.canvas = null;
        this.needInitialResize = false;
        this.initialScale = 1.0d;
        this.inverseGamma = 1.0d;
        this.color = null;
        this.image = null;
        this.buffer = null;
        this.converter = new Java2DFrameConverter();
        init(false, (DisplayMode) null, d);
    }

    public CanvasFrame(String str, GraphicsConfiguration graphicsConfiguration) {
        this(str, graphicsConfiguration, 0.0d);
    }

    public CanvasFrame(String str, GraphicsConfiguration graphicsConfiguration, double d) {
        super(str, graphicsConfiguration);
        this.latency = 200;
        this.keyEvent = null;
        this.keyEventDispatch = new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getID() != 401) {
                    return false;
                }
                synchronized (CanvasFrame.this) {
                    KeyEvent unused = CanvasFrame.this.keyEvent = keyEvent;
                    CanvasFrame.this.notify();
                }
                return false;
            }
        };
        this.canvas = null;
        this.needInitialResize = false;
        this.initialScale = 1.0d;
        this.inverseGamma = 1.0d;
        this.color = null;
        this.image = null;
        this.buffer = null;
        this.converter = new Java2DFrameConverter();
        init(false, (DisplayMode) null, d);
    }

    public CanvasFrame(String str, int i, DisplayMode displayMode) throws Exception {
        this(str, i, displayMode, 0.0d);
    }

    public CanvasFrame(String str, int i, DisplayMode displayMode, double d) throws Exception {
        super(str, getScreenDevice(i).getDefaultConfiguration());
        this.latency = 200;
        this.keyEvent = null;
        this.keyEventDispatch = new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getID() != 401) {
                    return false;
                }
                synchronized (CanvasFrame.this) {
                    KeyEvent unused = CanvasFrame.this.keyEvent = keyEvent;
                    CanvasFrame.this.notify();
                }
                return false;
            }
        };
        this.canvas = null;
        this.needInitialResize = false;
        this.initialScale = 1.0d;
        this.inverseGamma = 1.0d;
        this.color = null;
        this.image = null;
        this.buffer = null;
        this.converter = new Java2DFrameConverter();
        init(true, displayMode, d);
    }

    private void init(boolean z, DisplayMode displayMode, double d) {
        final DisplayMode displayMode2 = displayMode;
        final boolean z2 = z;
        final double d2 = d;
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                DisplayMode displayMode;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(CanvasFrame.this.keyEventDispatch);
                GraphicsDevice device = CanvasFrame.this.getGraphicsConfiguration().getDevice();
                DisplayMode displayMode2 = device.getDisplayMode();
                DisplayMode displayMode3 = displayMode2;
                if (displayMode3 == null || displayMode2 == null) {
                    displayMode = null;
                } else {
                    int width = displayMode3.getWidth();
                    int height = displayMode2.getHeight();
                    int bitDepth = displayMode2.getBitDepth();
                    int refreshRate = displayMode2.getRefreshRate();
                    if (width <= 0) {
                        width = displayMode2.getWidth();
                    }
                    if (height <= 0) {
                        height = displayMode2.getHeight();
                    }
                    if (bitDepth <= 0) {
                        bitDepth = displayMode2.getBitDepth();
                    }
                    if (refreshRate <= 0) {
                        refreshRate = displayMode2.getRefreshRate();
                    }
                    displayMode = new DisplayMode(width, height, bitDepth, refreshRate);
                }
                if (z2) {
                    CanvasFrame.this.setUndecorated(true);
                    CanvasFrame.this.getRootPane().setWindowDecorationStyle(0);
                    CanvasFrame.this.setResizable(false);
                    device.setFullScreenWindow(CanvasFrame.this);
                } else {
                    CanvasFrame.this.setLocationByPlatform(true);
                }
                if (displayMode != null && !displayMode.equals(displayMode2)) {
                    device.setDisplayMode(displayMode);
                }
                double d = d2;
                if (d == 0.0d) {
                    d = CanvasFrame.getGamma(device);
                }
                CanvasFrame canvasFrame = CanvasFrame.this;
                double d2 = 1.0d;
                if (d != 0.0d) {
                    d2 = 1.0d / d;
                }
                canvasFrame.inverseGamma = d2;
                CanvasFrame.this.setVisible(true);
                CanvasFrame.this.initCanvas(z2, displayMode2, d2);
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

    /* access modifiers changed from: protected */
    public void initCanvas(boolean z, DisplayMode displayMode, double d) {
        AnonymousClass2 r2 = new Canvas() {
            public void update(Graphics graphics) {
                paint(graphics);
            }

            public void paint(Graphics graphics) {
                try {
                    if (CanvasFrame.this.canvas.getWidth() <= 0) {
                        return;
                    }
                    if (CanvasFrame.this.canvas.getHeight() > 0) {
                        BufferStrategy bufferStrategy = CanvasFrame.this.canvas.getBufferStrategy();
                        while (true) {
                            Graphics drawGraphics = bufferStrategy.getDrawGraphics();
                            if (CanvasFrame.this.color != null) {
                                drawGraphics.setColor(CanvasFrame.this.color);
                                drawGraphics.fillRect(0, 0, getWidth(), getHeight());
                            }
                            if (CanvasFrame.this.image != null) {
                                drawGraphics.drawImage(CanvasFrame.this.image, 0, 0, getWidth(), getHeight(), (ImageObserver) null);
                            }
                            if (CanvasFrame.this.buffer != null) {
                                drawGraphics.drawImage(CanvasFrame.this.buffer, 0, 0, getWidth(), getHeight(), (ImageObserver) null);
                            }
                            drawGraphics.dispose();
                            if (!bufferStrategy.contentsRestored()) {
                                bufferStrategy.show();
                                if (!bufferStrategy.contentsLost()) {
                                    return;
                                }
                            }
                        }
                    }
                } catch (IllegalStateException | NullPointerException unused) {
                }
            }
        };
        this.canvas = r2;
        if (z) {
            r2.setSize(getSize());
            this.needInitialResize = false;
        } else {
            r2.setSize(10, 10);
            this.needInitialResize = true;
        }
        getContentPane().add(this.canvas);
        this.canvas.setVisible(true);
        this.canvas.createBufferStrategy(2);
    }

    public long getLatency() {
        return this.latency;
    }

    public void setLatency(long j) {
        this.latency = j;
    }

    public void waitLatency() throws InterruptedException {
        Thread.sleep(getLatency());
    }

    public KeyEvent waitKey() throws InterruptedException {
        return waitKey(0);
    }

    public synchronized KeyEvent waitKey(int i) throws InterruptedException {
        KeyEvent keyEvent2;
        if (i >= 0) {
            this.keyEvent = null;
            wait((long) i);
        }
        keyEvent2 = this.keyEvent;
        this.keyEvent = null;
        return keyEvent2;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Dimension getCanvasSize() {
        return this.canvas.getSize();
    }

    public void setCanvasSize(final int i, final int i2) {
        Dimension canvasSize = getCanvasSize();
        if (canvasSize.width != i || canvasSize.height != i2) {
            AnonymousClass4 r0 = new Runnable() {
                public void run() {
                    CanvasFrame.this.setExtendedState(0);
                    CanvasFrame.this.canvas.setSize(i, i2);
                    CanvasFrame.this.pack();
                    CanvasFrame.this.canvas.setSize(i + 1, i2 + 1);
                    CanvasFrame.this.canvas.setSize(i, i2);
                    CanvasFrame.this.needInitialResize = false;
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
    }

    public double getCanvasScale() {
        return this.initialScale;
    }

    public void setCanvasScale(double d) {
        this.initialScale = d;
        this.needInitialResize = true;
    }

    public Graphics2D createGraphics() {
        BufferedImage bufferedImage = this.buffer;
        if (!(bufferedImage != null && bufferedImage.getWidth() == this.canvas.getWidth() && this.buffer.getHeight() == this.canvas.getHeight())) {
            BufferedImage createCompatibleImage = this.canvas.getGraphicsConfiguration().createCompatibleImage(this.canvas.getWidth(), this.canvas.getHeight(), 3);
            if (this.buffer != null) {
                Graphics graphics = createCompatibleImage.getGraphics();
                graphics.drawImage(this.buffer, 0, 0, (ImageObserver) null);
                graphics.dispose();
            }
            this.buffer = createCompatibleImage;
        }
        return this.buffer.createGraphics();
    }

    public void releaseGraphics(Graphics2D graphics2D) {
        graphics2D.dispose();
        this.canvas.paint((Graphics) null);
    }

    public void showColor(Color color2) {
        this.color = color2;
        this.image = null;
        this.canvas.paint((Graphics) null);
    }

    public void showImage(Frame frame) {
        showImage(frame, false);
    }

    public void showImage(Frame frame, boolean z) {
        showImage((Image) this.converter.getBufferedImage(frame, Java2DFrameConverter.getBufferedImageType(frame) == 0 ? 1.0d : this.inverseGamma, z, (ColorSpace) null));
    }

    public void showImage(Image image2) {
        if (image2 != null) {
            if (isResizable() && this.needInitialResize) {
                setCanvasSize((int) Math.round(((double) image2.getWidth((ImageObserver) null)) * this.initialScale), (int) Math.round(((double) image2.getHeight((ImageObserver) null)) * this.initialScale));
            }
            this.color = null;
            this.image = image2;
            this.canvas.paint((Graphics) null);
        }
    }

    public static void tile(final CanvasFrame[] canvasFrameArr) {
        final AnonymousClass1MovedListener r6 = new ComponentAdapter() {
            boolean moved = false;

            public void componentMoved(ComponentEvent componentEvent) {
                this.moved = true;
                Component component = componentEvent.getComponent();
                synchronized (component) {
                    component.notify();
                }
            }
        };
        int round = (int) Math.round(Math.sqrt((double) canvasFrameArr.length));
        if (round * round < canvasFrameArr.length) {
            round++;
        }
        int i = round;
        final int i2 = 0;
        final int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < canvasFrameArr.length) {
            try {
                r6.moved = false;
                final CanvasFrame[] canvasFrameArr2 = canvasFrameArr;
                final int i6 = i3;
                final AnonymousClass1MovedListener r3 = r6;
                final int i7 = i4;
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        canvasFrameArr2[i6].addComponentListener(r3);
                        canvasFrameArr2[i6].setLocation(i2, i7);
                    }
                });
                int i8 = 0;
                while (!r6.moved && i8 < 5) {
                    synchronized (canvasFrameArr[i3]) {
                        canvasFrameArr[i3].wait(100);
                    }
                    i8++;
                }
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        canvasFrameArr[i3].removeComponentListener(r6);
                    }
                });
            } catch (Exception unused) {
            }
            int x = canvasFrameArr[i3].getX() + canvasFrameArr[i3].getWidth();
            i5 = Math.max(i5, canvasFrameArr[i3].getY() + canvasFrameArr[i3].getHeight());
            i3++;
            if (i3 % i == 0) {
                i4 = i5;
                i2 = 0;
            } else {
                i2 = x;
            }
        }
    }
}
