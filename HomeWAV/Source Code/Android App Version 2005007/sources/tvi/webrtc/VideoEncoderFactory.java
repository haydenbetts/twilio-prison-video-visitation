package tvi.webrtc;

import javax.annotation.Nullable;

public interface VideoEncoderFactory {
    @Nullable
    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    VideoCodecInfo[] getSupportedCodecs();
}
