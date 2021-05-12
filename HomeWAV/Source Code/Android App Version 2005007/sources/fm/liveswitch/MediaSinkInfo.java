package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSinkInfo extends Info {
    private FormatInfo _inputFormat;
    private String _label;
    private String _outputId;
    private String _outputName;
    private String _tag;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "tag")) {
            setTag(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "label")) {
            setLabel(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "outputId")) {
            setOutputId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "outputName")) {
            setOutputName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "inputFormat")) {
            setInputFormat(FormatInfo.fromJson(str2));
        }
    }

    public static MediaSinkInfo fromJson(String str) {
        return (MediaSinkInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaSinkInfo>() {
            public MediaSinkInfo invoke() {
                return new MediaSinkInfo();
            }
        }, new IAction3<MediaSinkInfo, String, String>() {
            public void invoke(MediaSinkInfo mediaSinkInfo, String str, String str2) {
                mediaSinkInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSinkInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSinkInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaSinkInfo.fromJson";
            }

            public MediaSinkInfo invoke(String str) {
                return MediaSinkInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSinkInfo[]) deserializeObjectArray.toArray(new MediaSinkInfo[0]);
    }

    public FormatInfo getInputFormat() {
        return this._inputFormat;
    }

    public String getLabel() {
        return this._label;
    }

    public String getOutputId() {
        return this._outputId;
    }

    public String getOutputName() {
        return this._outputName;
    }

    public String getTag() {
        return this._tag;
    }

    public MediaSinkInfo() {
    }

    MediaSinkInfo(MediaSinkStats mediaSinkStats, MediaSinkStats mediaSinkStats2) {
        super.setId(mediaSinkStats.getId());
        boolean z = mediaSinkStats2 == null;
        String tag = mediaSinkStats.getTag();
        setTag(!z ? Info.processString(tag, mediaSinkStats2.getTag()) : tag);
        String label = mediaSinkStats.getLabel();
        setLabel(!z ? Info.processString(label, mediaSinkStats2.getLabel()) : label);
        String outputId = mediaSinkStats.getOutputId();
        setOutputId(!z ? Info.processString(outputId, mediaSinkStats2.getOutputId()) : outputId);
        String outputName = mediaSinkStats.getOutputName();
        setOutputName(!z ? Info.processString(outputName, mediaSinkStats2.getOutputName()) : outputName);
        if (mediaSinkStats.getInputFormat() == null) {
            return;
        }
        if (z || !mediaSinkStats.getInputFormat().isEquivalent(mediaSinkStats2.getInputFormat())) {
            setInputFormat(new FormatInfo(mediaSinkStats.getInputFormat().getName(), mediaSinkStats.getInputFormat().getClockRate(), mediaSinkStats.getInputFormat().getChannelCount()));
        }
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (!StringExtensions.isNullOrEmpty(getTag())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (!StringExtensions.isNullOrEmpty(getLabel())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "label", JsonSerializer.serializeString(getLabel()));
        }
        if (!StringExtensions.isNullOrEmpty(getOutputId())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputId", JsonSerializer.serializeString(getOutputId()));
        }
        if (!StringExtensions.isNullOrEmpty(getOutputName())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputName", JsonSerializer.serializeString(getOutputName()));
        }
        if (getInputFormat() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "inputFormat", FormatInfo.toJson(getInputFormat()));
        }
    }

    public void setInputFormat(FormatInfo formatInfo) {
        this._inputFormat = formatInfo;
    }

    public void setLabel(String str) {
        this._label = str;
    }

    public void setOutputId(String str) {
        this._outputId = str;
    }

    public void setOutputName(String str) {
        this._outputName = str;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public static String toJson(MediaSinkInfo mediaSinkInfo) {
        return JsonSerializer.serializeObject(mediaSinkInfo, new IAction2<MediaSinkInfo, HashMap<String, String>>() {
            public void invoke(MediaSinkInfo mediaSinkInfo, HashMap<String, String> hashMap) {
                mediaSinkInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSinkInfo[] mediaSinkInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaSinkInfoArr, new IFunctionDelegate1<MediaSinkInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSinkInfo.toJson";
            }

            public String invoke(MediaSinkInfo mediaSinkInfo) {
                return MediaSinkInfo.toJson(mediaSinkInfo);
            }
        });
    }
}
