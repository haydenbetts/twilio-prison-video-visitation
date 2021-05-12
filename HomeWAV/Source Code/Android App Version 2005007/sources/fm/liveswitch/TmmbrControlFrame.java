package fm.liveswitch;

public class TmmbrControlFrame extends RtpControlFrame {
    private TmmbrEntry[] __entries;

    public static int getRegisteredFeedbackMessageType() {
        return 3;
    }

    public TmmbrEntry[] getEntries() {
        TmmbrEntry[] tmmbrEntryArr = this.__entries;
        return tmmbrEntryArr != null ? tmmbrEntryArr : new TmmbrEntry[0];
    }

    public TmmbrEntry getEntry() {
        return (TmmbrEntry) Utility.firstOrDefault((T[]) getEntries());
    }

    public static TmmbrControlFrame normalized(int i, long j) {
        return new TmmbrControlFrame(TmmbrEntry.normalized(i, j));
    }

    public static TmmbrControlFrame normalized(int i, long j, long j2) {
        TmmbrControlFrame tmmbrControlFrame = new TmmbrControlFrame(TmmbrEntry.normalized(i, j));
        tmmbrControlFrame.setPacketSenderSynchronizationSource(j2);
        return tmmbrControlFrame;
    }

    public void setEntries(TmmbrEntry[] tmmbrEntryArr) {
        if (tmmbrEntryArr == null) {
            tmmbrEntryArr = new TmmbrEntry[0];
        }
        this.__entries = tmmbrEntryArr;
    }

    public void setEntry(TmmbrEntry tmmbrEntry) {
        TmmbrEntry[] tmmbrEntryArr;
        if (tmmbrEntry == null) {
            tmmbrEntryArr = null;
        } else {
            tmmbrEntryArr = new TmmbrEntry[]{tmmbrEntry};
        }
        setEntries(tmmbrEntryArr);
    }

    public TmmbrControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
        int length = super.getFeedbackControlInformation().getLength() / TmmbrEntry.getFixedPayloadLength();
        setEntries(new TmmbrEntry[length]);
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength();
        for (int i = 0; i < length; i++) {
            getEntries()[i] = new TmmbrEntry(dataBuffer.subset(fixedHeaderLength, TmmbrEntry.getFixedPayloadLength()));
            fixedHeaderLength += TmmbrEntry.getFixedPayloadLength();
        }
    }

    public TmmbrControlFrame(TmmbrEntry[] tmmbrEntryArr) {
        super(getRegisteredFeedbackMessageType(), DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) tmmbrEntryArr) * TmmbrEntry.getFixedPayloadLength())));
        setEntries(new TmmbrEntry[ArrayExtensions.getLength((Object[]) tmmbrEntryArr)]);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) tmmbrEntryArr); i2++) {
            int length = tmmbrEntryArr[i2].getDataBuffer().getLength();
            getEntries()[i2] = new TmmbrEntry(super.getFeedbackControlInformation().subset(i, length));
            getEntries()[i2].getDataBuffer().write(tmmbrEntryArr[i2].getDataBuffer(), 0);
            i += length;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TmmbrControlFrame(fm.liveswitch.TmmbrEntry r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.TmmbrEntry[] r0 = new fm.liveswitch.TmmbrEntry[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.TmmbrEntry[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TmmbrControlFrame.<init>(fm.liveswitch.TmmbrEntry):void");
    }

    public TmmbrControlFrame(long j, long j2) {
        this(new TmmbrEntry(j, j2));
    }
}
