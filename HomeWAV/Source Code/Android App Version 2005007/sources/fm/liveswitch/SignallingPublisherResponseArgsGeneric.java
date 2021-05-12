package fm.liveswitch;

abstract class SignallingPublisherResponseArgsGeneric<T> extends SignallingPublisherResponseArgs {
    private T[] _requests;
    private T[] _responses;

    public T[] getRequests() {
        return this._requests;
    }

    public T[] getResponses() {
        return this._responses;
    }

    public void setRequests(T[] tArr) {
        this._requests = tArr;
    }

    public void setResponses(T[] tArr) {
        this._responses = tArr;
    }

    public SignallingPublisherResponseArgsGeneric(T[] tArr, T[] tArr2) {
        setRequests(tArr);
        setResponses(tArr2);
    }
}
