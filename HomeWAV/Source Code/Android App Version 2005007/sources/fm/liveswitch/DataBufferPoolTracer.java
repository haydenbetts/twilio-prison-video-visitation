package fm.liveswitch;

public class DataBufferPoolTracer implements IDataBufferPool {
    private DataBufferPool _pool;
    private String _tag;

    public DataBufferPoolTracer(String str, DataBufferPool dataBufferPool) {
        if (str == null) {
            throw new RuntimeException(new Exception("Tag cannot be null."));
        } else if (dataBufferPool != null) {
            setTag(str);
            setPool(dataBufferPool);
        } else {
            throw new RuntimeException(new Exception("Pool cannot be null."));
        }
    }

    public DataBufferPool getPool() {
        return this._pool;
    }

    public PoolStatistics getStatistics() {
        return getPool().getOrAddTraceStatistics(getTag());
    }

    public String getTag() {
        return this._tag;
    }

    private void setPool(DataBufferPool dataBufferPool) {
        this._pool = dataBufferPool;
    }

    private void setTag(String str) {
        this._tag = str;
    }

    public DataBuffer take(int i) {
        return getPool().traceTake(i, getTag());
    }

    public DataBuffer take(int i, boolean z) {
        return getPool().traceTake(i, z, getTag());
    }

    public DataBuffer take(int i, boolean z, boolean z2) {
        return getPool().traceTake(i, z, z2, getTag());
    }
}
