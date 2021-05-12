package fm.liveswitch.h264;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.twilio.video.VideoDimensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.EncodingInfo;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Size;
import fm.liveswitch.StringComparison;
import fm.liveswitch.StringExtensions;
import tvi.webrtc.VideoCodecInfo;

public class ProfileLevelId {
    private int _levelIdc;
    private int _profileIdc;
    private ProfileIop _profileIop;

    public static ProfileLevelId getBaselineLevel10() {
        return new ProfileLevelId(66, 0, 10);
    }

    public static ProfileLevelId getBaselineLevel31() {
        return new ProfileLevelId(66, 0, 31);
    }

    public static ProfileLevelId getConstrainedBaselineLevel10() {
        return new ProfileLevelId(66, 224, 10);
    }

    public static ProfileLevelId getConstrainedBaselineLevel31() {
        return new ProfileLevelId(66, 224, 31);
    }

    public static ProfileLevelId getDefault() {
        return getConstrainedBaselineLevel31();
    }

    public static ProfileLevelId getHighLevel50() {
        return new ProfileLevelId(100, 0, 50);
    }

    public String getLevel() {
        return StringExtensions.substring(toString(), 4, 2);
    }

    public int getLevelIdc() {
        return this._levelIdc;
    }

    public static ProfileLevelId getMainLevel50() {
        return new ProfileLevelId(77, 0, 50);
    }

