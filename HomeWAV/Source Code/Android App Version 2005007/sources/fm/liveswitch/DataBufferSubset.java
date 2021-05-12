package fm.liveswitch;

public class DataBufferSubset extends DataBuffer {
    private DataBuffer _parent;

    public boolean getIsSubset() {
        return true;
    }

    public DataBuffer append(DataBuffer dataBuffer) {
        throw new RuntimeException(new Exception("Cannot append to a DataBuffer subset."));
    }

    public DataBuffer append(DataBuffer[] dataBufferArr) {
        throw new RuntimeException(new Exception("Cannot append to a DataBuffer subset."));
    }

    DataBufferSubset(DataBuffer dataBuffer, int i, int i2) {
        super((byte[]) null, i, i2, dataBuffer.getLittleEndian());
        setParent(dataBuffer);
    }

    public DataBuffer free() {
        getParent().free();
        return this;
    }

    public byte[] getData() {
        return getParent().getData();
    }

    public boolean getIsPooled() {
        return getParent().getIsPooled();
    }

    public DataBuffer getParent() {
        return this._parent;
    }

    public DataBuffer keep() {
        getParent().keep();
        return this;
    }

    public DataBuffer prepend(DataBuffer dataBuffer) {
        throw new RuntimeException(new Exception("Cannot prepend to a DataBuffer subset."));
    }

    public void prepend(DataBuffer[] dataBufferArr) {
        throw new RuntimeException(new Exception("Cannot prepend to a DataBuffer subset."));
    }

    private void setParent(DataBuffer dataBuffer) {
        this._parent = dataBuffer;
    }

    public DataBuffer subset(int i) {
        return getParent().subset(super.getIndex() + i, super.getLength() - i);
    }

    public DataBuffer subset(int i, int i2) {
        return getParent().subset(i + super.getIndex(), i2);
    }
}
