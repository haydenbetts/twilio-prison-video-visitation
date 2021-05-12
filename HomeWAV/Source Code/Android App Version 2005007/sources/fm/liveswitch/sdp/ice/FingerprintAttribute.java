package fm.liveswitch.sdp.ice;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import java.util.ArrayList;

public class FingerprintAttribute extends Attribute {
    private String _fingerprint;
    private String _hashFunction;

    private FingerprintAttribute() {
        super.setAttributeType(AttributeType.IceFingerprintAttribute);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    public FingerprintAttribute(String str, String str2) {
        this();
        setHashFunction(str);
        if (StringExtensions.indexOf(str2, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR) < 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < StringExtensions.getLength(str2); i += 2) {
                arrayList.add(StringExtensions.substring(str2, i, 2));
            }
            str2 = StringExtensions.join(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, (String[]) arrayList.toArray(new String[0]));
        }
        setFingerprint(StringExtensions.toUpper(str2));
    }

    public static FingerprintAttribute fromAttributeValue(String str) {
        int indexOf = StringExtensions.indexOf(str, " ");
        String substring = StringExtensions.substring(str, 0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        FingerprintAttribute fingerprintAttribute = new FingerprintAttribute();
        fingerprintAttribute.setHashFunction(substring);
        fingerprintAttribute.setFingerprint(substring2);
        return fingerprintAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getHashFunction());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getFingerprint());
        return sb.toString();
    }

    public String getFingerprint() {
        return this._fingerprint;
    }

    public String getHashFunction() {
        return this._hashFunction;
    }

    private void setFingerprint(String str) {
        this._fingerprint = str;
    }

    private void setHashFunction(String str) {
        this._hashFunction = str;
    }
}
