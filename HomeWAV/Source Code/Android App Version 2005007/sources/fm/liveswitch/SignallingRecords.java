package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class SignallingRecords {
    private HashMap<String, SignallingRecord> _hash = new HashMap<>();

    public void add(SignallingRecord signallingRecord) {
        HashMapExtensions.set(HashMapExtensions.getItem(this._hash), signallingRecord.getKey(), signallingRecord);
    }

    public void clear() {
        this._hash.clear();
    }

    public SignallingRecords clone() {
        SignallingRecords signallingRecords = new SignallingRecords();
        for (SignallingRecord duplicate : HashMapExtensions.getValues(this._hash)) {
            signallingRecords.add(duplicate.duplicate());
        }
        return signallingRecords;
    }

    public boolean containsKey(String str) {
        return this._hash.containsKey(str);
    }

    public static SignallingRecords fromJson(String str) {
        return new SignallingRecords(SignallingRecord.fromJsonArray(str));
    }

    public int getCount() {
        return HashMapExtensions.getCount(this._hash);
    }

    public String[] getKeys() {
        ArrayList arrayList = new ArrayList();
        for (String add : HashMapExtensions.getKeys(this._hash)) {
            arrayList.add(add);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public SignallingRecord getRecord(String str) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this._hash, str, holder);
        SignallingRecord signallingRecord = (SignallingRecord) holder.getValue();
        if (!tryGetValue) {
            return null;
        }
        return signallingRecord;
    }

    public SignallingRecord[] getRecords() {
        ArrayList arrayList = new ArrayList();
        for (SignallingRecord add : HashMapExtensions.getValues(this._hash)) {
            arrayList.add(add);
        }
        return (SignallingRecord[]) arrayList.toArray(new SignallingRecord[0]);
    }

    public String getValueJson(String str) {
        SignallingRecord record = getRecord(str);
        if (record == null) {
            return null;
        }
        return record.getValueJson();
    }

    public boolean remove(String str) {
        return HashMapExtensions.remove(this._hash, str);
    }

    public SignallingRecords() {
    }

    public SignallingRecords(SignallingRecord[] signallingRecordArr) {
        for (SignallingRecord signallingRecord : signallingRecordArr) {
            HashMapExtensions.set(HashMapExtensions.getItem(this._hash), signallingRecord.getKey(), signallingRecord);
        }
    }

    public static String toJson(SignallingRecords signallingRecords) {
        return SignallingRecord.toJsonArray(signallingRecords.getRecords());
    }

    public String toJson() {
        return toJson(this);
    }
}
