package org.bytedeco.javacv;

import com.google.android.exoplayer2.C;
import java.io.File;
import java.util.Map;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.opencv.opencv_videoio.VideoWriter;

public class OpenCVFrameGrabber extends FrameGrabber {
    private static FrameGrabber.Exception loadingException;
    private int apiPreference;
    private VideoCapture capture;
    private final OpenCVFrameConverter converter;
    private int deviceNumber;
    private String filename;
    private final Mat mat;
    private Mat returnMatrix;

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        throw new UnsupportedOperationException("Device enumeration not support by OpenCV.");
    }

    public static OpenCVFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        return new OpenCVFrameGrabber(file);
    }

    public static OpenCVFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        return new OpenCVFrameGrabber(str);
    }

    public static OpenCVFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new OpenCVFrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(opencv_highgui.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + OpenCVFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public OpenCVFrameGrabber(int i) {
        this.deviceNumber = 0;
        this.filename = null;
        this.apiPreference = 0;
        this.capture = null;
        this.returnMatrix = null;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.mat = new Mat();
        this.deviceNumber = i;
    }

    public OpenCVFrameGrabber(File file) {
        this(file.getAbsolutePath());
    }

    public OpenCVFrameGrabber(File file, int i) {
        this(file.getAbsolutePath(), i);
    }

    public OpenCVFrameGrabber(String str) {
        this.deviceNumber = 0;
        this.filename = null;
        this.apiPreference = 0;
        this.capture = null;
        this.returnMatrix = null;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.mat = new Mat();
        this.filename = str;
    }

    public OpenCVFrameGrabber(String str, int i) {
        this.deviceNumber = 0;
        this.filename = null;
        this.apiPreference = 0;
        this.capture = null;
        this.returnMatrix = null;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.mat = new Mat();
        this.filename = str;
        this.apiPreference = i;
    }

    public void release() throws FrameGrabber.Exception {
        stop();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public double getGamma() {
        if (this.gamma == 0.0d) {
            return 2.2d;
        }
        return this.gamma;
    }

    public String getFormat() {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            return super.getFormat();
        }
        int i = (int) videoCapture.get(6);
        return "" + ((char) (i & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 24) & 255));
    }

    public int getImageWidth() {
        Mat mat2 = this.returnMatrix;
        if (mat2 != null) {
            return mat2.cols();
        }
        VideoCapture videoCapture = this.capture;
        return videoCapture == null ? super.getImageWidth() : (int) videoCapture.get(3);
    }

    public int getImageHeight() {
        Mat mat2 = this.returnMatrix;
        if (mat2 != null) {
            return mat2.rows();
        }
        VideoCapture videoCapture = this.capture;
        return videoCapture == null ? super.getImageHeight() : (int) videoCapture.get(4);
    }

    public int getPixelFormat() {
        VideoCapture videoCapture = this.capture;
        return videoCapture == null ? super.getPixelFormat() : (int) videoCapture.get(16);
    }

    public double getFrameRate() {
        VideoCapture videoCapture = this.capture;
        return videoCapture == null ? super.getFrameRate() : (double) ((int) videoCapture.get(5));
    }

    public void setImageMode(FrameGrabber.ImageMode imageMode) {
        if (imageMode != this.imageMode) {
            this.returnMatrix = null;
        }
        super.setImageMode(imageMode);
    }

    public int getFrameNumber() {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            return super.getFrameNumber();
        }
        return (int) videoCapture.get(1);
    }

    public void setFrameNumber(int i) throws FrameGrabber.Exception {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            super.setFrameNumber(i);
        } else if (!videoCapture.set(1, (double) i)) {
            throw new FrameGrabber.Exception("set() Error: Could not set CAP_PROP_POS_FRAMES to " + i + ".");
        }
    }

    public long getTimestamp() {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            return super.getTimestamp();
        }
        return Math.round(videoCapture.get(0) * 1000.0d);
    }

    public void setTimestamp(long j) throws FrameGrabber.Exception {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            super.setTimestamp(j);
            return;
        }
        double d = ((double) j) / 1000.0d;
        if (!videoCapture.set(0, d)) {
            throw new FrameGrabber.Exception("set() Error: Could not set CAP_PROP_POS_MSEC to " + d + ".");
        }
    }

    public int getLengthInFrames() {
        VideoCapture videoCapture = this.capture;
        if (videoCapture == null) {
            return super.getLengthInFrames();
        }
        return (int) videoCapture.get(7);
    }

    public long getLengthInTime() {
        return Math.round(((double) (((long) getLengthInFrames()) * C.MICROS_PER_SECOND)) / getFrameRate());
    }

    public double getOption(int i) {
        VideoCapture videoCapture = this.capture;
        if (videoCapture != null) {
            return videoCapture.get(i);
        }
        return Double.parseDouble((String) this.options.get(Integer.toString(i)));
    }

    public void setOption(int i, double d) {
        this.options.put(Integer.toString(i), Double.toString(d));
        VideoCapture videoCapture = this.capture;
        if (videoCapture != null) {
            videoCapture.set(i, d);
        }
    }

    public void start() throws FrameGrabber.Exception {
        String str = this.filename;
        if (str == null || str.length() <= 0) {
            this.capture = new VideoCapture(this.deviceNumber);
        } else {
            int i = this.apiPreference;
            if (i > 0) {
                this.capture = new VideoCapture(this.filename, i);
            } else {
                this.capture = new VideoCapture(this.filename);
            }
        }
        int i2 = 0;
        if (this.format != null && this.format.length() >= 4) {
            this.format = this.format.toUpperCase();
            this.capture.set(6, (double) VideoWriter.fourcc((byte) this.format.charAt(0), (byte) this.format.charAt(1), (byte) this.format.charAt(2), (byte) this.format.charAt(3)));
        }
        if (this.imageWidth > 0 && !this.capture.set(3, (double) this.imageWidth)) {
            this.capture.set(3, (double) this.imageWidth);
        }
        if (this.imageHeight > 0 && !this.capture.set(4, (double) this.imageHeight)) {
            this.capture.set(4, (double) this.imageHeight);
        }
        if (this.frameRate > 0.0d) {
            this.capture.set(5, this.frameRate);
        }
        if (this.bpp > 0) {
            this.capture.set(8, (double) this.bpp);
        }
        if (this.imageMode == FrameGrabber.ImageMode.RAW) {
            this.capture.set(16, 0.0d);
        }
        for (Map.Entry entry : this.options.entrySet()) {
            this.capture.set(Integer.parseInt((String) entry.getKey()), Double.parseDouble((String) entry.getValue()));
        }
        Mat mat2 = new Mat();
        while (true) {
            int i3 = i2 + 1;
            if (i2 >= 100) {
                break;
            }
            try {
                if (this.capture.read(mat2)) {
                    break;
                }
                Thread.sleep(100);
                i2 = i3;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        if (!this.capture.read(mat2)) {
            throw new FrameGrabber.Exception("read() Error: Could not read frame in start().");
        } else if (!this.triggerMode && !this.capture.grab()) {
            throw new FrameGrabber.Exception("grab() Error: Could not grab frame. (Has start() been called?)");
        }
    }

    public void stop() throws FrameGrabber.Exception {
        VideoCapture videoCapture = this.capture;
        if (videoCapture != null) {
            videoCapture.release();
            this.capture = null;
        }
    }

    public void trigger() throws FrameGrabber.Exception {
        Mat mat2 = new Mat();
        for (int i = 0; i < this.numBuffers + 1; i++) {
            this.capture.read(mat2);
        }
        if (!this.capture.grab()) {
            throw new FrameGrabber.Exception("grab() Error: Could not grab frame. (Has start() been called?)");
        }
    }

    public Frame grab() throws FrameGrabber.Exception {
        if (!this.capture.retrieve(this.mat)) {
            throw new FrameGrabber.Exception("retrieve() Error: Could not retrieve frame. (Has start() been called?)");
        } else if (this.triggerMode || this.capture.grab()) {
            if (this.imageMode == FrameGrabber.ImageMode.GRAY && this.mat.channels() > 1) {
                if (this.returnMatrix == null) {
                    this.returnMatrix = new Mat(this.mat.rows(), this.mat.cols(), this.mat.depth(), 1);
                }
                opencv_imgproc.cvtColor(this.mat, this.returnMatrix, 6);
            } else if (this.imageMode == FrameGrabber.ImageMode.COLOR && this.mat.channels() == 1) {
                if (this.returnMatrix == null) {
                    this.returnMatrix = new Mat(this.mat.rows(), this.mat.cols(), this.mat.depth(), 3);
                }
                opencv_imgproc.cvtColor(this.mat, this.returnMatrix, 8);
            } else {
                this.returnMatrix = this.mat;
            }
            return this.converter.convert(this.returnMatrix);
        } else {
            throw new FrameGrabber.Exception("grab() Error: Could not grab frame. (Has start() been called?)");
        }
    }
}
