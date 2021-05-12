package fm.liveswitch;

public abstract class Serializable {
    private String __serialized;
    private boolean _isDirty;
    private boolean _isSerialized;

    /* access modifiers changed from: protected */
    public boolean getIsDirty() {
        return this._isDirty;
    }

    /* access modifiers changed from: package-private */
    public boolean getIsSerialized() {
        return this._isSerialized;
    }

    /* access modifiers changed from: package-private */
    public String getSerialized() {
        return this.__serialized;
    }

    protected Serializable() {
        setIsSerialized(false);
        setIsDirty(false);
    }

    /* access modifiers changed from: protected */
    public void setIsDirty(boolean z) {
        this._isDirty = z;
    }

    /* access modifiers changed from: package-private */
    public void setIsSerialized(boolean z) {
        this._isSerialized = z;
    }

    /* access modifiers changed from: package-private */
    public void setSerialized(String str) {
        this.__serialized = str;
        setIsSerialized(true);
        setIsDirty(false);
    }
}
