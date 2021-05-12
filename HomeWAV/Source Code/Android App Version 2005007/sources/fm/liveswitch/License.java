package fm.liveswitch;

import java.util.Date;
import java.util.HashMap;

public class License {
    private static License __license;
    private static String __licenseKey;
    private static volatile boolean __licenseLogged = false;
    private static ILog __log = Log.getLogger(License.class);
    private static RsaKey __publicKey = RsaKey.parseBytes(Base64.decode("MIGJAoGBALf/wUlxLUmBEMqd4QHTc+/CP5P3t/BKD+mBNGkRv6CDNreyMuPPGzh3ijr6j//ikY2YzWDCaVuO5SRnZo4+6DwoLQI7nC/m7hcetvYSJhf4mjCeoH8JG1ks1YQeWSbsLp7KjYr5Dan93LIvk6lcjqzv8xO+8msJZ4X9kzW8tO8ZAgMBAAE="));
    private String _accountId;
    private String _contactEmailAddress;
    private String _contactName;
    private String _contactPhoneNumber;
    private NullableInteger _coreCount;
    private String _id;
    private boolean _isTrial;
    private String _productCode;
    private byte[] _signature;
    private String _siteAddress;
    private String _siteName;
    private Date _validFrom;
    private Date _validTo;

    public boolean check(Holder<String> holder) {
        if (Global.equals(getProductCode(), "LiveSwitch")) {
            if (getIsTrial()) {
                if (getIsValid()) {
                    if (!__licenseLogged) {
                        __log.info(StringExtensions.format("Valid {0} trial license key detected.", (Object) getProductCode()));
                    }
                    holder.setValue(null);
                    return true;
                }
                holder.setValue(StringExtensions.format("Expired {0} trial license key detected. Contact sales@frozenmountain.com for more information. (License is valid from {1} to {2}.)", getProductCode(), getYearMonthDay(getValidFrom()), getYearMonthDay(getValidTo())));
                if (!__licenseLogged) {
                    __log.error(holder.getValue());
                }
                return false;
            } else if (getIsValid()) {
                if (!__licenseLogged) {
                    __log.info(StringExtensions.format("Valid {0} license key detected.", (Object) getProductCode()));
                }
                holder.setValue(null);
                return true;
            } else {
                holder.setValue(StringExtensions.format("{0} license key is invalid for this build. Visit www.frozenmountain.com to generate an updated license key. (License is valid to {1} and build date is {2}.)", getProductCode(), getYearMonthDay(getValidTo()), getYearMonthDay(Build.getDate())));
                if (!__licenseLogged) {
                    __log.error(holder.getValue());
                }
                return false;
            }
        } else if (getIsTrial()) {
            if (getIsValid()) {
                holder.setValue(StringExtensions.format("Valid {0} trial license key detected, but expected a license key for {1}.", getProductCode(), "LiveSwitch"));
                if (!__licenseLogged) {
                    __log.error(holder.getValue());
                }
                return false;
            }
            holder.setValue(StringExtensions.format("Expired {0} trial license key detected, and expected a license key for {1}. Contact sales@frozenmountain.com for more information.", getProductCode(), "LiveSwitch"));
            if (!__licenseLogged) {
                __log.error(holder.getValue());
            }
            return false;
        } else if (getIsValid()) {
            holder.setValue(StringExtensions.format("Valid {0} license key detected, but expected a license key for {1}.", getProductCode(), "LiveSwitch"));
            if (!__licenseLogged) {
                __log.error(holder.getValue());
            }
            return false;
        } else {
            holder.setValue(StringExtensions.format("{0} license key is invalid for this build, and expected a license key for {1}. Visit www.frozenmountain.com to generate an updated license key.", getProductCode(), "LiveSwitch"));
            if (!__licenseLogged) {
                __log.error(holder.getValue());
            }
            return false;
        }
    }

    public static void checkKey() {
        if (__license == null) {
            __license = new License();
        }
    }

