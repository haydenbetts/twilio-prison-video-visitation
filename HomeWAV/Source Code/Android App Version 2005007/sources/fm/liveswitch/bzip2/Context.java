package fm.liveswitch.bzip2;

class Context {
    private int[] _base_zt;
    private int _bsBuffShadow;
    private int _bsLiveShadow;
    private int _eob;
    private int _groupNo;
    private int _groupPos;
    private int _lastShadow;
    private int[] _limit_zt;
    private int _minLens_zt;
    private int _nextSym;
    private int[] _perm_zt;
    private int _zt;

    public int[] getBase_zt() {
        return this._base_zt;
    }

    public int getBsBuffShadow() {
        return this._bsBuffShadow;
    }

    public int getBsLiveShadow() {
        return this._bsLiveShadow;
    }

    public int getEob() {
        return this._eob;
    }

    public int getGroupNo() {
        return this._groupNo;
    }

    public int getGroupPos() {
        return this._groupPos;
    }

    public int getLastShadow() {
        return this._lastShadow;
    }

    public int[] getLimit_zt() {
        return this._limit_zt;
    }

    public int getMinLens_zt() {
        return this._minLens_zt;
    }

    public int getNextSym() {
        return this._nextSym;
    }

    public int[] getPerm_zt() {
        return this._perm_zt;
    }

    public int getZt() {
        return this._zt;
    }

    public void setBase_zt(int[] iArr) {
        this._base_zt = iArr;
    }

    public void setBsBuffShadow(int i) {
        this._bsBuffShadow = i;
    }

    public void setBsLiveShadow(int i) {
        this._bsLiveShadow = i;
    }

    public void setEob(int i) {
        this._eob = i;
    }

    public void setGroupNo(int i) {
        this._groupNo = i;
    }

    public void setGroupPos(int i) {
        this._groupPos = i;
    }

    public void setLastShadow(int i) {
        this._lastShadow = i;
    }

    public void setLimit_zt(int[] iArr) {
        this._limit_zt = iArr;
    }

    public void setMinLens_zt(int i) {
        this._minLens_zt = i;
    }

    public void setNextSym(int i) {
        this._nextSym = i;
    }

    public void setPerm_zt(int[] iArr) {
        this._perm_zt = iArr;
    }

    public void setZt(int i) {
        this._zt = i;
    }
}
