package tvi.webrtc;

import javax.annotation.Nullable;

public interface VideoEncoderFactory {
    @Nullable
    @CalledByNative
    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    @CalledByNative
    VideoCodecInfo[] getSupportedCodecs();
}
