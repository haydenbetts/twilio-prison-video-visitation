package fm.liveswitch.sdp;

import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.Holder;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;
import java.util.HashMap;

public class FormatParametersAttribute extends Attribute {
    private int _format;
    private String _formatSpecificParameters;

    public HashMap<String, String> deserializeFormatSpecificParameters() {
        HashMap<String, String> hashMap = new HashMap<>();
        String formatSpecificParameters = getFormatSpecificParameters();
        if (formatSpecificParameters != null) {
            for (String trim : StringExtensions.split(formatSpecificParameters, new char[]{';'})) {
                String trim2 = trim.trim();
                int indexOf = StringExtensions.indexOf(trim2, "=");
                if (indexOf == -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), trim2, null);
                } else {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), StringExtensions.substring(trim2, 0, indexOf), trim2.substring(indexOf + 1));
                }
            }
        }
        return hashMap;
    }

    private FormatParametersAttribute() {
        super.setAttributeType(AttributeType.FormatParametersAttribute);
        super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
    }

    public FormatParametersAttribute(int i) {
        this(i, (String) null);
    }

    public FormatParametersAttribute(int i, String str) {
        this();
        setFormat(i);
        setFormatSpecificParameters(str);
    }

    public static FormatParametersAttribute fromAttributeValue(String str) {
        int indexOf = StringExtensions.indexOf(str, " ");
        int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(str, 0, indexOf));
        String substring = str.substring(indexOf + 1);
        FormatParametersAttribute formatParametersAttribute = new FormatParametersAttribute();
        formatParametersAttribute.setFormat(parseIntegerValue);
        formatParametersAttribute.setFormatSpecificParameters(substring);
        return formatParametersAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getFormat())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getFormatSpecificParameters());
        return sb.toString();
    }

    public int getFormat() {
        return this._format;
    }

    public String getFormatSpecificParameter(String str) {
        return HashMapExtensions.getItem(deserializeFormatSpecificParameters()).get(str);
    }

    public String getFormatSpecificParameters() {
        return this._formatSpecificParameters;
    }

    public void serializeFormatSpecificParameters(HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (String next : HashMapExtensions.getKeys(hashMap)) {
            if (HashMapExtensions.getItem(hashMap).get(next) == null) {
                arrayList.add(next);
            } else {
                arrayList.add(StringExtensions.join("=", new String[]{next, HashMapExtensions.getItem(hashMap).get(next)}));
            }
        }
        setFormatSpecificParameters(StringExtensions.join(";", (String[]) arrayList.toArray(new String[0])));
    }

    public void setFormat(int i) {
        this._format = i;
    }

    public void setFormatSpecificParameter(String str, String str2) {
        HashMap<String, String> deserializeFormatSpecificParameters = deserializeFormatSpecificParameters();
        HashMapExtensions.set(HashMapExtensions.getItem(deserializeFormatSpecificParameters), str, str2);
        serializeFormatSpecificParameters(deserializeFormatSpecificParameters);
    }

    private void setFormatSpecificParameters(String str) {
        this._formatSpecificParameters = str;
    }

    public boolean tryGetFormatSpecificParameter(String str, Holder<String> holder) {
        HashMap<String, String> deserializeFormatSpecificParameters = deserializeFormatSpecificParameters();
        holder.setValue(null);
        return HashMapExtensions.tryGetValue(deserializeFormatSpecificParameters, str, holder);
    }

    public boolean unsetFormatSpecificParameter(String str) {
        HashMap<String, String> deserializeFormatSpecificParameters = deserializeFormatSpecificParameters();
        if (!HashMapExtensions.remove(deserializeFormatSpecificParameters, str)) {
            return false;
        }
        serializeFormatSpecificParameters(deserializeFormatSpecificParameters);
        return true;
    }
}
