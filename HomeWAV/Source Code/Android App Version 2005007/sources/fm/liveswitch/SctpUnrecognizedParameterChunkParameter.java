package fm.liveswitch;

import java.util.ArrayList;

class SctpUnrecognizedParameterChunkParameter extends SctpTlvParameter {
    private SctpTlvParameter[] _unrecognizedParameters;

    public static byte[] getBytes(SctpUnrecognizedParameterChunkParameter sctpUnrecognizedParameterChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(8, false));
        for (SctpTlvParameter bytes : sctpUnrecognizedParameterChunkParameter.getUnrecognizedParameters()) {
            byteCollection.addRange(bytes.getBytes());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpTlvParameter[] getUnrecognizedParameters() {
        return this._unrecognizedParameters;
    }

    public static SctpUnrecognizedParameterChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        ArrayList arrayList = new ArrayList();
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = 4;
            if (fromBytes16 <= 4) {
                Log.debug("SCTP Unrecognized Parameter Chunk must contain at least one parameter");
                integerHolder.setValue(fromBytes16);
                return null;
            }
            boolean z = true;
            while (z) {
                SctpTlvParameter parseBytes = SctpTlvParameter.parseBytes(bArr, i, integerHolder);
                if (parseBytes == null) {
                    integerHolder.setValue(0);
                    return null;
                }
                arrayList.add(parseBytes);
                i += integerHolder.getValue();
                if (i >= fromBytes16) {
                    z = false;
                }
            }
            integerHolder.setValue(i);
            return new SctpUnrecognizedParameterChunkParameter((SctpTlvParameter[]) arrayList.toArray(new SctpTlvParameter[0]));
        } catch (Exception unused) {
            Log.debug("Could not parse SCTP Unrecognized Parameter Chunk Parameter");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpUnrecognizedParameterChunkParameter(SctpTlvParameter[] sctpTlvParameterArr) {
        super.setType(8);
        setUnrecognizedParameters(sctpTlvParameterArr);
    }

    public void setUnrecognizedParameters(SctpTlvParameter[] sctpTlvParameterArr) {
        this._unrecognizedParameters = sctpTlvParameterArr;
    }
}
