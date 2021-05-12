package fm.liveswitch.sdp;

public class CategoryAttribute extends Attribute {
    private String _category;

    public CategoryAttribute(String str) {
        super.setAttributeType(AttributeType.CategoryAttribute);
        if (str != null) {
            super.setMultiplexingCategory(AttributeCategory.Normal);
            setCategory(str);
            return;
        }
        throw new RuntimeException(new Exception("category cannot be null."));
    }

    public static CategoryAttribute fromAttributeValue(String str) {
        return new CategoryAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getCategory();
    }

    public String getCategory() {
        return this._category;
    }

    private void setCategory(String str) {
        this._category = str;
    }
}
