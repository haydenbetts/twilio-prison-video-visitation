package fm.liveswitch.g722;

class CodecState {
    private CodecBand[] _band;
    private int _bitsPerSample;
    private int _inBits;
    private long _inBuffer;
    private int _outBits;
    private long _outBuffer;
    private int[] _qmfSignalHistory;

    public CodecState(int i) {
        if (i == 48000) {
            setBitsPerSample(6);
        } else if (i == 56000) {
            setBitsPerSample(7);
        } else if (i == 64000) {
            setBitsPerSample(8);
        } else {
            throw new RuntimeException(new Exception("Unexpected rate."));
        }
        setQmfSignalHistory(new int[24]);
        CodecBand codecBand = new CodecBand();
        codecBand.setDet(32);
        CodecBand codecBand2 = new CodecBand();
        codecBand2.setDet(8);
        setBand(new CodecBand[]{codecBand, codecBand2});
    }

    public CodecBand[] getBand() {
        return this._band;
    }

    public int getBitsPerSample() {
        return this._bitsPerSample;
    }

    public int getInBits() {
        return this._inBits;
    }

    public long getInBuffer() {
        return this._inBuffer;
    }

    public int getOutBits() {
        return this._outBits;
    }

    public long getOutBuffer() {
        return this._outBuffer;
    }

    public int[] getQmfSignalHistory() {
        return this._qmfSignalHistory;
    }

    private void setBand(CodecBand[] codecBandArr) {
        this._band = codecBandArr;
    }

    private void setBitsPerSample(int i) {
        this._bitsPerSample = i;
    }

    /* access modifiers changed from: package-private */
    public void setInBits(int i) {
        this._inBits = i;
    }

    /* access modifiers changed from: package-private */
    public void setInBuffer(long j) {
        this._inBuffer = j;
    }

    /* access modifiers changed from: package-private */
    public void setOutBits(int i) {
        this._outBits = i;
    }

    /* access modifiers changed from: package-private */
    public void setOutBuffer(long j) {
        this._outBuffer = j;
    }

    private void setQmfSignalHistory(int[] iArr) {
        this._qmfSignalHistory = iArr;
    }
}
