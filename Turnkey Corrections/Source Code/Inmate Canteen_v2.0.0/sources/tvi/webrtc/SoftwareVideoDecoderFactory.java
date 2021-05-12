package tvi.webrtc;

import com.twilio.video.Vp8Codec;
import com.twilio.video.Vp9Codec;
import javax.annotation.Nullable;

public class SoftwareVideoDecoderFactory implements VideoDecoderFactory {
    @Nullable
    public VideoDecoder createDecoder(String str) {
        if (str.equalsIgnoreCase(Vp8Codec.NAME)) {
            return new VP8Decoder();
        }
        if (!str.equalsIgnoreCase(Vp9Codec.NAME) || !VP9Decoder.nativeIsSupported()) {
            return null;
        }
        return new VP9Decoder();
    }
}
