package fm.liveswitch;

class SctpIPv4ChunkParameter extends SctpTlvParameter {
    private String _iPv4Address;

    public static byte[] getBytes(SctpIPv4ChunkParameter sctpIPv4ChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        try {
            byteCollection.addRange(Binary.toBytes16(5, false));
            byteCollection.addRange(Binary.toBytes16(8, false));
            byteCollection.addRange(LocalNetwork.getAddressBytes(sctpIPv4ChunkParameter.getIPv4Address()));
            return byteCollection.toArray();
        } catch (Exception unused) {
            Log.error("SCTP: could not generate IPv4ChunkParameter");
            return null;
        }
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public String getIPv4Address() {
        return this._iPv4Address;
    }

    public static SctpIPv4ChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(8);
            return new SctpIPv4ChunkParameter(LocalNetwork.getAddress(BitAssistant.subArray(bArr, 4, 4)));
        } catch (Exception unused) {
            Log.debug("Could not read IPv4ChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpIPv4ChunkParameter(String str) {
        super.setType(5);
        setIPv4Address(str);
    }

    public void setIPv4Address(String str) {
        this._iPv4Address = str;
    }
}
