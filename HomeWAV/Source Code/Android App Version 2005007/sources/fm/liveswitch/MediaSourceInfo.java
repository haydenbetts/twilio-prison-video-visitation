package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSourceInfo extends Info {
    private String _inputId;
    private String _inputName;
    private String _label;
    private NullableBoolean _muted = new NullableBoolean();
    private FormatInfo _outputFormat;
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
        } else if (Global.equals(str, "muted")) {
            setMuted(JsonSerializer.deserializeBoolean(str2));
        } else if (Global.equals(str, "inputId")) {
            setInputId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "inputName")) {
            setInputName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "outputFormat")) {
            setOutputFormat(FormatInfo.fromJson(str2));
        }
    }

    public static MediaSourceInfo fromJson(String str) {
        return (MediaSourceInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaSourceInfo>() {
            public MediaSourceInfo invoke() {
                return new MediaSourceInfo();
            }
        }, new IAction3<MediaSourceInfo, String, String>() {
            public void invoke(MediaSourceInfo mediaSourceInfo, String str, String str2) {
                mediaSourceInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSourceInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSourceInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaSourceInfo.fromJson";
            }

            public MediaSourceInfo invoke(String str) {
                return MediaSourceInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSourceInfo[]) deserializeObjectArray.toArray(new MediaSourceInfo[0]);
    }

    public String getInputId() {
        return this._inputId;
    }

    public String getInputName() {
        return this._inputName;
    }

    public String getLabel() {
        return this._label;
    }

    public NullableBoolean getMuted() {
        return this._muted;
    }

    public FormatInfo getOutputFormat() {
        return this._outputFormat;
    }

    public String getTag() {
        return this._tag;
    }

    public MediaSourceInfo() {
    }

    MediaSourceInfo(MediaSourceStats mediaSourceStats, MediaSourceStats mediaSourceStats2) {
        String str;
        boolean z = mediaSourceStats2 == null;
        super.setId(mediaSourceStats.getId());
        String str2 = null;
        String tag = mediaSourceStats.getTag();
        if (!z) {
            tag = Info.processString(tag, z ? null : mediaSourceStats2.getTag());
        }
        setTag(tag);
        if (z) {
            str = mediaSourceStats.getLabel();
        } else {
            str = Info.processString(mediaSourceStats.getLabel(), !z ? mediaSourceStats2.getLabel() : str2);
        }
        setLabel(str);
        setMuted(z ? new NullableBoolean(mediaSourceStats.getMuted()) : Info.processBoolean(mediaSourceStats.getMuted(), mediaSourceStats2.getMuted()));
        String inputId = mediaSourceStats.getInputId();
        setInputId(!z ? Info.processString(inputId, mediaSourceStats2.getInputId()) : inputId);
        String inputName = mediaSourceStats.getInputName();
        setInputName(!z ? Info.processString(inputName, mediaSourceStats2.getInputName()) : inputName);
        if (mediaSourceStats.getOutputFormat() == null) {
            return;
        }
        if (z || !mediaSourceStats.getOutputFormat().isEquivalent(mediaSourceStats2.getOutputFormat())) {
            setOutputFormat(new FormatInfo(mediaSourceStats.getOutputFormat().getName(), mediaSourceStats.getOutputFormat().getClockRate(), mediaSourceStats.getOutputFormat().getChannelCount()));
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
        if (getMuted().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "muted", JsonSerializer.serializeBoolean(getMuted()));
        }
        if (!StringExtensions.isNullOrEmpty(getInputId())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "inputId", JsonSerializer.serializeString(getInputId()));
        }
        if (!StringExtensions.isNullOrEmpty(getInputName())) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "inputName", JsonSerializer.serializeString(getInputName()));
        }
        if (getOutputFormat() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputFormat", FormatInfo.toJson(getOutputFormat()));
        }
    }

    public void setInputId(String str) {
        this._inputId = str;
    }

    public void setInputName(String str) {
        this._inputName = str;
    }

    public void setLabel(String str) {
        this._label = str;
    }

    public void setMuted(NullableBoolean nullableBoolean) {
        this._muted = nullableBoolean;
    }

    public void setOutputFormat(FormatInfo formatInfo) {
        this._outputFormat = formatInfo;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public static String toJson(MediaSourceInfo mediaSourceInfo) {
        return JsonSerializer.serializeObject(mediaSourceInfo, new IAction2<MediaSourceInfo, HashMap<String, String>>() {
            public void invoke(MediaSourceInfo mediaSourceInfo, HashMap<String, String> hashMap) {
                mediaSourceInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSourceInfo[] mediaSourceInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaSourceInfoArr, new IFunctionDelegate1<MediaSourceInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSourceInfo.toJson";
            }

            public String invoke(MediaSourceInfo mediaSourceInfo) {
                return MediaSourceInfo.toJson(mediaSourceInfo);
            }
        });
    }
}
