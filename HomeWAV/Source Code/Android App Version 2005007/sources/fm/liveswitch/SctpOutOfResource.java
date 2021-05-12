package fm.liveswitch;

class SctpOutOfResource extends SctpErrorCause {
    public static byte[] getBytes(SctpOutOfResource sctpOutOfResource) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpOutOfResource.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(4, false));
        Log.debug("SCTP Error: out of resource.");
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpOutOfResource parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        integerHolder.setValue(4);
        return new SctpOutOfResource();
    }

    public SctpOutOfResource() {
        super.setCauseCode(4);
    }
}
