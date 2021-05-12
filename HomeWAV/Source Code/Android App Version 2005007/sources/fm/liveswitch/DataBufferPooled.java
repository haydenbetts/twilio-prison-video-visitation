package fm.liveswitch;

class DataBufferPooled extends DataBuffer {
    private AtomicInteger __counter = new AtomicInteger();
    private DataBufferPool __pool;
    private String __tag;

    public boolean getIsPooled() {
        return true;
    }

    public DataBufferPooled(DataBufferPool dataBufferPool, byte[] bArr, int i, boolean z, String str) {
        super(bArr, 0, i, z);
        this.__pool = dataBufferPool;
        this.__tag = str;
        keep();
    }

    public int decrementRetain() {
        return this.__counter.decrement();
    }

    public DataBuffer free() {
        this.__pool.returnBuffer(this, this.__tag);
        return this;
    }

    public int getRetainCount() {
        return this.__counter.getValue();
    }

    public byte[] invalidate() {
        byte[] innerData = super.getInnerData();
        super.setInnerData((byte[]) null);
        return innerData;
    }

    public DataBuffer keep() {
        if (super.getInnerData() != null) {
            this.__counter.increment();
            return this;
        }
        throw new RuntimeException(new Exception("DataBuffer already invalidated."));
    }
}
