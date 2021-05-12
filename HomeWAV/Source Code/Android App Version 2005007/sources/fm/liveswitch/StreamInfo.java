package fm.liveswitch;

import java.util.HashMap;

public abstract class StreamInfo extends Info {
    private String _tag;
    private String _transportId;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "tag")) {
            setTag(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "transportId")) {
            setTransportId(JsonSerializer.deserializeString(str2));
        }
    }

    public String getTag() {
        return this._tag;
    }

    public String getTransportId() {
        return this._transportId;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (getTransportId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "transportId", JsonSerializer.serializeString(getTransportId()));
        }
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setTransportId(String str) {
        this._transportId = str;
    }

    protected StreamInfo() {
    }
}
