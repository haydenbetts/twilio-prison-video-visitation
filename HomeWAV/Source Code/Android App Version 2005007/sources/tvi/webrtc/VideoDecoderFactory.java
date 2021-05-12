package tvi.webrtc;

import javax.annotation.Nullable;

public interface VideoDecoderFactory {
    @Nullable
    VideoDecoder createDecoder(String str);
}