    private static License fromJson(String str) {
        return (License) JsonSerializer.deserializeObject(str, new IFunction0<License>() {
            public License invoke() {
                return new License();
            }
        }, new IAction3<License, String, String>() {
            public void invoke(License license, String str, String str2) {
                if (str.equals("id")) {
                    license.setId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("aid")) {
                    license.setAccountId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("pc")) {
                    license.setProductCode(JsonSerializer.deserializeString(str2));
                } else if (str.equals("sn")) {
                    license.setSiteName(JsonSerializer.deserializeString(str2));
                } else if (str.equals("sa")) {
                    license.setSiteAddress(JsonSerializer.deserializeString(str2));
                } else if (str.equals("cn")) {
                    license.setContactName(JsonSerializer.deserializeString(str2));
                } else if (str.equals("cpn")) {
                    license.setContactPhoneNumber(JsonSerializer.deserializeString(str2));
                } else if (str.equals("cea")) {
                    license.setContactEmailAddress(JsonSerializer.deserializeString(str2));
                } else if (str.equals("cc")) {
                    license.setCoreCount(JsonSerializer.deserializeInteger(str2));
                } else if (str.equals("it")) {
                    NullableBoolean deserializeBoolean = JsonSerializer.deserializeBoolean(str2);
                    if (deserializeBoolean.getHasValue()) {
                        license.setIsTrial(deserializeBoolean.getValue());
                    }
                } else if (str.equals("vf")) {
                    NullableLong deserializeLong = JsonSerializer.deserializeLong(str2);
                    if (deserializeLong.getHasValue()) {
                        license.setValidFrom(DateExtensions.createDate(deserializeLong.getValue()));
                    }
                } else if (str.equals("vt")) {
                    NullableLong deserializeLong2 = JsonSerializer.deserializeLong(str2);
                    if (deserializeLong2.getHasValue()) {
                        license.setValidTo(DateExtensions.createDate(deserializeLong2.getValue()));
                    }
                }
            }
        });
    }

    public String getAccountId() {
        return this._accountId;
    }

    public String getContactEmailAddress() {
        return this._contactEmailAddress;
    }

    public String getContactName() {
        return this._contactName;
    }

    public String getContactPhoneNumber() {
        return this._contactPhoneNumber;
    }

    public NullableInteger getCoreCount() {
        return this._coreCount;
    }

    public static License getCurrent() {
        return __license;
    }

    public String getId() {
        return this._id;
    }

    public boolean getIsTrial() {
        return this._isTrial;
    }

    public boolean getIsValid() {
        long ticks = DateExtensions.getTicks(getValidFrom());
        long ticks2 = DateExtensions.getTicks(getValidTo());
        if (getIsTrial()) {
            long ticks3 = DateExtensions.getTicks(DateExtensions.getUtcNow());
            if (ticks3 < ticks || ticks3 > ticks2) {
                return false;
            }
            return true;
        } else if (DateExtensions.getTicks(Build.getDate()) <= ticks2) {
            return true;
        } else {
            return false;
        }
    }

    public String getProductCode() {
        return this._productCode;
    }

    private byte[] getSignature() {
        return this._signature;
    }

    public String getSiteAddress() {
        return this._siteAddress;
    }

    public String getSiteName() {
        return this._siteName;
    }

    public Date getValidFrom() {
        return this._validFrom;
    }

    public Date getValidTo() {
        return this._validTo;
    }

