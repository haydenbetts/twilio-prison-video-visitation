package fm.liveswitch;

public abstract class ReportControlFrame extends MediaControlFrame {
    private ReportBlock[] __reportBlocks;

    public static int getFixedPayloadHeaderLength() {
        return 4;
    }

    public int getReceptionReportCount() {
        return super.getByte1Last5Bits();
    }

    public ReportBlock getReportBlock() {
        return (ReportBlock) Utility.firstOrDefault((T[]) getReportBlocks());
    }

    public ReportBlock getReportBlock(long j) {
        for (ReportBlock reportBlock : getReportBlocks()) {
            if (reportBlock.getSynchronizationSource() == j) {
                return reportBlock;
            }
        }
        return null;
    }

    public ReportBlock[] getReportBlocks() {
        ReportBlock[] reportBlockArr = this.__reportBlocks;
        return reportBlockArr != null ? reportBlockArr : new ReportBlock[0];
    }

    public long getSynchronizationSource() {
        return super.getDataBuffer().read32(getFixedPayloadHeaderLength());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReportControlFrame(int r3, fm.liveswitch.ReportBlock r4) {
        /*
            r2 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0006
            fm.liveswitch.ReportBlock[] r4 = new fm.liveswitch.ReportBlock[r0]
            goto L_0x000c
        L_0x0006:
            r1 = 1
            fm.liveswitch.ReportBlock[] r1 = new fm.liveswitch.ReportBlock[r1]
            r1[r0] = r4
            r4 = r1
        L_0x000c:
            r2.<init>((int) r3, (fm.liveswitch.ReportBlock[]) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReportControlFrame.<init>(int, fm.liveswitch.ReportBlock):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReportControlFrame(int r3, fm.liveswitch.ReportBlock r4, int r5) {
        /*
            r2 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0006
            fm.liveswitch.ReportBlock[] r4 = new fm.liveswitch.ReportBlock[r0]
            goto L_0x000c
        L_0x0006:
            r1 = 1
            fm.liveswitch.ReportBlock[] r1 = new fm.liveswitch.ReportBlock[r1]
            r1[r0] = r4
            r4 = r1
        L_0x000c:
            r2.<init>((int) r3, (fm.liveswitch.ReportBlock[]) r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReportControlFrame.<init>(int, fm.liveswitch.ReportBlock, int):void");
    }

    public ReportControlFrame(int i, int i2, DataBuffer dataBuffer) {
        super(dataBuffer);
        super.setPayloadType(i);
        if (dataBuffer.getLength() >= MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()) {
            int read5 = super.getDataBuffer().read5(0, 3);
            setReceptionReportCount(read5);
            setReportBlocks(new ReportBlock[read5]);
            for (int i3 = 0; i3 < read5; i3++) {
                getReportBlocks()[i3] = new ReportBlock(dataBuffer.subset(i2, ReportBlock.getFixedPayloadLength()));
                i2 += ReportBlock.getFixedPayloadLength();
            }
            super.setPayloadLengthWithPadding(i2 - MediaControlFrame.getFixedHeaderLength());
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("DataBuffer must be at least ", IntegerExtensions.toString(Integer.valueOf(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength())), " bytes")));
    }

    public ReportControlFrame(int i, ReportBlock[] reportBlockArr) {
        this(i, reportBlockArr, MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength());
    }

    public ReportControlFrame(int i, ReportBlock[] reportBlockArr, int i2) {
        super(DataBuffer.allocate((ArrayExtensions.getLength((Object[]) reportBlockArr) * ReportBlock.getFixedPayloadLength()) + i2));
        super.setPayloadType(i);
        setReportBlocks(new ReportBlock[ArrayExtensions.getLength((Object[]) reportBlockArr)]);
        setReceptionReportCount(ArrayExtensions.getLength((Object[]) reportBlockArr));
        for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) reportBlockArr); i3++) {
            int length = reportBlockArr[i3].getDataBuffer().getLength();
            getReportBlocks()[i3] = new ReportBlock(super.getDataBuffer().subset(i2, length));
            getReportBlocks()[i3].getDataBuffer().write(reportBlockArr[i3].getDataBuffer(), 0);
            i2 += length;
        }
        super.setPayloadLengthWithPadding(i2 - MediaControlFrame.getFixedHeaderLength());
    }

    public ReportControlFrame(int i, long j, ReportBlock reportBlock) {
        this(i, reportBlock);
        setSynchronizationSource(j);
    }

    public ReportControlFrame(int i, long j, ReportBlock reportBlock, int i2) {
        this(i, reportBlock, i2);
        setSynchronizationSource(j);
    }

    public ReportControlFrame(int i, long j, ReportBlock[] reportBlockArr) {
        this(i, reportBlockArr);
        setSynchronizationSource(j);
    }

    public ReportControlFrame(int i, long j, ReportBlock[] reportBlockArr, int i2) {
        this(i, reportBlockArr, i2);
        setSynchronizationSource(j);
    }

    private void setReceptionReportCount(int i) {
        super.setByte1Last5Bits(i);
    }

    public void setReportBlock(ReportBlock reportBlock) {
        ReportBlock[] reportBlockArr;
        if (reportBlock == null) {
            reportBlockArr = null;
        } else {
            reportBlockArr = new ReportBlock[]{reportBlock};
        }
        setReportBlocks(reportBlockArr);
    }

    public void setReportBlocks(ReportBlock[] reportBlockArr) {
        if (reportBlockArr == null) {
            reportBlockArr = new ReportBlock[0];
        }
        setReceptionReportCount(ArrayExtensions.getLength((Object[]) reportBlockArr));
        this.__reportBlocks = reportBlockArr;
    }

    public void setSynchronizationSource(long j) {
        super.getDataBuffer().write32(j, getFixedPayloadHeaderLength());
    }
}
