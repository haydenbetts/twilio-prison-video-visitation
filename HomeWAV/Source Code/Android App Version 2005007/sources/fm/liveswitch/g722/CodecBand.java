package fm.liveswitch.g722;

class CodecBand {
    private int[] _a;
    private int[] _ap;
    private int[] _b;
    private int[] _bp;
    private int[] _d;
    private int _det;
    private int _nb;
    private int[] _p;
    private int[] _r;
    private int _s;
    private int[] _sg;
    private int _sp;
    private int _sz;

    public CodecBand() {
        setR(new int[3]);
        setA(new int[3]);
        setAp(new int[3]);
        setP(new int[3]);
        setD(new int[7]);
        setB(new int[7]);
        setBp(new int[7]);
        setSg(new int[7]);
    }

    public int[] getA() {
        return this._a;
    }

    public int[] getAp() {
        return this._ap;
    }

    public int[] getB() {
        return this._b;
    }

    public int[] getBp() {
        return this._bp;
    }

    public int[] getD() {
        return this._d;
    }

    public int getDet() {
        return this._det;
    }

    public int getNb() {
        return this._nb;
    }

    public int[] getP() {
        return this._p;
    }

    public int[] getR() {
        return this._r;
    }

    public int getS() {
        return this._s;
    }

    public int[] getSg() {
        return this._sg;
    }

    public int getSp() {
        return this._sp;
    }

    public int getSz() {
        return this._sz;
    }

    public void setA(int[] iArr) {
        this._a = iArr;
    }

    public void setAp(int[] iArr) {
        this._ap = iArr;
    }

    public void setB(int[] iArr) {
        this._b = iArr;
    }

    public void setBp(int[] iArr) {
        this._bp = iArr;
    }

    public void setD(int[] iArr) {
        this._d = iArr;
    }

    public void setDet(int i) {
        this._det = i;
    }

    public void setNb(int i) {
        this._nb = i;
    }

    public void setP(int[] iArr) {
        this._p = iArr;
    }

    public void setR(int[] iArr) {
        this._r = iArr;
    }

    public void setS(int i) {
        this._s = i;
    }

    public void setSg(int[] iArr) {
        this._sg = iArr;
    }

    public void setSp(int i) {
        this._sp = i;
    }

    public void setSz(int i) {
        this._sz = i;
    }
}
