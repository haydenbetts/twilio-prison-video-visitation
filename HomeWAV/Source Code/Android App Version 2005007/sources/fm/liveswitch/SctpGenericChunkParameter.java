package fm.liveswitch;

class SctpGenericChunkParameter extends SctpTlvParameter {
    private byte[] _meaningfulData;

    public static byte[] getBytes(SctpGenericChunkParameter sctpGenericChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpGenericChunkParameter.getType(), false));
        int length = ArrayExtensions.getLength(sctpGenericChunkParameter.getMeaningfulData()) + 4;
        byteCollection.addRange(Binary.toBytes16(length, false));
        byteCollection.addRange(sctpGenericChunkParameter.getMeaningfulData());
        int i = 4 - (length % 4);
        if (i != 4) {
            byteCollection.addRange(new byte[i]);
        }
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getMeaningfulData() {
        return this._meaningfulData;
    }

    public static SctpGenericChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(ArrayExtensions.getLength(bArr));
            return new SctpGenericChunkParameter(bArr);
        } catch (Exception unused) {
            Log.debug("Could not read SupportedExtensionsChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpGenericChunkParameter(byte[] bArr) {
        super.setType(Binary.fromBytes16(bArr, 0, false));
        setMeaningfulData(new byte[(Binary.fromBytes16(bArr, 2, false) - 4)]);
        BitAssistant.copy(bArr, 4, getMeaningfulData(), 0, ArrayExtensions.getLength(getMeaningfulData()));
    }

    public void setMeaningfulData(byte[] bArr) {
        this._meaningfulData = bArr;
    }

    public static SctpGenericChunkParameter toGenericParameter(SctpTlvParameter sctpTlvParameter) {
        try {
            return new SctpGenericChunkParameter(sctpTlvParameter.getBytes());
        } catch (Exception unused) {
            Log.debug("Could not read SupportedExtensionsChunkParameter.");
            return null;
        }
    }
}
