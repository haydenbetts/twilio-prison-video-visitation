package fm.liveswitch;

class SctpPadChunk extends SctpChunk {
    private int __length = 4;

    public static byte[] getBytes(SctpPadChunk sctpPadChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpPadChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(Binary.toBytes16(sctpPadChunk.__length, false));
        byte[] bArr = new byte[4];
        for (int i = sctpPadChunk.__length - 4; i > 0; i -= 4) {
            byteCollection.addRange(bArr);
        }
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpPadChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(fromBytes16);
            return new SctpPadChunk(fromBytes16);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpPadChunk(int i) {
        if (4 % 4 == 0) {
            this.__length = i;
            super.setUnrecognized(false);
            super.setType(SctpChunkType.getPad());
            return;
        }
        throw new RuntimeException(new Exception("Valid padding chunk size is between 4 and 65536 bytes"));
    }
}
