package org.bytedeco.javacv;

import cl.eye.CLCamera;
import java.io.File;
import java.io.PrintStream;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class PS3EyeFrameGrabber extends FrameGrabber {
    private static FrameGrabber.Exception loadingException;
    CLCamera camera;
    int cameraIndex;
    FrameConverter converter;
    IplImage image_1ch;
    IplImage image_4ch;
    byte[] ipl_frame;
    int[] ps3_frame;
    String stat;
    protected Triggered triggered;
    String uuid;

    protected enum Triggered {
        NO_TRIGGER,
        HAS_FRAME,
        NO_FRAME
    }

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        int cameraCount = CLCamera.cameraCount();
        String[] strArr = new String[cameraCount];
        for (int i = 0; i < cameraCount; i++) {
            strArr[i] = CLCamera.cameraUUID(i);
        }
        return strArr;
    }

    public static PS3EyeFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(PS3EyeFrameGrabber.class + " does not support device files.");
    }

    public static PS3EyeFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(PS3EyeFrameGrabber.class + " does not support device paths.");
    }

    public static PS3EyeFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new PS3EyeFrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                CLCamera.IsLibraryLoaded();
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + PS3EyeFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public PS3EyeFrameGrabber() throws FrameGrabber.Exception {
        this(0);
    }

    public PS3EyeFrameGrabber(int i) throws FrameGrabber.Exception {
        this(i, 640, 480, 60);
    }

    public PS3EyeFrameGrabber(int i, int i2, int i3, int i4) throws FrameGrabber.Exception {
        this(i, 640, 480, 60, (Object) null);
    }

    public PS3EyeFrameGrabber(int i, int i2, int i3, int i4, Object obj) throws FrameGrabber.Exception {
        this.cameraIndex = 0;
        this.ps3_frame = null;
        this.ipl_frame = null;
        this.image_4ch = null;
        this.image_1ch = null;
        this.converter = new OpenCVFrameConverter.ToIplImage();
        this.triggered = Triggered.NO_TRIGGER;
        this.camera = null;
        if (CLCamera.IsLibraryLoaded()) {
            this.camera = new CLCamera();
            this.cameraIndex = i;
            this.stat = "created";
            this.uuid = CLCamera.cameraUUID(i);
            if ((i2 == 640 && i3 == 480) || (i2 == 320 && i3 == 240)) {
                setImageWidth(i2);
                setImageHeight(i3);
                setImageMode(FrameGrabber.ImageMode.COLOR);
                setFrameRate((double) i4);
                setTimeout((1000 / i4) + 1);
                setBitsPerPixel(8);
                setTriggerMode(false);
                setNumBuffers(4);
                return;
            }
            throw new FrameGrabber.Exception("Only 640x480 or 320x240 images supported");
        }
        throw new FrameGrabber.Exception("CLEye multicam dll not loaded");
    }

    public static int getCameraCount() {
        return CLCamera.cameraCount();
    }

    public static String[] listPS3Cameras() {
        int cameraCount = getCameraCount();
        if (cameraCount <= 0) {
            return null;
        }
        String[] strArr = new String[cameraCount];
        while (true) {
            cameraCount--;
            if (cameraCount < 0) {
                return strArr;
            }
            strArr[cameraCount] = CLCamera.cameraUUID(cameraCount);
        }
    }

    public IplImage makeImage(int[] iArr) {
        this.image_4ch.getIntBuffer().put(this.ps3_frame);
        return this.image_4ch;
    }

    public int[] grab_raw() {
        if (this.camera.getCameraFrame(this.ps3_frame, this.timeout)) {
            return this.ps3_frame;
        }
        return null;
    }

    public void trigger() throws FrameGrabber.Exception {
        for (int i = 0; i < this.numBuffers + 1; i++) {
            grab_raw();
        }
        int[] grab_raw = grab_raw();
        this.ps3_frame = grab_raw;
        if (grab_raw != null) {
            this.triggered = Triggered.HAS_FRAME;
            this.timestamp = System.nanoTime() / 1000;
            return;
        }
        this.triggered = Triggered.NO_FRAME;
    }

    public IplImage grab_RGB4() {
        if (!this.camera.getCameraFrame(this.ps3_frame, this.timeout)) {
            return null;
        }
        this.timestamp = System.nanoTime() / 1000;
        this.image_4ch.getIntBuffer().put(this.ps3_frame);
        return this.image_4ch;
    }

    /* renamed from: org.bytedeco.javacv.PS3EyeFrameGrabber$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bytedeco$javacv$PS3EyeFrameGrabber$Triggered;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.bytedeco.javacv.PS3EyeFrameGrabber$Triggered[] r0 = org.bytedeco.javacv.PS3EyeFrameGrabber.Triggered.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bytedeco$javacv$PS3EyeFrameGrabber$Triggered = r0
                org.bytedeco.javacv.PS3EyeFrameGrabber$Triggered r1 = org.bytedeco.javacv.PS3EyeFrameGrabber.Triggered.NO_TRIGGER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bytedeco$javacv$PS3EyeFrameGrabber$Triggered     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bytedeco.javacv.PS3EyeFrameGrabber$Triggered r1 = org.bytedeco.javacv.PS3EyeFrameGrabber.Triggered.HAS_FRAME     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bytedeco$javacv$PS3EyeFrameGrabber$Triggered     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bytedeco.javacv.PS3EyeFrameGrabber$Triggered r1 = org.bytedeco.javacv.PS3EyeFrameGrabber.Triggered.NO_FRAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.PS3EyeFrameGrabber.AnonymousClass1.<clinit>():void");
        }
    }

    public Frame grab() throws FrameGrabber.Exception {
        IplImage iplImage;
        int i = AnonymousClass1.$SwitchMap$org$bytedeco$javacv$PS3EyeFrameGrabber$Triggered[this.triggered.ordinal()];
        if (i == 1) {
            iplImage = grab_RGB4();
        } else if (i == 2) {
            this.triggered = Triggered.NO_TRIGGER;
            iplImage = makeImage(this.ps3_frame);
        } else if (i == 3) {
            this.triggered = Triggered.NO_TRIGGER;
            return null;
        } else {
            throw new FrameGrabber.Exception("Int. error - unknown triggering state");
        }
        if (iplImage != null && this.imageMode == FrameGrabber.ImageMode.GRAY) {
            opencv_imgproc.cvCvtColor(iplImage, this.image_1ch, 7);
            iplImage = this.image_1ch;
        }
        return this.converter.convert(iplImage);
    }

    public void start() throws FrameGrabber.Exception {
        if (this.ps3_frame == null) {
            this.ps3_frame = new int[(this.imageWidth * this.imageHeight)];
            this.image_4ch = IplImage.create(this.imageWidth, this.imageHeight, 8, 4);
            this.image_1ch = IplImage.create(this.imageWidth, this.imageHeight, 8, 1);
        }
        if (!this.camera.createCamera(this.cameraIndex, this.imageMode == FrameGrabber.ImageMode.GRAY ? CLCamera.CLEYE_MONO_PROCESSED : CLCamera.CLEYE_COLOR_PROCESSED, (this.imageWidth == 320 && this.imageHeight == 240) ? CLCamera.CLEYE_QVGA : CLCamera.CLEYE_VGA, (int) this.frameRate)) {
            throw new FrameGrabber.Exception("Low level createCamera() failed");
        } else if (this.camera.startCamera()) {
            this.stat = "started";
        } else {
            throw new FrameGrabber.Exception("Camera start() failed");
        }
    }

    public void stop() throws FrameGrabber.Exception {
        if (this.camera.stopCamera()) {
            this.stat = "stopped";
            return;
        }
        throw new FrameGrabber.Exception("Camera stop() failed");
    }

    public void release() {
        CLCamera cLCamera = this.camera;
        if (cLCamera != null) {
            cLCamera.dispose();
            this.camera = null;
        }
        IplImage iplImage = this.image_4ch;
        if (iplImage != null) {
            iplImage.release();
            this.image_4ch = null;
        }
        IplImage iplImage2 = this.image_1ch;
        if (iplImage2 != null) {
            iplImage2.release();
            this.image_1ch = null;
        }
        if (this.ipl_frame != null) {
            this.ipl_frame = null;
        }
        if (this.ps3_frame != null) {
            this.ps3_frame = null;
        }
        this.stat = "released";
    }

    public void dispose() {
        release();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public CLCamera getCamera() {
        return this.camera;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UUID=");
        sb.append(this.uuid);
        sb.append("; status=");
        sb.append(this.stat);
        sb.append("; timeout=");
        sb.append(this.timeout);
        sb.append("; ");
        CLCamera cLCamera = this.camera;
        sb.append(cLCamera != null ? cLCamera.toString() : "<no camera>");
        return sb.toString();
    }

    public static void main(String[] strArr) {
        String[] listPS3Cameras = listPS3Cameras();
        for (int i = 0; i < listPS3Cameras.length; i++) {
            PrintStream printStream = System.out;
            printStream.println(i + ": " + listPS3Cameras[i]);
        }
    }
}
