package org.bytedeco.javacv;

import com.google.android.exoplayer2.C;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.flycapture.FlyCapture2.BusManager;
import org.bytedeco.flycapture.FlyCapture2.Camera;
import org.bytedeco.flycapture.FlyCapture2.CameraInfo;
import org.bytedeco.flycapture.FlyCapture2.Error;
import org.bytedeco.flycapture.FlyCapture2.FC2Config;
import org.bytedeco.flycapture.FlyCapture2.Image;
import org.bytedeco.flycapture.FlyCapture2.PGRGuid;
import org.bytedeco.flycapture.FlyCapture2.Property;
import org.bytedeco.flycapture.FlyCapture2.TimeStamp;
import org.bytedeco.flycapture.FlyCapture2.TriggerMode;
import org.bytedeco.flycapture.global.FlyCapture2;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class FlyCapture2FrameGrabber extends FrameGrabber {
    public static final int CAMERA_POWER = 1552;
    public static final int IMAGE_DATA_FORMAT = 4168;
    public static final int INITIALIZE = 0;
    public static final int IS_CAMERA_POWER = 1024;
    public static final int SOFTWARE_TRIGGER = 1580;
    public static final int SOFT_ASYNC_TRIGGER = 4140;
    public static final int TRIGGER_INQ = 1328;
    static final int VIDEOMODE_ANY = -1;
    private static FrameGrabber.Exception loadingException;
    private BusManager busMgr = new BusManager();
    private Camera camera;
    private CameraInfo cameraInfo;
    private Image conv_image = new Image();
    private FrameConverter converter = new OpenCVFrameConverter.ToIplImage();
    private final float[] gammaOut = new float[1];
    private final float[] outFloat = new float[1];
    private Image raw_image = new Image();
    private final int[] regOut = new int[1];
    private IplImage return_image = null;
    private IplImage temp_image;

    public static FlyCapture2FrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        return null;
    }

    public static FlyCapture2FrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        return null;
    }

    private int getDepth(int i) {
        if (i != 2097152) {
            return (i == 8388608 || i == 16777216) ? opencv_core.IPL_DEPTH_16S : (i == 33554432 || i == 67108864) ? 16 : 8;
        }
        return 16;
    }

    private int getNumChannels(int i) {
        if (i == Integer.MIN_VALUE) {
            return 1;
        }
        if (i == -2147483640) {
            return 3;
        }
        if (i == 2097152 || i == 4194304) {
            return 1;
        }
        if (i == 8388608) {
            return 3;
        }
        if (i == 16777216) {
            return 1;
        }
        if (i == 33554432) {
            return 3;
        }
        if (i == 67108864) {
            return 1;
        }
        if (i != 134217728) {
            return i != 1073741832 ? -1 : 4;
        }
        return 3;
    }

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        BusManager busManager = new BusManager();
        int[] iArr = new int[1];
        busManager.GetNumOfCameras(iArr);
        String[] strArr = new String[iArr[0]];
        for (int i = 0; i < iArr[0]; i++) {
            PGRGuid pGRGuid = new PGRGuid();
            Error GetCameraFromIndex = busManager.GetCameraFromIndex(i, pGRGuid);
            if (GetCameraFromIndex.notEquals(0)) {
                PrintError(GetCameraFromIndex);
                System.exit(-1);
            }
            Camera camera2 = new Camera();
            Error Connect = camera2.Connect(pGRGuid);
            if (Connect.notEquals(0)) {
                PrintError(Connect);
            }
            CameraInfo cameraInfo2 = new CameraInfo();
            Error GetCameraInfo = camera2.GetCameraInfo(cameraInfo2);
            if (GetCameraInfo.notEquals(0)) {
                PrintError(GetCameraInfo);
            }
            strArr[i] = CameraInfo(cameraInfo2);
        }
        return strArr;
    }

    static void PrintError(Error error) {
        error.PrintErrorTrace();
    }

    static String CameraInfo(CameraInfo cameraInfo2) {
        return "\n*** CAMERA INFORMATION ***\nSerial number - " + cameraInfo2.serialNumber() + "\nCamera model - " + cameraInfo2.modelName().getString() + "\nCamera vendor - " + cameraInfo2.vendorName().getString() + "\nSensor - " + cameraInfo2.sensorInfo().getString() + "\nResolution - " + cameraInfo2.sensorResolution().getString() + "\nFirmware version - " + cameraInfo2.firmwareVersion().getString() + "\nFirmware build time - " + cameraInfo2.firmwareBuildTime().getString() + "\n";
    }

    public static FlyCapture2FrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new FlyCapture2FrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(FlyCapture2.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + FlyCapture2FrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            exception.printStackTrace();
            throw loadingException;
        }
    }

    public FlyCapture2FrameGrabber(int i) throws FrameGrabber.Exception {
        this.busMgr.GetNumOfCameras(new int[1]);
        PGRGuid pGRGuid = new PGRGuid();
        Error GetCameraFromIndex = this.busMgr.GetCameraFromIndex(i, pGRGuid);
        if (GetCameraFromIndex.notEquals(0)) {
            PrintError(GetCameraFromIndex);
            System.exit(-1);
        }
        Camera camera2 = new Camera();
        this.camera = camera2;
        Error Connect = camera2.Connect(pGRGuid);
        if (Connect.notEquals(0)) {
            PrintError(Connect);
        }
        CameraInfo cameraInfo2 = new CameraInfo();
        this.cameraInfo = cameraInfo2;
        Error GetCameraInfo = this.camera.GetCameraInfo(cameraInfo2);
        if (GetCameraInfo.notEquals(0)) {
            PrintError(GetCameraInfo);
        }
    }

    public void release() throws FrameGrabber.Exception {
        if (this.camera != null) {
            stop();
            this.camera.Disconnect();
            this.camera = null;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public double getGamma() {
        if (!Float.isNaN(this.gammaOut[0]) && !Float.isInfinite(this.gammaOut[0])) {
            float[] fArr = this.gammaOut;
            if (fArr[0] != 0.0f) {
                return (double) fArr[0];
            }
        }
        return 2.2d;
    }

    public int getImageWidth() {
        IplImage iplImage = this.return_image;
        return iplImage == null ? super.getImageWidth() : iplImage.width();
    }

    public int getImageHeight() {
        IplImage iplImage = this.return_image;
        return iplImage == null ? super.getImageHeight() : iplImage.height();
    }

    public double getFrameRate() {
        Camera camera2 = this.camera;
        if (camera2 == null || camera2.isNull()) {
            return super.getFrameRate();
        }
        IntPointer intPointer = new IntPointer(1);
        IntPointer intPointer2 = new IntPointer(1);
        this.camera.GetVideoModeAndFrameRate(intPointer, intPointer2);
        return (double) intPointer2.get(0);
    }

    public void setImageMode(FrameGrabber.ImageMode imageMode) {
        if (imageMode != this.imageMode) {
            this.temp_image = null;
            this.return_image = null;
        }
        super.setImageMode(imageMode);
    }

    public void start() throws FrameGrabber.Exception {
        int i;
        if (this.frameRate > 0.0d && this.frameRate > 1.876d && this.frameRate > 3.76d && this.frameRate > 7.51d && this.frameRate > 15.01d && this.frameRate > 30.01d && this.frameRate > 60.01d && this.frameRate > 120.01d) {
            int i2 = (this.frameRate > 240.01d ? 1 : (this.frameRate == 240.01d ? 0 : -1));
        }
        if (this.imageMode == FrameGrabber.ImageMode.COLOR || this.imageMode == FrameGrabber.ImageMode.RAW) {
            if (this.imageWidth > 0 && this.imageHeight > 0 && ((this.imageWidth > 640 || this.imageHeight > 480) && ((this.imageWidth > 800 || this.imageHeight > 600) && ((this.imageWidth > 1024 || this.imageHeight > 768) && ((this.imageWidth > 1280 || this.imageHeight > 960) && this.imageWidth <= 1600))))) {
                int i3 = this.imageHeight;
            }
        } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && this.imageWidth > 0 && this.imageHeight > 0) {
            if (this.imageWidth <= 640 && this.imageHeight <= 480) {
                int i4 = this.bpp;
            } else if (this.imageWidth <= 800 && this.imageHeight <= 600) {
                int i5 = this.bpp;
            } else if (this.imageWidth <= 1024 && this.imageHeight <= 768) {
                int i6 = this.bpp;
            } else if (this.imageWidth <= 1280 && this.imageHeight <= 960) {
                int i7 = this.bpp;
            } else if (this.imageWidth <= 1600 && this.imageHeight <= 1200) {
                int i8 = this.bpp;
            }
        }
        TriggerMode triggerMode = new TriggerMode();
        Error GetTriggerMode = this.camera.GetTriggerMode(triggerMode);
        if (!GetTriggerMode.notEquals(0)) {
            triggerMode.onOff(this.triggerMode);
            triggerMode.source(7);
            triggerMode.mode(14);
            triggerMode.parameter(0);
            if (this.camera.SetTriggerMode(triggerMode).notEquals(0)) {
                triggerMode.onOff(true);
                triggerMode.source(7);
                triggerMode.mode(0);
                triggerMode.parameter(0);
                Error SetTriggerMode = this.camera.SetTriggerMode(triggerMode);
                if (SetTriggerMode.notEquals(0)) {
                    PrintError(SetTriggerMode);
                    throw new FrameGrabber.Exception("SetTriggerMode() Error " + SetTriggerMode.GetDescription());
                }
            }
            if (this.triggerMode) {
                waitForTriggerReady();
            }
            Error ReadRegister = this.camera.ReadRegister(IMAGE_DATA_FORMAT, this.regOut);
            if (!ReadRegister.notEquals(0)) {
                if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
                    i = this.regOut[0] | 1;
                } else {
                    i = this.regOut[0] & -2;
                }
                Error WriteRegister = this.camera.WriteRegister(IMAGE_DATA_FORMAT, i);
                if (!WriteRegister.notEquals(0)) {
                    Property property = new Property(6);
                    if (this.gamma != 0.0d) {
                        Error GetProperty = this.camera.GetProperty(property);
                        if (!GetProperty.notEquals(0)) {
                            property.onOff(true);
                            property.absControl(true);
                            property.absValue((float) this.gamma);
                            this.camera.SetProperty(property);
                            Error SetProperty = this.camera.SetProperty(property);
                            if (SetProperty.notEquals(0)) {
                                PrintError(SetProperty);
                                throw new FrameGrabber.Exception("SetProperty(gammaProp) Error " + SetProperty.GetDescription());
                            }
                        } else {
                            throw new FrameGrabber.Exception("GetProperty(gammaProp) Error " + GetProperty.GetDescription());
                        }
                    }
                    if (this.camera.GetProperty(property).notEquals(0)) {
                        this.gammaOut[0] = 2.2f;
                    } else {
                        this.gammaOut[0] = property.absValue();
                    }
                    Error StartCapture = this.camera.StartCapture();
                    if (!StartCapture.notEquals(0)) {
                        FC2Config fC2Config = new FC2Config();
                        Error GetConfiguration = this.camera.GetConfiguration(fC2Config);
                        if (!GetConfiguration.notEquals(0)) {
                            fC2Config.grabTimeout(this.timeout);
                            Error SetConfiguration = this.camera.SetConfiguration(fC2Config);
                            if (SetConfiguration.notEquals(0)) {
                                PrintError(SetConfiguration);
                                throw new FrameGrabber.Exception("SetConfiguration() Error " + SetConfiguration.GetDescription());
                            }
                            return;
                        }
                        PrintError(GetConfiguration);
                        throw new FrameGrabber.Exception("GetConfiguration() Error " + GetConfiguration.GetDescription());
                    }
                    PrintError(StartCapture);
                    throw new FrameGrabber.Exception("StartCapture() Error " + StartCapture.GetDescription());
                }
                PrintError(WriteRegister);
                throw new FrameGrabber.Exception("WriteRegister(IMAGE_DATA_FORMAT, reg) Error " + WriteRegister.GetDescription());
            }
            PrintError(ReadRegister);
            throw new FrameGrabber.Exception("ReadRegister(IMAGE_DATA_FORMAT, regOut) Error " + ReadRegister.GetDescription());
        }
        PrintError(GetTriggerMode);
        throw new FrameGrabber.Exception("GetTriggerMode() Error " + GetTriggerMode.GetDescription());
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void waitForTriggerReady() throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
        L_0x0004:
            org.bytedeco.flycapture.FlyCapture2.Camera r2 = r8.camera
            r3 = 1580(0x62c, float:2.214E-42)
            int[] r4 = r8.regOut
            org.bytedeco.flycapture.FlyCapture2.Error r2 = r2.ReadRegister(r3, r4)
            r3 = 0
            boolean r4 = r2.notEquals(r3)
            if (r4 != 0) goto L_0x002b
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r0
            int r2 = r8.timeout
            long r6 = (long) r2
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0022
            goto L_0x002a
        L_0x0022:
            int[] r2 = r8.regOut
            r2 = r2[r3]
            int r2 = r2 >>> 31
            if (r2 != 0) goto L_0x0004
        L_0x002a:
            return
        L_0x002b:
            PrintError(r2)
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "GetTriggerMode() Error "
            r1.append(r3)
            org.bytedeco.javacpp.BytePointer r2 = r2.GetDescription()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FlyCapture2FrameGrabber.waitForTriggerReady():void");
    }

    public void stop() throws FrameGrabber.Exception {
        Error StopCapture = this.camera.StopCapture();
        if (!StopCapture.notEquals(0)) {
            this.temp_image = null;
            this.return_image = null;
            this.timestamp = 0;
            this.frameNumber = 0;
            return;
        }
        PrintError(StopCapture);
        throw new FrameGrabber.Exception("flycapture camera StopCapture() Error " + StopCapture);
    }

    public void trigger() throws FrameGrabber.Exception {
        waitForTriggerReady();
        Error FireSoftwareTrigger = this.camera.FireSoftwareTrigger();
        if (FireSoftwareTrigger.notEquals(0)) {
            throw new FrameGrabber.Exception("flycaptureSetCameraRegister() Error " + FireSoftwareTrigger);
        }
    }

    private void setPixelFormat(Image image, int i) {
        image.SetDimensions(image.GetRows(), image.GetCols(), image.GetStride(), i, image.GetBayerTileFormat());
    }

    private void setStride(Image image, int i) {
        image.SetDimensions(image.GetRows(), image.GetCols(), i, image.GetPixelFormat(), image.GetBayerTileFormat());
    }

    public Frame grab() throws FrameGrabber.Exception {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        Error RetrieveBuffer = this.camera.RetrieveBuffer(this.raw_image);
        if (!RetrieveBuffer.notEquals(0)) {
            int GetCols = this.raw_image.GetCols();
            int GetRows = this.raw_image.GetRows();
            int GetPixelFormat = this.raw_image.GetPixelFormat();
            int depth = getDepth(GetPixelFormat);
            int GetStride = this.raw_image.GetStride();
            int i5 = GetRows * GetStride;
            int numChannels = getNumChannels(GetPixelFormat);
            Error ReadRegister = this.camera.ReadRegister(IMAGE_DATA_FORMAT, this.regOut);
            if (!ReadRegister.notEquals(0)) {
                ByteOrder byteOrder = (this.regOut[0] & 1) != 0 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
                boolean z2 = this.raw_image.GetBayerTileFormat() != 0;
                boolean z3 = GetPixelFormat == 134217728 || GetPixelFormat == 33554432 || GetPixelFormat == -2147483640 || GetPixelFormat == 1073741832;
                boolean z4 = GetPixelFormat == 1073741824 || GetPixelFormat == 536870912 || GetPixelFormat == 268435456;
                int i6 = GetRows;
                BytePointer capacity = this.raw_image.GetData().capacity((long) this.raw_image.GetDataSize());
                if ((depth == 8 || byteOrder.equals(ByteOrder.nativeOrder())) && (this.imageMode == FrameGrabber.ImageMode.RAW || ((this.imageMode == FrameGrabber.ImageMode.COLOR && numChannels == 3) || (this.imageMode == FrameGrabber.ImageMode.GRAY && numChannels == 1 && !z2)))) {
                    int i7 = i6;
                    if (this.return_image == null) {
                        this.return_image = IplImage.createHeader(GetCols, i7, depth, numChannels);
                    }
                    this.return_image.widthStep(GetStride);
                    this.return_image.imageSize(i5);
                    this.return_image.imageData(capacity);
                } else {
                    if (this.return_image == null) {
                        if (this.imageMode == FrameGrabber.ImageMode.COLOR) {
                            i = i6;
                            i4 = 3;
                        } else {
                            i = i6;
                            i4 = 1;
                        }
                        this.return_image = IplImage.create(GetCols, i, depth, i4);
                    } else {
                        i = i6;
                    }
                    if (this.temp_image == null) {
                        if (this.imageMode == FrameGrabber.ImageMode.COLOR && ((numChannels > 1 || depth > 8) && !z4 && !z2)) {
                            this.temp_image = IplImage.create(GetCols, i, depth, numChannels);
                        } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && z2) {
                            this.temp_image = IplImage.create(GetCols, i, depth, 3);
                        } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && z3) {
                            this.temp_image = IplImage.createHeader(GetCols, i, depth, 3);
                        } else if (this.imageMode != FrameGrabber.ImageMode.COLOR || numChannels != 1 || z4 || z2) {
                            this.temp_image = this.return_image;
                        } else {
                            this.temp_image = IplImage.createHeader(GetCols, i, depth, 1);
                        }
                    }
                    setStride(this.conv_image, this.temp_image.widthStep());
                    this.conv_image.SetData(this.temp_image.imageData(), this.temp_image.width() * this.temp_image.height() * this.temp_image.depth());
                    if (depth == 8) {
                        Image image = this.conv_image;
                        if (this.imageMode == FrameGrabber.ImageMode.RAW) {
                            i3 = 4194304;
                        } else {
                            i3 = this.temp_image.nChannels() == 1 ? Integer.MIN_VALUE : opencv_core.IPL_DEPTH_8S;
                        }
                        setPixelFormat(image, i3);
                    } else {
                        Image image2 = this.conv_image;
                        if (this.imageMode == FrameGrabber.ImageMode.RAW) {
                            i2 = 2097152;
                        } else {
                            i2 = this.temp_image.nChannels() == 1 ? 67108864 : opencv_core.ACCESS_WRITE;
                        }
                        setPixelFormat(image2, i2);
                    }
                    if (depth != 8 && this.conv_image.GetPixelFormat() == GetPixelFormat && this.conv_image.GetStride() == GetStride) {
                        this.temp_image.getByteBuffer().order(ByteOrder.nativeOrder()).asShortBuffer().put(capacity.asByteBuffer().order(byteOrder).asShortBuffer());
                        z = true;
                    } else {
                        if ((this.imageMode == FrameGrabber.ImageMode.GRAY && z3) || (this.imageMode == FrameGrabber.ImageMode.COLOR && numChannels == 1 && !z4 && !z2)) {
                            this.temp_image.widthStep(GetStride);
                            this.temp_image.imageSize(i5);
                            this.temp_image.imageData(capacity);
                        } else if (!z3 && (z2 || z4 || numChannels > 1)) {
                            Error Convert = this.raw_image.Convert(this.conv_image);
                            z = false;
                            if (Convert.notEquals(0)) {
                                PrintError(Convert);
                                throw new FrameGrabber.Exception("raw_image.Convert Error " + Convert);
                            }
                        }
                        z = false;
                    }
                    if (!z && depth != 8 && !byteOrder.equals(ByteOrder.nativeOrder())) {
                        ByteBuffer byteBuffer = this.temp_image.getByteBuffer();
                        byteBuffer.order(ByteOrder.nativeOrder()).asShortBuffer().put(byteBuffer.order(byteOrder).asShortBuffer());
                    }
                    if (this.imageMode == FrameGrabber.ImageMode.COLOR && numChannels == 1 && !z4 && !z2) {
                        opencv_imgproc.cvCvtColor(this.temp_image, this.return_image, 8);
                    } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && (z2 || z3)) {
                        opencv_imgproc.cvCvtColor(this.temp_image, this.return_image, 6);
                    }
                }
                int bayerTileFormat = this.cameraInfo.bayerTileFormat();
                if (bayerTileFormat == 1) {
                    this.sensorPattern = 0;
                } else if (bayerTileFormat == 2) {
                    this.sensorPattern = 1;
                } else if (bayerTileFormat == 3) {
                    this.sensorPattern = 4294967296L;
                } else if (bayerTileFormat != 4) {
                    this.sensorPattern = -1;
                } else {
                    this.sensorPattern = FrameGrabber.SENSOR_PATTERN_BGGR;
                }
                TimeStamp GetTimeStamp = this.raw_image.GetTimeStamp();
                this.timestamp = (GetTimeStamp.seconds() * C.MICROS_PER_SECOND) + ((long) GetTimeStamp.microSeconds());
                return this.converter.convert(this.return_image);
            }
            throw new FrameGrabber.Exception("flycaptureGetCameraRegister() Error " + ReadRegister);
        }
        throw new FrameGrabber.Exception("flycaptureGrabImage2() Error " + RetrieveBuffer + " (Has start() been called?)");
    }
}
