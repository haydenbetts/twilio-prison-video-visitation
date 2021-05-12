package fm.liveswitch.h264;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;

public class ProfileIdc {
    public static int getBaseline() {
        return 66;
    }

    public static int getCavlc444() {
        return AVCodecContext.FF_PROFILE_H264_HIGH_444_PREDICTIVE;
    }

    public static int getExtended() {
        return 88;
    }

    public static int getHigh() {
        return 100;
    }

    public static int getHigh10() {
        return 110;
    }

    public static int getHigh422() {
        return 122;
    }

    public static int getHigh444() {
        return 144;
    }

    public static int getMain() {
        return 77;
    }

    public static int getScalableBaseline() {
        return 83;
    }

    public static int getScalableHigh() {
        return 86;
    }
}