    public EncodingInfo getMaxEncoding() {
        if (StringExtensions.isEqual(getLevel(), "0a", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo = new EncodingInfo();
            encodingInfo.setBitrate(64);
            encodingInfo.setFrameRate(15.0d);
            encodingInfo.setSize(new Size(176, 144));
            return encodingInfo;
        } else if (StringExtensions.isEqual(getLevel(), "0b", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo2 = new EncodingInfo();
            encodingInfo2.setBitrate(192);
            encodingInfo2.setFrameRate(7.5d);
            encodingInfo2.setSize(new Size(VideoDimensions.CIF_VIDEO_WIDTH, VideoDimensions.CIF_VIDEO_HEIGHT));
            return encodingInfo2;
        } else if (StringExtensions.isEqual(getLevel(), "0c", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo3 = new EncodingInfo();
            encodingInfo3.setBitrate(384);
            encodingInfo3.setFrameRate(15.2d);
            encodingInfo3.setSize(new Size(VideoDimensions.CIF_VIDEO_WIDTH, VideoDimensions.CIF_VIDEO_HEIGHT));
            return encodingInfo3;
        } else if (StringExtensions.isEqual(getLevel(), "0d", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo4 = new EncodingInfo();
            encodingInfo4.setBitrate(768);
            encodingInfo4.setFrameRate(30.0d);
            encodingInfo4.setSize(new Size(VideoDimensions.CIF_VIDEO_WIDTH, VideoDimensions.CIF_VIDEO_HEIGHT));
            return encodingInfo4;
        } else if (StringExtensions.isEqual(getLevel(), "14", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo5 = new EncodingInfo();
            encodingInfo5.setBitrate(2000);
            encodingInfo5.setFrameRate(30.0d);
            encodingInfo5.setSize(new Size(VideoDimensions.CIF_VIDEO_WIDTH, VideoDimensions.CIF_VIDEO_HEIGHT));
            return encodingInfo5;
        } else if (StringExtensions.isEqual(getLevel(), "15", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo6 = new EncodingInfo();
            encodingInfo6.setBitrate(4000);
            encodingInfo6.setFrameRate(25.0d);
            encodingInfo6.setSize(new Size(VideoDimensions.CIF_VIDEO_WIDTH, 576));
            return encodingInfo6;
        } else if (StringExtensions.isEqual(getLevel(), "16", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo7 = new EncodingInfo();
            encodingInfo7.setBitrate(4000);
            encodingInfo7.setFrameRate(12.5d);
            encodingInfo7.setSize(new Size(VideoDimensions.HD_720P_VIDEO_HEIGHT, 576));
            return encodingInfo7;
        } else if (StringExtensions.isEqual(getLevel(), "1e", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo8 = new EncodingInfo();
            encodingInfo8.setBitrate(10000);
            encodingInfo8.setFrameRate(25.0d);
            encodingInfo8.setSize(new Size(VideoDimensions.HD_720P_VIDEO_HEIGHT, 576));
            return encodingInfo8;
        } else if (StringExtensions.isEqual(getLevel(), VideoCodecInfo.H264_LEVEL_3_1, StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo9 = new EncodingInfo();
            encodingInfo9.setBitrate(14000);
            encodingInfo9.setFrameRate(30.0d);
            encodingInfo9.setSize(new Size(1280, VideoDimensions.HD_720P_VIDEO_HEIGHT));
            return encodingInfo9;
        } else if (StringExtensions.isEqual(getLevel(), "20", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo10 = new EncodingInfo();
            encodingInfo10.setBitrate(20000);
            encodingInfo10.setFrameRate(42.2d);
            encodingInfo10.setSize(new Size(1280, 1024));
            return encodingInfo10;
        } else if (StringExtensions.isEqual(getLevel(), "28", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo11 = new EncodingInfo();
            encodingInfo11.setBitrate(20000);
            encodingInfo11.setFrameRate(30.0d);
            encodingInfo11.setSize(new Size(2048, 1024));
            return encodingInfo11;
        } else if (StringExtensions.isEqual(getLevel(), "29", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo12 = new EncodingInfo();
            encodingInfo12.setBitrate(50000);
            encodingInfo12.setFrameRate(30.0d);
            encodingInfo12.setSize(new Size(2048, 1024));
            return encodingInfo12;
        } else if (StringExtensions.isEqual(getLevel(), "2a", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo13 = new EncodingInfo();
            encodingInfo13.setBitrate(50000);
            encodingInfo13.setFrameRate(60.0d);
            encodingInfo13.setSize(new Size(2048, 1080));
            return encodingInfo13;
        } else if (StringExtensions.isEqual(getLevel(), "32", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo14 = new EncodingInfo();
            encodingInfo14.setBitrate(135000);
            encodingInfo14.setFrameRate(26.7d);
            encodingInfo14.setSize(new Size(3672, 1536));
            return encodingInfo14;
        } else if (StringExtensions.isEqual(getLevel(), "33", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo15 = new EncodingInfo();
            encodingInfo15.setBitrate(240000);
            encodingInfo15.setFrameRate(26.7d);
            encodingInfo15.setSize(new Size(4096, 2304));
            return encodingInfo15;
        } else if (StringExtensions.isEqual(getLevel(), "34", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo16 = new EncodingInfo();
            encodingInfo16.setBitrate(240000);
            encodingInfo16.setFrameRate(56.3d);
            encodingInfo16.setSize(new Size(4096, 2304));
            return encodingInfo16;
        } else if (StringExtensions.isEqual(getLevel(), "3c", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo17 = new EncodingInfo();
            encodingInfo17.setBitrate(240000);
            encodingInfo17.setFrameRate(30.2d);
            encodingInfo17.setSize(new Size(8192, 4320));
            return encodingInfo17;
        } else if (StringExtensions.isEqual(getLevel(), "3d", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo18 = new EncodingInfo();
            encodingInfo18.setBitrate(480000);
            encodingInfo18.setFrameRate(60.4d);
            encodingInfo18.setSize(new Size(8192, 4320));
            return encodingInfo18;
        } else if (StringExtensions.isEqual(getLevel(), "3e", StringComparison.OrdinalIgnoreCase)) {
            EncodingInfo encodingInfo19 = new EncodingInfo();
            encodingInfo19.setBitrate(AdaptiveTrackSelection.DEFAULT_MAX_INITIAL_BITRATE);
            encodingInfo19.setFrameRate(120.9d);
            encodingInfo19.setSize(new Size(8192, 4320));
            return encodingInfo19;
        } else {
            EncodingInfo encodingInfo20 = new EncodingInfo();
            encodingInfo20.setBitrate(64);
            encodingInfo20.setFrameRate(15.0d);
            encodingInfo20.setSize(new Size(176, 144));
            return encodingInfo20;
        }
    }

    public String getProfile() {
        return StringExtensions.substring(toString(), 0, 4);
    }

    public int getProfileIdc() {
        return this._profileIdc;
    }

    public ProfileIop getProfileIop() {
        return this._profileIop;
    }

    public ProfileLevelId() {
    }

    public ProfileLevelId(String str, String str2) {
        if (str == null) {
            throw new RuntimeException(new Exception("Cannot initialize with null profile."));
        } else if (str2 == null) {
            throw new RuntimeException(new Exception("Cannot initialize with null level."));
        } else if (StringExtensions.getLength(str) != 4) {
            throw new RuntimeException(new Exception(StringExtensions.format("Invalid profile. (Length was {0} and should be 4.)", (Object) IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(str))))));
        } else if (StringExtensions.getLength(str2) == 2) {
            byte[] hexBytes = BitAssistant.getHexBytes(str);
            byte[] hexBytes2 = BitAssistant.getHexBytes(str2);
            setProfileIdc(hexBytes[0]);
            setProfileIop(new ProfileIop(hexBytes[1]));
            setLevelIdc(hexBytes2[0]);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.format("Invalid level. (Length was {0} and should be 2.)", (Object) IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(str2))))));
        }
    }

    public ProfileLevelId(int i, int i2, int i3) {
        setProfileIdc(i);
        setProfileIop(new ProfileIop((byte) i2));
        setLevelIdc(i3);
    }

    public ProfileLevelId(String str) {
        if (str == null) {
            throw new RuntimeException(new Exception("Cannot initialize with null profile level ID."));
        } else if (StringExtensions.getLength(str) == 6) {
            byte[] hexBytes = BitAssistant.getHexBytes(StringExtensions.substring(str, 0, 2));
            byte[] hexBytes2 = BitAssistant.getHexBytes(StringExtensions.substring(str, 2, 2));
            byte[] hexBytes3 = BitAssistant.getHexBytes(StringExtensions.substring(str, 4, 2));
            setProfileIdc(hexBytes[0]);
            setProfileIop(new ProfileIop(hexBytes2[0]));
            setLevelIdc(hexBytes3[0]);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.format("Invalid profile level ID. (Length was {0} and should be 6.)", (Object) IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(str))))));
        }
    }

    public void setLevelIdc(int i) {
        this._levelIdc = i;
    }

    public void setProfileIdc(int i) {
        this._profileIdc = i;
    }

    public void setProfileIop(ProfileIop profileIop) {
        this._profileIop = profileIop;
    }

    public String toString() {
        return BitAssistant.getHexString(new byte[]{(byte) getProfileIdc(), (byte) getProfileIop().getDataBuffer().read8(0), (byte) getLevelIdc()});
    }
}