    private static String getYearMonthDay(Date date) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getYear(date)));
        String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMonth(date)));
        String integerExtensions3 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getDay(date)));
        while (StringExtensions.getLength(integerExtensions) < 4) {
            integerExtensions = StringExtensions.concat("0", integerExtensions);
        }
        while (StringExtensions.getLength(integerExtensions2) < 2) {
            integerExtensions2 = StringExtensions.concat("0", integerExtensions2);
        }
        while (StringExtensions.getLength(integerExtensions3) < 2) {
            integerExtensions3 = StringExtensions.concat("0", integerExtensions3);
        }
        return StringExtensions.format("{0}-{1}-{2}", integerExtensions, integerExtensions2, integerExtensions3);
    }

    static {
        __log.info(StringExtensions.format("{0} build version is {1} ({2}-{3}-{4}).", new Object[]{"LiveSwitch", Build.getVersion(), IntegerExtensions.toString(Integer.valueOf(Build.getYear())), IntegerExtensions.toString(Integer.valueOf(Build.getMonth())), IntegerExtensions.toString(Integer.valueOf(Build.getDay()))}));
    }

    private License() {
        this._coreCount = new NullableInteger();
        this._validFrom = new Date();
        this._validTo = new Date();
    }

    private License(String str, String str2, String str3, boolean z, Date date, Date date2) {
        this();
        setId(str);
        setAccountId(str2);
        setProductCode(str3);
        setIsTrial(z);
        setValidFrom(date);
        setValidTo(date2);
    }

    private License(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, NullableInteger nullableInteger, boolean z, Date date, Date date2) {
        this(str, str2, str3, z, date, date2);
        String str9 = str4;
        setSiteName(str4);
        String str10 = str5;
        setSiteAddress(str5);
        String str11 = str6;
        setContactName(str6);
        String str12 = str7;
        setContactPhoneNumber(str7);
        setContactEmailAddress(str8);
        setCoreCount(nullableInteger);
    }

    public static License parseKey(String str) {
        String decode;
        License fromJson;
        if (!StringExtensions.isNullOrEmpty(str)) {
            String[] split = StringExtensions.split(str.trim().replace(" ", StringExtensions.empty).replace("\t", StringExtensions.empty).replace("\r", StringExtensions.empty).replace("\n", StringExtensions.empty), new char[]{'.'});
            if (ArrayExtensions.getLength((Object[]) split) != 2) {
                return null;
            }
            String str2 = split[0];
            if (StringExtensions.getLength(str2) >= StringExtensions.getLength("fm")) {
                String substring = str2.substring(StringExtensions.getLength("fm"));
                if (StringExtensions.isNullOrEmpty(substring)) {
                    return null;
                }
                Holder holder = new Holder(null);
                boolean booleanValue = Base64.tryDecode(substring, holder).booleanValue();
                byte[] bArr = (byte[]) holder.getValue();
                if (!booleanValue || (decode = Utf8.decode(bArr)) == null || (fromJson = fromJson(decode)) == null) {
                    return null;
                }
                String str3 = split[1];
                if (StringExtensions.isNullOrEmpty(str3)) {
                    return null;
                }
                Holder holder2 = new Holder(null);
                boolean booleanValue2 = Base64.tryDecode(str3, holder2).booleanValue();
                byte[] bArr2 = (byte[]) holder2.getValue();
                if (!booleanValue2 || !RsaCrypto.verifySha256(Utf8.encode(substring), bArr2, __publicKey)) {
                    return null;
                }
                fromJson.setSignature(bArr2);
                return fromJson;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void setAccountId(String str) {
        this._accountId = str;
    }

    /* access modifiers changed from: private */
    public void setContactEmailAddress(String str) {
        this._contactEmailAddress = str;
    }

    /* access modifiers changed from: private */
    public void setContactName(String str) {
        this._contactName = str;
    }

    /* access modifiers changed from: private */
    public void setContactPhoneNumber(String str) {
        this._contactPhoneNumber = str;
    }

    /* access modifiers changed from: private */
    public void setCoreCount(NullableInteger nullableInteger) {
        this._coreCount = nullableInteger;
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: private */
    public void setIsTrial(boolean z) {
        this._isTrial = z;
    }

    public static void setKey(String str) {
        __licenseKey = str;
        __license = parseKey(str);
    }

    /* access modifiers changed from: private */
    public void setProductCode(String str) {
        this._productCode = str;
    }

    private void setSignature(byte[] bArr) {
        this._signature = bArr;
    }

    /* access modifiers changed from: private */
    public void setSiteAddress(String str) {
        this._siteAddress = str;
    }

    /* access modifiers changed from: private */
    public void setSiteName(String str) {
        this._siteName = str;
    }

    /* access modifiers changed from: private */
    public void setValidFrom(Date date) {
        this._validFrom = date;
    }

    /* access modifiers changed from: private */
    public void setValidTo(Date date) {
        this._validTo = date;
    }

    private String toJson() {
        return JsonSerializer.serializeObject(this, new IAction2<License, HashMap<String, String>>() {
            public void invoke(License license, HashMap<String, String> hashMap) {
                if (License.this.getId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(License.this.getId()));
                }
                if (License.this.getAccountId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "aid", JsonSerializer.serializeString(License.this.getAccountId()));
                }
                if (License.this.getProductCode() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "pc", JsonSerializer.serializeString(License.this.getProductCode()));
                }
                if (License.this.getSiteName() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sn", JsonSerializer.serializeString(License.this.getSiteName()));
                }
                if (License.this.getSiteAddress() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sa", JsonSerializer.serializeString(License.this.getSiteAddress()));
                }
                if (License.this.getContactName() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cn", JsonSerializer.serializeString(License.this.getContactName()));
                }
                if (License.this.getContactPhoneNumber() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cpn", JsonSerializer.serializeString(License.this.getContactPhoneNumber()));
                }
                if (License.this.getContactEmailAddress() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cea", JsonSerializer.serializeString(License.this.getContactEmailAddress()));
                }
                if (License.this.getCoreCount().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cc", JsonSerializer.serializeInteger(new NullableInteger(License.this.getCoreCount().getValue())));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "it", JsonSerializer.serializeBoolean(new NullableBoolean(License.this.getIsTrial())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "vf", JsonSerializer.serializeLong(new NullableLong(DateExtensions.getTicks(License.this.getValidFrom()))));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "vt", JsonSerializer.serializeLong(new NullableLong(DateExtensions.getTicks(License.this.getValidTo()))));
            }
        });
    }
}
