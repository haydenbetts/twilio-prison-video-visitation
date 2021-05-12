package fm.liveswitch.sdp;

public class BundleOnlyAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public BundleOnlyAttribute() {
        super.setAttributeType(AttributeType.BundleOnlyAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public static BundleOnlyAttribute fromAttributeValue(String str) {
        return new BundleOnlyAttribute();
    }
}
