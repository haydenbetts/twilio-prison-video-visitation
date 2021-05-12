package fm.liveswitch;

public class CircularDataBuffer extends DataBuffer {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(CircularDataBuffer.class);
    private DataBuffer __innerPooledBuffer;
    private int __lastWriteSize;
    private boolean __writeFlag;

    public void appendDataBuffer(DataBuffer dataBuffer) {
        BitAssistant.copy(dataBuffer.getData(), dataBuffer.getIndex(), getData(), beginWrite(dataBuffer.getLength()), dataBuffer.getLength());
        endWrite(dataBuffer.getLength());
    }

    public int beginWrite(int i) {
        if (this.__writeFlag) {
            throw new RuntimeException(new Exception("Must call EndWrite before calling BeginWrite again."));
        } else if (i >= 1) {
            int length = super.getLength() + i;
            if (length > ArrayExtensions.getLength(getData())) {
                DataBuffer dataBuffer = this.__innerPooledBuffer;
                DataBuffer take = __dataBufferPool.take(length);
                BitAssistant.copy(this.__innerPooledBuffer.getData(), super.getIndex(), take.getData(), 0, super.getLength());
                this.__innerPooledBuffer = take;
                dataBuffer.free();
                super.setIndex(0);
            }
            this.__writeFlag = true;
            this.__lastWriteSize = i;
            if (super.getIndex() + length > ArrayExtensions.getLength(getData())) {
                BitAssistant.copy(this.__innerPooledBuffer.getData(), super.getIndex(), this.__innerPooledBuffer.getData(), 0, super.getLength());
                super.setIndex(0);
            }
            int length2 = super.getLength() + super.getIndex();
            super.setLength(length);
            return length2;
        } else {
            throw new RuntimeException(new Exception("Must be a positive number greater than 1."));
        }
    }

    CircularDataBuffer(DataBuffer dataBuffer) {
        super((byte[]) null, 0, 0, false);
        this.__innerPooledBuffer = dataBuffer;
    }

    public static CircularDataBuffer create(int i) {
        return new CircularDataBuffer(__dataBufferPool.take(i));
    }

    public void discard(int i) {
        if (this.__writeFlag) {
            throw new RuntimeException(new Exception("Cannot discard in the middle of a write."));
        } else if (i >= 0) {
            int index = super.getIndex() + i;
            if (i <= super.getLength()) {
                super.setIndex(index);
                super.setLength(super.getLength() - i);
                return;
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Amount ({0}) is greater than the amount of remaining data ({1}).", IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(super.getLength())))));
        } else {
            throw new RuntimeException(new Exception("Amount must be greater or equal to 0."));
        }
    }

    public void endWrite(int i) {
        if (!this.__writeFlag) {
            throw new RuntimeException(new Exception("Must call BeginWrite before calling EndWrite."));
        } else if (i >= 0) {
            this.__writeFlag = false;
            super.setLength(super.getLength() - (this.__lastWriteSize - i));
        } else {
            throw new RuntimeException(new Exception("Value must be greater or equal to 0."));
        }
    }

    public DataBuffer free() {
        this.__innerPooledBuffer.free();
        this.__innerPooledBuffer = null;
        return this;
    }

    public int getCapacity() {
        return ArrayExtensions.getLength(getData());
    }

    public byte[] getData() {
        return this.__innerPooledBuffer.getData();
    }
}
