package fm.liveswitch;

abstract class SignallingClientRequestArgs extends SignallingClientArgs {
    private boolean _cancel;

    public boolean getCancel() {
        return this._cancel;
    }

    public void setCancel(boolean z) {
        this._cancel = z;
    }

    protected SignallingClientRequestArgs() {
    }
}
