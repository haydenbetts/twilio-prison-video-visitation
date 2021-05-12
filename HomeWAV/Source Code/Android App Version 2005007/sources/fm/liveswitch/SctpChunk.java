package fm.liveswitch;

import kotlin.jvm.internal.ByteCompanionObject;

abstract class SctpChunk {
    private ScheduledItem _resendScheduledItem;
    private int _type;
    private boolean _unrecognized;

    public abstract byte[] getBytes();

    public static void addPadding(ByteCollection byteCollection) {
        byteCollection.addRange(new byte[calculatePaddingBytes(byteCollection.getCount())]);
    }

    public static int calculatePaddingBytes(int i) {
        int i2 = i % 4;
        if (i2 == 0) {
            return 0;
        }
        return 4 - i2;
    }

    public boolean getDoNotProcessFurtherChunksIfItIsNotRecognized() {
        return (((byte) getType()) & ByteCompanionObject.MIN_VALUE) != 128;
    }

    public boolean getReportToSenderIfItIsNotRecognized() {
        return (((byte) getType()) & 64) == 64;
    }

    public ScheduledItem getResendScheduledItem() {
        return this._resendScheduledItem;
    }

    public int getType() {
        return this._type;
    }

    public boolean getUnrecognized() {
        return this._unrecognized;
    }

    public static SctpChunk parseBytes(byte[] bArr, int i, IntegerHolder integerHolder) {
        try {
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
            if (bArr2[0] == SctpChunkType.getAbort()) {
                return SctpAbortChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getCookieAck()) {
                return SctpCookieAckChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getCookieEcho()) {
                return SctpCookieEchoChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getData()) {
                return SctpDataChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getError()) {
                return SctpErrorChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getHeartbeat()) {
                return SctpHeartbeatChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getHeartbeatAck()) {
                return SctpHeartbeatAckChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getInitiation()) {
                return SctpInitChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getInitiationAck()) {
                return SctpInitAckChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getSack()) {
                return SctpSackChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getShutdown()) {
                return SctpShutdownChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getShutdownAck()) {
                return SctpShutdownAckChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getShutdownComplete()) {
                return SctpShutdownCompleteChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getPad()) {
                return SctpPadChunk.parseBytes(bArr2, integerHolder);
            }
            if (bArr2[0] == SctpChunkType.getForwardCumulativeTSN()) {
                return SctpForwardTsnChunk.parseBytes(bArr2, integerHolder);
            }
            Log.debug(StringExtensions.format("SCTP received unrecognized chunk type {0}", (Object) IntegerExtensions.toString(Integer.valueOf(bArr2[0]))));
            return SctpGenericChunk.parseBytes(bArr2, integerHolder);
        } catch (Exception unused) {
            Log.error("SCTP: could not read SCTP packet");
            integerHolder.setValue(0);
            return null;
        }
    }

    protected SctpChunk() {
    }

    public void setResendScheduledItem(ScheduledItem scheduledItem) {
        this._resendScheduledItem = scheduledItem;
    }

    /* access modifiers changed from: protected */
    public void setType(int i) {
        this._type = i;
    }

    /* access modifiers changed from: protected */
    public void setUnrecognized(boolean z) {
        this._unrecognized = z;
    }
}
