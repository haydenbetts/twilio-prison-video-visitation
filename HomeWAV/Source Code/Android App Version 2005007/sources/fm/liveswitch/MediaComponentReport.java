package fm.liveswitch;

import java.util.HashMap;

public abstract class MediaComponentReport extends Report {
    private NullableLong _firCount = new NullableLong();
    private NullableLong _lrrCount = new NullableLong();
    private NullableLong _nackCount = new NullableLong();
    private NullableLong _pliCount = new NullableLong();
    private NullableLong _sliCount = new NullableLong();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "nackCount")) {
            setNackCount(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "pliCount")) {
            setPliCount(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "firCount")) {
            setFirCount(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "lrrCount")) {
            setLrrCount(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "sliCount")) {
            setSliCount(JsonSerializer.deserializeLong(str2));
        }
    }

    public NullableLong getFirCount() {
        return this._firCount;
    }

    public NullableLong getLrrCount() {
        return this._lrrCount;
    }

    public NullableLong getNackCount() {
        return this._nackCount;
    }

    public NullableLong getPliCount() {
        return this._pliCount;
    }

    public NullableLong getSliCount() {
        return this._sliCount;
    }

    public MediaComponentReport() {
    }

    MediaComponentReport(MediaComponentStats mediaComponentStats, MediaComponentStats mediaComponentStats2) {
        boolean z = mediaComponentStats2 == null;
        long j = 0;
        setNackCount(Report.processLong(mediaComponentStats.getNackCount(), z ? 0 : mediaComponentStats2.getNackCount()));
        setPliCount(Report.processLong(mediaComponentStats.getPliCount(), z ? 0 : mediaComponentStats2.getPliCount()));
        setFirCount(Report.processLong(mediaComponentStats.getFirCount(), z ? 0 : mediaComponentStats2.getFirCount()));
        setSliCount(Report.processLong(mediaComponentStats.getSliCount(), z ? 0 : mediaComponentStats2.getSliCount()));
        setLrrCount(Report.processLong(mediaComponentStats.getLrrCount(), !z ? mediaComponentStats2.getLrrCount() : j));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getNackCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "nackCount", JsonSerializer.serializeLong(getNackCount()));
        }
        if (getPliCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "pliCount", JsonSerializer.serializeLong(getPliCount()));
        }
        if (getFirCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "firCount", JsonSerializer.serializeLong(getFirCount()));
        }
        if (getLrrCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "lrrCount", JsonSerializer.serializeLong(getLrrCount()));
        }
        if (getSliCount().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sliCount", JsonSerializer.serializeLong(getSliCount()));
        }
    }

    public void setFirCount(NullableLong nullableLong) {
        this._firCount = nullableLong;
    }

    public void setLrrCount(NullableLong nullableLong) {
        this._lrrCount = nullableLong;
    }

    public void setNackCount(NullableLong nullableLong) {
        this._nackCount = nullableLong;
    }

    public void setPliCount(NullableLong nullableLong) {
        this._pliCount = nullableLong;
    }

    public void setSliCount(NullableLong nullableLong) {
        this._sliCount = nullableLong;
    }
}
