package fm.liveswitch;

class SctpSackChunk extends SctpControlChunk {
    public static int _unset = -1;
    private long _advertisedReceiverWindowCredit;
    private long _cumulativeTsnAck;
    private long[] _duplicateTsns;
    private SctpGapAckBlock[] _gapAckBlocks;

    public boolean equals(SctpSackChunk sctpSackChunk) {
        if (getCumulativeTsnAck() != sctpSackChunk.getCumulativeTsnAck() || ArrayExtensions.getLength((Object[]) getGapAckBlocks()) != ArrayExtensions.getLength((Object[]) sctpSackChunk.getGapAckBlocks())) {
            return false;
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getGapAckBlocks()); i++) {
            if (getGapAckBlocks()[i].getAbsoluteGapAckBlockStart() != sctpSackChunk.getGapAckBlocks()[i].getAbsoluteGapAckBlockStart() || getGapAckBlocks()[i].getAbsoluteGapAckBlockEnd() != sctpSackChunk.getGapAckBlocks()[i].getAbsoluteGapAckBlockEnd()) {
                return false;
            }
        }
        return true;
    }

    public long getAdvertisedReceiverWindowCredit() {
        return this._advertisedReceiverWindowCredit;
    }

    public static byte[] getBytes(SctpSackChunk sctpSackChunk, int i) {
        if (i == _unset || 16 <= i) {
            ByteCollection byteCollection = new ByteCollection();
            byteCollection.add((byte) sctpSackChunk.getType());
            byteCollection.add((byte) 0);
            int min = i != _unset ? MathAssistant.min(sctpSackChunk.getNumberOfGapAckBlocks(), (int) MathAssistant.floor((double) ((i - 16) / 4))) : sctpSackChunk.getNumberOfGapAckBlocks();
            int i2 = (min * 4) + 16;
            int min2 = i != _unset ? MathAssistant.min(sctpSackChunk.getNumberOfDuplicateTsns(), (int) MathAssistant.floor((double) ((i - i2) / 4))) : sctpSackChunk.getNumberOfDuplicateTsns();
            byteCollection.addRange(Binary.toBytes16(i2 + (min2 * 4), false));
            byteCollection.addRange(Binary.toBytes32(sctpSackChunk.getCumulativeTsnAck(), false));
            byteCollection.addRange(Binary.toBytes32(sctpSackChunk.getAdvertisedReceiverWindowCredit(), false));
            byteCollection.addRange(Binary.toBytes16(min, false));
            byteCollection.addRange(Binary.toBytes16(min2, false));
            for (int i3 = 0; i3 < min; i3++) {
                byteCollection.addRange(sctpSackChunk.getGapAckBlocks()[i3].getBytes(sctpSackChunk.getCumulativeTsnAck()));
            }
            for (int i4 = 0; i4 < min2; i4++) {
                byteCollection.addRange(Binary.toBytes32(sctpSackChunk.getDuplicateTsns()[i4], false));
            }
            return byteCollection.toArray();
        }
        throw new RuntimeException(new Exception("SCTP chunk limit is set too low and prevents SCTP SACK chunk dispatching."));
    }

    public byte[] getBytes() {
        return getBytes(this, _unset);
    }

    public byte[] getBytes(int i) {
        return getBytes(this, i);
    }

    public long getCumulativeTsnAck() {
        return this._cumulativeTsnAck;
    }

    public long[] getDuplicateTsns() {
        return this._duplicateTsns;
    }

    public SctpGapAckBlock[] getGapAckBlocks() {
        return this._gapAckBlocks;
    }

    public int getNumberOfDuplicateTsns() {
        if (getDuplicateTsns() == null) {
            return 0;
        }
        return ArrayExtensions.getLength(getDuplicateTsns());
    }

    public int getNumberOfGapAckBlocks() {
        if (getGapAckBlocks() == null) {
            return 0;
        }
        return ArrayExtensions.getLength((Object[]) getGapAckBlocks());
    }

    public static SctpSackChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            long fromBytes32 = Binary.fromBytes32(bArr, 4, false);
            long fromBytes322 = Binary.fromBytes32(bArr, 8, false);
            int fromBytes16 = Binary.fromBytes16(bArr, 12, false);
            int fromBytes162 = Binary.fromBytes16(bArr, 14, false);
            SctpSackChunk sctpSackChunk = new SctpSackChunk(fromBytes32, fromBytes322, (SctpGapAckBlock[]) null, (long[]) null);
            int i = 16;
            if (fromBytes16 > 0) {
                SctpGapAckBlock[] sctpGapAckBlockArr = new SctpGapAckBlock[fromBytes16];
                for (int i2 = 0; i2 < fromBytes16; i2++) {
                    sctpGapAckBlockArr[i2] = SctpGapAckBlock.parseBytes(bArr, i, fromBytes32, integerHolder);
                    i += integerHolder.getValue();
                }
                sctpSackChunk.setGapAckBlocks(sctpGapAckBlockArr);
            }
            if (fromBytes162 > 0) {
                long[] jArr = new long[fromBytes162];
                for (int i3 = 0; i3 < fromBytes162; i3++) {
                    jArr[i3] = Binary.fromBytes32(bArr, i, false);
                    i += 4;
                }
                sctpSackChunk.setDuplicateTsns(jArr);
            }
            integerHolder.setValue(i);
            return sctpSackChunk;
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpSackChunk(long j, long j2, SctpGapAckBlock[] sctpGapAckBlockArr, long[] jArr) {
        super.setType(SctpChunkType.getSack());
        setCumulativeTsnAck(j);
        setAdvertisedReceiverWindowCredit(j2);
        setGapAckBlocks(sctpGapAckBlockArr == null ? new SctpGapAckBlock[0] : sctpGapAckBlockArr);
        setDuplicateTsns(jArr);
        super.setCanBundleWithDataAndSackChunks(true);
        super.setUnrecognized(false);
    }

    public void setAdvertisedReceiverWindowCredit(long j) {
        this._advertisedReceiverWindowCredit = j;
    }

    public void setCumulativeTsnAck(long j) {
        this._cumulativeTsnAck = j;
    }

    public void setDuplicateTsns(long[] jArr) {
        this._duplicateTsns = jArr;
    }

    public void setGapAckBlocks(SctpGapAckBlock[] sctpGapAckBlockArr) {
        this._gapAckBlocks = sctpGapAckBlockArr;
    }

    public String toString() {
        String format = StringExtensions.format("SCTP SACK. Cumulative: {0}. Number of blocks: {1}.", LongExtensions.toString(Long.valueOf(getCumulativeTsnAck())), IntegerExtensions.toString(Integer.valueOf(getNumberOfGapAckBlocks())));
        if (getNumberOfGapAckBlocks() > 0) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) getGapAckBlocks()); i++) {
                String concat = StringExtensions.concat(format, " ");
                SctpGapAckBlock sctpGapAckBlock = getGapAckBlocks()[i];
                format = StringExtensions.concat(concat, StringExtensions.format("Block {0}: start {1}, end {2}.", IntegerExtensions.toString(Integer.valueOf(i)), LongExtensions.toString(Long.valueOf(sctpGapAckBlock.getAbsoluteGapAckBlockStart())), LongExtensions.toString(Long.valueOf(sctpGapAckBlock.getAbsoluteGapAckBlockEnd()))));
            }
            format = StringExtensions.concat(format, ".");
        }
        String concat2 = StringExtensions.concat(format, StringExtensions.format(" Advertised receiver window credit: {0}. Number of duplicate TSNs: {1}.", LongExtensions.toString(Long.valueOf(getAdvertisedReceiverWindowCredit())), IntegerExtensions.toString(Integer.valueOf(getNumberOfDuplicateTsns()))));
        if (getNumberOfDuplicateTsns() <= 0) {
            return concat2;
        }
        String concat3 = StringExtensions.concat(concat2, "Duplicates: ");
        for (int i2 = 0; i2 < ArrayExtensions.getLength(getDuplicateTsns()); i2++) {
            concat3 = StringExtensions.concat(concat3, LongExtensions.toString(Long.valueOf(getDuplicateTsns()[i2])), ", ");
        }
        return StringExtensions.substring(concat3, 0, StringExtensions.getLength(concat3) - 1);
    }
}
