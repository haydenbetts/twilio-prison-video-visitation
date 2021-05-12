package fm.liveswitch.yuv;

import fm.liveswitch.Log;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;

public class Utility {
    public static boolean initialize() {
        ImageConverter imageConverter = new ImageConverter(VideoFormat.getBgr(), VideoFormat.getI420());
        try {
            imageConverter.processBuffer(VideoBuffer.createBlack(160, 120, ((VideoFormat) imageConverter.getInputFormat()).getName())).waitForResult();
            imageConverter.destroy();
            return true;
        } catch (Exception e) {
            Log.error("Could not initialize Yuv image converter.", e);
            return false;
        }
    }
}
