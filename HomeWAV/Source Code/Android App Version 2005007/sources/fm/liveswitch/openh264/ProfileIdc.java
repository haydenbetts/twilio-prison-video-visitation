package fm.liveswitch.openh264;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;

public class ProfileIdc {
    private static ProfileIdc __baseline = new ProfileIdc(66);
    private static ProfileIdc __cavlc444 = new ProfileIdc(AVCodecContext.FF_PROFILE_H264_HIGH_444_PREDICTIVE);
    private static ProfileIdc __extended = new ProfileIdc(88);
    private static ProfileIdc __high = new ProfileIdc(100);
    private static ProfileIdc __high10 = new ProfileIdc(110);
    private static ProfileIdc __high422 = new ProfileIdc(122);
    private static ProfileIdc __high444 = new ProfileIdc(144);
    private static ProfileIdc __main = new ProfileIdc(77);
    private static ProfileIdc __scalableBaseline = new ProfileIdc(83);
    private static ProfileIdc __scalableHigh = new ProfileIdc(86);
    private static ProfileIdc __unknown = new ProfileIdc(0);
    private int _value;

    public static ProfileIdc getBaseline() {
        return __baseline;
    }

    public static ProfileIdc getCavlc444() {
        return __cavlc444;
    }

    public static ProfileIdc getExtended() {
        return __extended;
    }

    public static ProfileIdc getHigh() {
        return __high;
    }

    public static ProfileIdc getHigh10() {
        return __high10;
    }

    public static ProfileIdc getHigh422() {
        return __high422;
    }

    public static ProfileIdc getHigh444() {
        return __high444;
    }

    public static ProfileIdc getMain() {
        return __main;
    }

    public static ProfileIdc getScalableBaseline() {
        return __scalableBaseline;
    }

    public static ProfileIdc getScalableHigh() {
        return __scalableHigh;
    }

    public static ProfileIdc getUnknown() {
        return __unknown;
    }

    public int getValue() {
        return this._value;
    }

    private ProfileIdc() {
    }

    private ProfileIdc(int i) {
        setValue(i);
    }

    private void setValue(int i) {
        this._value = i;
    }
}
