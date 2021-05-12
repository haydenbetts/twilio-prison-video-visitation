package fm.liveswitch;

class SignallingConnectArgs extends SignallingInputArgs {
    private IAction1<SignallingConnectFailureArgs> _onFailure;
    private IAction1<SignallingConnectSuccessArgs> _onSuccess;

    public IAction1<SignallingConnectFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingConnectSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public void setOnFailure(IAction1<SignallingConnectFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingConnectSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }
}
