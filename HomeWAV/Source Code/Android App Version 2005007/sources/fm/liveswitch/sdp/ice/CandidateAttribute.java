package fm.liveswitch.sdp.ice;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import java.util.HashMap;

public class CandidateAttribute extends Attribute {
    private String __candidateType;
    private String __connectionAddress;
    private String __foundation;
    private int _componentId;
    private HashMap<String, String> _extensions;
    private int _port;
    private long _priority;
    private String _protocol;
    private String _relatedAddress;
    private int _relatedPort;

    private CandidateAttribute() {
        this.__foundation = StringExtensions.empty;
        super.setAttributeType(AttributeType.IceCandidateAttribute);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    public CandidateAttribute(String str, long j, String str2, int i, String str3, int i2) {
        this(str, j, str2, i, str3, (String) null, 0, "udp", i2);
    }

    public CandidateAttribute(String str, long j, String str2, int i, String str3, String str4, int i2, String str5, int i3) {
        this();
        setFoundation(str);
        setProtocol(str5);
        setPriority(j);
        setConnectionAddress(str2);
        setPort(i);
        setCandidateType(str3);
        setRelatedAddress(str4);
        setRelatedPort(i2);
        setComponentId(i3);
        setExtensions(new HashMap());
    }

    public static CandidateAttribute fromAttributeValue(String str) {
        String str2 = str;
        int indexOf = StringExtensions.indexOf(str2, " ");
        int i = 0;
        String substring = StringExtensions.substring(str2, 0, indexOf);
        String substring2 = str2.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring2, " ");
        int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(substring2, 0, indexOf2));
        String substring3 = substring2.substring(indexOf2 + 1);
        int indexOf3 = StringExtensions.indexOf(substring3, " ");
        String lower = StringExtensions.toLower(StringExtensions.substring(substring3, 0, indexOf3));
        String substring4 = substring3.substring(indexOf3 + 1);
        int indexOf4 = StringExtensions.indexOf(substring4, " ");
        long parseLongValue = ParseAssistant.parseLongValue(StringExtensions.substring(substring4, 0, indexOf4));
        String substring5 = substring4.substring(indexOf4 + 1);
        int indexOf5 = StringExtensions.indexOf(substring5, " ");
        String substring6 = StringExtensions.substring(substring5, 0, indexOf5);
        String substring7 = substring5.substring(indexOf5 + 1);
        int indexOf6 = StringExtensions.indexOf(substring7, " ");
        int parseIntegerValue2 = ParseAssistant.parseIntegerValue(StringExtensions.substring(substring7, 0, indexOf6));
        String substring8 = substring7.substring(indexOf6 + 1);
        HashMap hashMap = new HashMap();
        String[] split = StringExtensions.split(substring8, new char[]{' '});
        int length = ArrayExtensions.getLength((Object[]) split);
        if (length % 2 == 1) {
            length--;
        }
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (i < length) {
            String str5 = split[i];
            String str6 = split[i + 1];
            String[] strArr = split;
            if (Global.equals(str5, "typ")) {
                str3 = str6;
            } else if (Global.equals(str5, "raddr")) {
                str4 = str6;
            } else if (Global.equals(str5, "rport")) {
                i2 = ParseAssistant.parseIntegerValue(str6);
            } else {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str5, str6);
            }
            i += 2;
            split = strArr;
        }
        CandidateAttribute candidateAttribute = new CandidateAttribute();
        candidateAttribute.setFoundation(substring);
        candidateAttribute.setComponentId(parseIntegerValue);
        candidateAttribute.setProtocol(lower);
        candidateAttribute.setPriority(parseLongValue);
        candidateAttribute.setConnectionAddress(substring6);
        candidateAttribute.setPort(parseIntegerValue2);
        candidateAttribute.setExtensions(hashMap);
        candidateAttribute.setCandidateType(str3);
        candidateAttribute.setRelatedAddress(str4);
        candidateAttribute.setRelatedPort(i2);
        return candidateAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getFoundation());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getComponentId())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getProtocol());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getPriority())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getConnectionAddress());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPort())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, "typ");
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getCandidateType());
        if (!Global.equals(getCandidateType(), CandidateType.getHost()) && getRelatedAddress() != null) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, "raddr");
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getRelatedAddress());
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, "rport");
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getRelatedPort())));
        }
        for (String next : HashMapExtensions.getKeys(getExtensions())) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, next);
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, HashMapExtensions.getItem(getExtensions()).get(next));
        }
        return sb.toString();
    }

    public String getCandidateType() {
        return this.__candidateType;
    }

    public int getComponentId() {
        return this._componentId;
    }

    public String getConnectionAddress() {
        return this.__connectionAddress;
    }

    public HashMap<String, String> getExtensions() {
        return this._extensions;
    }

    public String getFoundation() {
        return this.__foundation;
    }

    public int getPort() {
        return this._port;
    }

    public long getPriority() {
        return this._priority;
    }

    public String getProtocol() {
        return this._protocol;
    }

    public String getRelatedAddress() {
        return this._relatedAddress;
    }

    public int getRelatedPort() {
        return this._relatedPort;
    }

    public void setCandidateType(String str) {
        if (str != null) {
            this.__candidateType = str;
            return;
        }
        throw new RuntimeException(new Exception("Candidate type cannot be null."));
    }

    public void setComponentId(int i) {
        this._componentId = i;
    }

    public void setConnectionAddress(String str) {
        if (str != null) {
            this.__connectionAddress = str;
            return;
        }
        throw new RuntimeException(new Exception("Connection address cannot be null."));
    }

    private void setExtensions(HashMap<String, String> hashMap) {
        this._extensions = hashMap;
    }

    public void setFoundation(String str) {
        if (str != null) {
            this.__foundation = str;
            return;
        }
        throw new RuntimeException(new Exception("Foundation cannot be null."));
    }

    public void setPort(int i) {
        this._port = i;
    }

    public void setPriority(long j) {
        this._priority = j;
    }

    public void setProtocol(String str) {
        this._protocol = str;
    }

    public void setRelatedAddress(String str) {
        this._relatedAddress = str;
    }

    public void setRelatedPort(int i) {
        this._relatedPort = i;
    }
}
