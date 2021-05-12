package fm.liveswitch;

import java.util.Date;

abstract class SignallingMessageBase extends SignallingExtensible {
    byte[] __dataBytes;
    String __dataJson;
    private String __error;
    private boolean __successful;
    private NullableDate __timestamp = new NullableDate();
    private boolean _validate;

    protected static Date deserializeTimestamp(String str) {
        String deserializeString = JsonSerializer.deserializeString(str);
        Date utcNow = DateExtensions.getUtcNow();
        if (StringExtensions.isNullOrEmpty(deserializeString)) {
            return utcNow;
        }
        String[] split = StringExtensions.split(deserializeString, new char[]{'T'});
        if (ArrayExtensions.getLength((Object[]) split) != 2) {
            return utcNow;
        }
        String str2 = split[0];
        String str3 = split[1];
        String[] split2 = StringExtensions.split(str2, new char[]{'-'});
        if (ArrayExtensions.getLength((Object[]) split2) != 3) {
            return utcNow;
        }
        String str4 = split2[0];
        String str5 = split2[1];
        String str6 = split2[2];
        String[] split3 = StringExtensions.split(str3, new char[]{':'});
        if (ArrayExtensions.getLength((Object[]) split3) != 3) {
            return utcNow;
        }
        String str7 = split3[0];
        String str8 = split3[1];
        String[] split4 = StringExtensions.split(split3[2], new char[]{'.'});
        if (ArrayExtensions.getLength((Object[]) split4) != 2) {
            return utcNow;
        }
        String str9 = split4[0];
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str4, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(str5, integerHolder2);
        int value2 = integerHolder2.getValue();
        IntegerHolder integerHolder3 = new IntegerHolder(0);
        boolean tryParseIntegerValue3 = ParseAssistant.tryParseIntegerValue(str6, integerHolder3);
        int value3 = integerHolder3.getValue();
        IntegerHolder integerHolder4 = new IntegerHolder(0);
        boolean tryParseIntegerValue4 = ParseAssistant.tryParseIntegerValue(str7, integerHolder4);
        int value4 = integerHolder4.getValue();
        IntegerHolder integerHolder5 = new IntegerHolder(0);
        boolean tryParseIntegerValue5 = ParseAssistant.tryParseIntegerValue(str8, integerHolder5);
        int value5 = integerHolder5.getValue();
        IntegerHolder integerHolder6 = new IntegerHolder(0);
        return (!tryParseIntegerValue || !tryParseIntegerValue2 || !tryParseIntegerValue3 || !tryParseIntegerValue4 || !tryParseIntegerValue5 || !ParseAssistant.tryParseIntegerValue(str9, integerHolder6)) ? utcNow : DateExtensions.createDate(value, value2, value3, value4, value5, integerHolder6.getValue());
    }

    public byte[] getDataBytes() {
        byte[] bArr = this.__dataBytes;
        String str = this.__dataJson;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = null;
        if (str != null) {
            Holder holder = new Holder(bArr);
            boolean booleanValue = Base64.tryDecode(JsonSerializer.deserializeString(str), holder).booleanValue();
            byte[] bArr3 = (byte[]) holder.getValue();
            if (booleanValue) {
                bArr2 = bArr3;
            }
            this.__dataBytes = bArr2;
        }
        return bArr2;
    }

    public String getDataJson() {
        String str = this.__dataJson;
        byte[] bArr = this.__dataBytes;
        if (str != null) {
            return str;
        }
        if (bArr == null) {
            return null;
        }
        String serializeString = JsonSerializer.serializeString(Base64.encode(bArr));
        this.__dataJson = serializeString;
        return serializeString;
    }

    public String getError() {
        return this.__error;
    }

    public boolean getIsBinary() {
        return getDataBytes() != null;
    }

    public boolean getSuccessful() {
        return this.__successful;
    }

    public NullableDate getTimestamp() {
        return this.__timestamp;
    }

    public boolean getValidate() {
        return this._validate;
    }

    protected static String serializeTimestamp(NullableDate nullableDate) {
        String str;
        if (nullableDate.getHasValue()) {
            String integerExtensions = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getYear(nullableDate.getValue())));
            String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMonth(nullableDate.getValue())));
            String integerExtensions3 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getDay(nullableDate.getValue())));
            String integerExtensions4 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getHour(nullableDate.getValue())));
            String integerExtensions5 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMinute(nullableDate.getValue())));
            String integerExtensions6 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getSecond(nullableDate.getValue())));
            while (StringExtensions.getLength(integerExtensions) < 4) {
                integerExtensions = StringExtensions.concat("0", integerExtensions);
            }
            while (StringExtensions.getLength(integerExtensions2) < 2) {
                integerExtensions2 = StringExtensions.concat("0", integerExtensions2);
            }
            while (StringExtensions.getLength(integerExtensions3) < 2) {
                integerExtensions3 = StringExtensions.concat("0", integerExtensions3);
            }
            while (StringExtensions.getLength(integerExtensions4) < 2) {
                integerExtensions4 = StringExtensions.concat("0", integerExtensions4);
            }
            while (StringExtensions.getLength(integerExtensions5) < 2) {
                integerExtensions5 = StringExtensions.concat("0", integerExtensions5);
            }
            while (StringExtensions.getLength(integerExtensions6) < 2) {
                integerExtensions6 = StringExtensions.concat("0", integerExtensions6);
            }
            str = StringExtensions.format("{0}-{1}-{2}T{3}:{4}:{5}.00", new Object[]{integerExtensions, integerExtensions2, integerExtensions3, integerExtensions4, integerExtensions5, integerExtensions6});
        } else {
            str = null;
        }
        return JsonSerializer.serializeString(str);
    }

    public void setDataBytes(byte[] bArr) {
        this.__dataJson = null;
        this.__dataBytes = bArr;
        super.setIsDirty(true);
    }

    public void setDataJson(String str) {
        if (!getValidate() || str == null || JsonSerializer.isValidJson(str)) {
            this.__dataJson = str;
            this.__dataBytes = null;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("The value is not valid JSON."));
    }

    public void setError(String str) {
        this.__error = str;
        super.setIsDirty(true);
    }

    public void setSuccessful(boolean z) {
        this.__successful = z;
        super.setIsDirty(true);
    }

    public void setTimestamp(NullableDate nullableDate) {
        this.__timestamp = nullableDate;
        super.setIsDirty(true);
    }

    public void setValidate(boolean z) {
        this._validate = z;
    }

    public SignallingMessageBase() {
        setValidate(true);
    }
}
