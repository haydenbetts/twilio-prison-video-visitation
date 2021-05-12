package fm.liveswitch.h264;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.VideoFormat;

public class Format extends VideoFormat {
    public int getMaxBitrate() {
        return 100000;
    }

    public int getMinBitrate() {
        return 100;
    }

    /* access modifiers changed from: protected */
    public VideoFormat createInstance() {
        return new Format();
    }

    public Format() {
        super(VideoFormat.getH264Name());
    }

    public Format(int i) {
        super(VideoFormat.getH264Name(), i);
    }

    public Format(int i, int i2) {
        super(VideoFormat.getH264Name(), i, IntegerExtensions.toString(Integer.valueOf(i2)));
    }

    public Format(int i, String str, String str2, int i2) {
        super(VideoFormat.getH264Name(), i, str, str2, IntegerExtensions.toString(Integer.valueOf(i2)));
    }

    public Format(int i, ProfileLevelId profileLevelId, int i2) {
        super(VideoFormat.getH264Name(), i, profileLevelId.getProfile(), profileLevelId.getLevel(), IntegerExtensions.toString(Integer.valueOf(i2)));
    }

    public Format(String str, String str2, int i) {
        super(VideoFormat.getH264Name(), 90000, str, str2, IntegerExtensions.toString(Integer.valueOf(i)));
    }

    public Format(ProfileLevelId profileLevelId) {
        super(VideoFormat.getH264Name(), 90000, profileLevelId.getProfile(), profileLevelId.getLevel());
    }

    public Format(ProfileLevelId profileLevelId, int i) {
        super(VideoFormat.getH264Name(), 90000, profileLevelId.getProfile(), profileLevelId.getLevel(), IntegerExtensions.toString(Integer.valueOf(i)));
    }
}
