package fm.liveswitch;

abstract class SignallingClientArgs {
    private SignallingClient _client;

    public SignallingClient getClient() {
        return this._client;
    }

    public void setClient(SignallingClient signallingClient) {
        this._client = signallingClient;
    }

    protected SignallingClientArgs() {
    }
}
