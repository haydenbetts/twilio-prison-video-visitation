package fm.liveswitch.ogg;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.Encoding;
import fm.liveswitch.MathAssistant;
import kotlin.UByte;

public class Page {
    private static int[] _crcLookup = new int[256];
    private int _absoluteGranulePosition;
    private boolean _beginningOfStream;
    private boolean _continuedPacket;
    private boolean _endOfStream;
    private byte[] _payload;
    private int _sequenceNumber;
    private int _streamSerialNumber;

    private static int calculateChecksum(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        int i4 = 0;
        while (i < i3) {
            i4 = _crcLookup[((i4 >> 24) & UByte.MAX_VALUE) ^ (bArr[i] & UByte.MAX_VALUE)] ^ (i4 << 8);
            i++;
        }
        return i4;
    }

    public int getAbsoluteGranulePosition() {
        return this._absoluteGranulePosition;
    }

    public boolean getBeginningOfStream() {
        return this._beginningOfStream;
    }

    public byte[] getBytes() {
        int i;
        boolean continuedPacket = getContinuedPacket();
        if (getBeginningOfStream()) {
            continuedPacket |= true;
        }
        if (getEndOfStream()) {
            continuedPacket |= true;
        }
        int i2 = 27;
        if (getPayload() != null) {
            i = (int) MathAssistant.ceil(((double) (ArrayExtensions.getLength(getPayload()) + 1)) / 255.0d);
            if (i <= 255) {
                i2 = 27 + ArrayExtensions.getLength(getPayload()) + i;
            } else {
                throw new RuntimeException(new Exception("Payload exceeds Ogg page limits, and Ogg page-splitting is not currently supported."));
            }
        } else {
            i = 0;
        }
        DataBufferStream dataBufferStream = new DataBufferStream(i2, true);
        dataBufferStream.writeBytes(Encoding.getUtf8().getBytes("OggS"));
        dataBufferStream.write8(0);
        dataBufferStream.write8(continuedPacket ? 1 : 0);
        dataBufferStream.write64((long) getAbsoluteGranulePosition());
        dataBufferStream.write32((long) getStreamSerialNumber());
        dataBufferStream.write32((long) getSequenceNumber());
        int position = dataBufferStream.getPosition();
        dataBufferStream.write32(0);
        dataBufferStream.write8(i);
        if (getPayload() != null) {
            int length = ArrayExtensions.getLength(getPayload());
            for (int i3 = 0; i3 < i; i3++) {
                int min = MathAssistant.min(255, length);
                dataBufferStream.write8(min);
                length -= min;
            }
            dataBufferStream.writeBytes(getPayload());
        }
        dataBufferStream.setPosition(position);
        dataBufferStream.write32((long) calculateChecksum(dataBufferStream.getBuffer().getData(), dataBufferStream.getBuffer().getIndex(), dataBufferStream.getBuffer().getLength()));
        return dataBufferStream.getBuffer().toArray();
    }

    public boolean getContinuedPacket() {
        return this._continuedPacket;
    }

    public boolean getEndOfStream() {
        return this._endOfStream;
    }

    public byte[] getPayload() {
        return this._payload;
    }

    public int getSequenceNumber() {
        return this._sequenceNumber;
    }

    public int getStreamSerialNumber() {
        return this._streamSerialNumber;
    }

    static {
        for (int i = 0; i < ArrayExtensions.getLength(_crcLookup); i++) {
            int i2 = i << 24;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (((long) i2) & 2147483648L) != 0 ? (i2 << 1) ^ 79764919 : i2 << 1;
            }
            _crcLookup[i] = i2 & -1;
        }
    }

    public Page() {
        setStreamSerialNumber(1);
    }

    public void setAbsoluteGranulePosition(int i) {
        this._absoluteGranulePosition = i;
    }

    public void setBeginningOfStream(boolean z) {
        this._beginningOfStream = z;
    }

    public void setContinuedPacket(boolean z) {
        this._continuedPacket = z;
    }

    public void setEndOfStream(boolean z) {
        this._endOfStream = z;
    }

    public void setPayload(byte[] bArr) {
        this._payload = bArr;
    }

    public void setSequenceNumber(int i) {
        this._sequenceNumber = i;
    }

    public void setStreamSerialNumber(int i) {
        this._streamSerialNumber = i;
    }
}
