package fm.liveswitch;

import java.util.ArrayList;

class SctpAbortChunk extends SctpControlChunk {
    private SctpErrorCause[] _errorCauses;
    private boolean _verificationTagReflected;

    public static byte[] getBytes(SctpAbortChunk sctpAbortChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpAbortChunk.getType());
        byteCollection.add(sctpAbortChunk.getVerificationTagReflected() ? (byte) 1 : 0);
        int i = 4;
        if (sctpAbortChunk.getErrorCauses() != null) {
            for (SctpErrorCause sctpErrorCause : sctpAbortChunk.getErrorCauses()) {
                i += ArrayExtensions.getLength(sctpErrorCause.getBytes());
                byteCollection.addRange(sctpErrorCause.getBytes());
            }
        }
        byteCollection.insertRange(2, Binary.toBytes16(i, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpErrorCause[] getErrorCauses() {
        return this._errorCauses;
    }

    public boolean getVerificationTagReflected() {
        return this._verificationTagReflected;
    }

    public static SctpAbortChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        boolean z = true;
        try {
            if (bArr[1] != 1) {
                z = false;
            }
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = 4;
            if (fromBytes16 == 4) {
                integerHolder.setValue(4);
                return new SctpAbortChunk(z);
            }
            ArrayList arrayList = new ArrayList();
            while (i < fromBytes16) {
                IntegerHolder integerHolder2 = new IntegerHolder(0);
                SctpErrorCause parseBytes = SctpErrorCause.parseBytes(bArr, i, integerHolder2);
                int value = integerHolder2.getValue();
                if (parseBytes == null) {
                    integerHolder.setValue(0);
                    return null;
                }
                i += value;
                arrayList.add(parseBytes);
            }
            integerHolder.setValue(i);
            return new SctpAbortChunk(z, (SctpErrorCause[]) arrayList.toArray(new SctpErrorCause[0]));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpAbortChunk(boolean z) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getAbort());
        setVerificationTagReflected(z);
    }

    public SctpAbortChunk(boolean z, SctpErrorCause[] sctpErrorCauseArr) {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getAbort());
        setVerificationTagReflected(z);
        setErrorCauses(sctpErrorCauseArr);
        super.setUnrecognized(false);
    }

    public void setErrorCauses(SctpErrorCause[] sctpErrorCauseArr) {
        this._errorCauses = sctpErrorCauseArr;
    }

    public void setVerificationTagReflected(boolean z) {
        this._verificationTagReflected = z;
    }
}
