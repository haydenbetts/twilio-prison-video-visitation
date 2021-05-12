package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Binary;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;

public class Block extends Element {
    private byte[] _data;
    private byte _flags;
    private int _timecode;
    private long _trackNumber;

    public static byte[] getEbmlId() {
        return new byte[]{-95};
    }

    public Block() {
    }

    public Block(byte[] bArr) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        long readVariableInteger = Element.readVariableInteger(bArr, 0, integerHolder);
        int value = integerHolder.getValue();
        setTrackNumber(readVariableInteger);
        setTimecode(Binary.fromBytes16(bArr, value, false));
        int i = value + 2;
        setFlags((byte) Binary.fromBytes8(bArr, i));
        int i2 = i + 1;
        if (i2 < ArrayExtensions.getLength(bArr)) {
            setData(BitAssistant.subArray(bArr, i2));
        }
    }

    public byte[] getData() {
        return this._data;
    }

    public byte getFlags() {
        return this._flags;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        if (getFlags() == BlockFlags.getEbmlLacing() || getFlags() == BlockFlags.getFixedSizeLacing() || getFlags() == BlockFlags.getXiphLacing()) {
            return null;
        }
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        super.writeVariableInteger(getTrackNumber(), byteOutputStream);
        byteOutputStream.writeBuffer(Binary.toBytes16(getTimecode(), false));
        byteOutputStream.writeBuffer(Binary.toBytes8(getFlags()));
        if (getData() != null) {
            byteOutputStream.writeBuffer(getData());
        }
        return byteOutputStream.toArray();
    }

    public int getTimecode() {
        return this._timecode;
    }

    public long getTrackNumber() {
        return this._trackNumber;
    }

    public void setData(byte[] bArr) {
        this._data = bArr;
    }

    public void setFlags(byte b) {
        this._flags = b;
    }

    public void setTimecode(int i) {
        this._timecode = i;
    }

    public void setTrackNumber(long j) {
        this._trackNumber = j;
    }
}
