package fm.liveswitch;

class SctpPadChunkParameter extends SctpTlvParameter {
    private int __length = 4;

    public static byte[] getBytes(SctpPadChunkParameter sctpPadChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(32773, false));
        byteCollection.addRange(Binary.toBytes16(sctpPadChunkParameter.__length, false));
        byte[] bArr = new byte[4];
        for (int i = sctpPadChunkParameter.__length - 4; i > 0; i -= 4) {
            byteCollection.addRange(bArr);
        }
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpPadChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(fromBytes16);
            return new SctpPadChunkParameter(fromBytes16);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.debug("Could not read PadChunkParameter.");
            return null;
        }
    }

    public SctpPadChunkParameter(int i) {
        if (4 % 4 == 0) {
            this.__length = i;
            super.setType(32773);
            return;
        }
        throw new RuntimeException(new Exception("Valid padding parameter size is between 4 and 65536 bytes"));
    }
}
