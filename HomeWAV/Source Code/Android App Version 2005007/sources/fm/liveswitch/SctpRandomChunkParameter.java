package fm.liveswitch;

class SctpRandomChunkParameter extends SctpTlvParameter {
    private byte[] _randomNumber;

    public static byte[] getBytes(SctpRandomChunkParameter sctpRandomChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpRandomChunkParameter.getType(), false));
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(sctpRandomChunkParameter.getRandomNumber()) + 4, false));
        byteCollection.addRange(sctpRandomChunkParameter.getRandomNumber());
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getRandomNumber() {
        return this._randomNumber;
    }

    public static SctpRandomChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            byte[] bArr2 = new byte[(fromBytes16 - 4)];
            BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpRandomChunkParameter(bArr2);
        } catch (Exception unused) {
            Log.debug("Could not read RandomChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpRandomChunkParameter(byte[] bArr) {
        super.setType(32770);
        setRandomNumber(bArr);
    }

    public void setRandomNumber(byte[] bArr) {
        this._randomNumber = bArr;
    }
}
