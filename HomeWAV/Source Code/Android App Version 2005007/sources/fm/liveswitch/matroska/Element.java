package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Binary;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.DateExtensions;
import fm.liveswitch.Encoding;
import fm.liveswitch.Guid;
import fm.liveswitch.IntegerHolder;
import java.util.Date;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

public abstract class Element {
    private static long[] _dataBitsMask = {BitAssistant.leftShiftLong(1, 0) - 1, BitAssistant.leftShiftLong(1, 7) - 1, BitAssistant.leftShiftLong(1, 14) - 1, BitAssistant.leftShiftLong(1, 21) - 1, BitAssistant.leftShiftLong(1, 28) - 1, BitAssistant.leftShiftLong(1, 35) - 1, BitAssistant.leftShiftLong(1, 42) - 1, BitAssistant.leftShiftLong(1, 49) - 1, BitAssistant.leftShiftLong(1, 56) - 1};
    private static Date _relativeDateUtc;
    private static int _ticksPerNano = 100;
    private int _length;
    private int _size;
    private int _sizeLength;
    private boolean _writeDefaultValues;

    public abstract byte[] getId();

    /* access modifiers changed from: protected */
    public abstract byte[] getInnerBytes();

    static {
        _relativeDateUtc = new Date();
        _relativeDateUtc = DateExtensions.createDate(2001, 1, 1, 0, 0, 0);
    }

