package org.bytedeco.javacv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public class IPCameraFrameGrabber extends FrameGrabber {
    private static FrameGrabber.Exception loadingException;
    private final int connectionTimeout;
    private final FrameConverter converter;
    private Mat decoded;
    private DataInputStream input;
    private byte[] pixelBuffer;
    private final int readTimeout;
    private final URL url;

    public void release() throws FrameGrabber.Exception {
    }

    public void trigger() throws FrameGrabber.Exception {
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(opencv_highgui.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + IPCameraFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public IPCameraFrameGrabber(URL url2, int i, int i2, TimeUnit timeUnit) {
        this.converter = new OpenCVFrameConverter.ToMat();
        this.pixelBuffer = new byte[1024];
        this.decoded = null;
        if (url2 != null) {
            this.url = url2;
            if (timeUnit != null) {
                this.connectionTimeout = toIntExact(TimeUnit.MILLISECONDS.convert((long) i, timeUnit));
                this.readTimeout = toIntExact(TimeUnit.MILLISECONDS.convert((long) i2, timeUnit));
                return;
            }
            this.connectionTimeout = -1;
            this.readTimeout = -1;
            return;
        }
        throw new IllegalArgumentException("URL can not be null");
    }

    public IPCameraFrameGrabber(String str, int i, int i2, TimeUnit timeUnit) throws MalformedURLException {
        this(new URL(str), i, i2, timeUnit);
    }

    @Deprecated
    public IPCameraFrameGrabber(String str) throws MalformedURLException {
        this(new URL(str), -1, -1, (TimeUnit) null);
    }

    public void start() throws FrameGrabber.Exception {
        try {
            URLConnection openConnection = this.url.openConnection();
            int i = this.connectionTimeout;
            if (i >= 0) {
                openConnection.setConnectTimeout(i);
            }
            int i2 = this.readTimeout;
            if (i2 >= 0) {
                openConnection.setReadTimeout(i2);
            }
            this.input = new DataInputStream(openConnection.getInputStream());
        } catch (IOException e) {
            throw new FrameGrabber.Exception(e.getMessage(), e);
        }
    }

    public void stop() throws FrameGrabber.Exception {
        DataInputStream dataInputStream = this.input;
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
                this.input = null;
                releaseDecoded();
            } catch (IOException e) {
                throw new FrameGrabber.Exception(e.getMessage(), e);
            } catch (Throwable th) {
                this.input = null;
                releaseDecoded();
                throw th;
            }
        }
    }

    public Frame grab() throws FrameGrabber.Exception {
        try {
            byte[] readImage = readImage();
            Mat mat = new Mat(1, readImage.length, opencv_core.CV_8UC1, (Pointer) new BytePointer(readImage));
            releaseDecoded();
            FrameConverter frameConverter = this.converter;
            Mat imdecode = opencv_imgcodecs.imdecode(mat, 1);
            this.decoded = imdecode;
            return frameConverter.convert(imdecode);
        } catch (IOException e) {
            throw new FrameGrabber.Exception(e.getMessage(), e);
        }
    }

    public BufferedImage grabBufferedImage() throws IOException {
        return ImageIO.read(new ByteArrayInputStream(readImage()));
    }

    private void releaseDecoded() {
        Mat mat = this.decoded;
        if (mat != null) {
            mat.release();
            this.decoded = null;
        }
    }

    private byte[] readImage() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = this.input.read();
            if (read == -1) {
                break;
            } else if (read > 0) {
                stringBuffer.append((char) read);
                if (read == 13) {
                    stringBuffer.append((char) this.input.read());
                    int read2 = this.input.read();
                    stringBuffer.append((char) read2);
                    if (read2 == 13) {
                        stringBuffer.append((char) this.input.read());
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        String lowerCase = stringBuffer.toString().toLowerCase();
        int indexOf = lowerCase.indexOf("content-length: ");
        int indexOf2 = lowerCase.indexOf(13, indexOf);
        if (indexOf >= 0) {
            int parseInt = Integer.parseInt(lowerCase.substring(indexOf + 16, indexOf2).trim());
            ensureBufferCapacity(parseInt);
            this.input.readFully(this.pixelBuffer, 0, parseInt);
            this.input.read();
            this.input.read();
            this.input.read();
            this.input.read();
            return this.pixelBuffer;
        }
        throw new EOFException("The camera stream ended unexpectedly");
    }

    private void ensureBufferCapacity(int i) {
        int length = this.pixelBuffer.length;
        while (length < i) {
            length *= 2;
        }
        if (length > this.pixelBuffer.length) {
            this.pixelBuffer = new byte[length];
        }
    }

    private static int toIntExact(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            return i;
        }
        throw new ArithmeticException("integer overflow");
    }
}
