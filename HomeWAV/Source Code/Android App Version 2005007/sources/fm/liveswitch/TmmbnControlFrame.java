package fm.liveswitch;

public class TmmbnControlFrame extends RtpControlFrame {
    private TmmbnEntry[] __entries;

    public static int getRegisteredFeedbackMessageType() {
        return 4;
    }

    public TmmbnEntry[] getEntries() {
        TmmbnEntry[] tmmbnEntryArr = this.__entries;
        return tmmbnEntryArr != null ? tmmbnEntryArr : new TmmbnEntry[0];
    }

    public TmmbnEntry getEntry() {
        return (TmmbnEntry) Utility.firstOrDefault((T[]) getEntries());
    }

    public static TmmbnControlFrame normalized(int i, long j) {
        return new TmmbnControlFrame(TmmbnEntry.normalized(i, j));
    }

    public static TmmbnControlFrame normalized(int i, long j, long j2) {
        TmmbnControlFrame tmmbnControlFrame = new TmmbnControlFrame(TmmbnEntry.normalized(i, j));
        tmmbnControlFrame.setPacketSenderSynchronizationSource(j2);
        return tmmbnControlFrame;
    }

    public void setEntries(TmmbnEntry[] tmmbnEntryArr) {
        if (tmmbnEntryArr == null) {
            tmmbnEntryArr = new TmmbnEntry[0];
        }
        this.__entries = tmmbnEntryArr;
    }

    public void setEntry(TmmbnEntry tmmbnEntry) {
        TmmbnEntry[] tmmbnEntryArr;
        if (tmmbnEntry == null) {
            tmmbnEntryArr = null;
        } else {
            tmmbnEntryArr = new TmmbnEntry[]{tmmbnEntry};
        }
        setEntries(tmmbnEntryArr);
    }

    public TmmbnControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
        int length = super.getFeedbackControlInformation().getLength() / TmmbnEntry.getFixedPayloadLength();
        setEntries(new TmmbnEntry[length]);
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength();
        for (int i = 0; i < length; i++) {
            getEntries()[i] = new TmmbnEntry(dataBuffer.subset(fixedHeaderLength, TmmbnEntry.getFixedPayloadLength()));
            fixedHeaderLength += TmmbnEntry.getFixedPayloadLength();
        }
    }

    public TmmbnControlFrame(TmmbnEntry[] tmmbnEntryArr) {
        super(getRegisteredFeedbackMessageType(), DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) tmmbnEntryArr) * TmmbnEntry.getFixedPayloadLength())));
        setEntries(new TmmbnEntry[ArrayExtensions.getLength((Object[]) tmmbnEntryArr)]);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) tmmbnEntryArr); i2++) {
            int length = tmmbnEntryArr[i2].getDataBuffer().getLength();
            getEntries()[i2] = new TmmbnEntry(super.getFeedbackControlInformation().subset(i, length));
            getEntries()[i2].getDataBuffer().write(tmmbnEntryArr[i2].getDataBuffer(), 0);
            i += length;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TmmbnControlFrame(fm.liveswitch.TmmbnEntry r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.TmmbnEntry[] r0 = new fm.liveswitch.TmmbnEntry[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.TmmbnEntry[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TmmbnControlFrame.<init>(fm.liveswitch.TmmbnEntry):void");
    }

    public TmmbnControlFrame(long j, long j2) {
        this(new TmmbnEntry(j, j2));
    }
}
