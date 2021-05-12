package fm.liveswitch;

public class RedFecConfig {
    private int __activationThreshold = 5;
    private int __minimumReportsBeforeFec = 2;
    private boolean _bursty;
    private FecProtectionParameters _deltaParameters;
    private boolean _disabled;
    private FecProtectionParameters _keyFrameParameters;

    public int getActivationThreshold() {
        return this.__activationThreshold;
    }

    public boolean getBursty() {
        return this._bursty;
    }

    public FecProtectionParameters getDeltaParameters() {
        return this._deltaParameters;
    }

    public boolean getDisabled() {
        return this._disabled;
    }

    public FecProtectionParameters getKeyFrameParameters() {
        return this._keyFrameParameters;
    }

    /* access modifiers changed from: package-private */
    public int getMinimumReportsBeforeFec() {
        return this.__minimumReportsBeforeFec;
    }

    public RedFecConfig() {
        setDisabled(true);
        setBursty(Platform.getInstance().getIsMobile());
    }

    public void setActivationThreshold(int i) {
        this.__activationThreshold = MathAssistant.max(MathAssistant.min(i, 100), 0);
    }

    public void setBursty(boolean z) {
        this._bursty = z;
    }

    public void setDeltaParameters(FecProtectionParameters fecProtectionParameters) {
        this._deltaParameters = fecProtectionParameters;
    }

    /* access modifiers changed from: package-private */
    public void setDisabled(boolean z) {
        this._disabled = z;
    }

    public void setKeyFrameParameters(FecProtectionParameters fecProtectionParameters) {
        this._keyFrameParameters = fecProtectionParameters;
    }

    /* access modifiers changed from: package-private */
    public void setMinimumReportsBeforeFec(int i) {
        this.__minimumReportsBeforeFec = i;
    }
}
