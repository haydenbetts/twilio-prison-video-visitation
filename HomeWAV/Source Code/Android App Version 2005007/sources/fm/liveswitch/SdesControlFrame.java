package fm.liveswitch;

public class SdesControlFrame extends MediaControlFrame {
    private SdesChunk[] _chunks;

    public static int getFixedPayloadHeaderLength() {
        return 0;
    }

    public static int getRegisteredPayloadType() {
        return 202;
    }

    public SdesChunk[] getChunks() {
        return this._chunks;
    }

    public int getSourceCount() {
        return super.getByte1Last5Bits();
    }

    public SdesControlFrame() {
        this(new SdesChunk[0]);
    }

    public SdesControlFrame(DataBuffer dataBuffer) {
        super(dataBuffer);
        super.setPayloadType(getRegisteredPayloadType());
        int sourceCount = getSourceCount();
        SdesChunk[] sdesChunkArr = new SdesChunk[sourceCount];
        int i = 4;
        for (int i2 = 0; i2 < sourceCount; i2++) {
            SdesChunk sdesChunk = new SdesChunk(dataBuffer, i);
            sdesChunkArr[i2] = sdesChunk;
            i += sdesChunk.getLength();
        }
        setChunks(sdesChunkArr);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SdesControlFrame(fm.liveswitch.SdesChunk r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.SdesChunk[] r0 = new fm.liveswitch.SdesChunk[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.SdesChunk[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SdesControlFrame.<init>(fm.liveswitch.SdesChunk):void");
    }

    public SdesControlFrame(SdesChunk[] sdesChunkArr) {
        setChunks(sdesChunkArr);
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength();
        for (SdesChunk dataBuffer : sdesChunkArr) {
            fixedHeaderLength += dataBuffer.getDataBuffer().getLength();
        }
        super.setDataBuffer(DataBuffer.allocate(fixedHeaderLength));
        setSourceCount(ArrayExtensions.getLength((Object[]) sdesChunkArr));
        super.setPayloadType(getRegisteredPayloadType());
        super.setPayloadLengthWithPadding(fixedHeaderLength - MediaControlFrame.getFixedHeaderLength());
        int fixedHeaderLength2 = MediaControlFrame.getFixedHeaderLength();
        for (SdesChunk sdesChunk : sdesChunkArr) {
            super.getDataBuffer().write(sdesChunk.getDataBuffer(), fixedHeaderLength2);
            fixedHeaderLength2 += sdesChunk.getDataBuffer().getLength();
        }
    }

    private void setChunks(SdesChunk[] sdesChunkArr) {
        this._chunks = sdesChunkArr;
    }

    private void setSourceCount(int i) {
        super.setByte1Last5Bits(i);
    }
}
