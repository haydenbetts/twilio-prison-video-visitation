package fm.liveswitch.sdp;

public class OrientationAttribute extends Attribute {
    private String _orientation;

    public static OrientationAttribute fromAttributeValue(String str) {
        return new OrientationAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getOrientation();
    }

    public String getOrientation() {
        return this._orientation;
    }

    public OrientationAttribute(String str) {
        super.setAttributeType(AttributeType.OrientationAttribute);
        setOrientation(str);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    private void setOrientation(String str) {
        this._orientation = str;
    }
}
