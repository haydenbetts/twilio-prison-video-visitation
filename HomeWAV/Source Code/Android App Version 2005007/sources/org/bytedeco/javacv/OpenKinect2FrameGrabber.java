package org.bytedeco.javacv;

import com.twilio.video.VideoDimensions;
import java.io.File;
import java.io.PrintStream;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.libfreenect2.CpuPacketPipeline;
import org.bytedeco.libfreenect2.Frame;
import org.bytedeco.libfreenect2.FrameMap;
import org.bytedeco.libfreenect2.Freenect2;
import org.bytedeco.libfreenect2.Freenect2Device;
import org.bytedeco.libfreenect2.SyncMultiFrameListener;
import org.bytedeco.libfreenect2.global.freenect2;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class OpenKinect2FrameGrabber extends FrameGrabber {
    public static int DEFAULT_COLOR_HEIGHT = 480;
    public static int DEFAULT_COLOR_WIDTH = 640;
    public static int DEFAULT_DEPTH_HEIGHT = 480;
    public static int DEFAULT_DEPTH_WIDTH = 640;
    private static Freenect2 freenect2Context;
    private static FrameGrabber.Exception loadingException;
    private boolean IREnabled;
    private int IRFrameRate;
    private int IRImageHeight;
    private int IRImageWidth;
    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    private boolean colorEnabled;
    private boolean depthEnabled;
    private int depthFrameRate;
    private int depthImageHeight;
    private int depthImageWidth;
    private Freenect2Device device;
    private int deviceNumber;
    private SyncMultiFrameListener frameListener;
    private int frameTypes;
    private FrameMap frames;
    private boolean hasFirstGoodColorImage;
    private IplImage rawDepthImage;
    private IplImage rawIRImage;
    private IplImage rawVideoImage;
    private String serial;
    private BytePointer videoBuffer;
    private IplImage videoImageRGBA;

    public void release() throws FrameGrabber.Exception {
    }

    public void trigger() throws FrameGrabber.Exception {
    }

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        int enumerateDevices = freenect2Context.enumerateDevices();
        String[] strArr = new String[enumerateDevices];
        for (int i = 0; i < enumerateDevices; i++) {
            strArr[i] = freenect2Context.getDeviceSerialNumber(i).getString();
        }
        return strArr;
    }

    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    public void setByteOrder(ByteOrder byteOrder2) {
        this.byteOrder = byteOrder2;
    }

    public static OpenKinect2FrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new OpenKinect2FrameGrabber(i);
    }

    public static OpenKinect2FrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(OpenKinect2FrameGrabber.class + " does not support File devices.");
    }

    public static OpenKinect2FrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(OpenKinect2FrameGrabber.class + " does not support path.");
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                if (freenect2Context == null) {
                    Loader.load(freenect2.class);
                    freenect2Context = new Freenect2();
                }
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + OpenKinect2FrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            exception.printStackTrace();
            throw loadingException;
        }
    }

    public OpenKinect2FrameGrabber(int i) {
        int i2 = DEFAULT_DEPTH_WIDTH;
        this.depthImageWidth = i2;
        int i3 = DEFAULT_DEPTH_HEIGHT;
        this.depthImageHeight = i3;
        this.depthFrameRate = 60;
        this.IRImageWidth = i2;
        this.IRImageHeight = i3;
        this.IRFrameRate = 60;
        this.colorEnabled = false;
        this.depthEnabled = false;
        this.IREnabled = false;
        this.deviceNumber = 0;
        this.serial = null;
        this.device = null;
        this.frameTypes = 0;
        this.rawVideoImage = null;
        this.videoImageRGBA = null;
        this.hasFirstGoodColorImage = false;
        this.videoBuffer = null;
        this.rawIRImage = null;
        this.rawDepthImage = null;
        this.frames = new FrameMap();
        this.deviceNumber = i;
    }

    public void enableColorStream() {
        if (!this.colorEnabled) {
            this.frameTypes |= 1;
            this.colorEnabled = true;
        }
    }

    public void enableDepthStream() {
        if (!this.depthEnabled) {
            this.frameTypes |= 4;
            this.depthEnabled = true;
        }
    }

    public void enableIRStream() {
        if (!this.IREnabled) {
            this.frameTypes |= 2;
            this.IREnabled = true;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public void start() throws FrameGrabber.Exception {
        if (freenect2Context == null) {
            try {
                tryLoad();
            } catch (FrameGrabber.Exception e) {
                PrintStream printStream = System.out;
                printStream.println("Exception in the TryLoad !" + e);
                e.printStackTrace();
            }
        }
        Freenect2 freenect2 = freenect2Context;
        if (freenect2 == null) {
            throw new FrameGrabber.Exception("FATAL error: OpenKinect2 camera: driver could not load.");
        } else if (freenect2.enumerateDevices() != 0) {
            this.device = null;
            CpuPacketPipeline cpuPacketPipeline = new CpuPacketPipeline();
            String string = freenect2Context.getDeviceSerialNumber(this.deviceNumber).getString();
            this.serial = string;
            this.device = freenect2Context.openDevice(string, cpuPacketPipeline);
            SyncMultiFrameListener syncMultiFrameListener = new SyncMultiFrameListener(this.frameTypes);
            this.frameListener = syncMultiFrameListener;
            if (this.colorEnabled) {
                this.device.setColorFrameListener(syncMultiFrameListener);
            }
            if (this.depthEnabled || this.IREnabled) {
                this.device.setIrAndDepthFrameListener(this.frameListener);
            }
            this.rawVideoImage = IplImage.createHeader((int) VideoDimensions.HD_1080P_VIDEO_WIDTH, 1080, 8, 4);
            this.device.start();
            System.out.println("OpenKinect2 device started.");
            PrintStream printStream2 = System.out;
            printStream2.println("Serial: " + this.device.getSerialNumber().getString());
            PrintStream printStream3 = System.out;
            printStream3.println("Firmware: " + this.device.getFirmwareVersion().getString());
        } else {
            throw new FrameGrabber.Exception("FATAL error: OpenKinect2: no device connected!");
        }
    }

    public void stop() throws FrameGrabber.Exception {
        this.device.stop();
        this.frameNumber = 0;
    }

    /* access modifiers changed from: protected */
    public void grabVideo() {
        Frame frame = this.frames.get(1);
        int bytes_per_pixel = (int) frame.bytes_per_pixel();
        int width = (int) frame.width();
        int height = (int) frame.height();
        BytePointer data = frame.data();
        if (this.rawVideoImage == null) {
            this.rawVideoImage = IplImage.createHeader(width, height, 8, bytes_per_pixel);
        }
        opencv_core.cvSetData(this.rawVideoImage, data, ((width * bytes_per_pixel) * 8) / 8);
        if (this.videoImageRGBA == null) {
            this.videoImageRGBA = this.rawVideoImage.clone();
        }
        opencv_imgproc.cvCvtColor(this.rawVideoImage, this.videoImageRGBA, 5);
    }

    /* access modifiers changed from: protected */
    public void grabIR() {
        Frame frame = this.frames.get(2);
        frame.bytes_per_pixel();
        int width = (int) frame.width();
        int height = (int) frame.height();
        BytePointer data = frame.data();
        if (this.rawIRImage == null) {
            this.rawIRImage = IplImage.createHeader(width, height, 32, 1);
        }
        opencv_core.cvSetData(this.rawIRImage, data, ((width * 1) * 32) / 8);
    }

    /* access modifiers changed from: protected */
    public void grabDepth() {
        Frame frame = this.frames.get(4);
        frame.bytes_per_pixel();
        int width = (int) frame.width();
        int height = (int) frame.height();
        BytePointer data = frame.data();
        if (this.rawDepthImage == null) {
            this.rawDepthImage = IplImage.createHeader(width, height, 32, 1);
        }
        opencv_core.cvSetData(this.rawDepthImage, data, ((width * 1) * 32) / 8);
    }

    public Frame grab() throws FrameGrabber.Exception {
        if (!this.frameListener.waitForNewFrame(this.frames, 10000)) {
            System.out.println("Openkinect2: timeout!");
        }
        this.frameNumber++;
        if (this.colorEnabled) {
            grabVideo();
        }
        if (this.IREnabled) {
            grabIR();
        }
        if (this.depthEnabled) {
            grabDepth();
        }
        this.frameListener.release(this.frames);
        return null;
    }

    public IplImage getVideoImage() {
        return this.videoImageRGBA;
    }

    public IplImage getIRImage() {
        return this.rawIRImage;
    }

    public IplImage getDepthImage() {
        return this.rawDepthImage;
    }

    public int getDepthImageWidth() {
        return this.depthImageWidth;
    }

    public void setDepthImageWidth(int i) {
        this.depthImageWidth = i;
    }

    public int getDepthImageHeight() {
        return this.depthImageHeight;
    }

    public void setDepthImageHeight(int i) {
        this.depthImageHeight = i;
    }

    public int getIRImageWidth() {
        return this.IRImageWidth;
    }

    public void setIRImageWidth(int i) {
        this.IRImageWidth = i;
    }

    public int getIRImageHeight() {
        return this.IRImageHeight;
    }

    public void setIRImageHeight(int i) {
        this.IRImageHeight = i;
    }

    public int getDepthFrameRate() {
        return this.depthFrameRate;
    }

    public void setDepthFrameRate(int i) {
        this.depthFrameRate = i;
    }
}
