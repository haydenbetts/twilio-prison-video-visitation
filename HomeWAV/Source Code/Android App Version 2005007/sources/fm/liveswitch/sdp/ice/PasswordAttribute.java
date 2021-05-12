package fm.liveswitch.sdp.ice;

import fm.liveswitch.Guid;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class PasswordAttribute extends Attribute {
    private String _password;

    public static PasswordAttribute fromAttributeValue(String str) {
        return new PasswordAttribute(str);
    }

    public static String generatePassword() {
        return Guid.newGuid().toString().replace("-", "");
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getPassword();
    }

    public String getPassword() {
        return this._password;
    }

    public PasswordAttribute() {
        this(generatePassword());
    }

    public PasswordAttribute(String str) {
        super.setAttributeType(AttributeType.IcePasswordAttribute);
        if (StringExtensions.getLength(str) < 22 || StringExtensions.getLength(str) > 256) {
            throw new RuntimeException(new Exception("password must be at least 22 characters long up to 256 characters."));
        }
        setPassword(str);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    private void setPassword(String str) {
        this._password = str;
    }
}
