package fm.liveswitch.sdp;

public class SetupAttribute extends Attribute {
    private String _setup;

    public static SetupAttribute fromAttributeValue(String str) {
        return new SetupAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getSetup();
    }

    public String getSetup() {
        return this._setup;
    }

    private void setSetup(String str) {
        this._setup = str;
    }

    public SetupAttribute(String str) {
        super.setAttributeType(AttributeType.SetupAttribute);
        setSetup(str);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }
}
