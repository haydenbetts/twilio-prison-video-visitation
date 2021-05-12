package fm.liveswitch;

import java.util.HashMap;

public class MediaSourceStats extends BaseStats implements IEquivalent<MediaSourceStats> {
    private String _inputId;
    private String _inputName;
    private String _label;
    private boolean _muted;
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
            setMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (Global.equals(str, "inputId")) {
            setInputId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "inputName")) {
            setInputName(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "outputFormat")) {
            setOutputFormat(FormatInfo.fromJson(str2));
        }
    }

    public static MediaSourceStats fromJson(String str) {
        return (MediaSourceStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaSourceStats>() {
            public MediaSourceStats invoke() {
                return new MediaSourceStats();
            }
        }, new IAction3<MediaSourceStats, String, String>() {
            public void invoke(MediaSourceStats mediaSourceStats, String str, String str2) {
                mediaSourceStats.deserializeProperties(str, str2);
            }
        });
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

    public boolean getMuted() {
        return this._muted;
    }

    public FormatInfo getOutputFormat() {
        return this._outputFormat;
    }

    public String getTag() {
        return this._tag;
    }

    public boolean isEquivalent(MediaSourceStats mediaSourceStats) {
        if (mediaSourceStats == null) {
            return false;
        }
        if ((mediaSourceStats.getOutputFormat() != null || getOutputFormat() == null) && Global.equals(mediaSourceStats.getTag(), getTag()) && Global.equals(mediaSourceStats.getLabel(), getLabel()) && Global.equals(Boolean.valueOf(mediaSourceStats.getMuted()), Boolean.valueOf(getMuted())) && Global.equals(mediaSourceStats.getInputId(), getInputId()) && Global.equals(mediaSourceStats.getInputName(), getInputName()) && mediaSourceStats.getOutputFormat().isEquivalent(getOutputFormat())) {
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
        if (getInputId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "inputId", JsonSerializer.serializeString(getInputId()));
        }
        if (getInputName() != null) {
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

    public void setMuted(boolean z) {
        this._muted = z;
    }

    public void setOutputFormat(FormatInfo formatInfo) {
        this._outputFormat = formatInfo;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public static String toJson(MediaSourceStats mediaSourceStats) {
        return JsonSerializer.serializeObject(mediaSourceStats, new IAction2<MediaSourceStats, HashMap<String, String>>() {
            public void invoke(MediaSourceStats mediaSourceStats, HashMap<String, String> hashMap) {
                mediaSourceStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
