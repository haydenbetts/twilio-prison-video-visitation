package fm.liveswitch;

import java.util.HashMap;

public class MixerReport extends Info {
    private NullableBoolean _disabled = new NullableBoolean();
    private FormatInfo _inputFormat;
    private FormatInfo _outputFormat;
    private NullableInteger _outputFrameRate = new NullableInteger();
    private NullableInteger _outputHeight = new NullableInteger();
    private NullableInteger _outputWidth = new NullableInteger();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "disabled")) {
            setDisabled(JsonSerializer.deserializeBoolean(str2));
        } else if (Global.equals(str, "inputFormat")) {
            setInputFormat(FormatInfo.fromJson(str2));
        } else if (Global.equals(str, "outputFormat")) {
            setOutputFormat(FormatInfo.fromJson(str2));
        } else if (Global.equals(str, "outputWidth")) {
            setOutputWidth(JsonSerializer.deserializeInteger(str2));
        } else if (Global.equals(str, "outputHeight")) {
            setOutputHeight(JsonSerializer.deserializeInteger(str2));
        } else if (Global.equals(str, "outputFrameRate")) {
            setOutputFrameRate(JsonSerializer.deserializeInteger(str2));
        }
    }

    public NullableBoolean getDisabled() {
        return this._disabled;
    }

    public FormatInfo getInputFormat() {
        return this._inputFormat;
    }

    public FormatInfo getOutputFormat() {
        return this._outputFormat;
    }

    public NullableInteger getOutputFrameRate() {
        return this._outputFrameRate;
    }

    public NullableInteger getOutputHeight() {
        return this._outputHeight;
    }

    public NullableInteger getOutputWidth() {
        return this._outputWidth;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getDisabled().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disabled", JsonSerializer.serializeBoolean(getDisabled()));
        }
        if (getInputFormat() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "inputFormat", FormatInfo.toJson(getInputFormat()));
        }
        if (getOutputFormat() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputFormat", FormatInfo.toJson(getOutputFormat()));
        }
        if (getOutputWidth().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputWidth", JsonSerializer.serializeInteger(getOutputWidth()));
        }
        if (getOutputHeight().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputHeight", JsonSerializer.serializeInteger(getOutputHeight()));
        }
        if (getOutputFrameRate().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "outputFrameRate", JsonSerializer.serializeInteger(getOutputFrameRate()));
        }
    }

    public void setDisabled(NullableBoolean nullableBoolean) {
        this._disabled = nullableBoolean;
    }

    public void setInputFormat(FormatInfo formatInfo) {
        this._inputFormat = formatInfo;
    }

    public void setOutputFormat(FormatInfo formatInfo) {
        this._outputFormat = formatInfo;
    }

    public void setOutputFrameRate(NullableInteger nullableInteger) {
        this._outputFrameRate = nullableInteger;
    }

    public void setOutputHeight(NullableInteger nullableInteger) {
        this._outputHeight = nullableInteger;
    }

    public void setOutputWidth(NullableInteger nullableInteger) {
        this._outputWidth = nullableInteger;
    }
}
