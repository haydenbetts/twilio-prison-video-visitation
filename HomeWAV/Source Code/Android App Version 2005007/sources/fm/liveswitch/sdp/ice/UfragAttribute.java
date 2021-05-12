package fm.liveswitch.sdp.ice;

import fm.liveswitch.Guid;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class UfragAttribute extends Attribute {
    private String _ufrag;

    public static UfragAttribute fromAttributeValue(String str) {
        return new UfragAttribute(str);
    }

    public static String generateUfrag() {
        return StringExtensions.substring(Guid.newGuid().toString().replace("-", ""), 0, 8);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getUfrag();
    }

    public String getUfrag() {
        return this._ufrag;
    }

    private void setUfrag(String str) {
        this._ufrag = str;
    }

    public UfragAttribute() {
        this(generateUfrag());
    }

    public UfragAttribute(String str) {
        super.setAttributeType(AttributeType.IceUfragAttribute);
        if (StringExtensions.getLength(str) < 4 || StringExtensions.getLength(str) > 256) {
            throw new RuntimeException(new Exception("ufrag must be at least 4 characters long up to 256 characters."));
        }
        super.setMultiplexingCategory(AttributeCategory.Transport);
        setUfrag(str);
    }
}
