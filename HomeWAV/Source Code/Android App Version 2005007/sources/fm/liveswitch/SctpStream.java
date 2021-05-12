package fm.liveswitch;

class SctpStream {
    private int _id;
    private int _nextSsn;

    public int getId() {
        return this._id;
    }

    public int getNextSsn() {
        return this._nextSsn;
    }

    public SctpStream(int i) {
        setNextSsn(0);
        setId(i);
    }

    private void setId(int i) {
        this._id = i;
    }

    public void setNextSsn(int i) {
        this._nextSsn = i;
    }
}
