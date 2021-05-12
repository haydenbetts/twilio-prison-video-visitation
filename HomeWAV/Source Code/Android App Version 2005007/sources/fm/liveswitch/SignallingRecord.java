package fm.liveswitch;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.HashMap;

class SignallingRecord extends Dynamic {
    private String __key;
    private String __valueJson;
    private boolean _validate;

    public SignallingRecord duplicate() {
        SignallingRecord signallingRecord = new SignallingRecord(getKey());
        signallingRecord.setValidate(false);
        signallingRecord.setValueJson(getValueJson());
        signallingRecord.setValidate(true);
        return signallingRecord;
    }

    public static SignallingRecord fromJson(String str) {
        return (SignallingRecord) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingRecord>() {
            public SignallingRecord invoke() {
                return new SignallingRecord("key");
            }
        }, new IAction3<SignallingRecord, String, String>() {
            public void invoke(SignallingRecord signallingRecord, String str, String str2) {
                signallingRecord.setValidate(false);
                if (str != null) {
                    if (Global.equals(str, "key")) {
                        signallingRecord.setKey(JsonSerializer.deserializeString(str2));
                    } else if (Global.equals(str, CommonProperties.VALUE)) {
                        signallingRecord.setValueJson(JsonSerializer.deserializeRaw(str2));
                    }
                }
                signallingRecord.setValidate(true);
            }
        });
    }

    public static SignallingRecord[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingRecord>() {
            public String getId() {
                return "fm.liveswitch.SignallingRecord.fromJson";
            }

            public SignallingRecord invoke(String str) {
                return SignallingRecord.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingRecord[]) deserializeObjectArray.toArray(new SignallingRecord[0]);
    }

    public String getKey() {
        return this.__key;
    }

    /* access modifiers changed from: package-private */
    public boolean getValidate() {
        return this._validate;
    }

    public String getValueJson() {
        return this.__valueJson;
    }

    public void setKey(String str) {
        if (str != null) {
            this.__key = str;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("key cannot be null."));
    }

    /* access modifiers changed from: package-private */
    public void setValidate(boolean z) {
        this._validate = z;
    }

    public void setValueJson(String str) {
        if (!getValidate() || str == null || JsonSerializer.isValidJson(str)) {
            this.__valueJson = str;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("value is not valid JSON."));
    }

    public SignallingRecord() {
        setValidate(true);
    }

    public SignallingRecord(String str) {
        this(str, (String) null);
    }

    public SignallingRecord(String str, String str2) {
        this();
        setKey(str);
        setValueJson(str2);
    }

    public static String toJson(SignallingRecord signallingRecord) {
        return JsonSerializer.serializeObjectFast(signallingRecord, new IAction2<SignallingRecord, HashMap<String, String>>() {
            public void invoke(SignallingRecord signallingRecord, HashMap<String, String> hashMap) {
                if (signallingRecord.getKey() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "key", JsonSerializer.serializeString(signallingRecord.getKey()));
                }
                if (signallingRecord.getValueJson() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), CommonProperties.VALUE, JsonSerializer.serializeRaw(signallingRecord.getValueJson()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingRecord[] signallingRecordArr) {
        return JsonSerializer.serializeObjectArray(signallingRecordArr, new IFunctionDelegate1<SignallingRecord, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingRecord.toJson";
            }

            public String invoke(SignallingRecord signallingRecord) {
                return SignallingRecord.toJson(signallingRecord);
            }
        });
    }

    public String toString() {
        return toJson();
    }
}
