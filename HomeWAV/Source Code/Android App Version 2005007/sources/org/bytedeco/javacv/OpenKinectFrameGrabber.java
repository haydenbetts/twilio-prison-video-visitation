package org.bytedeco.javacv;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.libfreenect.freenect_context;
import org.bytedeco.libfreenect.freenect_usb_context;
import org.bytedeco.libfreenect.global.freenect;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.IplImage;

public class OpenKinectFrameGrabber extends FrameGrabber {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static FrameGrabber.Exception loadingException;
    private ByteOrder byteOrder;
    private FrameConverter converter;
    private boolean depth = false;
    private int depthFormat;
    private int deviceNumber = 0;
    private IplImage rawDepthImage;
    private BytePointer rawDepthImageData;
    private IplImage rawIRImage;
    private BytePointer rawIRImageData;
    private IplImage rawVideoImage;
    private BytePointer rawVideoImageData;
    private IplImage returnImage;
    private int[] timestamp;
    private int videoFormat;

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        freenect_context freenect_context = new freenect_context((Pointer) null);
        int freenect_init = freenect.freenect_init(freenect_context, (freenect_usb_context) null);
        if (freenect_init >= 0) {
            int freenect_num_devices = freenect.freenect_num_devices(freenect_context);
            if (freenect_num_devices >= 0) {
                String[] strArr = new String[freenect_num_devices];
                for (int i = 0; i < freenect_num_devices; i++) {
                    strArr[i] = "Kinect #" + i;
                }
                int freenect_shutdown = freenect.freenect_shutdown(freenect_context);
                if (freenect_shutdown >= 0) {
                    return strArr;
                }
                throw new FrameGrabber.Exception("freenect_shutdown() Error " + freenect_shutdown + ": Failed to shutdown context.");
            }
            throw new FrameGrabber.Exception("freenect_num_devices() Error " + freenect_init + ": Failed to get number of devices.");
        }
        throw new FrameGrabber.Exception("freenect_init() Error " + freenect_init + ": Failed to init context.");
    }

    public static OpenKinectFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(OpenKinectFrameGrabber.class + " does not support device files.");
    }

    public static OpenKinectFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(OpenKinectFrameGrabber.class + " does not support device paths.");
    }

    public static OpenKinectFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        return new OpenKinectFrameGrabber(i);
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(freenect.class);
            } catch (Throwable th) {
                FrameGrabber.Exception exception2 = new FrameGrabber.Exception("Failed to load " + OpenKinectFrameGrabber.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public OpenKinectFrameGrabber(int i) {
        Pointer pointer = null;
        this.rawDepthImageData = new BytePointer(pointer);
        this.rawVideoImageData = new BytePointer(pointer);
        this.rawIRImageData = new BytePointer(pointer);
        this.rawDepthImage = null;
        this.rawVideoImage = null;
        this.rawIRImage = null;
        this.returnImage = null;
        this.converter = new OpenCVFrameConverter.ToIplImage();
        this.timestamp = new int[]{0};
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.depthFormat = -1;
        this.videoFormat = -1;
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

    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    public void setByteOrder(ByteOrder byteOrder2) {
        this.byteOrder = byteOrder2;
    }

    public int getDepthFormat() {
        return this.depthFormat;
    }

    public void setDepthFormat(int i) {
        this.depthFormat = i;
    }

    public int getVideoFormat() {
        return this.videoFormat;
    }

    public void setVideoFormat(int i) {
        this.videoFormat = i;
    }

    public double getGamma() {
        if (this.gamma == 0.0d) {
            return 2.2d;
        }
        return this.gamma;
    }

    public void setImageMode(FrameGrabber.ImageMode imageMode) {
        if (imageMode != this.imageMode) {
            this.returnImage = null;
        }
        super.setImageMode(imageMode);
    }

    public void start() throws FrameGrabber.Exception {
        this.depth = "depth".equalsIgnoreCase(this.format);
    }

    public void stop() throws FrameGrabber.Exception {
        freenect.freenect_sync_stop();
    }

    public void trigger() throws FrameGrabber.Exception {
        for (int i = 0; i < this.numBuffers + 1; i++) {
            if (this.depth) {
                int i2 = this.depthFormat;
                if (i2 < 0) {
                    i2 = this.bpp;
                }
                int freenect_sync_get_depth = freenect.freenect_sync_get_depth(this.rawDepthImageData, this.timestamp, this.deviceNumber, i2);
                if (freenect_sync_get_depth != 0) {
                    throw new FrameGrabber.Exception("freenect_sync_get_depth() Error " + freenect_sync_get_depth + ": Failed to get depth synchronously.");
                }
            } else {
                int i3 = this.videoFormat;
                if (i3 < 0) {
                    i3 = this.bpp;
                }
                int freenect_sync_get_video = freenect.freenect_sync_get_video(this.rawVideoImageData, this.timestamp, this.deviceNumber, i3);
                if (freenect_sync_get_video != 0) {
                    throw new FrameGrabber.Exception("freenect_sync_get_video() Error " + freenect_sync_get_video + ": Failed to get video synchronously.");
                }
            }
        }
    }

    public IplImage grabDepth() throws FrameGrabber.Exception {
        int i = this.depthFormat;
        if (i < 0) {
            i = this.bpp;
        }
        if (!(i == 0 || i == 1 || i == 4)) {
        }
        int freenect_sync_get_depth = freenect.freenect_sync_get_depth(this.rawDepthImageData, this.timestamp, this.deviceNumber, i);
        if (freenect_sync_get_depth == 0) {
            IplImage iplImage = this.rawDepthImage;
            if (!(iplImage != null && iplImage.width() == 640 && this.rawDepthImage.height() == 480)) {
                this.rawDepthImage = IplImage.createHeader(640, 480, 16, 1);
            }
            opencv_core.cvSetData(this.rawDepthImage, this.rawDepthImageData, 1280);
            if (!ByteOrder.nativeOrder().equals(this.byteOrder)) {
                ByteBuffer byteBuffer = this.rawDepthImage.getByteBuffer();
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(byteBuffer.order(ByteOrder.BIG_ENDIAN).asShortBuffer());
            }
            this.timestamp = (long) this.timestamp[0];
            return this.rawDepthImage;
        }
        throw new FrameGrabber.Exception("freenect_sync_get_depth() Error " + freenect_sync_get_depth + ": Failed to get depth synchronously.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.opencv.opencv_core.IplImage grabVideo() throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r8 = this;
            int r0 = r8.videoFormat
            if (r0 >= 0) goto L_0x0006
            int r0 = r8.bpp
        L_0x0006:
            r1 = 2
            r2 = 1
            r3 = 3
            r4 = 8
            if (r0 == 0) goto L_0x0024
            if (r0 == r2) goto L_0x0021
            if (r0 == r1) goto L_0x0021
            if (r0 == r3) goto L_0x001e
            r2 = 5
            if (r0 == r2) goto L_0x0024
            r2 = 6
            if (r0 == r2) goto L_0x001a
            goto L_0x0024
        L_0x001a:
            r1 = 8
            r2 = 2
            goto L_0x0027
        L_0x001e:
            r1 = 16
            goto L_0x0027
        L_0x0021:
            r1 = 8
            goto L_0x0027
        L_0x0024:
            r1 = 8
            r2 = 3
        L_0x0027:
            org.bytedeco.javacpp.BytePointer r5 = r8.rawVideoImageData
            int[] r6 = r8.timestamp
            int r7 = r8.deviceNumber
            int r0 = org.bytedeco.libfreenect.global.freenect.freenect_sync_get_video(r5, r6, r7, r0)
            if (r0 != 0) goto L_0x0099
            r0 = 640(0x280, float:8.97E-43)
            r5 = 480(0x1e0, float:6.73E-43)
            org.bytedeco.opencv.opencv_core.IplImage r6 = r8.rawVideoImage
            if (r6 == 0) goto L_0x0049
            int r6 = r6.width()
            if (r6 != r0) goto L_0x0049
            org.bytedeco.opencv.opencv_core.IplImage r6 = r8.rawVideoImage
            int r6 = r6.height()
            if (r6 == r5) goto L_0x004f
        L_0x0049:
            org.bytedeco.opencv.opencv_core.IplImage r5 = org.bytedeco.opencv.opencv_core.IplImage.createHeader((int) r0, (int) r5, (int) r1, (int) r2)
            r8.rawVideoImage = r5
        L_0x004f:
            org.bytedeco.opencv.opencv_core.IplImage r5 = r8.rawVideoImage
            org.bytedeco.javacpp.BytePointer r6 = r8.rawVideoImageData
            int r0 = r0 * r2
            int r0 = r0 * r1
            int r0 = r0 / r4
            org.bytedeco.opencv.global.opencv_core.cvSetData(r5, r6, r0)
            if (r1 <= r4) goto L_0x0086
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = r8.byteOrder
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0086
            org.bytedeco.opencv.opencv_core.IplImage r0 = r8.rawVideoImage
            java.nio.ByteBuffer r0 = r0.getByteBuffer()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            java.nio.ByteBuffer r1 = r0.order(r1)
            java.nio.ShortBuffer r1 = r1.asShortBuffer()
            java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r0 = r0.order(r4)
            java.nio.ShortBuffer r0 = r0.asShortBuffer()
            r0.put(r1)
        L_0x0086:
            if (r2 != r3) goto L_0x008e
            org.bytedeco.opencv.opencv_core.IplImage r0 = r8.rawVideoImage
            r1 = 4
            org.bytedeco.opencv.global.opencv_imgproc.cvCvtColor(r0, r0, r1)
        L_0x008e:
            int[] r0 = r8.timestamp
            r1 = 0
            r0 = r0[r1]
            long r0 = (long) r0
            r8.timestamp = r0
            org.bytedeco.opencv.opencv_core.IplImage r0 = r8.rawVideoImage
            return r0
        L_0x0099:
            org.bytedeco.javacv.FrameGrabber$Exception r1 = new org.bytedeco.javacv.FrameGrabber$Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "freenect_sync_get_video() Error "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = ": Failed to get video synchronously."
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.OpenKinectFrameGrabber.grabVideo():org.bytedeco.opencv.opencv_core.IplImage");
    }

    public IplImage grabIR() throws FrameGrabber.Exception {
        int freenect_sync_get_video = freenect.freenect_sync_get_video(this.rawIRImageData, this.timestamp, this.deviceNumber, 2);
        if (freenect_sync_get_video == 0) {
            IplImage iplImage = this.rawIRImage;
            if (!(iplImage != null && iplImage.width() == 640 && this.rawIRImage.height() == 480)) {
                this.rawIRImage = IplImage.createHeader(640, 480, 8, 1);
            }
            opencv_core.cvSetData(this.rawIRImage, this.rawIRImageData, 640);
            this.timestamp = (long) this.timestamp[0];
            return this.rawIRImage;
        }
        throw new FrameGrabber.Exception("freenect_sync_get_video() Error " + freenect_sync_get_video + ": Failed to get video synchronously.");
    }

    public Frame grab() throws FrameGrabber.Exception {
        IplImage grabDepth = this.depth ? grabDepth() : grabVideo();
        int width = grabDepth.width();
        int height = grabDepth.height();
        int depth2 = grabDepth.depth();
        int nChannels = grabDepth.nChannels();
        if (this.imageMode == FrameGrabber.ImageMode.COLOR && nChannels == 1) {
            if (this.returnImage == null) {
                this.returnImage = IplImage.create(width, height, depth2, 3);
            }
            opencv_imgproc.cvCvtColor(grabDepth, this.returnImage, 8);
            return this.converter.convert(this.returnImage);
        } else if (this.imageMode != FrameGrabber.ImageMode.GRAY || nChannels != 3) {
            return this.converter.convert(grabDepth);
        } else {
            if (this.returnImage == null) {
                this.returnImage = IplImage.create(width, height, depth2, 1);
            }
            opencv_imgproc.cvCvtColor(grabDepth, this.returnImage, 6);
            return this.converter.convert(this.returnImage);
        }
    }
}
