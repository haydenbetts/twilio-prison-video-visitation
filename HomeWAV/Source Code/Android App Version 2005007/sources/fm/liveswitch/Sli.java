package fm.liveswitch;

public class Sli {
    private DataBuffer _dataBuffer;

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public int getFirst() {
        return getDataBuffer().read13(0, 0);
    }

    public int getNumber() {
        return getDataBuffer().read13(1, 5);
    }

    public int getPictureId() {
        return getDataBuffer().read6(3, 2);
    }

    private void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    public void setFirst(int i) {
        getDataBuffer().write13(i, 0, 0);
    }

    public void setNumber(int i) {
        getDataBuffer().write13(i, 1, 5);
    }

    public void setPictureId(int i) {
        getDataBuffer().write6(i, 3, 2);
    }

    public Sli() {
        this(DataBuffer.allocate(4));
    }

    public Sli(DataBuffer dataBuffer) {
        setDataBuffer(dataBuffer);
    }
}
