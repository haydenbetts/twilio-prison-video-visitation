package fm.liveswitch;

public class Md5Sha1HashContext {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Md5Sha1HashContext.class);
    private HashContext _md5 = new HashContext(HashType.Md5);
    private DataBuffer _output = __dataBufferPool.take(36);
    private HashContext _sha1 = new HashContext(HashType.Sha1);

    public DataBuffer compute(DataBuffer dataBuffer) {
        DataBuffer compute = this._md5.compute(dataBuffer);
        DataBuffer compute2 = this._sha1.compute(dataBuffer);
        this._output.write(compute, 0);
        this._output.write(compute2, 16);
        return this._output;
    }

    public void destroy() {
        this._md5.destroy();
        this._sha1.destroy();
        this._output.free();
    }
}
