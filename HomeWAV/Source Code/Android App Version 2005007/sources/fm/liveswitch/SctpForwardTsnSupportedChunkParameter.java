package fm.liveswitch;

class SctpForwardTsnSupportedChunkParameter extends SctpTlvParameter {
    private int __length = 4;

    public static byte[] getBytes(SctpForwardTsnSupportedChunkParameter sctpForwardTsnSupportedChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(SctpParameterType.ForwardTsnSupportedParameter, false));
        byteCollection.addRange(Binary.toBytes16(sctpForwardTsnSupportedChunkParameter.__length, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpForwardTsnSupportedChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(4);
            return new SctpForwardTsnSupportedChunkParameter();
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.debug("Could not read ForwardTSNSupportedChunkParameter.");
            return null;
        }
    }

    public SctpForwardTsnSupportedChunkParameter() {
        super.setType(SctpParameterType.ForwardTsnSupportedParameter);
    }
}
