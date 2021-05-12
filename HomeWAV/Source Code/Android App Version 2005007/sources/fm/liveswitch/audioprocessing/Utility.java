package fm.liveswitch.audioprocessing;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Log;

public class Utility {
    public static boolean initialize() {
        return initialize(new AecProcessor(new AudioConfig(16000, 1)));
    }

    private static boolean initialize(AecProcessor aecProcessor) {
        try {
            AudioFormat audioFormat = (AudioFormat) aecProcessor.getInputFormat();
            aecProcessor.processBuffer(new AudioBuffer(DataBuffer.wrap(new byte[((((audioFormat.getClockRate() * audioFormat.getChannelCount()) * 2) * 20) / 1000)], true), audioFormat)).waitForResult();
            aecProcessor.destroy();
            return true;
        } catch (Exception e) {
            Log.error("Could not initialize Aec processor.", e);
            return false;
        }
    }
}
