package fm.liveswitch;

import com.urbanairship.MessageCenterDataManager;
import java.util.Date;
import java.util.HashMap;

public abstract class BaseStats {
    private String _id;
    private Date _timestamp = new Date();

    protected BaseStats() {
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "id")) {
            setId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
            setTimestamp(UnixTimestamp.unixMillisToDateTime(JsonSerializer.deserializeLong(str2).getValue()));
        }
    }

    public String getId() {
        return this._id;
    }

    public Date getTimestamp() {
        return this._timestamp;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        if (getId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(getId()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, JsonSerializer.serializeLong(new NullableLong(UnixTimestamp.dateTimeToUnixMillis(getTimestamp()))));
    }

    /* access modifiers changed from: package-private */
    public void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: package-private */
    public void setTimestamp(Date date) {
        this._timestamp = date;
    }
}
