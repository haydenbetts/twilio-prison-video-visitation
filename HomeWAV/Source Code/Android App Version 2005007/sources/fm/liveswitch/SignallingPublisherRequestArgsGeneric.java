package fm.liveswitch;

abstract class SignallingPublisherRequestArgsGeneric<T> extends SignallingPublisherRequestArgs {
    private T[] _requests;

    public T[] getRequests() {
        return this._requests;
    }

    public void setRequests(T[] tArr) {
        this._requests = tArr;
    }

    public SignallingPublisherRequestArgsGeneric(T[] tArr) {
        setRequests(tArr);
    }
}
