package fm.liveswitch;

class SctpDataChunk extends SctpChunk {
    private boolean _abandoned;
    private boolean _acked;
    private boolean _beginning;
    private boolean _ending;
    private SctpMessage _message;
    private long _payloadProtocolIdentifier;
    private boolean _raised;
    private boolean _sackImmediately;
    private int _streamIdentifier;
    private int _streamSequenceNumber;
    private long _transmissionTime;
    private long _tsn;
    private boolean _unordered;
    private byte[] _userData;

    public static long addTSN(long j, long j2) {
        long j3 = j + j2;
        return j3 > 4294967295L ? (j3 - 4294967295L) - 1 : j3;
    }

    public static int compareSsns(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        if (j >= 0) {
            if (j2 < 0) {
                return 1;
            }
            double d = (double) j;
            if (d <= 52428.0d || ((double) j2) >= 13107.0d) {
                return ((((double) j2) <= 52428.0d || d >= 13107.0d) && i <= 0) ? 2 : 1;
            }
            return 2;
        }
    }

    public static int compareTsns(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        if (j >= 0) {
            if (j2 < 0) {
                return 1;
            }
            double d = (double) j;
            if (d <= 3.435973836E9d || ((double) j2) >= 8.58993459E8d) {
                return ((((double) j2) <= 3.435973836E9d || d >= 8.58993459E8d) && i <= 0) ? 2 : 1;
            }
            return 2;
        }
    }

    public static long decrementTSN(long j) {
        if (j <= 1) {
            return 4294967295L;
        }
        return j - 1;
    }

    public static int incrementSSN(int i) {
        if (i > 65534) {
            return 0;
        }
        return i + 1;
    }

    public static long incrementTSN(long j) {
        if (j > 4294967294L) {
            return 0;
        }
        return j + 1;
    }

    public static long subtractTSN(long j, long j2) {
        if (j < 0 || j2 < 0) {
            return 0;
        }
        return (((double) j2) <= 3.435973836E9d || ((double) j) >= 8.58993459E8d) ? j - j2 : (4294967295L - j2) + 1 + j;
    }

    public boolean equals(Object obj) {
        try {
            return getTsn() == ((SctpDataChunk) obj).getTsn();
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean getAbandoned() {
        return this._abandoned;
    }

    public boolean getAcked() {
        return this._acked;
    }

    public boolean getBeginning() {
        return this._beginning;
    }

    public static byte[] getBytes(SctpDataChunk sctpDataChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpDataChunk.getType());
        byteCollection.add((((sctpDataChunk.getSackImmediately() ? (char) 8 : 0) | sctpDataChunk.getEnding()) | (sctpDataChunk.getBeginning() ? (char) 2 : 0)) | (sctpDataChunk.getUnordered() ? (char) 4 : 0) ? (byte) 1 : 0);
        byteCollection.addRange(Binary.toBytes32(sctpDataChunk.getTsn(), false));
        byteCollection.addRange(Binary.toBytes16(sctpDataChunk.getStreamIdentifier(), false));
        byteCollection.addRange(Binary.toBytes16(sctpDataChunk.getStreamSequenceNumber(), false));
        byteCollection.addRange(Binary.toBytes32(sctpDataChunk.getPayloadProtocolIdentifier(), false));
        byteCollection.addRange(sctpDataChunk.getUserData());
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public boolean getEnding() {
        return this._ending;
    }

    /* access modifiers changed from: package-private */
    public SctpMessage getMessage() {
        return this._message;
    }

    public long getPayloadProtocolIdentifier() {
        return this._payloadProtocolIdentifier;
    }

    public boolean getRaised() {
        return this._raised;
    }

    public boolean getSackImmediately() {
        return this._sackImmediately;
    }

    public int getStreamIdentifier() {
        return this._streamIdentifier;
    }

    public int getStreamSequenceNumber() {
        return this._streamSequenceNumber;
    }

    public long getTransmissionTime() {
        return this._transmissionTime;
    }

    public long getTsn() {
        return this._tsn;
    }

    public boolean getUnordered() {
        return this._unordered;
    }

    public byte[] getUserData() {
        return this._userData;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static long maxTsns(long j, long j2) {
        return compareTsns(j, j2) == 1 ? j : j2;
    }

    public static long minTsns(long j, long j2) {
        return compareTsns(j, j2) == 1 ? j2 : j;
    }

    public static SctpDataChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        byte[] bArr2 = bArr;
        IntegerHolder integerHolder2 = integerHolder;
        try {
            boolean z = (bArr2[1] & 1) == 1;
            boolean z2 = (bArr2[1] & 2) == 2;
            boolean z3 = (bArr2[1] & 4) == 4;
            boolean z4 = (bArr2[1] & 8) == 8;
            int fromBytes16 = Binary.fromBytes16(bArr2, 2, false);
            long fromBytes32 = Binary.fromBytes32(bArr2, 4, false);
            int fromBytes162 = Binary.fromBytes16(bArr2, 8, false);
            int fromBytes163 = Binary.fromBytes16(bArr2, 10, false);
            long fromBytes322 = Binary.fromBytes32(bArr2, 12, false);
            int i = fromBytes16 - 16;
            byte[] bArr3 = new byte[i];
            BitAssistant.copy(bArr2, 16, bArr3, 0, i);
            integerHolder2.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpDataChunk(z3, z2, z, fromBytes32, fromBytes162, fromBytes163, fromBytes322, bArr3, z4);
        } catch (Exception unused) {
            Log.warn("Could not parse SCTP Data chunk");
            integerHolder2.setValue(0);
            return null;
        }
    }

    public SctpDataChunk(boolean z, boolean z2, boolean z3, long j, int i, int i2, long j2, byte[] bArr, boolean z4) {
        super.setType(SctpChunkType.getData());
        setEnding(z3);
        setUnordered(z);
        setBeginning(z2);
        setTsn(j);
        setStreamIdentifier(i);
        setStreamSequenceNumber(i2);
        setPayloadProtocolIdentifier(j2);
        setUserData(bArr);
        setSackImmediately(z4);
        setRaised(false);
        setTransmissionTime(-1);
        super.setUnrecognized(false);
    }

    public void setAbandoned(boolean z) {
        this._abandoned = z;
    }

    public void setAcked(boolean z) {
        this._acked = z;
    }

    public void setBeginning(boolean z) {
        this._beginning = z;
    }

    public void setEnding(boolean z) {
        this._ending = z;
    }

    /* access modifiers changed from: package-private */
    public void setMessage(SctpMessage sctpMessage) {
        this._message = sctpMessage;
    }

    public void setPayloadProtocolIdentifier(long j) {
        this._payloadProtocolIdentifier = j;
    }

    public void setRaised(boolean z) {
        this._raised = z;
    }

    public void setSackImmediately(boolean z) {
        this._sackImmediately = z;
    }

    public void setStreamIdentifier(int i) {
        this._streamIdentifier = i;
    }

    public void setStreamSequenceNumber(int i) {
        this._streamSequenceNumber = i;
    }

    public void setTransmissionTime(long j) {
        this._transmissionTime = j;
    }

    public void setTsn(long j) {
        this._tsn = j;
    }

    public void setUnordered(boolean z) {
        this._unordered = z;
    }

    public void setUserData(byte[] bArr) {
        this._userData = bArr;
    }

    public String toString() {
        return LongExtensions.toString(Long.valueOf(getTsn()));
    }
}
