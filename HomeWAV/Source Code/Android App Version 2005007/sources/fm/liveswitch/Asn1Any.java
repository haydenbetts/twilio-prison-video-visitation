package fm.liveswitch;

import java.util.Date;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

abstract class Asn1Any {
    private static byte[] _decode128LowerBytes = {64, 96, 112, 120, 124, 126, ByteCompanionObject.MAX_VALUE};
    private static byte[] _decode128UpperBytes = {ByteCompanionObject.MAX_VALUE, Utf8.REPLACEMENT_BYTE, 31, 15, 7, 3, 1};
    private static byte[] _encode128LowerBytes = {ByteCompanionObject.MIN_VALUE, -64, -32, -16, -8, -4};
    private static byte[] _encode128UpperBytes = {Utf8.REPLACEMENT_BYTE, 31, 15, 7, 3, 1};
    private static int _indefiniteLength = 128;
    private DataBuffer _sourceData;

    private static double intToDouble(int i) {
        return (double) i;
    }

    public abstract byte[] getContents();

    public abstract void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2);

    protected Asn1Any() {
    }

    public static byte[] decode128(byte[] bArr) {
        return decode128(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static byte[] decode128(byte[] bArr, int i, int i2) {
        byte b;
        byte[] bArr2 = new byte[((int) MathAssistant.ceil((intToDouble(i2) * 7.0d) / 8.0d))];
        for (int i3 = (i2 * 7) - 1; i3 >= 0; i3 -= 8) {
            int floor = (int) MathAssistant.floor(intToDouble(i3) / 7.0d);
            int i4 = i3 / 8;
            int i5 = i3 % 7;
            int i6 = i + floor;
            byte b2 = (byte) (bArr[i6] & _decode128LowerBytes[i5]);
            if (floor == 0) {
                b = 0;
            } else {
                b = bArr[i6 - 1];
            }
            bArr2[i4] = (byte) ((((byte) (b & _decode128UpperBytes[i5])) << (i5 + 1)) | (b2 >> (6 - i5)));
        }
        return bArr2;
    }

    public static long decode128Integer(byte[] bArr) {
        return decode128Integer(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static long decode128Integer(byte[] bArr, int i, int i2) {
        return Binary.fromBytes32(pad(decode128(bArr, i, i2), 4), 0, false);
    }

    public static long decode128Long(byte[] bArr) {
        return decode128Long(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static long decode128Long(byte[] bArr, int i, int i2) {
        return Binary.fromBytes64(pad(decode128(bArr, i, i2), 8), 0, false);
    }

    public static int decode128Short(byte[] bArr) {
        return decode128Short(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static int decode128Short(byte[] bArr, int i, int i2) {
        return Binary.fromBytes16(pad(decode128(bArr, i, i2), 2), 0, false);
    }

    public static Date deserializeTimestamp(String str) {
        if (!str.endsWith("Z")) {
            Log.error(StringExtensions.format("Timestamp format is incorrect: {0}.", (Object) str));
            return DateExtensions.getUtcNow();
        }
        String substring = StringExtensions.substring(str, 0, StringExtensions.getLength(str) - 1);
        if (StringExtensions.getLength(substring) % 2 > 0 || StringExtensions.getLength(substring) < StringExtensions.getLength("YYMMDD")) {
            Log.error(StringExtensions.format("Timestamp format is incorrect: {0}.", (Object) str));
            return DateExtensions.getUtcNow();
        }
        if (StringExtensions.getLength(substring) == StringExtensions.getLength("YYMMDD")) {
            substring = StringExtensions.concat(substring, "00");
        }
        if (StringExtensions.getLength(substring) == StringExtensions.getLength("YYMMDDhh")) {
            substring = StringExtensions.concat(substring, "00");
        }
        if (StringExtensions.getLength(substring) == StringExtensions.getLength("YYMMDDhhmm")) {
            substring = StringExtensions.concat(substring, "00");
        }
        if (StringExtensions.getLength(substring) == StringExtensions.getLength("YYMMDDhhmmss")) {
            if (ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 0, 2)) >= 50) {
                substring = StringExtensions.concat("19", substring);
            } else {
                substring = StringExtensions.concat("20", substring);
            }
        }
        return DateExtensions.createDate(ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 0, 4)), ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 4, 2)), ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 6, 2)), ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 8, 2)), ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 10, 2)), ParseAssistant.parseIntegerValue(StringExtensions.substring(substring, 12, 2)));
    }

    public static byte[] encode128(byte[] bArr) {
        byte b;
        byte[] bArr2 = new byte[((int) MathAssistant.ceil((intToDouble(ArrayExtensions.getLength(bArr)) * 8.0d) / 7.0d))];
        int length = (ArrayExtensions.getLength(bArr) * 8) - 1;
        while (true) {
            byte b2 = 0;
            if (length < 0) {
                break;
            }
            int i = length / 7;
            int floor = (int) MathAssistant.floor(intToDouble(length) / 8.0d);
            int i2 = length % 8;
            byte b3 = bArr[floor];
            if (i2 == 7) {
                b = b3 & ByteCompanionObject.MAX_VALUE;
            } else if (i2 == 6) {
                b = b3 & 254;
            } else {
                byte b4 = bArr[floor] & UByte.MAX_VALUE & _encode128LowerBytes[i2];
                if (floor != 0) {
                    b2 = bArr[floor - 1] & UByte.MAX_VALUE;
                }
                b = ((b2 & _encode128UpperBytes[i2]) << (i2 + 1)) | (b4 >> (7 - i2));
            }
            byte b5 = (byte) b;
            if (i < ArrayExtensions.getLength(bArr2) - 1) {
                b5 = (byte) (b5 | ByteCompanionObject.MIN_VALUE);
            }
            bArr2[i] = b5;
            length -= 7;
        }
        while ((bArr2[0] & UByte.MAX_VALUE) == 128) {
            bArr2 = BitAssistant.subArray(bArr2, 1);
        }
        return bArr2;
    }

    public static byte[] encode128Integer(int i) {
        return encode128(trim(Binary.toBytes32((long) i, false)));
    }

    public static byte[] encode128Long(long j) {
        return encode128(trim(Binary.toBytes64(j, false)));
    }

    public static byte[] encode128Short(short s) {
        return encode128(trim(Binary.toBytes16(s, false)));
    }

    public static DataBuffer getBuffer(Asn1Any asn1Any) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        BooleanHolder booleanHolder = new BooleanHolder(false);
        BooleanHolder booleanHolder2 = new BooleanHolder(false);
        asn1Any.getProperties(integerHolder, integerHolder2, booleanHolder, booleanHolder2);
        int value = integerHolder.getValue();
        int value2 = integerHolder2.getValue();
        boolean value3 = booleanHolder.getValue();
        boolean value4 = booleanHolder2.getValue();
        byte[] contents = asn1Any.getContents();
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(getIdentifier(value, value2, value3));
        byteCollection.addRange(getLength(contents, value4));
        byteCollection.addRange(contents);
        if (value4) {
            byteCollection.addRange(new byte[2]);
        }
        return DataBuffer.wrap(byteCollection.toArray());
    }

    public DataBuffer getBuffer() {
        return getBuffer(this);
    }

    public static byte[] getBytes(Asn1Any asn1Any) {
        return getBuffer(asn1Any).toArray();
    }

    public byte[] getBytes() {
        return getBuffer().toArray();
    }

    private static byte[] getIdentifier(int i, int i2, boolean z) {
        ByteCollection byteCollection = new ByteCollection();
        int i3 = z ? 32 : 0;
        if (i < 31) {
            byteCollection.add((byte) (i | i2 | i3));
        } else {
            byteCollection.add((byte) (i2 | 31 | i3));
            byteCollection.addRange(encode128(new byte[]{(byte) i}));
        }
        return byteCollection.toArray();
    }

    private static int getIdentifierLength(byte[] bArr, int i) {
        if ((bArr[i] & 31) == 31) {
            return getLength128(bArr, i + 1) + 1;
        }
        return 1;
    }

    private static byte[] getLength(byte[] bArr, boolean z) {
        if (z) {
            return new byte[]{(byte) _indefiniteLength};
        }
        ByteCollection byteCollection = new ByteCollection();
        if (ArrayExtensions.getLength(bArr) < 128) {
            byteCollection.add((byte) ArrayExtensions.getLength(bArr));
        } else {
            byte[] trim = trim(Binary.toBytes64((long) ArrayExtensions.getLength(bArr), false));
            byteCollection.add((byte) (((byte) ArrayExtensions.getLength(trim)) | ByteCompanionObject.MIN_VALUE));
            byteCollection.addRange(trim);
        }
        return byteCollection.toArray();
    }

    public static int getLength128(byte[] bArr, int i) {
        for (int i2 = i; i2 < ArrayExtensions.getLength(bArr); i2++) {
            if ((bArr[i2] & ByteCompanionObject.MIN_VALUE) == 0) {
                return (i2 - i) + 1;
            }
        }
        return 0;
    }

    private static int getLengthLength(byte[] bArr, int i) {
        if (bArr[i] == 128 || (bArr[i] & ByteCompanionObject.MIN_VALUE) != 128) {
            return 1;
        }
        return (bArr[i] & ByteCompanionObject.MAX_VALUE) + 1;
    }

    public DataBuffer getSourceData() {
        return this._sourceData;
    }

    public static byte[] pad(byte[] bArr, int i) {
        return pad(bArr, i, 0);
    }

    public static byte[] pad(byte[] bArr, int i, int i2) {
        ByteCollection byteCollection = new ByteCollection();
        byte[] bArr2 = new byte[(i - ArrayExtensions.getLength(bArr))];
        for (int i3 = 0; i3 < ArrayExtensions.getLength(bArr2); i3++) {
            bArr2[i3] = (byte) i2;
        }
        byteCollection.addRange(bArr2);
        byteCollection.addRange(bArr);
        return byteCollection.toArray();
    }

    public static Asn1Any parseBuffer(DataBuffer dataBuffer) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        Asn1Any parseBuffer = parseBuffer(dataBuffer, 0, integerHolder);
        integerHolder.getValue();
        return parseBuffer;
    }

    public static Asn1Any parseBuffer(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        Asn1Any parseBytesInternal = parseBytesInternal(dataBuffer.getData(), dataBuffer.getIndex() + i, integerHolder);
        if (parseBytesInternal != null) {
            parseBytesInternal.setSourceData(dataBuffer.subset(i, integerHolder.getValue()));
        }
        return parseBytesInternal;
    }

    public static Asn1Any parseBytes(byte[] bArr) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        Asn1Any parseBytes = parseBytes(bArr, 0, integerHolder);
        integerHolder.getValue();
        return parseBytes;
    }

    public static Asn1Any parseBytes(byte[] bArr, int i, IntegerHolder integerHolder) {
        return parseBuffer(DataBuffer.wrap(bArr), i, integerHolder);
    }

    private static Asn1Any parseBytesInternal(byte[] bArr, int i, IntegerHolder integerHolder) {
        int identifierLength = getIdentifierLength(bArr, i);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        IntegerHolder integerHolder3 = new IntegerHolder(0);
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean parseIdentifier = parseIdentifier(bArr, i, identifierLength, integerHolder2, integerHolder3, booleanHolder);
        int value = integerHolder2.getValue();
        int value2 = integerHolder3.getValue();
        boolean value3 = booleanHolder.getValue();
        if (!parseIdentifier) {
            integerHolder.setValue(0);
            return null;
        }
        int i2 = i + identifierLength;
        int lengthLength = getLengthLength(bArr, i2);
        IntegerHolder integerHolder4 = new IntegerHolder(0);
        BooleanHolder booleanHolder2 = new BooleanHolder(false);
        boolean parseLength = parseLength(bArr, i2, lengthLength, integerHolder4, booleanHolder2);
        int value4 = integerHolder4.getValue();
        boolean value5 = booleanHolder2.getValue();
        if (!parseLength) {
            integerHolder.setValue(0);
            return null;
        }
        byte[] subArray = BitAssistant.subArray(bArr, i2 + lengthLength, value4);
        integerHolder.setValue(identifierLength + lengthLength + value4);
        if (value2 == 0) {
            if (value == 3) {
                return Asn1BitString.parseContents(subArray);
            }
            if (value == 30) {
                return Asn1BmpString.parseContents(subArray);
            }
            if (value == 1) {
                return Asn1Boolean.parseContents(subArray);
            }
            if (value == 24) {
                return Asn1GeneralizedTime.parseContents(subArray);
            }
            if (value == 27) {
                return Asn1GeneralString.parseContents(subArray);
            }
            if (value == 25) {
                return Asn1GraphicString.parseContents(subArray);
            }
            if (value == 22) {
                return Asn1Ia5String.parseContents(subArray);
            }
            if (value == 2) {
                return Asn1Integer.parseContents(subArray);
            }
            if (value == 5) {
                return Asn1Null.parseContents(subArray);
            }
            if (value == 18) {
                return Asn1NumericString.parseContents(subArray);
            }
            if (value == 6) {
                return Asn1ObjectIdentifier.parseContents(subArray);
            }
            if (value == 4) {
                return Asn1OctetString.parseContents(subArray);
            }
            if (value == 19) {
                return Asn1PrintableString.parseContents(subArray);
            }
            if (value == 16) {
                return Asn1Sequence.parseContents(subArray);
            }
            if (value == 17) {
                return Asn1Set.parseContents(subArray);
            }
            if (value == 28) {
                return Asn1UniversalString.parseContents(subArray);
            }
            if (value == 23) {
                return Asn1UtcTime.parseContents(subArray);
            }
            if (value == 12) {
                return Asn1Utf8String.parseContents(subArray);
            }
            if (value == 26) {
                return Asn1VisibleString.parseContents(subArray);
            }
        } else if (value2 != 128) {
        }
        return Asn1Unknown.parseContents(value, value2, value3, value5, subArray);
    }

    private static boolean parseIdentifier(byte[] bArr, int i, int i2, IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder) {
        boolean z = false;
        if (ArrayExtensions.getLength(bArr) < i + i2 || i2 < 1 || i2 > 2) {
            integerHolder.setValue(0);
            integerHolder2.setValue(0);
            booleanHolder.setValue(false);
            return false;
        }
        if (i2 == 1) {
            integerHolder.setValue(bArr[i] & 31);
        } else {
            integerHolder.setValue((int) decode128Integer(bArr, i + 1, i2 - 1));
        }
        integerHolder2.setValue(bArr[i] & 192);
        if ((bArr[i] & 32) == 32) {
            z = true;
        }
        booleanHolder.setValue(z);
        return true;
    }

    private static boolean parseLength(byte[] bArr, int i, int i2, IntegerHolder integerHolder, BooleanHolder booleanHolder) {
        int i3 = i + i2;
        if (ArrayExtensions.getLength(bArr) < i3 || i2 < 1) {
            integerHolder.setValue(0);
            booleanHolder.setValue(false);
            return false;
        } else if (i2 != 1) {
            byte b = bArr[i] & ByteCompanionObject.MAX_VALUE;
            if (i2 != b + 1) {
                integerHolder.setValue(0);
                booleanHolder.setValue(false);
                return false;
            }
            integerHolder.setValue((int) Binary.fromBytes64(pad(BitAssistant.subArray(bArr, i + 1, b), 8), 0, false));
            booleanHolder.setValue(false);
            return true;
        } else {
            if (bArr[i] != 128) {
                integerHolder.setValue(bArr[i]);
                booleanHolder.setValue(false);
            } else {
                integerHolder.setValue(0);
                int i4 = i3;
                while (true) {
                    if (i4 < ArrayExtensions.getLength(bArr) - 1) {
                        if (bArr[i4] == 0 && bArr[i4 + 1] == 0) {
                            integerHolder.setValue(i4 - i3);
                            break;
                        }
                        i4++;
                    } else {
                        break;
                    }
                }
                booleanHolder.setValue(true);
            }
            return true;
        }
    }

    public static String serializeTimestamp(Date date, int i, int i2, int i3, int i4, int i5, int i6) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getYear(date)));
        String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMonth(date)));
        String integerExtensions3 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getDay(date)));
        String integerExtensions4 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getHour(date)));
        String integerExtensions5 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMinute(date)));
        String integerExtensions6 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getSecond(date)));
        while (StringExtensions.getLength(integerExtensions) < i) {
            integerExtensions = StringExtensions.concat("0", integerExtensions);
        }
        while (StringExtensions.getLength(integerExtensions) > i) {
            integerExtensions = integerExtensions.substring(1);
        }
        while (StringExtensions.getLength(integerExtensions2) < i2) {
            integerExtensions2 = StringExtensions.concat("0", integerExtensions2);
        }
        while (StringExtensions.getLength(integerExtensions2) > i2) {
            integerExtensions2 = integerExtensions2.substring(1);
        }
        while (StringExtensions.getLength(integerExtensions3) < i3) {
            integerExtensions3 = StringExtensions.concat("0", integerExtensions3);
        }
        while (StringExtensions.getLength(integerExtensions3) > i3) {
            integerExtensions3 = integerExtensions3.substring(1);
        }
        while (StringExtensions.getLength(integerExtensions4) < i4) {
            integerExtensions4 = StringExtensions.concat("0", integerExtensions4);
        }
        while (StringExtensions.getLength(integerExtensions4) > i4) {
            integerExtensions4 = integerExtensions4.substring(1);
        }
        while (StringExtensions.getLength(integerExtensions5) < i5) {
            integerExtensions5 = StringExtensions.concat("0", integerExtensions5);
        }
        while (StringExtensions.getLength(integerExtensions5) > i5) {
            integerExtensions5 = integerExtensions5.substring(1);
        }
        while (StringExtensions.getLength(integerExtensions6) < i6) {
            integerExtensions6 = StringExtensions.concat("0", integerExtensions6);
        }
        while (StringExtensions.getLength(integerExtensions6) > i6) {
            integerExtensions6 = integerExtensions6.substring(1);
        }
        return StringExtensions.format("{0}{1}{2}{3}{4}{5}", new Object[]{integerExtensions, integerExtensions2, integerExtensions3, integerExtensions4, integerExtensions5, integerExtensions6});
    }

    private void setSourceData(DataBuffer dataBuffer) {
        this._sourceData = dataBuffer;
    }

    public static byte[] trim(byte[] bArr) {
        return trim(bArr, 0);
    }

    public static byte[] trim(byte[] bArr, int i) {
        return trim(bArr, i, ArrayExtensions.getLength(bArr));
    }

    public static byte[] trim(byte[] bArr, int i, int i2) {
        ByteCollection byteCollection = new ByteCollection();
        boolean z = false;
        for (int length = ArrayExtensions.getLength(bArr) - i2; length < ArrayExtensions.getLength(bArr); length++) {
            byte b = bArr[length] & UByte.MAX_VALUE;
            if (!z && b > 0) {
                z = true;
            }
            if (z || ArrayExtensions.getLength(bArr) - length <= i) {
                byteCollection.add((byte) b);
            }
        }
        return byteCollection.toArray();
    }
}
