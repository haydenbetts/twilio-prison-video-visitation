package fm.liveswitch;

import java.util.HashMap;

public class CodecStats extends BaseStats implements IEquivalent<CodecStats> {
    private int _channelCount;
    private int _clockRate;
    private CodecType _codecType;
    private String _name;
    private String _parameters;
    private int _payloadType;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "codecType")) {
            setCodecType(Global.equals(JsonSerializer.deserializeString(str2), "encode") ? CodecType.Encode : CodecType.Decode);
        } else if (Global.equals(str, "payloadType")) {
            setPayloadType(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "name")) {
            setName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "clockRate")) {
            setClockRate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "channelCount")) {
            setChannelCount(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "parameters")) {
            setParameters(JsonSerializer.deserializeString(str2));
        }
    }

    public static CodecStats fromJson(String str) {
        return (CodecStats) JsonSerializer.deserializeObject(str, new IFunction0<CodecStats>() {
            public CodecStats invoke() {
                return new CodecStats();
            }
        }, new IAction3<CodecStats, String, String>() {
            public void invoke(CodecStats codecStats, String str, String str2) {
                codecStats.deserializeProperties(str, str2);
            }
        });
    }

    public int getChannelCount() {
        return this._channelCount;
    }

    public int getClockRate() {
        return this._clockRate;
    }

    public CodecType getCodecType() {
        return this._codecType;
    }

    public String getName() {
        return this._name;
    }

    public String getParameters() {
        return this._parameters;
    }

    public int getPayloadType() {
        return this._payloadType;
    }

    public boolean isEquivalent(CodecStats codecStats) {
        return codecStats != null && Global.equals(codecStats.getCodecType(), getCodecType()) && codecStats.getPayloadType() == getPayloadType() && Global.equals(codecStats.getName(), getName()) && codecStats.getClockRate() == getClockRate() && codecStats.getChannelCount() == getChannelCount() && Global.equals(codecStats.getParameters(), getParameters());
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "codecType", JsonSerializer.serializeString(Global.equals(getCodecType(), CodecType.Encode) ? "encode" : "decode"));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "payloadType", JsonSerializer.serializeInteger(new NullableInteger(getPayloadType())));
        if (getName() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(getName()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clockRate", JsonSerializer.serializeInteger(new NullableInteger(getClockRate())));
        if (getChannelCount() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelCount", JsonSerializer.serializeInteger(new NullableInteger(getChannelCount())));
        }
        if (getParameters() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "parameters", JsonSerializer.serializeString(getParameters()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setChannelCount(int i) {
        this._channelCount = i;
    }

    /* access modifiers changed from: package-private */
    public void setClockRate(int i) {
        this._clockRate = i;
    }

    /* access modifiers changed from: package-private */
    public void setCodecType(CodecType codecType) {
        this._codecType = codecType;
    }

    /* access modifiers changed from: package-private */
    public void setName(String str) {
        this._name = str;
    }

    /* access modifiers changed from: package-private */
    public void setParameters(String str) {
        this._parameters = str;
    }

    /* access modifiers changed from: package-private */
    public void setPayloadType(int i) {
        this._payloadType = i;
    }

    public static String toJson(CodecStats codecStats) {
        return JsonSerializer.serializeObject(codecStats, new IAction2<CodecStats, HashMap<String, String>>() {
            public void invoke(CodecStats codecStats, HashMap<String, String> hashMap) {
                codecStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
