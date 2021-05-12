package fm.liveswitch;

class SignallingDisconnectArgs extends SignallingInputArgs {
    private IAction1<SignallingDisconnectCompleteArgs> _onComplete;

    public IAction1<SignallingDisconnectCompleteArgs> getOnComplete() {
        return this._onComplete;
    }

    public void setOnComplete(IAction1<SignallingDisconnectCompleteArgs> iAction1) {
        this._onComplete = iAction1;
    }
}
