package fm.liveswitch.opus;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Log;
import fm.liveswitch.SoundUtility;

public class Utility {
    public static boolean initialize() {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        try {
            AudioFormat audioFormat = (AudioFormat) encoder.getInputFormat();
            encoder.destroy();
            decoder.processBuffer((AudioBuffer) encoder.processBuffer(new AudioBuffer(DataBuffer.wrap(new byte[SoundUtility.calculateDataLength(20, audioFormat.getConfig())], true), audioFormat)).waitForResult()).waitForResult();
            decoder.destroy();
            return true;
        } catch (Exception e) {
            Log.error("Could not initialize Opus Encoder or Decoder.", e);
            return false;
        }
    }
}
