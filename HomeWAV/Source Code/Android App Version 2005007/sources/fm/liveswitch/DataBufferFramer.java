package fm.liveswitch;

public class DataBufferFramer {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DataBufferFramer.class);
    private DataBuffer __dataBuffer = __dataBufferPool.take(4096);
    private int __readIndex = 0;
    private int __writeIndex = 0;

    public void destroy() {
        this.__dataBuffer.free();
    }

    public DataBuffer getAvailableDataBuffer() {
        return this.__dataBuffer.subset(this.__readIndex, getAvailableLength());
    }

    public int getAvailableLength() {
        return this.__writeIndex - this.__readIndex;
    }

    public int getFootprint() {
        return this.__dataBuffer.getLength();
    }

    public String getHexString() {
        return this.__dataBuffer.subset(this.__readIndex, getAvailableLength()).toHexString();
    }

    public DataBuffer read(IFunction1<DataBuffer, Integer> iFunction1) {
        int intValue;
        if (getAvailableLength() > 0 && (intValue = iFunction1.invoke(getAvailableDataBuffer()).intValue()) > 0 && intValue <= getAvailableLength()) {
            DataBuffer subset = this.__dataBuffer.subset(this.__readIndex, intValue);
            this.__readIndex += intValue;
            return subset;
        } else if (this.__readIndex <= 0) {
            return null;
        } else {
            DataBuffer take = __dataBufferPool.take(this.__dataBuffer.getLength());
            take.write(getAvailableDataBuffer());
            this.__dataBuffer.free();
            this.__dataBuffer = take;
            this.__writeIndex -= this.__readIndex;
            this.__readIndex = 0;
            return null;
        }
    }

    public void write(DataBuffer dataBuffer) {
        while (this.__writeIndex + dataBuffer.getLength() > this.__dataBuffer.getLength()) {
            DataBuffer allocate = DataBuffer.allocate(this.__dataBuffer.getLength() * 2);
            allocate.write(this.__dataBuffer);
            this.__dataBuffer.free();
            this.__dataBuffer = allocate;
        }
        this.__dataBuffer.write(dataBuffer, this.__writeIndex);
        this.__writeIndex += dataBuffer.getLength();
    }
}
