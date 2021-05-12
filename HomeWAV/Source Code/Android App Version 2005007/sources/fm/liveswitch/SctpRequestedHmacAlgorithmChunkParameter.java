package fm.liveswitch;

class SctpRequestedHmacAlgorithmChunkParameter extends SctpTlvParameter {
    private int[] _hmacIdentifiers;

    public static byte[] getBytes(SctpRequestedHmacAlgorithmChunkParameter sctpRequestedHmacAlgorithmChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpRequestedHmacAlgorithmChunkParameter.getType(), false));
        byteCollection.addRange(Binary.toBytes16((ArrayExtensions.getLength(sctpRequestedHmacAlgorithmChunkParameter.getHmacIdentifiers()) * 2) + 4, false));
        for (int bytes16 : sctpRequestedHmacAlgorithmChunkParameter.getHmacIdentifiers()) {
            byteCollection.addRange(Binary.toBytes16(bytes16, false));
        }
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public int[] getHmacIdentifiers() {
        return this._hmacIdentifiers;
    }

    public static SctpRequestedHmacAlgorithmChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int[] iArr = new int[((fromBytes16 - 4) / 2)];
            for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
                iArr[i] = Binary.fromBytes16(bArr, (i * 2) + 4, false);
            }
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpRequestedHmacAlgorithmChunkParameter(iArr);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.debug("Could not read RequestedHMACAlgorithmChunkParameter.");
            return null;
        }
    }

    public SctpRequestedHmacAlgorithmChunkParameter(int[] iArr) {
        super.setType(32772);
        setHmacIdentifiers(iArr);
    }

    public void setHmacIdentifiers(int[] iArr) {
        this._hmacIdentifiers = iArr;
    }
}
