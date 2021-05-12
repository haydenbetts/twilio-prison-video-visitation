package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import java.util.HashMap;

public class FormatInfo implements IEquivalent<FormatInfo> {
    private int _channelCount;
    private int _clockRate;
    private String _name;

    public FormatInfo() {
    }

    public FormatInfo(AudioFormat audioFormat) {
        this(audioFormat.getName(), audioFormat.getClockRate(), audioFormat.getChannelCount());
    }

    public FormatInfo(String str, int i) {
        this(str, i, 0);
    }

    public FormatInfo(String str, int i, int i2) {
        setName(str);
        setClockRate(i);
        setChannelCount(i2);
    }

    public FormatInfo(VideoFormat videoFormat) {
        this(videoFormat.getName(), videoFormat.getClockRate());
    }

    public static FormatInfo fromJson(String str) {
        return (FormatInfo) JsonSerializer.deserializeObject(str, new IFunction0<FormatInfo>() {
            public FormatInfo invoke() {
                return new FormatInfo();
            }
        }, new IAction3<FormatInfo, String, String>() {
            public void invoke(FormatInfo formatInfo, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "name")) {
                    formatInfo.setName(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "clockRate")) {
                    formatInfo.setClockRate(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "channelCount")) {
                    formatInfo.setChannelCount(JsonSerializer.deserializeInteger(str2).getValue());
                }
            }
        });
    }

    public static FormatInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, FormatInfo>() {
            public String getId() {
                return "fm.liveswitch.FormatInfo.fromJson";
            }

            public FormatInfo invoke(String str) {
                return FormatInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (FormatInfo[]) deserializeObjectArray.toArray(new FormatInfo[0]);
    }

    public static FormatInfo[] fromSdpMediaDescription(MediaDescription mediaDescription) {
        if (mediaDescription == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (MapAttribute mapAttribute : mediaDescription.getRtpMapAttributes()) {
            int i = 1;
            if (!StringExtensions.isNullOrEmpty(mapAttribute.getFormatParameters())) {
                IntegerHolder integerHolder = new IntegerHolder(1);
                ParseAssistant.tryParseIntegerValue(mapAttribute.getFormatParameters(), integerHolder);
                i = integerHolder.getValue();
            }
            arrayList.add(new FormatInfo(mapAttribute.getFormatName(), mapAttribute.getClockRate(), i));
        }
        return (FormatInfo[]) arrayList.toArray(new FormatInfo[0]);
    }

    public int getChannelCount() {
        return this._channelCount;
    }

    public int getClockRate() {
        return this._clockRate;
    }

    public String getCodecName() {
        return getName();
    }

    public String getName() {
        return this._name;
    }

    public boolean isEquivalent(FormatInfo formatInfo) {
        return formatInfo != null && isEquivalent(formatInfo.getName(), formatInfo.getClockRate(), formatInfo.getChannelCount());
    }

    public boolean isEquivalent(String str, int i, int i2) {
        return Global.equals(str, getName()) && i == getClockRate() && i2 == getChannelCount();
    }

    public void setChannelCount(int i) {
        this._channelCount = i;
    }

    public void setClockRate(int i) {
        this._clockRate = i;
    }

    public void setCodecName(String str) {
        setName(str);
    }

    public void setName(String str) {
        this._name = str;
    }

    public static String toJson(FormatInfo formatInfo) {
        return JsonSerializer.serializeObject(formatInfo, new IAction2<FormatInfo, HashMap<String, String>>() {
            public void invoke(FormatInfo formatInfo, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(formatInfo.getName()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clockRate", JsonSerializer.serializeInteger(new NullableInteger(formatInfo.getClockRate())));
                if (formatInfo.getChannelCount() > 0) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelCount", JsonSerializer.serializeInteger(new NullableInteger(formatInfo.getChannelCount())));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(FormatInfo[] formatInfoArr) {
        return JsonSerializer.serializeObjectArray(formatInfoArr, new IFunctionDelegate1<FormatInfo, String>() {
            public String getId() {
                return "fm.liveswitch.FormatInfo.toJson";
            }

            public String invoke(FormatInfo formatInfo) {
                return FormatInfo.toJson(formatInfo);
            }
        });
    }

    public String toString() {
        if (getChannelCount() > 1) {
            return StringExtensions.format("{0}/{1}/{2}", getName(), IntegerExtensions.toString(Integer.valueOf(getClockRate())), IntegerExtensions.toString(Integer.valueOf(getChannelCount())));
        }
        return StringExtensions.format("{0}/{1}", getName(), IntegerExtensions.toString(Integer.valueOf(getClockRate())));
    }
}
