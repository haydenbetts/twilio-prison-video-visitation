package fm.liveswitch;

public class ByeControlFrame extends MediaControlFrame {
    public static int getFixedPayloadHeaderLength() {
        return 0;
    }

    public static int getFixedReasonForLeavingPayloadHeaderLength() {
        return 1;
    }

    public static int getRegisteredPayloadType() {
        return 203;
    }

    public ByeControlFrame() {
        super(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()));
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength());
    }

    public ByeControlFrame(DataBuffer dataBuffer) {
        super(dataBuffer);
        if (dataBuffer.getLength() < MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()) {
            throw new RuntimeException(new Exception(StringExtensions.concat("ByeControlFrame requires a Databuffer of minimum length ", IntegerExtensions.toString(Integer.valueOf(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength())))));
        }
    }

    public ByeControlFrame(long j) {
        this(new long[]{j});
    }

    public ByeControlFrame(long j, String str) {
        this(new long[]{j}, str);
    }

    public ByeControlFrame(long[] jArr) {
        this(jArr, new long[0]);
    }

    public ByeControlFrame(long[] jArr, long[] jArr2) {
        this(jArr, jArr2, (String) null);
    }

    public ByeControlFrame(long[] jArr, long[] jArr2, String str) {
        int i;
        int i2 = 0;
        jArr = jArr == null ? new long[0] : jArr;
        jArr2 = jArr2 == null ? new long[0] : jArr2;
        int length = ArrayExtensions.getLength(jArr);
        int length2 = ArrayExtensions.getLength(jArr2);
        byte[] bArr = null;
        if (str != null) {
            bArr = Utf8.encode(str);
            i = ArrayExtensions.getLength(bArr) + getFixedReasonForLeavingPayloadHeaderLength();
        } else {
            i = 0;
        }
        int i3 = length + length2;
        int i4 = i3 * 4;
        super.setDataBuffer(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + i4 + i));
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength() + i4 + i);
        super.setPayloadType(getRegisteredPayloadType());
        long[] jArr3 = new long[i3];
        int i5 = 0;
        int i6 = 0;
        while (i5 < ArrayExtensions.getLength(jArr)) {
            jArr3[i6] = jArr[i5];
            i5++;
            i6++;
        }
        while (i2 < ArrayExtensions.getLength(jArr2)) {
            jArr3[i6] = jArr2[i2];
            i2++;
            i6++;
        }
        setSources(jArr3);
        if (getReason()) {
            setReasonForLeavingPayload(DataBuffer.wrap(bArr));
        }
    }

    public ByeControlFrame(long[] jArr, String str) {
        this(jArr, new long[0], str);
    }

    public boolean getReason() {
        return getReasonForLeavingOffset() < super.getDataBuffer().getLength();
    }

    public String getReasonForLeaving() {
        if (getReason()) {
            return Utf8.decode(getReasonForLeavingPayload().toArray());
        }
        return null;
    }

    public int getReasonForLeavingLength() {
        if (getReason()) {
            return super.getDataBuffer().read8(getReasonForLeavingOffset());
        }
        return 0;
    }

    public int getReasonForLeavingOffset() {
        return getSourcesPayloadOffset() + getSourcesLength();
    }

    public DataBuffer getReasonForLeavingPayload() {
        if (getReason()) {
            return super.getDataBuffer().subset(getReasonForLeavingOffset() + getFixedReasonForLeavingPayloadHeaderLength(), getReasonForLeavingLength());
        }
        return null;
    }

    public int getSourceCount() {
        return super.getByte1Last5Bits();
    }

    public long[] getSources() {
        int sourcesPayloadOffset = getSourcesPayloadOffset();
        long[] jArr = new long[getSourceCount()];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            LongHolder longHolder = new LongHolder(0);
            boolean tryRead32 = super.getDataBuffer().tryRead32(sourcesPayloadOffset, longHolder);
            long value = longHolder.getValue();
            if (!tryRead32) {
                return null;
            }
            sourcesPayloadOffset += 4;
            jArr[i] = value;
        }
        return jArr;
    }

    public int getSourcesLength() {
        return getSourceCount() * 4;
    }

    public int getSourcesPayloadOffset() {
        return MediaControlFrame.getFixedHeaderLength();
    }

    private void setReasonForLeavingLength(int i) {
        super.getDataBuffer().write8(i, getReasonForLeavingOffset());
    }

    private void setReasonForLeavingPayload(DataBuffer dataBuffer) {
        super.getDataBuffer().write(dataBuffer, getReasonForLeavingOffset() + getFixedReasonForLeavingPayloadHeaderLength());
        setReasonForLeavingLength(dataBuffer.getLength());
    }

    private void setSourceCount(int i) {
        super.setByte1Last5Bits(i);
    }

    public void setSources(long[] jArr) {
        int i = 0;
        if (jArr == null) {
            jArr = new long[0];
        }
        int sourcesPayloadOffset = getSourcesPayloadOffset();
        int i2 = 0;
        while (i < ArrayExtensions.getLength(jArr)) {
            if (super.getDataBuffer().write32(jArr[i], sourcesPayloadOffset)) {
                sourcesPayloadOffset += 4;
                i2++;
                i++;
            } else {
                Log.error("Could not write source to buffer.");
                return;
            }
        }
        setSourceCount(i2);
    }
}
