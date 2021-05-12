package fm.liveswitch;

class SctpUnresolvableAddress extends SctpErrorCause {
    private SctpTlvParameter _address;

    public SctpTlvParameter getAddress() {
        return this._address;
    }

    public static byte[] getBytes(SctpUnresolvableAddress sctpUnresolvableAddress) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpUnresolvableAddress.getCauseCode(), false));
        byte[] bytes = sctpUnresolvableAddress.getAddress().getBytes();
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes) + 4, false));
        byteCollection.addRange(bytes);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpUnresolvableAddress parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            SctpUnresolvableAddress sctpUnresolvableAddress = new SctpUnresolvableAddress(SctpTlvParameter.parseBytes(bArr, 4, integerHolder));
            integerHolder.setValue(integerHolder.getValue() + 4);
            Log.debug("SCTP Error: unresolvable address.");
            return sctpUnresolvableAddress;
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpUnresolvableAddress(SctpTlvParameter sctpTlvParameter) {
        super.setCauseCode(5);
        setAddress(sctpTlvParameter);
    }

    public void setAddress(SctpTlvParameter sctpTlvParameter) {
        this._address = sctpTlvParameter;
    }
}
