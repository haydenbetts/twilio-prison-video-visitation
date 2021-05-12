package fm.liveswitch;

import java.util.ArrayList;

class SctpErrorChunk extends SctpControlChunk {
    private SctpErrorCause[] _errorCauses;

    public static byte[] getBytes(SctpErrorChunk sctpErrorChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpErrorChunk.getType());
        byteCollection.add((byte) 0);
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) sctpErrorChunk.getErrorCauses()); i++) {
            byteCollection.addRange(sctpErrorChunk.getErrorCauses()[i].getBytes());
        }
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpErrorCause[] getErrorCauses() {
        return this._errorCauses;
    }

    public static SctpErrorChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = 4;
            ArrayList arrayList = new ArrayList();
            while (i < fromBytes16) {
                SctpErrorCause parseBytes = SctpErrorCause.parseBytes(bArr, i, integerHolder);
                if (parseBytes == null) {
                    integerHolder.setValue(0);
                    return null;
                }
                i += integerHolder.getValue();
                arrayList.add(parseBytes);
            }
            integerHolder.setValue(i);
            return new SctpErrorChunk((SctpErrorCause[]) arrayList.toArray(new SctpErrorCause[0]));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.warn("Could not parse SCTP Error chunk");
            return null;
        }
    }

    public SctpErrorChunk(SctpErrorCause[] sctpErrorCauseArr) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getError());
        setErrorCauses(sctpErrorCauseArr);
        super.setUnrecognized(false);
    }

    public void setErrorCauses(SctpErrorCause[] sctpErrorCauseArr) {
        this._errorCauses = sctpErrorCauseArr;
    }
}
