package tvi.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase;

public class HardwareVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "HardwareVideoDecoderFactory";
    private final boolean fallbackToSoftware;
    private final EglBase.Context sharedContext;

    @Deprecated
    public HardwareVideoDecoderFactory() {
        this((EglBase.Context) null);
    }

    public HardwareVideoDecoderFactory(EglBase.Context context) {
        this(context, true);
    }

    HardwareVideoDecoderFactory(EglBase.Context context, boolean z) {
        this.sharedContext = context;
        this.fallbackToSoftware = z;
    }

    @Nullable
    public VideoDecoder createDecoder(String str) {
        VideoCodecType valueOf = VideoCodecType.valueOf(str);
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType != null) {
            return new HardwareVideoDecoder(findCodecForType.getName(), valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(valueOf.mimeType())).intValue(), this.sharedContext);
        } else if (this.fallbackToSoftware) {
            return new SoftwareVideoDecoderFactory().createDecoder(str);
        } else {
            return null;
        }
    }

    @Nullable
    private MediaCodecInfo findCodecForType(VideoCodecType videoCodecType) {
        MediaCodecInfo mediaCodecInfo;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecType)) {
                return mediaCodecInfo;
            }
        }
        return null;
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecType.mimeType())) != null) {
            return isHardwareSupported(mediaCodecInfo, videoCodecType);
        }
        return false;
    }

    private boolean isHardwareSupported(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        String name = mediaCodecInfo.getName();
        switch (videoCodecType) {
            case VP8:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.") || name.startsWith("OMX.Nvidia.")) {
                    return true;
                }
                return false;
            case VP9:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            case H264:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
