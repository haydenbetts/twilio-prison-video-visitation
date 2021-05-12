package fm.liveswitch;

class SctpSupportedAddressTypesChunkParameter extends SctpTlvParameter {
    private int[] _addressTypes;

    public int[] getAddressTypes() {
        return this._addressTypes;
    }

    public static byte[] getBytes(SctpSupportedAddressTypesChunkParameter sctpSupportedAddressTypesChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(12, false));
        byteCollection.addRange(Binary.toBytes16((ArrayExtensions.getLength(sctpSupportedAddressTypesChunkParameter.getAddressTypes()) * 2) + 4, false));
        for (int i = 0; i < ArrayExtensions.getLength(sctpSupportedAddressTypesChunkParameter.getAddressTypes()); i++) {
            byteCollection.addRange(Binary.toBytes16(sctpSupportedAddressTypesChunkParameter.getAddressTypes()[i], false));
        }
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpSupportedAddressTypesChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = (Binary.fromBytes16(bArr, 2, false) - 4) / 2;
            int[] iArr = new int[fromBytes16];
            for (int i = 0; i < fromBytes16; i++) {
                iArr[i] = Binary.fromBytes16(bArr, (i * 2) + 4, false);
            }
            int fromBytes162 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(fromBytes162 + SctpChunk.calculatePaddingBytes(fromBytes162));
            return new SctpSupportedAddressTypesChunkParameter(iArr);
        } catch (Exception unused) {
            Log.debug("Could not read SupportedAddressTypesChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpSupportedAddressTypesChunkParameter(int[] iArr) {
        super.setType(12);
        setAddressTypes(iArr);
    }

    public void setAddressTypes(int[] iArr) {
        this._addressTypes = iArr;
    }
}
