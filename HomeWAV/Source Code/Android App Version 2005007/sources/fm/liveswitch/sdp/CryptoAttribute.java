package fm.liveswitch.sdp;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Base64;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Global;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CryptoAttribute extends Attribute {
    private String _cryptoSuite;
    private HashMap<String, String> _keyParams;
    private ArrayList<String> _sessionParams;
    private int _tag;

    private CryptoAttribute() {
        super.setAttributeType(AttributeType.CryptoAttribute);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    public CryptoAttribute(int i, String str) {
        this();
        setTag(i);
        setCryptoSuite(str);
        setKeyParams(new HashMap());
        setSessionParams(new ArrayList());
    }

    public static CryptoAttribute fromAttributeValue(String str) {
        int indexOf = StringExtensions.indexOf(str, " ");
        int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(str, 0, indexOf));
        String substring = str.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring, " ");
        String substring2 = StringExtensions.substring(substring, 0, indexOf2);
        String substring3 = substring.substring(indexOf2 + 1);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        int indexOf3 = StringExtensions.indexOf(substring3, " ");
        if (indexOf3 == -1) {
            for (String str2 : StringExtensions.split(substring3, new char[]{';'})) {
                int indexOf4 = StringExtensions.indexOf(str2, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                if (indexOf4 != -1) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), StringExtensions.substring(str2, 0, indexOf4), str2.substring(indexOf4 + 1));
                }
            }
        } else {
            for (String split : StringExtensions.split(StringExtensions.substring(substring3, 0, indexOf3), new char[]{';'})) {
                String[] split2 = StringExtensions.split(split, new char[]{':'});
                if (ArrayExtensions.getLength((Object[]) split2) == 2) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), split2[0], split2[1]);
                }
            }
            for (String add : StringExtensions.split(substring3.substring(indexOf3 + 1), new char[]{' '})) {
                arrayList.add(add);
            }
        }
        CryptoAttribute cryptoAttribute = new CryptoAttribute();
        cryptoAttribute.setTag(parseIntegerValue);
        cryptoAttribute.setCryptoSuite(substring2);
        cryptoAttribute.setKeyParams(hashMap);
        cryptoAttribute.setSessionParams(arrayList);
        return cryptoAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getTag())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getCryptoSuite());
        for (String next : HashMapExtensions.getKeys(getKeyParams())) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, next);
            StringBuilderExtensions.append(sb, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            StringBuilderExtensions.append(sb, HashMapExtensions.getItem(getKeyParams()).get(next));
        }
        Iterator<String> it = getSessionParams().iterator();
        while (it.hasNext()) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, it.next());
        }
        return sb.toString();
    }

    public String getCryptoSuite() {
        return this._cryptoSuite;
    }

    public DataBuffer getKey() {
        if (Global.equals(getCryptoSuite(), CryptoSuite.getAesCM128HmacSha180()) || Global.equals(getCryptoSuite(), CryptoSuite.getAesCm128HmacSha132())) {
            String str = HashMapExtensions.getItem(getKeyParams()).get(CryptoKeyMethod.getInline());
            int indexOf = StringExtensions.indexOf(str, "|");
            if (indexOf != -1) {
                str = StringExtensions.substring(str, 0, indexOf);
            }
            byte[] decode = Base64.decode(str);
            if (ArrayExtensions.getLength(decode) == 30) {
                return DataBuffer.wrap(BitAssistant.subArray(decode, 0, 16));
            }
            throw new RuntimeException(new Exception("Unexpected key/salt length."));
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Unrecognized crypto-suite: {0}.", (Object) getCryptoSuite())));
    }

    public HashMap<String, String> getKeyParams() {
        return this._keyParams;
    }

    public DataBuffer getSalt() {
        if (Global.equals(getCryptoSuite(), CryptoSuite.getAesCM128HmacSha180()) || Global.equals(getCryptoSuite(), CryptoSuite.getAesCm128HmacSha132())) {
            String str = HashMapExtensions.getItem(getKeyParams()).get(CryptoKeyMethod.getInline());
            int indexOf = StringExtensions.indexOf(str, "|");
            if (indexOf != -1) {
                str = StringExtensions.substring(str, 0, indexOf);
            }
            byte[] decode = Base64.decode(str);
            if (ArrayExtensions.getLength(decode) == 30) {
                return DataBuffer.wrap(BitAssistant.subArray(decode, 16, 14));
            }
            throw new RuntimeException(new Exception("Unexpected key/salt length."));
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Unrecognized crypto-suite: {0}.", (Object) getCryptoSuite())));
    }

    public ArrayList<String> getSessionParams() {
        return this._sessionParams;
    }

    public int getTag() {
        return this._tag;
    }

    private void setCryptoSuite(String str) {
        this._cryptoSuite = str;
    }

    private void setKeyParams(HashMap<String, String> hashMap) {
        this._keyParams = hashMap;
    }

    public CryptoAttribute setKeySalt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(ArrayExtensions.getLength(bArr) + ArrayExtensions.getLength(bArr2))];
        for (int i = 0; i < ArrayExtensions.getLength(bArr); i++) {
            bArr3[i] = bArr[i];
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength(bArr2); i2++) {
            bArr3[ArrayExtensions.getLength(bArr) + i2] = bArr2[i2];
        }
        HashMapExtensions.set(HashMapExtensions.getItem(getKeyParams()), CryptoKeyMethod.getInline(), Base64.encode(bArr3));
        return this;
    }

    private void setSessionParams(ArrayList<String> arrayList) {
        this._sessionParams = arrayList;
    }

    public void setTag(int i) {
        this._tag = i;
    }
}
