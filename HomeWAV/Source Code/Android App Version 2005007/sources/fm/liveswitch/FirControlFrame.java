package fm.liveswitch;

public class FirControlFrame extends PayloadSpecificControlFrame {
    private FirEntry[] __entries;

    public static int getRegisteredFeedbackMessageType() {
        return 4;
    }

    public FirControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
        int length = super.getFeedbackControlInformation().getLength() / FirEntry.getFixedPayloadLength();
        setEntries(new FirEntry[length]);
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength();
        for (int i = 0; i < length; i++) {
            getEntries()[i] = new FirEntry(dataBuffer.subset(fixedHeaderLength, FirEntry.getFixedPayloadLength()));
            fixedHeaderLength += FirEntry.getFixedPayloadLength();
        }
    }

    public FirControlFrame(FirEntry[] firEntryArr) {
        super(getRegisteredFeedbackMessageType(), DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) firEntryArr) * FirEntry.getFixedPayloadLength())));
        setEntries(new FirEntry[ArrayExtensions.getLength((Object[]) firEntryArr)]);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) firEntryArr); i2++) {
            int length = firEntryArr[i2].getDataBuffer().getLength();
            getEntries()[i2] = new FirEntry(super.getFeedbackControlInformation().subset(i, length));
            getEntries()[i2].getDataBuffer().write(firEntryArr[i2].getDataBuffer(), 0);
            i += length;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FirControlFrame(fm.liveswitch.FirEntry r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.FirEntry[] r0 = new fm.liveswitch.FirEntry[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.FirEntry[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.FirControlFrame.<init>(fm.liveswitch.FirEntry):void");
    }

    public FirControlFrame(int i) {
        this(new FirEntry(i));
    }

    public FirEntry[] getEntries() {
        FirEntry[] firEntryArr = this.__entries;
        return firEntryArr != null ? firEntryArr : new FirEntry[0];
    }

    public FirEntry getEntry() {
        return (FirEntry) Utility.firstOrDefault((T[]) getEntries());
    }

    public void setEntries(FirEntry[] firEntryArr) {
        if (firEntryArr == null) {
            firEntryArr = new FirEntry[0];
        }
        this.__entries = firEntryArr;
    }

    public void setEntry(FirEntry firEntry) {
        FirEntry[] firEntryArr;
        if (firEntry == null) {
            firEntryArr = null;
        } else {
            firEntryArr = new FirEntry[]{firEntry};
        }
        setEntries(firEntryArr);
    }
}
