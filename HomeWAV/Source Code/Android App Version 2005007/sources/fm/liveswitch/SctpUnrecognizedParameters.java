package fm.liveswitch;

import java.util.ArrayList;

class SctpUnrecognizedParameters extends SctpErrorCause {
    private SctpTlvParameter[] _parameters;

    public static byte[] getBytes(SctpUnrecognizedParameters sctpUnrecognizedParameters) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpUnrecognizedParameters.getCauseCode(), false));
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpUnrecognizedParameters.getParameters()); i++) {
            byteCollection.addRange(sctpUnrecognizedParameters.getParameters()[i].getBytes());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpTlvParameter[] getParameters() {
        return this._parameters;
    }

    public static SctpUnrecognizedParameters parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = 4;
            ArrayList arrayList = new ArrayList();
            while (i < fromBytes16) {
                SctpTlvParameter parseBytes = SctpTlvParameter.parseBytes(bArr, i, integerHolder);
                Log.debug(StringExtensions.format("SCTP Error: unrecognized parameter {0}", (Object) IntegerExtensions.toString(Integer.valueOf(parseBytes.getType()))));
                i += integerHolder.getValue();
                arrayList.add(parseBytes);
            }
            integerHolder.setValue(i);
            return new SctpUnrecognizedParameters((SctpTlvParameter[]) arrayList.toArray(new SctpTlvParameter[0]));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpUnrecognizedParameters(SctpTlvParameter[] sctpTlvParameterArr) {
        super.setCauseCode(8);
        setParameters(sctpTlvParameterArr);
    }

    public void setParameters(SctpTlvParameter[] sctpTlvParameterArr) {
        this._parameters = sctpTlvParameterArr;
    }
}
