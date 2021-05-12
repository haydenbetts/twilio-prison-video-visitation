package fm.liveswitch;

public class RembControlFrame extends PayloadSpecificControlFrame {
    public static int getRegisteredFeedbackMessageType() {
        return 15;
    }

    /* access modifiers changed from: package-private */
    public long getBitrate() {
        return (long) (getBitrateMantissa() << getBitrateExponent());
    }

    public int getBitrateExponent() {
        return super.getDataBuffer().read6(super.getFeedbackControlInformationOffset() + 5, 0);
    }

    public int getBitrateMantissa() {
        return super.getDataBuffer().read18(super.getFeedbackControlInformationOffset() + 5, 6);
    }

    private static int getRequiredBufferSize(int i) {
        return MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + 8 + (i * 4);
    }

    public long[] getSsrcEntries() {
        int read8 = super.getDataBuffer().read8(super.getFeedbackControlInformationOffset() + 4);
        long[] jArr = new long[read8];
        int feedbackControlInformationOffset = super.getFeedbackControlInformationOffset() + 8;
        for (int i = 0; i < read8; i++) {
            jArr[i] = super.getDataBuffer().read32(feedbackControlInformationOffset);
            feedbackControlInformationOffset += 4;
        }
        return jArr;
    }

    public RembControlFrame(long j, long[] jArr) {
        this(DataBuffer.allocate(getRequiredBufferSize(ArrayExtensions.getLength(jArr))));
        super.getDataBuffer().writeBytes(new byte[]{82, 69, 77, 66}, super.getFeedbackControlInformationOffset());
        super.getDataBuffer().write8(ArrayExtensions.getLength(jArr), super.getFeedbackControlInformationOffset() + 4);
        int i = 0;
        while (true) {
            if (i >= 64) {
                i = 0;
                break;
            } else if (j <= (262143 << i)) {
                break;
            } else {
                i++;
            }
        }
        super.getDataBuffer().write6(i, super.getFeedbackControlInformationOffset() + 5, 0);
        super.getDataBuffer().write18((int) (j >> i), super.getFeedbackControlInformationOffset() + 5, 6);
        int i2 = 0;
        for (int i3 = 0; i3 < ArrayExtensions.getLength(jArr); i3++) {
            super.getDataBuffer().write32(jArr[i3], super.getFeedbackControlInformationOffset() + 8 + i2);
            i2 += 4;
        }
    }

    public RembControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
    }

    public void setBitrateExponent(int i) {
        super.getDataBuffer().write6(i, super.getFeedbackControlInformationOffset() + 5, 0);
    }

    public void setBitrateMantissa(int i) {
        super.getDataBuffer().write18(i, super.getFeedbackControlInformationOffset() + 5, 6);
    }

    public void setSsrcEntries(long[] jArr) {
        if (jArr == null) {
            jArr = new long[0];
        }
        if (super.getDataBuffer().read8(super.getFeedbackControlInformationOffset() + 4) != ArrayExtensions.getLength(jArr)) {
            super.getDataBuffer().resize(getRequiredBufferSize(ArrayExtensions.getLength(jArr)));
            super.getDataBuffer().write8(ArrayExtensions.getLength(jArr), super.getFeedbackControlInformationOffset() + 4);
        }
        int feedbackControlInformationOffset = super.getFeedbackControlInformationOffset() + 8;
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            super.getDataBuffer().write32(jArr[i], feedbackControlInformationOffset);
            feedbackControlInformationOffset += 4;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "REMB Frame. SSRC(s): ");
        if (ArrayExtensions.getLength(getSsrcEntries()) > 0) {
            String str2 = "";
            for (long valueOf : getSsrcEntries()) {
                str2 = StringExtensions.concat(str2, LongExtensions.toString(Long.valueOf(valueOf)), ", ");
            }
            str = StringExtensions.substring(str2, 0, StringExtensions.getLength(str2) - 2);
        } else {
            str = "N/A";
        }
        StringBuilderExtensions.append(sb, StringExtensions.concat(str, ". Bitrate: "));
        long bitrate = getBitrate();
        if (bitrate >= -1) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(LongExtensions.toString(Long.valueOf(bitrate)), " bps."));
        } else {
            StringBuilderExtensions.append(sb, "N/A.");
        }
        return sb.toString();
    }
}
