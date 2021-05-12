package fm.liveswitch.sdp;

public class ConferenceTypeAttribute extends Attribute {
    private String _conferenceType;

    public ConferenceTypeAttribute(String str) {
        super.setAttributeType(AttributeType.ConferenceTypeAttribute);
        if (str != null) {
            setConferenceType(str);
            super.setMultiplexingCategory(AttributeCategory.Normal);
            return;
        }
        throw new RuntimeException(new Exception("conferenceType cannot be null."));
    }

    public static ConferenceTypeAttribute fromAttributeValue(String str) {
        return new ConferenceTypeAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getConferenceType();
    }

    public String getConferenceType() {
        return this._conferenceType;
    }

    private void setConferenceType(String str) {
        this._conferenceType = str;
    }
}
