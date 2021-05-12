package fm.liveswitch;

public class LrrControlFrame extends PayloadSpecificControlFrame {
    private LrrEntry[] __entries;

    public static int getRegisteredFeedbackMessageType() {
        return 10;
    }

    public LrrEntry[] getEntries() {
        LrrEntry[] lrrEntryArr = this.__entries;
        return lrrEntryArr != null ? lrrEntryArr : new LrrEntry[0];
    }

    public LrrEntry getEntry() {
        return (LrrEntry) Utility.firstOrDefault((T[]) getEntries());
    }

    public LrrControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
        int length = super.getFeedbackControlInformation().getLength() / LrrEntry.getFixedPayloadLength();
        setEntries(new LrrEntry[length]);
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength();
        for (int i = 0; i < length; i++) {
            getEntries()[i] = new LrrEntry(dataBuffer.subset(fixedHeaderLength, LrrEntry.getFixedPayloadLength()));
            fixedHeaderLength += LrrEntry.getFixedPayloadLength();
        }
    }

    public LrrControlFrame(LrrEntry[] lrrEntryArr) {
        super(getRegisteredFeedbackMessageType(), DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) lrrEntryArr) * LrrEntry.getFixedPayloadLength())));
        setEntries(new LrrEntry[ArrayExtensions.getLength((Object[]) lrrEntryArr)]);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) lrrEntryArr); i2++) {
            int length = lrrEntryArr[i2].getDataBuffer().getLength();
            getEntries()[i2] = new LrrEntry(super.getFeedbackControlInformation().subset(i, length));
            getEntries()[i2].getDataBuffer().write(lrrEntryArr[i2].getDataBuffer(), 0);
            i += length;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LrrControlFrame(fm.liveswitch.LrrEntry r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.LrrEntry[] r0 = new fm.liveswitch.LrrEntry[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.LrrEntry[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.LrrControlFrame.<init>(fm.liveswitch.LrrEntry):void");
    }

    public LrrControlFrame(int i) {
        this(new LrrEntry(i));
    }

    public void setEntries(LrrEntry[] lrrEntryArr) {
        if (lrrEntryArr == null) {
            lrrEntryArr = new LrrEntry[0];
        }
        this.__entries = lrrEntryArr;
    }

    public void setEntry(LrrEntry lrrEntry) {
        LrrEntry[] lrrEntryArr;
        if (lrrEntry == null) {
            lrrEntryArr = null;
        } else {
            lrrEntryArr = new LrrEntry[]{lrrEntry};
        }
        setEntries(lrrEntryArr);
    }
}
