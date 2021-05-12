package fm.liveswitch;

public abstract class HashContextBase {
    private HashType _type;

    /* access modifiers changed from: protected */
    public abstract DataBuffer doCompute(DataBuffer dataBuffer);

    /* access modifiers changed from: protected */
    public abstract void doDestroy();

    public static DataBuffer compute(HashType hashType, DataBuffer dataBuffer) {
        HashContext hashContext = new HashContext(hashType);
        DataBuffer compute = hashContext.compute(dataBuffer);
        hashContext.destroy();
        return compute;
    }

    public static DataBuffer compute(HashType hashType, String str) {
        HashContext hashContext = new HashContext(hashType);
        DataBuffer compute = hashContext.compute(str);
        hashContext.destroy();
        return compute;
    }

    public DataBuffer compute(DataBuffer dataBuffer) {
        return doCompute(dataBuffer);
    }

    public DataBuffer compute(String str) {
        return doCompute(DataBuffer.wrap(Utf8.encode(str)));
    }

    public void destroy() {
        doDestroy();
    }

    public HashType getType() {
        return this._type;
    }

    public HashContextBase(HashType hashType) {
        setType(hashType);
    }

    private void setType(HashType hashType) {
        this._type = hashType;
    }
}
