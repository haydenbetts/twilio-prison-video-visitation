package fm.liveswitch;

class IcePreferenceSpeedBlock {
    private int _start;
    private int _stop;

    public int getStart() {
        return this._start;
    }

    public int getStop() {
        return this._stop;
    }

    public IcePreferenceSpeedBlock(int i, int i2) {
        setStart(i);
        setStop(i2);
    }

    public void setStart(int i) {
        this._start = i;
    }

    public void setStop(int i) {
        this._stop = i;
    }
}
