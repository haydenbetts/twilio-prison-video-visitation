package tvi.webrtc;

import javax.annotation.Nullable;

public interface VideoDecoderFactory {
    @Nullable
    @CalledByNative
    VideoDecoder createDecoder(String str);
}
