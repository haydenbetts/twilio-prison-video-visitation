package fm.liveswitch;

class SctpGapAckBlock {
    private long _absoluteGapAckBlockEnd;
    private long _absoluteGapAckBlockStart;

    public long getAbsoluteGapAckBlockEnd() {
        return this._absoluteGapAckBlockEnd;
    }

    public long getAbsoluteGapAckBlockStart() {
        return this._absoluteGapAckBlockStart;
    }

    public static byte[] getBytes(SctpGapAckBlock sctpGapAckBlock, long j) {
        ByteCollection byteCollection = new ByteCollection();
        long subtractTSN = SctpDataChunk.subtractTSN(sctpGapAckBlock.getAbsoluteGapAckBlockStart(), j);
        long subtractTSN2 = SctpDataChunk.subtractTSN(sctpGapAckBlock.getAbsoluteGapAckBlockEnd(), j);
        byteCollection.addRange(Binary.toBytes16((int) subtractTSN, false));
        byteCollection.addRange(Binary.toBytes16((int) subtractTSN2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes(long j) {
        return getBytes(this, j);
    }

    public static SctpGapAckBlock parseBytes(byte[] bArr, int i, long j, IntegerHolder integerHolder) {
        try {
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
            long addTSN = SctpDataChunk.addTSN((long) Binary.fromBytes16(bArr2, 0, false), j);
            long addTSN2 = SctpDataChunk.addTSN((long) Binary.fromBytes16(bArr2, 2, false), j);
            integerHolder.setValue(4);
            return new SctpGapAckBlock(addTSN, addTSN2);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpGapAckBlock(long j, long j2) {
        setAbsoluteGapAckBlockStart(j);
        setAbsoluteGapAckBlockEnd(j2);
    }

    public void setAbsoluteGapAckBlockEnd(long j) {
        this._absoluteGapAckBlockEnd = j;
    }

    public void setAbsoluteGapAckBlockStart(long j) {
        this._absoluteGapAckBlockStart = j;
    }
}
