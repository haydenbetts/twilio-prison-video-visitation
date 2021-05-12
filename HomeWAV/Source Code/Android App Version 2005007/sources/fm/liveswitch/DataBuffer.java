package fm.liveswitch;

import androidx.core.view.InputDeviceCompat;
import com.urbanairship.actions.ToastAction;
import com.urbanairship.json.matchers.ArrayContainsMatcher;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBuffer {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DataBuffer.class);
    private int _index;
    private byte[] _innerData;
    private int _length;
    private boolean _littleEndian;

    public DataBuffer free() {
        return this;
    }

    public boolean getIsPooled() {
        return false;
    }

    public boolean getIsSubset() {
        return false;
    }

    public DataBuffer keep() {
        return this;
    }

    public static DataBuffer allocate(int i) {
        return allocate(i, false);
    }

    public static DataBuffer allocate(int i, boolean z) {
        return wrap(new byte[i], z);
    }

    public boolean and(int i, int i2) {
        return write8(i & read8(i2), i2);
    }

    public DataBuffer append(DataBuffer dataBuffer) {
        int length;
        if (dataBuffer != null && dataBuffer.getLength() > 0 && (length = getLength() + dataBuffer.getLength()) > getLength()) {
            int length2 = getLength();
            resize(length);
            write(dataBuffer, length2);
            dataBuffer.getLength();
        }
        return this;
    }

    public DataBuffer append(DataBuffer[] dataBufferArr) {
        if (dataBufferArr != null && ArrayExtensions.getLength((Object[]) dataBufferArr) > 0) {
            int length = getLength();
            for (DataBuffer length2 : dataBufferArr) {
                length += length2.getLength();
            }
            if (length > getLength()) {
                int length3 = getLength();
                resize(length);
                for (DataBuffer dataBuffer : dataBufferArr) {
                    write(dataBuffer, length3);
                    length3 += dataBuffer.getLength();
                }
            }
        }
        return this;
    }

    public static boolean areEqual(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        if (dataBuffer.getLength() != dataBuffer2.getLength()) {
            return false;
        }
        for (int i = 0; i < dataBuffer.getLength(); i++) {
            if (dataBuffer.read8(i) != dataBuffer2.read8(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean canResize(int i, int i2) {
        if (!getIsPooled() || i - getLength() <= 0) {
            return true;
        }
        if (i2 >= getIndex()) {
            if (getIndex() + i >= ArrayExtensions.getLength(getData())) {
                return false;
            }
        } else if (i2 < 0) {
            if (getIndex() + i > ArrayExtensions.getLength(getData())) {
                return false;
            }
        } else if ((getIndex() - i2) + i >= ArrayExtensions.getLength(getData())) {
            return false;
        }
        return true;
    }

    public DataBuffer clone() {
        return clone(getLittleEndian());
    }

    public DataBuffer clone(boolean z) {
        return subset(0);
    }

    public DataBuffer copy() {
        return copy(false);
    }

    public DataBuffer copy(boolean z) {
        DataBuffer dataBuffer;
        if (z) {
            dataBuffer = __dataBufferPool.take(getLength());
        } else {
            dataBuffer = allocate(getLength(), getLittleEndian());
        }
        dataBuffer.write(this);
        return dataBuffer;
    }

    private DataBuffer() {
    }

    DataBuffer(byte[] bArr, int i, int i2, boolean z) {
        setInnerData(bArr);
        setIndex(i);
        setLength(i2);
        setLittleEndian(z);
    }

    public static DataBuffer fromBytes(byte[] bArr) {
        return wrap(bArr);
    }

    public static DataBuffer fromBytes(byte[] bArr, boolean z) {
        return wrap(bArr, z);
    }

    public static DataBuffer fromHexString(String str) {
        return wrap(BitAssistant.getHexBytes(str));
    }

    public static DataBuffer fromHexString(String str, boolean z) {
        return wrap(BitAssistant.getHexBytes(str), z);
    }

    public static DataBuffer fromJson(String str) {
        return (DataBuffer) JsonSerializer.deserializeObject(str, new IFunction0<DataBuffer>() {
            public DataBuffer invoke() {
                return new DataBuffer();
            }
        }, new IAction3<DataBuffer, String, String>() {
            public void invoke(DataBuffer dataBuffer, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "data")) {
                    dataBuffer.setInnerData(Base64.decode(str2));
                } else if (Global.equals(str, ArrayContainsMatcher.INDEX_KEY)) {
                    dataBuffer.setIndex(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, ToastAction.LENGTH_KEY)) {
                    dataBuffer.setLength(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "littleEndian")) {
                    dataBuffer.setLittleEndian(JsonSerializer.deserializeBoolean(str2).getValue());
                }
            }
        });
    }

    public static DataBuffer[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.DataBuffer.fromJson";
            }

            public DataBuffer invoke(String str) {
                return DataBuffer.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (DataBuffer[]) deserializeObjectArray.toArray(new DataBuffer[0]);
    }

    public byte[] getData() {
        if (getInnerData() != null) {
            return getInnerData();
        }
        throw new RuntimeException(new Exception("This data buffer has been invalidated."));
    }

    public static DataBuffer getEmpty() {
        return allocate(0, false);
    }

    public int getIndex() {
        return this._index;
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerData() {
        return this._innerData;
    }

    public int getLength() {
        return this._length;
    }

    public boolean getLittleEndian() {
        return this._littleEndian;
    }

    public boolean or(int i, int i2) {
        return write8(i | read8(i2), i2);
    }

    public DataBuffer prepend(DataBuffer dataBuffer) {
        int length = dataBuffer.getLength();
        if (length <= getIndex()) {
            setIndex(getIndex() - length);
            setLength(getLength() + length);
        } else {
            resize(getLength() + length, 0);
        }
        write(dataBuffer, 0);
        dataBuffer.getLength();
        return this;
    }

    public void prepend(DataBuffer[] dataBufferArr) {
        if (dataBufferArr != null && ArrayExtensions.getLength((Object[]) dataBufferArr) > 0) {
            int i = 0;
            for (DataBuffer length : dataBufferArr) {
                i += length.getLength();
            }
            if (i <= getIndex()) {
                setIndex(getIndex() - i);
                setLength(getLength() + i);
            } else {
                resize(getLength() + i, 0);
            }
            int i2 = 0;
            for (DataBuffer dataBuffer : dataBufferArr) {
                write(dataBuffer, i2);
                i2 += dataBuffer.getLength();
            }
        }
    }

    public boolean read1(int i, int i2) {
        return Binary.fromBytes1(getData(), getIndex() + i, i2);
    }

    public int read10(int i, int i2) {
        return Binary.fromBytes10(getData(), getIndex() + i, i2);
    }

    public int read10Signed(int i, int i2) {
        int read10 = read10(i, i2);
        return read10 > 511 ? read10 - 1024 : read10;
    }

    public int read11(int i, int i2) {
        return Binary.fromBytes11(getData(), getIndex() + i, i2);
    }

    public int read11Signed(int i, int i2) {
        int read11 = read11(i, i2);
        return read11 > 1023 ? read11 - 2048 : read11;
    }

    public int read12(int i, int i2) {
        return Binary.fromBytes12(getData(), getIndex() + i, i2);
    }

    public int read12Signed(int i, int i2) {
        int read12 = read12(i, i2);
        return read12 > 2047 ? read12 - 4096 : read12;
    }

    public int read13(int i, int i2) {
        return Binary.fromBytes13(getData(), getIndex() + i, i2);
    }

    public int read13Signed(int i, int i2) {
        int read13 = read13(i, i2);
        return read13 > 4095 ? read13 - 8192 : read13;
    }

    public int read14(int i, int i2) {
        return Binary.fromBytes14(getData(), getIndex() + i, i2);
    }

    public int read14Signed(int i, int i2) {
        int read14 = read14(i, i2);
        return read14 > 8191 ? read14 - 16384 : read14;
    }

    public int read15(int i, int i2) {
        return Binary.fromBytes15(getData(), getIndex() + i, i2);
    }

    public int read15Signed(int i, int i2) {
        int read15 = read15(i, i2);
        return read15 > 16383 ? read15 - 32768 : read15;
    }

    public int read16(int i) {
        return Binary.fromBytes16(getData(), getIndex() + i, getLittleEndian());
    }

    public int read16(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 2);
        return read16(i);
    }

    public int read16Signed(int i) {
        int read16 = read16(i);
        return read16 > 32767 ? read16 - 65536 : read16;
    }

    public int read16Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 2);
        return read16Signed(i);
    }

    public int read17(int i, int i2) {
        return Binary.fromBytes17(getData(), getIndex() + i, i2);
    }

    public int read17Signed(int i, int i2) {
        int read17 = read17(i, i2);
        return read17 > 65535 ? read17 - 131072 : read17;
    }

    public int read18(int i, int i2) {
        return Binary.fromBytes18(getData(), getIndex() + i, i2);
    }

    public int read18Signed(int i, int i2) {
        int read18 = read18(i, i2);
        return read18 > 131071 ? read18 - 262144 : read18;
    }

    public int read19(int i, int i2) {
        return Binary.fromBytes19(getData(), getIndex() + i, i2);
    }

    public int read19Signed(int i, int i2) {
        int read19 = read19(i, i2);
        return read19 > 262143 ? read19 - 524288 : read19;
    }

    public int read2(int i, int i2) {
        return Binary.fromBytes2(getData(), getIndex() + i, i2);
    }

    public int read20(int i, int i2) {
        return Binary.fromBytes20(getData(), getIndex() + i, i2);
    }

    public int read20Signed(int i, int i2) {
        int read20 = read20(i, i2);
        return read20 > 524287 ? read20 - 1048576 : read20;
    }

    public int read21(int i, int i2) {
        return Binary.fromBytes21(getData(), getIndex() + i, i2);
    }

    public int read21Signed(int i, int i2) {
        int read21 = read21(i, i2);
        return read21 > 1048575 ? read21 - 2097152 : read21;
    }

    public int read22(int i, int i2) {
        return Binary.fromBytes22(getData(), getIndex() + i, i2);
    }

    public int read22Signed(int i, int i2) {
        int read22 = read22(i, i2);
        return read22 > 2097151 ? read22 - 4194304 : read22;
    }

    public int read23(int i, int i2) {
        return Binary.fromBytes23(getData(), getIndex() + i, i2);
    }

    public int read23Signed(int i, int i2) {
        int read23 = read23(i, i2);
        return read23 > 4194303 ? read23 - 8388608 : read23;
    }

    public int read24(int i) {
        return Binary.fromBytes24(getData(), getIndex() + i, getLittleEndian());
    }

    public int read24(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 3);
        return read24(i);
    }

    public int read24Signed(int i) {
        int read24 = read24(i);
        return read24 > 8388607 ? read24 - 16777216 : read24;
    }

    public int read24Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 3);
        return read24Signed(i);
    }

    public int read2Signed(int i, int i2) {
        int read2 = read2(i, i2);
        return read2 > 1 ? read2 - 4 : read2;
    }

    public int read3(int i, int i2) {
        return Binary.fromBytes3(getData(), getIndex() + i, i2);
    }

    public long read32(int i) {
        return Binary.fromBytes32(getData(), getIndex() + i, getLittleEndian());
    }

    public long read32(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 4);
        return read32(i);
    }

    public long read32Signed(int i) {
        long read32 = read32(i);
        return read32 > 2147483647L ? read32 - 4294967296L : read32;
    }

    public long read32Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 4);
        return read32Signed(i);
    }

    public int read3Signed(int i, int i2) {
        int read3 = read3(i, i2);
        return read3 > 3 ? read3 - 8 : read3;
    }

    public int read4(int i, int i2) {
        return Binary.fromBytes4(getData(), getIndex() + i, i2);
    }

    public long read40(int i) {
        return Binary.fromBytes40(getData(), getIndex() + i, getLittleEndian());
    }

    public long read40(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 5);
        return read40(i);
    }

    public long read40Signed(int i) {
        long read40 = read40(i);
        return read40 > 549755813887L ? read40 - 1099511627776L : read40;
    }

    public long read40Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 5);
        return read40Signed(i);
    }

    public long read48(int i) {
        return Binary.fromBytes48(getData(), getIndex() + i, getLittleEndian());
    }

    public long read48(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 6);
        return read48(i);
    }

    public long read48Signed(int i) {
        long read48 = read48(i);
        return read48 > 140737488355327L ? read48 - 281474976710656L : read48;
    }

    public long read48Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 6);
        return read48Signed(i);
    }

    public int read4Signed(int i, int i2) {
        int read4 = read4(i, i2);
        return read4 > 7 ? read4 - 16 : read4;
    }

    public int read5(int i, int i2) {
        return Binary.fromBytes5(getData(), getIndex() + i, i2);
    }

    public long read56(int i) {
        return Binary.fromBytes56(getData(), getIndex() + i, getLittleEndian());
    }

    public long read56(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 7);
        return read56(i);
    }

    public long read56Signed(int i) {
        long read56 = read56(i);
        return read56 > 36028797018963967L ? read56 - 72057594037927936L : read56;
    }

    public long read56Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 7);
        return read56Signed(i);
    }

    public int read5Signed(int i, int i2) {
        int read5 = read5(i, i2);
        return read5 > 15 ? read5 - 32 : read5;
    }

    public int read6(int i, int i2) {
        return Binary.fromBytes6(getData(), getIndex() + i, i2);
    }

    public long read64(int i) {
        return Binary.fromBytes64(getData(), getIndex() + i, getLittleEndian());
    }

    public long read64(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 8);
        return read64(i);
    }

    public long read64Signed(int i) {
        return read64(i);
    }

    public long read64Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 8);
        return read64Signed(i);
    }

    public int read6Signed(int i, int i2) {
        int read6 = read6(i, i2);
        return read6 > 31 ? read6 - 64 : read6;
    }

    public int read7(int i, int i2) {
        return Binary.fromBytes7(getData(), getIndex() + i, i2);
    }

    public int read7Signed(int i, int i2) {
        int read7 = read7(i, i2);
        return read7 > 63 ? read7 - 128 : read7;
    }

    public int read8(int i) {
        return Binary.fromBytes8(getData(), getIndex() + i);
    }

    public int read8(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 1);
        return read8(i);
    }

    public int read8Signed(int i) {
        int read8 = read8(i);
        return read8 > 127 ? read8 + InputDeviceCompat.SOURCE_ANY : read8;
    }

    public int read8Signed(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 1);
        return read8Signed(i);
    }

    public int read9(int i, int i2) {
        return Binary.fromBytes9(getData(), getIndex() + i, i2);
    }

    public int read9Signed(int i, int i2) {
        int read9 = read9(i, i2);
        return read9 > 255 ? read9 - 512 : read9;
    }

    public String readUtf8String(int i) {
        return Encoding.getUtf8().getString(getData(), getIndex() + i, getLength() - i);
    }

    public String readUtf8String(int i, int i2) {
        return Encoding.getUtf8().getString(getData(), getIndex() + i, i2);
    }

    public void resize(int i) {
        resize(i, getLength());
    }

    public void resize(int i, int i2) {
        resize(i, i2, false);
    }

    public void resize(int i, int i2, boolean z) {
        if (i2 == -1) {
            i2 = getLength();
        }
        if (i2 >= 0) {
            int length = i - getLength();
            if (length < 0) {
                int i3 = -length;
                int i4 = i2 + i3;
                if (i4 > getLength()) {
                    throw new RuntimeException(new Exception(StringExtensions.format("Truncating at offset '{0}' for length '{1}' would exceed data buffer length '{2}'.", IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(i3)), IntegerExtensions.toString(Integer.valueOf(getLength())))));
                } else if (i2 == 0) {
                    setIndex(getIndex() + i3);
                } else if (i4 != getLength()) {
                    BitAssistant.copy(getData(), getIndex() + i2 + i3, getData(), getIndex() + i2, (getLength() - i2) - i3);
                }
            } else if (length > 0) {
                if (i2 > getLength()) {
                    throw new RuntimeException(new Exception(StringExtensions.format("Offset '{0}' exceeds data buffer length '{1}'.", IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(getLength())))));
                } else if (i2 == 0 && length <= getIndex()) {
                    setIndex(getIndex() - length);
                    if (z) {
                        BitAssistant.set(getData(), getIndex(), length, 0);
                    }
                } else if (i2 != getLength() || getIndex() + getLength() + length >= ArrayExtensions.getLength(getData())) {
                    if (i > ArrayExtensions.getLength(getData())) {
                        if (!getIsPooled()) {
                            byte[] bArr = new byte[i];
                            BitAssistant.copy(getData(), getIndex(), bArr, 0, i2);
                            BitAssistant.copy(getData(), getIndex() + i2, bArr, length + i2, getLength() - i2);
                            setInnerData(bArr);
                        } else {
                            throw new RuntimeException(new Exception("Cannot expand the underlying byte array of a pooled data buffer."));
                        }
                    } else if (getIndex() + i > ArrayExtensions.getLength(getData())) {
                        int length2 = ArrayExtensions.getLength(getData()) - i;
                        BitAssistant.copy(getData(), getIndex(), getData(), length2, i2);
                        BitAssistant.copy(getData(), getIndex() + i2, getData(), length2 + i2 + length, getLength() - i2);
                        setIndex(length2);
                        if (z) {
                            BitAssistant.set(getData(), getIndex() + i2, length, 0);
                        }
                    } else {
                        BitAssistant.copy(getData(), getIndex() + i2, getData(), getIndex() + i2 + length, getLength() - i2);
                        if (z) {
                            BitAssistant.set(getData(), getIndex() + i2, length, 0);
                        }
                    }
                } else if (z) {
                    BitAssistant.set(getData(), getIndex() + getLength(), length, 0);
                }
            }
            setLength(i);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Offset '{0}' is invalid.", (Object) IntegerExtensions.toString(Integer.valueOf(i2)))));
    }

    public boolean sequenceEquals(DataBuffer dataBuffer) {
        return BitAssistant.sequencesAreEqual(getData(), getIndex(), dataBuffer.getData(), dataBuffer.getIndex(), getLength());
    }

    public boolean sequenceEqualsConstantTime(DataBuffer dataBuffer) {
        return BitAssistant.sequencesAreEqualConstantTime(getData(), getIndex(), dataBuffer.getData(), dataBuffer.getIndex(), getLength());
    }

    public void set(byte b) {
        set(b, 0, getLength());
    }

    public void set(byte b, int i) {
        BitAssistant.set(getData(), getIndex() + i, getLength() - i, b);
    }

    public void set(byte b, int i, int i2) {
        BitAssistant.set(getData(), getIndex() + i, i2, b);
    }

    /* access modifiers changed from: protected */
    public void setIndex(int i) {
        this._index = i;
    }

    /* access modifiers changed from: protected */
    public void setInnerData(byte[] bArr) {
        this._innerData = bArr;
    }

    /* access modifiers changed from: protected */
    public void setLength(int i) {
        this._length = i;
    }

    public void setLittleEndian(boolean z) {
        this._littleEndian = z;
    }

    public DataBuffer subset(int i) {
        return subset(i, getLength() - i);
    }

    public DataBuffer subset(int i, int i2) {
        if (i + i2 > getLength() || i2 < 0) {
            return null;
        }
        if (getIsPooled()) {
            return new DataBufferSubset(this, getIndex() + i, i2);
        }
        return new DataBuffer(getData(), getIndex() + i, i2, getLittleEndian());
    }

    public byte[] toArray() {
        return toBytes();
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[getLength()];
        BitAssistant.copy(getData(), getIndex(), bArr, 0, getLength());
        return bArr;
    }

    public String toHexString() {
        return BitAssistant.getHexString(getData(), getIndex(), getLength());
    }

    public static String toJson(DataBuffer dataBuffer) {
        return JsonSerializer.serializeObject(dataBuffer, new IAction2<DataBuffer, HashMap<String, String>>(dataBuffer) {
            final /* synthetic */ DataBuffer val$dataBuffer;

            {
                this.val$dataBuffer = r1;
            }

            public void invoke(DataBuffer dataBuffer, HashMap<String, String> hashMap) {
                byte[] array = this.val$dataBuffer.toArray();
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "data", Base64.encode(array));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), ArrayContainsMatcher.INDEX_KEY, JsonSerializer.serializeInteger(new NullableInteger(0)));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), ToastAction.LENGTH_KEY, JsonSerializer.serializeInteger(new NullableInteger(ArrayExtensions.getLength(array))));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "littleEndian", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$dataBuffer.getLittleEndian())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(DataBuffer[] dataBufferArr) {
        return JsonSerializer.serializeObjectArray(dataBufferArr, new IFunctionDelegate1<DataBuffer, String>() {
            public String getId() {
                return "fm.liveswitch.DataBuffer.toJson";
            }

            public String invoke(DataBuffer dataBuffer) {
                return DataBuffer.toJson(dataBuffer);
            }
        });
    }

    public boolean tryKeep() {
        try {
            keep();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean tryRead1(int i, int i2, BooleanHolder booleanHolder) {
        return Binary.tryFromBytes1(getData(), getIndex() + i, i2, booleanHolder);
    }

    public boolean tryRead10(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes10(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead11(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes11(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead12(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes12(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead13(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes13(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead14(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes14(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead15(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes15(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead16(int i, IntegerHolder integerHolder) {
        return Binary.tryFromBytes16(getData(), getIndex() + i, getLittleEndian(), integerHolder);
    }

    public boolean tryRead17(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes17(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead18(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes18(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead19(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes19(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead2(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes2(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead20(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes20(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead21(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes21(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead22(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes22(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead23(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes23(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead24(int i, IntegerHolder integerHolder) {
        return Binary.tryFromBytes24(getData(), getIndex() + i, getLittleEndian(), integerHolder);
    }

    public boolean tryRead3(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes3(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead32(int i, LongHolder longHolder) {
        return Binary.tryFromBytes32(getData(), getIndex() + i, getLittleEndian(), longHolder);
    }

    public boolean tryRead4(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes4(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead40(int i, LongHolder longHolder) {
        return Binary.tryFromBytes40(getData(), getIndex() + i, getLittleEndian(), longHolder);
    }

    public boolean tryRead48(int i, LongHolder longHolder) {
        return Binary.tryFromBytes48(getData(), getIndex() + i, getLittleEndian(), longHolder);
    }

    public boolean tryRead5(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes5(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead56(int i, LongHolder longHolder) {
        return Binary.tryFromBytes56(getData(), getIndex() + i, getLittleEndian(), longHolder);
    }

    public boolean tryRead6(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes6(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead64(int i, LongHolder longHolder) {
        return Binary.tryFromBytes64(getData(), getIndex() + i, getLittleEndian(), longHolder);
    }

    public boolean tryRead7(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes7(getData(), getIndex() + i, i2, integerHolder);
    }

    public boolean tryRead8(int i, IntegerHolder integerHolder) {
        return Binary.tryFromBytes8(getData(), getIndex() + i, integerHolder);
    }

    public boolean tryRead9(int i, int i2, IntegerHolder integerHolder) {
        return Binary.tryFromBytes9(getData(), getIndex() + i, i2, integerHolder);
    }

    public static DataBuffer wrap(byte[] bArr) {
        return wrap(bArr, 0, -1, false);
    }

    public static DataBuffer wrap(byte[] bArr, int i) {
        return wrap(bArr, i, -1, false);
    }

    public static DataBuffer wrap(byte[] bArr, int i, int i2) {
        return wrap(bArr, i, i2, false);
    }

    public static DataBuffer wrap(byte[] bArr, int i, int i2, boolean z) {
        if (bArr == null || i < 0) {
            return null;
        }
        if (i2 < 0) {
            i2 = ArrayExtensions.getLength(bArr) - i;
        }
        return new DataBuffer(bArr, i, i2, z);
    }

    public static DataBuffer wrap(byte[] bArr, int i, boolean z) {
        return wrap(bArr, i, -1, z);
    }

    public static DataBuffer wrap(byte[] bArr, boolean z) {
        return wrap(bArr, 0, -1, z);
    }

    public void write(DataBuffer dataBuffer) {
        write(dataBuffer, 0);
    }

    public void write(DataBuffer dataBuffer, int i) {
        BitAssistant.copy(dataBuffer.getData(), dataBuffer.getIndex(), getData(), getIndex() + i, dataBuffer.getLength());
    }

    public void write(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(dataBuffer.getLength() + i);
        write(dataBuffer, i);
    }

    public boolean write1(boolean z, int i, int i2) {
        return Binary.toBytes1(z, i2, getData(), getIndex() + i) != null;
    }

    public boolean write10(int i, int i2, int i3) {
        return Binary.toBytes10(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write11(int i, int i2, int i3) {
        return Binary.toBytes11(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write12(int i, int i2, int i3) {
        return Binary.toBytes12(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write13(int i, int i2, int i3) {
        return Binary.toBytes13(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write14(int i, int i2, int i3) {
        return Binary.toBytes14(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write15(int i, int i2, int i3) {
        return Binary.toBytes15(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write16(int i, int i2) {
        return Binary.toBytes16(i, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write16(int i, int i2, IntegerHolder integerHolder) {
        integerHolder.setValue(i2 + 2);
        return write16(i, i2);
    }

    public boolean write17(int i, int i2, int i3) {
        return Binary.toBytes17(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write18(int i, int i2, int i3) {
        return Binary.toBytes18(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write19(int i, int i2, int i3) {
        return Binary.toBytes19(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write2(int i, int i2, int i3) {
        return Binary.toBytes2(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write20(int i, int i2, int i3) {
        return Binary.toBytes20(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write21(int i, int i2, int i3) {
        return Binary.toBytes21(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write22(int i, int i2, int i3) {
        return Binary.toBytes22(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write23(int i, int i2, int i3) {
        return Binary.toBytes23(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write24(int i, int i2) {
        return Binary.toBytes24(i, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public boolean write24(int i, int i2, IntegerHolder integerHolder) {
        integerHolder.setValue(i2 + 3);
        return write24(i, i2);
    }

    public boolean write3(int i, int i2, int i3) {
        return Binary.toBytes3(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write32(long j, int i) {
        return Binary.toBytes32(j, getLittleEndian(), getData(), getIndex() + i) != null;
    }

    public boolean write32(long j, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 4);
        return write32(j, i);
    }

    public boolean write4(int i, int i2, int i3) {
        return Binary.toBytes4(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write40(long j, int i) {
        return Binary.toBytes40(j, getLittleEndian(), getData(), getIndex() + i) != null;
    }

    public boolean write40(long j, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 5);
        return write40(j, i);
    }

    public boolean write48(long j, int i) {
        return Binary.toBytes48(j, getLittleEndian(), getData(), getIndex() + i) != null;
    }

    public boolean write48(long j, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 6);
        return write48(j, i);
    }

    public boolean write5(int i, int i2, int i3) {
        return Binary.toBytes5(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write56(long j, int i) {
        return Binary.toBytes56(j, getLittleEndian(), getData(), getIndex() + i) != null;
    }

    public boolean write56(long j, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 7);
        return write56(j, i);
    }

    public boolean write6(int i, int i2, int i3) {
        return Binary.toBytes6(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write64(long j, int i) {
        return Binary.toBytes64(j, getLittleEndian(), getData(), getIndex() + i) != null;
    }

    public boolean write64(long j, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(i + 8);
        return write64(j, i);
    }

    public boolean write7(int i, int i2, int i3) {
        return Binary.toBytes7(i, i3, getData(), getIndex() + i2) != null;
    }

    public boolean write8(int i, int i2) {
        return Binary.toBytes8(i, getData(), getIndex() + i2) != null;
    }

    public boolean write8(int i, int i2, IntegerHolder integerHolder) {
        integerHolder.setValue(i2 + 1);
        return write8(i, i2);
    }

    public boolean write9(int i, int i2, int i3) {
        return Binary.toBytes9(i, i3, getLittleEndian(), getData(), getIndex() + i2) != null;
    }

    public void writeBytes(byte[] bArr) {
        writeBytes(bArr, 0, ArrayExtensions.getLength(bArr), 0);
    }

    public void writeBytes(byte[] bArr, int i, int i2, int i3) {
        if (i3 + i2 <= getLength()) {
            BitAssistant.copy(bArr, i, getData(), getIndex() + i3, i2);
            return;
        }
        throw new RuntimeException(new Exception("Would write out of the allowed bounds for this data buffer."));
    }

    public void writeBytes(byte[] bArr, int i, int i2, int i3, IntegerHolder integerHolder) {
        integerHolder.setValue(i3 + i2);
        writeBytes(bArr, i, i2, i3);
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        writeBytes(bArr, i, ArrayExtensions.getLength(bArr) - i, i2);
    }

    public void writeBytes(byte[] bArr, int i) {
        writeBytes(bArr, 0, ArrayExtensions.getLength(bArr), i);
    }

    public boolean xor(int i, int i2) {
        return write8(i ^ read8(i2), i2);
    }
}
