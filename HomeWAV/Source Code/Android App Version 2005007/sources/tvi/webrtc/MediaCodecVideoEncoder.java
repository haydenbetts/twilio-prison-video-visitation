package tvi.webrtc;

import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import kotlin.UByte;
import tvi.webrtc.VideoFrame;

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
    private static final MediaCodecProperties exynosH264HighProfileHwProperties;
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties;
    private static final MediaCodecProperties[] h264HighProfileHwList;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties mediatekH264HwProperties = new MediaCodecProperties("OMX.MTK.", 27, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties;
    @Nullable
    private static MediaCodecVideoEncoder runningInstance;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private static final MediaCodecProperties[] vp9HwList;
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

        static VideoCodecType fromNativeIndex(int i) {
            return values()[i];
        }
    }

    static {
        MediaCodecProperties mediaCodecProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
        qcomVp9HwProperties = mediaCodecProperties;
        MediaCodecProperties mediaCodecProperties2 = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
        exynosVp9HwProperties = mediaCodecProperties2;
        vp9HwList = new MediaCodecProperties[]{mediaCodecProperties, mediaCodecProperties2};
        MediaCodecProperties mediaCodecProperties3 = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
        exynosH264HighProfileHwProperties = mediaCodecProperties3;
        h264HighProfileHwList = new MediaCodecProperties[]{mediaCodecProperties3};
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
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/avc");
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") && findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList) != null;
    }

    @Nullable
    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8")) {
            return null;
        }
        return findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList);
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") && findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedColorList) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoder("video/avc", h264HwList(), supportedColorList) != null;
    }

    public static boolean isH264HighProfileHwSupported() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoder("video/avc", h264HighProfileHwList, supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") && findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") && findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedSurfaceColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoder("video/avc", h264HwList(), supportedSurfaceColorList) != null;
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
        String str3 = str;
        MediaCodecProperties[] mediaCodecPropertiesArr2 = mediaCodecPropertiesArr;
        int[] iArr2 = iArr;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!str3.equals("video/avc") || !Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
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
                        } else if (supportedTypes[i2].equals(str3)) {
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
                        int length2 = mediaCodecPropertiesArr2.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length2) {
                                z = false;
                                break;
                            }
                            MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr2[i3];
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
                                MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str3);
                                for (int i4 : capabilitiesForType.colorFormats) {
                                    Logging.v(TAG, "   Color: 0x" + Integer.toHexString(i4));
                                }
                                for (int i5 : iArr2) {
                                    for (int i6 : capabilitiesForType.colorFormats) {
                                        if (i6 == i5) {
                                            Logging.d(TAG, "Found target encoder for mime " + str3 + " : " + str2 + ". Color: 0x" + Integer.toHexString(i6) + ". Bitrate adjustment: " + bitrateAdjustmentType2);
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
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0236  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean initEncode(tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType r18, int r19, int r20, int r21, int r22, int r23, @javax.annotation.Nullable tvi.webrtc.EglBase14.Context r24) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            if (r7 == 0) goto L_0x0014
            r10 = 1
            goto L_0x0015
        L_0x0014:
            r10 = 0
        L_0x0015:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Java initEncode: "
            r11.append(r12)
            r11.append(r0)
            java.lang.String r12 = ". Profile: "
            r11.append(r12)
            r11.append(r2)
            java.lang.String r12 = " : "
            r11.append(r12)
            r11.append(r3)
            java.lang.String r12 = " x "
            r11.append(r12)
            r11.append(r4)
            java.lang.String r12 = ". @ "
            r11.append(r12)
            r11.append(r5)
            java.lang.String r12 = " kbps. Fps: "
            r11.append(r12)
            r11.append(r6)
            java.lang.String r12 = ". Encode from texture : "
            r11.append(r12)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "MediaCodecVideoEncoder"
            tvi.webrtc.Logging.d(r12, r11)
            r1.profile = r2
            r1.width = r3
            r1.height = r4
            java.lang.Thread r11 = r1.mediaCodecThread
            if (r11 != 0) goto L_0x024d
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r11 = tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType.VIDEO_CODEC_VP8
            java.lang.String r14 = "video/x-vnd.on2.vp9"
            java.lang.String r15 = "video/x-vnd.on2.vp8"
            java.lang.String r13 = "video/avc"
            if (r0 != r11) goto L_0x0083
            tvi.webrtc.MediaCodecVideoEncoder$MediaCodecProperties[] r2 = vp8HwList()
            if (r10 == 0) goto L_0x0078
            int[] r11 = supportedSurfaceColorList
            goto L_0x007a
        L_0x0078:
            int[] r11 = supportedColorList
        L_0x007a:
            tvi.webrtc.MediaCodecVideoEncoder$EncoderProperties r2 = findHwEncoder(r15, r2, r11)
            r14 = r15
        L_0x007f:
            r11 = 0
            r13 = 100
            goto L_0x00db
        L_0x0083:
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r11 = tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType.VIDEO_CODEC_VP9
            if (r0 != r11) goto L_0x0095
            tvi.webrtc.MediaCodecVideoEncoder$MediaCodecProperties[] r2 = vp9HwList
            if (r10 == 0) goto L_0x008e
            int[] r11 = supportedSurfaceColorList
            goto L_0x0090
        L_0x008e:
            int[] r11 = supportedColorList
        L_0x0090:
            tvi.webrtc.MediaCodecVideoEncoder$EncoderProperties r2 = findHwEncoder(r14, r2, r11)
            goto L_0x007f
        L_0x0095:
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r11 = tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType.VIDEO_CODEC_H264
            if (r0 != r11) goto L_0x00d7
            tvi.webrtc.MediaCodecVideoEncoder$MediaCodecProperties[] r11 = h264HwList()
            if (r10 == 0) goto L_0x00a2
            int[] r14 = supportedSurfaceColorList
            goto L_0x00a4
        L_0x00a2:
            int[] r14 = supportedColorList
        L_0x00a4:
            tvi.webrtc.MediaCodecVideoEncoder$EncoderProperties r11 = findHwEncoder(r13, r11, r14)
            tvi.webrtc.MediaCodecVideoEncoder$H264Profile r14 = tvi.webrtc.MediaCodecVideoEncoder.H264Profile.CONSTRAINED_HIGH
            int r14 = r14.getValue()
            if (r2 != r14) goto L_0x00cb
            tvi.webrtc.MediaCodecVideoEncoder$MediaCodecProperties[] r2 = h264HighProfileHwList
            if (r10 == 0) goto L_0x00b7
            int[] r14 = supportedSurfaceColorList
            goto L_0x00b9
        L_0x00b7:
            int[] r14 = supportedColorList
        L_0x00b9:
            tvi.webrtc.MediaCodecVideoEncoder$EncoderProperties r2 = findHwEncoder(r13, r2, r14)
            if (r2 == 0) goto L_0x00c6
            java.lang.String r2 = "High profile H.264 encoder supported."
            tvi.webrtc.Logging.d(r12, r2)
            r2 = 1
            goto L_0x00cc
        L_0x00c6:
            java.lang.String r2 = "High profile H.264 encoder requested, but not supported. Use baseline."
            tvi.webrtc.Logging.d(r12, r2)
        L_0x00cb:
            r2 = 0
        L_0x00cc:
            r14 = 20
            r14 = r13
            r13 = 20
            r16 = r11
            r11 = r2
            r2 = r16
            goto L_0x00db
        L_0x00d7:
            r2 = 0
            r11 = 0
            r13 = 0
            r14 = 0
        L_0x00db:
            if (r2 == 0) goto L_0x0236
            runningInstance = r1
            int r15 = r2.colorFormat
            r1.colorFormat = r15
            tvi.webrtc.MediaCodecVideoEncoder$BitrateAdjustmentType r15 = r2.bitrateAdjustmentType
            r1.bitrateAdjustmentType = r15
            tvi.webrtc.MediaCodecVideoEncoder$BitrateAdjustmentType r8 = tvi.webrtc.MediaCodecVideoEncoder.BitrateAdjustmentType.FRAMERATE_ADJUSTMENT
            r9 = 30
            if (r15 != r8) goto L_0x00ee
            goto L_0x00f2
        L_0x00ee:
            int r9 = java.lang.Math.min(r6, r9)
        L_0x00f2:
            r6 = 0
            r1.forcedKeyFrameMs = r6
            r6 = -1
            r1.lastKeyFrameMs = r6
            tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType r6 = tvi.webrtc.MediaCodecVideoEncoder.VideoCodecType.VIDEO_CODEC_VP8
            if (r0 != r6) goto L_0x0131
            java.lang.String r6 = r2.codecName
            tvi.webrtc.MediaCodecVideoEncoder$MediaCodecProperties r7 = qcomVp8HwProperties
            java.lang.String r7 = r7.codecPrefix
            boolean r6 = r6.startsWith(r7)
            if (r6 == 0) goto L_0x0131
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 21
            r8 = r10
            r19 = r11
            r10 = 15000(0x3a98, double:7.411E-320)
            if (r6 == r7) goto L_0x012e
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 22
            if (r6 != r7) goto L_0x011c
            goto L_0x012e
        L_0x011c:
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 23
            if (r6 != r7) goto L_0x0127
            r6 = 20000(0x4e20, double:9.8813E-320)
            r1.forcedKeyFrameMs = r6
            goto L_0x0134
        L_0x0127:
            int r6 = android.os.Build.VERSION.SDK_INT
            if (r6 <= r7) goto L_0x0134
            r1.forcedKeyFrameMs = r10
            goto L_0x0134
        L_0x012e:
            r1.forcedKeyFrameMs = r10
            goto L_0x0134
        L_0x0131:
            r8 = r10
            r19 = r11
        L_0x0134:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Color format: "
            r6.append(r7)
            int r7 = r1.colorFormat
            r6.append(r7)
            java.lang.String r7 = ". Bitrate adjustment: "
            r6.append(r7)
            tvi.webrtc.MediaCodecVideoEncoder$BitrateAdjustmentType r7 = r1.bitrateAdjustmentType
            r6.append(r7)
            java.lang.String r7 = ". Key frame interval: "
            r6.append(r7)
            long r10 = r1.forcedKeyFrameMs
            r6.append(r10)
            java.lang.String r7 = " . Initial fps: "
            r6.append(r7)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            tvi.webrtc.Logging.d(r12, r6)
            int r5 = r5 * 1000
            r1.targetBitrateBps = r5
            r1.targetFps = r9
            double r5 = (double) r5
            r9 = 4620693217682128896(0x4020000000000000, double:8.0)
            double r5 = r5 / r9
            r1.bitrateAccumulatorMax = r5
            r5 = 0
            r1.bitrateAccumulator = r5
            r1.bitrateObservationTimeMs = r5
            r5 = 0
            r1.bitrateAdjustmentScaleExp = r5
            java.lang.Thread r5 = java.lang.Thread.currentThread()
            r1.mediaCodecThread = r5
            android.media.MediaFormat r3 = android.media.MediaFormat.createVideoFormat(r14, r3, r4)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "bitrate"
            int r5 = r1.targetBitrateBps     // Catch:{ IllegalStateException -> 0x022b }
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "bitrate-mode"
            r5 = 2
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "color-format"
            int r5 = r2.colorFormat     // Catch:{ IllegalStateException -> 0x022b }
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "frame-rate"
            int r5 = r1.targetFps     // Catch:{ IllegalStateException -> 0x022b }
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "i-frame-interval"
            r3.setInteger(r4, r13)     // Catch:{ IllegalStateException -> 0x022b }
            if (r19 == 0) goto L_0x01b5
            java.lang.String r4 = "profile"
            r5 = 8
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = "level"
            r5 = 256(0x100, float:3.59E-43)
            r3.setInteger(r4, r5)     // Catch:{ IllegalStateException -> 0x022b }
        L_0x01b5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x022b }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r5 = "  Format: "
            r4.append(r5)     // Catch:{ IllegalStateException -> 0x022b }
            r4.append(r3)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r4 = r4.toString()     // Catch:{ IllegalStateException -> 0x022b }
            tvi.webrtc.Logging.d(r12, r4)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r2 = r2.codecName     // Catch:{ IllegalStateException -> 0x022b }
            android.media.MediaCodec r2 = createByCodecName(r2)     // Catch:{ IllegalStateException -> 0x022b }
            r1.mediaCodec = r2     // Catch:{ IllegalStateException -> 0x022b }
            r1.type = r0     // Catch:{ IllegalStateException -> 0x022b }
            if (r2 != 0) goto L_0x01df
            java.lang.String r0 = "Can not create media encoder"
            tvi.webrtc.Logging.e(r12, r0)     // Catch:{ IllegalStateException -> 0x022b }
            r17.release()     // Catch:{ IllegalStateException -> 0x022b }
            r2 = 0
            return r2
        L_0x01df:
            r0 = 0
            r4 = 1
            r2.configure(r3, r0, r0, r4)     // Catch:{ IllegalStateException -> 0x022b }
            if (r8 == 0) goto L_0x0205
            tvi.webrtc.EglBase14 r0 = new tvi.webrtc.EglBase14     // Catch:{ IllegalStateException -> 0x022b }
            int[] r2 = tvi.webrtc.EglBase.CONFIG_RECORDABLE     // Catch:{ IllegalStateException -> 0x022b }
            r3 = r24
            r0.<init>(r3, r2)     // Catch:{ IllegalStateException -> 0x022b }
            r1.eglBase = r0     // Catch:{ IllegalStateException -> 0x022b }
            android.media.MediaCodec r0 = r1.mediaCodec     // Catch:{ IllegalStateException -> 0x022b }
            android.view.Surface r0 = r0.createInputSurface()     // Catch:{ IllegalStateException -> 0x022b }
            r1.inputSurface = r0     // Catch:{ IllegalStateException -> 0x022b }
            tvi.webrtc.EglBase14 r2 = r1.eglBase     // Catch:{ IllegalStateException -> 0x022b }
            r2.createSurface((android.view.Surface) r0)     // Catch:{ IllegalStateException -> 0x022b }
            tvi.webrtc.GlRectDrawer r0 = new tvi.webrtc.GlRectDrawer     // Catch:{ IllegalStateException -> 0x022b }
            r0.<init>()     // Catch:{ IllegalStateException -> 0x022b }
            r1.drawer = r0     // Catch:{ IllegalStateException -> 0x022b }
        L_0x0205:
            android.media.MediaCodec r0 = r1.mediaCodec     // Catch:{ IllegalStateException -> 0x022b }
            r0.start()     // Catch:{ IllegalStateException -> 0x022b }
            android.media.MediaCodec r0 = r1.mediaCodec     // Catch:{ IllegalStateException -> 0x022b }
            java.nio.ByteBuffer[] r0 = r0.getOutputBuffers()     // Catch:{ IllegalStateException -> 0x022b }
            r1.outputBuffers = r0     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x022b }
            r0.<init>()     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r2 = "Output buffers: "
            r0.append(r2)     // Catch:{ IllegalStateException -> 0x022b }
            java.nio.ByteBuffer[] r2 = r1.outputBuffers     // Catch:{ IllegalStateException -> 0x022b }
            int r2 = r2.length     // Catch:{ IllegalStateException -> 0x022b }
            r0.append(r2)     // Catch:{ IllegalStateException -> 0x022b }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalStateException -> 0x022b }
            tvi.webrtc.Logging.d(r12, r0)     // Catch:{ IllegalStateException -> 0x022b }
            r0 = 1
            return r0
        L_0x022b:
            r0 = move-exception
            java.lang.String r2 = "initEncode failed"
            tvi.webrtc.Logging.e(r12, r2, r0)
            r17.release()
            r2 = 0
            return r2
        L_0x0236:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Can not find HW encoder for "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x024d:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r2 = "Forgot to release()?"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.MediaCodecVideoEncoder.initEncode(tvi.webrtc.MediaCodecVideoEncoder$VideoCodecType, int, int, int, int, int, tvi.webrtc.EglBase14$Context):boolean");
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0031  */
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
            if (r1 == 0) goto L_0x0047
        L_0x0027:
            java.lang.String r1 = "MediaCodecVideoEncoder"
            if (r7 == 0) goto L_0x0031
            java.lang.String r7 = "Sync frame request"
            tvi.webrtc.Logging.d(r1, r7)
            goto L_0x0036
        L_0x0031:
            java.lang.String r7 = "Sync frame forced"
            tvi.webrtc.Logging.d(r1, r7)
        L_0x0036:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r1 = "request-sync"
            r7.putInt(r1, r0)
            android.media.MediaCodec r0 = r6.mediaCodec
            r0.setParameters(r7)
            r6.lastKeyFrameMs = r8
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.MediaCodecVideoEncoder.checkKeyFrameRequired(boolean, long):void");
    }

    /* access modifiers changed from: package-private */
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
    public boolean encodeTexture(boolean z, int i, float[] fArr, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            GlRectDrawer glRectDrawer = this.drawer;
            int i2 = this.width;
            int i3 = this.height;
            glRectDrawer.drawOes(i, fArr, i2, i3, 0, 0, i2, i3);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j));
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeTexture failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean encodeFrame(long j, boolean z, VideoFrame videoFrame, int i, long j2) {
        long j3 = j2;
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j3);
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            if (buffer instanceof VideoFrame.TextureBuffer) {
                this.eglBase.makeCurrent();
                GLES20.glClear(16384);
                GlRectDrawer glRectDrawer = this.drawer;
                Matrix matrix = new Matrix();
                int i2 = this.width;
                int i3 = this.height;
                VideoFrameDrawer.drawTexture(glRectDrawer, (VideoFrame.TextureBuffer) buffer, matrix, i2, i3, 0, 0, i2, i3);
                this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j3));
            } else {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                int i4 = (this.height + 1) / 2;
                ByteBuffer dataY = i420.getDataY();
                ByteBuffer dataU = i420.getDataU();
                ByteBuffer dataV = i420.getDataV();
                int strideY = i420.getStrideY();
                int strideU = i420.getStrideU();
                int strideV = i420.getStrideV();
                if (dataY.capacity() < this.height * strideY) {
                    throw new RuntimeException("Y-plane buffer size too small.");
                } else if (dataU.capacity() < strideU * i4) {
                    throw new RuntimeException("U-plane buffer size too small.");
                } else if (dataV.capacity() >= i4 * strideV) {
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
    public void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final AnonymousClass1CaughtException r1 = new Object() {
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
                        r1.e = e2;
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
        } else if (r1.e == null) {
            Logging.d(TAG, "Java releaseEncoder done");
        } else {
            RuntimeException runtimeException = new RuntimeException(r1.e);
            runtimeException.setStackTrace(ThreadUtils.concatStackTraces(r1.e.getStackTrace(), runtimeException.getStackTrace()));
            throw runtimeException;
        }
    }

    private boolean setRates(int i, int i2) {
        int i3;
        checkOnMediaCodecThread();
        int i4 = i * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d = (double) i4;
            this.bitrateAccumulatorMax = d / 8.0d;
            int i5 = this.targetBitrateBps;
            if (i5 > 0 && i4 < i5) {
                this.bitrateAccumulator = (this.bitrateAccumulator * d) / ((double) i5);
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
                i4 = (int) (((double) i4) * getBitrateScale(i6));
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
        public int getIndex() {
            return this.index;
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        /* access modifiers changed from: package-private */
        public boolean isKeyFrame() {
            return this.isKeyFrame;
        }

        /* access modifiers changed from: package-private */
        public long getPresentationTimestampUs() {
            return this.presentationTimestampUs;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            boolean z = true;
            if (dequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[dequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[dequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[dequeueOutputBuffer]);
                    String str = "";
                    int i = 0;
                    while (true) {
                        int i2 = 8;
                        if (bufferInfo.size < 8) {
                            i2 = bufferInfo.size;
                        }
                        if (i >= i2) {
                            break;
                        }
                        str = str + Integer.toHexString(this.configData.get(i) & UByte.MAX_VALUE) + " ";
                        i++;
                    }
                    Logging.d(TAG, str);
                    this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                    dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                }
            }
            int i3 = dequeueOutputBuffer;
            if (i3 >= 0) {
                ByteBuffer duplicate = this.outputBuffers[i3].duplicate();
                duplicate.position(bufferInfo.offset);
                duplicate.limit(bufferInfo.offset + bufferInfo.size);
                reportEncodedFrame(bufferInfo.size);
                if ((bufferInfo.flags & 1) == 0) {
                    z = false;
                }
                if (z) {
                    Logging.d(TAG, "Sync frame generated");
                }
                if (!z || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                    return new OutputBufferInfo(i3, duplicate.slice(), z, bufferInfo.presentationTimeUs);
                }
                Logging.d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
                this.configData.rewind();
                allocateDirect.put(this.configData);
                allocateDirect.put(duplicate);
                allocateDirect.position(0);
                return new OutputBufferInfo(i3, allocateDirect, z, bufferInfo.presentationTimeUs);
            } else if (i3 == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (i3 == -2) {
                return dequeueOutputBuffer();
            } else {
                if (i3 == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + i3);
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, (ByteBuffer) null, false, -1);
        }
    }

    private double getBitrateScale(int i) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, ((double) i) / 20.0d);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reportEncodedFrame(int r12) {
        /*
            r11 = this;
            int r0 = r11.targetFps
            if (r0 == 0) goto L_0x00e5
            tvi.webrtc.MediaCodecVideoEncoder$BitrateAdjustmentType r0 = r11.bitrateAdjustmentType
            tvi.webrtc.MediaCodecVideoEncoder$BitrateAdjustmentType r1 = tvi.webrtc.MediaCodecVideoEncoder.BitrateAdjustmentType.DYNAMIC_ADJUSTMENT
            if (r0 == r1) goto L_0x000c
            goto L_0x00e5
        L_0x000c:
            int r0 = r11.targetBitrateBps
            double r0 = (double) r0
            r2 = 4620693217682128896(0x4020000000000000, double:8.0)
            int r4 = r11.targetFps
            double r5 = (double) r4
            double r5 = r5 * r2
            double r0 = r0 / r5
            double r2 = r11.bitrateAccumulator
            double r5 = (double) r12
            double r5 = r5 - r0
            double r2 = r2 + r5
            r11.bitrateAccumulator = r2
            double r0 = r11.bitrateObservationTimeMs
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r7 = (double) r4
            double r5 = r5 / r7
            double r0 = r0 + r5
            r11.bitrateObservationTimeMs = r0
            r0 = 4613937818241073152(0x4008000000000000, double:3.0)
            double r4 = r11.bitrateAccumulatorMax
            double r4 = r4 * r0
            double r0 = java.lang.Math.min(r2, r4)
            r11.bitrateAccumulator = r0
            double r2 = -r4
            double r0 = java.lang.Math.max(r0, r2)
            r11.bitrateAccumulator = r0
            double r0 = r11.bitrateObservationTimeMs
            r2 = 4658815484840378368(0x40a7700000000000, double:3000.0)
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 <= 0) goto L_0x00e5
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Acc: "
            r12.append(r0)
            double r0 = r11.bitrateAccumulator
            int r0 = (int) r0
            r12.append(r0)
            java.lang.String r0 = ". Max: "
            r12.append(r0)
            double r0 = r11.bitrateAccumulatorMax
            int r0 = (int) r0
            r12.append(r0)
            java.lang.String r0 = ". ExpScale: "
            r12.append(r0)
            int r0 = r11.bitrateAdjustmentScaleExp
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = "MediaCodecVideoEncoder"
            tvi.webrtc.Logging.d(r0, r12)
            r12 = 0
            double r1 = r11.bitrateAccumulator
            double r3 = r11.bitrateAccumulatorMax
            r5 = 1
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 <= 0) goto L_0x008e
            double r1 = r1 / r3
            double r1 = r1 + r6
            int r12 = (int) r1
            int r1 = r11.bitrateAdjustmentScaleExp
            int r1 = r1 - r12
            r11.bitrateAdjustmentScaleExp = r1
            r11.bitrateAccumulator = r3
        L_0x008c:
            r12 = 1
            goto L_0x00a0
        L_0x008e:
            double r8 = -r3
            int r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x00a0
            double r1 = -r1
            double r1 = r1 / r3
            double r1 = r1 + r6
            int r12 = (int) r1
            int r1 = r11.bitrateAdjustmentScaleExp
            int r1 = r1 + r12
            r11.bitrateAdjustmentScaleExp = r1
            double r1 = -r3
            r11.bitrateAccumulator = r1
            goto L_0x008c
        L_0x00a0:
            if (r12 == 0) goto L_0x00e1
            int r12 = r11.bitrateAdjustmentScaleExp
            r1 = 20
            int r12 = java.lang.Math.min(r12, r1)
            r11.bitrateAdjustmentScaleExp = r12
            r1 = -20
            int r12 = java.lang.Math.max(r12, r1)
            r11.bitrateAdjustmentScaleExp = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r1 = "Adjusting bitrate scale to "
            r12.append(r1)
            int r1 = r11.bitrateAdjustmentScaleExp
            r12.append(r1)
            java.lang.String r1 = ". Value: "
            r12.append(r1)
            int r1 = r11.bitrateAdjustmentScaleExp
            double r1 = r11.getBitrateScale(r1)
            r12.append(r1)
            java.lang.String r12 = r12.toString()
            tvi.webrtc.Logging.d(r0, r12)
            int r12 = r11.targetBitrateBps
            int r12 = r12 / 1000
            int r0 = r11.targetFps
            r11.setRates(r12, r0)
        L_0x00e1:
            r0 = 0
            r11.bitrateObservationTimeMs = r0
        L_0x00e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.MediaCodecVideoEncoder.reportEncodedFrame(int):void");
    }

    /* access modifiers changed from: package-private */
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
    public int getColorFormat() {
        return this.colorFormat;
    }

    static boolean isTextureBuffer(VideoFrame.Buffer buffer) {
        return buffer instanceof VideoFrame.TextureBuffer;
    }
}
