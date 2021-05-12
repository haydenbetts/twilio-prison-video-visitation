package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CodecInfo extends Info {
    private NullableInteger _channelCount = new NullableInteger();
    private NullableInteger _clockRate = new NullableInteger();
    private String _name;
    private String _parameters;
    private NullableInteger _payloadType = new NullableInteger();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "name")) {
            setName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "clockRate")) {
            setClockRate(JsonSerializer.deserializeInteger(str2));
        } else if (Global.equals(str, "channelCount")) {
            setChannelCount(JsonSerializer.deserializeInteger(str2));
        } else if (Global.equals(str, "parameters")) {
            setParameters(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "payloadType")) {
            setPayloadType(JsonSerializer.deserializeInteger(str2));
        }
    }

    public static CodecInfo fromJson(String str) {
        return (CodecInfo) JsonSerializer.deserializeObject(str, new IFunction0<CodecInfo>() {
            public CodecInfo invoke() {
                return new CodecInfo();
            }
        }, new IAction3<CodecInfo, String, String>() {
            public void invoke(CodecInfo codecInfo, String str, String str2) {
                codecInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static CodecInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CodecInfo>() {
            public String getId() {
                return "fm.liveswitch.CodecInfo.fromJson";
            }

            public CodecInfo invoke(String str) {
                return CodecInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CodecInfo[]) deserializeObjectArray.toArray(new CodecInfo[0]);
    }

    public NullableInteger getChannelCount() {
        return this._channelCount;
    }

    public NullableInteger getClockRate() {
        return this._clockRate;
    }

    public String getName() {
        return this._name;
    }

    public String getParameters() {
        return this._parameters;
    }

    public NullableInteger getPayloadType() {
        return this._payloadType;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (!StringExtensions.isNullOrEmpty(getName())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(getName()));
        }
        if (getClockRate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clockRate", JsonSerializer.serializeInteger(getClockRate()));
        }
        if (getChannelCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelCount", JsonSerializer.serializeInteger(getChannelCount()));
        }
        if (!StringExtensions.isNullOrEmpty(getParameters())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "parameters", JsonSerializer.serializeString(getParameters()));
        }
        if (getPayloadType().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "payloadType", JsonSerializer.serializeInteger(getPayloadType()));
        }
    }

    public void setChannelCount(NullableInteger nullableInteger) {
        this._channelCount = nullableInteger;
    }

    public void setClockRate(NullableInteger nullableInteger) {
        this._clockRate = nullableInteger;
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setParameters(String str) {
        this._parameters = str;
    }

    public void setPayloadType(NullableInteger nullableInteger) {
        this._payloadType = nullableInteger;
    }

    public static String toJson(CodecInfo codecInfo) {
        return JsonSerializer.serializeObject(codecInfo, new IAction2<CodecInfo, HashMap<String, String>>() {
            public void invoke(CodecInfo codecInfo, HashMap<String, String> hashMap) {
                codecInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CodecInfo[] codecInfoArr) {
        return JsonSerializer.serializeObjectArray(codecInfoArr, new IFunctionDelegate1<CodecInfo, String>() {
            public String getId() {
                return "fm.liveswitch.CodecInfo.toJson";
            }

            public String invoke(CodecInfo codecInfo) {
                return CodecInfo.toJson(codecInfo);
            }
        });
    }
}
