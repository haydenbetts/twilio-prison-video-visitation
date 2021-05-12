package fm.liveswitch;

abstract class SignallingPublisherRequestArgs extends SignallingPublisherArgs {
    private boolean _cancel;

    public boolean getCancel() {
        return this._cancel;
    }

    public void setCancel(boolean z) {
        this._cancel = z;
    }

    protected SignallingPublisherRequestArgs() {
    }
}
