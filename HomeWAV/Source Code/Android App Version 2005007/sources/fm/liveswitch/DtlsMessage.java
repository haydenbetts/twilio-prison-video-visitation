package fm.liveswitch;

import java.util.ArrayList;

class DtlsMessage {
    private int _contentType;
    private int _epoch;
    private int _handshakeFragmentLength;
    private int _handshakeFragmentOffset;
    private int _handshakeLength;
    private int _handshakeMessageSequence;
    private byte[] _handshakePayload;
    private int _handshakeType;
    private int _length;
    private DataBuffer _payload;
    private int _protocolVersionMajor;
    private int _protocolVersionMinor;
    private DataBuffer _raw;
    private long _sequenceNumber;

    public int getContentType() {
        return this._contentType;
    }

    public int getEpoch() {
        return this._epoch;
    }

    public int getHandshakeFragmentLength() {
        return this._handshakeFragmentLength;
    }

    public int getHandshakeFragmentOffset() {
        return this._handshakeFragmentOffset;
    }

    public int getHandshakeLength() {
        return this._handshakeLength;
    }

    public int getHandshakeMessageSequence() {
        return this._handshakeMessageSequence;
    }

    public byte[] getHandshakePayload() {
        return this._handshakePayload;
    }

    public int getHandshakeType() {
        return this._handshakeType;
    }

    public int getLength() {
        return this._length;
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public int getProtocolVersionMajor() {
        return this._protocolVersionMajor;
    }

    public int getProtocolVersionMinor() {
        return this._protocolVersionMinor;
    }

    public DataBuffer getRaw() {
        return this._raw;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public static DtlsMessage parse(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        if (dataBuffer.getLength() < i + 13) {
            integerHolder.setValue(i);
            return null;
        }
        int read16 = dataBuffer.read16(i + 11) + 13;
        if (dataBuffer.getLength() < i + read16) {
            integerHolder.setValue(i);
            return null;
        }
        DtlsMessage dtlsMessage = new DtlsMessage();
        dtlsMessage.setRaw(dataBuffer.subset(i, read16));
        IntegerHolder integerHolder2 = new IntegerHolder(i);
        int read8 = dataBuffer.read8(i, integerHolder2);
        int value = integerHolder2.getValue();
        dtlsMessage.setContentType(read8);
        IntegerHolder integerHolder3 = new IntegerHolder(value);
        int read82 = dataBuffer.read8(value, integerHolder3);
        int value2 = integerHolder3.getValue();
        dtlsMessage.setProtocolVersionMajor(read82);
        IntegerHolder integerHolder4 = new IntegerHolder(value2);
        int read83 = dataBuffer.read8(value2, integerHolder4);
        int value3 = integerHolder4.getValue();
        dtlsMessage.setProtocolVersionMinor(read83);
        IntegerHolder integerHolder5 = new IntegerHolder(value3);
        int read162 = dataBuffer.read16(value3, integerHolder5);
        int value4 = integerHolder5.getValue();
        dtlsMessage.setEpoch(read162);
        IntegerHolder integerHolder6 = new IntegerHolder(value4);
        long read48 = dataBuffer.read48(value4, integerHolder6);
        int value5 = integerHolder6.getValue();
        dtlsMessage.setSequenceNumber(read48);
        IntegerHolder integerHolder7 = new IntegerHolder(value5);
        int read163 = dataBuffer.read16(value5, integerHolder7);
        int value6 = integerHolder7.getValue();
        dtlsMessage.setLength(read163);
        dtlsMessage.setPayload(dataBuffer.subset(value6, dtlsMessage.getLength()));
        int length = value6 + dtlsMessage.getLength();
        if (dtlsMessage.getContentType() == DtlsContentType.getHandshake()) {
            DataBuffer payload = dtlsMessage.getPayload();
            IntegerHolder integerHolder8 = new IntegerHolder(0);
            int read84 = payload.read8(0, integerHolder8);
            integerHolder8.getValue();
            dtlsMessage.setHandshakeType(read84);
        } else {
            dtlsMessage.setHandshakeType(-1);
        }
        integerHolder.setValue(length);
        return dtlsMessage;
    }

    public static DtlsMessage[] parseMultiple(DataBuffer dataBuffer) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            DtlsMessage parse = parse(dataBuffer, i, integerHolder);
            int value = integerHolder.getValue();
            if (parse == null) {
                return (DtlsMessage[]) arrayList.toArray(new DtlsMessage[0]);
            }
            arrayList.add(parse);
            i = value;
        }
    }

    public void setContentType(int i) {
        this._contentType = i;
    }

    public void setEpoch(int i) {
        this._epoch = i;
    }

    public void setHandshakeFragmentLength(int i) {
        this._handshakeFragmentLength = i;
    }

    public void setHandshakeFragmentOffset(int i) {
        this._handshakeFragmentOffset = i;
    }

    public void setHandshakeLength(int i) {
        this._handshakeLength = i;
    }

    public void setHandshakeMessageSequence(int i) {
        this._handshakeMessageSequence = i;
    }

    public void setHandshakePayload(byte[] bArr) {
        this._handshakePayload = bArr;
    }

    public void setHandshakeType(int i) {
        this._handshakeType = i;
    }

    public void setLength(int i) {
        this._length = i;
    }

    public void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }

    public void setProtocolVersionMajor(int i) {
        this._protocolVersionMajor = i;
    }

    public void setProtocolVersionMinor(int i) {
        this._protocolVersionMinor = i;
    }

    public void setRaw(DataBuffer dataBuffer) {
        this._raw = dataBuffer;
    }

    public void setSequenceNumber(long j) {
        this._sequenceNumber = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, DtlsContentType.getDisplayName(getContentType()));
        if (getContentType() == DtlsContentType.getHandshake()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(" - ", DtlsHandshakeType.getDisplayName(getHandshakeType())));
        }
        StringBuilderExtensions.append(sb, StringExtensions.concat((Object[]) new String[]{" (epoch: ", IntegerExtensions.toString(Integer.valueOf(getEpoch())), "; sequence number: ", LongExtensions.toString(Long.valueOf(getSequenceNumber())), "; length: ", IntegerExtensions.toString(Integer.valueOf(getLength())), ")"}));
        return sb.toString();
    }
}
