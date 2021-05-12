package fm.liveswitch.vp9;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;

class Packet {
    private boolean _endOfLayerFrame;
    private boolean _extendedPictureID;
    private boolean _flexibleMode;
    private DataBuffer _header;
    private boolean _interPicturePredictedLayerFrame;
    private boolean _layerIndicesPresent;
    private DataBuffer _payload;
    private short _pictureID;
    private boolean _pictureIDPresent;
    private boolean _scalabilityStructurePresent;
    private boolean _startOfLayerFrame;

    public int getFixedHeaderLength() {
        return 1;
    }

    public boolean getReservedBit() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static fm.liveswitch.vp9.Packet extractV00(fm.liveswitch.DataBuffer r6, fm.liveswitch.DataBufferStream r7) {
        /*
            fm.liveswitch.vp9.Packet r0 = new fm.liveswitch.vp9.Packet
            r0.<init>()
            boolean r1 = r7.read1()
            r0.setPictureIDPresent(r1)
            boolean r1 = r7.read1()
            r0.setInterPicturePredictedLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setLayerIndicesPresent(r1)
            boolean r1 = r7.read1()
            r0.setFlexibleMode(r1)
            boolean r1 = r7.read1()
            r0.setStartOfLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setEndOfLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setScalabilityStructurePresent(r1)
            r7.nextByte()
            boolean r1 = r0.getPictureIDPresent()
            if (r1 == 0) goto L_0x005d
            boolean r1 = r7.read1()
            r0.setExtendedPictureID(r1)
            boolean r1 = r0.getExtendedPictureID()
            if (r1 == 0) goto L_0x0055
            int r1 = r7.read15()
            short r1 = (short) r1
            r0.setPictureID(r1)
            goto L_0x005d
        L_0x0055:
            int r1 = r7.read7()
            short r1 = (short) r1
            r0.setPictureID(r1)
        L_0x005d:
            boolean r1 = r0.getLayerIndicesPresent()
            if (r1 == 0) goto L_0x007c
            boolean r1 = r0.getFlexibleMode()
            if (r1 == 0) goto L_0x0076
            r7.read3()
            r7.read1()
            r7.read3()
            r7.read1()
            goto L_0x007c
        L_0x0076:
            r7.read8()
            r7.read8()
        L_0x007c:
            boolean r1 = r0.getInterPicturePredictedLayerFrame()
            if (r1 == 0) goto L_0x0091
            boolean r1 = r0.getFlexibleMode()
            if (r1 == 0) goto L_0x0091
        L_0x0088:
            r7.read7()
            boolean r1 = r7.read1()
            if (r1 != 0) goto L_0x0088
        L_0x0091:
            boolean r1 = r0.getScalabilityStructurePresent()
            if (r1 == 0) goto L_0x00d2
            int r1 = r7.read3()
            int r1 = r1 + 1
            boolean r2 = r7.read1()
            int r3 = r7.read4()
            int r3 = r3 + 1
            r4 = 0
            if (r2 == 0) goto L_0x00b6
            r2 = 0
        L_0x00ab:
            if (r2 >= r1) goto L_0x00b6
            r7.read16()
            r7.read16()
            int r2 = r2 + 1
            goto L_0x00ab
        L_0x00b6:
            r1 = 0
        L_0x00b7:
            if (r1 >= r3) goto L_0x00d2
            r7.read3()
            r7.read1()
            int r2 = r7.read2()
            r7.nextByte()
            r5 = 0
        L_0x00c7:
            if (r5 >= r2) goto L_0x00cf
            r7.read8()
            int r5 = r5 + 1
            goto L_0x00c7
        L_0x00cf:
            int r1 = r1 + 1
            goto L_0x00b7
        L_0x00d2:
            int r7 = r7.getPosition()
            fm.liveswitch.DataBuffer r6 = r6.subset(r7)
            r0.setPayload(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.vp9.Packet.extractV00(fm.liveswitch.DataBuffer, fm.liveswitch.DataBufferStream):fm.liveswitch.vp9.Packet");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static fm.liveswitch.vp9.Packet extractV02(fm.liveswitch.DataBuffer r6, fm.liveswitch.DataBufferStream r7) {
        /*
            fm.liveswitch.vp9.Packet r0 = new fm.liveswitch.vp9.Packet
            r0.<init>()
            boolean r1 = r7.read1()
            r0.setPictureIDPresent(r1)
            boolean r1 = r7.read1()
            r0.setInterPicturePredictedLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setLayerIndicesPresent(r1)
            boolean r1 = r7.read1()
            r0.setFlexibleMode(r1)
            boolean r1 = r7.read1()
            r0.setStartOfLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setEndOfLayerFrame(r1)
            boolean r1 = r7.read1()
            r0.setScalabilityStructurePresent(r1)
            r7.read1()
            boolean r1 = r0.getPictureIDPresent()
            if (r1 == 0) goto L_0x005d
            boolean r1 = r7.read1()
            r0.setExtendedPictureID(r1)
            boolean r1 = r0.getExtendedPictureID()
            if (r1 == 0) goto L_0x0055
            int r1 = r7.read15()
            short r1 = (short) r1
            r0.setPictureID(r1)
            goto L_0x005d
        L_0x0055:
            int r1 = r7.read7()
            short r1 = (short) r1
            r0.setPictureID(r1)
        L_0x005d:
            boolean r1 = r0.getLayerIndicesPresent()
            if (r1 == 0) goto L_0x0078
            r7.read3()
            r7.read1()
            r7.read3()
            r7.read1()
            boolean r1 = r0.getFlexibleMode()
            if (r1 != 0) goto L_0x0078
            r7.read8()
        L_0x0078:
            boolean r1 = r0.getInterPicturePredictedLayerFrame()
            if (r1 == 0) goto L_0x008d
            boolean r1 = r0.getFlexibleMode()
            if (r1 == 0) goto L_0x008d
        L_0x0084:
            r7.read7()
            boolean r1 = r7.read1()
            if (r1 != 0) goto L_0x0084
        L_0x008d:
            boolean r1 = r0.getScalabilityStructurePresent()
            if (r1 == 0) goto L_0x00e5
            int r1 = r7.read3()
            int r1 = r1 + 1
            boolean r2 = r7.read1()
            boolean r3 = r7.read1()
            int r4 = r7.read3()
            if (r4 != 0) goto L_0x00d8
            r4 = 0
            if (r2 == 0) goto L_0x00b6
            r2 = 0
        L_0x00ab:
            if (r2 >= r1) goto L_0x00b6
            r7.read16()
            r7.read16()
            int r2 = r2 + 1
            goto L_0x00ab
        L_0x00b6:
            if (r3 == 0) goto L_0x00e5
            int r1 = r7.read8()
            r2 = 0
        L_0x00bd:
            if (r2 >= r1) goto L_0x00e5
            r7.read3()
            r7.read1()
            int r3 = r7.read2()
            r7.nextByte()
            r5 = 0
        L_0x00cd:
            if (r5 >= r3) goto L_0x00d5
            r7.read8()
            int r5 = r5 + 1
            goto L_0x00cd
        L_0x00d5:
            int r2 = r2 + 1
            goto L_0x00bd
        L_0x00d8:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r0 = "VP9 RTP Scalability Structure failed sanity check for spec version 02."
            r7.<init>(r0)
            r6.<init>(r7)
            throw r6
        L_0x00e5:
            int r7 = r7.getPosition()
            fm.liveswitch.DataBuffer r6 = r6.subset(r7)
            r0.setPayload(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.vp9.Packet.extractV02(fm.liveswitch.DataBuffer, fm.liveswitch.DataBufferStream):fm.liveswitch.vp9.Packet");
    }

    private void generateHeader() {
        setHeader(DataBuffer.allocate(getHeaderLength()));
        getHeader().write1(getPictureIDPresent(), 0, 0);
        getHeader().write1(getInterPicturePredictedLayerFrame(), 0, 1);
        getHeader().write1(getLayerIndicesPresent(), 0, 2);
        getHeader().write1(getFlexibleMode(), 0, 3);
        getHeader().write1(getStartOfLayerFrame(), 0, 4);
        getHeader().write1(getEndOfLayerFrame(), 0, 5);
        getHeader().write1(getScalabilityStructurePresent(), 0, 6);
        getHeader().write1(getReservedBit(), 0, 7);
    }

    public DataBuffer getBuffer() {
        if (getHeader() == null) {
            generateHeader();
        }
        return getHeader().append(getPayload());
    }

    public boolean getEndOfLayerFrame() {
        return this._endOfLayerFrame;
    }

    public boolean getExtendedPictureID() {
        return this._extendedPictureID;
    }

    public boolean getFlexibleMode() {
        return this._flexibleMode;
    }

    public DataBuffer getHeader() {
        return this._header;
    }

    public int getHeaderLength() {
        return getFixedHeaderLength() + getVariableHeaderLength();
    }

    public boolean getInterPicturePredictedLayerFrame() {
        return this._interPicturePredictedLayerFrame;
    }

    public boolean getLayerIndicesPresent() {
        return this._layerIndicesPresent;
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public short getPictureID() {
        return this._pictureID;
    }

    public boolean getPictureIDPresent() {
        return this._pictureIDPresent;
    }

    public boolean getScalabilityStructurePresent() {
        return this._scalabilityStructurePresent;
    }

    public boolean getStartOfLayerFrame() {
        return this._startOfLayerFrame;
    }

    public int getVariableHeaderLength() {
        return (getPictureIDPresent() ? 1 : 0) + (getExtendedPictureID() ? 1 : 0);
    }

    public Packet() {
    }

    public Packet(DataBuffer dataBuffer, boolean z) {
        setPayload(dataBuffer);
        setStartOfLayerFrame(z);
    }

    public Packet(DataBuffer dataBuffer, boolean z, byte b) {
        this(dataBuffer, z);
    }

    public void setEndOfLayerFrame(boolean z) {
        this._endOfLayerFrame = z;
    }

    public void setExtendedPictureID(boolean z) {
        this._extendedPictureID = z;
    }

    public void setFlexibleMode(boolean z) {
        this._flexibleMode = z;
    }

    private void setHeader(DataBuffer dataBuffer) {
        this._header = dataBuffer;
    }

    public void setInterPicturePredictedLayerFrame(boolean z) {
        this._interPicturePredictedLayerFrame = z;
    }

    public void setLayerIndicesPresent(boolean z) {
        this._layerIndicesPresent = z;
    }

    public void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }

    public void setPictureID(short s) {
        this._pictureID = s;
    }

    public void setPictureIDPresent(boolean z) {
        this._pictureIDPresent = z;
    }

    public void setScalabilityStructurePresent(boolean z) {
        this._scalabilityStructurePresent = z;
    }

    public void setStartOfLayerFrame(boolean z) {
        this._startOfLayerFrame = z;
    }

    public static Packet wrap(DataBuffer dataBuffer) {
        DataBufferStream dataBufferStream = new DataBufferStream(dataBuffer);
        dataBufferStream.getBuffer().setLittleEndian(false);
        return extractV02(dataBuffer, dataBufferStream);
    }
}
