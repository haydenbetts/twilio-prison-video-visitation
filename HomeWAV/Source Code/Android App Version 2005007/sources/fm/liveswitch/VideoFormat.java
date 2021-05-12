package fm.liveswitch;

import com.google.android.gms.common.Scopes;
import com.twilio.video.H264Codec;
import com.twilio.video.Vp8Codec;
import com.twilio.video.Vp9Codec;
import fm.liveswitch.h264.Format;
import java.util.HashMap;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public class VideoFormat extends MediaFormat<VideoFormat> {
    private static HashMap<String, Integer> _fourCCLookup;
    private static HashMap<Integer, String> _reverseFourCCLookup = null;

    public static String getAbgrName() {
        return "ABGR";
    }

    public static String getArgbName() {
        return "ARGB";
    }

    public static String getBgrName() {
        return "BGR";
    }

    public static String getBgraName() {
        return "BGRA";
    }

    public static int getDefaultClockRate() {
        return 90000;
    }

    public static String getH264Name() {
        return H264Codec.NAME;
    }

    public static String getI420Name() {
        return "I420";
    }

    public static String getNv12Name() {
        return "NV12";
    }

    public static String getNv21Name() {
        return "NV21";
    }

    public static String getRgbName() {
        return "RGB";
    }

    public static String getRgbaName() {
        return "RGBA";
    }

    public static String getVp8Name() {
        return Vp8Codec.NAME;
    }

    public static String getVp9Name() {
        return Vp9Codec.NAME;
    }

    public static String getYv12Name() {
        return "YV12";
    }

    public String getParameters() {
        return null;
    }

    public VideoFormat clone() {
        return (VideoFormat) super.clone();
    }

    /* access modifiers changed from: protected */
    public VideoFormat createInstance() {
        return new VideoFormat();
    }

    public static int formatNameToFourCC(String str) {
        if (_fourCCLookup.containsKey(str)) {
            return HashMapExtensions.getItem(_fourCCLookup).get(str).intValue();
        }
        return 0;
    }

    public String fourCCToFormatName(int i) {
        if (_reverseFourCCLookup == null) {
            HashMap<Integer, String> hashMap = new HashMap<>();
            for (String next : HashMapExtensions.getKeys(_fourCCLookup)) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), HashMapExtensions.getItem(_fourCCLookup).get(next), next);
            }
            _reverseFourCCLookup = hashMap;
        }
        Holder holder = new Holder(null);
        return HashMapExtensions.tryGetValue(_reverseFourCCLookup, Integer.valueOf(i), holder) ? (String) holder.getValue() : "";
    }

    public static VideoFormat fromFormatInfo(FormatInfo formatInfo) {
        return new VideoFormat(formatInfo.getName(), formatInfo.getClockRate());
    }

    public static VideoFormat fromJson(String str) {
        return (VideoFormat) JsonSerializer.deserializeObject(str, new IFunction0<VideoFormat>() {
            public VideoFormat invoke() {
                return new VideoFormat();
            }
        }, new IAction3<VideoFormat, String, String>() {
            public void invoke(VideoFormat videoFormat, String str, String str2) {
                if (str.equals("name")) {
                    videoFormat.setName(JsonSerializer.deserializeString(str2));
                } else if (str.equals("clockRate")) {
                    videoFormat.setClockRate(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("isPacketized")) {
                    videoFormat.setIsPacketized(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("isEncrypted")) {
                    videoFormat.setIsEncrypted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("registeredPayloadType")) {
                    videoFormat.setRegisteredPayloadType(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("staticPayloadType")) {
                    videoFormat.setStaticPayloadType(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("packetizationMode")) {
                    videoFormat.setPacketizationMode(JsonSerializer.deserializeString(str2));
                } else if (str.equals(Scopes.PROFILE)) {
                    videoFormat.setProfile(JsonSerializer.deserializeString(str2));
                } else if (str.equals("level")) {
                    videoFormat.setLevel(JsonSerializer.deserializeString(str2));
                } else if (str.equals("levelIsStrict")) {
                    videoFormat.setLevelIsStrict(JsonSerializer.deserializeBoolean(str2).getValue());
                }
            }
        });
    }

    public static VideoFormat getAbgr() {
        VideoFormat videoFormat = new VideoFormat(getAbgrName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getArgb() {
        VideoFormat videoFormat = new VideoFormat(getArgbName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getBgr() {
        VideoFormat videoFormat = new VideoFormat(getBgrName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getBgra() {
        VideoFormat videoFormat = new VideoFormat(getBgraName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public int getFourCC() {
        return formatNameToFourCC(super.getName());
    }

    public static VideoFormat getH264() {
        return new Format();
    }

    public static VideoFormat getI420() {
        VideoFormat videoFormat = new VideoFormat(getI420Name());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public FormatInfo getInfo() {
        return new FormatInfo(this);
    }

    public boolean getIsAbgr() {
        return StringExtensions.isEqual(super.getName(), getAbgrName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsArgb() {
        return StringExtensions.isEqual(super.getName(), getArgbName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsBgr() {
        return StringExtensions.isEqual(super.getName(), getBgrName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsBgra() {
        return StringExtensions.isEqual(super.getName(), getBgraName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsCompressed() {
        return !getIsRaw();
    }

    public boolean getIsH264() {
        return StringExtensions.isEqual(super.getName(), getH264Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsI420() {
        return StringExtensions.isEqual(super.getName(), getI420Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsNv12() {
        return StringExtensions.isEqual(super.getName(), getNv12Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsNv21() {
        return StringExtensions.isEqual(super.getName(), getNv21Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsRaw() {
        return getIsYuvType() || getIsRgbType() || getIsRgbaType();
    }

    public boolean getIsRgb() {
        return StringExtensions.isEqual(super.getName(), getRgbName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsRgba() {
        return StringExtensions.isEqual(super.getName(), getRgbaName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsRgbaType() {
        return getIsRgba() || getIsBgra() || getIsArgb() || getIsAbgr();
    }

    public boolean getIsRgbType() {
        return getIsRgb() || getIsBgr();
    }

    public boolean getIsVp8() {
        return StringExtensions.isEqual(super.getName(), getVp8Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsVp9() {
        return StringExtensions.isEqual(super.getName(), getVp9Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsYuvType() {
        return getIsI420() || getIsYv12() || getIsNv12() || getIsNv21();
    }

    public boolean getIsYv12() {
        return StringExtensions.isEqual(super.getName(), getYv12Name(), StringComparison.OrdinalIgnoreCase);
    }

    /* access modifiers changed from: protected */
    public String getMaxLevel(String str, String str2) {
        if (!getIsH264()) {
            return super.getMaxLevel(str, str2);
        }
        return BitAssistant.getHexBytes(str)[0] > BitAssistant.getHexBytes(str2)[0] ? str : str2;
    }

    /* access modifiers changed from: protected */
    public String getMinLevel(String str, String str2) {
        if (!getIsH264()) {
            return super.getMinLevel(str, str2);
        }
        return BitAssistant.getHexBytes(str)[0] > BitAssistant.getHexBytes(str2)[0] ? str2 : str;
    }

    public static VideoFormat getNv12() {
        VideoFormat videoFormat = new VideoFormat(getNv12Name());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getNv21() {
        VideoFormat videoFormat = new VideoFormat(getNv21Name());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getRgb() {
        VideoFormat videoFormat = new VideoFormat(getRgbName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getRgba() {
        VideoFormat videoFormat = new VideoFormat(getRgbaName());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    public static VideoFormat getVp8() {
        return new fm.liveswitch.vp8.Format();
    }

    public static VideoFormat getVp9() {
        return new fm.liveswitch.vp9.Format();
    }

    public static VideoFormat getYv12() {
        VideoFormat videoFormat = new VideoFormat(getYv12Name());
        videoFormat.setIsFixedBitrate(true);
        return videoFormat;
    }

    /* access modifiers changed from: protected */
    public boolean isLevelCompatible(String str) {
        if (!getIsH264() || StringExtensions.getLength(super.getLevel()) != 2 || StringExtensions.getLength(str) != 2) {
            return super.isLevelCompatible(str);
        }
        if (BitAssistant.getHexBytes(super.getLevel())[0] <= BitAssistant.getHexBytes(str)[0]) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isProfileCompatible(String str) {
        if (!getIsH264() || StringExtensions.getLength(super.getProfile()) != 4 || StringExtensions.getLength(str) != 4) {
            return super.isProfileCompatible(str);
        }
        if (BitAssistant.getHexBytes(super.getProfile())[0] <= BitAssistant.getHexBytes(str)[0]) {
            return true;
        }
        return false;
    }

    public void setFourCC(int i) {
        super.setName(fourCCToFormatName(i));
    }

    public static int toFourCC(char c, char c2, char c3, char c4) {
        return c | BitAssistant.leftShiftInteger(c2, 8) | BitAssistant.leftShiftInteger(c3, 16) | BitAssistant.leftShiftInteger(c4, 24);
    }

    public static int toFourCC(String str) {
        return toFourCC(str.charAt(0), str.charAt(1), str.charAt(2), str.charAt(3));
    }

    public static String toJson(VideoFormat videoFormat) {
        return JsonSerializer.serializeObject(videoFormat, new IAction2<VideoFormat, HashMap<String, String>>(videoFormat) {
            final /* synthetic */ VideoFormat val$videoFormat;

            {
                this.val$videoFormat = r1;
            }

            public void invoke(VideoFormat videoFormat, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(this.val$videoFormat.getName()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clockRate", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoFormat.getClockRate())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isPacketized", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$videoFormat.getIsPacketized())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isEncrypted", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$videoFormat.getIsEncrypted())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "registeredPayloadType", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoFormat.getRegisteredPayloadType())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "staticPayloadType", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoFormat.getStaticPayloadType())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetizationMode", JsonSerializer.serializeString(this.val$videoFormat.getPacketizationMode()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Scopes.PROFILE, JsonSerializer.serializeString(this.val$videoFormat.getProfile()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "level", JsonSerializer.serializeString(this.val$videoFormat.getLevel()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "levelIsStrict", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$videoFormat.getLevelIsStrict())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    /* access modifiers changed from: protected */
    public void updateProfileToCompatible(VideoFormat videoFormat) {
        if (getIsH264() && videoFormat.getProfile() != null) {
            if (super.getProfile() != null && StringExtensions.getLength(super.getProfile()) == 4 && StringExtensions.getLength(videoFormat.getProfile()) == 4) {
                byte[] hexBytes = BitAssistant.getHexBytes(super.getProfile());
                byte[] hexBytes2 = BitAssistant.getHexBytes(videoFormat.getProfile());
                super.setProfile(BitAssistant.getHexString(new byte[]{hexBytes2[0], (byte) (hexBytes[1] | hexBytes2[1])}));
            } else {
                super.setProfile(videoFormat.getProfile());
            }
        }
        super.updateProfileToCompatible(videoFormat);
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        _fourCCLookup = hashMap;
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), getRgbName(), Integer.valueOf(toFourCC('r', 'a', 'w', ' ')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getBgrName(), Integer.valueOf(toFourCC('2', '4', 'B', 'G')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getNv12Name(), Integer.valueOf(toFourCC('N', 'V', '1', '2')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getNv21Name(), Integer.valueOf(toFourCC('N', 'V', '2', '1')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getI420Name(), Integer.valueOf(toFourCC('I', '4', '2', '0')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getYv12Name(), Integer.valueOf(toFourCC('Y', 'V', '1', '2')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getArgbName(), Integer.valueOf(toFourCC('A', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'G', 'B')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getAbgrName(), Integer.valueOf(toFourCC('A', 'B', 'G', Matrix.MATRIX_TYPE_RANDOM_REGULAR)));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getRgbaName(), Integer.valueOf(toFourCC(Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'G', 'B', 'A')));
        HashMapExtensions.set(HashMapExtensions.getItem(_fourCCLookup), getBgraName(), Integer.valueOf(toFourCC('B', 'G', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'A')));
    }

    protected VideoFormat() {
    }

    public VideoFormat(String str) {
        super(str, getDefaultClockRate());
    }

    public VideoFormat(String str, int i) {
        super(str, i);
    }

    public VideoFormat(String str, int i, String str2) {
        super(str, i, str2);
    }

    public VideoFormat(String str, int i, String str2, String str3) {
        super(str, i, str2, str3);
    }

    public VideoFormat(String str, int i, String str2, String str3, String str4) {
        super(str, i, str2, str3, str4);
    }
}
