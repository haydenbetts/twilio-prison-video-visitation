package tvi.webrtc;

import com.twilio.video.Vp8Codec;
import com.twilio.video.Vp9Codec;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.Nullable;

public class SoftwareVideoEncoderFactory implements VideoEncoderFactory {
    @Nullable
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        if (videoCodecInfo.name.equalsIgnoreCase(Vp8Codec.NAME)) {
            return new VP8Encoder();
        }
        if (!videoCodecInfo.name.equalsIgnoreCase(Vp9Codec.NAME) || !VP9Encoder.nativeIsSupported()) {
            return null;
        }
        return new VP9Encoder();
    }

    public VideoCodecInfo[] getSupportedCodecs() {
        return supportedCodecs();
    }

    static VideoCodecInfo[] supportedCodecs() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VideoCodecInfo(Vp8Codec.NAME, new HashMap()));
        if (VP9Encoder.nativeIsSupported()) {
            arrayList.add(new VideoCodecInfo(Vp9Codec.NAME, new HashMap()));
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}
