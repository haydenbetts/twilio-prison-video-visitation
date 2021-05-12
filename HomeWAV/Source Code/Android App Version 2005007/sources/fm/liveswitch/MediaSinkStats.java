package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSinkStats extends BaseStats implements IEquivalent<MediaSinkStats> {
    private FormatInfo _inputFormat;
    private String _label;
    private boolean _muted;
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
        } else if (Global.equals(str, "muted")) {
            setMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (Global.equals(str, "outputId")) {
            setOutputId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "outputName")) {
            setOutputName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "inputFormat")) {
            setInputFormat(FormatInfo.fromJson(str2));
        }
    }

    public static MediaSinkStats fromJson(String str) {
        return (MediaSinkStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaSinkStats>() {
            public MediaSinkStats invoke() {
                return new MediaSinkStats();
            }
        }, new IAction3<MediaSinkStats, String, String>() {
            public void invoke(MediaSinkStats mediaSinkStats, String str, String str2) {
                mediaSinkStats.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSinkStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSinkStats>() {
            public String getId() {
                return "fm.liveswitch.MediaSinkStats.fromJson";
            }

            public MediaSinkStats invoke(String str) {
                return MediaSinkStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSinkStats[]) deserializeObjectArray.toArray(new MediaSinkStats[0]);
    }

    public FormatInfo getInputFormat() {
        return this._inputFormat;
    }

    public String getLabel() {
        return this._label;
    }

    public boolean getMuted() {
        return this._muted;
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

    public boolean isEquivalent(MediaSinkStats mediaSinkStats) {
        if (mediaSinkStats == null) {
            return false;
        }
        if ((mediaSinkStats.getInputFormat() != null || getInputFormat() == null) && Global.equals(mediaSinkStats.getTag(), getTag()) && Global.equals(mediaSinkStats.getLabel(), getLabel()) && Global.equals(Boolean.valueOf(mediaSinkStats.getMuted()), Boolean.valueOf(getMuted())) && Global.equals(mediaSinkStats.getOutputId(), getOutputId()) && Global.equals(mediaSinkStats.getOutputName(), getOutputName()) && mediaSinkStats.getInputFormat().isEquivalent(getInputFormat())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (getLabel() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "label", JsonSerializer.serializeString(getLabel()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "muted", JsonSerializer.serializeBoolean(new NullableBoolean(getMuted())));
        if (getOutputId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputId", JsonSerializer.serializeString(getOutputId()));
        }
        if (getOutputName() != null) {
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

    public void setMuted(boolean z) {
        this._muted = z;
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

    public static String toJson(MediaSinkStats mediaSinkStats) {
        return JsonSerializer.serializeObject(mediaSinkStats, new IAction2<MediaSinkStats, HashMap<String, String>>() {
            public void invoke(MediaSinkStats mediaSinkStats, HashMap<String, String> hashMap) {
                mediaSinkStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSinkStats[] mediaSinkStatsArr) {
        return JsonSerializer.serializeObjectArray(mediaSinkStatsArr, new IFunctionDelegate1<MediaSinkStats, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSinkStats.toJson";
            }

            public String invoke(MediaSinkStats mediaSinkStats) {
                return MediaSinkStats.toJson(mediaSinkStats);
            }
        });
    }
}
