package fm.liveswitch.sdp;

public class FrameRateAttribute extends Attribute {
    private String _frameRate;

    public FrameRateAttribute(String str) {
        super.setAttributeType(AttributeType.FrameRateAttribute);
        if (str != null) {
            setFrameRate(str);
            super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
            return;
        }
        throw new RuntimeException(new Exception("frameRate cannot be null."));
    }

    public static FrameRateAttribute fromAttributeValue(String str) {
        return new FrameRateAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getFrameRate();
    }

    public String getFrameRate() {
        return this._frameRate;
    }

    private void setFrameRate(String str) {
        this._frameRate = str;
    }
}
