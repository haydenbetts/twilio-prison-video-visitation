package fm.liveswitch;

class SctpMissingMandatoryParameter extends SctpErrorCause {
    private int[] _missingParameters;

    public static byte[] getBytes(SctpMissingMandatoryParameter sctpMissingMandatoryParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpMissingMandatoryParameter.getCauseCode(), false));
        int length = ArrayExtensions.getLength(sctpMissingMandatoryParameter.getMissingParameters());
        byteCollection.addRange(Binary.toBytes16((length * 2) + 8, false));
        byteCollection.addRange(Binary.toBytes32((long) length, false));
        for (int i = 0; i < length; i++) {
            byteCollection.addRange(Binary.toBytes16(sctpMissingMandatoryParameter.getMissingParameters()[i], false));
        }
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public int[] getMissingParameters() {
        return this._missingParameters;
    }

    public static SctpMissingMandatoryParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes32 = (int) Binary.fromBytes32(bArr, 4, false);
            int[] iArr = new int[fromBytes32];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fromBytes32; i++) {
                iArr[i] = Binary.fromBytes16(bArr, (i * 2) + 8, false);
                StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(iArr[i])));
                if (i < fromBytes32 - 1) {
                    StringBuilderExtensions.append(sb, ", ");
                }
            }
            Log.debug(StringExtensions.concat("SCTP Error: missing mandatory parameters ", sb.toString()));
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpMissingMandatoryParameter(iArr);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpMissingMandatoryParameter(int[] iArr) {
        super.setCauseCode(2);
        setMissingParameters(iArr);
    }

    public void setMissingParameters(int[] iArr) {
        this._missingParameters = iArr;
    }
}
