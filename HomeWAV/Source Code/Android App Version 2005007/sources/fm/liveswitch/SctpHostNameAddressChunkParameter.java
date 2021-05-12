package fm.liveswitch;

class SctpHostNameAddressChunkParameter extends SctpTlvParameter {
    private String _hostName;

    public static byte[] getBytes(SctpHostNameAddressChunkParameter sctpHostNameAddressChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(11, false));
        byte[] bytes = Encoding.getUtf8().getBytes(sctpHostNameAddressChunkParameter.getHostName());
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes) + 4, false));
        byteCollection.addRange(bytes);
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public String getHostName() {
        return this._hostName;
    }

    public static SctpHostNameAddressChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(fromBytes16);
            return new SctpHostNameAddressChunkParameter(Encoding.getUtf8().getString(bArr, 4, fromBytes16 - 4));
        } catch (Exception unused) {
            Log.debug("Could not read HostNameAddressChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpHostNameAddressChunkParameter(String str) {
        super.setType(11);
        setHostName(str);
    }

    public void setHostName(String str) {
        this._hostName = str;
    }
}
