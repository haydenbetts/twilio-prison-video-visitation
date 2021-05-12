package fm.liveswitch;

public class FecProtectionParameters {
    private FecMaskType _maskType;
    private int _maxFrames;
    private int _rate;
    private boolean _useUepProtection;

    public FecProtectionParameters() {
        setRate(15);
        setUseUepProtection(false);
        setMaxFrames(1);
        setMaskType(FecMaskType.Random);
    }

    public FecMaskType getMaskType() {
        return this._maskType;
    }

    public int getMaxFrames() {
        return this._maxFrames;
    }

    public int getRate() {
        return this._rate;
    }

    public boolean getUseUepProtection() {
        return this._useUepProtection;
    }

    public void setMaskType(FecMaskType fecMaskType) {
        this._maskType = fecMaskType;
    }

    public void setMaxFrames(int i) {
        this._maxFrames = i;
    }

    public void setRate(int i) {
        this._rate = i;
    }

    public void setUseUepProtection(boolean z) {
        this._useUepProtection = z;
    }
}
