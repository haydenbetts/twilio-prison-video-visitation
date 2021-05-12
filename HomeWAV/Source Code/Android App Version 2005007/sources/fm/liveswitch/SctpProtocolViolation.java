package fm.liveswitch;

class SctpProtocolViolation extends SctpErrorCause {
    private byte[] _additionalInfo;

    public byte[] getAdditionalInfo() {
        return this._additionalInfo;
    }

    public static byte[] getBytes(SctpProtocolViolation sctpProtocolViolation) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpProtocolViolation.getCauseCode(), false));
        if (sctpProtocolViolation.getAdditionalInfo() != null) {
            byteCollection.addRange(sctpProtocolViolation.getAdditionalInfo());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpProtocolViolation parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        byte[] bArr2;
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            String str = "";
            if (fromBytes16 > 4) {
                bArr2 = new byte[(fromBytes16 - 4)];
                BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
                try {
                    str = Encoding.getUtf8().getString(bArr2, 0, ArrayExtensions.getLength(bArr2));
                } catch (Exception unused) {
                }
            } else {
                bArr2 = null;
            }
            Log.debug(StringExtensions.format("SCTP Error: SCTP protocol violation. {0}", (Object) str));
            integerHolder.setValue(fromBytes16);
            return new SctpProtocolViolation(bArr2);
        } catch (Exception unused2) {
            Log.warn("Could not parse ProtocolViolation");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpProtocolViolation() {
        super.setCauseCode(13);
    }

    public SctpProtocolViolation(byte[] bArr) {
        super.setCauseCode(13);
        setAdditionalInfo(bArr);
    }

    public void setAdditionalInfo(byte[] bArr) {
        this._additionalInfo = bArr;
    }
}
