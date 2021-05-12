package fm.liveswitch;

class SctpShutdownCompleteChunk extends SctpControlChunk {
    private boolean _verificationTagReflected;

    public static byte[] getBytes(SctpShutdownCompleteChunk sctpShutdownCompleteChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpShutdownCompleteChunk.getType());
        byteCollection.add(sctpShutdownCompleteChunk.getVerificationTagReflected() ? (byte) 1 : 0);
        byteCollection.insertRange(2, Binary.toBytes16(4, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public boolean getVerificationTagReflected() {
        return this._verificationTagReflected;
    }

    public static SctpShutdownCompleteChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        boolean z = true;
        try {
            if (bArr[1] != 1) {
                z = false;
            }
            integerHolder.setValue(4);
            return new SctpShutdownCompleteChunk(z);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpShutdownCompleteChunk(boolean z) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getShutdownComplete());
        setVerificationTagReflected(z);
        super.setUnrecognized(false);
    }

    public void setVerificationTagReflected(boolean z) {
        this._verificationTagReflected = z;
    }
}
