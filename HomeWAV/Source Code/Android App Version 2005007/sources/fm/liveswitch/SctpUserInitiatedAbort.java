package fm.liveswitch;

class SctpUserInitiatedAbort extends SctpErrorCause {
    private byte[] _upperLayerAbortReason;

    public static byte[] getBytes(SctpUserInitiatedAbort sctpUserInitiatedAbort) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpUserInitiatedAbort.getCauseCode(), false));
        if (sctpUserInitiatedAbort.getUpperLayerAbortReason() != null) {
            byteCollection.addRange(sctpUserInitiatedAbort.getUpperLayerAbortReason());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getUpperLayerAbortReason() {
        return this._upperLayerAbortReason;
    }

    public static SctpUserInitiatedAbort parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        byte[] bArr2;
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            Log.debug("SCTP Error: user initiated abort");
            if (fromBytes16 > 4) {
                bArr2 = new byte[(fromBytes16 - 4)];
                BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            } else {
                bArr2 = null;
            }
            integerHolder.setValue(fromBytes16);
            return new SctpUserInitiatedAbort(bArr2);
        } catch (Exception unused) {
            Log.warn("Could not parse User initiated abort");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpUserInitiatedAbort() {
        super.setCauseCode(12);
    }

    public SctpUserInitiatedAbort(byte[] bArr) {
        super.setCauseCode(12);
        setUpperLayerAbortReason(bArr);
    }

    public void setUpperLayerAbortReason(byte[] bArr) {
        this._upperLayerAbortReason = bArr;
    }
}
