package fm.liveswitch;

public class CircularBuffer {
    private boolean __allowRead;
    private DataBuffer __buffer;
    private int _latency;
    private boolean _littleEndian;
    private int _readOffset;
    private int _writeOffset;

    public CircularBuffer(int i, int i2) {
        this(i, i2, false);
    }

    public CircularBuffer(int i, int i2, boolean z) {
        this.__allowRead = false;
        if (i > 0) {
            this.__buffer = DataBuffer.allocate(i, z);
            setReadOffset(0);
            setWriteOffset(getLatency());
            setLatency(i2);
            setLittleEndian(z);
            return;
        }
        throw new RuntimeException(new Exception("Cannot create a circular buffer with no length."));
    }

    public int getAvailable() {
        return getWriteOffset() - getReadOffset();
    }

    public int getLatency() {
        return this._latency;
    }

    public int getLength() {
        return this.__buffer.getLength();
    }

    public boolean getLittleEndian() {
        return this._littleEndian;
    }

    public int getReadOffset() {
        return this._readOffset;
    }

    public int getWriteOffset() {
        return this._writeOffset;
    }

    public DataBuffer read(int i) {
        if (i > getLength()) {
            return null;
        }
        DataBuffer allocate = DataBuffer.allocate(i, getLittleEndian());
        if (this.__allowRead) {
            if (getReadOffset() + i <= getLength()) {
                allocate.write(this.__buffer.subset(getReadOffset(), i), 0);
                this.__buffer.set((byte) 0, getReadOffset(), i);
            } else {
                int length = this.__buffer.getLength() - getReadOffset();
                int i2 = i - length;
                allocate.write(this.__buffer.subset(getReadOffset(), length), 0);
                allocate.write(this.__buffer.subset(0, i2), length);
                this.__buffer.set((byte) 0, getReadOffset(), length);
                this.__buffer.set((byte) 0, 0, i2);
            }
            setReadOffset((getReadOffset() + i) % getLength());
        }
        return allocate;
    }

    private void setLatency(int i) {
        this._latency = i;
    }

    private void setLittleEndian(boolean z) {
        this._littleEndian = z;
    }

    private void setReadOffset(int i) {
        this._readOffset = i;
    }

    private void setWriteOffset(int i) {
        this._writeOffset = i;
    }

    public void write(DataBuffer dataBuffer) {
        int i;
        int i2 = 0;
        while (i2 < dataBuffer.getLength()) {
            if (dataBuffer.getLength() - i2 < getLength() - getWriteOffset()) {
                i = dataBuffer.getLength() - i2;
            } else {
                i = getLength() - getWriteOffset();
            }
            this.__buffer.write(dataBuffer.subset(i2, i), getWriteOffset());
            setWriteOffset((getWriteOffset() + i) % getLength());
            i2 += i;
        }
        if (!this.__allowRead && getWriteOffset() >= getReadOffset() + getLatency()) {
            this.__allowRead = true;
        }
    }
}
