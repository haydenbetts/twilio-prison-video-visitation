package fm.liveswitch.h264;

import fm.liveswitch.Binary;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.StringExtensions;

public class Nalu {
    private static DataBuffer __startCode;
    private int _bottomFieldFlag;
    private DataBuffer _buffer;
    private int[] _deltaPicOrderCnt;
    private int _fieldPicFlag;
    private int _firstMbInSlice;
    private int _frameNum;
    private int _idrPicFlag;
    private int _idrPicId;
    private int _picOrderCntLsb;
    private int _picOrderCntType;
    private int _ppsId;
    private int _sliceType;
    private int _spsId;

    public static int getFBitMask() {
        return 128;
    }

    public static int getNriMask() {
        return 96;
    }

    public static int getTypeMask() {
        return 31;
    }

    public static int findNalu(DataBuffer dataBuffer) {
        return findNextNalu(dataBuffer, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6 < r0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        if (r5.read24(r6) != 1) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        if (r3 == false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        return r6 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r5.read24(r6) == 1) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (r3 != false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r4 != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int findNextNalu(fm.liveswitch.DataBuffer r5, int r6) {
        /*
            int r0 = r5.getLength()
            int r0 = r0 + -3
            r1 = 0
            r2 = 1
            if (r6 < r0) goto L_0x000c
            r3 = 1
            goto L_0x000d
        L_0x000c:
            r3 = 0
        L_0x000d:
            int r4 = r5.read24(r6)
            if (r4 != r2) goto L_0x0015
        L_0x0013:
            r4 = 1
            goto L_0x0016
        L_0x0015:
            r4 = 0
        L_0x0016:
            if (r3 != 0) goto L_0x0028
            if (r4 != 0) goto L_0x0028
            int r6 = r6 + 1
            if (r6 < r0) goto L_0x0020
            r3 = 1
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            int r4 = r5.read24(r6)
            if (r4 != r2) goto L_0x0015
            goto L_0x0013
        L_0x0028:
            if (r3 == 0) goto L_0x002c
            r5 = -1
            return r5
        L_0x002c:
            int r6 = r6 + 3
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.h264.Nalu.findNextNalu(fm.liveswitch.DataBuffer, int):int");
    }

    /* access modifiers changed from: protected */
    public int getBottomFieldFlag() {
        return this._bottomFieldFlag;
    }

    public DataBuffer getBuffer() {
        return this._buffer;
    }

    public DataBuffer getBytes() {
        return getBuffer();
    }

    /* access modifiers changed from: protected */
    public int[] getDeltaPicOrderCnt() {
        return this._deltaPicOrderCnt;
    }

    public boolean getFBit() {
        return (getBuffer().read8(0) & getFBitMask()) == 1;
    }

    /* access modifiers changed from: protected */
    public int getFieldPicFlag() {
        return this._fieldPicFlag;
    }

    /* access modifiers changed from: protected */
    public int getFirstMbInSlice() {
        return this._firstMbInSlice;
    }

    /* access modifiers changed from: protected */
    public int getFrameNum() {
        return this._frameNum;
    }

    public int getHeader() {
        return getNalRefIdc() + getType();
    }

    /* access modifiers changed from: protected */
    public int getIdrPicFlag() {
        return this._idrPicFlag;
    }

    /* access modifiers changed from: protected */
    public int getIdrPicId() {
        return this._idrPicId;
    }

    public int getNalRefIdc() {
        return getBuffer().read8(0) & getNriMask();
    }

    public static Nalu getNalu(DataBuffer dataBuffer) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        Nalu nalu = getNalu(dataBuffer, 0, integerHolder);
        integerHolder.getValue();
        return nalu;
    }

    public static Nalu getNalu(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        int i2;
        if (i > dataBuffer.getLength()) {
            integerHolder.setValue(0);
            return null;
        }
        int findNextNalu = findNextNalu(dataBuffer, i);
        integerHolder.setValue(i);
        if (findNextNalu < 0) {
            i2 = dataBuffer.getLength();
        } else {
            if (dataBuffer.getData()[findNextNalu - 4] == 0) {
                integerHolder.setValue(4);
            } else {
                integerHolder.setValue(3);
            }
            i2 = findNextNalu - integerHolder.getValue();
        }
        Nalu nalu = new Nalu(dataBuffer.subset(i, i2 - i));
        if (nalu.getFBit() || nalu.getType() == 0) {
            return getNalu(dataBuffer, i2 + integerHolder.getValue(), integerHolder);
        }
        return nalu;
    }

    private String getNaluKey() {
        return StringExtensions.concat((Object[]) new String[]{IntegerExtensions.toString(Integer.valueOf(getFrameNum())), ".", IntegerExtensions.toString(Integer.valueOf(getPpsId())), ".", IntegerExtensions.toString(Integer.valueOf(getFieldPicFlag())), ".", IntegerExtensions.toString(Integer.valueOf(getBottomFieldFlag())), ".", IntegerExtensions.toString(Integer.valueOf(getNalRefIdc())), ".", IntegerExtensions.toString(Integer.valueOf(getPicOrderCntType())), ".", IntegerExtensions.toString(Integer.valueOf(getIdrPicFlag()))});
    }

    public DataBuffer getPayload() {
        return getBuffer().subset(1);
    }

    /* access modifiers changed from: protected */
    public int getPicOrderCntLsb() {
        return this._picOrderCntLsb;
    }

    /* access modifiers changed from: protected */
    public int getPicOrderCntType() {
        return this._picOrderCntType;
    }

    /* access modifiers changed from: protected */
    public int getPpsId() {
        return this._ppsId;
    }

    public DataBuffer getShortLength() {
        return DataBuffer.wrap(Binary.toBytes16(getBuffer().getLength(), false));
    }

    /* access modifiers changed from: protected */
    public int getSliceType() {
        return this._sliceType;
    }

    /* access modifiers changed from: protected */
    public int getSpsId() {
        return this._spsId;
    }

    public static DataBuffer getStartCode() {
        return __startCode;
    }

    public int getType() {
        return NaluType.getNaluType(getBuffer().read8(0));
    }

    static {
        byte[] bArr = new byte[4];
        bArr[3] = 1;
        __startCode = DataBuffer.wrap(bArr);
    }

    public Nalu() {
    }

    public Nalu(DataBuffer dataBuffer) {
        setBuffer(dataBuffer);
    }

    private void setBottomFieldFlag(int i) {
        this._bottomFieldFlag = i;
    }

    private void setBuffer(DataBuffer dataBuffer) {
        this._buffer = dataBuffer;
    }

    private void setDeltaPicOrderCnt(int[] iArr) {
        this._deltaPicOrderCnt = iArr;
    }

    private void setFieldPicFlag(int i) {
        this._fieldPicFlag = i;
    }

    private void setFirstMbInSlice(int i) {
        this._firstMbInSlice = i;
    }

    private void setFrameNum(int i) {
        this._frameNum = i;
    }

    private void setIdrPicFlag(int i) {
        this._idrPicFlag = i;
    }

    private void setIdrPicId(int i) {
        this._idrPicId = i;
    }

    private void setPicOrderCntLsb(int i) {
        this._picOrderCntLsb = i;
    }

    private void setPicOrderCntType(int i) {
        this._picOrderCntType = i;
    }

    private void setPpsId(int i) {
        this._ppsId = i;
    }

    private void setSliceType(int i) {
        this._sliceType = i;
    }

    private void setSpsId(int i) {
        this._spsId = i;
    }
}
