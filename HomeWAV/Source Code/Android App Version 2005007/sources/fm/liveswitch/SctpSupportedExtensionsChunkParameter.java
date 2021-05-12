package fm.liveswitch;

class SctpSupportedExtensionsChunkParameter extends SctpTlvParameter {
    private byte[] _supportedChunkExtensions;

    public static byte[] getBytes(SctpSupportedExtensionsChunkParameter sctpSupportedExtensionsChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpSupportedExtensionsChunkParameter.getType(), false));
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(sctpSupportedExtensionsChunkParameter.getSupportedChunkExtensions()) + 4, false));
        byteCollection.addRange(sctpSupportedExtensionsChunkParameter.getSupportedChunkExtensions());
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getSupportedChunkExtensions() {
        return this._supportedChunkExtensions;
    }

    public static SctpSupportedExtensionsChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            byte[] bArr2 = new byte[(fromBytes16 - 4)];
            BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpSupportedExtensionsChunkParameter(bArr2);
        } catch (Exception unused) {
            Log.debug("Could not read SupportedExtensionsChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpSupportedExtensionsChunkParameter(byte[] bArr) {
        super.setType(32776);
        setSupportedChunkExtensions(bArr);
    }

    public void setSupportedChunkExtensions(byte[] bArr) {
        this._supportedChunkExtensions = bArr;
    }
}
