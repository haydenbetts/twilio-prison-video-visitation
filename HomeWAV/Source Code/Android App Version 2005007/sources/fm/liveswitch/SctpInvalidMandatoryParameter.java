package fm.liveswitch;

class SctpInvalidMandatoryParameter extends SctpErrorCause {
    public static byte[] getBytes(SctpInvalidMandatoryParameter sctpInvalidMandatoryParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpInvalidMandatoryParameter.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(4, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpInvalidMandatoryParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        integerHolder.setValue(4);
        Log.debug("SCTP Error: invalid mandatory parameter.");
        return new SctpInvalidMandatoryParameter();
    }

    public SctpInvalidMandatoryParameter() {
        super.setCauseCode(7);
    }
}
