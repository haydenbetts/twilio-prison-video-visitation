package fm.liveswitch;

class SctpForwardTsnChunk extends SctpControlChunk {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(SctpForwardTsnChunk.class);
    public static int _unset = -1;
    private long _newCumulativeTsnAck;
    private int[][] _streamStreamSequencePairs;

    public static byte[] getBytes(SctpForwardTsnChunk sctpForwardTsnChunk, int i) {
        int i2;
        int[][] streamStreamSequencePairs = sctpForwardTsnChunk.getStreamStreamSequencePairs();
        if (streamStreamSequencePairs == null) {
            i2 = 0;
        } else {
            i2 = ArrayExtensions.getLength((Object[]) streamStreamSequencePairs);
        }
        int i3 = 8;
        int i4 = (i2 * 4) + 8;
        if (i == _unset || i4 <= i) {
            DataBuffer take = __dataBufferPool.take(i4);
            take.write8((byte) sctpForwardTsnChunk.getType(), 0);
            take.write8(0, 1);
            take.write16(i4, 2);
            take.write32(sctpForwardTsnChunk.getNewCumulativeTsnAck(), 4);
            for (int[] iArr : streamStreamSequencePairs) {
                take.write16(iArr[0], i3);
                take.write16(iArr[1], i3 + 2);
                i3 += 4;
            }
            byte[] array = take.toArray();
            take.free();
            return array;
        }
        throw new RuntimeException(new Exception("SCTP chunk limit is set too low and prevents SCTP Forward TSN chunk dispatching."));
    }

    public byte[] getBytes() {
        return getBytes(this, _unset);
    }

    public byte[] getBytes(int i) {
        return getBytes(this, i);
    }

    public long getNewCumulativeTsnAck() {
        return this._newCumulativeTsnAck;
    }

    public int[][] getStreamStreamSequencePairs() {
        return this._streamStreamSequencePairs;
    }

    public static SctpForwardTsnChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int i = 8;
            int fromBytes16 = (Binary.fromBytes16(bArr, 2, false) - 8) / 4;
            SctpForwardTsnChunk sctpForwardTsnChunk = new SctpForwardTsnChunk(Binary.fromBytes32(bArr, 4, false));
            if (fromBytes16 > 0) {
                int[][] iArr = new int[fromBytes16][];
                for (int i2 = 0; i2 < fromBytes16; i2++) {
                    int fromBytes162 = Binary.fromBytes16(bArr, i, false);
                    int i3 = i + 2;
                    int fromBytes163 = Binary.fromBytes16(bArr, i3, false);
                    i = i3 + 2;
                    iArr[i2] = new int[]{fromBytes162, fromBytes163};
                }
                sctpForwardTsnChunk.setStreamStreamSequencePairs(iArr);
            }
            integerHolder.setValue(i);
            return sctpForwardTsnChunk;
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpForwardTsnChunk(long j) {
        this(j, (int[][]) null);
    }

    public SctpForwardTsnChunk(long j, int[][] iArr) {
        super.setType(SctpChunkType.getForwardCumulativeTSN());
        setNewCumulativeTsnAck(j);
        setStreamStreamSequencePairs(iArr);
        super.setCanBundleWithDataAndSackChunks(true);
        super.setUnrecognized(false);
    }

    public void setNewCumulativeTsnAck(long j) {
        this._newCumulativeTsnAck = j;
    }

    public void setStreamStreamSequencePairs(int[][] iArr) {
        this._streamStreamSequencePairs = iArr;
    }
}
