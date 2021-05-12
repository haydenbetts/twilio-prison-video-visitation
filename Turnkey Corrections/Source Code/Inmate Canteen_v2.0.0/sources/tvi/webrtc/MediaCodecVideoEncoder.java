package tvi.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase14;
import tvi.webrtc.VideoFrame;

@TargetApi(19)
@JNINamespace("webrtc::jni")
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    @Nullable
    private static MediaCodecVideoEncoderErrorCallback errorCallback;
    private static final MediaCodecProperties exynosH264HighProfileHwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HighProfileHwList = {exynosH264HighProfileHwProperties};
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties mediatekH264HwProperties = new MediaCodecProperties("OMX.MTK.", 27, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    @Nullable
    private static MediaCodecVideoEncoder runningInstance;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private static final MediaCodecProperties[] vp9HwList = {qcomVp9HwProperties, exynosVp9HwProperties};
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    @Nullable
    private ByteBuffer configData = null;
    @Nullable
    private GlRectDrawer drawer;
    @Nullable
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    @Nullable
    private Surface inputSurface;
    private long lastKeyFrameMs;
    /* access modifiers changed from: private */
    @Nullable
    public MediaCodec mediaCodec;
    @Nullable
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    private static native void nativeFillInputBuffer(long j, int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3, ByteBuffer byteBuffer3, int i4);

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264;

        @CalledByNative("VideoCodecType")
        static VideoCodecType fromNativeIndex(int i) {
            return values()[i];
        }
    }

    public enum H264Profile {
        CONSTRAINED_BASELINE(0),
        BASELINE(1),
        MAIN(2),
        CONSTRAINED_HIGH(3),
        HIGH(4);
        
        private final int value;

        private H264Profile(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    private static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecPrefix = str;
            this.minSdk = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals(PeerConnectionFactory.TRIAL_ENABLED)) {
            arrayList.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    private static final MediaCodecProperties[] h264HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomH264HwProperties);
        arrayList.add(exynosH264HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals(PeerConnectionFactory.TRIAL_ENABLED)) {
            arrayList.add(mediatekH264HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    @CalledByNative
    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList) != null;
    }

    @Nullable
    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE)) {
            return null;
        }
        return findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList);
    }

    @CalledByNative
    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedColorList) != null;
    }

    @CalledByNative
    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList(), supportedColorList) != null;
    }

    public static boolean isH264HighProfileHwSupported() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) && findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) && findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedSurfaceColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains(H264_MIME_TYPE) && findHwEncoder(H264_MIME_TYPE, h264HwList(), supportedSurfaceColorList) != null;
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType2) {
            this.codecName = str;
            this.colorFormat = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType2;
        }
    }

    @Nullable
    private static EncoderProperties findHwEncoder(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr) {
        MediaCodecInfo mediaCodecInfo;
        String str2;
        boolean z;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!str.equals(H264_MIME_TYPE) || !Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
                try {
                    mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                    mediaCodecInfo = null;
                }
                if (mediaCodecInfo != null && mediaCodecInfo.isEncoder()) {
                    String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            str2 = null;
                            break;
                        } else if (supportedTypes[i2].equals(str)) {
                            str2 = mediaCodecInfo.getName();
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (str2 == null) {
                        continue;
                    } else {
                        Logging.v(TAG, "Found candidate encoder " + str2);
                        BitrateAdjustmentType bitrateAdjustmentType2 = BitrateAdjustmentType.NO_ADJUSTMENT;
                        int length2 = mediaCodecPropertiesArr.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length2) {
                                z = false;
                                break;
                            }
                            MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr[i3];
                            if (str2.startsWith(mediaCodecProperties.codecPrefix)) {
                                if (Build.VERSION.SDK_INT < mediaCodecProperties.minSdk) {
                                    Logging.w(TAG, "Codec " + str2 + " is disabled due to SDK version " + Build.VERSION.SDK_INT);
                                } else {
                                    if (mediaCodecProperties.bitrateAdjustmentType != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                        bitrateAdjustmentType2 = mediaCodecProperties.bitrateAdjustmentType;
                                        Logging.w(TAG, "Codec " + str2 + " requires bitrate adjustment: " + bitrateAdjustmentType2);
                                    }
                                    z = true;
                                }
                            }
                            i3++;
                        }
                        if (!z) {
                            continue;
                        } else {
                            try {
                                MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                                for (int i4 : capabilitiesForType.colorFormats) {
                                    Logging.v(TAG, "   Color: 0x" + Integer.toHexString(i4));
                                }
                                for (int i5 : iArr) {
                                    for (int i6 : capabilitiesForType.colorFormats) {
                                        if (i6 == i5) {
                                            Logging.d(TAG, "Found target encoder for mime " + str + " : " + str2 + ". Color: 0x" + Integer.toHexString(i6) + ". Bitrate adjustment: " + bitrateAdjustmentType2);
                                            return new EncoderProperties(str2, i6, bitrateAdjustmentType2);
                                        }
                                    }
                                }
                                continue;
                            } catch (IllegalArgumentException e2) {
                                Logging.e(TAG, "Cannot retrieve encoder capabilities", e2);
                            }
                        }
                    }
                }
            }
            return null;
        }
        Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
        return null;
    }

    @CalledByNative
    MediaCodecVideoEncoder() {
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (mediaCodecVideoEncoder != null && (thread = mediaCodecVideoEncoder.mediaCodecThread) != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    @Nullable
    static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean initEncode(VideoCodecType videoCodecType, int i, int i2, int i3, int i4, int i5, @Nullable EglBase14.Context context) {
        boolean z;
        EncoderProperties encoderProperties;
        String str;
        boolean z2;
        boolean z3;
        VideoCodecType videoCodecType2 = videoCodecType;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        boolean z4 = context != null;
        Logging.d(TAG, "Java initEncode: " + videoCodecType2 + ". Profile: " + i6 + " : " + i7 + " x " + i8 + ". @ " + i9 + " kbps. Fps: " + i10 + ". Encode from texture : " + z4);
        this.profile = i6;
        this.width = i7;
        this.height = i8;
        if (this.mediaCodecThread == null) {
            int i11 = 100;
            if (videoCodecType2 == VideoCodecType.VIDEO_CODEC_VP8) {
                str = VP8_MIME_TYPE;
                encoderProperties = findHwEncoder(VP8_MIME_TYPE, vp8HwList(), z4 ? supportedSurfaceColorList : supportedColorList);
                z = false;
            } else if (videoCodecType2 == VideoCodecType.VIDEO_CODEC_VP9) {
                str = VP9_MIME_TYPE;
                encoderProperties = findHwEncoder(VP9_MIME_TYPE, vp9HwList, z4 ? supportedSurfaceColorList : supportedColorList);
                z = false;
            } else if (videoCodecType2 == VideoCodecType.VIDEO_CODEC_H264) {
                EncoderProperties findHwEncoder = findHwEncoder(H264_MIME_TYPE, h264HwList(), z4 ? supportedSurfaceColorList : supportedColorList);
                if (i6 == H264Profile.CONSTRAINED_HIGH.getValue()) {
                    if (findHwEncoder(H264_MIME_TYPE, h264HighProfileHwList, z4 ? supportedSurfaceColorList : supportedColorList) != null) {
                        Logging.d(TAG, "High profile H.264 encoder supported.");
                        z3 = true;
                        z = z3;
                        str = H264_MIME_TYPE;
                        encoderProperties = findHwEncoder;
                        i11 = 20;
                    } else {
                        Logging.d(TAG, "High profile H.264 encoder requested, but not supported. Use baseline.");
                    }
                }
                z3 = false;
                z = z3;
                str = H264_MIME_TYPE;
                encoderProperties = findHwEncoder;
                i11 = 20;
            } else {
                str = null;
                encoderProperties = null;
                i11 = 0;
                z = false;
            }
            if (encoderProperties != null) {
                runningInstance = this;
                this.colorFormat = encoderProperties.colorFormat;
                this.bitrateAdjustmentType = encoderProperties.bitrateAdjustmentType;
                int i12 = 30;
                if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT) {
                    z2 = z4;
                } else {
                    i12 = Math.min(i10, 30);
                    z2 = z4;
                }
                this.forcedKeyFrameMs = 0;
                this.lastKeyFrameMs = -1;
                if (videoCodecType2 == VideoCodecType.VIDEO_CODEC_VP8 && encoderProperties.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
                    if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                        this.forcedKeyFrameMs = 15000;
                    } else if (Build.VERSION.SDK_INT == 23) {
                        this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
                    } else if (Build.VERSION.SDK_INT > 23) {
                        this.forcedKeyFrameMs = 15000;
                    }
                }
                Logging.d(TAG, "Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + i12);
                this.targetBitrateBps = i9 * 1000;
                this.targetFps = i12;
                double d = (double) this.targetBitrateBps;
                Double.isNaN(d);
                this.bitrateAccumulatorMax = d / 8.0d;
                this.bitrateAccumulator = 0.0d;
                this.bitrateObservationTimeMs = 0.0d;
                this.bitrateAdjustmentScaleExp = 0;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i7, i8);
                    createVideoFormat.setInteger("bitrate", this.targetBitrateBps);
                    createVideoFormat.setInteger("bitrate-mode", 2);
                    createVideoFormat.setInteger("color-format", encoderProperties.colorFormat);
                    createVideoFormat.setInteger("frame-rate", this.targetFps);
                    createVideoFormat.setInteger("i-frame-interval", i11);
                    if (z) {
                        createVideoFormat.setInteger(Scopes.PROFILE, 8);
                        createVideoFormat.setInteger("level", 256);
                    }
                    Logging.d(TAG, "  Format: " + createVideoFormat);
                    this.mediaCodec = createByCodecName(encoderProperties.codecName);
                    this.type = videoCodecType2;
                    if (this.mediaCodec == null) {
                        Logging.e(TAG, "Can not create media encoder");
                        release();
                        return false;
                    }
                    this.mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                    if (z2) {
                        this.eglBase = new EglBase14(context, EglBase.CONFIG_RECORDABLE);
                        this.inputSurface = this.mediaCodec.createInputSurface();
                        this.eglBase.createSurface(this.inputSurface);
                        this.drawer = new GlRectDrawer();
                    }
                    this.mediaCodec.start();
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
                    return true;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "initEncode failed", e);
                    release();
                    return false;
                }
            } else {
                throw new RuntimeException("Can not find HW encoder for " + videoCodecType2);
            }
        } else {
            throw new RuntimeException("Forgot to release()?");
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkKeyFrameRequired(boolean r7, long r8) {
        /*
            r6 = this;
            r0 = 500(0x1f4, double:2.47E-321)
            long r8 = r8 + r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r0
            long r0 = r6.lastKeyFrameMs
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0010
            r6.lastKeyFrameMs = r8
        L_0x0010:
            r0 = 0
            if (r7 != 0) goto L_0x0022
            long r4 = r6.forcedKeyFrameMs
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0022
            long r1 = r6.lastKeyFrameMs
            long r1 = r1 + r4
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r7 != 0) goto L_0x0027
            if (r1 == 0) goto L_0x0049
        L_0x0027:
            if (r7 == 0) goto L_0x0031
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame request"
            tvi.webrtc.Logging.d(r7, r1)
            goto L_0x0038
        L_0x0031:
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame forced"
            tvi.webrtc.Logging.d(r7, r1)
        L_0x0038:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r1 = "request-sync"
            r7.putInt(r1, r0)
            android.media.MediaCodec r0 = r6.mediaCodec
            r0.setParameters(r7)
            r6.lastKeyFrameMs = r8
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.MediaCodecVideoEncoder.checkKeyFrameRequired(boolean, long):void");
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean encodeTexture(boolean z, int i, float[] fArr, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            this.drawer.drawOes(i, fArr, this.width, this.height, 0, 0, this.width, this.height);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j));
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeTexture failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean encodeFrame(long j, boolean z, VideoFrame videoFrame, int i, long j2) {
        long j3 = j2;
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j3);
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            if (buffer instanceof VideoFrame.TextureBuffer) {
                this.eglBase.makeCurrent();
                GLES20.glClear(16384);
                VideoFrameDrawer.drawTexture(this.drawer, (VideoFrame.TextureBuffer) buffer, new Matrix(), this.width, this.height, 0, 0, this.width, this.height);
                this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j3));
            } else {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                int i2 = (this.height + 1) / 2;
                ByteBuffer dataY = i420.getDataY();
                ByteBuffer dataU = i420.getDataU();
                ByteBuffer dataV = i420.getDataV();
                int strideY = i420.getStrideY();
                int strideU = i420.getStrideU();
                int strideV = i420.getStrideV();
                if (dataY.capacity() < this.height * strideY) {
                    throw new RuntimeException("Y-plane buffer size too small.");
                } else if (dataU.capacity() < strideU * i2) {
                    throw new RuntimeException("U-plane buffer size too small.");
                } else if (dataV.capacity() >= i2 * strideV) {
                    nativeFillInputBuffer(j, i, dataY, strideY, dataU, strideU, dataV, strideV);
                    i420.release();
                    this.mediaCodec.queueInputBuffer(i, 0, ((this.width * this.height) * 3) / 2, j2, 0);
                } else {
                    throw new RuntimeException("V-plane buffer size too small.");
                }
            }
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeFrame failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final AnonymousClass1CaughtException r0 = new Object() {
            Exception e;
        };
        boolean z = false;
        if (this.mediaCodec != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e2);
                        r0.e = e2;
                    }
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                    countDownLatch.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000)) {
                Logging.e(TAG, "Media encoder release timeout");
                z = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        if (z) {
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        } else if (r0.e == null) {
            Logging.d(TAG, "Java releaseEncoder done");
        } else {
            RuntimeException runtimeException = new RuntimeException(r0.e);
            runtimeException.setStackTrace(ThreadUtils.concatStackTraces(r0.e.getStackTrace(), runtimeException.getStackTrace()));
            throw runtimeException;
        }
    }

    @CalledByNativeUnchecked
    private boolean setRates(int i, int i2) {
        int i3;
        checkOnMediaCodecThread();
        int i4 = i * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d = (double) i4;
            Double.isNaN(d);
            this.bitrateAccumulatorMax = d / 8.0d;
            int i5 = this.targetBitrateBps;
            if (i5 > 0 && i4 < i5) {
                double d2 = this.bitrateAccumulator;
                Double.isNaN(d);
                double d3 = d2 * d;
                double d4 = (double) i5;
                Double.isNaN(d4);
                this.bitrateAccumulator = d3 / d4;
            }
        }
        this.targetBitrateBps = i4;
        this.targetFps = i2;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && (i3 = this.targetFps) > 0) {
            i4 = (this.targetBitrateBps * 30) / i3;
            Logging.v(TAG, "setRates: " + i + " -> " + (i4 / 1000) + " kbps. Fps: " + this.targetFps);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            Logging.v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            int i6 = this.bitrateAdjustmentScaleExp;
            if (i6 != 0) {
                double d5 = (double) i4;
                double bitrateScale = getBitrateScale(i6);
                Double.isNaN(d5);
                i4 = (int) (d5 * bitrateScale);
            }
        } else {
            Logging.v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i4);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public int getIndex() {
            return this.index;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public boolean isKeyFrame() {
            return this.isKeyFrame;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("OutputBufferInfo")
        public long getPresentationTimestampUs() {
            return this.presentationTimestampUs;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00ad A[Catch:{ IllegalStateException -> 0x0176 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0142 A[Catch:{ IllegalStateException -> 0x0176 }] */
    @tvi.webrtc.CalledByNativeUnchecked
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public tvi.webrtc.MediaCodecVideoEncoder.OutputBufferInfo dequeueOutputBuffer() {
        /*
            r10 = this;
            r10.checkOnMediaCodecThread()
            android.media.MediaCodec$BufferInfo r0 = new android.media.MediaCodec$BufferInfo     // Catch:{ IllegalStateException -> 0x0176 }
            r0.<init>()     // Catch:{ IllegalStateException -> 0x0176 }
            android.media.MediaCodec r1 = r10.mediaCodec     // Catch:{ IllegalStateException -> 0x0176 }
            r2 = 0
            int r1 = r1.dequeueOutputBuffer(r0, r2)     // Catch:{ IllegalStateException -> 0x0176 }
            r4 = 1
            r5 = 0
            if (r1 < 0) goto L_0x00aa
            int r6 = r0.flags     // Catch:{ IllegalStateException -> 0x0176 }
            r6 = r6 & 2
            if (r6 == 0) goto L_0x001c
            r6 = 1
            goto L_0x001d
        L_0x001c:
            r6 = 0
        L_0x001d:
            if (r6 == 0) goto L_0x00aa
            java.lang.String r6 = "MediaCodecVideoEncoder"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0176 }
            r7.<init>()     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r8 = "Config frame generated. Offset: "
            r7.append(r8)     // Catch:{ IllegalStateException -> 0x0176 }
            int r8 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            r7.append(r8)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r8 = ". Size: "
            r7.append(r8)     // Catch:{ IllegalStateException -> 0x0176 }
            int r8 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            r7.append(r8)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = r7.toString()     // Catch:{ IllegalStateException -> 0x0176 }
            tvi.webrtc.Logging.d(r6, r7)     // Catch:{ IllegalStateException -> 0x0176 }
            int r6 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocateDirect(r6)     // Catch:{ IllegalStateException -> 0x0176 }
            r10.configData = r6     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer[] r6 = r10.outputBuffers     // Catch:{ IllegalStateException -> 0x0176 }
            r6 = r6[r1]     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            r6.position(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer[] r6 = r10.outputBuffers     // Catch:{ IllegalStateException -> 0x0176 }
            r6 = r6[r1]     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            int r8 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r7 + r8
            r6.limit(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r6 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer[] r7 = r10.outputBuffers     // Catch:{ IllegalStateException -> 0x0176 }
            r7 = r7[r1]     // Catch:{ IllegalStateException -> 0x0176 }
            r6.put(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r6 = ""
            r7 = r6
            r6 = 0
        L_0x006b:
            int r8 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            r9 = 8
            if (r8 >= r9) goto L_0x0073
            int r9 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
        L_0x0073:
            if (r6 >= r9) goto L_0x0098
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0176 }
            r8.<init>()     // Catch:{ IllegalStateException -> 0x0176 }
            r8.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r7 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            byte r7 = r7.get(r6)     // Catch:{ IllegalStateException -> 0x0176 }
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r7 = java.lang.Integer.toHexString(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            r8.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = " "
            r8.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = r8.toString()     // Catch:{ IllegalStateException -> 0x0176 }
            int r6 = r6 + 1
            goto L_0x006b
        L_0x0098:
            java.lang.String r6 = "MediaCodecVideoEncoder"
            tvi.webrtc.Logging.d(r6, r7)     // Catch:{ IllegalStateException -> 0x0176 }
            android.media.MediaCodec r6 = r10.mediaCodec     // Catch:{ IllegalStateException -> 0x0176 }
            r6.releaseOutputBuffer(r1, r5)     // Catch:{ IllegalStateException -> 0x0176 }
            android.media.MediaCodec r1 = r10.mediaCodec     // Catch:{ IllegalStateException -> 0x0176 }
            int r1 = r1.dequeueOutputBuffer(r0, r2)     // Catch:{ IllegalStateException -> 0x0176 }
            r2 = r1
            goto L_0x00ab
        L_0x00aa:
            r2 = r1
        L_0x00ab:
            if (r2 < 0) goto L_0x0142
            java.nio.ByteBuffer[] r1 = r10.outputBuffers     // Catch:{ IllegalStateException -> 0x0176 }
            r1 = r1[r2]     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r1 = r1.duplicate()     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            r1.position(r3)     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            int r6 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r3 + r6
            r1.limit(r3)     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            r10.reportEncodedFrame(r3)     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r0.flags     // Catch:{ IllegalStateException -> 0x0176 }
            r3 = r3 & r4
            if (r3 == 0) goto L_0x00cd
            goto L_0x00ce
        L_0x00cd:
            r4 = 0
        L_0x00ce:
            if (r4 == 0) goto L_0x00d7
            java.lang.String r3 = "MediaCodecVideoEncoder"
            java.lang.String r6 = "Sync frame generated"
            tvi.webrtc.Logging.d(r3, r6)     // Catch:{ IllegalStateException -> 0x0176 }
        L_0x00d7:
            if (r4 == 0) goto L_0x0135
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r3 = r10.type     // Catch:{ IllegalStateException -> 0x0176 }
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r6 = tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType.VIDEO_CODEC_H264     // Catch:{ IllegalStateException -> 0x0176 }
            if (r3 != r6) goto L_0x0135
            java.lang.String r3 = "MediaCodecVideoEncoder"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0176 }
            r6.<init>()     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = "Appending config frame of size "
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r7 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r7.capacity()     // Catch:{ IllegalStateException -> 0x0176 }
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = " to output buffer with offset "
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r0.offset     // Catch:{ IllegalStateException -> 0x0176 }
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r7 = ", size "
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            int r7 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            r6.append(r7)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r6 = r6.toString()     // Catch:{ IllegalStateException -> 0x0176 }
            tvi.webrtc.Logging.d(r3, r6)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r3 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r3.capacity()     // Catch:{ IllegalStateException -> 0x0176 }
            int r6 = r0.size     // Catch:{ IllegalStateException -> 0x0176 }
            int r3 = r3 + r6
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocateDirect(r3)     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r6 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            r6.rewind()     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r6 = r10.configData     // Catch:{ IllegalStateException -> 0x0176 }
            r3.put(r6)     // Catch:{ IllegalStateException -> 0x0176 }
            r3.put(r1)     // Catch:{ IllegalStateException -> 0x0176 }
            r3.position(r5)     // Catch:{ IllegalStateException -> 0x0176 }
            tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo r7 = new tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo     // Catch:{ IllegalStateException -> 0x0176 }
            long r5 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x0176 }
            r1 = r7
            r1.<init>(r2, r3, r4, r5)     // Catch:{ IllegalStateException -> 0x0176 }
            return r7
        L_0x0135:
            tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo r7 = new tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer r3 = r1.slice()     // Catch:{ IllegalStateException -> 0x0176 }
            long r5 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x0176 }
            r1 = r7
            r1.<init>(r2, r3, r4, r5)     // Catch:{ IllegalStateException -> 0x0176 }
            return r7
        L_0x0142:
            r0 = -3
            if (r2 != r0) goto L_0x0152
            android.media.MediaCodec r0 = r10.mediaCodec     // Catch:{ IllegalStateException -> 0x0176 }
            java.nio.ByteBuffer[] r0 = r0.getOutputBuffers()     // Catch:{ IllegalStateException -> 0x0176 }
            r10.outputBuffers = r0     // Catch:{ IllegalStateException -> 0x0176 }
            tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo r0 = r10.dequeueOutputBuffer()     // Catch:{ IllegalStateException -> 0x0176 }
            return r0
        L_0x0152:
            r0 = -2
            if (r2 != r0) goto L_0x015a
            tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo r0 = r10.dequeueOutputBuffer()     // Catch:{ IllegalStateException -> 0x0176 }
            return r0
        L_0x015a:
            r0 = -1
            if (r2 != r0) goto L_0x015f
            r0 = 0
            return r0
        L_0x015f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0176 }
            r1.<init>()     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r3 = "dequeueOutputBuffer: "
            r1.append(r3)     // Catch:{ IllegalStateException -> 0x0176 }
            r1.append(r2)     // Catch:{ IllegalStateException -> 0x0176 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalStateException -> 0x0176 }
            r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x0176 }
            throw r0     // Catch:{ IllegalStateException -> 0x0176 }
        L_0x0176:
            r0 = move-exception
            java.lang.String r1 = "MediaCodecVideoEncoder"
            java.lang.String r2 = "dequeueOutputBuffer failed"
            tvi.webrtc.Logging.e(r1, r2, r0)
            tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo r0 = new tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo
            r4 = -1
            r5 = 0
            r6 = 0
            r7 = -1
            r3 = r0
            r3.<init>(r4, r5, r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.MediaCodecVideoEncoder.dequeueOutputBuffer():tvi.webrtc.MediaCodecVideoEncoder$OutputBufferInfo");
    }

    private double getBitrateScale(int i) {
        double d = (double) i;
        Double.isNaN(d);
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, d / 20.0d);
    }

    private void reportEncodedFrame(int i) {
        if (this.targetFps != 0 && this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d = (double) this.targetBitrateBps;
            int i2 = this.targetFps;
            double d2 = (double) i2;
            Double.isNaN(d2);
            Double.isNaN(d);
            double d3 = d / (d2 * 8.0d);
            double d4 = this.bitrateAccumulator;
            double d5 = (double) i;
            Double.isNaN(d5);
            this.bitrateAccumulator = d4 + (d5 - d3);
            double d6 = this.bitrateObservationTimeMs;
            double d7 = (double) i2;
            Double.isNaN(d7);
            this.bitrateObservationTimeMs = d6 + (1000.0d / d7);
            double d8 = this.bitrateAccumulatorMax * BITRATE_CORRECTION_SEC;
            this.bitrateAccumulator = Math.min(this.bitrateAccumulator, d8);
            this.bitrateAccumulator = Math.max(this.bitrateAccumulator, -d8);
            if (this.bitrateObservationTimeMs > 3000.0d) {
                Logging.d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
                double d9 = this.bitrateAccumulator;
                double d10 = this.bitrateAccumulatorMax;
                boolean z = true;
                if (d9 > d10) {
                    this.bitrateAdjustmentScaleExp -= (int) ((d9 / d10) + 0.5d);
                    this.bitrateAccumulator = d10;
                } else if (d9 < (-d10)) {
                    this.bitrateAdjustmentScaleExp += (int) (((-d9) / d10) + 0.5d);
                    this.bitrateAccumulator = -d10;
                } else {
                    z = false;
                }
                if (z) {
                    this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    Logging.d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                    setRates(this.targetBitrateBps / 1000, this.targetFps);
                }
                this.bitrateObservationTimeMs = 0.0d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNativeUnchecked
    public boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public int getColorFormat() {
        return this.colorFormat;
    }

    @CalledByNative
    static boolean isTextureBuffer(VideoFrame.Buffer buffer) {
        return buffer instanceof VideoFrame.TextureBuffer;
    }
}
