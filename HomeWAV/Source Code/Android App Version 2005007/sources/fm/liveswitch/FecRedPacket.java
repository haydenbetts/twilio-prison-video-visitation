package fm.liveswitch;

import kotlin.jvm.internal.ByteCompanionObject;

class FecRedPacket {
    private byte[] _data;
    private int _headerLength = 0;
    private int _length;

    public static int getHighProtectionThreshold() {
        return 80;
    }

    public static int getMaxExcessOverhead() {
        return 50;
    }

    public static int getMinimumMediaPackets() {
        return 4;
    }

    public static int getRedForFecHeaderLength() {
        return 1;
    }

    public void assignPayload(byte[] bArr, int i, int i2) {
        if (this._headerLength + i2 <= getLength()) {
            BitAssistant.copy(bArr, i, getData(), this._headerLength, i2);
            return;
        }
        throw new RuntimeException(new Exception("Payload length exceeds allowed length."));
    }

    public void clearMarkerBit() {
        getData()[1] = (byte) (getData()[1] & ByteCompanionObject.MAX_VALUE);
    }

    public void createHeader(byte[] bArr, int i, int i2, int i3) {
        if (getRedForFecHeaderLength() + i <= getLength()) {
            BitAssistant.copy(bArr, 0, getData(), 0, i);
            getData()[1] = (byte) (getData()[1] & ByteCompanionObject.MIN_VALUE);
            getData()[1] = (byte) (getData()[1] + i2);
            getData()[i] = (byte) i3;
            this._headerLength = i + getRedForFecHeaderLength();
            return;
        }
        throw new RuntimeException(new Exception("Header length exceeds available length."));
    }

    public FecRedPacket(int i) {
        setData(new byte[i]);
        setLength(i);
    }

    public byte[] getData() {
        return this._data;
    }

    public int getLength() {
        return this._length;
    }

    public static void getPayloadTypes(byte[] bArr, IntegerHolder integerHolder, IntegerHolder integerHolder2, IntegerHolder integerHolder3, IntegerHolder integerHolder4) {
        integerHolder.setValue(-1);
        integerHolder3.setValue(-1);
        integerHolder2.setValue(-1);
        integerHolder4.setValue(-1);
        if (ArrayExtensions.getLength(bArr) > 0) {
            integerHolder2.setValue(0);
            integerHolder.setValue(BitAssistant.castInteger(bArr[integerHolder2.getValue()]) & 127);
            if ((BitAssistant.castInteger(bArr[integerHolder2.getValue()]) & 128) > 0 && ArrayExtensions.getLength(bArr) > 15) {
                int castInteger = ((BitAssistant.castInteger(bArr[14]) & 3) << 8) + bArr[15];
                integerHolder4.setValue(castInteger + 18);
                if (castInteger > 0 && ArrayExtensions.getLength(bArr) > integerHolder4.getValue()) {
                    integerHolder3.setValue(BitAssistant.castInteger(bArr[integerHolder4.getValue()]) & 127);
                }
            }
        }
    }

    public static void replacePayloadTypes(byte[] bArr, int i, int i2, int i3, int i4) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        IntegerHolder integerHolder3 = new IntegerHolder(0);
        IntegerHolder integerHolder4 = new IntegerHolder(0);
        getPayloadTypes(bArr, integerHolder, integerHolder2, integerHolder3, integerHolder4);
        int value = integerHolder.getValue();
        int value2 = integerHolder2.getValue();
        int value3 = integerHolder3.getValue();
        int value4 = integerHolder4.getValue();
        if (value > -1) {
            bArr[value2] = (byte) (bArr[value2] & ByteCompanionObject.MIN_VALUE);
            if (value == i) {
                bArr[value2] = (byte) (bArr[value2] | ((byte) i2));
            } else if (value == i3) {
                bArr[value2] = (byte) (bArr[value2] | ((byte) i4));
            }
        }
        if (value3 > -1) {
            bArr[value4] = (byte) (bArr[value4] & ByteCompanionObject.MIN_VALUE);
            if (value3 == i) {
                bArr[value4] = (byte) (bArr[value4] | ((byte) i2));
            } else if (value3 == i3) {
                bArr[value4] = (byte) (bArr[value4] | ((byte) i4));
            }
        }
    }

    private void setData(byte[] bArr) {
        this._data = bArr;
    }

    private void setLength(int i) {
        this._length = i;
    }

    public void setSequenceNumber(int i) {
        if (i < 0 || i >= 65536) {
            throw new RuntimeException(new Exception("Sequence number out of range."));
        }
        Binary.toBytes16(i, false, getData(), 2);
    }
}
