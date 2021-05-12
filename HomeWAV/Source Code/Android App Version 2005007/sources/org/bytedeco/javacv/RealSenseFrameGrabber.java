package org.bytedeco.javacv;

import java.io.File;
import java.io.PrintStream;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.librealsense.context;
import org.bytedeco.librealsense.device;
import org.bytedeco.librealsense.global.RealSense;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class RealSenseFrameGrabber extends FrameGrabber {
    public static int DEFAULT_COLOR_FRAMERATE = 30;
    public static int DEFAULT_COLOR_HEIGHT = 720;
    public static int DEFAULT_COLOR_WIDTH = 1280;
    public static int DEFAULT_DEPTH_HEIGHT = 480;
    public static int DEFAULT_DEPTH_WIDTH = 640;
    private static context context;
    private static device globalDevice;
    private static FrameGrabber.Exception loadingException;
    private boolean IREnabled;
    private int IRFrameRate;
    private int IRImageHeight;
    private int IRImageWidth;
    private boolean behaveAsColorFrameGrabber;
    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    private boolean colorEnabled;
    private FrameConverter converter;
    private boolean depth;
    private boolean depthEnabled;
    private int depthFrameRate;
    private int depthImageHeight;
    private int depthImageWidth;
    private device device;
    private int deviceNumber;
    private IplImage rawDepthImage;
    private Pointer rawDepthImageData;
    private IplImage rawIRImage;
    private Pointer rawIRImageData;
    private IplImage rawVideoImage;
    private Pointer rawVideoImageData;
    private IplImage returnImage;
    private boolean startedOnce;

    public void release() throws FrameGrabber.Exception {
    }

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        int i = context.get_device_count();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = context.get_device(i2).get_name().getString();
        }
        return strArr;
    }

    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    public void setByteOrder(ByteOrder byteOrder2) {
        this.byteOrder = byteOrder2;
    }

    public static RealSenseFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new RealSenseFrameGrabber(i);
    }

    public static RealSenseFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(RealSenseFrameGrabber.class + " does not support File devices.");
    }

    public static RealSenseFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(RealSenseFrameGrabber.class + " does not support path.");
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                if (context == null) {
                    Loader.load(RealSense.class);
                    context = new context();
                    PrintStream printStream = System.out;
                    printStream.println("RealSense devices found: " + context.get_device_count());
                }
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + RealSenseFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            exception.printStackTrace();
            throw loadingException;
        }
    }

    public RealSenseFrameGrabber(int i) {
        int i2 = DEFAULT_DEPTH_WIDTH;
        this.depthImageWidth = i2;
        int i3 = DEFAULT_DEPTH_HEIGHT;
        this.depthImageHeight = i3;
        this.depthFrameRate = 30;
        this.IRImageWidth = i2;
        this.IRImageHeight = i3;
        this.IRFrameRate = 30;
        this.deviceNumber = 0;
        this.device = null;
        this.depth = false;
        this.colorEnabled = false;
        this.depthEnabled = false;
        this.IREnabled = false;
        this.converter = new OpenCVFrameConverter.ToIplImage();
        this.startedOnce = false;
        this.behaveAsColorFrameGrabber = false;
        Pointer pointer = null;
        this.rawDepthImageData = new Pointer(pointer);
        this.rawVideoImageData = new Pointer(pointer);
        this.rawIRImageData = new Pointer(pointer);
        this.rawDepthImage = null;
        this.rawVideoImage = null;
        this.rawIRImage = null;
        this.returnImage = null;
        this.deviceNumber = i;
    }

    public static void main(String[] strArr) {
        context context2 = new context();
        PrintStream printStream = System.out;
        printStream.println("Devices found: " + context2.get_device_count());
        device device2 = context2.get_device(0);
        PrintStream printStream2 = System.out;
        printStream2.println("Using device 0, an " + device2.get_name());
        PrintStream printStream3 = System.out;
        printStream3.println(" Serial number: " + device2.get_serial());
    }

    public void enableColorStream() {
        if (!this.colorEnabled) {
            if (this.imageWidth == 0) {
                this.imageWidth = DEFAULT_COLOR_WIDTH;
            }
            if (this.imageHeight == 0) {
                this.imageHeight = DEFAULT_COLOR_HEIGHT;
            }
            if (this.frameRate == 0.0d) {
                this.frameRate = (double) DEFAULT_COLOR_FRAMERATE;
            }
            this.colorEnabled = true;
        }
    }

    public void disableColorStream() {
        if (this.colorEnabled) {
            this.device.disable_stream(1);
            this.colorEnabled = false;
        }
    }

    public void enableDepthStream() {
        if (!this.depthEnabled) {
            this.depthEnabled = true;
        }
    }

    public void disableDepthStream() {
        if (this.depthEnabled) {
            this.device.disable_stream(0);
            this.depthEnabled = false;
        }
    }

    public void enableIRStream() {
        if (!this.IREnabled) {
            this.IREnabled = true;
        }
    }

    public void disableIRStream() {
        if (this.IREnabled) {
            this.device.disable_stream(2);
            this.IREnabled = false;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public device getRealSenseDevice() {
        return this.device;
    }

    public float getDepthScale() {
        return this.device.get_depth_scale();
    }

    public double getFrameRate() {
        return super.getFrameRate();
    }

    public device loadDevice() throws FrameGrabber.Exception {
        int i;
        if (context == null) {
            context = new context();
        }
        context context2 = context;
        if (context2 == null || context2.get_device_count() <= (i = this.deviceNumber)) {
            throw new FrameGrabber.Exception("FATAL error: Realsense camera: " + this.deviceNumber + " not connected/found");
        }
        device device2 = context.get_device(i);
        this.device = device2;
        return device2;
    }

    public void start() throws FrameGrabber.Exception {
        int i;
        device device2 = globalDevice;
        if (device2 != null) {
            device2.close();
            context.close();
            globalDevice = null;
            context = null;
        }
        if (context == null) {
            context = new context();
        }
        context context2 = context;
        if (context2 == null || context2.get_device_count() <= (i = this.deviceNumber)) {
            throw new FrameGrabber.Exception("FATAL error: Realsense camera: " + this.deviceNumber + " not connected/found");
        }
        if (this.device == null) {
            this.device = context.get_device(i);
        }
        globalDevice = this.device;
        if (this.format != null) {
            String str = this.format;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case 3369:
                    if (str.equals("ir")) {
                        c = 0;
                        break;
                    }
                    break;
                case 112845:
                    if (str.equals("rgb")) {
                        c = 1;
                        break;
                    }
                    break;
                case 95472323:
                    if (str.equals("depth")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    enableIRStream();
                    break;
                case 1:
                    enableColorStream();
                    break;
                case 2:
                    enableDepthStream();
                    break;
            }
        }
        if (this.colorEnabled) {
            this.device.enable_stream(1, this.imageWidth, this.imageHeight, 5, (int) this.frameRate);
        }
        if (this.IREnabled) {
            this.device.enable_stream(2, this.IRImageWidth, this.IRImageHeight, 9, this.IRFrameRate);
        }
        if (this.depthEnabled) {
            this.device.enable_stream(0, this.depthImageWidth, this.depthImageHeight, 1, this.depthFrameRate);
        }
        if (!this.colorEnabled && !this.IREnabled && !this.depthEnabled) {
            enableColorStream();
            this.device.enable_stream(1, this.imageWidth, this.imageHeight, 5, (int) this.frameRate);
            this.behaveAsColorFrameGrabber = true;
        }
        this.device.start();
    }

    public void stop() throws FrameGrabber.Exception {
        this.device.stop();
        this.frameNumber = 0;
    }

    public IplImage grabDepth() {
        if (!this.depthEnabled) {
            System.out.println("Depth stream not enabled, impossible to get the image.");
            return null;
        }
        this.rawDepthImageData = this.device.get_frame_data(0);
        int i = this.device.get_stream_width(0);
        int i2 = this.device.get_stream_height(0);
        IplImage iplImage = this.rawDepthImage;
        if (!(iplImage != null && iplImage.width() == i && this.rawDepthImage.height() == i2)) {
            this.rawDepthImage = IplImage.createHeader(i, i2, 16, 1);
        }
        opencv_core.cvSetData(this.rawDepthImage, this.rawDepthImageData, ((i * 1) * 16) / 8);
        return this.rawDepthImage;
    }

    public IplImage grabVideo() {
        if (!this.colorEnabled) {
            System.out.println("Color stream not enabled, impossible to get the image.");
            return null;
        }
        this.rawVideoImageData = this.device.get_frame_data(1);
        int i = this.device.get_stream_width(1);
        int i2 = this.device.get_stream_height(1);
        IplImage iplImage = this.rawVideoImage;
        if (!(iplImage != null && iplImage.width() == i && this.rawVideoImage.height() == i2)) {
            this.rawVideoImage = IplImage.createHeader(i, i2, 8, 3);
        }
        opencv_core.cvSetData(this.rawVideoImage, this.rawVideoImageData, ((i * 3) * 8) / 8);
        return this.rawVideoImage;
    }

    public IplImage grabIR() {
        if (!this.IREnabled) {
            System.out.println("IR stream not enabled, impossible to get the image.");
            return null;
        }
        this.rawIRImageData = this.device.get_frame_data(2);
        int i = this.device.get_stream_width(2);
        int i2 = this.device.get_stream_height(2);
        IplImage iplImage = this.rawIRImage;
        if (!(iplImage != null && iplImage.width() == i && this.rawIRImage.height() == i2)) {
            this.rawIRImage = IplImage.createHeader(i, i2, 8, 1);
        }
        opencv_core.cvSetData(this.rawIRImage, this.rawIRImageData, ((i * 1) * 8) / 8);
        return this.rawIRImage;
    }

    public Frame grab() throws FrameGrabber.Exception {
        this.device.wait_for_frames();
        if (this.colorEnabled && this.behaveAsColorFrameGrabber) {
            IplImage grabVideo = grabVideo();
            if (this.returnImage == null) {
                this.returnImage = IplImage.create(this.device.get_stream_width(1), this.device.get_stream_height(1), 8, 1);
            }
            opencv_imgproc.cvCvtColor(grabVideo, this.returnImage, 6);
            return this.converter.convert(this.returnImage);
        } else if (this.IREnabled) {
            return this.converter.convert(grabIR());
        } else {
            if (!this.depthEnabled) {
                return null;
            }
            grabDepth();
            if (this.returnImage == null) {
                this.returnImage = IplImage.create(this.device.get_stream_width(0), this.device.get_stream_height(0), 8, 1);
            }
            return this.converter.convert(this.returnImage);
        }
    }

    public void trigger() throws FrameGrabber.Exception {
        this.device.wait_for_frames();
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

    public int getIRFrameRate() {
        return this.IRFrameRate;
    }

    public void setIRFrameRate(int i) {
        this.IRFrameRate = i;
    }

    public double getGamma() {
        if (this.gamma == 0.0d) {
            return 2.2d;
        }
        return this.gamma;
    }

    public void setPreset(int i) {
        RealSense.apply_ivcam_preset(this.device, i);
    }

    public void setShortRange() {
        setPreset(0);
    }

    public void setLongRange() {
        setPreset(1);
    }

    public void setMidRange() {
        setPreset(9);
    }

    public void setDefaultPreset() {
        setPreset(8);
    }

    public void setObjectScanningPreset() {
        setPreset(4);
    }

    public void setCursorPreset() {
        setPreset(7);
    }

    public void setGestureRecognitionPreset() {
        setPreset(3);
    }

    public void setBackgroundSegmentationPreset() {
        setPreset(2);
    }

    public void setIROnlyPreset() {
        setPreset(10);
    }

    public void setOption(int i, int i2) {
        this.device.set_option(i, (double) i2);
    }

    public void set(int i) {
        setOption(0, i);
    }

    public void setColorBrightness(int i) {
        setOption(1, i);
    }

    public void setColorContrast(int i) {
        setOption(2, i);
    }

    public void setColorExposure(int i) {
        setOption(3, i);
    }

    public void setColorGain(int i) {
        setOption(4, i);
    }

    public void setColorGamma(int i) {
        setOption(5, i);
    }

    public void setColorHue(int i) {
        setOption(6, i);
    }

    public void setColorSaturation(int i) {
        setOption(7, i);
    }

    public void setColorSharpness(int i) {
        setOption(8, i);
    }

    public void setColorWhiteBalance(int i) {
        setOption(9, i);
    }

    public void setColorEnableAutoExposure(int i) {
        setOption(10, i);
    }

    public void setColorEnableAutoWhiteBalance(int i) {
        setOption(11, i);
    }

    public void setLaserPower(int i) {
        setOption(12, i);
    }

    public void setAccuracy(int i) {
        setOption(13, i);
    }

    public void setMotionRange(int i) {
        setOption(14, i);
    }

    public void setFilterOption(int i) {
        setOption(15, i);
    }

    public void setConfidenceThreshold(int i) {
        setOption(16, i);
    }

    public void setDynamicFPS(int i) {
        setOption(17, i);
    }

    public void setLR_AutoExposureEnabled(int i) {
        setOption(28, i);
    }

    public void setLR_Gain(int i) {
        setOption(29, i);
    }

    public void setLR_Exposure(int i) {
        setOption(30, i);
    }

    public void setEmitterEnabled(int i) {
        setOption(31, i);
    }

    public void setDepthUnits(int i) {
        setOption(32, i);
    }

    public void setDepthClampMin(int i) {
        setOption(33, i);
    }

    public void setDepthClampMax(int i) {
        setOption(34, i);
    }

    public void setDisparityMultiplier(int i) {
        setOption(35, i);
    }

    public void setDisparityShift(int i) {
        setOption(36, i);
    }

    public void setAutoExposureMeanIntensitySetPoint(int i) {
        setOption(37, i);
    }

    public void setAutoExposureBrightRatioSetPoint(int i) {
        setOption(38, i);
    }

    public void setAutoExposureKpGain(int i) {
        setOption(39, i);
    }

    public void setAutoExposureKpExposure(int i) {
        setOption(40, i);
    }

    public void setAutoExposureKpDarkThreshold(int i) {
        setOption(41, i);
    }

    public void setAutoExposureTopEdge(int i) {
        setOption(42, i);
    }

    public void setAutoExposureBottomEdge(int i) {
        setOption(43, i);
    }

    public void setAutoExposureLeftEdge(int i) {
        setOption(44, i);
    }

    public void setAutoExposureRightEdge(int i) {
        setOption(45, i);
    }

    public void setDepthControlEstimateMedianDecrement(int i) {
        setOption(46, i);
    }

    public void setDepthControlEstimateMedianIncrement(int i) {
        setOption(47, i);
    }

    public void setDepthControlMedianThreshold(int i) {
        setOption(48, i);
    }

    public void setDepthControlMinimumThreshold(int i) {
        setOption(49, i);
    }

    public void setDepthControlScoreMaximumThreshold(int i) {
        setOption(50, i);
    }

    public void setDepthControlTextureCountThreshold(int i) {
        setOption(51, i);
    }

    public void setDepthControlTextureDifference(int i) {
        setOption(52, i);
    }

    public void setDepthControlSecondPeakThreshold(int i) {
        setOption(53, i);
    }

    public void setDepthControlNeighborThreshold(int i) {
        setOption(54, i);
    }

    public void setDepthControlLRThreshold(int i) {
        setOption(55, i);
    }

    public void setFisheyeExposure(int i) {
        setOption(56, i);
    }

    public void setFisheyeGain(int i) {
        setOption(57, i);
    }

    public void setFisheyeStobe(int i) {
        setOption(58, i);
    }

    public void setFisheyeExternalTrigger(int i) {
        setOption(59, i);
    }

    public void setFisheyeEnableAutoExposure(int i) {
        setOption(60, i);
    }

    public void setFisheyeAutoExposureMode(int i) {
        setOption(61, i);
    }

    public void setFisheyeAutoExposureAntiflickerRate(int i) {
        setOption(62, i);
    }

    public void setFisheyeAutoExposurePixelSampleRate(int i) {
        setOption(63, i);
    }

    public void setFisheyeAutoExposureSkipFrames(int i) {
        setOption(64, i);
    }

    public void setFramesQueueSize(int i) {
        setOption(65, i);
    }

    public void setHardwareLoggerEnabled(int i) {
        setOption(66, i);
    }
}
