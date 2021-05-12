package tvi.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase;
import tvi.webrtc.EglBase14;

@JNINamespace("webrtc::jni")
public class HardwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final List<String> H264_HW_EXCEPTION_MODELS = Arrays.asList(new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"});
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "HardwareVideoEncoderFactory";
    private final boolean enableH264HighProfile;
    private final boolean enableIntelVp8Encoder;
    private final boolean fallbackToSoftware;
    @Nullable
    private final EglBase14.Context sharedContext;

    private static native boolean nativeIsSameH264Profile(Map<String, String> map, Map<String, String> map2);

    public HardwareVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2) {
        this(context, z, z2, true);
    }

    HardwareVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2, boolean z3) {
        if (context instanceof EglBase14.Context) {
            this.sharedContext = (EglBase14.Context) context;
        } else {
            Logging.w(TAG, "No shared EglBase.Context.  Encoders will not use texture mode.");
            this.sharedContext = null;
        }
        this.enableIntelVp8Encoder = z;
        this.enableH264HighProfile = z2;
        this.fallbackToSoftware = z3;
    }

    @Deprecated
    public HardwareVideoEncoderFactory(boolean z, boolean z2) {
        this((EglBase.Context) null, z, z2);
    }

    @Nullable
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecType valueOf = VideoCodecType.valueOf(videoCodecInfo.name);
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType != null) {
            String name = findCodecForType.getName();
            String mimeType = valueOf.mimeType();
            Integer selectColorFormat = MediaCodecUtils.selectColorFormat(MediaCodecUtils.TEXTURE_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType));
            Integer selectColorFormat2 = MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType));
            if (valueOf == VideoCodecType.H264) {
                boolean z = true;
                if (!nativeIsSameH264Profile(videoCodecInfo.params, getCodecProperties(valueOf, true)) || !isH264HighProfileSupported(findCodecForType)) {
                    z = false;
                }
                boolean nativeIsSameH264Profile = nativeIsSameH264Profile(videoCodecInfo.params, getCodecProperties(valueOf, false));
                if (!z && !nativeIsSameH264Profile) {
                    return null;
                }
            }
            return new HardwareVideoEncoder(name, valueOf, selectColorFormat, selectColorFormat2, videoCodecInfo.params, getKeyFrameIntervalSec(valueOf), getForcedKeyFrameIntervalMs(valueOf, name), createBitrateAdjuster(valueOf, name), this.sharedContext);
        } else if (this.fallbackToSoftware) {
            return new SoftwareVideoEncoderFactory().createEncoder(videoCodecInfo);
        } else {
            return null;
        }
    }

    public VideoCodecInfo[] getSupportedCodecs() {
        ArrayList arrayList = new ArrayList();
        for (VideoCodecType videoCodecType : new VideoCodecType[]{VideoCodecType.VP8, VideoCodecType.VP9, VideoCodecType.H264}) {
            MediaCodecInfo findCodecForType = findCodecForType(videoCodecType);
            if (findCodecForType != null) {
                String name = videoCodecType.name();
                if (videoCodecType == VideoCodecType.H264 && isH264HighProfileSupported(findCodecForType)) {
                    arrayList.add(new VideoCodecInfo(name, getCodecProperties(videoCodecType, true)));
                }
                arrayList.add(new VideoCodecInfo(name, getCodecProperties(videoCodecType, false)));
            }
        }
        if (this.fallbackToSoftware) {
            for (VideoCodecInfo videoCodecInfo : SoftwareVideoEncoderFactory.supportedCodecs()) {
                if (!arrayList.contains(videoCodecInfo)) {
                    arrayList.add(videoCodecInfo);
                }
            }
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }

    @Nullable
    private MediaCodecInfo findCodecForType(VideoCodecType videoCodecType) {
        int i = 0;
        while (true) {
            MediaCodecInfo mediaCodecInfo = null;
            if (i >= MediaCodecList.getCodecCount()) {
                return null;
            }
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve encoder codec info", e);
            }
            if (mediaCodecInfo != null && mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecType)) {
                return mediaCodecInfo;
            }
            i++;
        }
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecType.mimeType())) != null) {
            return isHardwareSupportedInCurrentSdk(mediaCodecInfo, videoCodecType);
        }
        return false;
    }

    private boolean isHardwareSupportedInCurrentSdk(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        switch (videoCodecType) {
            case VP8:
                return isHardwareSupportedInCurrentSdkVp8(mediaCodecInfo);
            case VP9:
                return isHardwareSupportedInCurrentSdkVp9(mediaCodecInfo);
            case H264:
                return isHardwareSupportedInCurrentSdkH264(mediaCodecInfo);
            default:
                return false;
        }
    }

    private boolean isHardwareSupportedInCurrentSdkVp8(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.qcom.") && Build.VERSION.SDK_INT >= 19) || (name.startsWith("OMX.Exynos.") && Build.VERSION.SDK_INT >= 23) || (name.startsWith("OMX.Intel.") && Build.VERSION.SDK_INT >= 21 && this.enableIntelVp8Encoder);
    }

    private boolean isHardwareSupportedInCurrentSdkVp9(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) && Build.VERSION.SDK_INT >= 24;
    }

    private boolean isHardwareSupportedInCurrentSdkH264(MediaCodecInfo mediaCodecInfo) {
        if (H264_HW_EXCEPTION_MODELS.contains(Build.MODEL)) {
            return false;
        }
        String name = mediaCodecInfo.getName();
        if ((!name.startsWith("OMX.qcom.") || Build.VERSION.SDK_INT < 19) && (!name.startsWith("OMX.Exynos.") || Build.VERSION.SDK_INT < 21)) {
            return false;
        }
        return true;
    }

    private int getKeyFrameIntervalSec(VideoCodecType videoCodecType) {
        switch (videoCodecType) {
            case VP8:
            case VP9:
                return 100;
            case H264:
                return 20;
            default:
                throw new IllegalArgumentException("Unsupported VideoCodecType " + videoCodecType);
        }
    }

    private int getForcedKeyFrameIntervalMs(VideoCodecType videoCodecType, String str) {
        if (videoCodecType != VideoCodecType.VP8 || !str.startsWith("OMX.qcom.")) {
            return 0;
        }
        if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
            return 15000;
        }
        if (Build.VERSION.SDK_INT == 23) {
            return QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
        }
        if (Build.VERSION.SDK_INT > 23) {
            return 15000;
        }
        return 0;
    }

    private BitrateAdjuster createBitrateAdjuster(VideoCodecType videoCodecType, String str) {
        if (!str.startsWith("OMX.Exynos.")) {
            return new BaseBitrateAdjuster();
        }
        if (videoCodecType == VideoCodecType.VP8) {
            return new DynamicBitrateAdjuster();
        }
        return new FramerateBitrateAdjuster();
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo mediaCodecInfo) {
        return this.enableH264HighProfile && Build.VERSION.SDK_INT > 23 && mediaCodecInfo.getName().startsWith("OMX.Exynos.");
    }

    private Map<String, String> getCodecProperties(VideoCodecType videoCodecType, boolean z) {
        switch (videoCodecType) {
            case VP8:
            case VP9:
                return new HashMap();
            case H264:
                HashMap hashMap = new HashMap();
                hashMap.put(VideoCodecInfo.H264_FMTP_LEVEL_ASYMMETRY_ALLOWED, "1");
                hashMap.put(VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE, "1");
                hashMap.put(VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID, z ? VideoCodecInfo.H264_CONSTRAINED_HIGH_3_1 : VideoCodecInfo.H264_CONSTRAINED_BASELINE_3_1);
                return hashMap;
            default:
                throw new IllegalArgumentException("Unsupported codec: " + videoCodecType);
        }
    }
}
