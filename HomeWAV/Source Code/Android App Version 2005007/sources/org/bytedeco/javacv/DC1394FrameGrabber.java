package org.bytedeco.javacv;

import com.twilio.video.VideoDimensions;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.libdc1394.dc1394_t;
import org.bytedeco.libdc1394.dc1394camera_id_t;
import org.bytedeco.libdc1394.dc1394camera_list_t;
import org.bytedeco.libdc1394.dc1394camera_t;
import org.bytedeco.libdc1394.dc1394video_frame_t;
import org.bytedeco.libdc1394.presets.dc1394;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class DC1394FrameGrabber extends FrameGrabber {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean linux = Loader.getPlatform().startsWith("linux");
    private static FrameGrabber.Exception loadingException;
    private dc1394camera_t camera = null;
    private dc1394video_frame_t conv_image;
    private FrameConverter converter;
    private dc1394_t d = null;
    private dc1394video_frame_t enqueue_image;
    private dc1394.pollfd fds;
    private dc1394video_frame_t frame;
    private final float[] gammaOut;
    private boolean oneShotMode;
    private final int[] out;
    private final float[] outFloat;
    private dc1394video_frame_t[] raw_image;
    private boolean resetDone;
    private IplImage return_image;
    private IplImage temp_image;

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        dc1394_t dc1394_new = org.bytedeco.libdc1394.global.dc1394.dc1394_new();
        if (dc1394_new != null) {
            dc1394camera_list_t dc1394camera_list_t = new dc1394camera_list_t((Pointer) null);
            int dc1394_camera_enumerate = org.bytedeco.libdc1394.global.dc1394.dc1394_camera_enumerate(dc1394_new, dc1394camera_list_t);
            if (dc1394_camera_enumerate == 0) {
                int num = dc1394camera_list_t.num();
                String[] strArr = new String[num];
                if (num > 0) {
                    dc1394camera_id_t ids = dc1394camera_list_t.ids();
                    int i = 0;
                    while (i < num) {
                        ids.position((long) i);
                        dc1394camera_t dc1394_camera_new_unit = org.bytedeco.libdc1394.global.dc1394.dc1394_camera_new_unit(dc1394_new, ids.guid(), ids.unit());
                        if (dc1394_camera_new_unit != null) {
                            strArr[i] = dc1394_camera_new_unit.vendor().getString() + " " + dc1394_camera_new_unit.model().getString() + " 0x" + Long.toHexString(dc1394_camera_new_unit.guid()) + " / " + dc1394_camera_new_unit.unit();
                            org.bytedeco.libdc1394.global.dc1394.dc1394_camera_free(dc1394_camera_new_unit);
                            i++;
                        } else {
                            throw new FrameGrabber.Exception("dc1394_camera_new_unit() Error: Failed to initialize camera with GUID 0x" + Long.toHexString(ids.guid()) + " / " + dc1394_camera_new_unit.unit() + ".");
                        }
                    }
                }
                org.bytedeco.libdc1394.global.dc1394.dc1394_camera_free_list(dc1394camera_list_t);
                org.bytedeco.libdc1394.global.dc1394.dc1394_free(dc1394_new);
                return strArr;
            }
            throw new FrameGrabber.Exception("dc1394_camera_enumerate() Error " + dc1394_camera_enumerate + ": Failed to enumerate cameras.");
        }
        throw new FrameGrabber.Exception("dc1394_new() Error: Failed to initialize libdc1394.");
    }

    public static DC1394FrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(DC1394FrameGrabber.class + " does not support device files.");
    }

    public static DC1394FrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(DC1394FrameGrabber.class + " does not support device paths.");
    }

    public static DC1394FrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new DC1394FrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(org.bytedeco.libdc1394.global.dc1394.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + DC1394FrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public DC1394FrameGrabber(int i) throws FrameGrabber.Exception {
        this.fds = linux ? new dc1394.pollfd() : null;
        this.oneShotMode = false;
        this.resetDone = false;
        this.raw_image = new dc1394video_frame_t[]{new dc1394video_frame_t((Pointer) null), new dc1394video_frame_t((Pointer) null)};
        this.conv_image = new dc1394video_frame_t();
        this.frame = null;
        this.enqueue_image = null;
        this.return_image = null;
        this.converter = new OpenCVFrameConverter.ToIplImage();
        this.out = new int[1];
        this.outFloat = new float[1];
        this.gammaOut = new float[1];
        this.d = org.bytedeco.libdc1394.global.dc1394.dc1394_new();
        dc1394camera_list_t dc1394camera_list_t = new dc1394camera_list_t((Pointer) null);
        int dc1394_camera_enumerate = org.bytedeco.libdc1394.global.dc1394.dc1394_camera_enumerate(this.d, dc1394camera_list_t);
        if (dc1394_camera_enumerate == 0) {
            int num = dc1394camera_list_t.num();
            if (num > i) {
                dc1394camera_id_t position = dc1394camera_list_t.ids().position((long) i);
                dc1394camera_t dc1394_camera_new_unit = org.bytedeco.libdc1394.global.dc1394.dc1394_camera_new_unit(this.d, position.guid(), position.unit());
                this.camera = dc1394_camera_new_unit;
                if (dc1394_camera_new_unit != null) {
                    org.bytedeco.libdc1394.global.dc1394.dc1394_camera_free_list(dc1394camera_list_t);
                    return;
                }
                throw new FrameGrabber.Exception("dc1394_camera_new_unit() Error: Failed to initialize camera with GUID 0x" + Long.toHexString(position.guid()) + " / " + this.camera.unit() + ".");
            }
            throw new FrameGrabber.Exception("DC1394Grabber() Error: Camera number " + i + " not found. There are only " + num + " devices.");
        }
        throw new FrameGrabber.Exception("dc1394_camera_enumerate() Error " + dc1394_camera_enumerate + ": Failed to enumerate cameras.");
    }

    public void release() throws FrameGrabber.Exception {
        if (this.camera != null) {
            stop();
            org.bytedeco.libdc1394.global.dc1394.dc1394_camera_free(this.camera);
            this.camera = null;
        }
        dc1394_t dc1394_t = this.d;
        if (dc1394_t != null) {
            org.bytedeco.libdc1394.global.dc1394.dc1394_free(dc1394_t);
            this.d = null;
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
        dc1394camera_t dc1394camera_t = this.camera;
        if (dc1394camera_t == null) {
            return super.getFrameRate();
        }
        if (org.bytedeco.libdc1394.global.dc1394.dc1394_feature_get_absolute_value(dc1394camera_t, 431, this.outFloat) != 0) {
            org.bytedeco.libdc1394.global.dc1394.dc1394_video_get_framerate(this.camera, this.out);
            org.bytedeco.libdc1394.global.dc1394.dc1394_framerate_as_float(this.out[0], this.outFloat);
        }
        return (double) this.outFloat[0];
    }

    public void setImageMode(FrameGrabber.ImageMode imageMode) {
        if (imageMode != this.imageMode) {
            this.temp_image = null;
            this.return_image = null;
        }
        super.setImageMode(imageMode);
    }

    public void start() throws FrameGrabber.Exception {
        start(true, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0179 A[Catch:{ InterruptedException -> 0x034c, Exception -> 0x0304, all -> 0x0302 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01c1 A[ADDED_TO_REGION, Catch:{ InterruptedException -> 0x034c, Exception -> 0x0304, all -> 0x0302 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void start(boolean r19, boolean r20) throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r3 = r20
            org.bytedeco.javacv.FrameGrabber$ImageMode r0 = r1.imageMode
            org.bytedeco.javacv.FrameGrabber$ImageMode r4 = org.bytedeco.javacv.FrameGrabber.ImageMode.COLOR
            r5 = 1200(0x4b0, float:1.682E-42)
            r6 = 960(0x3c0, float:1.345E-42)
            r7 = 1600(0x640, float:2.242E-42)
            r8 = 768(0x300, float:1.076E-42)
            r9 = 1280(0x500, float:1.794E-42)
            r10 = 600(0x258, float:8.41E-43)
            r11 = 1024(0x400, float:1.435E-42)
            r12 = 800(0x320, float:1.121E-42)
            r13 = 640(0x280, float:8.97E-43)
            r14 = 480(0x1e0, float:6.73E-43)
            r15 = -1
            if (r0 == r4) goto L_0x009b
            org.bytedeco.javacv.FrameGrabber$ImageMode r0 = r1.imageMode
            org.bytedeco.javacv.FrameGrabber$ImageMode r4 = org.bytedeco.javacv.FrameGrabber.ImageMode.RAW
            if (r0 != r4) goto L_0x0029
            goto L_0x009b
        L_0x0029:
            org.bytedeco.javacv.FrameGrabber$ImageMode r0 = r1.imageMode
            org.bytedeco.javacv.FrameGrabber$ImageMode r4 = org.bytedeco.javacv.FrameGrabber.ImageMode.GRAY
            if (r0 != r4) goto L_0x00db
            int r0 = r1.imageWidth
            if (r0 <= 0) goto L_0x00db
            int r0 = r1.imageHeight
            if (r0 > 0) goto L_0x0039
            goto L_0x00db
        L_0x0039:
            int r0 = r1.imageWidth
            r4 = 8
            if (r0 > r13) goto L_0x004f
            int r0 = r1.imageHeight
            if (r0 > r14) goto L_0x004f
            int r0 = r1.bpp
            if (r0 <= r4) goto L_0x004b
            r0 = 70
            goto L_0x00dc
        L_0x004b:
            r0 = 69
            goto L_0x00dc
        L_0x004f:
            int r0 = r1.imageWidth
            if (r0 > r12) goto L_0x0063
            int r0 = r1.imageHeight
            if (r0 > r10) goto L_0x0063
            int r0 = r1.bpp
            if (r0 <= r4) goto L_0x005f
            r0 = 77
            goto L_0x00dc
        L_0x005f:
            r0 = 73
            goto L_0x00dc
        L_0x0063:
            int r0 = r1.imageWidth
            if (r0 > r11) goto L_0x0077
            int r0 = r1.imageHeight
            if (r0 > r8) goto L_0x0077
            int r0 = r1.bpp
            if (r0 <= r4) goto L_0x0073
            r0 = 78
            goto L_0x00dc
        L_0x0073:
            r0 = 76
            goto L_0x00dc
        L_0x0077:
            int r0 = r1.imageWidth
            if (r0 > r9) goto L_0x0089
            int r0 = r1.imageHeight
            if (r0 > r6) goto L_0x0089
            int r0 = r1.bpp
            if (r0 <= r4) goto L_0x0086
            r0 = 85
            goto L_0x00dc
        L_0x0086:
            r0 = 81
            goto L_0x00dc
        L_0x0089:
            int r0 = r1.imageWidth
            if (r0 > r7) goto L_0x00db
            int r0 = r1.imageHeight
            if (r0 > r5) goto L_0x00db
            int r0 = r1.bpp
            if (r0 <= r4) goto L_0x0098
            r0 = 86
            goto L_0x00dc
        L_0x0098:
            r0 = 84
            goto L_0x00dc
        L_0x009b:
            int r0 = r1.imageWidth
            if (r0 <= 0) goto L_0x00db
            int r0 = r1.imageHeight
            if (r0 > 0) goto L_0x00a4
            goto L_0x00db
        L_0x00a4:
            int r0 = r1.imageWidth
            if (r0 > r13) goto L_0x00af
            int r0 = r1.imageHeight
            if (r0 > r14) goto L_0x00af
            r0 = 68
            goto L_0x00dc
        L_0x00af:
            int r0 = r1.imageWidth
            if (r0 > r12) goto L_0x00ba
            int r0 = r1.imageHeight
            if (r0 > r10) goto L_0x00ba
            r0 = 72
            goto L_0x00dc
        L_0x00ba:
            int r0 = r1.imageWidth
            if (r0 > r11) goto L_0x00c5
            int r0 = r1.imageHeight
            if (r0 > r8) goto L_0x00c5
            r0 = 75
            goto L_0x00dc
        L_0x00c5:
            int r0 = r1.imageWidth
            if (r0 > r9) goto L_0x00d0
            int r0 = r1.imageHeight
            if (r0 > r6) goto L_0x00d0
            r0 = 80
            goto L_0x00dc
        L_0x00d0:
            int r0 = r1.imageWidth
            if (r0 > r7) goto L_0x00db
            int r0 = r1.imageHeight
            if (r0 > r5) goto L_0x00db
            r0 = 83
            goto L_0x00dc
        L_0x00db:
            r0 = -1
        L_0x00dc:
            r4 = 0
            if (r0 != r15) goto L_0x00ea
            org.bytedeco.libdc1394.dc1394camera_t r0 = r1.camera
            int[] r5 = r1.out
            org.bytedeco.libdc1394.global.dc1394.dc1394_video_get_mode(r0, r5)
            int[] r0 = r1.out
            r0 = r0[r4]
        L_0x00ea:
            r6 = r0
            double r7 = r1.frameRate
            r16 = 0
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 > 0) goto L_0x00f6
        L_0x00f3:
            r0 = -1
            goto L_0x0165
        L_0x00f6:
            double r7 = r1.frameRate
            r9 = 4611127572073593962(0x3ffe04189374bc6a, double:1.876)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x0104
            r0 = 32
            goto L_0x0165
        L_0x0104:
            double r7 = r1.frameRate
            r9 = 4615649186099473940(0x400e147ae147ae14, double:3.76)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x0112
            r0 = 33
            goto L_0x0165
        L_0x0112:
            double r7 = r1.frameRate
            r9 = 4620141526727776010(0x401e0a3d70a3d70a, double:7.51)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x0120
            r0 = 34
            goto L_0x0165
        L_0x0120:
            double r7 = r1.frameRate
            r9 = 4624639496855612293(0x402e051eb851eb85, double:15.01)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x012e
            r0 = 35
            goto L_0x0165
        L_0x012e:
            double r7 = r1.frameRate
            r9 = 4629140281733215683(0x403e028f5c28f5c3, double:30.01)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x013c
            r0 = 36
            goto L_0x0165
        L_0x013c:
            double r7 = r1.frameRate
            r9 = 4633642473985702625(0x404e0147ae147ae1, double:60.01)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x014a
            r0 = 37
            goto L_0x0165
        L_0x014a:
            double r7 = r1.frameRate
            r9 = 4638145369925631345(0x405e00a3d70a3d71, double:120.01)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x0158
            r0 = 38
            goto L_0x0165
        L_0x0158:
            double r7 = r1.frameRate
            r9 = 4642648617709280952(0x406e0051eb851eb8, double:240.01)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x00f3
            r0 = 39
        L_0x0165:
            if (r0 != r15) goto L_0x0172
            org.bytedeco.libdc1394.dc1394camera_t r0 = r1.camera
            int[] r5 = r1.out
            org.bytedeco.libdc1394.global.dc1394.dc1394_video_get_framerate(r0, r5)
            int[] r0 = r1.out
            r0 = r0[r4]
        L_0x0172:
            r13 = 1
            r1.oneShotMode = r4     // Catch:{ Exception -> 0x0304 }
            boolean r5 = r1.triggerMode     // Catch:{ Exception -> 0x0304 }
            if (r5 == 0) goto L_0x01a6
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_power(r5, r13)     // Catch:{ Exception -> 0x0304 }
            if (r5 == 0) goto L_0x0184
            r1.oneShotMode = r13     // Catch:{ Exception -> 0x0304 }
            goto L_0x01a6
        L_0x0184:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 390(0x186, float:5.47E-43)
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_mode(r5, r7)     // Catch:{ Exception -> 0x0304 }
            if (r5 == 0) goto L_0x0195
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 384(0x180, float:5.38E-43)
            org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_mode(r5, r7)     // Catch:{ Exception -> 0x0304 }
        L_0x0195:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 580(0x244, float:8.13E-43)
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_source(r5, r7)     // Catch:{ Exception -> 0x0304 }
            if (r5 == 0) goto L_0x01a6
            r1.oneShotMode = r13     // Catch:{ Exception -> 0x0304 }
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_power(r5, r4)     // Catch:{ Exception -> 0x0304 }
        L_0x01a6:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_operation_mode(r5, r14)     // Catch:{ Exception -> 0x0304 }
            if (r3 == 0) goto L_0x01bf
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 481(0x1e1, float:6.74E-43)
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_operation_mode(r5, r7)     // Catch:{ Exception -> 0x0304 }
            if (r5 != 0) goto L_0x01bf
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 3
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_iso_speed(r5, r7)     // Catch:{ Exception -> 0x0304 }
        L_0x01bf:
            if (r5 != 0) goto L_0x01c3
            if (r3 != 0) goto L_0x01cc
        L_0x01c3:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = 2
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_iso_speed(r5, r7)     // Catch:{ Exception -> 0x0304 }
            if (r5 != 0) goto L_0x02e6
        L_0x01cc:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_mode(r5, r6)     // Catch:{ Exception -> 0x0304 }
            if (r5 != 0) goto L_0x02ca
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_is_video_mode_scalable(r6)     // Catch:{ Exception -> 0x0304 }
            if (r5 != r13) goto L_0x0205
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            r7 = -1
            r8 = -1
            r9 = -1
            r10 = -1
            r11 = -1
            r12 = -1
            int r0 = org.bytedeco.libdc1394.global.dc1394.dc1394_format7_set_roi(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0304 }
            if (r0 != 0) goto L_0x01e9
            goto L_0x020d
        L_0x01e9:
            org.bytedeco.javacv.FrameGrabber$Exception r5 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_format7_set_roi() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = ": Could not set format7 mode."
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0304 }
            throw r5     // Catch:{ Exception -> 0x0304 }
        L_0x0205:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r0 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_framerate(r5, r0)     // Catch:{ Exception -> 0x0304 }
            if (r0 != 0) goto L_0x02ae
        L_0x020d:
            org.bytedeco.libdc1394.dc1394camera_t r0 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r5 = r1.numBuffers     // Catch:{ Exception -> 0x0304 }
            r6 = 4
            int r0 = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_setup(r0, r5, r6)     // Catch:{ Exception -> 0x0304 }
            if (r0 != 0) goto L_0x0292
            double r5 = r1.gamma     // Catch:{ Exception -> 0x0304 }
            r0 = 422(0x1a6, float:5.91E-43)
            int r7 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x0248
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            double r6 = r1.gamma     // Catch:{ Exception -> 0x0304 }
            float r6 = (float) r6     // Catch:{ Exception -> 0x0304 }
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_feature_set_absolute_value(r5, r0, r6)     // Catch:{ Exception -> 0x0304 }
            if (r5 != 0) goto L_0x022c
            goto L_0x0248
        L_0x022c:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_feature_set_absolute_value() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = ": Could not set gamma."
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0304 }
            throw r0     // Catch:{ Exception -> 0x0304 }
        L_0x0248:
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            float[] r6 = r1.gammaOut     // Catch:{ Exception -> 0x0304 }
            int r0 = org.bytedeco.libdc1394.global.dc1394.dc1394_feature_get_absolute_value(r5, r0, r6)     // Catch:{ Exception -> 0x0304 }
            if (r0 == 0) goto L_0x0259
            float[] r0 = r1.gammaOut     // Catch:{ Exception -> 0x0304 }
            r5 = 1074580685(0x400ccccd, float:2.2)
            r0[r4] = r5     // Catch:{ Exception -> 0x0304 }
        L_0x0259:
            boolean r0 = linux     // Catch:{ Exception -> 0x0304 }
            if (r0 == 0) goto L_0x0268
            org.bytedeco.libdc1394.presets.dc1394$pollfd r0 = r1.fds     // Catch:{ Exception -> 0x0304 }
            org.bytedeco.libdc1394.dc1394camera_t r5 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r5 = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_get_fileno(r5)     // Catch:{ Exception -> 0x0304 }
            r0.fd(r5)     // Catch:{ Exception -> 0x0304 }
        L_0x0268:
            boolean r0 = r1.oneShotMode     // Catch:{ Exception -> 0x0304 }
            if (r0 != 0) goto L_0x031a
            org.bytedeco.libdc1394.dc1394camera_t r0 = r1.camera     // Catch:{ Exception -> 0x0304 }
            int r0 = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_transmission(r0, r13)     // Catch:{ Exception -> 0x0304 }
            if (r0 != 0) goto L_0x0276
            goto L_0x031a
        L_0x0276:
            org.bytedeco.javacv.FrameGrabber$Exception r5 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_video_set_transmission() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = ": Could not start camera iso transmission."
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0304 }
            throw r5     // Catch:{ Exception -> 0x0304 }
        L_0x0292:
            org.bytedeco.javacv.FrameGrabber$Exception r5 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_capture_setup() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = ": Could not setup camera-\nmake sure that the video mode and framerate are\nsupported by your camera."
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0304 }
            throw r5     // Catch:{ Exception -> 0x0304 }
        L_0x02ae:
            org.bytedeco.javacv.FrameGrabber$Exception r5 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_video_set_framerate() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = ": Could not set framerate."
            r6.append(r0)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0304 }
            throw r5     // Catch:{ Exception -> 0x0304 }
        L_0x02ca:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_video_set_mode() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = ": Could not set video mode."
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0304 }
            throw r0     // Catch:{ Exception -> 0x0304 }
        L_0x02e6:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ Exception -> 0x0304 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0304 }
            r6.<init>()     // Catch:{ Exception -> 0x0304 }
            java.lang.String r7 = "dc1394_video_set_iso_speed() Error "
            r6.append(r7)     // Catch:{ Exception -> 0x0304 }
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = ": Could not set maximum iso speed."
            r6.append(r5)     // Catch:{ Exception -> 0x0304 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0304 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0304 }
            throw r0     // Catch:{ Exception -> 0x0304 }
        L_0x0302:
            r0 = move-exception
            goto L_0x035d
        L_0x0304:
            r0 = move-exception
            if (r2 == 0) goto L_0x035c
            boolean r5 = r1.resetDone     // Catch:{ all -> 0x0302 }
            if (r5 != 0) goto L_0x035c
            org.bytedeco.libdc1394.dc1394camera_t r0 = r1.camera     // Catch:{ InterruptedException -> 0x034c }
            org.bytedeco.libdc1394.global.dc1394.dc1394_reset_bus(r0)     // Catch:{ InterruptedException -> 0x034c }
            r5 = 100
            java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x034c }
            r1.resetDone = r13     // Catch:{ InterruptedException -> 0x034c }
            r1.start(r4, r3)     // Catch:{ InterruptedException -> 0x034c }
        L_0x031a:
            r1.resetDone = r4
            boolean r0 = linux
            if (r0 == 0) goto L_0x034b
            if (r3 == 0) goto L_0x034b
            boolean r0 = r1.triggerMode
            if (r0 == 0) goto L_0x0329
            r18.trigger()
        L_0x0329:
            org.bytedeco.libdc1394.presets.dc1394$pollfd r0 = r1.fds
            r0.events(r13)
            org.bytedeco.libdc1394.presets.dc1394$pollfd r0 = r1.fds
            r5 = 1
            int r3 = r1.timeout
            int r0 = org.bytedeco.libdc1394.global.dc1394.poll(r0, r5, r3)
            if (r0 != 0) goto L_0x0341
            r18.stop()
            r1.start(r2, r4)
            goto L_0x034b
        L_0x0341:
            boolean r0 = r1.triggerMode
            if (r0 == 0) goto L_0x034b
            r18.grab()
            r18.enqueue()
        L_0x034b:
            return
        L_0x034c:
            r0 = move-exception
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0302 }
            r2.interrupt()     // Catch:{ all -> 0x0302 }
            org.bytedeco.javacv.FrameGrabber$Exception r2 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x0302 }
            java.lang.String r3 = "dc1394_reset_bus() Error: Could not reset bus and try to start again."
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0302 }
            throw r2     // Catch:{ all -> 0x0302 }
        L_0x035c:
            throw r0     // Catch:{ all -> 0x0302 }
        L_0x035d:
            r1.resetDone = r4
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.DC1394FrameGrabber.start(boolean, boolean):void");
    }

    public void stop() throws FrameGrabber.Exception {
        int dc1394_external_trigger_set_power;
        this.enqueue_image = null;
        this.temp_image = null;
        this.return_image = null;
        this.timestamp = 0;
        this.frameNumber = 0;
        int dc1394_video_set_transmission = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_transmission(this.camera, 0);
        if (dc1394_video_set_transmission == 0) {
            int dc1394_capture_stop = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_stop(this.camera);
            if (dc1394_capture_stop != 0 && dc1394_capture_stop != -10) {
                throw new FrameGrabber.Exception("dc1394_capture_stop() Error " + dc1394_capture_stop + ": Could not stop the camera?");
            } else if (org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_get_mode(this.camera, this.out) == 0 && this.out[0] >= 384 && (dc1394_external_trigger_set_power = org.bytedeco.libdc1394.global.dc1394.dc1394_external_trigger_set_power(this.camera, 0)) != 0) {
                throw new FrameGrabber.Exception("dc1394_external_trigger_set_power() Error " + dc1394_external_trigger_set_power + ": Could not switch off external trigger.");
            }
        } else {
            throw new FrameGrabber.Exception("dc1394_video_set_transmission() Error " + dc1394_video_set_transmission + ": Could not stop the camera?");
        }
    }

    private void enqueue() throws FrameGrabber.Exception {
        enqueue(this.enqueue_image);
        this.enqueue_image = null;
    }

    private void enqueue(dc1394video_frame_t dc1394video_frame_t) throws FrameGrabber.Exception {
        int dc1394_capture_enqueue;
        if (dc1394video_frame_t != null && (dc1394_capture_enqueue = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_enqueue(this.camera, dc1394video_frame_t)) != 0) {
            throw new FrameGrabber.Exception("dc1394_capture_enqueue() Error " + dc1394_capture_enqueue + ": Could not release a frame.");
        }
    }

    public void trigger() throws FrameGrabber.Exception {
        enqueue();
        if (this.oneShotMode) {
            int dc1394_video_set_one_shot = org.bytedeco.libdc1394.global.dc1394.dc1394_video_set_one_shot(this.camera, 1);
            if (dc1394_video_set_one_shot != 0) {
                throw new FrameGrabber.Exception("dc1394_video_set_one_shot() Error " + dc1394_video_set_one_shot + ": Could not set camera into one-shot mode.");
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        do {
            org.bytedeco.libdc1394.global.dc1394.dc1394_software_trigger_get_power(this.camera, this.out);
            if (System.currentTimeMillis() - currentTimeMillis > ((long) this.timeout) || this.out[0] != 1) {
                int dc1394_software_trigger_set_power = org.bytedeco.libdc1394.global.dc1394.dc1394_software_trigger_set_power(this.camera, 1);
            }
            org.bytedeco.libdc1394.global.dc1394.dc1394_software_trigger_get_power(this.camera, this.out);
            break;
        } while (this.out[0] != 1);
        int dc1394_software_trigger_set_power2 = org.bytedeco.libdc1394.global.dc1394.dc1394_software_trigger_set_power(this.camera, 1);
        if (dc1394_software_trigger_set_power2 != 0) {
            throw new FrameGrabber.Exception("dc1394_software_trigger_set_power() Error " + dc1394_software_trigger_set_power2 + ": Could not trigger camera.");
        }
    }

    public Frame grab() throws FrameGrabber.Exception {
        int i;
        boolean z;
        int dc1394_convert_frames;
        int i2;
        int i3;
        enqueue();
        if (linux) {
            this.fds.events(1);
            if (org.bytedeco.libdc1394.global.dc1394.poll(this.fds, 1, this.timeout) == 0) {
                throw new FrameGrabber.Exception("poll() Error: Timeout occured. (Has start() been called?)");
            }
        }
        int dc1394_capture_dequeue = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_dequeue(this.camera, 672, this.raw_image[0]);
        if (dc1394_capture_dequeue == 0) {
            int i4 = 0;
            int i5 = 0;
            while (!this.raw_image[i4].isNull()) {
                enqueue();
                dc1394video_frame_t[] dc1394video_frame_tArr = this.raw_image;
                this.enqueue_image = dc1394video_frame_tArr[i4];
                i4 = (i4 + 1) % 2;
                i5++;
                int dc1394_capture_dequeue2 = org.bytedeco.libdc1394.global.dc1394.dc1394_capture_dequeue(this.camera, 673, dc1394video_frame_tArr[i4]);
                if (dc1394_capture_dequeue2 != 0) {
                    throw new FrameGrabber.Exception("dc1394_capture_dequeue(POLL) Error " + dc1394_capture_dequeue2 + ": Could not capture a frame.");
                }
            }
            dc1394video_frame_t dc1394video_frame_t = this.raw_image[(i4 + 1) % 2];
            this.frame = dc1394video_frame_t;
            int size = dc1394video_frame_t.size(0);
            int size2 = this.frame.size(1);
            int data_depth = this.frame.data_depth();
            int i6 = data_depth != 8 ? data_depth != 16 ? 0 : 16 : 8;
            int stride = this.frame.stride();
            int image_bytes = this.frame.image_bytes();
            int i7 = ((stride / size) * 8) / data_depth;
            ByteOrder byteOrder = this.frame.little_endian() != 0 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
            int color_coding = this.frame.color_coding();
            boolean z2 = color_coding == 361 || color_coding == 362;
            boolean z3 = color_coding == 356 || color_coding == 358;
            boolean z4 = color_coding == 353 || color_coding == 354 || color_coding == 355;
            BytePointer image = this.frame.image();
            if ((data_depth <= 8 || byteOrder.equals(ByteOrder.nativeOrder())) && !z4 && (this.imageMode == FrameGrabber.ImageMode.RAW || ((this.imageMode == FrameGrabber.ImageMode.COLOR && i7 == 3) || (this.imageMode == FrameGrabber.ImageMode.GRAY && i7 == 1 && !z2)))) {
                if (this.return_image == null) {
                    this.return_image = IplImage.createHeader(size, size2, i6, i7);
                }
                this.return_image.widthStep(stride);
                this.return_image.imageSize(image_bytes);
                this.return_image.imageData(image);
                i = i5;
            } else {
                double padding_bytes = (double) this.frame.padding_bytes();
                i = i5;
                int ceil = (int) Math.ceil(padding_bytes / ((double) ((size * data_depth) / 8)));
                int i8 = stride;
                int i9 = image_bytes;
                int ceil2 = (int) Math.ceil(padding_bytes / ((double) (((size * 3) * data_depth) / 8)));
                if (this.return_image == null) {
                    int i10 = this.imageMode == FrameGrabber.ImageMode.COLOR ? 3 : 1;
                    int i11 = this.imageMode == FrameGrabber.ImageMode.COLOR ? ceil2 : ceil;
                    IplImage create = IplImage.create(size, size2 + i11, i6, i10);
                    this.return_image = create;
                    create.height(create.height() - i11);
                }
                if (this.temp_image == null) {
                    if (this.imageMode == FrameGrabber.ImageMode.COLOR && ((i7 > 1 || data_depth > 8) && !z4 && !z2)) {
                        IplImage create2 = IplImage.create(size, size2 + ceil, i6, i7);
                        this.temp_image = create2;
                        create2.height(create2.height() - ceil);
                    } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && (z4 || z2 || (z3 && data_depth > 8))) {
                        IplImage create3 = IplImage.create(size, size2 + ceil2, i6, 3);
                        this.temp_image = create3;
                        create3.height(create3.height() - ceil2);
                    } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && z3) {
                        this.temp_image = IplImage.createHeader(size, size2, i6, 3);
                    } else if (this.imageMode != FrameGrabber.ImageMode.COLOR || i7 != 1 || z4 || z2) {
                        this.temp_image = this.return_image;
                    } else {
                        this.temp_image = IplImage.createHeader(size, size2, i6, 1);
                    }
                }
                this.conv_image.size(0, this.temp_image.width());
                this.conv_image.size(1, this.temp_image.height());
                if (data_depth > 8) {
                    dc1394video_frame_t dc1394video_frame_t2 = this.conv_image;
                    if (this.imageMode == FrameGrabber.ImageMode.RAW) {
                        i3 = 362;
                    } else {
                        i3 = this.temp_image.nChannels() == 1 ? 357 : 358;
                    }
                    dc1394video_frame_t2.color_coding(i3);
                    this.conv_image.data_depth(16);
                } else {
                    dc1394video_frame_t dc1394video_frame_t3 = this.conv_image;
                    if (this.imageMode == FrameGrabber.ImageMode.RAW) {
                        i2 = 361;
                    } else {
                        i2 = this.temp_image.nChannels() == 1 ? VideoDimensions.CIF_VIDEO_WIDTH : 356;
                    }
                    dc1394video_frame_t3.color_coding(i2);
                    this.conv_image.data_depth(8);
                }
                this.conv_image.stride(this.temp_image.widthStep());
                int imageSize = this.temp_image.imageSize();
                long j = (long) imageSize;
                this.conv_image.allocated_image_bytes(j).total_bytes(j).image_bytes(imageSize);
                this.conv_image.image(this.temp_image.imageData());
                if (z2) {
                    int color_filter = this.frame.color_filter();
                    if (color_filter == 512) {
                        this.frame.color_filter(515);
                    } else if (color_filter == 513) {
                        this.frame.color_filter(514);
                    } else if (color_filter == 514) {
                        this.frame.color_filter(513);
                    } else if (color_filter == 515) {
                        this.frame.color_filter(512);
                    }
                    int dc1394_debayer_frames = org.bytedeco.libdc1394.global.dc1394.dc1394_debayer_frames(this.frame, this.conv_image, 1);
                    this.frame.color_filter(color_filter);
                    if (dc1394_debayer_frames != 0) {
                        throw new FrameGrabber.Exception("dc1394_debayer_frames() Error " + dc1394_debayer_frames + ": Could not debayer frame.");
                    }
                } else if (data_depth > 8 && this.frame.data_depth() == this.conv_image.data_depth() && this.frame.color_coding() == this.conv_image.color_coding() && this.frame.stride() == this.conv_image.stride()) {
                    this.temp_image.getByteBuffer().order(ByteOrder.nativeOrder()).asShortBuffer().put(this.frame.getByteBuffer().order(byteOrder).asShortBuffer());
                    z = true;
                    if (!z && data_depth > 8 && !byteOrder.equals(ByteOrder.nativeOrder())) {
                        ByteBuffer byteBuffer = this.temp_image.getByteBuffer();
                        byteBuffer.order(ByteOrder.nativeOrder()).asShortBuffer().put(byteBuffer.order(byteOrder).asShortBuffer());
                    }
                    if (this.imageMode == FrameGrabber.ImageMode.COLOR && i7 == 1 && !z4 && !z2) {
                        opencv_imgproc.cvCvtColor(this.temp_image, this.return_image, 8);
                    } else if (this.imageMode == FrameGrabber.ImageMode.GRAY && (z2 || z3 || z4)) {
                        opencv_imgproc.cvCvtColor(this.temp_image, this.return_image, 6);
                    }
                } else if ((this.imageMode == FrameGrabber.ImageMode.GRAY && z3) || (this.imageMode == FrameGrabber.ImageMode.COLOR && i7 == 1 && !z4 && !z2)) {
                    this.temp_image.widthStep(i8);
                    this.temp_image.imageSize(i9);
                    this.temp_image.imageData(image);
                } else if (!z3 && ((z2 || z4 || i7 > 1) && (dc1394_convert_frames = org.bytedeco.libdc1394.global.dc1394.dc1394_convert_frames(this.frame, this.conv_image)) != 0)) {
                    throw new FrameGrabber.Exception("dc1394_convert_frames() Error " + dc1394_convert_frames + ": Could not convert frame.");
                }
                z = false;
                ByteBuffer byteBuffer2 = this.temp_image.getByteBuffer();
                byteBuffer2.order(ByteOrder.nativeOrder()).asShortBuffer().put(byteBuffer2.order(byteOrder).asShortBuffer());
                if (!(this.imageMode == FrameGrabber.ImageMode.COLOR || i7 == 1)) {
                }
                opencv_imgproc.cvCvtColor(this.temp_image, this.return_image, 6);
            }
            switch (this.frame.color_filter()) {
                case 512:
                    this.sensorPattern = 0;
                    break;
                case 513:
                    this.sensorPattern = 4294967296L;
                    break;
                case 514:
                    this.sensorPattern = 1;
                    break;
                case 515:
                    this.sensorPattern = FrameGrabber.SENSOR_PATTERN_BGGR;
                    break;
                default:
                    this.sensorPattern = -1;
                    break;
            }
            dc1394video_frame_t dc1394video_frame_t4 = this.frame;
            this.enqueue_image = dc1394video_frame_t4;
            this.timestamp = dc1394video_frame_t4.timestamp();
            this.frameNumber += i;
            return this.converter.convert(this.return_image);
        }
        throw new FrameGrabber.Exception("dc1394_capture_dequeue(WAIT) Error " + dc1394_capture_dequeue + ": Could not capture a frame. (Has start() been called?)");
    }
}