    public static boolean compare(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || ArrayExtensions.getLength(bArr) != ArrayExtensions.getLength(bArr2)) {
            return false;
        }
        for (int i = 0; i < ArrayExtensions.getLength(bArr); i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    protected Element() {
    }

    public byte[] getBytes() {
        int sizeLength = getSizeLength();
        setSizeLength(0);
        byte[] innerBytes = getInnerBytes();
        setSizeLength(sizeLength);
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        setSize(ArrayExtensions.getLength(innerBytes));
        write(innerBytes, getId(), byteOutputStream);
        setLength(byteOutputStream.getSize());
        return byteOutputStream.toArray();
    }

    public int getLength() {
        return this._length;
    }

    public int getSize() {
        return this._size;
    }

    public int getSizeLength() {
        return this._sizeLength;
    }

    public boolean getWriteDefaultValues() {
        return this._writeDefaultValues;
    }

    public static boolean readBool(byte[] bArr) {
        return readUnsignedInteger(bArr) == 1;
    }

    public static Date readDate(byte[] bArr) {
        return DateExtensions.createDate(DateExtensions.getTicks(_relativeDateUtc) + (Binary.fromBytes64(bArr, 0, false) / ((long) _ticksPerNano)));
    }

    public static double readDouble(byte[] bArr) {
        return BitAssistant.convertBytesToDouble(bArr, 0, false);
    }

    public static float readFloat(byte[] bArr) {
        return BitAssistant.convertBytesToSingle(bArr, 0, false);
    }

    public static Guid readGuid(byte[] bArr) {
        return new Guid(bArr);
    }

    public static byte[] readId(byte[] bArr, int i, IntegerHolder integerHolder) {
        byte b = bArr[i];
        int i2 = (b & ByteCompanionObject.MIN_VALUE) == 128 ? 1 : (b & 192) == 64 ? 2 : (b & 224) == 32 ? 3 : (b & 240) == 16 ? 4 : 0;
        integerHolder.setValue(i);
        if (i2 <= 0) {
            return null;
        }
        integerHolder.setValue(integerHolder.getValue() + i2);
        return BitAssistant.subArray(bArr, i, i2);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r4v10, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r4v10, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long readSignedInteger(byte[] r4) {
        /*
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L_0x0011
            byte r4 = r4[r1]
            r0 = 127(0x7f, float:1.78E-43)
            if (r4 <= r0) goto L_0x009f
        L_0x000e:
            int r4 = ~r4
            long r0 = (long) r4
            return r0
        L_0x0011:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 2
            if (r0 != r2) goto L_0x0021
            int r4 = fm.liveswitch.Binary.fromBytes16(r4, r1, r1)
            r0 = 32767(0x7fff, float:4.5916E-41)
            if (r4 <= r0) goto L_0x009f
            goto L_0x000e
        L_0x0021:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 3
            if (r0 != r2) goto L_0x0032
            int r4 = fm.liveswitch.Binary.fromBytes24(r4, r1, r1)
            r0 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r4 <= r0) goto L_0x009f
            goto L_0x000e
        L_0x0032:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 4
            if (r0 != r2) goto L_0x0046
            long r0 = fm.liveswitch.Binary.fromBytes32(r4, r1, r1)
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x009f
            long r0 = ~r0
            return r0
        L_0x0046:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 5
            if (r0 != r2) goto L_0x005c
            long r0 = fm.liveswitch.Binary.fromBytes40(r4, r1, r1)
            r2 = 549755813887(0x7fffffffff, double:2.71615461243E-312)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x009f
            long r0 = ~r0
            return r0
        L_0x005c:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 6
            if (r0 != r2) goto L_0x0072
            long r0 = fm.liveswitch.Binary.fromBytes48(r4, r1, r1)
            r2 = 140737488355327(0x7fffffffffff, double:6.95335580783495E-310)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x009f
            long r0 = ~r0
            return r0
        L_0x0072:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 7
            if (r0 != r2) goto L_0x0088
            long r0 = fm.liveswitch.Binary.fromBytes56(r4, r1, r1)
            r2 = 36028797018963967(0x7fffffffffffff, double:2.8480945388892175E-306)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x009f
            long r0 = ~r0
            return r0
        L_0x0088:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r4)
            r2 = 8
            if (r0 != r2) goto L_0x009f
            long r0 = fm.liveswitch.Binary.fromBytes64(r4, r1, r1)
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x009f
            long r0 = ~r0
            return r0
        L_0x009f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.matroska.Element.readSignedInteger(byte[]):long");
    }

    public static String readString(byte[] bArr) {
        return Encoding.getUtf8().getString(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long readUnsignedInteger(byte[] r3) {
        /*
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L_0x000c
            byte r3 = r3[r1]
        L_0x000a:
            long r0 = (long) r3
            return r0
        L_0x000c:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 2
            if (r0 != r2) goto L_0x0018
            int r3 = fm.liveswitch.Binary.fromBytes16(r3, r1, r1)
            goto L_0x000a
        L_0x0018:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 3
            if (r0 != r2) goto L_0x0024
            int r3 = fm.liveswitch.Binary.fromBytes24(r3, r1, r1)
            goto L_0x000a
        L_0x0024:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 4
            if (r0 != r2) goto L_0x0030
            long r0 = fm.liveswitch.Binary.fromBytes32(r3, r1, r1)
            return r0
        L_0x0030:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 5
            if (r0 != r2) goto L_0x003c
            long r0 = fm.liveswitch.Binary.fromBytes40(r3, r1, r1)
            return r0
        L_0x003c:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 6
            if (r0 != r2) goto L_0x0048
            long r0 = fm.liveswitch.Binary.fromBytes48(r3, r1, r1)
            return r0
        L_0x0048:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 7
            if (r0 != r2) goto L_0x0054
            long r0 = fm.liveswitch.Binary.fromBytes56(r3, r1, r1)
            return r0
        L_0x0054:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r3)
            r2 = 8
            if (r0 != r2) goto L_0x0061
            long r0 = fm.liveswitch.Binary.fromBytes64(r3, r1, r1)
            return r0
        L_0x0061:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.matroska.Element.readUnsignedInteger(byte[]):long");
    }

    public static String readUtf8(byte[] bArr) {
        return Encoding.getUtf8().getString(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f1 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readValue(byte[] r5, int r6, fm.liveswitch.IntegerHolder r7) {
        /*
            byte r0 = r5[r6]
            r1 = r0 & 128(0x80, float:1.794E-43)
            r2 = 128(0x80, float:1.794E-43)
            if (r1 != r2) goto L_0x001b
            r0 = r0 & 127(0x7f, float:1.78E-43)
            long r0 = (long) r0
            int r6 = r6 + 1
            r2 = 127(0x7f, double:6.27E-322)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
        L_0x0017:
            int r0 = r0 - r6
            long r0 = (long) r0
            goto L_0x00da
        L_0x001b:
            r1 = r0 & 192(0xc0, float:2.69E-43)
            r2 = 64
            r3 = 0
            if (r1 != r2) goto L_0x0036
            int r0 = fm.liveswitch.Binary.fromBytes16(r5, r6, r3)
            r0 = r0 & 16383(0x3fff, float:2.2957E-41)
            long r0 = (long) r0
            int r6 = r6 + 2
            r2 = 16383(0x3fff, double:8.0943E-320)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x0036:
            r1 = r0 & 224(0xe0, float:3.14E-43)
            r2 = 32
            if (r1 != r2) goto L_0x0053
            int r0 = fm.liveswitch.Binary.fromBytes24(r5, r6, r3)
            r1 = 2097151(0x1fffff, float:2.938734E-39)
            r0 = r0 & r1
            long r0 = (long) r0
            int r6 = r6 + 3
            r2 = 2097151(0x1fffff, double:1.0361303E-317)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x0053:
            r1 = r0 & 240(0xf0, float:3.36E-43)
            r2 = 16
            if (r1 != r2) goto L_0x006c
            long r0 = fm.liveswitch.Binary.fromBytes32(r5, r6, r3)
            r2 = 268435455(0xfffffff, double:1.326247364E-315)
            long r0 = r0 & r2
            int r6 = r6 + 4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x006c:
            r1 = r0 & 248(0xf8, float:3.48E-43)
            r2 = 8
            if (r1 != r2) goto L_0x0087
            long r0 = fm.liveswitch.Binary.fromBytes40(r5, r6, r3)
            r2 = 34359738367(0x7ffffffff, double:1.6975966327E-313)
            long r0 = r0 & r2
            int r6 = r6 + 5
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x0087:
            r1 = r0 & 252(0xfc, float:3.53E-43)
            r2 = 4
            if (r1 != r2) goto L_0x00a2
            long r0 = fm.liveswitch.Binary.fromBytes48(r5, r6, r3)
            r2 = 4398046511103(0x3ffffffffff, double:2.172923689948E-311)
            long r0 = r0 & r2
            int r6 = r6 + 6
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x00a2:
            r1 = r0 & 254(0xfe, float:3.56E-43)
            r2 = 2
            if (r1 != r2) goto L_0x00bd
            long r0 = fm.liveswitch.Binary.fromBytes56(r5, r6, r3)
            r2 = 562949953421311(0x1ffffffffffff, double:2.781342323133997E-309)
            long r0 = r0 & r2
            int r6 = r6 + 7
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x00bd:
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 1
            if (r0 != r1) goto L_0x00d8
            long r0 = fm.liveswitch.Binary.fromBytes64(r5, r6, r3)
            r2 = 72057594037927935(0xffffffffffffff, double:7.291122019556397E-304)
            long r0 = r0 & r2
            int r6 = r6 + 8
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00da
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            goto L_0x0017
        L_0x00d8:
            r0 = -1
        L_0x00da:
            r7.setValue(r6)
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x00f1
            int r2 = r7.getValue()
            int r1 = (int) r0
            int r2 = r2 + r1
            r7.setValue(r2)
            byte[] r5 = fm.liveswitch.BitAssistant.subArray(r5, r6, r1)
            return r5
        L_0x00f1:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.matroska.Element.readValue(byte[], int, fm.liveswitch.IntegerHolder):byte[]");
    }

    public static long readVariableInteger(byte[] bArr, int i, IntegerHolder integerHolder) {
        long j;
        byte b = bArr[i];
        int i2 = 0;
        if ((b & ByteCompanionObject.MIN_VALUE) == 128) {
            i2 = 1;
            j = (long) (bArr[i] & ByteCompanionObject.MAX_VALUE);
        } else if ((b & 192) == 64) {
            byte[] subArray = BitAssistant.subArray(bArr, i, 2);
            subArray[0] = (byte) (subArray[0] & Utf8.REPLACEMENT_BYTE);
            j = (long) Binary.fromBytes16(subArray, 0, false);
            i2 = 2;
        } else if ((b & 224) == 32) {
            byte[] subArray2 = BitAssistant.subArray(bArr, i, 3);
            subArray2[0] = (byte) (subArray2[0] & 31);
            j = (long) Binary.fromBytes24(subArray2, 0, false);
            i2 = 3;
        } else if ((b & 240) == 16) {
            byte[] subArray3 = BitAssistant.subArray(bArr, i, 4);
            subArray3[0] = (byte) (subArray3[0] & 15);
            j = Binary.fromBytes32(subArray3, 0, false);
            i2 = 4;
        } else {
            j = 0;
        }
        integerHolder.setValue(i + i2);
        return j;
    }

    public static byte[] serializeVariableInteger(long j) {
        return serializeVariableInteger(j, 0);
    }

    public static byte[] serializeVariableInteger(long j, int i) {
        if (i == 0) {
            i = 1;
            while (_dataBitsMask[i] <= j) {
                i++;
            }
        }
        long leftShiftLong = j | BitAssistant.leftShiftLong(1, i * 7);
        byte[] bArr = new byte[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((int) (255 & leftShiftLong));
            leftShiftLong = BitAssistant.rightShiftLong(leftShiftLong, 8);
        }
        return bArr;
    }

    private void setLength(int i) {
        this._length = i;
    }

    private void setSize(int i) {
        this._size = i;
    }

    public void setSizeLength(int i) {
        this._sizeLength = i;
    }

    public void setWriteDefaultValues(boolean z) {
        this._writeDefaultValues = z;
    }

    /* access modifiers changed from: protected */
    public void write(byte[] bArr, byte[] bArr2, ByteOutputStream byteOutputStream) {
        byteOutputStream.writeBuffer(bArr2);
        writeVariableInteger((long) ArrayExtensions.getLength(bArr), byteOutputStream, getSizeLength());
        byteOutputStream.writeBuffer(bArr);
    }

    /* access modifiers changed from: protected */
    public void writeBool(boolean z, byte[] bArr, ByteOutputStream byteOutputStream) {
        writeUnsignedInteger(z ? 1 : 0, bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeDate(Date date, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(Binary.toBytes64((DateExtensions.getTicks(date) - DateExtensions.getTicks(_relativeDateUtc)) * ((long) _ticksPerNano), false), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeDouble(double d, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(BitAssistant.convertDoubleToBytes(d, false), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeFloat(float f, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(BitAssistant.convertSingleToBytes(f, false), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeGuid(Guid guid, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(guid.toByteArray(), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeSignedInteger(long j, byte[] bArr, ByteOutputStream byteOutputStream) {
        long j2 = 0;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        int i2 = i == 0 ? 0 : 1;
        long j3 = 4294967168L;
        if (i < 0) {
            j2 = 4294967168L;
        }
        while (i2 < 8 && (j3 & j) != j2) {
            i2++;
            j3 = BitAssistant.leftShiftLong(j3, 8);
            j2 = BitAssistant.leftShiftLong(j2, 8);
        }
        byte[] bArr2 = new byte[i2];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            bArr2[i3] = (byte) ((int) (255 & j));
            j = BitAssistant.rightShiftLong(j, 8);
        }
        write(bArr2, bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeString(String str, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(Encoding.getUtf8().getBytes(str), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeUnsignedInteger(long j, byte[] bArr, ByteOutputStream byteOutputStream) {
        int i = 0;
        for (long j2 = Long.MAX_VALUE; (j & j2) != 0 && i < 8; j2 = BitAssistant.leftShiftLong(j2, 8)) {
            i++;
        }
        byte[] bArr2 = new byte[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            bArr2[i2] = (byte) ((int) (255 & j));
            j = BitAssistant.rightShiftLong(j, 8);
        }
        write(bArr2, bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeUtf8(String str, byte[] bArr, ByteOutputStream byteOutputStream) {
        write(Encoding.getUtf8().getBytes(str), bArr, byteOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeVariableInteger(long j, ByteOutputStream byteOutputStream) {
        writeVariableInteger(j, byteOutputStream, 0);
    }

    /* access modifiers changed from: protected */
    public void writeVariableInteger(long j, ByteOutputStream byteOutputStream, int i) {
        byte[] serializeVariableInteger = serializeVariableInteger(j, i);
        byteOutputStream.writeBuffer(serializeVariableInteger, 0, ArrayExtensions.getLength(serializeVariableInteger));
    }
}
