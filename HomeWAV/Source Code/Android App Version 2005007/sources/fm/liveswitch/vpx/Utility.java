package fm.liveswitch.vpx;

import fm.liveswitch.Log;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.vp8.Decoder;
import fm.liveswitch.vp8.Encoder;

public class Utility {
    public static boolean initialize() {
        return initialize(new Encoder(), new Decoder()) && initialize(new fm.liveswitch.vp9.Encoder(), new fm.liveswitch.vp9.Decoder());
    }

    private static boolean initialize(Encoder encoder, Decoder decoder) {
        try {
            encoder.destroy();
            decoder.processBuffer((VideoBuffer) encoder.processBuffer(VideoBuffer.createBlack(160, 120, VideoFormat.getI420Name())).waitForResult()).waitForResult();
            decoder.destroy();
            return true;
        } catch (Exception e) {
            Log.error("Could not initialize Vpx Encoder.", e);
            return false;
        }
    }
}
