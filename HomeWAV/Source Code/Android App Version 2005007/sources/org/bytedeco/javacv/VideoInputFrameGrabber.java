package org.bytedeco.javacv;

import java.io.File;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.videoinput.global.videoInputLib;
import org.bytedeco.videoinput.videoInput;

public class VideoInputFrameGrabber extends FrameGrabber {
    private static FrameGrabber.Exception loadingException;
    private IplImage bgrImage = null;
    private BytePointer bgrImageData = null;
    private FrameConverter converter = new OpenCVFrameConverter.ToIplImage();
    private int deviceNumber = 0;
    private IplImage grayImage = null;
    private videoInput myVideoInput = null;

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        int listDevices = videoInput.listDevices();
        String[] strArr = new String[listDevices];
        for (int i = 0; i < listDevices; i++) {
            strArr[i] = videoInput.getDeviceName(i).getString();
        }
        return strArr;
    }

    public static VideoInputFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(VideoInputFrameGrabber.class + " does not support device files.");
    }

    public static VideoInputFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(VideoInputFrameGrabber.class + " does not support device paths.");
    }

    public static VideoInputFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new VideoInputFrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(videoInputLib.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + VideoInputFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public VideoInputFrameGrabber(int i) {
        this.deviceNumber = i;
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

    public int getImageWidth() {
        videoInput videoinput = this.myVideoInput;
        return videoinput == null ? super.getImageWidth() : videoinput.getWidth(this.deviceNumber);
    }

    public int getImageHeight() {
        videoInput videoinput = this.myVideoInput;
        return videoinput == null ? super.getImageHeight() : videoinput.getHeight(this.deviceNumber);
    }

    public void start() throws FrameGrabber.Exception {
        start(-1);
    }

    public void start(int i) throws FrameGrabber.Exception {
        int i2;
        this.myVideoInput = new videoInput();
        if (this.frameRate > 0.0d) {
            this.myVideoInput.setIdealFramerate(this.deviceNumber, (int) this.frameRate);
        }
        if (!this.myVideoInput.setupDevice(this.deviceNumber, this.imageWidth > 0 ? this.imageWidth : 640, this.imageHeight > 0 ? this.imageHeight : 480, i)) {
            this.myVideoInput = null;
            throw new FrameGrabber.Exception("videoInput.setupDevice() Error: Could not setup device.");
        } else if (this.format != null && this.format.length() > 0) {
            if (this.format.equals("VI_NTSC_M")) {
                i2 = 0;
            } else if (this.format.equals("VI_PAL_B")) {
                i2 = 1;
            } else if (this.format.equals("VI_PAL_D")) {
                i2 = 2;
            } else if (this.format.equals("VI_PAL_G")) {
                i2 = 3;
            } else if (this.format.equals("VI_PAL_H")) {
                i2 = 4;
            } else if (this.format.equals("VI_PAL_I")) {
                i2 = 5;
            } else if (this.format.equals("VI_PAL_M")) {
                i2 = 6;
            } else if (this.format.equals("VI_PAL_N")) {
                i2 = 7;
            } else if (this.format.equals("VI_PAL_NC")) {
                i2 = 8;
            } else if (this.format.equals("VI_SECAM_B")) {
                i2 = 9;
            } else if (this.format.equals("VI_SECAM_D")) {
                i2 = 10;
            } else if (this.format.equals("VI_SECAM_G")) {
                i2 = 11;
            } else if (this.format.equals("VI_SECAM_H")) {
                i2 = 12;
            } else if (this.format.equals("VI_SECAM_K")) {
                i2 = 13;
            } else if (this.format.equals("VI_SECAM_K1")) {
                i2 = 14;
            } else if (this.format.equals("VI_SECAM_L")) {
                i2 = 15;
            } else if (this.format.equals("VI_NTSC_M_J")) {
                i2 = 16;
            } else {
                i2 = this.format.equals("VI_NTSC_433") ? 17 : -1;
            }
            if (i2 >= 0 && !this.myVideoInput.setFormat(this.deviceNumber, i2)) {
                throw new FrameGrabber.Exception("videoInput.setFormat() Error: Could not set format " + this.format + ".");
            }
        }
    }

    public void stop() throws FrameGrabber.Exception {
        videoInput videoinput = this.myVideoInput;
        if (videoinput != null) {
            videoinput.stopDevice(this.deviceNumber);
            this.myVideoInput = null;
        }
    }

    public void trigger() throws FrameGrabber.Exception {
        videoInput videoinput = this.myVideoInput;
        if (videoinput != null) {
            int width = videoinput.getWidth(this.deviceNumber);
            int height = this.myVideoInput.getHeight(this.deviceNumber);
            IplImage iplImage = this.bgrImage;
            if (!(iplImage != null && iplImage.width() == width && this.bgrImage.height() == height)) {
                IplImage create = IplImage.create(width, height, 8, 3);
                this.bgrImage = create;
                this.bgrImageData = create.imageData();
            }
            for (int i = 0; i < this.numBuffers + 1; i++) {
                this.myVideoInput.getPixels(this.deviceNumber, this.bgrImageData, false, true);
            }
            return;
        }
        throw new FrameGrabber.Exception("videoInput is null. (Has start() been called?)");
    }

    public Frame grab() throws FrameGrabber.Exception {
        videoInput videoinput = this.myVideoInput;
        if (videoinput != null) {
            int width = videoinput.getWidth(this.deviceNumber);
            int height = this.myVideoInput.getHeight(this.deviceNumber);
            IplImage iplImage = this.bgrImage;
            if (!(iplImage != null && iplImage.width() == width && this.bgrImage.height() == height)) {
                IplImage create = IplImage.create(width, height, 8, 3);
                this.bgrImage = create;
                this.bgrImageData = create.imageData();
            }
            if (this.myVideoInput.getPixels(this.deviceNumber, this.bgrImageData, false, true)) {
                this.timestamp = System.nanoTime() / 1000;
                if (this.imageMode != FrameGrabber.ImageMode.GRAY) {
                    return this.converter.convert(this.bgrImage);
                }
                if (this.grayImage == null) {
                    this.grayImage = IplImage.create(width, height, 8, 1);
                }
                opencv_imgproc.cvCvtColor(this.bgrImage, this.grayImage, 6);
                return this.converter.convert(this.grayImage);
            }
            throw new FrameGrabber.Exception("videoInput.getPixels() Error: Could not get pixels.");
        }
        throw new FrameGrabber.Exception("videoInput is null. (Has start() been called?)");
    }
}
