package fm.liveswitch.sdp;

public class ToolAttribute extends Attribute {
    private String _tool;

    public static ToolAttribute fromAttributeValue(String str) {
        return new ToolAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getTool();
    }

    public String getTool() {
        return this._tool;
    }

    private void setTool(String str) {
        this._tool = str;
    }

    public ToolAttribute(String str) {
        super.setAttributeType(AttributeType.ToolAttribute);
        if (str != null) {
            setTool(str);
            super.setMultiplexingCategory(AttributeCategory.Normal);
            return;
        }
        throw new RuntimeException(new Exception("tool cannot be null."));
    }
}
