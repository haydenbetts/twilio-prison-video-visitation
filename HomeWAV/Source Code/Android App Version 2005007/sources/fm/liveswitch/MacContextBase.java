package fm.liveswitch;

public abstract class MacContextBase {
    private MacType _type;

    /* access modifiers changed from: protected */
    public abstract DataBuffer doCompute(DataBuffer dataBuffer);

    /* access modifiers changed from: protected */
    public abstract void doDestroy();

    public static DataBuffer compute(MacType macType, DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        MacContext macContext = new MacContext(macType, dataBuffer);
        DataBuffer compute = macContext.compute(dataBuffer2);
        macContext.destroy();
        return compute;
    }

    public DataBuffer compute(DataBuffer dataBuffer) {
        return doCompute(dataBuffer);
    }

    public void destroy() {
        doDestroy();
    }

    public MacType getType() {
        return this._type;
    }

    public MacContextBase(MacType macType) {
        setType(macType);
    }

    private void setType(MacType macType) {
        this._type = macType;
    }
}
