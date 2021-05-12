package fm.liveswitch;

class SctpUnrecognizedChunkType extends SctpErrorCause {
    private SctpChunk _unrecognizedChunk;

    public static byte[] getBytes(SctpUnrecognizedChunkType sctpUnrecognizedChunkType) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpUnrecognizedChunkType.getCauseCode(), false));
        try {
            byte[] bytes = sctpUnrecognizedChunkType.getUnrecognizedChunk().getBytes();
            byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes) + 4, false));
            byteCollection.addRange(bytes);
        } catch (Exception unused) {
            Log.debug("SCTP: could not process bytes of an unknown chunk");
        }
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpChunk getUnrecognizedChunk() {
        return this._unrecognizedChunk;
    }

    public static SctpUnrecognizedChunkType parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            SctpUnrecognizedChunkType sctpUnrecognizedChunkType = new SctpUnrecognizedChunkType(SctpChunk.parseBytes(bArr, 4, integerHolder));
            Log.debug(StringExtensions.format("SCTP Error: unrecognized chunk type: {0}", (Object) IntegerExtensions.toString(Integer.valueOf(sctpUnrecognizedChunkType.getUnrecognizedChunk().getType()))));
            integerHolder.setValue(integerHolder.getValue() + 4);
            return sctpUnrecognizedChunkType;
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpUnrecognizedChunkType(SctpChunk sctpChunk) {
        super.setCauseCode(6);
        setUnrecognizedChunk(sctpChunk);
    }

    public void setUnrecognizedChunk(SctpChunk sctpChunk) {
        this._unrecognizedChunk = sctpChunk;
    }
}
