package fm.liveswitch;

public abstract class MediaSinkBase extends Dynamic {
    private String __id = Utility.generateId();
    private String _externalId;
    private String _tag;

    public String getExternalId() {
        return this._externalId;
    }

    public String getId() {
        return this.__id;
    }

    public String getTag() {
        return this._tag;
    }

    protected MediaSinkBase() {
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    public void setTag(String str) {
        this._tag = str;
    }
}
